<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <script type="text/javascript" src="/pmcc-crm/js/crm-customer-utils.js"></script>
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
                    <form id="legal_person_Form" class="form-horizontal">

                        <div id="legal_person" class="panel-body">

                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        委托单位
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="csEntrustmentUnit" id="csEntrustmentUnit" placeholder="委托单位" class="form-control" required="required">
                                        <span class="input-group-btn">
                                          <button type="button" id="btn_select_customer" class="btn btn-primary">选择</button>
                                        </span>
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        法定代表
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="csLegalRepresentative" id="csLegalRepresentative" placeholder="法定代表人" class="form-control" required="required">
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        社会统一信用代码
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="csSociologyCode" id="csSociologyCode" placeholder="社会统一信用代码" class="form-control" required="required">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        经营范围
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="csScopeOperation" id="csScopeOperation" placeholder="经营范围" class="form-control" required="required">
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        单位地址
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" name="csAddress" id="csAddress" placeholder="单位地址" class="form-control" required="required">
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        单位性质
                                    </label>
                                    <div class="col-sm-3">
                                        <select class="form-control">
                                            <option>请选择</option>
                                            <c:forEach items="${InitiateAFFILIATEDMap}" var="mymap">
                                                <option value="${mymap.key}">${mymap.value}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div id="no_legal_person">
                                <div class="form-group">

                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            委托姓名
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" name="csName" id="csName" placeholder="委托姓名" class="form-control" required="required">
                                        </div>
                                    </div>

                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            身份证号
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" name="csIdcard" id="csIdcard" placeholder="身份证号" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            委托住址
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" name="csAddress" id="csAddress2" placeholder="委托住址" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            身份证附件
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="file" name="csEnclosureLocation" id="csEnclosureLocation" placeholder="上传附件" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                        </div>
                    </form>
                </div>

                <div class="x_foot">
                    <button class="btn btn-default" onclick="addContacts()">新增联系人</button>
                    <table class="table table-bordered" id="tb_List">
                        <!-- cerare document add ajax data-->
                    </table>
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
<div id="divBoxContacts" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">联系人</h4>
            </div>
            <form id="frmContacts" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            姓名
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="cName" id="cName" placeholder="姓名" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            部门
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="cDept" id="cDept" placeholder="部门" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            号码
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="cPhone" id="cPhone" placeholder="号码" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            邮箱
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="cEmail" id="cEmail" placeholder="邮箱" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            类型
                                        </label>
                                        <div class="col-sm-10">
                                            <select class="form-control" name="cType" id="cType" required="required">
                                                <option>请选择</option>
                                                <c:forEach items="${InitiateContactsMap}" var="mymap">
                                                    <option value="${mymap.key}">${mymap.value}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="saveContacts()">
                        保存
                    </button>
                </div>
            </form>
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
    //CRM
    $("#btn_select_customer").click(function () {
        crmCustomer.select({
            multi: false,//是否允许多选
            onSelected: function (nodes) {
                var csEntrustmentUnit = document.getElementById("csEntrustmentUnit");
                csEntrustmentUnit.value = nodes[0].name;
                var id = nodes[0].id ;
                loadContactsDicList(id);
                console.log(nodes);
            }
        });
    })
    //加载 委托人 联系人列表
    function loadContactsDicList(id) {
        var cols = [];
        cols.push({field: 'cName', title: '姓名'});
        cols.push({field: 'cDept', title: '部门'});
        cols.push({field: 'cEmail', title: '邮箱'});
        cols.push({field: 'cPhone', title: '部门'});

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning" href="javascript:removeData(' + row.id + ',\'tb_List\')">删除</a>';
                str += '</div>';
                return str;
            }
        });
        TableInit("tb_List", "${pageContext.request.contextPath}/projectInfo/getProjectContactsVos", cols,{
            id: id}, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    }

    //新建 委托 联系人
    function addContacts() {
        $('#divBoxContacts').modal("show");
    }
    //新增 委托 联系人
    function saveContacts() {
        //cDept,cPhone,cEmail,cType,cName
        var data = formParams("frmContacts");//收集参数
        data.cDept = $("#cDept").val();
        data.cName = $("#cName").val();
        data.cPhone = $("#cPhone").val();
        data.cEmail = $("#cEmail").val();
        data.cType = $("#cType option:selected").val();
        if ($("#frmContacts").valid()) {
            $.ajax({
                url: "${pageContext.request.contextPath}/projectInfo/Contacts/save",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#divBoxContacts').modal('hide');
                        loadContactsDicList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        }
    }
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