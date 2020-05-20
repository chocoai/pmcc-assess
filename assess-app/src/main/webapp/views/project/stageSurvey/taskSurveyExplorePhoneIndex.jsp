<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.min.js?v=${assessVersion}"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.css">
    <style>
        .ui-autocomplete {
            z-index: 2147483647;
        }
    </style>
</head>

<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%--<%@include file="/views/share/form_head.jsp" %>--%>
            <div class="page-inner">
                <div class="row mt--2">
                    <%--<%@include file="/views/share/project/projectInfoSimple.jsp" %>--%>

                    <!-- 填写表单 start -->
                    <div class="col-md-12">
                        <div class="card full-height" style="min-height: 300px;">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        查勘附件
                                    </div>
                                    <%--<div class="card-tools">--%>
                                        <%--<button class="btn  btn-link btn-primary btn-sm"><span--%>
                                                <%--class="fa fa-angle-down"></span>--%>
                                        <%--</button>--%>
                                    <%--</div>--%>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class="row col-md-12">
                                    <div class="col-md-12">

                                        <button type="button" class="btn btn-sm btn-primary"
                                                onclick=" batchTreeTool.expandAll(true);">
                                            展开
                                        </button>
                                        <button type="button" class="btn btn-sm btn-primary"
                                                onclick=" batchTreeTool.expandAll(false);">
                                            收起
                                        </button>

                                    </div>

                                    <div class="col-md-9">
                                        <ul id="ztree" class="ztree" style="margin-top: 10px;"></ul>
                                    </div>

                                    <div class="col-md-3">
                                        <button type="button" style="margin-left: 20px;"
                                                class="btn btn-sm btn-primary fillInformation masterTool"
                                                onclick="batchTreeTool.fillInformation();">
                                            进入上传附件页面
                                        </button>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>





                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">
                        <div class="card-body">
                            <button type="button" id="cancel_btn btn-sm" class="btn btn-default"
                                    onclick="window.close()">关闭
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

</html>

<script type="text/javascript">
    $(function () {
        if (${!empty basicApplyBatch}) {
            batchTreeTool.loadTree();
        }
    });


</script>
<script type="text/javascript">
    
    var batchTreeTool = {} ;

    batchTreeTool.handleJquery = function (obj) {
        if (obj instanceof jQuery) {
            return obj;
        } else {
            return $(obj.selector);
        }
    };

    batchTreeTool.loadTree = function () {
        var setting = {
            view: {
                fontCss: function (treeId, treeNode) {
                    if (treeNode.executor != '${userAccount}') {
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

                }
            }
        };
        var zTreeObj = null;
        $.ajax({
            url: '${pageContext.request.contextPath}/basicApplyBatch/getBatchApplyTree',
            data: {basicApplyBatchId: '${basicApplyBatch.id}'},
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
    } ;




    //全部展开或收起
    batchTreeTool.expandAll = function (flag) {
        var zTreeObj = $.fn.zTree.getZTreeObj('ztree');
        zTreeObj.expandAll(flag);
    };



    //进入填写信息页面
    batchTreeTool.fillInformation = function () {
        var zTreeObj = $.fn.zTree.getZTreeObj('ztree');
        var nodes = zTreeObj.getSelectedNodes();
        if (nodes.length > 1) {
            notifyInfo('提示', '只能选择一个节点');
            return false;
        }
        var node = nodes[0];
        var url = '${pageContext.request.contextPath}/basicApplyBatch/informationPhoneEdit?';
        url += 'applyBatchId=' + node.applyBatchId;
        url += '&formClassify=' + '${basicApplyBatch.classify}';
        url += '&formType=' + '${basicApplyBatch.type}';
        url += '&tbId=' + node.tableId;
        url += '&tbType=' + node.type;
        url += '&planDetailsId=${projectPlanDetails.id}';
        if (node.executor != '${userAccount}') {
            notifyWarning("提示", "此节点不属于当前登陆人的,无操作权限!");
            return false;
        }
        openWin(url, function () {
        })
    }


</script>
