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

            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2> 设置项目经理</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_approval" class="form-horizontal">
                        <div class="form-group" style="display: none">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                审批结论
                            </label>
                            <div class="x-valid">
                                <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                    <label class="radio-inline">
                                        <input type="radio" value="Approval" required name="conclusion"
                                               checked="checked"
                                               class="grey"
                                               onclick="chkRadioClick()">
                                        同意
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                项目经理<span class="symbol required"></span>
                            </label>
                            <div class="x-valid">
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <input type="hidden" id="appointUserAccount" name="appointUserAccount">
                                    <input type="text" required
                                           placeholder="项目经理"
                                           id="appointUserAccountName" name="appointUserAccountName"
                                           class="form-control" readonly="readonly"
                                           maxlength="200" onclick="selectUserAccountManager()">
                                </div>
                            </div>
                            <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 " id="div_bisNext">
                                <label class="checkbox-inline">
                                    <input type="checkbox" id="chk_bisNext"><label for="chk_bisNext">继续分派</label>
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                说明
                            </label>
                            <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                                <div class="x-valid">
                                    <textarea placeholder="说明" id="opinionsTemp" name="opinionsTemp"
                                              class="form-control"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <%@include file="/views/share/ApprovalVariable.jsp" %>
                        </div>

                    </form>

                </div>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div class="form-group">
                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4   col-xs-offset-5 col-sm-offset-5 col-md-offset-5 col-lg-offset-5">
                            <button class="btn btn-default" onclick="window.close()">
                                取消
                            </button>
                            <button id="btn_submit" class="btn btn-success" onclick="saveform()">
                                提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>


<script type="application/javascript">
    $(function () {
        $("#frm_approval").validate();
    })
    // 项目经理
    function selectUserAccountManager() {
        erpEmployee.select({
            onSelected: function (data) {
                $("#appointUserAccountName").val(data.name);
                $("#appointUserAccount").val(data.account);
            }
        });
    }
    function saveform() {
        if (!$("#frm_approval").valid()) {
            return false;
        }
        var data = formParams("frm_approval");
        ;
        data.bisNext = $("#chk_bisNext").is(':checked') ? "1" : "0";
        data.appointUserAccount = $("#appointUserAccount").val();
        data.opinions = $("#opinionsTemp").val();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectInfo/projectApprovalAssignSubmit",
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
                    Alert("保存数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        })
    }
</script>
</html>
