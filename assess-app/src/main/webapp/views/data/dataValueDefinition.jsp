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
                                        <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                                data-toggle="modal" onclick="dataValueDefinition.prototype.showModel()"
                                                href="#divBoxFather">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                            新增
                                        </button>
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
            cols.push({field: 'entrustmentPurposeName', width: '50%',title: '委托目的'});
            cols.push({field: 'valueTypeName',width: '10%', title: '价值类型'});
            cols.push({field: 'propertyScopeName',width: '10%', title: '评估财产范围'});
            cols.push({field: 'scopeInclude', width: '10%',title: '范围包括'});
            cols.push({field: 'scopeNotInclude',width: '10%', title: '范围不包括'});
            cols.push({
                field: 'id',width: '10%', title: '操作', formatter: function (value, row, index) {
                    var str = '<button onclick="dataValueDefinition.prototype.getAndInit(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                    str += '<i class="fa fa-pen"></i>';
                    str += '</button>';
                    str += '<button onclick="dataValueDefinition.prototype.removeData(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                    str += '<i class="fa fa-minus"></i>';
                    str += '</button>';
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
            AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/dataValueDefinition/deleteDataValueDefinitionById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除数据成功");
                            dataValueDefinition.prototype.loadDataDicList();
                        }
                        else {
                            AlertError("删除数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        AlertError("调用服务端方法失败，失败原因:" + result);
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
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        $('#' + dataValueDefinition.prototype.config().box).modal('hide');
                        dataValueDefinition.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
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
                    AlertError("调用服务端方法失败，失败原因:" + result);
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
                <h4 class="modal-title">价值定义配置</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmFather" class="form-horizontal">
                    <input type="hidden" id="id" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <c:forEach items="${purposeDicList}" var="item">
                                        <div class="form-check">
                                            <label class="form-check-label" style="margin-left: 2px;">
                                                <input type="checkbox" id="entrustmentPurpose${item.id}"
                                                       required
                                                       name="entrustmentPurpose" value="${item.id}"
                                                       class="form-check-input">
                                                <span class="form-check-sign"><label
                                                        for="entrustmentPurpose${item.id}">${item.name}</label></span>
                                            </label>
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="row form-group">
                                    <c:forEach items="${valueTypeList}" var="item">
                                        <div class="form-check">
                                            <label class="form-check-label" style="margin-left: 2px;">
                                                <input type="checkbox" id="valueType${item.id}"
                                                       required
                                                       name="valueType" value="${item.id}"
                                                       class="form-check-input">
                                                <span class="form-check-sign"><label
                                                        for="valueType${item.id}">${item.name}</label></span>
                                            </label>
                                        </div>
                                    </c:forEach>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                评估财产范围<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select class="form-control input-full " name="propertyScope">
                                                    <option value="">--请选择--</option>
                                                    <c:if test="${not empty propertyScopes}">
                                                        <c:forEach items="${propertyScopes}" var="item">
                                                            <option value="${item.id}">${item.name}</option>
                                                        </c:forEach>
                                                    </c:if>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                范围包括<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input required type="text" class="form-control input-full " name="scopeInclude">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                范围不包括<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input required type="text" class="form-control input-full " name="scopeNotInclude">
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
                <button type="button" class="btn btn-primary btn-sm" onclick="dataValueDefinition.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

</html>
