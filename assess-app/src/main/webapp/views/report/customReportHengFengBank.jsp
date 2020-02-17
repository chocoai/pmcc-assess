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
                                            <select name="queryReportType" id="queryReportType" class="form-control input-full"
                                                    onchange="ReportHengFengBank.prototype.showBtn()">
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
                                    <div class="form-group form-inline" style="display: none" id="queryPreauditBtn">

                                        <label class="col-md-1 col-form-label">开始时间</label>
                                        <div class="col-md-2 p-0">
                                            <input id="queryPreviewsStartDate" name="queryPreviewsStartDate"
                                                   class="form-control input-full date-picker dbdate"
                                                   data-date-format="yyyy-mm-dd" placeholder="开始时间"/>
                                        </div>
                                        <label class="col-md-1 col-form-label">结束时间</label>
                                        <div class="col-md-2 p-0">
                                            <input id="queryPreviewsEndDate" name="queryPreviewsEndDate"
                                                   class="form-control input-full date-picker dbdate"
                                                   data-date-format="yyyy-mm-dd" placeholder="结束时间"/>
                                        </div>
                                    </div>
                                    <div class="form-group form-inline" style="display: none" id="queryResultBtn">

                                        <label class="col-md-1 col-form-label">开始时间</label>
                                        <div class="col-md-2 p-0">
                                            <input id="queryResultStartDate" name="queryResultStartDate"
                                                   class="form-control input-full date-picker dbdate"
                                                   data-date-format="yyyy-mm-dd" placeholder="开始时间"/>
                                        </div>
                                        <label class="col-md-1 col-form-label">结束时间</label>
                                        <div class="col-md-2 p-0">
                                            <input id="queryResultEndDate" name="queryResultEndDate"
                                                   class="form-control input-full date-picker dbdate"
                                                   data-date-format="yyyy-mm-dd" placeholder="结束时间"/>
                                        </div>
                                    </div>
                                    <div class="form-group form-inline">
                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="ReportHengFengBank.prototype.loadDataDicList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                        <button type="button" class="btn btn-success btn-sm" onclick="$('#frmQuery').clearAll()">
                                            重置
                                        </button>
                                        <button type="button" class="btn btn-info btn-sm"
                                                onclick="ReportHengFengBank.prototype.showModel()"
                                                data-toggle="modal" href="#divBox">
                                            <span class="btn-label">
												<i class="fa fa-cloud-upload-alt"></i>
											</span>
                                            上传附件
                                        </button>
                                        <button type="button" class="btn btn-success btn-sm"
                                                onclick="ReportHengFengBank.prototype.exportData()">
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
        ReportHengFengBank.prototype.loadDataDicList();
    });


    var ReportHengFengBank = function () {

    };
    ReportHengFengBank.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_FatherList";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            return data;
        },

        loadDataDicList: function () {
            var cols = [];
            cols.push({field: 'month', width: '5%',title: '月份'});
            cols.push({field: 'consignor', width: '5%',title: '委托人'});
            cols.push({field: 'unitName',width: '5%', title: '报告使用单位'});
            cols.push({field: 'numberValue',width: '10%', title: '报告编号'});
            cols.push({field: 'projectName', width: '10%',title: '项目名称'});
            cols.push({field: 'workDate',width: '5%', title: '工作日期'});
            cols.push({field: 'projectCategoryName', width: '5%',title: '抵押物性质'});
            cols.push({field: 'seat', width: '10%',title: '抵押物地址'});
            cols.push({field: 'area', width: '5%',title: '面积'});
            cols.push({field: 'assessTotal',width: '5%', title: '评估金额'});
            cols.push({field: 'methodNames',width: '5%', title: '评估方法'});
            cols.push({field: 'appraiser',width: '5%', title: '签字估价师'});
            cols.push({field: 'loan', width: '5%',title: '贷款金额'});
            cols.push({field: 'pledgeRatio',width: '5%', title: '抵押率'});
            cols.push({field: 'chargePrice', width: '5%',title: '收费金额'});
            cols.push({field: 'assessCompany', width: '5%',title: '评估公司'});
            cols.push({field: 'remark',width: '5%', title: '备注'});
            $("#" + ReportHengFengBank.prototype.config().table).bootstrapTable('destroy');
            TableInit(ReportHengFengBank.prototype.config().table, "${pageContext.request.contextPath}/customReportHengFengBank/getCustomReportHengFengBankList", cols, {
                numberValue: $("#queryNumberValue").val(),
                unitName: $("#queryUnitName").val(),
                reportType: $("#queryReportType").val(),
                queryPreviewsStartDate: $("#queryPreviewsStartDate").val(),
                queryPreviewsEndDate: $("#queryPreviewsEndDate").val(),
                queryResultStartDate: $("#queryResultStartDate").val(),
                queryResultEndDate: $("#queryResultEndDate").val()
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
            $("#" + ReportHengFengBank.prototype.config().frm).clearAll();
            ReportHengFengBank.prototype.showFiles();
            ReportHengFengBank.prototype.uploadFiles();
            $('#' + ReportHengFengBank.prototype.config().box).modal("show");
        },
        //加载附件
        showFiles: function () {
            FileUtils.getFileShows({
                target: "customReportHengFengBank",
                formData: {
                    tableName: "customReportHengFengBank",
                    tableId: 0
                },
                editFlag: true,
                deleteFlag: true
            });
        },
        uploadFiles: function () {
            FileUtils.uploadFiles({
                target: "customReportHengFengBank",
                showFileList: false,
                onUpload: function (file) {//上传之前触发
                    var formData = {
                        tableName: "customReportHengFengBank",
                        tableId: 0
                    };
                    return formData;
                },
                editFlag: true,
                deleteFlag: true
            }, {
                onUploadComplete: function () {
                    ReportHengFengBank.prototype.showFiles();
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
            var queryPreviewsStartDate = $("#queryPreviewsStartDate").val();
            var queryPreviewsEndDate = $("#queryPreviewsEndDate").val();
            var queryResultStartDate = $("#queryResultStartDate").val();
            var queryResultEndDate = $("#queryResultEndDate").val();
            var href = "${pageContext.request.contextPath}/customReportHengFengBank/export";
            href += "?numberValue=" + numberValue;
            href += "&unitName=" + unitName;
            href += "&reportType=" + reportType;
            href += "&queryPreviewsStartDate=" + queryPreviewsStartDate;
            href += "&queryPreviewsEndDate=" + queryPreviewsEndDate;
            href += "&queryResultStartDate=" + queryResultStartDate;
            href += "&queryResultEndDate=" + queryResultEndDate;
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
                                            <input id="customReportHengFengBank"
                                                   name="customReportHengFengBank"
                                                   placeholder="上传附件" class="form-control"
                                                   type="file">
                                            <div id="_customReportHengFengBank"></div>
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
