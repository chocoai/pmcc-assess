<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3> ${projectPlanDetails.projectPhaseName}</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form class="form-horizontal">
                        <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                楼盘名称
                            </label>
                            <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                <label class="form-control">${applyBatch.estateName}</label>
                            </div>
                        </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-3">
                                <ul id="ztree" class="ztree"></ul>
                            </div>
                            <div class="col-md-9">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    function saveform() {
        saveApprovalform("");
    }
</script>
<script type="text/javascript">
    $(function () {
        ztreeInit("${applyBatch.estateName}");
    })
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
        async: {
            enable: true,
            url: "${pageContext.request.contextPath}/basicApplyBatch/getTree",
            otherParam: {
                estateId: function () {
                    return ${applyBatch.estateId};
                }
            },
            autoParam: ["id=pid"]
        },
        // 回调函数
        callback: {
            onClick: function (event, treeId, treeNode, clickFlag) {
                informationDetail();
            }
        }
    };

    //初始化
    function ztreeInit(estateName) {
        zTreeObj = $.fn.zTree.init($("#ztree"), setting, [{
            "id": 0,
            "pid": 0,
            "displayName": estateName,
            "isParent": true
        }]);
        //展开第一级，选中根节点
        var rootNode = zTreeObj.getNodes()[0];
        zTreeObj.selectNode(rootNode);

        zTreeObj.expandNode(rootNode, true, false, true);
    }

    //信息详情页面
    function informationDetail() {
        var node = zTreeObj.getSelectedNodes()[0];
        var estateId = 0;
        if (node.id == 0) {
            estateId = '${applyBatch.estateId}';
        }
        var type = 3;
        window.open('${pageContext.request.contextPath}/basicApplyBatch/informationDetail?type=' + type + "&id=" + node.id + "&buildingType=" + node.level + "&estateId=" + estateId);
    }


</script>
</body>
</html>

