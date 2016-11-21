package com.xson;

import com.xson.feature.DefaultDeserializeFeature;
import com.xson.feature.DeserializeFeature;
import com.xson.feature.DefaultSerializeFeature;
import com.xson.feature.SerializeFeature;
import com.xson.lexer.JSONLexer;
import com.xson.lexer.JSONParser;
import com.xson.parser.JsonArrayListener;
import com.xson.parser.JsonObjectListener;
import com.xson.parser.JsonValueParser;
import com.xson.util.StringUtil;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import static javafx.scene.input.KeyCode.T;

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

    protected Json(){}

    /**
     * 将Java对象转换为Json字符串。
     * @param obj java对象
     * @param feature 转换的配置，具体参考{@link SerializeFeature}
     * @return 转换成json串的结果
     */
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
        } else if(obj instanceof JsonArrayAware){
            JsonArrayAware jsonArrayAware = (JsonArrayAware)obj;
            JsonArray jsonArray = new JsonArray();
            jsonArrayAware.toJson(jsonArray,feature);
            return jsonArray.toJsonString(feature);
        }else if (obj instanceof String) {
            return parseString((String)obj,feature);
        } else if (obj instanceof Date) {
            Date date = (Date) obj;
            if (feature.dateFormat() == null) {
                return parseString(date.toString(),feature);
            } else {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(feature.dateFormat());
                return parseString(simpleDateFormat.format(date),feature);
            }
        } else if (obj instanceof List || obj instanceof Set) {
            Collection<Object> collection = (Collection) obj;
            if (feature.writeCollectionAsJson()) {
                return new JsonArray(collection).toJsonString(feature);
            } else {
                return parseString(collection.toString(),feature);
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
                return parseString(map.toString(),feature);
            }
        } else if (obj instanceof Number || obj instanceof Boolean) {
            return String.valueOf(obj);
        }
        return parseString(obj.toString(),feature);
    }

    private static String parseString(String str,SerializeFeature feature){
        return feature.unicode()?StringUtil.unicodeString(str):StringUtil.jsonString(str);
    }

    public static JsonObject parseObject(InputStream inputStream) throws IOException{
        return parseObject(inputStream,null);
    }

    /**
     * 将json字符串解析为JsonObject，解析失败将抛出JsonParseException
     * @param inputStream json字符串输入流
     * @param feature 配置
     * @return 转换后的JsonObject
     * @throws IOException
     */
    public static JsonObject parseObject(InputStream inputStream,DeserializeFeature feature) throws IOException{
        JSONLexer lexer = JsonValueParser.initLexer(inputStream);
        return parseLexer(lexer,feature);
    }

    private static JsonObject parseLexer(JSONLexer lexer,DeserializeFeature feature){
        if(feature == null){
            feature = DefaultDeserializeFeature.globalDefaultDeserializeFeature;
        }
        JSONParser parser = JsonValueParser.initParser(lexer);
        parser.addErrorListener(new BaseErrorListener(){
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new JsonParseException("syntax error:"+msg+",line "+line);
            }
        });
        JsonObjectListener jsonObjectListener = new JsonObjectListener(feature);
        JsonValueParser.walk(parser,jsonObjectListener);
        return jsonObjectListener.getJsonObject();
    }

    /**
     * 将json字符串解析为JsonObject，解析失败将抛出JsonParseException
     * @param jsonString json字符串
     * @param feature 配置
     * @return JsonObject
     */
    public static JsonObject parseObject(String jsonString, DeserializeFeature feature) {
        JSONLexer lexer = JsonValueParser.initLexer(jsonString);
        return parseLexer(lexer,feature);
    }

    /**
     * 将json字符串解析为JsonArray，解析失败抛出JsonParseException
     * @param jsonString json字符串
     * @param feature 配置
     * @return JsonArray
     */
    public static JsonArray parseArray(String jsonString, DeserializeFeature feature) {
        return parseArray0( JsonValueParser.initLexer(jsonString),feature);
    }

    /**
     * 将json字符串解析为JsonArray，解析失败抛出JsonParseException
     * @param inputStream json字符串输入流
     * @param feature 配置信息
     * @return JsonArray
     * @throws IOException
     */
    public static JsonArray parseArray(InputStream inputStream, DeserializeFeature feature) throws IOException{
        return parseArray0(JsonValueParser.initLexer(inputStream),feature);
    }

    /**
     * 将json字符串解析为JsonArray，解析失败抛出JsonParseException
     * @param inputStream json字符串输入流
     * @return JsonArray
     * @throws IOException
     */
    public static JsonArray parseArray(InputStream inputStream) throws IOException {
        return parseArray(inputStream,null);
    }

    private static JsonArray parseArray0(JSONLexer lexer,DeserializeFeature feature){
        if(feature == null){
            feature = DefaultDeserializeFeature.globalDefaultDeserializeFeature;
        }
        JSONParser parser = JsonValueParser.initParser(lexer);
        parser.addErrorListener(new BaseErrorListener(){
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new JsonParseException("syntax error:"+msg+",line "+line);
            }
        });
        JsonArrayListener jsonArrayListener = new JsonArrayListener(feature);
        JsonValueParser.walk(parser,jsonArrayListener);
        return jsonArrayListener.getJsonArray();
    }

    /**
     * 将Java对象转换为Json字符串。
     * @param obj java对象
     * @return json字符串
     */
    public static String toJsonString(Object obj) {
        return toJsonString(obj, null);
    }

    /**
     * 将json字符串解析为JsonObject，解析失败将抛出JsonParseException
     * @param jsonString json字符串
     * @return JsonObject
     */
    public static JsonObject parseObject(String jsonString) {
        return parseObject(jsonString, null);
    }

    /**
     * 将json字符串解析为JsonArray，解析失败抛出JsonParseException
     * @param jsonString json字符串
     * @return JsonArray
     */
    public static JsonArray parseArray(String jsonString) {
        return parseArray(jsonString, null);
    }

    private static <T extends JsonBeanAware> T parseBean(JsonObject jsonObject,Class<T> type,DeserializeFeature feature){
        try {
            T t = type.newInstance();
            t.parse(jsonObject,feature);
            return t;
        } catch (InstantiationException e) {
            throw new JsonParseException(e);
        } catch (IllegalAccessException e) {
            throw new JsonParseException(e);
        }

    }

    /**
     * 将json字符串转换为java Bean对象，要求bean对象有一个public的无参构造函数且必须实现了JsonBeanAware接口
     * @param jsonString json字符串
     * @param type bean对象的Class
     * @param feature 配置
     * @param <T> bean对象的java类型
     * @return 解析后得到的bean对象
     * @throws JsonParseException 解析失败抛出JsonParseException
     */
    public static <T extends JsonBeanAware> T parseBean(String jsonString,Class<T> type,DeserializeFeature feature){
        if(feature == null){
            feature = DefaultDeserializeFeature.globalDefaultDeserializeFeature;
        }
        JsonObject jsonObject = parseObject(jsonString,feature);
        return parseBean(jsonObject,type,feature);
    }

    /**
     * 将json字符串转换为java Bean对象，要求bean对象有一个public的无参构造函数且必须实现了JsonBeanAware接口
     * @param inputStream json字符串输入流
     * @param type bean对象的Class
     * @param <T> bean对象的java类型
     * @return 解析后得到的bean对象
     * @throws IOException
     * @throws JsonParseException 解析失败抛出JsonParseException
     */
    public static <T extends JsonBeanAware> T parseBean(InputStream inputStream,Class<T> type) throws IOException{
        return parseBean(inputStream,type,null);
    }

    /**
     * 将json字符串转换为java Bean对象，要求bean对象有一个public的无参构造函数且必须实现了JsonBeanAware接口
     * @param inputStream json字符串输入流
     * @param type bean对象的Class
     * @param feature 配置
     * @param <T> bean对象的java类型
     * @return 解析后得到的bean对象
     * @throws IOException
     * @throws JsonParseException 解析失败抛出JsonParseException
     */
    public static <T extends JsonBeanAware> T parseBean(InputStream inputStream,Class<T> type,DeserializeFeature feature) throws IOException {
        if(feature == null){
            feature = DefaultDeserializeFeature.globalDefaultDeserializeFeature;
        }
        JsonObject jsonObject = parseObject(inputStream,feature);
        return parseBean(jsonObject,type,feature);
    }

    /**
     * 将json字符串转换为java Bean对象的ArrayList集合，要求bean对象有一个public的无参构造函数且必须实现了JsonBeanAware接口
     * @param inputStream json字符串输入流
     * @param type bean对象的Class
     * @param <T> bean对象的java类型
     * @return 解析后得到的bean对象
     * @throws IOException
     * @throws JsonParseException 解析失败抛出JsonParseException
     */
    public static <T extends JsonBeanAware> List<T> parseBeanList(InputStream inputStream,Class<T> type) throws IOException{
        return parseBeanList(inputStream,type,null);
    }

    /**
     * 将json字符串转换为java Bean对象的ArrayList集合，要求bean对象有一个public的无参构造函数且必须实现了JsonBeanAware接口
     * @param inputStream json字符串输入流
     * @param type bean对象的Class
     * @param feature 配置
     * @param <T> bean对象的java类型
     * @return 解析后得到的bean对象
     * @throws IOException
     * @throws JsonParseException 解析失败抛出JsonParseException
     */
    public static <T extends JsonBeanAware> List<T> parseBeanList(InputStream inputStream,Class<T> type,DeserializeFeature feature) throws IOException{
        if(feature == null){
            feature = DefaultDeserializeFeature.globalDefaultDeserializeFeature;
        }
        JsonArray jsonArray = parseArray(inputStream,feature);
        List<T> list = new ArrayList<T>();
        for(int i=0;i<jsonArray.size();i++){
            if(!(jsonArray.get(i) instanceof JsonObject)){
                throw new JsonParseException("Array item "+i+" is not a JsonObject!");
            }
            list.add(parseBean((JsonObject) jsonArray.get(i),type,feature));
        }
        return list;
    }

    /**
     * 将json字符串转换为java Bean对象的ArrayList集合，要求bean对象有一个public的无参构造函数且必须实现了JsonBeanAware接口
     * @param jsonString json字符串
     * @param type bean对象的Class
     * @param feature 配置
     * @param <T> bean对象的java类型
     * @return 解析后得到的bean对象
     */
    public static <T extends JsonBeanAware> List<T> parseBeanList(String jsonString,Class<T> type,DeserializeFeature feature){
        if(feature == null){
            feature = DefaultDeserializeFeature.globalDefaultDeserializeFeature;
        }
        JsonArray jsonArray = parseArray(jsonString,feature);
        List<T> list = new ArrayList<T>();
        for(int i=0;i<jsonArray.size();i++){
            if(!(jsonArray.get(i) instanceof JsonObject)){
                throw new JsonParseException("Array item "+i+" is not a JsonObject!");
            }
            list.add(parseBean((JsonObject) jsonArray.get(i),type,feature));
        }
        return list;
    }

    /**
     * 将json字符串转换为java Bean对象，要求bean对象有一个public的无参构造函数且必须实现了JsonBeanAware接口
     * @param jsonString  json字符串
     * @param type bean对象的Class
     * @param <T> bean对象的java类型
     * @return 解析后得到的bean对象
     */
    public static <T extends JsonBeanAware> T parseBean(String jsonString,Class<T> type){
        return parseBean(jsonString,type,null);
    }

    /**
     * 将json字符串转换为java Bean对象的ArrayList集合，要求bean对象有一个public的无参构造函数且必须实现了JsonBeanAware接口
     * @param jsonString  json字符串
     * @param type  bean对象的Class
     * @param <T> bean对象的java类型
     * @return 解析后得到的bean对象
     */
    public static <T extends JsonBeanAware> List<T> parseBeanList(String jsonString,Class<T> type){
        return parseBeanList(jsonString,type,null);
    }

    @Override
    public String toString() {
        return toJsonString(this, null);
    }

    public String toString(SerializeFeature feature) {
        return toJsonString(this, feature);
    }
}
