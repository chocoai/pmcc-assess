<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/9/27
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_content">
        <div class="x_title">
            <h3>
                在建工程（土建）申报 &nbsp;&nbsp;&nbsp;&nbsp;
                <button type="button" class="btn btn-success" onclick="civilEngineering.showAddModel();"
                        data-toggle="modal"> 新增
                </button>

            </h3>
            <div class="clearfix"></div>
        </div>
        <form class="form-horizontal">
            <div class="form-group">
                <div class="col-sm-12">

                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <table class="table table-bordered" id="civilEngineeringTableList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script>
    /**
    * @author:  zch
    * 描述:配置一些必须的参数
    * @date:2018-09-27
    **/
    var civilEngineeringConfig = {
        name: "土建",
        frm: "civilEngineeringFrm",
        table: "civilEngineeringTableList",
        box: "civilEngineeringBox",
        fileId: "civilEngineeringFileId"
    };
    var civilEngineering = new Object();

    //处理标识符的地方-------start
    civilEngineering.civilEngineeringFlag = true;
    //----------------------end

    civilEngineering.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    civilEngineering.objectWriteSelectData = function (frm, data, name) {
        if (civilEngineering.isEmpty(data)) {
            $("#" + frm + " ." + name).val(data).trigger("change");
        } else {
            $("#" + frm + " ." + name).val(null).trigger("change");
        }
    };

    civilEngineering.showFile = function (target,tableName, id) {
        FileUtils.getFileShows({
            target: target,
            formData: {
                fieldsName: target,
                tableName: tableName,
                tableId: id,
                projectId: 0
            },
            deleteFlag: true
        });
    };

    civilEngineering.fileUpload = function (target,tableName, id) {
        FileUtils.uploadFiles({
            target: target,
            disabledTarget: "btn_submit",
            onUpload: function (file) {
                var formData = {
                    fieldsName: target,
                    tableName: tableName,
                    tableId: id
                };
                return formData;
            }, onUploadComplete: function (result,file) {
                civilEngineering.showFile(target,tableName,id);
            },
            deleteFlag: true
        });
    };

    civilEngineering.init = function () {
        AssessCommon.initAreaInfo({
            provinceTarget: $("#" + civilEngineeringConfig.frm + "province"),
            cityTarget: $("#" + civilEngineeringConfig.frm + "city"),
            districtTarget: $("#" + civilEngineeringConfig.frm + "district"),
            provinceValue: '',
            cityValue: '',
            districtValue: ''
        });
    };

    /**
     * @author:  zch
     * 描述:在建工程（土建）显示
     * @date:2018-09-27
     **/
    civilEngineering.showAddModel = function () {
        $("#" + civilEngineeringConfig.frm).clearAll();
        if (civilEngineering.civilEngineeringFlag){
            civilEngineering.init();
            civilEngineering.civilEngineeringFlag = false;
        }
        civilEngineering.showFile(civilEngineeringConfig.fileId,AssessDBKey.DeclareBuildEngineering,0);
        civilEngineering.fileUpload(civilEngineeringConfig.fileId,AssessDBKey.DeclareBuildEngineering,0);
        //使校验生效
        $("#" + civilEngineeringConfig.frm).validate();
        $('#' + civilEngineeringConfig.box).modal("show");
    };

    /**
     * @author:  zch
     * 描述:在建工程（土建）删除
     * @date:2018-09-27
     **/
    civilEngineering.deleteData = function (id) {
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareBuildEngineering/deleteDeclareBuildEngineeringById",
            data: {id:id},
            success: function (result) {
                if (result.ret) {
                    civilEngineering.loadList();
                    toastr.success('成功!');
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };

    /**
    * @author:  zch
    * 描述:在建工程（土建）编辑
    * @date:2018-09-27
    **/
    civilEngineering.editData = function (id) {
        $("#" + civilEngineeringConfig.frm).clearAll();
        if (civilEngineering.civilEngineeringFlag){
            civilEngineering.init();
            civilEngineering.civilEngineeringFlag = false;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/declareBuildEngineering/getDeclareBuildEngineeringById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data ;
                    if (civilEngineering.isEmpty(data)){
                        $("#" + civilEngineeringConfig.frm).initForm(result.data);
                        civilEngineering.showFile(civilEngineeringConfig.fileId,AssessDBKey.DeclareBuildEngineering,data.id);
                        civilEngineering.fileUpload(civilEngineeringConfig.fileId,AssessDBKey.DeclareBuildEngineering,data.id);
                        $("#" + civilEngineeringConfig.frm + " input[name='declarationDate']").val(formatDate(data.declarationDate));
                        $("#" + civilEngineeringConfig.frm + " input[name='expectedCompletionDate']").val(formatDate(data.expectedCompletionDate));
                        $("#" + civilEngineeringConfig.frm + " input[name='startDate']").val(formatDate(data.startDate));
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
        //使校验生效
        $("#" + civilEngineeringConfig.frm).validate();
        $('#' + civilEngineeringConfig.box).modal("show");
    };

    /**
     * @author:  zch
     * 描述:在建工程（土建）更新
     * @date:2018-09-27
     **/
    civilEngineering.saveAndUpdateData = function () {
        if (!$("#" + civilEngineeringConfig.frm).valid()) {
            return false;
        }
        var data = formParams(civilEngineeringConfig.frm);
        data.planDetailsId = '${empty projectPlanDetails.id?0:projectPlanDetails.id}';
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareBuildEngineering/saveAndUpdateDeclareBuildEngineering",
            data: data,
            success: function (result) {
                if (result.ret) {
                    civilEngineering.loadList();
                    toastr.success('成功!');
                    $('#' + civilEngineeringConfig.box).modal("hide");
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };

    civilEngineering.loadList = function () {
        var cols = [];
        cols.push({field: 'provinceName', title: '省'});
        cols.push({field: 'cityName', title: '市'});
        cols.push({field: 'districtName', title: '县'});
        cols.push({field: 'bookNetValue', title: '帐面净值'});
        cols.push({field: 'bookValue', title: '帐面价值'});
        cols.push({field: 'declarer', title: '申报人'});
        cols.push({field: 'beLocated', title: '坐落'});
        cols.push({field: 'fileViewName', title: '附件'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="civilEngineering.deleteData(' + row.id + ',\'tb_List\')"><i class="fa fa-remove fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="编辑" onclick="civilEngineering.editData(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#" + civilEngineeringConfig.table).bootstrapTable('destroy');
        TableInit(civilEngineeringConfig.table, "${pageContext.request.contextPath}/declareBuildEngineering/getDeclareBuildEngineeringList", cols, {
            planDetailsId: ${empty projectPlanDetails.id?0:projectPlanDetails.id},
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

    $(function () {
        civilEngineering.loadList();
    });

</script>

<!-- 在建工程（土建） -->
<div id="civilEngineeringBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土建</h3>
            </div>
            <form id="civilEngineeringFrm" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">省
                                            <span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select name="province" id="civilEngineeringFrmprovince"
                                                    class="form-control search-select select2"
                                                    required="required">
                                                <option value="" name="province">-请选择-</option>
                                                <c:forEach items="${ProvinceList}" var="item">
                                                    <c:choose>
                                                        <c:when test="${item.areaId == projectInfo.province}">
                                                            <option value="${item.areaId}"
                                                                    selected="selected">${item.name}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${item.areaId}">${item.name}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">市<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select id="civilEngineeringFrmcity" name="city"
                                                    class="form-control search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>

                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">县<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select id="civilEngineeringFrmdistrict" name="district"
                                                    class="form-control search-select select2 district">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">占有单位<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="占有单位" name="occupancyUnit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">项目名称<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="项目名称" name="name" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">坐落<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="坐落" name="beLocated" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">结构<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="结构" name="structure" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建筑面积<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建筑面积(数字)" name="buildArea" class="form-control" data-rule-maxlength="100" data-rule-number='true'
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">开工日期<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="开工日期"
                                                   name="startDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">预期完成日期<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="预期完成日期"
                                                   name="expectedCompletionDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">形象进度<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="形象进度" name="speedProgress" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">付款比例<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="付款比例" name="paymentRatio" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">账面价值<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="账面价值" name="bookValue" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">帐面净值<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="帐面净值" name="bookNetValue" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">申报人<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="申报人" name="declarer" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">申报日期<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="申报日期"
                                                   name="declarationDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">备注<span class="symbol required"></span></label>
                                        <div class="col-sm-11">
                                            <textarea class="form-control" name="remark" required="required"></textarea>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <input id="civilEngineeringFileId" name="civilEngineeringFileId"
                                                   required="required" placeholder="附件" class="form-control"
                                                   type="file">
                                            <div id="_civilEngineeringFileId"></div>
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
                <button type="button" class="btn btn-primary"
                        onclick="civilEngineering.saveAndUpdateData();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>