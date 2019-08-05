package com.itdr.common;


import java.security.PrivateKey;

//这个类为了确保返回给前端的对象，都是同一个类的对象，然后把数据封装到这个类里
public class ResponseCode<T> {//泛型类

    private Integer status;

    //data可能是对象 ，集合，数组，所以用泛型，用了之后是什么就放什么
    private T data;

    private String mag;//提示信息

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMag() {
        return mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }


    //是否成功

    //成功的时候返回状态码和成功获取的数据
    public static <T> ResponseCode successRS(Integer status,T data){
        ResponseCode rs = new ResponseCode();
        rs.setStatus(status);
        rs.setData(data);
        return rs;
    }
    public static <T> ResponseCode successRS(T data){
        ResponseCode rs = new ResponseCode();
        rs.setStatus(0);
        rs.setData(data);
        return rs;
    }
    //失败的时候返回状态码和失败的信息
    public static <T> ResponseCode defeatedRS(Integer status,String mag){
        ResponseCode rs = new ResponseCode();
        rs.setStatus(status);
        rs.setMag(mag);
        return rs;
    }


    @Override
    public String toString() {
        return "ResponseCode{" +
                "status=" + status +
                ", data=" + data +
                ", mag='" + mag + '\'' +
                '}';
    }
}
