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
                <div class="x_content">
                    <div class="form-horizontal">
                        <table class="table">
                            <thead>
                            <tr>
                                <th class="hidden-xs">估价对象</th>
                                <th class="hidden-xs">假定未设立法定优先受偿权总价(元)</th>
                                <th class="hidden-xs">已抵押担保的债权数额总价(元)</th>
                                <th class="hidden-xs">拖欠的建设工程价款总价(元)</th>
                                <th class="hidden-xs">其它法定优先受偿款总价(元)</th>
                                <th class="hidden-xs">估价师知悉的法定优先受偿款总价(元)</th>
                                <th class="hidden-xs">抵押价值总价(元)</th>
                            </tr>
                            </thead>
                            <tbody id="tbody_data_section">

                            </tbody>
                        </table>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                    附件
                                </label>
                                <div class="col-md-11 col-sm-11 col-xs-12">
                                    <div id="_apply_file">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    $(function () {
        getItemHtml();
        FileUtils.getFileShows({
            target: "apply_file",
            formData: {
                tableName: AssessDBKey.SchemeReimbursement,
                tableId: "${master.id}",
                fieldsName: "apply"
            },
            deleteFlag: false
        })
    })

    function saveform() {
        saveApprovalform("");
    }

    function getItemHtml() {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeReimbursement/getSchemeReimbursementList",
            data: {
                masterId: "${master.id}"
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                $("#tbody_data_section").empty();
                if (result.ret) {
                    var html = "";
                    $.each(result.data, function (i, item) {
                        html += "<tr>";
                        html += "<td class='hidden-xs'>";
                        html += item.name;
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += Number(item.notSetUpTotalPrice).toFixed(2);
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += Number(item.mortgagedTotalPrice).toFixed(2);
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += Number(item.owedTotalPrice).toFixed(2);
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += Number(item.otherTotalPrice).toFixed(2);
                        html += "</td>";

                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += Number(item.knowTotalPrice).toFixed(2);
                        html += "</td>";

                        html += "<td class='hidden-xs'>";
                        html += Number(item.mortgageTotalPrice).toFixed(2);
                        html += "</td>";
                        html += "</tr>";
                    });
                    $("#tbody_data_section").append(html);
                }
            }
        });
    }
</script>
</body>
</html>

