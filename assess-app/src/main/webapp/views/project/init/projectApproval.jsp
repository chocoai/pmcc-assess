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
            <%@include file="/views/share/project/projectInfo.jsp" %>
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
                                <label class="col-sm-1 control-label">项目名称</label>
                                <div class="col-sm-11">
                                    <label class="form-control">${projectInfo.projectName}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">大类<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                   <label class="form-control">${projectInfo.projectClassName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">委托目的<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.entrustPurposeName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">评估基准日<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control"><fmt:formatDate value="${projectInfo.completeDateStart}" /></label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">省<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.provinceName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">市<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.cityName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">县<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.districtName}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">紧急程度<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.urgencyName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">价值类型<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.projectTypeName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">执业部门<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.departmentName}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">项目经理<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.userAccountManagerName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">项目经理下级<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.userAccountMemberName}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">项目说明<span class="symbol required"></span></label>
                                <div class="col-sm-11">
                                    <label class="form-control">${projectInfo.remarks}</label>                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">附件</label>
                                <div class="col-sm-3">
                                    <div id="_attachmentProjectInfoId"></div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title">
                    <h2> 委托人</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form  class="form-horizontal">
                    <div id="legal_person" class="panel-body">

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    委托单位
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.consignorVo.csEntrustmentUnitName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    法定代表
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.consignorVo.csEntrustmentUnit}</label>                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    社会统一信用代码
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.consignorVo.csEntrustmentUnit}</label>                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    经营范围
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.consignorVo.csEntrustmentUnit}</label>                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    单位地址
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.consignorVo.csEntrustmentUnit}</label>                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    单位性质
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.consignorVo.csUnitPropertiesName}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    附件
                                </label>
                                <div class="col-sm-3">
                                    <div id="_csAttachmentProjectEnclosureId"></div>
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
                                    <label class="form-control">${projectInfo.consignorVo.csName}</label>                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    身份证号
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.consignorVo.csIdcard}</label>                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    委托住址
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${projectInfo.consignorVo.csAddress}</label>                                </div>
                                </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    附件
                                </label>
                                <div class="col-sm-3">
                                    <div id="_csAttachmentProjectEnclosureId2"></div>
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
                    <table class="table table-bordered" id="tb_ListA">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title">
                    <h2> 占有人</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form  class="form-horizontal">
                        <div id="legal_person1" class="panel-body">

                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        占有单位
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control">${projectInfo.possessorVo.pEntrustmentUnitName}</label>
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        占有单位法定代表
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control">${projectInfo.possessorVo.pLegalRepresentative}</label>
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        社会统一信用代码
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control">${projectInfo.possessorVo.pSociologyCode}</label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        经营范围
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control">${projectInfo.possessorVo.pScopeOperation}</label>
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        占有单位地址
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control">${projectInfo.possessorVo.pAddress}</label>
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        单位性质
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control">${projectInfo.possessorVo.pUnitPropertiesName}</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        附件
                                    </label>
                                    <div class="col-sm-3">
                                        <div id="_pAttachmentProjectEnclosureId"></div>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div id="no_legal_person1">
                            <div class="form-group">

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        占有人姓名
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control">${projectInfo.possessorVo.pName}</label>
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        身份证号
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control">${projectInfo.possessorVo.pIdcard}</label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        占有人住址
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control">${projectInfo.possessorVo.pAddress}</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        附件
                                    </label>
                                    <div class="col-sm-3">
                                        <div id="_pAttachmentProjectEnclosureId2"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title">
                    <h2> 占有人联系人</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <table class="table table-bordered" id="tb_ListB">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title">
                    <h2> 报告使用单位</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form class="form-horizontal" >
                        <div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        报告使用单位
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control">${projectInfo.unitInformationVo.uUseUnitName}</label>
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        法定代表人
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control">${projectInfo.unitInformationVo.uLegalRepresentative}</label>
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        证照号
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control">${projectInfo.unitInformationVo.uCertificateNumber}</label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        单位性质
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control">${projectInfo.unitInformationVo.uUnitPropertiesName}</label>
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        经营范围
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control">${projectInfo.unitInformationVo.uScopeOperation}</label>
                                    </div>
                                </div>

                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">
                                        地址
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="form-control">${projectInfo.unitInformationVo.uAddress}</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title">
                    <h2> 报告使用单位联系人</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <table class="table table-bordered" id="tb_ListC">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>

<script>
    $(function () {
        //---------
        FileUtils.getFileShows({
            target: "attachmentProjectInfoId",
            formData: {
                tableName: "tb_project_info",
                tableId: ${projectInfo.id}
            },
            deleteFlag: false
        })
        //---------
        //---------
        FileUtils.getFileShows({
            target: "pAttachmentProjectEnclosureId",
            formData: {
                tableName: "tb_initiate_possessor",
                tableId: ${projectInfo.possessorVo.id}
            },
            deleteFlag: false
        })
        //---------
        //---------
        FileUtils.getFileShows({
            target: "pAttachmentProjectEnclosureId2",
            formData: {
                tableName: "tb_initiate_possessor",
                tableId: ${projectInfo.possessorVo.id}
            },
            deleteFlag: false
        })
        //---------
        //---------
        FileUtils.getFileShows({
            target: "csAttachmentProjectEnclosureId",
            formData: {
                tableName: "tb_initiate_consignor",
                tableId: ${projectInfo.consignorVo.id}
            },
            deleteFlag: false
        })
        //---------

        //---------
        FileUtils.getFileShows({
            target: "csAttachmentProjectEnclosureId2",
            formData: {
                tableName: "tb_initiate_consignor",
                tableId: ${projectInfo.consignorVo.id}
            },
            deleteFlag: false
        })
        //---------
    });
    var flags = new Array();
    flags[0] = 1,flags[1] = 2,flags[2] = 3;
    function loadInitContactsList(id,tb_List) {
        var cols = [];
        cols.push({field: 'cName', title: '姓名'});
        cols.push({field: 'cDept', title: '部门'});
        cols.push({field: 'cEmail', title: '邮箱'});
        cols.push({field: 'cPhone', title: '部门'});

        TableInit(""+tb_List, "${pageContext.request.contextPath}/projectInfo/getProjectContactsVos", cols,{
            crmId: id,flag:flags[0]}, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    }

    function loadInitContactsListC(id,tb_List,flag) {
        var cols = [];
        cols.push({field: 'cName', title: '姓名'});
        cols.push({field: 'cDept', title: '部门'});
        cols.push({field: 'cEmail', title: '邮箱'});
        cols.push({field: 'cPhone', title: '部门'});

        TableInit(""+tb_List, "${pageContext.request.contextPath}/projectInfo/getProjectContactsVosX", cols,{
            pid: id,flag:flag}, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    }
    //选项框
    $(document).ready(function () {
        $("#no_legal_person").hide();
        var changeType = ${projectInfo.consignorVo.csType};
        console.log(changeType);
        if (changeType==1){
            $("#no_legal_person").hide();
            $("#legal_person").show();
            try {
                var val = ${projectInfo.consignorVo.csEntrustmentUnit}+"";
                var id = ${projectInfo.consignorVo.id}+"";
                if (val!='' && val != null){
                    // loadInitContactsList(val,"tb_ListA");
                }
                if (id!=null && id!=''){
                    loadInitContactsListC(id,"tb_ListA",flags[0]);
                }
            }catch (e){
                console.info(e);
            }
        }
        if (changeType==0){
            $("#no_legal_person").show();
            $("#legal_person").hide();
            try {
                var val = ${projectInfo.consignorVo.id}+"";
                if(val!='' && val!=null){
                    loadInitContactsListC(val,"tb_ListA",flags[0]);
                }
            }catch (e){
                console.info(e);
            }
        }

        $("#no_legal_person1").hide();
        var changeTypeA = ${projectInfo.possessorVo.pType}
        if (changeTypeA==1){
            $("#no_legal_person1").hide();
            $("#legal_person1").show();
            try {
                var val = ${projectInfo.possessorVo.pEntrustmentUnit}+"";
                if (val!='' && val!=null){
                    // loadInitContactsList(val,"tb_ListB");
                }
                var id = ${projectInfo.possessorVo.id}+"";
                if (id!=null && id!=''){
                    loadInitContactsListC(id,"tb_ListB",flags[1]);
                }
            }catch (e){
                console.info(e);
            }
        }
        if (changeTypeA==0){
            $("#no_legal_person1").show();
            $("#legal_person1").hide();
            try {
                var val = ${projectInfo.possessorVo.id}+"";
                if (val!='' && val!=null){
                    loadInitContactsListC(val,"tb_ListB",flags[1]);
                }
            }catch (e){
                console.info(e);
            }
        }

        <%--loadInitContactsList(${projectInfo.unitInformationVo.uUseUnit},"tb_ListC");--%>
        loadInitContactsListC(${projectInfo.unitInformationVo.id},"tb_ListC",flags[2]);
    });
</script>

<script type="application/javascript">

    function saveform() {
        if (!$("#frm_approval").valid()) {
            return false;
        }
        var data = formParams("frm_approval");
        var dataManager = formParams("frm_set_project_manager");
        data = $.extend({}, dataManager, data);
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectInfo/projectApprovalSubmit",
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
