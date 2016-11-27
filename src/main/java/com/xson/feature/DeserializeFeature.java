package com.xson.feature;

import com.xson.cast.NumberCaster;

/**
 * json反序列化时使用到的接口，使用方法与SerializeFeature类似。
 *
 * @see SerializeFeature
 * @see DefaultDeserializeFeature
 */
public interface DeserializeFeature extends JsonFeature {
    NumberCaster numberCaster();

    /**
     * 是否保存null的value
     * @return 当value为null时，是否保留该key。
     */
    boolean readNullValue();
}
