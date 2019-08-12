package com.itdr.controller;

import com.itdr.common.ResponseCode;
import com.itdr.service.UserService;
import com.itdr.utils.JsonUtils;
import com.itdr.utils.PathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//控制层调用业务层
@WebServlet("/manage/user/*")//采用目录匹配，只要进入到这个路径下面的任何一个路径，都会只用这一个servlet
                             // 保证了一个用户模块只有一个servlet去处理
public class UsersController extends HttpServlet {

    //为了多个请求都使用业务层
    private UserService us = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取请求路径信
        String pathInfo = request.getPathInfo();
        String path = PathUtil.getPath(pathInfo);
        //处理统一返回对象
        ResponseCode rs = null;
        //判断一下是什么样的请求
        switch (path){
            case "list"://获取用户列表
//                 rs =
                listDo(request,response);
                 break;
            case "login"://登录模块
                loginDo(request,response);
                break;
                //禁用请求执行对用户的禁用操作，通过查询用户的id号，来判断用户是否应该被禁用
            case "disableuser"://禁用模块
                rs = disableuserDo(request);
                break;
        }

        //返回响应数据
//        response.getWriter().write(rs.toString());
//        response.setContentType("text/json;charset = utf-8");
//        response.getWriter().write(JsonUtils.obj2String(rs));


    }


    //获取用户列表的请求,需要使用业务层
    private void listDo(HttpServletRequest request,HttpServletResponse response){
        ResponseCode rs =new ResponseCode();
        //获取参数
        String pageSize = request.getParameter("pageSize");
        String pageNum =request.getParameter("pageNum");

        //调用业务层处理业务
        rs = us.selectAll(pageSize, pageNum);
        request.setAttribute("userlist",rs);
        try {
            request.getRequestDispatcher("/userlist.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return rs;
    }

    //用户登录的请求
    private void loginDo(HttpServletRequest request,HttpServletResponse response){
        //获取参数
        String username = request.getParameter("username");
        String password =request.getParameter("password");
        //调用业务层处理业务
        ResponseCode rs = us.selectOne(username, password);

        //获取session对象，为了记录登录成功与否
        HttpSession session = request.getSession();
        session.setAttribute("user",rs.getData());//把用户信息存进去
//        return rs;
        try {
            request.getRequestDispatcher("/home.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //禁用用户的请求
    private ResponseCode disableuserDo(HttpServletRequest request){
        //获取参数
        String uid = request.getParameter("uid");
        //调用业务层处理业务
        ResponseCode rs = us.selectOne(uid);

        return rs;

    }

}
