<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/11/10
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>备份还原</title>

    <%@include file="../../../common/easy-ui.jspf" %>

    <script type="text/javascript">


        //显示班级函数
        $(
        function realListBackup () {
            //datagrid初始化
            $('#dg').datagrid({

                //请求数据的url
                url: '${lesson}/admin/listBackupData',
                //载入提示信息
                loadMsg: 'loading...',
                //水平自动展开，如果设置此属性，则不会有水平滚动条，演示冻结列时，该参数不要设置
                fitColumns: true,
                //水平自动展开，如果设置此属性，则不会有水平滚动条，演示冻结列时，该参数不要设置
                fitColumns: true,
                //数据多的时候不换行
                nowrap: true,
                //设置分页
                pagination: true,
                //设置每页显示的记录数，默认是10个
                pageSize: 8,
                //每页显示记录数项目
                pageList: [ 8,  15, 20],
                //当前页 默认1
                pageNumber: 1,
                //指定id为标识字段，在删除，更新的时候有用，如果配置此字段，在翻页时，换页不会影响选中的项
                idField: 'id',
                //同列属性，但是这些列将会冻结在左侧,z大小不会改变，当宽度大于250时，会显示滚动条，但是冻结的列不在滚动条内
                frozenColumns:[[
                    {field:'checkbox',checkbox:true},    //复选框

                ]],
                columns:[[
                    {field:'id',title:'时间',width:300}

                ]],
                //上方工具条 添加 修改 删除 刷新按钮
                toolbar:[{
                    iconCls: 'icon-edit',    //图标
                    text: '备份',            //名称
                    handler: function () {  //回调函数
                        $.get(
                            "${lesson}/admin/copyBackupData",
                            function (data) {
                                if(data.result) {
                                    $.messager.alert("系统提示", "数据备份成功！");
                                    $("#dg").datagrid("reload");
                                    $('#dg').datagrid('clearData');

                                } else {
                                    $.messager.alert("系统提示", "数据备份失败！");
                                }
                            },"json"
                        )
                    }
                },'-',{
                    iconCls: 'icon-undo',    //图标
                    text: '还原',            //名称
                    handler: function () {  //回调函数
                        //获取选中要删除的行
                        var selectedRows = $("#dg").datagrid("getSelections");
                        //判断是否有选择的行
                        if(selectedRows.length == 0) {
                            $.messager.alert("系统提示", "请选择要还原的时间");
                            return;
                        }else if(selectedRows.length > 1){
                            $.messager.alert("系统提示", "只能还原的一个时间");
                            return;
                        }
                        $.messager.confirm("系统提示", "<font color=red>您确定要还原所有数据么？</font>", function(r) {
                            if(r) {
                                $.post("${lesson}/admin/restoreBackupData",
                                    {time:  selectedRows[0].id}, function(data){
                                        if(data.result) {
                                            $.messager.alert("系统提示", "数据还原成功！");
                                            $("#dg").datagrid("reload");

                                            $('#dg').datagrid('clearData');

                                        } else {
                                            $.messager.alert("系统提示", "数据还原失败！");
                                        }
                                    }, "json");
                            }
                        });

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
                                $.post("${lesson}/admin/deleteBackupData",
                                    {times: ids}, function(data){
                                       if(data.result) {
                                            $.messager.alert("系统提示", "数据删除成功！");
                                            $("#dg").datagrid("reload");

                                            $('#dg').datagrid('clearData');

                                        } else {
                                            $.messager.alert("系统提示", "数据删除失败！");
                                        }
                                    }, "json");
                            }
                        });

                    }
                }]
            });
        });
    </script>
</head>
<body>


<!-- 显示框 -->
<table id="dg"></table><!-- 显示框 -->


</body>
</html>
