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
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="card-body">
                                                <div class="row form-group">
                                                    <div class="col-md-12">
                                                        <div class="form-inline x-valid">
                                                            <label class="col-sm-1 control-label">总平面图</label>
                                                            <div class="col-sm-5">
                                                                <input id="estate_floor_total_plan" placeholder="上传附件" class="form-control input-full"
                                                                       type="file">
                                                                <div id="_estate_floor_total_plan"></div>
                                                            </div>
                                                            <label class="col-sm-1 control-label">外观图<span
                                                                    class="symbol required"></span></label>
                                                            <div class="col-sm-5">
                                                                <input id="estate_floor_Appearance_figure" placeholder="上传附件"
                                                                       class="form-control input-full"
                                                                       type="file">
                                                                <div id="_estate_floor_Appearance_figure"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <c:if test="${formType eq 'industry'}">
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">供水平面图</label>
                                                                <div class="col-sm-5">
                                                                    <input id="estate_water_supply_plan" placeholder="上传附件"
                                                                           class="form-control input-full"
                                                                           type="file">
                                                                    <div id="_estate_water_supply_plan"></div>
                                                                </div>
                                                                <label class="col-sm-1 control-label">供电平面图</label>
                                                                <div class="col-sm-5">
                                                                    <input id="estate_power_supply_plan" placeholder="上传附件"
                                                                           class="form-control input-full"
                                                                           type="file">
                                                                    <div id="_estate_power_supply_plan"></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row form-group">
                                                        <div class="col-md-12">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">供气平面图</label>
                                                                <div class="col-sm-5">
                                                                    <input id="estate_air_supply_plan" placeholder="上传附件"
                                                                           class="form-control input-full"
                                                                           type="file">
                                                                    <div id="_estate_air_supply_plan"></div>
                                                                </div>
                                                                <label class="col-sm-1 control-label">采暖平面图</label>
                                                                <div class="col-sm-5">
                                                                    <input id="estate_heating_plan" placeholder="上传附件" class="form-control input-full"
                                                                           type="file">
                                                                    <div id="_estate_heating_plan"></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <div class="x_content">
                                    <div class="x_title">
                                        <h3>土地实体情况 </h3>
                                        <div class="clearfix"></div>
                                    </div>
                                    <form id="frm_estateLandState" class="form-horizontal">
                                        <input type="hidden" name="id" value="${basicEstateLandState.id}">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="card-body">

                                                </div>
                                            </div>
                                        </div>
                                    </form>
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
        src="${pageContext.request.contextPath}/js/examine/examine.estate.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/sonEstateView.js?v=${assessVersion}"></script>
<script type="text/javascript">
    $(function () {
        estateCommon.initById('${basicEstate.id}');
    })
</script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.min.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
</html>
<script type="text/javascript">
</script>

