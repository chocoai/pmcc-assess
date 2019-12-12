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
                                我的参与
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
                                                   placeholder="项目名称"  name="projectName" class="form-control">
                                        </div>
                                    </div>
                                    <div>
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            状态
                                        </label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <select name="projectStatus" class="form-control">
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
                                            <input type="hidden" name="queryMember" data-title="account">
                                            <input type="text" data-rule-maxlength="50" readonly="readonly" onclick="selectCreate(this)"
                                                   placeholder="项目成员" name="queryMemberName"
                                                   class="form-control" data-title="name">
                                        </div>
                                    </div>
                                    <div>
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            项目经理
                                        </label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <input type="hidden" name="queryManager" data-title="account">
                                            <input type="text"  readonly="readonly" onclick="selectCreate(this)"
                                                   placeholder="项目经理"  name="queryManagerName" class="form-control" data-title="name">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group ">
                                    <div>
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            立项人
                                        </label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <input type="hidden" name="queryCreator" data-title="account">
                                            <input type="text"  readonly="readonly" onclick="selectCreate(this)"
                                                   placeholder="立项人"  name="queryCreatorName"
                                                   class="form-control" data-title="name">
                                        </div>
                                    </div>
                                    <div>
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            委托目的
                                        </label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <select name="entrustPurpose" class="form-control">
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
                                                   data-date-format="yyyy-mm-dd" name="queryTimeStart" placeholder="开始时间"/>
                                        </div>
                                    </div>
                                    <div>
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            结束时间
                                        </label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <input type="text" class="form-control date-picker dbdate"
                                                   data-date-format="yyyy-mm-dd" name="queryTimeEnd" placeholder="结束时间"/>
                                        </div>
                                    </div>

                                </div>
                                <div class="form-group ">
                                    <div>
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            报告使用单位
                                        </label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <input type="hidden" name="queryUseUnit">
                                            <input type="text" readonly="readonly" required="required"
                                                   placeholder="单位" class="form-control" name="queryUseUnitName"
                                                   onclick="selectCustomer(this)">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            评估部门
                                        </label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <input  name="queryDepartmentId" class="form-control" type="hidden" />
                                            <input  name="queryDepartmentName" class="form-control" placeholder="评估部门"
                                                   onclick="selectDepartment(this)" readonly="readonly"/>
                                        </div>
                                    </div>
                                    <div>
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            委托人
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <div class="input-group">
                                                <span class="input-group-addon">
                                                    <input type="radio"  name="queryConsignorType" value="0">自然人
                                                    <input type="radio" checked="checked" name="queryConsignorType" value="1">法人
                                                </span>
                                                <input type="text"  placeholder="委托人" class="form-control" name="queryConsignor">
                                            </div>
                                        </div>
                                    </div>
                                    <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                        <button type="button" class="btn btn-primary"
                                                onclick="loadParticipationList()">
                                            查询
                                        </button>
                                        <button type="button" class="btn btn-success" onclick="$('#frmQuery').clearAll()">
                                            重置
                                        </button>
                                    </div>
                                </div>

                            </form>
                            <table id="tb_myParticipation" class="table table-bordered">
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
<script type="text/javascript" src="/pmcc-crm/js/crm-customer-utils.js?v=1.0"></script>
<script type="application/javascript">
    $(function () {
        loadParticipationList();

    });
    function loadParticipationList() {
        var selectObj = formSerializeArray($("#frmQuery")) ;
        console.log(selectObj) ;
        var cols = [];
        var cols = [];
        cols.push({field: 'projectName', title: '项目名称',width:'30%'});
        cols.push({field: 'useUnitName', title: '使用报告单位',width:'10%'});
        cols.push({field: 'departmentName', title: '评估部门',width:'5%'});
        cols.push({
            field: 'serviceEnd', title: '项目成员', width: '10%', formatter: function (value, row, index) {
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
            field: 'projectClassName', title: '项目类型',width:'5%', formatter: function (value, row, index) {
                return row.projectCategoryName;
            }
        });
        cols.push({field: 'projectStatus', title: '项目状态',width:'5%'});
        cols.push({field: 'entrustPurposeName', title: '委托目的',width:'5%'});
        cols.push({field: 'loanTypeName', title: '贷款类型',width:'5%'});
        cols.push({
            field: 'finishPre', title: '项目进度',width:'10%', formatter: function (value, row, index) {
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
            field: 'gmtCreated', title: '立项时间',width:'10%', formatter: function (value, row, index) {
                return formatDate(row.gmtCreated, true);
            }
        });
        cols.push({
            field: 'opation', title: '操作', formatter: function (value, row, index) {
                var str = "<a target='_blank' href='${pageContext.request.contextPath}/projectCenter/projectInfo?projectId=" + row.id + "' style='margin-left: 5px;' data-placement='top' data-original-title='查看详情' class='btn btn-xs btn-success tooltips' ><i class='fa fa-search fa-white'></i></a>";
                return str;
            }
        });
        $("#tb_myParticipation").bootstrapTable('destroy');
        TableInit("tb_myParticipation", "${pageContext.request.contextPath}/projectCenter/getParticipationProject", cols, selectObj, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }
    
    function selectCreate(_this) {
        var div = $(_this).closest("div") ;
        erpEmployee.select({
            currOrgId: '${companyId}',
            onSelected: function (data) {
                div.find("input[data-title='name']") .val(data.name) ;
                div.find("input[data-title='account']") .val(data.account) ;
            }
        });
    }


    function selectCustomer(this_) {
        var div = $(this_).closest("div") ;
        //选择客户
        crmCustomer.select({
            multi: false,//是否允许多选
            companyId:"${companyId}",
            onSelected: function (nodes) {
                div.find("[name='queryUseUnit']") .val(nodes[0].id) ;
                div.find("[name='queryUseUnitName']") .val(nodes[0].name) ;
            }
        });
    }
    //部门
    function selectDepartment(this_) {
        var div = $(this_).closest("div") ;
        var options = {
            onSelected: function (nodes) {
                div.find("[name='queryDepartmentId']") .val(nodes[0].id) ;
                div.find("[name='queryDepartmentName']") .val(nodes[0].text) ;
            }
        };
        erpDepartment.select(options);
    };

</script>

</html>
