<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="/views/share/chksCommon.jsp" %>

<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    考核信息
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
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
            if ('${boxReDto}') {
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
            assessmentCommonHandle.getChksBootstrapTableVoBase($("#assessmentTableList"), json, []);
        }({processInsId: '${processInsId}'}));
    });

</script>