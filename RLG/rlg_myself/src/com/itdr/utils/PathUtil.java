package com.itdr.utils;

public class PathUtil {
    public static String getPath(String path){
        String s = path.replace(".","/");
        String[] sar = s.split("/");
        return sar[1];
    }
}
