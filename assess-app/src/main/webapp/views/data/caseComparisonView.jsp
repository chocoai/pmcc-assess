<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>


<body class="nav-md footer_fixed">
<%--<%@include file="share/main_head.jsp" %>--%>
<!-- start: MAIN CONTAINER -->
<div class="container body">
    <div class="main_container">
        <%--<jsp:include page="${pageContext.request.contextPath}/views/share/main_navigation.jsp" flush="true"/>--%>
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="row">
                <div class="x_panel">
                    <div class="x_title">
                        <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                            ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                        </h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <div class="col-md-3">
                            <div class="input-group">
                                <input type="text" id="queryName" class="form-control">
                                <span class="input-group-btn">
                              <a href="javascript://" onclick="queryCaseComparison()" class="btn btn-primary">查询</a>
                            </span>
                            </div>
                            <ul id="ztree" class="ztree"></ul>
                        </div>
                        <div class="col-md-9">
                            <!-- start: DYNAMIC TABLE PANEL -->
                            <div class="x_panel">
                                <div class="x_title">
                                    案例配置
                                </div>
                                <div class="x_content">
                                    <div id="customer_view" class="form-horizontal">

                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<link rel="stylesheet" href="/assets/plugins/zTree_v3/css/metroStyle/metroStyle.css">
<script type="text/javascript" src="/assets/plugins/zTree_v3/js/jquery.ztree.all.min.js"></script>
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    var zTreeObj;
    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pid",
                rootPId: 0
            }
        },
        async: {
            enable: true,
            url: "${pageContext.request.contextPath}/baseProjectClassify/getProjectClassifyTree",
            autoParam: ["id=pid"]
        },
        // 回调函数
        callback: {
            onClick: function (event, treeId, treeNode, clickFlag) {
                //显示配置信息

            }
        }
    };

    $(document).ready(function () {
        ztreeInit();
    });

    //初始化
    function ztreeInit() {
        zTreeObj = $.fn.zTree.init($("#ztree"), setting, [{"id": 0, "pid": 0, "name": "数据信息", "isParent": true}]);
        //展开第一级，选中根节点
        var rootNode = zTreeObj.getNodes()[0];
        zTreeObj.selectNode(rootNode);
        zTreeObj.expandNode(rootNode, true, false, true);
    }


    //查询项目分类
    function queryProjectClassify() {
        var queryName = $("#queryName").val();
        if (queryName) {
            $.ajax({
                url: "${pageContext.request.contextPath}/baseProjectClassify/getProjectClassifyTree",
                data: {name: queryName},
                type: "post",
                dataType: "json",
                success: function (result) {
                    zTreeObj = $.fn.zTree.init($("#ztree"), setting, result);
                    //选中第一个节点
                    var rootNode = zTreeObj.getNodes()[0];
                    zTreeObj.selectNode(rootNode);
                }
            })
        } else {
            ztreeInit();
        }
    }










</script>


</html>
