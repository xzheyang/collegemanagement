<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/9/27
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>

    <%@include file="../../common/easy-ui.jspf" %>

    <script type="text/javascript">

        function update(){
            //进行表单校验
            var v = $("#updatePasswordBox").form("validate");//对应表单中的所有输入框进行校验
            if(v){//表单校验通过
                //判断两次输入是否一致
                var v1 = $("#newPassword").val();
                var v2 = $("#rePassword").val();
                var v3 = $("#oldPassword").val();
                if(v1 == v2){
                    //输入一致，发送ajax请求，修改当前用户的密码
                    var url = "${lesson}/user/updatePassword";
                    $.post(url,
                        {"newPassword":v1,
                          "oldPassword":v3
                        },
                        function(data){
                        if(data.data == '1'){
                            //修改密码成功
                            $.messager.alert("提示信息","密码修改成功！","info");
                        }else{
                            //修改失败
                            $.messager.alert("提示信息","密码修改失败！","warning");
                        }
                        //关闭修改密码的窗口
                        $("#editPwdWindow").window("close");
                    });
                }else{
                    //输入不一致，提示用户输入不一致
                    $.messager.alert("提示信息","两次输入密码不一致！","warning");
                }
            }
        }
    </script>

</head>
<body>


<form id="updatePasswordBox" name="修改密码" >
<table cellpadding=3>

    <tr>
        <td>旧密码：</td>
        <td><input id="oldPassword" type="Password" class="easyui-validatebox"
                   required="true" data-options="validType:'length[1,11]'" />
        </td>
    </tr>

    <tr>
        <td>新密码：</td>
        <td><input id="newPassword" type="Password" class="easyui-validatebox"
                   required="true" data-options="validType:'length[1,11]'" />
        </td>
    </tr>
    <tr>
        <td>确认密码：</td>
        <td><input id="rePassword" type="Password" class="easyui-validatebox"
                   required="true" data-options="validType:'length[1,11]'"/>
        </td>
    </tr>
</table>
</div>
<div  border="false" style="text-align: right; height: 30px; line-height: 30px;">
    <a id="enter" class="easyui-linkbutton" icon="icon-ok" href="javascript:update()" >确定</a>
    <a id="cancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
</div>
</div>

</form>

</body>
</html>
