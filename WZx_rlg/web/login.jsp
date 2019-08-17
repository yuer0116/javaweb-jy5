
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <!-- Google Chrome Frame也可以让IE用上Chrome的引擎: -->
    <meta name="renderer" content="webkit">
    <!--国产浏览器高速模式-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 定义页面的最新版本 -->
    <meta name="keywords" content="搜索关键字，以半角英文逗号隔开" />
    <title>睿乐购后台管理登录</title>

    <!-- 公共样式 开始 -->
    <link rel="shortcut icon" href="images/favicon.ico"/>
    <link rel="bookmark" href="images/favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <script type="text/javascript" src="framework/jquery-1.11.3.min.js" ></script>
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css">
    <!--[if lt IE 9]>
    <script src="framework/html5shiv.min.js"></script>
    <script src="framework/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="layui/layui.js"></script>
    <%--<!-- 滚动条插件 -->--%>
    <%--<script src="framework/jquery-ui-1.10.4.min.js"></script>--%>
    <%--<script src="framework/jquery.mousewheel.min.js"></script>--%>
    <%--<script src="framework/jquery.mCustomScrollbar.min.js"></script>--%>
    <%--<script src="framework/cframe.js"></script><!-- 仅供所有子页面使用 -->--%>
    <!-- 公共样式 结束 -->
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <script type="text/javascript" src="js/login.js"></script>
    <%--<script src="js/jquery-3.3.1.js"></script>--%>
</head>

<body>
<!--主体 开始-->
<div class="login_main">
    <!--轮播图 开始-->
    <div class="layui-carousel lbt" id="loginLbt">
        <div carousel-item>
            <div class="item" style="background: url(images/login_bg1.jpg) no-repeat; background-size: cover;"></div>
            <div class="item" style="background: url(images/login_bg2.jpg) no-repeat; background-size: cover;"></div>
        </div>
    </div>
    <!--轮播图 结束-->
    <div class="logo">
        <img src="images/login_logo.png" />
        <div>
            <h1>睿乐购后台管理系统登录</h1>
        </div>
    </div>
    <div class="form_tzgg">
        <div class="form">
            <form action= "/manage/user/login.do" method="post" class="layui-form">
                <div class="title">用户登录</div>
                <div class="con" onclick="getFocus(this)">
                    <input type="text" name="username"   placeholder="请输入您的用户名" autocomplete="off" class="layui-input" id="un">
                </div>
                <div class="con" onclick="getFocus(this)">
                    <input type="password" name="password"  placeholder="请输入您的账户密码" autocomplete="off" class="layui-input" id="pd">
                </div>
                <div class="but">
                    <input type="button" value="登录" id="lc_2" >
                </div>
            </form>
        </div>
    </div>
</div>
<!--主体 结束-->
</body>
<script src="/js/jquery-3.3.1.js"></script>
<script>
     $("#lc_2").click(function () {
         var un = $("#un").val();
         var pd = $("#pd").val();
         $.post(
             "/manage/user/login.do",
             {username:un,password:pd},
             function (dt) {
                 window.open("home.jsp");
             })
     })

     $("#un").blur(function () {
         var username = $("#un").val();
         $.post(
             "/manage/user/yz.do",
             {username:username},
             function (bool) {
                 $("#un").after("<span>" + bool + "</span>");
             }
         )
     })
</script>
</html>
