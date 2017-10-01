<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/10/1
  Time: 0:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册班级</title>

    <script type="text/javascript">
        //取出传回来的参数并判断信息
        var success ='${success}';

        if(success!=''){
            alert(success);
        }

    </script>
</head>
<body>

<form action="${lesson}/admin/registerClass" method="post">

    <table>
        <tr>
            <td>
                班级名: <input type="text" name="name" >
            </td>
        </tr>

        <tr>
            <td>
                年级(四位): <input type="text" name="year">
            </td>
        </tr>
        <tr>
            <td>
                教师id: <input type="text" name="teacherId">
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
