package com.xson;

import java.util.List;
import java.util.Vector;

/**
 * 线程安全的{@link JsonArray}
 */
public class SyncJsonArray extends JsonArray{
    private static final long serialVersionUID = 7742922165748458863L;

    public SyncJsonArray() {
    }

    public SyncJsonArray(JsonArray jsonArray) {
        super(jsonArray);
    }

    public SyncJsonArray(List<Object> list) {
        super(list);
    }

    @Override
    protected List<Object> createList() {
        return new Vector<Object>();
    }

    @Override
    protected List<Object> createList(List<Object> list) {
        return new Vector<Object>(list);
    }
}

