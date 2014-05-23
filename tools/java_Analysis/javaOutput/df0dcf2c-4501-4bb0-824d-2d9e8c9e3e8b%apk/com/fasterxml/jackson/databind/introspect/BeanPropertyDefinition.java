package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty;

public abstract class BeanPropertyDefinition
{
  public BeanPropertyDefinition()
  {
  }

  public boolean couldDeserialize()
  {
    return getMutator() != null;
  }

  public AnnotationIntrospector.ReferenceProperty findReferenceType()
  {
    return null;
  }

  public Class<?>[] findViews()
  {
    return null;
  }

  public abstract AnnotatedMember getAccessor();

  public abstract AnnotatedField getField();

  public abstract AnnotatedMethod getGetter();

  public abstract AnnotatedMember getMutator();

  public abstract String getName();

  public AnnotatedMember getPrimaryMember()
  {
    return null;
  }

  public abstract AnnotatedMethod getSetter();

  public abstract boolean hasConstructorParameter();

  public abstract boolean hasField();

  public abstract boolean hasGetter();

  public abstract boolean hasSetter();

  public abstract boolean isExplicitlyIncluded();

  public boolean isTypeId()
  {
    return false;
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.6.1-SNAPSHOT
 */