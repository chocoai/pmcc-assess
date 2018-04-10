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

                <c:forEach var="activity" items="${chksApprovalAssessListVos}">
                    <input type="hidden" value="${activity.chksApprovalInfoVos.id}" class="activityList">
                    <input type="hidden" value="${activity.scoreRatio}" id="${activity.chksApprovalInfoVos.id}_scoreRatio">
                    <div class="accordion" id="accordion${activity.chksApprovalInfoVos.id}" role="tablist" aria-multiselectable="true">
                        <div class="panel">
                            <a class="panel-heading" role="tab" id="heading${activity.chksApprovalInfoVos.id}" data-toggle="collapse" data-parent="#accordion${activity.chksApprovalInfoVos.id}"
                               href="#collapse${activity.chksApprovalInfoVos.id}"
                               aria-expanded="true" aria-controls="collapse${activity.chksApprovalInfoVos.id}">
                                <h4 class="panel-title">${thisActivityName}(${thisUserName})->${activity.chksApprovalInfoVos.activityName}</h4>
                            </a>
                            <div id="collapse${activity.chksApprovalInfoVos.id}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading${activity.chksApprovalInfoVos.id}"
                                 aria-expanded="true"
                                 style="height: 0px;">
                                <div class="panel-body">
                                    <h4><a target="_blank" href="${chksApprovalBusinessVo.businessUrl}" class="label label-info">查看原始表单</a></h4>
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th style="width:50%">考核项</th>

                                            <c:forEach var="user" items="${activity.chksApprovalInfoVos.sysUserDtos}">
                                                <input type="hidden" value="${user.userAccount}" class="activityUser_${activity.chksApprovalInfoVos.id}">
                                                <th colspan="2" style="text-align: center">${user.userName}(${user.departmentName})</th>
                                            </c:forEach>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="assess" items="${activity.assessmentItemDtos}">

                                            <tr>
                                                <td style="width:50%">${assess.assessmentDes}<label class="label label-success">${assess.minScore}~${assess.maxScore}</label></td>
                                                <c:forEach var="user" items="${activity.chksApprovalInfoVos.sysUserDtos}">
                                                    <td>
                                                        <div class="x-valid">
                                                            <input required="required"
                                                                   data-rule-range="[${assess.minScore},${assess.maxScore}]"
                                                                   type="number"
                                                                   step="0.1"
                                                                   class="form-control ${activity.chksApprovalInfoVos.id}_${user.userAccount}"
                                                                   value="0.0"
                                                                   onblur="complexScore('${activity.chksApprovalInfoVos.id}','${user.userAccount}')"
                                                                   id="${activity.chksApprovalInfoVos.id}_${user.userAccount}_${assess.id}"
                                                                   name="${activity.chksApprovalInfoVos.id}_${user.userAccount}_${assess.id}"
                                                            />
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="x-valid">
                                                            <input type="text"
                                                                   class="form-control "
                                                                   placeholder="考核说明"
                                                                   id="${activity.chksApprovalInfoVos.id}_${user.userAccount}_${assess.id}_remarks"
                                                                   name="${activity.chksApprovalInfoVos.id}_${user.userAccount}_${assess.id}_remarks"
                                                            />
                                                        </div>
                                                    </td>
                                                </c:forEach>
                                            </tr>
                                        </c:forEach>
                                        <tr>
                                            <td style="width:50%">合计</td>
                                            <c:forEach var="user" items="${activity.chksApprovalInfoVos.sysUserDtos}">
                                                <td colspan="2">
                                                    得分：<label class="label label-warning" id="${activity.chksApprovalInfoVos.id}_${user.userAccount}_total">0</label>;
                                                    有效分：<label class="label label-warning" id="${activity.chksApprovalInfoVos.id}_${user.userAccount}_total_vaild">0</label>
                                                    <input type="hidden" id="${activity.chksApprovalInfoVos.id}_${user.userAccount}_total_text"/>
                                                    <input type="hidden" id="${activity.chksApprovalInfoVos.id}_${user.userAccount}_total_vaild_text"/>
                                                </td>
                                            </c:forEach>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
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
        $(".panel-heading").each(function () {
            $(this).get(0).click();
        })
    });
    function complexScore(activity, userAccoount) {
        var obj = activity + "_" + userAccoount;
        var objs = $("." + obj);
        var sum = 0.0;
        $.each(objs, function (i, j) {
            $(j).val(parseFloat($(j).val()).toFixed(1));
            sum += parseFloat($(j).val());
        });
        var ratio = $("#" + activity + "_scoreRatio").val();
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
        var activityList = $(".activityList");
        $.each(activityList, function (p, activity) {
            var activityId = $(activity).val();
            var userList = $(".activityUser_" + activityId);
            $.each(userList, function (i, user) {
                var userAccount = $(user).val();
                var assessList = $("." + activityId + "_" + userAccount);
                var assessArray = [];
                $.each(assessList, function (j, assess) {
                    var id = $(assess).attr("id");
                    var ids = id.split('_');
                    assessArray.push({
                        score: $(assess).val(),
                        scoreRemarks:$("#"+id+"_remarks").val(),
                        assessModelId: ids[2]
                    })
                });
                var data = {
                    userAccount: userAccount,
                    chksApprovalAssessDetails: assessArray,
                    chksApprovalBusinessId:${chksApprovalBusinessVo.id},
                    chksApprovalInfoId: activityId,
                    totalScore: $("#" + activityId + "_" + userAccount + "_total_text").val(),
                    totalVaildScore: $("#" + activityId + "_" + userAccount + "_total_vaild_text").val(),
                    boxId:${chksApprovalBusinessVo.businessBoxId}
                };
                formData.push(data);
            })
        });
        $.ajax({
            url: "${pageContext.request.contextPath}/ChksAssess/saveAssessResult",
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
