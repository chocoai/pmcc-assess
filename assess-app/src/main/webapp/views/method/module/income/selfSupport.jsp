<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="self_support_info" style="display: none;">
    <div class="x_panel">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
            </ul>
            <h2>收入类<i class="red" id="amount_money_total_0"></i></h2>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <div class="x_title">
                <h3>历史数据</h3>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <div class="btn-group">
                    <button class="btn btn-success" data-toggle="modal" onclick="selfSupport.addHistory(0);">
                        新增
                    </button>
                </div>
                <div class="btn-group">
                    <button type="button" class="btn btn-primary"
                            onclick="$('#ajaxFileUpload').val('').attr('data-type',0).trigger('click')">导入数据
                    </button>
                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                            aria-expanded="false">
                        <span class="caret"></span>
                        <span class="sr-only">Toggle Dropdown</span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="javascript://"
                               onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftMethodIncomeHistory);">下载模板</a>
                        </li>
                        <li><a href="javascript://;"
                               onclick="$('#ajaxFileUpload').val('').attr('data-type',0).trigger('click');">导入数据</a>
                        </li>
                    </ul>
                </div>
                <table class="table table-bordered" id="tb_history_income_list">
                </table>
            </div>

            <div class="x_title">
                <h3>预测数据</h3>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <table class="table table-bordered" id="tb_forecast_income_list">
                </table>
            </div>

        </div>
    </div>

    <div class="x_panel">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
            </ul>
            <h2>成本类 <i class="red" id="amount_money_total_1"></i></h2>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <div class="x_title">
                <h3>历史数据</h3>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <div class="btn-group">
                    <button class="btn btn-success" data-toggle="modal" onclick="selfSupport.addHistory(1);">
                        新增
                    </button>
                </div>
                <div class="btn-group">
                    <button type="button" class="btn btn-primary"
                            onclick="$('#ajaxFileUpload').val('').attr('data-type',1).trigger('click')">导入数据
                    </button>
                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                            aria-expanded="false">
                        <span class="caret"></span>
                        <span class="sr-only">Toggle Dropdown</span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="javascript://"
                               onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftMethodIncomeHistory);">下载模板</a>
                        </li>
                        <li><a href="javascript://;"
                               onclick="$('#ajaxFileUpload').val('').attr('data-type',1).trigger('click');">导入数据</a>
                        </li>
                    </ul>
                </div>
                <table class="table table-bordered" id="tb_history_cost_list">
                </table>
            </div>

            <div class="x_title">
                <h3>预测数据</h3>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <table class="table table-bordered" id="tb_forecast_cost_list">
                </table>
            </div>
        </div>
    </div>

    <div class="x_panel">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
            </ul>
            <h2>参数</h2>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <form id="frm_self_support" class="form-horizontal" enctype="multipart/form-data">
                <input type="hidden" name="id" value="0">
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label" title="行业经营平均利润率">
                            利润率<span class="symbol required"></span>
                        </label>
                        <div class="col-sm-3">
                            <input type="text" name="averageProfitRate" placeholder="行业经营平均利润率" class="form-control"
                                   data-rule-number="true" required="required">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label" title="行业经营平均利润率取数说明">
                            取数说明
                        </label>
                        <div class="col-sm-11">
                            <textarea name="averageProfitRateRemark" class="form-control"
                                      placeholder="行业经营平均利润率取数说明"></textarea>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="x_panel">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
            </ul>
            <h2>测算结果</h2>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">

        </div>
    </div>
</div>

<div id="modal_history" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">历史数据</h3>
            </div>
            <form id="frm_history" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            会计科目<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <select name="accountingSubject" class="form-control"
                                                    onchange="selfSupport.accountingSubjectChange(this);"
                                                    required="required"></select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            一级编号<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <select name="firstLevelNumber" class="form-control"
                                                    onchange="selfSupport.firstLevelNumberChange(this);"
                                                    required="required"></select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            二级编号<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <select name="secondLevelNumber" class="form-control"
                                                    required="required"></select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            月度<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="month" placeholder="月度"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            单价<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="unitPrice" placeholder="单价"
                                                   onblur="selfSupport.computeMoney();"
                                                   data-rule-number="true" class="form-control" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            数量<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="number" placeholder="数量"
                                                   onblur="selfSupport.computeMoney();"
                                                   data-rule-digits="true" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            金额<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="amountMoney" placeholder="金额"
                                                   data-rule-number="true" class="form-control" required="required">
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
                    <button type="button" class="btn btn-primary"
                            onclick="selfSupport.saveHistory();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="modal_forecast" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">预测数据</h3>
            </div>
            <form id="frm_forecast" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type">
                <input type="hidden" name="sectionId">
                <div class="modal-body">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                初始金额<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="initialAmount" placeholder="初始金额" data-rule-number="true"
                                       class="form-control" required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                增长率<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="growthRate" placeholder="增长率" data-rule-number="true"
                                       class="form-control" required="required">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="selfSupport.saveForecast();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="modal_forecast_year_list" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">预测年度信息</h3>
            </div>
            <div class="modal-body">
                <table class="table table-bordered" id="tb_forecast_year_list">
                </table>
            </div>
        </div>
    </div>
</div>

<input type="file" id="ajaxFileUpload" name="file" style="display: none;" data-type="0" onchange="selfSupport.importHistory(this);">
<script type="text/javascript">
    $(function () {

    })

    var selfSupport = {};

    //添加自营金额列表信息
    selfSupport.addHistory = function (type) {
        $("#frm_history").clearAll();
        $("#frm_history").find('[name=firstLevelNumber]').empty();
        $("#frm_history").find('[name=secondLevelNumber]').empty();
        AssessCommon.loadDataDicByKey(selfSupport.getHistoryTypeKey(type), "", function (html, data) {
            $("#modal_history").find('[name=accountingSubject]').empty().append(html);
        });
        $("#frm_history").find('[name=id]').val(0);
        $("#frm_history").find('[name=type]').val(type);
        $('#modal_history').modal();
    }

    //编辑历史信息
    selfSupport.editHistory = function (index, type) {
        var row = $("#" + selfSupport.getHistoryListId(type)).bootstrapTable('getData')[index];
        $("#frm_history").clearAll();
        $("#frm_history").initForm(row);
        AssessCommon.loadDataDicByKey(selfSupport.getHistoryTypeKey(row.type), row.accountingSubject, function (html, data) {
            $("#frm_history").find('[name=accountingSubject]').empty().append(html);
        })
        AssessCommon.loadDataDicByPid(row.accountingSubject, row.firstLevelNumber, function (html, data) {
            $("#frm_history").find('[name=firstLevelNumber]').empty().append(html);
        })
        AssessCommon.loadDataDicByPid(row.firstLevelNumber, row.secondLevelNumber, function (html, data) {
            $("#frm_history").find('[name=secondLevelNumber]').empty().append(html);
        })
        $('#modal_history').modal();
    }

    //获取类型key
    selfSupport.getHistoryTypeKey = function (type) {
        switch (type) {
            case 0:
                return AssessDicKey.mdIncomeHistoryTypeIncome;
            case 1:
                return AssessDicKey.mdIncomeHistoryTypeCost;
        }
    }

    //获取list id
    selfSupport.getHistoryListId = function (type) {
        if (type == 0)
            return "tb_history_income_list";
        if (type == 1)
            return "tb_history_cost_list";
    }

    //获取list id
    selfSupport.getForecastListId = function (type) {
        if (type == 0)
            return "tb_forecast_income_list";
        if (type == 1)
            return "tb_forecast_cost_list";
    }

    //会计编号切换
    selfSupport.accountingSubjectChange = function (_this) {
        $("#frm_history").find('[name=secondLevelNumber]').empty();
        AssessCommon.loadDataDicByPid($(_this).val(), "", function (html, data) {
            $("#frm_history").find('[name=firstLevelNumber]').empty().append(html);
        })
    }

    //一级编号切换
    selfSupport.firstLevelNumberChange = function (_this) {
        AssessCommon.loadDataDicByPid($(_this).val(), "", function (html, data) {
            $("#frm_history").find('[name=secondLevelNumber]').empty().append(html);
        })
    }

    //删除历史信息
    selfSupport.delHistory = function (id, type) {
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/income/deleteHistory",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        selfSupport.loadHistoryList(type);
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

    //保存历史信息
    selfSupport.saveHistory = function () {
        if (!$("#frm_history").valid()) {
            return false;
        }
        var data = formParams("frm_history");
        data.incomeId = $("#frm_income").find('[name=id]').val();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/income/saveHistory",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('保存成功');
                    var type = $("#frm_history").find('[name=type]').val();
                    selfSupport.loadHistoryList(type);
                    $('#modal_history').modal('hide');
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    //导入历史数据
    selfSupport.importHistory = function (_this) {
        Loading.progressShow();
        console.log($($(_this).attr('data-type')));
        var type = $(_this).attr('data-type');
        $.ajaxFileUpload({
            type: "POST",
            url: "${pageContext.request.contextPath}/income/importHistory",
            data: {
                incomeId: $("#frm_income").find('[name=id]').val(),
                type: type
            },//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: 'ajaxFileUpload',//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert(result.data.replace(/\n/g, '<br/>'));
                    selfSupport.loadHistoryList(type);
                } else {
                    Alert("导入数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result, status, e) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }

    //加载历史列表信息
    selfSupport.loadHistoryList = function (type) {
        var cols = [];
        cols.push({field: 'accountingSubjectName', title: '会计科目'});
        cols.push({field: 'firstLevelNumberName', title: '一级编号'});
        cols.push({field: 'secondLevelNumberName', title: '二级编号'});
        cols.push({field: 'month', title: '月度'});
        cols.push({field: 'unitPrice', title: '单价'});
        cols.push({field: 'number', title: '数量'});
        cols.push({field: 'amountMoney', title: '金额'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑" onclick="selfSupport.editHistory(' + index + ',' + type + ');" ><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="selfSupport.delHistory(' + row.id + ',' + type + ')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#" + selfSupport.getHistoryListId(type)).bootstrapTable('destroy');
        TableInit(selfSupport.getHistoryListId(type), "${pageContext.request.contextPath}/income/getHistoryList", cols, {
            type: type,
            incomeId: $("#frm_income").find('[name=id]').val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    //编辑预测信息
    selfSupport.editForecast = function (index, type) {
        var row = $("#" + selfSupport.getForecastListId(type)).bootstrapTable('getData')[index];
        $("#frm_forecast").clearAll();
        $("#frm_forecast").initForm(row);
        $('#modal_forecast').modal();
    }

    //保存预测信息
    selfSupport.saveForecast = function () {
        if (!$("#frm_forecast").valid()) {
            return false;
        }
        var data = formParams("frm_forecast");
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/income/saveForecast",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('保存成功');
                    var type = $("#frm_forecast").find('[name=type]').val();
                    selfSupport.loadForecastList(type);
                    $('#modal_forecast').modal('hide');
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    //删除预测信息
    selfSupport.delForecast = function (id, type) {
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/income/deleteForecast",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        selfSupport.loadForecastList(type);
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

    //加载预测列表信息
    selfSupport.loadForecastList = function (type) {
        var cols = [];
        cols.push({
            field: 'beginDate', title: '开始时间', formatter: function (value, row, index) {
                return formatDate(row.beginDate, false);
            }
        });
        cols.push({
            field: 'endDate', title: '结束时间', formatter: function (value, row, index) {
                return formatDate(row.endDate, false);
            }
        });
        cols.push({field: 'yearCount', title: '年份数'});
        cols.push({field: 'initialAmount', title: '初始金额'});
        cols.push({field: 'growthRate', title: '增长率'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="查看" onclick="selfSupport.viewForecastYear(' + row.id + ');" ><i class="fa fa-search fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑" onclick="selfSupport.editForecast(' + index + ',' + type + ');" ><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="selfSupport.delForecast(' + row.id + ',' + type + ')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#" + selfSupport.getForecastListId(type)).bootstrapTable('destroy');
        TableInit(selfSupport.getForecastListId(type), "${pageContext.request.contextPath}/income/getForecastList", cols, {
            type: type,
            incomeId: $("#frm_income").find('[name=id]').val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    //查看年度预测
    selfSupport.viewForecastYear = function (forecastId) {
        selfSupport.loadForecastYearList(forecastId);
        $('#modal_forecast_year_list').modal('show');
    }

    //加载年度预测列表
    selfSupport.loadForecastYearList = function (forecastId) {
        var cols = [];
        cols.push({
            field: 'beginDate', title: '开始时间', formatter: function (value, row, index) {
                return formatDate(row.beginDate, false);
            }
        });
        cols.push({
            field: 'endDate', title: '结束时间', formatter: function (value, row, index) {
                return formatDate(row.endDate, false);
            }
        });
        cols.push({field: 'amount', title: '金额'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="查看月度" onclick="selfSupport.editForecast(' + index + ');" ><i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_forecast_year_list").bootstrapTable('destroy');
        TableInit("tb_forecast_year_list", "${pageContext.request.contextPath}/income/getForecastYearList", cols, {
            forecastId: forecastId
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }



    //计算金额
    selfSupport.computeMoney = function () {
        var unitPrice = $("#frm_history").find('[name=unitPrice]').val();
        var number = $("#frm_history").find('[name=number]').val();
        if (unitPrice && number) {
            var amountMoney = parseFloat(unitPrice) * parseFloat(number);
            $("#frm_history").find('[name=amountMoney]').val(amountMoney.toFixed(2));
        }
    }
</script>



