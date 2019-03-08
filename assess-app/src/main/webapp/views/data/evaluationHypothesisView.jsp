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
                    <form id="frmQuery" class="form-horizontal">
                        <div class="form-group ">
                            <div>
                                <label class="col-sm-1 control-label">
                                    名称
                                </label>
                                <div class="col-sm-2">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="名称" id="queryName" name="queryName"
                                           class="form-control">
                                </div>
                            </div>

                            <div class="col-sm-3">
                                <button type="button" class="btn btn-primary" onclick="loadHypothesisList()">
                                    查询
                                </button>

                                <button type="button" class="btn btn-success" onclick="addHypothesis()"
                                        data-toggle="modal" href="#divBox"> 新增
                                </button>
                            </div>
                        </div>

                    </form>
                    <table class="table table-bordered" id="tb_List">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<div id="divBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">评估假设</h3>
            </div>
            <form id="frm" class="form-horizontal">
                <input type="hidden" id="id" name="id" value="0">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="name" id="name" class="form-control"
                                                   placeholder="名称" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            是否启用
                                        </label>
                                        <div class="col-sm-4">
                                            <label class="radio-inline">
                                                <input type="checkbox" id="bisEnable" name="bisEnable" value="true"
                                                       checked="checked">
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            项目类型类别<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <div class="btn btn-xs btn-success"
                                                 onclick="appendHTML('',this)"><i
                                                    class="fa fa-plus"></i></div>
                                        </div>
                                    </div>
                                </div>
                                <div style="margin-bottom: 8px;" class="system">
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-2 control-label">
                                                项目类型
                                            </label>
                                            <div class="col-sm-3">
                                                <select name="type" onchange="typeChange(this);" id="type0"
                                                        class="form-control search-select select2 type0">
                                                </select>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                项目类别
                                            </label>
                                            <div class="col-sm-3">
                                                <select name="category"
                                                        class="form-control search-select select2 category0">
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
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            委托目的<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10" id="entrustmentPurpose">
                                            <c:forEach items="${purposeDicList}" var="item">
                                                <span class="checkbox-inline">
                                                <input type="checkbox" id="entrustmentPurpose${item.id}" required
                                                       name="entrustmentPurpose" value="${item.id}"
                                                       class="form-inline">
                                                <label for="entrustmentPurpose${item.id}">${item.name}</label>
                                                </span>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            发布日期
                                        </label>
                                        <div class="col-sm-5">
                                            <input type="text" data-date-format="yyyy-mm-dd"
                                                   placeholder="发布日期" name="pubDate"
                                                   class="form-control date-picker dbdate">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            是否修改
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="radio-inline">
                                                <input type="checkbox" id="bisModifiable" name="bisModifiable"
                                                       value="true"
                                                       checked="checked">
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            模板<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea required placeholder="请填写模板" class="form-control"
                                                      id="template" name="template" onkeyup="extractTemplateField()">

                                            </textarea>
                                            <div class="template-field">

                                            </div>
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
                    <button type="button" class="btn btn-primary" onclick="saveHypothesis()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    $(function () {
        loadHypothesisList();

    })

    //提取字段
    function extractTemplateField() {
        var text = $("#template").val();
        $('.template-field').empty();
        var fieldArray = AssessCommon.extractField(text);
        if (fieldArray && fieldArray.length > 0) {
            var html = '';
            $.each(fieldArray, function (i, item) {
                field = fieldArray;
                html += '<span class="label label-default">' + item + '</span> ';
            })
            $('.template-field').append(html);
        }
    }

    //加载 评估假设 数据列表
    function loadHypothesisList() {
        var cols = [];
        cols.push({
            field: 'name', title: '名称', formatter: function (value, row, index) {
                return '<span title="' + row.template + '">' + value + '</span>';
            }
        });
        cols.push({field: 'typeName', title: '项目类型'});
        cols.push({field: 'entrustmentPurposeStr', title: '委托目的'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="editHypothesis(' + index + ')"><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="removeHypothesis(' + row.id + ')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_List").bootstrapTable('destroy');
        var name = $("#queryName").val();
        TableInit("tb_List", "${pageContext.request.contextPath}/evaluationHypothesis/list", cols, {
            name: name
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }

    //删除 评估假设 数据()
    function removeHypothesis(id) {
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/evaluationHypothesis/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        loadHypothesisList();//重载 (刷新)
                    }
                    else {
                        Alert("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        })
    }

    //对新增 评估假设 数据处理
    function addHypothesis() {
        $("#frm").clearAll();
        extractTemplateField();
        reload();
    }

    //新增 评估假设 数据
    function saveHypothesis() {
        var data = formParams("frm");
        var type = ',';
        $("#frm").find('[name^=type]').each(function () {
            type += $(this).val() + ',';
        })
        var category = ',';
        $("#frm").find('[name^=category]').each(function () {
            category += $(this).val() + ',';
        })
        data.type = type;//方便like查询
        data.category = category;//方便like查询
        data.entrustmentPurpose = ',' + data.entrustmentPurpose + ',';//方便like查询
        if ($("#frm").valid()) {
            $.ajax({
                url: "${pageContext.request.contextPath}/evaluationHypothesis/save",
                type: "post",
                dataType: "json",
                data: {
                    formData: JSON.stringify(data)
                },
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        loadHypothesisList();
                        $('#divBox').modal('hide');
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        }
    }

    //评估假设 修改
    function editHypothesis(index) {
        var row = $("#tb_List").bootstrapTable('getData')[index];
        var id = row.id;
        $.ajax({
            url: "${pageContext.request.contextPath}/evaluationHypothesis/get",
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
                    AssessCommon.checkboxToChecked($("#frm").find(":checkbox[name='entrustmentPurpose']"), row.entrustmentPurpose.split(','));
                    extractTemplateField();
                    $('#divBox').modal();
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
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
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
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
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
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
        var html = "<div class='form-group' style='margin-top:8px;'>";
        html += "<label class='col-md-2 col-sm-2  control-label'>" + '项目类型' + "</label>";
        html += "<div class='col-sm-3'>";
        html += "<select  name='type' id='" + projectType + "' onchange='typeChange(this)' class='form-control search-select select2 " + projectType + "'>";
        html += "<option selected='selected' value=''>" + '请选择' + "</option>";
        html += "</select>";
        html += "</div>";

        html += "<label class='col-md-2 col-sm-2  control-label'>" + '项目类别' + "</label>";
        html += "<div class='col-sm-3'>";
        html += "<select  name='category' id='" + projectCategory + "'  class='form-control search-select select2 " + projectCategory + "'>";
        html += "<option selected='selected' value=''>" + '请先选择类型' + "</option>";
        html += "</select>";
        html += "</div>";

        html += "<div class='col-sm-2'>";
        html += "<input class='btn btn-warning' type='button' value='X' onclick='cleanHTMLData(this)'>";
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
        var html = "<div class='form-group' style='margin-top:8px;'>";
        html += "<label class='col-md-2 col-sm-2  control-label'>" + '项目类型' + "</label>";
        html += "<div class='col-sm-3'>";
        html += "<select  name='type' id='type0' onchange='typeChange(this)' class='form-control search-select select2 type0'>";
        html += "<option selected='selected' value=''>" + '请选择' + "</option>";
        html += "</select>";
        html += "</div>";

        html += "<label class='col-md-2 col-sm-2  control-label'>" + '项目类别' + "</label>";
        html += "<div class='col-sm-3'>";
        html += "<select  name='category' class='form-control search-select select2 category0'>";
        html += "<option selected='selected' value=''>" + '请先选择类型' + "</option>";
        html += "</select>";
        html += "</div>";

        html += "<div class='col-sm-2'>";
        html += "<input class='btn btn-warning' type='button' value='X' onclick='cleanHTMLData(this)'>";
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
