package com.xson.feature;


import com.xson.cast.NumberCaster;
import com.xson.cast.TypeCaster;

public class DefaultDeserializeFeature implements DeserializeFeature {

    public static final DefaultDeserializeFeature globalDefaultDeserializeFeature = new DefaultDeserializeFeature();

    private NumberCaster numberCaster = TypeCaster.instance;
    private boolean readNullValue = true;
    private boolean base64 = false;

    @Override
    public boolean unicode() {
        return unicode;
    }

    @Override
    public boolean base64() {
        return base64;
    }

    public void setBase64(boolean base64) {
        this.base64 = base64;
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
