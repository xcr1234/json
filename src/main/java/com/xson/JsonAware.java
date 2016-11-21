package com.xson;


import com.xson.feature.SerializeFeature;

public interface JsonAware {
    String toJsonString(SerializeFeature feature);
}
