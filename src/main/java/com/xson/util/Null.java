package com.xson.util;


import com.xson.JsonAware;
import com.xson.feature.SerializeFeature;

import java.io.*;

public final class Null implements Serializable,JsonAware {
    private static final long serialVersionUID = -5850043100711134472L;


    public static final Null instance = new Null();

    private Null(){}

    @Override
    public int hashCode() {
        return 0;
    }


    @Override
    public boolean equals(Object obj) {
        return obj == null || obj instanceof Null;
    }

    @Override
    public String toString() {
        return "null";
    }

    @Override
    public String toJsonString(SerializeFeature feature) {
        return "null";
    }
}
