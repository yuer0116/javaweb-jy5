package com.itdr.dao;

import com.itdr.pojo.Order;
import com.itdr.utils.PoolUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class OrderDao {
    public List<Order> selectAll(String pageSize, String pageNum) {

        ComboPooledDataSource co = PoolUtil.getCom();
        QueryRunner qr = new QueryRunner(co);
        String sql = "select * from order ";
        List<Order> li = null;
        try {
            li= qr.query(sql, new BeanListHandler<Order>(Order.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return li;
    }
}
