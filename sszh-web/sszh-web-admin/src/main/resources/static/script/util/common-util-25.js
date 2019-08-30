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
                    parent.window.parent.window.location.href = XMLHttpRequest.getResponseHeader('content_path');
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
                        parent.window.parent.window.location.href = XMLHttpRequest.getResponseHeader('content_path');
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

// 销售属性参数校验
var check_value = function(obj, type, min, max){
	var value = obj.value;
	type = type.toLocaleLowerCase();
	if('string' == type && (""==obj.value || undefined==obj.value || obj.value.length < min || obj.value.length > max)) {
		/* window.layer.tips('该项不能为空，自动填充默认值为：' + min, obj);
		obj.value = min; */
		window.layer.tips('该项长度限制为：' + min + '-' + max + '字符', obj);
		//if(obj.value.length > max) obj.value = obj.value.substring(0, max);
		return true;
	}
	if("intnumber"==type || "int"==type || "number"==type) {
		var oldNum = 1;
		if(obj.value<0){
			oldNum = -1;
		}
		//先把非数字的都替换掉，除了数字和.
		obj.value = obj.value.replace(/[^\d.]/g,"");
		obj.value = parseInt(obj.value)*oldNum;
		if(obj.value>max || obj.value<min || "NaN"==obj.value){
			/* window.layer.tips('该项必须在[' + min +', ' + max + ']之间，已自动设定默认值', obj);
			if(obj.value>max) obj.value = max;
			if(obj.value<min) obj.value = min;
			if("NaN"==obj.value) obj.value = min; */
			obj.value = '';
			window.layer.tips('该项必须在[' + min +', ' + max + ']之间', obj);
			return true;
		}
	}
	if("price"==type || "money"==type || "floatnumber"==type || "float"==type){
		//先把非数字的都替换掉，除了数字和.
		obj.value = obj.value.replace(/[^\d.]/g,"");
		//必须保证第一个为数字而不是.
		obj.value = obj.value.replace(/^\./g,"");
		//保证只有出现一个.而没有多个.
		obj.value = obj.value.replace(/\.{2,}/g,".");
		//保证.只出现一次，而不能出现两次以上
		obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
		obj.value = parseFloat(obj.value).toFixed(2);
		if(obj.value>max || obj.value<min || "NaN"==obj.value){
			/* window.layer.tips('该项必须在[' + min +', ' + max + ']之间，已自动设定默认值', obj);
			if(obj.value>max) obj.value = max;
			if(obj.value<min) obj.value = min;
			if("NaN"==obj.value) obj.value = min; */
			obj.value = '';
			window.layer.tips('该项必须在[' + min +', ' + max + ']之间', obj);
			return true;
		}
	}
}

//获取form表单数据
var getFormData = function(formDom) {
    var formData = formDom.serializeArray();
    var formObject = {};
    for (var item in formData) {
        formObject[formData[item].name] = formData[item].value;
    }
    return formObject;
}

//判断是否为空
var isEmpty = function isEmpty(obj) {
    if (!obj || undefined === obj || obj == null || typeof obj == "undefined" || obj === "") {
        return true;
    } else {
        return false;
    }
}