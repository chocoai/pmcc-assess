<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                                                onclick="loadMethodList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                        <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                                data-toggle="modal" onclick="addDataDic()" href="#divBox">
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
                <h4 class="modal-title">评估方法</h4>
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
                                            <div class="col-sm-3">
                                                <button  type="button" class="btn btn-success btn-sm"  onclick="appendHTML('',this);">
											            <span class="btn-label"><i class="fa fa-plus"></i></span>添加类型
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="system">

                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                评估方法<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select id="method" name="method" class="form-control input-full"
                                                        required="">
                                                    <option selected="selected" value="">请选择</option>
                                                    <c:forEach items="${methodDicList}" var="item">
                                                        <option value="${item.id}">${item.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                适用原因模板<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                              <textarea required="required" placeholder="请填写适用原因"
                                                        class="form-control input-full"
                                                        id="applicableReason" name="applicableReason"
                                                        onkeyup="extractApplicableField();"></textarea>
                                                <div class="applicableReason-field">

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                不适用原因模板<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                              <textarea placeholder="请填写不适用原因" class="form-control input-full"
                                                        id="notApplicableReason" name="notApplicableReason"
                                                        required="required"
                                                        onkeyup="extractNotApplicableField();"></textarea>
                                                <div class="not-applicableReason-field">
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
                <button type="button" class="btn btn-primary btn-sm" onclick="saveSubDataDic()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>


<script type="application/javascript">
    $(function () {
        loadMethodList();
    })

    //提取显示适用的字段
    function extractApplicableField() {
        var text = $("#applicableReason").val();
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

    //提取显示不适用的字段
    function extractNotApplicableField() {
        var text = $("#notApplicableReason").val();
        $('.not-applicableReason-field').empty();
        var fieldArray = AssessCommon.extractField(text);
        if (fieldArray && fieldArray.length > 0) {
            var html = '';
            $.each(fieldArray, function (i, item) {
                html += '<span class="label label-default">' + item + '</span> ';
            })
            $('.not-applicableReason-field').append(html);
        }
    }

    //加载 评估技术方法 数据列表
    function loadMethodList() {
        var cols = [];
        cols.push({field: 'name', width: '10%', title: '名称'});
        cols.push({field: 'typeName', width: '10%', title: '类型'});
        cols.push({field: 'methodStr', width: '10%', title: '评估方法'});
        cols.push({field: 'applicableReason', title: '适用原因模板', width: '30%'});
        cols.push({field: 'notApplicableReason', title: '不适用原因模板', width: '30%'});
        cols.push({
            field: 'id', width: '10%', title: '操作', formatter: function (value, row, index) {
                var str = '<button onclick="editMethod(' + index + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button onclick="removeData(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
                return str;
            }
        });
        $("#tb_List").bootstrapTable('destroy');
        var methodStrChange = $("#queryName").val();
        TableInit("tb_List", "${pageContext.request.contextPath}/evaluationMethod/list", cols, {
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

    //删除 评估技术方法 数据()
    function removeData(id) {
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/evaluationMethod/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        notifySuccess("成功", "删除数据成功");
                        loadMethodList();//重载 (刷新)
                    } else {
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

    //对新增 评估技术方法 数据处理
    function addDataDic() {
        $("#frm").clearAll();
        extractApplicableField();
        extractNotApplicableField();
        reload();
    }

    //取消 新增 评估技术方法
    function removeSubDataDic() {
        $("#divBox").hide();
    }

    //新增 评估技术方法 数据
    function saveSubDataDic() {
        var flag = false;
        var data = formParams("frm");
        data.id = $("#id").val();
        data.name = $("#name").val();
        data.applicableReason = $("#applicableReason").val();
        data.notApplicableReason = $("#notApplicableReason").val();
        data.method = $("#method option:selected").val();
        data.type = ',' + data.type + ',';//方便like查询
        data.category = ',' + data.category + ',';//方便like查询
        if ($("#frm").valid()) {
            $.ajax({
                url: "${pageContext.request.contextPath}/evaluationMethod/save",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        loadMethodList();
                        $('#divBox').modal('hide');
                    } else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        }
    }

    //评估技术方法 修改
    function editMethod(index) {
        var row = $("#tb_List").bootstrapTable('getData')[index];
        var id = row.id;
        $.ajax({
            url: "${pageContext.request.contextPath}/evaluationMethod/get",
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
                    extractApplicableField();
                    extractNotApplicableField();
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
                } else {
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
                    } else {
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
        html += '<div class="col-sm-1">';
        html += "<button type='button' class='btn btn-warning btn-sm' onclick='cleanHTMLData(this)'><i class=\"fa fa-minus\"></i></button>";
        html += "</div>";

        html += "</div>";
        html += "</div>";
        html += "</div>";
        return html;
    }

    function cleanHTMLData(this_) {
        $(this_).closest('.form-group').remove();
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

        html += '<div class="col-sm-1">';
        html += "<button type='button' class='btn btn-warning btn-sm' type='button' onclick='cleanHTMLData(this)'><i class=\"fa fa-minus\"></i></button>";
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
