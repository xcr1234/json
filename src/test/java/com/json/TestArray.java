package com.json;


import com.json.feature.DefaultSerializeFeature;
import com.json.feature.SerializeFeature;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class TestArray {

    @Before
    public void setUp(){
        //设置全局日期格式
        DefaultSerializeFeature.globalDefaultFuture.setDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    }

    @Test
    public void test1(){
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(0).add(5).add(3).add(4).add(1).add(2);
        jsonArray.sort(new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                int x = (Integer)o1;
                int y = (Integer)o2;
                return x-y;
            }
        });
        Assert.assertEquals(jsonArray.toString(),"[0,1,2,3,4,5]");
    }

    @Test
    public void test2(){
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(1);
        jsonArray.add("hello");
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("list");
        arrayList.add("str");
        Date date = new Date();
        jsonArray.add(date);
        jsonArray.add(arrayList);

        System.out.println(jsonArray);
    }

    @Test
    public void test3(){
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(1);
        jsonArray.add(null);
        jsonArray.add(false);
        jsonArray.add("hello\"");

        System.out.println(jsonArray);

        Map<String,String> map = new HashMap<String,String>();
        map.put("k1","v1");
        map.put("k2","v2");
        jsonArray.add(map);

        Assert.assertEquals("[1,null,false,\"hello\\\"\",{\"k1\":\"v1\",\"k2\":\"v2\"}]",jsonArray.toString());

        //map使用默认的toString方式
        // 自定义feature
        // 方式一，调用DefaultSerializeFeature的各种set方法
        DefaultSerializeFeature defaultSerializeFeature = new DefaultSerializeFeature();
        defaultSerializeFeature.setWriteCollectionAsJson(false);
        Assert.assertEquals("[1,null,false,\"hello\\\"\",\"{k1=v1, k2=v2}\"]",jsonArray.toJsonString(defaultSerializeFeature));

        //方式二： 重写DefaultSerializeFeature的接口方法。
        SerializeFeature serializeFeature = new DefaultSerializeFeature(){
            @Override
            public boolean writeCollectionAsJson() {
                return false;
            }
        };
        Assert.assertEquals("[1,null,false,\"hello\\\"\",\"{k1=v1, k2=v2}\"]",jsonArray.toJsonString(serializeFeature));
    }
}
