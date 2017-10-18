<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/10/17
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>分数管理</title>
    <%@include file="../../../common/easy-ui.jspf" %>

    <script type="text/javascript">

        var url;
        //关闭对话框
        function closeLog() {
            blankLog();
            $("#dlg").dialog("close"); //关闭对话框
        }

        //置空弹出框函数
        function  blankLog() {
            $("#lessonId").val("");
            $("#studentId").val("");
            $("#score").val("");
        }

        //保存,修改Score数据
        function saveScore() {
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

        //查询函数
        function searchScore(value,name){

            realListScore(value);
            //置空显示框
            var rows = $('#dg').datagrid('getRows');
            for(var i=rows.length-1;i>=0;i--){
                $('#dg').datagrid('deleteRow',i);
            }
        }


        function realListScore(byLesson) {
            //datagrid初始化
            $('#dg').datagrid({
                //发送的额外参数
                queryParams: {
                    byLesson: byLesson
                },

                //请求数据的url
                url: '${lesson}/details/listScoreByLessonId',
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
                idField: 'studentId',
                //上方工具条 添加 修改 删除
                toolbar:[{
                    iconCls: 'icon-add',    //图标
                    text: '添加',            //名称
                    handler: function () {  //回调函数

                        //打开对话框并且设置标题
                        $("#dlg").dialog("open").dialog("setTitle", "添加分数");
                        //添加url
                        url = "${lesson}/admin/insertScore"
                    }
                },'-',{
                    iconCls: 'icon-edit',
                    text: '修改',
                    handler: function () {
                        //获取选中要修改的行
                        var selectedRows = $("#dg").datagrid("getSelections");
                        //确保被选中行只能为一行
                        if(selectedRows.length != 1) {
                            $.messager.alert("系统提示", "请选择一个要修改的分数");
                            return;
                        }
                        //获取选中行id
                        var row = selectedRows[0];
                        //打开对话框并且设置标题
                        $("#dlg").dialog("open").dialog("setTitle", "修改分数信息");
                        //将数组回显对话框中
                        $("#fm").form("load", row);//会自动识别name属性，将row中对应的数据，填充到form表单对应的name属性中

                        //url修改
                        url = "${lesson}/admin/updateScore" ;
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
                            idsStr.push(selectedRows[i].studentId);
                        }
                        //将数组安装,连接成字符串
                        var ids = idsStr.join(","); //1,2,3,4
                        //定义选中 选中id数组
                        var idsStr2 = [];
                        //循环遍历将选中行的id push进入数组
                        for(var i = 0; i < selectedRows.length; i++) {
                            idsStr2.push(selectedRows[i].lessonId);
                        }
                        //将数组安装,连接成字符串
                        var ids2 = idsStr2.join(","); //1,2,3,4
                        //提示是否确认删除
                        $.messager.confirm("系统提示", "<font color=red>您确定要删除选中的"+selectedRows.length+"条数据么？</font>", function(r) {
                            if(r) {
                                $.post("${lesson}/admin/deleteScore",
                                    {studentIds: ids,lesssonIds: ids2},
                                    function(result){
                                        if(result.exist) {
                                            $.messager.alert("系统提示", '分数异常不能删除');
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
                    {field:'studentId',title:'学生Id',width:300} //id字段
                ]],
                columns:[[
                    {field:'lessonId',title:'课程编号',width:200},

                    {field:'lesson.name',title:'课程名',width:300,
                        formatter: function (value, rec) {
                            return rec.lesson.name;
                        }
                    },
                    {field:'lesson.teacherId',title:'教师Id',width:300,
                        formatter: function (value, rec) {
                            return rec.lesson.teacherId;
                        }
                    },
                    {field:'lesson.session',title:'学期',width:300,
                        formatter: function (value, rec) {
                            return rec.lesson['session'];
                        }
                    },
                    {field:'score',title:'分数',width:300}
                ]]
            });
        }




    </script>
</head>
<body>



<!--
            搜索框
-->
<input id="ss" class="easyui-searchbox" style="width:260px"
       data-options="searcher:searchScore,prompt:'Please Input Value',menu:'#mm'"></input>
<div id="mm" style="width:60px;height:80px">
    <div data-options="name:'id'">课程Id</div>
    <div data-options="name:'name'">课程名</div>
</div>





<!-- 显示框 -->
<table id="dg"></table>


<!--
            保存修改弹出框
-->

<div id="dlg" class="easyui-dialog"  style="width:500px; height:250px;top:100px; padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="8px">
            <tr>
                <td>学生ID</td>
                <td>
                    <input type="text" id="studentId" name="studentId" class="easyui-validatebox" required="true">
                </td>
            </tr>
            <tr>
                <td>课程ID</td>
                <td>
                    <input type="text" id="lessonId" name="lessonId" class="easyui-validatebox" required="true"
                           style="width:60px">
                </td>
            </tr>
            <tr>
                <td>分数</td>
                <td>
                    <input type="text" id="score" name="score" class="easyui-validatebox" required="true">
                </td>
            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <div>
        <a href="javascript:saveScore()" class="easyui-linkbutton" iconCls="icon-ok" plain="true">保存</a>
        <a href="javascript:closeLog()" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">关闭</a>
    </div>
</div>

</body>
</html>
