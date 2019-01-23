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
                                <button type="button" class="btn btn-primary" onclick="loadPrincipleList()">
                                    查询
                                </button>

                                <button type="button" class="btn btn-success" onclick="addPrinciple()"
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
                <h3 class="modal-title">评估原则</h3>
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
                                            <input type="text" class="form-control" name="name" id="name" placeholder="名称"
                                                   required="required">
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
                                        <div class="col-sm-10 col-sm-offset-2">
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
                                                项目类型<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-3">
                                                <select required name="type" onchange="typeChange(this);" id="type0" class="form-control type0">
                                                </select>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                项目类别<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-3">
                                                <select required name="category" class="form-control category0">
                                                    <option selected="selected" value="">请先选择类型</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group project-type">

                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            委托目的<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10" id="entrustmentPurpose">
                                            <c:forEach items="${purposeDicList}" var="item">
                                                <span class="checkbox-inline">
                                                <input type="checkbox" required id="entrustmentPurpose${item.id}" name="entrustmentPurpose" value="${item.id}"
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
                                                <input type="checkbox" id="bisModifiable" name="bisModifiable" value="true"
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
                                            <textarea placeholder="请填写模板" class="form-control" id="template"
                                                      name="template" required="required"
                                                      onkeyup="extractTemplateField()">

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
                    <button type="button" class="btn btn-primary" onclick="savePrinciple()">
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
        loadPrincipleList();

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
    //加载 评估原则 数据列表
    function loadPrincipleList() {
        var cols = [];
        cols.push({field: 'name', title: '名称', formatter: function (value, row, index) {
            return '<span title="'+row.template+'">'+value+'</span>';
        }});
        cols.push({field: 'typeName', title: '项目类型'});
        cols.push({field: 'entrustmentPurposeStr', title: '委托目的'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="editPrinciple(' + index + ')"><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="removePrinciple(' + row.id + ')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_List").bootstrapTable('destroy');
        var name = $("#queryName").val();
        TableInit("tb_List", "${pageContext.request.contextPath}/evaluationPrinciple/list", cols, {
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

    //删除 评估原则 数据()
    function removePrinciple(id) {
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/evaluationPrinciple/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        loadPrincipleList();//重载 (刷新)
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


    //对新增 评估原则 数据处理
    function addPrinciple() {
        $("#frm").clearAll();
        extractTemplateField();
        $("#frm").find(".project-type").empty();
        num = 0;
        getType(0);
    }
    //新增 评估原则 数据
    function savePrinciple() {
        var data = formParams("frm");
        data.entrustmentPurpose = ',' + data.entrustmentPurpose + ',';//方便like查询
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
        if ($("#frm").valid()) {
            $.ajax({
                url: "${pageContext.request.contextPath}/evaluationPrinciple/save",
                type: "post",
                dataType: "json",
                data: {
                    formData: JSON.stringify(data)
                },
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        loadPrincipleList();
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
    //评估原则 修改
    function editPrinciple(index) {
        var row = $("#tb_List").bootstrapTable('getData')[index];
        $("#frm").clearAll().initForm(row);
        num = 0;
        $("#frm").find(".project-type").empty();
        AssessCommon.checkboxToChecked($("#frm").find(":checkbox[name='entrustmentPurpose']"),row.entrustmentPurpose.split(','));
        extractTemplateField();
        $('#divBox').modal();
    }

    var num = 0;

    //类型
    function getType(number) {
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

    function appendHTML() {
        num++;
        var projectType = "type" + num;
        var projectCategory = "category" + num;
        var html = createHTML(projectType, projectCategory);
        $("#frm").find(".project-type").append(html);
        getType();
    }

    function createHTML(projectType, projectCategory) {
        var html = "<div class='form-group' style='margin-top:8px;'>";
        html += "<label class='col-md-2 col-sm-2  control-label'>" + '项目类型' + "<span class='symbol required'></span></label>";
        html += "<div class='x-valid'><div class='col-sm-3'>";
        html += "<select required  name='" + projectType + "' id='" + projectType + "' onchange='typeChange(this)' class='form-control search-select select2 " + projectType + "'>";
        html += "<option selected='selected' value=''>" + '请选择' + "</option>";
        html += "</select>";
        html += "</div></div>";

        html += "<label class='col-md-2 col-sm-2  control-label'>" + '项目类别' + "<span class='symbol required'></span></label>";
        html += "<div class='x-valid'><div class='col-sm-3'>";
        html += "<select required  name='" + projectCategory + "' id='" + projectCategory + "'  class='form-control " + projectCategory + "'>";
        html += "<option selected='selected' value=''>" + '请先选择类型' + "</option>";
        html += "</select>";
        html += "</div></div>";

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
        var pid = $(this_).val();
        if(!pid){
            $(this_).closest('.form-group').find('[name^=category]').empty();
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
                        $(this_).closest('.form-group').find('[name^=category]').html(option);

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
    }

</script>


</html>
