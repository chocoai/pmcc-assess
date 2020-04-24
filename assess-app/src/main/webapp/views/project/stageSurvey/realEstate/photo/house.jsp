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
                                <div class="x_content">
                                    <div class="x_title">
                                        <h3>
                                            房屋基本信息
                                        </h3>
                                        <div class="clearfix"></div>
                                    </div>
                                    <form class="form-horizontal" id="basicHouseFrm">
                                        <input type="hidden" name="id" value="${basicHouse.id}">
                                        <input type="hidden" name="quoteId" value="${basicHouse.quoteId}">
                                        <input type="hidden" name="unitId" value="${basicHouse.unitId}">
                                        <input type="hidden" name="estateId" value="${basicHouse.estateId}">
                                        <input type="hidden" name="buildingId" value="${basicHouse.buildingId}">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="card-body">


                                                    <div id="houseFilePart"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="x_content">
                                    <div class="x_title">
                                        <h3>
                                            户型
                                        </h3>
                                        <div class="clearfix"></div>
                                    </div>
                                    <form class="form-horizontal" id="basicHouseHuxing">
                                        <input type="hidden" name="id" value="${basicHouseHuxing.id}">
                                        <div id="houseHuxingFilePart"></div>
                                    </form>
                                </div>
                                <div class="x_content">
                                    <div class="x_title">
                                        <h3>
                                            房屋交易信息
                                        </h3>
                                        <div class="clearfix"></div>
                                    </div>
                                    <form class="form-horizontal" id="basicTradingFrm">
                                        <input type="hidden" name="id" value="${basicHouseTrading.id}">



                                        <div class="x_title">
                                            <div class="clearfix"></div>
                                        </div>


                                        <div id="houseTradingFilePart"></div>

                                    </form>
                                </div>
                                <div class="x_content">
                                    <%@include file="/views/project/stageSurvey/common/houseRoom.jsp" %>
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



<script src='${pageContext.request.contextPath}/js/common.column.js?v=${assessVersion}'></script>


<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.house.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/sonHouseView.js?v=${assessVersion}"></script>


<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/select/huxing.select.js?v=${assessVersion}"></script>

<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/autocomplete/roomType.js?v=${assessVersion}"></script>

<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
<script type="text/javascript">
    $(function () {
        houseCommon.initById('${basicHouse.id}');
    })


</script>