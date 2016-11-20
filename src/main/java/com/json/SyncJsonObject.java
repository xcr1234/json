package com.json;


import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 线程安全的{@link JsonObject}
 */
public class SyncJsonObject extends JsonObject{
    private static final long serialVersionUID = -8800176015516190396L;

    public SyncJsonObject() {
    }

    public SyncJsonObject(JsonObject jsonObject) {
        super(jsonObject);
    }

    public SyncJsonObject(Map<String, Object> map) {
        super(map);
    }

    @Override
    protected Map<String, Object> createMap() {
        return Collections.synchronizedMap(new LinkedHashMap<String, Object>());
    }

    @Override
    protected Map<String, Object> createMap(Map<String, Object> map) {
        return Collections.synchronizedMap(new LinkedHashMap<String, Object>(map));
    }
}
