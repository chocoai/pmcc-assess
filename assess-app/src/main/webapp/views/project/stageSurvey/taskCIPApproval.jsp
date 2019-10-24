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
                <div class="x_content" style="min-height: 300px;">
                    <form class="form-horizontal" id="frmProjectCIP">
                        <!-- formClassify 大类 , formType 类型-->
                        <input type="hidden" name="formClassify" value="${applyBatch.classify}">
                        <input type="hidden" name="formType" value="${applyBatch.type}">
                        <input type="hidden" name="planDetailsId" value="${applyBatch.planDetailsId}">
                        <input type="hidden" name="applyBatchId" value="${applyBatch.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    大类
                                </label>
                                <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                    <c:if test="${not empty formClassifyList}">
                                        <c:forEach var="item" items="${formClassifyList}">
                                            <c:if test="${applyBatch.classify == item.id}">
                                                <label class="form-control">${item.name}</label>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    类型
                                </label>
                                <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                    <c:if test="${not empty examineFormTypeList}">
                                        <c:forEach var="item" items="${examineFormTypeList}">
                                            <c:if test="${applyBatch.type == item.key}">
                                                <label class="form-control">${item.value}</label>
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
                            <div class="col-xs-8  col-sm-8  col-md-8  col-lg-8">
                            </div>
                        </div>
                    </div>
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
        },
        // 回调函数
        callback: {
            onClick: function (event, treeId, treeNode, clickFlag) {
                var frm = $("#frmProjectCIP");
                var data = formSerializeArray(frm);
                data.tbType = treeNode.type;
                data.tableId = treeNode.tableId;
                data.tableName = treeNode.tableName;
                informationDetail(data);
            }
        }
    };

    //初始化
    function ztreeInit() {
        $.ajax({
            url: '${pageContext.request.contextPath}/basicApplyBatch/getBatchApplyTree',
            data: {estateId: '${applyBatch.estateId}'},
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
    function informationDetail(data) {
        window.open('${pageContext.request.contextPath}/basicApplyBatch/informationDetail?'+ parseParam(data) );
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


</script>
</body>
</html>

