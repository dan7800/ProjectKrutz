import re
import uuid
import sqlite3
import requests

import sys
from pprint import pprint

from googleplay-api.config import *
from googleplay-api.googleplay import GooglePlayAPI
from googleplay-api.helpers import sizeof_fmt

from os import path
from scrapy import log
from scrapy import signals
from scrapy.exceptions import DropItem
from scrapy.xlib.pydispatch import dispatcher
from scrapy.contrib.pipeline.files import FilesPipeline
from scrapy.http import Request
from scraper.items import ApkItem

# Creates a unique identifier for the APK
class UniqueIdentifierPipeline(object):
    def process_item(self, item, spider):
        item['id'] = str(uuid.uuid4())
        return item

# Stores the APK information in the database
class SQLiteStorePipeline(object):
    filename = '../Evolution of Android Applications.sqlite'
    
    def __init__(self):
        self.conn = None
        dispatcher.connect(self.initialize, signals.engine_started)
        dispatcher.connect(self.finalize, signals.engine_stopped)

    # Tries to insert the APK file's information into the database.
    # If an error occurs or the APK file is a duplicate, the APK file 
    # is not downloaded and the APK file's information is not inserted 
    # into the database.
    def process_item(self, item, spider):
        try:
            self.conn.execute('INSERT INTO ApkInformation (Name, Version, Developer, Genre, UserRating, DatePublished, FileSize, NumberOfDownloads, OperatingSystems, URL, SourceId, ApkId, CollectionDate) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)', (item['name'], item['software_version'], item['developer'], item['genre'], item['score'], item['date_published'], item['file_size'], item['num_downloads'], item['operating_systems'], item['url'], item['source_id'], item['id'], item['collection_date']))
            return item
        except Exception as e:
            raise DropItem('%s <%s>' % (e.message, item['url']))

    def initialize(self):
        if path.exists(self.filename):
            self.conn = sqlite3.connect(self.filename)
        else:
            log.msg('File does not exist: %s' % self.filename, level=log.ERROR)
 
    def finalize(self):
        if self.conn is not None:
            self.conn.commit()
            self.conn.close()
            self.conn = None

# Uses https://github.com/egirault/googleplay-api to download APK files from Google Play
class GooglePlayDownloadPipeline(object):
    def process_item(self, item, spider):
        if item['source_id'] == 2:
            package_name = item['url'][item['url'].find('id=') + 3:]
            filename = 'downloads/full/%s.apk' % item['id']

            # Connect
            api = GooglePlayAPI(ANDROID_ID)
            api.login(GOOGLE_LOGIN, GOOGLE_PASSWORD, AUTH_TOKEN)

            # Get the version code and the offer type from the app details
            m = api.details(package_name)
            doc = m.docV2
            vc = doc.details.appDetails.versionCode
            ot = doc.offer[0].offerType

            # Download
            log.msg('Downloading file from <%s>' % item['url'], level=log.INFO)
            data = api.download(package_name, vc, ot)
            try:
                open(filename, 'wb').write(data)
            except IOError as e:
                log.msg('%s <%s>' % (e, item['url']), level=log.ERROR)
        return item

# Names the downloaded file with its unique identifier
class APKFilesPipeline(FilesPipeline):
    def get_media_requests(self, item, info):
        return [Request(x, meta={'apk_id': item['id']}) for x in item.get(self.FILES_URLS_FIELD, [])]

    def file_path(self, request, response=None, info=None):
        media_ext = path.splitext(request.url)[1]

        # For Google Play spider, which yields something along the lines of ".apk?h=syrPj2oViqBMGpbX5XEB7g&t=1396043020"
        if len(media_ext) > 4 and media_ext.startswith('.apk'):
            media_ext = media_ext[:4]
        # Ignore any files that are not APK files
        elif media_ext != '.apk':
            raise DropItem('File is not an APK file: %s' % request.url)

        return 'full/%s%s' % (request.meta['apk_id'], media_ext)
