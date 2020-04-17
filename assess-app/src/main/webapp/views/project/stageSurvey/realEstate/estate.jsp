<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <title>信息填写</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <div class="page-inner">
                <div class="row">
                    <!-- 填写表单 start -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        信息填写
                                        <button type="button" class="btn btn-sm btn-primary"
                                                onclick="showHistoryModal();">历史记录
                                        </button>
                                        <button type="button" class="btn btn-sm btn-primary"
                                                onclick="showCaseQuoteModal();">引用案例
                                        </button>
                                        <button type="button" class="btn btn-sm btn-primary"
                                                onclick="showProjectQuoteModal();">引用备选案例
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frm_estate" class="form-horizontal">
                                    <input type="hidden" name="id" value="${basicEstate.id}">
                                    <input type="hidden" name="quoteId" value="${basicEstate.quoteId}">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1">省
                                                    <span class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <select name="province"
                                                            class="form-control input-full search-select select2 province">
                                                    </select>
                                                </div>
                                                <label class="col-sm-1">市<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <select name="city"
                                                            class="form-control input-full search-select select2 city">
                                                    </select>
                                                </div>
                                                <label class="col-sm-1">县</label>
                                                <div class="col-sm-3">
                                                    <select name="district"
                                                            class="form-control input-full search-select select2 district">
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1">楼盘名称<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <div class="input-group">
                                                        <input type="text" id="txt_estate_search"
                                                               data-rule-maxlength="100" placeholder="楼盘名称"
                                                               required="required"
                                                               name="name" class="form-control"
                                                               value="${basicEstate.name}">
                                                        <div class="input-group-prepend">
                                                            <button class="btn btn-info btn-sm "
                                                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                    type="button"
                                                                    onclick="estateCommon.mapMarker(false);">
                                                                标注
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                                <label class="col-sm-1">楼盘方位<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <select name="position"
                                                            class="form-control input-full search-select position select2"
                                                            required>
                                                    </select>
                                                </div>
                                                <label class="col-sm-1">基础版块<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <div class="input-group">
                                                        <input type="hidden" name="blockId"
                                                               value="${basicEstate.blockId}">
                                                        <input type="text"
                                                               onchange="$(this).closest('.input-group').find('[name=blockId]').val('0');"
                                                               placeholder="基础版块" class="form-control"
                                                               name="blockName"
                                                               value="${basicEstate.blockName}">
                                                        <div class="input-group-prepend">
                                                            <button class="btn btn-warning btn-sm "
                                                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                    type="button"
                                                                    onclick="$(this).closest('.input-group').find('input').val('');">
                                                                清空
                                                            </button>
                                                        </div>
                                                        <div class="input-group-prepend">
                                                            <button class="btn btn-primary btn-sm "
                                                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                    type="button"
                                                                    onclick="estateCommon.blockSelect(this);">
                                                                选择
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
                                                <label class="col-sm-1">基础版块描述<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-11">
                        <textarea class="form-control input-full" name="blockDescription" id="blockDescription" required
                                  placeholder="基础版块描述">${basicEstate.blockDescription}</textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <div class="col-sm-12">
                                                    <table class="table estateStreetInfos">
                                                        <thead>
                                                        <tr>
                                                            <th style="width: 10%">街道号</th>
                                                            <th style="width: 10%">大门名称</th>
                                                            <th style="width: 10%">附号</th>
                                                            <th style="width: 7%">大门图</th>
                                                            <th style="width: 7%"></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>

                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1">占地面积(平方米)</label>
                                                <div class="col-sm-3">
                                                    <input type="text" data-rule-maxlength="100"
                                                           data-rule-number='true'
                                                           placeholder="占地面积(请输入数字)" name="coverAnArea"
                                                           class="form-control input-full"
                                                           value="${basicEstate.coverAnArea}">
                                                </div>
                                                <label class="col-sm-1">容积率</label>
                                                <div class="col-sm-3">
                                                    <input type="text"
                                                           placeholder="容积率" name="volumetricRate"
                                                           class="form-control input-full"
                                                           value="${basicEstate.volumetricRate}">
                                                </div>
                                                <label class="col-sm-1">绿化率</label>
                                                <div class="col-sm-3">
                                                    <input type="text"
                                                           placeholder="绿化率" name="greeningRate"
                                                           class="form-control input-full  x-percent"
                                                           value="${basicEstate.greeningRate}">
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1">总楼栋数<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <input type="text" data-rule-maxlength="100"
                                                           data-rule-number='true'
                                                           placeholder="总楼栋数(请输入数字)" name="buildingNumber"
                                                           required
                                                           class="form-control input-full"
                                                           value="${basicEstate.buildingNumber}">
                                                </div>
                                                <label class="col-sm-1">开发商</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="开发商"
                                                           class="form-control input-full"
                                                           name="developerName"
                                                           value="${basicEstate.developerName}">
                                                    <input type="hidden" placeholder="开发商"
                                                           class="form-control input-full" name="developer"
                                                           value="${basicEstate.developer}">
                                                </div>
                                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                                    开盘时间
                                                </label>
                                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                                    <input type="text" placeholder="开盘时间"
                                                           data-date-format='yyyy-mm-dd'
                                                           name="openTime"
                                                           class="form-control input-full dbdate">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1">楼盘区位分析<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-11">
                        <textarea class="form-control input-full" name="locationDescribe" required
                                  placeholder="楼盘区位分析">${basicEstate.locationDescribe}</textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="nonIndustrialFile"></div>
                                    <c:if test="${formType eq 'industry'}">
                                        <div id="industrialFile"></div>
                                    </c:if>
                                    <div class="card-header">
                                        <div class="card-category">基础设施情况</div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1">基础设施完备度<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <select class="form-control input-full search-select select2 "
                                                            name="infrastructureCompleteness"
                                                            required="required">
                                                    </select>
                                                </div>
                                                <div class="col-sm-8">
                                                    <div id="industrySupplyInfoContainer"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <div class="x_content">
                                    <div class="card-header">
                                        <div class="card-title">土地实体情况</div>
                                    </div>
                                    <form id="frm_estateLandState" class="form-horizontal">
                                        <input type="hidden" name="id" value="${basicEstateLandState.id}">
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1">土地名称<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <input type="text" class="form-control input-full" name="name"
                                                               required
                                                               onfocus="examineCommon.referenceValue(estateCommon.estateForm.find('input[name=name]'),$(this));"
                                                               placeholder="名称" value="${basicEstateLandState.name}">
                                                    </div>

                                                </div>
                                            </div>
                                        </div>

                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <div class="col-sm-12">

                                                        <table class="table basicEstateLandCategoryInfo">
                                                            <thead>
                                                            <tr>
                                                                <th style="width: 15%">土地用途类型</th>
                                                                <th style="width: 15%">土地用途类别</th>
                                                                <th style="width: 10%">土地取得时间</th>
                                                                <th style="width: 10%">土地使用年限</th>
                                                                <th style="width: 20%">土地级别</th>
                                                                <th style="width: 10%"></th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                            color="#6f5499" size="10">
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1">四至(1)<span
                                                            class="symbol required"></span></label>
                                                    <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1">
                                                        <select class="form-control input-full" name="eastToName">
                                                            <option value="东至" selected="selected">东至</option>
                                                            <option value="东南">东南</option>
                                                            <option value="东北">东北</option>
                                                        </select>
                                                    </div>
                                                    <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                                        <input type="text" class="form-control input-full" required
                                                               name="eastTo"
                                                               value="${basicEstateLandState.eastTo}">
                                                    </div>
                                                    <label class="col-sm-1">四至(2)<span
                                                            class="symbol required"></span></label>
                                                    <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1">
                                                        <select class="form-control input-full" name="southToName">
                                                            <option value="南至" selected="selected">南至</option>
                                                            <option value="东南">东南</option>
                                                            <option value="西南">西南</option>
                                                        </select>
                                                    </div>
                                                    <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                                        <input type="text" class="form-control input-full" required
                                                               name="southTo"
                                                               value="${basicEstateLandState.southTo}">
                                                    </div>
                                                    <label class="col-sm-1">四至(3)<span
                                                            class="symbol required"></span></label>
                                                    <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1">
                                                        <select class="form-control input-full" name="westToName">
                                                            <option value="西至" selected="selected">西至</option>
                                                            <option value="西南">西南</option>
                                                            <option value="西北"></option>
                                                        </select>
                                                    </div>
                                                    <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                                        <input type="text" class="form-control input-full" required
                                                               name="westTo"
                                                               value="${basicEstateLandState.westTo}">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1">四至(4)<span
                                                            class="symbol required"></span></label>
                                                    <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1">
                                                        <select class="form-control input-full" name="northToName">
                                                            <option value="北至" selected="selected">北至</option>
                                                            <option value="东北">东北</option>
                                                            <option value="西北">西北</option>
                                                        </select>
                                                    </div>
                                                    <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                                        <input type="text" class="form-control input-full" required
                                                               name="northTo"
                                                               value="${basicEstateLandState.northTo}">
                                                    </div>

                                                    <label class="col-sm-1">土地形状<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full shapeState"
                                                                name="shapeState" required>
                                                        </select>
                                                    </div>
                                                    <label class="col-sm-1">土地面积<span
                                                            class="symbol required"></span></label>
                                                    <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1">
                                                        <select class="form-control input-full" name="landAreaUnit">
                                                            <option value="平方米" selected="selected">平方米</option>
                                                            <option value="亩">亩</option>
                                                        </select>
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control input-full"
                                                               data-rule-number='true' required
                                                               placeholder="土地面积(请输入数字)" name="landArea"
                                                               value="${basicEstateLandState.landArea}">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1">地形</label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full search-select select2 planeness"
                                                                name="planeness">
                                                        </select>
                                                    </div>
                                                    <label class="col-sm-1">地势</label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full search-select select2 topographicTerrain"
                                                                name="topographicTerrain">
                                                        </select>
                                                    </div>
                                                    <label class="col-sm-1">土地开发程度</label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full developmentDegree"
                                                                name="developmentDegree">
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1">土地利用现状</label>
                                                    <div class="col-sm-3">
                                                        <input type="text" class="form-control input-full"
                                                               placeholder="未开发，已开发完成投入使用，部分开发"
                                                               name="presentSituationLandUse"
                                                               value="${basicEstateLandState.presentSituationLandUse}">
                                                    </div>
                                                    <label class="col-sm-1 developmentDegreeContent">土地开发程度备注</label>
                                                    <div class="col-sm-3 developmentDegreeContent">
                                                        <input type="text" class="form-control input-full"
                                                               placeholder="土地开发程度备注"
                                                               name="developmentDegreeRemark"
                                                               value="${basicEstateLandState.developmentDegreeRemark}">
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <div id="developmentDegreeContentContainer"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card-header">
                                            <div class="card-category">开发限制条件</div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1">容积率</label>
                                                    <div class="col-sm-3">
                                                        <input type="text" class="form-control input-full "
                                                               placeholder="容积率"
                                                               name="plotRatio"
                                                               onfocus="examineCommon.referenceValue(estateCommon.estateForm.find('input[name=volumetricRate]'),$(this));"
                                                               value="${basicEstateLandState.plotRatio}">
                                                    </div>
                                                    <label class="col-sm-1">建筑密度</label>
                                                    <div class="col-sm-3">
                                                        <input type="text" class="form-control input-full"
                                                               placeholder="建筑密度"
                                                               name="buildingDensity"
                                                               value="${basicEstateLandState.buildingDensity}">
                                                    </div>
                                                    <label class="col-sm-1">绿地率</label>
                                                    <div class="col-sm-3">
                                                        <input type="text" class="form-control input-full"
                                                               placeholder="绿地率"
                                                               onfocus="examineCommon.referenceValue(estateCommon.estateForm.find('input[name=greeningRate]'),$(this));"
                                                               name="greenSpaceRate"
                                                               value="${basicEstateLandState.greenSpaceRate}">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1">兼容比例</label>
                                                    <div class="col-sm-3">
                                                        <input type="text" class="form-control input-full"
                                                               placeholder="兼容比例"
                                                               name="compatibleRatio"
                                                               value="${basicEstateLandState.compatibleRatio}">
                                                    </div>
                                                    <div style="display: none">
                                                        <label class="col-sm-1">投资强度（万元/亩）</label>
                                                        <div class="col-sm-3">
                                                            <input type="text" class="form-control input-full"
                                                                   data-rule-number='true'
                                                                   placeholder="投资强度（万元/亩） 数字"
                                                                   name="investmentIntensity"
                                                                   value="${basicEstateLandState.investmentIntensity}">
                                                        </div>
                                                    </div>
                                                    <div style="display: none">
                                                        <label class="col-sm-1">建筑限高</label>
                                                        <div class="col-sm-3">
                                                            <input type="text" class="form-control input-full"
                                                                   data-rule-number='true'
                                                                   placeholder="建筑限高(数字)" name="buildingHeightLimit"
                                                                   value="${basicEstateLandState.buildingHeightLimit}">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card-header">
                                            <div class="card-category">土壤</div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1">污染<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full search-select select2"
                                                                name="contaminated" required>
                                                        </select>
                                                    </div>
                                                    <label class="col-sm-1">酸碱度<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full search-select select2"
                                                                name="ph" required>
                                                        </select>
                                                    </div>
                                                    <label class="col-sm-1">肥力</label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full search-select select2"
                                                                name="fertility">
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1">稳定性<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full search-select select2"
                                                                name="holdOn" required>
                                                        </select>
                                                    </div>
                                                    <label class="col-sm-1">承载力<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full search-select select2"
                                                                name="bearingCapacity" required>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid" style="display: none">
                                                    <label class="col-sm-1">投资强度（万元/亩）</label>
                                                    <div class="col-sm-11">
                    <textarea class="form-control input-full"
                              name="specialRestrictions">${basicEstateLandState.specialRestrictions}</textarea>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </form>
                                </div>
                                <div class="x_content">
                                    <%@include file="/views/project/tool/landLevelModalView.jsp" %>
                                    <%@include file="/views/project/stageSurvey/common/estateNetwork.jsp" %>
                                    <%@include file="/views/project/stageSurvey/common/estateParking.jsp" %>
                                    <%@include file="/views/project/stageSurvey/common/matchingEnvironment.jsp" %>
                                    <%@include file="/views/project/stageSurvey/common/matchingFinance.jsp" %>
                                    <c:if test="${formType eq 'residence'}">
                                        <%@include file="/views/project/stageSurvey/common/matchingEducation.jsp" %>
                                        <%@include file="/views/project/stageSurvey/common/matchingRecreation.jsp" %>
                                        <%@include file="/views/project/stageSurvey/common/matchingRestaurant.jsp" %>
                                        <%@include file="/views/project/stageSurvey/common/matchingMarket.jsp" %>
                                        <%@include file="/views/project/stageSurvey/common/matchingMedical.jsp" %>
                                    </c:if>
                                    <%@include file="/views/project/stageSurvey/common/matchingTransit.jsp" %>
                                    <%@include file="/views/project/stageSurvey/common/matchingTrafficHub.jsp" %>
                                    <%@include file="/views/project/stageSurvey/common/matchingMetro.jsp" %>
                                    <%@include file="/views/project/stageSurvey/common/matchingMainRoad.jsp" %>
                                    <%@include file="/views/project/stageSurvey/common/matchingMainConversion.jsp" %>
                                    <c:if test="${formType eq 'industry'}">
                                        <%@include file="/views/project/stageSurvey/common/estateSupplyWater.jsp" %>
                                        <%@include
                                                file="/views/project/stageSurvey/common/estateSupplyDrainWater.jsp" %>
                                        <%@include file="/views/project/stageSurvey/common/estateSupplyPower.jsp" %>
                                        <%@include file="/views/project/stageSurvey/common/estateSupplyHeating.jsp" %>
                                        <%@include file="/views/project/stageSurvey/common/estateSupplyGas.jsp" %>
                                        <%@include file="/views/project/stageSurvey/common/matchingMaterial.jsp" %>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">
                        <div class="card-body">
                            <button type="button" id="cancel_btn btn-sm" class="btn btn-default"
                                    onclick="window.close()">关闭
                            </button>
                            <button type="button" style="margin-left: 10px;" class="btn btn-warning"
                                    onclick="saveDataInfo();">保存
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>
</html>

<%@include file="/views/project/stageSurvey/common/applyInfoHistory.jsp" %>
<%@include file="/views/project/stageSurvey/common/applyInfoQuote.jsp" %>
<%@include file="/views/project/stageSurvey/common/estateStreetInfo.jsp" %>
<%@include file="/views/project/stageSurvey/common/estateLandCategoryInfo.jsp" %>


<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.min.js?v=${assessVersion}"></script>
<script src='${pageContext.request.contextPath}/js/autocomplete/estate.case.js?v=${assessVersion}'></script>
<script src="${pageContext.request.contextPath}/js/autocomplete/developer.js?v=${assessVersion}"></script>
<script src='${pageContext.request.contextPath}/js/common.column.js?v=${assessVersion}'></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.estate.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/sonEstateView.js?v=${assessVersion}"></script>

<script src="${pageContext.request.contextPath}/js/select/block.select.js?v=${assessVersion}"></script>


<!-- 高德抓取周边数据 -->
<script src="${pageContext.request.contextPath}/js/select/selectMap/transit.checkbox.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/metro.checkbox.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/finance.checkbox.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/education.checkbox.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/recreation.checkbox.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/restaurant.checkbox.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/market.checkbox.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/medical.checkbox.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/trafficHub.checkbox.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/distance.get.fun.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/js/map.placeSearch.js?v=${assessVersion}"></script>
<script type="text/javascript">
    $(function () {
        estateCommon.initById('${basicEstate.id}');
        //楼盘自动填充插件
        estateCommon.autocompleteStart();
    });

    //保存数据信息
    function saveDataInfo() {
        Loading.progressShow();
        var item = {};
        item.basicEstate = formSerializeArray(estateCommon.estateForm);
        item.basicEstate.id = estateCommon.estateForm.find("input[name='id']").val();
        item.basicEstate.name = estateCommon.estateForm.find("input[name='name']").val();

        var data = formSerializeArray(estateCommon.estateLandStateForm);
        item.basicEstateLandState = data;
        var formData = JSON.stringify(examineCommon.getFormData());
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/saveDraft",
            type: "post",
            dataType: "json",
            async: false,
            data: {
                formData: formData,
                formClassify: '${tbType}',
                planDetailsId: '${planDetailsId}'
            },
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "保存数据成功", function () {
                        window.close();
                    });
                } else {
                    AlertError("保存失败,失败原因:" + result.errmsg);
                }
            }
        });
    }

    function showHistoryModal() {
        historyInfo.caseEstate.showModel('${tbId}', '${formClassify}', '${tbType}', '${basicApplyBatch.id}');
    };

    function showCaseQuoteModal() {
        caseFun.caseEstate.showModel();
    }

    function showProjectQuoteModal() {
        projectData.prototype.showModel();
    }
</script>