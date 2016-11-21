package com.xson;


import com.xson.feature.DeserializeFeature;

public interface JsonBeanAware {
    void parse(JsonObject jsonObject, DeserializeFeature feature);
}
