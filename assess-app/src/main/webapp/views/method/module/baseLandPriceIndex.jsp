<%--
  Created by IntelliJ IDEA.
  User: zch
  Date: 2020-9-16
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/views/project/tool/rewardRate.jsp"></jsp:include>
<%@include file="/views/method/module/projectLandAchievementGroup.jsp" %>

<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    ${judgeObject.name}
                    <small>(${judgeObject.evaluationArea}㎡)</small>
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>

    </div>
</div>

<%--地价指数--%>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    地价指数
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-up"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body" style="display:none;">
            <form class="form-horizontal">
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">期日修正系数</label>
                            <div class="col-sm-3">
                                <input type="text"
                                       class="form-control input-full" onblur="calculationNumeric(this);"
                                       name="dateAmend"
                                       value="${dateAmend != null?dateAmend:master.dateAmend}"
                                       id="dateAmend">
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <table class="table table-bordered" id="indexDetailTable"></table>
        </div>
    </div>
</div>


<div class="col-md-12">
    <%--容积率系数--%>
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    容积率系数
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-up"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body" style="display: none;">
            <form class="form-horizontal">
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">容积率修正系数</label>
                            <div class="col-sm-3">
                                <input type="text"
                                       class="form-control input-full" onblur="calculationNumeric(this);"
                                       name="volumeFractionAmend"
                                       value="${master.volumeFractionAmend == null?volumeFractionAmend:master.volumeFractionAmend}"
                                       id="volumeFractionAmend">
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <table class="table table-bordered" id="volumetricRateDetailTable"></table>
        </div>
    </div>
</div>


<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    因素条件说明及修正系数
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form class="form-horizontal" id="areaAndSeveralAmendForm">
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">区域及个别修正系数</label>
                            <div class="col-sm-3">
                                <div class="input-group">
                                    <input type="text"
                                           class="form-control x-percent" onblur="calculationNumeric(this);"
                                           name="areaAndSeveralAmend" value="${master.areaAndSeveralAmend}"
                                           data-value="${master.areaAndSeveralAmend}"
                                           id="areaAndSeveralAmend">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <table class="table table-bordered" id="landLevelTableList">
                            <thead>
                            <tr>
                                <th width="50%">土地级别类型类别</th>
                                <th width="30%">土地级别等级</th>
                                <th width="20%">分值</th>
                            </tr>
                            </thead>
                            <tbody >

                            </tbody>
                        </table>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    基准地价因素
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form class="form-horizontal" id="master">
                <input type="hidden" name="id" value="${master.id}">
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                基准地价(元/㎡)
                            </label>
                            <div class="col-sm-3">
                                <input type="text" placeholder="农用地总面积"
                                       class="form-control input-full"
                                       id="standardPremium" name="standardPremium"
                                       required
                                       onblur="calculationNumeric(this);"
                                       value="${master.standardPremium}"
                                       data-rule-number="true">
                            </div>
                            <label class="col-sm-1 control-label">
                                说明
                            </label>
                            <div class="col-sm-3">
                                <input type="text"
                                       value="${master.standardPremiumRemark}"
                                       class="form-control input-full"
                                       name="standardPremiumRemark">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                土地还原利率
                            </label>
                            <div class="col-sm-3">
                                <div class="input-group">
                                    <input type="text" required
                                           class="form-control x-percent"
                                           id="rewardRate" onblur="calculationNumeric(this);"
                                           name="rewardRate" placeholder="还原率"
                                           value="${master.rewardRate}" data-value="${master.rewardRate}">

                                    <div class="input-group-prepend">
                                        <input type="hidden" name="rewardRateId"
                                               value="${master.rewardRateId}">
                                        <button class="btn btn-info btn-sm "
                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                type="button"
                                                onclick="getRewardRate(this);">还原率测算
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                法定年限
                            </label>
                            <div class="col-sm-3">
                                <input type="text" placeholder="法定年限"
                                       class="form-control input-full"
                                       id="legalAge"
                                       name="legalAge" required
                                       onblur="calculationNumeric(this);"
                                       value="${master.legalAge}"
                                       data-rule-number="true">
                            </div>
                            <label class="col-sm-1 control-label">
                                说明
                            </label>
                            <div class="col-sm-3">
                                <input type="text" value="${master.legalAgeRemark}"
                                       class="form-control input-full"
                                       name="legalAgeRemark">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                剩余使用年限
                            </label>
                            <div class="col-sm-3">
                                <input type="text" placeholder="剩余使用年限"
                                       class="form-control input-full"
                                       id="landSurplusYear"
                                       name="landSurplusYear" required
                                       onblur="calculationNumeric(this);"
                                       value="${master.landSurplusYear}"
                                       data-rule-number="true">
                            </div>
                            <label class="col-sm-1 control-label">
                                说明
                            </label>
                            <div class="col-sm-3">
                                <input type="text"
                                       value="${master.landSurplusYearRemark}"
                                       class="form-control input-full"
                                       name="landSurplusYearRemark">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                开发程度修正
                            </label>
                            <div class="col-sm-3">
                                <input type="text" placeholder="开发程度修正"
                                       class="form-control input-full"
                                       id="developCorrect"
                                       name="developCorrect" required
                                       onblur="calculationNumeric(this);"
                                       value="${master.developCorrect}"
                                       data-rule-number="true">
                            </div>
                            <label class="col-sm-1 control-label">
                                说明
                            </label>
                            <div class="col-sm-3">
                                <input type="text"
                                       value="${master.developCorrectRemark}"
                                       class="form-control input-full"
                                       name="developCorrectRemark">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                委估宗地面积(㎡)
                            </label>
                            <div class="col-sm-3">
                                <input type="text" placeholder="委估宗地面积"
                                       class="form-control input-full"
                                       id="evaluationArea"
                                       name="evaluationArea" required
                                       onblur="calculationNumeric(this);"
                                       value="${master.evaluationArea}"
                                       data-rule-number="true">
                            </div>
                            <label class="col-sm-1 control-label">
                                说明
                            </label>
                            <div class="col-sm-3">
                                <input type="text"
                                       value="${master.evaluationAreaRemark}"
                                       class="form-control input-full"
                                       name="evaluationAreaRemark">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                委估对象容积率
                            </label>
                            <div class="col-sm-3">
                                <input type="text" placeholder="委估对象容积率"
                                       class="form-control input-full"
                                       id="volumetricRate"
                                       name="volumetricRate" required
                                       onblur="calculationNumeric(this);"
                                       value="${master.volumetricRate}"
                                       data-rule-number="true">
                            </div>
                            <label class="col-sm-1 control-label">
                                说明
                            </label>
                            <div class="col-sm-3">
                                <input type="text"
                                       value="${master.volumetricRateRemark}"
                                       class="form-control input-full"
                                       name="volumetricRateRemark">
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    测算结果
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form class="form-horizontal">
                <div class="row form-group">
                    <div class="col-md-12">
                        <table class="table table-bordered">
                            <tbody>
                            <tr>
                                <td>年期修正系数</td>
                                <td id="periodAmend">
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
            </form>
        </div>
    </div>
</div>



<script>

    /**
     * 土地因素  测算调用方法  触发事件后赋值
     */
    function initPlotRatioElementAmendValue(query ,dataAll ,table) {
        landAchievementGroup.applyMethodLoadHtml(dataAll ,table,function () {
            landAchievementGroup.countProjectLandAchievementGroupByDataTableIdAndDataTableNameAndProjectId(query.projectId ,query.dataTableId ,query.dataTableName ,function (item) {
                if (item){
                    $("#areaAndSeveralAmend").val(AssessCommon.pointToPercent(item)).trigger('blur');
                }else {
                    $("#areaAndSeveralAmend").val('').trigger('blur');
                }
            }) ;
        }) ;
    }

    /**
     * 土地因素  测算调用方法
     */
    function loadProjectLandAchievementGroup() {
        if ('${basicEstateLandCategoryInfo}') {
            var query = {
                projectId: '${judgeObject.projectId}',
                dataTableId: '${basicEstateLandCategoryInfo.id}',
                dataTableName: AssessDBKey.BasicEstateLandCategoryInfo,
                levelDetailId:'${basicEstateLandCategoryInfo.landLevel}',
                targetTableId:'${master.id}',
                targetTableName:AssessDBKey.MdBaseLandPrice
            };
            var table = $("#landLevelTableList") ;
            landAchievementGroup.getInitProjectLandAchievementGroupData(query.projectId ,query.dataTableId ,query.dataTableName ,function (dataAll) {
                if (!dataAll || dataAll.length == 0){
                    landAchievementGroup.initProjectLandAchievementGroup(query.levelDetailId,query.projectId ,query.dataTableId ,query.dataTableName ,function (data) {
                        initPlotRatioElementAmendValue(query,data,table) ;
                    }) ;
                }else {
                    initPlotRatioElementAmendValue(query,dataAll,table) ;
                }
            });
        }
    }

    //获取报酬率
    function getRewardRate(_this) {
        var group = $(_this).closest('.input-group');
        rewardRateFunc.calculation(group.find('[name=rewardRateId]').val(), function (data) {
            if (data) {
                group.find('[name=rewardRateId]').val(data.id);
                var element = group.find(':text');
                element.val(data.resultValue);
                AssessCommon.elementParsePoint(group.find('[name=rewardRate]').val(data.resultValue)).trigger('blur');
            }
        })
    }

    //土地指数表
    function getLandIndexId() {
        AssessCommon.ajaxServerMethod({
            judgeObjectId: '${master.judgeObjectId}'
        }, "/baseLandPrice/getLandPriceIndexByJudgeId", "get", function (data) {
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
            cols.push({
                field: 'indexNumber', title: '指数', formatter: function (value, row, index) {
                    var str = value;
                    var dateAmendIndex = '${dateAmendIndex}' ;
                    if (dateAmendIndex) {
                        if (dateAmendIndex == row.id){
                            str += '<span class="label label-success">基期</span>';
                        }
                    }
                    return str;
                }
            });
            if (!data) {
                console.log(data);
                return false;
            }
            $("#indexDetailTable").bootstrapTable('destroy');
            TableInit("indexDetailTable", "${pageContext.request.contextPath}/dataHousePriceIndexDetail/getBootstrapTableVo", cols, {
                housePriceId: data.id
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        });
    }


    //容积率修正系数表
    function showVolumetricRateDetailList(hasVolumeFractionAmendId) {
        var cols = [];
        cols.push({field: 'plotRatio', title: '容积率'});
        cols.push({field: 'correctionFactor', title: '修正系数'});
        $("#volumetricRateDetailTable").bootstrapTable('destroy');
        TableInit("volumetricRateDetailTable", "${pageContext.request.contextPath}/dataLandLevelDetailVolume/getBootstrapTableVo", cols, {
            levelDetailId: hasVolumeFractionAmendId
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };



    $(function () {
        //土地指数表
        getLandIndexId();
        //容积率修正系数表
        showVolumetricRateDetailList(${hasVolumeFractionAmendId});
        if (!'${master.standardPremium}') {
            $("#standardPremium").val(getSomePlaces(parseFloat('${standardPremium}'), 2));
        }
        if (!'${master.legalAge}') {
            $("#legalAge").val(getSomePlaces(parseFloat('${legalAge}'), 2));
        }
        if (!'${master.volumetricRate}') {
            $("#volumetricRate").val(getSomePlaces(parseFloat('${volumetricRate}'), 2));
        }
        loadProjectLandAchievementGroup() ;
        calculationNumeric();
    });

    //v取几位小数
    function getSomePlaces(num, v) {
        var vv = Math.pow(10, v);
        return Math.round(num * vv) / vv;
    }

</script>

<script>

    function calculationNumeric(that) {
        var formData = formParams("master");
        formData.dateAmend = $.trim($("#dateAmend").val()) ? $("#dateAmend").val() : '';
        formData.volumeFractionAmend = $.trim($("#volumeFractionAmend").val()) ? $("#volumeFractionAmend").val() : '';
        formData.areaAndSeveralAmend = AssessCommon.percentToPoint($("#areaAndSeveralAmend").val());
        $.ajax({
            url: getContextPath() + "/baseLandPrice/calculationNumeric",
            type: "post",
            data: {
                fomData: JSON.stringify(formData),
                dateAmend: formData.dateAmend,
                volumeFractionAmend: formData.volumeFractionAmend
            },
            success: function (result) {
                var data = result.data;
                if (data) {
                    console.log(data);
                    $("#correctionDifference").text(data.correctionDifference);
                    $("#periodAmend").text(data.periodAmend);
                    $("#parcelPrice").text(data.parcelPrice);
                    $("#parcelTotalPrice").text(data.parcelTotalPrice);
                    $("#parcelBhouPrice").text(data.parcelBhouPrice);
                    $("#floorPremium").text(data.floorPremium);
                }
            }
        })
    }
</script>