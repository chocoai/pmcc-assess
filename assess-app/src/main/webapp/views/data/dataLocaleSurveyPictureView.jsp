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
            cols.push({field: 'fileName', title: '名称'});
            cols.push({field: 'certifyPartName', title: '对应查勘部位'});
            cols.push({field: 'certifyPartCategoryName', title: '附件类别'});
            cols.push({field: 'sorting', title: '排序'});
            cols.push({field: 'remark', title: '备注'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="dataLocaleSurveyPicture.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="dataLocaleSurveyPicture.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + dataLocaleSurveyPicture.prototype.config().table).bootstrapTable('destroy');
            TableInit(dataLocaleSurveyPicture.prototype.config().table, "${pageContext.request.contextPath}/dataLocaleSurveyPicture/getDataLocaleSurveyPictureList", cols, {
                masterId: id
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
            var masterId = $('#frmMaster').find("#masterId").val();
            $.ajax({
                url: "${pageContext.request.contextPath}/dataLocaleSurveyPicture/deleteDataLocaleSurveyPictureById",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('删除成功');
                        dataLocaleSurveyPicture.prototype.loadDataDicList(masterId);
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
            var masterId = $('#frmMaster').find("#masterId").val();
            data.masterId = masterId;
            $.ajax({
                url: "${pageContext.request.contextPath}/dataLocaleSurveyPicture/saveAndUpdateDataLocaleSurveyPicture",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#' + dataLocaleSurveyPicture.prototype.config().box).modal('hide');
                        dataLocaleSurveyPicture.prototype.loadDataDicList(masterId);
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
                    Alert("调用服务端方法失败，失败原因:" + result);
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
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })

        }, showItemModel: function (id) {
            dataLocaleSurveyPicture.prototype.loadDataDicList(id);
            $('#divBoxDetail').modal("show");
            $('#frmMaster').find("#masterId").val(id);
        }

    }
</script>
<div id="divDataLocaleSurveyPicture" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">现场查勘图片配置</h3>
            </div>
            <form id="frmDataLocaleSurveyPicture" class="form-horizontal">
                <input type="hidden" id="id" name="id">
                <input type="hidden" name="masterId">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <%--<div class="x-valid">--%>
                                        <%--<label class="col-sm-1 control-label">类型<span--%>
                                                <%--class="symbol required"></span></label>--%>
                                        <%--<div class="col-sm-3">--%>
                                            <%--<select name='type' class='form-control search-select select2' required>--%>
                                                <%--<option value="">-请选择-</option>--%>
                                                <%--<c:forEach var="item" items="${pictureTemplates}">--%>
                                                    <%--<option value="${item.id}">${item.name}</option>--%>
                                                <%--</c:forEach>--%>
                                            <%--</select>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">名称<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" required
                                                   name="fileName" placeholder="名称">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">排序<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" required
                                                   name="sorting" placeholder="排序">
                                        </div>
                                    </div>
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">
                                                是否上报告
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" id="bisEnable" name="bisEnable"
                                                           value="true" checked>
                                                </label>
                                            </div>
                                        </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">对应查勘部位<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select name="certifyPart"
                                                    class="form-control search-select certifyPart select2" required>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">附件类别<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select name="certifyPartCategory"
                                                    class="form-control search-select certifyPartCategory select2"
                                                    required>
                                            </select>
                                        </div>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">备注</label>
                                        <div class="col-sm-10">
                                    <textarea placeholder="备注" name="remark"
                                              class="form-control"></textarea>
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
                    <button type="button" class="btn btn-primary"
                            onclick="dataLocaleSurveyPicture.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxDetail" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">明细</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                <form id="frmMaster" class="form-horizontal">
                    <input type="hidden" id="masterId">
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <button type="button" data-dismiss="modal" class="btn btn-default"
                                onclick="dataLocaleSurveyPicture.prototype.showModel()">
                            新增
                        </button>
                        <table class="table table-bordered" id="dataLocaleSurveyPictureList">
                            <!-- cerare document add ajax data-->
                        </table>

                    </div>
                </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>
