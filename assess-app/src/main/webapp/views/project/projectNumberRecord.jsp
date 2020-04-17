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
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="名称" id="queryProjectName" name="queryProjectName"
                                                   class="form-control input-full input-full">
                                        </div>
                                        <label class="col-md-1 col-form-label">报告类型</label>
                                        <div class="col-md-2 p-0">
                                            <select name="queryReportType" id="queryReportType" class="form-control input-full">
                                                <option value="">--请选择--</option>
                                                <c:forEach var="item" items="${reportTypeList}">
                                                    <option value="${item.id}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <label class="col-md-1 col-form-label">文号</label>
                                        <div class="col-md-2 p-0">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="文号" id="queryNumberValue" name="queryNumberValue"
                                                   class="form-control input-full">
                                        </div>

                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="projectNumberRecord.prototype.loadDataDicList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
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
        projectNumberRecord.prototype.loadDataDicList();
    });


    var projectNumberRecord = function () {

    };
    projectNumberRecord.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_FatherList";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            return data;
        },
        loadDataDicList: function () {
            var cols = [];
            cols.push({field: 'projectName', title: '项目名称'});
            cols.push({field: 'reportTypeName', title: '报告类型'});
            cols.push({field: 'year', title: '年份'});
            cols.push({field: 'number', title: '编号'});
            cols.push({field: 'numberValue', title: '文号'});
            cols.push({field: 'bisDelete', title: '状态', formatter: function (value, row, index) {
                if(value==false){
                    return "有效";
                }
                return "无效";
            }});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<button onclick="checkDetail(' + row.projectId + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看详情">';
                    str += '<i class="fa fa-search"></i>';
                    str += '</button>';
                    return str;
                }
            });
            $("#" + projectNumberRecord.prototype.config().table).bootstrapTable('destroy');
            TableInit(projectNumberRecord.prototype.config().table, "${pageContext.request.contextPath}/projectNumberRecord/getProjectNumberRecordList", cols, {
                projectName: $("#queryProjectName").val(),
                reportType: $("#queryReportType").val(),
                numberValue: $("#queryNumberValue").val()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },

    }

    function checkDetail(id) {
        var href = "${pageContext.request.contextPath}/projectCenter/projectInfo";
        href += "?projectId=" + id;
        window.open(href, "");
    }
</script>

</html>
