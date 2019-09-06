/**
 * 公共工具包
 */
var util = {
    /*--------------------------------------------------------------------校验属性--------------------------------------------------------------------*/
    check: {
        /**
         * 判断是否为空
         * @param obj
         * @returns {boolean}
         */
        isEmpty: function isEmpty(obj) {
            if (!obj || undefined === obj || obj == null || typeof obj == "undefined" || obj === "") return true;
            return false;
        },
        /**
         * 检测密码强度
         * @param str
         * @returns {number}
         */
        check_password : function (str) {
            var Lv = 0;
            if (str.length < 6) return Lv;
            if (/[0-9]/.test(str)) Lv++;
            if (/[a-z]/.test(str)) Lv++;
            if (/[A-Z]/.test(str)) Lv++;
            if (/[\.|-|_]/.test(str)) Lv++;
            return Lv;
        },
        /**
         * 销售属性参数校验（一般用在input的onblur失去焦点事件，需要引入layui.js）
         * @param obj
         * @param type
         * @param min
         * @param max
         * @returns {boolean}
         */
        check_value: function (obj, type, min, max) {
            type = type.toLocaleLowerCase();
            if ('string' == type && ("" == obj.value || undefined == obj.value || obj.value.length < min || obj.value.length > max)) {
                /* window.layer.tips('该项不能为空，自动填充默认值为：' + min, obj);
                obj.value = min; */
                window.layer.tips('该项长度限制为：' + min + '-' + max + '字符', obj);
                //if(obj.value.length > max) obj.value = obj.value.substring(0, max);
                return true;
            }
            if ("intnumber" == type || "int" == type || "number" == type) {
                var oldNum = 1;
                if (obj.value < 0) {
                    oldNum = -1;
                }
                //先把非数字的都替换掉，除了数字和.
                obj.value = obj.value.replace(/[^\d.]/g, "");
                obj.value = parseInt(obj.value) * oldNum;
                if (obj.value > max || obj.value < min || "NaN" == obj.value) {
                    /* window.layer.tips('该项必须在[' + min +', ' + max + ']之间，已自动设定默认值', obj);
                    if(obj.value>max) obj.value = max;
                    if(obj.value<min) obj.value = min;
                    if("NaN"==obj.value) obj.value = min; */
                    obj.value = '';
                    window.layer.tips('该项必须在[' + min + ', ' + max + ']之间', obj);
                    return true;
                }
            }
            if ("price" == type || "money" == type || "floatnumber" == type || "float" == type) {
                //先把非数字的都替换掉，除了数字和.
                obj.value = obj.value.replace(/[^\d.]/g, "");
                //必须保证第一个为数字而不是.
                obj.value = obj.value.replace(/^\./g, "");
                //保证只有出现一个.而没有多个.
                obj.value = obj.value.replace(/\.{2,}/g, ".");
                //保证.只出现一次，而不能出现两次以上
                obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
                obj.value = parseFloat(obj.value).toFixed(2);
                if (obj.value > max || obj.value < min || "NaN" == obj.value) {
                    /* window.layer.tips('该项必须在[' + min +', ' + max + ']之间，已自动设定默认值', obj);
                    if(obj.value>max) obj.value = max;
                    if(obj.value<min) obj.value = min;
                    if("NaN"==obj.value) obj.value = min; */
                    obj.value = '';
                    window.layer.tips('该项必须在[' + min + ', ' + max + ']之间', obj);
                    return true;
                }
            }
        }
    },
    /*--------------------------------------------------------------------form表单操作--------------------------------------------------------------------*/
    form: {
        /**
         * 获取表单数据
         * @param formDom
         */
        getFormData: function (formDom) {
            var formData = formDom.serializeArray();
            var formObject = {};
            for (var item in formData) {
                formObject[formData[item].name] = formData[item].value;
            }
            return formObject;
        }
    },
    /*--------------------------------------------------------------------table表格操作--------------------------------------------------------------------*/
    table: {
        /**
         * 获取表单数据
         * @param formDom
         */
        getFormData: function (formDom) {
            var formData = formDom.serializeArray();
            var formObject = {};
            for (var item in formData) {
                formObject[formData[item].name] = formData[item].value;
            }
            return formObject;
        }
    },
    /*--------------------------------------------------------------------字符串操作--------------------------------------------------------------------*/
    string : {
        /**
         * 去除空格
         * @param  {str}
         * @param  {type} 
         *       type:  1-所有空格  2-前后空格  3-前空格 4-后空格
         * @return {String}
         */
        trim : function (str, type) {
            type = type || 1;
            switch (type) {
                case 1:
                    return str.replace(/\s+/g, "");
                case 2:
                    return str.replace(/(^\s*)|(\s*$)/g, "");
                case 3:
                    return str.replace(/(^\s*)/g, "");
                case 4:
                    return str.replace(/(\s*$)/g, "");
                default:
                    return str;
            }
        },
        /*过滤html代码(把<>转换)*/
        filterTag : function (str) {
            str = str.replace(/&/ig, "&amp;");
            str = str.replace(/</ig, "&lt;");
            str = str.replace(/>/ig, "&gt;");
            str = str.replace(" ", "&nbsp;");
            return str;
        }
    },
    /*--------------------------------------------------------------------数字操作--------------------------------------------------------------------*/
    number : {
        /*随机数范围*/
        random : function (min, max) {
            if (arguments.length === 2) {
                return Math.floor(min + Math.random() * ( (max+1) - min ));
            }else{
                return null;
            }
        }
    },
    /*--------------------------------------------------------------------日期操作--------------------------------------------------------------------*/
    date: {
        format : function (date, fmt) {
            var o = {
                "M+": this.getMonth() + 1,                      //月份
                "d+": this.getDate(),                           //日
                "H+": this.getHours(),                          //小时
                "m+": this.getMinutes(),                        //分
                "s+": this.getSeconds(),                        //秒
                "q+": Math.floor((this.getMonth() + 3) / 3),    //季度
                "S": this.getMilliseconds()                     //毫秒
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
    },
    /*--------------------------------------------------------------------集合操作--------------------------------------------------------------------*/
    array: {
        /**
         * 判断一个元素是否在数组中
         * @param arr
         * @param val
         * @returns {boolean}
         */
        contains: function (arr, val) {
            return arr.indexOf(val) != -1 ? true : false;
        },
        /**
         * @param  {arr} 数组
         * @param  {type} 1：从小到大   2：从大到小   3：随机
         * @return {Array}
         */
        sort: function (arr, type) {
            type = type ? type : 1;
            return arr.sort(function (a, b) {
                switch (type) {
                    case 1:
                        return a - b;
                    case 2:
                        return b - a;
                    case 3:
                        return Math.random() - 0.5;
                    default:
                        return arr;
                }
            })
        },
        /*求两个集合的并集*/
        union: function (a, b) {
            var newArr = a.concat(b);
            return this.unique(newArr);
        },
        /*求两个集合的交集*/
        intersect : function (a, b) {
            var _this = this;
            a = this.unique(a);
            return this.map(a, function(o) {
                return _this.contains(b, o) ? o : null;
            });
        },
        /*删除其中一个元素*/
        remove : function (arr, ele) {
            var index = arr.indexOf(ele);
            if(index > -1) {
                arr.splice(index, 1);
            }
            return arr;
        },
        /*将类数组转换为数组的方法*/
        formArray : function (ary) {
            var arr = [];
            if(Array.isArray(ary)) {
                arr = ary;
            } else {
                arr = Array.prototype.slice.call(ary);
            };
            return arr;
        },
        /*最大值*/
        max : function (arr) {
            return Math.max.apply(null, arr);
        },
        /*最小值*/
        min : function (arr) {
            return Math.min.apply(null, arr);
        },
        /*求和*/
        sum : function (arr) {
            return arr.reduce( function (pre, cur) {
                return pre + cur;
            });
        },
        /*平均值*/
        average : function (arr) {
            return this.sum(arr)/arr.length;
        }
    },
    /*--------------------------------------------------------------------http操作--------------------------------------------------------------------*/
    http : {
        /*-----------------Get---------------------*/
        //Get默认请求
        //Get请求，Form表单提交
        //Get请求，JSON数据提交
        /*-----------------POST---------------------*/
        //Post默认请求
        //Get请求，Form表单提交
        //Get请求，JSON数据提交
    },
    /*--------------------------------------------------------------------储存操作--------------------------------------------------------------------*/
    storage : {
        constructor : function() {
            this.ls = window.localStorage;
            this.ss = window.sessionStorage;
        },
        /*-----------------cookie---------------------*/
        /*设置cookie*/
        setCookie : function (name, value, day) {
            var setting = arguments[0];
            if (Object.prototype.toString.call(setting).slice(8, -1) === 'Object'){
                for (var i in setting) {
                    var oDate = new Date();
                    oDate.setDate(oDate.getDate() + day);
                    document.cookie = i + '=' + setting[i] + ';expires=' + oDate;
                }
            }else{
                var oDate = new Date();
                oDate.setDate(oDate.getDate() + day);
                document.cookie = name + '=' + value + ';expires=' + oDate;
            }
        },
        /*获取cookie*/
        getCookie : function (name) {
            var arr = document.cookie.split('; ');
            for (var i = 0; i < arr.length; i++) {
                var arr2 = arr[i].split('=');
                if (arr2[0] == name) {
                    return arr2[1];
                }
            }
            return '';
        },
        /*删除cookie*/
        removeCookie : function (name) {
            this.setCookie(name, 1, -1);
        },
        /*-----------------localStorage---------------------*/
        /*设置localStorage*/
        setLocal : function (key, val) {
            var setting = arguments[0];
            if (Object.prototype.toString.call(setting).slice(8, -1) === 'Object'){
                for(var i in setting){
                    this.ls.setItem(i, JSON.stringify(setting[i]));
                }
            }else{
                this.ls.setItem(key, JSON.stringify(val));
            }
        },
        /*获取localStorage*/
        getLocal : function (key) {
            if (key) return JSON.parse(this.ls.getItem(key));
            return null;
            
        },
        /*移除localStorage*/
        removeLocal : function (key) {
            this.ls.removeItem(key);
        },
        /*移除所有localStorage*/
        clearLocal : function () {
            this.ls.clear();
        },
        /*-----------------sessionStorage---------------------*/
        /*设置sessionStorage*/
        setSession : function (key, val) {
            var setting = arguments[0];
            if (Object.prototype.toString.call(setting).slice(8, -1) === 'Object'){
                for(var i in setting){
                    this.ss.setItem(i, JSON.stringify(setting[i]));
                }
            }else{
                this.ss.setItem(key, JSON.stringify(val));
            }
        },
        /*获取sessionStorage*/
        getSession : function (key) {
            if (key) return JSON.parse(this.ss.getItem(key));
            return null;
        },
        /*移除sessionStorage*/
        removeSession : function (key) {
            this.ss.removeItem(key);
        },
        /*移除所有sessionStorage*/
        clearSession : function () {
            this.ss.clear();
        }
    },
    /*--------------------------------------------------------------------URL操作--------------------------------------------------------------------*/
    url : {
        /*获取网址参数*/
        getURL : function (name){
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            var r = decodeURI(window.location.search).substr(1).match(reg);
            if(r!=null) return  r[2]; return null;
        },
        /*获取全部url参数,并转换成json对象*/
        getUrlAllParams : function (url) {
            var url = url ? url : window.location.href;
            var _pa = url.substring(url.indexOf('?') + 1),
                _arrS = _pa.split('&'),
                _rs = {};
            for (var i = 0, _len = _arrS.length; i < _len; i++) {
                var pos = _arrS[i].indexOf('=');
                if (pos == -1) {
                    continue;
                }
                var name = _arrS[i].substring(0, pos),
                    value = window.decodeURIComponent(_arrS[i].substring(pos + 1));
                _rs[name] = value;
            }
            return _rs;
        },
        /*删除url指定参数，返回url*/
        delParamsUrl : function (url, name){
            var baseUrl = url.split('?')[0] + '?';
            var query = url.split('?')[1];
            if (query.indexOf(name)>-1) {
                var obj = {};
                var arr = query.split("&");
                for (var i = 0; i < arr.length; i++) {
                    arr[i] = arr[i].split("=");
                    obj[arr[i][0]] = arr[i][1];
                };
                delete obj[name];
                var url = baseUrl + JSON.stringify(obj).replace(/[\"\{\}]/g,"").replace(/\:/g,"=").replace(/\,/g,"&");
                return url
            }else{
                return url;
            }
        }
    },
    /*--------------------------------------------------------------------其它操作--------------------------------------------------------------------*/
    other : {
        /*获取十六进制随机颜色*/
        getRandomColor : function () {
            return '#' + (function(h) {
                return new Array(7 - h.length).join("0") + h;
            })((Math.random() * 0x1000000 << 0).toString(16));
        },
        /*图片加载*/
        imgLoadAll : function (arr,callback){
            var arrImg = []; 
            for (var i = 0; i < arr.length; i++) {
                var img = new Image();
                img.src = arr[i];
                img.onload = function(){
                    arrImg.push(this);
                    if (arrImg.length == arr.length) {
                        callback && callback();
                    }
                }
            }
        },
        /*音频加载*/
        loadAudio : function (src, callback) {
            var audio = new Audio(src);
            audio.onloadedmetadata = callback;
            audio.src = src;
        },
        /*DOM转字符串*/
        domToStirng : function (htmlDOM){
            var div= document.createElement("div");
            div.appendChild(htmlDOM);
            return div.innerHTML
        },
        /*字符串转DOM*/
        stringToDom : function (htmlString){
            var div= document.createElement("div");
            div.innerHTML=htmlString;
            return div.children[0];
        }
    }
};

//重写JQuery的Ajax
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
};

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
};

