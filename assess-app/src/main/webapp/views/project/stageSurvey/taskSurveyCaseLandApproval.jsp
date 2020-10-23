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

                    <!-- 填写表单 start -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        ${projectPlanDetails.projectPhaseName}
                                        <small>${declareRecord.name}</small>
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
                                        <button type="button" class="btn btn-sm btn-info" onclick="informationDetail()">
                                            查看信息
                                        </button>
                                        <c:if test="${flog=='approval'}">
                                            <button type="button" class="btn btn-sm btn-primary"
                                                    onclick="fillInformation();">
                                                编辑信息
                                            </button>
                                        </c:if>
                                        <button class="btn btn-sm btn-warning paste alternativeCase"
                                                onclick="addToAlternative();">添加到备选案例
                                        </button>
                                        <ul id="ztree" class="ztree"></ul>
                                    </div>
                                    <div class="col-md-3">
                                        <form id="basicBatchApplyFrm" class="form-horizontal">
                                            <input type="hidden" name="formClassify" value="${applyBatch.classify}">
                                            <input type="hidden" name="formType" value="${applyBatch.type}">
                                            <input type="hidden" name="planDetailsId"
                                                   value="${applyBatch.planDetailsId}">
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

                    <%@include file="/views/share/form_approval.jsp" %>
                    <%-- <%@include file="/views/share/form_log.jsp" %>--%>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>

<script type="application/javascript">
    function saveform() {
        saveApprovalform("");
    }
</script>
<script type="text/javascript">

    $(function () {
        if (${!empty applyBatch}) {
            if (${!empty applyBatch.referenceApplyBatchId}) {
                ztreeInit(${applyBatch.referenceApplyBatchId});
            } else {
                ztreeInit(${applyBatch.id});
            }
        }
    });
    var setting = {
        view: {
            fontCss: function (treeId, treeNode) {
                if (treeNode.executor != '${projectPlanDetails.executeUserAccount}') {
                    return {color: "grey"};//灰色 业务规定的
                } else {
                    return {color: "black"};
                }
            }
        },
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
        },// 回调函数
        callback: {
            onClick: function (event, treeId, treeNode, clickFlag) {
                showFunctionBtn();
            }
        }
    };

    //初始化
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
                zTreeObj.setting.callback.onClick(null, zTreeObj.setting.treeId, rootNode);//调用事件
            }
        })
    }

    //信息详情页面
    function informationDetail(data) {
        var treeNode = zTreeObj.getSelectedNodes()[0];
        var frm = $("#basicBatchApplyFrm");
        var data = formSerializeArray(frm);
        data.tbType = treeNode.type;
        data.tbId = treeNode.tableId;
        data.applyBatchId = treeNode.applyBatchId;

        window.open('${pageContext.request.contextPath}/basicApplyBatch/informationDetail?' + parseParam(data));
    }

    //进入填写信息页面
    function fillInformation() {
        var node = zTreeObj.getSelectedNodes()[0];
        var frm = $("#basicBatchApplyFrm");
        var data = formSerializeArray(frm);
        data.tbType = node.type;
        data.applyBatchId = node.applyBatchId;
        data.tbId = node.tableId;
        window.open('${pageContext.request.contextPath}/basicApplyBatch/informationEdit?' + parseParam(data));
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

    function showFunctionBtn() {
        var node = zTreeObj.getSelectedNodes()[0];
    }

    //添加到备选案例
    function addToAlternative() {
        Loading.progressShow();
        var node = zTreeObj.getSelectedNodes()[0];
        var data = {};
        data.batchDetailId = node.id;
        data.type='land';
        data.business_id = node.tableId;
        data.business_key = node.type;
        $.ajax({
            url: "${pageContext.request.contextPath}/basicAlternativeCase/addToAlternative",
            data: {
                formData: JSON.stringify(data)
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    notifySuccess("成功", "添加成功");
                }
                else {
                    AlertError("添加失败", "失败原因:" + result.errmsg, 1, null, null);
                }
            }
        });
    }
</script>
</html>

