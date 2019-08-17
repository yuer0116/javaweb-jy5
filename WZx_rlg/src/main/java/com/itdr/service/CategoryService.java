package com.itdr.service;

import com.itdr.common.Const;
import com.itdr.common.ResponseCode;
import com.itdr.dao.CategoryDao;
import com.itdr.pojo.Category;
import com.itdr.pojo.Product;

import java.util.List;

public class CategoryService {
    private CategoryDao cd = new CategoryDao();

    //获取分类列表
    public ResponseCode selectAll(String pageSize, String pageNum) {
        //设置默认值
        if (pageSize == null || pageSize.equals("")){
            pageSize = "0";
        }
        if (pageNum == null || pageNum.equals("")){
            pageSize = "10";
        }
        //调用数据层
        List<Category> li = cd.selectAll(pageSize,pageNum);
        ResponseCode rs = new ResponseCode();
        rs.setStatus(0);
        rs.setData(li);
        return rs;
    }

    //修改品类名称
    public ResponseCode set_category_name(String categoryId, String categoryName) {
        ResponseCode rs = new ResponseCode();
        if (categoryId == null||categoryId.equals("")){
            categoryId = "0";
        }
        if (categoryName == null||categoryName.equals("")){
            rs.setStatus(0);
            rs.setMag("请输入要修改的品类名称");
            return rs;
        }
        List<Category> li = cd.set_category_name(categoryId,categoryName);
        if (li.size() == 0){
            rs.setStatus(Const.CATEGORY_NO_CODE);
            rs.setMag(Const.CATEGORY_NO_MSG);
            return rs;
        }
        rs.setStatus(0);
        rs.setMag("商品品类名称修改成功！");
        return rs;
    }

    //增加分类节点
    public ResponseCode add_category(String parentId, String categoryName) {
        ResponseCode rs = new ResponseCode();
        //设置默认值
        if (parentId == null || parentId.equals("")){
            parentId = "0";
        }
        if (categoryName == null || categoryName.equals("")){
            rs.setStatus(0);
            rs.setMag("请输入您要的品类名称");
            return rs;
        }
        //调用数据层
        List<Category> li = cd.add_category(parentId,categoryName);
        //如果商品分类存在
        if (li.size() != 0){
            rs.setStatus(Const.CATEGORY_HAVE_CODE);
            rs.setMag(Const.CATEGORY_HAVE_MSG);
            return rs;
        }
        rs.setStatus(0);
        rs.setMag("商品品类添加成功");
        return rs;
    }

}

