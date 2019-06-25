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
            <div class="row">
                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                    <div class="x_panel">
                        <div class="x_title collapse-link">
                            <h2>
                                项目列表
                            </h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <form id="frmQuery" class="form-horizontal">
                                <div class="form-group ">
                                    <div>
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            项目名称
                                        </label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="项目名称" id="queryName" name="queryName"
                                                   class="form-control">
                                        </div>
                                    </div>
                                    <div>
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            状态
                                        </label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <select id="status" class="form-control">
                                                <option value="">--请选择--</option>
                                                <c:forEach var="item" items="${statusEnumList}">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div>
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            项目成员
                                        </label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <input type="hidden" id="queryMember">
                                            <input type="text" data-rule-maxlength="50" readonly onclick="selectProjectMember()"
                                                   placeholder="项目成员" id="queryMemberName" name="queryMemberName"
                                                   class="form-control">
                                        </div>
                                    </div>
                                    <div>
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            项目经理
                                        </label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <input type="hidden" id="queryManager">
                                            <input type="text" data-rule-maxlength="50" readonly onclick="selectProjectManager()"
                                                   placeholder="项目经理" id="queryManagerName" name="queryManagerName"
                                                   class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group ">
                                    <div>
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            立项人
                                        </label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <input type="hidden" id="queryCreator">
                                            <input type="text" data-rule-maxlength="50" readonly onclick="selectProjectCreator()"
                                                   placeholder="立项人" id="queryCreatorName" name="queryCreatorName"
                                                   class="form-control">
                                        </div>
                                    </div>
                                    <div>
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            委托目的
                                        </label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <select id="entrustPurpose" class="form-control">
                                                <option value="">--请选择--</option>
                                                <c:forEach var="item" items="${entrustPurposeList}">
                                                    <option value="${item.id}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div>
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            开始时间
                                        </label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <input type="text" class="form-control date-picker dbdate"
                                                   data-date-format="yyyy-mm-dd" id="queryTimeStart" placeholder="开始时间"/>
                                        </div>
                                    </div>
                                    <div>
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            结束时间
                                        </label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <input type="text" class="form-control date-picker dbdate"
                                                   data-date-format="yyyy-mm-dd" id="queryTimeEnd" placeholder="结束时间"/>
                                        </div>
                                    </div>

                                </div>
                                <div class="form-group ">
                                    <div>
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            委托人
                                        </label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <input type="text" data-rule-maxlength="50" placeholder="委托人"
                                                   id="queryConsignor" class="form-control">
                                        </div>
                                    </div>
                                    <div>
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            报告使用单位
                                        </label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <input type="hidden" id="queryUseUnit">
                                            <input type="text" readonly="readonly" required="required"
                                                   placeholder="单位" class="form-control" id="queryUseUnitName"
                                                   onclick="selectCustomer(this)">
                                        </div>
                                    </div>
                                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                        <button type="button" class="btn btn-primary"
                                                onclick="loadProjectList()">
                                            查询
                                        </button>
                                        <button type="button" class="btn btn-success" onclick="queryReset()">
                                            重置
                                        </button>
                                    </div>
                                </div>

                            </form>
                            <table id="tb_projectList" class="table table-bordered">
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="/pmcc-crm/js/crm-customer-utils.js"></script>
<script type="application/javascript">
    $(function () {
        loadProjectList();

    })

    function loadProjectList() {
        var cols = [];
        cols.push({field: 'projectName', title: '项目名称'});
        cols.push({
            field: 'serviceEnd', width: '20%', title: '项目成员', formatter: function (value, row, index) {
                var s = "";
                if (row.userAccountManagerName) {
                    s += "<label style='padding: 5px;' class='label label-info'>" + row.userAccountManagerName.split("_")[0] + "</label>"
                }
                if (row.userAccountMemberName) {
                    s += " " + row.userAccountMemberName.split("_")[0];
                }
                return s;
            }
        });
        cols.push({
            field: 'projectClassName', title: '项目类型', formatter: function (value, row, index) {
                var s = "";
                if (row.projectClassName) {
                    s += row.projectClassName;
                }
                if (row.projectClassName) {
                    s += "/" + row.projectTypeName;
                }
                if (row.projectClassName) {
                    s += "/" + row.projectCategoryName;
                }
                return s;
            }
        });
        cols.push({field: 'projectStatus', title: '项目状态'});
        cols.push({field: 'entrustPurposeName', title: '委托目的'});
        cols.push({
            field: 'finishPre', width: '20%', title: '项目进度', formatter: function (value, row, index) {
                var s = "<div class='progress progress_sm' style='margin-bottom: 0px;'>";
                if (value == "100") {
                    s += "<div class='progress-bar progress-bar-success' role='progressbar'  style='width: " + value + "%;'></div>";
                }
                else {
                    s += "<div class='progress-bar progress-bar-warning' role='progressbar'  style='width: " + value + "%;'></div>";
                }
                s += "</div>";
                s += "<small>完成" + value + "%</small>";
                return s;
            }
        });
        cols.push({
            field: 'gmtCreated', title: '立项时间', formatter: function (value, row, index) {
                return formatDate(row.gmtCreated, false);
            }
        });
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = "";
                if (row.projectStatus) {
                    console.log(row.projectStatus) ;
                    if (row.projectStatus == '草稿') {
                        str = "<a target='_blank' href='${pageContext.request.contextPath}/projectInfo/projectInfoEdit?projectId=" + row.id + "' style='margin-left: 5px;' data-placement='top' data-original-title='重新申请' class='btn btn-xs btn-success tooltips' ><i class='fa fa-flag '></i></a>";
                    } else {
                        str = "<a target='_blank' href='${pageContext.request.contextPath}/projectInfo/projectDetails?projectId=" + row.id + "' style='margin-left: 5px;' data-placement='top' data-original-title='查看详情' class='btn btn-xs btn-success tooltips' ><i class='fa fa-search fa-white'></i></a>";
                    }
                }
                return str;
            }
        });
        $("#tb_projectList").bootstrapTable('destroy');
        TableInit("tb_projectList", "${pageContext.request.contextPath}/projectCenter/getProjectList", cols, {
            queryName: $("#queryName").val(),
            projectStatus: $("#status").val(),
            queryCreator:$("#queryCreator").val(),
            queryMember:$("#queryMember").val(),
            queryManager:$("#queryManager").val(),
            entrustPurpose:$("#entrustPurpose").val(),
            queryTimeStart:$("#queryTimeStart").val(),
            queryTimeEnd:$("#queryTimeEnd").val(),
            queryConsignor:$("#queryConsignor").val(),
            queryUseUnit:$("#queryUseUnit").val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    //项目成员选择
    function selectProjectMember() {
        erpEmployee.select({
            onSelected: function (data) {
                $("#queryMember").val(data.account);
                $("#queryMemberName").val(data.name);
            }
        });
    }
    //项目经理选择
    function selectProjectManager() {
        erpEmployee.select({
            onSelected: function (data) {
                $("#queryManager").val(data.account);
                $("#queryManagerName").val(data.name);
            }
        });
    }
    //立项人选择
    function selectProjectCreator() {
        erpEmployee.select({
            onSelected: function (data) {
                $("#queryCreator").val(data.account);
                $("#queryCreatorName").val(data.name);
            }
        });
    }

    function selectCustomer(this_) {
        //选择客户
        crmCustomer.select({
            multi: false,//是否允许多选
            companyId:"${companyId}",
            onSelected: function (nodes) {
                $("#queryUseUnit").val(nodes[0].id);
                $("#queryUseUnitName").val(nodes[0].name);
                $.ajax({
                    type: "get",
                    url: "${pageContext.request.contextPath}/initiateCrmCustomer/getCrmCustomerDto",
                    data: "crmId=" + nodes[0].id,
                    success: function (msg) {

                    }
                });
            }
        });
    };

    //重置查询条件
    function queryReset() {
        $("#frmQuery").find("input").val("");
        $("#frmQuery").find("select").val("");
    }
</script>
</body>
</html>
