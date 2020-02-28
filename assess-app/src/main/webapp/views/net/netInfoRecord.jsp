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
                                        <label class="col-md-1 col-form-label">内容</label>
                                        <div class="col-md-3 p-0">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="内容" id="queryContent" name="queryContent"
                                                   class="form-control input-full">
                                        </div>
                                        <label class="col-md-1 col-form-label">标题</label>
                                        <div class="col-md-3 p-0">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="标题" id="queryTitle" name="queryTitle"
                                                   class="form-control input-full">
                                        </div>
                                        <label class="col-md-1 col-form-label">网站</label>
                                        <div class="col-md-3 p-0">
                                            <select class="form-control input-full" required id="queryWebName">
                                                <option value="">--请选择--</option>
                                                <c:if test="${not empty webTypes}">
                                                    <c:forEach items="${webTypes}" var="item">
                                                        <option value="${item}">${item}</option>
                                                    </c:forEach>
                                                </c:if>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group form-inline">
                                        <label class="col-md-1 col-form-label">省</label>
                                        <div class="col-md-3 p-0">
                                            <select id="province" name="province"
                                                    class="form-control input-full search-select select2" required="required">
                                            </select>
                                        </div>
                                        <label class="col-md-1 col-form-label">市</label>
                                        <div class="col-md-3 p-0">
                                            <select id="city" name="city" class="form-control input-full search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                        <label class="col-md-1 col-form-label">类型</label>
                                        <div class="col-md-3 p-0">
                                            <input type="text" data-rule-maxlength="50"
                                                       placeholder="类型" id="queryType" name="queryType"
                                                       class="form-control input-full">
                                        </div>
                                    </div>
                                    <div class="form-group form-inline">
                                        <label class="col-md-1 col-form-label">开始日期</label>
                                        <div class="col-md-3 p-0">
                                            <input placeholder="开始日期" id="queryStartTime" data-date-format="yyyy-mm-dd"
                                                   class="form-control input-full date-picker dbdate roomTime">

                                        </div>
                                        <label class="col-md-1 col-form-label">结束日期</label>
                                        <div class="col-md-3 p-0">
                                            <input placeholder="结束日期" id="queryEndTime" data-date-format="yyyy-mm-dd"
                                                   class="form-control input-full date-picker dbdate roomTime">

                                        </div>
                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="detailInfo.prototype.loadDataDicList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                        <button style="margin-left: 10px" class="btn btn-warning  btn-sm" type="button"
                                                onclick="detailInfo.prototype.assignTask()">
                                            任务分派(认领)
                                        </button>
                                    </div>
                                </form>
                                <input type="hidden" id="selectIds">
                                <table class="table table-bordered" id="transaction_List">
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
<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">信息补全</h3>
            </div>
            <form id="frmFather" class="form-horizontal">
                <input type="hidden" id="id" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            成交价
                                        </label>
                                        <div class="col-sm-4">
                                            <label class="form-control input-full" name="currentPrice"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            估算价
                                        </label>
                                        <div class="col-sm-4">
                                            <label class="form-control input-full" name="consultPrice"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            起始价
                                        </label>
                                        <div class="col-sm-4">
                                            <label class="form-control input-full" name="initPrice"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            单位
                                        </label>
                                        <div class="col-sm-4">
                                            <label class="form-control input-full" name="unitName"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            数量
                                        </label>
                                        <div class="col-sm-4">
                                            <label class="form-control input-full" name="amount"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            面积
                                        </label>
                                        <div class="col-sm-4">
                                            <label class="form-control input-full" name="area"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            评估基准日
                                        </label>
                                        <div class="col-sm-3">
                                            <input disabled
                                                   name="assessBaseDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control input-full date-picker dbdate">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            协商日期
                                        </label>
                                        <div class="col-sm-3">
                                            <input disabled
                                                   name="negotiatedDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control input-full date-picker dbdate">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            协商总价
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control input-full" name="negotiatedTotalPrice"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        关闭
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxClose" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">关闭原因</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="closeFrm" class="form-horizontal">
                    <input type="hidden" id="id" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                关闭原因
                                            </label>
                                            <div class="col-sm-11">
                                           <textarea placeholder="关闭原因" name="closeReason" id="closeReason" required
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
                <button type="button" class="btn btn-primary btn-sm" onclick="detailInfo.prototype.closeItem()">
                    确认关闭
                </button>
            </div>

        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        detailInfo.prototype.loadDataDicList();
        AssessCommon.initAreaInfo({
            useDefaultText: false,
            provinceTarget: $("#province"),
            cityTarget: $("#city"),
            districtTarget: $("#district")
        })
    });
    var detailInfo = function () {

    };
    detailInfo.prototype = {
        config: function () {
            var data = {};
            data.table = "transaction_List";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            return data;
        },
        loadDataDicList: function () {
            var cols = [];
            cols.push({field: 'title', title: '标题', width: '20%'});
            cols.push({field: 'province', title: '省', width: '5%'});
            cols.push({field: 'city', title: '市', width: '5%'});
            cols.push({
                field: 'sourceSiteName', title: '来源网站', width: '`8%', formatter: function (value, row, index) {
                    var str = '<a href="' + row.sourceSiteUrl + '" target="_blank" >' + row.sourceSiteName + '</a>';
                    str += '<br/>(' + row.sourceSiteUrl + ')';
                    return str;
                }
            });
            cols.push({field: 'type', title: '类型', width: '10%'});
            cols.push({
                field: 'beginTime', title: '开始时间', width: '10%', formatter: function (value, row, index) {
                    return formatDate(row.beginTime, false);
                }
            });
            cols.push({
                field: 'id', title: '操作', width: '7%', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                   // str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="查看网址" onclick="detailInfo.prototype.openItem(' + index + ')"><i class="fa fa-eye fa-white"></i></a>';
                    str += '<button onclick="detailInfo.prototype.openItem(' + index + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看网址">';
                    str += '<i class="fa fa-search"></i>';
                    str += '</button>';
                    //str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="关闭" onclick="detailInfo.prototype.closeModal(' + row.id + ')"><i class="fa fa-trash-o"></i></a>';
                    str += '<button onclick="detailInfo.prototype.closeModal(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="关闭">';
                    str += '<i class="fa fa-minus"></i>';
                    str += '</button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + detailInfo.prototype.config().table).bootstrapTable('destroy');
            TableInit(detailInfo.prototype.config().table, "${pageContext.request.contextPath}/netInfoRecordController/getInfoRecordList", cols, {
                queryTitle: $("#queryTitle").val(),
                queryWebName: $("#queryWebName").val(),
                province: $("#province").val(),
                city: $("#city").val(),
                queryContent: $("#queryContent").val(),
                queryType: $("#queryType").val(),
                queryStartTime: $("#queryStartTime").val(),
                queryEndTime: $("#queryEndTime").val()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            }, true);
        },
        openItem: function (index) {
            var row = $("#transaction_List").bootstrapTable('getData')[index];
            if (row.sourceSiteUrl) {
                window.open(row.sourceSiteUrl, "");
            }
        },
        getAndInit: function (id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/netInfoRecordController/getNetInfoRecordById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + detailInfo.prototype.config().frm).clearAll();
                        $("#" + detailInfo.prototype.config().frm).initForm(result.data);
                        $('#' + detailInfo.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        saveData: function () {
            if (!$("#" + detailInfo.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(detailInfo.prototype.config().frm);
            $.ajax({
                url: "${pageContext.request.contextPath}/netInfoRecordController/updateNetInfoRecord",
                type: "post",
                dataType: "json",
                data: {formData: JSON.stringify(data)},
                success: function (result) {
                    if (result.ret) {
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        $('#' + detailInfo.prototype.config().box).modal('hide');
                        detailInfo.prototype.loadDataDicList();
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
        getOldData: function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/netInfoRecordController/getOldData",
                type: "post",
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        Loading.progressHide();
                        notifySuccess("成功","获取数据成功");
                    }
                    else {
                        AlertError("获取数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        assignTask: function () {
            var rows = $('#transaction_List').bootstrapTable('getSelections');

            if (rows && rows.length > 0) {
                var idArray = [];
                $.each(rows, function (i, item) {
                    idArray.push(item.id);
                })
                var ids = idArray.join()
                $("#selectIds").val(ids);
                //选择人员
                detailInfo.prototype.selectUser()
            } else {
                toastr.info('请选择要分派的数据');
            }
        },
        selectUser: function () {
            erpEmployee.select({
                onSelected: function (data) {
                    if (data.account) {
                        //确认分派
                        var ids = $("#selectIds").val();
                        detailInfo.prototype.affirm(data.account, ids);
                    }
                    else {

                    }
                }
            });
        },
        affirm: function (executor, ids) {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/netInfoRecordController/assignTask",
                type: "post",
                data: {
                    executor: executor,
                    ids: ids
                },
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        notifySuccess("成功","分派成功");
                        detailInfo.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("分派失败,失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        closeModal: function (id) {
            $("#closeFrm").clearAll();
            $("#closeFrm").find("input[name='id']").val(id);
            $('#divBoxClose').modal("show");
        },
        closeItem: function () {
            AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
                if (!$("#closeFrm").valid()) {
                    return false;
                }
                var closeReason = $("#closeFrm").find("#closeReason").val();
                var id = $("#closeFrm").find("input[name='id']").val();
                $.ajax({
                    url: "${pageContext.request.contextPath}/netInfoRecordController/closeItem",
                    type: "post",
                    dataType: "json",
                    data: {
                        id: id,
                        closeReason: closeReason
                    },
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除数据成功");
                            $('#divBoxClose').modal('hide');
                            detailInfo.prototype.loadDataDicList();
                        }
                        else {
                            AlertError("保存数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        AlertError("调用服务端方法失败，失败原因:" + result);
                    }
                })
            });


        }

    }


</script>


</html>
