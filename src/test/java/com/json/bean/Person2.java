package com.json.bean;


import com.json.JsonObject;
import com.json.JsonObjectAware;
import com.json.feature.SerializeFeature;

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
}
