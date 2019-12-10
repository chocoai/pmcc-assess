<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                        ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frmQuery" class="form-horizontal">
                        <div class="form-group">
                            <div>
                                <label class="col-sm-1 control-label">
                                    内容
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="内容" id="queryContent" name="queryContent"
                                           class="form-control">
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">
                                    标题
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="标题" id="queryTitle" name="queryTitle"
                                           class="form-control">
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">
                                    网站
                                </label>
                                <div class="col-sm-3">
                                    <select class="form-control" required id="queryWebName">
                                        <option value="">--请选择--</option>
                                        <c:if test="${not empty webTypes}">
                                            <c:forEach items="${webTypes}" var="item">
                                                <option value="${item}">${item}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group ">
                            <div>
                                <label class="col-sm-1 control-label">省
                                </label>
                                <div class="col-sm-3">
                                    <select id="province" name="province"
                                            class="form-control search-select select2" required="required">
                                    </select>
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">市
                                </label>
                                <div class="col-sm-3">
                                    <select id="city" name="city" class="form-control search-select select2"
                                            required="required">
                                    </select>
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">
                                    类型
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="类型" id="queryType" name="queryType"
                                           class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="form-group ">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    开始日期
                                </label>
                                <div class="col-sm-3">
                                    <input placeholder="开始日期" id="queryStartTime" data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate roomTime">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    结束日期
                                </label>
                                <div class="col-sm-3">
                                    <input placeholder="结束日期" id="queryEndTime" data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate roomTime">
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <button type="button" class="btn btn-primary"
                                        onclick="detailInfo.prototype.loadDataDicList()">
                                    查询
                                </button>
                                <button type="button" class="btn btn-primary"
                                        onclick="detailInfo.prototype.assignTask()">
                                    发起审批
                                </button>
                            </div>
                        </div>
                    </form>
                    <input type="hidden" id="selectIds">
                    <table class="table table-bordered" id="transaction_List">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">信息补全</h3>
            </div>
            <form id="frmFather" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <table class="table table-bordered" id="singleData">
                                    <!-- cerare document add ajax data-->
                                </table>
                                <div class="form-group">
                                    <input type="hidden" name="masterId">
                                    <input type="hidden" name="id">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            类型
                                        </label>
                                        <div class="col-sm-2">
                                            <select class="form-control" name="type">
                                                <option value="">-请选择-</option>
                                                <option value="房产">房产</option>
                                                <option value="土地">土地</option>
                                            </select>
                                        </div>
                                    </div>
                                    <%--<button type="button" style="display: none" class="btn btn-primary landContent"--%>
                                    <%--onclick="detailInfo.prototype.showLandHistoryModal()">--%>
                                    <%--新增及历史--%>
                                    <%--</button>--%>
                                    <%--<button type="button" style="display: none" class="btn btn-primary houseContent"--%>
                                    <%--onclick="detailInfo.prototype.showHouseHistoryModal()">--%>
                                    <%--新增及历史--%>
                                    <%--</button>--%>
                                </div>
                                <div class="form-group landContent" style="display: none">
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                省
                                            </label>
                                            <div class="col-sm-2">
                                                <select name="province" class="form-control search-select select2">
                                                </select>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                市
                                            </label>
                                            <div class="col-sm-2">
                                                <select name="city" class="form-control search-select select2">
                                                </select>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                区
                                            </label>
                                            <div class="col-sm-2">
                                                <select name="district" class="form-control search-select select2">
                                                </select>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                土地类型
                                            </label>
                                            <div class="col-sm-2">
                                                <div class="input-group">
                                                    <input type="text" name="belongType" class="form-control"
                                                           list="landUseList">
                                                    <datalist id="landUseList">

                                                    </datalist>
                                                    <span class="input-group-btn">
                                                <button type="button" class="btn btn-default docs-tooltip"
                                                        onclick="$(this).closest('.input-group').find('input').val('');"
                                                        data-toggle="tooltip" data-original-title="清除">
                                                <i class="fa fa-trash-o"></i>
                                                </button>
                                            </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                土地类别
                                            </label>
                                            <div class="col-sm-2">
                                                <div class="input-group">
                                                    <input type="text" name="belongCategory" class="form-control"
                                                           list="landUseCategoryList">
                                                    <datalist id="landUseCategoryList">

                                                    </datalist>
                                                    <span class="input-group-btn">
                                                <button type="button" class="btn btn-default docs-tooltip"
                                                        onclick="$(this).closest('.input-group').find('input').val('');"
                                                        data-toggle="tooltip" data-original-title="清除">
                                                <i class="fa fa-trash-o"></i>
                                                </button>
                                            </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                土地性质
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" name="landPurpose" class="form-control">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                街道
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" name="street" class="form-control">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                面积
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                       id="landArea"
                                                       name="area" class="form-control"
                                                       onblur="detailInfo.prototype.getUnitPrice()">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                单位
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" name="areaUnit" class="form-control">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                地块名称
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" name="name" class="form-control">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                宗地编号
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" name="parcelNumber" class="form-control">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                宗地位置
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" name="parcelSite" class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                交易方式
                                            </label>
                                            <div class="col-sm-2">
                                                <select name="dealType" class="form-control search-select select2">
                                                </select>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                成交价
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                       id="landCurrentPrice"
                                                       name="currentPrice" class="form-control"
                                                       onblur="detailInfo.prototype.getUnitPrice()">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                成交(协商)日期
                                            </label>
                                            <div class="col-sm-2">
                                                <input name="negotiatedDate" data-date-format="yyyy-mm-dd"
                                                       onchange="detailInfo.prototype.getRealizationCycle()"
                                                       id="landNegotiatedDate" class="form-control date-picker dbdate">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                评估价
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                       name="consultPrice" id="landConsultPrice" class="form-control"
                                                       onblur="detailInfo.prototype.getHouseRealizationRatios()">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                评估基准日期
                                            </label>
                                            <div class="col-sm-2">
                                                <input name="assessStandardDate" data-date-format="yyyy-mm-dd"
                                                       onchange="detailInfo.prototype.getRealizationCycle()"
                                                       id="landAssessStandardDate"
                                                       class="form-control date-picker dbdate">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                单价
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                       name="unitPrice" class="form-control" id="landUnitPrice">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                楼面地价
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                       name="floorPrice" class="form-control">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                变现率
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" name="landRealizationRatios"
                                                       class="form-control x-percent">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                变现周期
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" name="realizationCycle" id="landRealizationCycle"
                                                       class="form-control">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                净用地面积
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                       name="landArea" class="form-control">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                容积率
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" name="plotRatio" data-rule-number="true"
                                                       data-rule-maxlength="50" class="form-control">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                容积率说明
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" name="plotRatioRemark" class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                绿化率
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" name="greeningRate" class="form-control x-percent">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                绿化率说明
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" name="greeningRateRemark" class="form-control">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                建筑密度
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" name="buildDensity" class="form-control"
                                                       data-rule-number="true" data-rule-maxlength="50">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                建筑密度说明
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" name="buildDensityRemark" class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                建筑高度
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" name="buildHeight" class="form-control"
                                                       data-rule-number="true" data-rule-maxlength="50">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                建筑高度说明
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" name="buildHeightRemark" class="form-control">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                指标款(亩)
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" name="indexAmount" class="form-control"
                                                       data-rule-number="true" data-rule-maxlength="50">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                指标款说明
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" name="indexAmountRemark" class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                成交对象概况
                                            </label>
                                            <div class="col-sm-11">
                                                <textarea name="dealPartInfo" id="landDealPartInfo"
                                                          class="form-control"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                附件
                                            </label>
                                            <div class="col-sm-10">
                                                <input id="uploadLandFile" type="file" multiple="false">
                                                <div id="_uploadLandFile"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group houseContent" style="display: none">
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                省
                                            </label>
                                            <div class="col-sm-2">
                                                <select name="province" class="form-control search-select select2">
                                                </select>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                市
                                            </label>
                                            <div class="col-sm-2">
                                                <select name="city" class="form-control search-select select2">
                                                </select>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                区
                                            </label>
                                            <div class="col-sm-2">
                                                <select name="district" class="form-control search-select select2">
                                                </select>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                房产类型
                                            </label>
                                            <div class="col-sm-2">
                                                <div class="input-group">
                                                    <input type="text" name="belongType" class="form-control"
                                                           list="houseUseList">
                                                    <datalist id="houseUseList">

                                                    </datalist>
                                                    <span class="input-group-btn">
                                                <button type="button" class="btn btn-default docs-tooltip"
                                                        onclick="$(this).closest('.input-group').find('input').val('');"
                                                        data-toggle="tooltip" data-original-title="清除">
                                                <i class="fa fa-trash-o"></i>
                                                </button>
                                            </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                房产类别
                                            </label>
                                            <div class="col-sm-2">
                                                <div class="input-group">
                                                    <input type="text" name="belongCategory" class="form-control"
                                                           list="houseUseCategoryList">
                                                    <datalist id="houseUseCategoryList">

                                                    </datalist>
                                                    <span class="input-group-btn">
                                                <button type="button" class="btn btn-default docs-tooltip"
                                                        onclick="$(this).closest('.input-group').find('input').val('');"
                                                        data-toggle="tooltip" data-original-title="清除">
                                                <i class="fa fa-trash-o"></i>
                                                </button>
                                            </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                街道
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" name="street" class="form-control">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                面积
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                       id="houseArea"
                                                       name="area" class="form-control"
                                                       onblur="detailInfo.prototype.getUnitPrice()">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                楼盘名称
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" name="name" class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                楼栋号
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" name="buildingNumber" class="form-control">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                单元号
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" name="unitNumber" class="form-control">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                房号
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" name="houseNumber" class="form-control">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                交易方式
                                            </label>
                                            <div class="col-sm-2">
                                                <select name="dealType" class="form-control search-select select2">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                成交价
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                       id="houseCurrentPrice"
                                                       name="currentPrice" class="form-control"
                                                       onblur="detailInfo.prototype.getUnitPrice()">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                成交(协商)日期
                                            </label>
                                            <div class="col-sm-2">
                                                <input name="negotiatedDate" data-date-format="yyyy-mm-dd"
                                                       onchange="detailInfo.prototype.getRealizationCycle()"
                                                       id="houseNegotiatedDate" class="form-control date-picker dbdate">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                评估价
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                       name="consultPrice" id="houseConsultPrice" class="form-control"
                                                       onblur="detailInfo.prototype.getHouseRealizationRatios()">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                评估基准日期
                                            </label>
                                            <div class="col-sm-2">
                                                <input name="assessStandardDate" data-date-format="yyyy-mm-dd"
                                                       onchange="detailInfo.prototype.getRealizationCycle()"
                                                       id="houseAssessStandardDate"
                                                       class="form-control date-picker dbdate">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                单价
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                       name="unitPrice" class="form-control" id="houseUnitPrice">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                变现率
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" name="houseRealizationRatios"
                                                       class="form-control x-percent">
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                变现周期
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" name="realizationCycle" id="houseRealizationCycle"
                                                       class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                成交对象概况
                                            </label>
                                            <div class="col-sm-11">
                                                <textarea name="dealPartInfo" id="houseDealPartInfo"
                                                          class="form-control"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                附件
                                            </label>
                                            <div class="col-sm-10">
                                                <input id="uploadHouseFile" type="file" multiple="false">
                                                <div id="_uploadHouseFile"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        关闭
                    </button>
                    <button type="button" style="display: none" class="btn btn-primary landContent"
                            onclick="detailInfo.prototype.saveLandDetail()">
                        保存
                    </button>
                    <button type="button" style="display: none" class="btn btn-primary houseContent"
                            onclick="detailInfo.prototype.saveHouseDetail()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxTableList" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">数据列表</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <form id="frmAdd" class="form-horizontal">
                            <input type="hidden" name="masterId">
                            <div class="panel-body">
                                <button type="button" class="btn btn-success"
                                        onclick="detailInfo.prototype.addInit()"
                                        data-toggle="modal"> 新增
                                </button>
                                <div class="x_title">
                                    <h3>房产
                                    </h3>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                    <div class="row">
                                        <div class="panel-body">
                                            <table class="table table-bordered" id="houseHistory">
                                                <!-- cerare document add ajax data-->
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <div class="x_title">
                                    <h3>土地</h3>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                    <div class="row">
                                        <div class="panel-body">
                                            <table class="table table-bordered" id="landHistory">
                                                <!-- cerare document add ajax data-->
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>


<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    $(function () {
        detailInfo.prototype.loadDataDicList();
        AssessCommon.initAreaInfo({
            useDefaultText: false,
            provinceTarget: $("#province"),
            cityTarget: $("#city"),
            districtTarget: $("#district")
        })
    });
    var detailInfo = function () {

    };
    detailInfo.prototype = {
        config: function () {
            var data = {};
            data.table = "transaction_List";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            data.singleTable = "singleData";
            return data;
        },
        loadDataDicList: function () {
            var cols = [];
            cols.push({field: 'title', title: '标题', width: '12%'});
            cols.push({field: 'province', title: '省', width: '5%'});
            cols.push({field: 'city', title: '市', width: '5%'});
            cols.push({field: 'sourceSiteName', title: '来源网站', width: '10%'});
            cols.push({field: 'type', title: '类型', width: '6%'});
            cols.push({
                field: 'beginTime', title: '开始时间', width: '7%', formatter: function (value, row, index) {
                    return formatDate(row.beginTime, false);
                }
            });
            cols.push({
                field: 'endTime', title: '结束时间', width: '7%', formatter: function (value, row, index) {
                    return formatDate(row.endTime, false);
                }
            });
            cols.push({field: 'content', title: '内容', width: '20%'});
            cols.push({field: 'sourceSiteUrl', title: '来源地址', width: '5%'});
            cols.push({field: 'initPrice', title: '起始价', width: '5%'});
            cols.push({field: 'consultPrice', title: '估算价', width: '5%'});
            cols.push({field: 'currentPrice', title: '成交价', width: '5%'});
            cols.push({
                field: 'status', width: '5%', title: '状态', formatter: function (value, row, index) {
                    var s;
                    if (row.status == 1) {
                        s = '-';
                    }
                    if (row.status == 2) {
                        s = '已填写';
                    }
                    if (row.status == 3) {
                        s = '审批中';
                    }
                    if (row.status == 4) {
                        s = '审批通过';
                    }
                    return s;
                }
            });
            cols.push({
                field: 'id', width: '6%', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="补全信息" onclick="detailInfo.prototype.showTableListModal(' + row.id + ')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="查看网址" onclick="detailInfo.prototype.openItem(' + index + ')"><i class="fa fa-eye fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + detailInfo.prototype.config().table).bootstrapTable('destroy');
            TableInit(detailInfo.prototype.config().table, "${pageContext.request.contextPath}/netInfoRecordMyTaskController/getInfoRecordList", cols, {
                queryTitle: $("#queryTitle").val(),
                queryWebName: $("#queryWebName").val(),
                province: $("#province").val(),
                city: $("#city").val(),
                queryContent: $("#queryContent").val(),
                queryType: $("#queryType").val(),
                queryStartTime: $("#queryStartTime").val(),
                queryEndTime: $("#queryEndTime").val()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            }, true);
        },
        openItem: function (index) {
            var row = $("#transaction_List").bootstrapTable('getData')[index];
            if (row.sourceSiteUrl) {
                window.open(row.sourceSiteUrl, "");
            }
        },
        showTableListModal: function (masterId) {
            // var masterId = $("#" + detailInfo.prototype.config().frm).find("input[name='masterId']").val();
            detailInfo.prototype.loadLandHistoryList(masterId);
            detailInfo.prototype.loadHouseHistoryList(masterId);
            $("#frmAdd").find("input[name='masterId']").val(masterId);
            $('#divBoxTableList').modal("show");
        },
        addInit: function () {
            var id = $("#frmAdd").find("input[name='masterId']").val();
            detailInfo.prototype.loadOnclickData(id);
            $.ajax({
                url: "${pageContext.request.contextPath}/netInfoRecordMyTaskController/getDetailByMasterId",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + detailInfo.prototype.config().frm).find("select[name='type']").prop("disabled", false);
                        $("#" + detailInfo.prototype.config().frm).clearAll();
                        detailInfo.prototype.showContent(result.data.type, result, id)
                        $("#" + detailInfo.prototype.config().frm).find("select[name='type']").off('change').on('change', function () {
                            detailInfo.prototype.showContent($(this).val(), result, id)
                        });
                        $('#' + detailInfo.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showContent: function (type, result, id) {
            //加载数据
            if (type == result.data.type) {
                $("#" + detailInfo.prototype.config().frm).initForm(result.data);
                AssessCommon.initAreaInfo({
                    provinceTarget: $("#" + detailInfo.prototype.config().frm).find("select[name='province']"),
                    cityTarget: $("#" + detailInfo.prototype.config().frm).find("select[name='city']"),
                    districtTarget: $("#" + detailInfo.prototype.config().frm).find("select[name='district']"),
                    provinceValue: result.data.province,
                    cityValue: result.data.city,
                    districtValue: result.data.district
                })

            }
            $("#" + detailInfo.prototype.config().frm).find("input[name='masterId']").val(id);
            $("#" + detailInfo.prototype.config().frm).find("select[name='type']").val(type);
            //附件tableId
            var tableId = result.data.id ? result.data.id : 0;
            if (type == '房产') {
                $("#" + detailInfo.prototype.config().frm).find(".houseContent").show();
                $("#" + detailInfo.prototype.config().frm).find(".houseContent").find("input").attr("disabled", false);
                $("#" + detailInfo.prototype.config().frm).find(".houseContent").find("select").attr("disabled", false);
                $("#" + detailInfo.prototype.config().frm).find(".landContent").hide();
                $("#" + detailInfo.prototype.config().frm).find(".landContent").find("input").attr("disabled", true);
                $("#" + detailInfo.prototype.config().frm).find(".landContent").find("select").attr("disabled", true);
                AssessCommon.loadDataListHtml(AssessDicKey.examineHouseLoadUtility, result.data.belongType, function (html, data) {
                    $("#" + detailInfo.prototype.config().frm).find("#houseUseList").empty().html(html).trigger('change');
                }, true);
                $("#" + detailInfo.prototype.config().frm).find("input[name='belongType']").off('change').on('change', function () {
                    AssessCommon.getSonDataList(AssessDicKey.examineHouseLoadUtility, $(this).val(), result.data.belongCategory, function (html, data) {
                        $("#" + detailInfo.prototype.config().frm).find("#houseUseCategoryList").empty().html(html).trigger('change');
                    });
                });
                if (result.data.masterId && type == result.data.type) {
                    //百分字段
                    $("#" + detailInfo.prototype.config().frm).find('[name=houseRealizationRatios]').attr('data-value', result.data.houseRealizationRatios);
                    AssessCommon.elementParsePercent($("#" + detailInfo.prototype.config().frm).find('[name=houseRealizationRatios]'));
                    //初始化选项
                    AssessCommon.loadDataDicByKey(AssessDicKey.dataDealType, result.data.dealType, function (html, data) {
                        $("#" + detailInfo.prototype.config().frm).find("select[name='dealType']").empty().html(html).trigger('change');
                    });
                } else {
                    AssessCommon.loadDataDicByKey(AssessDicKey.dataDealType, '', function (html, data) {
                        $("#" + detailInfo.prototype.config().frm).find("select[name='dealType']").empty().html(html).trigger('change');
                    });
                }
                //加载附件
                detailInfo.prototype.showFile("uploadHouseFile", "tb_net_info_record_house", tableId);
                detailInfo.prototype.fileUpload("uploadHouseFile", "tb_net_info_record_house", tableId);
            } else if (type == '土地') {
                $("#" + detailInfo.prototype.config().frm).find(".landContent").show();
                $("#" + detailInfo.prototype.config().frm).find(".landContent").find("input").attr("disabled", false);
                $("#" + detailInfo.prototype.config().frm).find(".landContent").find("select").attr("disabled", false);
                $("#" + detailInfo.prototype.config().frm).find(".houseContent").hide();
                $("#" + detailInfo.prototype.config().frm).find(".houseContent").find("input").attr("disabled", true);
                $("#" + detailInfo.prototype.config().frm).find(".houseContent").find("select").attr("disabled", true);
                AssessCommon.loadDataListHtml(AssessDicKey.estate_total_land_use, result.data.belongType, function (html, data) {
                    $("#" + detailInfo.prototype.config().frm).find("#landUseList").empty().html(html).trigger('change');
                }, true);
                $("#" + detailInfo.prototype.config().frm).find("input[name='belongType']").off('change').on('change', function () {
                    AssessCommon.getSonDataList(AssessDicKey.estate_total_land_use, $(this).val(), result.data.belongCategory, function (html, data) {
                        $("#" + detailInfo.prototype.config().frm).find("#landUseCategoryList").empty().html(html).trigger('change');
                    });
                });

                if (result.data.masterId && type == result.data.type) {
                    //百分字段
                    $("#" + detailInfo.prototype.config().frm).find('[name=greeningRate]').attr('data-value', result.data.greeningRate);
                    AssessCommon.elementParsePercent($("#" + detailInfo.prototype.config().frm).find('[name=greeningRate]'));
                    $("#" + detailInfo.prototype.config().frm).find('[name=landRealizationRatios]').attr('data-value', result.data.landRealizationRatios);
                    AssessCommon.elementParsePercent($("#" + detailInfo.prototype.config().frm).find('[name=landRealizationRatios]'));
                    //初始化选项
                    AssessCommon.loadDataDicByKey(AssessDicKey.dataDealType, result.data.dealType, function (html, data) {
                        $("#" + detailInfo.prototype.config().frm).find("select[name='dealType']").empty().html(html).trigger('change');
                    });
                } else {
                    AssessCommon.loadDataDicByKey(AssessDicKey.dataDealType, '', function (html, data) {
                        $("#" + detailInfo.prototype.config().frm).find("select[name='dealType']").empty().html(html).trigger('change');
                    });
                }
            } else {
                $("#" + detailInfo.prototype.config().frm).find(".houseContent").hide();
                $("#" + detailInfo.prototype.config().frm).find(".landContent").hide();
            }
            //加载附件
            detailInfo.prototype.showFile("uploadLandFile", "tb_net_info_record_land", tableId);
            detailInfo.prototype.fileUpload("uploadLandFile", "tb_net_info_record_land", tableId);
        },
        saveLandDetail: function () {
            var masterId = $("#frmAdd").find("input[name='masterId']").val();
            if (!$("#" + detailInfo.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(detailInfo.prototype.config().frm);
            data.dealPartInfo = $("#" + detailInfo.prototype.config().frm).find("#landDealPartInfo").val();
            data.type = $("#" + detailInfo.prototype.config().frm).find("select[name='type']").val();
            $.ajax({
                url: "${pageContext.request.contextPath}/netInfoRecordLand/saveLandDetail",
                type: "post",
                dataType: "json",
                data: {
                    formData: JSON.stringify(data),
                    changeStatus: true
                },
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#' + detailInfo.prototype.config().box).modal('hide');
                        detailInfo.prototype.loadLandHistoryList(masterId);
                        detailInfo.prototype.updateLandStatus(result.data.id);
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        updateLandStatus: function (landId) {
            $.ajax({
                url: "${pageContext.request.contextPath}/netInfoRecordLand/updateLandStatus",
                type: "post",
                dataType: "json",
                data: {
                    landId: landId,
                },
                success: function (result) {
                    if (result.ret) {
                        $("#" + detailInfo.prototype.config().table).bootstrapTable('updateByUniqueId', {id: result.data.id, row: result.data});
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        saveHouseDetail: function () {
            var masterId = $("#frmAdd").find("input[name='masterId']").val();
            if (!$("#" + detailInfo.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(detailInfo.prototype.config().frm);
            data.dealPartInfo = $("#" + detailInfo.prototype.config().frm).find("#houseDealPartInfo").val();
            data.type = $("#" + detailInfo.prototype.config().frm).find("select[name='type']").val();
            $.ajax({
                url: "${pageContext.request.contextPath}/netInfoRecordHouse/saveHouseDetail",
                type: "post",
                dataType: "json",
                data: {
                    formData: JSON.stringify(data),
                    changeStatus: true
                },
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#' + detailInfo.prototype.config().box).modal('hide');
                        detailInfo.prototype.loadHouseHistoryList(masterId);
                        detailInfo.prototype.updateHouseStatus(result.data.id);
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        updateHouseStatus: function (houseId) {
            $.ajax({
                url: "${pageContext.request.contextPath}/netInfoRecordHouse/updateHouseStatus",
                type: "post",
                dataType: "json",
                data: {
                    houseId: houseId,
                },
                success: function (result) {
                    if (result.ret) {
                        $("#" + detailInfo.prototype.config().table).bootstrapTable('updateByUniqueId', {id: result.data.id, row: result.data});
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        assignTask: function () {
            var rows = $('#transaction_List').bootstrapTable('getSelections');

            if (rows && rows.length > 0) {
                var idArray = [];
                $.each(rows, function (i, item) {
                    idArray.push(item.id);
                })
                var ids = idArray.join()
                $("#selectIds").val(ids);
                //确认
                var href = "${pageContext.request.contextPath}/netInfoAssignTask/apply";
                href += "?ids=" + ids;
                window.open(href, "");
            } else {
                toastr.info('请选择要审批的数据');
            }
        },
        loadOnclickData: function (id) {
            var cols = [];
            cols.push({field: 'title', title: '标题', width: '15%'});
            cols.push({field: 'province', title: '省', width: '5%'});
            cols.push({field: 'city', title: '市', width: '5%'});
            cols.push({field: 'sourceSiteName', title: '来源网站', width: '10%'});
            cols.push({field: 'type', title: '类型', width: '6%'});
            cols.push({
                field: 'beginTime', title: '开始时间', width: '7%', formatter: function (value, row, index) {
                    return formatDate(row.beginTime, false);
                }
            });
            cols.push({
                field: 'endTime', title: '结束时间', width: '7%', formatter: function (value, row, index) {
                    return formatDate(row.endTime, false);
                }
            });
            cols.push({field: 'content', title: '内容', width: '25%'});
            cols.push({field: 'initPrice', title: '起始价', width: '5%'});
            cols.push({field: 'consultPrice', title: '估算价', width: '5%'});
            cols.push({field: 'currentPrice', title: '成交价', width: '5%'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="查看网址" target="_blank" href="' + row.sourceSiteUrl + '" ><i class="fa fa-eye fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + detailInfo.prototype.config().singleTable).bootstrapTable('destroy');
            TableInit(detailInfo.prototype.config().singleTable, "${pageContext.request.contextPath}/netInfoRecordController/getInfoRecordListByIds", cols, {
                ids: id
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                pagination: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        fileUpload: function (target, tableName, id) {
            FileUtils.uploadFiles({
                target: target,
                disabledTarget: "btn_submit",
                onUpload: function (file) {
                    var formData = {
                        tableName: tableName,
                        tableId: id
                    };
                    return formData;
                }, onUploadComplete: function (result, file) {
                    detailInfo.prototype.showFile(target, tableName, id);
                },
                deleteFlag: true
            });
        },
        showFile: function (target, tableName, id) {
            FileUtils.getFileShows({
                target: target,
                formData: {
                    tableName: tableName,
                    tableId: id
                },
                deleteFlag: true
            })
        },
        loadLandHistoryList: function (masterId) {
            var cols = [];
            cols.push({
                field: 'provinceName', title: '区域', formatter: function (value, row, index) {
                    var str = AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
                    if (row.street) {
                        str += row.street;
                    }
                    return str;
                }
            });
            cols.push({field: 'area', title: '面积'});
            cols.push({field: 'name', title: '地块名称'});
            cols.push({field: 'dealTypeName', title: '交易方式'});
            cols.push({field: 'currentPrice', title: '成交价'});
            cols.push({
                field: 'other', title: '其他', formatter: function (value, row, index) {
                    var result = '';
                    if (row.parcelNumber) {
                        result += '宗地编号：' + row.parcelNumber + '<br/>';
                    }
                    if (row.parcelSite) {
                        result += '宗地位置：' + row.parcelSite + '<br/>';
                    }
                    if (row.negotiatedDate) {
                        result += '成交(协商)日期：' + formatDate(row.negotiatedDate) + '<br/>';
                    }
                    if (row.consultPrice) {
                        result += '评估价：' + row.consultPrice + '<br/>';
                    }
                    if (row.assessStandardDate) {
                        result += '评估基准日：' + formatDate(row.assessStandardDate) + '<br/>';
                    }
                    if (row.unitPrice) {
                        result += '单价：' + row.unitPrice + '<br/>';
                    }
                    if (row.houseRealizationRatios) {
                        result += '变现率：' + row.houseRealizationRatios + '<br/>';
                    }
                    if (row.realizationCycle) {
                        result += '变现周期：' + row.realizationCycle + '<br/>';
                    }
                    if (row.floorPrice) {
                        result += '楼面地价：' + row.floorPrice + '<br/>';
                    }
                    if (row.landArea) {
                        result += '净用地面积：' + row.landArea + '<br/>';
                    }
                    if (row.plotRatio) {
                        result += '容积率：' + row.plotRatio + '<br/>';
                    }
                    if (row.plotRatioRemark) {
                        result += '容积率说明：' + row.plotRatioRemark + '<br/>';
                    }
                    if (row.greeningRate) {
                        result += '绿化率：' + row.greeningRate * 100 + '%<br/>';
                    }
                    if (row.greeningRateRemark) {
                        result += '绿化率说明：' + row.greeningRateRemark + '<br/>';
                    }
                    if (row.buildDensity) {
                        result += '建筑密度：' + row.buildDensity + '<br/>';
                    }
                    if (row.buildDensityRemark) {
                        result += '建筑密度说明：' + row.buildDensityRemark + '<br/>';
                    }
                    if (row.buildHeight) {
                        result += '建筑高度：' + row.buildHeight + '<br/>';
                    }
                    if (row.buildHeightRemark) {
                        result += '建筑高度说明：' + row.buildHeightRemark + '<br/>';
                    }
                    if (row.indexAmount) {
                        result += '指标款(亩)：' + row.indexAmount + '<br/>';
                    }
                    if (row.indexAmountRemark) {
                        result += '指标款(亩)说明：' + row.indexAmountRemark + '<br/>';
                    }
                    return result;
                }
            });
            cols.push({field: 'dealPartInfo', title: '成交对象概况'});
            cols.push({field: 'fileViewName', title: '附件'});
            cols.push({
                field: 'id', width: '6%', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="detailInfo.prototype.getAndInitLand(' + row.id + ')"><i class="fa fa-edit fa-white"></i></a>';
                    if(row.status==0) {
                        str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="删除" onclick="detailInfo.prototype.deleteLandItem(' + row.id + ')"><i class="fa fa-minus fa-white"></i></a>';
                    }
                    str += '</div>';
                    return str;
                }
            });
            $("#landHistory").bootstrapTable('destroy');
            TableInit("landHistory", "${pageContext.request.contextPath}/netInfoRecordLand/getLandListByMasterId", cols, {
                masterId: masterId
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        getAndInitLand: function (id) {
            var masterId = $("#frmAdd").find("input[name='masterId']").val();
            detailInfo.prototype.loadOnclickData(masterId);
            $.ajax({
                url: "${pageContext.request.contextPath}/netInfoRecordLand/getDataById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + detailInfo.prototype.config().frm).clearAll();
                        detailInfo.prototype.showContent(result.data.type, result, masterId)
                        $("#" + detailInfo.prototype.config().frm).find("select[name='type']").prop("disabled", true);
                        $('#' + detailInfo.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        deleteLandItem: function (id) {
            var masterId = $("#frmAdd").find("input[name='masterId']").val();
            Alert("确认删除!", 2, null, function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/netInfoRecordLand/delete",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            detailInfo.prototype.loadLandHistoryList(masterId);
                        }
                        else {
                            Alert("保存数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            });
        },
        loadHouseHistoryList: function (masterId) {
            var cols = [];
            cols.push({
                field: 'provinceName', title: '区域', formatter: function (value, row, index) {
                    var str = AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
                    if (row.street) {
                        str += row.street;
                    }
                    return str;
                }
            });
            cols.push({field: 'area', title: '面积'});
            cols.push({field: 'name', title: '楼盘名称'});
            cols.push({field: 'dealTypeName', title: '交易方式'});
            cols.push({field: 'currentPrice', title: '成交价'});
            cols.push({
                field: 'other', title: '其他', formatter: function (value, row, index) {
                    var result = '';
                    if (row.negotiatedDate) {
                        result += '成交(协商)日期：' + formatDate(row.negotiatedDate) + '<br/>';
                    }
                    if (row.consultPrice) {
                        result += '评估价：' + row.consultPrice + '<br/>';
                    }
                    if (row.assessStandardDate) {
                        result += '评估基准日：' + formatDate(row.assessStandardDate) + '<br/>';
                    }
                    if (row.unitPrice) {
                        result += '单价：' + row.unitPrice + '<br/>';
                    }
                    if (row.houseRealizationRatios) {
                        result += '变现率：' + row.houseRealizationRatios + '<br/>';
                    }
                    if (row.realizationCycle) {
                        result += '变现周期：' + row.realizationCycle + '<br/>';
                    }
                    return result;
                }
            });
            cols.push({field: 'dealPartInfo', title: '成交对象概况'});
            cols.push({field: 'fileViewName', title: '附件'});
            cols.push({
                field: 'id', width: '6%', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="detailInfo.prototype.getAndInitHouse(' + row.id + ')"><i class="fa fa-edit fa-white"></i></a>';
                    if(row.status==0) {
                        str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="删除" onclick="detailInfo.prototype.deleteHouseItem(' + row.id + ')"><i class="fa fa-minus fa-white"></i></a>';
                    }
                    str += '</div>';
                    return str;
                }
            });
            $("#houseHistory").bootstrapTable('destroy');
            TableInit("houseHistory", "${pageContext.request.contextPath}/netInfoRecordHouse/getHouseListByMasterId", cols, {
                masterId: masterId
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        getAndInitHouse: function (id) {
            var masterId = $("#frmAdd").find("input[name='masterId']").val();
            detailInfo.prototype.loadOnclickData(masterId);
            $.ajax({
                url: "${pageContext.request.contextPath}/netInfoRecordHouse/getDataById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + detailInfo.prototype.config().frm).clearAll();
                        detailInfo.prototype.showContent(result.data.type, result, masterId)
                        $("#" + detailInfo.prototype.config().frm).find("select[name='type']").prop("disabled", true);
                        $('#' + detailInfo.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        deleteHouseItem: function (id) {
            var masterId = $("#frmAdd").find("input[name='masterId']").val();
            Alert("确认删除!", 2, null, function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/netInfoRecordHouse/delete",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            detailInfo.prototype.loadHouseHistoryList(masterId);
                        }
                        else {
                            Alert("保存数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            });
        },
        getUnitPrice: function () {
            var landCurrentPrice = Number($("#landCurrentPrice").val());
            var landArea = Number($("#landArea").val());
            var houseCurrentPrice = Number($("#houseCurrentPrice").val());
            var houseArea = Number($("#houseArea").val());
            if (houseCurrentPrice >= 0 && houseArea > 0) {
                var houseUnitPrice = (houseCurrentPrice / houseArea).toFixed(2);
                $("#houseUnitPrice").val(houseUnitPrice);
            } else {
                $("#houseUnitPrice").val("");
            }
            if (landCurrentPrice >= 0 && landArea > 0) {
                var landUnitPrice = (landCurrentPrice / landArea).toFixed(2);
                $("#landUnitPrice").val(landUnitPrice);
            } else {
                $("#landUnitPrice").val("");
            }
            detailInfo.prototype.getHouseRealizationRatios();
        },
        getHouseRealizationRatios: function () {
            var landCurrentPrice = Number($("#landCurrentPrice").val());
            var landConsultPrice = Number($("#landConsultPrice").val());
            var houseCurrentPrice = Number($("#houseCurrentPrice").val());
            var houseConsultPrice = Number($("#houseConsultPrice").val());

            if (houseCurrentPrice >= 0 && houseConsultPrice > 0) {
                var houseRealizationRatios = (houseCurrentPrice / houseConsultPrice).toFixed(2);
                $("#" + detailInfo.prototype.config().frm).find('[name=houseRealizationRatios]').attr('data-value', houseRealizationRatios);
                AssessCommon.elementParsePercent($("#" + detailInfo.prototype.config().frm).find('[name=houseRealizationRatios]'));
            } else {
                $("#" + detailInfo.prototype.config().frm).find('[name=houseRealizationRatios]').val('');
            }
            if (landCurrentPrice >= 0 && landConsultPrice > 0) {
                var landRealizationRatios = (landCurrentPrice / landConsultPrice).toFixed(2);
                $("#" + detailInfo.prototype.config().frm).find('[name=landRealizationRatios]').attr('data-value', landRealizationRatios);
                AssessCommon.elementParsePercent($("#" + detailInfo.prototype.config().frm).find('[name=landRealizationRatios]'));
            } else {
                $("#" + detailInfo.prototype.config().frm).find('[name=landRealizationRatios]').val('');
            }
        },
        getRealizationCycle: function () {
            var landNegotiatedDate = $("#landNegotiatedDate").val();
            var landAssessStandardDate = $("#landAssessStandardDate").val();
            var houseNegotiatedDate = $("#houseNegotiatedDate").val();
            var houseAssessStandardDate = $("#houseAssessStandardDate").val();

            if (landNegotiatedDate && landAssessStandardDate) {
                $("#landRealizationCycle").val(getDateDiff(landAssessStandardDate, landNegotiatedDate));
            } else {
                $("#landRealizationCycle").val('');
            }
            if (houseNegotiatedDate && houseAssessStandardDate) {
                $("#houseRealizationCycle").val(getDateDiff(houseAssessStandardDate, houseNegotiatedDate));
            } else {
                $("#houseRealizationCycle").val('');
            }
        },
    }

</script>


</html>
