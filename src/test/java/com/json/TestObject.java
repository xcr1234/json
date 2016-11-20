package com.json;


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
}
