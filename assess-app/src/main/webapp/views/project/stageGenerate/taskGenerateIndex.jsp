<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <%@include file="/views/share/project/projectInfoSimple.jsp" %>
                    <%@include file="/views/share/project/projectPlanDetails.jsp" %>

                    <!-- 填写表单 start -->
                    <c:forEach items="${generationVos}" var="generationVo">
                        <div class="col-md-12">
                            <div class="card full-height">
                                <div class="card-header collapse-link">
                                    <div class="card-head-row">
                                        <div class="card-title">
                                                ${generationVo.areaGroupName}
                                        </div>
                                        <div class="card-tools">
                                            <button class="btn  btn-link btn-primary btn-xs"><span
                                                    class="fa fa-angle-down"></span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <form class="form-horizontal" id="groupForm${generationVo.areaGroupId}"
                                          enctype="multipart/form-data">
                                        <input type="hidden" id="areaGroupId" name="areaGroupId"
                                               value="${generationVo.areaGroupId}">
                                        <input type="hidden" name="id" value="${generationVo.id}">
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">
                                                        报告出具日期<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <input type="text" name="reportIssuanceDate"
                                                               placeholder="报告出具日期"
                                                               class="form-control input-full date-picker dbdate"
                                                               pattern='yyyy-MM-dd'
                                                               data-date-format="yyyy-mm-dd" required
                                                               value="<fmt:formatDate value='${generationVo.reportIssuanceDate}' pattern='yyyy-MM-dd'/>">
                                                    </div>
                                                    <label class="col-sm-1 control-label">
                                                        作业结束时间<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <input type="text" name="homeWorkEndTime" placeholder="作业结束时间"
                                                               class="form-control input-full date-picker dbdate"
                                                               data-date-format="yyyy-mm-dd"
                                                               pattern='yyyy-MM-dd' required
                                                               value="<fmt:formatDate value='${generationVo.homeWorkEndTime}' pattern='yyyy-MM-dd'/>">
                                                    </div>

                                                    <label class="col-sm-1 control-label">
                                                        资质类型<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control input-full" name="qualificationType"
                                                                onchange="onChange(this)" required>
                                                            <option value="">--请选择--</option>
                                                            <c:if test="${not empty qualificationTypes}">
                                                                <c:forEach items="${qualificationTypes}" var="itemA">
                                                                    <c:if test="${generationVo.qualificationType eq itemA.key}">
                                                                        <option selected="selected"
                                                                                value="${itemA.key}">${itemA.value}</option>
                                                                    </c:if>
                                                                    <c:if test="${generationVo.qualificationType ne itemA.key}">
                                                                        <option value="${itemA.key}">${itemA.value}</option>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </c:if>
                                                        </select>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">
                                                        现场查勘开始日期<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <input type="text" required name="investigationsStartDate"
                                                               placeholder="现场查勘开始日期"
                                                               class="form-control input-full date-picker dbdate"
                                                               data-date-format="yyyy-mm-dd"
                                                               pattern='yyyy-MM-dd'
                                                               value="<fmt:formatDate value='${generationVo.investigationsStartDate}' pattern='yyyy-MM-dd'/>">
                                                    </div>
                                                    <label class="col-sm-1 control-label">
                                                        现场查勘结束日期<span class="symbol required"></span>
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <input type="text" required name="investigationsEndDate"
                                                               placeholder="现场查勘结束日期"
                                                               class="form-control input-full date-picker dbdate"
                                                               data-date-format="yyyy-mm-dd"
                                                               pattern='yyyy-MM-dd'
                                                               value="<fmt:formatDate value='${generationVo.investigationsEndDate}' pattern='yyyy-MM-dd'/>">
                                                    </div>
                                                    <label class="col-sm-1 control-label">估价师<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <select name="realEstateAppraiser" multiple="multiple"
                                                                class="form-control input-full search-select select2"
                                                                required="required">
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">
                                                        备案日期
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <input type="text" name="recordDate" placeholder="备案日期"
                                                               class="form-control input-full date-picker dbdate"
                                                               data-date-format="yyyy-mm-dd"
                                                               pattern='yyyy-MM-dd'
                                                               value="<fmt:formatDate value='${generationVo.recordDate}' pattern='yyyy-MM-dd'/>">
                                                    </div>
                                                    <label class="col-sm-1 control-label">查询码</label>
                                                    <div class="col-sm-3">
                                                        <input type="text" name="queryCode"
                                                               class="form-control input-full" placeholder="查询码">
                                                    </div>
                                                    <label class="col-sm-1 control-label">备案号</label>
                                                    <div class="col-sm-3">
                                                        <input type="text" name="recordNo"
                                                               class="form-control input-full" placeholder="备案号">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">评估类型(增加封面)</label>
                                                    <div class="col-sm-3">
                                                        <select name="assessCategory"
                                                                class="form-control input-full search-select select2">
                                                            <option>请选择</option>
                                                            <c:forEach items="${projectCategoryKeyValueDtoList}"
                                                                       var="projectCategoryKey">
                                                                <option value="${projectCategoryKey.key}">${projectCategoryKey.value}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <c:forEach items="${reportTypeList}" var="reportType" varStatus="status">
                                            <div class="row form-group">
                                                <div class="col-md-12">
                                                    <div class="form-inline x-valid">
                                                        <div class="col-sm-1">
                                                            <a class="btn-primary btn btn-sm"
                                                               onclick="reGetDocumentNumber('${generationVo.areaGroupId}','${reportType.id}',this)">重新拿号<i
                                                                    class="fa fa-undo"></i></a>
                                                        </div>

                                                        <label class="col-sm-1">
                                                            <!-- 报告附件方法 -->
                                                            <a class="btn-dark btn btn-sm"
                                                               onclick="generateReport('${generationVo.areaGroupId}','${reportType.id}',this)">生成${reportType.name}
                                                                <i class="fa fa-file-word-o"></i></a>
                                                        </label>

                                                        <div class="col-sm-3">
                                                            <!-- 报告附件id -->
                                                            <div id="_${reportType.fieldName}${generationVo.areaGroupId}"></div>
                                                        </div>

                                                        <div class="col-sm-1">
                                                            <div class="btn-group">
                                                                <button type="button" class="btn btn-primary btn-sm">
                                                                    意见稿
                                                                </button>
                                                                <button type="button"
                                                                        class="btn btn-primary dropdown-toggle btn-sm"
                                                                        data-toggle="dropdown">
                                                                    <span class="caret"></span>
                                                                </button>
                                                                <ul class="dropdown-menu" role="menu">
                                                                    <c:forEach var="item"
                                                                               items="${documentTemplateList}">
                                                                        <li>
                                                                            <a href="${pageContext.request.contextPath}/documentOpinion/applyIndex/${item.id}&${projectInfo.id}&${reportType.fieldName}&${generationVo.areaGroupId}&${reportType.id}&${generationVo.id}"
                                                                               target="_blank">${item.templateName}</a>
                                                                        </li>
                                                                    </c:forEach>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-1">
                                                            <c:if test="${generationVo != null}">
                                                                <c:if test="${generationVo.id != null}">
                                                                    <input id="GGGGGG${reportType.fieldName}${generationVo.areaGroupId}"
                                                                           name="file" type="file" style="display: none"
                                                                           onchange="upFileLoadReport(this,'${reportType.fieldName}${generationVo.areaGroupId}' ,'${generationVo.id}' ,'${generationVo.areaGroupId}' )">
                                                                    <div class="btn btn-primary btn-sm"
                                                                         onclick="$(this).prev().trigger('click')">上传报告
                                                                    </div>
                                                                </c:if>
                                                            </c:if>
                                                        </div>

                                                        <div class="col-sm-1">
                                                            <a class="btn-primary btn btn-sm"
                                                               onclick="getReportNumber('${generationVo.areaGroupId}','${reportType.id}',this)">拿号<i
                                                                    class="fa fa-dot-circle-o"></i></a>
                                                        </div>

                                                        <!-- 结果表附件 方法 -->
                                                        <div class="col-sm-1">
                                                            <a class="btn-primary btn btn-sm"
                                                               onclick="resultSheetReport('${generationVo.areaGroupId}','${reportType.id}',this)">结果表附件<i
                                                                    class="fa fa-file-word-o"></i></a>
                                                        </div>
                                                        <!-- 结果表附件id -->
                                                        <div class="col-sm-2">
                                                            <div id="_${reportType.id}result_sheet_one${generationVo.areaGroupId}"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <script type="text/javascript">
                            $(function () {
                                getSchemeReportGeneration({
                                    projectPlanId: '${projectPlan.id}',
                                    areaGroupId: '${generationVo.areaGroupId}'
                                }, function (info) {
                                    initFormSchemeReportGeneration(info, $('#groupForm${generationVo.areaGroupId}'), '${generationVo.areaGroupId}');
                                });
                            })
                        </script>
                    </c:forEach>

                    <%--                    <c:forEach items="${generationVos}" var="generationVo">
                                            <div class="x_panel area_panel">
                                                <div class="x_title collapse-link">
                                                    <ul class="nav navbar-right panel_toolbox">
                                                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                                                    </ul>
                                                    <h3>${generationVo.areaGroupName}</h3>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="x_content">
                                                    <form class="form-horizontal" id="groupForm${generationVo.areaGroupId}"
                                                          enctype="multipart/form-data">
                                                        <input type="hidden" id="areaGroupId" name="areaGroupId"
                                                               value="${generationVo.areaGroupId}">
                                                        <input type="hidden" name="id" value="${generationVo.id}">
                                                        <div class="row form-group">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">
                                                                    报告出具日期<span class="symbol required"></span>
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" name="reportIssuanceDate" placeholder="报告出具日期"
                                                                           class="form-control input-full date-picker dbdate" pattern='yyyy-MM-dd'
                                                                           data-date-format="yyyy-mm-dd" required
                                                                           value="<fmt:formatDate value='${generationVo.reportIssuanceDate}' pattern='yyyy-MM-dd'/>">
                                                                </div>
                                                            </div>
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">
                                                                    作业结束时间<span class="symbol required"></span>
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" name="homeWorkEndTime" placeholder="作业结束时间"
                                                                           class="form-control input-full date-picker dbdate"
                                                                           data-date-format="yyyy-mm-dd"
                                                                           pattern='yyyy-MM-dd' required
                                                                           value="<fmt:formatDate value='${generationVo.homeWorkEndTime}' pattern='yyyy-MM-dd'/>">
                                                                </div>
                                                            </div>
                                                            <div class="form-inline x-valid">
                                                                <div>
                                                                    <label class="col-sm-1 control-label">
                                                                        资质类型<span class="symbol required"></span>
                                                                    </label>
                                                                    <div class="col-sm-3">
                                                                        <select class="form-control input-full" name="qualificationType"
                                                                                onchange="onChange(this)" required>
                                                                            <option value="">--请选择--</option>
                                                                            <c:if test="${not empty qualificationTypes}">
                                                                                <c:forEach items="${qualificationTypes}" var="itemA">
                                                                                    <c:if test="${generationVo.qualificationType eq itemA.key}">
                                                                                        <option selected="selected"
                                                                                                value="${itemA.key}">${itemA.value}</option>
                                                                                    </c:if>
                                                                                    <c:if test="${generationVo.qualificationType ne itemA.key}">
                                                                                        <option value="${itemA.key}">${itemA.value}</option>
                                                                                    </c:if>
                                                                                </c:forEach>
                                                                            </c:if>
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row form-group">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">
                                                                    现场查勘开始日期<span class="symbol required"></span>
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" required name="investigationsStartDate"
                                                                           placeholder="现场查勘开始日期"
                                                                           class="form-control input-full date-picker dbdate"
                                                                           data-date-format="yyyy-mm-dd"
                                                                           pattern='yyyy-MM-dd'
                                                                           value="<fmt:formatDate value='${generationVo.investigationsStartDate}' pattern='yyyy-MM-dd'/>">
                                                                </div>
                                                            </div>
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">
                                                                    现场查勘结束日期<span class="symbol required"></span>
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" required name="investigationsEndDate" placeholder="现场查勘结束日期"
                                                                           class="form-control input-full date-picker dbdate"
                                                                           data-date-format="yyyy-mm-dd"
                                                                           pattern='yyyy-MM-dd'
                                                                           value="<fmt:formatDate value='${generationVo.investigationsEndDate}' pattern='yyyy-MM-dd'/>">
                                                                </div>
                                                            </div>
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">估价师<span
                                                                        class="symbol required"></span></label>
                                                                <div class="col-sm-3">
                                                                    <select name="realEstateAppraiser" multiple="multiple"
                                                                            class="form-control input-full search-select select2"
                                                                            required="required">
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row form-group">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">
                                                                    备案日期
                                                                </label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" name="recordDate" placeholder="备案日期"
                                                                           class="form-control input-full date-picker dbdate"
                                                                           data-date-format="yyyy-mm-dd"
                                                                           pattern='yyyy-MM-dd'
                                                                           value="<fmt:formatDate value='${generationVo.recordDate}' pattern='yyyy-MM-dd'/>">
                                                                </div>
                                                            </div>

                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">查询码</label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" name="queryCode" class="form-control input-full" placeholder="查询码">
                                                                </div>
                                                            </div>

                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">备案号</label>
                                                                <div class="col-sm-3">
                                                                    <input type="text" name="recordNo" class="form-control input-full" placeholder="备案号">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row form-group">
                                                            <div class="form-inline x-valid">
                                                                <label class="col-sm-1 control-label">评估类型(增加封面)</label>
                                                                <div class="col-sm-3">
                                                                    <select name="assessCategory" class="form-control input-full search-select select2">
                                                                        <option>请选择</option>
                                                                        <c:forEach items="${projectCategoryKeyValueDtoList}"
                                                                                   var="projectCategoryKey">
                                                                            <option value="${projectCategoryKey.key}">${projectCategoryKey.value}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <c:forEach items="${reportTypeList}" var="reportType" varStatus="status">
                                                            <div class="row form-group">
                                                                <div class="form-inline x-valid">
                                                                    <div class="col-sm-1">
                                                                        <a class="btn-primary btn "
                                                                           onclick="reGetDocumentNumber('${generationVo.areaGroupId}','${reportType.id}',this)">重新拿号<i
                                                                                class="fa fa-undo"></i></a>
                                                                    </div>

                                                                    <label class="col-sm-1">
                                                                        <!-- 报告附件方法 -->
                                                                        <a class="btn-dark btn "
                                                                           onclick="generateReport('${generationVo.areaGroupId}','${reportType.id}',this)">生成${reportType.name}
                                                                            <i class="fa fa-file-word-o"></i></a>
                                                                    </label>

                                                                    <div class="col-sm-3">
                                                                        <!-- 报告附件id -->
                                                                        <div id="_${reportType.fieldName}${generationVo.areaGroupId}"></div>
                                                                    </div>
                                                                </div>

                                                                <div class="form-inline x-valid">
                                                                    <div class="col-sm-1">
                                                                        <div class="btn-group">
                                                                            <button type="button" class="btn btn-primary">
                                                                                意见稿
                                                                            </button>
                                                                            <button type="button" class="btn btn-primary dropdown-toggle"
                                                                                    data-toggle="dropdown">
                                                                                <span class="caret"></span>
                                                                            </button>
                                                                            <ul class="dropdown-menu" role="menu">
                                                                                <c:forEach var="item" items="${documentTemplateList}">
                                                                                    <li>
                                                                                        <a href="${pageContext.request.contextPath}/documentOpinion/applyIndex/${item.id}&${projectInfo.id}&${reportType.fieldName}&${generationVo.areaGroupId}&${reportType.id}&${generationVo.id}"
                                                                                           target="_blank">${item.templateName}</a>
                                                                                    </li>
                                                                                </c:forEach>
                                                                            </ul>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-sm-1">
                                                                        <c:if test="${generationVo != null}">
                                                                            <c:if test="${generationVo.id != null}">
                                                                                <input id="GGGGGG${reportType.fieldName}${generationVo.areaGroupId}"
                                                                                       name="file" type="file" style="display: none"
                                                                                       onchange="upFileLoadReport(this,'${reportType.fieldName}${generationVo.areaGroupId}' ,'${generationVo.id}' ,'${generationVo.areaGroupId}' )">
                                                                                <div class="btn btn-primary"
                                                                                     onclick="$(this).prev().trigger('click')">上传报告
                                                                                </div>
                                                                            </c:if>
                                                                        </c:if>
                                                                    </div>
                                                                </div>

                                                                <div class="form-inline x-valid">
                                                                    <div class="col-sm-1">
                                                                        <a class="btn-primary btn "
                                                                           onclick="getReportNumber('${generationVo.areaGroupId}','${reportType.id}',this)">拿号<i
                                                                                class="fa fa-dot-circle-o"></i></a>
                                                                    </div>
                                                                </div>

                                                                <div class="form-inline x-valid">
                                                                    <!-- 结果表附件 方法 -->
                                                                    <div class="col-sm-1">
                                                                        <a class="btn-primary btn "
                                                                           onclick="resultSheetReport('${generationVo.areaGroupId}','${reportType.id}',this)">结果表附件<i
                                                                                class="fa fa-file-word-o"></i></a>
                                                                    </div>
                                                                    <!-- 结果表附件id -->
                                                                    <div class="col-sm-2">
                                                                        <div id="_${reportType.id}result_sheet_one${generationVo.areaGroupId}"></div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </c:forEach>
                                                    </form>
                                                </div>
                                            </div>
                                            <script type="text/javascript">
                                                $(function () {
                                                    getSchemeReportGeneration({
                                                        projectPlanId: '${projectPlan.id}',
                                                        areaGroupId: '${generationVo.areaGroupId}'
                                                    }, function (info) {
                                                        initFormSchemeReportGeneration(info, $('#groupForm${generationVo.areaGroupId}'), '${generationVo.areaGroupId}');
                                                    });
                                                })
                                            </script>
                                        </c:forEach>--%>


                    <%@include file="/views/share/form_apply.jsp" %>
                    <%@include file="/views/share/form_log.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>


</body>

</html>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/map.position.js?v=1.0"></script>
<input type="file" id="ajaxFileUpload" name="file" style="display: none;">
<script type="text/javascript">

    /**
     * 获取资质
     * @param data
     * @param callback
     */
    function getAdPersonalIdentityDto(data, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/public/getAdPersonalIdentityDto",
            data: data,
            type: "get",
            dataType: "json",
            success: function (result) {
                if (result.ret && result.data) {
                    callback(result.data);
                } else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }

    //上传报告 临时添加zch
    function upFileLoadReport(that, fileId, id, areaGroupId) {
        var fileElementId = $(that).attr("id");
        $.ajaxFileUpload({
            type: "POST",
            url: "${pageContext.request.contextPath}/public/importAjaxFile",
            data: {
                tableName: AssessDBKey.GenerateReportInfo,
                tableId: id,
                fieldsName: fileId
            },//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: fileElementId,//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                if (result.ret) {
                    initFormSchemeReportGeneration({id: id}, $(that).closest('form'), areaGroupId);
                }
            },
            error: function (result, status, e) {
                Loading.progressHide();
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }

    /**
     * @author:  zch
     * 描述:报告附件 数组  拼接为REPORT_TYPE_PREAUDIT
     * @date:  2019-05-27
     **/
    function getSchemeReportGenerationFileControlIdArray(callback) {
        AssessCommon.loadDataDicByKey(AssessDicKey.REPORT_TYPE, '', function (html, data) {
            var fileArray = [];
            var underline = "_";
            data.forEach(function (value, index) {
                var fieldName = value.fieldName;
                if (fieldName) {
                    var strArray = fieldName.split(".");
                    var tempArray = [];
                    if (strArray.length >= 1) {
                        strArray.forEach(function (item, i) {
                            tempArray.push(item.toUpperCase());
                        });
                    }
                    if (tempArray.length >= 1) {
                        fileArray.push(tempArray.join(underline));
                    }
                }
                //单一的一个结果集
                fileArray.push(value.id + "result_sheet_one");
            });
            if (callback) {
                callback(fileArray);
            }
        });
    }


    function fileShow(fieldsName, deleteFlag, id) {
        FileUtils.getFileShows({
            target: fieldsName,
            //showMode: 'table',
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.GenerateReportInfo,
                tableId: id == undefined ? 0 : id
            },
            editFlag: true,
            deleteFlag: deleteFlag == undefined ? true : deleteFlag
        })
    }

    function onChange(item) {
        var v = $(item).find("option:selected");
        var frm = $(item).closest("form");
        getAdPersonalIdentityDto({qualificationType: v.val()}, function (data) {
            dataQualificationShow(data, null, frm);
        });
    }

    /**
     * 资质显示
     * @param data
     * @param realEstateAppraiser
     * @param frm
     */
    function dataQualificationShow(data, realEstateAppraiser, frm) {
        var retHtml = '';
        $.each(data, function (i, item) {
            retHtml += '<option key="' + item.name + '" title="' + item.name + '" value="' + item.userAccount + '"';
            if (realEstateAppraiser) {
                var tempArr = realEstateAppraiser.split(",");
                $.each(tempArr, function (i, n) {
                    if (item.userAccount == n) {
                        retHtml += 'selected="selected"';
                    }
                });
            }
            retHtml += '>' + item.name + '</option>';
        });
        $(frm).find("select[name='realEstateAppraiser']").empty().html(retHtml).trigger('change');
    }


    //赋值
    function initFormSchemeReportGeneration(info, frm, areaGroupId) {
        if (info) {
            getSchemeReportGenerationFileControlIdArray(function (schemeReportGenerationFileControlIdArray) {
                $.each(schemeReportGenerationFileControlIdArray, function (i, n) {
                    fileShow(n + "" + areaGroupId, true, info.id);
                });
            });
            frm.initForm(info);
            getAdPersonalIdentityDto({qualificationType: info.qualificationType}, function (data) {
                dataQualificationShow(data, info.realEstateAppraiser, frm);
            });
        }
    }

    function getSchemeReportGeneration(data, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/generateReport/getGenerateReportGeneration",
            data: data,
            type: "get",
            dataType: "json",
            success: function (result) {
                if (result.ret && result.data) {
                    callback(result.data);
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }

    //重新拿号 也会替换文号相关的内容
    function reGetDocumentNumber(areaId, reportType, item) {
        var form = $(item).closest("form");
        var data = formSerializeArray(form);
        data.areaGroupId = areaId;
        data.symbolOperation = 'reset';//重新拿号 标志
        data.projectPlanId = '${projectPlan.id}';
        data.projectId = '${projectPlan.projectId}';
        if (!AssessCommon.isNumber(data.assessCategory)) {
            data.assessCategory = null;
        }
        AlertConfirm("是否确认", "请注意报告二维码无法自动替换,假如要替换类似于报告二维码这样的数据请在页面上上删除报告二维码,然后用\\${报告二维码}这样的文本放置在删除的位置", function () {
            generateReportHandle(data, reportType, form, areaId, function () {
                notifySuccess("成功",'重新拿号成功!');
            });
        })

    }

    //拿号 并替换文号
    function getReportNumber(areaId, reportType, item) {
        var form = $(item).closest("form");
        var data = formSerializeArray(form);
        data.areaGroupId = areaId;
        data.symbolOperation = 'get';//拿号 标志
        data.projectPlanId = '${projectPlan.id}';
        data.projectId = '${projectPlan.projectId}';
        if (!AssessCommon.isNumber(data.assessCategory)) {
            data.assessCategory = null;
        }
        generateReportHandle(data, reportType, form, areaId, function () {
            notifySuccess("成功",'拿号成功');
        });
    }

    //生成  结果集  附件
    function resultSheetReport(areaId, reportType, item) {
        var form = $(item).closest("form");
        var data = formSerializeArray(form);
        data.areaGroupId = areaId;
        data.projectPlanId = '${projectPlan.id}';
        data.projectId = '${projectPlan.projectId}';
        if (!AssessCommon.isNumber(data.assessCategory)) {
            data.assessCategory = null;
        }
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/generateReport/resultSheetReport",
            data: {fomData: JSON.stringify(data), reportType: reportType},
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    notifySuccess("成功",'生成结果集附件成功');
                    getSchemeReportGeneration(data, function (info) {
                        initFormSchemeReportGeneration(info, form, areaId);
                    });
                } else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }

    //生成报告
    function generateReport(areaId, reportType, item) {
        var form = $(item).closest("form");
        var data = formSerializeArray(form);
        if (!form.valid()) {
            return false;
        }
        if (data.realEstateAppraiser) {

        } else {
            notifyInfo('提示','估价师必须选择');
            return false;
        }
        data.areaGroupId = areaId;
        data.projectPlanId = '${projectPlan.id}';
        data.projectId = '${projectPlan.projectId}';
        data.symbolOperation = 'none';//不拿号 标志
        if (!AssessCommon.isNumber(data.assessCategory)) {
            data.assessCategory = null;
        }
        generateReportHandle(data, reportType, form, areaId, function () {
            notifySuccess("成功",'报告生成成功');
        });
    }

    //报告替换 method
    function generateReportHandle(data, ids, form, areaId, callback) {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/generateReport/generate",
            data: {ids: ids, fomData: JSON.stringify(data)},
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    getSchemeReportGeneration(data, function (info) {
                        initFormSchemeReportGeneration(info, form, areaId);
                        if (callback) {
                            callback();
                        }
                        Loading.progressHide();
                    });
                } else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }

    /**
     * 报告信息 修改或者添加
     * @param data
     * @param callback
     */
    function saveGenerateReportInfo(data, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/generateReport/saveGenerateReportInfo",
            data: {fomData: JSON.stringify(data)},
            type: "post",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback();
                    }
                } else {
                    AlertError("失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        });
    }

    //提交
    function submit() {
        var allData = [];
        $(".area_panel").each(function () {
            var form = $(this).find('form');
            allData.push(formSerializeArray(form));
        });
        saveGenerateReportInfo(allData, function () {
            var data = {};
            data.planId = '${projectPlan.id}';
            data.areaGroupId = $("#areaGroupId").val();
            if ("${processInsId}" != "0") {
                submitEditToServer(JSON.stringify(data));
            }
            else {
                submitToServer(JSON.stringify(data));
            }
        });
    }

    //提交
    function commitApply() {
        var allData = [];
        var isPass = true;
        $(".area_panel").each(function () {
            $(this).find('.x_content').show();
            var form = $(this).find('form');
            isPass = form.valid();
            allData.push(formSerializeArray(form));
        });
        if (!isPass) {
            return false;
        }
        var data = {};
        data.planId = '${projectPlan.id}';
        data.areaGroupId = $("#areaGroupId").val();
        var url = "${pageContext.request.contextPath}/generate/submitApply";
        if ("${empty processInsId?"0":processInsId}" != "0") {
            url = "${pageContext.request.contextPath}/generate/submitEditApproval";
            var approvalData = formParams("frm_approval");
            data = $.extend(data, approvalData);
        }
        saveGenerateReportInfo(allData, function () {
            //提交流程
            $.ajax({
                url: url,
                data: data,
                success: function (result) {
                    if (result.ret) {
                        AlertSuccess("成功", "提交成功", function () {
                            window.close();
                        });
                    } else {
                        AlertError(result.errmsg);
                    }
                }
            })
        });
    }

</script>
