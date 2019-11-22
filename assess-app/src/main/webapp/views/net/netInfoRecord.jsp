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
                        <div class="form-group">
                            <div>
                                <label class="col-sm-1 control-label">
                                    内容
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="内容" id="queryContent" name="queryContent"
                                           class="form-control">
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">
                                    标题
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="标题" id="queryTitle" name="queryTitle"
                                           class="form-control">
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">
                                    网站
                                </label>
                                <div class="col-sm-3">
                                    <select class="form-control" required id="queryWebName">
                                        <option value="">--请选择--</option>
                                        <c:if test="${not empty webTypes}">
                                            <c:forEach items="${webTypes}" var="item">
                                                <option value="${item}">${item}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group ">
                            <div>
                                <label class="col-sm-1 control-label">省
                                </label>
                                <div class="col-sm-3">
                                    <select id="province" name="province"
                                            class="form-control search-select select2" required="required">
                                    </select>
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">市
                                </label>
                                <div class="col-sm-3">
                                    <select id="city" name="city" class="form-control search-select select2"
                                            required="required">
                                    </select>
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">
                                    类型
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="类型" id="queryType" name="queryType"
                                           class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="form-group ">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    开始日期
                                </label>
                                <div class="col-sm-3">
                                    <input placeholder="开始日期" id="queryStartTime" data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate roomTime">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    结束日期
                                </label>
                                <div class="col-sm-3">
                                    <input placeholder="结束日期" id="queryEndTime" data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate roomTime">
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <button type="button" class="btn btn-primary"
                                        onclick="detailInfo.prototype.loadDataDicList()">
                                    查询
                                </button>
                                <button type="button" class="btn btn-primary"
                                        onclick="detailInfo.prototype.getOldData()">
                                    测试
                                </button>
                                <%--<button type="button" class="btn btn-primary"--%>
                                        <%--onclick="detailInfo.prototype.assignTask()">--%>
                                    <%--任务分派--%>
                                <%--</button>--%>
                                <button type="button" class="btn btn-primary"
                                        onclick="detailInfo.prototype.assignTask()">
                                    任务分派(认领)
                                </button>
                            </div>
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
    <!-- end: MAIN CONTAINER -->
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
                                            <label class="form-control" name="currentPrice"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            估算价
                                        </label>
                                        <div class="col-sm-4">
                                            <label class="form-control" name="consultPrice"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            起始价
                                        </label>
                                        <div class="col-sm-4">
                                            <label class="form-control" name="initPrice"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            单位
                                        </label>
                                        <div class="col-sm-4">
                                            <label class="form-control" name="unitName"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            数量
                                        </label>
                                        <div class="col-sm-4">
                                            <label class="form-control" name="amount"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            面积
                                        </label>
                                        <div class="col-sm-4">
                                            <label class="form-control" name="area"></label>
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
                                                   class="form-control date-picker dbdate">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            协商日期
                                        </label>
                                        <div class="col-sm-3">
                                            <input disabled
                                                   name="negotiatedDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            协商总价
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="form-control" name="negotiatedTotalPrice"></label>
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

<%@include file="/views/share/main_footer.jsp" %>
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
            cols.push({field: 'title', title: '标题', width: '13%'});
            cols.push({field: 'province', title: '省', width: '5%'});
            cols.push({field: 'city', title: '市', width: '5%'});
            cols.push({field: 'sourceSiteName', title: '来源网站', width: '10%'});
            cols.push({field: 'type', title: '类型', width: '6%'});
            cols.push({
                field: 'beginTime', title: '开始时间', width: '7%', formatter: function (value, row, index) {
                    return formatDate(row.beginTime, false);
                }
            });
            cols.push({
                field: 'endTime', title: '结束时间', width: '7%', formatter: function (value, row, index) {
                    return formatDate(row.endTime, false);
                }
            });
            cols.push({field: 'content', title: '内容', width: '25%'});
            cols.push({field: 'initPrice', title: '起始价', width: '5%'});
            cols.push({field: 'consultPrice', title: '估算价', width: '5%'});
            cols.push({field: 'currentPrice', title: '成交价', width: '5%'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    // str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="详情" onclick="detailInfo.prototype.getAndInit(' + row.id + ')"><i class="fa fa-search fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="查看网址" onclick="detailInfo.prototype.openItem(' + index + ')"><i class="fa fa-eye fa-white"></i></a>';
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
                    Alert("调用服务端方法失败，失败原因:" + result);
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
                        toastr.success('保存成功');
                        $('#' + detailInfo.prototype.config().box).modal('hide');
                        detailInfo.prototype.loadDataDicList();
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
        getOldData: function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/netInfoRecordController/getOldData",
                type: "post",
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        Loading.progressHide();
                        toastr.success('获取数据成功');
                    }
                    else {
                        Alert("获取数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
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
                        toastr.success('分派成功');
                        detailInfo.prototype.loadDataDicList();
                    }
                    else {
                        Alert("失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        }

    }


</script>


</html>
