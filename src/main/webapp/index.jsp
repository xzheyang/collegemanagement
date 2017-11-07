<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <%@include file="common/easy-ui.jspf" %>
</head>
<body>
<h2 >本网站模仿南通大学信息管理系统而构建</h2>


<br>

<a href="${lesson}/down/myWebGuide" class="easyui-linkbutton" iconCls="icon-tip">详细介绍下载</a>

<br>
<br>

<table class="easyui-datagrid" title="用户提供(请不要修改密码)" style="width:176px;height:129px">
    <thead>
    <tr>
        <th >权限</th>
        <th >用户id</th>
        <th >用户密码</th>
    </tr>
    <tr>
        <th>管理员</th><th>1</th><th>1</th>
    </tr>
    <tr>
        <th>老师</th><th>7</th><th>7</th>
    </tr>
    <tr>
        <th>学生</th><th>201400101</th><th>1</th>
    </tr>
    </thead>
</table>

<br>
<br>

<a href="${lesson}/login.jsp" class="easyui-linkbutton" iconCls="icon-redo" >由此登录</a>

</body>
</html>
