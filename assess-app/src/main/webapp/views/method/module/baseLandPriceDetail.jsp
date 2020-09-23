<%--
  Created by IntelliJ IDEA.
  User: zch
  Date: 2020-9-16
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                                <label class="form-control input-full"> ${master.dateAmend}</label>
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
                                <label class="form-control input-full"> ${master.volumeFractionAmend}</label>
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
                            <label class="col-sm-1 control-label">区域及个别修正系数</label>
                            <div class="col-sm-3">
                                <label class="form-control input-full"> <fmt:formatNumber value="${master.areaAndSeveralAmend}" type="percent" maxFractionDigits="4"/></label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <table class="table table-bordered" id="landLevelTableList">
                            <thead>
                            <tr>
                                <th width="20%">土地级别类型类别</th>
                                <th width="30%">土地级别等级</th>
                                <th width="30%">说明</th>
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
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                基准地价(元/㎡)
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${master.standardPremium}</label>
                            </div>
                            <label class="col-sm-1 control-label">
                                说明
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${master.standardPremiumRemark}</label>
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
                                <label class="form-control input-full">
                                    <c:choose>
                                        <c:when test="${(master.rewardRate).matches('[0-9]+') }">    <!--如果 -->
                                            <fmt:formatNumber value="${master.rewardRate}" type="percent" maxFractionDigits="4"/>
                                        </c:when>
                                        <c:otherwise>  <!--否则 -->
                                            ${master.rewardRate}
                                        </c:otherwise>
                                    </c:choose>
                                </label>
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
                                <label class="form-control input-full">${master.legalAge}</label>
                            </div>
                            <label class="col-sm-1 control-label">
                                说明
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${master.legalAgeRemark}</label>
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
                                <label class="form-control input-full">${master.landSurplusYear}</label>
                            </div>
                            <label class="col-sm-1 control-label">
                                说明
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${master.landSurplusYearRemark}</label>
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
                                <label class="form-control input-full">${master.developCorrect}</label>
                            </div>
                            <label class="col-sm-1 control-label">
                                说明
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${master.developCorrectRemark}</label>
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
                                <label class="form-control input-full">${master.evaluationArea}</label>
                            </div>
                            <label class="col-sm-1 control-label">
                                说明
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${master.evaluationAreaRemark}</label>
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
                                <label class="form-control input-full">${master.volumetricRate}</label>
                            </div>
                            <label class="col-sm-1 control-label">
                                说明
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${master.volumetricRateRemark}</label>
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
                                <td id="periodAmend">${master.periodAmend}</td>
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
            </form>
        </div>
    </div>
</div>

<script type="application/javascript">


    $(function () {
        //土地指数表
        getLandIndexId();
        //容积率修正系数表
        showVolumetricRateDetailList('${hasVolumeFractionAmendId}');

        loadProjectLandAchievementGroup() ;
    });

    /**
     * 土地因素  测算调用方法
     */
    function loadProjectLandAchievementGroup() {
        if ('${basicEstateLandCategoryInfo}') {
            var query = {
                projectId: '${judgeObject.projectId}',
                dataTableId: '${basicEstateLandCategoryInfo.id}',
                dataTableName: AssessDBKey.BasicEstateLandCategoryInfo,
                levelDetailId:'${basicEstateLandCategoryInfo.landLevel}'
            };
            var table = $("#landLevelTableList") ;
            landAchievementGroup.getInitProjectLandAchievementGroupData(query.projectId ,query.dataTableId ,query.dataTableName ,function (dataAll) {
                if (dataAll && dataAll.length > 0){
                    landAchievementGroup.detailMethodLoadHtml(dataAll ,table) ;
                }
            });
        }
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
                    if (row.bisBase) {
                        str += '<span class="label label-success">基期</span>';
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



</script>
