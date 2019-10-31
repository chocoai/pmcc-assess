<%--
  Created by IntelliJ IDEA.
  User: red
  Date: 2017/10/27
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="project_phase_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">
                    工作事项编辑
                </h3>
            </div>

            <form id="modify_project_phase_form" class="form-horizontal">
                <input type="hidden" id="id" name="id">
                <input type="hidden" id="projectClassId" name="projectClassId">
                <input type="hidden" id="projectTypeId" name="projectTypeId">
                <input type="hidden" id="projectCategoryId" name="projectCategoryId">
                <input id="workTemplate" name="workTemplate" type="hidden">
                <input id="workProcessTemplate" name="workProcessTemplate" type="hidden">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label" for="projectPhaseName">
                                            工作事项<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <input class="form-control" id="projectPhaseName" name="projectPhaseName"
                                                   required
                                                   data-rule-maxlength="255" placeholder="工作事项名称">
                                        </div>
                                        <div class="x-valid">
                                            <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label" for="phaseForm">
                                                自定义服务
                                            </label>
                                            <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                                <select id="phaseForm" name="phaseForm"
                                                        class="form-control search-select select2">
                                                    <option value="">-选择-</option>
                                                    <c:forEach var="item" items="${sysBaseFormListMatter}">
                                                        <option value="${item.assistName}">${item.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label" for="workStageId">
                                            所属阶段<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4  ">
                                            <select class="form-control" id="workStageId" name="workStageId" required>
                                                <option value="">-选择-</option>
                                            </select>
                                        </div>
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            模型
                                        </label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <input class="form-control" name="boxName"
                                                   data-rule-maxlength="255" placeholder="模型">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label" for="phaseTime">
                                            计划工时<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <div class="input-group">
                                                <input class="form-control" id="phaseTime" name="phaseTime" required
                                                       data-rule-number placeholder="计划耗费的工时">
                                                <span class="input-group-addon"> <i class="fa fa-clock-o"></i> </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label" for="phaseSort">
                                            序号<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <div class="input-group">
                                                <input class="form-control" id="phaseSort" name="phaseSort" required
                                                       data-rule-digits="true" placeholder="用于显示排序">
                                                <span class="input-group-addon"> <i class="fa fa-sort-numeric-asc"></i> </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label" for="phaseSort">
                                            自定义附件模板
                                        </label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <select name="assetsSettingId" class="form-control">
                                                <c:forEach items="${dataAssetsAppraisalDicList}" var="item">
                                                    <option value="${item.id}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            允许挂起
                                        </label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <label class="checkbox-inline">
                                                <input type="checkbox" id="bisWait" name="bisWait" value="true">
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label" for="phaseTime">
                                            默认使用流程
                                        </label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <label class="checkbox-inline">
                                                <input type="checkbox" id="bisUseBox" name="bisUseBox" value="true">
                                            </label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label" for="phaseSort">
                                            允许重启
                                        </label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <label class="checkbox-inline">
                                                <input type="checkbox" id="bisCanReturn" name="bisCanReturn"
                                                       value="true">
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label" for="workTemplate">
                                            工作模板
                                        </label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 " id="workTemplate_file_co">
                                            <input id="workTemplate_file" name="workTemplate_file" type="file"
                                                   multiple="false">
                                            <div id="_workTemplate_file"></div>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label" for="workProcessTemplate">
                                            流程模板
                                        </label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 " id="workProcessTemplate_file_co">
                                            <input id="workProcessTemplate_file" name="workProcessTemplate_file"
                                                   type="file"
                                                   multiple="false">
                                            <div id="_workProcessTemplate_file"></div>
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
                <button id="save_project_phase" type="button" class="btn btn-primary">
                    提交
                </button>
            </div>
        </div>
    </div>
</div>


<script type="application/javascript">
    //工作事项界面对象
    var workPhaseObj = {
        modalWorkPhase: $('#project_phase_modal'),
        formWorkPhase: $('#modify_project_phase_form')
    };


    /**
     * 渲染工作事件表格
     * @param documentEl
     */
    workPhaseObj.rendWorkPhaseTable = function (documentEl, typeId, categoryId) {
        documentEl.bootstrapTable('destroy');

        var cols = [];
        cols.push({field: 'id', title: 'id', visible: false});
        cols.push({
            field: 'workStageName', title: '阶段', formatter: function (value, row) {
                return "<div style='vertical-align: middle !important;text-align: center;'><h5>" + value + "</h5></div>";
            }
        });
        cols.push({
            field: 'projectPhaseName', title: '工作事项', formatter: function (value, row) {
                if (!row.bisEnable) {
                    return "<del><font color='#999'> " + value + "</font></del>";
                }
                return value;
            }
        });
        cols.push({
            field: 'phaseTime', title: '计划工时', formatter: function (value, row) {
                value += "h";
                if (!row.bisEnable) {
                    return "<del><font color='#999'> " + value + "</font></del>";
                }
                return value;
            }
        });
        cols.push({
            field: 'gmtCreated', title: '创建时间', formatter: function (value, row, index) {
                var gmtCreated = formatDate(row.gmtCreated, true);
                if (!row.bisEnable) {
                    return "<del><font color='#999'> " + gmtCreated + "</font></del>";
                }
                return gmtCreated;
            }
        });
        cols.push({
            field: 'boxName', title: '操作模型', formatter: function (value, row) {
                if (!row.bisEnable) {
                    return "<del><font color='#999'> " + value + "</font></del>";
                }
                return value;
            }
        });
        cols.push({
            field: 'phaseSort', title: '排序', formatter: function (value, row) {
                return "<div style='vertical-align: middle !important;text-align: center;'><h5>" + value + "</h5></div>";
            }
        });
        cols.push({
                title: '操作',
                formatter: function (value, row, index) {
//                    var str = '<div class="btn-margin">';
//                    if (row.bisEnable) {
//                        str += '<a id="item_edit" class="re btn btn-xs btn-teal" href="javascript:void(0);"><i class="fa fa-edit"></i>修改</a>';
//                        str += '<a id="item_disable" class="re btn btn-xs btn-light-grey" href="javascript:void(0);"><i class="fa fa-eye-slash"></i>禁用</a>';
//
//                    } else {
//                        str += '<a id="item_enable" class="re btn btn-xs btn-success" href="javascript:void(0);"><i class="fa fa-eye"></i>启用</a>';
//
//                    }
//                    str += '</div>';
//                    return str;


                    var str = "<a id='item_edit' style='margin-left: 5px;' data-toggle='tooltip' data-placement='top' data-original-title='编辑' class='btn btn-xs btn-success tooltips'  ><i class='fa fa-edit fa-white'></i></a>";
                    str += "<a id='item_disable' style='margin-left: 5px;' data-toggle='tooltip' data-placement='top' data-original-title='删除'  class='btn btn-xs btn-warning tooltips' ><i class='fa fa-minus fa-white'></i></a>";
                    return str;
                }

                ,
                events: {
                    'click #item_edit': function (e, value, row, index) {
                        workPhaseObj.formWorkPhase.clearAll(); //清空一些状态
                        workStageObj.renderWorkStageDropList($("#workStageId"), $("#typeId").val());
                        //编辑
                        workPhaseObj.formWorkPhase.initForm(row);

                        $("#phaseForm").select2().val(row.phaseForm).trigger("change");
                        $("#workPhaseBoxName").select2().val(row.boxName).trigger("change");

                        //回填一些没有的值
                        $('#boxCnName').val(row.boxCnName);
                        $('#phaseSignatureName').val(row.phaseSignatureName);
                        if (row.bisAssessment) {
                            $('#bisAssessment').prop("checked", true);
                        }
                        $('#bisWait').prop("checked", row.bisWait);
                        $('#bisUseBox').prop("checked", row.bisUseBox);
                        $('#bisCanReturn').prop("checked", row.bisCanReturn);
                        //初始化文件组件
                        workPhaseObj.uploadFile("workTemplate_file", row.id, "workTemp");
                        workPhaseObj.uploadFile("workProcessTemplate_file", row.id, "processTemp");
                        workPhaseObj.fileList("workTemplate_file", row.id, "workTemp");
                        workPhaseObj.fileList("workProcessTemplate_file", row.id, "processTemp");
                        //编辑页面回显文件


                        //显示编辑模态窗口
                        workPhaseObj.modalWorkPhase.modal({backdrop: 'static', keyboard: false});
                    },
                    'click #item_assessment': function (e, value, row, index) {
                        if (!row.boxName) {
                            Alert("当前工作事项还没有配置模型,不能配置考核数据");
                            return false;
                        }
                        boxDetailItemObj.renderBoxDetailItemTableForWorkPhase(row.boxName, row.workStageId, 0, "project");
                        boxDetailItemObj.boxDetailItemModalShowForPhase(row);
                    },
                    'click #item_disable': function (e, value, row, index) {
                        //禁用
                        workPhaseObj.enableWorkPhase(row.id, false, function () {
                            TableReload(documentEl);
                        });
                    }

                    ,
                    'click #item_enable': function (e, value, row, index) {
                        workPhaseObj.enableWorkPhase(row.id, true, function () {
                            TableReload(documentEl);
                        });
                    }
                }
            }
        );
        TableInit(documentEl, "${pageContext.request.contextPath}/ProjectPhase/list", cols, {
            classId: $("#clasId").val(),
            typeId: typeId,
            categoryId: categoryId
        }, {
            toolbar: '#modelListToolbar',
            showColumns: false,
            uniqueId: "id",
            singleSelect: true,
            onClickRow: function (_row, _tr, _field) {
                //当点击某行，将值赋值给表单

            },
            onLoadSuccess: function (data) {
                var rows = data.rows;

                var startIndex = 0; //开始的行
                var rowspan = 1; //要合并多少行
                if (rows) {
                    for (var i = 0; i < rows.length; i++) {
                        var nexIndex = i + 1;
                        if ((rows.length - nexIndex) > 0 && (rows[i].workStageName == rows[i + 1].workStageName)) {
                            rowspan++;
                        } else {
                            //merge
                            documentEl.bootstrapTable('mergeCells', {
                                index: startIndex,
                                field: 'workStageName',
                                rowspan: rowspan
                            });
                            rowspan = 1; //重置
                            startIndex = i + 1; //下一个开始的行
                        }
                    }
                }

            }
        });
    };

    /**
     * 新增工作事项
     */
    workPhaseObj.createWorkPhase = function (typeId, categoryId) {
        workPhaseObj.formWorkPhase.clearAll();
        var workStage = $("#workStageId");

        if (typeId && categoryId) {
            //label显示
            var lab = $("#typeId option:selected").text() + "->"
                + $("#categoryId option:selected").text() + '工作事项编辑';
            $('#class_type_category_label').text(lab);

            /**填装值**/
            $('#projectClassId').val($("#classId").val());
            $('#projectTypeId').val(typeId);
            $('#projectCategoryId').val(categoryId);
            $('#phaseTime').val(1); //默认给一个工时
            $('#bisAssessment').prop("checked", true);
            $('#bisUseBox').prop("checked", false);
            $('#bisCanReturn').prop("checked", false);

            /**
             * 初始化文件上传组件状态
             **/
            workPhaseObj.uploadFile("workTemplate_file", 0, "workTemp");
            workPhaseObj.uploadFile("workProcessTemplate_file", 0, "processTemp");
            workPhaseObj.fileList("workTemplate_file", 0, "workTemp");
            workPhaseObj.fileList("workProcessTemplate_file", 0, "processTemp");

            $('#project_phase_modal').modal({backdrop: 'static', keyboard: false});

            workStageObj.renderWorkStageDropList(workStage, typeId);
        } else {
            Alert("新增项目阶段时必须选择具体的类型->类别.");
        }
        $("#phaseForm").select2();
        $("#workPhaseBoxName").select2();
    };


    //启用或者停用工作事项
    workPhaseObj.enableWorkPhase = function (id, enable, successFn) {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/ProjectPhase/disableOrEnableProjectPhase",
            type: "post",
            data: {
                id: id,
                bisEnable: enable
            },
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('操作成功');
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

    /**
     * 保存工作事项
     */
    workPhaseObj.saveWorkPhase = function (successFn) {
        if (!workPhaseObj.formWorkPhase.valid()) return false;
        var data = formSerializeArray(workPhaseObj.formWorkPhase);
        if (!data) {
            Alert("没有需要保存的数据");
            return;
        }
        data.bisWait = $('#bisWait').prop('checked');
        data.bisUseBox = $('#bisUseBox').prop('checked');
        data.bisCanReturn = $('#bisCanReturn').prop('checked');
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/ProjectPhase/saveProjectPhase",
            type: "post",
            data: data,
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('操作成功');
                    $('#project_phase_modal').modal('hide');
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



    workPhaseObj.uploadFile = function (el, id, fieldName) {
        FileUtils.uploadFiles({
            target: el,
            showFileList: false,
            onUpload: function (file) {//上传之前触发
                var formData = {
                    tableName: "tb_project_phase",
                    fieldsName: fieldName,
                    tableId: id
                };
                return formData;
            },
            onUploadComplete: function () {
                workPhaseObj.fileList(el, id, fieldName);
            }
        });
    }

    workPhaseObj.fileList = function (el, id, fieldName) {
        FileUtils.getFileShows({
            target: el,
            formData: {
                tableName: "tb_project_phase",
                tableId: id,
                fieldsName: fieldName
            },
            deleteFlag: true
        })
    };


    workPhaseObj.resetWorkTemplateFile = function () {
        $('#workTemplate_file_co').empty();
        var workTemplate = '<input id="workTemplate_file" name="workTemplate_file" type="file" multiple="false"><div id="_workTemplate_file"></div>';

        $('#workTemplate_file_co').html(workTemplate);
    };
    workPhaseObj.resetWorkProcessTemplateFile = function () {
        $('#workProcessTemplate_file_co').empty();
        var workProcessTemplate = '<input id="workProcessTemplate_file" name="workProcessTemplate_file" type="file" multiple="false"><div id="_workProcessTemplate_file"></div>';

        $('#workProcessTemplate_file_co').html(workProcessTemplate);
    };

    //end: 文件操作------------


    //--------------document init----------------------//
    $(function () {


        //保存工作事项按钮触发事件
        $('#save_project_phase').click(function () {
            workPhaseObj.saveWorkPhase(function () {
                TableReload(projectWorkStagePhaseObj.workPhaseTable);
            });
        });

        $("#modify_project_phase_form").find('[name=boxName]').click(function () {
            var _this = this;
            bpmBoxRe.select(function (row) {
                $(_this).val(row.name);
            });
        })

    });
</script>
