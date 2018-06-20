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
            <%@include file="/views/share/project/projectCsrInfo.jsp" %>
            <!--填写表单-->
            <form id="frm_content" class="form-horizontal">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>客户信息</h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <td>
                                    <input type="checkbox" name="checkboxAll" onclick="checkBoxAllClick(this);">
                                </td>
                                <th>序号</th>
                                <th>一级分行</th>
                                <th>二级分行</th>
                                <th>姓名</th>
                                <th>身份证号</th>
                                <th>婚姻状态</th>
                                <th>报告附件</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${borrowerList}" var="item" varStatus="i">
                                <tr>
                                    <td>
                                        <input type="hidden" name="id" value="${item.id}">
                                        <input type="hidden" name="borrowerId" value="${item.borrowerId}">
                                        <input type="hidden" name="csrProjectId" value="${item.csrProjectId}">
                                        <input type="checkbox" name="checkboxItem"
                                               onclick="checkBoxItemClick(this);"></td>
                                    <th>${i.index+1}</th>
                                    <td>${item.firstLevelBranch}</td>
                                    <td>${item.secondLevelBranch}</td>
                                    <td>${item.name}</td>
                                    <td>${item.idNumber}</td>
                                    <td>${item.maritalStatus}</td>
                                    <td>${item.attachmentHtml}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
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
                        <button id="btn_generate" class="btn btn-primary" onclick="generateReport();">
                            生成报告<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
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
        })
    }

    //生成报告
    function generateReport() {
        var borrowerIds = '';
        var csrProjectId = $("#frm_content").find("[name=csrProjectId]:eq(0)").val();
        $("#frm_content").find("[name=checkboxItem]").each(function () {
            var id = $(this).closest('tr').find('[name="id"]').val();
            borrowerIds += id + ',';
        })
        $.ajax({
            url: '${pageContext.request.contextPath}/csrProjectInfo/generateReport',
            type: 'post',
            dataType: 'json',
            data: {
                csrProjectId: csrProjectId,
                borrowerIds: borrowerIds.replace(/,$/, '')
            },
            success: function (result) {
                if (result.ret) {
                    Alert('生成成功', 1, null, function () {
                        window.location.href = window.location.href;
                    });
                }
            }
        })
    }

    //提交
    function commitApply() {
        if (!$("#frm_content").valid()) {
            return false;
        }
    }
</script>