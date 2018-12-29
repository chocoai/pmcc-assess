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
                    <h3>${declareRecord.name}</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_assess" class="form-horizontal">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">评估人员</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${thisUserInfo.userName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">核对日期</label>
                                <div class="col-sm-3">
                                    <label class="form-control"><fmt:formatDate
                                            value="${surveyAssetInventory.checkDate}" pattern="yyyy-MM-dd"/></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">是否查看原件</label>
                                <div class="col-sm-3">
                                    <label class="form-control"> ${surveyAssetInventory.bisCheckOriginal eq true? '是':'否'}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">证明文件</label>
                                <div class="col-sm-3">
                                    <div id="_checkOriginalFile"></div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">说明</label>
                                <div class="col-sm-11">
                                    <label class="form-control">${surveyAssetInventory.remark}</label>
                                </div>
                            </div>
                        </div>
                    </form>

                    <div class="x_title collapse-link">
                        <h2>
                            <small>清查内容</small>
                        </h2>
                        <div class="clearfix"></div>
                    </div>

                    <table class="table" id="tb_List">
                        <thead>
                        <tr>
                            <th style="width: 10%">一致性内容</th>
                            <th style="width: 10%">登记</th>
                            <th style="width: 10%">实际</th>
                            <th style="width: 6%">是否一致</th>
                            <th style="width: 10%">差异原因</th>
                            <th style="width: 10%">证明文件</th>
                            <th style="width: 10%">证明文件附件</th>
                            <th style="width: 5%">证明人</th>
                            <th style="width: 6%">调查时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${surveyAssetInventoryContentVos}" var="item" varStatus="s">
                            <tr>
                                <td>
                                    <label class="form-control">${item.inventoryContentName}</label>
                                </td>
                                <td>
                                    <label class="form-control">${item.registration}</label>
                                </td>
                                <td>
                                    <label class="form-control">${item.actual}</label>
                                </td>
                                <td>
                                    <label class="form-control">${item.areConsistent}</label>
                                </td>
                                <c:if test="${item.areConsistent eq '不一致'}">
                                    <td>
                                        <label class="form-control">${item.differenceReason}</label>
                                    </td>
                                    <td>
                                        <label class="form-control">${item.credential}</label>
                                    </td>
                                    <td>
                                        <div id="_credentialAccessory${item.id}"></div>
                                        <script type="text/javascript">
                                            $(function () {
                                                //清查内容附件加载
                                                showFileCommon("${item.id}");
                                            })
                                        </script>
                                    </td>
                                    <td>
                                        <label class="form-control">${item.voucher}</label>
                                    </td>
                                    <td>
                                        <label class="form-control">
                                            <fmt:formatDate value="${item.surveyTime}" pattern="yyyy-MM-dd"/>
                                        </label>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <div class="x_title">
                        <h2>
                            <small>他项权利</small>
                        </h2>
                        <div class="clearfix"></div>
                    </div>
                    <table class="table table-bordered" id="tb_inventory_right_list">
                        <!-- cerare document add ajax data-->
                    </table>
                    <div class="form-horizontal">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">特殊情况</label>
                                <div class="col-sm-11">
                                    <label class="form-control">${surveyAssetInventory.specialCase}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">特殊情况附件</label>
                                <div class="col-sm-3">
                                    <div id="_specialCaseFile"></div>
                                </div>
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
<!--查看他项权利信息-->
<div id="viewInventoryRightModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">他项权利信息</h3>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">类型</label>
                        <div class="col-sm-4">
                            <label class="form-control" data-name="typeName"></label>
                        </div>
                    </div>
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">类别</label>
                        <div class="col-sm-4">
                            <label class="form-control" data-name="categoryName"></label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">他权证编号</label>
                        <div class="col-sm-4">
                            <label class="form-control" data-name="number"></label>
                        </div>
                    </div>
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">登记日期</label>
                        <div class="col-sm-4">
                            <label class="form-control" data-name="registerDate"></label>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">
                            义务人
                        </label>
                        <div class="col-sm-4">
                            <label class="form-control" data-name="obligor"></label>
                        </div>
                    </div>
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">
                            权利人
                        </label>
                        <div class="col-sm-4">
                            <label class="form-control" data-name="obligee"></label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">
                            登记金额
                        </label>
                        <div class="col-sm-4">
                            <label class="form-control" data-name="registerAmount"></label>
                        </div>
                    </div>
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">
                            行权金额
                        </label>
                        <div class="col-sm-4">
                            <label class="form-control" data-name="actualAmount"></label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">
                            登记面积
                        </label>
                        <div class="col-sm-4">
                            <label class="form-control" data-name="registerArea"></label>
                        </div>
                    </div>
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">
                            他权级次
                        </label>
                        <div class="col-sm-4">
                            <label class="form-control" data-name="rightRank"></label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">开始日期</label>
                        <div class="col-sm-4">
                            <label class="form-control" data-name="beginDate"></label>
                        </div>
                    </div>
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">结束日期</label>
                        <div class="col-sm-4">
                            <label class="form-control" data-name="endDate"></label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">
                            附件
                        </label>
                        <div class="col-sm-10">
                            <div id="_inventoryRightFile"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    $(function () {
        loadAssetOtherRightList();

        FileUtils.getFileShows({
            target: "checkOriginalFile",
            formData: {
                tableName: AssessDBKey.SurveyAssetInventory,
                fieldsName: "checkOriginal",
                tableId: '${surveyAssetInventory.id}'
            },
            deleteFlag: false
        })

        FileUtils.getFileShows({
            target: "specialCaseFile",
            formData: {
                tableName: AssessDBKey.SurveyAssetInventory,
                fieldsName: "specialCase",
                tableId: '${surveyAssetInventory.id}'
            },
            deleteFlag: false
        })
    })

    //显示附件通用
    function showFileCommon(tableId) {
        FileUtils.getFileShows({
            showMode: 'table',
            target: "credentialAccessory" + tableId,
            formData: {
                tableName: AssessDBKey.SurveyAssetInventoryContent,
                tableId: tableId
            },
            deleteFlag: false
        })
    }

    function saveform() {
        saveApprovalform("");
    }

    //加载他项权利附件
    function loadInventoryRightFile(tableId) {
        FileUtils.getFileShows({
            target: "inventoryRightFile",
            formData: {
                tableName: AssessDBKey.SurveyAssetInventoryRight,
                tableId: tableId
            },
            deleteFlag: true
        });
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
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top"  onclick="viewInventoryRightInfo(' + index + ')"><i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_inventory_right_list").bootstrapTable('destroy');
        TableInit("tb_inventory_right_list", "${pageContext.request.contextPath}/surveyAssetInventoryRight/getListByPlanDetailsId", cols, {
            planDetailsId: '${projectPlanDetails.id}'
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
        });
    }

    //查看他项信息
    function viewInventoryRightInfo(index) {
        var row = $("#tb_inventory_right_list").bootstrapTable('getData')[index];
        $("#viewInventoryRightModal").find('[data-name]').each(function () {
            $(this).text('').text(row[$(this).attr('data-name')]);
        })
        loadInventoryRightFile(row.id);
        $("#viewInventoryRightModal").find('[data-name=registerDate]').text(formatDate(row.registerDate, false));
        $("#viewInventoryRightModal").find('[data-name=beginDate]').text(formatDate(row.beginDate, false));
        $("#viewInventoryRightModal").find('[data-name=endDate]').text(formatDate(row.endDate, false));
        $("#viewInventoryRightModal").modal();

    };
</script>
</body>
</html>

