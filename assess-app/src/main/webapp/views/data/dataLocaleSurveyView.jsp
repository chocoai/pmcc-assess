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
                                        <label for="queryType" class="col-md-1 col-form-label">类型</label>
                                        <div class="col-md-3 p-0">
                                            <select id='queryType' name='queryType'
                                                    class='form-control input-full search-select select2'>
                                                <option value="">-请选择-</option>
                                                <option value="0">系统</option>
                                                <option value="1">个人</option>
                                            </select>
                                        </div>
                                        <label for="queryName" class="col-md-1 col-form-label">名称</label>
                                        <div class="col-md-3 p-0">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="名称" id="queryName" name="queryName"
                                                   class="form-control input-full">
                                        </div>
                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="dataProperty.prototype.findList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                        <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                                data-toggle="modal" onclick="dataProperty.prototype.showModel()"
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


<%@include file="/views/data/dataLocaleSurveyPictureView.jsp" %>
</body>

<script type="text/javascript">
    $(function () {
        dataProperty.prototype.loadDataDicList(null);
    });
    var dataProperty = function () {

    };
    dataProperty.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_FatherList";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            return data;
        },
        isEmpty: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function (type, name) {
            var cols = [];
            cols.push({field: 'name',width: '20%',  title: '名称'});
            cols.push({field: 'typeName',width: '20%',  title: '类型'});
            cols.push({field: 'sorting', width: '10%', title: '排序'});
            cols.push({field: 'remark',width: '35%',  title: '备注'});
            cols.push({
                field: 'id',width: '15%',  title: '操作', formatter: function (value, row, index) {
                    var str = '<button type="button" onclick="dataProperty.prototype.showItemModel(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看">';
                    str += '<i class="fa fa-search"></i>';
                    str += '</button>';
                    str += '<button type="button" onclick="dataProperty.prototype.getAndInit(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                    str += '<i class="fa fa-pen"></i>';
                    str += '</button>';
                    str += '<button type="button" onclick="dataProperty.prototype.removeData(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                    str += '<i class="fa fa-minus"></i>';
                    str += '</button>';
                    return str;
                }
            });
            $("#" + dataProperty.prototype.config().table).bootstrapTable('destroy');
            TableInit(dataProperty.prototype.config().table, "${pageContext.request.contextPath}/dataLocaleSurvey/getDataLocaleSurveyList", cols, {
                type: type,
                name: name
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        findList: function () {
            var type = $("#frmQuery").find("#queryType").val();
            var name = $("#frmQuery").find("#queryName").val();
            dataProperty.prototype.loadDataDicList(type, name);
        },
        removeData: function (id) {
            AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/dataLocaleSurvey/deleteDataLocaleSurveyById",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除数据成功");
                        dataProperty.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
            })
        },
        showModel: function () {
            $("#" + dataProperty.prototype.config().frm).clearAll();
            $('#' + dataProperty.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + dataProperty.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(dataProperty.prototype.config().frm);
            $.ajax({
                url: "${pageContext.request.contextPath}/dataLocaleSurvey/saveAndUpdateDataLocaleSurvey",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        $('#' + dataProperty.prototype.config().box).modal('hide');
                        dataProperty.prototype.loadDataDicList();
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
                url: "${pageContext.request.contextPath}/dataLocaleSurvey/getDataLocaleSurveyById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + dataProperty.prototype.config().frm).clearAll();
                        $("#" + dataProperty.prototype.config().frm).initForm(result.data);
                        $('#' + dataProperty.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        }, showItemModel: function (id) {
            dataLocaleSurveyPicture.prototype.showItemModel(id);
        },

    }
</script>

<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">现场查勘图片模板配置</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmFather" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <c:if test="${permission eq 'admin'}">
                                        <div class="col-md-6">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-2 col-form-label">
                                                    类型<span class="symbol required"></span>
                                                </label>
                                                <div class="col-sm-10">
                                                    <select name='type'
                                                            class='form-control input-full search-select select2'
                                                            required>
                                                        <option value="">-请选择-</option>
                                                        <option value="0">系统</option>
                                                        <option value="1">个人</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full" required
                                                       name="name" placeholder="名称">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                排序
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full" required
                                                       name="sorting" placeholder="排序">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                备注
                                            </label>
                                            <div class="col-sm-11">
                                            <textarea placeholder="备注" id="remark" name="remark"
                                                      class="form-control input-full"></textarea>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="dataProperty.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

</html>
