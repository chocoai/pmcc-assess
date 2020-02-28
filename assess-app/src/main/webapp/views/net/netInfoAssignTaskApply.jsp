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
<body class="nav-md">


<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0px">
            <!-- 公共模块引用 -->
            <%@include file="/views/share/form_head.jsp" %>
            <!-- 公共模块end -->

            <div class="x_panel">

                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>案例整理
                    </h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="row">
                        <div class="panel-body">
                            <form id="master_form" class="form-horizontal">
                                <input type="hidden" name="id" value="${netInfoAssignTask.id}">

                                <table class="table table-bordered" id="transaction_List">
                                    <!-- cerare document add ajax data-->
                                </table>
                            </form>

                        </div>

                    </div>
                </div>
            </div>

            <!-- 公共尾部模块引用 -->
            <div class="panel-body">
                <div class="form-group">
                    <div class="col-md-4 col-sm-4 col-xs-12 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>

                        <button id="commit_btn" class="btn btn-success" onclick="masterObj.commit();">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </div>
                </div>
            </div>
            <%--返回修改--%>
            <c:if test="${processInsId != 0}">
                <%@include file="/views/share/form_log.jsp" %>
                <form id="process_variable_form">
                    <%@include file="/views/share/form_edit.jsp" %>
                </form>
            </c:if>
            <!-- 尾部end -->

        </div>

    </div>

</div>

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
                                </div>
                                <div class="form-group landContent" style="display: none">
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                省
                                            </label>
                                            <div class="col-sm-2">
                                                <select name="province" id="landProvince"
                                                        class="form-control search-select select2">
                                                </select>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                市
                                            </label>
                                            <div class="col-sm-2">
                                                <select name="city" id="landCity"
                                                        class="form-control search-select select2">
                                                </select>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                区
                                            </label>
                                            <div class="col-sm-2">
                                                <select name="district" id="landDistrict"
                                                        class="form-control search-select select2">
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
                                                <i class="fa fa-minus"></i>
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
                                                <i class="fa fa-minus"></i>
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
                                                       onfocus="detailInfo.prototype.getHouseRealizationRatios()"
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
                                                <select name="province" id="houseProvince"
                                                        class="form-control search-select select2">
                                                </select>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                市
                                            </label>
                                            <div class="col-sm-2">
                                                <select name="city" id="houseCity"
                                                        class="form-control search-select select2">
                                                </select>
                                            </div>
                                        </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                区
                                            </label>
                                            <div class="col-sm-2">
                                                <select name="district" id="houseDistrict"
                                                        class="form-control search-select select2">
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
                                                <i class="fa fa-minus"></i>
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
                                                <i class="fa fa-minus"></i>
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
                                                成交总价
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
                                                评估总价
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
                                                成交单价
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
                                                       onfocus="detailInfo.prototype.getHouseRealizationRatios()"
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
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                交易类型
                                            </label>
                                            <div class="col-sm-2">
                                                <select name="tradingType" class="form-control search-select select2">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                限购状态
                                            </label>
                                            <div class="col-sm-3">
                                                <select class="form-control" required name="purchaseLimitStatus">
                                                    <option value="">--请选择--</option>
                                                    <option value="限购">限购</option>
                                                    <option value="不限购">不限购</option>
                                                </select>
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
        if (!$("#master_form").valid()) {
            return false;
        }
        var data = formParams("master_form");
        data.projectId = "${projectInfo.id}";
        Loading.progressShow("正在提交数据...");
        $.ajax({
            url: "${pageContext.request.contextPath}/netInfoAssignTask/applyCommit",
            type: "post",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    Alert("提交数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    };

    //返回修改
    function editCommit() {
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
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    Alert("提交数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
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
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="补全信息" onclick="detailInfo.prototype.showTableListModal(' + row.id + ')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="查看网址" onclick="detailInfo.prototype.openItem(' + index + ')"><i class="fa fa-eye fa-white"></i></a>';
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
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
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
                    changeStatus: false
                },
                success: function (result) {
                    if (result.ret) {
                        notifySuccess('成功','保存成功');
                        $('#' + detailInfo.prototype.config().box).modal('hide');
                        detailInfo.prototype.loadLandHistoryList(masterId);
                    }
                    else {
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
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
                        notifySuccess('成功','保存成功');
                        $('#' + detailInfo.prototype.config().box).modal('hide');
                        detailInfo.prototype.loadHouseHistoryList(masterId);
                    }
                    else {
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
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
                    str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="删除" onclick="detailInfo.prototype.deleteLandItem(' + row.id + ')"><i class="fa fa-minus fa-white"></i></a>';
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
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
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
                            notifySuccess('成功','删除成功');
                            detailInfo.prototype.loadLandHistoryList(masterId);
                        }
                        else {
                            AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
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
                        result += row.houseNumber;
                    }
                    return result;
                }
            });
            cols.push({
                field: 'currentPrice', title: '价格', formatter: function (value, row, index) {
                    var result = '';
                    if (row.currentPrice) {
                        result += '成交总价：' + row.currentPrice + '<br/>';
                    }
                    if (row.consultPrice) {
                        result += '评估总价：' + row.consultPrice + '<br/>';
                    }
                    if (row.unitPrice) {
                        result += '成交单价：' + row.unitPrice + '<br/>';
                    }
                    return result;
                }
            });
            cols.push({
                field: 'area', title: '信息1', formatter: function (value, row, index) {
                    var result = '';
                    if (row.area) {
                        result += '面积：' + row.area + '<br/>';
                    }
                    if (row.belongType) {
                        result += '类型：' + row.belongType + '<br/>';
                    }
                    if (row.belongCategory) {
                        result += '类别：' + row.belongCategory + '<br/>';
                    }
                    if (row.dealTypeName) {
                        result += '交易方式：' + row.dealTypeName + '<br/>';
                    }
                    if (row.tradingTypeName) {
                        result += '交易类型：' + row.tradingTypeName + '<br/>';
                    }
                    if (row.purchaseLimitStatus) {
                        result += '限购状态：' + row.purchaseLimitStatus + '<br/>';
                    }
                    return result;
                }
            });
            cols.push({
                field: 'other', title: '信息2', formatter: function (value, row, index) {
                    var result = '';
                    if (row.negotiatedDate) {
                        result += '成交(协商)日期：' + formatDate(row.negotiatedDate) + '<br/>';
                    }
                    if (row.assessStandardDate) {
                        result += '评估基准日：' + formatDate(row.assessStandardDate) + '<br/>';
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
                    str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="删除" onclick="detailInfo.prototype.deleteHouseItem(' + row.id + ')"><i class="fa fa-minus fa-white"></i></a>';
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
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
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
                            notifySuccess('成功','删除成功');
                            detailInfo.prototype.loadHouseHistoryList(masterId);
                        }
                        else {
                            AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
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
