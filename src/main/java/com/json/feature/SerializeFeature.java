package com.json.feature;


public interface SerializeFeature extends JsonFeature{
    /**
     * 在json输出时是否保留值为null的key.
     */
    boolean writesNullValue();

    /**
     * 当碰到List/Set/Map时，将其转换为JsonArray或者JsonObject输出。
     * @return 是否转换，或者用其自带的toString输出。
     */
    boolean writeCollectionAsJson();


    String dateFormat();
}
