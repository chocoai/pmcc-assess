<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-11-7
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_title">
    <h3>历史数据</h3>
    <div class="clearfix"></div>
</div>
<div class="x_content">
    <div class="form-horizontal">
        <div class="form-group ">
            <label class="col-sm-1 control-label">
                范围
            </label>
            <div class="col-sm-2">
                <select class="form-control" onchange="forecastRestaurant.loadHistoryList(0,$(this).val())">
                    <option value="">全部</option>
                    <option value="1">预测</option>
                    <option value="0">非预测</option>
                </select>
            </div>
            <div class="col-sm-4">
                <div class="btn-group">
                    <button class="btn btn-success" data-toggle="modal"
                            onclick="forecastRestaurant.addHistory(0);">
                        新增
                    </button>
                </div>
                <div class="btn-group">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                            aria-expanded="false">
                        导入数据
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
                <div class="btn-group">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                            aria-expanded="false">
                        添加到预测
                        <span class="caret"></span>
                        <span class="sr-only">Toggle Dropdown</span>
                    </button>
                    <ul class="dropdown-menu" role="menu" id="ulForecastRestaurantAnalyseIncome"></ul>
                </div>
                <div class="btn-group">
                    <button class="btn btn-primary" data-toggle="modal"
                            onclick="forecastRestaurant.forecastToHistory(0);">
                        取消预测
                    </button>
                </div>
                <div class="btn-group">
                    <button class="btn btn-primary" data-toggle="modal"
                            onclick="forecastRestaurant.startAnalyse(0);">
                        开始分析
                    </button>
                </div>
            </div>
        </div>
    </div>
    <table class="table table-bordered" id="tb_history_restaurant_income_list">
    </table>
</div>
<div class="x_title">
    <h3>预测分析数据</h3>
    <div class="clearfix"></div>
</div>
<div class="x_content">
    <table class="table table-bordered" id="tb_forecast_restaurant_income_analyse_list">
    </table>
</div>

<div id="modal_history_restaurant" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">历史数据</h3>
            </div>
            <form id="frm_history_restaurant_restaurant" class="form-horizontal">
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
                                                    required="required"></select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            一级编号<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="firstLevelNumber" placeholder="一级编号"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            二级编号<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="secondLevelNumber" placeholder="二级编号"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            开始时间<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="month" placeholder="开始时间"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            结束时间<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="unit" placeholder="结束时间" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            单位<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="unit" placeholder="单位" class="form-control"
                                                   required="required">
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
                                            利用率<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="unitPrice" placeholder="利用率"
                                                   onblur="selfSupport.computeMoney();"
                                                   data-rule-number="true" class="form-control" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            利用率说明<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="unitPrice" placeholder="利用率说明"
                                                   onblur="selfSupport.computeMoney();"
                                                   data-rule-number="true" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            标价<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="unitPrice" placeholder="标价"
                                                   onblur="selfSupport.computeMoney();"
                                                   data-rule-number="true" class="form-control" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            标价说明<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="unitPrice" placeholder="标价说明"
                                                   onblur="selfSupport.computeMoney();"
                                                   data-rule-number="true" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            折扣率<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="unitPrice" placeholder="折扣率"
                                                   onblur="selfSupport.computeMoney();"
                                                   data-rule-number="true" class="form-control" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            折扣率说明<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="unitPrice" placeholder="折扣率说明"
                                                   onblur="selfSupport.computeMoney();"
                                                   data-rule-number="true" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            执行价<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="amountMoney" placeholder="执行价"
                                                   data-rule-number="true" class="form-control" required="required">
                                        </div>
                                    </div>
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
                            onclick="forecastRestaurant.saveHistory();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    var forecastRestaurant = {};

    //获取list id
    forecastRestaurant.getHistoryListId = function (type) {
        if (type == 0) return "tb_history_restaurant_income_list";
        if (type == 1) return "tb_history_restaurant_cost_list";
    }

    //获取预测分析list id
    forecastRestaurant.getForecastAnalyseListId = function (type) {
        if (type == 0) return "tb_forecast_restaurant_income_analyse_list";
        if (type == 1) return "tb_forecast_restaurant_cost_analyse_list";
    }

    //获取list id
    forecastRestaurant.getForecastListId = function (type) {
        if (type == 0) return "tb_forecast_income_list";
        if (type == 1) return "tb_forecast_cost_list";
    }

    //添加自营金额列表信息
    forecastRestaurant.addHistory = function (type, frm, modal) {
        var frm_id = frm == undefined ? 'frm_history_restaurant' : frm;
        var modal_id = modal == undefined ? 'modal_history_restaurant' : modal;
        $("#" + frm_id).clearAll();
        $("#" + frm_id).find('[name=firstLevelNumber]').empty();
        $("#" + frm_id).find('[name=secondLevelNumber]').empty();
        AssessCommon.loadDataDicByKey(forecastRestaurant.getHistoryTypeKey(type), "", function (html, data) {
            $("#" + modal_id).find('[name=accountingSubject]').empty().append(html);
        });
        $("#" + frm_id).find('[name=id]').val(0);
        $("#" + frm_id).find('[name=type]').val(type);
        $("#" + modal_id).modal();
    }

    //编辑历史信息
    forecastRestaurant.editHistory = function (index, type) {
        var row = $("#" + forecastRestaurant.getHistoryListId(type)).bootstrapTable('getData')[index];
        $("#frm_history_restaurant").clearAll();
        $("#frm_history_restaurant").initForm(row);
        AssessCommon.loadDataDicByKey(forecastRestaurant.getHistoryTypeKey(row.type), row.accountingSubject, function (html, data) {
            $("#frm_history_restaurant").find('[name=accountingSubject]').empty().append(html).trigger('change');
        })
        $('#modal_history').modal();
    }

    //计算金额
    forecastRestaurant.computeMoney = function (frm) {
        var frm_id = frm == undefined ? 'frm_history_restaurant' : frm;
        var unitPrice = $("#" + frm_id).find('[name=unitPrice]').val();
        var number = $("#" + frm_id).find('[name=number]').val();
        if (unitPrice && number) {
            var amountMoney = parseFloat(unitPrice) * parseFloat(number);
            $("#" + frm_id).find('[name=amountMoney]').val(amountMoney.toFixed(2));
        }
    }

    //获取类型key
    forecastRestaurant.getHistoryTypeKey = function (type) {
        if (type == 0)
            return AssessDicKey.mdIncomeHistoryTypeIncome;
        if (type == 1)
            return AssessDicKey.mdIncomeHistoryTypeCost;
    }

    //加载历史列表信息
    forecastRestaurant.loadHistoryList = function (type, bisForecast) {
        var cols = [];
        if (incomeIndex.getFormType() == 0) {
            cols.push({field: 'year', title: '年度'});
            cols.push({field: 'month', title: '月度'});
            cols.push({
                field: 'accountingSubjectName', title: '会计科目', formatter: function (value, row, index) {
                    if (row.bisForecast) {
                        value += '<i class="green fa fa-tag"></i>';
                    }
                    return value;
                }
            });
            cols.push({field: 'firstLevelNumber', title: '一级编号'});
            cols.push({field: 'secondLevelNumber', title: '二级编号'});
            cols.push({field: 'unit', title: '单位'});
            cols.push({field: 'unitPrice', title: '单价'});
            cols.push({field: 'number', title: '数量'});
            cols.push({field: 'amountMoney', title: '金额'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑" onclick="forecastRestaurant.editHistory(' + index + ',' + type + ');" ><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="forecastRestaurant.delHistory(' + row.id + ',' + type + ')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
        }
        $("#" + forecastRestaurant.getHistoryListId(type)).bootstrapTable('destroy');
        var queryParam = {
            type: type,
            incomeId: incomeIndex.getInComeId()
        };
        if (bisForecast) {
            queryParam.bisForecast = bisForecast;
        }
        TableInit(forecastRestaurant.getHistoryListId(type), "${pageContext.request.contextPath}/income/getHistoryList", cols, queryParam, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
                setTimeout(function () {
                    $.ajax({
                        url: '${pageContext.request.contextPath}/income/getForecastAnalyseList',
                        type: 'get',
                        data: {
                            type: type,
                            incomeId: incomeIndex.getInComeId()
                        },
                        success: function (result) {
                            var html = '';
                            $.each(result.rows, function (i, item) {
                                html += '<li><a href="javascript://" onclick="forecastRestaurant.historyToForecast(' + type + ',' + item.id + ');">' + item.year + '</a></li>';
                            })
                            var elementId = type == 0 ? "ulForecastRestaurantAnalyseIncome" : "ulForecastRestaurantAnalyseCost";
                            $("#" + elementId).empty().html(html);
                        }
                    })
                }, 500);
            }
        }, true);
    }

    //删除历史信息
    forecastRestaurant.delHistory = function (id, type) {
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
                        forecastRestaurant.loadHistoryList(type);
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
    forecastRestaurant.saveHistory = function () {
        if (!$("#frm_history_restaurant").valid()) {
            return false;
        }
        var data = formParams("frm_history_restaurant");
        data.incomeId = incomeIndex.getInComeId();
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
                    var type = $("#frm_history_restaurant").find('[name=type]').val();
                    forecastRestaurant.loadHistoryList(type);
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
    forecastRestaurant.importHistory = function (_this) {
        Loading.progressShow();
        var type = $(_this).attr('data-type');
        $.ajaxFileUpload({
            type: "POST",
            url: "${pageContext.request.contextPath}/income/importHistory",
            data: {
                incomeId: incomeIndex.getInComeId(),
                type: type
            },//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: 'ajaxFileUpload',//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert(result.data.replace(/\n/g, '<br/>'));
                    forecastRestaurant.loadHistoryList(type);
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

    //历史数据添加到预测数据
    forecastRestaurant.historyToForecast = function (type, forecastAnalyseId) {
        var rows = $('#' + forecastRestaurant.getHistoryListId(type)).bootstrapTable('getSelections');
        if (rows && rows.length > 0) {
            var idArray = [];
            $.each(rows, function (i, item) {
                idArray.push(item.id);
            })
            $.ajax({
                url: '${pageContext.request.contextPath}/income/historyToForecast',
                data: {
                    forecastAnalyseId: forecastAnalyseId,
                    ids: idArray.join()
                },
                success: function (result) {
                    if (result.ret) {
                        toastr.success("添加成功");
                        $('#' + forecastRestaurant.getHistoryListId(type)).bootstrapTable('refresh');
                        $('#' + forecastRestaurant.getForecastAnalyseListId(type)).bootstrapTable('refresh');
                    }
                }
            })
        } else {
            toastr.info('请选择要添加的数据');
        }
    }

    //取消预测还原为历史数据
    forecastRestaurant.forecastToHistory = function (type) {
        var rows = $('#' + forecastRestaurant.getHistoryListId(type)).bootstrapTable('getSelections');
        if (rows && rows.length > 0) {
            var idArray = [];
            $.each(rows, function (i, item) {
                idArray.push(item.id);
            })
            $.ajax({
                url: '${pageContext.request.contextPath}/income/forecastToHistory',
                data: {
                    ids: idArray.join(),
                    incomeId: incomeIndex.getInComeId(),
                    type: 0
                }, success: function (result) {
                    if (result.ret) {
                        toastr.success("取消成功");
                        $table.bootstrapTable('refresh');
                    }
                }
            })
        } else {
            toastr.info('请选择要取消的数据');
        }
    }

    //开始分析
    forecastRestaurant.startAnalyse = function (type) {
        Loading.progressShow();
        $.ajax({
            url: '${pageContext.request.contextPath}/income/startAnalyse',
            data: {
                incomeId: incomeIndex.getInComeId(),
                type: type
            },
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('分析成功');
                    $("#" + forecastRestaurant.getForecastAnalyseListId(type)).bootstrapTable('refresh');
                }
            }
        })
    }

    //加载预测分析数据
    forecastRestaurant.loadForecastAnalyseList = function (type) {
        var cols = [];
        cols.push({field: 'year', title: '年份'});
        cols.push({field: 'amountMoney', title: '金额'});
        cols.push({field: 'quantitativeTrend', title: '数量趋势'});
        cols.push({field: 'univalentTrend', title: '单价趋势'});
        $("#" + forecastRestaurant.getForecastAnalyseListId(type)).bootstrapTable('destroy');
        TableInit(forecastRestaurant.getForecastAnalyseListId(type), "${pageContext.request.contextPath}/income/getForecastAnalyseList", cols, {
            type: type,
            bisParticipateIn: true,
            incomeId: incomeIndex.getInComeId()
        }, {
            showColumns: false,
            showRefresh: false,
            pagination: false,
            search: false,
            onLoadSuccess: function (data) {
                $(".tooltips").tooltip();
            }
        });
    }

</script>