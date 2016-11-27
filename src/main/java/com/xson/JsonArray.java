package com.xson;

import com.xson.cast.TypeUtils;
import com.xson.feature.SerializeFeature;
import com.xson.util.Args;
import com.xson.util.Null;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

/**
 * 表示json数组，由List实现。
 */
public class JsonArray extends Json implements Serializable,Iterable<Object>,JsonAware {
    private static final long serialVersionUID = 1502182893478537858L;
    private List<Object> list;

    private static final Null NULL = Null.instance;

    public JsonArray() {
        this.list = createList();
    }

    public JsonArray(JsonArray jsonArray) {
        this.list = createList(jsonArray.list);
    }

    public JsonArray(List<Object> list) {
        this.list = createList();
        for (Object o : list) {
            list.add(o == null ? NULL : o);
        }
    }

    public JsonArray(Collection<Object> collection){
        this.list = createList();
        this.list.addAll(collection);
    }

    JsonArray(Object array){
        for(int i=0;i< Array.getLength(array);i++){
            this.list.add(Array.get(array,i));
        }
    }


    private JsonArray(List<Object> list,Object o){
        this.list = list;
    }

    protected List<Object> createList() {
        return new ArrayList<Object>();
    }

    protected List<Object> createList(List<Object> list) {
        return new ArrayList<Object>(list);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public JsonArray add(Object o) {
        list.add(o == null ? NULL : o);
        return this;
    }

    public Object get(int index, Object defaultValue) {
        Object o = list.get(index);
        if (o == null || o instanceof Null) {
            return defaultValue;
        }
        return o;
    }

    public Object get(int index) {
        return get(index, null);
    }

    public Object remove(int index) {
        Object o = list.remove(index);
        if (o instanceof Null) {
            return null;
        }
        return o;
    }

    public Object remove(Object object) {
        Object o = list.remove(object == null ? NULL : object);
        if (o instanceof Null) {
            return null;
        }
        return o;
    }

    public void clear() {
        list.clear();
    }

    public List<Object> toList() {
        List<Object> list = createList();
        for (Object o : this.list) {
            list.add(o instanceof Null ? null : o);
        }
        return list;
    }

    public List<String> toStringList(){
        List<String> list = new ArrayList<String>();
        for(Object o:this){
            list.add(String.valueOf(o));
        }
        return list;
    }

    public JsonArray subArray(int start,int offset){
        List<Object> subList = list.subList(start,offset);
        return new JsonArray(subList,null);
    }

    @Override
    public Iterator<Object> iterator() {
        return new JsonArrayItr();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsonArray jsonArray = (JsonArray) o;

        return list != null ? list.equals(jsonArray.list) : jsonArray.list == null;

    }

    @Override
    public int hashCode() {
        return list != null ? list.hashCode() : 0;
    }

    @Override
    public String toJsonString(SerializeFeature feature) {
        StringBuilder stringBuilder = new StringBuilder("[");
        int i = 0;
        for (Object o : this) {
            if(o!=null||feature.writesNullValue()){
                if (i > 0) {
                    stringBuilder.append(",");
                }
                stringBuilder.append(Json.toJsonString(o, feature));
                i++;
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public String getString(int index) {
        Object value = get(index);
        return TypeUtils.castToString(value);
    }

    public Byte getByte(int index) {
        Object value = get(index);
        return TypeUtils.castToByte(value);
    }

    public Character getChar(int index) {
        Object value = get(index);
        return TypeUtils.castToChar(value);
    }

    public Short getShort(int index) {
        Object value = get(index);
        return TypeUtils.castToShort(value);
    }

    public BigDecimal getBigDecimal(int index) {
        Object value = get(index);
        return TypeUtils.castToBigDecimal(value);
    }

    public BigInteger getBigInteger(int index) {
        Object value = get(index);
        return TypeUtils.castToBigInteger(value);
    }

    public Float getFloat(int index) {
        Object value = get(index);
        return TypeUtils.castToFloat(value);
    }

    public Double getDouble(int index) {
        Object value = get(index);
        return TypeUtils.castToDouble(value);
    }

    public Date getDate(int index) {
        Object value = get(index);
        return TypeUtils.castToDate(value);
    }

    public java.sql.Date getSqlDate(int index) {
        Object value = get(index);
        return TypeUtils.castToSqlDate(value);
    }

    public Timestamp getTimestamp(int index) {
        Object value = get(index);
        return TypeUtils.castToTimestamp(value);
    }

    public Long getLong(int index) {
        Object value = get(index);
        return TypeUtils.castToLong(value);
    }

    public Integer getInt(int index) {
        Object value = get(index);
        return TypeUtils.castToInt(value);
    }

    public byte[] getBytes(int index) {
        Object value = get(index);
        return TypeUtils.castToBytes(value);
    }

    public Boolean getBoolean(int index) {
        Object value = get(index);
        return TypeUtils.castToBoolean(value);
    }

    public JsonObject getJsonObject(int index) {
        Object value = get(index);
        return TypeUtils.castToJsonObject(value);
    }

    public JsonArray getJsonArray(int index) {
        Object value = get(index);
        return TypeUtils.castToJsonArray(value);
    }

    public String getJson(int index){
        Object value = get(index);
        return Json.toJsonString(value);
    }

    public JsonObject toJsonObject(){
        JsonObject jsonObject = new JsonObject();
        putTo(jsonObject);
        return jsonObject;
    }

    protected void putTo(JsonObject jsonObject){
        for(int i=0;i<this.size();i++){
            Object value = this.get(i);
            jsonObject.put(String.valueOf(i),value);
        }
    }

    public void sort(Comparator<Object> comparator){
        Collections.sort(list,comparator);
    }

    public void foreach(ForeachHelper foreachHelper){
        Args.notNull(foreachHelper,"foreach helper");
        int i = 0;
        for(Object o:this.list){
            if(foreachHelper.flag){
                foreachHelper.foreach(i,o instanceof Null?null:o);
            }else{
                break;
            }
            i++;
        }
    }


    private class JsonArrayItr implements Iterator<Object> {

        private Iterator<Object> innerIterator = list.iterator();

        @Override
        public boolean hasNext() {
            return innerIterator.hasNext();
        }

        @Override
        public Object next() {
            Object o = innerIterator.next();
            if (o instanceof Null) {
                return null;
            }
            return o;
        }


        //@Override
        public void remove() {
            innerIterator.remove();
        }
    }


    public static abstract class ForeachHelper{
        boolean flag = true;
        protected abstract void foreach(int index,Object value);
        public final void BREAK() {
            flag = false;
        }
    }


}
