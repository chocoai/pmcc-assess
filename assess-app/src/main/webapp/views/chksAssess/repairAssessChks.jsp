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
            <div class="page-title" style="margin: 0px">
                <div class="title_left" style="width: auto;">
                    <h3>
                        【${chksApprovalBusinessVo.processCnName}】
                        <small>${chksApprovalBusinessVo.processTitle}</small>
                    </h3>

                </div>
            </div>
            <div class="clearfix"></div>
            <form class="form-horizontal" id="frm_assess">
                <div class="accordion" id="accordionAssessChks" role="tablist" aria-multiselectable="true">
                    <div class="panel">
                        <a class="panel-heading" role="tab" id="headingAssessChks" data-toggle="collapse" data-parent="#accordionAssessChks"
                           href="#collapseAssessChks"
                           aria-expanded="true" aria-controls="collapseAssessChks">
                            <h4 class="panel-title">修改考核数据(${thisUserName})->${chksApprovalInfoVo.activityName}</h4>
                        </a>
                        <div id="collapseAs sessChks" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingAssessChks"
                             aria-expanded="true" style="max-height: 400px;">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th style="width:50%">考核项<a target="_blank" class="label label-waring" href="/pmcc-chks/ChksAssess/detailsBusiness?processInsId=${processInsId}">查看记录</a></th>

                                    <c:forEach var="user" items="${chksApprovalInfoVo.sysUserDtos}">
                                        <input type="hidden" value="${user.userAccount}" class="activityUser">
                                        <th colspan="2">${user.userName}(${user.departmentName})</th>
                                    </c:forEach>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="assess" items="${assessmentItemDtos}">

                                    <tr>
                                        <td style="width:50%">${assess.assessmentDes}<label class="label label-success">${assess.minScore}~${assess.maxScore}</label></td>
                                        <c:forEach var="user" items="${chksApprovalInfoVo.sysUserDtos}">
                                            <td>
                                                <div class="x-valid">
                                                    <input required="required"
                                                           data-rule-range="[${assess.minScore},${assess.maxScore}]"
                                                           type="number"
                                                           step="0.1"
                                                           class="form-control ${user.userAccount}"
                                                           value="0.0"
                                                           onblur="complexAssessScore('${user.userAccount}')"
                                                           id="${user.userAccount}_${assess.id}" name="${user.userAccount}_${assess.id}"
                                                    />
                                                </div>
                                            </td>
                                            <td>
                                                <div class="x-valid">
                                                    <input type="text"
                                                           class="form-control "
                                                           placeholder="考核说明"
                                                           id="${user.userAccount}_${assess.id}_remarks" name="${user.userAccount}_${assess.id}_remarks"
                                                    />
                                                </div>
                                            </td>
                                        </c:forEach>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td style="width:50%">合计</td>
                                    <c:forEach var="user" items="${chksApprovalInfoVo.sysUserDtos}">
                                        <td colspan="2">
                                            得分：<label class="label label-warning" id="${user.userAccount}_total">0.0</label>;
                                            有效分：<label class="label label-warning" id="${user.userAccount}_total_vaild">0.0</label>
                                            <input type="hidden" id="${user.userAccount}_total_text" value="0.0"/>
                                            <input type="hidden" id="${user.userAccount}_total_vaild_text" value="0.0"/>
                                        </td>
                                    </c:forEach>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </form>
            <div class="x_panel">
                <div class="x_title">
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <a id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </a>
                        <a id="commit_btn" class="btn btn-success" onclick="saveform()">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    $(function () {
        $("#frm_assess").validate();
    });
    function complexAssessScore(obj) {
        var objs = $("." + obj);
        var sum = 0.0;
        $.each(objs, function (i, j) {
            $(j).val(parseFloat($(j).val()).toFixed(1));
            sum += parseFloat($(j).val());
        });
        var ratio = "${scoreRatio}";
        $("#" + obj + "_total").html(sum.toFixed(1));
        $("#" + obj + "_total_vaild").html((parseFloat(ratio) * sum).toFixed(1));
        $("#" + obj + "_total_text").val(sum.toFixed(1));
        $("#" + obj + "_total_vaild_text").val((parseFloat(ratio) * sum).toFixed(1));

    }

    function saveform() {
        if (!$("#frm_assess").valid()) {
            return false;
        }
        Loading.progressShow();
        var formData = [];
        var userList = $(".activityUser");
        $.each(userList, function (i, user) {
            var userAccount = $(user).val();
            var assessList = $("." + userAccount);
            var assessArray = [];
            $.each(assessList, function (j, assess) {
                var id = $(assess).attr("id");
                var ids = id.split('_');
                assessArray.push({
                    score: $(assess).val(),
                    scoreRemarks: $("#" + id + "_remarks").val(),
                    assessModelId: ids[1]
                })
            });
            var data = {
                userAccount: userAccount,
                chksApprovalAssessDetails: assessArray,
                chksApprovalBusinessId: "${chksApprovalBusinessVo.id}",
                chksApprovalInfoId: "${chksApprovalInfoVo.id}",
                totalScore: $("#" + userAccount + "_total_text").val(),
                totalVaildScore: $("#" + userAccount + "_total_vaild_text").val(),
                boxId: "${chksApprovalBusinessVo.businessBoxId}",
                bisRepair: "1"
            };
            formData.push(data);
        })
        $.ajax({
            url: "${pageContext.request.contextPath}/ChksAssess/repairAssessChksSave",
            data: {
                formData: JSON.stringify(formData)
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("考核成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    Alert("保存数据失败，失败原因：" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }
</script>


</html>
