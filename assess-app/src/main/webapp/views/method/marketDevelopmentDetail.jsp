<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/8/17
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 假设开发法 -->
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h2>假设开发法</h2>
        <div class="clearfix"></div>
    </div>

    <div class="x_content optionsBuildBox">
        <div>
            <!-- 隐藏框数据 -->
            <input type="hidden" id="mdDevelopmentHypothesisJSON" name="mdDevelopmentHypothesisJSON"
                   value='${mdDevelopmentHypothesisJSON}'>
            <input type="hidden" id="mdDevelopmentArchitecturalJSON" name="mdDevelopmentArchitecturalJSON"
                   value='${mdDevelopmentArchitecturalJSON}'>
        </div>
        <div class="col-sm-12 form-group">
            <span class="col-sm-1">
                <label>假设开发法:</label>
            </span>
            <span class="col-sm-2 col-sm-offset-1 checkbox-inline">
                 <input type="radio" name="development" value="1" checked="checked">
                <label>土地</label>
            </span>
            <span class="col-sm-2  checkbox-inline">
                <input type="radio" name="development" value="2">
                <label>在建工程</label>
            </span>
        </div>
        <div class="col-sm-12 form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    项目建设期(年)
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="项目建设期(年)" readonly="readonly"
                           class="form-control" name="projectConstructionPeriod" id="projectConstructionPeriod">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    已开发时间(年)
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="已开发时间(年)" readonly="readonly"
                           class="form-control" name="developedTime" id="developedTime">
                </div>
            </div>
        </div>
    </div>
</div>
<div class="landEngineering">
    <jsp:include page="../method/module/developmentModule/landEngineeringDetail.jsp"></jsp:include>
    <!-- 主题内容 -->
</div>
<div class="architecturalEngineering" style="display: none;">
    <jsp:include page="../method/module/developmentModule/underConstructionDetail.jsp"></jsp:include>
    <!-- 主题内容 -->
</div>
<script>

    var AlgorithmsPrototype = function () {

    };
    AlgorithmsPrototype.prototype.isNumber = function (obj) {
        if (obj == 0) {
            return true;
        }
        if (obj == '0') {
            return true;
        }
        if (AlgorithmsPrototype.prototype.isNotNull(obj)) {
            var regPos = /^\d+(\.\d+)?$/; //非负浮点数
            var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
            if (regPos.test(obj) || regNeg.test(obj)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    /**
     * @author:  zch
     * 描述:判断是否为null
     * @date:
     **/
    AlgorithmsPrototype.prototype.isNotNull = function (obj) {
        if (obj == null) {
            return false;
        }
        if (obj == '') {
            return false;
        }
        if (obj == "") {
            return false;
        }
        if (obj == 'undefined') {
            return false;
        }
        return true;
    };

    var optionsBuildBox = new Object();

    optionsBuildBox.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    };
    optionsBuildBox.specialTreatment = function (obj) {
        if (optionsBuildBox.isEmpty(obj)) {
            var nnn = "" + obj + "";
            var str = nnn.substring(nnn.length - 1, nnn.length);
            if (str == '%') {//检测是否为百分比
                return nnn;
            } else {
                str = AssessCommon.pointToPercent(Number(nnn));
                return str;
            }
            return obj;
        }
        return 0;
    };
    optionsBuildBox.set = function (name, data,id) {
        if (optionsBuildBox.isEmpty(name)) {
            if (optionsBuildBox.isEmpty(data)) {
                $("#" + id).find("input[name='" + name + "']").val(data);
            }
        }
    };
    optionsBuildBox.showHypothesisDevelopment = function () {
        $(".landEngineering").show();
        $(".architecturalEngineering").hide();
    };
    optionsBuildBox.showArchitecturalEngineering = function () {
        $(".architecturalEngineering").show();
        $(".landEngineering").hide();
    };

    /**
     * @author:  zch
     * 描述:选项卡初始化载入
     * @date:2018-08-17
     **/
    optionsBuildBox.tabControl = function () {
        $(".optionsBuildBox input[type='radio'][name='development']").change(function () {
            var val = $(".optionsBuildBox :radio:checked").val();
            if (val == 1) {
                optionsBuildBox.showHypothesisDevelopment();
            }
            if (val == 2) {
                optionsBuildBox.showArchitecturalEngineering();
            }
        });
    };


    //初始化在建工程
    optionsBuildBox.hypothesisDevelopmentInit = function (data) {
        var forms = $("#underConstructionModel").find("form");
        $.each(forms, function (i, n) {
            $(n).clearAll();
        });
        $.each(forms, function (i, n) {
            $(n).initForm(data);
        });
        data = JSON.parse(data);
        $("#developedTime").val(data.developedTime);
        $("#projectConstructionPeriod").val(data.projectConstructionPeriod);
        $("#underConstructionModel").find(".estimateSaleTotal").html(data.estimateSaleTotal);
        $("#underConstructionModel").find(".totalGrossFloorArea").html(data.totalGrossFloorArea);
        $("#underConstructionModel").find(".constructionCostSubtotal").html(data.constructionCostSubtotal);
        $("#underConstructionModel").find(".unforeseenExpenses").html(data.unforeseenExpenses);
        $("#underConstructionModel").find(".investmentProfit").html(data.investmentProfit);
        $("#underConstructionModel").find(".landPriceCorrecting").html(data.landPriceCorrecting);
        $("#underConstructionModel").find(".estimateUnitPriceLandC33").html(data.estimateUnitPriceLandC33);
        $("#underConstructionModel").find("#underConstructionParameterFrm" + " table").eq(0).html(data.table);
        $.each(forms, function (i, n) {
            var inputs = $(n).find(":input");
            $.each(inputs, function (i, k) {
                var kk = $(k);
                var className = kk.attr("class");
                var str = null;
                var name = kk.attr("name");
                if (className.indexOf("x-percent") != -1) {
                    try {
                        str = eval("data." + name);
                        if (optionsBuildBox.isEmpty(str)) {
                            str = optionsBuildBox.specialTreatment(str);
                            optionsBuildBox.set(name,str,"underConstructionModel");
                        }
                    } catch (e) {
                    }
                }
            });
        });
    };

    //初始化 土地
    optionsBuildBox.architecturalEngineeringInit = function (data) {
        var forms = $("#landEngineeringModel").find("form");
        $.each(forms, function (i, n) {
            $(n).clearAll();
        });
        $.each(forms, function (i, n) {
            $(n).initForm(data);
        });
        data = JSON.parse(data);
        $("#developedTime").val(data.developedTime);
        $("#projectConstructionPeriod").val(data.projectConstructionPeriod);

        $("#landEngineeringModel").find(".estimateSaleTotal").html(data.estimateSaleTotal);
        $("#landEngineeringModel").find(".totalGrossFloorArea").html(data.totalGrossFloorArea);
        $("#landEngineeringModel").find(".constructionCostSubtotal").html(data.constructionCostSubtotal);
        $("#landEngineeringModel").find(".unforeseenExpenses").html(data.unforeseenExpenses);
        $("#landEngineeringModel").find(".investmentProfit").html(data.investmentProfit);
        $("#landEngineeringModel").find(".landPriceCorrecting").html(data.landPriceCorrecting);
        $("#landEngineeringModel").find(".estimateUnitPriceLandC33").html(data.estimateUnitPriceLandC33);
        $("#landEngineeringModel").find("#parameterFrm" + " table").eq(0).html(data.table);
        $.each(forms, function (i, n) {
            var inputs = $(n).find(":input");
            $.each(inputs, function (i, k) {
                var kk = $(k);
                var className = kk.attr("class");
                var str = null;
                var name = kk.attr("name");
                if (className.indexOf("x-percent") != -1) {
                    try {
                        str = eval("data." + name);
                        if (optionsBuildBox.isEmpty(str)) {
                            str = optionsBuildBox.specialTreatment(str);
                            optionsBuildBox.set(name,str,"landEngineeringModel");
                        }
                    } catch (e) {
                    }
                }
            });
        });
    };

    optionsBuildBox.detailInit = function () {
        var mdDevelopmentHypothesis = "${mdDevelopmentHypothesis}";
        var mdDevelopmentArchitectural = "${mdDevelopmentArchitectural}";
        if (AlgorithmsPrototype.prototype.isNotNull(mdDevelopmentArchitectural)) {
            mdDevelopmentArchitectural = $("#mdDevelopmentArchitecturalJSON").val();
            try {
                mdDevelopmentArchitectural = JSON.parse(mdDevelopmentArchitectural);
                optionsBuildBox.architecturalEngineeringInit(mdDevelopmentArchitectural);
            } catch (e) {
                console.log("json parse 失败!")
            }
        }

        if (AlgorithmsPrototype.prototype.isNotNull(mdDevelopmentHypothesis)) {
            mdDevelopmentHypothesis = $("#mdDevelopmentHypothesisJSON").val();
            try {
                mdDevelopmentHypothesis = JSON.parse(mdDevelopmentHypothesis);
                optionsBuildBox.hypothesisDevelopmentInit(mdDevelopmentHypothesis);
            } catch (e) {
                console.log("json parse 失败!")
            }
        }
    };


    $(function () {
        optionsBuildBox.tabControl();
        optionsBuildBox.detailInit();
    });
</script>

