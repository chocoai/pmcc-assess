<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<script type="text/html" id="taskRightAssistDiv">
    <div class="x_panel">
        <div class="x_title">
            <h3>他权分组（0{index}）
                <small>
                    <a href="javascript://;" class="btn btn-xs btn-warning"
                       onclick="saveSurveyAssetInventoryRightRecord(true,'_number',null);">保存</a>
                    <a href="javascript://;" class="btn btn-xs btn-warning" onclick="cleanHTMLData(this)">移除</a>
                </small>
            </h3>
        </div>
        <div class="x_content">
            <form class="form-horizontal" id="surveyAssetInventoryRightFrm_number">
                <input type="hidden" name="id">
                <div class="form-group">
                    <div class="col-sm-offset-1  col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                        <div class="btn-group">
                            <input type="hidden" name="recordIds" data-count="_number">
                            <button class="btn-primary btn" type="button"
                                 onclick="declareRecordModeObj.init({callback:selectRecord,this_:this});">选择权证
                            </button>
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
                            <button type="button" class="btn btn-success" onclick="addData(this,'_number')"
                                    data-toggle="modal" data-target="#divBox_number"> 新增
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
            </form>
            <input type="file" id="ajaxFileUpload_number" name="file" style="display: none;"
                   onchange="importRightData(this,'_number');">
        </div>
        <div id="divBox_number" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
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
                        <form id="frm_inventory_right_number" class="form-horizontal">
                            <input type="hidden" name="inventoryRightRecordId">
                            <input type="hidden" name="id" value="0">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                        类别<span class="symbol required"></span>
                                    </label>
                                    <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                        <select class="form-control" required name="category"
                                                onchange="changeRemark(this,'_number')">
                                            <c:forEach var="item" items="${inventoryRightTypeList}">
                                                <option value="${item.id}">${item.name}</option>
                                            </c:forEach>
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
                                        <input type="text" placeholder="登记金额"
                                               name="registerAmount" class="form-control">
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                        行权金额
                                    </label>
                                    <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                        <input type="text" placeholder="行权金额"
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
                                        <input type="text" placeholder="登记面积"
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
            <div id="taskRightAssistAppend">

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
<%@include file="/views/project/tool/declareRecordModeView.jsp" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
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

    function selectRecord(_this, id) {
        var group = $(_this).closest(".form-group");
        var recordIds = group.find("input[name='recordIds']").val();
        var value = "";
        if (recordIds) {
            var arr = recordIds.split(",");
            arr.push(id);
            value = unique(arr).join(",");
        } else {
            value = id;
        }
        group.find("input[name='recordIds']").val(value);
        var number = group.find("input[name='recordIds']").attr("data-count");
        loadDeclareRecordTable(number, value, "#" + commonField.surveyFrm + number);
        saveSurveyAssetInventoryRightRecord(true,number,null) ;
    }

    function unique(arr) {
        if (!Array.isArray(arr)) {
            return;
        }
        var array = [];
        for (var i = 0; i < arr.length; i++) {
            if (array.indexOf(arr[i]) === -1) {
                array.push(arr[i]);
            }
        }
        return array;
    }

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

    function loadDeclareRecordTable(number, ids, item) {
        var tableId = "tb_List_recordTable" + number;
        $.ajax({
            url: "${pageContext.request.contextPath}/declareRecord/getDeclareRecordListByIds",
            type: "get",
            dataType: "json",
            data: {id: ids},
            success: function (result) {
                if (result.ret) {
                    loadDeclareRecordTable2($("#" + tableId), result.data, item);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }

    /**
     * 清除选择的权证
     */
    function removeDataDeclareRecord(id, item, tableId) {
        var table = $("#" + tableId);
        table.bootstrapTable('remove', {
            field: 'id',
            values: [id]
        });
        var data = table.bootstrapTable('getData');
        var target = $(item);
        var recordIds = target.find("input[name='recordIds']") ;
        var ids = [] ;
        $.each(data,function (i,obj) {
            ids.push(obj.id) ;
        }) ;
        recordIds.val(ids.join(","));
        toastr.success('清除成功!');
        saveSurveyAssetInventoryRightRecord(true,recordIds.attr("data-count"),null) ;
    }

    function loadDeclareRecordTable2(target, data, item) {
        target.bootstrapTable('destroy');
        var cols = [];
        cols.push({field: 'name', title: '权证名称', width: "22%"});
        cols.push({field: 'buildingNumber', title: '楼栋号', width: "6%"});
        cols.push({field: 'unit', title: '单元号', width: "6%"});
        cols.push({field: 'ownership', title: '所有权人', width: "6%"});
        cols.push({field: 'seat', title: '坐落', width: "19%"});
        cols.push({
            field: 'id', title: '操作', width: 200, formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success" data-title="删除" href="javascript:removeDataDeclareRecord(' + row.id + ",'" + item + "'" + ",'" + target.attr("id") + "'" + ');" ><i class="fa fa-remove">删除</i></a>';
                str += '</div>';
                return str;
            }
        });
        var init = {
            searchAlign: 'left',
            search: false,   //显示隐藏搜索框
            showHeader: true,     //是否显示列头
            showLoading: false,
            undefinedText: '',
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [2, 5, 10, 15],        //可供选择的每页的行数（*）
            showFullscreen: false,
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: false,                   //是否显示分页（*）
            strictSearch: true,
            showColumns: false,                  //是否显示所有的列
            showRefresh: false,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: false,                //是否启用点击选中行
            //height: 680,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
            data: data,
            columns: cols
        };
        target.bootstrapTable(init);
        //隐藏正在加载 正在努力地加载数据中，请稍候……
        target.bootstrapTable('hideLoading');
    }


    //加载 他项权利列表
    function loadAssetRightList(number, inventoryRightRecordId) {
        var cols = [];
        cols.push({field: 'typeName', title: '类型'});
        cols.push({field: 'categoryName', title: '类别'});
        cols.push({field: 'remark', title: '他项权利描述', width: '40%'});
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
            $.ajax({
                url: "${pageContext.request.contextPath}/surveyAssetInventoryRightRecord/getSurveyAssetInventoryRightRecordList",
                type: "get",
                dataType: "json",
                async: false,
                data: {
                    projectId: '${projectPlanDetails.projectId}',
                    planDetailsId: '${projectPlanDetails.id}'
                },
                success: function (result) {
                    if (result.ret) {
                        if (result.data.length >= 1) {
                            $.each(result.data, function (i, item) {
                                var html = $("#" + commonField.taskRightAssistDiv).html();
                                var number = commonField.getNumber();
                                html = html.replace(/_number/g, number).replace(/{index}/g, i + 1);
                                $("#" + commonField.taskRightAssistAppend).append(html);
                                uploadFileCommon(commonField.specialCaseFile + number, AssessDBKey.SurveyAssetInventoryRightRecord, item.id);
                                showFileCommon(commonField.specialCaseFile + number, AssessDBKey.SurveyAssetInventoryRightRecord, item.id);
                                $("#" + commonField.inventoryFrm + number).find('[name=inventoryRightRecordId]').val(item.id);
                                $("#" + commonField.surveyFrm + number).initForm(item);
                                if (item.recordIds) {
                                    $("#" + commonField.surveyFrm + number).find("input[name='recordIds']").val(item.recordIds);
                                    loadDeclareRecordTable(number, item.recordIds, "#" + commonField.surveyFrm + number);
                                }
                                loadAssetRightList(number, item.id);
                            });
                        } else {
                            flag = false;
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        }
        //添加 html
        if (!flag) {
            var html = $("#" + commonField.taskRightAssistDiv).html();
            var number = commonField.getNumber();
            var index = $("#" + commonField.taskRightAssistAppend).find('.x_panel').length;
            html = html.replace(/_number/g, number).replace(/{index}/g, index + 1);
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
        AssessCommon.loadDataDicByKey(AssessDicKey.houseInventoryRightCategory, null, function (html) {
            form.find("select[name='category']").empty().html(html).trigger('change');
        })
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

    function changeRemark(that, value) {
        var form = $("#" + commonField.inventoryFrm + value);
        form.find("textarea[name='remark']").val($(that).find('option:selected').attr('title'));
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
        var x_panel = $(_this).closest(".x_panel");
        var form = x_panel.find("form").eq(0);
        $.ajax({
            url: "${pageContext.request.contextPath}/surveyAssetInventoryRightRecord/remove",
            type: "post",
            dataType: "json",
            data: {id: form.find("input[name='id']").val()},
            success: function (result) {
                if (result.ret) {
                    x_panel.remove();
                    toastr.success('移除成功');
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

    function importRightData(_this, number) {
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
                    loadAssetRightList(number, item.id);
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

    function saveSurveyAssetInventoryRightRecord(flag, number, callback) {
        if (flag) {
            //单个保存
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
        } else {
            //保存所有
            var data = [];
            var forms = $("#" + commonField.taskRightAssistAppend).find("form");
            //校验
            for (var i = 0; i < forms.size(); i++) {
                if (!$(forms[i]).valid()) {
                    return false;
                }
            }
            $.each(forms, function (i, n) {
                var text = $(n).attr("id");
                if (text.indexOf(commonField.surveyFrm) != -1) {
                    var item = formSerializeArray($(n));
                    data.push(item);
                }
            });
            $.ajax({
                url: "${pageContext.request.contextPath}/surveyAssetInventoryRightRecord/saveFormData",
                type: "post",
                dataType: "json",
                data: {formData: JSON.stringify(data)},
                success: function (result) {
                    if (result.ret) {
                        if (callback) {
                            callback(data);
                        }
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
        var formData = JSON.stringify({});
        saveSurveyAssetInventoryRightRecord(false, null, function () {
            if ("${processInsId}" != "0") {
                submitEditToServer(formData);
            }
            else {
                submitToServer(formData, mustUseBox);
            }
        });
    }


</script>


</html>

