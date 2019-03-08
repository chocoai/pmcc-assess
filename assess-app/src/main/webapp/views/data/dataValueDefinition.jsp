<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/7/19
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
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
                        <%--<div class="form-group ">
                            <div>
                                <label class="col-sm-1 control-label">
                                    方法
                                </label>
                                <div class="col-sm-3">
                                    <select class="form-control" id="queryType">
                                        <option value="">--请选择--</option>
                                        <c:if test="${not empty methods}">
                                            <c:forEach items="${methods}" var="item">
                                                <option value="${item.id}">${item.name}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>

                            <div class="col-sm-3">
                                <button type="button" class="btn btn-primary"
                                        onclick="dataValueDefinition.prototype.loadDataDicList()">
                                    查询
                                </button>--%>

                                <button type="button" class="btn btn-success"
                                        onclick="dataValueDefinition.prototype.showModel()"
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
        dataValueDefinition.prototype.loadDataDicList();
    });
    var dataValueDefinition = function () {

    };
    dataValueDefinition.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_FatherList";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            return data;
        },
        loadDataDicList: function () {
            var cols = [];
            cols.push({field: 'entrustmentPurposeName', title: '委托目的'});
            cols.push({field: 'valueTypeName', title: '价值类型'});
            cols.push({field: 'propertyScopeName', title: '评估财产范围'});
            cols.push({field: 'scopeInclude', title: '范围包括'});
            cols.push({field: 'scopeNotInclude', title: '范围不包括'});
            cols.push({field: 'template', title: '模板'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="dataValueDefinition.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="dataValueDefinition.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + dataValueDefinition.prototype.config().table).bootstrapTable('destroy');
            TableInit(dataValueDefinition.prototype.config().table, "${pageContext.request.contextPath}/dataValueDefinition/getDataValueDefinitionList", cols, {
                type: $("#queryType").val()
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
            Alert("确认删除!", 2, null, function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/dataValueDefinition/deleteDataValueDefinitionById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            dataValueDefinition.prototype.loadDataDicList();
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
            $("#" + dataValueDefinition.prototype.config().frm).clearAll();
            $('#' + dataValueDefinition.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + dataValueDefinition.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(dataValueDefinition.prototype.config().frm);
            data.entrustmentPurpose = ',' + data.entrustmentPurpose + ',';
            data.valueType = ',' + data.valueType + ',';
            $.ajax({
                url: "${pageContext.request.contextPath}/dataValueDefinition/saveAndUpdateDataValueDefinition",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#' + dataValueDefinition.prototype.config().box).modal('hide');
                        dataValueDefinition.prototype.loadDataDicList();
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
                url: "${pageContext.request.contextPath}/dataValueDefinition/getDataValueDefinitionById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + dataValueDefinition.prototype.config().frm).clearAll();
                        $("#" + dataValueDefinition.prototype.config().frm).initForm(result.data);
                        AssessCommon.checkboxToChecked($("#frmFather").find(":checkbox[name='entrustmentPurpose']"), result.data.entrustmentPurpose.split(','));
                        AssessCommon.checkboxToChecked($("#frmFather").find(":checkbox[name='valueType']"), result.data.valueType.split(','));
                        $('#' + dataValueDefinition.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
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
                <h3 class="modal-title">价值定义配置</h3>
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
                                            委托目的<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
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
                                            价值类型<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <c:forEach items="${valueTypeList}" var="item">
                                                <span class="checkbox-inline">
                                                <input type="checkbox" id="valueType${item.id}" required
                                                       name="valueType" value="${item.id}"
                                                       class="form-inline">
                                                <label for="valueType${item.id}">${item.name}</label>
                                                </span>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            评估财产范围<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select class="form-control" name="propertyScope">
                                                <option value="">--请选择--</option>
                                                <c:if test="${not empty propertyScopes}">
                                                    <c:forEach items="${propertyScopes}" var="item">
                                                        <option value="${item.id}">${item.name}</option>
                                                    </c:forEach>
                                                </c:if>
                                            </select>
                                            <%--<input required type="text" class="form-control" name="propertyScope">--%>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            范围包括<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input required type="text" class="form-control" name="scopeInclude">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            范围不包括<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input required type="text" class="form-control" name="scopeNotInclude">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            模版<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea placeholder="填写模版" class="form-control" id="template"
                                                      name="template" required="required"></textarea>
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
                    <button type="button" class="btn btn-primary" onclick="dataValueDefinition.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>
