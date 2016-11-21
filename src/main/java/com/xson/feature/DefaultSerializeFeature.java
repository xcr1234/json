package com.xson.feature;


public class DefaultSerializeFeature implements SerializeFeature{

    public DefaultSerializeFeature(){}

    public static final DefaultSerializeFeature globalDefaultFuture  = new DefaultSerializeFeature();

    private boolean writesNullValue = false;
    private boolean writeCollectionAsJson = true;
    private boolean unicode = false;
    private String dateFormat;

    public DefaultSerializeFeature setWritesNullValue(boolean writesNullValue) {
        this.writesNullValue = writesNullValue;
        return this;
    }

    public DefaultSerializeFeature setWriteCollectionAsJson(boolean writeCollectionAsJson) {
        this.writeCollectionAsJson = writeCollectionAsJson;
        return this;
    }

    public DefaultSerializeFeature setUnicode(boolean unicode) {
        this.unicode = unicode;
        return this;
    }

    @Override
    public boolean writesNullValue() {
        return writesNullValue;
    }

    @Override
    public boolean writeCollectionAsJson() {
        return writeCollectionAsJson;
    }

    @Override
    public String dateFormat() {
        return dateFormat;
    }

    public DefaultSerializeFeature setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
        return this;
    }

    @Override
    public boolean unicode() {
        return unicode;
    }
}
