<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2017/7/31
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${flog=='approval'}">
    <div class="col-md-12">
        <div class="card full-height">
            <div class="card-header">
                <div class="card-head-row">
                    <div class="card-title">审批信息</div>
                </div>
            </div>
            <div class="card-body">
                <form id="frm_approval" class="form-horizontal">
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline">
                                <label class="col-sm-1 col-form-label">
                                    审批结论<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-4">
                                    <div class="form-check">
                                        <label class="form-radio-label">
                                            <input class="form-radio-input" type="radio" name="conclusion"
                                                   value="Approval" checked="checked" onclick="formApproval.chkRadioClick()">
                                            <span>同意</span>
                                        </label>
                                        <label class="form-radio-label ml-3">
                                            <input class="form-radio-input" type="radio" name="conclusion"
                                                   value="Decline" onclick="formApproval.chkRadioClick()">
                                            <span class="form-radio-sign">不同意，退回上一级</span>
                                        </label>
                                        <label class="form-radio-label ml-3">
                                            <input class="form-radio-input" type="radio" name="conclusion" value="Back"
                                                   onclick="formApproval.chkRadioClick()">
                                            <span class="form-radio-sign">不同意，退回申请人</span>
                                        </label>
                                    </div>
                                </div>
                                <c:if test="${lastNodes==0}"> <%--如果有下级节点--%>
                                    <div class="col-md-5" id="div_bisNext">
                                        <div class="form-check">
                                            <label class="form-check-label">
                                                <input id="chk_bisNext" name="chk_bisNext"
                                                       onclick="formApproval.chkbisNextClick()" class="form-check-input"
                                                       type="checkbox" value="">
                                                <span class="form-check-sign">跳过多级审批</span>
                                            </label>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <c:if test="${bisSelectUser=='1'}">
                        <div class="row form-group">
                            <div class="col-md-12">
                                <div class="form-inline">
                                    <label class="col-sm-1 col-form-label">
                                        下级审批人<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-4">
                                        <select class='form-control  input-full search-select select2'
                                                placeholder='下级审批人' id='nextApproval' name='nextApproval' required>
                                            <option value="">请选择</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${approvalReview==1}">
                        <c:forEach var="item" items="${boxReviewTemplate}">
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <label class="col-sm-1 col-form-label">
                                            ${item.content}(${item.standard})<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-11  x-valid">
                                        <input type="text" name="op_${item.id}" placeholder="${item.content}"
                                               required
                                               class='form-control input-full approvalFlog'>

                                    </div>
                                </div>
                            </div>

                        </c:forEach>
                    </c:if>
                    <c:if test="${approvalReview==0}">
                        <div class="row form-group">
                            <div class="col-md-12">
                                <div class="form-inline">
                                    <label class="col-sm-1 col-form-label">
                                        审批意见
                                    </label>
                                    <div class="col-sm-11">
                                        <div class="x-valid">
                                            <div type="text/plain" id="opinionsTemp" name="opinionsTemp"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline">
                                <label class="col-sm-1 col-form-label">
                                    审批文件
                                </label>
                                <div class="col-sm-11">
                                    <div class="x-valid">
                                        <input id="file_approval_upload" name="file_upload" type="file" multiple="false">
                                        <div id="_file_approval_upload">
                                        </div>                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <%@include file="/views/share/ApprovalVariable.jsp" %>
                    </div>
                </form>
                    <%--流程可考核，並且不是事后考核--%>

            </div>
        </div>
    </div>
    <c:if test="${boxReDto.bisLaunchCheck eq true}">
        <%@include file="../chks/assessmentForm.jsp" %>
    </c:if>
    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">
        <button class="btn btn-default " style="margin-left: 10px;" onclick="window.close()">
            取消
        </button>
        <button id="btn_submit" class="btn btn-primary" style="margin-left: 10px;"
                onclick="saveform();">
            提交
        </button>
    </div>
</c:if>
<c:if test="${flog=='details'}">
    <c:if test="${boxReDto.bisLaunchCheck eq true}">
        <%@include file="/views/chks/assessmentForm.jsp" %>
    </c:if>
    <%@include file="/views/share/form_details.jsp" %>
</c:if>
<%@include file="/views/share/form_log.jsp" %>
<script type="application/javascript">
    var formApproval = {
        valid: function () {
            return $("#frm_approval").valid();
        },

        chkbisNextClick: function () {
            if ($("#chk_bisNext").is(":checked")) {
                $("#bisNext").val(1);
                $('#nextApproval').attr('required',false).closest('.form-group').hide();//隐藏选择下级审批人
            }
            else {
                $("#bisNext").val(0);
                $('#nextApproval').attr('required',true).closest('.form-group').show();//显示选择下级审批人
            }
        },

        chkRadioClick: function () {
            var rdoValue = $("input[name='conclusion']:checked").val();
            if (rdoValue == "Approval") {
                $("#opinions").attr("required", false);//审批意见不必填
                $('#nextApproval').attr('required',true).closest('.form-group').show();//显示选择下级审批人
            }
            else {
                $("#opinions").attr("required", true);//审批意见必填
                $('#nextApproval').attr('required',false).closest('.form-group').hide();//隐藏选择下级审批人
            }
        },

        loadOpation: function () {
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
                opation = opinionsTemp.getContent();
            }
            return opation;
        },

        getFormData: function () {
            formApproval.chkbisNextClick();
            var formData = formParams("frm_approval");
            formData.opinions = formApproval.loadOpation();
            return formData;
        }
    };

    $(function () {
        window.opinionsTemp = UE.getEditor('opinionsTemp', {
            zIndex: 1000
        });
        var selectNextUser = "${selectNextUser}";
        if (selectNextUser) {
            $.ajax({
                url: '${pageContext.request.contextPath}/RpcErpService/getUsersInfoByAccounts',
                data: {accounts: selectNextUser},
                success: function (result) {
                    if (result.ret && result.data) {
                        $.each(result.data, function (i, item) {
                            $("#nextApproval").append('<option value="' + item.userAccount + '">' + item.userName + '</option>');
                        })
                    }
                }
            })
        }

        if ("${flog}" == "approval") {
            $("#frm_approval").validate();
            $("#opinions").attr("required", false);//审批意见不必填
        }
        FileUtils.uploadFiles({
            target: "file_approval_upload",
            disabledTarget: "btn_submit",
            formData: {
                tableName: AssessDBKey.BoxApprovalLog,
                projectId: "${projectId}",
                processInsId: "${processInsId}",
                reActivityName: "${activityReName}",
                fieldsName: "log",
                processTaskId: "${taskId}"
            },
            deleteFlag: true
        });

        FileUtils.getFileShows({
            target: "file_approval_upload",
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


