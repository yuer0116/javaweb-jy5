package com.itdr.service;

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
}
