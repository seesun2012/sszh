/**
 * 公共工具包
 */
//重写Ajax
if (!(typeof jQuery == 'undefined')) {
    //首先备份下jquery的ajax方法
    var _ajax = $.ajax;
    //重写jquery的ajax方法
    $.ajax = function (opt) {
        //备份opt中error和success方法
        var fn = {
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            },
            success: function (data, textStatus) {
            }
        }
        if (opt.error) {
            fn.error = opt.error;
        }
        if (opt.success) {
            fn.success = opt.success;
        }
        //扩展增强处理
        var _opt = $.extend(opt, {
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                //错误方法增强处理
                if ('TIMEOUT' == XMLHttpRequest.getResponseHeader('SESSIONS_TATUS')) {
                    window.location.href = XMLHttpRequest.getResponseHeader('content_path');
                }
                fn.error(XMLHttpRequest, textStatus, errorThrown);
            },
            success: function (data, textStatus) {
                //成功回调方法增强处理
                fn.success(data, textStatus);
            },
            beforeSend: function (XHR) {
                //提交前回调方法
            },
            complete: function (XHR, TS) {
                //请求完成后回调函数 (请求成功或失败之后均调用)。
            }
        });
        return _ajax(_opt);
    }
}

//重写layui的Ajax请求
if (!(typeof layui == "undefined")) {
    layui.use(['layer', 'jquery'], function () {
        var layer = layui.layer,
            $ = layui.jquery;
        //首先备份下jquery的ajax方法
        var _ajax = $.ajax;
        //重写jquery的ajax方法
        var flashLoad;
        $.ajax = function (opt) {
            //备份opt中error和success方法
            var fn = {
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                },
                success: function (data, textStatus) {
                }
            }
            if (opt.error) {
                fn.error = opt.error;
            }
            if (opt.success) {
                fn.success = opt.success;
            }
            //扩展增强处理
            var _opt = $.extend(opt, {
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    //错误方法增强处理
                    if ('TIMEOUT' == XMLHttpRequest.getResponseHeader('SESSIONS_TATUS')) {
                        window.location.href = XMLHttpRequest.getResponseHeader('content_path');
                    }
                    fn.error(XMLHttpRequest, textStatus, errorThrown);
                },
                success: function (data, textStatus) {
                    //成功回调方法增强处理
                    if (-1 == data.status || '-1' == data.status) {
                        return layer.msg(data.tip);
                    }
                    fn.success(data, textStatus);
                },
                beforeSend: function (XHR) {
                    //提交前回调方法
                    flashLoad = layer.load(0, {shade: [0.7, '#393D49']}, {shadeClose: true}); //0代表加载的风格，支持0-2
                },
                complete: function (XHR, TS) {
                    //请求完成后回调函数 (请求成功或失败之后均调用)。
                    layer.close(flashLoad);
                }
            });
            return _ajax(_opt);
        }
    });
}

//格式化日期，参数如如：new Date(时间戳).format('yyyy-MM-dd hh:mm:ss');
Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "H+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}

//封装带回调的ajax请求（带转圈圈的加载框）
var doProductType = function (params) {
    layui.use(['form', 'jquery'], function () {
        var $ = layui.jquery;
        var index = layer.load(0, {shade: [0.7, '#393D49']}, {shadeClose: true}); //0代表加载的风格，支持0-2    此处请求后台程序，下方是成功后的前台处理……
        $.ajax({
            url: params.url,
            type: params.type, //请求方式
            dataType: 'json',
            contentType: params.contentType,
            data: params.params,
            success: function (data) {
                layer.close(index);
                if (data.status == 1) {
                    //正常业务处理
                    if (params.callback) params.callback(data);
                    if (!params.notShowMsg) {
                        layer.msg(data.tip, {
                            time: 5000, //5s后自动关闭
                            icon: 1
                        });
                    }
                    return true;
                }
                layer.msg(data.tip, {
                    time: 5000, //5s后自动关闭
                    icon: 2
                });
                return false;
            },
            error: function (json) {
                layer.close(index);
                layer.msg("请求失败，请稍后重试！", {
                    icon: 2
                });
            }
        });
    });
}

//判断是否为空
var isEmpty = function isEmpty(obj) {
    if (!obj || undefined === obj || obj == null || typeof obj == "undefined" || obj === "") {
        return true;
    } else {
        return false;
    }
}