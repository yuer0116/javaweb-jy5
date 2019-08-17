<%--
  Created by IntelliJ IDEA.
  User: xiaoyaoxiaodi
  Date: 2019/8/6
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
${uli}
<table>
    <tr>
        <th>ID</th>
        <th>用户名</th>
        <th>密码</th>
        <th>电话</th>
        <th>权限</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${uli.data}" var="us">
        <tr>
            <td>${us.id}</td>
            <td>${us.uname}</td>
            <td>${us.psd}</td>
            <td>${us.tel}</td>
            <td>${us.type}</td>
            <td>${us.stats}</td>
            <td>
                <a href="">编辑</a>
                <a href="">更新</a>
                <a href="/manage/user/disableuser.do?uid=${us.id}">禁用</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
