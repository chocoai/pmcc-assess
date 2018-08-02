<%--
  房屋配套设备设施
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <!-- 包含此文件时需要删除掉css -->
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="houseCorollaryEquipment.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>房屋配套设备设施信息
        </h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content" style="display: none">
        <div>
            <button type="button" class="btn btn-success" onclick="houseCorollaryEquipment.prototype.showModel()"
                    data-toggle="modal" href="#divBox"> 新增
            </button>
        </div>
        <form class="form-horizontal">
            <div class="form-group">
                <div class="x-valid">
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <table class="table table-bordered" id="HouseCorollaryEquipmentList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>
</body>


<%--<%@include file="/views/share/main_footer.jsp" %>--%>
<script type="application/javascript">

    $(function () {
        houseCorollaryEquipment.prototype.fileUpload();
    });

    var houseCorollaryEquipment;
    (function () {
        var flag = true;
        var fileID = null;
        houseCorollaryEquipment = function () {

        };
        houseCorollaryEquipment.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            setFileID:function (id_) {
                fileID = id_;
            },
            getFileID:function () {
                if (fileID == null || fileID == ''){
                    return 0;
                }
                return fileID;
            },
            viewInit: function () {
                houseCorollaryEquipment.prototype.loadDataDicList();
                if (houseCorollaryEquipment.prototype.getFlag()) {
                    houseCorollaryEquipment.prototype.init();
                    houseCorollaryEquipment.prototype.setFlag(false);
                }
            },
            config: function () {
                var data = {};
                data.table = "HouseCorollaryEquipmentList";
                data.box = "divBoxHouseCorollaryEquipment";
                data.frm = "frmHouseCorollaryEquipment";
                data.FileID = "positionDiagramFileID" ;// ExamineFileUpLoadTwoFieldEnum
                data.type = "null";//
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'name', title: '名称'});
                cols.push({field: 'parameterIndexH', title: '参数指标'});
                cols.push({field: 'useH', title: '用途'});
                cols.push({field: 'maintenanceStatus', title: '维护状况'});
                cols.push({field: 'typeName', title: '类型'});
                cols.push({field: 'categoryName', title: '类别'});
                cols.push({field: 'priceName', title: '价格'});
                cols.push({field: 'fileName', title: '位置图'});
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="houseCorollaryEquipment.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseCorollaryEquipment.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                $("#" + houseCorollaryEquipment.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseCorollaryEquipment.prototype.config().table, "${pageContext.request.contextPath}/examineHouseCorollaryEquipment/getExamineHouseCorollaryEquipmentList", cols, {
                    type: houseCorollaryEquipment.prototype.config().type,
                    declareId : $("#declareId").val(),
                    examineType : $("#examineType").val()
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
            },
            removeData: function (id) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineHouseCorollaryEquipment/deleteExamineHouseCorollaryEquipmentById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            houseCorollaryEquipment.prototype.loadDataDicList();
                        }
                        else {
                            Alert("保存数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            showModel: function () {
                $("#" + houseCorollaryEquipment.prototype.config().frm).clearAll();
                $("#" + houseCorollaryEquipment.prototype.config().frm + " .type").val(houseCorollaryEquipment.prototype.config().type);
                $("#"+houseCorollaryEquipment.prototype.config().FileID).empty();
                $("#"+"_"+houseCorollaryEquipment.prototype.config().FileID).empty();
                $('#' + houseCorollaryEquipment.prototype.config().box).modal("show");
            },
            saveData: function () {
                if (!$("#" + houseCorollaryEquipment.prototype.config().frm).valid()) {
                    return false;
                }
                var data = formParams(houseCorollaryEquipment.prototype.config().frm);
                if ($("#declareId").size() > 0) {
                    data.declareId = $("#declareId").val();
                }
                if ($("#examineType").size() > 0) {
                    data.examineType = $("#examineType").val();
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineHouseCorollaryEquipment/saveAndUpdateExamineHouseCorollaryEquipment",
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('保存成功');
                            $('#' + houseCorollaryEquipment.prototype.config().box).modal('hide');
                            houseCorollaryEquipment.prototype.loadDataDicList();
                        }
                        else {
                            Alert("保存数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            getAndInit: function (id) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineHouseCorollaryEquipment/getExamineHouseCorollaryEquipmentById",
                    type: "get",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            houseCorollaryEquipment.prototype.setFileID(result.data.id);
                            $("#" + houseCorollaryEquipment.prototype.config().frm).clearAll();
                            $("#" + houseCorollaryEquipment.prototype.config().frm).initForm(result.data);
                            houseCorollaryEquipment.prototype.showFile();
                            if (result.data.price == null || result.data.price == '') {
                                $("#" + houseCorollaryEquipment.prototype.config().frm + " .price").val(null).trigger("change");
                            } else {
                                $("#" + houseCorollaryEquipment.prototype.config().frm + " .price").val(result.data.price).trigger("change");
                            }
                            if (result.data.type == null || result.data.type == '') {
                                $("#" + houseCorollaryEquipment.prototype.config().frm + " .type").val(null).trigger("change");
                            } else {
                                $("#" + houseCorollaryEquipment.prototype.config().frm + " .type").val(result.data.type).trigger("change");
                            }
                            if (result.data.category == null || result.data.category == '') {
                                $("#" + houseCorollaryEquipment.prototype.config().frm + " .category").val(null).trigger("change");
                            } else {
                                $("#" + houseCorollaryEquipment.prototype.config().frm + " .category").val(result.data.category).trigger("change");
                            }
                            $('#' + houseCorollaryEquipment.prototype.config().box).modal("show");
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            init: function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineHouseCorollaryEquipment/examine_house_corollary_equipment_price",
                    type: "get",
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                            var data = result.data;
                            var gradeNum = data.length;
                            var option = "<option value=''>请选择</option>";
                            if (gradeNum > 0) {
                                for (var i = 0; i < gradeNum; i++) {
                                    option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                }
                                $("#" + houseCorollaryEquipment.prototype.config().frm + " .price").html(option);
                                $("#" + houseCorollaryEquipment.prototype.config().frm + " .price").select2();//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineHouseCorollaryEquipment/examine_house_corollary_equipment_type",
                    type: "get",
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                            var data = result.data;
                            var gradeNum = data.length;
                            var option = "<option value=''>请选择</option>";
                            if (gradeNum > 0) {
                                for (var i = 0; i < gradeNum; i++) {
                                    option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                }
                                $("#" + houseCorollaryEquipment.prototype.config().frm + " .type").html(option);
                                $("#" + houseCorollaryEquipment.prototype.config().frm + " .type").select2({minimumResultsForSearch: -1});//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineHouseCorollaryEquipment/examine_house_corollary_equipment_category",
                    type: "get",
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                            var data = result.data;
                            var gradeNum = data.length;
                            var option = "<option value=''>请选择</option>";
                            if (gradeNum > 0) {
                                for (var i = 0; i < gradeNum; i++) {
                                    option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                }
                                $("#" + houseCorollaryEquipment.prototype.config().frm + " .category").html(option);
                                $("#" + houseCorollaryEquipment.prototype.config().frm + " .category").select2({minimumResultsForSearch: -1});//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })

            },
            fileUpload:function () {
                FileUtils.uploadFiles({
                    target:houseCorollaryEquipment.prototype.config().FileID,
                    disabledTarget: "btn_submit",
                    onUpload: function (file) {
                        var formData={
                            fieldsName:houseCorollaryEquipment.prototype.config().FileID,
                            tableName: AssessDBKey.ExamineHouseCorollaryEquipment,
                            tableId: houseCorollaryEquipment.prototype.getFileID()
                        };
                        return formData;
                    },onUploadComplete:function () {
                        houseCorollaryEquipment.prototype.showFile();
                    },
                    deleteFlag: true
                });
            },
            showFile:function () {
                FileUtils.getFileShows({
                    target: houseCorollaryEquipment.prototype.config().FileID,
                    formData: {
                        fieldsName:houseCorollaryEquipment.prototype.config().FileID,
                        tableName: AssessDBKey.ExamineHouseCorollaryEquipment,
                        tableId: houseCorollaryEquipment.prototype.getFileID(),
                        projectId: 0
                    },
                    deleteFlag: true
                })
            }
        }
    })();

</script>

<div id="divBoxHouseCorollaryEquipment" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">房屋配套设备设施</h3>
            </div>
            <form id="frmHouseCorollaryEquipment" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            名称
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="name" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            参数指标
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="parameterIndexH" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            维护状况
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="maintenanceStatus" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            设备用途
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="useH" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            类别
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="category"
                                                    class="form-control search-select select2 category">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            类型
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="type"
                                                    class="form-control search-select select2 type">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            价格
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="price"
                                                    class="form-control search-select select2 price">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            户型图
                                        </label>
                                        <div class="col-sm-10">
                                            <input id="positionDiagramFileID" name="positionDiagramFileID"
                                                   required="required" placeholder="上传附件" class="form-control" type="file">
                                            <div id="_positionDiagramFileID"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="houseCorollaryEquipment.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>