<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>


<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="row">
                <div class="x_panel">
                    <div class="x_title ">
                        <h2>
                            <i class="fa ${baseViewDto.currentMenu.icon}"></i>
                            ${baseViewDto.currentMenu.name}
                        </h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <div class="col-md-2">
                            <ul id="ztree" class="ztree"></ul>
                        </div>
                        <div class="col-md-2">
                            <ul id="ztree2" class="ztree"></ul>
                        </div>
                        <div class="col-md-2">
                            <ul id="ztree3" class="ztree"></ul>
                        </div>
                        <div class="col-xs-6">
                            <form id="frm_dataHisRightInfoPublicity" class="form-horizontal" style="display: none">
                                <div class="form-group ">
                                    <input type="hidden" id="zTreeNodeId">
                                    <button type="button" class="btn btn-primary" onclick="saveObject()">
                                        保存
                                    </button>
                                </div>
                                <div class="form-group ">
                                  <textarea required placeholder="他权公式信息" name="content" id="content" rows="20"
                                            class="form-control"></textarea>
                                </div>
                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
</div>


</body>


<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    var zTreeObj;
    var zTreeObj2;
    var zTreeObj3;
    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pid",
                rootPId: 0
            },
        },
        async: {
            enable: true,
            url: "${pageContext.request.contextPath}/dataHisRightInfoPublicity/getDataHisRightInfoPublicityTree",
            autoParam: ["id=pid"]
        },
        // 回调函数
        callback: {
            onClick: function (event, treeId, treeNode, clickFlag) {
                $("#content").val("");
                $("#frm_dataHisRightInfoPublicity").hide();
                // 判断是否父节点
                if (treeNode.parent) {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/dataHisRightInfoPublicity/getDataHisRightInfoPublicityTree",
                        data: {"pid": treeNode.id},
                        error: function () {//请求失败处理函数
                            alert('请求失败');
                        },
                        success: function (data) { //添加子节点到指定的父节点

                            var jsondata = eval(data);
                            if (jsondata == null || jsondata == "") {

                            }
                            else {
                                var treeObj;
                                if (treeNode.pid == 0) {
                                    zTreeObj2 = $.fn.zTree.init($("#ztree2"), setting, [{
                                        "id": treeNode.id,
                                        "pid": treeNode.pid,
                                        "name": "市",
                                        "isParent": true
                                    }]);
                                    treeObj = $.fn.zTree.getZTreeObj("ztree2");
                                    //清空区
                                    $.fn.zTree.init($("#ztree3"), setting, [{
                                        "id": 0,
                                        "pid": 0,
                                        "name": "区",
                                        "isParent": true
                                    }]);

                                } else {
                                    zTreeObj3 = $.fn.zTree.init($("#ztree3"), setting, [{
                                        "id": treeNode.id,
                                        "pid": treeNode.pid,
                                        "name": "区",
                                        "isParent": true
                                    }]);
                                    treeObj = $.fn.zTree.getZTreeObj("ztree3");
                                    getContent(treeNode.id);
                                }
                                var parentZNode = treeObj.getNodeByParam("id", treeNode.id, null);//获取指定父节点
                                newNode = treeObj.addNodes(parentZNode, jsondata, false);

                            }
                        }
                    });
                } else {

                    getContent(treeNode.id);
                }

            }
        }
    };
    $(function () {

        ztreeInit();
    });


    function ztreeInit() {
        zTreeObj = $.fn.zTree.init($("#ztree"), setting, [{"id": 0, "pid": 0, "name": "省", "isParent": true}]);
        //展开第一级，选中根节点
        var rootNode = zTreeObj.getNodes()[0];
        zTreeObj.selectNode(rootNode);
        //$("#zTreeNodeId").val(-1);
        //loadContractList();
        zTreeObj.expandNode(rootNode, true, false, true);
    }

    //保存
    function saveObject() {
        if ($("#frm_dataHisRightInfoPublicity").valid()) {
            Loading.progressShow();
            var data = formParams("frm_dataHisRightInfoPublicity");
            data.province = zTreeObj.getSelectedNodes()[0].id;
            data.city = zTreeObj2.getSelectedNodes()[0].id;
            if (zTreeObj3.getSelectedNodes()[0]) {
                data.district = zTreeObj3.getSelectedNodes()[0].id;
            }
            $.ajax({
                url: "${pageContext.request.contextPath}/dataHisRightInfoPublicity/saveAndUpdateDataHisRightInfoPublicity",
                data: data,
                type: "post",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('保存成功');
                    } else {
                        Alert("保存失败：" + result.errmsg);
                    }
                }
            })
        }
    }

    //获取信息
    function getContent(areaId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/dataHisRightInfoPublicity/getContent",
            data: {
                areaId: areaId
            },
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        $("#content").val(result.data.content);
                    } else {
                        $("#content").val("");
                    }
                    $("#frm_dataHisRightInfoPublicity").show();
                } else {
                    Alert(result.errmsg);
                }
            }
        })

    }

</script>

</html>
