<%--
  车位信息
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="estateParking.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>车位信息
        </h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content" style="display:none;">
        <div>
            <button type="button" class="btn btn-success" onclick="estateParking.prototype.showModel()"
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
                    <table class="table table-bordered" id="estateParkingList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    $(function () {
        estateParking.prototype.fileUpload();
    });

    var estateParking;
    (function () {
        var flag = true;
        var fileID = null;
        estateParking = function () {

        };
        estateParking.prototype = {
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
                if (estateParking.prototype.getFlag()){
                    estateParking.prototype.init();
                    estateParking.prototype.setFlag(false);
                }
                estateParking.prototype.loadDataDicList();
            },
            fileUpload:function () {
                FileUtils.uploadFiles({
                    target:estateParking.prototype.config().fileIDName,
                    disabledTarget: "btn_submit",
                    onUpload: function (file) {
                        var formData={
                            fieldsName:estateParking.prototype.config().fileIDName,
                            tableName: AssessDBKey.ExamineEstateParking,
                            tableId: estateParking.prototype.getFileID()
                        };
                        return formData;
                    },onUploadComplete:function () {
                        estateParking.prototype.showFile();
                    },
                    deleteFlag: true
                });
            },
            showFile:function () {
                FileUtils.getFileShows({
                    target: estateParking.prototype.config().fileIDName,
                    formData: {
                        fieldsName:estateParking.prototype.config().fileIDName,
                        tableName: AssessDBKey.ExamineEstateParking,
                        tableId: estateParking.prototype.getFileID(),
                        projectId: 0
                    },
                    deleteFlag: true
                })
            },
            config: function () {
                var data = {};
                data.table = "estateParkingList";
                data.box = "divBoxEstateParking";
                data.frm = "frmEstateParking";
                data.fileIDName = "house_estateParking" ;//ExamineFileUpLoadFieldEnum
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'parkingTypeName', title: '车位类型'});
                cols.push({field: 'location', title: '车辆位置'});
                cols.push({field: 'fileViewName', title: '上传的附件'});
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="estateParking.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="estateParking.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                $("#" + estateParking.prototype.config().table).bootstrapTable('destroy');
                TableInit(estateParking.prototype.config().table, "${pageContext.request.contextPath}/examineEstateParking/getExamineEstateParkingList", cols, {
                    declareId : $("#declareId").val(),
                    planDetailsId:$("#planDetailsId").val(),
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
                    url: "${pageContext.request.contextPath}/examineEstateParking/deleteExamineEstateParkingById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            estateParking.prototype.loadDataDicList();
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
                $("#" + estateParking.prototype.config().frm).clearAll();
                // estateParking.prototype.init();
                $('#' + estateParking.prototype.config().box).modal("show");
                $("#"+estateParking.prototype.config().fileIDName).empty();
                $("#_"+estateParking.prototype.config().fileIDName).empty();
            },
            saveData: function () {
                if (!$("#" + estateParking.prototype.config().frm).valid()) {
                    return false;
                }
                var data = formParams(estateParking.prototype.config().frm);
                if ($("#declareId").size() > 0) {
                    data.declareId = $("#declareId").val();
                }
                if ($("#planDetailsId").size() > 0) {
                    data.planDetailsId = $("#planDetailsId").val();
                }
                if ($("#examineType").size() > 0) {
                    data.examineType = $("#examineType").val();
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineEstateParking/saveAndUpdateExamineEstateParking",
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('保存成功');
                            $('#' + estateParking.prototype.config().box).modal('hide');
                            estateParking.prototype.loadDataDicList();
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
                    url: "${pageContext.request.contextPath}/examineEstateParking/getExamineEstateParkingById",
                    type: "get",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            $("#" + estateParking.prototype.config().frm).clearAll();
                            $("#" + estateParking.prototype.config().frm).initForm(result.data);
                            estateParking.prototype.setFileID(result.data.id);
                            if (result.data.parkingType == null || result.data.parkingType == '') {
                                $("#" + estateParking.prototype.config().frm + " .parkingType").val(null).trigger("change");
                            } else {
                                $("#" + estateParking.prototype.config().frm + " .parkingType").val(result.data.parkingType).trigger("change");
                            }
                            $('#' + estateParking.prototype.config().box).modal("show");
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            init: function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineEstateParking/estate_car_type",
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
                                $("#" + estateParking.prototype.config().frm).find('select.parkingType').empty().html(option).trigger('change');
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            }
        }
    })();

</script>

<div id="divBoxEstateParking" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">车位</h3>
            </div>
            <form id="frmEstateParking" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            车位位置
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="location"
                                                   placeholder="车位位置" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            车位类型
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="parkingType"
                                                    class="form-control search-select select2 parkingType">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            车辆图
                                        </label>
                                        <div class="col-sm-10">
                                            <input id="house_estateParking" name="house_estateParking"
                                                   required="required" placeholder="上传附件" class="form-control" type="file">
                                            <div id="_house_estateParking"></div>
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
                    <button type="button" class="btn btn-primary" onclick="estateParking.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>


