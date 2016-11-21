package com.xson.util;


import com.xson.feature.DeserializeFeature;

public class StringUtil {
    public static String quoteString(String str){
        return "\""+str+"\"";
    }

    public static String replaceQuote(String str){
        return str.replace("\"","\\\"");
    }

    public static String jsonString(String str){
        return quoteString(replaceQuote(str));
    }

    public static String unicodeString(String str){
        return quoteString(Unicode.string2Unicode(str));
    }

    public static String parseJson(String str, DeserializeFeature feature){
        if(feature.unicode()){
            return Unicode.unicode2String(removeQuote(str));
        }else{
            return removeQuote(str).replace("\\","\"");
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
