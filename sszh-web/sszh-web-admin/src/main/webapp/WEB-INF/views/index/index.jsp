<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>SSZH-运营管理系统</title>
    <link rel="stylesheet" href="js/layui-v2.5.4/css/layui.css" media="all">
</head>
<body class="layui-layout-body">

    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <a href="javascript:;" class="layui-logo" onclick="openContent(this)" data-url="home/index">
                <span>SSZH-运营管理系统</span>
            </a>
            <%--菜单栏伸缩--%>
            <ul class="layui-nav layui-layout-left" style="padding: 0px 10px 0px 10px;">
                <li class="layui-nav-item layadmin-flexible" lay-unselect="">
                    <a href="javascript:;" layadmin-event="flexible" title="侧边伸缩">
                        <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
                    </a>
                </li>
            </ul>
            <!-- 天气预报 -->
            <div class="weather layui-layout-left" style="line-height: 55px; padding: 0px 10px 0px 80px;" pc>
                <div id="tp-weather-widget"></div>
                <script>(function(T,h,i,n,k,P,a,g,e){g=function(){P=h.createElement(i);a=h.getElementsByTagName(i)[0];P.src=k;P.charset="utf-8";P.async=1;a.parentNode.insertBefore(P,a)};T["ThinkPageWeatherWidgetObject"]=n;T[n]||(T[n]=function(){(T[n].q=T[n].q||[]).push(arguments)});T[n].l=+new Date();if(T.attachEvent){T.attachEvent("onload",g)}else{T.addEventListener("load",g,false)}}(window,document,"script","tpwidget","//widget.seniverse.com/widget/chameleon.js"))</script>
                <script>
                    tpwidget("init", {
                        "flavor": "slim",
                        "location": "WX4FBXXFKE4F",
                        "geolocation": "enabled",
                        "language": "zh-chs",
                        "unit": "c",
                        "theme": "chameleon",
                        "container": "tp-weather-widget",
                        "bubble": "disabled",
                        "alarmType": "badge",
                        "color": "#FFFFFF",
                        "uid": "U9EC08A15F",
                        "hash": "039da28f5581f4bcb5c799fb4cdfb673"
                    });
                    tpwidget("show");
                </script>
            </div>
            <!-- 其他系统 -->
            <ul class="layui-nav layui-layout-left" style="padding: 0px 10px 0px 170px;">
                <li class="layui-nav-item">
                    <a href="javascript:;">其它系统</a>
                    <dl class="layui-nav-child">
                        <dd><a href="https://exmail.qq.com/login" target="_blank">邮件管理</a></dd>
                        <dd><a href="">消息管理</a></dd>
                        <dd><a href="">授权管理</a></dd>
                        <dd><a href="">商户管理系统</a></dd>
                    </dl>
                </li>
            </ul>
            <%--系统时间--%>
            <div class="layui-layout-right" style="padding: 23px 250px 0px 0px;">
                <p><span class="system_time" style="color: rgba(255,255,255,.7);"></span></p>
            </div>
            <%--退出登陆--%>
            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <img src="http://t.cn/RCzsdCq" class="layui-nav-img">admin
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="">基本资料</a></dd>
                        <dd><a href="">修改密码</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="login/doOut">退出登录</a></li>
            </ul>
        </div>
        
        <%--左侧菜单栏--%>
        <div class="layui-side layui-bg-black">
            <div class="layui-side-scroll" id="sszh-index-left-menu" style="background-color: #000000;"></div>
        </div>

        <!-- 内容主体区域 -->
        <div class="layui-body">
            <iframe src="home/index" width="100%" height="100%" style="margin: 0px; padding: 0px; border: 0px;"></iframe>
        </div>
    
        <!-- 底部区域 -->
        <div class="layui-footer layui-larry-foot">
            <p align="center"><span>2019 &copy;</span><a href="https://blog.csdn.net/seesun2012" target="_blank">深圳市SSZH科技股份有限公司</a>&nbsp;&nbsp;版权所有</p>
        </div>
        
    </div>
        
    
    <script src="js/layui-v2.5.4/layui.js"></script>
    <script src="script/util/common-util-25.js" charset="utf-8"></script>
    <script>
        //JavaScript代码区域
        function openContent(self){
            var iframe = document.getElementsByTagName('iframe');
            iframe[0].src = self.dataset.url;
        }
        layui.use(['element', 'layer', 'form', 'jquery'], function () {
            var element = layui.element,
                $ = layui.jquery;
            
            //获取菜单数据，拼接菜单
            $.ajax({
                url: "sysMenu/getReloMenuList",
                type: "GET",
                async: true,
                dataType: "json",
                success: function (data) {
                    if (data) data = data.data; 
                    //一级菜单
                    var first_parent = [];
                    //二级菜单
                    var second_parent = [];
                    if (data && data.length > 0) {
                        for (var i = 0; i < data.length; i++) {
                            if(data[i].parentId == 0) {
                                first_parent.push(data[i]);
                            } else {
                                second_parent.push(data[i]);
                            }
                        }
                    }
                    var str = '';
                    for (var i = 0; i < first_parent.length; i++) {
                        str += '<ul class="layui-nav layui-nav-tree" lay-filter="test">\n' +
                            '       <li class="layui-nav-item">\n' +
                            '           <a href="javascript:;"><i class="layui-icon layui-icon-set"></i>  <span>' +first_parent[i].name+ '</span></a>\n' +
                            '           <dl class="layui-nav-child">\n';
                        for (var j = 0; j < second_parent.length; j++) {
                            if (first_parent[i].id == second_parent[j].parentId) {
                                str += '               <dd><a href="javascript:;" onclick="openContent(this)" data-url="'+second_parent[j].url+'"><i class="layui-icon layui-icon-menu-fill"></i> <span>' +second_parent[i].name+ '</span></a></dd>\n';
                            }
                        }
                        str += '           </dl>\n' +
                            '       </li>\n' +
                            '   </ul>';
                    }
                    $('#sszh-index-left-menu').append(str);
                    element.init();
                },
                error: function (error) {
                    layer.msg('菜单栏请求异常...');
                }
            });
            element.init();

            //同步服务器时间
            var systemTiem = new Date().getTime();
            var loadSystemTime = function (time) {
                if (!isEmpty(time)) systemTiem = time;
                $('.system_time')[0].textContent = new Date(systemTiem).format('yyyy年MM月dd日 HH:mm:ss');
                setTimeout(function () {
                    loadSystemTime(systemTiem + 500);
                }, 1000);
            }
            $.ajax({
                url: "system/syncSystemTime",
                type: "GET",
                async: true,
                dataType: "json",
                success: function (data) {
                    loadSystemTime(data.data);
                }
            });
            loadSystemTime(systemTiem);
            
        });
    </script>
</body>
</html>