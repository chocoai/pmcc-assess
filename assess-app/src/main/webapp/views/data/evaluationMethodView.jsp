<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                                <button type="button" class="btn btn-primary" onclick="loadMethodList()">
                                    查询
                                </button>

                                <button type="button" class="btn btn-success" onclick="addDataDic()"
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
                <h3 class="modal-title">评估方法</h3>
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
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="name" id="name"
                                                   placeholder="请填写名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            类型<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="type"
                                                    class="form-control search-select select2 type">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            类别<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="category"
                                                    class="form-control category search-select select2">
                                                <option selected="selected" value="">请先选择类型</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            评估方法<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select id="method" name="method" class="form-control" required="">
                                                <option selected="selected" value="">请选择</option>
                                                <c:forEach items="${methodDicList}" var="item">
                                                    <option value="${item.id}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            适用原因模板<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea required="required" placeholder="请填写适用原因" class="form-control"
                                                      id="applicableReason" name="applicableReason"
                                                      onkeyup="extractApplicableField();"></textarea>
                                            <div class="applicableReason-field">

                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            不适用原因模板<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea placeholder="请填写不适用原因" class="form-control"
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
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default" onclick="removeSubDataDic()">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="saveSubDataDic()">
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
        loadMethodList();
        objMethod.event.init();
        objMethod.event.change();
    })

    //提取显示适用的字段
    function extractApplicableField() {
        var text = $("#applicableReason").val();
        $('.applicableReason-field').empty();
        var fieldArray = AssessCommon.extractField(text);
        console.log(fieldArray);
        if (fieldArray && fieldArray.length > 0) {
            var html = '';
            $.each(fieldArray, function (i, item) {
                field = fieldArray;
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
                Nofield = fieldArray;
                html += '<span class="label label-default">' + item + '</span> ';
            })
            $('.not-applicableReason-field').append(html);
        }
    }

    //加载 评估技术方法 数据列表
    function loadMethodList() {
        var cols = [];
        cols.push({field: 'name', title: '名称'});
        cols.push({field: 'methodStr', title: '评估方法'});
        cols.push({field: 'applicableReason', title: '适用原因模板', width: '40%'});
        cols.push({field: 'notApplicableReason', title: '不适用原因模板', width: '40%'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="editMethod(' + index + ')"><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_List").bootstrapTable('destroy');
        var methodStrChange = $("#queryName").val();
        TableInit("tb_List", "${pageContext.request.contextPath}/evaluationMethod/list", cols, {
            methodStr: methodStrChange
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
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/evaluationMethod/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        loadMethodList();//重载 (刷新)
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

    //对新增 评估技术方法 数据处理
    function addDataDic() {
        $("#frm").clearAll();
        extractApplicableField();
        extractNotApplicableField();
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
        data.method = $("#method option:selected").val();
        data.applicableReason = $("#applicableReason").val();
        data.notApplicableReason = $("#notApplicableReason").val();
        if ($("#frm").valid()) {
            $.ajax({
                url: "${pageContext.request.contextPath}/evaluationMethod/save",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        loadMethodList();
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

    //评估技术方法 修改
    function editMethod(index) {
        var row = $("#tb_List").bootstrapTable('getData')[index];
        $("#frm").clearAll();
        $("#frm").initForm(row);
        objMethod.event.init();
        extractApplicableField();
        extractNotApplicableField();
        $('#divBox').modal();
    }



    /**
     * @author:  zch
     * 描述:加载一些select2数据 (类型 类别)
     * @date:2018-08-30
     **/
    var objMethod = new Object();
    objMethod.flag = true;
    objMethod.isEmpty = function (data) {
        if (data) {
            return true;
        }
        return false;
    }
    objMethod.writeSelect = function (frm, data, name) {
        if (objMethod.isEmpty(data)) {
            $("#" + frm + " ." + name).val(data).trigger("change");
        } else {
            $("#" + frm + " ." + name).val(null).trigger("change");
        }
    }
    objMethod.event = {
        init:function () {
            if (objMethod.flag){
                objMethod.event.type();
                objMethod.flag = false;
            }
        },
        //类型
        type:function () {
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
                            $("#frm" + " .type").html(option);
                            $("#frm" + " .type").select2();
                            $("#frm" + " .category").select2();
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
        },
        change:function () {
            objMethod.event.category();
        },
        //类别
        category:function () {
            //监听change 事件 并做出......
            $("#frm" + " .type").change(function () {
                var pid = $("#frm" + " .type").eq(1).val();
                if (!objMethod.isEmpty(pid)) {
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
                                if ($("#frm" + " .category").prev(".category").size() > 0) {
                                    $("#frm" + " .category").prev(".category").remove();
                                }
                                $("#frm" + " .category").empty();
                                $("#frm" + " .category").html(option);
                                $("#frm" + " .category").select2();
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
    }



</script>


</html>
