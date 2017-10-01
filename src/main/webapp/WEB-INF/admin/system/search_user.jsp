<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/9/25
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>搜索</title>

    <%@include file="../../../common/easy-ui.jspf" %>

    <script type="text/javascript">

        //Bug  点了没有数据的全选,再搜索其它,则不能再点击其他数据
        //Bug  删除数据,刷新页面还在,是因为缓存?

        //要update和saveUser跳转的路径,它们在ToolBar中赋值
        var url;

        //关闭对话框
        function closeLog() {
            blankLog();
            $("#dlg").dialog("close"); //关闭对话框
        }

        //置空弹出框函数
        function  blankLog() {
            $("#id").val("");
            $("#password").val("");
            $("#roleId").val("");
            $("#block").val("");
        }

        //保存,修改user数据
        function saveUser() {
            $("#fm").form("submit",{
                url: url,
                onSubmit: function() {
                    return $(this).form("validate");
                }, //进行验证，通过才让提交
                success: function(result) {
                    var result = eval("(" + result + ")"); //将json格式的result转换成js对象
                    if(result.success) {
                        $.messager.alert("系统提示", "数据保存成功");
                        blankLog(); //保存成功后将内容置空
                        $("#dlg").dialog("close"); //关闭对话框
                        $("#dg").datagrid("reload"); //刷新一下
                    } else {
                        $.messager.alert("系统提示", "数据保存失败");
                        return;
                    }
                }
            });
        }



        /*
        function clearData() {
            var item = $('#filegrid').datagrid('getRows');
            if (item) {
                for (var i = item.length - 1; i >= 0; i--) {
                    var index = $('#filegrid').datagrid('getRowIndex', item[i]);
                    $('#filegrid').datagrid('deleteRow', index);
                }
            }
        }*/



        //查询函数
        function searchUser(value,name){

            realSearchUser(value,name);
            //置空显示框
            var rows = $('#dg').datagrid('getRows');
            for(var i=rows.length-1;i>=0;i--){
                $('#dg').datagrid('deleteRow',i);
            }
        }

        function realSearchUser (value,name) {
            //datagrid初始化
            $('#dg').datagrid({
                //发送的额外参数
                queryParams: {
                    searchType: name,
                    searchValue: value,
                },

                //请求数据的url
                url: '${lesson}/admin/searchUser',
                //载入提示信息
                loadMsg: 'loading...',
                //水平自动展开，如果设置此属性，则不会有水平滚动条，演示冻结列时，该参数不要设置
                fitColumns: true,
                //数据多的时候不换行
                nowrap: true,
                //设置分页
                pagination: true,
                //设置每页显示的记录数，默认是10个
                pageSize: 3,
                //每页显示记录数项目
                pageList: [ 3, 5, 10, 15, 20],
                //当前页 默认1
                pageNumber: 1,
                //指定id为标识字段，在删除，更新的时候有用，如果配置此字段，在翻页时，换页不会影响选中的项
                idField: 'id',
                //上方工具条 添加 修改 删除 刷新按钮
                toolbar:[{
                    iconCls: 'icon-add',    //图标
                    text: '添加',            //名称
                    handler: function () {  //回调函数

                            //打开对话框并且设置标题
                            $("#dlg").dialog("open").dialog("setTitle", "添加User");
                            //添加url
                            url = "${lesson}/admin/insertUser"
                    }
                },'-',{
                    iconCls: 'icon-edit',
                    text: '修改',
                    handler: function () {
                        //获取选中要修改的行
                        var selectedRows = $("#dg").datagrid("getSelections");
                        //确保被选中行只能为一行
                        if(selectedRows.length != 1) {
                            $.messager.alert("系统提示", "请选择一个要修改的user");
                            return;
                        }
                        //获取选中行id
                        var row = selectedRows[0];
                        //打开对话框并且设置标题
                        $("#dlg").dialog("open").dialog("setTitle", "修改user信息");
                        //将数组回显对话框中
                        $("#fm").form("load", row);//会自动识别name属性，将row中对应的数据，填充到form表单对应的name属性中
                        //MD5不能显示password,oldId用来修改原来id
                        $("#password").val("");
                        $("#oldId").val(row.id);
                        //url修改
                        url = "${lesson}/admin/updateUser" ;
                    }
                },'-',{
                    iconCls: 'icon-remove',
                    text: '删除',
                    handler: function () {
                        //获取选中要删除的行
                        var selectedRows = $("#dg").datagrid("getSelections");
                        //判断是否有选择的行
                        if(selectedRows.length == 0) {
                            $.messager.alert("系统提示", "请选择要删除的数据");
                            return;
                        }
                        //定义选中 选中id数组
                        var idsStr = [];
                        //循环遍历将选中行的id push进入数组
                        for(var i = 0; i < selectedRows.length; i++) {
                            idsStr.push(selectedRows[i].id);
                        }
                        //将数组安装,连接成字符串
                        var ids = idsStr.join(","); //1,2,3,4
                        //提示是否确认删除
                        $.messager.confirm("系统提示", "<font color=red>您确定要删除选中的"+selectedRows.length+"条数据么？</font>", function(r) {
                            if(r) {
                                $.post("${lesson}/admin/deleteUser",
                                    {ids: ids}, function(result){
                                        if(result.exist) {
                                            $.messager.alert("系统提示", '用户有学生或老师信息,请去用户管理删除');
                                        } else if(result.success) {
                                            $.messager.alert("系统提示", "数据删除成功！");
                                            $("#dg").datagrid("reload");
                                            //clearData();      使用方法清除列表,但依然会有TypeError
                                            $('#dg').datagrid('clearData');
                                            //$("#dg").datagrid("clearSelections");  两个都是清除选择,但这个问题在于缓存
                                            //$("#dg").datagrid("clearChecked");
                                        } else {
                                            $.messager.alert("系统提示", "数据删除失败！");
                                        }
                                    }, "json");
                            }
                        });

                    }
                }],
                //同列属性，但是这些列将会冻结在左侧,z大小不会改变，当宽度大于250时，会显示滚动条，但是冻结的列不在滚动条内
                frozenColumns:[[
                    {field:'checkbox',checkbox:true},    //复选框
                    {field:'id',title:'编号',width:200}    //id字段
                ]],
                columns:[[
                    {field:'roleId',title:'权限[管理员(1),教师(2),学生(3)]',width:300},   //其他数据 字段
                    {field:'block',title:'锁定',width:300},
                ]],
            });
        }

    </script>
</head>
<body>

<!--
            搜索框
-->
<input id="ss" class="easyui-searchbox" style="width:260px"
       data-options="searcher:searchUser,prompt:'Please Input Value',menu:'#mm'"></input>
<div id="mm" style="width:60px;height:80px">
    <div data-options="name:'id'">Id</div>
    <div data-options="name:'name'">姓名</div>
</div>

<br><br><br>

<!--
            显示框
-->

<table id="dg"></table>


<!--
            保存修改弹出框
-->

<div id="dlg" class="easyui-dialog"  style="width:500px; height:250px;top:100px; padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="8px">
            <tr>
                <td>id</td>
                <td> <!-- 原始id -->
                    <input type="hidden" id="oldId" name="oldId" class="easyui-validatebox" required="true">
                    <input type="text" id="id" name="id" class="easyui-validatebox" required="true">
                </td>
            </tr>
            <tr>
                <td>password</td>
                <td>
                    <input type="text" id="password" name="password" class="easyui-validatebox"
                           style="width:60px">&nbsp;(为空则不修改)
                </td>
            </tr>
            <td>权限</td>
            <td>
                <select id="roleId" name="roleId" class="easyui-combobox" name="dept" style="width:200px;">
                    <option value=1>管理员(1)</option>
                    <option value=2>教师(2)</option>
                    <option value=3>学生(3)</option>
                </select>
            </td>
            <tr>
                <td>是否禁止登录</td>
                <td>
                    <select id="block" name="block" class="easyui-combobox" name="dept" style="width:200px;">
                        <option value=false>否</option>
                        <option value=true>是</option>
                    </select>
                </td>
            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <div>
        <a href="javascript:saveUser()" class="easyui-linkbutton" iconCls="icon-ok" plain="true">保存</a>
        <a href="javascript:closeLog()" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">关闭</a>
    </div>
</div>




<div class="se-preview-section-delimiter"></div>


</body>
