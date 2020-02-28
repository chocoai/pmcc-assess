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
                                <form class="form-horizontal" id="frmProjectCIP">
                                    <!-- formClassify 大类 , formType 类型-->
                                    <input type="hidden" name="formClassify" value="${applyBatch.classify}">
                                    <input type="hidden" name="formType" value="${applyBatch.type}">
                                    <input type="hidden" name="planDetailsId" value="${applyBatch.planDetailsId}">
                                    <input type="hidden" name="applyBatchId" value="${applyBatch.id}">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">

                                                <label class="col-sm-1">
                                                    大类
                                                </label>
                                                <div class="col-sm-2">
                                                    <c:if test="${not empty formClassifyList}">
                                                        <c:forEach var="item" items="${formClassifyList}">
                                                            <c:if test="${applyBatch.classify == item.id}">
                                                                <label class="form-control input-full">${item.name}</label>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>
                                                </div>
                                                <label class="col-sm-1">
                                                    类型
                                                </label>
                                                <div class="col-sm-2">
                                                    <c:if test="${not empty examineFormTypeList}">
                                                        <c:forEach var="item" items="${examineFormTypeList}">
                                                            <c:if test="${applyBatch.type == item.key}">
                                                                <label class="form-control input-full">${item.value}</label>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>
                                                </div>

                                                <c:if test="${not empty declareRecord}">
                                                    <label class="col-sm-1">
                                                        建筑状态
                                                    </label>
                                                    <div class="col-sm-2">
                                                        <c:if test="${not empty buildingStatusList}">
                                                            <c:forEach var="item" items="${buildingStatusList}">
                                                                <c:if test="${applyBatch.buildingStatus == item.id}">
                                                                    <label class="form-control input-full">${item.name}</label>
                                                                </c:if>
                                                            </c:forEach>
                                                        </c:if>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <div class="col-md-3 pull-left" style="max-height: 500px;overflow: auto;">
                                                <ul id="ztree" class="ztree"></ul>
                                            </div>
                                            <div class="col-md-8 pull-left" id="btnGroup">
                                                <button class="btn btn-sm btn-success" onclick="informationDetail()">
                                                    详情
                                                </button>
                                                <c:if test="${flog=='approval'}">
                                                    <button class="btn btn-sm btn-primary" onclick="fillInformation();">
                                                        编辑
                                                    </button>
                                                </c:if>
                                                <c:if test="${not empty declareRecord && flog!='approval'}">
                                                    <button class="btn btn-sm btn-warning paste alternativeCase" style="display: none"
                                                       onclick="addToAlternative();">
                                                        添加到备选案例
                                                    </button>
                                                </c:if>
                                            </div>
                                        </div>
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
        ztreeInit();
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
        },// 回调函数
        callback: {
            onClick: function (event, treeId, treeNode, clickFlag) {
                showFunctionBtn();
            }
        }
    };

    //初始化
    function ztreeInit() {
        $.ajax({
            url: '${pageContext.request.contextPath}/basicApplyBatch/getBatchApplyTree',
            data: {basicApplyBatchId: '${applyBatch.id}'},
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
        data.tableId = treeNode.tableId;
        data.tableName = treeNode.tableName;

        window.open('${pageContext.request.contextPath}/basicApplyBatch/informationDetail?' + parseParam(data));
    }

    //进入填写信息页面
    function fillInformation() {
        var node = zTreeObj.getSelectedNodes()[0];
        var frm = $("#frmProjectCIP");
        var data = formSerializeArray(frm);
        data.tbType = node.type;
        data.tbId = node.tableId;
        window.open('${pageContext.request.contextPath}/basicApplyBatch/fillInfo?' + parseParam(data));
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


    //test
    var obj = {name: 'tom', 'class': {className: 'class1'}, classMates: [{name: 'lily'}]};
    //    console.log(parseParam(obj)) ;
    //output: "name=tom&class.className=class1&classMates[0].name=lily"
    //    console.log(parseParam(obj, 'stu')) ;
    //output: "stu.name=tom&stu.class.className=class1&stu.classMates[0].name=lily"

    function showFunctionBtn() {
        var node = zTreeObj.getSelectedNodes()[0];
        if (node.executor == '${userAccount}') {
            $("#btnGroup").find('.btn.alternativeCase').show();
        } else {
            $("#btnGroup").find('.btn.alternativeCase').hide();
        }

    }

    //添加到备选案例
    function addToAlternative() {
        Loading.progressShow();
        var node = zTreeObj.getSelectedNodes()[0];
        var data = {};
        data.business_id = node.id;
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
                    notifySuccess("成功,添加成功");
                }
                else {
                    AlertError("添加失败，失败原因:" + result.errmsg, 1, null, null);
                }
            }
        });
    }
</script>
</body>
</html>

