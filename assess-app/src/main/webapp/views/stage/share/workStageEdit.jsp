<%--
  Created by IntelliJ IDEA.
  User: red
  Date: 2017/10/27
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="work_stage_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">项目阶段编辑</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="modify_work_stage_form" class="form-horizontal">
                    <input type="hidden" id="workStageIdKey" name="id">
                    <input type="hidden" id="workStageProjectClassId" name="projectClassId">
                    <input type="hidden" id="workStageProjectTypeId" name="projectTypeId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                阶段名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input class="form-control input-full" id="workStageName"
                                                       name="workStageName" required
                                                       data-rule-maxlength="255" placeholder="项目阶段名称">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                自定义服务
                                            </label>
                                            <div class="col-sm-10">
                                                <select id="stageForm" name="stageForm"
                                                        class="form-control input-full">
                                                    <option value="">-选择-</option>
                                                    <c:forEach var="item" items="${sysBaseFormListStage}">
                                                        <option value="${item.assistName}">${item.name}</option>
                                                    </c:forEach>

                                                    <c:forEach var="item" items="${sysBaseFormListStageAuto}">
                                                        <option value="${item.assistName}">${item.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                计划模型
                                            </label>
                                            <div class="col-sm-10">
                                                <input class="form-control input-full" name="boxName"
                                                       data-rule-maxlength="255" placeholder="模型">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                复核模型
                                            </label>
                                            <div class="col-sm-10">
                                                <input class="form-control input-full" name="reviewBoxName"
                                                       data-rule-maxlength="255" placeholder="复核模型">
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                角色类型
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="boxRoleType" id="boxRoleType"
                                                        class="form-control input-full">
                                                    <option value="">-选择-</option>
                                                    <option value="1">部门角色</option>
                                                    <option value="2">公有角色</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-6" id="div_roleId" style="display: none">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                部门角色
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="hidden" id="boxRoleId" name="boxRoleId">
                                                <input data-rule-maxlength="200" placeholder="选择公用角色则部门可不填"
                                                       onfocus="workStageObj.selApprovalRole()" id="boxRoleName"
                                                       name="boxRoleName"
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6" id="div_roleKey" style="display: none">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                公有角色
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="boxRoleKey" id="boxRoleKey"
                                                        class="form-control input-full search-select select2">
                                                    <option value="">-选择-</option>
                                                    <c:forEach var="item" items="${publicRole}">
                                                        <option value="${item.name}">${item.cnName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                执行顺序<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <div class="input-group">
                                                    <input class="form-control input-full" id="workStageSort"
                                                           name="stageSort" required
                                                           data-rule-digits="true" placeholder="用于显示排序">
                                                    <span class="input-group-addon"> <i
                                                            class="fa fa-sort-numeric-asc"></i> </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                比重<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input class="form-control input-full" id="specificGravity"
                                                       name="specificGravity"
                                                       required
                                                       data-rule-number="true" placeholder="比重、权重">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                项目经理工分
                                            </label>
                                            <div class="col-sm-10">
                                                <div class="input-group">
                                                    <input class="form-control input-full" id="managerReviewScore" name="managerReviewScore" data-rule-number="true" placeholder="项目经理工分">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                技术总工工分
                                            </label>
                                            <div class="col-sm-10">
                                                <input class="form-control input-full" id="ceReviewScore" name="ceReviewScore"
                                                       data-rule-number="true" placeholder="技术总工工分">
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <div class="col-sm-10">
                                                <div class="form-check" style="justify-content:left">
                                                    <label class="form-check-label">
                                                        <input class="form-check-input" type="checkbox"
                                                               id="workStageAllowIssued"
                                                               name="allowIssued" value="true"
                                                               checked="checked">
                                                        <span class="form-check-sign">允许下发</span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <div class="col-sm-10">
                                                <div class="form-check" style="justify-content:left">
                                                    <label class="form-check-label">
                                                        <input class="form-check-input" type="checkbox"
                                                               id="workStagebisEnable"
                                                               name="bisEnable" value="true"
                                                               checked="checked">
                                                        <span class="form-check-sign">是否可用</span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <div class="col-sm-10">
                                                <div class="form-check" style="justify-content:left">
                                                    <label class="form-check-label">
                                                        <input class="form-check-input" type="checkbox"
                                                               id="workStagebisLoadDefalut"
                                                               name="bisLoadDefalut" value="true"
                                                               checked="checked">
                                                        <span class="form-check-sign">是否加载默认项</span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button id="save_work_stage" type="button" class="btn btn-primary btn-sm">
                    提交
                </button>
            </div>

        </div>
    </div>
</div>

<script type="application/javascript">


    //项目阶段界面对象
    var workStageObj = {
        assessmentDataSource: "project",
        workStageData: [],
        modalWorkStage: $('#work_stage_modal'),
        formWorkStage: $('#modify_work_stage_form')
    };

    /**
     * 获取项目有哪些阶段
     * 需要同步获取数据，防止出现未知数据显示异常
     */
    workStageObj.getWordStage = function (id, typeId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/ProjectPhase/workStage",
            type: "get",
            async: false,
            data: {
                id: id,
                classId: 0,
                typeId: typeId
            },
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    workStageObj.workStageData = result.data;
                } else {
                    workStageObj.workStageData = [];
                    console.log("获取项目阶段失败:" + result.errmsg);
                }
            },
            error: function (result) {
                workStageObj.workStageData = [];
                console.log("获取项目阶段失败，失败原因:" + e);
            }
        });

        return workStageObj.workStageData;
    };

    //渲染阶段下拉列表
    workStageObj.renderWorkStageDropList = function (documentEl, typeId) {
        documentEl.empty();
        var data = workStageObj.getWordStage(null, typeId);
        if (data && data.length > 0) {
            var retHtml = '<option value="" selected>-请选择-</option>';
            $.each(data, function (i, item) {
                retHtml += ' <option value="' + item.id + '">' + item.workStageName + '</option>';
            })
            documentEl.html(retHtml);

            $('#create_project_phase').removeAttr('disabled');
        } else {
            var typeName = $("#typeId option:selected").text();
            AlertError("失败", "【" + typeName + "】类型还没有配置项目阶段，请点击'新增项目阶段'来添加项目阶段.");
        }
    };

    //渲染项目阶段步数图
    workStageObj.renderWorkStageStep = function (documentEl, typeId) {
        if (documentEl) {
            documentEl.empty();
            var data = workStageObj.getWordStage(null, typeId);
            if (data && data.length > 0) {
                var retHtml = "<ul class='wizard_steps anchor'>";
                $.each(data, function (i, item) {
                    var step = i + 1;
                    retHtml += '<li><a href="javascript:void(0);" class="done tooltips" data-placement="bottom" data-original-title="点击我可编辑" isdone="1" ';
                    retHtml += 'title="点击我可编辑" onclick="workStageObj.editWorkStage(' + item.id + ')"';
                    retHtml += '>';
                    retHtml += '<div class="step_no">' + step + '</div>';
                    retHtml += '<span class="step_descr"><small style="color: #575962 !important">' + item.workStageName + '(' + item.stageSort + ')</small></span>';
                    retHtml += '</a>';
                    //+ '<button class="btn btn-xs btn-default" onclick="disableWorkStage(' + item.id + ')" title="禁用当前阶段"><li class="fa fa-times"></li></button>'
                    retHtml += '</li>';
                });
                retHtml += '</ul>'
            } else {
                //retHtml += '<div class="alert alert-block alert-info fade in"><h4 class="alert-heading"><i class="fa fa-exclamation-triangle"></i>请配置项目阶段</h4></div>';
            }
            documentEl.html(retHtml);
            $(".tooltips").tooltip();
        }
    };

    /**
     * 编辑阶段数据
     */
    workStageObj.editWorkStage = function (id, typeId) {
        if (id) {
            //如果传入数据，则是对数据进行修改
            if (workStageObj.workStageData && workStageObj.workStageData.length > 0) {
                $.each(workStageObj.workStageData, function (i, item) {
                    if (id === item.id) {
                        workStageObj.formWorkStage.initForm(item);

                        //计划模型赋值
                        $('#workStageBoxName').val(item.boxCnName);
                        $('#boxRoleName').val(item.boxRoleName);
                        $('#boxRoleId').val(item.boxRoleId);
                        $('#boxRoleKey').val(item.boxRoleKey);
                        $('#boxRoleType').val(item.boxRoleType);
                        $("#stageName").select2().val(item.stageName).trigger("change");
                        $("#boxName").select2().val(item.boxName).trigger("change");
                        $("#reviewBoxName").select2().val(item.reviewBoxName).trigger("change");
                        //复核模型赋值
                        $('#workStageReviewBoxName').val(item.reviewBoxCnName);

                        //强制回显checkBox
                        $('#workStageAllowIssued').prop('checked', item.allowIssued);
                        $('#workStagebisEnable').prop('checked', item.bisEnable);
                        $('#workStagebisLoadDefalut').prop('checked', item.bisLoadDefalut);
                        $('#bisAssessmentReview').prop("checked", item.bisAssessment);

                        workStageObj.modalWorkStage.modal();

                        //编辑才启用考核配置按钮
                        $('#workStageBox_assessment').removeAttr('disabled');
                        $('#reviewBox_assessment').removeAttr('disabled');
                    }
                });
            }
        } else {
            //新增数据
            workStageObj.formWorkStage.clearAll();

            if (typeId || true) {
                //label显示
                var lab = $("#typeId option:selected").text() + '项目阶段编辑';
                $('#work_stage_label').text(lab);

                /**填装值**/
                $('#workStageProjectClassId').val($("#classId").val());
                $('#workStageProjectTypeId').val(typeId);
                $('#workStagebisLoadDefalut').prop("checked", true);
                $('#workStagebisEnable').prop("checked", true);
                $('#bisAssessmentReview').prop("checked", true);

                $("#stageName").select2();
                $("#boxName").select2();
                $("#reviewBoxName").select2();
                workStageObj.modalWorkStage.modal({backdrop: 'static', keyboard: false});

                //新增项目时不能使用配置考核按钮
                $('#workStageBox_assessment').attr('disabled', "true");
                $('#reviewBox_assessment').attr('disabled', "true");
            } else {
                AlertError("失败", "新增项目阶段时必须选择具体的类型");
            }
        }
        $("#boxRoleType").change();


    };

    /**
     * 保存阶段数据
     */
    workStageObj.saveWorkStage = function (successFn) {

        if (!workStageObj.formWorkStage.valid()) return false;

        var data = formSerializeArray(workStageObj.formWorkStage);
        if (!data) {
            AlertError("失败", "没有需要保存的数据");
            return false;
        }
        //保存项目如果禁用了则提示一下
        if (!data.bisEnable) {
            AlertError("失败", "您在编辑项目阶段时选择了禁用此阶段，您确认要这么做(如果这么做页面上将不会显示此阶段的相关数据)？", 2, function () {
                data.bisEnable = true;
            }, function () {
                data.bisEnable = false;
            });
        }

        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/ProjectPhase/saveWorkStage",
            type: "post",
            data: data,
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "数据已成功保存到数据库");
                    workStageObj.modalWorkStage.modal('hide');
                    if (typeof successFn === "function") {
                        successFn();
                    }
                } else {
                    AlertError("失败", "操作失败:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    };


    //选择部门
    workStageObj.selApprovalRole = function () {
        bpmApprovalRole.select({
            value: $("#boxRoleId").val(),
            onSelected: function (node) {
                $("#boxRoleId").val(node.id);
                $("#boxRoleName").val(node.text);
            }
        });
    };


    //-----------------document init-------------------//
    $(function () {

        //保存项目阶段按钮触发事件
        $('#save_work_stage').click(function () {
            workStageObj.saveWorkStage(function () {
                var typeId = $('#workStageProjectTypeId').val();
                workStageObj.renderWorkStageStep(projectWorkStagePhaseObj.workStageWizard, typeId);
                TableReload(projectWorkStagePhaseObj.workPhaseTable); //重新加载工作事项表格
            });
        });
        $("#stageName").select2();
        $("#boxName").select2();
        $("#reviewBoxName").select2();
        $("#boxRoleKey").select2();
        $("#boxRoleType").change(function () {
            var boxRoleType = $("#boxRoleType").val();
            switch (boxRoleType) {
                case "1": {
                    $("#div_roleId").show();
                    $("#div_roleKey").hide();
                    break;
                }
                case "2": {
                    $("#div_roleId").hide();
                    $("#div_roleKey").show();
                    break;
                }
                default: {
                    $("#div_roleId").hide();
                    $("#div_roleKey").hide();
                    break;
                }
            }
        });

        $("#modify_work_stage_form").find('[name=boxName],[name=reviewBoxName]').click(function () {
            var _this = this;
            bpmBoxRe.select(function (row) {
                $(_this).val(row.name);
            });
        })
    });
</script>

