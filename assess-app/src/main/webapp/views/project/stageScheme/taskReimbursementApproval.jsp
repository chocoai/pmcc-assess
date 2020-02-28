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
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        ${projectPlanDetails.projectPhaseName}
                                        <small>${areaGroup.areaName}</small>
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form class="form-horizontal">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <table class="table">
                                                <thead>
                                                <tr>
                                                    <th class="hidden-xs">估价对象</th>
                                                    <th class="hidden-xs">已抵押担保的债权数额总价(元)</th>
                                                    <th class="hidden-xs">拖欠的建设工程价款总价(元)</th>
                                                    <th class="hidden-xs">其它法定优先受偿款总价(元)</th>
                                                    <th class="hidden-xs">估价师知悉的法定优先受偿款总价(元)</th>
                                                    <th class="hidden-xs">他权明细</th>
                                                </tr>
                                                </thead>
                                                <tbody id="tbody_data_section">

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-md-1 control-label">
                                                    附件
                                                </label>
                                                <div class="col-md-11">
                                                    <div id="_apply_file">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>

                    <%@include file="/views/share/form_approval.jsp" %>
                    <%@include file="/views/share/form_log.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>


</body>
<div id="divBoxRightItem" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">他权信息</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <table class="table table-bordered" id="RightItemList">
                                    <!-- cerare document add ajax data-->
                                </table>

                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>

        </div>
    </div>
</div>

<script type="application/javascript">
    $(function () {
        getItemHtml();
        FileUtils.getFileShows({
            target: "apply_file",
            formData: {
                tableName: AssessDBKey.SchemeReimbursement,
                tableId: "${master.id}",
                fieldsName: "apply"
            },
            deleteFlag: false
        })
    })

    function saveform() {
        saveApprovalform("");
    }

    function getItemHtml() {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeReimbursement/getSchemeReimbursementList",
            data: {
                masterId: "${master.id}"
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                $("#tbody_data_section").empty();
                if (result.ret) {
                    var html = "";
                    $.each(result.data, function (i, item) {
                        html += "<tr>";
                        html += "<td class='hidden-xs'>";
                        html += item.name;
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += Number(item.mortgagedTotalPrice).toFixed(2);
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += Number(item.owedTotalPrice).toFixed(2);
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += Number(item.otherTotalPrice).toFixed(2);
                        html += "</td>";

                        html += "<td class='hidden-xs'>";
                        html += Number(item.knowTotalPrice).toFixed(2);
                        html += "</td>";

                        html += "<td class='hidden-xs'>";
                        html += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="查看" onclick="checkRightItem('+item.inventoryRightRecordId+')"><i class="fa fa-search fa-white"></i></a>';
                        html += "</td>";
                        html += "</tr>";
                    });
                    $("#tbody_data_section").append(html);
                }
            }
        });
    }

    function checkRightItem(groupId) {
        if(!groupId){
            alert("未关联他权")
            return;
        }
        loadAssetRightList(groupId);
        $('#divBoxRightItem').modal("show");
    }

    //加载 他项权利列表
    function loadAssetRightList(groupId) {
        var cols = [];
        cols.push({field: 'categoryName', title: '类别'});
        cols.push({field: 'remark', title: '他项权利描述', width: '40%'});
        cols.push({field: 'number', title: '他权证编号'});
        cols.push({field: 'obligor', title: '义务人'});
        cols.push({field: 'obligee', title: '权利人'});
        cols.push({field: 'registerArea', title: '登记面积'});
        cols.push({field: 'rightRank', title: '他权级次'});
        $("#RightItemList").bootstrapTable('destroy');
        TableInit("RightItemList", "${pageContext.request.contextPath}/surveyAssetRightItem/getBootstrapTableVo", cols, {
            projectId: '${projectPlanDetails.projectId}', groupId: groupId
        }, {
            method: "get",
            showColumns: false,
            showRefresh: false,
            search: false,
            striped: true,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        });
    }
</script>
</body>
</html>

