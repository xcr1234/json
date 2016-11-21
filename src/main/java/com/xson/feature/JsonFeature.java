package com.xson.feature;

/**
 * json序列化/反序列化中用到的公共配置。
 */
public interface JsonFeature {
    /**
     * 是否使用Unicode编码
     * @return 使用unicode编码后，json name和value都会被转换为unicode字符串格式。
     */
    boolean unicode();
}
