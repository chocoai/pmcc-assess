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
            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title">
                    <h2> 项目信息</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_project_info" class="form-horizontal">
                        <input type="hidden" id="projectId" name="id" value="${projectInfo.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">项目名称<span class="symbol required"></span></label>
                                <div class="col-sm-11">
                                    <input required placeholder="项目名称" id="projectName" name="projectName"
                                           value="${projectInfo.projectName}" class="form-control">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title">
                    <div class="clearfix" id="changeType">
                        委托人
                        法人<input type="radio" name="csType" value="1" checked="checked">
                        自然人<input type="radio" name="csType" value="0" >
                    </div>
                </div>
                <div class="x_content">
                    <form id="legal_person_Form" class="form-inline">

                        <div id="legal_person" class="panel-body">

                            <div class="form-inline">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            委托单位
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" name="csEntrustmentUnit" id="csEntrustmentUnit" placeholder="委托单位" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            法定代表人
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" name="csLegalRepresentative" id="csLegalRepresentative" placeholder="法定代表人" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            社会统一信用代码
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" name="csSociologyCode" id="csSociologyCode" placeholder="社会统一信用代码" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="form-inline">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            单位性质
                                        </label>
                                        <div class="col-sm-9">
                                            <select class="form-control">
                                                <option>请选择</option>
                                                <c:forEach items="${InitiateAFFILIATEDMap}" var="mymap">
                                                    <option value="${mymap.key}">${mymap.value}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            经营范围
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" name="csScopeOperation" id="csScopeOperation" placeholder="经营范围" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            地址
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" name="csAddress" id="csAddress" placeholder="地址" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div id="no_legal_person">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-3 control-label">
                                        委托姓名
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="text" name="csName" id="csName" placeholder="委托姓名" class="form-control" required="required">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-3 control-label">
                                        地址
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="text" name="cs_address" id="cs_address" placeholder="地址" class="form-control" required="required">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-3 control-label">
                                        身份证
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="text" name="csIdcard" id="csIdcard" placeholder="身份证" class="form-control" required="required">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-3 control-label">
                                        身份证附件
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="file" name="cs_enclosure_location" id="cs_IDCard_Location" placeholder="身份证" class="form-control" required="required">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title">
                    <h2> 委托人联系人</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">

                </div>
            </div>
            <div class="x_panel">
                <div class="x_title">
                    <h2> 占有人</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">

                </div>
            </div>
            <div class="x_panel">
                <div class="x_title">
                    <h2> 占有人联系人</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">

                </div>
            </div>
            <div class="x_panel">
                <div class="x_title">
                    <h2> 报告使用单位</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">

                </div>
            </div>
            <div class="x_panel">
                <div class="x_title">
                    <h2> 报告使用单位联系人</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">

                </div>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-5">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                取消
                            </button>

                            <button id="commit_btn" class="btn btn-success" onclick="projectApply();">
                                提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/views/share/main_footer.jsp" %>
</body>
</html>
<script>
    //选项框
    $(document).ready(function () {
        $("#no_legal_person").hide();
        $("#changeType input[type='radio'][name='csType']").change(function () {
            if ($(this).val()==1){
                $("#no_legal_person").hide();
                $("#legal_person").show();
            }
            if ($(this).val()==0){
                $("#no_legal_person").show();
                $("#legal_person").hide();
            }
        });
    });
</script>

<script type="text/javascript">
    $(function () {

    });


    //提交
    function projectApply() {
        Loading.progressShow();
        $.ajax({
            url: getContextPath() + "/projectInfo/projectApplySubmit",
            type: "post",
            data: formParams("frm_project_info"),
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    //保存完后其他动作
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    }
</script>