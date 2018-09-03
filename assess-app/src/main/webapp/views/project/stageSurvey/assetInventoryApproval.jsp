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
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>

            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2>${declareRecord.name}:${projectPlanDetails.projectPhaseName}</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_assess" class="form-horizontal">

                        <div class="form-group">

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">评估人员</label>
                                <div class="col-sm-2">
                                    <label class="form-control">${thisUserInfo.userName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">核对日期</label>
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
                        <c:forEach items="${surveyAssetInventoryContentVos}" var="items" varStatus="s">
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
                            <script type="text/javascript">
                                $(function () {
                                    //清查内容附件加载
                                    showFileCommon("${items.id}");
                                })
                            </script>
                        </c:forEach>
                        </tbody>
                    </table>

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
        loadAssetOtherRightList();
    })

    //显示附件通用
    function showFileCommon(tableId) {
        FileUtils.getFileShows({
            showMode: 'table',
            target: "credentialAccessory" + tableId,
            formData: {
                tableName: "tb_survey_asset_template",
                tableId: tableId,
                fieldsName: "credentialAccessory",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: false
        })
    }

    function saveform() {
        saveApprovalform("");
    }

    function loadAssetOtherRightList() {
        var cols = [];
        cols.push({field: 'typeName', title: '类型'});
        cols.push({field: 'categoryName', title: '类型'});
        cols.push({field: 'number', title: '他权证编号'});
        cols.push({field: 'obligor', title: '义务人'});
        cols.push({field: 'obligee', title: '权利人'});
        cols.push({field: 'registerArea', title: '登记面积'});
        cols.push({field: 'rightRank', title: '他权级次'});
        cols.push({field: 'registerAmount', title: '登记金额'});
        cols.push({field: 'actualAmount', title: '行权金额'});
        cols.push({
            field: 'registerDate', title: '登记日期', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        cols.push({
            field: 'beginDate', title: '实际行权人行权日期', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        cols.push({
            field: 'endDate', title: '预计到期日', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        $("#tb_OtherList").bootstrapTable('destroy');
        TableInit("tb_OtherList", "${pageContext.request.contextPath}/surveyAssetInventoryRight/list", cols, {
            planDetailsId: '${projectPlanDetails.id}'
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
        });
    }




</script>
</body>
</html>

