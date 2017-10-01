<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/9/28
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>excel上传学生数据</title>

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

<h2 style="color: red">
    格式(第一行写属性): 姓名,性别(女为1,男为0),生日(格式为1995/10/6),身份证号(18位),班级号 </h2>
<form enctype="multipart/form-data" action="${lesson}/admin/registerStudentExcel" method="post">
    <input type="file" name="file">
    <input type="submit" value="上传">
</form>

</body>
</html>
