<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/9/25
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>信息查询系统后台管理</title>
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
                <h2>信息查询后台系统</h2>
            </td>
        </tr>
    </table>
</div>

<--

        左侧导航栏

-->

<!-- 左侧导航栏,加title自动有侧滑栏 -->
<div data-options="region:'west'" style="width: 200px" title="导航菜单">

    <div class="easyui-accordion" data-options="fit:true,border:false">
        <div title="快键管理" data-options="selected:true,iconCls:'icon-item'" style="padding: 10px">

            <a href="javascript:openTab('搜索用户','admin/system/search_user')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-search'" style="width: 150px">搜索用户</a>

            <a href="javascript:openTab('撤回user操作','admin/')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-up'" style="width: 150px">撤回操作</a>


        </div>

        <div title="注册" data-options="iconCls:'icon-'" style="padding:10px;">

            <a href="javascript:openTab('Excel注册学生','admin/register/register_student_excel')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-large_chart'" style="width: 150px;">Excel注册学生</a>
            <a href="javascript:openTab('Excel注册班级','admin/register/register_class_excel')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-'" style="width: 150px;">Excel注册班级</a>
            <a href="javascript:openTab('Excel注册教师','admin/register/register_teacher_excel')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-min_edit'" style="width: 150px;">Excel注册教师</a>

            <a href="javascript:openTab('注册学生','admin/register/register_student')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-large_chart'" style="width: 150px;">注册学生</a>
            <a href="javascript:openTab('注册班级','admin/register/register_class')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-'" style="width: 150px;">注册班级</a>
            <a href="javascript:openTab('注册教师','admin/register/register_teacher')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-min_edit'" style="width: 150px;">注册教师</a>
        </div>

        <div title="用户管理" data-options="iconCls:'icon-'" style="padding:10px;">
            <a href="javascript:openTab('班级管理','admin/details/class_details')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-'" style="width: 150px;">班级管理</a>
            <a href="javascript:openTab('教师管理','admin/details/teacher_details')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-'" style="width: 150px;">教师管理</a>
            <a href="javascript:openTab('学生管理','admin/details/student_details')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-'" style="width: 150px;">学生管理</a>
        </div>

        <div title="课程与分数管理" data-options="iconCls:'icon-'" style="padding:10px;">
            <a href="javascript:openTab('Excel添加课程','admin/lesson/insert_lesson_excel')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-'" style="width: 150px;">Excel添加课程</a>
            <a href="javascript:openTab('课程管理','admin/details/lesson_details')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-'" style="width: 150px;">课程管理</a>
            <a href="#" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-'" style="width: 150px;">批量上传分数</a>
            <a href="#" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-'" style="width: 150px;">批量修改分数</a>
        </div>


        <div title="公选课操作" data-options="iconCls:'icon-'" style="padding:10px;">
            <a href="#" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-'" style="width: 150px;">批量上传分数</a>
            <a href="#" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-'" style="width: 150px;">批量修改分数</a>
        </div>


        <div title="系统管理" data-options="iconCls:'icon-'" style="padding:10px">
            <a href="#" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-link'" style="width: 150px">友情链接管理</a>
            <a href="javascript:openTab('修改密码','user/update_password')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-modifyPassword'" style="width: 150px;">修改密码</a>
            <a href="#')" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'icon-reload'" style="width: 150px;">刷新系统缓存</a>
            <a href="${lesson}/logout" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-exit'"
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
