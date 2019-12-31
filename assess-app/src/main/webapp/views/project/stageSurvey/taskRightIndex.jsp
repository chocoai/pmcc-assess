<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<div id="divBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">他项权利</h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="projectId">
                    <input type="hidden" name="planDetailsId">
                    <input type="hidden" name="groupId">
                    <input type="hidden" name="id" value="0">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                类别<span class="symbol required"></span>
                            </label>
                            <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                <select class="form-control" required name="category"
                                        onchange="changeRemark(this)">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">他项权力描述<span
                                    class="symbol required"></span></label>
                            <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                <textarea class="form-control" required="required" name="remark"></textarea>
                            </div>
                        </div>
                    </div>
                    <c:if test="${projectInfo.entrustPurpose == pledgeId}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">对变现能力的影响<span
                                        class="symbol required"></span></label>
                                <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <textarea class="form-control" required="required"
                                                      name="influence"></textarea>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                他权证编号
                            </label>
                            <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                <input type="text" placeholder="他权证编号" name="number" class="form-control">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">登记日期</label>
                            <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                <input placeholder="登记日期" name="registerDate"
                                       data-date-format="yyyy-mm-dd"
                                       class="form-control date-picker dbdate" readonly="readonly">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                义务人
                            </label>
                            <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                <input type="text" placeholder="义务人" name="obligor" class="form-control">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                权利人
                            </label>
                            <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                <input type="text" placeholder="权利人" name="obligee" class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                登记金额
                            </label>
                            <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                <input type="text" placeholder="登记金额" data-rule-number='true'
                                       name="registerAmount" class="form-control">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                行权金额
                            </label>
                            <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                <input type="text" placeholder="行权金额" data-rule-number='true'
                                       name="actualAmount" class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                登记面积
                            </label>
                            <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                <input type="text" placeholder="登记面积" data-rule-number='true'
                                       name="registerArea" class="form-control">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                他权级次
                            </label>
                            <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                <input type="text" placeholder="他权级次"
                                       name="rightRank" class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">开始日期</label>
                            <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                <input placeholder="开始日期"
                                       name="beginDate" data-date-format="yyyy-mm-dd"
                                       class="form-control date-picker dbdate" readonly="readonly">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">结束日期</label>
                            <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                <input placeholder="结束日期"
                                       name="endDate" data-date-format="yyyy-mm-dd"
                                       class="form-control date-picker dbdate" readonly="readonly">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                附件
                            </label>
                            <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                <input id="inventoryRightFile" type="file" multiple="false">
                                <div id="_inventoryRightFile"></div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="saveData(this)">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<script type="text/html" id="taskRightAssistDiv">
    <div class="x_panel">
        <div class="x_title">
            <h3>他权分组（0{index}）
                <small>
                    <a href="javascript://;" class="btn btn-xs btn-warning"
                       onclick="cleanHTMLData(this,'_number')">移除</a>
                </small>
            </h3>
        </div>
        <div class="x_content">
            <div class="form-horizontal">
                <div class="form-group">
                    <div class="col-sm-offset-1  col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                        <div class="row">
                            <div class="col-xs-1  col-sm-1  col-md-1  col-lg-1">
                                <div class="btn-group">
                                    <input type="hidden" name="groupId" value="_number">
                                    <button class="btn-primary btn" type="button"
                                            onclick="declareRecordModeObj.init({callback:selectRecord,this_:this});">
                                        选择权证
                                    </button>
                                </div>
                            </div>

                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                权证名称
                            </label>
                            <div class="col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                                <input type="text" placeholder="权证名称" name="declareName" class="form-control">
                            </div>
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                楼栋号
                            </label>
                            <div class="col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                                <input type="text" placeholder="楼栋号" name="buildingNumber" class="form-control">
                            </div>
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                单元号
                            </label>
                            <div class="col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                                <input type="text" placeholder="单元号" name="unitNumber" class="form-control">
                            </div>
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                坐落
                            </label>
                            <div class="col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                                <input type="text" placeholder="坐落" name="seat" class="form-control">
                            </div>

                            <div class="col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                                <button type="button" class="btn btn-primary"
                                        onclick="queryDeclareRecordTable(this,true);" aria-expanded="false">
                                    查询 <i class="fa fa-search"></i>
                                </button>
                            </div>

                            <div class="col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                                <button type="button" class="btn btn-primary"
                                        onclick="queryDeclareRecordTable(this,false);" aria-expanded="false">
                                    重置 <i class="fa fa-paper-plane"></i>
                                </button>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">权证信息<span
                                class="symbol required"></span></label>
                        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                            <table class="table table-bordered" id="tb_List_recordTable_number">
                                <!-- cerare document add ajax data-->
                            </table>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-1  col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                        <div class="btn-group">
                            <button type="button" class="btn btn-success" onclick="addData(this,'_number')"> 新增
                            </button>
                        </div>
                        <div class="btn-group">
                            <button type="button" class="btn btn-primary"
                                    onclick="editData(this,'_number')" aria-expanded="false">
                                编辑
                            </button>
                        </div>
                        <div class="btn-group">
                            <button type="button" class="btn btn-warning"
                                    onclick="delData(this,'_number')" aria-expanded="false">
                                删除
                            </button>
                        </div>
                        <div class="btn-group">
                            <button type="button" class="btn btn-warning"
                                    onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftAssetInventoryRight)"
                                    aria-expanded="false">
                                下载模板
                            </button>
                        </div>
                        <div class="btn-group">
                            <button type="button" class="btn btn-primary"
                                    onclick="$('#ajaxFileUpload_number').val('').trigger('click')"> 导入
                            </button>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">他权明细<span
                                class="symbol required"></span></label>
                        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                            <table class="table table-bordered" id="tb_List_number">
                                <!-- cerare document add ajax data-->
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <input type="file" id="ajaxFileUpload_number" name="file"
                   onchange="importRightData('_number');" style="display: none;">
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
                <div>
                    <h3>他项权利
                        <small>
                            <a href="javascript://;" class="btn btn-xs btn-success" onclick="appendHtml(false)">添加分组<i
                                    class="fa fa-plus"></i>
                            </a>
                        </small>
                    </h3>
                </div>
            </div>
            <div id="taskRightAssistAppend"></div>
            <%@include file="/views/share/form_apply.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>

</body>
<%@include file="/views/share/main_footer.jsp" %>
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
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
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
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg);
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
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
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
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
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
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
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
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
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
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
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
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
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
        cols.push({field: 'unitNumber', title: '单元号', width: "6%"});
        cols.push({field: 'ownership', title: '所有权人', width: "6%"});
        cols.push({field: 'seat', title: '坐落', width: "29%"});
        cols.push({
            field: 'id', title: '操作', width: 200, formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success" data-title="删除" href="javascript:removeDataDeclareRecord(' + row.id + ",'" + row.groupId + "'" + ');" ><i class="fa fa-remove">删除</i></a>';
                str += '</div>';
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
            groupId: groupId,planDetailsId:'${projectPlanDetails.id}'
        });
    }

    function queryDeclareRecordTable(_this,flag) {
        var group = $(_this).closest(".form-group");
        if (flag){
            var select = {groupId:group.find("input[name='groupId']").val(),planDetailsId:'${projectPlanDetails.id}'};
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
            loadDeclareRecordTable2(select.groupId,select) ;
        }else {
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
                appendHtml2(data);
            });
        }
    }

    /**
     * 替换关键数据
     */
    function appendHtml2(data) {
        var target = $("#" + commonField.taskRightAssistAppend);
        var index = target.find(".x_panel").size();
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
            toastr.info("请选择要编辑的数据");
        } else if (rows.length == 1) {
            box.modal("show");
            initForm(data = rows[0], box.find("form"));
        } else {
            toastr.info("只能选择一行数据进行编辑");
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
            toastr.info("请选择要删除的数据");
        } else {
            var idArray = [];
            $.each(rows, function (i, item) {
                idArray.push(item.id);
            });
            Alert("确认要删除么？", 2, null, function () {
                deleteSurveyAssetRightItemById(idArray.join(","), function () {
                    toastr.success('删除成功');
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
        var x_panel = $(_this).closest(".x_panel");
        deleteSurveyAssetRightGroupById(groupId, function () {
            x_panel.remove();
            toastr.success('移除成功');
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
            toastr.success('保存成功');
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
                    Alert(result.data);
                    loadAssetRightList(groupId);
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

