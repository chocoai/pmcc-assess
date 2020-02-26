<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
</head>


<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <%@include file="/views/share/project/projectInfoSimple.jsp" %>
                    <%@include file="/views/share/project/projectPlanDetails.jsp" %>

                    <!-- 填写表单 start -->
                    <jsp:include page="/views/method/incomeIndex.jsp"></jsp:include>

                    <div class="col-md-12">
                        <div class="form-horizontal">
                            <div class="row form-group">
                                <div class="col-md-12">
                                <label class="col-sm-1 control-label">
                                    附件<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-11">
                                    <input id="report_file" name="report_file" type="file" multiple="false">
                                    <div id="_report_file"></div>
                                </div>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">

                        <div class="card-body">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                取消
                            </button>
                            <button id="btn_save" class="btn btn-warning" onclick="saveData()">
                                保存<i style="margin-left: 10px" class="fa fa-save"></i>
                            </button>
                            <button id="btn_submit" class="btn btn-success" onclick="submit()">
                                提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                            </button>
                        </div>
                    </div>

                    <%@include file="/views/share/form_log.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>

</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
<input type="hidden" id="mdIncomeJSON" value='${mdIncomeJSON}'>
<input type="hidden" id="incomeSelfSupportJSON" value='${incomeSelfSupportJSON}'>


<script type="application/javascript">
    function submit() {

        if (!incomeIndex.valid()) {
            return false;
        }
        var data = {};
        data.incomeInfo = incomeIndex.getData();
        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(data));
        }
        else {
            submitToServer(JSON.stringify(data));
        }
    }

    //保存
    function saveData() {
        var data = {};
        data.incomeInfo = incomeIndex.getData();
        $.ajax({
            url: "${pageContext.request.contextPath}/income/saveIncome",
            data: {formData: JSON.stringify(data)},
            success: function (result) {
                if (result.ret) {
                    notifySuccess("成功","保存成功");
                } else {
                    AlertError("保存失败,失败原因:"+result.errmsg);
                }
            }
        })
    }

</script>

</html>

