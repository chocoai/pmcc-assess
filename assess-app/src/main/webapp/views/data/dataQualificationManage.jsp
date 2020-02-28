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
                                        <label for="queryType" class="col-md-1 col-form-label">资质类型</label>
                                        <div class="col-md-3 p-0">
                                            <select class="form-control input-full" required id="queryType">
                                                <option value="">--请选择--</option>
                                                <c:if test="${not empty qualificationTypes}">
                                                    <c:forEach items="${qualificationTypes}" var="item">
                                                        <option value="${item.key}">${item.value}</option>
                                                    </c:forEach>
                                                </c:if>
                                            </select>
                                        </div>

                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="dataQualification.prototype.loadDataDicList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                        <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                                onclick="dataQualification.prototype.showModel()">
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

    /**
     * 获取资质
     * @param userAccount
     * @param qualificationType
     * @param callback
     * @author zch
     */
    function getAdPersonalIdentityDto(userAccount, qualificationType, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/dataQualification/getAdPersonalIdentityDto",
            data: {
                userAccount: userAccount,
                qualificationType: qualificationType
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                if (result.ret && result.data) {
                    callback(result.data);
                }
            },
            error: function (result) {
                notifyWarning("调用服务端方法失败","失败原因:" + result);
            }
        });
    }

    $(function () {
        dataQualification.prototype.loadDataDicList();
    });
    var dataQualification = function () {

    };
    dataQualification.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_FatherList";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            return data;
        },
        loadDataDicList: function () {
            var cols = [];
            cols.push({field: 'qualificationTypeName',width:"40%", title: '资质类型'});
            cols.push({field: 'userAccountName',width:"40%", title: '人员'});
            cols.push({
                field: 'id',width:"20%", title: '操作', formatter: function (value, row, index) {
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    var str = '<button onclick="dataQualification.prototype.getAndInit(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                    str += '<i class="fa fa-pen"></i>';
                    str += '</button>';
                    str += '<button onclick="dataQualification.prototype.removeData(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                    str += '<i class="fa fa-minus"></i>';
                    str += '</button>';
                    return str;
                }
            });
            $("#" + dataQualification.prototype.config().table).bootstrapTable('destroy');
            TableInit(dataQualification.prototype.config().table, "${pageContext.request.contextPath}/dataQualification/getDataQualificationList", cols, {
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
                Loading.progressShow();
                $.ajax({
                    url: "${pageContext.request.contextPath}/dataQualification/deleteDataQualificationById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        Loading.progressHide();
                        if (result.ret) {
                            notifySuccess("成功","删除数据成功");
                            dataQualification.prototype.loadDataDicList();
                        }
                        else {
                            AlertError("删除数据失败", result.errmsg);
                        }
                    },
                    error: function (result) {
                        Loading.progressHide();
                        AlertError("调用服务端方法失败", result);
                    }
                })
            });
        },
        showModel: function () {
            $("#" + dataQualification.prototype.config().frm).clearAll();
            $('#' + dataQualification.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + dataQualification.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(dataQualification.prototype.config().frm);
            $.ajax({
                url: "${pageContext.request.contextPath}/dataQualification/saveAndUpdateDataQualification",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        $('#' + dataQualification.prototype.config().box).modal('hide');
                        dataQualification.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("错误", "保存数据失败");
                    }
                },
                error: function (result) {
                    AlertError("错误", "保存数据失败");
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/dataQualification/getDataQualificationById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + dataQualification.prototype.config().frm).clearAll();
                        $("#" + dataQualification.prototype.config().frm).initForm(result.data);
                        $('#' + dataQualification.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    notifyWarning("调用服务端方法失败","失败原因:"+ result);
                }
            })
        },
        //选择人员
        personSelect: function (this_) {
            var frm = $(this_).closest("form");
            var item = formParams(frm.attr("id"));
            if (item.qualificationType) {
                erpEmployee.select({
                    multi: false,
                    currOrgId: 1,
                    userName: $("#userAccountName").val(),
                    userAccount: $("#userAccount").val(),
                    onSelected: function (data) {
                        getAdPersonalIdentityDto(data.account, item.qualificationType, function (item) {
                            if (item.length >= 1) {
                                $("#userAccount").val(data.account);
                                $("#userAccountName").val(data.name);
                            } else {
                                $("#userAccount").val("");
                                $("#userAccountName").val("");
                                $('#' + dataQualification.prototype.config().box).modal('hide');
                                notifyWarning("失败","该人员未有评估师资格");
                            }
                        });
                    }
                });
            } else {
                $('#' + dataQualification.prototype.config().box).modal('hide');
                notifyWarning("失败","资格类型必须先选择");
            }
        }

    }
</script>
<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">资质管理</h4>
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
                                            <label class="col-sm-2 control-label">
                                                资质类型<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select class="form-control  input-full" required name="qualificationType">
                                                    <option value="">--请选择--</option>
                                                    <c:if test="${not empty qualificationTypes}">
                                                        <c:forEach items="${qualificationTypes}" var="item">
                                                            <option value="${item.key}">${item.value}</option>
                                                        </c:forEach>
                                                    </c:if>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                选择人员
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="hidden" id="userAccount" name="userAccount">
                                                <input type="text" data-rule-maxlength="50" readonly
                                                       placeholder="选择人员"
                                                       onclick="dataQualification.prototype.personSelect(this)"
                                                       id="userAccountName" name="userAccountName" class="form-control input-full">
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
                <button type="button" class="btn btn-primary btn-sm" onclick="dataQualification.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>
</html>
