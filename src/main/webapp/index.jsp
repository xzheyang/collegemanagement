<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<h2>本网站模仿南通大学信息管理系统而构建</h2>
由ssm+shiro搭建<br>

详细介绍下载
<form action="${lesson}/down/myWebGuide" method="get">
    <input type="submit" value="下载">
</form>



帐号提供(请不要修改提供的帐号)
<table>
    <tr>
        <td>类别</td><td>帐号</td><td>密码</td>
    </tr>
    <tr>
        <td>管理员</td><td>1</td><td>1</td>
    </tr>
        <td>老师</td><td>7</td><td>7</td>
    <tr>
        <td>学生</td><td>201400101</td><td>1</td>
    </tr>
</table>

<br>
<br>
<a href="${lesson}/login.jsp">由此登录</a>
</body>
</html>
