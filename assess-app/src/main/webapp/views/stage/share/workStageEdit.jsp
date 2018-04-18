<%--
  Created by IntelliJ IDEA.
  User: red
  Date: 2017/10/27
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="work_stage_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">
                    项目阶段编辑
                </h4>
            </div>

            <form id="modify_work_stage_form" class="form-horizontal">
                <input type="hidden" id="workStageIdKey" name="id">
                <input type="hidden" id="workStageProjectTypeId" name="projectTypeId">

                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label" for="workStageName">
                                            阶段名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input class="form-control" id="workStageName" name="workStageName" required
                                                   data-rule-maxlength="255" placeholder="项目阶段名称">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            自定义服务
                                        </label>
                                        <div class="col-sm-10">
                                            <select id="stageName" name="stageName"
                                                    class="form-control search-select select2">
                                                <option value="">-选择-</option>
                                                <c:forEach var="item" items="${sysBaseFormListStage}">
                                                    <option value="${item.assistName}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            计划模型
                                        </label>
                                        <div class="col-sm-10">
                                            <input class="form-control" name="boxName"
                                                   data-rule-maxlength="255" placeholder="模型">
                                        </div>
                                    </div>

                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            复核模型
                                        </label>
                                        <div class="col-sm-10">
                                            <input class="form-control" name="reviewBoxName"
                                                   data-rule-maxlength="255" placeholder="复核模型">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            角色类型
                                        </label>
                                        <div class="col-sm-4">
                                            <select name="boxRoleType" id="boxRoleType" class="form-control">
                                                <option value="">-选择-</option>
                                                <option value="1">部门角色</option>
                                                <option value="2">公有角色</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid" id="div_roleId" style="display: none">
                                        <label class="col-sm-2 control-label">
                                            部门角色
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="hidden" id="boxRoleId" name="boxRoleId">
                                            <input data-rule-maxlength="200" placeholder="选择公用角色则部门可不填"
                                                   onfocus="workStageObj.selDept()" id="boxRoleName"
                                                   name="boxRoleName"
                                                   class="form-control">
                                        </div>
                                    </div>
                                    <div class="x-valid" id="div_roleKey" style="display: none">
                                        <label class="col-sm-2 control-label">
                                            公有角色
                                        </label>
                                        <div class="col-sm-4">
                                            <select name="boxRoleKey" id="boxRoleKey"
                                                    class="form-control search-select select2">
                                                <option value="">-选择-</option>
                                                <c:forEach var="item" items="${publicRole}">
                                                    <option value="${item.name}">${item.cnName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label" for="workStageSort">
                                            执行顺序<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <div class="input-group">
                                                <input class="form-control" id="workStageSort" name="stageSort" required
                                                       data-rule-digits="true" placeholder="用于显示排序">
                                                <span class="input-group-addon"> <i class="fa fa-sort-numeric-asc"></i> </span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label" for="specificGravity">
                                            比重<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input class="form-control" id="specificGravity" name="specificGravity"
                                                   required
                                                   data-rule-number="true" placeholder="比重、权重">
                                        </div>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label" for="workStagebisEnable">
                                            允许下发
                                        </label>
                                        <div class="col-sm-2">
                                            <label class="checkbox-inline">
                                                <input type="checkbox" id="workStageAllowIssued" name="allowIssued">
                                            </label>
                                        </div>
                                    </div>

                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label" for="workStagebisEnable">
                                            是否可用
                                        </label>
                                        <div class="col-sm-2">
                                            <label class="checkbox-inline">
                                                <input type="checkbox" id="workStagebisEnable" name="bisEnable"
                                                       value="true"
                                                       checked>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label" for="workStagebisLoadDefalut">
                                            是否加载默认项
                                        </label>
                                        <div class="col-sm-2">
                                            <label class="checkbox-inline">
                                                <input type="checkbox" id="workStagebisLoadDefalut"
                                                       name="bisLoadDefalut" value="true"
                                                       checked>
                                            </label>
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
                <button id="save_work_stage" type="button" class="btn btn-primary">
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
            error: function (e) {
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
            Alert("【" + typeName + "】类型还没有配置项目阶段，请点击'新增项目阶段'来添加项目阶段.");
        }
    };

    //渲染项目阶段步数图
    workStageObj.renderWorkStageStep = function (documentEl, typeId) {
        if (documentEl) {
            documentEl.empty();
            var data = workStageObj.getWordStage(null, typeId);
            var retHtml = "<ul class='wizard_steps anchor'>";
            if (data && data.length > 0) {
                $.each(data, function (i, item) {
                    var step = i + 1;
                    retHtml += '<li><a href="javascript:void(0);" class="done tooltips" data-placement="bottom" data-original-title="点击我可编辑" isdone="1" ';
                    retHtml += 'title="点击我可编辑" onclick="workStageObj.editWorkStage(' + item.id + ')"';
                    retHtml += '>';
                    retHtml += '<div class="step_no">' + step + '</div>';
                    retHtml += '<span class="step_descr"><small>' + item.workStageName + '(' + item.stageSort + ')</small></span>';
                    retHtml += '</a>';
                    //+ '<button class="btn btn-xs btn-default" onclick="disableWorkStage(' + item.id + ')" title="禁用当前阶段"><li class="fa fa-times"></li></button>'
                    retHtml += '</li>';
                });
            } else {
                retHtml += '<div class="alert alert-block alert-warning fade in"><h4 class="alert-heading"><i class="fa fa-exclamation-triangle"></i>请配置项目阶段</h4></div>';
            }
            retHtml += '</ul>'
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
                Alert("新增项目阶段时必须选择具体的类型");
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
            Alert("没有需要保存的数据");
            return false;
        }
        //保存项目如果禁用了则提示一下
        if (!data.bisEnable) {
            Alert("您在编辑项目阶段时选择了禁用此阶段，您确认要这么做(如果这么做页面上将不会显示此阶段的相关数据)？", 2, function () {
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
                    toastr.success('操作成功');
                    workStageObj.modalWorkStage.modal('hide');
                    if (typeof successFn === "function") {
                        successFn();
                    }
                } else {
                    Alert("操作失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };


    //选择部门
    workStageObj.selDept = function () {

        loadSelectDept(1, $("#boxRoleId").val(), false, function (nodes) {
            if (nodes || nodes.length > 0) {
                $("#boxRoleId").val(nodes[0].id);
                $("#boxRoleName").val(nodes[0].text);
            }
        }, "role")
    };


    //    function boxRoleChange(curr) {
    //
    //    }

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

