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
                                        <button class="btn btn-sm btn-success paste"
                                                data-toggle="modal" href="#divBoxCase"
                                                onclick="openCaseBox();">案例申请
                                        </button>
                                        <ul id="ztree" class="ztree"></ul>
                                    </div>
                                    <div class="col-md-3">
                                        <form id="frmProjectCIP" class="form-horizontal">
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
                                            <div class="row form-group">
                                                <div class="col-md-12 form-inline">
                                                    <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                                        建筑状态
                                                    </label>
                                                    <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                        <c:if test="${not empty buildingStatusList}">
                                                            <c:forEach var="item" items="${buildingStatusList}">
                                                                <c:if test="${applyBatch.buildingStatus == item.id}">
                                                                    <label class="form-control input-full">${item.name}</label>
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
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>
<div id="divBoxCase" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">添加或升级案例</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmCase" class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <ul id="caseZtree" class="ztree" style="max-height: 260px;overflow: auto;"></ul>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="submitCase()">
                    提交
                </button>
            </div>

        </div>
    </div>
</div>
<script type="application/javascript">
    function saveform() {
        saveApprovalform("");
    }
</script>
<script type="text/javascript">

    $(function () {
        if (${!empty applyBatch}) {
            ztreeInit(${applyBatch.id});
        }
    });
    var setting = {
        data: {
            key: {
                name: "name"
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
        var frm = $("#frmProjectCIP");
        var data = formSerializeArray(frm);
        data.tbType = treeNode.type;
        data.tbId = treeNode.tableId;
        data.applyBatchId = treeNode.applyBatchId;

        window.open('${pageContext.request.contextPath}/basicApplyBatch/informationDetail?' + parseParam(data));
    }

    //进入填写信息页面
    function fillInformation() {
        var node = zTreeObj.getSelectedNodes()[0];
        var frm = $("#frmProjectCIP");
        var data = formSerializeArray(frm);
        data.tbType = node.type;
        data.applyBatchId = node.applyBatchId;
        data.tbId = node.tableId;
        window.open('${pageContext.request.contextPath}/basicApplyBatch/informationEdit?' + parseParam(data));
    }

    //js对象转成路径参数
    var parseParam = function (param, key) {
        var paramStr = "";
        if (param instanceof String || param instanceof Number || param instanceof Boolean) {
            paramStr += "&" + key + "=" + encodeURIComponent(param);
        } else {
            $.each(param, function (i) {
                var k = key == null ? i : key + (param instanceof Array ? "[" + i + "]" : "." + i);
                paramStr += '&' + parseParam(this, k);
            });
        }
        return paramStr.substr(1);
    };

    //添加到备选案例
    function addToAlternative() {
        Loading.progressShow();
        var node = zTreeObj.getSelectedNodes()[0];
        var data = {};
        data.batchDetailId = node.id;
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
                } else {
                    AlertError("添加失败", "失败原因:" + result.errmsg, 1, null, null);
                }
            }
        });
    }
</script>

<%--案例快速申请--%>
<script type="application/javascript">
    var caseSetting = {
        check: {
            enable: true,
            chkStyle: "checkbox",
            chkboxType: {"Y": "", "N": ""}//必须设为null ,这样可以真正意义上让复选框不影响父级和子级,哪个被点击了就勾选哪个
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
        }
    };

    function caseZtreeInit(basicApplyBatchId) {
        $.ajax({
            url: '${pageContext.request.contextPath}/basicApplyBatch/getBatchApplyTree',
            data: {
                basicApplyBatchId: basicApplyBatchId,
                showTag: true
            },
            type: 'get',
            dataType: "json",
            success: function (result) {
                zTreeObj = $.fn.zTree.init($("#caseZtree"), caseSetting, result);
                var nodesSys = zTreeObj.getNodes(); //可以获取所有的父节点
                var nodesSysAll = zTreeObj.transformToArray(nodesSys); //获取树所有节点
                for (var i = 0; i < nodesSysAll.length; i++) {
                    if (nodesSysAll[i].displayName.indexOf('新增') != -1) {
                        nodesSysAll[i].checked = true;
                        zTreeObj.updateNode(nodesSysAll[i]);
                    }
                }
                //展开第一级，选中根节点
                var rootNode = zTreeObj.getNodes()[0];
                zTreeObj.selectNode(rootNode);
                zTreeObj.expandAll(true);
            }
        })
    }


    function openCaseBox() {
        if (${!empty applyBatch}) {
            if (${!empty applyBatch.referenceApplyBatchId}) {
                caseZtreeInit(${applyBatch.referenceApplyBatchId});
            } else {
                caseZtreeInit(${applyBatch.id});
            }
        }
        $('#divBoxCase').modal("show");
    }

    //申请案例提交
    function submitCase() {
        var sourceApplyBatchId = '${applyBatch.id}';
        var zTreeObj = $.fn.zTree.getZTreeObj($("#caseZtree").prop("id"));
        var nodes = zTreeObj.getCheckedNodes(true);
        if (nodes.length == 0) {
            notifyInfo('提示', '勾选至少一个节点');
            return false;
        }
        $.each(nodes, function (i, node) {
            checkParentNodeRecursion(node);
        });
        var halfCheckNodes = zTreeObj.getNodesByFilter(function (node) {
            return  node.getCheckStatus().half;
        },false);
        $.each(halfCheckNodes, function (i, node) {
            node.halfCheck=true;
            nodes.push(node);
        });
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/basicApplyBatchSurveySubmit",
            data: {
                sourceApplyBatchId: sourceApplyBatchId,
                zTreeData: JSON.stringify(nodes)
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    notifySuccess("成功", "申请成功");
                    $('#divBoxCase').modal("hide");
                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            }
        })
    }

    //在勾选的节点中，当前节点的上级节点为新增节点则该节点必须选中，递归执行
    function checkParentNodeRecursion(node) {
        if (!node) return;
        var parentNode = node.getParentNode();
        if (parentNode && parentNode.bisAdd && !parentNode.checked) {
            parentNode.checked = true;
            zTreeObj.updateNode(parentNode);
        }
        checkParentNodeRecursion(parentNode);
    }
</script>
</html>

