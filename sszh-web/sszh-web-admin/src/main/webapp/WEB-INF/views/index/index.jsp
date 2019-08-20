<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>layout 后台大布局 - Layui</title>
	<link rel="stylesheet" href="js/layui-v2.5.4/css/layui.css" media="all">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
	<div class="layui-header">
		<div class="layui-logo">XXXX-运营管理系统</div>
		<!-- 头部区域（可配合layui已有的水平导航） -->
		<ul class="layui-nav layui-layout-left">
			<li class="layui-nav-item"><a href="">控制台</a></li>
			<li class="layui-nav-item"><a href="">商品管理</a></li>
			<li class="layui-nav-item"><a href="">用户</a></li>
			<li class="layui-nav-item">
				<a href="javascript:;">其它系统</a>
				<dl class="layui-nav-child">
					<dd><a href="">邮件管理</a></dd>
					<dd><a href="">消息管理</a></dd>
					<dd><a href="">授权管理</a></dd>
				</dl>
			</li>
		</ul>
		<ul class="layui-nav layui-layout-right">
			<li class="layui-nav-item">
				<a href="javascript:;">
					<img src="http://t.cn/RCzsdCq" class="layui-nav-img">
					贤心
				</a>
				<dl class="layui-nav-child">
					<dd><a href="">基本资料</a></dd>
					<dd><a href="">安全设置</a></dd>
				</dl>
			</li>
			<li class="layui-nav-item"><a href="login/doOut">退了</a></li>
		</ul>
	</div>

	<div class="layui-side layui-bg-black">
		<div class="layui-side-scroll">
			<div title="菜单缩放" class="kit-side-fold"><i class="fa fa-navicon" aria-hidden="true"></i></div>
			<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
			<ul class="layui-nav layui-nav-tree"  lay-filter="test">
				<li class="layui-nav-item">
					<a href="javascript:;"><i class="layui-icon layui-icon-set"></i> <span >系统管理</span></a>
					<dl class="layui-nav-child">
						<dd><a href="javascript:;"><i class="fa fa-window-restore fa-lg"></i> <span >系统信息</span></a></dd>
						<dd><a href="javascript:;"><i class="fa fa-database fa-lg"></i> <span >操作日志</span></a></dd>
					</dl>
				</li>
			</ul>
		</div>
	</div>

	<div class="layui-body">
		<!-- 内容主体区域 -->
		<div style="padding: 15px;">
			<div style="margin: 0px auto; width: 100%; border: 0px solid red;">
				<p style="text-align: center; font-size: 20px; padding: 10px; font-weight: 700;">欢迎使用【XXXX-运营管理系统】，当前时间：new Date()</p>
			</div>
		</div>
	</div>

	<div class="layui-footer">
		<!-- 底部固定区域 -->
		<p style="text-align: center;">Copyright ©2019 <a href="https://blog.csdn.net/seesun2012" target="_blank">seesun2012</a></p>
	</div>
</div>
<script src="js/layui-v2.5.4/layui.js"></script>
<script>
	//JavaScript代码区域
	//JavaScript代码区域
	layui.use('element', function(){
		var element = layui.element;

	});
	var isShow = true;  //定义一个标志位
	$('.kit-side-fold').click(function(){
		//选择出所有的span，并判断是不是hidden
		$('.layui-nav-item span').each(function(){
			if($(this).is(':hidden')){
				$(this).show();
			}else{
				$(this).hide();
			}
		});
		//判断isshow的状态
		if(isShow){
			$('.layui-side.layui-bg-black').width(60); //设置宽度
			$('.kit-side-fold i').css('margin-right', '70%');  //修改图标的位置
			//将footer和body的宽度修改
			$('.layui-body').css('left', 60+'px');
			$('.layui-footer').css('left', 60+'px');
			//将二级导航栏隐藏
			$('dd span').each(function(){
				$(this).hide();
			});
			//修改标志位
			isShow =false;
		}else{
			$('.layui-side.layui-bg-black').width(200);
			$('.kit-side-fold i').css('margin-right', '10%');
			$('.layui-body').css('left', 200+'px');
			$('.layui-footer').css('left', 200+'px');
			$('dd span').each(function(){
				$(this).show();
			});
			isShow =true;
		}
	});
</script>
</body>
</html>