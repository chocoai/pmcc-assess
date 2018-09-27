<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/9/27
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_content">
        <div class="x_title">
            <h3>
                在建工程（设备安装） &nbsp;&nbsp;&nbsp;&nbsp;
                <button type="button" class="btn btn-success" onclick="equipmentInstallation.showAddModel();"
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
                    <table class="table table-bordered" id="equipmentInstallationTableList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script>

    var equipmentInstallationConfig = {
        name: "设备安装",
        frm: "equipmentInstallationFrm",
        table: "equipmentInstallationTableList",
        box: "equipmentInstallationBox",
        fileId:"equipmentInstallationFileId"
    };

    var equipmentInstallation = new Object();
    //处理标识符的地方-------start
    equipmentInstallation.equipmentInstallationFlag = true;
    //----------------------end

    equipmentInstallation.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    equipmentInstallation.objectWriteSelectData = function (frm, data, name) {
        if (equipmentInstallation.isEmpty(data)) {
            $("#" + frm + " ." + name).val(data).trigger("change");
        } else {
            $("#" + frm + " ." + name).val(null).trigger("change");
        }
    };

    equipmentInstallation.showFile = function (target,tableName, id) {
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

    equipmentInstallation.fileUpload = function (target,tableName, id) {
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
                equipmentInstallation.showFile(target,tableName,id);
            },
            deleteFlag: true
        });
    };

    equipmentInstallation.init = function () {
        AssessCommon.initAreaInfo({
            provinceTarget: $("#" + equipmentInstallationConfig.frm + "province"),
            cityTarget: $("#" + equipmentInstallationConfig.frm + "city"),
            districtTarget: $("#" + equipmentInstallationConfig.frm + "district"),
            provinceValue: '',
            cityValue: '',
            districtValue: ''
        });
    };

    /**
     * @author:  zch
     * 描述:在建工程（设备安装）显示
     * @date:2018-09-27
     **/
    equipmentInstallation.showAddModel = function () {
        $("#" + equipmentInstallationConfig.frm).clearAll();
        if (equipmentInstallation.equipmentInstallationFlag){
            equipmentInstallation.init();
            equipmentInstallation.equipmentInstallationFlag = false;
        }
        equipmentInstallation.showFile(equipmentInstallationConfig.fileId,AssessDBKey.DeclareBuildEquipmentInstall,0);
        equipmentInstallation.fileUpload(equipmentInstallationConfig.fileId,AssessDBKey.DeclareBuildEquipmentInstall,0);
        //使校验生效
        $("#" + equipmentInstallationConfig.frm).validate();
        $('#' + equipmentInstallationConfig.box).modal("show");
    };

    /**
     * @author:  zch
     * 描述:在建工程（设备安装）更新
     * @date:2018-09-27
     **/
    equipmentInstallation.saveAndUpdateData = function () {
        if (!$("#" + equipmentInstallationConfig.frm).valid()) {
            return false;
        }
        var data = formParams(equipmentInstallationConfig.frm);
        data.planDetailsId = '${empty projectPlanDetails.id?0:projectPlanDetails.id}';
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareBuildEquipmentInstall/saveAndUpdateDeclareBuildEquipmentInstall",
            data: data,
            success: function (result) {
                if (result.ret) {
                    equipmentInstallation.loadList();
                    toastr.success('成功!');
                    $('#' + equipmentInstallationConfig.box).modal("hide");
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
     * 描述:在建工程（设备安装）编辑
     * @date:2018-09-27
     **/
    equipmentInstallation.editData = function (id) {
        $("#" + equipmentInstallationConfig.frm).clearAll();
        if (equipmentInstallation.equipmentInstallationFlag){
            equipmentInstallation.init();
            equipmentInstallation.equipmentInstallationFlag = false;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/declareBuildEquipmentInstall/getDeclareBuildEquipmentInstallById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data ;
                    if (equipmentInstallation.isEmpty(data)){
                        $("#" + equipmentInstallationConfig.frm).initForm(result.data);
                        equipmentInstallation.showFile(equipmentInstallationConfig.fileId,AssessDBKey.DeclareBuildEquipmentInstall,data.id);
                        equipmentInstallation.fileUpload(equipmentInstallationConfig.fileId,AssessDBKey.DeclareBuildEquipmentInstall,data.id);
                        $("#" + equipmentInstallationConfig.frm + " input[name='declarationDate']").val(formatDate(data.declarationDate));
                        $("#" + equipmentInstallationConfig.frm + " input[name='expectedCompletionDate']").val(formatDate(data.expectedCompletionDate));
                        $("#" + equipmentInstallationConfig.frm + " input[name='startDate']").val(formatDate(data.startDate));
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
        //使校验生效
        $("#" + equipmentInstallationConfig.frm).validate();
        $('#' + equipmentInstallationConfig.box).modal("show");
    };

    /**
     * @author:  zch
     * 描述:在建工程（设备安装）删除
     * @date:2018-09-27
     **/
    equipmentInstallation.deleteData = function (id) {
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareBuildEquipmentInstall/deleteDeclareBuildEquipmentInstallById",
            data: {id:id},
            success: function (result) {
                if (result.ret) {
                    equipmentInstallation.loadList();
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

    equipmentInstallation.loadList = function () {
        var cols = [];
        cols.push({field: 'provinceName', title: '省'});
        cols.push({field: 'cityName', title: '市'});
        cols.push({field: 'districtName', title: '县'});
        cols.push({field: 'bookEquipmentFee', title: '账面设备费'});
        cols.push({field: 'bookCapitalCost', title: '账面资金成本'});
        cols.push({field: 'bookInstallationFee', title: '账面安装费'});
        cols.push({field: 'declarer', title: '申报人'});
        cols.push({field: 'beLocated', title: '坐落'});
        cols.push({field: 'fileViewName', title: '附件'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="equipmentInstallation.deleteData(' + row.id + ',\'tb_List\')"><i class="fa fa-remove fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="编辑" onclick="equipmentInstallation.editData(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#" + equipmentInstallationConfig.table).bootstrapTable('destroy');
        TableInit(equipmentInstallationConfig.table, "${pageContext.request.contextPath}/declareBuildEquipmentInstall/getDeclareBuildEquipmentInstallList", cols, {
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
        equipmentInstallation.loadList();
    });
</script>

<!-- 在建工程（设备安装） -->
<div id="equipmentInstallationBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">设备安装</h3>
            </div>
            <form id="equipmentInstallationFrm" class="form-horizontal">
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
                                            <select name="province" id="equipmentInstallationFrmprovince"
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
                                            <select id="equipmentInstallationFrmcity" name="city"
                                                    class="form-control search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>

                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">县<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select id="equipmentInstallationFrmdistrict" name="district"
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
                                        <label class="col-sm-1 control-label">规格型号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="规格型号" name="specificationModel" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">生产厂家<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="生产厂家" name="manufacturer" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">计量单位<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="计量单位" name="measurementUnit" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">数量<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text" data-rule-maxlength="100" data-rule-number='true'
                                                   placeholder="数量(数字)" name="number" class="form-control"
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
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">预期完成日期<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="预期完成日期"
                                                   name="expectedCompletionDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">账面设备费<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="账面设备费" name="bookEquipmentFee" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">账面资金成本<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="账面资金成本" name="bookCapitalCost" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">账面安装费<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="账面安装费" name="bookInstallationFee" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">其它<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="其它" name="other" class="form-control"
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
                                            <input id="equipmentInstallationFileId" name="equipmentInstallationFileId"
                                                   required="required" placeholder="附件" class="form-control"
                                                   type="file">
                                            <div id="_equipmentInstallationFileId"></div>
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
                        onclick="equipmentInstallation.saveAndUpdateData();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>