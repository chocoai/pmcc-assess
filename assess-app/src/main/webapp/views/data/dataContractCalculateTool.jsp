<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                        ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <c:if test="${readonly ne true}">
                        <form id="frmQuery" class="form-horizontal">
                            <div class="form-group ">

                                <div class="col-sm-3">
                                        <%--   <button type="button" class="btn btn-primary" onclick="dataContractCalculateTool.prototype.loadDataDicList()">
                                               查询
                                           </button>--%>

                                    <button type="button" class="btn btn-success"
                                            onclick="dataContractCalculateTool.prototype.showModel()"
                                            data-toggle="modal" href="#divBox"> 新增
                                    </button>
                                </div>
                            </div>
                        </form>
                    </c:if>
                    <table class="table table-bordered" id="tb_FatherList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    $(function () {
        dataContractCalculateTool.prototype.loadDataDicList();
    });
    var dataContractCalculateTool = function () {

    };
    dataContractCalculateTool.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_FatherList";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            return data;
        },
        loadDataDicList: function () {
            var cols = [];
            cols.push({field: 'referenceNumber', title: '文号'});
            cols.push({field: 'customerName', title: '客户名称'});
            cols.push({field: 'fileViewName', title: '附件'});
            <c:if test="${readonly ne true}">
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="dataContractCalculateTool.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="dataContractCalculateTool.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            </c:if>
            $("#" + dataContractCalculateTool.prototype.config().table).bootstrapTable('destroy');
            TableInit(dataContractCalculateTool.prototype.config().table, "${pageContext.request.contextPath}/dataContractCalculateTool/list", cols, {}, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            Alert("确认删除!", 2, null, function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/dataContractCalculateTool/deleteDataContractCalculateToolById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            dataContractCalculateTool.prototype.loadDataDicList();
                        }
                        else {
                            Alert("保存数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            });
        },
        showModel: function () {
            $("#" + dataContractCalculateTool.prototype.config().frm).clearAll();
            dataContractCalculateTool.prototype.loadFiles(0);
            dataContractCalculateTool.prototype.showFileList(0);
            $('#' + dataContractCalculateTool.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + dataContractCalculateTool.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(dataContractCalculateTool.prototype.config().frm);
            $.ajax({
                url: "${pageContext.request.contextPath}/dataContractCalculateTool/saveAndUpdateDataContractCalculateTool",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#' + dataContractCalculateTool.prototype.config().box).modal('hide');
                        dataContractCalculateTool.prototype.loadDataDicList();
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
                url: "${pageContext.request.contextPath}/dataContractCalculateTool/getDataContractCalculateToolById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + dataContractCalculateTool.prototype.config().frm).clearAll();
                        $("#" + dataContractCalculateTool.prototype.config().frm).initForm(result.data);
                        dataContractCalculateTool.prototype.loadFiles(id);
                        dataContractCalculateTool.prototype.showFileList(id);
                        $('#' + dataContractCalculateTool.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        //显示附件列表
        showFileList: function (tableId) {
            FileUtils.getFileShows({
                target: "uploadFile",
                formData: {
                    tableName: AssessDBKey.DataContractCalculateTool,
                    tableId: tableId
                },
                deleteFlag: true
            })
        },
        loadFiles: function (tableId) {
            FileUtils.uploadFiles({
                target: "uploadFile",
                onUpload: function (file) {
                    var formData = {
                        tableName: AssessDBKey.DataContractCalculateTool,
                        tableId: tableId
                    };
                    return formData;
                }, onUploadComplete: function (result, file) {
                    dataContractCalculateTool.prototype.showFileList(tableId);
                },
                deleteFlag: true
            });
        }

    }
</script>
<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">合同计算工具配置</h3>
            </div>
            <form id="frmFather" class="form-horizontal">
                <input type="hidden" id="id" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        文号
                                    </label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="referenceNumber" placeholder="文号">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">
                                        客户名称
                                    </label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="customerName" placeholder="客户名称">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">附件</label>
                                        <div class="col-sm-10">
                                            <input id="uploadFile" placeholder="上传附件" class="form-control" type="file">
                                            <div id="_uploadFile"></div>
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
                            onclick="dataContractCalculateTool.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>

