package com.json;


import com.json.feature.SerializeFeature;

public interface JsonAware {
    String toJsonString(SerializeFeature feature);
}
