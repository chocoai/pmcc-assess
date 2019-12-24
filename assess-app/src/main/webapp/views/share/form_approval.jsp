<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2017/7/31
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="frm_approval" class="form-horizontal">
    <c:if test="${flog=='approval'}">
        <div class="x_panel">
            <div class="x_title collapse-link">
                <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                </ul>
                <h3> 审批信息</h3>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <div class="form-group">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        审批结论<span class="symbol required"></span>
                    </label>
                    <div class="x-valid">
                        <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
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
                    <c:if test="${lastNodes==0}"> <%--如果有下级节点--%>
                        <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 " id="div_bisNext">
                            <label class="checkbox-inline">
                                <input type="checkbox" id="chk_bisNext" name="chk_bisNext" value="" class="grey"
                                       onclick="formApproval.chkbisNextClick();">
                                跳过多级审批
                            </label>
                        </div>
                        <script type="text/javascript">
                            $(function () {
                                formApproval.chkbisNextClick();
                            })
                        </script>
                    </c:if>
                </div>
                <c:if test="${bisSelectUser=='1'}">
                    <div class="form-group" id="div_nextApproval">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                            下级审批人<span class="symbol required"></span>
                        </label>
                        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                            <div class="x-valid">
                                <select id="nextApproval" name="nextApproval" class="form-control" required>
                                    <option value="">-请选择-</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </c:if>
                <div class="form-group">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        审批意见<span class="symbol required"></span>
                        <c:forEach items="${nextUserInfo}" var="item">
                            <label>${item.userAccount}_${item.userNickname}</label>
                        </c:forEach>
                    </label>
                    <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                        <c:if test="${approvalReview==1}">
                            <c:forEach var="item" items="${boxReviewTemplate}">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 " for="op_${item.id}">
                                                ${item.content}(${item.standard})
                                        </label>
                                        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                                        <textarea required placeholder="${item.content}" name="op_${item.id}"
                                                  class="form-control approvalFlog"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                        <c:if test="${approvalReview==0}">
                            <div class="x-valid">
                                <div id="opinionsTemp" style="height: auto; background-color:#EEE;"></div>
                            </div>
                        </c:if>
                    </div>
                </div>
                <div class="form-group">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        审批文件
                    </label>
                    <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                        <input id="file_upload" name="file_upload" type="file" multiple="false">
                        <div id="_file_upload">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <%@include file="/views/share/ApprovalVariable.jsp" %>
                </div>
            </div>
        </div>
    </c:if>
</form>
<c:if test="${bisCheck==1}">
    <%@include file="form_chks.jsp" %>
</c:if>
<c:if test="${flog=='approval'}">
    <div class="x_panel">
        <div class="x_content">
            <div class="form-group" style="text-align: center;">
                <div>
                    <button class="btn btn-default" onclick="window.close()">
                        取消
                    </button>
                    <button id="btn_submit" class="btn btn-primary" onclick="saveform()">
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
                $("#div_nextApproval").hide();
            }
            else {
                $("#bisNext").val(0);
                $("#div_nextApproval").show();
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
            target: "file_upload",
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
