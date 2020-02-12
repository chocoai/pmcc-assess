<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="/views/share/chksCommon.jsp" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>考核信息</h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
            <table class="table" id="assessmentTableList">
            </table>
        </div>
    </div>
</div>


<script type="text/javascript">

    /*初始化*/
    $(document).ready(function () {
        (function (json) {
            if (!json.processInsId) {
                if ('${projectPlanDetails.processInsId}') {
                    json.processInsId = '${projectPlanDetails.processInsId}';
                }
                if ('${activityId}') {
                    json.activityId = '${activityId}';
                }
            }
            if ('${boxReDto}'){
                json.boxId = '${boxReDto.id}';
            }
            if ('${activityDtoList}') {
                var activityIds = [];
                var data = null;
                try {
                    data = JSON.parse('${el:toJsonString(activityDtoList)}');
                } catch (e) {
                    console.log(e);
                }
                if (data) {
                    $.each(data, function (i, item) {
                        activityIds.push(item.id);
                    });
                }
                if (activityIds.length != 0) {
                    json.activityIds = activityIds.join(",");
                }
            }

            //这里涉及到权限,如果要优化请慎重操作
            //抽查组人员  可以对任务进行抽查
            var array = [];
            if ('${spotUserAccounts}') {
                var spotUserAccounts = JSON.parse('${el:toJsonString(spotUserAccounts)}');
                $.each(spotUserAccounts, function (i, account) {
                    if (account == '${sysUserDto.userAccount}') {
                        array.push(assessmentCommonHandle.getSpotCol());
                    }
                });

            }
            assessmentCommonHandle.getChksBootstrapTableVoBase($("#assessmentTableList"), json, array);
        }({processInsId: '${processInsId}'}));
    });

</script>