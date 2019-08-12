package com.itdr.controller;

import com.itdr.common.ResponseCode;
import com.itdr.service.ProductService;
import com.itdr.utils.JsonUtils;
import com.itdr.utils.PathUtil;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/manage/product/*")//产品控制层
public class ProductController extends HttpServlet {
    private ProductService ps = new ProductService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request ,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String path = PathUtil.getPath(pathInfo);

        ResponseCode rs = null;
        switch (path){
            case "search":
                searchDo(request,response);//搜索商品，根据商品名查
                break;
            case "add_product"://添加商品
                rs = add_productDo(request);
                break;
            case "detail"://商品详情，根据商品id查
                rs = detailDo(request);
                break;
            case "set_sale_status"://商品上下架，修改商品状态
                rs = set_sale_statusDo(request);
                break;
            case "set_sale_statusSJ"://商品上下架，修改商品状态
                rs = set_sale_statusDoSJ(request);
                break;
            case "plist"://商品列表
                plistDo(request,response);
                break;

        }
//
        response.setContentType("text/json;charset = utf-8");
//        response.getWriter().write(JsonUtils.obj2String(rs));
        response.getWriter().write(rs.toString());

    }
    //商品上下架
    private ResponseCode set_sale_statusDo(HttpServletRequest request) {
        ResponseCode rs = new ResponseCode();
        String productId = request.getParameter("productId");

       rs = ps.set_sale_status(productId);
        return rs;
    }
    private ResponseCode set_sale_statusDoSJ(HttpServletRequest request) {
        ResponseCode rs = new ResponseCode();
        String productId = request.getParameter("productId");

        rs = ps.set_sale_statusSJ(productId);
        return rs;
    }

    //搜索商品
    private void searchDo(HttpServletRequest request,HttpServletResponse response) {
        ResponseCode rs = new ResponseCode();

        String productName = request.getParameter("productName");
        rs = ps.search_product(productName);
        request.setAttribute("productsearch",rs);
        try {
            request.getRequestDispatcher("/productsearch.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return rs;
    }

    //商品详情
    private ResponseCode detailDo(HttpServletRequest request) {
        ResponseCode rs = new ResponseCode();

        String productId = request.getParameter("productId");
        rs = ps.product_detail(productId);
        return  rs;
    }

    //添加/更新商品
    private ResponseCode add_productDo(HttpServletRequest request) {
        ResponseCode rs =new ResponseCode();

        //获取参数
        String productName = request.getParameter("productName");
        String parentId = request.getParameter("parentId");
        String status = request.getParameter("status");

        rs = ps.add_product(productName,parentId,status);
        return rs;
    }

    //获取商品列表
    private void plistDo(HttpServletRequest request, HttpServletResponse response){
        ResponseCode rs =new ResponseCode();

        //获取参数
        String pageSize = request.getParameter("pageSize");
        String pageNum =request.getParameter("pageNum");

        rs = ps.selectAll(pageSize, pageNum);
        request.setAttribute("productlist",rs);
        try {
            request.getRequestDispatcher("/productlist.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
