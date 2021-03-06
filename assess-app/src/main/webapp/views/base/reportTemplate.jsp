<%--
  Created by IntelliJ IDEA.
  User: red
  Date: 2017/10/17
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>报告模板管理</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>


<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="row">
                <div class="col-xs-12">
                    <div class="x_panel">
                        <div class="x_title collapse-link">
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                            </ul>
                            <h2>
                                <i class="fa ${baseViewDto.currentMenu.icon}"></i>
                                ${baseViewDto.currentMenu.name}
                            </h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <form class="form-horizontal">
                                <div class="form-group ">
                                    <div>
                                        <label class="col-sm-1 control-label">
                                            类型
                                        </label>
                                        <div class="col-sm-2">
                                            <select name="queryType" id="queryType" class="form-control">
                                                <option value="">--请选择--</option>
                                                <c:forEach var="item" items="${reportTemplateTypeList}">
                                                    <option value="${item.id}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div>
                                        <label class="col-sm-1 control-label">
                                            类别
                                        </label>
                                        <div class="col-sm-2">
                                            <select name="queryCategory" id="queryCategory" class="form-control">

                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-sm-3">
                                        <button type="button" class="btn btn-primary"
                                                onclick="loadTemplateList()">
                                            查询
                                        </button>
                                        <button type="button" class="btn btn-success" data-toggle="modal"
                                                href="#modalTemplate"
                                                onclick="addTemplate();"> 新增
                                        </button>
                                    </div>
                                </div>
                            </form>
                            <table id="tbList" class="table table-bordered"></table>
                        </div>
                    </div>
                    <div class="x_panel" id="panel_bookmark" style="display: none;">
                        <div class="x_title collapse-link">
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                            </ul>
                            <h2>
                                <label id="span_template_name">当前模板</label>的书签设置
                            </h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <p>
                                <button type="button" class="btn btn-success"
                                        data-toggle="modal"
                                        href="#modal_template_bookmark"
                                        onclick="addTemplateBookmark();"> 新增
                                </button>
                            </p>
                            <table id="tb_bookmark_list" class="table table-bordered"></table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <input type='hidden' id='templateId' name='templateId' value="0">
    <!-- end: MAIN CONTAINER -->
</div>

</body>
<%@include file="/views/share/main_footer.jsp" %>

<!-- start: org modal -->
<div id="modalTemplate" class="modal fade bs-example-modal-lg" data-height="200"
     data-backdrop="static" tabindex="1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class='modal-header'>
                <button type='button' class='close' data-dismiss='modal' aria-label='Close'><span
                        aria-hidden='true'>&times;</span></button>
                <h3 class='modal-title'>报告模板</h3></div>
            <form id='frm' class='form-horizontal'>
                <input type='hidden' id='id' name='id' value="0">
                <div class='modal-body'>
                    <div class='row'>
                        <div class='col-md-12'>
                            <div class='panel-body'>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input name='name' required class='form-control' required
                                                   maxlength="200">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class='x-valid'><label
                                            class='col-sm-2 control-label'>类型<span
                                            class="symbol required"></span></label>
                                        <div class='col-sm-4'>
                                            <select id='type' name='type' required class='form-control'>
                                                <option value="">--请选择--</option>
                                                <c:forEach var="item" items="${reportTemplateTypeList}">
                                                    <option value="${item.id}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class='x-valid'><label class='col-sm-2 control-label'>类别</label>
                                        <div class='col-sm-4'>
                                            <select id='category' name='category' class='form-control'>

                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class='x-valid'><label
                                            class='col-sm-2 control-label'>取附件sql
                                    </label>
                                        <div class='col-sm-10'>
                                         <textarea name="valueSql"
                                                   placeholder="select * from table"
                                                   class="form-control"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid"><label class="col-sm-2 control-label">模板文件</label>
                                        <div class="col-sm-10">
                                            <input id="uploadFile" name="uploadFile" type="file" multiple="false">
                                            <div id="_uploadFile">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class='modal-footer'>
                <button type='button' data-dismiss='modal' class='btn btn-default'>取消</button>
                <button type='button' class='btn btn-primary save_custom_model' onclick="saveTemplate();"
                        id='btn_save_bid_organization_agency'>保存
                </button>
            </div>
        </div>
    </div>
</div>
<!-- end: org  modal -->
<div id="modal_template_bookmark" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1"
     role="dialog" aria-hidden="true" data-height="200">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class='modal-header'>
                <button type='button' class='close' data-dismiss='modal' aria-label='Close'><span
                        aria-hidden='true'>&times;</span></button>
                <h4 class='modal-title'>书签配置</h4></div>

            <form id='frm_bookmark' class='form-horizontal'>
                <input type='hidden' id='bookmarkId' name='id' value="0">
                <div class='modal-body'>
                    <div class='row'>
                        <div class='col-md-12'>
                            <div class='panel-body'>
                                <div class="form-group">
                                    <div class='x-valid'><label
                                            class='col-sm-2 control-label'>书签名称<span
                                            class="symbol required"></span></label>
                                        <div class='col-sm-4'>
                                            <input name='name' class='form-control' required
                                                   maxlength="200">
                                        </div>
                                    </div>
                                    <div class='x-valid'><label
                                            class='col-sm-2 control-label'>书签中文名称<span
                                            class="symbol required"></span></label>
                                        <div class='col-sm-4'>
                                            <input name='displayName' class='form-control' required
                                                   maxlength="200">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class='x-valid'><label
                                            class='col-sm-2 control-label'>取值sql<span
                                            class="symbol required"></span></label>
                                        <div class='col-sm-10'>
                                         <textarea id="valueSql" required name="valueSql"
                                                   placeholder="select * from table"
                                                   class="form-control"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class='modal-footer'>
                <button type='button' data-dismiss='modal' class='btn btn-default'>取消</button>
                <button type='button' class='btn btn-primary save_custom_model' onclick="saveTemplateBookmark();"
                        id='btn_save_bookmark'>保存
                </button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function autoReadBookmark_Callback(message) {
        if (message == "ok") {
            notifySuccess('成功','书签导入成功');
            loadTemplateBookmarkList($("#templateId").val());
        } else {
            AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
        }
    }

</script>
<script type="application/javascript">
    $(function () {
        loadTemplateList();
        FileUtils.uploadFiles({
            target: "uploadFile",
            showFileList: false,
            fileExtArray: ["doc", "docx"],
            onUpload: function (file) {//上传之前触发
                var formData = {
                    tableName: "tb_report_template",
                    creater: "${currUserAccount}",
                    tableId: $("#id").val()
                };
                return formData;
            },
            onUploadComplete: function () {
                alert(123);
                loadTemplateAttachment($("#id").val(), "${currUserAccount}");
            }
        });

        $("#queryType").change(function () {
            $("#queryCategory").empty();
            AssessCommon.loadDataDicByPid($(this).val(), "", function (retHtml, data) {
                $("#queryCategory").html(retHtml);
            })
        })

        $("#type").change(function () {
            $("#category").empty();
            AssessCommon.loadDataDicByPid($(this).val(), "", function (retHtml, data) {
                $("#category").html(retHtml);
            })
        })
    })


    //加载列表数据
    function loadTemplateList() {
        var cols = [];
        cols.push({field: 'name', title: '名称'});
        cols.push({field: 'typeName', title: '类型'});
        cols.push({field: 'categoryName', title: '类别'});
        cols.push({
            field: 'opt', title: '操作', formatter: function (value, row, index) {
                var str = "<a style='margin-left: 5px;' data-placement='top' data-original-title='一键写入书签' class='btn btn-xs btn-success tooltips' onclick='autoReadBookmark(" + index + ")'   ><i class='fa fa-pencil fa-white'></i></a>";
                str += "<a style='margin-left: 5px;' data-placement='top' data-original-title='编辑' class='btn btn-xs btn-success tooltips' onclick='editTemplate(" + index + ")'   ><i class='fa fa-edit fa-white'></i></a>";
                str += "<a style='margin-left: 5px;' data-placement='top' data-original-title='删除'  class='btn btn-xs btn-warning tooltips'  onclick='delTemplate(" + row.id + ")'   ><i class='fa fa-minus fa-white'></i></a>";
                return str;

            }
        });
        $("#tbList").bootstrapTable('destroy');
        TableInit("tbList", "${pageContext.request.contextPath}/reportTemplate/getTemplateList", cols, {
            type: $("#queryType").val(),
            category: $("#queryCategory").val()
        }, {
            showColumns: false,
            showRefresh: false,
            uniqueId: "id",
            search: false,
            onClickRow: function (row) {
                $("#panel_bookmark").show();
                loadTemplateDetailInfo(row.id, row.subjectIdName, row.contractTypeName);
            },
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }


    //新增模板
    function addTemplate() {
        $("#frm").clearAll();
        $("#id").val("0");
        $("#category").empty();
        loadTemplateAttachment(0, "${currUserInfo.userAccount}");
    }

    //编辑模板
    function editTemplate(index) {
        var row = $("#tbList").bootstrapTable('getData')[index];
        $("#frm").clearAll();
        $("#frm").initForm(row);
        $("#category").empty();
        AssessCommon.loadDataDicByPid(row.type, row.category, function (retHtml, data) {
            $("#category").html(retHtml);
        })
        loadTemplateAttachment(row.id);
        $('#modalTemplate').modal();
    }

    //自动读取书签
    function autoReadBookmark(index) {
        var row = $("#tbList").bootstrapTable('getData')[index];
        var templateId = row.id;
        PageOfficeUtils.openWindow({
            url: '${pageContext.request.contextPath}/zhuozheng/bookmarkRead?templateId=' + templateId,
            callback: 'autoReadBookmark_Callback'
        })
    }

    //加载附件
    function loadTemplateAttachment(tableId, creater) {
        FileUtils.getFileShows({
            target: "uploadFile",
            formData: {
                tableName: "tb_report_template",
                creater: creater,
                tableId: tableId
            },
            deleteFlag: true
        });
    }

    //删除模板
    function delTemplate(id) {
        AlertConfirm("确认要删除么", "删除后数据不可恢复", function (result) {
                Loading.progressShow();
                $.ajax({
                    url: "${pageContext.request.contextPath}/reportTemplate/deleteTemplate",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        Loading.progressHide();
                        if (result.ret) {
                            notifySuccess('成功','删除成功');
                            $('#tbList').bootstrapTable("refresh");
                        } else {
                            AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        Loading.progressHide();
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                })
        });
    }

    //保存模板
    function saveTemplate() {
        if ($("#frm").valid()) {
            Loading.progressShow();
            var data = formParams("frm");
            $.ajax({
                url: "${pageContext.request.contextPath}/reportTemplate/saveTemplate",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        notifySuccess('成功','保存成功');
                        $('#tbList').bootstrapTable("refresh");
                        $('#modalTemplate').modal('hide');
                    } else {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        }
    }

    /**
     *加载模板其它配置信息
     * @param templateId
     */
    function loadTemplateDetailInfo(templateId, subjectIdName, contractTypeName) {
        $("#templateId").val(templateId);
        $("#span_template_name").text(subjectIdName + "-" + contractTypeName);
        loadTemplateBookmarkList(templateId);
        $("#panel_template_other_set").show();
    }

    //加载列表数据
    function loadTemplateBookmarkList(templateId) {
        var cols = [];
        cols.push({field: 'name', title: '书签名称', width: "20%"});
        cols.push({field: 'displayName', title: '书签中文名称', width: "20%"});
        cols.push({field: 'valueSql', title: '取值sql', width: "40%"});
        cols.push({
            field: 'opt', title: '操作', formatter: function (value, row, index) {
                var str = "<a style='margin-left: 5px;' data-placement='top' data-original-title='编辑' class='btn btn-xs btn-success tooltips' onclick='editTemplateBookmark(" + index + ")'   ><i class='fa fa-edit fa-white'></i></a>";
                str += "<a style='margin-left: 5px;' data-placement='top' data-original-title='删除'  class='btn btn-xs btn-warning tooltips'  onclick='delTemplateBookmark(" + row.id + ")'   ><i class='fa fa-minus fa-white'></i></a>";
                return str;
            }
        });
        $("#tb_bookmark_list").bootstrapTable('destroy');
        TableInit("tb_bookmark_list", "${pageContext.request.contextPath}/reportTemplate/getTemplateBookmarkList", cols, {
            templateId: templateId
        }, {
            showColumns: false,
            showRefresh: false,
            uniqueId: "id",
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }


    //新增模板
    function addTemplateBookmark() {
        $("#frm_bookmark").clearAll();
        $("#bookmarkId").val("0");
    }

    //编辑模板
    function editTemplateBookmark(index) {
        var row = $("#tb_bookmark_list").bootstrapTable('getData')[index];
        $("#frm_bookmark").clearAll();
        $("#frm_bookmark").initForm(row);
        $('#modal_template_bookmark').modal();
    }


    //删除模板
    function delTemplateBookmark(id) {
        AlertConfirm("确认要删除么", "删除后数据不可恢复", function (result) {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/reportTemplate/deleteTemplateBookmark",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        notifySuccess('成功','删除成功');
                        $('#tb_bookmark_list').bootstrapTable("refresh");
                    } else {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        });
    }

    //保存模板
    function saveTemplateBookmark() {
        if ($("#frm_bookmark").valid()) {
            Loading.progressShow();
            var data = formParams("frm_bookmark");
            data.templateId = $("#templateId").val();
            $.ajax({
                url: "${pageContext.request.contextPath}/reportTemplate/saveTemplateBookmark",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        notifySuccess('成功','保存成功');
                        $('#tb_bookmark_list').bootstrapTable("refresh");
                        $('#modal_template_bookmark').modal('hide');
                    } else {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        }
    }


</script>

</body>
</html>


