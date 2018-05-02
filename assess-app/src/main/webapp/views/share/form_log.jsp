<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2017/7/31
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title">
        <h2> 审批日志</h2>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <table id="tb_log" class="table table-bordered" ></table>
    </div>
</div>


<script type="application/javascript">
    $(function () {
        loadLogTable();
    })
    function loadLogTable() {
        var cols = [];
        cols.push({field: 'activityName', title: '审批节点', width: '10%'});
        cols.push({field: 'createrName', title: '审批人', width: '5%'});
        cols.push({
            field: 'created', title: '审批时间', width: '14%', formatter: function (value, row, index) {
                return formatDate(value, true);
            }
        });
        cols.push({field: 'conclusion', title: '结论', width: '5%'});
        cols.push({field: 'opinions', title: '审批意见', width: '35%'});
        cols.push({
            field: 'attachmentVos', title: '审批附件', width: '10%', formatter: function (value, row, index) {
                if(value) {
                    var str = "";
                    $.each(value, function (i, j) {
                        str += "<a class='fileupload-preview'>" + j.fileName + "</a>";
                        str += "<a><i class='fa fa-download' onclick='downAttachments(" + j.id + ")' style='margin-left: 15px;font-size: 15px;'></i><br/></a>";
                    });
                    return str;
                }
            }
        });
        var paramData = {};
        paramData["showColumns"] = false; //不显示选择显示列
        paramData["showRefresh"] = false;//不显示刷新按钮
        paramData["search"] = false;//不显示查询按钮
        TableInit("tb_log", "${pageContext.request.contextPath}/home/getApprovalLog", cols,
            {processInsId:"${processInsId}"}, paramData);

    }
</script>