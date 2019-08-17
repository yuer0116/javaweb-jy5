package com.itdr.dao;
import com.itdr.pojo.Order;
import com.itdr.utils.PoolUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;
public class OrderDao {
    public List<Order> selectAll(String pageSize, String pageNum) {
        ComboPooledDataSource co = PoolUtil.getCom();
        QueryRunner qr = new QueryRunner(co);
        String sql = "select * from orders";
        List<Order> li = null;
        try {
            li= qr.query(sql, new BeanListHandler<Order>(Order.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return li;
    }

    //订单详情
    public Order order_detail(String order_No) {
        QueryRunner qr = new QueryRunner(PoolUtil.getCom());
        String sql = "select * from orders where order_no = ?";
        Order order = null;
        try {
            order = qr.query(sql,new BeanHandler<Order>(Order.class),order_No);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  order;
    }

    public int send_goodsDo(String orderNo) {
        QueryRunner qr = new QueryRunner(PoolUtil.getCom());
        String sql = "update orders set status = 0 where order_no = ?";
        int a = 0;
        try {
            a =qr.update(sql,orderNo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;

    }
}
