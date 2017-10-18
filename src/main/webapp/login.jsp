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
    <%@include file="common/easy-ui.jspf" %>
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



<div id="loginWindow" class="easyui-window" title="登录" style="width:350px;height:188px;top:100px;padding:5px;"
     minimizable="false" maximizable="false" resizable="false" collapsible="false">
    <div class="easyui-layout" fit="true">
        <div region="center" border="false" style="padding:5px;background:#fff;border:1px solid #ccc;">
            <form id="loginForm" method="post" action="login">
                <div style="padding:5px 0;">
                    帐号:
                    <input type="text" name="id" style="width:260px;" class="easyui-validatebox" required="true" />
                </div>
                <div style="padding:5px 0;">
                    密码:
                    <input type="password" name="password" style="width:260px;" class="easyui-validatebox" required="true" />
                </div>
                <div style="text-align:center;padding:10px 0;">
                    <input type="submit" value="登录" class="easyui-linkbutton" style="width: 76px">
                </div>
            </form>
        </div>


    </div>
</div>

</body>

</html>
