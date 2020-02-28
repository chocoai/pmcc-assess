<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
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
                                        <label for="queryName" class="col-md-1 col-form-label">名称</label>
                                        <div class="col-md-3 p-0">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="名称" id="queryName" name="queryName"
                                                   class="form-control input-full">
                                        </div>

                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="loadThinkingList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                        <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                                data-toggle="modal" onclick="addThinking()" href="#divBox">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                            新增
                                        </button>
                                    </div>


                                </form>
                                <table class="table table-bordered" id="tb_List">
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
<div id="divBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">评估思路</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frm" class="form-horizontal">
                    <input type="hidden" id="id" name="id" value="0">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" required data-rule-maxlength="50" placeholder="名称"
                                                       id="name" name="name" class="form-control input-full">
                                            </div>

                                            <div class="col-sm-4">
                                                <div class="form-check" style="justify-content:left">
                                                    <label class="form-check-label">
                                                        <input class="form-check-input" type="checkbox" id="bisEnable"
                                                               name="bisEnable" value="true"
                                                               checked="checked">
                                                        <span class="form-check-sign">是否启用</span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                项目类型类别
                                            </label>

                                            <div class="btn btn-xs btn-success btn-sm"
                                                 onclick="appendHTML('',this)"><i
                                                    class="fa fa-plus"></i></div>

                                        </div>
                                    </div>
                                </div>
                                <div class="system">
                                    <div class="row form-group">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                项目类型
                                            </label>
                                            <div class="col-sm-3">
                                                <select name="type" onchange="typeChange(this);" id="type0"
                                                        class="form-control input-full search-select select2 type0">
                                                </select>
                                            </div>


                                            <label class="col-sm-2 control-label">
                                                项目类别
                                            </label>

                                            <div class="col-sm-3">
                                                <select name="category"
                                                        class="form-control input-full search-select select2 category0">
                                                    <option selected="selected" value="">请先选择类型</option>
                                                </select>

                                            </div>
                                            <div class="col-sm-2">
                                                <input type="button" class="btn btn-warning" value="X"
                                                       onclick="cleanHTMLData(this)">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <c:forEach items="${methodDicList}" var="item">
                                        <div class="form-check">
                                            <label class="form-check-label" style="margin-left: 2px;">
                                                <input type="checkbox" id="method${item.id}"
                                                       required
                                                       name="method" value="${item.id}"
                                                       class="form-check-input">
                                                <span class="form-check-sign"><label
                                                        for="method${item.id}">${item.name}</label></span>
                                            </label>
                                        </div>
                                    </c:forEach>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                模板内容<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                              <textarea placeholder="模板内容" class="form-control input-full"
                                                        id="templateContent"
                                                        name="templateContent" required="required"
                                                        onkeyup="extractTemplateContentField();">
                                            </textarea>
                                                <div class="applicableReason-field">

                                                </div>
                                            </div>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="saveThinking()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<script type="application/javascript">
    $(function () {
        loadThinkingList();
    })

    //提取模板内容的字段
    function extractTemplateContentField() {
        var text = $("#templateContent").val();
        $('.applicableReason-field').empty();
        var fieldArray = AssessCommon.extractField(text);
        if (fieldArray && fieldArray.length > 0) {
            var html = '';
            $.each(fieldArray, function (i, item) {
                html += '<span class="label label-default">' + item + '</span> ';
            })
            $('.applicableReason-field').append(html);
        }
    }

    //加载 评估技术思路 数据列表
    function loadThinkingList() {
        var cols = [];
        cols.push({field: 'name', width: '5%', title: '名称'});
        cols.push({field: 'methodStr', width: '5%', title: '评估方法'});
        cols.push({field: 'typeName', width: '5%', title: '项目类型'});
        cols.push({field: 'templateContent', title: '模板内容', width: '40%'});
        cols.push({
            field: 'id', title: '操作', width: '5%', formatter: function (value, row, index) {
                var str = '<button onclick="editThinking(' + index + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button onclick="removeThinking(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
                return str;
            }
        });
        $("#tb_List").bootstrapTable('destroy');
        var methodStrChange = $("#queryName").val();
        TableInit("tb_List", "${pageContext.request.contextPath}/evaluationThinking/list", cols, {
            name: methodStrChange
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }

    //删除 评估技术思路 数据()
    function removeThinking(id) {
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/evaluationThinking/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        notifySuccess("成功", "删除数据成功");
                        loadThinkingList();//重载 (刷新)
                    }
                    else {
                        AlertError("删除数据失败", result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        })
    }

    //对新增 评估技术思路 数据处理
    function addThinking() {
        $("#frm").clearAll();
        extractTemplateContentField();
        reload();
    }

    //新增 评估技术思路 数据
    function saveThinking() {
        var data = formParams("frm");
        data.method = ',' + data.method + ',';//方便like查询
        data.type = ',' + data.type + ',';//方便like查询
        data.category = ',' + data.category + ',';//方便like查询
        if ($("#frm").valid()) {
            $.ajax({
                url: "${pageContext.request.contextPath}/evaluationThinking/save",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        loadThinkingList();
                        $('#divBox').modal('hide');
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        }
    }

    //评估技术思路 修改
    function editThinking(index) {
        var row = $("#tb_List").bootstrapTable('getData')[index];
        var id = row.id;
        $.ajax({
            url: "${pageContext.request.contextPath}/evaluationThinking/get",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    $("#frm").clearAll();
                    $("#frm").clearAll().initForm(result.data);
                    var type = result.data.type;
                    var types = type.substring(1, type.length - 1).split(',');
                    var category = result.data.category;
                    var categorys = category.substring(1, category.length - 1).split(',');
                    reload(types[0], categorys[0]);
                    for (var i = 0; i < types.length - 1; i++) {
                        appendHTML(types[i + 1], categorys[i + 1]);
                    }
                    AssessCommon.checkboxToChecked($("#frm").find(":checkbox[name='method']"), row.method.split(','));
                    extractTemplateContentField();
                    $('#divBox').modal();
                }
            },
            error: function (result) {
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    var num = 0;

    //类型
    function getType(number, typeValue) {
        if (!number && number != 0) {
            number = num
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/baseProjectClassify/getProjectClassifyListByFieldName",
            type: "post",
            dataType: "json",
            data: {fieldName: "single"},//字段为固定 请参照BaseProjectClassifyController中....
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    if (data.length >= 1) {
                        var option = "<option value=''>请选择</option>";
                        for (var i = 0; i < data.length; i++) {
                            option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                        }
                        $("#frm").find('select.type' + number).html(option);
                        if (typeValue) {
                            $("#frm").find('select.type' + number).val([typeValue]).trigger('change');
                        }
                    }
                }
                else {
                    notifyWarning("获取类型失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                notifyWarning("调用服务端方法失败，失败原因:" + result);
            }
        });
    }

    //类别
    function getCategory(number, categoryValue) {
        if (!number && number != 0) {
            number = num;
        }
        //监听change 事件 并做出......
        $("#frm" + " .type" + number).change(function () {
            var pid = $("#frm" + " .type" + number).eq(1).val();
            if (!pid) {
                return false;
            }
            $.ajax({
                url: "${pageContext.request.contextPath}/baseProjectClassify/getCacheProjectClassifyListByPid",
                type: "post",
                dataType: "json",
                data: {pid: pid},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (data.length >= 1) {
                            var option = "<option value=''>请选择</option>";
                            for (var i = 0; i < data.length; i++) {
                                option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                            }
                            $("#frm").find('select.category' + number).html(option);
                            if (categoryValue) {
                                $("#frm").find('select.category' + number).val([categoryValue]).trigger('change');
                            }
                        }
                    }
                    else {
                        notifyWarning("获取类别失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    notifyWarning("调用服务端方法失败，失败原因:" + result);
                }
            })
        });
    }

    function appendHTML(typeValue, categoryValue) {
        num++;
        var projectType = "type" + num;
        var projectCategory = "category" + num;
        var html = createHTML(projectType, projectCategory);
        $("#frm").find(".system").append(html);
        $("#frm").find("." + projectType).select2();
        $("#frm").find("." + projectCategory).select2();
        getType(null, typeValue);
        getCategory(null, categoryValue);
    }

    function createHTML(projectType, projectCategory) {
        var html = '<div class="row form-group">';
        html += '<div class="col-md-12">';
        html += '<div class="form-inline x-valid">';


        html += '<label class="col-sm-2 col-form-label">' + '项目类型' + '</label>';
        html += '<div class="col-sm-3 ">';
        html += "<select  name='type' id='" + projectType + "' onchange='typeChange(this)' class='form-control input-full search-select select2 " + projectType + "'>";
        html += "<option selected='selected' value=''>" + '请选择' + "</option>";
        html += "</select>";
        html += "</div>";


        html += '<label class="col-sm-2 col-form-label">' + '项目类别' + '</label>';
        html += '<div class="col-sm-3 ">';
        html += "<select  name='category' id='" + projectCategory + "'  class='form-control input-full search-select select2 " + projectCategory + "'>";
        html += "<option selected='selected' value=''>" + '请先选择类型' + "</option>";
        html += "</select>";
        html += "</div>";


        html += '<label class="col-sm-1 col-form-label">' + '取消' + '</label>';
        html += '<div class="col-sm-1">';
        html += "<input type='button' class='btn btn-warning btn-sm' type='button' value='X' onclick='cleanHTMLData(this)'>";
        html += "</div>";


        html += "</div>";
        html += "</div>";
        html += "</div>";
        return html;
    }

    function cleanHTMLData(this_) {
        $(this_).parent().parent().remove();
    }

    function typeChange(this_) {
        var str = $(this_).attr("id");
        var number = str.substr(str.length - 1, 1);
        getCategory(number);
    }

    function reload(typeValue, categoryValue) {
        $("#frm").find(".system").empty();
        var html = '<div class="row form-group">';
        html += '<div class="col-md-12">';
        html += '<div class="form-inline x-valid">';

        html += '<label class="col-sm-2 col-form-label">' + '项目类型' + '</label>';
        html += '<div class="col-sm-3">';
        html += "<select  name='type' id='type0' onchange='typeChange(this)' class='form-control input-full search-select select2 type0'>";
        html += "<option selected='selected' value=''>" + '请选择' + "</option>";
        html += "</select>";
        html += "</div>";

        html += '<label class="col-sm-2 col-form-label">' + '项目类别' + '</label>';
        html += '<div class="col-sm-3">';
        html += "<select  name='category' class='form-control input-full search-select select2 category0'>";
        html += "<option selected='selected' value=''>" + '请先选择类型' + "</option>";
        html += "</select>";
        html += "</div>";


        html += '<label class="col-sm-1 col-form-label">' + '取消' + '</label>';
        html += '<div class="col-sm-1">';
        html += "<input type='button' class='btn btn-warning btn-sm' type='button' value='X' onclick='cleanHTMLData(this)'>";
        html += "</div>";

        html += "</div>";
        html += "</div>";
        html += "</div>";
        $("#frm").find(".system").append(html);
        getType(0, typeValue);
        getCategory(0, categoryValue);
        $("#frm").find(".type0").select2();
        $("#frm").find(".category0").select2();

    }

</script>


</html>
