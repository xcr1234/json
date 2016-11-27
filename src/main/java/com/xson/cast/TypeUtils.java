package com.xson.cast;


import com.xson.*;
import com.xson.feature.DefaultSerializeFeature;
import com.xson.util.base64.Base64;

import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TypeUtils {
    public static String castToString(Object value){
        if (value == null) {
            return null;
        }

        return value.toString();
    }

    public static Byte castToByte(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Number) {
            return ((Number) value).byteValue();
        }

        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0 //
                    || "null".equals(strVal) //
                    || "NULL".equals(strVal)) {
                return null;
            }

            return Byte.parseByte(strVal);
        }

        throw new JsonCastException("can not cast to byte, value : " + value);
    }

    public static Character castToChar(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Character) {
            return (Character) value;
        }

        if (value instanceof String) {
            String strVal = (String) value;

            if (strVal.length() == 0) {
                return null;
            }

            if (strVal.length() != 1) {
                throw new JsonCastException("can not cast to char, value : " + value);
            }

            return strVal.charAt(0);
        }

        throw new JsonCastException("can not cast to char, value : " + value);
    }

    public static Short castToShort(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Number) {
            return ((Number) value).shortValue();
        }

        if (value instanceof String) {
            String strVal = (String) value;

            if (strVal.length() == 0 //
                    || "null".equals(strVal) //
                    || "NULL".equals(strVal)) {
                return null;
            }

            return Short.parseShort(strVal);
        }

        throw new JsonCastException("can not cast to short, value : " + value);
    }

    public static BigDecimal castToBigDecimal(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }

        if (value instanceof BigInteger) {
            return new BigDecimal((BigInteger) value);
        }

        String strVal = value.toString();
        if (strVal.length() == 0) {
            return null;
        }

        return new BigDecimal(strVal);
    }

    public static BigInteger castToBigInteger(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof BigInteger) {
            return (BigInteger) value;
        }

        if (value instanceof Float || value instanceof Double) {
            return BigInteger.valueOf(((Number) value).longValue());
        }

        String strVal = value.toString();
        if (strVal.length() == 0 //
                || "null".equals(strVal) //
                || "NULL".equals(strVal)) {
            return null;
        }

        return new BigInteger(strVal);
    }

    public static Float castToFloat(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Number) {
            return ((Number) value).floatValue();
        }

        if (value instanceof String) {
            String strVal = value.toString();
            if (strVal.length() == 0 //
                    || "null".equals(strVal) //
                    || "NULL".equals(strVal)) {
                return null;
            }

            if (strVal.indexOf(',') != 0) {
                strVal = strVal.replaceAll(",", "");
            }

            return Float.parseFloat(strVal);
        }

        throw new JsonCastException("can not cast to float, value : " + value);
    }
    public static Double castToDouble(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }

        if (value instanceof String) {
            String strVal = value.toString();
            if (strVal.length() == 0 //
                    || "null".equals(strVal) //
                    || "NULL".equals(strVal)) {
                return null;
            }

            if (strVal.indexOf(',') != 0) {
                strVal = strVal.replaceAll(",", "");
            }

            return Double.parseDouble(strVal);
        }

        throw new JsonCastException("can not cast to double, value : " + value);
    }

    public static Date castToDate(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Date) { // 使用频率最高的，应优先处理
            return (Date) value;
        }

        if (value instanceof Calendar) {
            return ((Calendar) value).getTime();
        }

        long longValue = -1;

        if (value instanceof Number) {
            longValue = ((Number) value).longValue();
            return new Date(longValue);
        }

        if (value instanceof String) {
            String strVal = (String) value;

            if (strVal.startsWith("/Date(") && strVal.endsWith(")/")) {
                strVal = strVal.substring(6, strVal.length() - 2);
            }

            if (strVal.indexOf('-') != -1) {
                String format;
                if (strVal.length() ==  "yyyy-MM-dd HH:mm:ss".length()) {
                    format =  "yyyy-MM-dd HH:mm:ss";
                } else if (strVal.length() == 10) {
                    format = "yyyy-MM-dd";
                } else if (strVal.length() == "yyyy-MM-dd HH:mm:ss".length()) {
                    format = "yyyy-MM-dd HH:mm:ss";
                } else {
                    format = "yyyy-MM-dd HH:mm:ss.SSS";
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                try {
                    return dateFormat.parse(strVal);
                } catch (ParseException e) {
                    throw new JsonCastException("can not cast to Date, value : " + strVal,e);
                }
            }

            if (strVal.length() == 0) {
                return null;
            }

            longValue = Long.parseLong(strVal);
        }

        if (longValue < 0) {
            throw new JsonCastException("can not cast to Date, value : " + value);
        }


        return new Date(longValue);
    }

    public static java.sql.Date castToSqlDate(Object value){
        if(value == null) return null;
        Date date = castToDate(value);
        return new java.sql.Date(date.getTime());
    }
    public static Timestamp castToTimestamp(Object value){
        if(value==null) return null;
        Date date = castToDate(value);
        return new Timestamp(date.getTime());
    }

    public static Long castToLong(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Number) {
            return ((Number) value).longValue();
        }

        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0 //
                    || "null".equals(strVal) //
                    || "NULL".equals(strVal)) {
                return null;
            }

            if (strVal.indexOf(',') != 0) {
                strVal = strVal.replaceAll(",", "");
            }

            try {
                return Long.parseLong(strVal);
            } catch (NumberFormatException ex) {
                //
            }

        }

        throw new JsonCastException("can not cast to long, value : " + value);
    }

    public static Integer castToInt(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Integer) {
            return (Integer) value;
        }

        if (value instanceof Number) {
            return ((Number) value).intValue();
        }

        if (value instanceof String) {
            String strVal = (String) value;

            if (strVal.length() == 0 //
                    || "null".equals(strVal) //
                    || "NULL".equals(strVal)) {
                return null;
            }

            if (strVal.indexOf(',') != 0) {
                strVal = strVal.replaceAll(",", "");
            }

            return Integer.parseInt(strVal);
        }

        if (value instanceof Boolean) {
            return (Boolean) value ? 1 : 0;
        }

        throw new JsonCastException("can not cast to int, value : " + value);
    }

    public static byte[] castToBytes(Object value) {
        if (value instanceof byte[]) {
            return (byte[]) value;
        }

        if (value instanceof String) {
           return ((String) value).getBytes();
        }
        throw new JsonCastException("can not cast to Bytes, value : " + value);
    }

    public static Boolean castToBoolean(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Boolean) {
            return (Boolean) value;
        }

        if (value instanceof Number) {
            return ((Number) value).intValue() == 1;
        }

        if (value instanceof String) {
            String strVal = (String) value;

            if (strVal.length() == 0 //
                    || "null".equals(strVal) //
                    || "NULL".equals(strVal)) {
                return null;
            }

            if ("true".equalsIgnoreCase(strVal) //
                    || "1".equals(strVal)) {
                return Boolean.TRUE;
            }

            if ("false".equalsIgnoreCase(strVal) //
                    || "0".equals(strVal)) {
                return Boolean.FALSE;
            }
        }

        throw new JsonCastException("can not cast to boolean, value : " + value);
    }

    public static JsonObject castToJsonObject(Object value){
        if(value ==null){
            return null;
        }
        if(value instanceof JsonObject){
            return (JsonObject) value;
        }

        if(value instanceof Map){
            JsonObject jsonObject = new JsonObject();
            for(Object o:((Map) value).entrySet()){
                Map.Entry entry = (Map.Entry) o;
                jsonObject.put(String.valueOf(entry.getKey()),String.valueOf(entry.getValue()));
            }
            return jsonObject;
        }

        if(value instanceof List || value instanceof Set){
            JsonObject jsonObject = new JsonObject();
            Iterator iterator = ((Collection)value).iterator();
            int i=0;
            while (iterator.hasNext()){
                jsonObject.put(String.valueOf(i++),iterator.next());
                i++;
            }
            return jsonObject;
        }

        if(value.getClass().isArray()){
            JsonObject jsonObject = new JsonObject();
            for(int i=0;i< Array.getLength(value);i++){
                jsonObject.put(String.valueOf(i),Array.get(value,i));
            }
            return jsonObject;
        }

        if(value instanceof JsonObjectAware){
            JsonObject jsonObject = new JsonObject();
            JsonObjectAware jsonObjectAware = (JsonObjectAware) value;
            jsonObjectAware.toJson(jsonObject, DefaultSerializeFeature.globalDefaultFuture);
            return jsonObject;
        }
        if(value instanceof JsonArrayAware){
            JsonArrayAware jsonArrayAware = (JsonArrayAware) value;
            JsonArray jsonArray = new JsonArray();
            jsonArrayAware.toJson(jsonArray,DefaultSerializeFeature.globalDefaultFuture);
            return jsonArray.toJsonObject();
        }
        if(value instanceof JsonArray){
            return ((JsonArray) value).toJsonObject();
        }
        throw new JsonCastException("can not cast to JsonObject, value : " + value);
    }

    public static JsonArray castToJsonArray(Object value){
        if(value == null) return null;
        if(value instanceof JsonArray){
            return (JsonArray) value;
        }
        if(value.getClass().isArray()){
            JsonArray jsonArray = new JsonArray();
            for(int i=0;i< Array.getLength(value);i++){
                jsonArray.add(Array.get(value,i));
            }
            return jsonArray;
        }
        if(value instanceof List || value instanceof Set){
            JsonArray jsonArray = new JsonArray();
            Iterator iterator = ((Collection)value).iterator();
            while (iterator.hasNext()){
                jsonArray.add(iterator.next());
            }
            return jsonArray;
        }
        if(value instanceof JsonArrayAware){
            JsonArrayAware jsonArrayAware = (JsonArrayAware) value;
            JsonArray jsonArray = new JsonArray();
            jsonArrayAware.toJson(jsonArray,DefaultSerializeFeature.globalDefaultFuture);
            return jsonArray;
        }
        throw new JsonCastException("can not cast to JsonArray, value : " + value);
    }


}
