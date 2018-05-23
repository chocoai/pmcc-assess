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
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>


            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title">
                    <h2>${projectPlanDetails.projectPhaseName}</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_assess" class="form-horizontal">

                        <div class="form-group">

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">评估人员<span class="symbol required"></span></label>
                                <div class="col-sm-2">
                                    <select id="evaluator" name="evaluator" class="form-control" required="required">
                                        <option selected="selected"
                                                value="${thisUserInfo.id}">${thisUserInfo.userName}</option>
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">核对日期<span class="symbol required"></span></label>
                                <div class="col-sm-2">
                                    <input required="required" placeholder="核对日期" id="checkDate" name="checkDate"
                                           data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate" readonly="readonly"
                                           value="<fmt:formatDate value='${surveyAssetInventory.checkDate}' pattern='yyyy-MM-dd'/>">
                                </div>
                            </div>

                            <div class="col-sm-3">
                                <button type="button" class="btn btn-success" onclick="addData()"
                                        data-toggle="modal" href="#divBox"> 新增</button>
                            </div>

                        </div>

                    </form>


                    <div class="x_title">
                        <h2><small><i class="fa fa-bars"></i>清查内容</small></h2>
                        <div class="clearfix"></div>
                    </div>
                    <table class="table table-bordered" id="tb_List">
                        <!-- cerare document add ajax data-->
                    </table>

                    <form id="frm_checkbox" class="form-horizontal">
                        <div class="form-group">

                            <label class="col-sm-2 control-label" data-label="&lt;label&gt;">
                                他项权利与实际情况是否一致
                            </label>

                            <label class="col-sm-0.5 control-label">
                                <input id="agreementBox" type="checkbox" value="checkbox"
                                       style="vertical-align:middle;"/>
                                <span style="vertical-align:middle;">一致</span>
                            </label>
                        </div>

                    </form>

                    <form id="frm_survey" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                他权登记人
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-3">
                                    <input type="text" placeholder="他权登记人"
                                           id="otherRightsRegistrar" name="otherRightsRegistrar" class="form-control"
                                           value="${surveyAssetOtherTemplate.otherRightsRegistrar}">
                                </div>
                            </div>

                            <label class="col-sm-1 control-label">
                                实际行权人
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-3">
                                    <input type="text" placeholder="实际行权人"
                                           id="rightHander" name="rightHander" class="form-control"
                                           value="${surveyAssetOtherTemplate.rightHander}">
                                </div>
                            </div>

                            <label class="col-sm-1 control-label">
                                登记面积
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-3">
                                    <input type="text" placeholder="登记面积"
                                           id="registerArea" name="registerArea" class="form-control"
                                           value="${surveyAssetOtherTemplate.registerArea}">
                                </div>
                            </div>

                        </div>

                        <div class="form-group">

                            <label class="col-sm-1 control-label">
                                实际面积
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-3">
                                    <input type="text" placeholder="实际面积"
                                           id="actualArea" name="actualArea" class="form-control"
                                           value="${surveyAssetOtherTemplate.actualArea}">
                                </div>
                            </div>

                            <label class="col-sm-1 control-label">
                                登记用途
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-3">
                                    <input type="text" placeholder="登记用途"
                                           id="registerPurpose" name="registerPurpose" class="form-control"
                                           value="${surveyAssetOtherTemplate.registerPurpose}">
                                </div>
                            </div>

                            <label class="col-sm-1 control-label">
                                实际用途
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-3">
                                    <input type="text" placeholder="实际用途"
                                           id="actualPurpose" name="actualPurpose" class="form-control"
                                           value="${surveyAssetOtherTemplate.actualPurpose}">
                                </div>
                            </div>

                        </div>

                        <div class="form-group">

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">登记日期<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input required="required" placeholder="登记日期" id="registerDate" name="registerDate"
                                           data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate" readonly="readonly"
                                           value="<fmt:formatDate value='${surveyAssetOtherTemplate.registerDate}' pattern='yyyy-MM-dd'/>">
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">到期日<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input required="required" placeholder="到期日" id="dueDate" name="dueDate"
                                           data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate" readonly="readonly"
                                           value="<fmt:formatDate value='${surveyAssetOtherTemplate.dueDate}' pattern='yyyy-MM-dd'/>">
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">实际行权人行权日期<span
                                        class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input required="required" placeholder="实际行权人行权日期" id="exerciseDate"
                                           name="exerciseDate" data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate" readonly="readonly"
                                           value="<fmt:formatDate value='${surveyAssetOtherTemplate.exerciseDate}' pattern='yyyy-MM-dd'/>">
                                </div>
                            </div>

                        </div>

                        <div class="form-group">

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">预计到期日<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input required="required" placeholder="预计到期日" id="predictDueDate"
                                           name="predictDueDate" data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate" readonly="readonly"
                                           value="<fmt:formatDate value='${surveyAssetOtherTemplate.predictDueDate}' pattern='yyyy-MM-dd'/>">
                                </div>
                            </div>

                        </div>
                    </form>

                </div>
            </div>


            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title">
                    <h2>${projectPlanDetails.projectPhaseName}成果提交</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_task" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                实际工时
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-3">
                                    <input type="text" required
                                           placeholder="实际工时" data-rule-number='true'
                                           id="actualHours" name="actualHours" class="form-control" maxlength="3"
                                           value="${projectPlanDetails.actualHours}">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                成果描述
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-11">
                                        <textarea required placeholder="成果描述" id="taskRemarks" name="taskRemarks"
                                                  class="form-control">${projectPlanDetails.taskRemarks}</textarea>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                成果文件
                            </label>
                            <div class="col-sm-11">
                                <input id="apply_file" name="apply_file" type="file" multiple="false">
                                <div id="_apply_file">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
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

<div id="divBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">字典管理</h4></button>
            </div>
            <form id="frm" class="form-horizontal">
                <input type="hidden" id="id" name="id" value="0">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">

                                <div class="form-group disform">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            清查内容
                                        </label>
                                        <div class="col-sm-9">
                                            <select class="form-control" required id="inventoryContent"
                                                    name="inventoryContent">
                                                <option value="">-请选择-</option>
                                                <c:forEach var="item" items="${checkContentList}">
                                                    <option value="${item.id}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group disform">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            是否一致
                                        </label>
                                        <label class="col-sm-1 control-label">
                                            <input id="areConsistent" name="areConsistent" type="checkbox" value="一致"
                                                   style="vertical-align:middle;"/>
                                            <span style="vertical-align:middle;">一致</span>
                                        </label>
                                    </div>
                                </div>

                                <div id="notReal">
                                    <div class="form-group disform">
                                        <div class="x-valid">
                                            <label class="col-sm-3 control-label">
                                                登记面积
                                            </label>
                                            <div class="col-sm-9">
                                                <input type="text" data-rule-maxlength="50" placeholder="登记面积"
                                                       id="registrationAddress" name="registrationAddress"
                                                       class="form-control">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group disform">
                                        <div class="x-valid">
                                            <label class="col-sm-3 control-label">
                                                实际面积
                                            </label>
                                            <div class="col-sm-9">
                                                <input type="text" data-rule-maxlength="50" placeholder="实际面积"
                                                       id="actualAddress" name="actualAddress" class="form-control">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group disform">
                                        <div class="x-valid">
                                            <label class="col-sm-3 control-label">
                                                差异原因
                                            </label>
                                            <div class="col-sm-9">
                                                <input type="text" data-rule-maxlength="50" placeholder="差异原因"
                                                       id="differenceReason" name="differenceReason"
                                                       class="form-control">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group disform">
                                        <div class="x-valid">
                                            <label class="col-sm-3 control-label">
                                                证明文件
                                            </label>
                                            <div class="col-sm-9">
                                                <input type="text" data-rule-maxlength="50" placeholder="证明文件"
                                                       id="credential" name="credential" class="form-control">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group disform">
                                        <label class="col-sm-3 control-label">
                                            证明文件附件
                                        </label>
                                        <div class="col-sm-9">
                                            <input id="credentialAccessory" name="credentialAccessory" type="file"
                                                   multiple="false">
                                            <div id="_credentialAccessory">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group disform">
                                        <div class="x-valid">
                                            <label class="col-sm-3 control-label">
                                                证明人
                                            </label>
                                            <div class="col-sm-9">
                                                <input type="text" data-rule-maxlength="50" placeholder="证明人"
                                                       id="voucher" name="voucher" class="form-control">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group disform">
                                        <div class="x-valid">
                                            <label class="col-sm-3 control-label">调查时间<span
                                                    class="symbol required"></span></label>
                                            <div class="col-sm-9">
                                                <input placeholder="调查时间" id="surveyTime" name="surveyTime"
                                                       data-date-format="yyyy-mm-dd"
                                                       class="form-control date-picker dbdate" readonly="readonly">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group noshow">
                                        <div class="x-valid">
                                            <label class="col-sm-3 control-label">

                                            </label>
                                            <div class="col-sm-9">
                                                <input type="text" data-rule-maxlength="50" placeholder="id"
                                                       id="pid" name="pid" class="form-control"
                                                       value="${empty surveyAssetInventory.id?0:surveyAssetInventory.id}">
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
                        <button id="saveFrm" type="button" class="btn btn-primary" onclick="saveData()">
                            保存
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="/views/share/main_footer.jsp" %>
<%--<script type="text/javascript" src="/pmcc-crm/js/crm-customer-utils.js"></script>--%>
<script type="application/javascript">

    $(function () {
        $('#surveyAssetInventory').css('display', 'none');
        loadDataDicList();

        $("#btn_select_customer").click(function () {
            crmCustomer.select({
                multi: false,//是否允许多选
                onSelected: function (nodes) {
                    console.log(nodes);
                }
            });
        })

        $("#frm_task").validate();

        FileUtils.uploadFiles({
            target: "apply_file",
            disabledTarget: "btn_submit",
            formData: {
                tableName: "tb_project_plan_details",
                tableId: ${projectPlanDetails.id},
                fieldsName: "apply",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        });

        FileUtils.getFileShows({
            target: "apply_file",
            formData: {
                tableName: "tb_project_plan_details",
                tableId: ${projectPlanDetails.id},
                fieldsName: "apply",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        })


        FileUtils.uploadFiles({
            target: "credentialAccessory",
            disabledTarget: "btn_submit",
            formData: {
                tableName: "tb_survey_asset_template",
                tableId: 0,
                fieldsName: "credentialAccessory",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        });

        FileUtils.getFileShows({
            target: "credentialAccessory",
            formData: {
                tableName: "tb_survey_asset_template",
                tableId: ${empty surveyAssetTemplateVo?0:surveyAssetTemplateVo.credentialAccessory},
                fieldsName: "credentialAccessory",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        })
    });

    var json = "";
    function params() {
        var data = {};
        var surveyAssetInventoryDto = formParams("frm_assess");//评估人员 核对时间
        var surveyAssetOtherTemplateDto = formParams("frm_survey"); //他项权利是否一致
        data.surveyAssetInventoryDto = surveyAssetInventoryDto;
        data.surveyAssetOtherTemplateDto = surveyAssetOtherTemplateDto;
        //合并json
        console.info(data);
        json = JSON.stringify(data);
        console.info(json);
    }

    function submit() {
        if (!$("#frm_task").valid()) {
            return false;
        }

        if ("${processInsId}" != "0") {
            params();
            submitEditToServer(json, $("#taskRemarks").val(), $("#actualHours").val());
        }
        else {
            params();
            submitToServer(json, $("#taskRemarks").val(), $("#actualHours").val());
        }
    }


    function loadDataDicList() {
        var cols = [];
        cols.push({field: 'inventoryContentName', title: '清查内容'});
        cols.push({field: 'areConsistent', title: '是否一致'});
        cols.push({field: 'registrationAddress', title: '登记面积'});
        cols.push({field: 'actualAddress', title: '实际面积'});
        cols.push({field: 'differenceReason', title: '差异原因'});
        cols.push({field: 'credential', title: '证明文件'});
        cols.push({field: 'credentialAccessory', title: '证明文件附件'});
        cols.push({field: 'voucher', title: '证明人'});

        cols.push({
            field: 'surveyTime', title: '调查时间', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success" href="javascript:editHrProfessional(' + index + ');" >编辑</i></a>';
                str += '<a class="btn btn-xs btn-warning" href="javascript:delData(' + row.id + ',\'tb_List\')">删除</a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_List").bootstrapTable('destroy');
        TableInit("tb_List", "${pageContext.request.contextPath}/surveyAssetTemplate/list", cols, {
            pid: ${empty surveyAssetInventory.id?0:surveyAssetInventory.id}

        }, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    }


    function addData() {
        $(".disform").clearAll();
        $("#notReal").show();
        $(".noshow").hide();
    }

    function saveData() {
        var flag = false;
        //var data = formParams("frm");
//        var data = $("#frm").serialize();
        var data = formParams("frm");
        if ($("#frm").valid()) {
            $.ajax({
                url: "${pageContext.request.contextPath}/surveyAssetTemplate/save",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        loadDataDicList();
                        $('#divBox').modal('hide');
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

    function editHrProfessional(index) {
        var row = $("#tb_List").bootstrapTable('getData')[index];
        $("#frm").clearAll();
        $("#frm").initForm(row);
        $("#surveyTime").val(formatDate(row.surveyTime, false));
        $(".noshow").hide();
        $('#divBox').modal();
        if ($('#areConsistent').prop("checked")) {
            $('#notReal').css('display', 'none');
        } else {
            $('#notReal').css('display', 'block');
        }
    }


    function delData(id, tbId) {
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/surveyAssetTemplate/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        loadDataDicList();//重载 (刷新)
                        $('#' + tbId).bootstrapTable("refresh");
                    }
                    else {
                        Alert("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        })
    }

    $('#agreementBox').click(function () {
        if ($('#agreementBox')[0].checked == false) {
            $('#frm_survey').css('display', 'block');
        } else {
            $('#frm_survey').css('display', 'none');
        }
    });

    $('#areConsistent').click(function () {
        if ($('#areConsistent').prop("checked")) {
            $('#notReal').css('display', 'none');
        } else {
            $('#notReal').css('display', 'block');
        }
    });


    //选择人员
    /*erpDepartment.select({
     onSelected:function (nodes) {
     console.log(nodes);
     }
     });

     erpEmployee.select({
     onSelected:function (data) {
     console.log(data);
     }
     });*/

    // 评估人员
    var evaluator = document.getElementById("evaluator");
    evaluator.onclick = function () {
        erpEmployee.select({
            onSelected: function (data) {
                evaluator.removeChild(evaluator.firstChild);
                var fieldElment = document.createElement("option");
                fieldElment.setAttribute("value", data.account);
                fieldElment.setAttribute("selected", "selected");
                fieldElment.appendChild(document.createTextNode(data.name));
                evaluator.appendChild(fieldElment);
            }
        });
    }


</script>

</html>

