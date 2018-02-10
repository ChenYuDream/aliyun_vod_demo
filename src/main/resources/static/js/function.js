"use strict"
var pageUtil = {
    renderPage: function (vmPage, jump, domId) {
        layui.laypage({
            cont: domId,    //div的ID
            pages: vmPage.totalPage,//总页数
            curr: vmPage.currentPage || 1, //当前页
            skip: true,
            first: "首页",
            last: "尾页",
            skin: '#009688',
            jump: function (obj, first) {//跳转页面时的回调,obj:配置项参数,first:是否为首次加载
                if (!first) {
                    vmPage.currentPage = obj.curr;
                    jump();
                }
            }
        });
    }
};

var avalonFn = {
    define: function ($id, data) {
        data.$id = $id;
        return avalon.define(data);
    },
    model: function (vm) {
        return vm.$model;
    },
    watch: function (vm, watch, fn) {
        return vm.$watch(watch, function (newValue, oldValue) {
            newValue != oldValue && fn(newValue, oldValue);
        });
    }
};

/**
 * 为数组定义一个根据值移除元素的函数
 * @param val
 */
Array.prototype.removeByValue = function (val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == val) {
            this.splice(i, 1);
            break;
        }
    }
}

var jsonUtil = {
    /**
     * 合并json
     * @param jsonObj1
     * @param jsonObj2
     * @returns {{}}
     */
    mergeJsonObj: function (jsonObj1, jsonObj2) {
        var jsonResult = {};
        for (var item in jsonObj1) {
            jsonResult[item] = jsonObj1[item];
        }
        for (var item in jsonObj2) {
            jsonResult[item] = jsonObj2[item];
        }
        return jsonResult;
    },
    parseStr: function (el) {
        return JSON.stringify(el);
    },
    parseJson: function (str) {
        return JSON.parse(str);
    }
};
/**
 * 弹窗工具类
 */
var layerUtil = {
    iFrame: function (url, title, area) {
        layer.open({
            title: title,
            type: 2,
            area: area,
            shadeClose: true,
            content: url
        });
    },
    preview: function (url, dataStr, title, area) {
        $.ajax({
            type: "post",
            url: "" + url,
            data: dataStr,
            success: function (str) {
                parent.layer.open({
                    title: title,
                    type: 1,
                    area: area,
                    shadeClose: true,
                    content: str
                });
            }
        })
    },
    popup: function (url, title, btn, area, fn) {
        var currentIndex;
        $.ajax({
            type: "post",
            url: "" + url,
            beforeSend: function () {
                currentIndex = layer.load();
            },
            success: function (str) {
                layer.close(currentIndex);
                layer.open({
                    title: title,
                    type: 1,
                    move: true,
                    area: area,
                    btnAlign: 'c',
                    btn: btn,
                    maxWidth: '1200px',
                    shadeClose: false,
                    content: str,
                    yes: function (index, layero) {
                        if (fn && typeof fn == "function") {
                            return fn(layero);
                        } else {
                            return;
                        }
                    }
                });
            }
        })
    },
    confirm: function (msg, fn) {
        //询问框
        layui.layer.confirm(msg, {
            btn: ['确定', '取消'] //按钮
        }, function () {
            if (fn && typeof fn == "function") {
                fn();
            }
        });
    },
    /**
     * 加载弹窗
     */
    loading: function (msg) {
        if (typeof(msg) == "undefined") {
            msg = '正在加载数据';
        }
        return layui.layer.msg(msg, {time: 0, icon: 16, shade: 0.01});
    },
    /**
     * 操作失败的弹窗
     * @param msg
     */
    fail: function (msg) {
        layui.layer.msg(msg, {icon: 5, anim: 6});
    },
    /**
     * 操作成功弹窗
     * @param msg
     */
    success: function (msg) {
        layui.layer.msg(msg, {icon: 1});
    },
    closeAll: function () {
        layui.layer.closeAll();
    },
    closeIndex: function (index) {
        layui.layer.close(index);
    }
}

var ajaxUtil = {
    /**
     * post 只用处理code==0的情况
     * @param url
     * @param dataStr
     * @param fn
     * @param isLoading
     */
    post: function (url, dataStr, isLoading, fn) {
        var currentIndex;
        $.ajax({
            type: 'post',
            url: url,
            data: dataStr,
            dataType: "json",
            timeout: 10000,
            beforeSend: function () {
                if (isLoading) {
                    currentIndex = layerUtil.loading();
                }
            },
            success: function (data) {
                if (isLoading) {
                    layerUtil.closeIndex(currentIndex);
                }
                if (data.code >= 0) {
                    if (fn && typeof fn == "function") {
                        fn(data);
                    } else {
                        return;
                    }
                } else {
                    layerUtil.fail("操作失败");
                }
            }
        });
    },
    postDealCode: function (url, dataStr, isLoading, fn) {
        var currentIndex;
        $.ajax({
            type: 'post',
            url: url,
            data: dataStr,
            dataType: "json",
            timeout: 10000,
            beforeSend: function () {
                if (isLoading) {
                    currentIndex = layerUtil.loading("请稍后...");
                }
            },
            success: function (data) {
                if (isLoading) {
                    currentIndex = layerUtil.closeIndex(currentIndex);
                }
                if (fn && typeof fn == "function") {
                    fn(data);
                } else {
                    return;
                }
            }
        });
    }
}

/**
 * 字符串删除左右两端的空格
 * @returns {string}
 */
String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}