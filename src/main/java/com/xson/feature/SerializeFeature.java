package com.xson.feature;

/**
 * <pre>
 * 序列化json字符串时使用到的配置接口。
 * 默认实现是{@link DefaultSerializeFeature}，默认的全局配置是{@link DefaultSerializeFeature#globalDefaultFuture}
 *
 * 修改配置有两种方式：
 * 1.通过调用DefaultSerializeFeature的各种set方法修改。
 * 例如：
 * //修改局部配置
 * DefaultSerializeFeature feature = new DefaultSerializeFeature();
 * feature.setUnicode(true);
 *
 * //修改全局配置
 * DefaultSerializeFeature.globalDefaultFuture.setUnicode(true);
 *
 * 2.自己定义一个类实现SerializeFeature接口，覆盖DefaultSerializeFeature中的方法：
 *  SerializeFeature serializeFeature = new DefaultSerializeFeature(){
 *
 *      public boolean unicode() {
 *      return true;
 *      }
 *  };
 *
 *
 * </pre>
 */
public interface SerializeFeature extends JsonFeature{
    /**
     * 在json输出时是否保留值为null的key.
     * @return boolean
     */
    boolean writesNullValue();

    /**
     * 当碰到List/Set/Map时，将其转换为JsonArray或者JsonObject输出。
     * @return 是否转换，或者用其自带的toString输出。
     */
    boolean writeCollectionAsJson();

    String dateFormat();


}
