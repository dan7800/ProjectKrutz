package com.twitter.android.provider;

import android.net.Uri;
import android.provider.BaseColumns;

public final class ac
  implements BaseColumns
{
  public static final Uri a = Uri.parse("content://com.twitter.android.provider.TwitterProvider/messages_received_view");
  public static final Uri b = Uri.parse("content://com.twitter.android.provider.TwitterProvider/messages_sent_view");

  public ac()
  {
  }
}

/* Location:
 * Qualified Name:     com.twitter.android.provider.ac
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.6.1-SNAPSHOT
 */