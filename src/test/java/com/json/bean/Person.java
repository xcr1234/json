package com.json.bean;


import com.json.JsonAware;
import com.json.JsonObject;
import com.json.feature.SerializeFeature;

import java.util.List;

public class Person implements JsonAware {

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
    public String toJsonString(SerializeFeature feature) {
        //自己的bean如果想转换成json都要实现JsonAware接口，并且可以借助JsonObject类来实现该接口。
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("id",id);
        jsonObject.put("name",name);
        jsonObject.put("contents",contents);
        return jsonObject.toJsonString(feature);
    }
}
