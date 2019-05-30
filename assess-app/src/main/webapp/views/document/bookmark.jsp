<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="modalTemplate_bookmark_base" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class='modal-header'>
                <h3 class='modal-title'>书签</h3></div>
            <p id="toolbar_bookmark">
                <a class="btn btn-success" onclick="uploadDataSource();"> 刷新数据源 </a>
            </p>
            <table id="tb_bookmark_list" class="table table-bordered"></table>
            <div class='modal-footer'>
                <button type='button' data-dismiss='modal' class='btn btn-default'>取消</button>
            </div>
        </div>
    </div>
</div>


<div id="modalTemplate_bookmark" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class='modal-header'>
                <h3 class='modal-title'>合同书签</h3></div>
            <form id='frmTemplate_bookmark' class='form-horizontal'>
                <div class='modal-body'>
                    <div class="form-group">
                        <div class='x-valid'>
                            <label class='col-sm-2 control-label'>名称<span class="symbol required"></span></label>
                            <div class='col-sm-10'>
                                <input type="hidden" name="id" required class='form-control'>
                                <label type="text" name="name" class='form-control'></label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class='x-valid'>
                            <label class='col-sm-2 control-label'>通用字段</label>
                            <div class='col-sm-10'>
                                <select id='template_bookmark_fieldName' name="fieldName" class='form-control search-select select2'>
                                    <option value="">请选择</option>
                                    <c:forEach var="item" items="${templateEnums}">
                                        <option value="${item.key}">${item.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class='modal-footer'>
                <button type='button' data-dismiss='modal' class='btn btn-default'>取消</button>
                <button type='button' class='btn btn-primary' onclick="saveTemplateBookmark();">保存
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
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    function loadBookmarkList() {
        var cols = [];
        cols.push({field: 'name', title: '书签名称'});
        cols.push({field: 'fieldName', title: '通用字段'});
        cols.push({
            field: 'opt', title: '操作', formatter: function (value, row, index) {
                var str = "<a style='margin-left: 5px;' data-placement='top' data-original-title='编辑' class='btn btn-xs btn-success tooltips' onclick='editTemplateBookmark(" + index + ")'   ><i class='fa fa-edit fa-white'></i></a>";

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
                    toastr.success('保存成功');
                    TableReload(bookmarkTable);
                    $('#modalTemplate_bookmark').modal('hide');
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }


</script>
