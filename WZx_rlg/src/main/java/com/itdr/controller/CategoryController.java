package com.itdr.controller;

import com.itdr.common.ResponseCode;
import com.itdr.service.CategoryService;
import com.itdr.utils.JsonUtils;
import com.itdr.utils.PathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manage/category/*")
public class CategoryController extends HttpServlet {

    CategoryService cs = new CategoryService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        String path = PathUtil.getPath(pathInfo);

        //处理统一返回对象
        ResponseCode rs = null;
        //判断一下是什么样的请求
        switch (path){
            case "clist"://先获取商品分类列表
                rs = clistDo(request,response);
                break;
            case "add_category"://添加商品分类
                rs = add_categoryDo(request);
                break;
            case "set_category_name"://
                rs = set_category_nameDo(request);
                break;
        }

        //返回响应数据
        response.setContentType("text/json;charset = utf-8");
        response.getWriter().write(JsonUtils.obj2String(rs));
    }

    //修改商品分类名称
    private ResponseCode set_category_nameDo(HttpServletRequest request) {
        ResponseCode rs =new ResponseCode();

        //获取参数
        String categoryId = request.getParameter("categoryId");
        String categoryName = request.getParameter("categoryName");
        //调用业务层处理业务
        rs = cs.set_category_name(categoryId,categoryName);
        return rs;
    }

    //添加商品分类
    private ResponseCode add_categoryDo(HttpServletRequest request) {
        ResponseCode rs =new ResponseCode();

        //获取参数
        String categoryName = request.getParameter("categoryName");
        String parentId = request.getParameter("parentId");
        //调用业务层处理业务
        rs = cs.add_category(parentId,categoryName);
        return rs;

    }

    //获取分类列表
    private ResponseCode clistDo(HttpServletRequest request,HttpServletResponse response) {
       ResponseCode rs =new ResponseCode();
        String pageSize = request.getParameter("pageSize");
        String pageNum =request.getParameter("pageNum");
        rs = cs.selectAll(pageSize, pageNum);
        return rs;
    }

}
