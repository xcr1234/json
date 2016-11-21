package com.xson.feature;


import com.xson.util.NumberCaster;
import com.xson.util.TypeCaster;

public class DefaultDeserializeFeature implements DeserializeFeature {

    public static final DefaultDeserializeFeature globalDefaultDeserializeFeature = new DefaultDeserializeFeature();

    private NumberCaster numberCaster = TypeCaster.instance;
    private boolean readNullValue = true;

    @Override
    public boolean unicode() {
        return unicode;
    }
    private boolean unicode = false;

    public void setNumberCaster(NumberCaster numberCaster) {
        this.numberCaster = numberCaster;
    }
    public void setUnicode(boolean unicode) {
        this.unicode = unicode;
    }
    public void setReadNullValue(boolean readNullValue) {
        this.readNullValue = readNullValue;
    }

    @Override
    public NumberCaster numberCaster() {
        return numberCaster;
    }

    @Override
    public boolean readNullValue() {
        return readNullValue;
    }
}
