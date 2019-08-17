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
    <link rel="shortcut icon" href="/images/favicon.ico"/>
    <link rel="bookmark" href="/images/favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="/css/base.css">
    <script type="text/javascript" src="/framework/jquery-1.11.3.min.js" ></script>
    <link rel="stylesheet" type="text/css" href="/layui/css/layui.css">
    <!--[if lt IE 9]>
    <script src="/framework/html5shiv.min.js"></script>
    <script src="/framework/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="/layui/layui.js"></script>
    <!-- 滚动条插件 -->
    <script src="/framework/jquery-ui-1.10.4.min.js"></script>
    <script src="/framework/jquery.mousewheel.min.js"></script>
    <script src="/framework/jquery.mCustomScrollbar.min.js"></script>
    <script src="/framework/cframe.js"></script><!-- 仅供所有子页面使用 -->
    <!-- 公共样式 结束 -->
    <link rel="stylesheet" type="text/css" href="/css/frameStyle.css">
    <%--<link rel="stylesheet" type="text/css" href="/css/productsearch.css">--%>
    <script type="text/javascript" src="/framework/frame.js" ></script>
    <style>
        #nr{
            background: darkgray;
        }
        #productName{
            height: 60px;
            width: 300px;
            border-radius: 8px;
            margin-left: -100px;
            margin-top: 50px;
            font-size: 20px;
        }
        #yes{
            background: #2F4056;
            height: 60px;
            width: 100px;
            border-radius: 8px;
            color: white;
            font-size: 20px;

        }
        #data{
            height: 330px;
            width: 750px;
            background: cornflowerblue;
            margin-top: 50px;
            margin-left: 60px;
        }
    </style>
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
                    <dt><a href="/manage/user/list.do" onclick="menuCAClick('tgls/agent/agent_add.html',this)">用户列表</a></dt>
                </dl>
            </li>
            <li>
                <a class="menuFA" href="javascript:void(0)"><i class="iconfont icon-shangpin left"></i>商品管理<i class="iconfont icon-dajiantouyou right"></i></a>
                <dl>
                    <dt><a href="/manage/product/plist.do" onclick="menuCAClick('tgls/goodsManage/goodsType_list.html',this)">商品列表</a></dt>
                    <dt><a href="/manage/product/search.do" onclick="menuCAClick('tgls/goodsManage/goods_list.html',this)">商品搜索</a></dt>
                    <dt><a href="javascript:void(0)" onclick="menuCAClick('tgls/goodsManage/goods_add.html',this)">添加商品</a></dt>
                </dl>
            </li>
            <li>
                <a class="menuFA" href="javascript:void(0)"><i class="iconfont icon-yunying left"></i>分类管理<i class="iconfont icon-dajiantouyou right"></i></a>
                <dl>
                    <dt><a href="javascript:void(0)" onclick="menuCAClick('tgls/base/base_add.html',this)">添加分类</a></dt>
                    <dt><a href="javascript:void(0)" onclick="menuCAClick('tgls/base/base_list.html',this)">改分类名</a></dt>
                    <dt><a href="javascript:void(0)" onclick="menuCAClick('tgls/base/base_customList.html',this)">获取分类详情</a></dt>
                    <dt><a href="javascript:void(0)" onclick="menuCAClick('tgls/base/base_customNewList.html',this)">获取分类子id(平级)</a></dt>
                </dl>
            </li>
            <li>
                <a class="menuFA" href="javascript:void(0)"><i class="iconfont icon-icon left"></i>订单管理<i class="iconfont icon-dajiantouyou right"></i></a>
                <dl>
                    <dt><a href="javascript:void(0)" onclick="menuCAClick('tgls/print/outPrintData.html',this)">订单列表</a></dt>
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
        <img class="jt" src="/images/top_jt.png"/>
        <div class="topMenu">
            <ul>
                <li><a href="javascript:void(0)" onclick="menuCAClick('tgls/modify_password.html',this)"><i class="iconfont icon-xiugaimima"></i>修改密码</a></li>
                <li><a href="login.html"><i class="iconfont icon-084tuichu"></i>注销</a></li>
            </ul>
        </div>
    </div>
    <!-- 头部栏 - 结束 -->

    <!-- 核心区域 - 开始 -->
    <div class="frameMain">
        <div class="title" id="frameMainTitle">
            <span><i class="iconfont icon-xianshiqi"></i>后台首页</span>
        </div>
        <%--//内容显示区域--%>
        <div id="nr" class="con" style="text-align: center">
            <form action="/manage/product/search.do" method="post">
                <div>
                    <input type="text" id="productName" placeholder="请输入您要查询的商品"/>
                    <input type="button" id="yes" value="搜索"/>
                </div>
                <div id="data"></div>
            </form>

        </div>
            <%--<iframe id="mainIframe" src="" scrolling="no">--%>
            <%----%>
            <%--</iframe>--%>

        </div>


    </div>
    <!-- 核心区域 - 结束 -->
</div>
</body>
<script src="js/jquery-3.3.1.js"></script>
<script>
    $("#yes").click(function () {
        var spm = $("#productName").val();
        $.post(
            "/manage/product/search.do",
            {productName:spm},
            function (dt) {
                if (dt.status != 0) {
                    $("#data").empty();
                    $("#data").append("<p>"+dt.mag+"</p>");
                }else{
                    $("#data").empty();
                    $("#data").append("<p>"+"商品名："+dt.date.pname+"</br>"+"分类:" +
                        dt.date.parentId+"</br>" +"</p>");
                }
            }
        )
    })
</script>


</html>
