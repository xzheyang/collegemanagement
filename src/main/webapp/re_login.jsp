<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/10/12
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>重复登录</title>
    <script type="text/javascript">


        //取出传回来的参数error并判断错误信息
        var error ='<%=request.getParameter("error")%>';
        if(error=='reLogin') {
            alert("重复登录或未安全退出,请再次登录");
        }
        location.href = 'logout';
    </script>
</head>
<body>

</body>
</html>
