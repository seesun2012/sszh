<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登陆</title>
    <link rel="stylesheet" href="js/layui-v2.5.4/css/layui.css" media="all">

    <style type="text/css">
        #LAY_app, body, html {
            height: 100%
        }

        .layui-layout-body {
            overflow: auto
        }

        #LAY-user-login, .layadmin-user-display-show {
            display: block !important
        }

        .layadmin-user-login {
            background-color: #f2f2f2;
            position: relative;
            left: 0;
            top: 0;
            padding: 110px 0;
            min-height: 100%;
            box-sizing: border-box
        }

        .layadmin-user-login-main {
            width: 375px;
            margin: 0 auto;
            box-sizing: border-box
        }

        .layadmin-user-login-box {
            padding: 20px
        }

        .layadmin-user-login-header {
            text-align: center
        }

        .layadmin-user-login-header h2 {
            margin-bottom: 10px;
            font-weight: 300;
            font-size: 30px;
            color: #000
        }

        .layadmin-user-login-header p {
            font-weight: 300;
            color: #999
        }

        .layadmin-user-login-body .layui-form-item {
            position: relative
        }

        .layadmin-user-login-icon {
            position: absolute;
            left: 1px;
            top: 1px;
            width: 38px;
            line-height: 36px;
            text-align: center;
            color: #d2d2d2
        }

        .layadmin-user-login-body .layui-form-item .layui-input {
            padding-left: 38px
        }

        .layadmin-user-login-codeimg {
            max-height: 38px;
            width: 100%;
            cursor: pointer;
            box-sizing: border-box
        }

        .layadmin-user-login-other {
            position: relative;
            font-size: 0;
            line-height: 38px;
            padding-top: 20px
        }

        .layadmin-user-login-other > * {
            display: inline-block;
            vertical-align: middle;
            margin-right: 10px;
            font-size: 14px
        }

        .layadmin-user-login-other .layui-icon {
            position: relative;
            top: 2px;
            font-size: 26px
        }

        .layadmin-user-login-other a:hover {
            opacity: .8
        }

        .layadmin-user-jump-change {
            float: right
        }

        .layadmin-user-login-footer {
            position: absolute;
            left: 0;
            bottom: 0;
            width: 100%;
            line-height: 30px;
            padding: 20px;
            text-align: center;
            box-sizing: border-box;
            color: rgba(0, 0, 0, .5)
        }

        .layadmin-user-login-footer span {
            padding: 0 5px
        }

        .layadmin-user-login-footer a {
            padding: 0 5px;
            color: rgba(0, 0, 0, .5)
        }

        .layadmin-user-login-footer a:hover {
            color: rgba(0, 0, 0, 1)
        }

        .layadmin-user-login-main[bgimg] {
            background-color: #fff;
            box-shadow: 0 0 5px rgba(0, 0, 0, .05)
        }

        .ladmin-user-login-theme {
            position: fixed;
            bottom: 0;
            left: 0;
            width: 100%;
            text-align: center
        }

        .ladmin-user-login-theme ul {
            display: inline-block;
            padding: 5px;
            background-color: #fff
        }

        .ladmin-user-login-theme ul li {
            display: inline-block;
            vertical-align: top;
            width: 64px;
            height: 43px;
            cursor: pointer;
            transition: all .3s;
            -webkit-transition: all .3s;
            background-color: #f2f2f2
        }

        .ladmin-user-login-theme ul li:hover {
            opacity: .9
        }

        @media screen and (max-width: 768px) {
            .layadmin-user-login {
                padding-top: 60px
            }

            .layadmin-user-login-main {
                width: 300px
            }

            .layadmin-user-login-box {
                padding: 10px
            }
        }
    </style>

</head>
<body>

<div class="layadmin-user-login">
    <div class="layadmin-user-login-main">
        <div class="layadmin-user-login-box layadmin-user-login-header">
            <h2>SSZH-运营管理系统</h2>
            <p>系统当前时间：<span class="system_time"></span></p>
        </div>
        <form class="layadmin-user-login-box layadmin-user-login-body layui-form">
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-username"></label>
                <input type="text" name="account" value="" lay-verify="required" placeholder="用户名" class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-password"></label>
                <input type="password" name="passWord" value="" lay-verify="required" placeholder="密码"
                       class="layui-input">
            </div>
            <div class="layui-form-item">
                <div class="layui-row">
                    <div class="layui-col-xs7">
                        <label class="layadmin-user-login-icon layui-icon layui-icon-vercode"></label>
                        <input type="text" name="vCode" lay-verify="required" placeholder="图形验证码" class="layui-input">
                    </div>
                    <div class="layui-col-xs5">
                        <div style="margin-left: 10px;">
                            <img src="system/getCheckCode" class="layadmin-user-login-codeimg" id="ckeck_code_img"
                                 onclick="changeImg(this)">
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-form-item" style="margin-bottom: 20px;">
                <input type="checkbox" name="remember" lay-skin="primary" title="记住密码">
                <div class="layui-unselect layui-form-checkbox" lay-skin="primary"><span>记住密码</span><i
                        class="layui-icon layui-icon-ok"></i></div>
                <a href="user/forget" class="layadmin-user-jump-change layadmin-link" style="margin-top: 7px;">忘记密码？</a>
            </div>
            <div class="layui-form-item">
                <a class="layui-btn layui-btn-fluid" lay-submit="" lay-filter="login_submit_btn">登 入</a>
            </div>
            <div class="layui-trans layui-form-item layadmin-user-login-other">
                <label>社交账号登入</label>
                <a href="javascript:;"><i class="layui-icon layui-icon-login-qq"></i></a>
                <a href="javascript:;"><i class="layui-icon layui-icon-login-wechat"></i></a>
                <a href="javascript:;"><i class="layui-icon layui-icon-login-weibo"></i></a>
                <%--<a lay-href="user/reg" class="layadmin-user-jump-change layadmin-link">注册帐号</a>--%>
            </div>
        </form>
    </div>
</div>


<div class="layui-trans layadmin-user-login-footer">
    <p>Copyright ©2019 <a href="https://blog.csdn.net/seesun2012" target="_blank">seesun2012</a></p>
    <p>
        <span><a href="https://blog.csdn.net/seesun2012" target="_blank">技术博客</a></span>
        <span><a href="https://github.com/seesun2012" target="_blank">GitHub</a></span>
        <span><a href="#" target="_blank">联系管理员</a></span>
    </p>
</div>


<script src="js/layui-v2.5.4/layui.js" charset="utf-8"></script>
<script src="script/util/common-util-25.js" charset="utf-8"></script>
<script type="text/javascript">
    //刷新验证码
    function changeImg(self) {
        self.src = "system/getCheckCode?ran=" + Math.random();
    }
    layui.use(['layer', 'form', 'jquery'], function () {
        var form = layui.form,
            layer = layui.layer,
            $ = layui.jquery;
        //同步服务器时间
        var systemTiem = new Date().getTime();
        var loadSystemTime = function (time) {
            if (!util.check.isEmpty(time)) systemTiem = time;
            $('.system_time')[0].textContent = new Date(systemTiem).format('yyyy-MM-dd HH:mm:ss');
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
        //提交登陆
        form.on('submit(login_submit_btn)', function (btn) {
            var form = $(btn.elem).parent().parent();
            var formObject = util.form.getFormData(form);
            $.ajax({
                url: "login/doLogin",
                type: "POST",
                async: true,
                data: {
                    account: formObject.account,
                    passWord: formObject.passWord,
                    vCode: formObject.vCode
                },
                dataType: "json",
                success: function (data) {
                    layer.msg('验证成功，即将跳转至首页...');
                    setTimeout(function () {
                        window.location.href = data.data;
                    }, 1000);
                },
                error: function (error) {
                    layer.msg('请求异常');
                }
            });
        });
    });
</script>

</body>
</html>