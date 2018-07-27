<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
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
                    <h2>${parentProject.projectPhaseName}-${projectPlanDetails.projectPhaseName}</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_asset" class="form-horizontal">
                        <input type="hidden" name="id" value="${surveyAssetInventory.id}">
                        <input type="hidden" id="defaultLocaltion" name="defaultLocaltion"
                               value="${surveyAssetInventory.defaultLocaltion}">
                        <div class="form-group">

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">评估人员<span class="symbol required"></span></label>
                                <div class="col-sm-2">
                                    <div class="input-group">
                                        <input type="hidden" id="evaluatorID">
                                        <input type="text" class="form-control" readonly="readonly"
                                               value="${thisUserInfo.userName}" required="required"
                                               id="evaluator" name="evaluator" maxlength="200">
                                        <span class="input-group-btn">
                                            <button type="button" class="btn btn-default docs-tooltip"
                                                    data-toggle="tooltip"
                                                    data-original-title="选择" onclick="selectEvaluator()">
                                            <i class="fa fa-search"></i>
                                            </button>
                                            <button type="button" class="btn btn-default docs-tooltip"
                                                    onclick="$(this).closest('.input-group').find('input').val('');"
                                                    data-toggle="tooltip" data-original-title="清除">
                                            <i class="fa fa-trash-o"></i>
                                            </button>
                                        </span>
                                    </div>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">核对日期<span class="symbol required"></span></label>
                                <div class="col-sm-2">
                                    <input type="text" required placeholder="核对日期" id="checkDate" name="checkDate"
                                           data-date-format="yyyy-mm-dd" class="form-control date-picker dbdate"
                                           readonly="readonly"
                                           value="<fmt:formatDate value='${surveyAssetInventory.checkDate}' pattern='yyyy-MM-dd'/>">
                                </div>
                            </div>
                        </div>
                    </form>
                    <form id="frm_asset_inventory" class="form-horizontal">
                        <div class="x_title collapse-link">
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                            </ul>
                            <h2>
                                <small><i class="fa fa-bars"></i>清查内容</small>
                            </h2>
                            <div class="clearfix"></div>
                        </div>
                        <table class="table" id="tb_surveyList">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>清查内容</th>
                                <th>是否一致</th>
                                <th>登记面积</th>
                                <th>实际面积</th>
                                <th>差异原因</th>
                                <th>证明文件</th>
                                <th>证明文件附件</th>
                                <th>证明人</th>
                                <th>调查时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${surveyAssetTemplateVos}" var="items" varStatus="s">
                                <tr>
                                    <input type="hidden" id="id" name="id" value="${items.id}">
                                    <td>${s.index + 1}</td>
                                    <td name="inventoryContent"
                                        dic-id="${items.inventoryContent}">${items.inventoryContentName}</td>
                                    <td>
                                        <input id="areConsistent${items.id}" name="areConsistent" type="checkbox"
                                               value="一致" style="vertical-align:middle;"
                                               onclick="showHiddenCheck(this,${items.id})"/>
                                        <label style="vertical-align:middle;font-weight: normal;margin-bottom: 0px;"
                                               for="areConsistent${items.id}">一致</label>
                                    </td>
                                    <td>
                                        <div class="x-valid">
                                            <input type="text" data-rule-maxlength="50" placeholder="登记面积" required
                                                   id="registrationAddress${items.id}"
                                                   name="registrationAddress${items.id}"
                                                   class="form-control showHidden" value="${items.registrationAddress}">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="x-valid">
                                            <input type="text" data-rule-maxlength="50" placeholder="实际面积" required
                                                   id="actualAddress${items.id}" name="actualAddress${items.id}"
                                                   class="form-control showHidden" value="${items.actualAddress}">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="x-valid">
                                            <input type="text" data-rule-maxlength="50" placeholder="差异原因" required
                                                   id="differenceReason${items.id}" name="differenceReason${items.id}"
                                                   class="form-control showHidden" value="${items.differenceReason}">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="x-valid">
                                            <input type="text" data-rule-maxlength="50" placeholder="证明文件" required
                                                   id="credential${items.id}" name="credential${items.id}"
                                                   class="form-control showHidden" value="${items.credential}">
                                        </div>
                                    </td>
                                    <td>
                                        <input id="credentialAccessory${items.id}" name="credentialAccessory"
                                               type="file" multiple="false" class="showHidden">
                                        <div id="_credentialAccessory${items.id}" class="showHidden"></div>
                                    </td>
                                    <td>
                                        <div class="x-valid">
                                            <input type="text" data-rule-maxlength="50" placeholder="证明人" required
                                                   id="voucher${items.id}" name="voucher${items.id}"
                                                   class="form-control showHidden" value="${items.voucher}">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="x-valid">
                                            <input placeholder="调查时间" id="surveyTime${items.id}"
                                                   name="surveyTime${items.id}" required
                                                   data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate showHidden"
                                                   readonly="readonly"
                                                   value='<fmt:formatDate value="${items.surveyTime}" pattern="yyyy-MM-dd"/>'>
                                        </div>
                                    </td>
                                    <td>
                                        <a class="btn btn-xs btn-danger" onclick="emptyRefill(this)">清空重填</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </form>
                    <div class="x_title ">
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                        </ul>
                        <h2>
                            <small class="col-sm-1"><i class="fa fa-bars"></i>他项权利</small>
                        </h2>
                        <button type="button" class="btn btn-success" onclick="addData()"
                                data-toggle="modal" href="#divBox"> 新增
                        </button>
                        <div class="clearfix"></div>
                    </div>
                    <table class="table table-bordered" id="tb_List">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>

            <div class="form-group" style="display: none">
                <div class="col-sm-11">
                    <iframe src="${pageContext.request.contextPath}/map/positionPicker?position="
                            width="900" height="600" frameborder=”no” border=”0″ marginwidth=”0″
                            marginheight=”0″ scrolling=”no” allowtransparency=”yes”></iframe>
                </div>
            </div>

            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2>${parentProject.projectPhaseName}-${projectPlanDetails.projectPhaseName}成果提交</h2>
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
                <h3 class="modal-title">他项权利</h3>
            </div>
            <form id="frm" class="form-horizontal">
                <input type="hidden" name="id" value="0">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            类型<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <select class="form-control" required id="type" name="type">
                                                <option value="">-请选择-</option>
                                                <c:forEach var="items" items="${otherRightTypeList}">
                                                    <option value="${items.id}">${items.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            他权登记人<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="他权登记人" required
                                                   id="otherRightsRegistrar" name="otherRightsRegistrar"
                                                   class="form-control">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            实际行权人<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="实际行权人" required
                                                   id="rightHander" name="rightHander" class="form-control">
                                        </div>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            登记面积<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="登记面积" required
                                                   id="registerArea" name="registerArea" class="form-control">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            实际面积<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="实际面积" required
                                                   id="actualArea" name="actualArea" class="form-control">
                                        </div>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            登记用途<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="登记用途" required
                                                   id="registerPurpose" name="registerPurpose" class="form-control">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            实际用途<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="实际用途" required
                                                   id="actualPurpose" name="actualPurpose" class="form-control">
                                        </div>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">登记日期<span class="symbol required"></span></label>
                                        <div class="col-sm-4">
                                            <input placeholder="登记日期" id="registerDate" name="registerDate"
                                                   data-date-format="yyyy-mm-dd" required
                                                   class="form-control date-picker dbdate" readonly="readonly">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">到期日<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-4">
                                            <input required="required" placeholder="到期日" id="dueDate" name="dueDate"
                                                   data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate" readonly="readonly">
                                        </div>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">实际行权人行权日期<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-4">
                                            <input required="required" placeholder="实际行权人行权日期" id="exerciseDate"
                                                   name="exerciseDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate" readonly="readonly">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">预计到期日<span class="symbol required"></span></label>
                                        <div class="col-sm-4">
                                            <input required="required" placeholder="预计到期日" id="predictDueDate"
                                                   name="predictDueDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate" readonly="readonly">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </form>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="saveData()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">

    $(function () {
        loadAssetOtherList();

        //清查内容附件上传和加载
        uploadFileCommon("${surveyAssetTemplateVos.get(0).id}");
        showFileCommon("${surveyAssetTemplateVos.get(0).id}");

        uploadFileCommon("${surveyAssetTemplateVos.get(1).id}");
        showFileCommon("${surveyAssetTemplateVos.get(1).id}");

        uploadFileCommon("${surveyAssetTemplateVos.get(2).id}");
        showFileCommon("${surveyAssetTemplateVos.get(2).id}");

        uploadFileCommon("${surveyAssetTemplateVos.get(3).id}");
        showFileCommon("${surveyAssetTemplateVos.get(3).id}");

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
    });

    //上传附件通用
    function uploadFileCommon(tableId) {
        FileUtils.uploadFiles({
            showMode: 'table',
            target: "credentialAccessory" + tableId,
            disabledTarget: "btn_submit",
            formData: {
                tableName: "tb_survey_asset_template",
                tableId: tableId,
                fieldsName: "credentialAccessory",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        });
    }

    //显示附件通用
    function showFileCommon(tableId) {
        FileUtils.getFileShows({
            showMode: 'table',
            target: "credentialAccessory" + tableId,
            formData: {
                tableName: "tb_survey_asset_template",
                tableId: tableId,
                fieldsName: "credentialAccessory",
                projectId: "${projectPlanDetails.projectId}"
            },
            deleteFlag: true
        })
    }

    //加载 他项权利列表
    function loadAssetOtherList() {
        var cols = [];
        cols.push({field: 'typeName', title: '类型'});
        cols.push({field: 'otherRightsRegistrar', title: '他权登记人'});
        cols.push({field: 'rightHander', title: '实际行权人'});
        cols.push({field: 'registerArea', title: '登记面积'});
        cols.push({field: 'actualArea', title: '实际面积'});
        cols.push({field: 'registerPurpose', title: '登记用途'});
        cols.push({field: 'actualPurpose', title: '实际用途'});

        cols.push({
            field: 'registerDate', title: '登记日期', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        cols.push({
            field: 'dueDate', title: '到期日', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        cols.push({
            field: 'exerciseDate', title: '实际行权人行权日期', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        cols.push({
            field: 'predictDueDate', title: '预计到期日', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑" onclick="editData(' + index + ');" ><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="delData(' + row.id + ')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_List").bootstrapTable('destroy');
        TableInit("tb_List", "${pageContext.request.contextPath}/surveyAssetOtherTemplate/list", cols, {
            pid: ${empty surveyAssetInventory?0:surveyAssetInventory.id}
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        });
    }


    //获取需要保存的数据
    function getFormData() {
        var trs = $("#tb_surveyList").find('tbody tr');
        var dataItem = [];
        $.each(trs, function (i, tr) {
            var item = {};
            var temp = $(tr).find('[name="areConsistent"]:checked').prop("checked");    //是否一致
            if (temp) {
                item.areConsistent = "一致";    //是否一致
            } else {
                item.areConsistent = "不一致";
            }
            item.inventoryContent = $(tr).find('[name^="inventoryContent"]').attr("dic-id");    //清查内容
            item.registrationAddress = $(tr).find('[name^="registrationAddress"]').val();    //登记面积
            item.actualAddress = $(tr).find('[name^="actualAddress"]').val();                //实际面积
            item.differenceReason = $(tr).find('[name^="differenceReason"]').val();          //差异原因
            item.credential = $(tr).find('[name^="credential"]').val();                      //证明文件
            item.voucher = $(tr).find('[name^="voucher"]').val();                            //证明人
            item.surveyTime = $(tr).find('[name^="surveyTime"]').val();                      //查勘时间
            item.projectId = ${projectPlanDetails.projectId};
            item.planDetailId = ${projectPlanDetails.id};
            item.id = $(tr).find('[name="id"]').val();    //id
            dataItem.push(item);
        });
        var data = {};
        data.surveyAssetInventoryDto = formParams("frm_asset");//评估人员 核对时间
        data.surveyAssetTemplateDtos = dataItem;
        return data;
    }

    function submit() {
        if (!$("#frm_asset").valid()) {
            return false;
        }
        if (!$("#frm_asset_inventory").valid()) {
            return false;
        }
        if (!$("#frm_task").valid()) {
            return false;
        }
        var formData = JSON.stringify(getFormData());
        if ("${processInsId}" != "0") {
            submitEditToServer(formData, $("#taskRemarks").val(), $("#actualHours").val());
        }
        else {
            submitToServer(formData, $("#taskRemarks").val(), $("#actualHours").val());
        }
    }

    //他权一致显示隐藏切换
    $('#agreementBox').click(function () {
        if ($('#agreementBox').prop("checked")) {
            $('#frm_survey').css('display', 'none');
            $('#frm_survey').clearAll();
        } else {
            $('#frm_survey').css('display', 'block');
        }
    });


    //表格一致显示隐藏切换
    function showHiddenCheck(_this, id) {
        if ($('#areConsistent' + id).prop("checked")) {
            $(_this).closest("tr").find(".showHidden,div").css('display', 'none');
            $(_this).closest("tr").find("input:text").val("");
        } else {
            $(_this).closest("tr").find(".showHidden,div").css('display', 'block');
        }
    }

    //清空重填
    function emptyRefill(_this) {
        $(_this).closest("tr").find("input").val("");
    }

    //评估人员
    function selectEvaluator() {
        erpEmployee.select({
            onSelected: function (data) {
                $("#evaluator").val(data.name);
                $("#evaluatorID").val(data.account);
            }
        });
    }

    //他权
    function addData() {
        $("#frm").clearAll();
    }
    //他权保存
    function saveData() {
        var flag = false;
        var data = formParams("frm");
        data.projectId = ${projectPlanDetails.projectId};
        data.planDetailId = ${projectPlanDetails.id};
        data.pid = ${empty surveyAssetInventory?0:surveyAssetInventory.id};
        if ($("#frm").valid()) {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/surveyAssetOtherTemplate/save",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('保存成功');
                        loadAssetOtherList();
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
    //他权修改
    function editData(index) {
        var row = $("#tb_List").bootstrapTable('getData')[index];
        $("#frm").clearAll();
        $("#frm").initForm(row);
        $("#registerDate").val(formatDate(row.registerDate, false));
        $("#dueDate").val(formatDate(row.dueDate, false));
        $("#exerciseDate").val(formatDate(row.exerciseDate, false));
        $("#predictDueDate").val(formatDate(row.predictDueDate, false));
        $('#divBox').modal();
    }
    //他权删除
    function delData(id) {
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/surveyAssetOtherTemplate/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        loadAssetOtherList();//重载 (刷新)
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

    //解析定位结果
    function onCompleteSuccess(data) {
        $("#defaultLocaltion").val(data.position);
    }

    //解析定位错误信息
    function onErrorFail(data) {
        window.location.reload();
    }

</script>

</html>

