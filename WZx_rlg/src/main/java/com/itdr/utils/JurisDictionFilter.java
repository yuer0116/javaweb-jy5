package com.itdr.utils;

import com.itdr.common.ResponseCode;
import com.itdr.pojo.Users;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "JurisDictionFilter",value = "/manage/*")
public class JurisDictionFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //解决中文乱码，一定要写在放行之前，在放行之前
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        //统一返回数据对象
        ResponseCode rs = new ResponseCode();

        //把servletrequest向下转型，为了使用子类的更多方法
        HttpServletRequest request = (HttpServletRequest) req;

        //获取路径
        String pathInfo = request.getPathInfo();

        //判断是否登录，如果登录了，直接放行
        if (pathInfo.equals("/login.do")){
            //放行
            chain.doFilter(req, resp);
            return;
        }

        //其它请求处理，验证session，看看你是否是一个登录状态
        HttpSession session = request.getSession();
        Users user = (Users)session.getAttribute("user");

        if (user == null){//说明没有登录
            rs.setStatus(3);
            rs.setMag("请登录后再操作");
            //有页面之后，我就让用户重定向到登录页面
            resp.getWriter().write(rs.toString());
            return;
        }
        //为防止有人侵入，跳过登录环节，所以每次请求的时候都要过滤一次，双保险
        if (user.getType() != 1){
            rs.setStatus(3);
            rs.setMag("没有操作权限");
            resp.getWriter().write(rs.toString());
            return;
        }

        //如果什么问题都没有，放行
        chain.doFilter(req, resp);
        return;


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
