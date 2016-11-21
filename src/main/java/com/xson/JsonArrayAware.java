package com.xson;

import com.xson.feature.SerializeFeature;

public interface JsonArrayAware {
    void toJson(JsonArray jsonArray, SerializeFeature feature);
}
