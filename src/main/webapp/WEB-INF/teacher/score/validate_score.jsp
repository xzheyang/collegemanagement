<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/10/16
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>验证分数</title>

    <%@include file="../../../common/easy-ui.jspf" %>

    <script type="text/javascript">

        //把easy ui屏蔽的onchange重启
        $(document).ready(function(){
            //相当于select的onchange事件
            $('#byLesson').combobox({
                onChange:function(newValue,oldValue){
                    //newValue,新值，用户选择后的值
                    //oldValue,旧值，用户选择前的值
                    if(newValue!=''){
                        realListScore(newValue)
                    }

                }
            });
        });

        //初始化班级下拉框
        $(function(){
                $.ajax({
                    url:"${lesson}/details/listLessonByTeacher",
                    dataType:"json",
                    type:"GET",

                    success:function(data){



                        var dataObj = jQuery.extend(true, [], data);  //深层复制对象是{}array是[]


                        //去重(极复杂版)
                        var text=[];
                        $.each(dataObj, function(index, item){
                            text.push(item.session);
                        });
                        function findReRank(arr){
                            var res = [];
                            var rank = [];
                            var json = {};
                            for(var i = 0; i < arr.length; i++){
                                if(!json[arr[i]]){
                                    res.push(arr[i]);
                                    json[arr[i]] = 1;
                                }else {  rank.push(i)  }
                            }
                            return rank;
                        }

                        var reRank = findReRank(text);
                        for(var i = 0; i < reRank.length; i++) {
                            dataObj.splice(reRank[i]-i, 1);
                        }


                        //学期下拉框
                        $('#bySession').combobox({

                                data: dataObj,
                                valueField:  'session',
                                textField:  'session',
                                editable:false, //不可编辑状态

                            onHidePanel: function(){
                                var dataObj2 = jQuery.extend(true, [], data);
                                $("#byLesson").combobox("setValue",'');//清空课程
                                var session = $('#bySession').combobox('getValue');
                                var idsStr = [];
                                $.each(dataObj2, function(index, item){
                                  if(item.session!=session){
                                      idsStr.push(index);

                                  }
                                });

                                for(var i = 0; i < idsStr.length; i++) {
                                    dataObj2.splice(idsStr[i]-i, 1);

                                }

                                $("#byLesson").combobox("loadData",dataObj2);
                                }

                            }
                        );

                        //课程下拉框
                        $('#byLesson').combobox({
                            data: data,
                            valueField: 'id',
                            textField: 'name',
                            editable:false
                        });
                    },
                    error:function(){
                        alert("初始化下拉控件失败");
                    }
                })
            }
        );


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
                idField: 'id',

                //同列属性，但是这些列将会冻结在左侧,z大小不会改变，当宽度大于250时，会显示滚动条，但是冻结的列不在滚动条内
                frozenColumns:[[

                    {field:'studentId',title:'学生Id',width:300} //id字段
                ]],
                columns:[[
                    {field:'lessonId',title:'课程编号',width:200},
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

    </script>
</head>
<body>


<!-- 按键框 -->
<table  width="100%">
    <tr>
        <td>
            学期:
            <input id="bySession" name="bySession" class="easyui-combobox" width="50px" >
        </td>
        <td align="right">
            课程名称:
            <input id="byLesson" name="byLesson" class="easyui-combobox" width="50px" align="right">
        </td>
    </tr>
</table>








<!-- 显示框 -->
<table id="dg"></table>

</body>
</html>
