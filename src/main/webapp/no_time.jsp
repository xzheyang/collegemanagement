<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/10/28
  Time: 0:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>未允许时间段</title>
    <%@include file="common/easy-ui.jspf" %>
    <script type="text/javascript">

            $(function () {
                $.messager.alert("操作提示", "在未允许时间段申请", "info");
            });

            window.close();
    </script>
</head>
<body>

<h2 color="red">管理员尚未开启选择</h2>

</body>
</html>
