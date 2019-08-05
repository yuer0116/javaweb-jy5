package com.itdr.controller;

import com.itdr.common.ResponseCode;
import com.itdr.utils.PathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.interfaces.RSAKey;

@WebServlet("/manage/product/*")
public class ProductController extends HttpServlet {
    ProductService ps = new ProductService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String path = PathUtil.getPath(pathInfo);

        ResponseCode rs = null;
        switch (path){
            case "list":
                rs = listDo(request);
                break;


        }

        response.getWriter().write(rs.toString());
    }
    //获取商品列表
    private ResponseCode listDo(HttpServletRequest request) {



    }
}
