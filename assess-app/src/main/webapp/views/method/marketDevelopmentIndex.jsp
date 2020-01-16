<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 假设开发法 -->
<div class="x_panel">
    <div class="x_title">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3 style="word-break: break-all">
            ${judgeObject.name}
            <small>(${judgeObject.evaluationArea}㎡)</small>
            <small>
                <button class="btn btn-xs btn-primary" data-toggle="modal" href="#boxSchemeInfoModel"
                        onclick="developmentCommon.loadSchemeInfoTableList({projectId:'${projectPlanDetails.projectId}',methodDataId:'${mdDevelopment.id}',methodType:'${methodTypeObj.id}'},'landEngineering.selectFun');">
                    引用
                </button>
            </small>
        </h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">

        <div class="col-sm-12 form-group" id="developmentCheckboxTool">
                <span class="col-sm-1">
                    <label>经营方式</label><span class="symbol required"></span>
                </span>
            <span class="col-sm-2 col-sm-offset-1 checkbox-inline">
                     <input type="radio" id="developmentLand" name="type" value="1" checked="checked">
                    <label for="developmentLand">土地</label>
                </span>
            <span class="col-sm-2  checkbox-inline">
                    <input type="radio" id="developmentEngineering" name="type" value="2">
                    <label for="developmentEngineering">在建工程</label>
                </span>
        </div>

        <%@include file="/views/method/module/developmentModule/landEngineering.jsp" %>
    </div>

</div>
<script src="${pageContext.request.contextPath}/js/method/developmentCommon.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/declare/declare.common.js?v=${assessVersion}"></script>
<%@include file="/views/method/module/developmentModule/landEngineeringJs.jsp" %>
<%@include file="/views/method/module/developmentCommon.jsp" %>
<%@include file="/views/project/tool/rewardRate.jsp" %>
<%@include file="/views/method/module/economicIndicators.jsp" %>

<script>


    var development = {};

    development.config = {
        frm: "#mdDevelopmentLandFrm"
    };
    /**
     * 空字符串检测
     * @param item
     * @returns {boolean}
     */
    development.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    development.isNotBlankObject = function (obj) {
        for (var key in obj) {
            return true;
        }
        return false
    };

    function loadParamsValue(that, t) {
        var value = $(that).val();
        var i = 0;
        if (!development.isNotBlank(value)) {
            if (t != 'not') {
                $(that).attr("data-value", '0');
                $(that).val(0);
            }
        }
        if (development.isNotBlank(value)) {
            if ($.isNumeric(value)) {
                i++;
            }
            var reg = new RegExp(/^[0-9]+\.?[0-9]*%$/);
            if (reg.test(value)) {
                i++;
            }
            if (i == 0) {
                alert("不符合，必须是数字!");
                $(that).attr("data-value", '');
                $(that).val('');
                return false;
            }
        }
        var target = $(development.config.frm);
        calculationNumeric(formSerializeArray(target), function (data) {
            target.find("input[name='projectConstructionPeriod']").val(data.projectConstructionPeriod);

            target.find("td[name='constructionCostSubtotal']").html(data.constructionCostSubtotal);
            target.find("input[name='constructionCostSubtotal']").val(data.constructionCostSubtotal);

            target.find("td[name='interestInvestment']").html(data.interestInvestment);
            target.find("input[name='interestInvestment']").val(data.interestInvestment);

            target.find("td[name='investmentProfit']").html(data.investmentProfit);
            target.find("input[name='investmentProfit']").val(data.investmentProfit);


            target.find("td[name='assessPrice']").html(data.assessPrice);
            target.find("input[name='assessPrice']").val(data.assessPrice);
            try {
                if (data.assessPrice) {
                    if (data.price == 0) {
                        data.price = data.assessPrice;
                    }
                }
            } catch (e) {
            }
            target.find("td[name='price']").html(data.price);
            target.find("input[name='price']").val(data.price);
            if ($("#md_development_form").size() != 0) {
                $("#md_development_form").find("input[name='price']").val(data.price);
            }
        });
    }

    function checkParams(that) {
        var value = $(that).val();
        if (!development.isNotBlank(that)) {
            return false;
        }
        if (!development.isNotBlank(value)) {
            $(that).attr("data-value", '');
            $(that).val('');
        }
        loadParamsValue(that, 'not');
    }

    function calculationNumeric(data, callback) {
        $.ajax({
            type: "post",
            url: getContextPath() + "/mdDevelopment/calculationNumeric",
            data: {fomData: JSON.stringify(data)},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
//                    Alert("失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    }


    development.valid = function (callback) {
        var frm = $(development.config.frm);
        if (!frm.valid()) {
            return false;
        }
        if (callback) {
            callback();
        }
    };

    development.getFomData = function () {
        var frm = $(development.config.frm);
        var data = formSerializeArray(frm);
        return data;
    };

    development.initData = function () {
        var frm = $(development.config.frm);
        if (!development.isNotBlank('${mdDevelopment.id}')) {
            $.ajax({
                type: "get",
                url: "${pageContext.request.contextPath}/dataTaxRateAllocation/specialTreatment",
                data: {
                    province: '${schemeAreaGroup.province}',
                    city: '${schemeAreaGroup.city}',
                    district: '${schemeAreaGroup.district}',
                    bisNationalUnity: true
                },
                success: function (result) {
                    if (result.ret) {
                        if (result.data) {
                            $.each(result.data, function (i, item) {
                                var taxRate = item.taxRate;
                                if (item.taxRate) {
                                    taxRate = Number(item.taxRate) * 100;
                                    taxRate = taxRate + "%";
                                }
                                if (item.typeName == '增值税') {
                                    if (item.taxRate) {
                                        frm.find("input[name='landValueAddedTax']").val(taxRate);
                                        frm.find("input[name='landValueAddedTax']").attr("data-value", item.taxRate);
                                    }
                                }
                                if (item.typeName == '契税') {
                                    if (item.taxRate) {
                                        frm.find("input[name='deedTaxRate']").val(taxRate);
                                        frm.find("input[name='deedTaxRate']").attr("data-value", item.taxRate);
                                    }
                                }
                                if (item.typeName == '所得税') {
                                    if (item.taxRate) {
                                        frm.find("input[name='projectDevelopmentIncomeTax']").val(taxRate);
                                        frm.find("input[name='projectDevelopmentIncomeTax']").attr("data-value", item.taxRate);
                                    }
                                }
                                if (item.typeName == '其它税费') {
                                    if (item.taxRate) {
                                        frm.find("input[name='otherEngineeringCost']").val(taxRate);
                                        frm.find("input[name='otherEngineeringCost']").attr("data-value", item.taxRate);
                                    }
                                }
                            });
                        }
                    } else {
                        Alert("保存失败:" + result.errmsg);
                    }
                },
                error: function (e) {
                    Alert("调用服务端方法失败，失败原因:" + e);
                }
            });
        }
        landEngineering.loadMdDevelopmentInfrastructureChildrenTable();
        landEngineering.unsaleableBuildingAreaFunHandle();
        landEngineering.loadMdCalculatingMethodEngineeringCostTable();
    };

    development.writeValueEvent = function (value, callback) {
        var frm = $(development.config.frm);
        frm.find("input[name='type']").val(value);
        if (value == 1) {
            var managementExpense = frm.find("input[placeholder='续建管理费率']");
            managementExpense.attr({placeholder: "管理费率"});
            managementExpense.closest(".form-group").find("label").first().text("管理费率");

            var landGetRelevant = frm.find("input[placeholder='在建工程修复费用']");
            landGetRelevant.attr({placeholder: "土地取得附加成本"});
            landGetRelevant.closest(".form-group").find("label").first().text("土地取得附加成本(万元)");

            var interestInvestmentTax = frm.find("input[placeholder='续建投资利息率']");
            interestInvestmentTax.attr({placeholder: "投资利息率"});
            interestInvestmentTax.closest(".form-group").find("label").first().text("投资利息率");

            var investmentProfitTax = frm.find("input[placeholder='续建投资利润率']");
            investmentProfitTax.attr({placeholder: "投资利润率"});
            investmentProfitTax.closest(".form-group").find("label").first().text("投资利润率");
        }
        if (value == 2) {
            var managementExpense = frm.find("input[placeholder='管理费率']");
            managementExpense.attr({placeholder: "续建管理费率"});
            managementExpense.closest(".form-group").find("label").first().text("续建管理费率");

            var landGetRelevant = frm.find("input[placeholder='土地取得附加成本']");
            landGetRelevant.attr({placeholder: "在建工程修复费用"});
            landGetRelevant.closest(".form-group").find("label").first().text("在建工程修复费用(万元)");

            var interestInvestmentTax = frm.find("input[placeholder='投资利息率']");
            interestInvestmentTax.attr({placeholder: "续建投资利息率"});
            interestInvestmentTax.closest(".form-group").find("label").first().text("续建投资利息率");

            var investmentProfitTax = frm.find("input[placeholder='投资利润率']");
            investmentProfitTax.attr({placeholder: "续建投资利润率"});
            investmentProfitTax.closest(".form-group").find("label").first().text("续建投资利润率");
        }
        if (callback) {
            callback(value);
        }
    };

    development.checkedFun = function (that, name, flag) {
        var form = $(that).closest("form");
        var target = form.find("[name='" + name + "']:checkbox");
        if (flag) {//全选或者全不选
            var number = 1;
            target.each(function (i, n) {
                if ($(this).prop("checked")) {
                    number++;
                }
            });
            if (number == 1) {
                target.prop("checked", true);
            } else {
                target.prop("checked", false);
            }
        } else {
            //首先让选中的失效
            target.each(function (i, n) {
                if ($(this).prop("checked")) {
                    $(this).prop("disabled", true);
                }
            });
            //然后让没有选中的元素设置为选中
            target.each(function (i, n) {
                if (!$(this).prop("checked")) {
                    $(this).prop("checked", true);
                }
            });
            //最后是让失效的元素恢复,并且使其不选中
            target.each(function (i, n) {
                if ($(this).prop("disabled")) {
                    $(this).prop("disabled", false);
                    $(this).prop("checked", false);
                }
            });
        }
    };

    development.initParcelSettingData = function (data) {
        var industrySupplyInfoContainer = $("#industrySupplyInfoContainer_BBBBB");
        var developmentDegreeContentContainer = $("#developmentDegreeContentContainer_BBBBB");
        industrySupplyInfoContainer.empty();
        developmentDegreeContentContainer.empty();
        //宗地外设定
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateLandInfrastructure, '', function (html, resultData) {
            var resultHtml = '<div>';
            var array = [];
            if (data) {
                if (data.parcelSettingOuter) {
                    array = data.parcelSettingOuter.split(',');
                }
            }
            $.each(resultData, function (i, item) {
                resultHtml += '<span class="checkbox-inline"><input type="checkbox" ';
                if ($.inArray(item.id.toString(), array) > -1) {
                    resultHtml += ' checked="checked" ';
                }
                resultHtml += ' id="parcelSettingOuterBBBBB' + item.id + '" name="parcelSettingOuter" value="' + item.id + '">';
                resultHtml += '<label for="parcelSettingOuterBBBBB' + item.id + '">' + item.name + '</label></span>';
            });
            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '全选或全不选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"development.checkedFun(this,'parcelSettingOuter',true)\">";
            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '反选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"development.checkedFun(this,'parcelSettingOuter',false)\">";
            resultHtml += "</div>";
            if (industrySupplyInfoContainer.find("div").size() == 0) {
                industrySupplyInfoContainer.append(resultHtml);
            } else {
                $.each(resultData, function (i, item) {
                    var ele = "#parcelSettingOuterBBBBB" + item.id;
                    ele = $(ele);
                    if ($.inArray(item.id.toString(), array) > -1) {
                        ele.prop("checked", true);
                    } else {
                        ele.prop("checked", false);
                    }
                });
            }
        }, true);
        //宗地内设定
        AssessCommon.loadDataDicByKey(AssessDicKey.estateDevelopment_degreePrepared_land, '', function (html, resultData) {
            var resultHtml = '<div>';
            var array = [];
            if (data) {
                if (data.parcelSettingInner) {
                    array = data.parcelSettingInner.split(',');
                }
            }
            $.each(resultData, function (i, item) {
                resultHtml += '<span class="checkbox-inline"><input type="checkbox" ';
                if ($.inArray(item.id.toString(), array) > -1) {
                    resultHtml += ' checked="checked" ';
                }
                resultHtml += ' id="parcelSettingInnerBBBBB' + item.id + '" name="parcelSettingInner" value="' + item.id + '">';
                resultHtml += '<label for="parcelSettingInnerBBBBB' + item.id + '">' + item.name + '</label></span>';
            });
            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '全选或全不选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"development.checkedFun(this,'parcelSettingInner',true)\">";
            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '反选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"development.checkedFun(this,'parcelSettingInner',false)\">";
            resultHtml += "</div>";
            if (developmentDegreeContentContainer.find("div").size() == 0) {
                developmentDegreeContentContainer.append(resultHtml);
            } else {
                $.each(resultData, function (i, item) {
                    var ele = "#parcelSettingInnerBBBBB" + item.id;
                    ele = $(ele);
                    if ($.inArray(item.id.toString(), array) > -1) {
                        ele.prop("checked", true);
                    } else {
                        ele.prop("checked", false);
                    }
                });
            }
        });
    };

    $(document).ready(function () {

        development.initData();

        var frm = $(development.config.frm);
        var type = '${mdDevelopment.type}';
        if (development.isNotBlank(type)) {
            if (type == '1') {
                $("#developmentLand").attr('checked', 'true');
                $("#developmentEngineering").removeAttr("checked");
            }
            if (type == '2') {
                $("#developmentEngineering").attr('checked', 'true');
                $("#developmentLand").removeAttr("checked");
            }
        } else {
            type = $("#developmentCheckboxTool").find("input[type='radio'][name='type']:checked").val();
        }
        development.writeValueEvent(type, function () {
            landEngineering.loadMdCalculatingMethodEngineeringCostTable();
            landEngineering.infrastructureChildrenTable.bootstrapTable('refresh');
        });

        $("#developmentCheckboxTool").find("input[type='radio'][name='type']").change(function () {
            var value = $(this).val();
            development.writeValueEvent(value, function () {
                landEngineering.loadMdCalculatingMethodEngineeringCostTable();

            });
        });


        var data = {
            parcelSettingInner: '${mdDevelopment.parcelSettingInner}',
            parcelSettingOuter: '${mdDevelopment.parcelSettingOuter}'
        };
        development.initParcelSettingData(data);
    });
</script>