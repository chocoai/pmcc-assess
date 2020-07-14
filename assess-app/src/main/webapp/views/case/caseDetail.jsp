<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <div class="page-inner">
                <div class="row">
                    <!-- 填写表单 start -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        案例信息
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-sm"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class="row col-md-12">
                                    <div class="col-md-9">
                                        <ul id="ztree" class="ztree"></ul>
                                    </div>
                                    <div class="col-md-3">
                                        <form id="frmProjectCIP" class="form-horizontal">
                                            <input type="hidden" name="formClassify" value="${applyBatch.classify}">
                                            <input type="hidden" name="formType" value="${applyBatch.type}">
                                            <input type="hidden" name="planDetailsId" value="${applyBatch.planDetailsId}">
                                            <div class="row form-group">
                                                <div class="col-md-12 form-inline">
                                                    <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                                        大类
                                                    </label>
                                                    <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                        <c:if test="${not empty formClassifyList}">
                                                            <c:forEach var="item" items="${formClassifyList}">
                                                                <c:if test="${applyBatch.classify == item.id}">
                                                                    <label class="form-control input-full">${item.name}</label>
                                                                </c:if>
                                                            </c:forEach>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col-md-12 form-inline">
                                                    <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                                        类型
                                                    </label>
                                                    <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                        <c:if test="${not empty examineFormTypeList}">
                                                            <c:forEach var="item" items="${examineFormTypeList}">
                                                                <c:if test="${applyBatch.type == item.key}">
                                                                    <label class="form-control input-full">${item.value}</label>
                                                                </c:if>
                                                            </c:forEach>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">
                        <div class="card-body">
                            <button type="button" id="cancel_btn btn-sm" class="btn btn-default"
                                    onclick="window.close()">关 闭
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>

<div id="divVersionBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">版本</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="frm_version">
                    <input type="hidden" name="tbType">
                    <input type="hidden" name="tbId">
                    <input type="hidden" name="tableName">
                    <input type="hidden" name="applyBatchId">
                    <input type="hidden" name="formClassify" value="${applyBatch.classify}">
                    <input type="hidden" name="formType" value="${applyBatch.type}">
                    <input type="hidden" name="planDetailsId" value="${applyBatch.planDetailsId}">

                    <div class="row">
                    <div class="col-md-12">
                        <div class="card-body">
                            <table class="table table-bordered" id="table_dataList">
                            </table>
                        </div>
                    </div>
                </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>

        </div>
    </div>
</div>

</html>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.min.js?v=${assessVersion}"></script>
<script type="text/javascript">
    $(function () {
        if (${!empty applyBatch}) {
            ztreeInit(${applyBatch.id});

        }
    });
    var setting = {
        data: {
            key: {
                name: "displayName"
            },
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pid",
                rootPId: 0
            }
        },
        // 回调函数
        callback: {
            onClick: function (event, treeId, treeNode, clickFlag) {
                openVersionBox();
            }
        }
    };

    //初始化tree
    function ztreeInit(basicApplyBatchId) {
        $.ajax({
            url: '${pageContext.request.contextPath}/basicApplyBatch/getBatchApplyTree',
            data: {basicApplyBatchId: basicApplyBatchId},
            type: 'get',
            dataType: "json",
            success: function (result) {
                zTreeObj = $.fn.zTree.init($("#ztree"), setting, result);
                //展开第一级，选中根节点
                var rootNode = zTreeObj.getNodes()[0];
                zTreeObj.selectNode(rootNode);
                zTreeObj.expandAll(true);
            }
        })
    }


    //信息详情页面
    function informationDetail(id) {
        var frm = $("#frm_version");
        var data = formSerializeArray(frm);
        data.tbId = id;
        window.open('${pageContext.request.contextPath}/basicApplyBatch/informationDetail?' + parseParam(data));
    }

    //js对象转成路径参数
    var parseParam = function (param) {
        var arr = [];
        var keys = Object.keys(param);
        for (var i = 0; i < keys.length; i++) {
            var key = keys[i];
            var value = param[key];
            if (!value) {
                // continue ;
            }
            var paramStr = key + "=" + value;
            arr.push(paramStr)
        }
        return arr.join("&");
    };

    function openVersionBox(){
        var treeNode = zTreeObj.getSelectedNodes()[0];
        var frm = $("#frmProjectCIP");
        var data = formSerializeArray(frm);
        data.tbType = treeNode.type;
        data.tableName = treeNode.tableName;
        data.applyBatchId = treeNode.applyBatchId;
        showVersionList(treeNode.id);
        $("#frm_version").clearAll().initForm(data);
        $("#divVersionBox").modal("show");
    }


    function showVersionList(applyBatchDetailId) {
        var cols = [];
        cols.push({field: 'version', title: '版本'});
        cols.push({
            field: 'id', title: '查看', formatter: function (value, row, index) {
                var str = '<button type="button" onclick="informationDetail(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看">';
                str += '<i class="fa fa-search"></i>';
                str += '</button>';
                return str;
            }
        });
        $("#table_dataList").bootstrapTable('destroy');
        TableInit("table_dataList", "${pageContext.request.contextPath}/basic/getVersionList", cols, {
            applyBatchDetailId: applyBatchDetailId
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };
</script>

