<!DOCTYPE html>
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
            <%@include file="/views/share/common_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <!-- 填写表单 start -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        土地信息
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class="x_content">
                                    <div class="card-header">
                                        <div class="card-title">土地类型</div>
                                    </div>
                                    <%@include
                                            file="/views/project/stageSurvey/commonDetail/estateLandUseCategory.jsp" %>
                                    <form id="basicHouseFrm" class="form-horizontal" style="display: none">
                                        <input type="hidden" name="id" value="${basicHouse.id}">
                                        <input type="hidden" name="houseNumber" value="${basicHouse.houseNumber}">
                                    </form>
                                    <c:if test="${projectPhase eq 'caseStudyExtend'}">
                                        <div class="x_title">
                                            <h3>
                                                交易信息
                                                <small>
                                                    <button class="btn btn-sm btn-success" style="margin-left: 5px"
                                                            type="button" onclick="houseTrading.appendHtml(false)">
                                                <span class="btn-label"><i
                                                        class="fa fa-plus"></i>
                                                </span>
                                                        添加分组
                                                    </button>
                                                </small>
                                            </h3>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div id="basicHouseLandTradingAppend"></div>
                                        <%@include
                                                file="/views/project/stageSurvey/commonDetail/houseTradingLandCase.jsp" %>
                                    </c:if>

                                </div>

                                <div class="x_content">
                                    <c:if test="${projectPhase ne 'caseStudyExtend'}">
                                        <%@include
                                                file="/views/project/stageSurvey/commonDetail/houseTradingLandSurvey.jsp" %>
                                    </c:if>
                                    <%@include file="/views/project/stageSurvey/commonDetail/houseFaceStreet.jsp" %>
                                    <%@include file="/views/method/module/projectLandAchievementGroup.jsp" %>
                                </div>
                            </div>
                        </div>
                    </div>

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
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.house.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.house.detail.js?v=${assessVersion}"></script>
<!-- 选项卡 内容之一 -->
<script type="text/html" id="damagedDegreeTabContentHtml">
    <div role="tabpanel" class="tab-pane fade" id="tab_content_{type}" aria-labelledby="profile-tab">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th width="10%">名称</th>
                <th width="10%">实例状况</th>
                <th width="60%">状况内容</th>
                <th width="10%">标准得分</th>
                <th width="10%">分数</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</script>
<!-- 结构部分下面4列 的 支撑html -->
<script type="text/html" id="damagedDegreeTabTrHtml">
    <tr class="group">
        <td>
            <input type="hidden" name="id" value="{id}">
            <input type="hidden" name="category" value="{category}">
            {categoryName}
            <div class="btn btn-xs btn-primary pull-right" {isShow}
                 onclick="damagedDegree.damagedDegreeDetailModalShow('{id}','{category}')">明细内容
            </div>
        </td>
        <td>
            <select class="form-control input-full" data-role="required" required="required" name="entityCondition"
                    onchange="damagedDegree.entityConditionChange(this);"
                    data-intact="{intact}" data-basicallyIntact="{basicallyIntact}"
                    data-generalDamage="{generalDamage}" data-seriousDamage="{seriousDamage}">
                <option value="">-请选择-</option>
                <option value="intact">完好</option>
                <option value="basicallyIntact">基本完好</option>
                <option value="generalDamage">一般损坏</option>
                <option value="seriousDamage">严重损坏</option>
            </select>
        </td>
        <td>
            <span data-name="entityConditionContent" style="color: red;"></span>
            <textarea class="form-control input-full" name="entityConditionContent"></textarea>
        </td>
        <td>
            {standardScore}
        </td>
        <td>
            <input type="text" class="form-control input-full" name="score" value="{score}">
        </td>
    </tr>
</script>
</html>
<script>
    $(function () {
        houseCommon.initDetailById('${basicHouse.id}', '', false);
        if (${projectPhase eq 'caseStudyExtend'}) {
            houseLandTrading.init();
        }
    })
</script>


