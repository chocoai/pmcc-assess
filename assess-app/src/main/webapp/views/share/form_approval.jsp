<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2017/7/31
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${flog=='approval'}">
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
                <div class="form-group">
                    <label class="col-sm-1 control-label">
                        审批结论
                    </label>
                    <div class="x-valid">
                        <div class="col-sm-5">
                            <label class="radio-inline">
                                <input type="radio" value="Approval" required name="conclusion" checked="checked"
                                       class="grey"
                                       onclick="formApproval.chkRadioClick()">
                                同意
                            </label>
                            <label class="radio-inline">
                                <input type="radio" value="Decline" required name="conclusion" class="grey"
                                       onclick="formApproval.chkRadioClick()">
                                不同意，退回上一级
                            </label>
                            <label class="radio-inline">
                                <input type="radio" value="Back" required name="conclusion" class="grey"
                                       onclick="formApproval.chkRadioClick()">
                                不同意，退回申请人
                            </label>
                        </div>
                    </div>
                    <c:if test="${bisSelectUser!='1'}">
                        <c:if test="${lastNodes==0}"> <%--如果有下级节点--%>
                            <div class="col-sm-5" id="div_bisNext">
                                <label class="checkbox-inline">
                                    <input type="checkbox" id="chk_bisNext" name="chk_bisNext" checked="checked"
                                           value=""
                                           class="grey"
                                           onclick="formApproval.chkbisNextClick()">
                                    跳过多级审批
                                </label>

                            </div>
                        </c:if>
                    </c:if>
                </div>
                <c:if test="${bisSelectUser=='1'}">
                    <div class="form-group">
                        <label class="col-sm-1 control-label">
                            下级审批人
                        </label>
                        <div class="col-sm-11">
                            <input type="hidden" id="nextApproval" name="nextApproval"
                                   value="${selectNextUser}">
                            <input type="text" required value="${selectNextUserName}"
                                   placeholder="下级审批人"
                                   id="nextApprovalName" name="nextApprovalName"
                                   class="form-control" readonly="readonly"
                                   maxlength="200" onclick="nextApprovalSelect()">
                        </div>
                    </div>
                </c:if>
                <div class="form-group">
                    <label class="col-sm-1 control-label">
                        审批意见
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
            <div class="form-group" style="text-align: center;">
                <div>
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
</c:if>
<c:if test="${flog=='details'}">
    <%@include file="/views/share/form_details.jsp" %>
</c:if>


<script type="application/javascript">
    var formApproval = {
        valid: function () {
            return $("#frm_approval").valid();
        },

        chkbisNextClick: function () {
            if ($("#chk_bisNext").is(":checked")) {
                $("#bisNext").val(1);
            }
            else {
                $("#bisNext").val(0);
            }
            if (window.cutomNextClick) {
                cutomNextClick();
            }
        },

        chkRadioClick: function () {
            var rdoValue = $("input[name='conclusion']:checked").val();
            if (rdoValue == "Approval") {
                $("#opinions").attr("required", false);//审批意见不必填
            }
            else {
                $("#opinions").attr("required", true);//审批意见必填
            }
        },

        loadOpation: function () {
            formApproval.chkbisNextClick();
            var opation = "";
            if ("${approvalReview}" == "1") {
                var objs = $(".approvalFlog");
                var opation = "";
                $.each(objs, function (i, j) {
                    var obj = $(j);
                    opation += obj.attr("placeholder") + "【" + obj.val() + "】" + ";<br/>";
                });
                opation = opation.substring(0, opation.length - 1);
            }
            else {
                opation = $("#opinionsTemp").val();
            }
            return opation;
        },

        getFormData: function () {
            var formData = formApproval.getFormData();
            formData.opinions = formApproval.loadOpation();
            return formData;
        }
    };

    $(function () {
        if ("${flog}" == "approval") {
            $("#frm_approval").validate();
            $("#opinions").attr("required", false);//审批意见不必填
        }
        FileUtils.uploadFiles({
            target: "file_upload",
            disabledTarget: "btn_submit",
            formData: {
                tableName: AssessDBKey.BoxApprovalLog,
                processInsId: "${processInsId}",
                reActivityName: "${activityReName}",
                fieldsName: "log",
                processTaskId: "${taskId}"
            },
            deleteFlag: true
        });

        FileUtils.getFileShows({
            target: "file_upload",
            formData: {
                tableName: AssessDBKey.BoxApprovalLog,
                processInsId: "${processInsId}",
                reActivityName: "${activityReName}",
                fieldsName: "log",
                processTaskId: "${taskId}"
            },
            deleteFlag: true
        })
    })
</script>