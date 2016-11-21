package com.xson;


import com.xson.feature.SerializeFeature;

public interface JsonObjectAware {
    void toJson(JsonObject jsonObject, SerializeFeature feature);
}
