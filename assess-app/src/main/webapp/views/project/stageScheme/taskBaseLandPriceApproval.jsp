<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3 style="word-break: break-all">
                        ${judgeObject.name}
                        <small>(${judgeObject.evaluationArea}㎡)</small>
                    </h3>
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
                                        <label class="col-sm-1 control-label">区域及个别修正系数</label>
                                        <div class="col-sm-3">
                                            <div class="input-group">
                                                <input type="text" readonly="readonly" class="form-control"
                                                       id="areaAndSeveralAmend">
                                                <span class="input-group-btn">
                            <button type="button" class="btn btn-default docs-tooltip"
                                    onclick="getLandLevelTabContent();"
                                    data-toggle="tooltip" data-original-title="土地因素">
                        <i class="fa fa-magic"></i>
                        </button>
                </span>
                                            </div>
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
                            <h3>基准地价因素</h3>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <form class="form-horizontal" id="master">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            基准地价(元/㎡)
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control">${master.standardPremium}</label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            说明
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control">${master.standardPremiumRemark}</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-1 control-label">
                                        土地还原利率
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control">${master.rewardRate}</label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            法定年限
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control">${master.legalAge}</label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            说明
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control">${master.legalAgeRemark}</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            剩余使用年限
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control">${master.landSurplusYear}</label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            说明
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control">${master.landSurplusYearRemark}</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            开发程度修正
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control">${master.developCorrect}</label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            说明
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control">${master.developCorrectRemark}</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            委估宗地面积(㎡)
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control">${master.evaluationArea}</label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            说明
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control">${master.evaluationAreaRemark}</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            委估对象容积率
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control">${master.volumetricRate}</label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            说明
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control">${master.volumetricRateRemark}</label>
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
                                            <td id="dateAmend">${master.dateAmend}</td>
                                            <td>年期修正系数</td>
                                            <td id="periodAmend">${master.periodAmend}</td>
                                        </tr>
                                        <tr>
                                            <td>容积率修正</td>
                                            <td id="volumeFractionAmend">${master.volumeFractionAmend}</td>
                                            <td>委估宗地单价（元/㎡）</td>
                                            <td id="parcelPrice">${master.parcelPrice}</td>
                                        </tr>
                                        <tr>
                                            <td>委估宗地单价（万元/亩）</td>
                                            <td id="parcelBhouPrice">${master.parcelBhouPrice}</td>
                                            <td>委估宗地总价（万元）</td>
                                            <td id="parcelTotalPrice">${master.parcelTotalPrice}</td>
                                        </tr>
                                        <tr>
                                            <td>楼面地价</td>
                                            <td id="floorPremium">${master.floorPremium}</td>
                                            <td>修正差额(%)</td>
                                            <td id="correctionDifference">${master.correctionDifference}</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_approval.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.estate.js?v=${assessVersion}"></script>
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
            {gradeName}
        </td>
        <td>
            <label name="reamark" class="form-control">{reamark}</label>
        </td>
        <td>
            <label name="landFactorTotalScore" class="form-control">{landFactorTotalScore}</label>
        </td>
    </tr>
</script>
<div id="detailAchievementModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true" data-height="500">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土地因素</h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="landLevelContentFrm">
                    <div class="form-group">
                        <div class="x-valid">
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <div id="_select_land_level_file"></div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <table class="table table-striped table-bordered">
                                <thead>
                                <tr>
                                    <th width="10%">土地级别类别</th>
                                    <th width="10%">土地级别类型</th>
                                    <th width="10%">土地级别等级</th>
                                    <th width="20%">说明</th>
                                    <th width="10%">分值</th>
                                </tr>
                                </thead>
                                <tbody id="landLevelTabContent">

                                </tbody>
                            </table>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>

<script type="application/javascript">
    function saveform() {
        saveApprovalform("");
    }

    $(function () {
        //土地指数表
        getLandIndexId();
        //容积率修正系数表
        showVolumetricRateDetailList('${landLevelDetailId}');

        $("#areaAndSeveralAmend").val(AssessCommon.pointToPercent('${master.areaAndSeveralAmend}'));
    });

    //v取几位小数
    function getSomePlaces(num, v) {
        var vv = Math.pow(10, v);
        return Math.round(num * vv) / vv;
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
    function showVolumetricRateDetailList(landLevelDetailId) {
        var cols = [];
        cols.push({field: 'plotRatio', title: '容积率'});
        cols.push({field: 'correctionFactor', title: '修正系数'});
        $("#volumetricRateDetailTable").bootstrapTable('destroy');
        TableInit("volumetricRateDetailTable", "${pageContext.request.contextPath}/dataLandLevelDetailVolume/getBootstrapTableVo", cols, {
            allocationVolumeRatioId: landLevelDetailId
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
    function getLandLevelTabContent() {
        FileUtils.getFileShows({
            target: "select_land_level_file",
            formData: {
                tableName: AssessDBKey.DataLandLevel,
                tableId: '${landLevelId}'
            },
            deleteFlag: false
        })

        var jsonContent = JSON.parse('${master.landLevelContent}');
        var data = estateCommon.landLevelFilter(jsonContent);
        if (jQuery.isEmptyObject(data)) {
            return false;
        }
        $("#detailAchievementModal").modal();
        var target = $("#landLevelTabContent");
        target.empty();

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
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landFactorTotalScore}/g, AssessCommon.pointToPercent(item.achievement));
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelCategoryName}/g, item.category);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelTypeName}/g, item.typeName);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{gradeName}/g, item.gradeName);
                    var text = "";
                    $.each(obj, function (i, n) {
                        text += "等级:" + n.gradeName + "，说明:" + n.reamark + "； \r";
                    });
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{reamark}/g, text);
                    target.append(landLevelBodyHtml);
                }
            });

        });
    };
</script>
</body>
</html>

