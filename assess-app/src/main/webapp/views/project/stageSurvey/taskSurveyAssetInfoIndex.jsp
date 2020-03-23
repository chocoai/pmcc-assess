<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <%@include file="/views/share/project/projectInfoSimple.jsp" %>
                    <%@include file="/views/share/project/projectPlanDetails.jsp" %>

                    <div class="col-md-12">
                        <div class="x_panel card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="x_title card-title">
                                        权证信息
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-${empty areaGroups?'down':'up'}"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form class="form-horizontal">
                                    <input type="hidden" name="projectId" value="${projectInfo.id}">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline">
                                                <label class="col-sm-1 control-label">
                                                    权证号
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-maxlength="50" placeholder="权证号"
                                                           name="name"
                                                           class="form-control input-full">
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    坐落
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-maxlength="50" placeholder="坐落"
                                                           name="seat"
                                                           class="form-control input-full">
                                                </div>
                                                <div class="col-sm-3">
                                                    <button type="button" class="btn btn-info btn-sm"
                                                            onclick="assetInfo.loadDeclareRecordList(this);">
                                                        查询
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <table class="table table-bordered" id="tb_declare_record_list"></table>
                                </form>
                            </div>
                        </div>
                    </div>
                    <%@include file="/views/share/form_apply.jsp" %>
                    <%--<%@include file="/views/share/form_log.jsp" %>--%>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>

<script>

    var assetInfo = {};

    assetInfo.declareRecordTable = $("#tb_declare_record_list") ;

    assetInfo.handleJquery = function (obj) {
        if (obj instanceof jQuery) {
            return obj;
        } else {
            return $(obj.selector);
        }
    };

    assetInfo.loadDeclareRecordList = function (_this) {
        var data = formSerializeArray($(_this).closest("form"));
        var arr = Object.keys(data);
        $.each(arr, function (i, item) {
            if (!data[item]) {
                data[item] = undefined;
            }
        });
        console.log(data) ;
        var target = assetInfo.handleJquery(assetInfo.declareRecordTable) ;
        var cols = [];
        cols.push({field: 'name', title: '权证号'});
        cols.push({field: 'seat', title: '坐落'});
        cols.push({field: 'certUse', title: '证载用途'});
        cols.push({field: 'practicalUse', title: '实际用途'});
        cols.push({field: 'floorArea', title: '面积'});
        target.bootstrapTable('destroy');
        TableInit(target, "${pageContext.request.contextPath}/declareRecord/getDeclareRecordList", cols, data, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        });
    };


    $(document).ready(function () {
        var tableEle = assetInfo.handleJquery(assetInfo.declareRecordTable)[0] ;
        assetInfo.loadDeclareRecordList(tableEle) ;
    }) ;

</script>


<script type="text/javascript">

    function submit(mustUseBox) {
        var formData = JSON.stringify({});
        if ("${processInsId}" != "0") {
            submitEditToServer(formData);
        } else {
            submitToServer(formData, mustUseBox);
        }
    }


</script>


</html>

