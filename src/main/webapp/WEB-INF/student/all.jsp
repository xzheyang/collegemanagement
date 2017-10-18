<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/9/25
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>信息查询系统</title>
    <%@include file="../../common/easy-ui.jspf" %>
    <style type="text/css">
        body {
            font-family: microsoft yahei;
        }
    </style>

    <script type="text/javascript">

        function openTab(text,url) {
            //判断当前选项卡是否存在
            if($("#tabs").tabs('exists',text)){
                //如果存在 显示
                $("#tabs").tabs("select",text);
            }else{
                //如果不存在 则新建一个
                $("#tabs").tabs('add',{
                    title:text,
                    closable:true,      //是否允许选项卡摺叠。
                    // iconCls:icon,    //显示图标
                    content:"<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${path}/"+url+"'></iframe>"
                    //url 远程加载所打开的url
                })
            }}

    </script>

</head>
<body class="easyui-layout">

<--

上方导航栏

-->


<div data-options="region:'north'" style="height: 78px; background-color: #E0ECFF">

    <table style="padding: 5px" width="100%">
        <tr>
            <td width="50%">
                <h2>信息查询系统</h2>
            </td>
        </tr>
    </table>
</div>

<--

左侧导航栏

-->

<!-- 左侧导航栏,加title自动有侧滑栏 -->
<div data-options="region:'west'" style="width: 200px" title="导航菜单">

    <div class="easyui-accordion" data-options="fit:true,border:false" >
        <div title="分数查询" data-options="selected:true,iconCls:'icon-item'" style="padding: 10px">

            <a href="javascript:openTab('全部成绩查询','student/score/list_score')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-search'" style="width: 150px">全部成绩查询</a>

            <a href="javascript:openTab('补考成绩查询','')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-search'" style="width: 150px">补考成绩查询</a>

        </div>

        <div title="课程查询" data-options="iconCls:'icon-'" style="padding:10px;">

            <a href="#" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-large_chart'" style="width: 150px;">课程表</a>


        </div>

        <div title="个人信息" data-options="iconCls:'icon-'" style="padding:10px;">
            <a href="#" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-'" style="width: 150px;">个人信息</a>
        </div>

        <div title="公选课" data-options="iconCls:'icon-'" style="padding:10px;">
            <a href="#" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-'" style="width: 150px;">公选课报名</a>
            <a href="#" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-'" style="width: 150px;">体育选课报名</a>
        </div>


        <div title="系统管理" data-options="iconCls:'icon-'" style="padding:10px">
            <a href="javascript:openTab('修改密码','user/update_password')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-modifyPassword'" style="width: 150px;">修改密码</a>
            <a href="#" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-reload'" style="width: 150px;">刷新系统缓存</a>
            <a href="${path}/logout" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-exit'"
               style="width: 150px;">安全退出</a>
        </div>
    </div>

</div>

<--

中央显示栏

-->

<div data-options="region:'center'" style="background:#eee;" >

    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="首页" data-options="iconCls:'icon-home'">
            <div align="center" style="padding-top: 100px"><font color="red" size="10">欢迎使用</font></div>
        </div>
    </div>

</div>

<--

下方导航栏

-->

<div data-options="region:'south'" style="height: 25px;padding: 5px" align="center">
    <div data-options="region:'south'" style="height: 25px;padding: 5px" align="center">
        仿南通大学信息查询系统
    </div>
</div>
</body>
</html>
