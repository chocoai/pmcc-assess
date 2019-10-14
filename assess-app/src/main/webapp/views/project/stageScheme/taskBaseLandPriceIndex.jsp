<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
</head>


<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <jsp:include page="/views/project/tool/rewardRate.jsp"></jsp:include>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>委估对象${number}号基准地价系数修正法价格测算表</h3>
                    <div class="clearfix"></div>
                </div>

                <div class="x_content">
                    <div class="x_panel">
                        <div class="x_title collapse-link">
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                            </ul>
                            <h3>土地指数</h3>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <table class="table table-bordered" id="indexDetailTable">
                                <!-- cerare document add ajax data-->
                            </table>
                        </div>
                    </div>

                    <div class="x_panel">
                        <div class="x_title collapse-link">
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                            </ul>
                            <h3>容积率修正系数表</h3>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <table class="table table-bordered" id="volumetricRateDetailTable">
                                <!-- cerare document add ajax data-->
                            </table>
                        </div>
                    </div>

                    <div class="x_panel">
                        <div class="x_title collapse-link">
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                            </ul>
                            <h3>因素条件说明及修正系数</h3>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <form class="form-horizontal" id="areaAndSeveralAmendForm">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            区域及个别修正系数
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" placeholder="区域及个别修正系数" class="form-control"
                                                   id="areaAndSeveralAmend" name="areaAndSeveralAmend" required readonly
                                                   data-rule-number="true">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                        <table class="table table-striped table-bordered" style="display: none">
                                            <thead>
                                            <tr>
                                                <th width="10%">土地级别类别</th>
                                                <th width="10%">土地级别类型</th>
                                                <th width="10%">土地级别等级</th>
                                                <th width="20%">说明</th>
                                                <th width="10%">分值</th>
                                                <th width="5%"></th>
                                            </tr>
                                            </thead>
                                            <tbody id="landLevelTabContent">

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="x_panel">
                        <div class="x_title collapse-link">
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                            </ul>
                            <h3>基准地价因素</h3>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <form class="form-horizontal" id="master">
                                <input type="hidden" name="id" value="${master.id}">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            基准地价(元/㎡)
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" placeholder="农用地总面积" class="form-control"
                                                   id="standardPremium" name="standardPremium" required
                                                   onblur="getParcelPrice()"
                                                   value="${master.standardPremium}" data-rule-number="true">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            说明
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" value="${master.standardPremiumRemark}"
                                                   class="form-control" name="standardPremiumRemark">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-1 control-label">
                                        土地还原利率
                                    </label>
                                    <div class="col-sm-3">
                                        <div class="input-group">
                                            <input type="text" required class="form-control x-percent"
                                                   id="rewardRate"
                                                   name="rewardRate" placeholder="还原率" readonly="readonly"
                                                   value="${master.rewardRate}">
                                            <span class="input-group-btn">
                                                     <input type="hidden" name="rewardRateId"
                                                            value="${master.rewardRateId}">
                                                     <input type="button" class="btn btn-primary" value="还原率测算"
                                                            onclick="getRewardRate(this);"/>
                                                     </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            法定年限
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" placeholder="法定年限" class="form-control"
                                                   id="legalAge"
                                                   name="legalAge" required onblur="getPeriodAmend()"
                                                   value="${master.legalAge}" data-rule-number="true">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            说明
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" value="${master.legalAgeRemark}"
                                                   class="form-control" name="legalAgeRemark">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            剩余使用年限
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" placeholder="剩余使用年限" class="form-control"
                                                   id="landSurplusYear"
                                                   name="landSurplusYear" required onblur="getPeriodAmend()"
                                                   value="${master.landSurplusYear}" data-rule-number="true">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            说明
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" value="${master.landSurplusYearRemark}"
                                                   class="form-control" name="landSurplusYearRemark">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            开发程度修正
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" placeholder="开发程度修正" class="form-control"
                                                   id="developCorrect"
                                                   name="developCorrect" required onblur="getParcelPrice()"
                                                   value="${master.developCorrect}" data-rule-number="true">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            说明
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" value="${master.developCorrectRemark}"
                                                   class="form-control" name="developCorrectRemark">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            委估宗地面积(㎡)
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" placeholder="委估宗地面积" class="form-control"
                                                   id="evaluationArea"
                                                   name="evaluationArea" required onblur="getParcelPrice()"
                                                   value="${master.evaluationArea}" data-rule-number="true">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            说明
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" value="${master.evaluationAreaRemark}"
                                                   class="form-control" name="evaluationAreaRemark">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            委估对象容积率
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" placeholder="委估对象容积率" class="form-control"
                                                   id="volumetricRate"
                                                   name="volumetricRate" required onblur="getParcelPrice()"
                                                   value="${master.volumetricRate}" data-rule-number="true">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            说明
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" value="${master.volumetricRateRemark}"
                                                   class="form-control" name="volumetricRateRemark">
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="x_panel">
                        <div class="x_title collapse-link">
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                            </ul>
                            <h3>测算结果</h3>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <div class="form-group">
                                <div class="col-md-12 col-sm-12">
                                    <table class="table table-bordered">
                                        <tbody>
                                        <tr>
                                            <td>期日修正系数</td>
                                            <td id="dateAmend">${dateAmend}</td>
                                            <td>年期修正系数</td>
                                            <td id="periodAmend">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>容积率修正</td>
                                            <td id="volumeFractionAmend">${volumeFractionAmend}</td>
                                            <td>委估宗地单价（元/㎡）</td>
                                            <td id="parcelPrice">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>委估宗地单价（万元/亩）</td>
                                            <td id="parcelBhouPrice">
                                            </td>
                                            <td>委估宗地总价（万元）</td>
                                            <td id="parcelTotalPrice">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>楼面地价</td>
                                            <td id="floorPremium">
                                            </td>
                                            <td>修正差额(%)</td>
                                            <td id="correctionDifference">
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button id="btn_submit" class="btn btn-success" onclick="submit()">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/examine/examine.estate.js"></script>
<script type="text/html" id="landLevelTabContentBody">
    <tr class="group">
        <td class="table-cell">
            {landLevelTypeName}
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        <td>
            {landLevelCategoryName}
        </td>
        <td>
            <select class="form-control" name="landLevelGrade" onchange="estateCommon.landLevelHandle(this);">
                {landLevelGradeHTML}
            </select>
        </td>
        <td>
            <label name="reamark" class="form-control">{reamark}</label>
        </td>
        <td>
            <input type="hidden" class="form-control" name="dataLandLevelAchievement"
                   value="{dataLandLevelAchievement}">
            <input type="text" class="form-control" name="landFactorTotalScore" value="{landFactorTotalScore}"
                   onblur="getAreaAndSeveralAmend(this)">
            <input type="hidden" name="landLevelContent" value='{landLevelContent}'>
        </td>
        <td>
            <input class="btn btn-warning" type="button" value="X"
                   onclick="estateCommon.landLevelEmpty(this)">
        </td>
    </tr>
</script>
<script type="application/javascript">
    function submit() {
        if (!$("#master").valid()) {
            return false;
        }
        var formData = formParams("master");
        formData.rewardRate = AssessCommon.pointToPercent(formData.rewardRate);
        formData.dateAmend = $("#dateAmend").text();
        formData.periodAmend = $("#periodAmend").text();
        formData.volumeFractionAmend = $("#volumeFractionAmend").text();
        formData.parcelPrice = $("#parcelPrice").text();
        formData.parcelBhouPrice = $("#parcelBhouPrice").text();
        formData.parcelTotalPrice = $("#parcelTotalPrice").text();
        formData.floorPremium = $("#floorPremium").text();
        formData.correctionDifference = $("#correctionDifference").text();
        formData.areaAndSeveralAmend = $("#areaAndSeveralAmend").val();

        var landLevelContent = [];
        $("#areaAndSeveralAmendForm").find("input[name='landLevelContent']").each(function (i, n) {
            var group = $(n).closest(".group");
            var dataLandLevelAchievement = group.find("input[name='dataLandLevelAchievement']").val();
            var landFactorTotalScore = group.find("input[name='landFactorTotalScore']").val();
            var obj = JSON.parse($(n).val());
            var dataObject = [];
            obj.forEach(function (value, index) {
                if (value.id == dataLandLevelAchievement) {
                    value.modelStr = "update";
                    value.achievement = landFactorTotalScore;
                } else {
                    delete value.modelStr;
                }
                dataObject.push(value);
            });
            landLevelContent.push(dataObject);
        });
        if (landLevelContent.length >= 1) {
            formData.landLevelContent = JSON.stringify(landLevelContent);
        }else{
            formData.landLevelContent ="";
        }

        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(formData));
        }
        else {
            submitToServer(JSON.stringify(formData));
        }
    }

    $(function () {
        //土地指数表
        getLandIndexId();
        //容积率修正系数表
        getVolumetricRateId();
        //因素条件说明及修正系数
        getLevelDetailId()
        if ('${master.areaAndSeveralAmend}') {
            $("#areaAndSeveralAmend").val('${master.areaAndSeveralAmend}');
        } else {
            $("#areaAndSeveralAmend").val('${landFactorTotalScore}');
        }

        getPeriodAmend();
    });

    //获取报酬率
    function getRewardRate(_this) {
        var group = $(_this).closest('.input-group');
        rewardRateFunc.calculation(group.find('[name=rewardRateId]').val(), function (data) {
            if (data) {
                group.find('[name=rewardRateId]').val(data.id);
                var element = group.find(':text');
                element.val(data.resultValue);
                AssessCommon.elementParsePoint(group.find('[name=rewardRate]').val(data.resultValue));
                //获取年期修正系数
                getPeriodAmend();
            }
        })
    }

    //获取委估宗地单价（元/㎡）
    function getParcelPrice() {
        //基准地价E4
        var standardPremium = parseFloat($("#standardPremium").val());
        //期日修正系数E5
        var dateAmend = parseFloat($("#dateAmend").text());
        //年期修正系数E6
        var periodAmend = parseFloat($("#periodAmend").text());
        //容积率修正E10
        var volumeFractionAmend = parseFloat($("#volumeFractionAmend").text());
        //区域及个别修正系数E11
        var areaAndSeveralAmend = parseFloat($("#areaAndSeveralAmend").val());
        //开发程度修正E12
        var developCorrect = parseFloat($("#developCorrect").val());
        //委估宗地面积E15
        var evaluationArea = parseFloat($("#evaluationArea").val());
        //委估宗地面积G17
        var volumetricRate = parseFloat($("#volumetricRate").val());

        if (standardPremium && dateAmend && periodAmend && volumeFractionAmend && areaAndSeveralAmend && developCorrect) {
            var money = standardPremium * dateAmend * periodAmend * volumeFractionAmend * (1 + areaAndSeveralAmend) + developCorrect;
            if (money) {
                //宗地单价
                $("#parcelPrice").text(getSomePlaces(money, 2));
                //宗地亩价
                $("#parcelBhouPrice").text(getBhouPrice(money, 2));
                //宗地总价
                if (evaluationArea) {
                    $("#parcelTotalPrice").text(getSomePlaces(evaluationArea * money / 10000, 2));
                }
                //楼面地价
                if (volumetricRate) {
                    $("#floorPremium").text(getSomePlaces(money / volumetricRate, 2));
                }
                //修正差额
                $("#correctionDifference").text(getSomePlaces((money / standardPremium - 1) * 100, 2) + "%");
            }
        }
    }

    //获取一亩的价
    function getBhouPrice(num, v) {
        return getSomePlaces(num * AssessCommon.BHOU / 10000, v);
    }

    //v取几位小数
    function getSomePlaces(num, v) {
        var vv = Math.pow(10, v);
        return Math.round(num * vv) / vv;
    }

    //获取年期修正系数
    function getPeriodAmend() {
        //E7
        var rewardRate = parseFloat(AssessCommon.percentToPoint($("#rewardRate").val()));
        //E9
        var landSurplusYear = parseFloat($("#landSurplusYear").val());
        //E8
        var legalAge = parseFloat($("#legalAge").val());
        //(1-1/(1+E7)^E9)/(1-1/(1+E7)^E8)
        if (rewardRate && landSurplusYear && legalAge) {
            var temp = 1 / Math.pow(1 + parseFloat(rewardRate), landSurplusYear);

            var temp2 = 1 / Math.pow(1 + parseFloat(rewardRate), legalAge);
            var result = (1 - temp) / (1 - temp2);

            $("#periodAmend").text(getSomePlaces(result, 4))
        }
        //获取委估宗地单价（元/㎡）
        getParcelPrice();
    }

    //土地指数表
    function getLandIndexId() {
        $.ajax({
            url: "${pageContext.request.contextPath}/baseLandPrice/getLandIndexId",
            type: "get",
            dataType: "json",
            data: {judgeObjectId: '${projectPlanDetails.judgeObjectId}'},
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        showLandIndexDetailList(result.data);
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })

    }

    function showLandIndexDetailList(landIndexId) {
        var cols = [];
        cols.push({
            field: 'startDate', title: '开始月份', formatter: function (value, row, index) {
                return formatDate(value);
            }
        });
        cols.push({
            field: 'endDate', title: '结束月份', formatter: function (value, row, index) {
                return formatDate(value);
            }
        });
        cols.push({field: 'indexNumber', title: '指数'});
        $("#indexDetailTable").bootstrapTable('destroy');
        TableInit("indexDetailTable", "${pageContext.request.contextPath}/dataHousePriceIndexDetail/getBootstrapTableVo", cols, {
            housePriceId: landIndexId
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

    //容积率修正系数表
    function getVolumetricRateId() {
        $.ajax({
            url: "${pageContext.request.contextPath}/baseLandPrice/getVolumetricRateId",
            type: "get",
            dataType: "json",
            data: {judgeObjectId: '${projectPlanDetails.judgeObjectId}'},
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        showVolumetricRateDetailList(result.data);
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })

    }

    function showVolumetricRateDetailList(volumeRatioId) {
        var cols = [];
        cols.push({field: 'plotRatio', title: '容积率'});
        cols.push({field: 'correctionFactor', title: '修正系数'});
        $("#volumetricRateDetailTable").bootstrapTable('destroy');
        TableInit("volumetricRateDetailTable", "${pageContext.request.contextPath}/dataAllocationCorrectionCoefficientVolumeRatioDetail/getBootstrapTableVo", cols, {
            allocationVolumeRatioId: volumeRatioId
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };


    //因素条件说明及修正系数
    function getLevelDetailId() {
        $.ajax({
            url: "${pageContext.request.contextPath}/baseLandPrice/getLevelDetailId",
            type: "get",
            dataType: "json",
            data: {judgeObjectId: '${projectPlanDetails.judgeObjectId}'},
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        if ('${apply}' == 'apply') {
                            //因素条件说明及修正系数
                            getLandLevelTabContent(result.data);
                        } else {
                            //因素条件说明及修正系数
                            landLevelLoadHtml2('${master.landLevelContent}');
                        }
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })

    }

    function getLandLevelTabContent(levelDetailId) {
        $.ajax({
            url: getContextPath() + "/dataLandDetailAchievement/landLevelFilter",
            type: "get",
            data: {levelDetailId: levelDetailId},
            success: function (result) {
                landLevelLoadHtml(result.data);
            }
        })
    }

    function landLevelLoadHtml(data) {
        if (jQuery.isEmptyObject(data)) {
            return false;
        }
        var target = $("#landLevelTabContent");
        target.empty();
        target.parent().show();

        //由于js来筛选 有大量json 解析或者字符串化 影响代码阅读度，因此改为了后台直接处理,第一次的时候有2此筛选分类这样确实代码可读性差
        data.forEach(function (dataA, indexM) {
            $.each(dataA, function (i, obj) {
                var item = estateCommon.getLandLevelFilter(obj);
                var landLevelBodyHtml = $("#landLevelTabContentBody").html();
                if (landLevelBodyHtml) {
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{dataLandLevelAchievement}/g, item.id);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landFactorTotalScore}/g, item.achievement);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelCategoryName}/g, item.category);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelTypeName}/g, item.typeName);
                    var text = "";
                    $.each(obj, function (i, n) {
                        text += "等级:" + n.gradeName + "，说明:" + n.reamark + "； \r";
                    });
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{reamark}/g, text);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelContent}/g, JSON.stringify(obj));
                    AssessCommon.loadAsyncDataDicByKey(AssessDicKey.programmeMarketCostapproachGrade, item.grade, function (html, data) {
                        landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelGradeHTML}/g, html);
                        target.append(landLevelBodyHtml);
                    }, false);
                }
            });

            if (indexM == 0) {
                target.find("tr").first().find("td").first().attr("rowspan", dataA.length);
                target.find("tr").each(function (i, n) {
                    if (i != 0) {
                        $(n).find("td").first().remove();
                    }
                });
            }
            if (indexM == 1) {
                var length = data[0].length;
                target.find("tr").eq(length).find("td").first().attr("rowspan", dataA.length);
                target.find("tr").each(function (i, n) {
                    if (i > length) {
                        $(n).find("td").first().remove();
                    }
                });
            }
        });
    };

    function landLevelLoadHtml2(jsonStr) {
        var jsonParse = JSON.parse(jsonStr);
        var data = estateCommon.landLevelFilter(jsonParse);
        if (jQuery.isEmptyObject(data)) {
            return false;
        }
        var target = $("#landLevelTabContent");
        target.empty();
        target.parent().show();

        //由于js来筛选 有大量json 解析或者字符串化 影响代码阅读度，因此改为了后台直接处理,第一次的时候有2此筛选分类这样确实代码可读性差
        data.forEach(function (dataA, indexM) {
            $.each(dataA, function (i, obj) {
                var item;
                obj.forEach(function (value, index) {
                    if (value.modelStr == "update") {
                        item = value;
                    }
                });
                var landLevelBodyHtml = $("#landLevelTabContentBody").html();
                if (landLevelBodyHtml) {
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{dataLandLevelAchievement}/g, item.id);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landFactorTotalScore}/g, item.achievement);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelCategoryName}/g, item.category);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelTypeName}/g, item.typeName);
                    var text = "";
                    $.each(obj, function (i, n) {
                        text += "等级:" + n.gradeName + "，说明:" + n.reamark + "； \r";
                    });
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{reamark}/g, text);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelContent}/g, JSON.stringify(obj));
                    AssessCommon.loadAsyncDataDicByKey(AssessDicKey.programmeMarketCostapproachGrade, item.grade, function (html, data) {
                        landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelGradeHTML}/g, html);
                        target.append(landLevelBodyHtml);
                    }, false);
                }
            });

            if (indexM == 0) {
                target.find("tr").first().find("td").first().attr("rowspan", dataA.length);
                target.find("tr").each(function (i, n) {
                    if (i != 0) {
                        $(n).find("td").first().remove();
                    }
                });
            }
            if (indexM == 1) {
                var length = data[0].length;
                target.find("tr").eq(length).find("td").first().attr("rowspan", dataA.length);
                target.find("tr").each(function (i, n) {
                    if (i > length) {
                        $(n).find("td").first().remove();
                    }
                });
            }
        });
    };


    //计算区域及个别修正系数
    function getAreaAndSeveralAmend(that) {
        var tbody = $(that).closest('tbody');
        var landFactorTotalScore = 0;
        tbody.find('input[name^=landFactorTotalScore]').each(function () {
            landFactorTotalScore += Number($(this).val());
        })
        $("#areaAndSeveralAmend").val(getSomePlaces(landFactorTotalScore, 2));
        //获取委估宗地单价（元/㎡）
        getParcelPrice();
        getPeriodAmend();
    }
</script>

</html>

