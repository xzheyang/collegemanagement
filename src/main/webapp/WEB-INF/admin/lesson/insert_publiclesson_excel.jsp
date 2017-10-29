<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/10/22
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Excel添加公选课</title>
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
    格式(第一行写属性):  公选课名,指导教师名字,指导教师Id,课程时间,课程学分,人数上限 </h2>
<form enctype="multipart/form-data" action="${lesson}/admin/insertPublicLessonByExcel" method="post">
    <input type="file" name="file">
    <input type="submit" value="上传">
</form>

</body>
</html>
