<%--
  环境因素
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="matchingEnvironment.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>环境因素信息
        </h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content" style="display: none">
        <div>
            <button type="button" class="btn btn-success" onclick="matchingEnvironment.prototype.showModel()"
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
                    <table class="table table-bordered" id="MatchingEnvironmentList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var matchingEnvironment;
    (function () {
        var flag = true;
        matchingEnvironment = function () {

        };
        matchingEnvironment.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            viewInit: function () {
                if (matchingEnvironment.prototype.getFlag()) {
                    matchingEnvironment.prototype.init();
                    matchingEnvironment.prototype.setFlag(false);
                }
                matchingEnvironment.prototype.loadDataDicList();
            },
            config: function () {
                var data = {};
                data.table = "MatchingEnvironmentList";
                data.box = "divBoxMatchingEnvironment";
                data.frm = "frmMatchingEnvironment";
                data.type = "null";//
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'typeName', title: '环境类型'});
                cols.push({field: 'categoryName', title: '影响因素'});
                cols.push({field: 'influenceDegreeName', title: '影响程度'});
                cols.push({field: 'remark', title: '描述'});
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="matchingEnvironment.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="matchingEnvironment.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                $("#" + matchingEnvironment.prototype.config().table).bootstrapTable('destroy');
                TableInit(matchingEnvironment.prototype.config().table, "${pageContext.request.contextPath}/examineMatchingEnvironment/getExamineMatchingEnvironmentList", cols, {
                    type: matchingEnvironment.prototype.config().type,
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
                    url: "${pageContext.request.contextPath}/examineMatchingEnvironment/deleteExamineMatchingEnvironmentById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            matchingEnvironment.prototype.loadDataDicList();
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
                $("#" + matchingEnvironment.prototype.config().frm).clearAll();
                $("#" + matchingEnvironment.prototype.config().frm + " .type").val(matchingEnvironment.prototype.config().type);
                // matchingEnvironment.prototype.init();
                $('#' + matchingEnvironment.prototype.config().box).modal("show");
            },
            saveData: function () {
                if (!$("#" + matchingEnvironment.prototype.config().frm).valid()) {
                    return false;
                }
                var data = formParams(matchingEnvironment.prototype.config().frm);
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
                    url: "${pageContext.request.contextPath}/examineMatchingEnvironment/saveAndUpdateExamineMatchingEnvironment",
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('保存成功');
                            $('#' + matchingEnvironment.prototype.config().box).modal('hide');
                            matchingEnvironment.prototype.loadDataDicList();
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
                    url: "${pageContext.request.contextPath}/examineMatchingEnvironment/getExamineMatchingEnvironmentById",
                    type: "get",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            $("#" + matchingEnvironment.prototype.config().frm).clearAll();
                            $("#" + matchingEnvironment.prototype.config().frm).initForm(result.data);
                            if (result.data.category == null || result.data.category == '') {
                                $("#" + matchingEnvironment.prototype.config().frm + " .category").val(null).trigger("change");
                            } else {
                                $("#" + matchingEnvironment.prototype.config().frm + " .category").val(result.data.category).trigger("change");
                            }
                            if (result.data.type == null || result.data.type == '') {
                                $("#" + matchingEnvironment.prototype.config().frm + " .type").val(null).trigger("change");
                            } else {
                                $("#" + matchingEnvironment.prototype.config().frm + " .type").val(result.data.type).trigger("change");
                            }
                            if (result.data.influenceDegree == null || result.data.influenceDegree == '') {
                                $("#" + matchingEnvironment.prototype.config().frm + " .influenceDegree").val(null).trigger("change");
                            } else {
                                $("#" + matchingEnvironment.prototype.config().frm + " .influenceDegree").val(result.data.influenceDegree).trigger("change");
                            }
                            $('#' + matchingEnvironment.prototype.config().box).modal("show");
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            init: function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineMatchingEnvironment/environment_category",
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
                                $("#" + matchingEnvironment.prototype.config().frm ).find("select.category").html(option);
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineMatchingEnvironment/environment_type",
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
                                $("#" + matchingEnvironment.prototype.config().frm ).find("select.type").html(option);
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineMatchingEnvironment/environment_influence_degree",
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
                                $("#" + matchingEnvironment.prototype.config().frm ).find("select.influenceDegree").html(option);
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

<div id="divBoxMatchingEnvironment" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">环境因素</h3>
            </div>
            <form id="frmMatchingEnvironment" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            环境类型<span class="symbol required"></span>
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
                                            影响因素<span class="symbol required"></span>
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
                                            影响程度<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="influenceDegree"
                                                    class="form-control search-select select2 influenceDegree">
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
                    <button type="button" class="btn btn-primary" onclick="matchingEnvironment.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>