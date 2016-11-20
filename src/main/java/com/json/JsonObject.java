package com.json;


import com.json.feature.SerializeFeature;
import com.json.util.Args;
import com.json.util.StringUtil;
import com.json.util.Unicode;

import java.io.Serializable;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 普通的json对象，是json属性和值的容器。
 */
public class JsonObject extends Json implements Serializable,Iterable<Map.Entry<String,Object>>,JsonAware {
    private static final long serialVersionUID = 996072007977713757L;

    private Map<String, Object> map;

    private static final Null NULL = Null.instance;

    protected Map<String, Object> createMap() {
        return new LinkedHashMap<String, Object>();
    }

    protected Map<String, Object> createMap(Map<String, Object> map) {
        return new LinkedHashMap<String, Object>(map);
    }

    public JsonObject() {
        this.map = createMap();
    }

    public JsonObject(JsonObject jsonObject) {
        this.map = createMap(jsonObject.map);
    }

    public JsonObject(Map<String, Object> map) {
        this.map = createMap(map);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsonObject that = (JsonObject) o;

        return map != null ? map.equals(that.map) : that.map == null;

    }

    @Override
    public int hashCode() {
        return map != null ? map.hashCode() : 0;
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return size() > 0;
    }

    /**
     * 从json中取出属性值
     * @param name 属性名，不可为空
     * @return 对应的属性值，如果属性名不存在或者对应值为null都会返回null.
     */
    public Object get(String name) {
        return get(name, null);
    }

    /**
     * 从json中取出属性值
     * @param name  属性名，不可为空
     * @param defaultValue 如果属性名不存在或者对应值为null则返回该值
     * @return 对应的属性值
     */
    public Object get(String name, Object defaultValue) {
        Args.notNull(name, "the name");
        Object object = map.get(name);
        if (object == null || object instanceof Null) {
            return defaultValue;
        }
        return object;
    }

    /**
     * 将属性键值对放入json对象
     * @param name 键，不可为空
     * @param o 值
     */
    public void put(String name,Object o){
        Args.notNull(name, "the name");
        map.put(name,o==null?NULL:o);
    }

    /**
     * json对象中是否包含该键
     * @param name 键的名称，不可为空
     * @return 是否包含该键
     */
    public boolean containsKey(String name){
        Args.notNull(name, "the name");
        return map.containsKey(name);
    }

    /**
     * json对象中是否包含该值
     * @param value 键的值
     * @return 是否包含该键值
     */
    public boolean containsValue(Object value){
        return map.containsValue(value==null?NULL:value);
    }

    public Object remove(String name){
        Args.notNull(name, "the name");
        Object o = map.remove(name);
        if(o instanceof Null){
            return null;
        }
        return o;
    }

    public Map<String, Object> toMap() {
        Map<String,Object> map = createMap();
        for(Map.Entry<String,Object> entry:this.map.entrySet()){
            String name = entry.getKey();
            Object value = (entry.getValue() instanceof Null?null:entry.getValue());
            map.put(name,value);
        }
        return map;
    }

    @Override
    public Iterator<Map.Entry<String,Object>> iterator(){
        return new JsonObjectItr();
    }

    public void clear(){
        map.clear();
    }

    @Override
    public String toJsonString(SerializeFeature feature) {
        StringBuilder stringBuilder = new StringBuilder("{");
        int i = 0;
        for (Map.Entry<String, Object> entry : this) {

            Object value = entry.getValue();
            if(value!=null||feature.writesNullValue()){
                if (i > 0) {
                    stringBuilder.append(",");
                }
                stringBuilder.append(StringUtil.jsonString(entry.getKey()));
                stringBuilder.append(':');
                stringBuilder.append(Json.toJsonString(entry.getValue(), feature));
                i++;
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }


    private class JsonObjectItr implements Iterator<Map.Entry<String,Object>>{

        private Iterator<Map.Entry<String,Object>> innerIterator = map.entrySet().iterator();

        @Override
        public boolean hasNext() {
            return innerIterator.hasNext();
        }

        @Override
        public Map.Entry<String, Object> next() {
            Map.Entry<String, Object> entry = innerIterator.next();
            String name = entry.getKey();
            Object value = (entry.getValue() instanceof Null?null:entry.getValue());
            return new SimpleEntry<String, Object>(name,value);
        }

        //@Override
        public void remove() {
            this.innerIterator.remove();
        }
    }

    private class SimpleEntry<K,V> implements Map.Entry<K,V>{

        private final K key;
        private V value;

        public SimpleEntry(final K key,final V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SimpleEntry<?, ?> that = (SimpleEntry<?, ?>) o;

            if (key != null ? !key.equals(that.key) : that.key != null) return false;
            return value != null ? value.equals(that.value) : that.value == null;

        }

        @Override
        public int hashCode() {
            int result = key != null ? key.hashCode() : 0;
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }


}
