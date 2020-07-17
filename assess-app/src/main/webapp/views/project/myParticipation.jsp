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
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-md-1 col-form-label">项目名称</label>
                                                <div class="col-md-2 p-0">
                                                    <input type="text" data-rule-maxlength="50"
                                                           placeholder="项目名称" name="projectName"
                                                           class="form-control input-full">
                                                </div>
                                                <button style="margin-left: 10px" class="btn btn-info  btn-sm"
                                                        type="button"
                                                        onclick="loadParticipationList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                                    查询
                                                </button>
                                                <button style="margin-left: 10px" class="btn btn-info  btn-sm"
                                                        type="button"
                                                        onclick="$('#frmQuery').clearAll()">
                                                    <span class="fa fa-undo-alt" aria-hidden="true"
                                                          class="-space"></span>
                                                    重置
                                                </button>
                                                <button style="margin-left: 10px"
                                                        class="btn btn-sm btn-info  btn-border"
                                                        type="button"
                                                        onclick="showOtherQuery(this)">
                                                    <span class="btn-label">
												<i class="fa fa-plus-circle"></i>
											</span>
                                                    高级查询
                                                </button>

                                            </div>
                                        </div>
                                    </div>
                                    <div id="showOtherQuery" style="display: none">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-md-1 col-form-label">状态</label>
                                                <div class="col-md-2 p-0">
                                                    <select name="projectStatus" class="form-control input-full">
                                                        <option value="">--请选择--</option>
                                                        <c:forEach var="item" items="${statusEnumList}">
                                                            <option value="${item.key}">${item.value}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <label class="col-md-1 col-form-label">项目成员</label>
                                                <div class="col-md-2 p-0">
                                                    <input type="hidden" name="queryMember" data-title="account">
                                                    <input type="text" data-rule-maxlength="50" readonly="readonly"
                                                           onclick="selectCreate(this)"
                                                           placeholder="项目成员" name="queryMemberName"
                                                           class="form-control input-full" data-title="name">
                                                </div>
                                                <label class="col-md-1 col-form-label">项目经理</label>
                                                <div class="col-md-2 p-0">
                                                    <input type="hidden" name="queryManager" data-title="account">
                                                    <input type="text" readonly="readonly" onclick="selectCreate(this)"
                                                           placeholder="项目经理" name="queryManagerName"
                                                           class="form-control input-full" data-title="name">
                                                </div>
                                                <label class="col-md-1 col-form-label">立项人</label>
                                                <div class="col-md-2 p-0">
                                                    <input type="hidden" name="queryCreator" data-title="account">
                                                    <input type="text" readonly="readonly" onclick="selectCreate(this)"
                                                           placeholder="立项人" name="queryCreatorName"
                                                           class="form-control input-full" data-title="name">
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-md-1 col-form-label">委托目的</label>
                                                <div class="col-md-2 p-0">
                                                    <select name="entrustPurpose" class="form-control input-full">
                                                        <option value="">--请选择--</option>
                                                        <c:forEach var="item" items="${entrustPurposeList}">
                                                            <option value="${item.id}">${item.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <label class="col-md-1 col-form-label">开始时间</label>
                                                <div class="col-md-2 p-0">
                                                    <input type="text"
                                                           class="form-control input-full date-picker dbdate"
                                                           data-date-format="yyyy-mm-dd" name="queryTimeStart"
                                                           placeholder="开始时间"/>
                                                </div>
                                                <label class="col-md-1 col-form-label">结束时间</label>
                                                <div class="col-md-2 p-0">
                                                    <input type="text"
                                                           class="form-control input-full date-picker dbdate"
                                                           data-date-format="yyyy-mm-dd" name="queryTimeEnd"
                                                           placeholder="结束时间"/>
                                                </div>
                                                <label class="col-md-1 col-form-label">报告使用单位</label>
                                                <div class="col-md-2 p-0">
                                                    <input type="text"
                                                           placeholder="单位" class="form-control input-full"
                                                           name="queryUseUnitName">
                                                </div>


                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-md-1 col-form-label">评估部门</label>
                                                <div class="col-md-2 p-0">
                                                    <input name="queryDepartmentId" class="form-control input-full"
                                                           type="hidden"/>
                                                    <input name="queryDepartmentName" class="form-control input-full"
                                                           placeholder="评估部门"
                                                           onclick="selectDepartment(this)" readonly="readonly"/>
                                                </div>
                                                <label class="col-md-1 col-form-label">委托人</label>
                                                <div class="col-md-1 p-0">
                                                    <select name="queryConsignorType" class="form-control input-full">
                                                        <option value="">--请选择--</option>
                                                        <option value="0">自然人</option>
                                                        <option value="1">法人</option>
                                                    </select>
                                                </div>
                                                <div class="col-md-1 p-0">
                                                    <input type="text" placeholder="委托人" class="form-control input-full"
                                                           name="queryConsignor">
                                                </div>
                                                <label class="col-md-1 col-form-label">项目类型</label>
                                                <div class="col-md-2 p-0">
                                                    <select class="form-control input-full" required name="queryProjectCategoryId">
                                                        <option value="">--请选择--</option>
                                                        <c:forEach var="classItem" items="${projectCategoryList}">
                                                            <c:forEach var="typeItem" items="${classItem.keyValueDtos}">
                                                                <c:if test="${not empty typeItem.keyValueDtos}">
                                                                    <c:forEach var="categoryItem" items="${typeItem.keyValueDtos}">
                                                                        <option value="${categoryItem.key}">${categoryItem.value}</option>
                                                                    </c:forEach>
                                                                </c:if>
                                                            </c:forEach>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    </div>
                                </form>
                                <table class="table table-bordered" id="tb_myParticipation">
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

<script type="text/javascript" src="/pmcc-crm/js/crm-customer-utils.js?v=1.0"></script>
<script type="application/javascript">
    $(function () {
        loadParticipationList();

    });

    function loadParticipationList() {
        var selectObj = formSerializeArray($("#frmQuery"));
        var cols = [];
        cols.push({
            field: 'projectName', title: '项目名称', width: '30%', formatter: function (value, row, index) {
                var str = value;
                str += '<span style="margin-left: 2px;background-color: #868b9e;" class="label label-default">' + row.useUnitName + '</span>';
                str += '<span style="margin-left: 2px;background-color: #9ed2a0;" class="label label-success">' + row.departmentName + '</span>';
                str += '<span style="margin-left: 2px;background-color: #b3b0e2;" class="label label-secondary">' + row.entrustPurposeName + '</span>';
                str += '<span style="margin-left: 2px;background-color: #accfea;" class="label label-info">' + row.loanTypeName + '</span>';
                return str;
            }
        });
        cols.push({
            field: 'serviceEnd', title: '项目成员', width: '15%', formatter: function (value, row, index) {
                var s = "";
                if (row.userAccountManagerName) {
                    s += "<span class='label label-primary'>" + row.userAccountManagerName.split("_")[0] + "</span>"
                }
                if (row.userAccountMemberName) {
                    s += " " + row.userAccountMemberName.split("_")[0];
                }
                return s;
            }
        });
        cols.push({
            field: 'projectClassName', title: '类型', width: '8%', formatter: function (value, row, index) {
                return row.projectCategoryName;
            }
        });
        cols.push({field: 'projectStatus', title: '状态', width: '10%'});
        cols.push({
            field: 'finishPre', title: '项目进度', width: '15%', formatter: function (value, row, index) {
                var s = "<div class='progress progress-sm' style='margin-bottom: 0px;'>";
                s += "<div class='progress-bar bg-success' role='progressbar'  style='width: " + value + "%;'></div>";
                s += "</div>";
                s += "<small>完成" + value + "%</small>";
                return s;
            }
        });
        cols.push({
            field: 'gmtCreated', title: '立项时间', width: '14%', formatter: function (value, row, index) {
                return formatDate(row.gmtCreated, true);
            }
        });
        cols.push({
            field: 'opation', title: '操作', width: '10%', formatter: function (value, row, index) {
                var str = '<button type="button" onclick="checkDetail(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看详情">';
                str += '<i class="fa fa-search"></i>';
                str += '</button>';
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
        var div = $(_this).closest("div");
        erpEmployee.select({
            currOrgId: '${companyId}',
            onSelected: function (data) {
                div.find("input[data-title='name']").val(data.name);
                div.find("input[data-title='account']").val(data.account);
            }
        });
    }


    function selectCustomer(this_) {
        var div = $(this_).closest("div");
        //选择客户
        crmCustomer.select({
            multi: false,//是否允许多选
            companyId: "${companyId}",
            onSelected: function (nodes) {
                div.find("[name='queryUseUnit']").val(nodes[0].id);
                div.find("[name='queryUseUnitName']").val(nodes[0].name);
            }
        });
    }

    //部门
    function selectDepartment(this_) {
        var div = $(this_).closest("div");
        var options = {
            onSelected: function (nodes) {
                div.find("[name='queryDepartmentId']").val(nodes[0].id);
                div.find("[name='queryDepartmentName']").val(nodes[0].name);
            }
        };
        erpDepartment.select(options);
    };

    function checkDetail(id) {
        var href = "${pageContext.request.contextPath}/projectCenter/projectInfo";
        href += "?projectId=" + id;
        window.open(href, "");
    }

    function showOtherQuery(_that) {
        $("#showOtherQuery").toggle();
        var c = $(_that).find("i").attr("class");
        if (c.indexOf("plus") != -1) {
            $(_that).find("i").attr("class", "fa fa-minus-circle");
        } else {
            $(_that).find("i").attr("class", "fa fa-plus-circle");
        }

    }
</script>

</html>
