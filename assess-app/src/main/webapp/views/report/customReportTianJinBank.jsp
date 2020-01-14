<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                        ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frmQuery" class="form-horizontal">
                        <div class="form-group ">
                            <div>
                                <label class="col-sm-1 control-label">
                                    报告类型
                                </label>
                                <div class="col-sm-2">
                                    <select name="queryReportType" id="queryReportType" class="form-control"
                                            onchange="ReportTianJinBank.prototype.showBtn()">
                                        <option value="">--请选择--</option>
                                        <c:forEach var="item" items="${reportTypeList}">
                                            <option value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">
                                    文号
                                </label>
                                <div class="col-sm-2">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="文号" id="queryNumberValue" name="queryNumberValue"
                                           class="form-control">
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">
                                    报告使用单位
                                </label>
                                <div class="col-sm-2">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="报告使用单位" id="queryUnitName" name="queryUnitName"
                                           class="form-control">
                                </div>
                            </div>
                        </div>
                            <div class="form-group ">
                                <div id="queryPreauditBtn" style="display: none">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            开始时间
                                        </label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <input id="queryPreviewsStartDate" name="queryPreviewsStartDate"
                                                   class="form-control date-picker dbdate"
                                                   data-date-format="yyyy-mm-dd" placeholder="开始时间"/>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            结束时间
                                        </label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <input id="queryPreviewsEndDate" name="queryPreviewsEndDate"
                                                   class="form-control date-picker dbdate"
                                                   data-date-format="yyyy-mm-dd" placeholder="结束时间"/>
                                        </div>
                                    </div>
                                </div>
                                <div id="queryResultBtn" style="display: none">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            开始时间
                                        </label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <input id="queryResultStartDate" name="queryResultStartDate"
                                                   class="form-control date-picker dbdate"
                                                   data-date-format="yyyy-mm-dd" placeholder="开始时间"/>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            结束时间
                                        </label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <input id="queryResultEndDate" name="queryResultEndDate"
                                                   class="form-control date-picker dbdate"
                                                   data-date-format="yyyy-mm-dd" placeholder="结束时间"/>
                                        </div>
                                    </div>
                                </div>
                            <div class="col-sm-3">
                                <button type="button" class="btn btn-success" onclick="$('#frmQuery').clearAll()">
                                    重置
                                </button>
                                <button type="button" class="btn btn-primary"
                                        onclick="ReportTianJinBank.prototype.loadDataDicList()">
                                    查询
                                </button>
                                <button type="button" class="btn btn-success"
                                        onclick="ReportTianJinBank.prototype.showModel()"
                                        data-toggle="modal" href="#divBox">
                                    上传附件
                                </button>
                                <button type="button" class="btn btn-info"
                                        onclick="ReportTianJinBank.prototype.exportData()">
                                    导出
                                </button>
                            </div>
                        </div>

                    </form>
                    <table class="table table-bordered" id="tb_FatherList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<%@include file="/views/data/dataPropertyServiceItem.jsp" %>
<%@include file="/views/data/dataPropertyModelQuote.jsp" %>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    $(function () {
        ReportTianJinBank.prototype.loadDataDicList();
    });


    var ReportTianJinBank = function () {

    };
    ReportTianJinBank.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_FatherList";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            return data;
        },

        loadDataDicList: function () {
            var cols = [];
            cols.push({field: 'pledger', title: '抵押人'});
            cols.push({field: 'loanTypeName', title: '抵押物'});
            cols.push({field: 'seat', title: '位置'});
            cols.push({
                field: 'valuationDate', title: '价值时点', formatter: function (value, row, index) {
                    return formatDate(row.valuationDate, false);
                }
            });cols.push({
                field: 'reportIssuanceDate', title: '报告日', formatter: function (value, row, index) {
                    return formatDate(row.reportIssuanceDate, false);
                }
            });

            cols.push({field: 'area', title: '面积'});
            cols.push({field: 'assessTotal', title: '评估总价'});
            cols.push({field: 'previewsNumber', title: '预估文号'});
            cols.push({field: 'resultNumber', title: '正式报告文号'});
            cols.push({field: 'assessCost', title: '评估费用'});
            cols.push({field: 'standardCost', title: '标准收费'});
            cols.push({field: 'discount', title: '折扣'});
            cols.push({field: 'remark', title: '备注'});
            $("#" + ReportTianJinBank.prototype.config().table).bootstrapTable('destroy');
            TableInit(ReportTianJinBank.prototype.config().table, "${pageContext.request.contextPath}/customReportTianJinBank/getCustomReportTianJinBankList", cols, {
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
            $("#" + ReportTianJinBank.prototype.config().frm).clearAll();
            ReportTianJinBank.prototype.showFiles();
            ReportTianJinBank.prototype.uploadFiles();
            $('#' + ReportTianJinBank.prototype.config().box).modal("show");
        },
        //加载附件
        showFiles: function () {
            FileUtils.getFileShows({
                target: "customReportTianJinBank",
                formData: {
                    tableName: "customReportTianJinBank",
                    tableId: 0
                },
                editFlag: true,
                deleteFlag: true
            });
        },
        uploadFiles: function () {
            FileUtils.uploadFiles({
                target: "customReportTianJinBank",
                showFileList: false,
                onUpload: function (file) {//上传之前触发
                    var formData = {
                        tableName: "customReportTianJinBank",
                        tableId: 0
                    };
                    return formData;
                },
                editFlag: true,
                deleteFlag: true
            }, {
                onUploadComplete: function () {
                    ReportTianJinBank.prototype.showFiles();
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
            var href = "${pageContext.request.contextPath}/customReportTianJinBank/export";
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
                                            <input id="customReportTianJinBank"
                                                   name="customReportTianJinBank"
                                                   placeholder="上传附件" class="form-control"
                                                   type="file">
                                            <div id="_customReportTianJinBank"></div>
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
