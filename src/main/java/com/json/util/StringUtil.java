package com.json.util;


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
}
