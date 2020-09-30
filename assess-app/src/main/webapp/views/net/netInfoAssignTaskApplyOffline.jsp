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
                                <form id="frmAdd" class="form-horizontal">
                                    <input type="hidden" name="masterId">
                                    <div class="row form-group">
                                        <div class="col-xs-8  col-sm-8  col-md-8  col-lg-8">
                                            <button type="button" class="btn btn-success btn-sm"
                                                    onclick="masterObj.showHouseBox()"
                                                    data-toggle="modal">
                                    <span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                                新增房产
                                            </button>

                                            <button type="button" class="btn btn-success btn-sm"
                                                    onclick="masterObj.showLandBox()"
                                                    data-toggle="modal">
                                    <span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                                新增土地
                                            </button>

                                            <div class="dropdown" style="display: inline;margin-left: 5px;">
                                                <button type="button" class="btn btn-info dropdown-toggle btn-sm"
                                                        data-toggle="dropdown"
                                                        aria-expanded="false">
                                                    导入拍卖信息-房产
                                                </button>
                                                <div class="dropdown-menu" role="menu">
                                                    <a href="javascript://" class="dropdown-item"
                                                       onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftpNetInfoAssignOfflineHouse);">下载模板</a>
                                                    <a href="javascript://;" class="dropdown-item"
                                                       onclick="$('#ajaxFileUploadAuctionHouse').val('').attr('data-type',1).trigger('click');">导入数据</a>
                                                </div>
                                            </div>

                                            <div class="dropdown" style="display: inline;margin-left: 5px;">
                                                <button type="button" class="btn btn-info dropdown-toggle btn-sm"
                                                        data-toggle="dropdown"
                                                        aria-expanded="false">
                                                    导入拍卖信息-土地
                                                </button>
                                                <div class="dropdown-menu" role="menu">
                                                    <a href="javascript://" class="dropdown-item"
                                                       onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftpNetInfoAssignOfflineLand);">下载模板</a>
                                                    <a href="javascript://;" class="dropdown-item"
                                                       onclick="$('#ajaxFileUploadAuctionLand').val('').attr('data-type',1).trigger('click');">导入数据</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
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
                                <form enctype="multipart/form-data">
                                    <input type="file" name="file" id="ajaxFileUploadAuctionHouse" style="display: none"
                                           onchange="detailInfo.prototype.houseImportHandle(this);" multiple="multiple">

                                    <input type="file" id="ajaxFileUploadAuctionLand" name="file" style="display: none"
                                           onchange="detailInfo.prototype.landImportHandle(this)" multiple="multiple">
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
                            <button id="commit_btn" style="margin-left: 10px" class="btn btn-primary"
                                    onclick="masterObj.commit();">
                                提交
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

        <script type="text/javascript"
                src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
        <input type="file" id="ajaxFileUpload" name="file" style="display: none;">

    </div>
</div>
</body>
</html>

<div id="boxNetInfoRecordHouse" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 80%">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">信息补全</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <input type="hidden" name="masterId">
                                    <input type="hidden" name="id">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 col-form-label">
                                                类型
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" name="type" class="form-control input-full"
                                                       value="房产" readonly>
                                            </div>

                                            <label class="col-sm-1 col-form-label">
                                                类别<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-2">
                                                <select class="form-control input-full search-select select2"
                                                        name="category">
                                                    <option value="">请选择</option>
                                                    <option value="新房">新房</option>
                                                    <option value="二手房">二手房</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="houseContent">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    省
                                                </label>
                                                <div class="col-sm-2">
                                                    <select name="province"
                                                            class="form-control input-full search-select select2">
                                                    </select>
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    市
                                                </label>
                                                <div class="col-sm-2">
                                                    <select name="city"
                                                            class="form-control input-full search-select select2">
                                                    </select>
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    区
                                                </label>
                                                <div class="col-sm-2">
                                                    <select name="district"
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
                                                           onblur="masterObj.triggerHouseEvent()"></div>
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
                                                           name="currentPrice" class="form-control input-full"
                                                           onblur="masterObj.triggerHouseEvent()">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    成交(协商)日期
                                                </label>
                                                <div class="col-sm-2">
                                                    <input name="negotiatedDate" data-date-format="yyyy-mm-dd"
                                                           onchange="masterObj.triggerHouseEvent()"
                                                           class="form-control input-full date-picker dbdate">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    评估总价
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                           name="consultPrice"
                                                           class="form-control input-full"
                                                           onblur="masterObj.triggerHouseEvent()">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    评估基准日期
                                                </label>
                                                <div class="col-sm-2">
                                                    <input name="assessStandardDate" data-date-format="yyyy-mm-dd"
                                                           onchange="masterObj.triggerHouseEvent()"
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
                                                           name="unitPrice" class="form-control input-full">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    变现率
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="houseRealizationRatios"
                                                           onfocus="masterObj.triggerHouseEvent()"
                                                           class="form-control input-full x-percent">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    变现周期
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="realizationCycle"
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
                                                    <select class="form-control input-full search-select select2"
                                                            required
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
                                             <textarea name="dealPartInfo"
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
                <button type="button"  class="btn btn-primary btn-sm "
                        onclick="masterObj.saveHouseDetail()">
                    保存
                </button>
            </div>


        </div>
    </div>
</div>


<div id="boxNetInfoRecordLand" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 80%">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">信息补全</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <input type="hidden" name="masterId">
                                    <input type="hidden" name="id">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 col-form-label">
                                                类型<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" name="type" class="form-control input-full"
                                                       value="土地" readonly>
                                            </div>

                                            <label class="col-sm-1 col-form-label">
                                                类别<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-2">
                                                <select class="form-control input-full search-select select2"
                                                        name="category">
                                                    <option value="">请选择</option>
                                                    <option value="一级市场">一级市场</option>
                                                    <option value="二级市场">二级市场</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="landContent">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    省
                                                </label>
                                                <div class="col-sm-2">
                                                    <select name="province"
                                                            class="form-control input-full search-select select2">
                                                    </select>
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    市
                                                </label>
                                                <div class="col-sm-2">
                                                    <select name="city"
                                                            class="form-control input-full search-select select2">
                                                    </select>
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    区
                                                </label>
                                                <div class="col-sm-2">
                                                    <select name="district"
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
                                                           onchange="masterObj.triggerLandEvent()"
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
                                                           name="area" class="form-control input-full"
                                                           onblur="masterObj.triggerLandEvent()"></div>
                                                <label class="col-sm-1 col-form-label">
                                                    单位
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="areaUnit"  placeholder="平方米、亩"
                                                           class="form-control input-full"
                                                           onblur="masterObj.triggerLandEvent()">
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
                                                           name="currentPrice" class="form-control input-full"
                                                           onblur="masterObj.triggerLandEvent()">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    成交单价（元/㎡）
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                           name="unitPrice" class="form-control input-full"
                                                           onblur="masterObj.triggerLandEvent()">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    成交单价（万元/每亩）
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                           name="unitPriceMu" class="form-control input-full">
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
                                                           onchange="masterObj.triggerLandEvent()"
                                                           class="form-control input-full date-picker dbdate">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    评估起拍单价（元/㎡）
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                           name="consultPrice"
                                                           class="form-control input-full"
                                                           onblur="masterObj.triggerLandEvent()">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    评估起拍单价（万元/每亩）
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                                           name="consultPriceMu"
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
                                                    <input type="text" name="realizationCycle"
                                                           class="form-control input-full">
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    变现率
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" name="landRealizationRatios"
                                                           onfocus="masterObj.triggerLandEvent()"
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
                                                 <textarea name="dealPartInfo"
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
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>

                <button type="button"  class="btn btn-primary btn-sm "
                        onclick="masterObj.saveLandDetail()">
                    保存
                </button>
            </div>


        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        detailInfo.prototype.loadHouseHistoryList();
        detailInfo.prototype.loadLandHistoryList();
    });


    var masterObj = {};

    masterObj.config = {
        houseBox: $("#boxNetInfoRecordHouse"),
        landBox: $("#boxNetInfoRecordLand"),
        landTable: $("#landHistory") ,
        houseTable: $("#houseHistory") ,
    };

    /**
     * 提交数据
     * @returns {*}
     */
    masterObj.commit = function () {
        if ("${processInsId}" == "0") {
            //申请
            Loading.progressShow("正在提交数据...");
            $.ajax({
                url: "${pageContext.request.contextPath}/netInfoAssignTask/applyCommit",
                type: "post",
                data: {id: '${netInfoAssignTask.id}'},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        AlertSuccess("成功", "提交数据成功", function () {
                            window.close();
                        });
                    } else {
                        AlertError("失败", "提交数据失败", "失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            });
        } else {
            //修改提交
            //返回修改要提交的数据
            var approvalModelDto = formSerializeArray($("#process_variable_form"));
            approvalModelDto.assignTaskId = '${netInfoAssignTask.id}';
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
                    } else {
                        AlertError("失败", "提交数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            });
        }
    };

    /**
     * 房产
     */
    masterObj.showHouseBox = function () {
        var box = $(this.config.houseBox.selector);
        var obj = {masterId: 0, type: '房产'};
        masterObj.initHouse(obj);
        box.modal("show");
    };

    /**
     * 房产 自动触发事件要计算的值
     */
    masterObj.triggerHouseEvent = function () {
        var frm = $(this.config.houseBox.selector).find("form");
        var data = formSerializeArray(frm);
        if (data.area && data.currentPrice) {
            var unitPrice = frm.find("input[name=unitPrice]");
            if (!unitPrice.val()) {
                unitPrice.val((Number(data.currentPrice) / Number(data.area)).toFixed(2));
            }
        }
        var negotiatedDate = data.negotiatedDate;//成交(协商)日期
        var assessStandardDate = data.assessStandardDate;//评估基准日期
        if (negotiatedDate && assessStandardDate) {
            frm.find("input[name=realizationCycle]").val(getDateDiff(assessStandardDate, negotiatedDate));
        }
        if (data.currentPrice && data.consultPrice) {
            var houseRealizationRatios = (Number(data.currentPrice) / Number(data.consultPrice)).toFixed(2);
            frm.find('input[name=houseRealizationRatios]').val(houseRealizationRatios).trigger('change');
        }
    };

    masterObj.saveHouseDetail = function() {
        var masterId = $("#frmAdd").find("input[name='masterId']").val();
        var box = $(this.config.houseBox.selector);
        var frm = box.find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        data.type = "房产";
        data.assignTaskId = '${netInfoAssignTask.id}';
        AssessCommon.ajaxServerMethod({
            formData: JSON.stringify(data),
            changeStatus: false
        }, "/netInfoRecordHouse/saveHouseDetail", "post", function () {
            notifySuccess('成功', '保存成功');
            box.modal('hide');
            detailInfo.prototype.loadHouseHistoryList(masterId);
        });
    } ;

    masterObj.editHouse = function(id) {
        var table = $(this.config.houseTable.selector);
        var data = table.bootstrapTable('getRowByUniqueId', id) ;
        var box = $(this.config.houseBox.selector);
        box.modal('show');
        masterObj.initHouse(data) ;
    } ;

    /**
     * 房产 赋值并初始化
     * @param data
     */
    masterObj.initHouse = function (data) {
        var frm = $(this.config.houseBox.selector).find("form");
        frm.clearAll(['type']);
        frm.initForm(data);
        frm.validate();
        AssessCommon.initAreaInfo({
            provinceTarget: frm.find("select[name=province]"),
            cityTarget: frm.find("select[name=city]"),
            districtTarget: frm.find("select[name=district]"),
            provinceValue: data.province,
            cityValue: data.city,
            districtValue: data.district
        });
        AssessCommon.loadDataListHtml(AssessDicKey.examineHouseLoadUtility, data.belongType, function (html, data) {
            frm.find("#houseUseList").empty().html(html).trigger('change');
        }, true);
        frm.find("input[name='belongType']").off('change').on('change', function () {
            AssessCommon.getSonDataList(AssessDicKey.examineHouseLoadUtility, $(this).val(), data.belongCategory, function (html, data) {
                frm.find("#houseUseCategoryList").empty().html(html).trigger('change');
            });
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.dataDealType, data.dealType, function (html, data) {
            frm.find("select[name='dealType']").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseTransactionType, data.tradingType, function (html, data) {
            frm.find("select[name='tradingType']").empty().html(html).trigger('change');
        });
        AssessCommon.initArraySelect2(frm, data, ["dealType", "tradingType", "purchaseLimitStatus" ,"category"]);
        var tableId = "0";
        if (data.id) {
            tableId = data.id;
        }
        detailInfo.prototype.showFile("uploadHouseFile", "tb_net_info_record_house", tableId);
        detailInfo.prototype.fileUpload("uploadHouseFile", "tb_net_info_record_house", tableId);
    };

    masterObj.showLandBox = function () {
        var box = $(this.config.landBox.selector);
        var obj = {masterId: 0, type: '土地'};
        masterObj.initLand(obj);
        box.modal("show");
    };

    masterObj.editLand = function(id){
        var table = $(this.config.landTable.selector);
        var data = table.bootstrapTable('getRowByUniqueId', id) ;
        var box = $(this.config.landBox.selector);
        box.modal('show');
        masterObj.initLand(data) ;
    };

    masterObj.initLand = function (data) {
        var frm = $(this.config.landBox.selector).find("form");
        frm.clearAll(['type']);
        frm.initForm(data);
        frm.validate();
        AssessCommon.initAreaInfo({
            provinceTarget: frm.find("select[name=province]"),
            cityTarget: frm.find("select[name=city]"),
            districtTarget: frm.find("select[name=district]"),
            provinceValue: data.province,
            cityValue: data.city,
            districtValue: data.district
        });
        AssessCommon.loadDataListHtml(AssessDicKey.estate_total_land_use, data.belongType, function (html, data) {
            frm.find("#landUseList").empty().html(html).trigger('change');
        }, true);
        frm.find("input[name='belongType']").off('change').on('change', function () {
            AssessCommon.getSonDataList(AssessDicKey.estate_total_land_use, $(this).val(), data.belongCategory, function (html, data) {
                frm.find("#landUseCategoryList").empty().html(html).trigger('change');
            });
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.dataDealType, data.dealType, function (html, data) {
            frm.find("select[name='dealType']").empty().html(html).trigger('change');
        });
        var tableId = "0";
        if (data.id) {
            tableId = data.id;
        }
        detailInfo.prototype.showFile("uploadLandFile", "tb_net_info_record_land", tableId);
        detailInfo.prototype.fileUpload("uploadLandFile", "tb_net_info_record_land", tableId);
        AssessCommon.initArraySelect2(frm, data, ["dealType", "tradingType", "purchaseLimitStatus" ,"category"]);
    };

    /**
     * 土地 自动触发事件要计算的值
     */
    masterObj.triggerLandEvent = function () {
        var frm = $(this.config.landBox.selector).find("form");
        var data = formSerializeArray(frm);
        if (data.area && data.currentPrice) {
            var unitPrice = frm.find("input[name=unitPrice]");
            if (!unitPrice.val()) {
                var tempValue = (Number(data.currentPrice) * 10000 / Number(data.area)) ;
                if (data.areaUnit == '亩') {
                    unitPrice.val( (tempValue / AssessCommon.BHOU).toFixed(2));
                    frm.find("input[name=unitPriceMu]").val( tempValue.toFixed(2));
                }else {
                    unitPrice.val(tempValue.toFixed(2));
                    frm.find("input[name=unitPriceMu]").val( (tempValue * AssessCommon.BHOU / 10000).toFixed(2));
                }
            }
        }
        var negotiatedDate = data.negotiatedDate;//成交(协商)日期
        var assessStandardDate = data.assessStandardDate;//评估基准日期
        if (negotiatedDate && assessStandardDate) {
            frm.find("input[name=realizationCycle]").val(getDateDiff(assessStandardDate, negotiatedDate));
        }
        if (data.unitPrice && data.consultPrice) {
            var landRealizationRatios = (Number(data.unitPrice) / Number(data.consultPrice)).toFixed(2);
            frm.find('input[name=landRealizationRatios]').val(landRealizationRatios).trigger('change');
        }
    } ;

    masterObj.saveLandDetail = function () {
        var masterId = $("#frmAdd").find("input[name='masterId']").val();
        var box = $(this.config.landBox.selector);
        var frm = box.find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        data.type = "土地";
        data.assignTaskId = '${netInfoAssignTask.id}';
        AssessCommon.ajaxServerMethod({
            formData: JSON.stringify(data),
            changeStatus: false
        }, "/netInfoRecordLand/saveLandDetail", "post", function () {
            notifySuccess('成功', '保存成功');
            box.modal('hide');
            detailInfo.prototype.loadLandHistoryList(masterId);
        });
    } ;


    var detailInfo = function () {
    };


    detailInfo.prototype = {
        config: function () {
            var data = {};
            data.table = "transaction_List";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            data.singleTable = "singleData";
            data.houseBox = 'boxNetInfoRecordHouse';
            data.landBox = 'boxNetInfoRecordLand';
            return data;
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
            cols.push({field: 'category', title: '类别'});
            cols.push({field: 'dealTypeName', title: '交易方式'});
            cols.push({field: 'currentPrice', title: '成交价'});
            cols.push({field: 'unitPrice', title: '单价'});
            cols.push({field: 'floorPrice', title: '楼面地价'});
            cols.push({field: 'landArea', title: '净用地面积'});
            cols.push({field: 'fileViewName', title: '附件'});
            cols.push({
                field: 'id', width: '6%', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="masterObj.editLand(' + row.id + ')"><i class="fa fa-pen fa-white"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="删除" onclick="detailInfo.prototype.deleteLandItem(' + row.id + ')"><i class="fa fa-minus fa-white"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#landHistory").bootstrapTable('destroy');
            TableInit("landHistory", "${pageContext.request.contextPath}/netInfoAssignTask/getLandListByAssignTaskId", cols, {
                assignTaskId: '${netInfoAssignTask.id}'
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
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
                        } else {
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
            cols.push({field: 'category', title: '类别'});
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
                        result += row.houseNumber + "号";
                    }
                    return result;
                }
            });
            cols.push({field: 'currentPrice', title: '成交总价'});
            cols.push({field: 'consultPrice', title: '评估总价'});
            cols.push({field: 'dealTypeName', title: '交易方式'});
            cols.push({field: 'tradingTypeName', title: '交易类型'});
            cols.push({field: 'purchaseLimitStatus', title: '限购状态'});
            cols.push({field: 'fileViewName', title: '附件'});
            cols.push({
                field: 'id', width: '6%', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="masterObj.editHouse(' + row.id + ')"><i class="fa fa-pen fa-white"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="删除" onclick="detailInfo.prototype.deleteHouseItem(' + row.id + ')"><i class="fa fa-minus fa-white"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#houseHistory").bootstrapTable('destroy');
            TableInit("houseHistory", "${pageContext.request.contextPath}/netInfoAssignTask/getHouseListByAssignTaskId", cols, {
                assignTaskId: '${netInfoAssignTask.id}'
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
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
                        } else {
                            AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                })
            });
        }
    };

    detailInfo.prototype.houseImportHandle = function (that) {
        var data = {masterId: $("#frmAdd").find("input[name='masterId']").val()};
        data.type = "房产";
        data.assignTaskId = '${netInfoAssignTask.id}';
        detailInfo.prototype.ajaxFileUploadCommon(data, $(that).attr("id"), "/netInfoAssignTask/importAssignHouseData", function () {
            detailInfo.prototype.loadHouseHistoryList();
        }, false);
    };

    detailInfo.prototype.landImportHandle = function (that) {
        var data = {masterId: $("#frmAdd").find("input[name='masterId']").val()};
        data.type = "土地";
        data.assignTaskId = '${netInfoAssignTask.id}';
        detailInfo.prototype.ajaxFileUploadCommon(data, $(that).attr("id"), "/netInfoAssignTask/importAssignLandData", function () {
            detailInfo.prototype.loadLandHistoryList();
        }, false);
    };

    detailInfo.prototype.ajaxFileUploadCommon = function (data, fileElementId, url, callback, flag) {
        Loading.progressShow();
        $.ajaxFileUpload({
            type: "POST",
            url: "${pageContext.request.contextPath}" + url,
            data: data,//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: fileElementId,//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                    if (flag) {

                    } else {
                        AlertSuccess("导入情况", result.data);
                    }
                } else {
                    if (result.errmsg) {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                    } else {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                    }
                }
            },
            error: function (result, status, e) {
                Loading.progressHide();
                if (result.errmsg) {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                } else {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                }
            }
        });
    };

</script>
