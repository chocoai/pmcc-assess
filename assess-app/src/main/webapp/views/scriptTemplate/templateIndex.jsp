<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <style>
        .product-buyer-name {
            overflow: hidden; /*自动隐藏文字*/
            text-overflow: ellipsis; /*文字隐藏后添加省略号*/
            display: -webkit-box;
            -webkit-line-clamp: 6; /*想要显示的行数*/
            -webkit-box-orient: vertical;
        }
    </style>
</head>

<body>
<div class="wrapper">
    <%@include file="/views/share/main_navigation.jsp" %>
    <%@include file="/views/share/main_head.jsp" %>
    <div class="main-panel">
        <div class="content">
            <div class="panel-header bg-primary-gradient">
                <div class="page-inner py-5">
                </div>
            </div>
            <div class="page-inner mt--5">
                <div class="row mt--2">

                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">${baseViewDto.currentMenu.name}</div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frmQuery" class="form-horizontal">
                                    <div class="form-group form-inline">
                                        <label for="templateName" class="col-md-1 col-form-label">名称</label>
                                        <div class="col-md-3 p-0">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="名称" id="templateName" name="templateName"
                                                   class="form-control input-full">
                                        </div>


                                        <label for="templateKey" class="col-md-1 col-form-label">模板key</label>
                                        <div class="col-md-3 p-0">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="模板key" id="templateKey" name="templateKey"
                                                   class="form-control input-full">
                                        </div>


                                        <div class="col-md-4 p-0">
                                            <button style="margin-left: 10px" class="btn btn-info  btn-sm"
                                                    type="button"
                                                    onclick="objTemplate.loadTableList()">
                                                <span class="btn-label">
                                                    <i class="fa fa-search"></i>
                                                </span>
                                                查询
                                            </button>

                                            <button style="margin-left: 5px" class="btn btn-success btn-sm"
                                                    type="button"
                                                    onclick="objTemplate.showDataModel()">
                                                <span class="btn-label">
                                                    <i class="fa fa-plus"></i>
                                                </span>
                                                新增
                                            </button>
                                        </div>
                                    </div>
                                </form>
                                <table class="table table-bordered" id="tb_FatherList">
                                    <!-- cerare document add ajax data-->
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>

</body>

<script type="text/javascript">

    var objTemplate = {};

    objTemplate.config = {table: '#tb_FatherList', box: '#divBoxFather', frmQuery: '#frmQuery'};

    objTemplate.showDataModel = function () {
        var box = $(objTemplate.config.box);
        objTemplate.initFormData(box.find("form"), {});
        box.modal("show");
    };

    objTemplate.editData = function (id) {
        var data = $(objTemplate.config.table).bootstrapTable("getRowByUniqueId", id);
        var box = $(objTemplate.config.box);
        objTemplate.initFormData(box.find("form"), data);
        box.modal("show");
    };

    objTemplate.deleteByIdsData = function (id) {
        AssessCommon.ajaxServerFun({id: id}, "/scriptTemplate/deleteScriptTemplateByIds", "post", function () {
            objTemplate.loadTableList();
        }, "delete");
    };

    objTemplate.saveData = function () {
        var box = $(objTemplate.config.box);
        var frm = box.find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        data.templateOriginalText = ue.getContent();
        var funParams = 'add';
        if (data.id) {
            funParams = 'update';
        }
        AssessCommon.ajaxServerFun({formData: JSON.stringify(data)}, "/scriptTemplate/saveAndUpdateScriptTemplate", "post", function () {
            objTemplate.loadTableList();
            box.modal("hide");
        }, funParams);
    };

    objTemplate.initFormData = function (frm, item) {
        frm.clearAll();
        frm.initForm(item);
        AssessCommon.initAreaInfo({
            provinceTarget: frm.find("select[name='province']"),
            cityTarget: frm.find("select[name='city']"),
            districtTarget: frm.find("select[name='district']"),
            provinceValue: item.province,
            cityValue: item.city,
            districtValue: item.district
        });
        AssessCommon.initArraySelect2(frm, item, ['province', 'city', 'district']);
        var content = item.templateOriginalText;
        if (!content) {
            content = "";
        }
        setTimeout(function () {
            ue.setContent(content, false);
        }, 300);
        frm.validate();
    };

    objTemplate.loadEditView = function (id) {
        var url = "${pageContext.request.contextPath}/scriptTemplate/templateEdit/"+id ;
        openWin(url, function () {
            objTemplate.loadTableList();
        })
    };

    objTemplate.loadTableList = function () {
        var data = formSerializeArray($(objTemplate.config.frmQuery));
        var arr = Object.keys(data);
        $.each(arr, function (i, item) {
            if (!data[item]) {
                data[item] = undefined;
            }
        });
        var table = $(objTemplate.config.table);
        var cols = [];
        cols.push({field: 'templateName', title: '模板名称', width: "6%"});
        cols.push({field: 'templateKey', title: '模板key', width: "7%"});
        //处理很多内容 省略
        cols.push({
            field: 'templateOriginalText',
            title: '原始模板(超过6行会省略显示)',
            width: '35%',
            formatter: function (value, row, index) {
                var html = "<div class='product-buyer-name'>" + value + "</div>";
                return html;
            }
        });
        cols.push({
            field: 'scriptTemplate', title: '脚本模板(超过6行会省略显示)', width: '35%', formatter: function (value, row, index) {
                var html = "<div class='product-buyer-name'>" + value + "</div>";
                return html;
            }
        });
        cols.push({
            field: 'id', width: '15%', title: '操作', formatter: function (value, row, index) {
                var str = '<button onclick="objTemplate.editData(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';

                str += '<button onclick="objTemplate.loadEditView(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="脚本编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';

                str += '<button onclick="objTemplate.deleteByIdsData(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
                return str;
            }
        });
        table.bootstrapTable('destroy');
        TableInit(table, "${pageContext.request.contextPath}/scriptTemplate/getBootstrapTableVo", cols, data, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

    $(function () {
        objTemplate.loadTableList();
    });

    var ue = UE.getEditor('template', {
        toolbars: [
            ['source', 'autotypeset', 'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc']
        ],
        zIndex: 11009,
        initialFrameHeight: 220,
        elementPathEnabled: false,//是否启用元素路径，默认是true显示
        wordCount: false, //是否开启字数统计
        autoHeightEnabled: false,
        autoFloatEnabled: true
    });


</script>

<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">脚本模板</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="row form-group">
                                <div class="col-md-6">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 col-form-label">
                                            模板名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input class="form-control input-full" name="templateName" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 col-form-label">
                                            模板key<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input class="form-control input-full" name="templateKey" required>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 control-label">
                                            模版<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-11">
                                            <div style="width:99%;height:200px;" id="template"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 col-form-label">
                                            脚本模板
                                        </label>
                                        <div class="col-sm-11">
                                            <textarea class="form-control input-full" name="scriptTemplate"></textarea>
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
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm"
                        onclick=" objTemplate.saveData()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>


</html>

