<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="/">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>菜单管理</title>
    <link rel="stylesheet" href="js/layui-v2.5.4/css/layui.css" media="all">
    <style type="text/css">
        body {
            margin: 0px;
            padding: 10px;
        }
        /*滚动条样式*/
        body::-webkit-scrollbar {
            width: 4px;    
        }
        body::-webkit-scrollbar-thumb {
            border-radius: 10px;
            -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
            background: rgba(0,0,0,0.2);
        }
        body::-webkit-scrollbar-track {
            -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
            border-radius: 0;
            background: rgba(0,0,0,0.1);

        }
    </style>
</head>
<body>

    <form class= "layui-form serch_form">
        <p style="padding: 0px 0px 10px 0px;"><span>系统管理</span> > <span>菜单管理</span></p>
        <div>
            ID：
            <div class="layui-inline">
                <input type= "text" id= "id" name= "id" placeholder= "请输入ID" class= "layui-input" autocomplete="off">
            </div>
            &nbsp;&nbsp;&nbsp;&nbsp;父级ID：
            <div class="layui-inline">
                <select class="parentId" name="parentId" lay-filter="parentId" lay-search="">
                    <option value=""></option>
                </select>
            </div>
            &nbsp;&nbsp;&nbsp;&nbsp;菜单名称：
            <div class="layui-inline">
                <input type= "text" id= "name" name= "name" placeholder= "请输入菜单名称" class= "layui-input" autocomplete="off">
            </div>
            &nbsp;&nbsp;&nbsp;&nbsp;状态：
            <div class= "layui-input-inline">
                <select id= "status" name= "status">
                    <option value="">请选择</option>
                    <option value="1">开启</option>
                    <option value="-1">隐藏</option>
                </select>
            </div>
            <div class= "layui-inline">
                <a class= "layui-btn search_btn" style= " margin-left: 18%;" lay-submit= "" lay-filter="search_btn">查询</a>
            </div>
            <div class= "layui-inline">
                <a class= "layui-btn" style= " margin-left: 18%;" lay-submit= "" lay-filter="add_this_data_btn">新增菜单</a>
            </div>
        </div>
    </form>
    <table id="lay_data_table"></table>
    
    
    
    <div class="add_form" hidden>
        <form class= "layui-form" style="padding: 10px 30px 150px 0px;">
            <input type="hidden" name="id" value="">
            <div class="layui-form-item">
                <label class="layui-form-label">菜单名称：</label>
                <div class="layui-input-block">
                    <input type="text" name="name" lay-verify="name" autocomplete="off" placeholder="请输入菜单名称" class="layui-input" onblur="util.check.check_value(this, 'string', 1, 10)">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">父级菜单：</label>
                <div class="layui-input-block">
					<select class="parentId" name="parentId" lay-filter="parentId" lay-search="">
						<option value=""></option>
					</select>
				</div>
            </div>
        </form>
    </div>

    
    <script src="js/layui-v2.5.4/layui.js" charset="utf-8"></script>
    <script src="script/util/common-util-25.js" charset="utf-8"></script>
    <script type="text/javascript">
        layui.use(['layer', 'table', 'form', 'jquery'], function(){
            var table = layui.table,
                layer = layui.layer,
                form = layui.form,
                $ = layui.jquery;
            
            //方法级渲染
            table.render({
                id : 'lay_data_table',
                elem: '#lay_data_table'
                ,url:'sysMenu/selectPage'
                ,contentType:'application/x-www-form-urlencoded'
                //,height: 'full-80'                          //高度最大化减去差值
                ,page: true
                ,request: {
                    pageName: 'pageNum',                    //页码的参数名称，默认：page
                    limitName: 'pageSize'                   //每页数据量的参数名，默认：limit
                }
                ,parseData: function(res){                  //res 即为原始返回的数据
                    return {
                        "code": 0,                          //解析接口状态
                        "msg": '',                          //解析提示文本
                        "count": res.data.total,            //解析数据长度
                        "data": res.data.list               //解析数据列表
                    };
                }
                ,cols: [[
                    {field:'id', title: 'ID', width:90, sort: true}
                    ,{field:'parentId', title: '父菜单ID', width:90}
                    ,{field:'name', title: '菜单名称', sort: true}
                    ,{field:'url', title: 'URL'}
                    ,{field:'perms', title: '授权'}
                    ,{field:'icon', title: '菜单图标'}
                    ,{field:'type', title: '类型', width:90, sort: true, templet:function(e) {
                        if (e.type == 1) return '菜单'; 
                        if (e.type == 2) return '按钮'; 
                    }}
                    ,{field:'orderNum', title: '排序值', width:90, sort: true, templet : function(e) {
                        if (!e.orderNum) return ''; 
                        if (e.orderNum == 0) return ''; 
                        return e.orderNum
                    }}
                    ,{field:'createTime', title: '创建时间', width:160, sort: true, templet : function(e) {
                        return new Date(e.createTime).format('yyyy-MM-dd HH:mm:ss');
                    }}
                    ,{field:'status', title: '菜单状态', width:95, templet:function(e) {
                        return '<input type="checkbox" name="status" value="'+e.id+'" lay-skin="switch" lay-text="开启|隐藏" lay-filter="menu_status_btn" '+(e.status == 1 ? 'checked' : '')+'>';
                    }}
                    ,{fixed: 'right', title:'操作', width:160, templet: function(e) {
                        return '<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a><a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a><a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>';
                    }}
                ]]
                ,done: function(res, curr, count){
                    //成功之后
                }

            });
            
            //获取菜单数据，拼接菜单
            $.ajax({
                url: "sysMenu/getReloMenuList",
                type: "GET",
                async: true,
                dataType: "json",
                success: function (data) {
                    var str = '';
                    if (data.data) {
                        data.data.forEach(function(self, opt){
                            str += "<option value=\"" + self.id + "\" >" + (self.name + '/' + self.parentId) + "</option>";
                        });
                    }
                    $('.parentId').each(function(e, v){
                        $(v).html('<option value="">请输入搜索关键词</option>' + str);
                    });
                    form.render();
                }
            });
            
            //查询
            form.on('submit(search_btn)', function(e){
                table.reload('lay_data_table', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: e.field
                }, 'data');
            });
            
            //新增菜单
            form.on('submit(add_this_data_btn)', function(obj){
                layer.open({
					title : "新增菜单",
					type : 1,						//弹出窗类型（参考layui）
					shade: 0.5, 					//显示遮罩
					maxmin: true, 					//允许全屏最小化
					shadeClose: true, 				//开启自适应
					anim: 0, 						//0-6的动画形式，-1不开启
					moveOut : true,					//是否允许拖拽到窗口外
					offset: ['15%', '35%'],			//距离[上边距：高，左边距：宽]
					area: ['30%', '70%'],			//高宽[宽，高]
					content: $('.add_form')
				});
            });
            
            //开启、隐藏
            form.on('switch(menu_status_btn)', function(obj){
                $.ajax({
                    url: "sysMenu/update",
                    type: "POST",
                    async: true,
                    data: {
                        id : obj.elem.value,
                        status : obj.elem.checked ? 1 : -1
                    },
                    dataType: "json",
                    success: function (data) {
                        layer.msg(obj.elem.checked ? '开启成功' : '隐藏成功');
                        var serchData = util.form.getFormData($('.serch_form'));
                        table.reload('lay_data_table', {
                            where: serchData
                        }, 'data');
                    }
                });
            });
            
            
            
        });
    </script>

</body>
</html>