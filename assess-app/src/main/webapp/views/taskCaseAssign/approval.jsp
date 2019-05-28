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
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>案列任务分派申请</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="master" class="form-horizontal">
                        <input type="hidden" name="id" value="${master.id}">
                        <div class="form-group ">
                            <div>
                                <label class="col-sm-1 control-label">
                                    省
                                </label>
                                <div class="col-sm-2">
                                    <label class="form-control">${master.provinceName}</label>
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">
                                    市
                                </label>
                                <div class="col-sm-2">
                                    <label class="form-control">${master.cityName}</label>
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">
                                    区
                                </label>
                                <div class="col-sm-2">
                                    <label class="form-control">${master.districtName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-md-1 col-sm-1 col-xs-12 control-label">
                                    认领人<span class="symbol required"></span>
                                </label>
                                <div class="col-md-2 col-sm-2 col-xs-12 ">
                                    <label class="form-control">${master.executorName}
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="lpInfo">

                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                    备注
                                </label>
                                <div class="col-md-11 col-sm-11 col-xs-12 ">
                                    <label class="form-control">${master.remark}
                                    </label>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <c:if test="${CurrentStep==1}">
                <div class="x_panel">
                    <div class="x_content">
                        <div style="text-align: center;">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                取消
                            </button>
                            <button id="btn_submit" class="btn btn-success" onclick="saveform();">
                                提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </c:if>

                <div id="showApproval">
                <%@include file="/views/share/form_approval.jsp" %>
                </div>

            <%@include file="/views/share/form_log.jsp" %>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>


</body>
<%@include file="/views/share/main_footer.jsp" %>
<script src="${pageContext.request.contextPath}/js/autocomplete/lpmc.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.css">
<script src='${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.js'></script>
<script type="application/javascript">
    $(function () {
        if ("${master}") {
            writeLpData(${master.lpInfo});
        }
        if("${CurrentStep}"=="1"){
            $("#showApproval").hide();
        }
    });


    //保存数据
    function saveform() {
        var data = formApproval.getFormData();
        data.executor = '${master.executor}';

        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/taskCaseAssign/approvalSubmit",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    Alert(result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        })
    }


    function writeLpData(json) {
        $(".lpInfo").empty();
        var jsonarray = eval(json);
        $.each(jsonarray, function (i, n) {
            var html = "<div class='form-group' >";
            html += "<div class='x-valid'>";

            html += "<label class=' col-md-1 col-sm-1 col-xs-12 control-label'>" + "楼盘名称" + "</label>";
            html += "<input type='hidden' name='lpbh' value='" + n["lpbh"] + "'>";
            html += "<input type='hidden' name='lpName' value='" + n["name"] + "'>";
            html += "<div class=' col-md-2 col-sm-2 col-xs-12 '>";
            html += "<label class='form-control'>" + n["name"];
            html += "</label>";
            html += "</div>";

            html += "<c:if test="${CurrentStep==1}">";
            html += "<div class='btn btn-xs btn-success' onclick='enterData(this)'>";
            html += "<i class='fa fa-arrow-right'></i>";
            html += "</div>";
            html += "</c:if>";

            html += "<c:if test="${CurrentStep==2}">";
            html += "<div class='btn btn-xs btn-success' onclick='checkDetail(this)'>";
            html += "<i class='fa fa-arrow-right'></i>";
            html += "</div>";
            html += "</c:if>";

            html += "</div>";
            html += "</div>";
            $(".lpInfo").append(html);
        })
    }

    function enterData(item) {
        var assignId = "${master.id}";
        var lpName = $(item).parent().parent().find("input[name=lpName]").val();
        var href = "${pageContext.request.contextPath}/taskCaseAssign/basicApplyIndex";
        href += "?lpName=" + lpName;
        href += "&assignId=" + assignId;
        window.open(href, "");
    }


</script>


</html>
