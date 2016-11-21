package com.xson.util;


public final class Args {
    public static void notNull(Object arg,String m){
        if(arg == null){
            throw new IllegalArgumentException(m+" may not be null!");
        }
    }
}
