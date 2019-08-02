package com.itdr.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class PoolUtil {
    //静态变量，PoolUtil类加载的时候，这个static变量就加载了，
    // 加载的时候就new了，这个combo就实例化了，一实例化就进到了配置文件
    //这个连接池就存在了，所以每次调用这个方法都用的是一个连接池，因为是静态的
    public static ComboPooledDataSource co= new ComboPooledDataSource();

    public static ComboPooledDataSource getCom(){
        return co;

    }
}
