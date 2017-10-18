<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/10/16
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>成绩查询</title>

    <%@include file="../../../common/easy-ui.jspf" %>

    <script type="text/javascript">


        //显示班级函数
        $(function realListClasses () {
            //datagrid初始化
            $('#dg').datagrid({
                //发送的额外参数

                //请求数据的url
                url: '${lesson}/details/listScoreByStudentId',
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

                    {field:'lessonId',title:'编号',width:200}    //id字段
                ]],
                columns:[[
                    {field:'lesson.name',title:'课程名',width:300,
                        formatter: function (value, rec) {
                            return rec.lesson.name;
                        }
                    },   //其他数据 字段
                    {field:'lesson.session',title:'学期',width:300,
                        formatter: function (value, rec) {
                            return rec.lesson['session'];
                        }
                    },
                    {field:'score',title:'分数',width:300}
                ]]
            });
        }
        )





    </script>
</head>
<body>

<!-- 显示框 -->
<table id="dg"></table>

</body>
</html>
