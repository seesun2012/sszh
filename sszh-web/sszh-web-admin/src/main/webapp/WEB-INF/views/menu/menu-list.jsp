<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="/">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>菜单管理</title>
    <link rel="stylesheet" href="js/layui-v2.5.4/css/layui.css" media="all">
</head>
<body class="layui-layout-body">

    <%--<p><span>系统管理</span> > <span>菜单管理</span></p>--%>
    <form class= "layui-form" style="padding: 10px">
        <div class= "layui-form-item" style="background-color: #ffffff;">
            <label class= "layui-form-label">门票名称：</label>
            <div class= "layui-input-inline">
                <input type= "text" id= "title" name= "ticketName" placeholder= "请输入门票名称" class= "layui-input" autocomplete="off">
            </div>
            <label class= "layui-form-label">场馆：</label>
            <div class= "layui-input-inline">
                <select name="venueId" class = "VENUE_ID_SELECT" lay-filter="VENUE_ID_SELECT_1"></select>
            </div>
            <label class= "layui-form-label">运动项目：</label>
            <div class= "layui-input-inline">
                <select id= "sportId" name= "sportId" class = "SPORT_ID_SELECT_1"></select>
            </div>
            <label class= "layui-form-label">状态：</label>
            <div class= "layui-input-inline">
                <select id= "status" name= "status">
                    <option value="">请选择</option>
                    <option value="2">正常</option>
                    <option value="1">停用</option>
                    <option value="3">已过期</option>
                </select>
            </div>
            <div class= "layui-inline">
                <a class= "layui-btn layui-btn-normal search_btn" style= " margin-left: 18%;" lay-submit= "" lay-filter="search_btn">查询</a>
            </div>
            <div class= "layui-inline">
                <a class= "layui-btn layui-btn-normal" style= " margin-left: 18%;" lay-submit= "" lay-filter="add_door_ticket_btn">新增门票</a>
            </div>
        </div>
        <table class="layui-table" id="lay_data_table"></table>
    </form>

    
    <script src="js/layui-v2.5.4/layui.js" charset="utf-8"></script>
    <script src="script/util/common-util-25.js" charset="utf-8"></script>
    <script type="text/javascript">
        layui.use(['layer', 'form', 'jquery', 'table'], function () {
            var form = layui.form,
                layer = layui.layer,
                table = layui.table,
                $ = layui.jquery;
    
            table.render({
                elem: '#lay_data_table',
                url:'sysMenu/list',
                cols: [[{
                    field:'id',
                    width:80, 
                    title: 'ID', 
                    sort: true
                }, {
                    title: '用户名',
                    field:'username',
                    width:80,
                    sort: true,
                    templet : function(row, value) {
                        debugger;
                    }}
                ]],
                jump: function(obj, first){
                    $("[name='pageNum']").val(obj.curr);//向隐藏域设置当前页的值
                    $("[name='pageSize']").val(obj.limit);//向隐藏域设置当前页的大小
                    if(!first){//首次不执行(点击的时候才执行)
                        queryTrainSchemeFY();//执行查询分页函数(这个函数必须写在这里)
                    }
                }
            });
            
        });
    </script>

</body>
</html>