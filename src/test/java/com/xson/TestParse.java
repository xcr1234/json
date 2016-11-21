package com.xson;

import com.xson.bean.Person;
import com.xson.feature.DefaultDeserializeFeature;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestParse {
    @Test
    public void test1(){
        String str = "{\"id\":1,\"name\":\"a\",\"contents\":[]}";
        JsonObject jsonObject = Json.parseObject(str);

        assertEquals(str,jsonObject.toString());
        assertEquals(jsonObject.get("id"),1);
        assertEquals(jsonObject.get("contents"),new JsonArray());
    }

    @Test
    public void test2(){
        String str = "[0,1,3,5,-1]";
        JsonArray jsonArray = Json.parseArray(str);
        assertEquals(str,jsonArray.toString());
        assertEquals(jsonArray.get(0),0);
        assertEquals(jsonArray.get(2),3);
    }

    @Test
    public void test3(){
        String str = "{\"id\":1,\"name\":\"a\",\"contents\":[\"abc\",\"def\",\"zzz\"]}";
        Person person = Json.parseBean(str,Person.class);
        System.out.println(person);
    }

    @Test
    public void test4(){
        String json = "{\"\\u61\":100,\"\\u62\":\"\\u61\\u62\\u63\",\"\\u68\\u65\\u6c\\u6c\\u6f\":\"\\u4f60\\u597d\"}";
        JsonObject jsonObject = Json.parseObject(json,new DefaultDeserializeFeature(){
            @Override
            public boolean unicode() {
                return true;
            }
        });
        System.out.println(jsonObject);
        assertEquals("你好",jsonObject.get("hello"));
    }

    @Test
    public void test5(){
        Person zsf = new Person(1,"张三丰","zhang","san","feng");
        Person zrt = new Person(2,"赵日天","zhao","ri","tian");
        Person ylc = new Person(3,"叶良辰","ye","liang","chen");

        List<Person> list = new ArrayList<Person>();
        list.add(zsf);
        list.add(zrt);
        list.add(ylc);

        JsonArray jsonArray = new JsonArray();
        jsonArray.add(zsf).add(zrt).add(ylc);

        String json = jsonArray.toString();
        List<Person> personList = Json.parseBeanList(json,Person.class);
        System.out.println(personList);
        assertEquals(list,personList);
    }



}
