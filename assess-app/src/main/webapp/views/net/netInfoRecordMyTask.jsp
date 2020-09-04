<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body>
<div class="wrapper">
    <%@include file="/views/share/main_navigation.jsp" %>
    <%@include file="/views/share/main_head.jsp" %>
    <div class="main-panel">
        <div class="content">
            <div class="panel-header bg-primary-gradient">
                <div class="page-inner py-5">
                </div>
            </div>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">${baseViewDto.currentMenu.name}</div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frmQuery" class="form-horizontal">
                                    <div class="form-group form-inline">
                                        <label class="col-md-1 col-form-label">内容</label>
                                        <div class="col-md-3 p-0">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="内容" id="queryContent" name="queryContent"
                                                   class="form-control input-full">
                                        </div>
                                        <label class="col-md-1 col-form-label">标题</label>
                                        <div class="col-md-3 p-0">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="标题" id="queryTitle" name="queryTitle"
                                                   class="form-control input-full">
                                        </div>
                                        <label class="col-md-1 col-form-label">网站</label>
                                        <div class="col-md-3 p-0">
                                            <select class="form-control input-full" required id="queryWebName">
                                                <option value="">--请选择--</option>
                                                <c:if test="${not empty webTypes}">
                                                    <c:forEach items="${webTypes}" var="item">
                                                        <option value="${item}">${item}</option>
                                                    </c:forEach>
                                                </c:if>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group form-inline">
                                        <label class="col-md-1 col-form-label">省</label>
                                        <div class="col-md-3 p-0">
                                            <select id="province" name="province"
                                                    class="form-control input-full search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                        <label class="col-md-1 col-form-label">市</label>
                                        <div class="col-md-3 p-0">
                                            <select id="city" name="city"
                                                    class="form-control input-full search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                        <label class="col-md-1 col-form-label">类型</label>
                                        <div class="col-md-3 p-0">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="类型" id="queryType" name="queryType"
                                                   class="form-control input-full">
                                        </div>
                                    </div>
                                    <div class="form-group form-inline">
                                        <label class="col-md-1 col-form-label">开始日期</label>
                                        <div class="col-md-3 p-0">
                                            <input placeholder="开始日期" id="queryStartTime" data-date-format="yyyy-mm-dd"
                                                   class="form-control input-full date-picker dbdate roomTime">
                                        </div>
                                        <label class="col-md-1 col-form-label">结束日期</label>
                                        <div class="col-md-3 p-0">
                                            <input placeholder="结束日期" id="queryEndTime" data-date-format="yyyy-mm-dd"
                                                   class="form-control input-full date-picker dbdate roomTime">
                                        </div>
                                        <label class="col-md-1 col-form-label">状态</label>
                                        <div class="col-md-3 p-0">
                                            <select class="form-control input-full" required id="queryStatus">
                                                <option value="">--请选择--</option>
                                                <option value="1">未填写</option>
                                                <option value="2">已填写</option>
                                                <option value="3">审批中</option>
                                                <option value="4">审批完成</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group form-inline">
                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="detailInfo.prototype.loadDataDicList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                        <button style="margin-left: 10px" type="button" class="btn btn-primary btn-sm"
                                                onclick="detailInfo.prototype.assignTask()">
                                            发起审批
                                        </button>
                                        <button style="margin-left: 10px" type="button" class="btn btn-primary btn-sm"
                                                onclick="window.open('${pageContext.request.contextPath}/netInfoAssignTask/applyOffline')">
                                            线下案例
                                        </button>
                                        <button style="margin-left: 10px" type="button" class="btn btn-warning btn-sm"
                                                onclick="detailInfo.prototype.backTask()">
                                            取消认领任务
                                        </button>
                                        <button style="margin-left: 10px" type="button" class="btn btn-warning btn-sm"
                                                onclick="detailInfo.prototype.closeAllModal()">
                                            关闭任务
                                        </button>
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
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>

</body>


<div id="divBoxTableList" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">数据列表</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card-body">
                            <form id="frmAdd" class="form-horizontal">
                                <input type="hidden" name="masterId">
                                <button type="button" class="btn btn-success btn-sm" id="addBtn" href="#divBoxFather"
                                        onclick="detailInfo.prototype.addInit()"
                                        data-toggle="modal">
                                    <span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                    新增
                                </button>
                                <div class="x_title">房产</div>
                                <div class="x_content">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <table class="table table-bordered" id="houseHistory">
                                                <!-- cerare document add ajax data-->
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <div class="x_title">土地</div>

                                <div class="x_content">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <table class="table table-bordered" id="landHistory">
                                                <!-- cerare document add ajax data-->
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>

        </div>
    </div>
</div>


<div id="divBoxClose" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">关闭原因</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="closeFrm" class="form-horizontal">
                    <input type="hidden" id="id" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                关闭原因
                                            </label>
                                            <div class="col-sm-11">
                                           <textarea placeholder="关闭原因" name="closeReason" id="closeReason" required
                                                     class="form-control input-full"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="detailInfo.prototype.closeItem()">
                    确认关闭
                </button>
            </div>

        </div>
    </div>
</div>

<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 80%">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">信息补全</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmFather" class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <table class="table table-bordered" id="singleData">
                                            <!-- cerare document add ajax data-->
                                        </table>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <input type="hidden" name="masterId">
                                    <input type="hidden" name="id">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 col-form-label">
                                                类型<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-2">
                                                <select class="form-control input-full" name="type">
                                                    <option value="">-请选择-</option>
                                                    <option value="房产">房产</option>
                                                    <option value="土地">土地</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="landContent" style="display: none">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    省
                                                </label>
                                                <div class="col-sm-2">
                                                    <select name="province" id="landProvince"
                                                            class="form-control input-full search-select select2">
                                                    </select>
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    市
                                                </label>
                                                <div class="col-sm-2">
                                                    <select name="city" id="landCity"
                                                            class="form-control input-full search-select select2">
                                                    </select>
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    区
                                                </label>
                                                <div class="col-sm-2">
                                                    <select name="district" id="landDistrict"
                                                            class="form-control input-full search-select select2">
                                                    </select>
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    街道
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="street" class="form-control input-full">
                                                </div>

                                            </div>
                                        </div>

                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    宗地位置
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="parcelSite"
                                                           class="form-control input-full">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    地块名称
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="name" class="form-control input-full">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    宗地编号
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="parcelNumber"
                                                           class="form-control input-full">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    土地性质
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="landPurpose" placeholder="出让、划拨"
                                                           class="form-control input-full">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    土地类型
                                                </label>
                                                <div class="col-sm-2">
                                                    <div class="input-group">
                                                        <input type="text" name="belongType" class="form-control"
                                                               list="landUseList">
                                                        <datalist id="landUseList">

                                                        </datalist>
                                                        <div class="input-group-prepend">
                                                            <button class="btn btn-warning btn-sm "
                                                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                    type="button"
                                                                    onclick="$(this).closest('.input-group').find('input').val('');">
                                                                清空
                                                            </button>
                                                        </div>

                                                    </div>
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    土地类别
                                                </label>
                                                <div class="col-sm-2">
                                                    <div class="input-group">
                                                        <input type="text" name="belongCategory" class="form-control"
                                                               list="landUseCategoryList">
                                                        <datalist id="landUseCategoryList">

                                                        </datalist>
                                                        <div class="input-group-prepend">
                                                            <button class="btn btn-warning btn-sm "
                                                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                    type="button"
                                                                    onclick="$(this).closest('.input-group').find('input').val('');">
                                                                清空
                                                            </button>
                                                        </div>

                                                    </div>
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    交易方式
                                                </label>
                                                <div class="col-sm-2">
                                                    <select name="dealType"
                                                            class="form-control input-full search-select select2">
                                                    </select>
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    成交(协商)日期
                                                </label>
                                                <div class="col-sm-2">
                                                    <input name="negotiatedDate" data-date-format="yyyy-mm-dd"
                                                           onchange="detailInfo.prototype.getRealizationCycle()"
                                                           id="landNegotiatedDate"
                                                           class="form-control input-full date-picker dbdate">
                                                </div>

                                            </div>
                                        </div>

                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    面积
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                           id="landArea"
                                                           name="area" class="form-control input-full"
                                                           onblur="detailInfo.prototype.getUnitPrice()"></div>
                                                <label class="col-sm-1 col-form-label">
                                                    单位
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="areaUnit" id="areaUnit" placeholder="平方米、亩"
                                                           class="form-control input-full"
                                                           onblur="detailInfo.prototype.getUnitPrice()">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    净用地面积
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                           name="landArea" class="form-control input-full">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    净用地面积单位
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="landAreaUnit" placeholder="平方米、亩"
                                                           class="form-control input-full">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    成交总价（万元）
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                           id="landCurrentPrice"
                                                           name="currentPrice" class="form-control input-full"
                                                           onblur="detailInfo.prototype.getUnitPrice()">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    成交单价（元/㎡）
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                           name="unitPrice" class="form-control input-full"
                                                           id="landUnitPrice"
                                                           onblur="detailInfo.prototype.getHouseRealizationRatios()">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    成交单价（万元/每亩）
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                           name="unitPriceMu" class="form-control input-full"
                                                           id="unitPriceMu">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    成交楼面地价（元/㎡）
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                           name="floorPrice" class="form-control input-full">
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    评估基准日期
                                                </label>
                                                <div class="col-sm-2">
                                                    <input name="assessStandardDate" data-date-format="yyyy-mm-dd"
                                                           onchange="detailInfo.prototype.getRealizationCycle()"
                                                           id="landAssessStandardDate"
                                                           class="form-control input-full date-picker dbdate">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    评估起拍单价（元/㎡）
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                           name="consultPrice" id="landConsultPrice"
                                                           class="form-control input-full"
                                                           onblur="detailInfo.prototype.getHouseRealizationRatios()">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    评估起拍单价（万元/每亩）
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                           name="consultPriceMu" id="consultPriceMu"
                                                           class="form-control input-full">
                                                </div>


                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    变现周期
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="realizationCycle" id="landRealizationCycle"
                                                           class="form-control input-full">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    变现率
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="landRealizationRatios"
                                                           onfocus="detailInfo.prototype.getHouseRealizationRatios()"
                                                           class="form-control input-full x-percent">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    容积率
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="plotRatio" data-rule-number="true"
                                                           data-rule-maxlength="50" class="form-control input-full">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    容积率说明
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="plotRatioRemark"
                                                           class="form-control input-full">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    绿化率
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="greeningRate"
                                                           class="form-control input-full x-percent">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    绿化率说明
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="greeningRateRemark"
                                                           class="form-control input-full"></div>
                                                <label class="col-sm-1 col-form-label">
                                                    建筑密度
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="buildDensity"
                                                           class="form-control input-full x-percent"></div>
                                                <label class="col-sm-1 col-form-label">
                                                    建筑密度说明
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="buildDensityRemark"
                                                           class="form-control input-full"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    建筑高度(米)
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="buildHeight"
                                                           class="form-control input-full"
                                                           data-rule-number="true" data-rule-maxlength="50">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    建筑高度说明
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="buildHeightRemark"
                                                           class="form-control input-full">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    指标款(亩)
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="indexAmount"
                                                           class="form-control input-full"
                                                           data-rule-number="true" data-rule-maxlength="50"></div>
                                                <label class="col-sm-1 col-form-label">
                                                    指标款说明
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="indexAmountRemark"
                                                           class="form-control input-full">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    成交对象概况
                                                </label>
                                                <div class="col-sm-11">
                                                 <textarea name="dealPartInfo" id="landDealPartInfo"
                                                           class="form-control input-full"></textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    附件
                                                </label>
                                                <div class="col-sm-11">
                                                    <input id="uploadLandFile" type="file" multiple="false">
                                                    <div id="_uploadLandFile"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="houseContent" style="display: none">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    省
                                                </label>
                                                <div class="col-sm-2">
                                                    <select name="province" id="houseProvince"
                                                            class="form-control input-full search-select select2">
                                                    </select>
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    市
                                                </label>
                                                <div class="col-sm-2">
                                                    <select name="city" id="houseCity"
                                                            class="form-control input-full search-select select2">
                                                    </select>
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    区
                                                </label>
                                                <div class="col-sm-2">
                                                    <select name="district" id="houseDistrict"
                                                            class="form-control input-full search-select select2">
                                                    </select>
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    房产类型
                                                </label>
                                                <div class="col-sm-2">
                                                    <div class="input-group">
                                                        <input type="text" name="belongType" class="form-control"
                                                               list="houseUseList">
                                                        <datalist id="houseUseList">

                                                        </datalist>

                                                        <div class="input-group-prepend">
                                                            <button class="btn btn-warning btn-sm "
                                                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                    type="button"
                                                                    onclick="$(this).closest('.input-group').find('input').val('');">
                                                                清空
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
                                                <label class="col-sm-1 col-form-label">
                                                    房产类别
                                                </label>
                                                <div class="col-sm-2">
                                                    <div class="input-group">
                                                        <input type="text" name="belongCategory" class="form-control"
                                                               list="houseUseCategoryList">
                                                        <datalist id="houseUseCategoryList">

                                                        </datalist>
                                                        <div class="input-group-prepend">
                                                            <button class="btn btn-warning btn-sm "
                                                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                    type="button"
                                                                    onclick="$(this).closest('.input-group').find('input').val('');">
                                                                清空
                                                            </button>
                                                        </div>

                                                    </div>
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    街道
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="street" class="form-control input-full">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    面积
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                           id="houseArea"
                                                           name="area" class="form-control input-full"
                                                           onblur="detailInfo.prototype.getUnitPrice()"></div>
                                                <label class="col-sm-1 col-form-label">
                                                    楼盘名称
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="name" class="form-control input-full">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    楼栋号
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="buildingNumber"
                                                           class="form-control input-full">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    单元号
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="unitNumber"
                                                           class="form-control input-full"></div>
                                                <label class="col-sm-1 col-form-label">
                                                    房号
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="houseNumber"
                                                           class="form-control input-full">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    交易方式
                                                </label>
                                                <div class="col-sm-2">
                                                    <select name="dealType"
                                                            class="form-control input-full search-select select2">
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    成交总价
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                           id="houseCurrentPrice"
                                                           name="currentPrice" class="form-control input-full"
                                                           onblur="detailInfo.prototype.getUnitPrice()">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    成交(协商)日期
                                                </label>
                                                <div class="col-sm-2">
                                                    <input name="negotiatedDate" data-date-format="yyyy-mm-dd"
                                                           onchange="detailInfo.prototype.getRealizationCycle()"
                                                           id="houseNegotiatedDate"
                                                           class="form-control input-full date-picker dbdate">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    评估总价
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                           name="consultPrice" id="houseConsultPrice"
                                                           class="form-control input-full"
                                                           onblur="detailInfo.prototype.getHouseRealizationRatios()">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    评估基准日期
                                                </label>
                                                <div class="col-sm-2">
                                                    <input name="assessStandardDate" data-date-format="yyyy-mm-dd"
                                                           onchange="detailInfo.prototype.getRealizationCycle()"
                                                           id="houseAssessStandardDate"
                                                           class="form-control input-full date-picker dbdate">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    成交单价
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                           name="unitPrice" class="form-control input-full"
                                                           id="houseUnitPrice">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    变现率
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="houseRealizationRatios"
                                                           onfocus="detailInfo.prototype.getHouseRealizationRatios()"
                                                           class="form-control input-full x-percent">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    变现周期
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="realizationCycle"
                                                           id="houseRealizationCycle"
                                                           class="form-control input-full">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    交易类型
                                                </label>
                                                <div class="col-sm-2">
                                                    <select name="tradingType"
                                                            class="form-control input-full search-select select2">
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    限购状态
                                                </label>
                                                <div class="col-sm-2">
                                                    <select class="form-control input-full" required
                                                            name="purchaseLimitStatus">
                                                        <option value="">--请选择--</option>
                                                        <option value="限购">限购</option>
                                                        <option value="不限购">不限购</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    成交对象概况
                                                </label>
                                                <div class="col-sm-11">
                                             <textarea name="dealPartInfo" id="houseDealPartInfo"
                                                       class="form-control input-full"></textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    附件
                                                </label>
                                                <div class="col-sm-11">
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
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>

                <button type="button" style="display:none" class="btn btn-primary btn-sm landContent"
                        onclick="detailInfo.prototype.saveLandDetail()">
                    保存
                </button>
                <button type="button" style="display:none" class="btn btn-primary btn-sm houseContent"
                        onclick="detailInfo.prototype.saveHouseDetail()">
                    保存
                </button>
            </div>


        </div>
    </div>
</div>


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
            cols.push({field: 'title', title: '标题', width: '20%'});
            cols.push({field: 'province', title: '省', width: '5%'});
            cols.push({field: 'city', title: '市', width: '5%'});
            cols.push({
                field: 'sourceSiteName', title: '来源网站', width: '28%', formatter: function (value, row, index) {
                    var str = '<a href="' + row.sourceSiteUrl + '" target="_blank" >' + row.sourceSiteName + '</a>';
                    str += '<br/>(' + row.sourceSiteUrl + ')';
                    return str;
                }
            });
            cols.push({field: 'type', title: '类型', width: '10%'});
            cols.push({
                field: 'beginTime', title: '开始时间', width: '10%', formatter: function (value, row, index) {
                    return formatDate(row.beginTime, false);
                }
            });
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
                field: 'id', width: '9%', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button onclick="detailInfo.prototype.showTableListModal(' + row.id + ',' + row.status + ')" style="margin-left: 5px;" class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="补全信息">';
                    str += '<i class="fa fa-pen"></i>';
                    str += '</button>';

                    str += '<button onclick="detailInfo.prototype.openItem(' + index + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看网址">';
                    str += '<i class="fa fa-eye"></i>';
                    str += '</button>';

                    str += '<button onclick="detailInfo.prototype.closeModal(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="关闭">';
                    str += '<i class="fa fa-times"></i>';
                    str += '</button>';
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
                queryEndTime: $("#queryEndTime").val(),
                queryStatus: $("#queryStatus").val(),
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
        showTableListModal: function (masterId, status) {
            detailInfo.prototype.loadLandHistoryList(masterId);
            detailInfo.prototype.loadHouseHistoryList(masterId);
            $("#frmAdd").find("input[name='masterId']").val(masterId);
            if (status == 3 || status == 4) {
                $("#frmAdd").find("#addBtn").hide();
            } else {
                $("#frmAdd").find("#addBtn").show();
            }
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
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showContent: function (type, result, id) {
            //加载数据
            if (type == result.data.type) {
                $("#" + detailInfo.prototype.config().frm).initForm(result.data);
                AssessCommon.initAreaInfo({
                    provinceTarget: $("#houseProvince"),
                    cityTarget: $("#houseCity"),
                    districtTarget: $("#houseDistrict"),
                    provinceValue: result.data.province,
                    cityValue: result.data.city,
                    districtValue: result.data.district
                })
                AssessCommon.initAreaInfo({
                    provinceTarget: $("#landProvince"),
                    cityTarget: $("#landCity"),
                    districtTarget: $("#landDistrict"),
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
                $("#" + detailInfo.prototype.config().box).find(".houseContent").show();
                $("#" + detailInfo.prototype.config().frm).find(".houseContent").find("input").attr("disabled", false);
                $("#" + detailInfo.prototype.config().frm).find(".houseContent").find("select").attr("disabled", false);
                $("#" + detailInfo.prototype.config().box).find(".landContent").hide();
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
                    AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseTransactionType, result.data.tradingType, function (html, data) {
                        $("#" + detailInfo.prototype.config().frm).find("select[name='tradingType']").empty().html(html).trigger('change');
                    });
                } else {
                    AssessCommon.loadDataDicByKey(AssessDicKey.dataDealType, '', function (html, data) {
                        $("#" + detailInfo.prototype.config().frm).find("select[name='dealType']").empty().html(html).trigger('change');
                    });
                    AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseTransactionType, '', function (html, data) {
                        $("#" + detailInfo.prototype.config().frm).find("select[name='tradingType']").empty().html(html).trigger('change');
                    });
                }
                //加载附件
                detailInfo.prototype.showFile("uploadHouseFile", "tb_net_info_record_house", tableId);
                detailInfo.prototype.fileUpload("uploadHouseFile", "tb_net_info_record_house", tableId);
            } else if (type == '土地') {
                $("#" + detailInfo.prototype.config().box).find(".landContent").show();
                $("#" + detailInfo.prototype.config().frm).find(".landContent").find("input").attr("disabled", false);
                $("#" + detailInfo.prototype.config().frm).find(".landContent").find("select").attr("disabled", false);
                $("#" + detailInfo.prototype.config().box).find(".houseContent").hide();
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
                    $("#" + detailInfo.prototype.config().frm).find('[name=buildDensity]').attr('data-value', result.data.buildDensity);
                    AssessCommon.elementParsePercent($("#" + detailInfo.prototype.config().frm).find('[name=buildDensity]'));

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
                $("#" + detailInfo.prototype.config().box).find(".houseContent").hide();
                $("#" + detailInfo.prototype.config().box).find(".landContent").hide();
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
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        $('#' + detailInfo.prototype.config().box).modal('hide');
                        detailInfo.prototype.loadLandHistoryList(masterId);
                        detailInfo.prototype.updateLandStatus(result.data.id);
                    } else {
                        AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
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
                        $("#" + detailInfo.prototype.config().table).bootstrapTable('updateByUniqueId', {
                            id: result.data.id,
                            row: result.data
                        });
                    } else {
                        AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
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
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        $('#' + detailInfo.prototype.config().box).modal('hide');
                        detailInfo.prototype.loadHouseHistoryList(masterId);
                        detailInfo.prototype.updateHouseStatus(result.data.id);
                    } else {
                        AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
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
                        $("#" + detailInfo.prototype.config().table).bootstrapTable('updateByUniqueId', {
                            id: result.data.id,
                            row: result.data
                        });
                    } else {
                        AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        assignTask: function () {
            var rows = $('#transaction_List').bootstrapTable('getSelections');
            if (rows && rows.length > 0) {
                var idArray = [];
                $.each(rows, function (i, item) {
                    if (item.status == 1 || item.status == 2) {
                        idArray.push(item.id);
                    }
                })
                var ids = idArray.join();
                $("#selectIds").val(ids);
                //确认
                var href = "${pageContext.request.contextPath}/netInfoAssignTask/apply";
                href += "?ids=" + ids;
                window.open(href, "");
            } else {
                notifyInfo('提示', '请选择要审批的数据');
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
                    str += '<a  class="btn btn-xs btn-info tooltips"  data-placement="top" data-original-title="查看网址" target="_blank" href="' + row.sourceSiteUrl + '" ><i class="fa fa-eye"></i></a>';
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
                field: 'info1', title: '信息1', formatter: function (value, row, index) {
                    var result = '';
                    var str = AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
                    if (row.street) {
                        str += row.street;
                    }
                    if (str) {
                        result += '位置：' + str + '<br/>';
                    }
                    if (row.parcelSite) {
                        result += '宗地位置：' + row.parcelSite + '<br/>';
                    }
                    if (row.name) {
                        result += '地块名称：' + row.name + '<br/>';
                    }
                    if (row.parcelNumber) {
                        result += '宗地编号：' + row.parcelNumber + '<br/>';
                    }
                    if (row.landPurpose) {
                        result += '土地性质：' + row.landPurpose + '<br/>';
                    }
                    if (row.belongType) {
                        result += '土地类型：' + row.belongType + '<br/>';
                    }
                    if (row.belongCategory) {
                        result += '土地类别：' + row.belongCategory + '<br/>';
                    }
                    if (row.dealType) {
                        result += '交易方式：' + row.dealTypeName + '<br/>';
                    }
                    return result;
                }
            });

            cols.push({
                field: 'info2', title: '信息2', formatter: function (value, row, index) {
                    var result = '';
                    if (row.negotiatedDate) {
                        result += '成交(协商)日期：' + formatDate(row.negotiatedDate) + '<br/>';
                    }
                    if (row.area) {
                        result += '面积：' + row.area + '<br/>';
                    }
                    if (row.areaUnit) {
                        result += '单位：' + row.areaUnit + '<br/>';
                    }
                    if (row.landArea) {
                        result += '净用地面积：' + row.landArea + '<br/>';
                    }
                    if (row.landAreaUnit) {
                        result += '净用地面积单位：' + row.landAreaUnit + '<br/>';
                    }
                    if (row.currentPrice) {
                        result += '成交总价（万元）：' + row.currentPrice + '<br/>';
                    }
                    if (row.unitPrice) {
                        result += '成交单价（元/㎡）：' + row.unitPrice + '<br/>';
                    }
                    if (row.unitPriceMu) {
                        result += '成交单价（万元/每亩）：' + row.unitPriceMu + '<br/>';
                    }
                    return result;
                }
            });
            cols.push({
                field: 'info3', title: '信息3', formatter: function (value, row, index) {
                    var result = '';
                    if (row.floorPrice) {
                        result += '成交楼面地价（元/㎡）：' + row.floorPrice + '<br/>';
                    }
                    if (row.assessStandardDate) {
                        result += '评估基准日：' + formatDate(row.assessStandardDate) + '<br/>';
                    }
                    if (row.consultPrice) {
                        result += '评估起拍单价（元/㎡）：' + row.consultPrice + '<br/>';
                    }
                    if (row.consultPriceMu) {
                        result += '评估起拍单价（万元/每亩）：' + row.consultPriceMu + '<br/>';
                    }
                    if (row.realizationCycle) {
                        result += '变现周期：' + row.realizationCycle + '<br/>';
                    }
                    if (row.landRealizationRatios) {
                        result += '变现率：' + row.landRealizationRatios * 100 + '%<br/>';
                    }
                    if (row.plotRatio) {
                        result += '容积率：' + row.plotRatio + '<br/>';
                    }
                    if (row.plotRatioRemark) {
                        result += '容积率说明：' + row.plotRatioRemark + '<br/>';
                    }
                    return result;
                }
            });
            cols.push({
                field: 'info4', title: '信息4', formatter: function (value, row, index) {
                    var result = '';
                    if (row.greeningRate) {
                        result += '绿化率：' + row.greeningRate * 100 + '%<br/>';
                    }
                    if (row.greeningRateRemark) {
                        result += '绿化率说明：' + row.greeningRateRemark + '<br/>';
                    }
                    if (row.buildDensity) {
                        result += '建筑密度：' + row.buildDensity * 100 + '%<br/>';
                    }
                    if (row.buildDensityRemark) {
                        result += '建筑密度说明：' + row.buildDensityRemark + '<br/>';
                    }
                    if (row.buildHeight) {
                        result += '建筑高度(米)：' + row.buildHeight + '<br/>';
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
                    if (row.dealPartInfo) {
                        result += '成交对象概况：' + row.dealPartInfo + '<br/>';
                    }
                    return result;
                }
            });
            cols.push({field: 'fileViewName', title: '附件'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    if (row.status == 0) {
                        var str = '<div class="btn-margin">';
                        str += '<button type="button" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="detailInfo.prototype.getAndInitLand(' + row.id + ')"><i class="fa fa-pen"></i></button>';
                        str += '<button type="button" class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="删除" onclick="detailInfo.prototype.deleteLandItem(' + row.id + ')"><i class="fa fa-minus"></i></button>';
                        str += '</div>';
                        return str;
                    } else {
                        return '-';
                    }
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
                        detailInfo.prototype.showContent(result.data.type, result, masterId);
                        $("#" + detailInfo.prototype.config().frm).find("select[name='type']").prop("disabled", true);
                        $('#' + detailInfo.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        deleteLandItem: function (id) {
            var masterId = $("#frmAdd").find("input[name='masterId']").val();
            AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/netInfoRecordLand/delete",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除数据成功");
                            detailInfo.prototype.loadLandHistoryList(masterId);
                        } else {
                            AlertError("失败", "删除数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                    }
                })
            });
        },
        loadHouseHistoryList: function (masterId) {
            var cols = [];
            cols.push({
                field: 'info1', title: '信息1', formatter: function (value, row, index) {
                    var result = '';
                    var str = AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
                    if (row.street) {
                        str += row.street;
                    }
                    if (str) {
                        result += '位置：' + str + '<br/>';
                    }
                    if (row.belongType) {
                        result += '房产类型：' + row.belongType + '<br/>';
                    }
                    if (row.belongCategory) {
                        result += '房产类别：' + row.belongCategory + '<br/>';
                    }
                    if (row.area) {
                        result += '面积：' + row.area + '<br/>';
                    }

                    return result;
                }
            });
            cols.push({
                field: 'info2', title: '信息2', formatter: function (value, row, index) {
                    var result = '';
                    var str = '';
                    if (row.name) {
                        str += row.name;
                    }
                    if (row.buildingNumber) {
                        str += row.buildingNumber + "栋";
                    }
                    if (row.unitNumber) {
                        str += row.unitNumber + "单元";
                    }
                    if (row.houseNumber) {
                        str += row.houseNumber + "号";
                    }

                    if (str) {
                        result += '楼盘：' + str + '<br/>';
                    }
                    if (row.dealType) {
                        result += '交易方式：' + row.dealTypeName + '<br/>';
                    }
                    if (row.currentPrice) {
                        result += '成交总价：' + row.currentPrice + '<br/>';
                    }
                    if (row.negotiatedDate) {
                        result += '成交(协商)日期：' + formatDate(row.negotiatedDate) + '<br/>';
                    }
                    return result;
                }
            });

            cols.push({
                field: 'other3', title: '信息3', formatter: function (value, row, index) {
                    var result = '';

                    if (row.consultPrice) {
                        result += '评估总价：' + row.consultPrice + '<br/>';
                    }
                    if (row.assessStandardDate) {
                        result += '评估基准日期：' + formatDate(row.assessStandardDate) + '<br/>';
                    }
                    if (row.unitPrice) {
                        result += '成交单价：' + row.unitPrice + '<br/>';
                    }
                    if (row.houseRealizationRatios) {
                        result += '变现率：' + row.houseRealizationRatios * 100 + '%<br/>';
                    }
                    return result;
                }
            });
            cols.push({
                field: 'other4', title: '信息4', formatter: function (value, row, index) {
                    var result = '';
                    if (row.realizationCycle) {
                        result += '变现周期：' + row.realizationCycle + '<br/>';
                    }
                    if (row.tradingTypeName) {
                        result += '交易类型：' + row.tradingTypeName + '<br/>';
                    }
                    if (row.purchaseLimitStatus) {
                        result += '限购状态：' + row.purchaseLimitStatus + '<br/>';
                    }
                    if (row.dealPartInfo) {
                        result += '成交对象概况：' + row.dealPartInfo + '<br/>';
                    }
                    return result;
                }
            });
            cols.push({field: 'fileViewName', title: '附件'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    if (row.status == 0) {
                        var str = '<div class="btn-margin">';
                        str += '<button type="button" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="detailInfo.prototype.getAndInitHouse(' + row.id + ')"><i class="fa fa-pen"></i></button>';
                        str += '<button type="button" class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="删除" onclick="detailInfo.prototype.deleteHouseItem(' + row.id + ')"><i class="fa fa-minus"></i></button>';
                        str += '</div>';
                        return str;
                    } else {
                        return '-';
                    }
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
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        deleteHouseItem: function (id) {
            var masterId = $("#frmAdd").find("input[name='masterId']").val();
            AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/netInfoRecordHouse/delete",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除数据成功");
                            detailInfo.prototype.loadHouseHistoryList(masterId);
                        } else {
                            AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                    }
                })
            });
        },
        getUnitPrice: function () {
            var landCurrentPrice = Number($("#landCurrentPrice").val());
            var landArea = Number($("#landArea").val());
            var areaUnit = $("#areaUnit").val();

            var houseCurrentPrice = Number($("#houseCurrentPrice").val());
            var houseArea = Number($("#houseArea").val());
            if (houseCurrentPrice >= 0 && houseArea > 0) {
                var houseUnitPrice = (houseCurrentPrice / houseArea).toFixed(2);
                $("#houseUnitPrice").val(houseUnitPrice);
            } else {
                $("#houseUnitPrice").val("");
            }
            if (landCurrentPrice >= 0 && landArea > 0) {
                if (areaUnit && areaUnit == "亩") {
                    console.log(areaUnit + "=areaUnit")
                    var landUnitPrice = (landCurrentPrice / landArea).toFixed(2);
                    $("#unitPriceMu").val(landUnitPrice);
                    $("#landUnitPrice").val((landCurrentPrice * 10000 / landArea / AssessCommon.BHOU).toFixed(2));
                } else {
                    var landUnitPrice = (landCurrentPrice * 10000 / landArea).toFixed(2);
                    $("#landUnitPrice").val(landUnitPrice);
                    $("#unitPriceMu").val((landCurrentPrice / landArea * AssessCommon.BHOU).toFixed(2));
                }

            } else {
                $("#landUnitPrice").val("");
                $("#unitPriceMu").val("");
            }
            detailInfo.prototype.getHouseRealizationRatios();
        },
        getHouseRealizationRatios: function () {
            var landUnitPrice = Number($("#landUnitPrice").val());
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
            if (landUnitPrice >= 0 && landConsultPrice > 0) {
                var landRealizationRatios = (landUnitPrice / landConsultPrice).toFixed(2);
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
        backTask: function () {
            var rows = $('#transaction_List').bootstrapTable('getSelections');
            if (rows && rows.length > 0) {
                var idArray = [];
                $.each(rows, function (i, item) {
                    if (item.status != 3 && item.status != 4) {
                        idArray.push(item.id);
                    }
                })
                var ids = idArray.join();
                if (!ids) {
                    notifyInfo('提示', '至少选择一条非审批中或审批通过的任务');
                    return false;
                }
                AlertConfirm("确认取消任务", "取消后任务将退回", function () {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/netInfoAssignTask/backTask",
                        type: "post",
                        dataType: "json",
                        data: {ids: ids},
                        success: function (result) {
                            if (result.ret) {
                                notifySuccess("成功", "取消认领任务成功");
                                detailInfo.prototype.loadDataDicList();
                            } else {
                                AlertError("失败", "取消认领任务失败，失败原因:" + result.errmsg);
                            }
                        },
                        error: function (result) {
                            AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                        }
                    })
                });
            } else {
                notifyInfo('提示', '请选择要取消认领的任务');
            }
        },
        closeModal: function (id) {
            $("#closeFrm").clearAll();
            $("#closeFrm").find("input[name='id']").val(id);
            $('#divBoxClose').modal("show");
        },
        closeAllModal: function () {
            var rows = $('#transaction_List').bootstrapTable('getSelections');
            if (!rows || rows.length == 0) {
                notifyInfo('提示', '请选择要关闭的任务');
                return false;
            }
            var ids = [];
            $.each(rows, function (k, item) {
                ids.push(item.id);
            });
            $("#closeFrm").clearAll();
            $("#closeFrm").find("input[name='id']").val(ids.join(","));
            $('#divBoxClose').modal("show");
        },
        closeItem: function () {
            AlertConfirm("是否确认关闭", "关闭后任务将消失", function () {
                if (!$("#closeFrm").valid()) {
                    return false;
                }
                var closeReason = $("#closeFrm").find("#closeReason").val();
                var id = $("#closeFrm").find("input[name='id']").val();
                $.ajax({
                    url: "${pageContext.request.contextPath}/netInfoRecordController/closeItem",
                    type: "post",
                    dataType: "json",
                    data: {
                        id: id,
                        closeReason: closeReason
                    },
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "关闭成功");
                            $('#divBoxClose').modal('hide');
                            detailInfo.prototype.loadDataDicList();
                        } else {
                            AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                    }
                })
            });
        },
        getMuPrict: function (_this, targeName) {
            var val = $(_this).val();
            if (val && AssessCommon.isNumber(val)) {
                $(_this).closest('.form-group').find('[name=' + targeName + ']').val(parseFloat(val) * 666.67 / 10000).toFixed(4);
            }
        }
    }

</script>


</html>
