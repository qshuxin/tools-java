<%--
  Created by IntelliJ IDEA.
  User: shuxinqin
  Date: 18-3-20
  Time: 下午2:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>建表语句生成实体类</title>
</head>
<body>
<div style="padding: 100px 100px 10px;">
    <form class="bs-example bs-example-form" role="form" action="upload" method="post">
        <div class="input-group input-group-lg">
            <span class="input-group-addon">数据库建表语句</span><br>
            <textarea class="form-control" rows="10" cols="100" placeholder="" id="createTable" name="createTable">${requestScope.source}</textarea>
        </div>
        <br>
        <%--<div class="input-group-btn input-group-lg">--%>
            <%--<span class="input-group-addon">性别</span>--%>
            <%--<span style="font-size:180px;">--%>
                <%--<select id="sex" name="sex">--%>
                    <%--<option value="1">男</option>--%>
                    <%--<option value="2">女</option>--%>
                <%--</select>--%>
            <%--</span>--%>
        <%--</div>--%>
        <%--<br>--%>
        <%--<div class="input-group input-group-lg">--%>
            <%--<span class="input-group-addon">年龄</span>--%>
            <%--<input type="text" class="form-control" placeholder="" id="age" name="age">--%>
        <%--</div>--%>
        <br>
        <div class="input-group input-group-lg" >
            <span class="input-group-addon">实体类</span><br>
            <textarea class="form-control"  rows="10" cols="100" placeholder="" id="remark" name="remark" >${requestScope.bean}</textarea>
        </div>
        <div class="input-group input-group-lg" >
            <span class="input-group-addon">Mapper接口</span><br>
            <textarea class="form-control"  rows="10" cols="100" placeholder="" id="remark" name="remark" >${requestScope.mapper}</textarea>
        </div>
        <div class="input-group input-group-lg" >
            <span class="input-group-addon">XML</span><br>
            <textarea class="form-control"  rows="10" cols="100" placeholder="" id="remark" name="remark" >${requestScope.xml}</textarea>
        </div>
        <button type="submit">提交</button>
    </form>
</div>
</body>
</html>
