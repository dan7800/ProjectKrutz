package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.type.ResolvedType;
import java.io.Serializable;
import java.lang.reflect.Modifier;

public abstract class JavaType extends ResolvedType
  implements Serializable
{
  private static final long serialVersionUID = -5321897246493723158L;
  protected final Class<?> _class;
  protected final int _hashCode;
  protected final Object _typeHandler;
  protected final Object _valueHandler;

  protected JavaType(Class<?> paramClass, int paramInt, Object paramObject1, Object paramObject2)
  {
    this._class = paramClass;
    this._hashCode = (paramInt + paramClass.getName().hashCode());
    this._valueHandler = paramObject1;
    this._typeHandler = paramObject2;
  }

  protected void _assertSubclass(Class<?> paramClass1, Class<?> paramClass2)
  {
    if (!this._class.isAssignableFrom(paramClass1))
      throw new IllegalArgumentException("Class " + paramClass1.getName() + " is not assignable to " + this._class.getName());
  }

  protected abstract JavaType _narrow(Class<?> paramClass);

  protected JavaType _widen(Class<?> paramClass)
  {
    return _narrow(paramClass);
  }

  public JavaType containedType(int paramInt)
  {
    return null;
  }

  public int containedTypeCount()
  {
    return 0;
  }

  public String containedTypeName(int paramInt)
  {
    return null;
  }

  public abstract boolean equals(Object paramObject);

  public JavaType forcedNarrowBy(Class<?> paramClass)
  {
    if (paramClass == this._class)
      return this;
    JavaType localJavaType = _narrow(paramClass);
    if (this._valueHandler != localJavaType.getValueHandler())
      localJavaType = localJavaType.withValueHandler(this._valueHandler);
    if (this._typeHandler != localJavaType.getTypeHandler())
      localJavaType = localJavaType.withTypeHandler(this._typeHandler);
    return localJavaType;
  }

  public JavaType getContentType()
  {
    return null;
  }

  public JavaType getKeyType()
  {
    return null;
  }

  public final Class<?> getRawClass()
  {
    return this._class;
  }

  public <T> T getTypeHandler()
  {
    return this._typeHandler;
  }

  public <T> T getValueHandler()
  {
    return this._valueHandler;
  }

  public boolean hasGenericTypes()
  {
    return containedTypeCount() > 0;
  }

  public final int hashCode()
  {
    return this._hashCode;
  }

  public boolean isAbstract()
  {
    return Modifier.isAbstract(this._class.getModifiers());
  }

  public boolean isArrayType()
  {
    return false;
  }

  public boolean isCollectionLikeType()
  {
    return false;
  }

  public boolean isConcrete()
  {
    if ((0x600 & this._class.getModifiers()) == 0);
    while (this._class.isPrimitive())
      return true;
    return false;
  }

  public abstract boolean isContainerType();

  public final boolean isEnumType()
  {
    return this._class.isEnum();
  }

  public final boolean isFinal()
  {
    return Modifier.isFinal(this._class.getModifiers());
  }

  public final boolean isInterface()
  {
    return this._class.isInterface();
  }

  public boolean isMapLikeType()
  {
    return false;
  }

  public final boolean isPrimitive()
  {
    return this._class.isPrimitive();
  }

  public boolean isThrowable()
  {
    return Throwable.class.isAssignableFrom(this._class);
  }

  public JavaType narrowBy(Class<?> paramClass)
  {
    if (paramClass == this._class)
      return this;
    _assertSubclass(paramClass, this._class);
    JavaType localJavaType = _narrow(paramClass);
    if (this._valueHandler != localJavaType.getValueHandler())
      localJavaType = localJavaType.withValueHandler(this._valueHandler);
    if (this._typeHandler != localJavaType.getTypeHandler())
      localJavaType = localJavaType.withTypeHandler(this._typeHandler);
    return localJavaType;
  }

  public abstract JavaType narrowContentsBy(Class<?> paramClass);

  public abstract String toString();

  public JavaType widenBy(Class<?> paramClass)
  {
    if (paramClass == this._class)
      return this;
    _assertSubclass(this._class, paramClass);
    return _widen(paramClass);
  }

  public abstract JavaType widenContentsBy(Class<?> paramClass);

  public abstract JavaType withContentTypeHandler(Object paramObject);

  public abstract JavaType withContentValueHandler(Object paramObject);

  public abstract JavaType withTypeHandler(Object paramObject);

  public abstract JavaType withValueHandler(Object paramObject);
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.JavaType
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.6.1-SNAPSHOT
 */