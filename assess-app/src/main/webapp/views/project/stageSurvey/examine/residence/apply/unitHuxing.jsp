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
        <h3>户型信息
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
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            setFileID: function (id_) {
                fileID = id_;
            },
            getFileID: function () {
                if (fileID == null || fileID == '') {
                    return 0;
                }
                return fileID;
            },
            viewInit: function () {
                if (unitHuxing.prototype.getFlag()) {
                    unitHuxing.prototype.init();
                    unitHuxing.prototype.setFlag(false);
                }
                unitHuxing.prototype.loadDataDicList();
            },
            fileUpload: function () {
                FileUtils.uploadFiles({
                    target: unitHuxing.prototype.config().unitHuxingFileIDFildName,
                    disabledTarget: "btn_submit",
                    onUpload: function (file) {
                        var formData = {
                            fieldsName: unitHuxing.prototype.config().unitHuxingFileIDFildName,
                            tableName: AssessDBKey.ExamineUnitHuxing,
                            tableId: unitHuxing.prototype.getFileID()
                        };
                        return formData;
                    }, onUploadComplete: function () {
                        unitHuxing.prototype.showFile();
                    },
                    deleteFlag: true
                });
            },
            showFile: function () {
                FileUtils.getFileShows({
                    target: unitHuxing.prototype.config().unitHuxingFileIDFildName,
                    formData: {
                        fieldsName: unitHuxing.prototype.config().unitHuxingFileIDFildName,
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
                cols.push({field: 'spanLength', title: '跨长'});
                cols.push({field: 'orientation', title: '朝向'});
                cols.push({field: 'spanWidth', title: '跨宽'});
                cols.push({field: 'spanNumber', title: '跨数'});
                cols.push({field: 'fileViewName', title: '户型图'});
                cols.push({
                    field: 'houseCategory', title: '房型', formatter: function (value, row, index) {
                        var str = "";
                        if (unitHuxing.prototype.isNotNull(row.houseCategory)){
                            str = unitHuxing.prototype.rule("formatter",JSON.parse(row.houseCategory));
                        }
                        return str;
                    }
                });
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
                    declareId: $("#declareId").val(),
                    planDetailsId: $("#planDetailsId").val(),
                    examineType: $("#examineType").val()
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
                data.houseCategory = unitHuxing.prototype.rule("get",data);
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
            /**
             * @author:  zch
             * 描述:户型的类别填写方式【】房【】厅【】厨【】卫【】花园【】阳台
             * @date:
             **/
            rule: function (flag, item) {
                var text = "";
                //格式化
                if (flag == "formatter") {
                    if (unitHuxing.prototype.isNotNull(item.house)) {
                        text += item.house + "房-";
                    }
                    if (unitHuxing.prototype.isNotNull(item.saloon)) {
                        text += item.saloon + "客厅-";
                    }
                    if (unitHuxing.prototype.isNotNull(item.kitchen)) {
                        text += item.kitchen + "厨房-";
                    }
                    if (unitHuxing.prototype.isNotNull(item.toilet)) {
                        text += item.toilet + "卫生间-";
                    }
                    if (unitHuxing.prototype.isNotNull(item.garden)) {
                        text += item.garden + "花园-";
                    }
                    if (unitHuxing.prototype.isNotNull(item.balcony)) {
                        text += item.balcony + "阳台";
                    }
                    return text;
                }
                //转为json存入数据库
                if (flag == "get"){
                    var data = {};
                    if (unitHuxing.prototype.isNotNull(item.house)) {
                        data.house = item.house;
                    }
                    if (unitHuxing.prototype.isNotNull(item.saloon)) {
                        data.saloon = item.saloon;
                    }
                    if (unitHuxing.prototype.isNotNull(item.kitchen)) {
                        data.kitchen = item.kitchen;
                    }
                    if (unitHuxing.prototype.isNotNull(item.toilet)) {
                        data.toilet = item.toilet;
                    }
                    if (unitHuxing.prototype.isNotNull(item.garden)) {
                        data.garden = item.garden;
                    }
                    if (unitHuxing.prototype.isNotNull(item.balcony)) {
                        data.balcony = item.balcony;
                    }
                    return JSON.stringify(data);
                }
                //赋值
                if (flag == "set"){
                    if (unitHuxing.prototype.isNotNull(item.house)) {
                        $("#" + unitHuxing.prototype.config().frm + " input[name='house']").val(item.house);
                    }
                    if (unitHuxing.prototype.isNotNull(item.saloon)) {
                        $("#" + unitHuxing.prototype.config().frm + " input[name='saloon']").val(item.saloon);
                    }
                    if (unitHuxing.prototype.isNotNull(item.kitchen)) {
                        $("#" + unitHuxing.prototype.config().frm + " input[name='kitchen']").val(item.kitchen);
                    }
                    if (unitHuxing.prototype.isNotNull(item.toilet)) {
                        $("#" + unitHuxing.prototype.config().frm + " input[name='toilet']").val(item.toilet);
                    }
                    if (unitHuxing.prototype.isNotNull(item.garden)) {
                        $("#" + unitHuxing.prototype.config().frm + " input[name='garden']").val(item.garden);
                    }
                    if (unitHuxing.prototype.isNotNull(item.balcony)) {
                        $("#" + unitHuxing.prototype.config().frm + " input[name='balcony']").val(item.balcony);
                    }
                }
            },
            isNotNull: function (item) {
                if (item) {
                    return true;
                }
                return false;
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
                            var data = result.data ;
                            if (unitHuxing.prototype.isNotNull(data)){
                                if (!unitHuxing.prototype.isNotNull(data.houseLayout)) {
                                    $("#" + unitHuxing.prototype.config().frm + " .houseLayout").val(null).trigger("change");
                                } else {
                                    $("#" + unitHuxing.prototype.config().frm + " .houseLayout").val(data.houseLayout).trigger("change");
                                }
                                if (unitHuxing.prototype.isNotNull(data.houseCategory)){
                                    unitHuxing.prototype.rule("set",JSON.parse(data.houseCategory));
                                }
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
                                $("#" + unitHuxing.prototype.config().frm ).find("select.houseLayout").html(option);
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
                                            面积<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="面积(数字)" data-rule-number='true' name="area"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            跨长<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="跨长(数字)" data-rule-number='true'
                                                   name="spanLength" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            户型描述<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea placeholder="户型描述" name="description"
                                                      class="form-control" required="required">

                                            </textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            跨数<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="跨数(数字)" data-rule-number='true'
                                                   name="spanNumber" class="form-control" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            跨宽<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="跨宽(数字)" data-rule-number='true'
                                                   name="spanWidth" class="form-control" required="required">
                                        </div>
                                    </div>
                                    <%--<div class="x-valid">--%>
                                    <%--<label class="col-sm-2 control-label">--%>
                                    <%--户型内容<span class="symbol required"></span>--%>
                                    <%--</label>--%>
                                    <%--<div class="col-sm-4">--%>
                                    <%--<select required="required" name="houseLayout"--%>
                                    <%--class="form-control search-select select2 houseLayout">--%>
                                    <%--</select>--%>
                                    <%--</div>--%>
                                    <%--</div>--%>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            户型 (房) <span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <div class="input-group">
                                                <span class="input-group-addon">卧室</span>
                                                <input type="text" name="house" data-rule-number='true' class="form-control"
                                                       required="required">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            户型 (厅)
                                        </label>
                                        <div class="col-sm-4">
                                            <div class="input-group">
                                                <span class="input-group-addon">客厅</span>
                                                <input type="text" name="saloon" data-rule-number='true' class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            户型 (厨)
                                        </label>
                                        <div class="col-sm-4">
                                            <div class="input-group">
                                                <span class="input-group-addon">厨房</span>
                                                <input type="text" name="kitchen" data-rule-number='true' class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            户型 (卫) <span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <div class="input-group">
                                                <span class="input-group-addon">卫生间</span>
                                                <input type="text" name="toilet" data-rule-number='true' class="form-control"
                                                       required="required">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            户型 (花园)
                                        </label>
                                        <div class="col-sm-4">
                                            <div class="input-group">
                                                <span class="input-group-addon">花园</span>
                                                <input type="text" name="garden" data-rule-number='true' class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            户型 (阳台)
                                        </label>
                                        <div class="col-sm-4">
                                            <div class="input-group">
                                                <span class="input-group-addon">阳台</span>
                                                <input type="text" name="balcony" data-rule-number='true' class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            朝向<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="朝向"
                                                   name="orientation" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            户型图<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input id="house_latest_family_planV" name="house_latest_family_planV"
                                                   required="required" placeholder="上传附件" class="form-control"
                                                   type="file">
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

