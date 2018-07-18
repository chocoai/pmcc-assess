<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body>
    <div class="x_content">
        <div class="form-group">
            <div class="x-valid">
                <button type="button" class="btn btn-success" onclick="examineEstateNetworkDataObj.prototype.showModel()"
                        data-toggle="modal" href="#divBox"> 新增
                </button>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <table class="table table-bordered" id="examineEstateNetworkList">
                    <!-- cerare document add ajax data-->
                </table>
            </div>
        </div>
    </div>
</body>


<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">

    var examineEstateNetworkDataObj = function () {

    };
    examineEstateNetworkDataObj.prototype = {
        config:function () {
            var data = {};
            data.table = "examineEstateNetworkList" ;
            data.box = "divBoxExamineEstateNetwork";
            data.frm = "frmExamineEstateNetwork";
            return data;
        },
        loadDataDicList:function () {
            var cols = [];
            cols.push({field: 'name', title: '通讯名称'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="examineEstateNetworkDataObj.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="examineEstateNetworkDataObj.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#"+examineEstateNetworkDataObj.prototype.config().table).bootstrapTable('destroy');
            TableInit(examineEstateNetworkDataObj.prototype.config().table, "${pageContext.request.contextPath}/examineEstateNetwork/getExamineEstateNetworkList", cols, {
                name:$("#queryName").val()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData:function (id) {
            $.ajax({
                url:"${pageContext.request.contextPath}/examineEstateNetwork/deleteExamineEstateNetworkById",
                type: "post",
                dataType: "json",
                data: {id:id},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('删除成功');
                        examineEstateNetworkDataObj.prototype.loadDataDicList();
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
        showModel:function () {
            $("#"+examineEstateNetworkDataObj.prototype.config().frm).clearAll();
            $('#'+examineEstateNetworkDataObj.prototype.config().box).modal("show");
        },
        saveData:function () {
            if (!$("#"+examineEstateNetworkDataObj.prototype.config().frm).valid()){
                return false;
            }
            var data = formParams(examineEstateNetworkDataObj.prototype.config().frm);
            $.ajax({
                url:"${pageContext.request.contextPath}/examineEstateNetwork/saveAndUpdateExamineEstateNetwork",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#'+examineEstateNetworkDataObj.prototype.config().box).modal('hide');
                        examineEstateNetworkDataObj.prototype.loadDataDicList();
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
        getAndInit:function (id) {
            $.ajax({
                url:"${pageContext.request.contextPath}/examineEstateNetwork/getExamineEstateNetworkById",
                type: "get",
                dataType: "json",
                data: {id:id},
                success: function (result) {
                    if (result.ret) {
                        $("#"+examineEstateNetworkDataObj.prototype.config().frm).clearAll();
                        $("#" + examineEstateNetworkDataObj.prototype.config().frm).initForm(result.data);
                        $('#'+examineEstateNetworkDataObj.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        }
    }
    /**
     * 初始化
     */
    $(function () {
        examineEstateNetworkDataObj.prototype.loadDataDicList();
    })

</script>

<div id="divBoxExamineEstateNetwork" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">通信网络</h3>
            </div>
            <form id="frmExamineEstateNetwork" class="form-horizontal">
                <input type="hidden" id="id" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            通信网络名称
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="name"
                                                   placeholder="通信网络名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            服务内容
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="serviceContent"
                                                   placeholder="服务内容" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            通信网络指标参数
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="indexParameter"
                                                   placeholder="通信网络指标参数" required="required">
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
                    <button type="button" class="btn btn-primary" onclick="examineEstateNetworkDataObj.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>
