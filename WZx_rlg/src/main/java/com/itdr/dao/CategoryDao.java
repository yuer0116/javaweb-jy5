package com.itdr.dao;

import com.itdr.pojo.Category;
import com.itdr.pojo.Product;
import com.itdr.utils.PoolUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDao {

    //获得分类列表
    public List<Category> selectAll(String pageSize, String pageNum) {
        QueryRunner qr = new QueryRunner(PoolUtil.getCom());
        String sql = "select * from category ";
        List<Category> li = null;
        try {
            li= qr.query(sql, new BeanListHandler<Category>(Category.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return li;
    }

    //增加品类
    public List<Category> add_category(String parentId, String categoryName)  {
        QueryRunner qr = new QueryRunner(PoolUtil.getCom());
        String sql = "select * from  category where id = ? or cname = ?";
        List<Category> li = null;
        try {
            li= qr.query(sql, new BeanListHandler<Category>(Category.class),parentId,categoryName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (li.size() != 0){
            return li;
        }else{
            String sql1 = "insert into category values(null,?)";
            try {
                qr.update(sql1,categoryName);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return li;
    }

    //修改品类名称
    public List<Category> set_category_name(String categoryId, String categoryName)  {
        QueryRunner qr = new QueryRunner(PoolUtil.getCom());
        String sql = "select * from category where id = ?";
        List<Category> li = null;
        try {
            li = qr.query(sql,new BeanListHandler<Category>(Category.class),categoryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (li.size() == 0) {
            return li;
        }else{
            String sql1 = "update category set cname = ? where id = ?";
            try {
                qr.update(sql1,categoryName,categoryId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return li;
    }

}
