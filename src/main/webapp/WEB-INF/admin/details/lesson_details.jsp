<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/10/8
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课程管理</title>

    <%@include file="../../../common/easy-ui.jspf" %>

    <script type="text/javascript">

        //要update和save跳转的路径,它们在ToolBar中赋值
        var url;
        //搜寻类型和值
        var searchType;
        var searchValue;

        //关闭对话框
        function closeLog() {
            blankLog();
            $("#dlg").dialog("close"); //关闭对话框
        }

        //置空弹出框函数
        function  blankLog() {
            $("#id").val("");
            $("#name").val("");
            $("#session").val("");
            $("#teacherId").val("");
        }


        function saveLesson() {
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

        //把easy ui屏蔽的onchange重启
        $(document).ready(function(){
            //相当于select的onchange事件
            $('#byClass').combobox({
                onChange:function(newValue,oldValue){
                    //newValue,新值，用户选择后的值
                    //oldValue,旧值，用户选择前的值
                    listLessons(newValue)
                }
            });
        });


        //初始化班级下拉框
        $(function(){
                $.ajax({
                    url:"${lesson}/details/listClass",
                    dataType:"json",
                    type:"GET",

                    success:function(data){
                        //绑定第一个下拉框

                        $('#byClass').combobox({
                                data: data,
                                valueField: 'id',
                                textField: 'name',
                                editable:false, //不可编辑状态
                                panelHeight:'auto'
                            }
                        );
                    },
                    error:function(){
                        alert("初始化下拉控件失败");
                    }
                })
            }
        );

        function searchLesson(value,name){
            searchType=name;
            searchValue=value;
            realListLesson('','${lesson}/details/searchLessonById');
        }

        function listLessons(byClass){
            realListLesson(byClass,'${lesson}/details/listLessonByClass')
        }



        function realListLesson (byClass,ToUrl) {
            //datagrid初始化
            $('#dg').datagrid({
                //发送的额外参数
                queryParams: {
                    byClass: byClass,
                    searchType: searchType,
                    searchValue: searchValue
                },

                //请求数据的url
                url: ToUrl,
                //载入提示信息
                loadMsg: 'loading...',
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
                //上方工具条 添加 修改 删除 刷新按钮
                toolbar:[{
                    iconCls: 'icon-add',    //图标
                    text: '添加',            //名称
                    handler: function () {  //回调函数

                        //打开对话框并且设置标题
                        $("#dlg").dialog("open").dialog("setTitle", "添加课程");
                        //添加url
                        url = "${lesson}/admin/insertLesson"
                    }
                },'-',{
                    iconCls: 'icon-edit',
                    text: '修改',
                    handler: function () {
                        //获取选中要修改的行
                        var selectedRows = $("#dg").datagrid("getSelections");
                        //确保被选中行只能为一行
                        if(selectedRows.length != 1) {
                            $.messager.alert("系统提示", "请选择一个要修改的课程");
                            return;
                        }
                        //获取选中行id
                        var row = selectedRows[0];
                        //打开对话框并且设置标题
                        $("#dlg").dialog("open").dialog("setTitle", "修改课程信息");
                        //将数组回显对话框中
                        $("#fm").form("load", row);//会自动识别name属性，将row中对应的数据，填充到form表单对应的name属性中
                        //oldId用来修改原来id
                        $("#oldId").val(row.id);
                        //url修改
                        url = "${lesson}/admin/updateLesson" ;
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
                                $.post("${lesson}/admin/deleteLesson",
                                    {ids: ids}, function(result){
                                        if(result.exist) {
                                            $.messager.alert("系统提示", '课程下有分数不能删除');
                                        } else if(result.success) {
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
                }],
                //同列属性，但是这些列将会冻结在左侧,z大小不会改变，当宽度大于250时，会显示滚动条，但是冻结的列不在滚动条内
                frozenColumns:[[
                    {field:'checkbox',checkbox:true},    //复选框
                    {field:'id',title:'编号',width:200}    //id字段
                ]],
                columns:[[
                    {field:'name',title:'名字',width:300},   //其他数据 字段
                    {field:'session',title:'学期',width:300},
                    {field:'teacherId',title:'老师Id',width:300}
                ]]
            });
        }



    </script>
</head>
<body>

<!-- 按键框 -->
<table  width="100%">
    <tr>
        <td>
            <input id="ss" class="easyui-searchbox" style="width:260px"
                   data-options="searcher:searchLesson,prompt:'Please Input Value',menu:'#mm'"></input>
            <div id="mm" style="width:60px;height:80px">
                <div data-options="name:'id'">Id</div>
                <div data-options="name:'name'">老师</div>
            </div>
        </td>
        <td align="right">
            按班级显示课程:
            <input id="byClass" name="byClass" class="easyui-combobox" width="50px" >
        </td>
    </tr>
</table>


<!--
            保存修改弹出框
-->

<div id="dlg" class="easyui-dialog"  style="width:500px; height:250px;top:100px; padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="8px">
            <tr>
                <td>编号</td>
                <td> <!-- 原始id -->
                    <input type="hidden" id="oldId" name="oldId" class="easyui-validatebox" >
                    <input type="text" id="id" name="id" class="easyui-validatebox" required="true">
                </td>
            </tr>
            <tr>
                <td>课程名</td>
                <td>
                    <input type="text" id="name" name="name" class="easyui-validatebox" required="true"
                           style="width:60px">
                </td>
            </tr>
            <tr>
                <td>学期(格式xxxx/x/x)</td>
                <td>
                    <input type="text" id="session" name="session" class="easyui-validatebox" required="true"
                           style="width:60px">
                </td>
            </tr>
            <tr>
                <td>老师id</td>
                <td>
                    <input type="text" id="teacherId" name="teacherId" class="easyui-validatebox" required="true"
                           style="width:60px">
                </td>
            </tr>
            <tr>
                <td>班级ids(用逗号隔开,暂时不支持修改班级ids)</td>
                <td>
                    <input type="text" id="classIds" name="classIds" class="easyui-validatebox" required="true"
                           style="width:60px">
                </td>
            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <div>
        <a href="javascript:saveLesson()" class="easyui-linkbutton" iconCls="icon-ok" plain="true">保存</a>
        <a href="javascript:closeLog()" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">关闭</a>
    </div>
</div>




<br><br><br>


<!-- 显示框 -->
<table id="dg"></table>

</body>
</html>
