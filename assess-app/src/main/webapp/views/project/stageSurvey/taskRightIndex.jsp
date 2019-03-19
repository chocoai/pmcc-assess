<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<script type="text/html" id="taskRightAssistDiv">
    <div class="x_content">
        <div class="btn-group">
            <button type="button" class="btn btn-success" onclick="addData(this,'_number')"
                    data-toggle="modal" data-target="#divBox_number"> 新增
            </button>
        </div>
        <div class="btn-group">
            <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                    aria-expanded="false">导入数据
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" role="menu">
                <li><a href="javascript://"
                       onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftAssetInventoryRight);">下载模板</a>
                </li>
                <li><a href="javascript://;"
                       onclick="$('#ajaxFileUpload_number').val('').trigger('click')">导入数据</a>
                </li>
            </ul>
        </div>
        <div class="btn-group">
            <button type="button" class="btn btn-warning"
                    onclick="cleanHTMLData(this)" aria-expanded="false">
                X
            </button>
        </div>
        <div class="btn-group">
            <button type="button" class="btn btn-success"
                    onclick="delData(this,'_number')" aria-expanded="false">
                删除他项权力
            </button>
        </div>
        <div class="btn-group">
            <button type="button" class="btn btn-success"
                    onclick="editData(this,'_number')" aria-expanded="false">
                编辑他项权力
            </button>
        </div>
        <div class="btn-group">
            <button type="button" class="btn btn-success"
                    onclick="saveSurveyAssetInventoryRightRecord(true,'_number')" aria-expanded="false">
                保存
            </button>
        </div>
        <table class="table table-bordered" id="tb_List_number">
            <!-- cerare document add ajax data-->
        </table>
        <form class="form-horizontal" id="surveyAssetInventoryRightFrm_number">
            <input type="hidden" name="id">
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">特殊情况</label>
                    <div class="col-sm-11">
                                    <textarea placeholder="特殊情况" class="form-control" name="specialcase"></textarea>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">申报</label>
                    <div class="col-sm-5">
                        <select class="form-control search-select select2" multiple="multiple" required="required"
                                name="recordIds">
                            <c:forEach var="items" items="${declareRecordList}">
                                <option value="${items.id}">${items.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">特殊情况附件</label>
                    <div class="col-sm-4">
                        <input id="specialCaseFile_number" type="file" multiple="false">
                        <div id="_specialCaseFile_number"></div>
                    </div>
                </div>
            </div>
        </form>
        <input type="file" id="ajaxFileUpload_number" name="file" style="display: none;"
               onchange="importRightData(this,'_number');">
    </div>

    <div id="divBox_number" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
         aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title">他项权利</h3>
                </div>
                <div class="modal-body">
                    <form id="frm_inventory_right_number" class="form-horizontal">
                        <input type="hidden" name="inventoryRightRecordId">
                        <input type="hidden" name="id" value="0">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-2 control-label">
                                    类别<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-10">
                                    <select class="form-control" required id="category" name="category">
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-2 control-label">
                                    他权证编号
                                </label>
                                <div class="col-sm-4">
                                    <input type="text" placeholder="他权证编号" name="number" class="form-control">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-2 control-label">登记日期</label>
                                <div class="col-sm-4">
                                    <input placeholder="登记日期" name="registerDate"
                                           data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate" readonly="readonly">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-2 control-label">
                                    义务人
                                </label>
                                <div class="col-sm-4">
                                    <input type="text" placeholder="义务人" name="obligor" class="form-control">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-2 control-label">
                                    权利人
                                </label>
                                <div class="col-sm-4">
                                    <input type="text" placeholder="权利人" name="obligee" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-2 control-label">
                                    登记金额
                                </label>
                                <div class="col-sm-4">
                                    <input type="text" placeholder="登记金额"
                                           name="registerAmount" class="form-control">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-2 control-label">
                                    行权金额
                                </label>
                                <div class="col-sm-4">
                                    <input type="text" placeholder="行权金额"
                                           name="actualAmount" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-2 control-label">
                                    登记面积
                                </label>
                                <div class="col-sm-4">
                                    <input type="text" placeholder="登记面积"
                                           name="registerArea" class="form-control">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-2 control-label">
                                    他权级次
                                </label>
                                <div class="col-sm-4">
                                    <input type="text" placeholder="他权级次"
                                           name="rightRank" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-2 control-label">开始日期</label>
                                <div class="col-sm-4">
                                    <input placeholder="开始日期"
                                           name="beginDate" data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate" readonly="readonly">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-2 control-label">结束日期</label>
                                <div class="col-sm-4">
                                    <input placeholder="结束日期"
                                           name="endDate" data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate" readonly="readonly">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-2 control-label">备注</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" name="remark"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-2 control-label">
                                    附件
                                </label>
                                <div class="col-sm-10">
                                    <input id="inventoryRightFile_number" type="file" multiple="false">
                                    <div id="_inventoryRightFile_number"></div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="saveData(this,'_number')">
                        保存
                    </button>
                </div>
            </div>
        </div>
    </div>

</script>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title">
                    <h3>他项权利
                        <button class="btn btn-xs btn-success" onclick="appendHtml(false)">添加<i
                                class="fa fa-plus"></i>
                        </button>
                    </h3>
                    <div class="clearfix"></div>
                </div>
            </div>
            <div class="x_panel" id="taskRightAssistAppend">
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <c:choose>
                            <c:when test="${projectPhase.bisUseBox eq false}">
                                <button id="btn_submit" class="btn btn-success"
                                        onclick="submit(false);">
                                    直接提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
                                <button id="btn_submit" class="btn btn-primary"
                                        onclick="submit(true);">
                                    提交审批<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button id="btn_submit" class="btn btn-success"
                                        onclick="submit();">
                                    提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
<script type="text/javascript">

    //_number
    var commonField = {
        tbList: "tb_List",
        divBox: "divBox",
        inventoryFrm: "frm_inventory_right",
        surveyFrm: "surveyAssetInventoryRightFrm",
        inventoryRightFile: "inventoryRightFile",
        specialCaseFile: "specialCaseFile",
        taskRightAssistAppend: "taskRightAssistAppend",
        taskRightAssistDiv: "taskRightAssistDiv",
        ajaxFileUpload: "ajaxFileUpload",
        getNumber: function () {
            var number = parseInt(Math.random() * (999999 - 100000) + 100000, 10) + Math.round(Math.random() * 10);
            return number;
        }
    };

    //上传附件通用
    function uploadFileCommon(fieldsName, tableName, id) {
        FileUtils.uploadFiles({
            target: fieldsName,
            disabledTarget: "btn_submit",
            formData: {
                fieldsName: fieldsName,
                tableName: tableName,
                tableId: id
            },
            deleteFlag: true
        });
    }

    //显示附件通用
    function showFileCommon(fieldsName, tableName, id) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: tableName,
                tableId: id
            },
            deleteFlag: true
        })
    }

    //加载 他项权利列表
    function loadAssetRightList(number, inventoryRightRecordId) {
        var cols = [];
        cols.push({field: 'typeName', title: '类型'});
        cols.push({field: 'categoryName', title: '类别'});
        cols.push({field: 'number', title: '他权证编号'});
        cols.push({field: 'obligor', title: '义务人'});
        cols.push({field: 'obligee', title: '权利人'});
        cols.push({field: 'registerArea', title: '登记面积'});
        cols.push({field: 'rightRank', title: '他权级次'});
        $("#" + commonField.tbList + number).bootstrapTable('destroy');
        TableInit(commonField.tbList + number, "${pageContext.request.contextPath}/surveyAssetInventoryRight/getListByPlanDetailsId", cols, {
            planDetailsId: '${projectPlanDetails.id}', inventoryRightRecordId: inventoryRightRecordId
        }, {
            method: "get",
            showColumns: true,
            showRefresh: true,
            search: false,
            striped: true,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        }, true);
    }

    /**
     * 添加html,并且替换
     * @returns {*|jQuery}
     */
    function appendHtml(flag) {
        if (flag){
            $.ajax({
                url: "${pageContext.request.contextPath}/surveyAssetInventoryRightRecord/getSurveyAssetInventoryRightRecordList",
                type: "get",
                dataType: "json",
                async:true,
                data: {
                    projectId: '${projectPlanDetails.projectId}',
                    planDetailsId: '${projectPlanDetails.id}'
                },
                success: function (result) {
                    if (result.ret) {
                        if (result.data.length >= 1){
                            $.each(result.data,function (i,item) {
                                var html = $("#" + commonField.taskRightAssistDiv).html();
                                var number = commonField.getNumber();
                                html = html.replace(/_number/g, number);
                                $("#" + commonField.taskRightAssistAppend).append(html);
                                uploadFileCommon(commonField.specialCaseFile + number, AssessDBKey.SurveyAssetInventoryRightRecord, item.id);
                                showFileCommon(commonField.specialCaseFile + number, AssessDBKey.SurveyAssetInventoryRightRecord, item.id);
                                $("#" + commonField.inventoryFrm + number).find('[name=inventoryRightRecordId]').val(item.id);
                                $("#" + commonField.surveyFrm + number).initForm(item);
                                $("#" + commonField.surveyFrm + number).find("select[name='recordIds']").val(item.recordIds.split(",")).trigger("change").select2();
                                loadAssetRightList(number, item.id);
                            });
                        }else {
                            flag = false;
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        }
        if (!flag){
            var html = $("#" + commonField.taskRightAssistDiv).html();
            var number = commonField.getNumber();
            html = html.replace(/_number/g, number);
            $("#" + commonField.taskRightAssistAppend).append(html);
            $.ajax({
                url: "${pageContext.request.contextPath}/surveyAssetInventoryRightRecord/save",
                type: "post",
                dataType: "json",
                data: {
                    projectId: '${projectPlanDetails.projectId}',
                    planDetailsId: '${projectPlanDetails.id}'
                },
                success: function (result) {
                    if (result.ret) {
                        uploadFileCommon(commonField.specialCaseFile + number, AssessDBKey.SurveyAssetInventoryRightRecord, result.data.id);
                        showFileCommon(commonField.specialCaseFile + number, AssessDBKey.SurveyAssetInventoryRightRecord, result.data.id);
                        $("#" + commonField.inventoryFrm + number).find('[name=inventoryRightRecordId]').val(result.data.id);
                        $("#" + commonField.surveyFrm + number).find("select[name='recordIds']").select2();
                        $("#" + commonField.surveyFrm + number).initForm(result.data);
                        loadAssetRightList(number, result.data.id);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        }
    }

    /**
     * 添加模型后对模型中的数据显示做必要的处理
     * @param _this
     */
    function addData(_this, value) {
        var form = $("#" + commonField.inventoryFrm + value);
        var inventoryRightRecordId = form.find('[name=inventoryRightRecordId]').val();
        form.clearAll();
        form.find('[name=inventoryRightRecordId]').val(inventoryRightRecordId);
        uploadFileCommon(commonField.inventoryRightFile + value, AssessDBKey.SurveyAssetInventoryRight, 0);
        showFileCommon(commonField.inventoryRightFile + value, AssessDBKey.SurveyAssetInventoryRight, 0);
        AssessCommon.loadProjectClassifyListByField(AssessProjectClassifyKey.singleHousetaskRightCategory, null, function (html, data) {
            form.find("select[name='category']").empty().html(html).trigger('change');
        });
        //日期触发
        DatepickerUtils.parse();
    }

    /**
     * @author:  zch
     * 描述:编辑
     * @date:
     **/
    function editData(_this, value) {
        var x_content = $(_this).closest(".x_content");
        var form = $("#" + commonField.inventoryFrm + value);
        var rows = $("#" + commonField.tbList + value).bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            toastr.info("请选择要编辑的数据");
        } else if (rows.length == 1) {
            $.ajax({
                url: "${pageContext.request.contextPath}/surveyAssetInventoryRight/get",
                type: "get",
                dataType: "json",
                data: {id: rows[0].id},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        form.clearAll();
                        $("#" + commonField.divBox + value).modal("show");
                        form.initForm(data);
                        AssessCommon.loadProjectClassifyListByField(AssessProjectClassifyKey.singleHousetaskRightCategory, data.category, function (html, item) {
                            form.find("select[name='category']").empty().html(html).trigger('change');
                        });
                        form.find("input[name='id']").val(data.id);
                        form.find("input[name='registerDate']").val(formatDate(data.registerDate, false));
                        form.find("input[name='beginDate']").val(formatDate(data.beginDate, false));
                        form.find("input[name='endDate']").val(formatDate(data.endDate, false));
                        uploadFileCommon(commonField.inventoryRightFile + value, AssessDBKey.SurveyAssetInventoryRight, data.id);
                        showFileCommon(commonField.inventoryRightFile + value, AssessDBKey.SurveyAssetInventoryRight, data.id);
                    }
                }
            })
        } else {
            toastr.info("只能选择一行数据进行编辑");
        }
    }

    /**
     * @author:  zch
     * 描述:删除
     * @date:
     **/
    function delData(_this, value) {
        var x_content = $(_this).closest(".x_content");
        var form = x_content.find("form").eq(0);
        var rows = $("#" + commonField.tbList + value).bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            toastr.info("请选择要删除的数据");
        } else {
            var idArray = [];
            $.each(rows, function (i, item) {
                idArray.push(item.id);
            });
            Alert("确认要删除么？", 2, null, function () {
                Loading.progressShow();
                $.ajax({
                    url: "${pageContext.request.contextPath}/surveyAssetInventoryRight/delete",
                    type: "post",
                    dataType: "json",
                    data: {id: idArray.join()},
                    success: function (result) {
                        Loading.progressHide();
                        if (result.ret) {
                            toastr.success('删除成功');
                            loadAssetRightList(value, form.find("input[name='id']").val());
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
    }

    /**
     * 清除html
     * @param _this
     */
    function cleanHTMLData(_this) {
        var x_content = $(_this).closest(".x_content");
        var div = x_content.prev();
        var form = x_content.find("form").eq(0);
        $.ajax({
            url: "${pageContext.request.contextPath}/surveyAssetInventoryRightRecord/remove",
            type: "post",
            dataType: "json",
            data: {id: form.find("input[name='id']").val()},
            success: function (result) {
                if (result.ret) {
                    x_content.remove();
                    div.remove();
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }

    /**
     * 保存数据
     * @param _this
     * @returns {boolean}
     */
    function saveData(_this, number) {
        var form = $(_this).parent().parent().find("form").eq(0);
        var frm = commonField.inventoryFrm + number;
        if (!$("#" + frm).valid()) {
            return false;
        }
        var data = formParams(frm);
        data.type = '${projectInfo.projectCategoryId}';
        data.projectId = '${projectInfo.id}';
        data.planDetailsId = '${projectPlanDetails.id}';
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/surveyAssetInventoryRight/save",
            type: "post",
            dataType: "json",
            data: {formData: JSON.stringify(data)},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('保存成功');
                    $("#" + commonField.divBox + number).modal("hide");
                    loadAssetRightList(number, data.inventoryRightRecordId);
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }

    function importRightData(_this,number) {
        var ajaxFileUpload = commonField.ajaxFileUpload + number;
        var item = formSerializeArray($("#" + commonField.surveyFrm + number));
        Loading.progressShow();
        $.ajaxFileUpload({
            type: "POST",
            url: "${pageContext.request.contextPath}/surveyAssetInventoryRight/importData",
            data: {
                projectId: '${projectId}',
                certName: '${declareRecord.name}',
                planDetailsId: ' ${projectPlanDetails.id}'
            },//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: ajaxFileUpload,//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert(result.data.replace(/\n/g, '<br/>'));
                    loadAssetRightList(number,item.id);
                } else {
                    Alert("导入数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result, status, e) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }

    function saveSurveyAssetInventoryRightRecord(flag, number) {
        if (flag) {
            var item = formSerializeArray($("#" + commonField.surveyFrm + number));
            var data = [];
            data.push(item);
            $.ajax({
                url: "${pageContext.request.contextPath}/surveyAssetInventoryRightRecord/saveFormData",
                type: "post",
                dataType: "json",
                data: {formData: JSON.stringify(data)},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        }
    }

    $(document).ready(function () {
        //a first load
        appendHtml(true);
    });


    function submit(mustUseBox) {
        // if (!$("#frm_asset").valid()) {
        //     return false;
        // }
        var formData = JSON.stringify({});

        if ("${processInsId}" != "0") {
            submitEditToServer(formData);
        }
        else {
            submitToServer(formData, mustUseBox);
        }
    }


</script>


</html>

