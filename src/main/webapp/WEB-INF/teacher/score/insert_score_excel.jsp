<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/10/16
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Excel上传分数</title>

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
    格式(第一行写属性):  课程Id,学生Id,学生分数 </h2>
<form enctype="multipart/form-data" action="${lesson}/lesson/insertScoreByExcel" method="post">
    <input type="file" name="file">
    <input type="submit" value="上传">
</form>

</body>
</html>
