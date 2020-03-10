<%--
  Created by IntelliJ IDEA.
  User: huhao
  Date: 2018/01/29
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>案例整理申请</title>

    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        案列整理
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="master_form" class="form-horizontal">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <input type="hidden" name="id" value="${netInfoAssignTask.id}">

                                            <table class="table table-bordered" id="transaction_List">
                                                <!-- cerare document add ajax data-->
                                            </table>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>


                    <!-- 公共尾部模块引用 -->
                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">

                        <div class="card-body">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                取消
                            </button>

                            <button id="commit_btn" class="btn btn-success" onclick="masterObj.commit();">
                                提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                            </button>
                        </div>
                    </div>
                    <c:if test="${processInsId != 0}">
                        <%@include file="/views/share/form_log.jsp" %>
                        <form id="process_variable_form">
                            <%@include file="/views/share/form_edit.jsp" %>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>


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
                                <p>
                                    <button type="button" class="btn btn-success btn-sm" id="addBtn"
                                            href="#divBoxFather"
                                            onclick="detailInfo.prototype.addInit()"
                                            data-toggle="modal">
                                    <span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                        新增
                                    </button>
                                </p>
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
                                                           class="form-control input-full" onblur="detailInfo.prototype.getUnitPrice()">
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
                                                           class="form-control input-full"
                                                           data-rule-number="true" data-rule-maxlength="50"></div>
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

</body>
</html>

<script type="text/javascript">
    $(function () {
        detailInfo.prototype.loadDataDicList();
    })


    var masterObj = {};


    /**
     * 提交数据
     * @returns {*}
     */
    masterObj.commit = function () {
        if ("${processInsId}" == "0") {
            //申请
            commit();
        } else {
            //修改提交
            editCommit();
        }
    }

    //申请提交
    function commit() {
        var result = $("#transaction_List").bootstrapTable('getData');
        if (result.length == 0) {
            notifySuccess('成功','至少添加一条数据');
            return false;
        }

        var id = $("#master_form").find("input[name='id']").val();
        console.log(id+'==')
        Loading.progressShow("正在提交数据...");
        $.ajax({
            url: "${pageContext.request.contextPath}/netInfoAssignTask/applyCommit",
            type: "post",
            data: {id:id},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "提交数据成功", function () {
                        window.close();
                    });
                }
                else {
                    AlertError("失败","提交数据失败","失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    };

    //返回修改
    function editCommit() {
        var result = $("#transaction_List").bootstrapTable('getData');
        if (result.length == 0) {
            notifySuccess('成功','至少添加一条数据');
            return false;
        }
        var data = formParams("master_form");

        //返回修改要提交的数据
        var approvalModelDto = formSerializeArray($("#process_variable_form"));
        approvalModelDto.businessDataJson = JSON.stringify(data);
        Loading.progressShow("正在提交数据...");
        $.ajax({
            url: "${pageContext.request.contextPath}/netInfoAssignTask/editCommit",
            type: "post",
            data: approvalModelDto,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "提交数据成功", function () {
                        window.close();
                    });
                }
                else {
                    AlertError("失败","提交数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    };


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
            cols.push({
                field: 'sourceSiteName', title: '来源网站', width: '10%', formatter: function (value, row, index) {
                    var str = '<a href="' + row.sourceSiteUrl + '" target="_blank" >' + row.sourceSiteName + '</a>';
                    str += '<br/>(' + row.sourceSiteUrl + ')';
                    return str;
                }
            });
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
            cols.push({
                field: 'id', width: '6%', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="补全信息" onclick="detailInfo.prototype.showTableListModal(' + row.id + ')"><i class="fa fa-pen fa-white"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="查看网址" onclick="detailInfo.prototype.openItem(' + index + ')"><i class="fa fa-eye fa-white"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + detailInfo.prototype.config().table).bootstrapTable('destroy');
            TableInit(detailInfo.prototype.config().table, "${pageContext.request.contextPath}/netInfoRecordController/getInfoRecordListByIds", cols, {
                ids: '${netInfoAssignTask.netInfoIds}'
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
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
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
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
                    changeStatus: false
                },
                success: function (result) {
                    if (result.ret) {
                        notifySuccess('成功', '保存成功');
                        $('#' + detailInfo.prototype.config().box).modal('hide');
                        detailInfo.prototype.loadLandHistoryList(masterId);
                    }
                    else {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
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
                    changeStatus: false
                },
                success: function (result) {
                    if (result.ret) {
                        notifySuccess('成功', '保存成功');
                        $('#' + detailInfo.prototype.config().box).modal('hide');
                        detailInfo.prototype.loadHouseHistoryList(masterId);
                    }
                    else {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        },
        loadOnclickData: function (id) {
            var cols = [];
            cols.push({field: 'title', title: '标题', width: '15%'});
            cols.push({field: 'province', title: '省', width: '5%'});
            cols.push({field: 'city', title: '市', width: '5%'});
            cols.push({
                field: 'sourceSiteName', title: '来源网站', width: '10%', formatter: function (value, row, index) {
                    var str = '<a href="' + row.sourceSiteUrl + '" target="_blank" >' + row.sourceSiteName + '</a>';
                    str += '<br/>(' + row.sourceSiteUrl + ')';
                    return str;
                }
            });
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
            cols.push({field: 'name', title: '地块名称'});
            cols.push({field: 'dealTypeName', title: '交易方式'});
            cols.push({field: 'currentPrice', title: '成交价'});
            cols.push({field: 'unitPrice', title: '单价'});
            cols.push({field: 'floorPrice', title: '楼面地价'});
            cols.push({field: 'landArea', title: '净用地面积'});
            cols.push({field: 'fileViewName', title: '附件'});
            cols.push({
                field: 'id', width: '6%', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="detailInfo.prototype.getAndInitLand(' + row.id + ')"><i class="fa fa-pen fa-white"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="删除" onclick="detailInfo.prototype.deleteLandItem(' + row.id + ')"><i class="fa fa-minus fa-white"></i></button>';
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
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
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
                            notifySuccess('成功', '删除成功');
                            detailInfo.prototype.loadLandHistoryList(masterId);
                        }
                        else {
                            AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
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

            cols.push({
                field: 'name', title: '楼盘', formatter: function (value, row, index) {
                    var result = '';
                    if (row.name) {
                        result += row.name;
                    }
                    if (row.buildingNumber) {
                        result += row.buildingNumber + "栋";
                    }
                    if (row.unitNumber) {
                        result += row.unitNumber + "单元";
                    }
                    if (row.houseNumber) {
                        result += row.houseNumber+ "号";
                    }
                    return result;
                }
            });
            cols.push({field: 'currentPrice', title: '成交总价'});
            cols.push({field: 'consultPrice', title: '评估总价'});
            cols.push({field: 'dealTypeName', title: '交易方式'});
            cols.push({field: 'dealTypeName', title: '交易方式'});
            cols.push({field: 'tradingTypeName', title: '交易类型'});
            cols.push({field: 'purchaseLimitStatus', title: '限购状态'});
            cols.push({field: 'fileViewName', title: '附件'});
            cols.push({
                field: 'id', width: '6%', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="detailInfo.prototype.getAndInitHouse(' + row.id + ')"><i class="fa fa-pen fa-white"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="删除" onclick="detailInfo.prototype.deleteHouseItem(' + row.id + ')"><i class="fa fa-minus fa-white"></i></button>';
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
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
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
                            notifySuccess('成功', '删除成功');
                            detailInfo.prototype.loadHouseHistoryList(masterId);
                        }
                        else {
                            AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
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
                if(areaUnit&&areaUnit=="亩"){
                    console.log(areaUnit+"=areaUnit")
                    var landUnitPrice = (landCurrentPrice / landArea).toFixed(2);
                    $("#unitPriceMu").val(landUnitPrice);
                    $("#landUnitPrice").val((landCurrentPrice* 10000 / landArea / AssessCommon.BHOU).toFixed(2));
                }else {
                    var landUnitPrice = (landCurrentPrice * 10000/ landArea).toFixed(2);
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
    }
</script>
