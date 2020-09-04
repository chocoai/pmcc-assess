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
                                        <label class="col-md-1 col-form-label">开始时间</label>
                                        <div class="col-md-2 p-0">
                                            <input type="hidden" id="userAccount" name="userAccount"
                                                   class="form-control"/>
                                            <input type="text" data-rule-maxlength="50" readonly="readonly"
                                                   onclick="selectUser(this)" placeholder="人员" name="userName"
                                                   class="form-control input-full">
                                        </div>
                                        <button class="btn btn-info  btn-sm" type="button" style="margin-left: 10px"
                                                onclick="customEstateTimes.prototype.loadSurveyEstateTimesList();">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                    </div>
                                </form>
                                <table class="table table-bordered" id="tb_List"></table>
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
        customEstateTimes.prototype.loadSurveyEstateTimesList();
    });


    var customEstateTimes = function () {
    };
    customEstateTimes.prototype = {
        loadSurveyEstateTimesList: function () {
            var cols = [];
            cols.push({field: 'estateName', width: '20%', title: '楼盘'});
            cols.push({field: 'surveyCount', width: '10%', title: '数量'});
            cols.push({
                field: 'projectName', width: '40%', title: '项目', formatter: function (value, row, index) {
                    return value.replace(/,/g,'<br/>');
                }
            });
            cols.push({
                field: 'surveyDate', width: '20%', title: '时间', formatter: function (value, row, index) {
                    return value.replace(/,/g,'<br/>');
                }
            });
            $("#tb_List").bootstrapTable('destroy');
            TableInit($("#tb_List"), "${pageContext.request.contextPath}/customPublic/getSurveyEstateTimesList", cols, {
                userAccount: $("#userAccount").val()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        }
    }

    function selectUser(_this) {
        var div = $(_this).closest("div");
        erpEmployee.select({
            currOrgId: '${companyId}',
            showAllUser: 2,
            onSelected: function (data) {
                div.find("input[name='userName']").val(data.name);
                div.find("input[name='userAccount']").val(data.account);
            }
        });
    }
</script>
</html>
