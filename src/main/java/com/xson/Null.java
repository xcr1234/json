package com.xson;


import com.xson.feature.SerializeFeature;

import java.io.*;

final class Null implements Externalizable,JsonAware{
    private static final long serialVersionUID = -5850043100711134472L;


    public static final Null instance = new Null();

    public Null(){}

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

    private static final byte[] BYTES = {-43, -30, -64, -17, -65, -43, -65, -43, -56, -25, -46, -78}; //（这是一个gbk格式的字符串）

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.write(BYTES);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        in.read(new byte[BYTES.length]);
    }

    @Override
    public String toJsonString(SerializeFeature feature) {
        return "null";
    }
}
