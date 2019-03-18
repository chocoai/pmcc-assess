<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<script type="text/html" id="taskRightAssistDiv">
    <div class="x_content">
        <div class="btn-group">
            <button type="button" class="btn btn-success" onclick="addData(this)"
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
        <table class="table table-bordered" id="tb_List_number">
            <!-- cerare document add ajax data-->
        </table>
        <form class="form-horizontal" id="surveyAssetInventoryRightFrm_number">
            <input type="hidden" name="id">
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">特殊情况</label>
                    <div class="col-sm-11">
                                    <textarea placeholder="特殊情况" class="form-control"
                                              name="specialCase"></textarea>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">申报</label>
                    <div class="col-sm-5">
                        <select class="form-control search-select select2"  multiple="multiple"  required="required" name="recordIds">
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
               onchange="importRightData(this);">
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
                        <input type="hidden" name="id" value="0">
                        <input type="hidden" name="inventoryRightRecordId">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-2 control-label">
                                    类别<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-4">
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
                    <button type="button" class="btn btn-primary" onclick="saveData(this)">
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
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>他项权利
                        <button class="btn btn-xs btn-success" onclick="appendHtml('',this)"><i class="fa fa-plus"></i>
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
    function loadAssetRightList(number) {
        var cols = [];
        cols.push({field: 'typeName', title: '类型'});
        cols.push({field: 'categoryName', title: '类别'});
        cols.push({field: 'number', title: '他权证编号'});
        cols.push({field: 'obligor', title: '义务人'});
        cols.push({field: 'obligee', title: '权利人'});
        cols.push({field: 'registerArea', title: '登记面积'});
        cols.push({field: 'rightRank', title: '他权级次'});
        $("#tb_List" + number).bootstrapTable('destroy');
        TableInit("tb_List" + number, "${pageContext.request.contextPath}/surveyAssetInventoryRight/getListByPlanDetailsId", cols, {
            planDetailsId: '${projectPlanDetails.id}'
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        });
    }

    /**
     * 添加html,并且替换
     * @returns {*|jQuery}
     */
    function appendHtml() {
        var html = $("#taskRightAssistDiv").html();
        var number = parseInt(Math.random() * (999999 - 100000) + 100000, 10) + Math.round(Math.random() * 10);
        html = html.replace(/_number/g, number);
        $("#taskRightAssistAppend").append(html);
        $.ajax({
            url: "${pageContext.request.contextPath}/surveyAssetInventoryRightRecord/save",
            type: "post",
            dataType: "json",
            data: {
                projectId:'${projectPlanDetails.projectId}',
                planDetailsId:'${projectPlanDetails.id}'
            },
            success: function (result) {
                if (result.ret) {
                    uploadFileCommon("specialCaseFile" + number, AssessDBKey.SurveyAssetInventoryRightRecord, result.data.id);
                    showFileCommon("specialCaseFile" + number, AssessDBKey.SurveyAssetInventoryRightRecord,result.data.id);
                    $("#frm_inventory_right" + number).find('[name=inventoryRightRecordId]').val(result.data.id);
                    $("#surveyAssetInventoryRightFrm" + number).find("select[name='recordIds']").select2();
                    $("#surveyAssetInventoryRightFrm" + number).find('[name=id]').val(result.data.id);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
        loadAssetRightList(number);
    }

    /**
     * 添加模型后对模型中的数据显示做必要的处理
     * @param _this
     */
    function addData(_this) {
        var text = $(_this).attr("data-target");
        var value = text.replace(/[^0-9]/ig, "");
        var inventoryRightRecordId = $("#frm_inventory_right" + value).find('[name=inventoryRightRecordId]').val();
        $("#frm_inventory_right" + value).clearAll();
        $("#frm_inventory_right" + value).find('[name=id]').val(0);
        $("#frm_inventory_right" + value).find('[name=inventoryRightRecordId]').val(inventoryRightRecordId);
        uploadFileCommon("inventoryRightFile" + value, AssessDBKey.SurveyAssetInventoryRight, 0);
        showFileCommon("inventoryRightFile" + value, AssessDBKey.SurveyAssetInventoryRight, 0);
    }

    /**
     * 清除html
     * @param _this
     */
    function cleanHTMLData(_this) {
        var x_content = $(_this).closest(".x_content");
        var div = x_content.prev();
        x_content.remove();
        div.remove();
    }

    function saveData(_this) {

    }

    function importRightData(_this) {

    }

    $(document).ready(function () {
        //a first load
        appendHtml();
    });

    //提交
    function submit(flag) {
        if (flag){
            taskExploreIndex.checkAssignmentTask(true);
        }else {
            taskExploreIndex.checkAssignmentTask(false);
        }
    }
</script>


</html>

