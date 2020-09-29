<%--
  Created by IntelliJ IDEA.
  User: zch
  Date: 2020-9-16
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        <div class="card-body">
            <form class="form-horizontal" id="master">
                <div class="col-md-12">
                    <div class="card full-height">
                        <div class="card-header collapse-link">
                            <div class="card-head-row">
                                <div class="card-title">
                                    年平均产值调查
                                </div>
                                <div class="card-tools">
                                    <button class="btn  btn-link btn-primary btn-xs"><span
                                            class="fa fa-angle-down"></span>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 control-label">
                                            农用地总面积(亩)
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control input-full">${master.farmlandArea}</label>
                                        </div>
                                        <label class="col-sm-1 control-label">
                                            耕地面积(亩)
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control input-full">${master.ploughArea}</label>
                                        </div>
                                        <label class="col-sm-1 control-label">
                                            人口数(人)
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control input-full">${master.populationNumber}</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">宗地外设定</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                            <label class="form-control input-full">${master.parcelSettingOuterName}</label>
                                        </div>
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">宗地内设定</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control input-full">${master.parcelSettingInnerName}</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <table class="table table-bordered" id="tb_research_list">
                                        <!-- cerare document add ajax data-->
                                    </table>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

                <div class="col-md-12">
                    <div class="card full-height">
                        <div class="card-header collapse-link">
                            <div class="card-head-row">
                                <div class="card-title">
                                    土地取得费及相关税费
                                </div>
                                <div class="card-tools">
                                    <button class="btn  btn-link btn-primary btn-xs"><span
                                            class="fa fa-angle-down"></span>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <form class="form-horizontal" id="approachTaxesForm">
                                <table class="table" id="tb_taxesList">
                                    <thead>
                                    <tr>
                                        <th>类型</th>
                                        <th>耕地标准</th>
                                        <th>非耕地标准</th>
                                        <th>价格(元/亩)</th>
                                        <th>备注</th>
                                    </tr>
                                    </thead>
                                    <tbody id="tbodyContent">
                                    <c:forEach items="${taxesVos}" var="approachTaxe" varStatus="s">
                                        <tr id="content${approachTaxe.id}">


                                        </tr>
                                        <script type="text/javascript">
                                            $(function () {
                                                var html = uploadTaxeHtml("${approachTaxe.id}", "${approachTaxe.typeKey}", "${approachTaxe.typeName}", "${approachTaxe.standardFirst}", "${approachTaxe.standardSecond}", "${approachTaxe.price}", "${approachTaxe.remark}");
                                                $("#content${approachTaxe.id}").append(html);
                                            })
                                        </script>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </form>
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
                                                <label class="form-control input-full"><fmt:formatNumber value="${master.plotRatioElementAmend}" type="percent" maxFractionDigits="4"/></label>
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
                                            <tbody>
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
                                    土地开发因素
                                </div>
                                <div class="card-tools">
                                    <button class="btn  btn-link btn-primary btn-xs"><span
                                            class="fa fa-angle-down"></span>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <form class="form-horizontal" id="master_other">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                宗地外六通费用(元/㎡)
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full">${master.circulationExpense}</label>
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                说明
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full">${master.circulationExpenseRemark}</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                场平费用(元/㎡)
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full">${master.flatExpense}</label>
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                说明
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full">${master.flatExpenseRemark}</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%" color="#6f5499" size="10">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                计息周期
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full">${master.machineCycle}</label>
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                说明
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full">${master.machineCycleRemark}</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                计息利率
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full"><fmt:formatNumber value="${master.calculatedInterest}" type="percent" maxFractionDigits="4"/></label>
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                说明
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full">${master.calculatedInterestRemark}</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%" color="#6f5499" size="10">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                开发利润率
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full"><fmt:formatNumber value="${master.profitMargin}" type="percent" maxFractionDigits="4"/></label>
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                说明
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full">${master.profitMarginRemark}</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                土地增值率
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full"><fmt:formatNumber value="${master.incrementalBenefit}" type="percent" maxFractionDigits="4"/></label>
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                说明
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full">${master.incrementalBenefitRemark}</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                容积率调整
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full"> <fmt:formatNumber value="${master.plotRatioAdjust}" type="percent" maxFractionDigits="4"/></label>
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                说明
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full">${master.plotRatioAdjustRemark}</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%" color="#6f5499" size="10">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                剩余年限
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full">${master.landRemainingYear}</label>
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                说明
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full">${master.landRemainingYearRemark}</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                还原利率
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full">
                                                    <c:choose>
                                                        <c:when test="${(master.rewardRate).contains('%') }">    <!--如果 -->
                                                            ${master.rewardRate}
                                                        </c:when>
                                                        <c:otherwise>  <!--否则 -->
                                                            <fmt:formatNumber value="${master.rewardRate}" type="percent" maxFractionDigits="4"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </label>
                                            </div>

                                            <label class="col-sm-1 control-label">
                                                说明
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full">${master.rewardRateRemark}</label>
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
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <table class="table table-bordered">
                                        <tbody>
                                        <tr>
                                            <td> 耕地比例</td>
                                            <td id="ploughArearatio"> <fmt:formatNumber value="${master.ploughArearatio}" type="percent" maxFractionDigits="4"/></td>
                                            <td> 非耕地比例</td>
                                            <td id="noPloughArearatio"> <fmt:formatNumber value="${master.noPloughArearatio}" type="percent" maxFractionDigits="4"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td> 土地取得费及相关税费(元/亩)</td>
                                            <td id="landAcquisitionBhou">${master.landAcquisitionBhou}</td>
                                            <td> 土地取得费及相关税费(元/㎡)</td>
                                            <td id="landAcquisitionUnit"> ${master.landAcquisitionUnit}</td>
                                        </tr>
                                        <tr>
                                            <td> 土地开发费(元/亩)</td>
                                            <td id="landProductionBhou">
                                                ${master.landProductionBhou}
                                            </td>
                                            <td> 土地开发费(元/㎡)</td>
                                            <td id="landProductionUnit">
                                                ${master.landProductionUnit}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td> 土地开发利息(元/亩)</td>
                                            <td id="landProductionInterestBhou">
                                                ${master.landProductionInterestBhou}
                                            </td>
                                            <td> 土地开发利息(元/㎡)</td>
                                            <td id="landProductionInterestUnit">
                                                ${master.landProductionInterestUnit}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td> 土地开发利润(元/亩)</td>
                                            <td id="landProductionProfitBhou">
                                                ${master.landProductionProfitBhou}
                                            </td>
                                            <td> 土地开发利润(元/㎡)</td>
                                            <td id="landProductionProfitUnit">
                                                ${master.landProductionProfitUnit}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td> 土地取得成本（元/亩）</td>
                                            <td id="landCostPriceBhou">
                                                ${master.landCostPriceBhou}
                                            </td>
                                            <td> 土地取得成本（元/㎡）</td>
                                            <td id="landCostPriceUnit">
                                                ${master.landCostPriceUnit}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td> 无限年期土地使用权价格(元/亩)</td>
                                            <td id="landUseBhou">
                                                ${master.landUseBhou}
                                            </td>
                                            <td> 无限年期土地使用权价格(元/㎡)</td>
                                            <td >
                                                ${master.landUsePrice}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td> 年期修正（万元/亩）</td>
                                            <td id="priceCorrectionBhou">
                                                ${master.priceCorrectionBhou}
                                            </td>
                                            <td> 年期修正（元/㎡）</td>
                                            <td id="priceCorrectionUnit">
                                                ${master.priceCorrectionUnit}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td> 宗地个别因素修正(万元/亩)</td>
                                            <td id="plotRatioElementAmendBhou">
                                                ${master.plotRatioElementAmendBhou}
                                            </td>
                                            <td> 宗地个别因素修正(元/㎡)</td>
                                            <td id="plotRatioElementAmendUnit">
                                                ${master.plotRatioElementAmendUnit}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td> 容积率修正(万元/亩)</td>
                                            <td id="plotRatioAdjustBhou">
                                                ${master.plotRatioAdjustBhou}
                                            </td>
                                            <td> 容积率修正(元/㎡)</td>
                                            <td id="plotRatioAdjustUnit">
                                                ${master.plotRatioAdjustUnit}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td> 委估宗地价格(万元/亩)</td>
                                            <td id="parcelBhou">
                                                ${master.parcelBhou}
                                            </td>
                                            <td> 委估宗地价格(元/㎡)</td>
                                            <td id="parcelUnit">
                                                ${master.parcelUnit}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td> 年期修正系数</td>
                                            <td id="yearFixed">
                                                    <fmt:formatNumber value="${master.yearFixed}" type="percent" maxFractionDigits="4"/>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


<script type="text/javascript">
    $(function () {
        research.prototype.loadDataList(${master.id});
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
                dataTableName: AssessDBKey.BasicEstateLandCategoryInfo
            };
            var table = $("#landLevelTableList") ;
            landAchievementGroup.getInitProjectLandAchievementGroupData(query.projectId ,query.dataTableId ,query.dataTableName ,function (dataAll) {
                if (dataAll && dataAll.length > 0){
                    landAchievementGroup.detailMethodLoadHtml(dataAll ,table) ;
                }
            });
        }
    }

    var research = function () {

    };
    research.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_research_list";
            return data;
        },
        loadDataList: function (masterId) {
            var cols = [];
            cols.push({field: 'yearDescribe', title: '年份描述'});
            cols.push({field: 'averageProduction', title: '平均产值(元)'});
            $("#" + research.prototype.config().table).bootstrapTable('destroy');
            TableInit(research.prototype.config().table, "${pageContext.request.contextPath}/costApproach/getMdCostApproachItemList", cols, {
                masterId: masterId
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        }
    };

    <%--税率配置--%>
    function uploadTaxeHtml(id, typeKey, typeName, standardFirst, standardSecond, price,remark) {
        var html = '';
        html += '<td>' + typeName + '</td>';
        switch (typeKey) {
            case "data.land.approximation.method.land.compensate": {
            }
            case "data.land.approximation.method.placement.compensate": {
            }
            case "data.land.approximation.method.house.compensate": {
            }
            case "data.land.approximation.method.removal.award": {
            }
            case"data.land.approximation.method.vegetable.build": {
            }
            case"data.land.approximation.method.plough.reclaim": {
            }
            case"data.land.approximation.method.occupation.land": {
                html += '<td>' + standardFirst + '</td>';
                html += '<td>' + standardSecond + '</td>';
                html += '<td>' + price + '</td>';
                html += '<td>' + remark + '</td>';
                break;
            }
            case"data.land.approximation.method.crops.compensate": {
            }
            case "data.land.approximation.method.land.manager": {
            }
            case "data.land.approximation.method.cannot.foresee": {
            }
            case "data.land.approximation.method.land.acquisition": {
                html += '<td>' + AssessCommon.pointToPercent(standardFirst) + '</td>';
                html += '<td>';
                html += '/';
                html += '</td>';
                html += '<td>' + price + '</td>';
                html += '<td>' + remark + '</td>';
                break;
            }
            default:
                html += '<td>';
                html += '/';
                html += '</td>';
                html += '<td>';
                html += '/';
                html += '</td>';
                html += '<td>' + price + '</td>';
                html += '<td>' + remark + '</td>';

        }

        return html;

    }
</script>
