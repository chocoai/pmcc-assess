<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body>
<div class="wrapper">
    <%@include file="/views/share/main_navigation.jsp" %>
    <%@include file="/views/share/main_head.jsp" %>
    <div class="main-panel">
        <div class="content">
            <div class="panel-header bg-primary-gradient">
                <div class="page-inner py-5">
                </div>
            </div>
            <div class="page-inner mt--5">
                <div class="row mt--2">

                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">${baseViewDto.currentMenu.name}</div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frmQuery" class="form-horizontal">
                                    <div class="form-group form-inline">
                                        <label class="col-md-1 col-form-label">报告类型</label>
                                        <div class="col-md-2 p-0">
                                            <select name="queryReportType" id="queryReportType" class="form-control input-full">
                                                <option value="">--请选择--</option>
                                                <c:forEach var="item" items="${reportTypeList}">
                                                    <option value="${item.id}">${item.name}</option>
                                                </c:forEach>
                                            </select></div>
                                        <label class="col-md-1 col-form-label">文号</label>
                                        <div class="col-md-2 p-0">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="文号" id="queryNumberValue" name="queryNumberValue"
                                                   class="form-control input-full">
                                        </div>
                                        <label class="col-md-1 col-form-label">报告使用单位</label>
                                        <div class="col-md-2 p-0">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="报告使用单位" id="queryUnitName" name="queryUnitName"
                                                   class="form-control input-full">
                                        </div>
                                    </div>
                                    <div class="form-group form-inline">

                                        <label class="col-md-1 col-form-label">开始时间</label>
                                        <div class="col-md-2 p-0">
                                            <input id="queryStartDate" name="queryStartDate"
                                                   class="form-control input-full date-picker dbdate"
                                                   data-date-format="yyyy-mm-dd" placeholder="开始时间"/>
                                        </div>
                                        <label class="col-md-1 col-form-label">结束时间</label>
                                        <div class="col-md-2 p-0">
                                            <input id="queryEndDate" name="queryEndDate"
                                                   class="form-control input-full date-picker dbdate"
                                                   data-date-format="yyyy-mm-dd" placeholder="结束时间"/>
                                        </div>
                                        <button class="btn btn-info  btn-sm" type="button" style="margin-left: 10px"
                                                onclick="ReportTianFuBank.prototype.loadDataDicList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                        <button type="button" class="btn btn-success btn-sm" style="margin-left: 5px" onclick="$('#frmQuery').clearAll()">
                                            重置
                                        </button>
                                        <button type="button" class="btn btn-info btn-sm" style="margin-left: 5px"
                                                onclick="ReportTianFuBank.prototype.showModel()"
                                                data-toggle="modal" href="#divBox">
                                            <span class="btn-label">
												<i class="fa fa-cloud-upload-alt"></i>
											</span>
                                            上传附件
                                        </button>
                                        <button type="button" class="btn btn-success btn-sm" style="margin-left: 5px"
                                                onclick="ReportTianFuBank.prototype.exportData()">
                                            <span class="btn-label">
												<i class="fa fa-cloud-download-alt"></i>
											</span>
                                            导出
                                        </button>
                                    </div>

                                </form>
                                <table class="table table-bordered" id="tb_FatherList">
                                    <!-- cerare document add ajax data-->
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>


</body>

<script type="text/javascript">
    $(function () {
        ReportTianFuBank.prototype.loadDataDicList();
    });


    var ReportTianFuBank = function () {

    };
    ReportTianFuBank.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_FatherList";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            return data;
        },

        loadDataDicList: function () {
            var cols = [];
            cols.push({
                field: 'valuationDate', width: '5%',title: '评估时间', formatter: function (value, row, index) {
                    return formatDate(row.valuationDate, false);
                }
            });
            cols.push({field: 'unitName',width: '5%', title: '单位'});
            cols.push({field: 'consignor', width: '5%',title: '客户名称'});
            cols.push({field: 'assessTotal', width: '5%',title: '借款金额'});
            cols.push({field: 'projectCategoryName', width: '5%',title: '抵押物品种'});
            cols.push({field: 'assessOrganization',width: '5%', title: '评估机构'});
            cols.push({field: 'numberValue', width: '10%',title: '评估报告编号'});
            cols.push({field: 'assessCost', width: '5%',title: '评估费金额'});
            cols.push({field: 'customerManager', width: '5%',title: '经办客户经理'});
            //cols.push({field: 'principal', title: '机构负责人'});
            // cols.push({
            //     field: 'enterTieme', title: '入账日期', formatter: function (value, row, index) {
            //         return formatDate(row.enterTieme, false);
            //     }
            // });
            $("#" + ReportTianFuBank.prototype.config().table).bootstrapTable('destroy');
            TableInit(ReportTianFuBank.prototype.config().table, "${pageContext.request.contextPath}/customReportTianFuBank/getCustomReportTianFuBankList", cols, {
                numberValue: $("#queryNumberValue").val(),
                unitName: $("#queryUnitName").val(),
                reportType: $("#queryReportType").val(),
                queryStartDate: $("#queryStartDate").val(),
                queryEndDate: $("#queryEndDate").val()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        showModel: function () {
            $("#" + ReportTianFuBank.prototype.config().frm).clearAll();
            ReportTianFuBank.prototype.showFiles();
            ReportTianFuBank.prototype.uploadFiles();
            $('#' + ReportTianFuBank.prototype.config().box).modal("show");
        },
        //加载附件
        showFiles: function () {
            FileUtils.getFileShows({
                target: "customReportTianFuBank",
                formData: {
                    tableName: "customReportTianFuBank",
                    tableId: 0
                },
                editFlag: true,
                deleteFlag: true
            });
        },
        uploadFiles: function () {
            FileUtils.uploadFiles({
                target: "customReportTianFuBank",
                showFileList: false,
                onUpload: function (file) {//上传之前触发
                    var formData = {
                        tableName: "customReportTianFuBank",
                        tableId: 0
                    };
                    return formData;
                },
                editFlag: true,
                deleteFlag: true
            }, {
                onUploadComplete: function () {
                    ReportTianFuBank.prototype.showFiles();
                }
            });
        },
        showBtn: function () {
            $("#queryResultBtn").find('input').val('');
            $("#queryPreauditBtn").find('input').val('');
            var type = $("#queryReportType").find("option:selected").text();
            if (type == "结果报告") {
                $("#queryResultBtn").show();
                $("#queryPreauditBtn").hide();
            } else if (type == "预评报告") {
                $("#queryResultBtn").hide();
                $("#queryPreauditBtn").show();
            } else {
                $("#queryResultBtn").hide();
                $("#queryPreauditBtn").hide();
            }
        },
        exportData: function () {
            var numberValue = $("#queryNumberValue").val();
            var unitName = $("#queryUnitName").val();
            var reportType = $("#queryReportType").val();
            var queryStartDate = $("#queryStartDate").val();
            var queryEndDate = $("#queryEndDate").val();
            var href = "${pageContext.request.contextPath}/customReportTianFuBank/export";
            href += "?numberValue=" + numberValue;
            href += "&unitName=" + unitName;
            href += "&reportType=" + reportType;
            href += "&queryStartDate=" + queryStartDate;
            href += "&queryEndDate=" + queryEndDate;
            window.open(href, "");
        }

    }


</script>
<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">附件</h3>
            </div>
            <form id="frmFather" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-xs-1">
                                            附件
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input id="customReportTianFuBank"
                                                   name="customReportTianFuBank"
                                                   placeholder="上传附件" class="form-control"
                                                   type="file">
                                            <div id="_customReportTianFuBank"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        关闭
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>
