<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">
    var dataLocaleSurveyPicture = function () {

    };
    dataLocaleSurveyPicture.prototype = {
        config: function () {
            var data = {};
            data.table = "dataLocaleSurveyPictureList";
            data.box = "divDataLocaleSurveyPicture";
            data.frm = "frmDataLocaleSurveyPicture";
            return data;
        },
        isEmpty: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function (id) {
            var cols = [];
            cols.push({field: 'fileName', width: '20%', title: '名称'});
            cols.push({field: 'certifyPartName',width: '20%',  title: '对应查勘部位'});
            cols.push({field: 'certifyPartCategoryName',width: '20%',  title: '附件类别'});
            cols.push({field: 'sorting',width: '10%',  title: '排序'});
            cols.push({field: 'remark', width: '20%', title: '备注'});
            cols.push({
                field: 'id', width: '10%', title: '操作', formatter: function (value, row, index) {
                    var str = '<button onclick="dataLocaleSurveyPicture.prototype.getAndInit(' + row.id + ')"  style="margin-left: 5px;"  class="btn btn-icon btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                    str += '<i class="fa fa-pen"></i>';
                    str += '</button>';
                    str += '<button onclick="dataLocaleSurveyPicture.prototype.removeData(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn btn-icon btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                    str += '<i class="fa fa-minus"></i>';
                    str += '</button>';
                    return str;
                }
            });
            $("#" + dataLocaleSurveyPicture.prototype.config().table).bootstrapTable('destroy');
            TableInit(dataLocaleSurveyPicture.prototype.config().table, "${pageContext.request.contextPath}/dataLocaleSurveyPicture/getDataLocaleSurveyPictureList", cols, {
                masterId: id
            }, {
                showColumns: false,
                showRefresh: false,
                toolbar: '#toolBtn',
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            var masterId = $('#divBoxDetail').find("#masterId").val();
            $.ajax({
                url: "${pageContext.request.contextPath}/dataLocaleSurveyPicture/deleteDataLocaleSurveyPictureById",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除数据成功");
                        dataLocaleSurveyPicture.prototype.loadDataDicList(masterId);
                    }
                    else {
                        AlertError("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
            })
        },
        showModel: function () {
            $("#" + dataLocaleSurveyPicture.prototype.config().frm).clearAll();
            AssessCommon.loadDataDicByKey(AssessDicKey.certifyPart, '', function (html, data) {
                $("#" + dataLocaleSurveyPicture.prototype.config().frm).find("select[name='certifyPart']").empty().html(html).trigger('change');
            });
            //绑定变更事件
            $("#" + dataLocaleSurveyPicture.prototype.config().frm).find("select[name='certifyPart']").on('change', function () {
                dataLocaleSurveyPicture.prototype.getCategory($(this).val(), false);
            });
            $('#' + dataLocaleSurveyPicture.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + dataLocaleSurveyPicture.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(dataLocaleSurveyPicture.prototype.config().frm);
            var masterId = $('#divBoxDetail').find("#masterId").val();
            data.masterId = masterId;
            $.ajax({
                url: "${pageContext.request.contextPath}/dataLocaleSurveyPicture/saveAndUpdateDataLocaleSurveyPicture",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        $('#' + dataLocaleSurveyPicture.prototype.config().box).modal('hide');
                        dataLocaleSurveyPicture.prototype.loadDataDicList(masterId);
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/dataLocaleSurveyPicture/getDataLocaleSurveyPictureById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + dataLocaleSurveyPicture.prototype.config().frm).clearAll();
                        $("#" + dataLocaleSurveyPicture.prototype.config().frm).initForm(result.data);
                        AssessCommon.loadDataDicByKey(AssessDicKey.certifyPart, result.data.certifyPart, function (html, data) {
                            $("#" + dataLocaleSurveyPicture.prototype.config().frm).find("select[name='certifyPart']").empty().html(html).trigger('change');
                        });
                        //绑定变更事件
                        $("#" + dataLocaleSurveyPicture.prototype.config().frm).find("select[name='certifyPart']").on('change', function () {
                            dataLocaleSurveyPicture.prototype.getCategory($(this).val(), result.data.certifyPartCategory);
                        });
                        $('#' + dataLocaleSurveyPicture.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getCategory: function (pid, value) {
            if (!pid) {
                var option = "<option value=''>-先选择对应查勘部位-</option>";
                $("#" + dataLocaleSurveyPicture.prototype.config().frm).find("select[name='certifyPartCategory']").html(option);
                $("#" + dataLocaleSurveyPicture.prototype.config().frm).find("select[name='certifyPartCategory']").val(['']).trigger('change');
                return false;
            }
            $.ajax({
                url: "${pageContext.request.contextPath}/baseDataDic/getCacheDataDicListByPid",
                type: "get",
                dataType: "json",
                data: {pid: pid},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (data.length >= 1) {
                            var option = "<option value=''>-请选择-</option>";
                            for (var i = 0; i < data.length; i++) {
                                option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                            }
                            $("#" + dataLocaleSurveyPicture.prototype.config().frm).find("select[name='certifyPartCategory']").html(option);
                            if (value) {
                                $("#" + dataLocaleSurveyPicture.prototype.config().frm).find("select[name='certifyPartCategory']").val([value]).trigger('change');
                            } else {
                                $("#" + dataLocaleSurveyPicture.prototype.config().frm).find("select[name='certifyPartCategory']").val(['']).trigger('change');
                            }
                        }

                    }
                    else {
                        notifyWarning("获取附件类别失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    notifyWarning("调用服务端方法失败，失败原因:" + result);
                }
            })

        }, showItemModel: function (id) {
            dataLocaleSurveyPicture.prototype.loadDataDicList(id);
            $('#divBoxDetail').modal("show");
            $('#divBoxDetail').find("#masterId").val(id);
        }

    }
</script>

<div id="divBoxDetail" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">明细</h4>
                <input type="hidden" name="housePriceId">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card-body">
                            <input type="hidden" id="masterId">
                            <p id="toolBtn">
                                <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                        data-toggle="modal" onclick="dataLocaleSurveyPicture.prototype.showModel()" href="#indexDetailBox">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                    新增
                                </button>
                            </p>
                            <table class="table table-bordered" id="dataLocaleSurveyPictureList">
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>

        </div>
    </div>
</div>


<div id="divDataLocaleSurveyPicture" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">现场查勘图片配置</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmDataLocaleSurveyPicture" class="form-horizontal">
                    <input type="hidden" id="id" name="id">
                    <input type="hidden" name="masterId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full" required
                                                       name="fileName" placeholder="名称">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                排序<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full" required
                                                       name="sorting" placeholder="排序">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <div class="col-sm-10">
                                                <div class="form-check" style="justify-content:left">
                                                    <label class="form-check-label">
                                                        <input class="form-check-input" type="checkbox" id="bisEnable"
                                                               name="bisEnable" value="true"
                                                               checked="checked">
                                                        <span class="form-check-sign">是否上报告</span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                对应查勘部位<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="certifyPart"
                                                        class="form-control input-full search-select certifyPart select2"
                                                        required>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                附件类别<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="certifyPartCategory"
                                                        class="form-control input-full search-select certifyPartCategory select2"
                                                        required>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                备注
                                            </label>
                                            <div class="col-sm-11">
                                            <textarea placeholder="备注" id="remark" name="remark"
                                                      class="form-control input-full input-full"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm"
                        onclick="dataLocaleSurveyPicture.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


