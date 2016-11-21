package com.xson.util;


import java.math.BigDecimal;
import java.math.BigInteger;

public class TypeCaster {
    public static Number toNumber(String str){
        int exp = str.indexOf('e');
        int point = str.indexOf('.');
        if(point==-1&&exp==-1){
            try {
                return Integer.valueOf(str);
            }catch (NumberFormatException e){
                return Long.valueOf(str);
            }
        }else if(point!=-1&&exp==-1){
            try {
                return Float.valueOf(str);
            }catch (NumberFormatException e){
                return Double.valueOf(str);
            }
        }else if(point==-1){
            return new BigInteger(str);
        }else {
            return new BigDecimal(str);
        }
    }
}
