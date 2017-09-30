<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/9/29
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>班级上传</title>

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
    注意:上一学年的班级必须注册完,才能注册这一学年的
    格式(第一行写属性):  班级名, 学年 ,教师id </h2>
<form enctype="multipart/form-data" action="${lesson}/admin/registerClassExcel" method="post">
    <input type="file" name="file">
    <input type="submit" value="上传">
</form>

</body>
</html>
