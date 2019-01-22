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
                    <input type="hidden" name="id" value="${master.id}">
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
            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>

                        <button id="btn_submit" class="btn btn-success" onclick="submit();">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
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
        getTaxAllocation();
    });

    function submit() {
        if ($("#master").length > 0) {
            $("#master").validate();
            if (!$("#master").valid()) {
                return false;
            }
        }
        var formData = formParams("master");
        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(formData));
        }
        else {
            submitToServer(JSON.stringify(formData));
        }
    }

    function getTaxAllocation() {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectTaskLiquidationAnalysis/getTaxAllocation",
            data: {
                projectPlanDetailsId: "${projectPlanDetails.id}",
                judgeObjectId: "${projectPlanDetails.judgeObjectId}",
                mainId: "${master.id}"
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                var html = "";
                var total = 0;
                $.each(result.data, function (i, item) {
                    total += item.money;
                    html += "<tr>";
                    html += "<td class='hidden-xs'>";
                    html += item.typeName;
                    html += "</td>";
                    html += "<td class='hidden-xs'>";
                    html += item.rate;
                    html += "</td>";
                    html += "<td class='hidden-xs'>";
                    html += "<input type='text'  name='remark_" + item.type + "' value='" + item.remark + "' class='form-control'>";
                    html += "</td>";
                    html += "<td class='hidden-xs'>";
                    html += "<div class='x-valid'>";
                    html += "<input type='text'  name='price_" + item.type + "' value='" + item.money + "'  class='form-control' data-rule-number='true'>";
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
                if ('${master.total}') {
                    html += "<input type='text'  name='total' class='form-control' value='${master.total}' data-rule-number='true'>";
                } else {
                    html += "<input type='text'  name='total' class='form-control' value='" + total + "' data-rule-number='true'>";
                }
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

</html>

