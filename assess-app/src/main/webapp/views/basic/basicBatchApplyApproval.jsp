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
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>楼盘信息</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="basicBatchApplyDetialFrm" class="form-horizontal">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    省
                                </label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <label class="form-control">${applyBatch.provinceName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    市
                                </label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <label class="form-control">${applyBatch.cityName}</label>
                                </div>
                            </div>
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
                            <span class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-xs-offset-1 col-sm-offset-1 col-md-offset-1 col-lg-offset-1 checkbox-inline">
                                <input type="radio" id="applyFormType0" name="type" value="0">
                                <label for="applyFormType0">非工业交通仓储</label>
                            </span>

                            <span class=" col-xs-2  col-sm-2  col-md-2  col-lg-2   checkbox-inline">
                                <input type="radio" id="applyFormType1" name="type" value="1">
                                <label for="applyFormType1">工业交通仓储</label>
                            </span>
                            <span class=" col-xs-2  col-sm-2  col-md-2  col-lg-2   checkbox-inline">
                                <input type="radio" id="applyFormType2" name="type" value="2">
                                <label for="applyFormType2">构筑物</label>
                            </span>

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
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
</html>
<script type="text/javascript">
    $(function () {
        $("#basicBatchApplyDetialFrm").find("input[type='radio'][name='type'][value='${applyBatch.type}']").trigger('click');
        $("#basicBatchApplyDetialFrm").find("input[type='radio']").on('click', function () {
            return false;
        });
        ztreeInit("${applyBatch.estateName}");
    })
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
        zTreeObj = $.fn.zTree.init($("#ztree"), setting, [{"id": 0, "pid": 0, "name": estateName, "isParent": true}]);
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
            estateId = ${applyBatch.estateId};
        }
        var type = ${applyBatch.type};
        window.open('${pageContext.request.contextPath}/basicApplyBatch/informationDetail?type=' + type + "&id=" + node.id + "&buildingType=" + node.level + "&estateId=" + estateId);
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
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    Alert(result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        })
    }

</script>

