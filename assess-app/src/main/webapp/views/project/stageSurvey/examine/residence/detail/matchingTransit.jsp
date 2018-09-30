<%--
  公交
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="matchingTransit.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>公交信息
        </h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content" style="display: none">
        <div>
            <%--<button type="button" class="btn btn-success" onclick="matchingTransit.prototype.showModel()"--%>
                    <%--data-toggle="modal" href="#divBox"> 新增--%>
            <%--</button>--%>
        </div>
        <form class="form-horizontal">
            <div class="form-group">
                <div class="x-valid">
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <table class="table table-bordered" id="TransitList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var matchingTransit;
    (function () {
        var flag = true;
        matchingTransit = function () {

        };
        matchingTransit.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            viewInit: function () {
                matchingTransit.prototype.loadDataDicList();
                if (matchingTransit.prototype.getFlag()) {
                    matchingTransit.prototype.init();
                    matchingTransit.prototype.setFlag(false);
                }
            },
            config: function () {
                var data = {};
                data.table = "TransitList";
                data.box = "divBoxTransit";
                data.frm = "frmTransit";
                data.type = "transit";//根据ExamineMatchingTrafficTypeEnum 配置
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'name', title: '名称'});
                cols.push({field: 'distanceName', title: '距离'});
                // cols.push({field: 'type', title: '类型'});
                cols.push({field: 'lineName', title: '线路名称'});
                cols.push({field: 'theLine', title: '所在线路'});
                // cols.push({
                //     field: 'id', title: '操作', formatter: function (value, row, index) {
                //         var str = '<div class="btn-margin">';
                //         str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="matchingTransit.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                //         str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="matchingTransit.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                //         str += '</div>';
                //         return str;
                //     }
                // });
                $("#" + matchingTransit.prototype.config().table).bootstrapTable('destroy');
                TableInit(matchingTransit.prototype.config().table, "${pageContext.request.contextPath}/examineMatchingTraffic/getExamineMatchingTrafficList", cols, {
                    type: matchingTransit.prototype.config().type,
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
                    url: "${pageContext.request.contextPath}/examineMatchingTraffic/deleteExamineMatchingTrafficById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            matchingTransit.prototype.loadDataDicList();
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
                $("#" + matchingTransit.prototype.config().frm).clearAll();
                $("#" + matchingTransit.prototype.config().frm + " .type").val(matchingTransit.prototype.config().type);
                $('#' + matchingTransit.prototype.config().box).modal("show");
            },
            saveData: function () {
                if (!$("#" + matchingTransit.prototype.config().frm).valid()) {
                    return false;
                }
                var data = formParams(matchingTransit.prototype.config().frm);
                if ($("#declareId").size() > 0) {
                    data.declareId = $("#declareId").val();
                }
                if ($("#examineType").size() > 0) {
                    data.examineType = $("#examineType").val();
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineMatchingTraffic/saveAndUpdateExamineMatchingTraffic",
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('保存成功');
                            $('#' + matchingTransit.prototype.config().box).modal('hide');
                            matchingTransit.prototype.loadDataDicList();
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
                    url: "${pageContext.request.contextPath}/examineMatchingTraffic/getExamineMatchingTrafficById",
                    type: "get",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            $("#" + matchingTransit.prototype.config().frm).clearAll();
                            $("#" + matchingTransit.prototype.config().frm).initForm(result.data);
                            if (result.data.distance == null || result.data.distance == '') {
                                $("#" + matchingTransit.prototype.config().frm + " .distance").val(null).trigger("change");
                            } else {
                                $("#" + matchingTransit.prototype.config().frm + " .distance").val(result.data.distance).trigger("change");
                            }
                            $('#' + matchingTransit.prototype.config().box).modal("show");
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            init: function () {

            }
        }
    })();

</script>

<div id="divBoxTransit" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">公交</h3>
            </div>
            <form id="frmTransit" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type" class="type">
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
                                            <input type="text" class="form-control" name="name"
                                                   placeholder="名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            距离
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
                                            线路名称
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="lineName" class="form-control"
                                                   placeholder="线路名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            所在线路
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="theLine" class="form-control"
                                                   placeholder="所在线路" required="required">
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
                    <button type="button" class="btn btn-primary" onclick="matchingTransit.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>




