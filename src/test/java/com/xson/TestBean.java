package com.xson;


import com.xson.bean.Person;
import com.xson.bean.Person2;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

public class TestBean {

    @Test
    public void test1(){
        List<String> list = new ArrayList<String>();
        list.add("abc");
        list.add("def");
        list.add("zzz");

        Person person = new Person();
        person.setId(1);
        person.setName("a");
        person.setContents(list);

        System.out.println(Json.toJsonString(person));
    }
    @Test
    public void test2(){
        List<String> list = new ArrayList<String>();

        Person2 person = new Person2();
        person.setId(1);
        person.setName("a");
        person.setContents(list);

        System.out.println(Json.toJsonString(person));
    }

}
