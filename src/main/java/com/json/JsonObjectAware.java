package com.json;


import com.json.feature.SerializeFeature;

public interface JsonObjectAware {
    void toJson(JsonObject jsonObject, SerializeFeature feature);
}
