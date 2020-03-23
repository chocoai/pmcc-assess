<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<div id="divBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">他项权利</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="projectId">
                    <input type="hidden" name="planDetailsId">
                    <input type="hidden" name="groupId">
                    <input type="hidden" name="id" value="0">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                类别<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-11">
                                                <select class="form-control input-full" required name="category"
                                                        onchange="changeRemark(this)">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">他项权力描述<span
                                                    class="symbol required"></span></label>
                                            <div class="col-sm-11">
                                                <textarea class="form-control input-full" required="required"
                                                          name="remark"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <c:if test="${projectInfo.entrustPurpose == pledgeId}">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">对变现能力的影响<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-11">
                                            <textarea class="form-control input-full" required="required"
                                                      name="influence"></textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                他权证编号
                                            </label>
                                            <div class="col-sm-5">
                                                <input type="text" placeholder="他权证编号" name="number"
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1 control-label">登记日期</label>
                                            <div class="col-sm-5">
                                                <input placeholder="登记日期" name="registerDate"
                                                       data-date-format="yyyy-mm-dd"
                                                       class="form-control input-full date-picker dbdate"
                                                       readonly="readonly">
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                义务人
                                            </label>
                                            <div class="col-sm-5">
                                                <input type="text" placeholder="义务人" name="obligor"
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                权利人
                                            </label>
                                            <div class="col-sm-5">
                                                <input type="text" placeholder="权利人" name="obligee"
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                登记金额
                                            </label>
                                            <div class="col-sm-5">
                                                <input type="text" placeholder="登记金额" data-rule-number='true'
                                                       name="registerAmount" class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                行权金额
                                            </label>
                                            <div class="col-sm-5">
                                                <input type="text" placeholder="行权金额" data-rule-number='true'
                                                       name="actualAmount" class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                登记面积
                                            </label>
                                            <div class="col-sm-5">
                                                <input type="text" placeholder="登记面积" data-rule-number='true'
                                                       name="registerArea" class="form-control input-full">
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                他权级次
                                            </label>
                                            <div class="col-sm-5">
                                                <input type="text" placeholder="他权级次"
                                                       name="rightRank" class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">开始日期</label>
                                            <div class="col-sm-5">
                                                <input placeholder="开始日期"
                                                       name="beginDate" data-date-format="yyyy-mm-dd"
                                                       class="form-control input-full date-picker dbdate"
                                                       readonly="readonly">
                                            </div>
                                            <label class="col-sm-1 control-label">结束日期</label>
                                            <div class="col-sm-5">
                                                <input placeholder="结束日期"
                                                       name="endDate" data-date-format="yyyy-mm-dd"
                                                       class="form-control input-full date-picker dbdate"
                                                       readonly="readonly">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                附件
                                            </label>
                                            <div class="col-sm-11">
                                                <input id="inventoryRightFile" type="file" multiple="false">
                                                <div id="_inventoryRightFile"></div>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="saveData(this)">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<script type="text/html" id="taskRightAssistDiv">
    <div class="col-md-12">
        <div class="card full-height">
            <div class="card-header collapse-link">
                <div class="card-head-row">

                    <div class="card-title">
                        他权分组（0{index}）
                    </div>
                    <div class="card-tools">
                        <%--<button type="button" class="btn btn-warning btn-sm" onclick="cleanHTMLData(this,'_number')"><i--%>
                                <%--class="fa fa-minus"></i></button>--%>
                    </div>
                    <button type="button" class="btn btn-warning btn-sm" onclick="cleanHTMLData(this,'_number')"><i
                            class="fa fa-minus"></i></button>

                </div>
            </div>
            <div class="card-body">
                <form class="form-horizontal">
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline">
                                <div class="btn-group">
                                    <input type="hidden" name="groupId" value="_number">

                                </div>
                                <label class="col-sm-1 control-label">权证名称</label>
                                <div class="col-sm-1">
                                    <input type="text" placeholder="权证名称" name="declareName"
                                           class="form-control input-full">
                                </div>
                                <label class="col-sm-1 control-label">
                                    楼栋号
                                </label>
                                <div class="col-sm-1">
                                    <input type="text" placeholder="楼栋号" name="buildingNumber"
                                           class="form-control input-full">
                                </div>
                                <label class="col-sm-1 control-label">
                                    单元号
                                </label>
                                <div class="col-sm-1">
                                    <input type="text" placeholder="单元号" name="unitNumber"
                                           class="form-control input-full">
                                </div>

                                <label class="col-sm-1 control-label">
                                    坐落
                                </label>
                                <div class="col-sm-1">
                                    <input type="text" placeholder="坐落" name="seat" class="form-control input-full">
                                </div>
                                <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                        onclick="queryDeclareRecordTable(this,true);">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                    查询
                                </button>
                                <button class="btn-primary btn btn-sm" style="margin-left: 5px;" type="button"
                                        onclick="declareRecordModeObj.init({callback:selectRecord,this_:this});">
                                    <span class="btn-label"><i class="fa fa-grip-vertical"></i></span>选择权证
                                </button>
                                <button type="button" class="btn btn-primary btn-sm" style="margin-left: 5px;"
                                        onclick="queryDeclareRecordTable(this,false);" aria-expanded="false">
                                    <span class="btn-label"><i class="fa fa-undo-alt"></i></span>重置
                                </button>
                            </div>
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">权证信息<span
                                        class="symbol required"></span></label>
                                <div class="col-sm-11">
                                    <table class="table table-bordered" id="tb_List_recordTable_number">
                                        <!-- cerare document add ajax data-->
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <label class="col-sm-1 control-label"></label>
                        <div class="col-sm-11">
                            <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                    data-toggle="modal" onclick="addData(this,'_number')"><span class="btn-label"><i
                                    class="fa fa-plus"></i></span>新增
                            </button>
                            <button type="button" class="btn btn-primary btn-sm" onclick="editData(this,'_number')"
                                    aria-expanded="false"><span class="btn-label"><i class="fa fa-pen"></i></span>编辑
                            </button>
                            <button type="button" class="btn btn-warning btn-sm" onclick="delData(this,'_number')"
                                    aria-expanded="false"><span class="btn-label"><i class="fa fa-minus"></i></span>删除
                            </button>
                            <button type="button" class="btn btn-success btn-sm"
                                    onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftAssetInventoryRight)"
                                    aria-expanded="false"><span class="btn-label"><i
                                    class="fa fa-cloud-download-alt"></i></span>下载模板
                            </button>
                            <button type="button" class="btn btn-primary btn-sm"
                                    onclick="$('#ajaxFileUpload_number').val('').trigger('click')"><span
                                    class="btn-label"><i class="fa fa-cloud-upload-alt"></i></span>导入
                            </button>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-sm-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 control-label">他权明细<span
                                        class="symbol required"></span></label>
                                <div class="col-sm-11">
                                    <table class="table table-bordered" id="tb_List_number">
                                        <!-- cerare document add ajax data-->
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>

                    <input type="file" id="ajaxFileUpload_number" name="file"
                           onchange="importRightData('_number');" style="display: none;">
                </form>
            </div>
        </div>
    </div>
</script>
<body>

<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <%@include file="/views/share/project/projectInfoSimple.jsp" %>
                    <%@include file="/views/share/project/projectPlanDetails.jsp" %>


                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header ">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        他项权利
                                        <small>
                                            <button type="button" class="btn btn-sm btn-success"
                                                    onclick="appendHtml(false)">添加分组
                                            </button>
                                        </small>
                                    </div>
                                </div>
                            </div>

                            <div class="card-body">

                                <div id="taskRightAssistAppend"></div>
                            </div>
                        </div>
                    </div>


                    <%@include file="/views/share/form_apply.jsp" %>
                    <%--<%@include file="/views/share/form_log.jsp" %>--%>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>

</body>
<%@include file="/views/project/tool/declareRecordModeView.jsp" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
<script type="text/javascript">

    var commonField = {
        tbList: "tb_List",
        recordTable: "tb_List_recordTable",
        divBox: "divBox",
        inventoryRightFile: "inventoryRightFile",
        specialCaseFile: "specialCaseFile",
        taskRightAssistAppend: "taskRightAssistAppend",
        taskRightAssistDiv: "taskRightAssistDiv",
        ajaxFileUpload: "ajaxFileUpload"
    };


    //上传附件通用
    function uploadFileCommon(fieldsName, tableName, id) {
        if (!id) {
            id = 0;
        }
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
        if (!id) {
            id = 0;
        }
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

    function saveSurveyAssetRightItem(data, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/surveyAssetRightItem/saveAndUpdateSurveyAssetRightItemAll",
            type: "post",
            dataType: "json",
            data: {fomData: JSON.stringify(data), updateNull: true},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }

    function saveSurveyAssetRightGroup(data, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/surveyAssetRightGroup/saveAndUpdateSurveyAssetRightGroupAll",
            type: "post",
            dataType: "json",
            data: {fomData: JSON.stringify(data), updateNull: false},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    console.log(data);
                    console.log(result.errmsg);
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }

    function saveSurveyAssetRightDeclare(data, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/surveyAssetRightDeclare/saveAndUpdateSurveyAssetRightDeclareAll",
            type: "post",
            dataType: "json",
            data: {fomData: JSON.stringify(data), updateNull: false},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }


    function deleteSurveyAssetRightGroupById(id, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/surveyAssetRightGroup/deleteSurveyAssetRightGroupById",
            type: "post",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }

    function deleteSurveyAssetRightItemById(id, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/surveyAssetRightItem/deleteSurveyAssetRightItemById",
            type: "post",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }

    function deleteSurveyAssetRightDeclareById(id, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/surveyAssetRightDeclare/deleteSurveyAssetRightDeclareById",
            type: "post",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }

    function getSurveyAssetRightGroupListByExample(data, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/surveyAssetRightGroup/getSurveyAssetRightGroupListByExample",
            type: "get",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }

    function selectRecord(_this, id) {
        var group = $(_this).closest(".form-group");
        var groupId = group.find("input[name='groupId']").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/declareRecord/getDeclareRecordListByIds",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var arr = [];
                    $.each(result.data, function (i, item) {
                        var obj = {declareId: item.id, declareName: item.name};
                        obj.buildingNumber = item.buildingNumber;
                        obj.unitNumber = item.unit;
                        obj.ownership = item.ownership;
                        obj.seat = item.seat;
                        obj.groupId = groupId;
                        obj.planDetailsId = '${projectPlanDetails.id}';
                        arr.push(obj);
                    });
                    if (arr.length >= 1) {
                        saveSurveyAssetRightDeclare(arr, function () {
                            loadDeclareRecordTable(groupId);
                        });
                    }
                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }

    function removeDataDeclareRecord(id, groupId) {
        deleteSurveyAssetRightDeclareById(id, function () {
            loadDeclareRecordTable(groupId);
        });
    }

    function loadDeclareRecordTable2(groupId, data) {
        var target = $("#" + commonField.recordTable + groupId);
        var cols = [];
        cols.push({field: 'declareName', title: '权证名称', width: "22%"});
        cols.push({field: 'buildingNumber', title: '楼栋号', width: "16%"});
        cols.push({field: 'unitNumber', title: '单元号', width: "10%"});
        cols.push({field: 'ownership', title: '所有权人', width: "10%"});
        cols.push({field: 'seat', title: '坐落', width: "29%"});
        cols.push({
            field: 'id', title: '操作', width: "10%", formatter: function (value, row, index) {
                var str = '<button type="button" onclick="removeDataDeclareRecord(' + row.id + ",'" + row.groupId + "'" + ')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
                return str;
            }
        });
        target.bootstrapTable('destroy');
        TableInit(target, "${pageContext.request.contextPath}/surveyAssetRightDeclare/getBootstrapTableVo", cols, data, {
            method: "get",
            showColumns: false,
            showRefresh: false,
            search: false,
            striped: true,
            onLoadSuccess: function () {

            }
        });
        //隐藏正在加载 正在努力地加载数据中，请稍候……
        target.bootstrapTable('hideLoading');
    }

    function loadDeclareRecordTable(groupId) {
        loadDeclareRecordTable2(groupId, {
            groupId: groupId, planDetailsId: '${projectPlanDetails.id}'
        });
    }

    function queryDeclareRecordTable(_this, flag) {
        var group = $(_this).closest(".form-group");
        if (flag) {
            var select = {
                groupId: group.find("input[name='groupId']").val(),
                planDetailsId: '${projectPlanDetails.id}'
            };
            var declareName = group.find("input[name='declareName']").val();
            if (declareName) {
                select.declareName = declareName;
            }
            var buildingNumber = group.find("input[name='buildingNumber']").val();
            if (buildingNumber) {
                select.buildingNumber = buildingNumber;
            }
            var unitNumber = group.find("input[name='unitNumber']").val();
            if (unitNumber) {
                select.unitNumber = unitNumber;
            }
            var seat = group.find("input[name='seat']").val();
            if (seat) {
                select.seat = seat;
            }
            loadDeclareRecordTable2(select.groupId, select);
        } else {
            group.find("input[name='declareName']").val('');
            group.find("input[name='buildingNumber']").val('');
            group.find("input[name='unitNumber']").val('');
            group.find("input[name='seat']").val('');
        }
    }


    //加载 他项权利列表
    function loadAssetRightList(groupId) {
        var cols = [];
//        cols.push({field: 'typeName', title: '类型'});
        cols.push({field: 'categoryName', title: '类别'});
        cols.push({field: 'remark', title: '他项权利描述', width: '40%'});
        cols.push({field: 'number', title: '他权证编号'});
        cols.push({field: 'obligor', title: '义务人'});
        cols.push({field: 'obligee', title: '权利人'});
        cols.push({field: 'registerArea', title: '登记面积'});
        cols.push({field: 'rightRank', title: '他权级次'});
        $("#" + commonField.tbList + groupId).bootstrapTable('destroy');
        TableInit(commonField.tbList + groupId, "${pageContext.request.contextPath}/surveyAssetRightItem/getBootstrapTableVo", cols, {
            projectId: '${projectPlanDetails.projectId}', groupId: groupId
        }, {
            method: "get",
            showColumns: false,
            showRefresh: false,
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
        //获取数据并赋值
        if (flag) {
            getSurveyAssetRightGroupListByExample({
                projectId: '${projectPlanDetails.projectId}',
                planDetailsId: '${projectPlanDetails.id}'
            }, function (data) {
                if (data.length >= 1) {
                    appendHtml2(data);
                } else {
                    appendHtml(data.length >= 1);
                }
            });
        }
        //添加 html
        if (!flag) {
            saveSurveyAssetRightGroup([{
                projectId: '${projectPlanDetails.projectId}',
                planDetailsId: '${projectPlanDetails.id}'
            }], function (data) {
                notifySuccess("成功", "添加他权分组成功!");
                appendHtml2(data);
            });
        }
    }

    /**
     * 替换关键数据
     */
    function appendHtml2(data) {
        var target = $("#" + commonField.taskRightAssistAppend);
        var index = target.find(".card").size();
        $.each(data, function (i, item) {
            var html = $("#" + commonField.taskRightAssistDiv).html();
            html = html.replace(/_number/g, item.id).replace(/{index}/g, index + i + 1);
            target.append(html);
            uploadFileCommon(commonField.specialCaseFile + item.id, AssessDBKey.SurveyAssetRightGroup, item.id);
            showFileCommon(commonField.specialCaseFile + item.id, AssessDBKey.SurveyAssetRightGroup, item.id);
            loadAssetRightList(item.id);
            loadDeclareRecordTable(item.id);
        });
    }

    /**
     * 添加模型后对模型中的数据显示做必要的处理
     * @param _this
     * @param groupId
     */
    function addData(_this, groupId) {
        var box = $("#" + commonField.divBox);
        box.modal("show");
        initForm({groupId: groupId}, box.find("form"));
    }

    /*赋值*/
    function initForm(data, form) {
        form.clearAll();
        form.initForm(data);
        uploadFileCommon(commonField.inventoryRightFile, AssessDBKey.SurveyAssetRightItem, data.id);
        showFileCommon(commonField.inventoryRightFile, AssessDBKey.SurveyAssetRightItem, data.id);
        AssessCommon.loadDataDicByKey(AssessDicKey.houseInventoryRightCategory, data.category, function (html) {
            form.find("select[name='category']").empty().html(html).trigger('change');
        });
        form.find("input[name='registerDate']").val(formatDate(data.registerDate, false));
        form.find("input[name='beginDate']").val(formatDate(data.beginDate, false));
        form.find("input[name='endDate']").val(formatDate(data.endDate, false));
        //日期触发
        DatepickerUtils.parse();
        form.validate();
    }

    /**
     * @author:  zch
     * 描述:编辑
     * @date:
     **/
    function editData(_this, value) {
        var box = $("#" + commonField.divBox);
        var rows = $("#" + commonField.tbList + value).bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            notifyInfo('提示', "请选择要编辑的数据");
        } else if (rows.length == 1) {
            box.modal("show");
            initForm(data = rows[0], box.find("form"));
        } else {
            notifyInfo('提示', "只能选择一行数据进行编辑");
        }
    }

    function changeRemark(that) {
        var form = $(that).closest("form");
        form.find("textarea[name='remark']").val($(that).find('option:selected').attr('title'));
    }

    /**
     * @author:  zch
     * 描述:删除
     * @date:
     **/
    function delData(_this, value) {
        var rows = $("#" + commonField.tbList + value).bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            notifyInfo('提示', "请选择要删除的数据");
        } else {
            var idArray = [];
            $.each(rows, function (i, item) {
                idArray.push(item.id);
            });
            AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
                deleteSurveyAssetRightItemById(idArray.join(","), function () {
                    notifySuccess("成功", "删除数据成功");
                    loadAssetRightList(value);
                });
            })
        }
    }

    /**
     * 清除html
     * @param _this
     * @param groupId
     */
    function cleanHTMLData(_this, groupId) {
        var x_panel = $(_this).closest(".card").parent();
        deleteSurveyAssetRightGroupById(groupId, function () {
            x_panel.remove();
            notifySuccess('成功', '移除成功');
        });
    }

    /**
     * 保存数据
     */
    function saveData() {
        var box = $("#" + commonField.divBox);
        var frm = box.find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        data.type = '${projectInfo.projectCategoryId}';
        data.projectId = '${projectInfo.id}';
        data.planDetailsId = '${projectPlanDetails.id}';
        saveSurveyAssetRightItem([data], function () {
            notifySuccess('成功', '保存成功');
            box.modal("hide");
            loadAssetRightList(data.groupId);
        });
    }

    function importRightData(groupId) {
        var ajaxFileUpload = commonField.ajaxFileUpload + groupId;
        Loading.progressShow();
        $.ajaxFileUpload({
            type: "POST",
            url: "${pageContext.request.contextPath}/surveyAssetRightItem/importData",
            data: {
                projectId: '${projectPlanDetails.projectId}',
                groupId: groupId,
                planDetailsId: ' ${projectPlanDetails.id}'
            },//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: ajaxFileUpload,//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    notifyInfo('提示', result.data);
                    loadAssetRightList(groupId);
                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result, status, e) {
                Loading.progressHide();
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }


    $(document).ready(function () {
        //a first load
        appendHtml(true);
    });


    function submit(mustUseBox) {
        var formData = JSON.stringify({});
        if ("${processInsId}" != "0") {
            submitEditToServer(formData);
        } else {
            submitToServer(formData, mustUseBox);
        }
    }


</script>


</html>

