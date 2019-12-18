<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<script type="text/html" id="taskRightAssistDiv">
    <div class="x_panel">
        <div class="x_title">
            <h3>他权分组（0{index}）
            </h3>
        </div>
        <div class="x_content">
            <div class="form-horizontal">
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

            <div id="taskRightAssistAppend"></div>


            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
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
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
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



    function saveform() {
        saveApprovalform("");
    }
</script>
</html>
