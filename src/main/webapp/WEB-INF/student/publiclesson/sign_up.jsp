<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/10/22
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>公选课报名</title>
    <%@include file="../../../common/easy-ui.jspf" %>

    <script type="text/javascript">

        $( function realListPublicLesson() {
                //datagrid初始化
                $('#dg').datagrid({

                    //请求数据的url
                    url: '${lesson}/details/listPublicLessonForS',
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

                    //同列属性，但是这些列将会冻结在左侧,z大小不会改变，当宽度大于250时，会显示滚动条，但是冻结的列不在滚动条内
                    frozenColumns:[[
                        {field:'checkbox',checkbox:true},    //复选框
                        {field:'id',title:'公选课Id',width:100} //id字段
                    ]],
                    columns:[[
                        {field:'name',title:'课程名',width:200},
                        {field:'teacherName',title:'指导教师',width:200},
                        {field:'time',title:'课程时间',width:200},
                        {field:'credit',title:'学分',width:300},
                        {field:'amount',title:'人数上限',width:200},
                        {field:'teacherId',title:'已选人数',width:200}
                    ]],

                    toolbar:[{
                        iconCls: 'icon-add',    //图标
                        text: '报名',            //名称
                        handler: function () {  //回调函数
                            //获取选中要删除的行
                            var selectedRows = $("#dg").datagrid("getSelections");
                            //判断是否有选择的行
                            if(selectedRows.length == 0) {
                                $.messager.alert("系统提示", "请选择要报名的课程");
                                return;
                            }else if(selectedRows.length > 1){
                                $.messager.alert("系统提示", "只能报名的一个课程");
                                return;
                            }
                            $.post("${lesson}/publicLesson/insertChoice",
                                {lessonId: selectedRows[0].id},
                                function(result){
                                     if(result.success) {
                                        $.messager.alert("系统提示", "报名成功！");

                                         $("#dg2").datagrid("reload");
                                         $("#dg").datagrid("reload");


                                         $("#dg").datagrid("clearSelections");
                                    } else {
                                        $.messager.alert("系统提示", result.error);
                                    }
                                }, "json");
                        }
                    }]
                });
            }
        );
        $( function myPublicLesson() {
                //datagrid初始化
                $('#dg2').datagrid({

                    //请求数据的url
                    url: '${lesson}/publicLesson/getChoice',
                    //载入提示信息
                    loadMsg: 'loading...',
                    //数据多的时候不换行
                    nowrap: true,
                    //指定id为标识字段，在删除，更新的时候有用，如果配置此字段，在翻页时，换页不会影响选中的项
                    idField: 'id',

                    //同列属性，但是这些列将会冻结在左侧,z大小不会改变，当宽度大于250时，会显示滚动条，但是冻结的列不在滚动条内
                    frozenColumns:[[
                        {field:'checkbox',checkbox:true},    //复选框
                        {field:'id',title:'公选课Id',width:100} //id字段
                    ]],
                    columns:[[
                        {field:'name',title:'课程名',width:200},
                        {field:'teacherName',title:'指导教师',width:150},
                        {field:'time',title:'课程时间',width:200},
                        {field:'credit',title:'学分',width:150},
                        {field:'amount',title:'人数上限',width:150},
                        {field:'teacherId',title:'已选人数',width:150}
                    ]],

                    toolbar:[{
                        iconCls: 'icon-remove',
                        text: '取消报名',
                        handler: function () {
                            //获取选中要删除的行
                            var selectedRows = $("#dg2").datagrid("getSelections");
                            //判断是否有选择的行
                            if(selectedRows.length == 0) {
                                $.messager.alert("系统提示", "请选择要取消报名的课程");
                                return;
                            }


                            $.messager.confirm("系统提示", "<font color=red>取消报名？</font>", function(r) {
                                if(r) {
                                    $.post("${lesson}/publicLesson/deleteChoice",
                                        {lessonId: selectedRows[0].id},
                                        function(result){
                                            if(result.success) {
                                                $.messager.alert("系统提示", "取消报名成功");

                                                $("#dg").datagrid("reload");
                                                $("#dg2").datagrid("reload");

                                                var rows = $('#dg2').datagrid('getRows');
                                                for(var i=rows.length-1;i>=0;i--){
                                                    $('#dg2').datagrid('deleteRow',i);
                                                }


                                            } else {
                                                $.messager.alert("系统提示", "取消报名失败！");
                                            }
                                        }, "json");
                                }
                            });

                        }
                    }]
                });
            }

        );
    </script>
</head>

<body class="easyui-layout">

<!-- 显示框 -->
<table id="dg"></table>

<div data-options="region:'south'" style="height: 150px;padding: 5px" align="center">
    <div data-options="region:'south'" style="height: 25px;padding: 5px" >
       你报名的公选课
    </div>
<table id="dg2"></table>
</div>
</body>
</html>
