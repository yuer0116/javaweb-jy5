package com.itdr.controller;

import com.itdr.common.Const;
import com.itdr.common.ResponseCode;
import com.itdr.service.OrderService;
import com.itdr.utils.JsonUtils;
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
            case "olist":
                rs = listDo(request,response);
                break;
            case "detail":
                detailDo(request,response);
                break;
            case "send_goods":
                send_goodsDo(request,response);
                break;
        }
        response.setContentType("text/json;charset = utf-8");
        response.getWriter().write(JsonUtils.obj2String(rs));
    }
    //订单列表
    private ResponseCode listDo(HttpServletRequest request,HttpServletResponse response){
        ResponseCode rs = new ResponseCode();
        String pageName = request.getParameter("pageName");
        String pageSize = request.getParameter("pageSize");

        rs = os.selectAll(pageName,pageSize);
        request.setAttribute("orderlist",rs);
        return rs;

    }

    //订单详情
    private void detailDo(HttpServletRequest request,HttpServletResponse response) {
        ResponseCode rs = new ResponseCode();
        String order_No = request.getParameter("order_No");
        rs = os.order_detail(order_No);
        try {
            response.getWriter().write(rs.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //订单发货
    private void send_goodsDo(HttpServletRequest request, HttpServletResponse response) {
        ResponseCode rs = new ResponseCode();
        String orderNo  = request.getParameter("orderNo ");

        rs = os.send_goodsDo(orderNo);
        try {
            response.getWriter().write(rs.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
