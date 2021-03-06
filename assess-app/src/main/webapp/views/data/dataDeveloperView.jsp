<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/7/19
  Time: 14:18
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
                                        <label for="queryName" class="col-md-1 col-form-label">名称</label>
                                        <div class="col-md-3 p-0">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="名称" id="queryName" name="queryName"
                                                   class="form-control input-full">
                                        </div>

                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="dataDeveloper.prototype.loadDataDicList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                        <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                                data-toggle="modal" onclick="dataDeveloper.prototype.showModel()"
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
        dataDeveloper.prototype.loadDataDicList();
    });



    var dataDeveloper = function () {

    };
    dataDeveloper.prototype = {
        config:function () {
            var data = {};
            data.table = "tb_FatherList" ;
            data.box = "divBoxFather";
            data.frm = "frmFather";
            return data;
        },
        loadDataDicList:function () {
            var cols = [];
            cols.push({field: 'name', title: '名称'});
            cols.push({field: 'companyNatureName', title: '公司性质'});
            cols.push({field: 'socialPrestigeName', title: '社会信誉'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<button onclick="dataDeveloper.prototype.getAndInit(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                    str += '<i class="fa fa-pen"></i>';
                    str += '</button>';
                    str += '<button onclick="dataDeveloper.prototype.removeData(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                    str += '<i class="fa fa-minus"></i>';
                    str += '</button>';
                    return str;
                }
            });
            $("#"+dataDeveloper.prototype.config().table).bootstrapTable('destroy');
            TableInit(dataDeveloper.prototype.config().table, "${pageContext.request.contextPath}/dataDeveloper/getDataDeveloperList", cols, {
                name:$("#queryName").val()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData:function (id) {
            AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
               $.ajax({
                   url:"${pageContext.request.contextPath}/dataDeveloper/deleteDataDeveloperById",
                   type: "post",
                   dataType: "json",
                   data: {id:id},
                   success: function (result) {
                       if (result.ret) {
                           notifySuccess("成功", "删除数据成功");
                           dataDeveloper.prototype.loadDataDicList();
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
        showModel:function () {
            $("#"+dataDeveloper.prototype.config().frm).clearAll();
            $('#'+dataDeveloper.prototype.config().box).modal("show");
        },
        saveData:function () {
            if (!$("#"+dataDeveloper.prototype.config().frm).valid()){
                return false;
            }
            var data = formParams(dataDeveloper.prototype.config().frm);
            $.ajax({
                url:"${pageContext.request.contextPath}/dataDeveloper/saveAndUpdateDataDeveloper",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        $('#'+dataDeveloper.prototype.config().box).modal('hide');
                        dataDeveloper.prototype.loadDataDicList();
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
        getAndInit:function (id) {
            $.ajax({
                url:"${pageContext.request.contextPath}/dataDeveloper/getDataDeveloperById",
                type: "get",
                dataType: "json",
                data: {id:id},
                success: function (result) {
                    if (result.ret) {
                        $("#"+dataDeveloper.prototype.config().frm).clearAll();
                        $("#" + dataDeveloper.prototype.config().frm).initForm(result.data);
                        $('#'+dataDeveloper.prototype.config().box).modal("show");
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
                <h4 class="modal-title">开发商</h4>
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
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full" name="name"
                                                       placeholder="名称" required="required">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                公司性质
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="companyNature" class="form-control input-full search-select select2">
                                                    <option value="">-请选择-</option>
                                                    <c:forEach items="${unitPropertiesList}" var="item">
                                                        <option value="${item.id}">${item.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                社会信誉
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="socialPrestige" class="form-control input-full search-select select2">
                                                    <option value="">-请选择-</option>
                                                    <c:forEach items="${reputations}" var="item">
                                                        <option value="${item.id}">${item.name}</option>
                                                    </c:forEach>
                                                </select>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="dataDeveloper.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

</html>
