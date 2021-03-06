<%--
  Created by IntelliJ IDEA.
  User: huhao
  Date: 2018/01/29
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>归档记录</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <div class="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        归档记录
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="master_form" class="form-horizontal">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <table class="table table-bordered" id="tb_FatherList">
                                            </table>
                                        </div>
                                    </div>
                                </form>
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
</html>

<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">归档信息1</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">序号</th>
                        <th scope="col">名称</th>
                        <th scope="col">纸质资料</th>
                        <th scope="col">电子资料</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1</td>
                        <td>申报附件</td>
                        <td><div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" type="checkbox" value="">
                                <span class="form-check-sign">Agree with terms and conditions</span>
                            </label>
                        </div></td>
                        <td><div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" type="checkbox" value="">
                                <span class="form-check-sign">Agree with terms and conditions</span>
                            </label>
                        </div></td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Jacob</td>
                        <td>Thornton</td>
                        <td>@fat</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td colspan="2">Larry the Bird</td>
                        <td>@twitter</td>
                    </tr>
                    </tbody>
                </table>


            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm"
                        onclick="projectXlxPigeonholeRecord.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        projectXlxPigeonholeRecord.prototype.loadDataDicList();
    });
    var projectXlxPigeonholeRecord = function () {

    };
    projectXlxPigeonholeRecord.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_FatherList";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            return data;
        },

        loadDataDicList: function () {
            var cols = [];
            cols.push({field: 'fileName', width: '50%', title: '文件名称'});
            cols.push({
                field: 'hasPaperDatum', width: '15%', title: '有无纸质资料', formatter: function (value,row,index) {
                    var html='<div class="form-check" style="justify-content:left">';
                    html+='<label class="form-check-label">';
                    html+='<input class="form-check-input" type="checkbox" name="hasPaperDatum" value="true" onclick="projectXlxPigeonholeRecord.prototype.saveData2('+index+',this)"';
                    if (value==true) {
                        html += ' checked="checked" ';
                    }
                    html+='><span class="form-check-sign"></span>';
                    html+='</label>';
                    html+='</div>';
                    return html;
                }
            });
            cols.push({
                field: 'hasElectronicDatum', width: '15%', title: '有无电子资料', formatter: function (value,row,index) {
                    var html='<div class="form-check" style="justify-content:left">';
                    html+='<label class="form-check-label">';
                    html+='<input class="form-check-input" type="checkbox" name="hasElectronicDatum" value="true"  onclick="projectXlxPigeonholeRecord.prototype.saveData2('+index+',this)"';
                    if (value==true) {
                        html += ' checked="checked" ';
                    }
                    html+='><span class="form-check-sign"></span>';
                    html+='</label>';
                    html+='</div>';
                    return html;
                }
            });
            $("#" + projectXlxPigeonholeRecord.prototype.config().table).bootstrapTable('destroy');
            TableInit(projectXlxPigeonholeRecord.prototype.config().table, "${pageContext.request.contextPath}/projectXlxPigeonholeRecord/list", cols, {
                projectId: ${projectId}
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                pageSize: 150,
                pagination: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/projectXlxPigeonholeRecord/delete",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除数据成功");
                            projectXlxPigeonholeRecord.prototype.loadDataDicList();
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
            $("#" + projectXlxPigeonholeRecord.prototype.config().frm).clearAll();
            $('#' + projectXlxPigeonholeRecord.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + projectXlxPigeonholeRecord.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(projectXlxPigeonholeRecord.prototype.config().frm);
            data.hasPaperDatum = $('#hasPaperDatum').prop('checked');
            data.hasElectronicDatum = $('#hasElectronicDatum').prop('checked');
            data.projectId = ${projectId};
            $.ajax({
                url: "${pageContext.request.contextPath}/projectXlxPigeonholeRecord/save",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        $('#' + projectXlxPigeonholeRecord.prototype.config().box).modal('hide');
                        projectXlxPigeonholeRecord.prototype.loadDataDicList();
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
                url: "${pageContext.request.contextPath}/projectXlxPigeonholeRecord/get",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + projectXlxPigeonholeRecord.prototype.config().frm).clearAll();
                        $("#" + projectXlxPigeonholeRecord.prototype.config().frm).initForm(result.data);
                        $('#' + projectXlxPigeonholeRecord.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        refresh: function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/projectXlxPigeonholeRecord/refresh",
                type: "post",
                dataType: "json",
                data: {projectId: ${projectId}},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "刷新数据成功");
                        projectXlxPigeonholeRecord.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("刷新数据成功，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        saveData2:function(index,_that){
            var row = $("#tb_FatherList").bootstrapTable('getData')[index];
            var tempName = $(_that).attr("name");
            var value = $(_that).prop('checked');;
            row[tempName] = value;
            $.ajax({
                url: "${pageContext.request.contextPath}/projectXlxPigeonholeRecord/save",
                type: "post",
                dataType: "json",
                data: {formData: JSON.stringify(row)},
                success: function (result) {
                    if (result.ret) {
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

</script>
