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
                                        <label class="col-md-1 col-form-label">项目名称</label>
                                        <div class="col-md-2 p-0">
                                            <input id="queryProjectName" name="queryProjectName"
                                                   class="form-control input-full" placeholder="项目名称"/>
                                        </div>
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
                                        <label class="col-md-1 col-form-label">开始时间</label>
                                        <div class="col-md-2 p-0">
                                            <input id="queryStartDate" name="queryStartDate"
                                                   class="form-control input-full date-picker dbdate"
                                                   data-date-format="yyyy-mm-dd" placeholder="开始时间"/>
                                        </div>
                                    </div>
                                    <div class="form-group form-inline">
                                        <label class="col-md-1 col-form-label">结束时间</label>
                                        <div class="col-md-2 p-0">
                                            <input id="queryEndDate" name="queryEndDate"
                                                   class="form-control input-full date-picker dbdate"
                                                   data-date-format="yyyy-mm-dd" placeholder="结束时间"/>
                                        </div>
                                        <button class="btn btn-info  btn-sm" type="button"style="margin-left: 10px"
                                                onclick="ReportAppraiserAssociation.prototype.loadDataDicList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                        <button type="button" class="btn btn-success btn-sm" style="margin-left: 5px"
                                                onclick="$('#frmQuery').clearAll()">
                                            重置
                                        </button>
                                        <button type="button" class="btn btn-info btn-sm" style="margin-left: 5px"
                                                onclick="ReportAppraiserAssociation.prototype.showModel()"
                                                data-toggle="modal" href="#divBox">
                                            <span class="btn-label">
												<i class="fa fa-cloud-upload-alt"></i>
											</span>
                                            上传附件
                                        </button>
                                        <button type="button" class="btn btn-success btn-sm" style="margin-left: 5px"
                                                onclick="ReportAppraiserAssociation.prototype.export()">
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
        ReportAppraiserAssociation.prototype.loadDataDicList();
    });


    var ReportAppraiserAssociation = function () {

    };
    ReportAppraiserAssociation.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_FatherList";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            return data;
        },

        loadDataDicList: function () {
            var cols = [];
            //报表显示数据字段，项目名称、文号、报告使用单位、总价、单价、价值时点、报告日
            cols.push({field: 'projectName', width: '5%', title: '项目名称'});
            cols.push({field: 'numberValue', width: '5%', title: '文号'});
            cols.push({field: 'serviceComeFromName', width: '3%', title: '业务来源'});
            //cols.push({field: 'entrustPurposeName', width: '3%', title: '估价目的'});
            cols.push({field: 'methodNames', width: '5%', title: '评估方法'});
            cols.push({field: 'unitName', width: '5%', title: '使用单位'});
            // cols.push({
            //     field: 'investigationsStartDate',
            //     width: '5%',
            //     title: '估价作业开始日',
            //     formatter: function (value, row, index) {
            //         return formatDate(row.investigationsStartDate, false);
            //     }
            // });
            // cols.push({
            //     field: 'homeWorkEndTime', width: '5%', title: '估价作业结束日', formatter: function (value, row, index) {
            //         return formatDate(row.homeWorkEndTime, false);
            //     }
            // });
            cols.push({
                field: 'valuationDate', width: '5%', title: '估价时点', formatter: function (value, row, index) {
                    return formatDate(row.valuationDate, false);
                }
            });
            //cols.push({field: 'landArea', width: '5%', title: '土地面积'});
            //cols.push({field: 'evaluationArea', width: '5%', title: '建筑面积'});
            cols.push({field: 'assessTotal', width: '5%', title: '评估总值'});
            cols.push({field: 'price', width: '5%', title: '评估单价'});

            //cols.push({field: 'seat', width: '5%', title: '估价对象位置'});
            cols.push({field: 'consignor', width: '5%', title: '委托人'});
            //cols.push({field: 'csAddress', width: '5%', title: '委托人地址'});
            //cols.push({field: 'csPostcode', width: '5%', title: '委托人邮编'});
            //cols.push({field: 'csPhnoe', width: '5%', title: '委托人电话'});
            //cols.push({field: 'firstAppraiser', width: '5%', title: '第一报告人'});
            //cols.push({field: 'firstRegistrationNumber', width: '5%', title: '第一报告人注册号'});
            //cols.push({field: 'participationAppraiser', width: '5%', title: '参与报告人'});
            //cols.push({field: 'registrationNumber', width: '5%', title: '注册号'});
            //cols.push({field: 'contractPrice', width: '5%', title: '收费'});
            cols.push({
                field: 'gmtCreated', width: '5%', title: '报告生成时间', formatter: function (value, row, index) {
                    return formatDate(row.gmtCreated, false);
                }
            });
            $("#" + ReportAppraiserAssociation.prototype.config().table).bootstrapTable('destroy');
            TableInit(ReportAppraiserAssociation.prototype.config().table, "${pageContext.request.contextPath}/customReportAppraiserAssociation/getCustomReportAppraiserAssociationList", cols, {
                projectName: $("#queryProjectName").val(),
                numberValue: $("#queryNumberValue").val(),
                unitName: $("#queryUnitName").val(),
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
        showModel: function () {
            $("#" + ReportAppraiserAssociation.prototype.config().frm).clearAll();
            ReportAppraiserAssociation.prototype.showFiles();
            ReportAppraiserAssociation.prototype.uploadFiles();
            $('#' + ReportAppraiserAssociation.prototype.config().box).modal("show");
        },
        //加载附件
        showFiles: function () {
            FileUtils.getFileShows({
                target: "customReportAppraiserAssociation",
                formData: {
                    tableName: "customReportAppraiserAssociation",
                    tableId: 0
                },
                editFlag: true,
                deleteFlag: true
            });
        },
        uploadFiles: function () {
            FileUtils.uploadFiles({
                target: "customReportAppraiserAssociation",
                showFileList: false,
                onUpload: function (file) {//上传之前触发
                    var formData = {
                        tableName: "customReportAppraiserAssociation",
                        tableId: 0
                    };
                    return formData;
                },
                editFlag: true,
                deleteFlag: true
            }, {
                onUploadComplete: function () {
                    ReportAppraiserAssociation.prototype.showFiles();
                }
            });
        },
        export: function () {
            var projectName = $("#queryProjectName").val();
            var numberValue = $("#queryNumberValue").val();
            var unitName = $("#queryUnitName").val();
            var queryStartDate = $("#queryStartDate").val();
            var queryEndDate = $("#queryEndDate").val();
            var href = "${pageContext.request.contextPath}/customReportAppraiserAssociation/export";
            href += "?projectName=" + projectName;
            href += "&numberValue=" + numberValue;
            href += "&unitName=" + unitName;
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
                                            <input id="customReportAppraiserAssociation"
                                                   name="customReportAppraiserAssociation"
                                                   placeholder="上传附件" class="form-control input-full"
                                                   type="file">
                                            <div id="_customReportAppraiserAssociation"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" data-dismiss="modal" class="btn btn-default">
                                    关闭
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

</html>
