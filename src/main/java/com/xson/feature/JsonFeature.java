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

    /**
     * 字符串使用使用base64编码
     * @return 使用base64编码后，json name和value都会被转换为base64字符串格式。
     */
    boolean base64();
}
