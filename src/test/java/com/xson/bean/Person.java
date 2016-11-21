package com.xson.bean;


import com.xson.JsonArray;
import com.xson.JsonAware;
import com.xson.JsonBeanAware;
import com.xson.JsonObject;
import com.xson.feature.DeserializeFeature;
import com.xson.feature.SerializeFeature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Person implements JsonAware,JsonBeanAware {

    private Integer id;
    private String name;
    private List<String> contents;

    public Person() {
    }

    public Person(Integer id, String name,String ...contents){
        this.id = id;
        this.name = name;
        this.contents = new ArrayList<String>();
        Collections.addAll(this.contents, contents);
    }

    public Person(Integer id, String name, List<String> contents) {
        this.id = id;
        this.name = name;
        this.contents = contents;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getContents() {
        return contents;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }

    @Override
    public String toJsonString(SerializeFeature feature) {
        //自己的bean如果想转换成json都要实现JsonAware接口，并且可以借助JsonObject类来实现该接口。
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("id",id);
        jsonObject.put("name",name);
        jsonObject.put("contents",contents);
        return jsonObject.toJsonString(feature);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contents=" + contents +
                '}';
    }

    @Override
    public void parse(JsonObject jsonObject, DeserializeFeature feature) {
        this.id = (Integer) jsonObject.get("id");
        this.name = (String) jsonObject.get("name");
        JsonArray jsonArray = (JsonArray) jsonObject.get("contents");
        if(jsonArray!=null){
            this.contents = new ArrayList<String>();
            for(Object o:jsonArray){
                this.contents.add(String.valueOf(o));
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != null ? !id.equals(person.id) : person.id != null) return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        return contents != null ? contents.equals(person.contents) : person.contents == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (contents != null ? contents.hashCode() : 0);
        return result;
    }
}
