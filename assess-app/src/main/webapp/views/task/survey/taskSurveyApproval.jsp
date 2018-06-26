<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>


<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfo.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>

            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2>${parentProject.projectPhaseName}:${projectPlanDetails.projectPhaseName}</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_assess" class="form-horizontal">

                        <div class="form-group">

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">评估人员<span class="symbol required"></span></label>
                                <div class="col-sm-2">
                                    <label class="form-control">${thisUserInfo.userName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">核对日期<span class="symbol required"></span></label>
                                <div class="col-sm-2">
                                    <label class="form-control"><fmt:formatDate value="${surveyAssetInventory.checkDate}" pattern="yyyy-MM-dd"/></label>
                                </div>
                            </div>

                        </div>
                    </form>

                    <div class="x_title collapse-link">
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                        </ul>
                        <h2>
                            <small><i class="fa fa-bars"></i>清查内容</small>
                        </h2>
                        <div class="clearfix"></div>
                    </div>

                    <table class="table" id="tb_List">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>清查内容</th>
                            <th>是否一致</th>
                            <th>登记面积</th>
                            <th>实际面积</th>
                            <th>差异原因</th>
                            <th>证明文件</th>
                            <th>证明文件附件</th>
                            <th>证明人</th>
                            <th>调查时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${surveyAssetTemplateVos}" var="items" varStatus="s">
                            <tr>
                                    <%--<input type="hidden" id="id" name="id" value="${items.id}">--%>
                                <td>${s.index + 1}</td>
                                <td>
                                    <label class="form-control">${items.inventoryContentName}</label>
                                </td>
                                <td>
                                    <label class="form-control">${items.areConsistent}</label>
                                </td>
                                <td>
                                    <label class="form-control">${items.registrationAddress}</label>
                                </td>
                                <td>
                                    <label class="form-control">${items.actualAddress}</label>
                                </td>
                                <td>
                                    <label class="form-control">${items.differenceReason}</label>
                                </td>
                                <td>
                                    <label class="form-control">${items.credential}</label>
                                </td>
                                <td>
                                    <div id="_credentialAccessory${items.id}"></div>
                                </td>
                                <td>
                                    <label class="form-control">${items.voucher}</label>
                                </td>
                                <td>
                                    <label class="form-control">
                                        <fmt:formatDate value="${items.surveyTime}" pattern="yyyy-MM-dd"/>
                                    </label>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <%--<table class="table table-bordered" id="tb_List">--%>
                    <%--<!-- cerare document add ajax data-->--%>
                    <%--</table>--%>

                    <div class="x_title collapse-link">
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                        </ul>
                        <h2>
                            <small><i class="fa fa-bars"></i>他项权利</small>
                        </h2>
                        <div class="clearfix"></div>
                    </div>
                    <table class="table table-bordered" id="tb_OtherList">
                        <!-- cerare document add ajax data-->
                    </table>

                    <%--<form id="frm_checkbox" class="form-horizontal">--%>
                    <%--<div class="form-group">--%>
                    <%--<label class="col-sm-2 control-label" data-label="&lt;label&gt;">--%>
                    <%--他项权利与实际情况--%>
                    <%--</label>--%>
                    <%--</div>--%>
                    <%--</form>--%>


                    <%--<form id="frm_survey" class="form-horizontal">--%>
                    <%--<div class="form-group">--%>
                    <%--<label class="col-sm-1 control-label">--%>
                    <%--他权登记人--%>
                    <%--</label>--%>
                    <%--<div class="x-valid">--%>
                    <%--<div class="col-sm-2">--%>
                    <%--<label class="form-control">${surveyAssetOtherTemplate.otherRightsRegistrar}</label>--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <%--<label class="col-sm-1 control-label">--%>
                    <%--实际行权人--%>
                    <%--</label>--%>
                    <%--<div class="x-valid">--%>
                    <%--<div class="col-sm-2">--%>
                    <%--<label class="form-control">${surveyAssetOtherTemplate.rightHander}</label>--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <%--<label class="col-sm-1 control-label">--%>
                    <%--登记面积--%>
                    <%--</label>--%>
                    <%--<div class="x-valid">--%>
                    <%--<div class="col-sm-2">--%>
                    <%--<label class="form-control">${surveyAssetOtherTemplate.registerArea}</label>--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <%--</div>--%>

                    <%--<div class="form-group">--%>

                    <%--<label class="col-sm-1 control-label">--%>
                    <%--实际面积--%>
                    <%--</label>--%>
                    <%--<div class="x-valid">--%>
                    <%--<div class="col-sm-2">--%>
                    <%--<label class="form-control">${surveyAssetOtherTemplate.actualArea}</label>--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <%--<label class="col-sm-1 control-label">--%>
                    <%--登记用途--%>
                    <%--</label>--%>
                    <%--<div class="x-valid">--%>
                    <%--<div class="col-sm-2">--%>
                    <%--<label class="form-control">${surveyAssetOtherTemplate.registerPurpose}</label>--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <%--<label class="col-sm-1 control-label">--%>
                    <%--实际用途--%>
                    <%--</label>--%>
                    <%--<div class="x-valid">--%>
                    <%--<div class="col-sm-2">--%>
                    <%--<label class="form-control">${surveyAssetOtherTemplate.actualPurpose}</label>--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <%--</div>--%>

                    <%--<div class="form-group">--%>

                    <%--<div class="x-valid">--%>
                    <%--<label class="col-sm-1 control-label">登记日期</label>--%>
                    <%--<div class="col-sm-2">--%>
                    <%--<label class="form-control"><fmt:formatDate value="${surveyAssetOtherTemplate.registerDate}" pattern="yyyy-MM-dd"/></label>--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="x-valid">--%>
                    <%--<label class="col-sm-1 control-label">到期日</label>--%>
                    <%--<div class="col-sm-2">--%>
                    <%--<label class="form-control"><fmt:formatDate value="${surveyAssetOtherTemplate.dueDate}" pattern="yyyy-MM-dd"/></label>--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="x-valid">--%>
                    <%--<label class="col-sm-1 control-label">实际行权人行权日期</label>--%>
                    <%--<div class="col-sm-2">--%>
                    <%--<label class="form-control"><fmt:formatDate value="${surveyAssetOtherTemplate.exerciseDate}" pattern="yyyy-MM-dd"/></label>--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <%--</div>--%>

                    <%--<div class="form-group">--%>

                    <%--<div class="x-valid">--%>
                    <%--<label class="col-sm-1 control-label">预计到期日</label>--%>
                    <%--<div class="col-sm-2">--%>
                    <%--<label class="form-control"><fmt:formatDate value="${surveyAssetOtherTemplate.predictDueDate}" pattern="yyyy-MM-dd"/></label>--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <%--</div>--%>
                    <%--</form>--%>

                </div>
            </div>

            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2>${parentProject.projectPhaseName}-${projectPlanDetails.projectPhaseName}工作成果</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                实际工时
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control">${projectPlanDetails.actualHours}</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                成果描述
                            </label>
                            <div class="col-sm-11">
                                <label class="form-control">${projectPlanDetails.taskRemarks}</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                成果文件
                            </label>
                            <div class="col-sm-11">
                                <div id="_file_upload_task"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    $(function () {
//        loadDataDicList();
        loadDataList();
        showFiles();
        FileUtils.getFileShows({
            target: "file_upload_task",
            formData: {
                tableName: AssessDBKey.ProjectPlanDetails,
                tableId: ${projectPlanDetails.id},
                fieldsName: "apply",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: false
        })
    })
    function saveform() {
        saveApprovalform("");
    }


    <%--function loadDataDicList() {--%>
    <%--var cols = [];--%>
    <%--cols.push({field: 'inventoryContentName', title: '清查内容'});--%>
    <%--cols.push({field: 'areConsistent', title: '是否一致'});--%>
    <%--cols.push({field: 'registrationAddress', title: '登记面积'});--%>
    <%--cols.push({field: 'actualAddress', title: '实际面积'});--%>
    <%--cols.push({field: 'differenceReason', title: '差异原因'});--%>
    <%--cols.push({field: 'credential', title: '证明文件'});--%>
    <%--cols.push({field: 'credentialAccessory',--%>
    <%--title: '证明文件附件',--%>
    <%--});--%>
    <%--cols.push({field: 'voucher', title: '证明人'});--%>

    <%--cols.push({--%>
    <%--field: 'surveyTime', title: '调查时间', formatter: function (value, row, index) {--%>
    <%--return formatDate(value, false);--%>
    <%--}--%>
    <%--});--%>

    <%--$("#tb_List").bootstrapTable('destroy');--%>
    <%--TableInit("tb_List", "${pageContext.request.contextPath}/surveyAssetTemplate/list", cols, {--%>
    <%--pid: ${surveyAssetInventory.id}--%>
    <%--}, {--%>
    <%--showColumns: false,--%>
    <%--showRefresh: false,--%>
    <%--search: false--%>
    <%--});--%>
    <%--}--%>

    function loadDataList() {
        var cols = [];
        cols.push({field: 'typeName', title: '类型'});
        cols.push({field: 'otherRightsRegistrar', title: '他权登记人'});
        cols.push({field: 'rightHander', title: '实际行权人'});
        cols.push({field: 'registerArea', title: '登记面积'});
        cols.push({field: 'actualArea', title: '实际面积'});
        cols.push({field: 'registerPurpose', title: '登记用途'});
        cols.push({field: 'actualPurpose', title: '实际用途'});

        cols.push({
            field: 'registerDate', title: '登记日期', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        cols.push({
            field: 'dueDate', title: '到期日', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        cols.push({
            field: 'exerciseDate', title: '实际行权人行权日期', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        cols.push({
            field: 'predictDueDate', title: '预计到期日', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        cols.push({field: 'sorting', title: '排序'});

        $("#tb_OtherList").bootstrapTable('destroy');
        TableInit("tb_OtherList", "${pageContext.request.contextPath}/surveyAssetOtherTemplate/list", cols, {
            pid: ${surveyAssetInventory.id}
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
        });
    }

    function showFiles() {
        var temp = $("#tb_List").find('tbody tr');
        $.each(temp, function (i, tr) {
            FileUtils.getFileShows({
                target: "credentialAccessory${surveyAssetTemplateVos.get(i).id}",
                formData: {
                    tableName: "tb_survey_asset_template",
                    tableId: ${surveyAssetTemplateVos.get(i).id},
                    fieldsName: "credentialAccessory"
                },
                deleteFlag: false
            })
        })
    }


</script>
</body>
</html>

