<html>

<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<%@ page import="com.itdr.pojo.Users" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <!-- Google Chrome Frame也可以让IE用上Chrome的引擎: -->
    <meta name="renderer" content="webkit">
    <!--国产浏览器高速模式-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="" />
    <!-- 作者 -->
    <meta name="revised" content="Zhenxuan Wang" />
    <meta name="description" content="睿乐购" />
    <meta name="keywords" content="搜索关键字，以半角英文逗号隔开" />
    <title>睿乐购管理系统</title>
    <!-- 公共样式 开始 -->
    <link rel="shortcut icon" href="images/favicon.ico"/>
    <link rel="bookmark" href="images/favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css">
    <!-- 公共样式 结束 -->
    <link rel="stylesheet" type="text/css" href="css/frameStyle.css">
</head>

<body>
<!-- 左侧菜单 - 开始 -->
<div class="frameMenu">
    <div class="logo">
        <img src="/images/logo.png" alt="">
        <div class="logoText">
            <h1>睿乐购管理系统</h1>
            <p>RUILEGOUGUANLIXITONG</p>
        </div>
    </div>
    <div class="menu">
        <ul>
            <li>
                <a class="menuFA" href="javascript:void(0)"><i class="iconfont icon-liuliangyunpingtaitubiao03 left"></i>用户管理<i class="iconfont icon-dajiantouyou right"></i></a>
                <dl>
                    <dt><a href="/userlist.jsp">用户列表</a></dt>
                </dl>
            </li>
            <li>
                <a class="menuFA" href="javascript:void(0)"><i class="iconfont icon-shangpin left"></i>商品管理<i class="iconfont icon-dajiantouyou right"></i></a>
                <dl>
                    <dt><a href="/productlist.jsp" >商品列表</a></dt>
                    <dt><a href="/manage/product/search.do">商品搜索</a></dt>
                    <dt><a href="javascript:void(0)">添加商品</a></dt>
                </dl>
            </li>
            <li>
                <a class="menuFA" href="javascript:void(0)"><i class="iconfont icon-yunying left"></i>分类管理<i class="iconfont icon-dajiantouyou right"></i></a>
                <dl>
                    <dt><a href="/categorylist.jsp" onclick="menuCAClick('tgls/base/base_add.html',this)">分类列表</a></dt>
                    <dt><a href="javascript:void(0)" onclick="menuCAClick('tgls/base/base_list.html',this)">改分类名</a></dt>
                    <dt><a href="javascript:void(0)" onclick="menuCAClick('tgls/base/base_customList.html',this)">获取分类详情</a></dt>
                    <dt><a href="javascript:void(0)" onclick="menuCAClick('tgls/base/base_customNewList.html',this)">获取分类子id(平级)</a></dt>
                </dl>
            </li>
            <li>
                <a class="menuFA" href="javascript:void(0)"><i class="iconfont icon-icon left"></i>订单管理<i class="iconfont icon-dajiantouyou right"></i></a>
                <dl>
                    <dt><a href="/orderlist.jsp" onclick="menuCAClick('tgls/print/outPrintData.html',this)">订单列表</a></dt>
                    <dt><a href="javascript:void(0)" onclick="menuCAClick('tgls/print/outPrintData.html',this)">订单详情</a></dt>
                    <dt><a href="javascript:void(0)" onclick="menuCAClick('tgls/print/outPrintData.html',this)">订单发货</a></dt>
                    <dt><a href="javascript:void(0)" onclick="menuCAClick('tgls/print/outPrintData.html',this)">按订单号查询</a></dt>
                </dl>
            </li>
            <li>
                <a class="menuFA" href="javascript:void(0)"><i class="iconfont icon-icon left"></i>管理员模块<i class="iconfont icon-dajiantouyou right"></i></a>
                <dl>
                    <dt><a href="javascript:void(0)" onclick="menuCAClick('tgls/reportForm/reportForm1.html',this)">管理员列表</a></dt>
                </dl>
            </li>
        </ul>
    </div>
</div>
<!-- 左侧菜单 - 结束 -->
<div class="main">
    <!-- 头部栏 - 开始 -->
    <div class="frameTop">
        <img class="jt" src="images/top_jt.png"/>
        <div class="topMenu">
            <ul>
                <li><a href="javascript:void(0)" ><i class="iconfont icon-xiugaimima"></i>修改密码</a></li>
                <li><a href="index.html" id="zx" onclick="zx()"><i class="iconfont icon-084tuichu"></i>注销</a></li>
            </ul>
        </div>
    </div>
    <!-- 头部栏 - 结束 -->
    <!-- 核心区域 - 开始 -->
    <div class="frameMain">
        <div class="title" id="frameMainTitle">
            <span><i class="iconfont icon-xianshiqi"></i>用户列表</span>
        </div>
        <%--//内容显示区域--%>
        <div class="con" style="text-align: center">
            <table id="tab" width="80%" border="1" style="margin: auto" >
                <tr>
                    <th>ID</th>
                    <th>用户名</th>
                    <th>密码</th>
                    <th>手机号</th>
                    <th>权限</th>
                    <th>状态</th>
                </tr>
            </table>
            <iframe id="mainIframe" src="" scrolling="no">

            </iframe>
        </div>
    </div>
    <!-- 核心区域 - 结束 -->
</div>
</body>
<script type="text/javascript" src="framework/jquery-1.11.3.min.js" ></script>
<!--[if lt IE 9]>
<script src="framework/html5shiv.min.js"></script>
<script src="framework/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="layui/layui.js"></script>
<!-- 滚动条插件 -->
<script src="framework/jquery-ui-1.10.4.min.js"></script>
<script src="framework/jquery.mousewheel.min.js"></script>
<script src="framework/jquery.mCustomScrollbar.min.js"></script>
<%--<script src="framework/cframe.js"></script>--%>
<script type="text/javascript" src="framework/frame.js" ></script>
<%--<script src="js/jquery-3.3.1.js"></script>--%>
<script>
    $(function () {
            $.get(
                "/manage/user/list.do",
                function (dt) {
                    if (dt.status !== 0){
                        alert(dt.mag);
                    }else{
                        for (var i = 0;i <dt.data.length;i++){
                            var s = "<tr><td>"+dt.data[i].id+"</td><td>"+dt.data[i].uname+"</td>" +
                                "<td>"+dt.data[i].psd+"</td><td>"+dt.data[i].tel+"</td>" +
                                "<td>"+dt.data[i].type+"</td><td>"+dt.data[i].stats+"</td></tr>";
                            $("#tab").append(s);
                        }
                    }
                },
                "json"
            )


        }
    )

    function zx() {
        var session = pageContext.getsessio;
    }
</script>
</html>

