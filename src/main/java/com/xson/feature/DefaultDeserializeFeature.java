package com.xson.feature;


public class DefaultDeserializeFeature implements DeserializeFeature {

    public static final DefaultDeserializeFeature globalDefaultDeserializeFeature = new DefaultDeserializeFeature();

    @Override
    public boolean unicode() {
        return unicode;
    }
    private boolean unicode = false;

    public void setUnicode(boolean unicode) {
        this.unicode = unicode;
    }
}
