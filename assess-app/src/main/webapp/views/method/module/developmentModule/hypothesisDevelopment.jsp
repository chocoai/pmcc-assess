<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/8/17
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="frmDevelopment form-horizontal" id="frmDevelopment">
    <div class="form-group">
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">建筑选择</label>
            <div class="col-md-3 col-sm-3">
                <select name="hypothesisDevelopmentSelect2"
                        class="form-control search-select select2 hypothesisDevelopmentSelect2">
                </select>
            </div>
        </div>
    </div>

    <div class="form-group build" style="display: none">
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">建设周期</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="建设周期" class="form-control" name="constructionCycle">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">开发周期</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="开发周期" class="form-control" name="developedTime">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">调用市场比较法</label>
            <div class="col-md-2 col-sm-2">
                <label class="control-label btn">调用市场比较法<i class="fa fa-plus-circle"></i></label>
            </div>
        </div>
    </div>

    <div class="form-group residence" style="display: none">
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">住宅建面积</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="住宅建筑面积" class="form-control" name="residenceCommerceBuildArea">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">可售面积</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="可售面积" class="form-control" name="residenceCommerceMaySaleArea">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">单位售价</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="单位售价" class="form-control" name="residenceCommerceUnitPrice">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">合价</label>
            <div class="col-md-2 col-sm-2">
                <input type="text" readonly="readonly"
                       placeholder="合价" class="form-control" name="residenceCommerceTotalPrice">
            </div>
        </div>
    </div>

    <div class="form-group work" style="display: none">
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">办公建筑面积</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="办公建筑面积" class="form-control" name="workBuildArea">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">可售面积</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="可售面积" class="form-control" name="workMaySaleArea">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">单位售价</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="单位售价" class="form-control" name="workUnitPrice">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">合价</label>
            <div class="col-md-2 col-sm-2">
                <input type="text" readonly="readonly"
                       placeholder="合价" class="form-control" name="workTotalPrice">
            </div>
        </div>
    </div>

    <div class="form-group undergroundCommerce" style="display: none">
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">地下商业建筑面积</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="地下商业建筑面积" class="form-control" name="undergroundCommerceBuildArea">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">可售面积</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="可售面积" class="form-control" name="undergroundCommerceMaySaleArea">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">单位售价</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="单位售价" class="form-control" name="undergroundCommerceUnitPrice">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">合价</label>
            <div class="col-md-2 col-sm-2">
                <input type="text" readonly="readonly"
                       placeholder="合价" class="form-control" name="undergroundCommerceTotalPrice">
            </div>
        </div>
    </div>

    <div class="form-group Garage" style="display:none;">
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">车库建筑面积</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="车库建筑面积" class="form-control" name="garageBuildArea">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">可售面积</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="可售面积" class="form-control" name="garageMaySaleArea">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">单位售价</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="单位售价" class="form-control" name="garageUnitPrice">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">合价</label>
            <div class="col-md-2 col-sm-2">
                <input type="text" readonly="readonly"
                       placeholder="合价" class="form-control" name="garageTotalPrice">
            </div>
        </div>
    </div>

    <div class="form-group business" style="display: none">
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">商业建筑面积</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="商业建筑面积" class="form-control" name="businessCommerceBuildArea">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">可售面积</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="可售面积" class="form-control" name="businessCommerceMaySaleArea">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">单位售价</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="单位售价" class="form-control" name="businessCommerceUnitPrice">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">合价</label>
            <div class="col-md-2 col-sm-2">
                <input type="text" readonly="readonly"
                       placeholder="合价" class="form-control" name="businessCommerceTotalPrice">
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">预期建面合计</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="预期建面合计" class="form-control" name="" readonly="readonly">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">可售合计</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="可售合计" class="form-control" name="" readonly="readonly">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">总建面积</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="总建面积" class="form-control" name="" readonly="readonly">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-md-1 col-sm-1 control-label">总合计</label>
            <div class="col-md-2 col-sm-2">
                <input type="text"
                       placeholder="总合计" class="form-control" name="" readonly="readonly">
            </div>
        </div>
    </div>

</form>

<script>
    var hypothesisDevelopment = new Object();
    hypothesisDevelopment.isNumber = function (obj) {
        return true;
    }
    hypothesisDevelopment.config = function () {
        var config = {};
        config.frm = "frmDevelopment";//表单id
        /*此处的配置key(select2中的key为xxxxSelect2)必须与页面上input name的一致 describe 为描述*/
        var inputNameConfig = {
            constructionInstallationEngineeringFee: {
                key: "constructionInstallationEngineeringFee",
                describe: "建筑安装工程费",
                value: "",
                select: ""
            },
            hypothesisDevelopmentSelect2: {
                key: "hypothesisDevelopmentSelect2",
                describe: "",
                value: "",
                select: "hypothesisDevelopmentSelect2"
            },
            constructionCycle: {key: "constructionCycle", describe: "建设周期", value: "", select: ""},
            developedTime: {key: "developedTime", describe: "开发周期", value: "", select: ""},

            workUnitPrice: {key: "workUnitPrice", describe: "单位售价", value: "", select: ""},
            workTotalPrice: {key: "workTotalPrice", describe: "销售合价", value: "", select: ""},
            workMaySaleArea: {key: "workMaySaleArea", describe: "可售面积", value: "", select: ""},
            workBuildArea: {key: "workBuildArea", describe: "建筑面积", value: "", select: ""},

            garageUnitPrice: {key: "garageUnitPrice", describe: "单位售价", value: "", select: ""},
            garageTotalPrice: {key: "garageTotalPrice", describe: "销售合价", value: "", select: ""},
            garageMaySaleArea: {key: "garageMaySaleArea", describe: "可售面积", value: "", select: ""},
            garageBuildArea: {key: "garageBuildArea", describe: "建筑面积", value: "", select: ""},

            undergroundCommerceUnitPrice: {
                key: "undergroundCommerceUnitPrice",
                describe: "单位售价",
                value: "",
                select: ""
            },
            undergroundCommerceTotalPrice: {
                key: "undergroundCommerceTotalPrice",
                describe: "销售合价",
                value: "",
                select: ""
            },
            undergroundCommerceMaySaleArea: {
                key: "undergroundCommerceMaySaleArea",
                describe: "可售面积",
                value: "",
                select: ""
            },
            undergroundCommerceBuildArea: {
                key: "undergroundCommerceBuildArea",
                describe: "建筑面积",
                value: "",
                select: ""
            },
            residenceCommerceUnitPrice: {key: "residenceCommerceUnitPrice", describe: "单位售价", value: "", select: ""},
            residenceCommerceTotalPrice: {key: "residenceCommerceTotalPrice", describe: "销售合价", value: "", select: ""},
            residenceCommerceMaySaleArea: {
                key: "residenceCommerceMaySaleArea",
                describe: "可售面积",
                value: "",
                select: ""
            },
            residenceCommerceBuildArea: {key: "residenceCommerceBuildArea", describe: "建筑面积", value: "", select: ""},

            businessCommerceUnitPrice: {key: "businessCommerceUnitPrice", describe: "单位售价", value: "", select: ""},
            businessCommerceTotalPrice: {key: "businessCommerceTotalPrice", describe: "销售合价", value: "", select: ""},
            businessCommerceMaySaleArea: {key: "businessCommerceMaySaleArea", describe: "可售面积", value: "", select: ""},
            businessCommerceBuildArea: {key: "businessCommerceBuildArea", describe: "建筑面积", value: "", select: ""},

        };
        config.inputConfig = function () {
            return inputNameConfig;
        };
        config.inputName = function () {
            var arr = new Array();
            //遍历对象
            $.each(hypothesisDevelopment.config().inputConfig(), function (i, n) {
                arr.push(n);
            })
            return arr;
        };
        return config;
    };
    hypothesisDevelopment.inputFun = {
        workInput: function () {
            hypothesisDevelopment.inputAlgorithmObject.workFun();
        }
    }
    hypothesisDevelopment.inputAlgorithmObject = {
        //子表单 办公建筑 算法
        workFun: function () {
            var workBuildArea = null;
            workBuildArea = hypothesisDevelopment.inputAlgorithmObject.jqueryInputGetAndSet("get",hypothesisDevelopment.config().inputConfig().workBuildArea,null);
        },
        jqueryInputGetAndSet:function (flag, name, data) {
            if (flag == 'get') {
                var text = null;
                text = $("." + hypothesisDevelopment.config().frm + " " + "input[name='" + name + "']").val();
                text = hypothesisDevelopment.inputAlgorithmObject.specialTreatment(text);
                return text;
            }
            if (flag == 'set') {
                $("." + hypothesisDevelopment.config().frm + " " + "input[name='" + name + "']").val(data);
            }
        },
        isNotNull: function (obj) {
            if (obj == 0) {
                return true;
            }
            if (obj) {
                return true;
            }
            return false;
        },
        specialTreatment: function (obj) {
            if (hypothesisDevelopment.inputAlgorithmObject.isNotNull(obj)) {
                return obj;
            }
            return 0;
        }
    };
    /**
     * @author:  zch
     * 描述:判断某个字符串中是否包含某个字符串 (workdddg,WORK) return true
     * @date:
     **/
    hypothesisDevelopment.indexOfUtils = function (s, str) {
        if (s.indexOf(str) >= 0) {
            return true;
        }
        s = s.toUpperCase();
        str = str.toUpperCase();
        if (s.indexOf(str) >= 0) {
            return true;
        }
        return false;
    };
    hypothesisDevelopment.inputForm = function (key, value) {
        if (hypothesisDevelopment.indexOfUtils(key,"work")){
            hypothesisDevelopment.inputFun.workInput();
        }
    };
    hypothesisDevelopment.inputEvent = function () {
        var arr = hypothesisDevelopment.config().inputName();
        $.each(arr, function (i, n) {
            var key = n.key;
            var input = $("." + hypothesisDevelopment.config().frm + " " + "input[name='" + key + "']");
            input.bind("blur", function () {//使用失去焦点事件来收集数据并且计算
                var value = input.val();
                if (hypothesisDevelopment.isNumber(value)) {
                    try {
                        var funName = "hypothesisDevelopment.inputFun." + key + "Input(" + input.val() + ")";
                        eval(funName);
                    } catch (e) {
                        console.log("没有相关定义的函数或者是属于子表单");
                        hypothesisDevelopment.inputForm(key, value);
                    }
                } else {
                    Alert("请输入合法数字!")
                }
            });
        })
        hypothesisDevelopment.selectEvent.monitor.monitor();
    }

    hypothesisDevelopment.selectEvent = {
        load: {
            hypothesisDevelopmentSelect2: function () {
                AssessCommon.loadDataDicByKey(AssessDicKey.mdHypothesisDevelopment, "", function (html, data) {
                    var retHtml = '<option value="" selected>-请选择-</option>';
                    $.each(data, function (i, item) {
                        retHtml += ' <option value="' + item.fieldName + '">' + item.name + '</option>';
                    });
                    $("." + hypothesisDevelopment.config().frm + " ." + hypothesisDevelopment.config().inputConfig().hypothesisDevelopmentSelect2.select).html(retHtml);
                    $("." + hypothesisDevelopment.config().frm + " ." + hypothesisDevelopment.config().inputConfig().hypothesisDevelopmentSelect2.select).select2();
                });
            },
            load: function () {
                hypothesisDevelopment.selectEvent.load.hypothesisDevelopmentSelect2();
            }
        },
        //监听 change 事件
        monitor: {
            hypothesisDevelopmentSelect2: function () {
                var key = hypothesisDevelopment.config().frm + " ." + hypothesisDevelopment.config().inputConfig().hypothesisDevelopmentSelect2.select;
                $("." + key).change(function () {
                    var value = $("." + key).eq(1).val();
                    $("." + hypothesisDevelopment.config().frm + " ." + value).show();
                });
            },
            monitor: function () {
                hypothesisDevelopment.selectEvent.monitor.hypothesisDevelopmentSelect2();
            }
        }
    };
    hypothesisDevelopment.eventInit = function () {
        hypothesisDevelopment.selectEvent.load.load();
        hypothesisDevelopment.inputEvent();
    };

    $(function () {
        hypothesisDevelopment.eventInit();
    });
</script>
