package com.json.feature;


public class DefaultSerializeFeature implements SerializeFeature{

    public DefaultSerializeFeature(){}

    public static final DefaultSerializeFeature globalDefaultFuture  = new DefaultSerializeFeature();

    private boolean writesNullValue = false;
    private boolean writeCollectionAsJson = true;

    private String dateFormat;

    public  void setWritesNullValue(boolean writesNullValue) {
        this.writesNullValue = writesNullValue;
    }

    public void setWriteCollectionAsJson(boolean writeCollectionAsJson) {
        this.writeCollectionAsJson = writeCollectionAsJson;
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

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

}
