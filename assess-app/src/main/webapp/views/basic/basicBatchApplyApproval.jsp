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
            <div class="col-md-12">
                <div class="x_panel card">
                    <div class="card-header collapse-link">
                        <div class="card-head-row">
                            <div class="card-title">
                                楼盘信息
                            </div>
                            <div class="card-tools">
                                <button class="btn  btn-link btn-primary btn-xs"><span
                                        class="fa fa-angle-down"></span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="x_content card-body">
                        <form id="basicBatchApplyDetialFrm" class="form-horizontal">
                            <!-- formClassify 大类 , formType 类型-->
                            <input type="hidden" name="formClassify" value="${applyBatch.classify}">
                            <input type="hidden" name="formType" value="${applyBatch.type}">
                            <input type="hidden" name="planDetailsId" value="${applyBatch.planDetailsId}">
                            <input type="hidden" name="applyBatchId" value="${applyBatch.id}">
                            <div class="row form-group">
                                <div class="col-md-12 form-inline">
                                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                        大类
                                    </label>
                                    <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                        <c:if test="${not empty formClassifyList}">
                                            <c:forEach var="item" items="${formClassifyList}">
                                                <c:if test="${applyBatch.classify == item.id}">
                                                    <label class="form-control input-full">${item.name}</label>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </div>
                                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                        类型
                                    </label>
                                    <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
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
                        <div class="form-horizontal">
                            <div class="form-group">
                                <div class="col-xs-3  col-sm-3  col-md-3  col-xs-3 col-lg-offset-1 col-sm-offset-1 col-xs-offset-1 col-md-offset-1">
                                    <ul id="ztree" class="ztree"></ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>

</html>
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
                informationDetail();
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
    function informationDetail() {
        var node = zTreeObj.getSelectedNodes()[0];
        if (node.bisModify == false) {
            var href = "${pageContext.request.contextPath}/";
            switch (node.type) {
                case "estate": {
                    href += "caseEstate/detailView";
                    break;
                }
                case "building": {
                    href += "caseBuilding/detailView";
                    break;
                }
                case "unit": {
                    href += "caseUnit/detailView";
                    break;
                }
                case "house": {
                    href += "caseHouse/detailView";
                    break;
                }
            }
            href += "?id=" + node.id;
            window.open(href, "");
        } else {
            var classify = ${applyBatch.classify};
            var formType = ${applyBatch.type};
            var url = '${pageContext.request.contextPath}/basicApplyBatch/informationDetail?';
            url += 'applyBatchId=' +  ${applyBatch.id};
            url += '&formClassify=' + classify;
            url += '&formType=' + formType;
            url += '&tableId=' + node.tableId;
            url += '&tbType=' + node.type;
            url += '&tableName=' + node.tableName;
            url += '&planDetailsId=${projectPlanDetails.id}';
            openWin(url, function () {
            })
        }
    }


    function saveform() {
        if (!$("#frm_approval").valid()) {
            return false;
        }
        var data = formApproval.getFormData();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/basicApprovalSubmit",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "提交数据成功", function () {
                        window.close();
                    });
                }
                else {
                    AlertError(result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        })
    }

    //有案例数据初始化tree
    function caseEstateZtreeInit(applyBatchId) {
        $.ajax({
            url: '${pageContext.request.contextPath}/basicApplyBatch/getCaseApprovalZtreeDto',
            data: {applyBatchId: applyBatchId},
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
</script>

