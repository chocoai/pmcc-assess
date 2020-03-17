<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body>
<%--<%@include file="share/main_head.jsp" %>--%>
<!-- start: MAIN CONTAINER -->
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
                                <form id="query_form" class="form-horizontal">
                                    <div class="form-group form-inline">
                                        <label for="queryProjectName" class="col-md-1 col-form-label">项目名称</label>
                                        <div class="col-md-2 p-0">
                                            <input id="queryProjectName" name="queryProjectName"
                                                   class="form-control input-full" placeholder="项目名称"/>
                                        </div>
                                        <label for="queryTitle" class="col-md-1 col-form-label">标题</label>
                                        <div class="col-md-2 p-0">
                                            <input id="queryTitle" name="queryTitle" class="form-control input-full"
                                                   placeholder="标题"/>
                                        </div>
                                        <label for="queryStartTime" class="col-md-1 col-form-label">开始时间</label>
                                        <div class="col-md-2 p-0">
                                            <input id="queryStartTime" name="queryStartTime"
                                                   class="form-control input-full date-picker dbdate"
                                                   data-date-format="yyyy-mm-dd" placeholder="开始时间"/>
                                        </div>
                                        <label for="queryEndTime" class="col-md-1 col-form-label">结束时间</label>
                                        <div class="col-md-2 p-0">
                                            <input id="queryEndTime" name="queryEndTime"
                                                   class="form-control input-full date-picker dbdate"
                                                   data-date-format="yyyy-mm-dd" placeholder="结束时间"/>
                                        </div>
                                    </div>
                                    <div class="form-group form-inline">
                                        <label for="queryEndTime" class="col-md-1 col-form-label">选择人员</label>
                                        <div class="col-md-2 p-0">
                                            <input type="hidden" id="queryUserAccount" name="queryUserAccount">
                                            <input type="text" data-rule-maxlength="50" readonly
                                                   placeholder="选择人员" onclick="personSelect()"
                                                   id="queryUserAccountName" name="queryUserAccountName"
                                                   class="form-control input-full">
                                        </div>

                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="loadDataDicList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                    </div>
                                </form>

                                <table class="table table-bordered" id="tb_List">
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

<script type="application/javascript">

    $(function () {
        loadDataDicList();
    });

    //加载代理数据列表
    function loadDataDicList() {
        var data = formParams("query_form");
        var arr = Object.keys(data) ;
        $.each(arr,function (i,item) {
            if (!data[item]) {
                data[item] = undefined ;
            }
        }) ;
        console.log(data);
        var cols = [];
        cols.push({field: 'projectNames', title: '项目名称'});
        cols.push({field: 'title', title: '标题'});
        cols.push({field: 'content', title: '内容'});
        cols.push({field: 'createdName', title: '创建时间'});
        cols.push({field: 'creatorName', title: '创建人员'});
        cols.push({field: 'fileHtml', title: '附件'});
        var target = $("#tb_List") ;
        target.bootstrapTable('destroy');
        TableInit(target, "${pageContext.request.contextPath}/assessReport/getWorkLogList", cols, data, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }


    //选择人员
    function personSelect() {
        erpEmployee.select({
            onSelected: function (data) {
                if (data.account) {
                    $("#queryUserAccountName").val(data.name);
                    $("#queryUserAccount").val(data.account);
                }
                else {
                    $("#queryUserAccountName").val('');
                    $("#queryUserAccount").val('');
                }
            }
        });
    }
</script>
</html>
