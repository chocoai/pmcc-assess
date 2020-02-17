<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="modalTemplate_bookmark_base" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">书签</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <p id="toolbar_bookmark">
                                    <%--<a class="btn btn-success" onclick="uploadDataSource();"> 刷新数据源 </a>--%>
                                    <button style="margin-left: 5px" class="btn btn-success btn-sm"
                                            onclick="uploadDataSource()">
                                        刷新数据源
                                    </button>
                                </p>
                                <table id="tb_bookmark_list" class="table table-bordered"></table>


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


<div id="modalTemplate_bookmark" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">合同书签</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmTemplate_bookmark" class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="form-group">
                                    <div class='x-valid'>
                                        <label class='col-sm-2 control-label'>名称<span class="symbol required"></span></label>
                                        <div class='col-sm-10'>
                                            <input type="hidden" name="id" required class='form-control'>
                                            <label type="text" name="name" class='form-control input-full'></label>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class='x-valid'>
                                        <label class='col-sm-2 control-label'>通用字段</label>
                                        <div class='col-sm-10'>
                                            <select id='template_bookmark_fieldName' name="fieldName" class='form-control search-select select2 input-full'>
                                                <option value="">请选择</option>
                                                <c:forEach var="item" items="${templateEnums}">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
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
                    取消
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="saveTemplateBookmark()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>




<script type="text/javascript">
    var bookmarkTable = $("#tb_bookmark_list");
    $(function () {
        loadBookmarkList();
        $("#template_bookmark_fieldName").select2();
    });
    function reloadBookmarkList(templateId) {
        TableReload(bookmarkTable, "${pageContext.request.contextPath}/DocumentTemplate/getCmsTemplateBookmark", {
            templateId: templateId
        });
    }
    function uploadDataSource() {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/DocumentTemplate/getBookmarkDatasource",
            type: "get",
            dataType: "json",
            data: {templateId: $("#templateId").val()},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    var ativityHtml = "<option value='-1'>全部</option>";
                    if (result.ret) {
                        $.each(result.data, function (i, j) {
                            ativityHtml += "<option value='" + j.key + "'>" + j.value + "</option>";
                        });
                    }
                    $("#template_bookmark_fieldName").html(ativityHtml);
                    $("#template_bookmark_fieldName").select2();
                }
                else {
                    notifyWarning("刷新失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                notifyWarning("刷新失败，失败原因:" + result);
            }
        })
    }

    function loadBookmarkList() {
        var cols = [];
        cols.push({field: 'name',width:"40%",  title: '书签名称'});
        cols.push({field: 'fieldName',width:"40%",  title: '通用字段'});
        cols.push({
            field: 'opt', title: '操作',width:"20%",  formatter: function (value, row, index) {
                var str = '<button onclick="editTemplateBookmark(' + index + ')"  style="margin-left: 5px;"  class="btn btn-icon btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                return str;
            }
        });
        TableInit(bookmarkTable, "${pageContext.request.contextPath}/DocumentTemplate/getCmsTemplateBookmark", cols, {
            templateId: 0
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            toolbar: '#toolbar_bookmark',
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    //编辑模板书签
    function editTemplateBookmark(index) {

        var row = $(bookmarkTable).bootstrapTable('getData')[index];
        $("#frmTemplate_bookmark").clearAll();
        $("#frmTemplate_bookmark").initForm(row);
        $("#template_bookmark_fieldName").select2().val(row.fieldName).trigger("change");
        $('#modalTemplate_bookmark').modal({backdrop: 'static', keyboard: false});
    }


    //保存模板书签
    function saveTemplateBookmark() {
        if (!$("#frmTemplate_bookmark").valid()) {
            return false;
        }
        var data = formParams("frmTemplate_bookmark");
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/DocumentTemplate/saveCmsTemplateBookmark",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "数据已成功保存到数据库");
                    TableReload(bookmarkTable);
                    $('#modalTemplate_bookmark').modal('hide');
                }
                else {
                    AlertError("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        })
    }


</script>
