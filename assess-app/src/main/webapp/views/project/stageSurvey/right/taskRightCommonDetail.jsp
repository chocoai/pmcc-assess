<%--
  Created by IntelliJ IDEA.
  User: zch
  Date: 2020-4-1
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<script type="text/html" id="taskRightAssistDiv">
    <div class="col-md-12">
        <div class="card full-height">
            <div class="card-header collapse-link">
                <div class="card-head-row">
                    <div class="card-title">
                        他权分组（0{index}）
                    </div>
                    <div class="card-tools">
                        <button type="button" class="btn  btn-link btn-primary btn-xs"><span
                                class="fa fa-angle-down"></span>
                        </button>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <form class="form-horizontal">

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
                </form>
            </div>
        </div>
    </div>
</script>


<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header ">
            <div class="card-head-row">
                <div class="card-title">
                    他项权利
                </div>
            </div>
        </div>
        <div class="card-body">
            <div id="taskRightAssistAppend"></div>
        </div>
    </div>
</div>


<script type="text/javascript">
    var commonField = {
        tbList: "tb_List",
        recordTable: "tb_List_recordTable",
        inventoryRightFile: "inventoryRightFile",
        specialCaseFile: "specialCaseFile",
        taskRightAssistAppend: "taskRightAssistAppend",
        taskRightAssistDiv: "taskRightAssistDiv"
    };


    //上传附件通用
    function uploadFileCommon(fieldsName, tableName, id) {
        if (! id){
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
        if (! id){
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
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }



    function loadDeclareRecordTable(groupId) {
        var target  =  $("#" + commonField.recordTable + groupId);
        var cols = [];
        cols.push({field: 'declareName', title: '权证名称', width: "12%"});
        cols.push({field: 'buildingNumber', title: '楼栋号', width: "6%"});
        cols.push({field: 'unitNumber', title: '单元号', width: "6%"});
        cols.push({field: 'ownership', title: '所有权人', width: "6%"});
        cols.push({field: 'seat', title: '坐落', width: "19%"});
        target.bootstrapTable('destroy');
        TableInit(target, "${pageContext.request.contextPath}/surveyAssetRightDeclare/getBootstrapTableVo", cols, {
            projectId: '${projectPlanDetails.projectId}', groupId: groupId
        }, {
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


    //加载 他项权利列表
    function loadAssetRightList(groupId) {
        var cols = [];
        cols.push({field: 'typeName', title: '类型'});
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
        });
    }




    /**
     * 替换关键数据
     */
    function appendHtml2(data) {
        var target = $("#" + commonField.taskRightAssistAppend) ;
        var index = target.find(".x_panel").size() ;
        $.each(data, function (i, item) {
            var html = $("#" + commonField.taskRightAssistDiv).html();
            html = html.replace(/_number/g, item.id).replace(/{index}/g,index+ i + 1);
            target.append(html);
            uploadFileCommon(commonField.specialCaseFile + item.id, AssessDBKey.SurveyAssetRightGroup, item.id);
            showFileCommon(commonField.specialCaseFile + item.id, AssessDBKey.SurveyAssetRightGroup, item.id);
            loadAssetRightList(item.id);
            loadDeclareRecordTable(item.id);
        });
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
                }
            });
        }
    }


    $(document).ready(function () {
        //a first load
        appendHtml(true);
    });

</script>