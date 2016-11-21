package com.xson;


import com.xson.feature.DefaultSerializeFeature;
import com.xson.util.Unicode;
import org.junit.Assert;
import org.junit.Test;

public class TestObject {

    @Test
    public void test1(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("a",100);
        jsonObject.put("b",1024.31);
        jsonObject.put("c","abc");

        Assert.assertEquals(jsonObject.get("a"),100);
        Assert.assertEquals(jsonObject.get("b"),1024.31);
        Assert.assertEquals(jsonObject.get("c"),"abc");
        Assert.assertEquals(jsonObject.toString(),"{\"a\":100,\"b\":1024.31,\"c\":\"abc\"}");
    }


    @Test
    public void test2(){

        JsonObject jsonObject = new JsonObject();
        jsonObject.put("a",100);
        jsonObject.put("b","abc");

        JsonObject jo = new JsonObject();
        jo.put("m","ok");
        jsonObject.put("jo",jo);

        Assert.assertEquals(jsonObject.toString(),"{\"a\":100,\"b\":\"abc\",\"jo\":{\"m\":\"ok\"}}");
        Assert.assertTrue(jsonObject.get("jo") instanceof JsonObject);
        Assert.assertEquals(((JsonObject)jsonObject.get("jo")).get("m"),"ok");
    }

    @Test
    public void test3(){
        //测试使用unicode编码
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("a",100);
        jsonObject.put("b","abc");
        jsonObject.put("hello","你好");

        DefaultSerializeFeature feature = new DefaultSerializeFeature();
        feature.setUnicode(true);

        JsonObject jo = new JsonObject();
        jo.put(Unicode.string2Unicode("a"),100);
        jo.put(Unicode.string2Unicode("b"),Unicode.string2Unicode("abc"));
        jo.put(Unicode.string2Unicode("hello"),Unicode.string2Unicode("你好"));

        Assert.assertEquals(jo.toString(),jsonObject.toJsonString(feature));

    }
}
