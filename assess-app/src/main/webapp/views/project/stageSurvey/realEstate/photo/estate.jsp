<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <title>附件上传页面</title>
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
                                        附件上传页面

                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frm_estate" class="form-horizontal">
                                    <input type="hidden" name="id" value="${basicEstate.id}">
                                    <input type="hidden" name="quoteId" value="${basicEstate.quoteId}">


                                    <div class="row form-group" style="display: none;">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <div class="col-sm-12">
                                                    <table class="table estateStreetInfos">
                                                        <thead>
                                                        <tr>
                                                            <th style="width: 10%">街道号</th>
                                                            <th style="width: 10%">大门名称</th>
                                                            <th style="width: 10%">附号</th>
                                                            <th style="width: 7%">大门图</th>
                                                            <th style="width: 7%"></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                    <div id="nonIndustrialFile"></div>
                                    <c:if test="${formType eq 'industry'}">
                                        <div id="industrialFile"></div>
                                    </c:if>
                                </form>

                                <div class="x_content" style="display: none;">
                                    <div class="card-header">
                                        <div class="card-title">土地实体情况</div>
                                    </div>
                                    <form id="frm_estateLandState" class="form-horizontal">
                                        <input type="hidden" name="id" value="${basicEstateLandState.id}">
                                    </form>
                                </div>

                            </div>
                        </div>
                    </div>

                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">
                        <div class="card-body">
                            <button type="button" id="cancel_btn btn-sm" class="btn btn-default"
                                    onclick="window.close()">关闭
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
</html>

<%@include file="/views/project/stageSurvey/common/estateStreetInfo.jsp" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.estate.js?v=${assessVersion}"></script>


<script type="text/javascript">
    $(function () {
        estateCommon.initById('${basicEstate.id}');
        //楼盘自动填充插件
        estateCommon.autocompleteStart();
    });


</script>