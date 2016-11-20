package com.json;

import com.json.feature.DeserializeFeature;
import com.json.feature.DefaultSerializeFeature;
import com.json.feature.SerializeFeature;
import com.json.util.StringUtil;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <pre>
 * 所有Json对象的基类，以及提供了Json转换所需要的静态方法。
 *
 * 用法：
 * {@link #toJsonString(Object, SerializeFeature)} }
 * {@link #parseObject(String, DeserializeFeature)}
 * {@link #parseArray(String, DeserializeFeature)}
 * </pre>
 */
public abstract class Json {

    @SuppressWarnings("unchecked")
    public static String toJsonString(Object obj, SerializeFeature feature) {
        if (obj == null) {
            return "null";
        }
        if (feature == null) {
            feature = DefaultSerializeFeature.globalDefaultFuture;
        }
        if (obj instanceof JsonAware) {
            return ((JsonAware) obj).toJsonString(feature);
        }else if(obj instanceof JsonObjectAware) {
            JsonObjectAware jsonObjectAware = (JsonObjectAware)obj;
            JsonObject jsonObject = new JsonObject();
            jsonObjectAware.toJson(jsonObject,feature);
            return jsonObject.toJsonString(feature);
        } else if (obj instanceof String) {
            return StringUtil.jsonString((String) obj);
        } else if (obj instanceof Date) {
            Date date = (Date) obj;
            if (feature.dateFormat() == null) {
                return StringUtil.jsonString(date.toString());
            } else {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(feature.dateFormat());
                return StringUtil.jsonString(simpleDateFormat.format(date));
            }
        } else if (obj instanceof List || obj instanceof Set) {
            Collection<Object> collection = (Collection) obj;
            if (feature.writeCollectionAsJson()) {
                return new JsonArray(collection).toJsonString(feature);
            } else {
                return StringUtil.jsonString(collection.toString());
            }
        } else if (obj instanceof Map) {
            Map map = (Map) obj;
            if (feature.writeCollectionAsJson()) {
                JsonObject jsonObject = new JsonObject();
                for (Object o : map.entrySet()) {
                    Map.Entry entry = (Map.Entry) o;
                    jsonObject.put(String.valueOf(entry.getKey()), entry.getValue());
                }
                return jsonObject.toJsonString(feature);
            } else {
                return StringUtil.jsonString(map.toString());
            }
        } else if (obj instanceof Number || obj instanceof Boolean) {
            return String.valueOf(obj);
        }
        return StringUtil.jsonString(obj.toString());
    }

    public static JsonObject parseObject(String jsonString, DeserializeFeature feature) {
        return null;
    }

    public static JsonArray parseArray(String jsonString, DeserializeFeature feature) {
        return null;
    }

    public static String toJsonString(Object obj) {
        return toJsonString(obj, null);
    }

    public static JsonObject parseObject(String jsonString) {
        return parseObject(jsonString, null);
    }

    public static JsonArray parseArray(String jsonString) {
        return parseArray(jsonString, null);
    }

    @Override
    public String toString() {
        return toJsonString(this, null);
    }

    public String toString(SerializeFeature feature) {
        return toJsonString(this, feature);
    }
}
