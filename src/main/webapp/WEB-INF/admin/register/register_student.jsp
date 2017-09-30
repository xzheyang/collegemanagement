<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/9/30
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生注册</title>

    <script type="text/javascript">
        //取出传回来的参数并判断信息
        var success ='${success}';
        var error ='${error}';

        if(success!=''){
            alert(success);
        }
        if(error!=''){
            alert(error);
        }
    </script>
</head>
<body>


<form action="${lesson}/admin/registerStudent" method="post">

    <table>
        <tr>
            <td>
                id: <input type="text" name="id">
            </td>
        </tr>
        <tr>
            <td>
                姓名: <input type="text" name="userName">
            </td>
        </tr>
        <tr>
            <td>
                性别: <input type="radio" name="woman" value=true >女<input type="radio" name="woman" value=false >男
            </td>
        </tr>
        <tr>
            <td>
                生日: <input type="text" name="birthday">
            </td>
        </tr>
        <tr>
            <td>
                身份证: <input type="text" name="identification">
            </td>
        </tr>
        <tr>
            <td>
                班级id: <input type="text" name="classId">
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
