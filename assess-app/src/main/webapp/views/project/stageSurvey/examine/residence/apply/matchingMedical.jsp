<%--
  医养条件
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="matchingMedical.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>医养条件信息
        </h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content" style="display:none;">
        <div>
            <button type="button" class="btn btn-success" onclick="matchingMedical.prototype.showModel()"
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
                    <table class="table table-bordered" id="MatchingMedicalList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var matchingMedical;
    (function () {
        var flag = true;
        matchingMedical = function () {

        };
        matchingMedical.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            viewInit: function () {
                matchingMedical.prototype.loadDataDicList();
                if (matchingMedical.prototype.getFlag()) {
                    matchingMedical.prototype.init();
                    matchingMedical.prototype.setFlag(false);
                }
            },
            config: function () {
                var data = {};
                data.table = "MatchingMedicalList";
                data.box = "divBoxMatchingMedical";
                data.frm = "frmMatchingMedical";
                data.type = "null";//
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'bedNumber', title: '床位数'});
                cols.push({field: 'distanceName', title: '医养条件距离'});
                cols.push({field: 'organizationLevelName', title: '医养条件机构等级'});
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="matchingMedical.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="matchingMedical.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                $("#" + matchingMedical.prototype.config().table).bootstrapTable('destroy');
                TableInit(matchingMedical.prototype.config().table, "${pageContext.request.contextPath}/examineMatchingMedical/getExamineMatchingMedicalList", cols, {
                    type: matchingMedical.prototype.config().type,
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
                    url: "${pageContext.request.contextPath}/examineMatchingMedical/deleteExamineMatchingMedicalById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            matchingMedical.prototype.loadDataDicList();
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
                $("#" + matchingMedical.prototype.config().frm).clearAll();
                $("#" + matchingMedical.prototype.config().frm + " .type").val(matchingMedical.prototype.config().type);
                // matchingMedical.prototype.init();
                $('#' + matchingMedical.prototype.config().box).modal("show");
            },
            saveData: function () {
                if (!$("#" + matchingMedical.prototype.config().frm).valid()) {
                    return false;
                }
                var data = formParams(matchingMedical.prototype.config().frm);
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
                    url: "${pageContext.request.contextPath}/examineMatchingMedical/saveAndUpdateExamineMatchingMedical",
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('保存成功');
                            $('#' + matchingMedical.prototype.config().box).modal('hide');
                            matchingMedical.prototype.loadDataDicList();
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
                    url: "${pageContext.request.contextPath}/examineMatchingMedical/getExamineMatchingMedicalById",
                    type: "get",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            $("#" + matchingMedical.prototype.config().frm).clearAll();
                            $("#" + matchingMedical.prototype.config().frm).initForm(result.data);
                            if (result.data.organizationLevel == null || result.data.organizationLevel == '') {
                                $("#" + matchingMedical.prototype.config().frm + " .organizationLevel").val(null).trigger("change");
                            } else {
                                $("#" + matchingMedical.prototype.config().frm + " .organizationLevel").val(result.data.organizationLevel).trigger("change");
                            }
                            if (result.data.distance == null || result.data.distance == '') {
                                $("#" + matchingMedical.prototype.config().frm + " .distance").val(null).trigger("change");
                            } else {
                                $("#" + matchingMedical.prototype.config().frm + " .distance").val(result.data.distance).trigger("change");
                            }
                            $('#' + matchingMedical.prototype.config().box).modal("show");
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            init: function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineMatchingMedical/estate_examinematchingmedical_level",
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
                                $("#" + matchingMedical.prototype.config().frm + " .organizationLevel").html(option);
                                $("#" + matchingMedical.prototype.config().frm + " .organizationLevel").select2({minimumResultsForSearch: -1});//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })

                $.ajax({
                    url: "${pageContext.request.contextPath}/examineMatchingMedical/estate_examinematchingmedical_distance",
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
                                $("#" + matchingMedical.prototype.config().frm + " .distance").html(option);
                                $("#" + matchingMedical.prototype.config().frm + " .distance").select2({minimumResultsForSearch: -1});//加载样式
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

<div id="divBoxMatchingMedical" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">医养条件</h3>
            </div>
            <form id="frmMatchingMedical" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            床位数<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="bedNumber" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            医养条件距离<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="distance"
                                                    class="form-control search-select select2 distance">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            医养条件内容<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="organizationLevel"
                                                    class="form-control search-select select2 organizationLevel">
                                            </select>
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
                    <button type="button" class="btn btn-primary" onclick="matchingMedical.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>

