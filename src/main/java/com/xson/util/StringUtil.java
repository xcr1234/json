package com.xson.util;


import com.xson.feature.DeserializeFeature;
import com.xson.feature.SerializeFeature;
import com.xson.util.base64.Base64;

public class StringUtil {
    private static String quoteString(String str){
        return "\""+str+"\"";
    }

    private static String replaceQuote(String str){
        return str.replace("\"","\\\"");
    }

    private static String jsonString(String str){
        return quoteString(replaceQuote(str));
    }

    private static String unicodeString(String str){
        return quoteString(Unicode.string2Unicode(str));
    }

    public static String parseJson(String str, DeserializeFeature feature){
        if(feature.unicode()){
            return Unicode.unicode2String(removeQuote(str));
        }else if(feature.base64()){
            return Base64.decode(removeQuote(str));
        } else{
            return removeQuote(str).replace("\\","\"");
        }
    }

    public static String parseString(String str,SerializeFeature feature){
        if(feature.unicode()){
            return StringUtil.unicodeString(str);
        }else if(feature.base64()){
            return quoteString(Base64.encode(str));
        } else {
            return StringUtil.jsonString(str);
        }
    }

    public static String removeQuote(String str){
        if(str.length()==0){
            return str;
        }
        if(str.length()==1){
            return str.charAt('0')=='\"'?"":str;
        }
        int l = str.charAt(0)=='\"'?1:0;
        int r = str.charAt(str.length()-1)=='\"'?str.length()-1:str.length();
        return str.substring(l,r);
    }
}
