<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/9/25
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>

    <script type="text/javascript">

        //取出传回来的参数error并判断错误信息
        var error ='<%=request.getParameter("error")%>';
        if(error=='passError'){
            alert("密码或用户名错误");
        }else if(error=='blocked'){
            alert("用户被锁定");
        }

    </script>
</head>

<body>

登录<br>





<form action="login" method="post">

        <table>
            <tr>
                <td>
                    id: <input type="text" name="id">
                </td>
            </tr>
            <tr>
                <td>
                    密码: <input type="password" name="password">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="提交">
                </td>
            </tr>
        </table>

</form>

</body>
</html>
