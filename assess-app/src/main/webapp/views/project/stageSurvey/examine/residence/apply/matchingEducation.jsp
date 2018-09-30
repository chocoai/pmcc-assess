<%--
教育条件
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="matchingEducation.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>教育条件信息
        </h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content" style="display: none;">
        <div>
            <button type="button" class="btn btn-success" onclick="matchingEducation.prototype.showModel()"
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
                    <table class="table table-bordered" id="MatchingEducationList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var matchingEducation;
    (function () {
        var flag = true;
        matchingEducation = function () {

        };
        matchingEducation.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            viewInit: function () {
                matchingEducation.prototype.loadDataDicList();
                if (matchingEducation.prototype.getFlag()) {
                    matchingEducation.prototype.init();
                    matchingEducation.prototype.setFlag(false);
                }
            },
            config: function () {
                var data = {};
                data.table = "MatchingEducationList";
                data.box = "divBoxMatchingEducation";
                data.frm = "frmMatchingEducation";
                data.type = "null";//
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'schoolName', title: '学校名称'});
                cols.push({field: 'schoolNatureName', title: '学校性质'});
                cols.push({field: 'schoolGradationName', title: '学校级次'});
                cols.push({field: 'schoolLevelName', title: '学校等级'});
                cols.push({field: 'distanceName', title: '距离'});
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="matchingEducation.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="matchingEducation.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                $("#" + matchingEducation.prototype.config().table).bootstrapTable('destroy');
                TableInit(matchingEducation.prototype.config().table, "${pageContext.request.contextPath}/examineMatchingEducation/getExamineMatchingEducationList", cols, {
                    type: matchingEducation.prototype.config().type,
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
                    url: "${pageContext.request.contextPath}/examineMatchingEducation/deleteExamineMatchingEducationById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            matchingEducation.prototype.loadDataDicList();
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
                $("#" + matchingEducation.prototype.config().frm).clearAll();
                $("#" + matchingEducation.prototype.config().frm + " .type").val(matchingEducation.prototype.config().type);
                // matchingEducation.prototype.init();
                $('#' + matchingEducation.prototype.config().box).modal("show");
            },
            saveData: function () {
                if (!$("#" + matchingEducation.prototype.config().frm).valid()) {
                    return false;
                }
                var data = formParams(matchingEducation.prototype.config().frm);
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
                    url: "${pageContext.request.contextPath}/examineMatchingEducation/saveAndUpdateExamineMatchingEducation",
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('保存成功');
                            $('#' + matchingEducation.prototype.config().box).modal('hide');
                            matchingEducation.prototype.loadDataDicList();
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
                    url: "${pageContext.request.contextPath}/examineMatchingEducation/getExamineMatchingEducationById",
                    type: "get",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            $("#" + matchingEducation.prototype.config().frm).clearAll();
                            $("#" + matchingEducation.prototype.config().frm).initForm(result.data);
                            if (result.data.schoolNature == null || result.data.schoolNature == '') {
                                $("#" + matchingEducation.prototype.config().frm + " .schoolNature").val(null).trigger("change");
                            } else {
                                $("#" + matchingEducation.prototype.config().frm + " .schoolNature").val(result.data.schoolNature).trigger("change");
                            }
                            if (result.data.schoolGradation == null || result.data.schoolGradation == '') {
                                $("#" + matchingEducation.prototype.config().frm + " .schoolGradation").val(null).trigger("change");
                            } else {
                                $("#" + matchingEducation.prototype.config().frm + " .schoolGradation").val(result.data.schoolGradation).trigger("change");
                            }
                            if (result.data.schoolLevel == null || result.data.schoolLevel == '') {
                                $("#" + matchingEducation.prototype.config().frm + " .schoolLevel").val(null).trigger("change");
                            } else {
                                $("#" + matchingEducation.prototype.config().frm + " .schoolLevel").val(result.data.schoolLevel).trigger("change");
                            }
                            if (result.data.distance == null || result.data.distance == '') {
                                $("#" + matchingEducation.prototype.config().frm + " .distance").val(null).trigger("change");
                            } else {
                                $("#" + matchingEducation.prototype.config().frm + " .distance").val(result.data.distance).trigger("change");
                            }
                            $('#' + matchingEducation.prototype.config().box).modal("show");
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            init: function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineMatchingEducation/school_nature",
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
                                $("#" + matchingEducation.prototype.config().frm).find("select.schoolNature").html(option);
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineMatchingEducation/school_gradation",
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
                                $("#" + matchingEducation.prototype.config().frm).find("select.schoolGradation").html(option);
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineMatchingEducation/school_level",
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
                                $("#" + matchingEducation.prototype.config().frm).find("select.schoolLevel").html(option);
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineMatchingEducation/estate_distance",
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
                                $("#" + matchingEducation.prototype.config().frm).find("select.distance").html(option);
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

<div id="divBoxMatchingEducation" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">教育条件</h3>
            </div>
            <form id="frmMatchingEducation" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type" class="type">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            学校名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="schoolName"
                                                   placeholder="学校名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            学校等级<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="schoolLevel"
                                                    class="form-control search-select select2 schoolLevel">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            距离<span class="symbol required"></span>
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
                                            学校级次<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="schoolGradation"
                                                    class="form-control search-select select2 schoolGradation">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            学校性质<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="schoolNature"
                                                    class="form-control search-select select2 schoolNature">
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
                    <button type="button" class="btn btn-primary" onclick="matchingEducation.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>


