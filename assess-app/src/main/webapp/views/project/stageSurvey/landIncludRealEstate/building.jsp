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
                                            楼栋基本信息
                                        </h3>
                                        <div class="clearfix"></div>
                                    </div>
                                    <form class="form-horizontal" id="basicBuildingFrm">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="card-body">
                                                    <input type="hidden" name="id" value="${basicBuilding.id}">
                                                    <%@include file="/views/project/stageSurvey/common/canvasQRcode.jsp" %>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">
                                                                    楼栋号<span class="symbol required"></span>
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <div class="input-group">
                                                                        <input type="text" id="txt_building_search" data-rule-maxlength="100"
                                                                               placeholder="楼栋号" required="required"
                                                                               name="buildingNumber" class="form-control"
                                                                               onblur="buildingCommon.buildingNumberBlur(this);"
                                                                               value="${basicBuilding.buildingNumber}">
                                                                        <span class="input-group-btn">
                          <c:if test="${empty isApplyBatch}">
                            <div onclick="buildingCommon.mapMarker();" class="btn btn-info"><i
                                    class="fa fa-map-marker"></i> 标注</div>
                          </c:if>
                                <c:if test="${isApplyBatch eq 'show'}">
                                 <div onclick="buildingCommon.mapMarker2(false,${tableId});" class="btn btn-info"><i
                                         class="fa fa-map-marker"></i> 标注</div>
                                </c:if>
                        </span>
                                                                    </div>
                                                                </div>
                                                                <label class="col-sm-1 control-label">
                                                                    楼栋名称<span class="symbol required"></span>
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" placeholder="楼栋名称" name="buildingName"
                                                                           class="form-control input-full" required="required"
                                                                           value="${basicBuilding.buildingName}">
                                                                </div>
                                                                <label class="col-sm-1 control-label">
                                                                    总层数<span class="symbol required"></span>
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" placeholder="总层数" data-rule-number="true"
                                                                           name="floorCount" class="form-control input-full" required="required"
                                                                           value="${basicBuilding.floorCount}">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">
                                                                    物业类型
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <select name="propertyType"
                                                                            class="form-control input-full propertyType">
                                                                    </select>
                                                                </div>
                                                                <label class="col-sm-1 control-label">
                                                                    物业类别
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <select name="propertyCategory" class="form-control input-full propertyCategory">
                                                                    </select>
                                                                </div>
                                                                <label class="col-sm-1 control-label">
                                                                    首层位置
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" placeholder="首层位置(数字)" data-rule-number='true'
                                                                           name="firstFloor" class="form-control input-full"
                                                                           value="${basicBuilding.firstFloor}">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">
                                                                    最高层
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" placeholder="最高层(数字)" data-rule-number='true'
                                                                           name="maxFloor" class="form-control input-full"
                                                                           value="${basicBuilding.maxFloor}">
                                                                </div>
                                                                <label class="col-sm-1 control-label">
                                                                    套内面积
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" placeholder="套内面积"
                                                                           name="inJacketArea" class="form-control input-full"
                                                                           value="${basicBuilding.inJacketArea}">
                                                                </div>
                                                                <label class="col-sm-1 control-label">
                                                                    使用面积
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" placeholder="使用面积"
                                                                           name="useArea" class="form-control input-full"
                                                                           value="${basicBuilding.useArea}">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">
                                                                    所在位置
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" placeholder="所在位置" name="location"
                                                                           class="form-control input-full"
                                                                           value="${basicBuilding.location}">
                                                                </div>
                                                                <label class="col-sm-1 control-label">
                                                                    建筑使用年限<span class="symbol required"></span>
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <c:if test="${basicApply.type == 0 || basicApply.type==null || basicApply.type == 3}">
                                                                        <select name="residenceUseYear" required
                                                                                class="form-control input-full residenceUseYear search-select select2">
                                                                        </select>
                                                                    </c:if>
                                                                    <c:if test="${basicApply.type == 1}">
                                                                        <select name="industryUseYear" required
                                                                                class="form-control input-full industryUseYear search-select select2">
                                                                        </select>
                                                                    </c:if>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">
                                                                    土地使用年限
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" placeholder="土地使用年限(数字)" data-rule-number='true'
                                                                           name="landUseYear" class="form-control input-full"
                                                                           value="${basicBuilding.landUseYear}">
                                                                </div>
                                                                <label class="col-sm-1 control-label">
                                                                    开盘时间
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <input placeholder="开盘时间"
                                                                           name="openTime" data-date-format="yyyy-mm-dd"
                                                                           class="form-control input-full date-picker dbdate openTime">
                                                                </div>
                                                                <label class="col-sm-1 control-label">
                                                                    交房时间
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <input placeholder="交房时间"
                                                                           name="roomTime" data-date-format="yyyy-mm-dd"
                                                                           class="form-control input-full date-picker dbdate roomTime">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">
                                                                    建筑结构类型
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <select name="buildingStructureType"
                                                                            class="form-control input-full buildingStructureType">
                                                                    </select>
                                                                </div>
                                                                <label class="col-sm-1 control-label">
                                                                    建筑结构类别
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <select name="buildingStructureCategory"
                                                                            class="form-control input-full buildingStructureCategory">
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">
                                                                    外观风格
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <select name="appearanceStyle"
                                                                            class="form-control input-full appearanceStyle">
                                                                    </select>
                                                                </div>
                                                                <label class="col-sm-1 control-label">
                                                                    外观新旧
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <select name="appearanceNewAndOld"
                                                                            class="form-control input-full appearanceNewAndOld">
                                                                    </select>
                                                                </div>
                                                                <label class="col-sm-1 control-label">单元说明</label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" name="remark" class="form-control input-full"
                                                                           value="${basicBuilding.remark}">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">
                                                                    竣工时间获取方式<span class="symbol required"></span>
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <select name="completedTimeType" required
                                                                            class="form-control input-full completedTimeType search-select select2">
                                                                    </select>
                                                                </div>
                                                                <label class="col-sm-1 control-label">
                                                                    竣工时间<span class="symbol required"></span>
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <input placeholder="竣工时间"
                                                                           name="beCompletedTime" required data-date-format="yyyy-mm-dd"
                                                                           class="form-control input-full date-picker dbdate beCompletedTime">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row form-group" id="navButtonBuildGroupFileId">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">平面图</label>
                                                                <div class="col-sm-3">
                                                                    <input id="building_floor_plan" placeholder="上传附件" class="form-control input-full"
                                                                           type="file">
                                                                    <div id="_building_floor_plan"></div>
                                                                </div>
                                                                <label class="col-sm-1 control-label">外装图</label>
                                                                <div class="col-sm-3">
                                                                    <input id="building_figure_outside" placeholder="上传附件"
                                                                           class="form-control input-full"
                                                                           type="file">
                                                                    <div id="_building_figure_outside"></div>
                                                                </div>
                                                                <label class="col-sm-1 control-label">外观图</label>
                                                                <div class="col-sm-3">
                                                                    <input id="building_floor_Appearance_figure" placeholder="上传附件"
                                                                           class="form-control input-full" type="file">
                                                                    <div id="_building_floor_Appearance_figure"></div>
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
                                    <%@include file="/views/project/stageSurvey/common/buildingFunction.jsp" %>
                                    <%@include file="/views/project/stageSurvey/common/buildingOutfit.jsp" %>
                                    <c:if test="${formType eq 'industry'}">
                                        <%@include file="/views/project/stageSurvey/common/buildingMaintenance.jsp" %>
                                        <%@include file="/views/project/stageSurvey/common/buildingSurface.jsp" %>
                                    </c:if>
                                </div>
                                <script type="text/javascript"
                                        src="${pageContext.request.contextPath}/js/examine/examine.build.js?v=${assessVersion}"></script>
                                <script type="text/javascript"
                                        src="${pageContext.request.contextPath}/js/examine/sonBuildView.js?v=${assessVersion}"></script>
                                <script type="text/javascript">
                                    $(function () {
                                        buildingCommon.initById('${basicBuilding.id}');
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

