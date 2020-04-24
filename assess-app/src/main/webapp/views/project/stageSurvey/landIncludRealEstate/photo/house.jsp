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
                                        <c:if test="${not empty planDetailsId}">
                                            <button type="button" class="btn btn-sm btn-primary"
                                                    onclick="showHistoryModal();">历史记录
                                            </button>

                                            <button type="button" class="btn btn-sm btn-primary"
                                                    onclick="showCaseQuoteModal();">引用案例
                                            </button>
                                        </c:if>
                                        <c:if test="${empty planDetailsId}">
                                            <button type="button" class="btn btn-sm btn-primary"
                                                    onclick="showProjectQuoteModal();">引用备选案例
                                            </button>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class="x_content">
                                    <div class="x_title">
                                        <h3>
                                            房屋基本信息
                                        </h3>
                                        <div class="clearfix"></div>
                                    </div>
                                    <form class="form-horizontal" id="basicHouseFrm">
                                        <input type="hidden" name="id" value="${basicHouse.id}">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="card-body">
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">房号<span
                                                                        class="symbol required"></span></label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" required placeholder="房号" name="houseNumber"
                                                                           class="form-control input-full" value="${basicHouse.houseNumber}"
                                                                           id="txt_House_search">
                                                                </div>
                                                                <label class="col-sm-1 control-label">所在楼层<span
                                                                        class="symbol required"></span></label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" placeholder="所在楼层" name="floor" required
                                                                           class="form-control input-full" value="${basicHouse.floor}">
                                                                </div>
                                                                <label class="col-sm-1 control-label">楼层描述</label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" class="form-control input-full" name="floorDesc"
                                                                           value="${basicHouse.floorDesc}">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">户型</label>
                                                                <div class="col-sm-3">
                                                                    <div class="input-group">
                                                                        <input type="hidden" name="huxingId" value="${basicHouse.huxingId}">
                                                                        <c:if test="${empty isApplyBatch}">
                                                                            <input type="text" readonly="readonly"
                                                                                   onclick="houseCommon.selectHuxing(this);"
                                                                                   placeholder="户型" class="form-control" name="huxingName"
                                                                                   value="${basicHouse.huxingName}">
                                                                            <div class="input-group-prepend">
                                                                                <button class="btn btn-primary btn-sm "
                                                                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                                        type="button" onclick="houseCommon.selectHuxing(this);">选择
                                                                                </button>
                                                                            </div>
                                                                        </c:if>
                                                                        <c:if test="${isApplyBatch eq 'show'}">
                                                                            <input type="text" readonly="readonly"
                                                                                   onclick="houseCommon.selectHuxing2(this,${tableId});"
                                                                                   placeholder="户型" class="form-control" name="huxingName"
                                                                                   value="${basicHouse.huxingName}">
                                                                            <div class="input-group-prepend">
                                                                                <button class="btn btn-primary btn-sm "
                                                                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                                        type="button"
                                                                                        onclick="houseCommon.selectHuxing2(this,${tableId});">选择
                                                                                </button>
                                                                            </div>
                                                                        </c:if>
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
                                                                <label class="col-sm-1 control-label">户型图</label>
                                                                <div class="col-sm-3">
                                                                    <div id="_house_huxing_plan"></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">空间布局</label>
                                                                <div class="col-sm-3">
                                                                    <select class="form-control input-full spatialDistribution"
                                                                            name="spatialDistribution">
                                                                    </select>
                                                                </div>
                                                                <label class="col-sm-1 control-label">空间布局描述</label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" data-rule-maxlength="255" placeholder="空间布局描述"
                                                                           name="spatialDistributionDesc"
                                                                           class="form-control input-full"
                                                                           value="${basicHouse.spatialDistributionDesc}">
                                                                </div>
                                                                <label class="col-sm-1 control-label">朝向<span
                                                                        class="symbol required"></span></label>
                                                                <div class="col-sm-3">
                                                                    <select class="form-control input-full search-select select2 orientation"
                                                                            name="orientation"
                                                                            required>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">面积<span
                                                                        class="symbol required"></span></label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" placeholder="面积" name="area" data-rule-number="true" required
                                                                           class="form-control input-full" value="${basicHouse.area}">
                                                                </div>
                                                                <label class="col-sm-1 control-label">面积描述</label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" data-rule-maxlength="255" placeholder="面积描述"
                                                                           name="areaDesc" class="form-control input-full"
                                                                           value="${basicHouse.areaDesc}">
                                                                </div>
                                                                <label class="col-sm-1 control-label">调查方式<span
                                                                        class="symbol required"></span></label>
                                                                <div class="col-sm-3">
                                                                    <select class="form-control input-full search-select select2 researchType"
                                                                            name="researchType"
                                                                            required>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">
                                                                    实际用途<span class="symbol required"></span>
                                                                </label>
                                                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                                                    <div class="input-group">
                                                                        <input type="text" name="practicalUse" list="practicalUseList"
                                                                               value="${basicHouse.practicalUse}"
                                                                               class="form-control">
                                                                        <datalist id="practicalUseList">

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
                                                                <label class="col-sm-1 control-label">使用情况<span
                                                                        class="symbol required"></span></label>
                                                                <div class="col-sm-3">
                                                                    <select class="form-control input-full useCondition" name="useCondition" required>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid" style="display: none;">
                                                                <label class="col-sm-1 control-label">使用情况描述<span
                                                                        class="symbol required"></span></label>
                                                                <div class="col-sm-11">
                    <textarea class="form-control input-full" required
                              name="useConditionDescription"
                              id="useConditionDescription">${basicHouse.useConditionDescription}</textarea>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">房屋平面图</label>
                                                                <div class="col-sm-3">
                                                                    <input id="house_img_plan" placeholder="上传附件" class="form-control input-full"
                                                                           type="file">
                                                                    <div id="_house_img_plan"></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="x_content">
                                    <%@include file="/views/project/stageSurvey/common/houseFaceStreet.jsp" %>
                                    <%@include file="/views/project/stageSurvey/common/houseRoom.jsp" %>
                                    <c:if test="${formType eq 'residence'}">
                                        <%@include file="/views/project/stageSurvey/common/houseWater.jsp" %>
                                        <%@include file="/views/project/stageSurvey/common/houseWaterDrain.jsp" %>
                                        <%@include file="/views/project/stageSurvey/common/houseNewWind.jsp" %>
                                        <%@include file="/views/project/stageSurvey/common/houseAirConditioner.jsp" %>
                                        <%@include file="/views/project/stageSurvey/common/houseHeating.jsp" %>
                                        <%@include file="/views/project/stageSurvey/common/houseIntelligent.jsp" %>
                                    </c:if>
                                    <c:if test="${formType eq 'industry'}">
                                        <%@include file="/views/project/stageSurvey/common/houseCorollaryEquipment.jsp" %>
                                    </c:if>
                                    <%@include file="/views/project/stageSurvey/common/houseDamagedDegree.jsp" %>
                                </div>
                                <script type="text/javascript"
                                        src="${pageContext.request.contextPath}/js/examine/examine.house.js?v=${assessVersion}"></script>
                                <script type="text/javascript"
                                        src="${pageContext.request.contextPath}/js/examine/sonHouseView.js?v=${assessVersion}"></script>
                                <script type="text/javascript"
                                        src="${pageContext.request.contextPath}/js/select/huxing.select.js?v=${assessVersion}"></script>
                                <script type="text/javascript">
                                    $(function () {
                                        houseCommon.initById('${basicHouse.id}');
                                    })
                                </script>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">
                        <div class="card-body">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                关闭
                            </button>
                            <button class="btn btn-warning" onclick="saveDataInfo();">
                                保存<i style="margin-left: 10px" class="fa fa-save"></i>
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

<%@include file="/views/project/stageSurvey/common/applyInfoHistory.jsp" %>
<%@include file="/views/project/stageSurvey/common/applyInfoQuote.jsp" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.min.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
</html>
<script type="text/javascript">

    //保存数据信息
    function saveDataInfo() {
        Loading.progressShow();
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

    function showCaseQuoteModal() {
        //打开楼盘modal
        if ("estate" == "${tbType}") {
            caseFun.caseEstate.showModel();
        }
        //打开楼栋modal
        if ("building" == "${tbType}") {
            caseFun.caseBuild.showModel(${quoteId});
        }
        //打开单元modal
        if ("unit" == "${tbType}") {
            caseFun.caseUnit.showModel(${quoteId});
        }
        //打开房屋modal
        if ("house" == "${tbType}") {
            caseFun.caseHouse.showModel(${quoteId});
        }
    }

    //打开历史数据modal
    function showHistoryModal() {
        //打开楼盘modal
        if ("estate" == "${tbType}") {
            historyInfo.caseEstate.showModel('${tbId}', '${formClassify}', '${tbType}', '${basicApplyBatch.id}');
        }

        //打开楼栋modal
        if ("building" == "${tbType}") {
            historyInfo.caseBuild.showModel('${tbId}', '${formClassify}', '${tbType}', '${basicApplyBatch.id}');
        }
        //打开单元modal
        if ("unit" == "${tbType}") {
            historyInfo.caseUnit.showModel('${tbId}', '${formClassify}', '${tbType}', '${basicApplyBatch.id}');
        }
        //打开房屋modal
        if ("house" == "${tbType}") {
            historyInfo.caseHouse.showModel('${tbId}', '${formClassify}', '${tbType}', '${basicApplyBatch.id}');
        }

    };

    function showProjectQuoteModal() {
        //打开楼盘modal
        if ("estate" == "${tbType}") {
            projectData.prototype.showModel();
        }

        //打开楼栋modal
        if ("building" == "${tbType}") {
            projectBuild.prototype.showModel();
        }
        //打开单元modal
        if ("unit" == "${tbType}") {
            projectUnit.prototype.showModel();
        }
        //打开房屋modal
        if ("house" == "${tbType}") {
            projectHouse.prototype.showModel();
        }
    }
</script>

