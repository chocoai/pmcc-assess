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
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>${judgeObjectName}</h3>
                    <div class="clearfix"></div>
                </div>
                <form class="form-horizontal" id="master">
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="hidden-xs">物业类型</th>
                            <th class="hidden-xs">税率</th>
                            <th class="hidden-xs">备注</th>
                            <th class="hidden-xs">商业</th>
                        </tr>
                        </thead>
                        <tbody id="tbody_data_section">
                        <tr>
                            <td class="hidden-xs">面积(平方米)</td>
                            <td class="hidden-xs">/</td>
                            <td class="hidden-xs"></td>
                            <td class="hidden-xs">
                                ${judgeObject.evaluationArea}
                            </td>
                        </tr>
                        <tr>
                            <td class="hidden-xs">评估价(元)</td>
                            <td class="hidden-xs">/</td>
                            <td class="hidden-xs"></td>
                            <td class="hidden-xs">
                                ${schemeSurePrice.price}
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    function saveform() {
        saveApprovalform("");
    }

    $(function () {
        getTaxAllocation();
    });

    function getTaxAllocation() {

        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectTaskLiquidationAnalysis/getTaxAllocation",
            data: {
                judgeObjectId: "${projectPlanDetails.judgeObjectId}",
                mainId: "${master.id}"
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                var html = "";
                $.each(result.data, function (i, item) {
                    html += "<tr>";
                    html += "<td class='hidden-xs'>";
                    html += item.typeName;
                    html += "</td>";
                    html += "<td class='hidden-xs'>";
                    html += item.rate;
                    html += "</td>";
                    html += "<td class='hidden-xs'>";
                    html += item.remark;
                    html += "</td>";
                    html += "<td class='hidden-xs'>";
                    html += "<div class='x-valid'>";
                    html += item.money;
                    html += "</div>";
                    html += "</td>";
                    html += "</tr>";
                });
                html += "<tr>";
                html += "<td class='hidden-xs' colspan='3' style='text-align:center;'>";
                html += "合计费用";
                html += "</td>";
                html += "<td class='hidden-xs'>";
                html += "<div class='x-valid'>";
                html += '${master.total}';
                html += "</div>";
                html += "</td>";
                html += "</tr>";
                $("#tbody_data_section").append(html);

            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }
</script>
</body>
</html>

