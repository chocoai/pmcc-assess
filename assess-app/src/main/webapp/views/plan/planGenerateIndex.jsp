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
                <div class="title_left">
                    <h3>
                        <i class="fa ${boxprocessIcon}" style="margin-right: 20px;"></i>
                        ${boxCnName}
                        <small>
                            <label>${boxdescription}</label>
                            <label class="label label-success"><i class="fa fa-flag"
                                                                  style="margin-right: 8px"></i>出具报告</label>
                            <label class="label label-primary"><i class="fa fa-user"
                                                                  style="margin-right: 8px"></i>${currUserName}</label>
                        </small>
                    </h3>
                </div>
            </div>
            <div class="clearfix"></div>
            <%@include file="/views/share/project/projectInfo.jsp" %>
            <!--填写表单-->
            <form id="frm_content" class="form-horizontal">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>报告选择</h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <div class="panel-body">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        报告类型<span class="symbol required"></span>
                                    </label>
                                    <div class="col-sm-4">
                                        <c:forEach items="${reportTypeList}" var="item">
                                        <span class="checkbox-inline">
                                            <input type="checkbox" required id="reportType_${item.id}" name="reportType"
                                                   onclick="computeNumber();" value="${item.id}">
                                        <label for="reportType_${item.id}">${item.name}</label></span>
                                        </c:forEach>
                                    </div>
                                    <label id="lbl_report_count" class="col-sm-1 control-label" style="display: none;">
                                        报告份数：<span style="color: red;font-size: large;">0</span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>
                                    <small>权证信息</small>
                                </h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <table class="table table-hover">
                                    <thead>
                                    <tr>
                                        <td><input type="checkbox" name="checkboxAll" onclick="checkBoxAllClick(this);">
                                        </td>
                                        <th>序号</th>
                                        <th>名称</th>
                                        <th>证载面积</th>
                                        <th>评估面积</th>
                                        <th>评估单价</th>
                                        <th>已出面积</th>
                                        <th>报告面积</th>
                                        <%--<th>评估总价</th>--%>
                                        <%--<th>报告总价</th>--%>
                                        <th>报告附件</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${reportRecordList}" var="item" varStatus="i">
                                        <tr>
                                            <td><input type="checkbox" name="checkboxItem"
                                                       onclick="checkBoxItemClick(this);"></td>
                                            <th>${i.index+1}</th>
                                            <td>${item.name}</td>
                                            <td>${item.floorArea}</td>
                                            <td>${item.assessArea}</td>
                                            <td>${item.assessUnitPrice}</td>
                                            <td>${item.alreadyOutArea}</td>
                                            <td>
                                                <div class="x-valid">
                                                    <input type="text" class="form-control" name="reportArea"
                                                           data-rule-digits="true"
                                                           style="width: 120px;height: 30px;"></div>
                                            </td>
                                            <%--<td>${item.assessTotalPrince}</td>--%>
                                            <%--<td>${item.reportTotalPrice}</td>--%>
                                            <td></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
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
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button id="btn_generate" class="btn btn-success" onclick="generateReport();">
                            生成报告<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                        <button id="commit_btn" class="btn btn-success" onclick="commitApply();">
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
</html>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    $(function () {


    })

    //checkboxAll点击
    function checkBoxAllClick(_this) {
        $("#frm_content").find("[name=checkboxItem]").each(function () {
            $(this).prop("checked", $(_this).prop("checked"));
            checkBoxItemClick(this);
        })
    }

    //checkbox点击
    function checkBoxItemClick(_this) {
        var isChecked = $(_this).prop("checked");
        var tr = $(_this).closest("tr");
        computeNumber();
        if (isChecked) {
            tr.find("[name=reportArea]").attr("required", "true");
        } else {
            tr.find("[name=reportArea]").removeAttr("required");
        }
    }

    //计算份数
    function computeNumber() {
        var reportTypeLength = $("#frm_content").find("[name=reportType]:checked").length;
        var checkboxItemLength = $("#frm_content").find("[name=checkboxItem]:checked").length;
        if (reportTypeLength > 0 && checkboxItemLength > 0) {
            $("#lbl_report_count").find("span").text(reportTypeLength*checkboxItemLength);
            $("#lbl_report_count").show();
        }else{
            $("#lbl_report_count").find("span").text(0);
            $("#lbl_report_count").hide();
        }
    }

    //生成报告
    function generateReport() {

    }

    //提交
    function commitApply() {
        if (!$("#frm_content").valid()) {
            return false;
        }
    }
</script>