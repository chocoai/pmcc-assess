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
                                        <h3>单元基本信息</h3>
                                        <div class="clearfix"></div>
                                    </div>
                                    <form class="form-horizontal" id="basicUnitFrm">
                                        <input type="hidden" name="id" value="${basicUnit.id}">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="card-body">
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">单元编号<span
                                                                        class="symbol required"></span></label>
                                                                <div class="col-sm-3">
                                                                    <div class="input-group">
                                                                        <input type="text" data-rule-maxlength="100" placeholder="单元编号"
                                                                               required="required"
                                                                               name="unitNumber" class="form-control"
                                                                               value="${basicUnit.unitNumber}"
                                                                               id="txt_Unit_search">
                                                                        <span class="input-group-btn">
                            <c:if test="${empty isApplyBatch}">
                            <div onclick="unitCommon.mapMarker();" class="btn btn-info"><i
                                    class="fa fa-map-marker"></i> 标注</div>
                            </c:if>
                              <c:if test="${isApplyBatch eq 'show'}">
                            <div onclick="unitCommon.mapMarker2(false,${tableId});" class="btn btn-info"><i
                                    class="fa fa-map-marker"></i> 标注</div>
                              </c:if>
                        </span>
                                                                    </div>
                                                                </div>

                                                                <label class="col-sm-1 control-label">梯户比<span
                                                                        class="symbol required"></span></label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" placeholder="梯户比"
                                                                           name="elevatorHouseholdRatio" class="form-control input-full"
                                                                           required="required"
                                                                           value="${basicUnit.elevatorHouseholdRatio}">
                                                                </div>

                                                                <label class="col-sm-1 control-label">户型说明</label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" name="huxingExplain" class="form-control input-full"
                                                                           value="${basicUnit.huxingExplain}">
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
                                    <%@include file="/views/project/stageSurvey/common/unitHuxing.jsp" %>
                                    <%@include file="/views/project/stageSurvey/common/unitHuxingPrice.jsp" %>
                                    <%@include file="/views/project/stageSurvey/common/unitDecorate.jsp" %>
                                    <%@include file="/views/project/stageSurvey/common/unitElevator.jsp" %>
                                </div>
                                <script type="text/javascript"
                                        src="${pageContext.request.contextPath}/js/examine/examine.unit.js?v=${assessVersion}"></script>
                                <script type="text/javascript"
                                        src="${pageContext.request.contextPath}/js/examine/sonUnitView.js?v=${assessVersion}"></script>
                                <script type="text/javascript"
                                        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
                                <script type="text/javascript"
                                        src="${pageContext.request.contextPath}/js/basic/basic.common.js?v=${assessVersion}"></script>
                                <script type="text/javascript">
                                    $(function () {
                                        unitCommon.initById('${basicUnit.id}');
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

