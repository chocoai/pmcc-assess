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
                                                onclick="loadProjectSpotCheckList();">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                        <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                                onclick="showSpotCheckModal();"><span class="btn-label"><i
                                                class="fa fa-plus"></i></span>
                                            新增
                                        </button>
                                    </div>
                                </form>
                                <table class="table table-bordered" id="tbSpotChcekList"></table>
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
<%--填写考核基础信息--%>
<div id="spotCheckModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">外勤考核</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form id="frmSpotCheck" class="form-horizontal">
                    <div class="form-group form-inline">
                        <label class="col-sm-2 col-form-label">年份<span class="symbol required"></span></label>
                        <div class="col-sm-10">
                            <select class="form-control input-full search-select select2" name="year">
                                <option value="">未选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group form-inline">
                        <label class="col-sm-2 col-form-label">考核月份<span class="symbol required"></span></label>
                        <div class="col-sm-10">
                            <select class="form-control input-full search-select select2" name="month">
                                <option value="">未选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group form-inline">
                        <label class="col-sm-2 col-form-label">外勤月份<span class="symbol required"></span></label>
                        <div class="col-sm-4">
                            <select class="form-control input-full search-select select2" required
                                    name="legworkStartMonth">
                                <option value="">未选择</option>
                            </select>
                        </div>
                        <div class="col-sm-2" style="text-align: center;">~</div>
                        <div class="col-sm-4">
                            <select class="form-control input-full search-select select2" required
                                    name="legworkEndMonth">
                                <option value="">未选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group form-inline">
                        <label class="col-sm-2 col-form-label">标题<span
                                class="symbol required"></span></label>
                        <div class="col-sm-10">
                            <input type="text" data-rule-maxlength="100" placeholder="标题"
                                   name="title" onfocus="titleFocus(this);"
                                   class="form-control input-full">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="saveSpotCheck();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        loadProjectSpotCheckList();
    });


    //加载抽查数据列表
    function loadProjectSpotCheckList() {
        var cols = [];
        cols.push({field: 'title', title: '标题', width: '40%'});
        cols.push({field: 'year', title: '年份', width: '15%'});
        cols.push({field: 'month', title: '考核月份', width: '15%'});
        cols.push({
            field: 'month', title: '外勤月份', width: '15%', formatter: function (value, row, index) {
                return row.legworkStartMonth + "~" + row.legworkEndMonth;
            }
        });
        cols.push({
            field: 'status', title: '状态', width: '10%', formatter: function (value, row, index) {
                var str = "";
                switch (value) {
                    case "runing": {
                        str = "<span class='label label-info'>" + "进行中" + "</span>";
                        break;
                    }
                    case "finish": {
                        str = "<span class='label label-success'>" + "已完成" + "</span>";
                        break;
                    }
                    case "close": {
                        str = "<span class='label label-warning'>" + "关闭" + "</span>";
                        break;
                    }
                }
                return str;
            }
        });
        cols.push({
            field: 'opt', title: '操作', width: '10%', formatter: function (value, row, index) {
                var str = '';
                str += '<button type="button" onclick="viewDetail(' + row.id + ',\'' + row.processInsId + '\')"  style="margin-left: 5px;"  class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看详情">';
                str += '<i class="fa fa-search"></i>';
                str += '</button>';
                str += '<button type="button" onclick="cleanProjectAssessmentBonus(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="重新发起">';
                str += '<i class="fa fa-reply fa-white"></i>';
                str += '</button>';
                return str;
            }
        });
        $("#tbSpotChcekList").bootstrapTable('destroy');
        TableInit("tbSpotChcekList", "${pageContext.request.contextPath}/projectAssessmentBonus/getProjectAssessmentBonusDataList", cols, {
            title: $("#queryName").val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }


    //显示窗口
    function showSpotCheckModal() {
        var frm = $('#frmSpotCheck');
        var box = $('#spotCheckModal');
        frm.clearAll();
        frm.validate();

        var date = new Date();
        var arrMonth = [];
        var arrYear = [];
        arrYear.push(date.getFullYear());
        arrYear.push(date.getFullYear() - 1);
        for (var i = 1; i <= 12; i++) {
            arrMonth.push(i);
        }
        frm.find("select[name='year']").empty().html(getOptionHtml(arrYear, "年")).trigger('change');
        frm.find("select[name='year']").val(date.getFullYear()).trigger('change');

        frm.find("select[name='month']").empty().html(getOptionHtml(arrMonth, "月")).trigger('change');
        frm.find("select[name='month']").val(date.getMonth()).trigger('change');

        frm.find("select[name='legworkStartMonth']").empty().html(getOptionHtml(arrMonth, "月")).trigger('change');
        frm.find("select[name='legworkStartMonth']").val(date.getMonth() - 3).trigger('change');

        frm.find("select[name='legworkEndMonth']").empty().html(getOptionHtml(arrMonth, "月")).trigger('change');
        frm.find("select[name='legworkEndMonth']").val(date.getMonth()).trigger('change');
        box.modal();
    }

    function getOptionHtml(arr, title) {
        var html = "";
        html += "<option value=''>请选择</option>";
        $.each(arr, function (k, item) {
            html += "<option value='" + item + "'" + ">" + item + title + "</option>";
        });
        return html;
    }

    function getProjectAssessmentBonusByCount(data, callback) {
        $.ajax({
            url: '${pageContext.request.contextPath}/projectAssessmentBonus/getProjectAssessmentBonusByCount',
            data: data,
            type: 'get',
            dataType: 'json',
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    AlertError('错误', result.errmsg);
                }
            }
        });
    }

    function cleanProjectAssessmentBonus(id) {
        AlertConfirm("是否确认重新发起", "重新发起后会删除之前考核的数据和任务", function (flag) {
            $.ajax({
                url: '${pageContext.request.contextPath}/projectAssessmentBonus/afreshAssessmentBonusTask',
                data: {id: id},
                type: 'post',
                dataType: 'json',
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("提示", "重新发起成功!");
                        loadProjectSpotCheckList();
                    } else {
                        AlertError('错误', result.errmsg);
                    }
                }
            });
        });
    }

    //保存数据
    function saveSpotCheck() {
        if (!$('#frmSpotCheck').valid()) {
            return false;
        }
        var data = formSerializeArray($('#frmSpotCheck'));
        getProjectAssessmentBonusByCount(data, function (item) {
            if (Number(item) >= 1) {
                notifyInfo("提示", "重复考核!");
                return false;
            }
            $.ajax({
                url: '${pageContext.request.contextPath}/projectAssessmentBonus/getHrLegworkDtoList',
                data: {formData: JSON.stringify(data)},
                type: 'get',
                dataType: 'json',
                success: function (result) {
                    if (result.ret) {
                        if (result.data) {
                            if (result.data.length == 0) {
                                notifyInfo("提示", "无外勤数据!");
                                return false;
                            }
                            $.ajax({
                                url: '${pageContext.request.contextPath}/projectAssessmentBonus/launchAssessmentBonusTask',
                                data: {formData: JSON.stringify(data)},
                                type: 'post',
                                dataType: 'json',
                                success: function (result) {
                                    if (result.ret) {
                                        $('#spotCheckModal').modal('hide');
                                        notifySuccess("成功", "保存数据成功!");
                                        loadProjectSpotCheckList();
                                    } else {
                                        AlertError('错误', result.errmsg);
                                    }
                                }
                            });
                        }
                    } else {
                        AlertError('错误', result.errmsg);
                    }
                }
            });
        });
    }

    //自动生成标题
    function titleFocus(_this) {
        var form = $(_this).closest('form');
        var year = form.find('[name=year]').val();
        var month = form.find('[name=month]').val();
        form.find('[name=title]').val(year + "年" + month + "月" + "项目外勤考核");
    }

    //查看详情
    function viewDetail(id, processInsId) {
        var url = '${pageContext.request.contextPath}/projectAssessmentBonus/detail?boxId=0';
        if (id) {
            url += '&bonusId=' + id;
        }
        if (processInsId) {
            url += '&processInsId=' + processInsId;
        }
        window.open(url);
    }
</script>


</html>
