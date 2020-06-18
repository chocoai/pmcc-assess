<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" class="no-js">
<head>
    <title>单元</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <div class="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        单元基本信息
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form class="form-horizontal" id="basicUnitFrm">
                                    <input type="hidden" name="id" value="${basicUnit.id}">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">名称</label>
                                                <div class=" col-sm-3 ">
                                                    <div class="input-group">
                                                        <label class="form-control "
                                                               name="unitNumber">${basicUnit.unitNumber}</label>
                                                        <div class="input-group-prepend">
                                                            <button class="btn btn-info btn-sm "
                                                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                    type="button"
                                                                    onclick="unitCommon.mapMarker(true);">
                                                                标注
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                                <label class="col-sm-1 control-label">梯户比</label>
                                                <div class=" col-sm-3 ">
                                                    <label class="form-control input-full"
                                                           name="elevatorHouseholdRatio">${basicUnit.elevatorHouseholdRatio}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">户型数</label>
                                                <div class=" col-sm-3 ">
                                                    <label class="form-control input-full"
                                                           name="huxingNum">${basicUnit.huxingNum}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <c:if test="${formType eq 'industry'}">
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">户型说明</label>
                                                    <div class=" col-sm-3 ">
                                                        <label class="form-control input-full"
                                                               name="huxingExplain">${basicUnit.huxingExplain}</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div id="basicUnit"></div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <c:if test="${empty isHistory}">
                        <%@include file="/views/project/stageSurvey/commonDetail/unitCommonPart.jsp" %>
                        <%@include file="/views/project/stageSurvey/commonDetail/unitStairs.jsp" %>
                        <%@include file="/views/project/stageSurvey/commonDetail/unitElevator.jsp" %>
                        <%@include file="/views/project/stageSurvey/commonDetail/unitDecorate.jsp" %>
                    </c:if>
                    <%@include file="/views/project/chksCustomize/chksSurvey.jsp" %>
                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">
                        <div class="card-body">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                关闭
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

<%@include file="/views/share/chksCommon.jsp" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.unit.js?v=${assessVersion}"></script>


<script type="text/javascript">
    $(function () {
        unitCommon.initDetailById('${basicUnit.id}','',false);
    })
</script>
</html>
