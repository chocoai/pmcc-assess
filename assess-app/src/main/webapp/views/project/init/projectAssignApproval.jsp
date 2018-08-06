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
            <%@include file="/views/share/project/projectInfo.jsp" %>

            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2> 审批信息</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_approval" class="form-horizontal">
                        <div class="form-group" style="display: none">
                            <label class="col-sm-1 control-label">
                                审批结论
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-4">
                                    <label class="radio-inline">
                                        <input type="radio" value="Approval" required name="conclusion"
                                               checked="checked"
                                               class="grey"
                                               onclick="chkRadioClick()">
                                        同意
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                项目经理
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-3">
                                    <input type="hidden" id="appointUserAccount" name="appointUserAccount">
                                    <input type="text" required
                                           placeholder="项目经理"
                                           id="appointUserAccountName" name="appointUserAccountName"
                                           class="form-control" readonly="readonly"
                                           maxlength="200" onclick="selectUserAccountManager()">
                                </div>
                            </div>
                            <div class="col-sm-5" id="div_bisNext">
                                <label class="checkbox-inline">
                                    <input type="checkbox" id="chk_bisNext"><label for="chk_bisNext">下级分派</label>
                                </label>

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                说明
                            </label>
                            <div class="col-sm-11">
                                <c:if test="${approvalReview==1}">
                                    <c:forEach var="item" items="${boxReviewTemplate}">
                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-11" for="op_${item.id}">
                                                        ${item.content}(${item.standard})
                                                </label>
                                                <div class="col-sm-11">
                                        <textarea required placeholder="${item.content}" name="op_${item.id}"
                                                  class="form-control approvalFlog"></textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${approvalReview==0}">
                                    <div class="x-valid">
                           <textarea required placeholder="审批意见" id="opinionsTemp" name="opinionsTemp"
                                     class="form-control"></textarea>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                审批文件
                            </label>
                            <div class="col-sm-11">
                                <input id="file_upload" name="file_upload" type="file" multiple="false">
                                <div id="_file_upload">
                                </div>
                            </div>
                        </div>


                        <div class="form-group">
                            <%@include file="/views/share/ApprovalVariable.jsp" %>
                        </div>

                    </form>
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-5">
                            <button class="btn btn-default" onclick="window.close()">
                                取消
                            </button>
                            <button id="btn_submit" class="btn btn-success" onclick="saveform()">
                                提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>


<script type="application/javascript">
    $(function () {
        $("#frm_approval").validate();
        $("#opinions").attr("required", false);//审批意见不必填
        FileUtils.uploadFiles({
            target: "file_upload",
            disabledTarget: "btn_submit",
            formData: {
                tableName: AssessDBKey.BoxApprovalLog,
                processInsId: "${processInsId}",
                reActivityName: "${activityReName}",
                fieldsName: "log",
                processTaskId:${taskId}
            },
            deleteFlag: true
        });
    })
    // 项目经理
    function selectUserAccountManager() {
        erpEmployee.select({
            onSelected: function (data) {
                $("#appointUserAccountName").val(data.name);
                $("#appointUserAccount").val(data.account);
            }
        });
    }
    function saveform() {
        var data = formParams("frm_approval");
        if ($("#chk_bisNext").is(':checked')) {
            data.bisNext = "1";
            if ($("#appointUserAccount").val() == "") {
                Alert("选择了下级分派则必须选择项目经理");
                return false;
            }
        }
        else {
            data.bisNext = "0";
        }
        data.appointUserAccount = $("#appointUserAccount").val();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectInfo/projectApprovalAssignSubmit",
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
                    Alert("保存数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        })
    }
</script>
</html>
