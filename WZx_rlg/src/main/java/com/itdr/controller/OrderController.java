package com.itdr.controller;

import com.itdr.common.ResponseCode;
import com.itdr.service.OrderService;
import com.itdr.utils.PathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manage/order/*")
public class OrderController extends HttpServlet {

    OrderService os = new OrderService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        String path = PathUtil.getPath(pathInfo);

        ResponseCode rs = null;
        switch (path){
            case "listDo":
                rs = listDo(request);
                break;
        }

        response.getWriter().write(rs.toString());

    }

    private ResponseCode listDo(HttpServletRequest request) {
        ResponseCode rs = new ResponseCode();


        String pageName = request.getParameter("pageName");
        String pageSize = request.getParameter("pageSize");

        rs = os.selectAll(pageName,pageSize);
        return rs;



    }


}
