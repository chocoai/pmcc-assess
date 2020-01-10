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
                                    项目名称
                                </label>
                                <div class="col-sm-2">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="名称" id="queryProjectName" name="queryProjectName"
                                           class="form-control">
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">
                                    报告类型
                                </label>
                                <div class="col-sm-2">
                                    <select name="queryReportType" id="queryReportType" class="form-control">
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

                            <div class="col-sm-3">
                                <button type="button" class="btn btn-primary"
                                        onclick="projectNumberRecord.prototype.loadDataDicList()">
                                    查询
                                </button>

                                <%--<button type="button" class="btn btn-success"--%>
                                        <%--onclick="projectNumberRecord.prototype.showModel()"--%>
                                        <%--data-toggle="modal" href="#divBox"> 新增--%>
                                <%--</button>--%>
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
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += "<a target='_blank' href='${pageContext.request.contextPath}/projectCenter/projectInfo?projectId=" + row.projectId + "' style='margin-left: 5px;' data-placement='top' data-original-title='查看详情' class='btn btn-xs btn-success tooltips' ><i class='fa fa-search fa-white'></i></a>";
                    str += '</div>';
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
</script>

</html>
