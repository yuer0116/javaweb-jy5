package com.itdr.service;

import com.itdr.common.Const;
import com.itdr.common.ResponseCode;
import com.itdr.dao.OrderDao;
import com.itdr.pojo.Order;

import java.util.List;

public class OrderService {
    OrderDao od = new OrderDao();
    public ResponseCode selectAll(String pageNum, String pageSize) {
        //设置默认值
        if (pageSize == null || pageSize.equals("")){
            pageSize = "10";
        }
        if (pageNum == null || pageNum.equals("")){
            pageNum = "1";
        }

        //调用数据层
        List<Order> li = od.selectAll(pageSize,pageNum);
        ResponseCode rs = new ResponseCode();
        rs.setStatus(0);
        rs.setData(li);
        return rs;
    }

    public ResponseCode order_detail(String order_No) {
        ResponseCode rs = new ResponseCode();
        if (order_No == null || order_No.equals("")){
            rs.setStatus(108);
            rs.setMag("请输入您要查询的订单号！");
            return rs;
        }
        Order order = od.order_detail(order_No);
        if (order == null){
            rs.setStatus(Const.PRODUCT_SEARCH_CODE);
            rs.setMag(Const.PRODUCT_SEARCH_MSG);
            return  rs;
        }
        rs.setStatus(0);
        rs.setData(order);
        rs.setMag("您已成功查询到该订单！");
        return rs;
    }
    //订单发货
    public ResponseCode send_goodsDo(String orderNo) {
        ResponseCode rs = new ResponseCode();

        if (orderNo == null || orderNo.equals("")){
            rs = ResponseCode.defeatedRS(Const.USER_PARAMETER_CODE,Const.USER_PARAMETER_MSG);
            return rs;
        }
        //字符串转数值
        Integer pid = null;
        try {//因为上面即使传进来一个abc，上面的判断也不会认为有错，在转型的时候就会报错，所以try/catch
            pid = Integer.parseInt(orderNo);
        }catch (Exception e){
            rs = ResponseCode.defeatedRS(109,"输入非法参数");
            return rs;
        }
        //查找是否有这样一个用户
        Order product = od.order_detail(orderNo);
        //如果用户不存在
        if (product == null){
            rs = ResponseCode.defeatedRS(Const.USER_NO_CODE,Const.USER_NO_MSG);//消息码有问题
            return rs;
        }
        int row = od.send_goodsDo(orderNo);//uid传进来应该是一个 Integer的值,最终返回一个int类型的值
        if (row <= 0){//如果小于等于0，代表禁用用户的过程失败了
            rs = ResponseCode.defeatedRS(106,"订单发货失败");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(row);
        return rs;

    }
}
