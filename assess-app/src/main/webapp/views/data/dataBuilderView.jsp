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
                                    建造商
                                </label>
                                <div class="col-sm-2">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="建造商 名称" id="queryName" name="queryName"
                                           class="form-control">
                                </div>
                            </div>

                            <div class="col-sm-3">
                                <button type="button" class="btn btn-primary"
                                        onclick="dataBuilder.prototype.loadDataDicList()">
                                    查询
                                </button>

                                <button type="button" class="btn btn-success"
                                        onclick="dataBuilder.prototype.showModel()"
                                        data-toggle="modal" href="#divBox"> 新增
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
    <!-- end: MAIN CONTAINER -->
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    $(function () {
        dataBuilder.prototype.loadDataDicList();
        dataBuilder.prototype.init();
    });
    var dataBuilder = function () {

    };
    dataBuilder.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_FatherList";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            return data;
        },
        loadDataDicList: function () {
            var cols = [];
            cols.push({field: 'name', title: '名称'});
            cols.push({field: 'socialPrestige', title: '社会信誉'});
            cols.push({field: 'companyNature', title: '公司性质'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="dataBuilder.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="dataBuilder.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + dataBuilder.prototype.config().table).bootstrapTable('destroy');
            TableInit(dataBuilder.prototype.config().table, "${pageContext.request.contextPath}/dataBuilder/getDataBuilderList", cols, {
                name: $("#queryName").val()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            Alert("确认删除!",2,null,function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/dataBuilder/deleteDataBuilderById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            dataBuilder.prototype.loadDataDicList();
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
            $("#qualificationGrade").val(null).trigger("change");
            $("#qualificationGradeV").val(null).trigger("change");
            $("#" + dataBuilder.prototype.config().frm).clearAll();
            $('#' + dataBuilder.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + dataBuilder.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(dataBuilder.prototype.config().frm);
            var size = $("#qualificationGrade option").size();
            if (size == 0) {

            }
            if (size >= 1) {
                data.qualificationGrade = $("#qualificationGrade").val();
            }
            $.ajax({
                url: "${pageContext.request.contextPath}/dataBuilder/saveAndUpdateDataBuilder",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#' + dataBuilder.prototype.config().box).modal('hide');
                        dataBuilder.prototype.loadDataDicList();
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
                url: "${pageContext.request.contextPath}/dataBuilder/getDataBuilderById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + dataBuilder.prototype.config().frm).clearAll();
                        $("#" + dataBuilder.prototype.config().frm).initForm(result.data);
                        $("#qualificationGradeV").select2();
                        var size = $("#qualificationGrade option").size();
                        if (size == 0) {
                            $("#qualificationGradeV").val(result.data.qualificationGrade).trigger("change");
                        }
                        if (size >= 1) {
                            $("#qualificationGrade").val(result.data.qualificationGrade).trigger("change");
                        }
                        $('#' + dataBuilder.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function () {
            $("#qualificationGradeV").change(function () {
                var id = $("#qualificationGradeV").val();
                $.ajax({
                    url: "${pageContext.request.contextPath}/dataBuilder/getBasisList",
                    dataType: "JSON",
                    data: {'id': id},
                    type: "GET",
                    success: function (result) {
                        if (result.ret && result.data) {
                            var data = result.data;
                            var gradeNum = data.length;
                            var option = "<option value=''>请选择</option>";
                            if (gradeNum > 0) {
                                for (var i = 0; i < gradeNum; i++) {
                                    option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                }
                                $("#qualificationGrade").parent().parent().parent().show();
                                $("#qualificationGrade").html(option);
                            } else {
                                $("#qualificationGrade").empty();
                                $("#qualificationGrade").parent().parent().parent().hide();
                            }

                        }
                    },
                    error: function (e) {
                        Alert("调用服务端方法失败，失败原因:" + e);
                    }
                });
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
                <h3 class="modal-title">建造商</h3>
            </div>
            <form id="frmFather" class="form-horizontal">
                <input type="hidden" id="id" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            名称
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="name"
                                                   placeholder="名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            资质等级
                                        </label>
                                        <div class="col-sm-10">
                                            <select id="qualificationGradeV" required="required"
                                                    name="qualificationGrade"
                                                    class="form-control search-select select2">
                                                <c:forEach items="${baseList}" var="item">
                                                    <option value="${item.id}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            公司性质
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="companyNature"
                                                   placeholder="公司性质" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            社会信誉
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea class="form-control" name="socialPrestige" placeholder="社会信誉"
                                                      required="required">

                                            </textarea>
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
                    <button type="button" class="btn btn-primary" onclick="dataBuilder.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>
