<%--
 户型
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="unitHuxing.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>房型信息
        </h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content" style="display: none">
        <div>
            <button type="button" class="btn btn-success" onclick="unitHuxing.prototype.showModel()"
                    data-toggle="modal" href="#divBox"> 新增
            </button>
        </div>
        <form id="frm_unitHuxing" class="form-horizontal">
            <div class="form-group">
                <div class="x-valid">
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <table class="table table-bordered" id="UnitHuxingList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">
    $(function () {
        unitHuxing.prototype.fileUpload();

    });
    var unitHuxing;
    (function () {
        var flag = true;
        var fileID = null;
        unitHuxing = function () {
        };
        unitHuxing.prototype = {
            setFlag:function (flag_) {
                flag = flag_;
            },
            getFlag:function () {
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
                if (unitHuxing.prototype.getFlag()){
                    unitHuxing.prototype.init();
                    unitHuxing.prototype.setFlag(false);
                }
                unitHuxing.prototype.loadDataDicList();
            },
            fileUpload:function () {
                FileUtils.uploadFiles({
                    target:unitHuxing.prototype.config().unitHuxingFileIDFildName,
                    disabledTarget: "btn_submit",
                    onUpload: function (file) {
                        var formData={
                            fieldsName:unitHuxing.prototype.config().unitHuxingFileIDFildName,
                            tableName: AssessDBKey.ExamineUnitHuxing,
                            tableId: unitHuxing.prototype.getFileID()
                        };
                        return formData;
                    },onUploadComplete:function () {
                        unitHuxing.prototype.showFile();
                    },
                    deleteFlag: true
                });
            },
            showFile:function () {
                FileUtils.getFileShows({
                    target: unitHuxing.prototype.config().unitHuxingFileIDFildName,
                    formData: {
                        fieldsName:unitHuxing.prototype.config().unitHuxingFileIDFildName,
                        tableName: AssessDBKey.ExamineUnitHuxing,
                        tableId: unitHuxing.prototype.getFileID(),
                        projectId: 0
                    },
                    deleteFlag: true
                })
            },
            config: function () {
                var data = {};
                data.table = "UnitHuxingList";
                data.box = "divBoxUnitHuxing";
                data.frm = "frmUnitHuxing";
                data.type = "null";//
                data.unitHuxingFileIDFildName = "house_latest_family_planV";//根据 ExamineFileUpLoadFieldEnum 配置
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'description', title: '描述'});
                cols.push({field: 'name', title: '房型名称'});
                cols.push({field: 'houseLayoutName', title: '房型'});
                cols.push({field: 'spanLength', title: '跨长'});
                cols.push({field: 'spanWidth', title: '跨宽'});
                cols.push({field: 'spanNumber', title: '跨数'});
                cols.push({field: 'fileViewName', title: '户型图'});
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="unitHuxing.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="unitHuxing.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                $("#" + unitHuxing.prototype.config().table).bootstrapTable('destroy');
                TableInit(unitHuxing.prototype.config().table, "${pageContext.request.contextPath}/examineUnitHuxing/getExamineUnitHuxingList", cols, {
                    type: unitHuxing.prototype.config().type,
                    declareId : $("#declareId").val(),
                    planDetailsId : $("#planDetailsId").val(),
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
                    url: "${pageContext.request.contextPath}/examineUnitHuxing/deleteExamineUnitHuxingById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            unitHuxing.prototype.loadDataDicList();
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
                $("#" + unitHuxing.prototype.config().frm).clearAll();
                $("#" + unitHuxing.prototype.config().frm + " .type").val(unitHuxing.prototype.config().type);
                // unitHuxing.prototype.showFile();
                $("#house_latest_family_planV").empty();
                $("#_house_latest_family_planV").empty();
                $('#' + unitHuxing.prototype.config().box).modal("show");
            },
            saveData: function () {
                if (!$("#" + unitHuxing.prototype.config().frm).valid()) {
                    return false;
                }
                var data = formParams(unitHuxing.prototype.config().frm);
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
                    url: "${pageContext.request.contextPath}/examineUnitHuxing/saveAndUpdateExamineUnitHuxing",
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('保存成功');
                            $('#' + unitHuxing.prototype.config().box).modal('hide');
                            unitHuxing.prototype.loadDataDicList();
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
                    url: "${pageContext.request.contextPath}/examineUnitHuxing/getExamineUnitHuxingById",
                    type: "get",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            $("#" + unitHuxing.prototype.config().frm).clearAll();
                            $("#" + unitHuxing.prototype.config().frm).initForm(result.data);
                            unitHuxing.prototype.setFileID(result.data.id);
                            unitHuxing.prototype.showFile();
                            if (result.data.houseLayout == null || result.data.houseLayout == '') {
                                $("#" + unitHuxing.prototype.config().frm + " .houseLayout").val(null).trigger("change");
                            } else {
                                $("#" + unitHuxing.prototype.config().frm + " .houseLayout").val(result.data.houseLayout).trigger("change");
                            }
                            $('#' + unitHuxing.prototype.config().box).modal("show");
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            init: function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineUnitHuxing/examine_unit_house_layout",
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
                                $("#" + unitHuxing.prototype.config().frm + " .houseLayout").html(option);
                                $("#" + unitHuxing.prototype.config().frm + " .houseLayout").select2({minimumResultsForSearch: -1});//加载样式
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

<div id="divBoxUnitHuxing" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">户型</h3>
            </div>
            <form id="frmUnitHuxing" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            房型名称
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="房型名称" name="name"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            面积
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="面积(数字)" data-rule-number='true' name="area"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            跨长
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="跨长(数字)" data-rule-number='true'
                                                   name="spanLength" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            跨宽
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="跨宽(数字)" data-rule-number='true'
                                                   name="spanWidth" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            跨数
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="跨数(数字)" data-rule-number='true'
                                                   name="spanNumber" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            户型描述
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="户型描述" name="description"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            户型
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="houseLayout"
                                                    class="form-control search-select select2 houseLayout">
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
                                            <input id="house_latest_family_planV" name="house_latest_family_planV"
                                                   required="required" placeholder="上传附件" class="form-control" type="file">
                                            <div id="_house_latest_family_planV"></div>
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
                    <button type="button" class="btn btn-primary" onclick="unitHuxing.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>

