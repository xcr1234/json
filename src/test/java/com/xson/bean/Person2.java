package com.xson.bean;


import com.xson.JsonObject;
import com.xson.JsonObjectAware;
import com.xson.feature.SerializeFeature;

import java.util.List;

public class Person2 implements JsonObjectAware{

    private Integer id;
    private String name;
    private List<String> contents;

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
    public void toJson(JsonObject jsonObject, SerializeFeature feature) {
        //将java bean转换成json，也可以选择实现JsonObjectAware接口，系统在转换时new一个空的JsonObject供使用，然后以该JsonObject为基础得到json字符串.
        jsonObject.put("id",id);
        jsonObject.put("name",name);
        jsonObject.put("contents",contents);
    }

    @Override
    public String toString() {
        return "Person2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contents=" + contents +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person2 person2 = (Person2) o;

        if (id != null ? !id.equals(person2.id) : person2.id != null) return false;
        if (name != null ? !name.equals(person2.name) : person2.name != null) return false;
        return contents != null ? contents.equals(person2.contents) : person2.contents == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (contents != null ? contents.hashCode() : 0);
        return result;
    }
}
