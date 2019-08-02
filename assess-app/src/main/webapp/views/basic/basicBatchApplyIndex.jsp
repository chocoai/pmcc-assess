<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" id="basicApplyId" role="main" style="margin-left: 0">
            <div class="page-title">
                <div class="title_left">
                    <h2><i class="fa "></i>
                        案例批量申请
                    </h2>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i
                                class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h3>
                        楼盘信息
                    </h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <%@include file="/views/basic/basicBatchPage/batchView.jsp" %>

                </div>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div style="text-align: center;">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button class="btn btn-warning" onclick="saveDraft()">
                            保存<i style="margin-left: 10px" class="fa fa-save"></i>
                        </button>
                        <button id="btn_submit" class="btn btn-success" onclick="submit();">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </div>
                </div>
            </div>
            <c:if test="${processInsId ne '0'}">
                <%@include file="/views/share/form_log.jsp" %>
                <form id="frm_approval">
                    <%@include file="/views/share/ApprovalVariable.jsp" %>
                </form>
            </c:if>
        </div>
    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/map.position.js"></script>
<%@include file="/views/share/main_footer.jsp" %>
</html>

<script type="text/javascript">
    //申请提交
    function submit() {
        if ("${processInsId}" != "0") {
            editSubmit();
        } else {
            if (!$("#basicBatchApplyFrm").valid()) {
                return false;
            }
            var id = $("#basicBatchApplyFrm").find("input[name='id']").val();
            if (!id) {
                Alert("请先添加楼盘的信息");
                return false;
            }
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/basicApplyBatch/basicApplyBatchSubmit",
                type: "post",
                dataType: "json",
                async: false,
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        Alert("提交数据成功!", 1, null, function () {
                            window.close();
                        });

                    } else {
                        Alert(result.errmsg);
                    }
                }
            });
        }
    }

    //返回修改提交
    function editSubmit() {
        var data = {};
        var approvalData = formParams('frm_approval');
        data = $.extend({}, approvalData, data);
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/editSubmit",
            type: "post",
            dataType: "json",
            async: false,
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                } else {
                    Alert(result.errmsg);
                }
            }
        });
    }


    //保存草稿
    function saveDraft() {
        if (!$("#basicBatchApplyFrm").valid()) {
            return false;
        }
        var radioValue = $("#basicBatchApplyFrm").find("input[type='radio']:checked").val();
        if(!radioValue){
            Alert("请选择类型");
            return false;
        }
        Loading.progressShow();
        var formData = formParams("basicBatchApplyFrm");
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/saveApplyDraftInfo",
            type: "post",
            dataType: "json",
            data: formData,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("保存草稿成功!", 1, null, function () {
                        window.close();
                    });
                } else {
                    Alert(result.errmsg);
                }
            }
        });
    }
</script>
