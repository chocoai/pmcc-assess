<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-11-7
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_title">
    历史或调查数据
</div>
<div class="x_content">
    <div class="form-horizontal">
        <div class="row form-group ">
            <div class="col-md-12">
            <div class="form-inline x-valid">
            <label class="col-sm-1 control-label">
                范围
            </label>
            <div class="col-sm-1">
                <select class="form-control input-full" name="bisForecast">
                    <option value="">全部</option>
                    <option value="1">预测</option>
                    <option value="0">非预测</option>
                </select>
            </div>
            <label class="col-sm-1 control-label">
                年度
            </label>
            <div class="col-sm-1">
                <input type="text" name="year" class="form-control input-full">
            </div>
            <label class="col-sm-1 control-label">
                二级编号
            </label>
            <div class="col-sm-1">
                <input type="text" name="secondLevelNumber" class="form-control input-full">
            </div>
            <div class="col-sm-6">
                <div class="btn-group">
                    <button type="button" class="btn btn-info btn-sm" onclick="selfSupportForecast.loadHistoryList(0,this);">
                        查询
                    </button>
                    <button type="button" class="btn btn-success btn-sm" data-toggle="modal"
                            onclick="selfSupportForecast.addHistory(0);">
                        新增
                    </button>
                    <button type="button" class="btn btn-warning btn-sm" onclick="selfSupportForecast.delHistoryBatch(0);">
                        删除
                    </button>
                </div>
                <div class="dropdown" style="display: inline;margin-left: 5px;">
                    <button type="button" class="btn btn-primary dropdown-toggle btn-sm" data-toggle="dropdown"
                            aria-expanded="false">
                        导入数据
                    </button>
                    <div class="dropdown-menu" role="menu">
                        <a href="javascript://" class="dropdown-item"
                           onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftMethodIncomeHistory);">下载模板</a>
                        <a href="javascript://;" class="dropdown-item"
                           onclick="$('#ajaxFileUpload').val('').attr('data-type',0).trigger('click');">导入数据</a>
                    </div>
                </div>
                <div class="dropdown" style="display: inline;margin-left: 5px;">
                    <button type="button" class="btn btn-primary dropdown-toggle btn-sm" data-toggle="dropdown"
                            aria-expanded="false">
                        添加到预测
                    </button>
                    <div class="dropdown-menu" role="menu" id="ulForecastAnalyseIncome"></div>
                </div>
                <div class="btn-group">
                    <button class="btn btn-primary btn-sm" data-toggle="modal"
                            onclick="selfSupportForecast.forecastToHistory(0);">
                        取消预测
                    </button>
                </div>
                <div class="btn-group">
                    <button class="btn btn-primary btn-sm" data-toggle="modal"
                            onclick="selfSupportForecast.startAnalyse(0);">
                        开始分析
                    </button>
                </div>
            </div>
            </div>
            </div>
        </div>
    </div>
    <table class="table table-bordered" id="tb_history_income_list">
    </table>
</div>
<div class="x_title">
    历史数据分析
</div>
<div class="x_content">
    <table class="table table-bordered" id="tb_forecast_income_analyse_list">
    </table>
</div>

<div id="modal_history" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">历史或调查数据</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frm_history" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="type">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            类型<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <select name="sourceType" class="form-control input-full" required>
                                                <option value="历史">历史</option>
                                                <option value="调查">调查</option>
                                            </select>
                                        </div>
                                        <label class="col-sm-2 control-label">
                                            会计科目<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <select name="accountingSubject" class="form-control input-full"
                                                    required="required"></select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            年度<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="year" placeholder="年度"
                                                   data-rule-digits="true" class="form-control input-full date-year"
                                                   required="required">
                                        </div>
                                        <label class="col-sm-2 control-label">
                                            月度
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="month" placeholder="月度" class="form-control input-full" >
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            一级编号
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="firstLevelNumber" placeholder="一级编号" class="form-control input-full" >
                                        </div>
                                        <label class="col-sm-2 control-label">
                                            二级编号
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="secondLevelNumber" placeholder="二级编号" class="form-control input-full" >
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            单位
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="unit" placeholder="单位" class="form-control input-full">
                                        </div>
                                        <label class="col-sm-2 control-label">
                                            单价
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="unitPrice" placeholder="单价" onblur="selfSupportForecast.computeMoney();" data-rule-number="true" class="form-control input-full" >
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            数量
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="number" placeholder="数量" onblur="selfSupportForecast.computeMoney();" data-rule-digits="true" class="form-control input-full" >
                                        </div>
                                        <label class="col-sm-2 control-label">
                                            金额<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="amountMoney" placeholder="金额"
                                                   data-rule-number="true" class="form-control input-full" required="required">
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            房屋折旧与使用费
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" name="deprecitionRoyalty" placeholder="房屋折旧与使用费" class="form-control input-full">
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
                <button type="button" class="btn btn-primary btn-sm" onclick="selfSupportForecast.saveHistory()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<div id="divBoxAnalyseItemData" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">明细</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="col-md-12">
                                        <table class="table table-bordered" id="analyseItemList">
                                            <!-- cerare document add ajax data-->
                                        </table>
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
            </div>

        </div>
    </div>
</div>



<script type="text/javascript">
    $(function () {
        DatepickerUtils.initDate($('.date-year'), {
            format: 'yyyy',
            startView: 4,
            minView: 4
        });
    })


    var selfSupportForecast = {};

    //获取list id
    selfSupportForecast.getHistoryListId = function (type) {
        if (type == 0)
            return "tb_history_income_list";
        if (type == 1)
            return "tb_history_cost_list";
    }

    //获取预测分析list id
    selfSupportForecast.getForecastAnalyseListId = function (type) {
        if (type == 0)
            return "tb_forecast_income_analyse_list";
        if (type == 1)
            return "tb_forecast_cost_analyse_list";
    }

    //获取list id
    selfSupportForecast.getForecastListId = function (type) {
        if (type == 0)
            return "tb_forecast_income_list";
        if (type == 1)
            return "tb_forecast_cost_list";
    }

    //添加自营金额列表信息
    selfSupportForecast.addHistory = function (type, frm, modal) {
        var frm_id = frm == undefined ? 'frm_history' : frm;
        var modal_id = modal == undefined ? 'modal_history' : modal;
        $("#" + frm_id).clearAll();
        $("#" + frm_id).find('[name=firstLevelNumber]').empty();
        $("#" + frm_id).find('[name=secondLevelNumber]').empty();
        AssessCommon.loadDataDicByKey(selfSupportForecast.getHistoryTypeKey(type), "", function (html, data) {
            $("#" + modal_id).find('[name=accountingSubject]').empty().append(html);
        });
        $("#" + frm_id).find('[name=id]').val(0);
        $("#" + frm_id).find('[name=type]').val(type);
        $("#" + modal_id).modal();
    }

    //编辑历史信息
    selfSupportForecast.editHistory = function (index, type) {
        var row = $("#" + selfSupportForecast.getHistoryListId(type)).bootstrapTable('getData')[index];
        $("#frm_history").clearAll();
        $("#frm_history").initForm(row);
        AssessCommon.loadDataDicByKey(selfSupportForecast.getHistoryTypeKey(row.type), row.accountingSubject, function (html, data) {
            $("#frm_history").find('[name=accountingSubject]').empty().append(html).trigger('change');
        })
        $('#modal_history').modal();
    }

    //计算金额
    selfSupportForecast.computeMoney = function (frm) {
        var frm_id = frm == undefined ? 'frm_history' : frm;
        var unitPrice = $("#" + frm_id).find('[name=unitPrice]').val();
        var number = $("#" + frm_id).find('[name=number]').val();
        if (unitPrice && number) {
            var amountMoney = parseFloat(unitPrice) * parseFloat(number);
            $("#" + frm_id).find('[name=amountMoney]').val(amountMoney.toFixed(2));
        }
    }

    //获取类型key
    selfSupportForecast.getHistoryTypeKey = function (type) {
        if (type == 0)
            return AssessDicKey.mdIncomeHistoryTypeIncome;
        if (type == 1)
            return AssessDicKey.mdIncomeHistoryTypeCost;
    }

    //加载历史列表信息
    selfSupportForecast.loadHistoryList = function (type, _this) {
        var cols = [];
        cols.push({field: 'year', title: '年度'});
        cols.push({field: 'month', title: '月度'});
        cols.push({field: 'sourceType', title: '类型'});
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
        cols.push({field: 'deprecitionRoyalty', title: '房屋折旧与使用费'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                //str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑" onclick="selfSupportForecast.editHistory(' + index + ',' + type + ');" ><i class="fa fa-edit fa-white"></i></a>';
                str += '<button type="button" onclick="selfSupportForecast.editHistory(' + index + ',' + type + ');"  style="margin-left: 5px;"  class="btn   btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button type="button" onclick="selfSupportForecast.delHistory(' + row.id + ',' + type + ')"  style="margin-left: 5px;"  class="btn   btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
                //str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="selfSupportForecast.delHistory(' + row.id + ',' + type + ')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#" + selfSupportForecast.getHistoryListId(type)).bootstrapTable('destroy');
        var queryParam = {
            type: type,
            formType: incomeIndex.getFormType(),
            incomeId: incomeIndex.getInComeId(),
            bisForecast:$(_this).closest('.form-horizontal').find('[name=bisForecast]').val(),
            year:$(_this).closest('.form-horizontal').find('[name=year]').val(),
            secondLevelNumber:$(_this).closest('.form-horizontal').find('[name=secondLevelNumber]').val()
        };
        TableInit(selfSupportForecast.getHistoryListId(type), "${pageContext.request.contextPath}/income/getHistoryList", cols, queryParam, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
                setTimeout(function () {
                    $.ajax({
                        url: '${pageContext.request.contextPath}/income/getAnalyseCountByYear',
                        type: 'get',
                        data: {
                            type: type,
                            formType: incomeIndex.getFormType(),
                            incomeId: incomeIndex.getInComeId()
                        },
                        success: function (result) {
                            var html = '';
                            $.each(result.data, function (i, item) {
                                html += '<a href="javascript://" class="dropdown-item" onclick="selfSupportForecast.historyToForecast(' + type + ',' + item + ');">' + item + '</a>';
                            })
                            var elementId = type == 0 ? "ulForecastAnalyseIncome" : "ulForecastAnalyseCost";
                            $("#" + elementId).empty().html(html);
                        }
                    })
                }, 500);
            }
        }, true);
    }

    //删除历史信息
    selfSupportForecast.delHistoryBatch = function (type) {
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            var rows = $("#" + selfSupportForecast.getHistoryListId(type)).bootstrapTable('getSelections');
            if (rows && rows.length > 0) {
                var arrayId = [];
                $.each(rows,function (i,row) {
                    arrayId.push(row.id);
                })
                selfSupportForecast.delHistory(arrayId.join(),type);
            } else {
                notifyInfo('提示',"请选择需删除的数据");
            }
        })
    }

    //删除历史信息
    selfSupportForecast.delHistory = function (ids, type) {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/income/deleteHistory",
            type: "post",
            dataType: "json",
            data: {ids: ids},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    notifySuccess("成功",'删除成功');
                    selfSupportForecast.loadHistoryList(type);
                }
                else {
                    AlertError("失败","删除数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败","调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    //保存历史信息
    selfSupportForecast.saveHistory = function () {
        if (!$("#frm_history").valid()) {
            return false;
        }
        var data = formParams("frm_history");
        data.incomeId = incomeIndex.getInComeId();
        data.formType = incomeIndex.getFormType();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/income/saveHistory",
            type: "post",
            dataType: "json",
            data: {formData: JSON.stringify(data)},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    notifySuccess("成功",'保存成功');
                    var type = $("#frm_history").find('[name=type]').val();
                    selfSupportForecast.loadHistoryList(type);
                    $('#modal_history').modal('hide');
                }
                else {
                    AlertError("失败","保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败","调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    //导入历史数据
    selfSupportForecast.importHistory = function (_this) {
        Loading.progressShow();
        var type = $(_this).attr('data-type');
        $.ajaxFileUpload({
            type: "POST",
            url: "${pageContext.request.contextPath}/income/importHistory",
            data: {
                incomeId: incomeIndex.getInComeId(),
                formType: incomeIndex.getFormType(),
                type: type
            },//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: 'ajaxFileUpload',//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    notifyInfo("成功",result.data);
                    selfSupportForecast.loadHistoryList(type);
                } else {
                    AlertError("失败","导入数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result, status, e) {
                Loading.progressHide();
                AlertError("失败","调用服务端方法失败，失败原因:" + result);
            }
        });
    }

    //历史数据添加到预测数据
    selfSupportForecast.historyToForecast = function (type, year) {
        var rows = $('#' + selfSupportForecast.getHistoryListId(type)).bootstrapTable('getSelections');
        if (rows && rows.length > 0) {
            var idArray = [];
            $.each(rows, function (i, item) {
                idArray.push(item.id);
            })
            $.ajax({
                url: '${pageContext.request.contextPath}/income/historyToForecast',
                data: {
                    year: year,
                    formType: incomeIndex.getFormType(),
                    ids: idArray.join()
                },
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功","添加成功");
                        $('#' + selfSupportForecast.getHistoryListId(type)).bootstrapTable('refresh');
                        $('#' + selfSupportForecast.getForecastAnalyseListId(type)).bootstrapTable('refresh');
                    }
                }
            })
        } else {
            notifyInfo('提示','请选择要添加的数据');
        }
    }

    //取消预测还原为历史数据
    selfSupportForecast.forecastToHistory = function (type) {
        var rows = $('#' + selfSupportForecast.getHistoryListId(type)).bootstrapTable('getSelections');
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
                    formType: incomeIndex.getFormType(),
                    type: type
                }, success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功","取消成功");
                        $('#' + selfSupportForecast.getHistoryListId(type)).bootstrapTable('refresh');
                        $('#' + selfSupportForecast.getForecastAnalyseListId(type)).bootstrapTable('refresh');
                    }
                }
            })
        } else {
            notifyInfo('提示','请选择要取消的数据');
        }
    }

    //开始分析
    selfSupportForecast.startAnalyse = function (type) {
        Loading.progressShow();
        $.ajax({
            url: '${pageContext.request.contextPath}/income/startAnalyse',
            data: {
                incomeId: incomeIndex.getInComeId(),
                formType: incomeIndex.getFormType(),
                type: type
            },
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    notifySuccess("成功",'分析成功');
                    $("#" + selfSupportForecast.getForecastAnalyseListId(type)).bootstrapTable('refresh');
                }
            }
        })
    }

    //加载历史数据分析
    selfSupportForecast.loadForecastAnalyseList = function (type) {
        var cols = [];
        cols.push({field: 'year', title: '年份'});
        cols.push({field: 'sourceType', title: '类型'});
        cols.push({field: 'amountMoney', title: '金额'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                if(row.amountMoney != null){
                    var str = '<div class="btn-margin">';
                    //str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="查看明细" onclick="selfSupportForecast.showItemData(' + row.id  + ')">查看明细</a>';
                    str += '<button onclick="selfSupportForecast.showItemData(' + row.id  + ')" style="margin-left: 5px;" class="btn   btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看明细">';
                    str += '<i class="fa fa-search"></i>';
                    str += '</button>';
                    str += '</div>';
                    return str;
                }
            }
        });
        $("#" + selfSupportForecast.getForecastAnalyseListId(type)).bootstrapTable('destroy');
        TableInit(selfSupportForecast.getForecastAnalyseListId(type), "${pageContext.request.contextPath}/income/getForecastAnalyseList", cols, {
            type: type,
            formType: incomeIndex.getFormType(),
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

    selfSupportForecast.showItemData = function (id) {
        selfSupportForecast.loadForecastAnalyseItemList(id);
        $('#divBoxAnalyseItemData').modal("show");
    }

    //加载预测分析明细
    selfSupportForecast.loadForecastAnalyseItemList = function (id) {
        var cols = [];
        cols.push({field: 'name', title: '会计科目/一级编号/二级编号'});
        cols.push({field: 'amountMoney', title: '金额'});
        cols.push({field: 'number', title: '数量'});
        cols.push({field: 'moneyTrend', title: '金额趋势'});
        cols.push({field: 'quantitativeTrend', title: '数量趋势'});
        cols.push({field: 'unitPriceTrend', title: '单价趋势'});
        $("#analyseItemList").bootstrapTable('destroy');
        TableInit("analyseItemList", "${pageContext.request.contextPath}/income/getForecastAnalyseItemList", cols, {
            forecastAnalyseId: id,
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


    //加载历史数据分析
    selfSupportForecast.loadCostForecastAnalyseList = function (type) {
        var cols = [];
        cols.push({field: 'year', title: '年份'});
        cols.push({field: 'sourceType', title: '类型'});
        cols.push({field: 'amountMoney', title: '成本金额'});
        cols.push({
            field: 'costRatio', title: '成本比率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({field: 'earnedProfit', title: '经营利润'});
        cols.push({
            field: 'earnedProfitRatio', title: '经营利润比率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({
            field: 'operatingExpensesRatio', title: '经营费用比率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({
            field: 'operatingTaxRatio', title: '税金及附加比率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({
            field: 'managementCostRatio', title: '管理费用比率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        cols.push({
            field: 'financialCostRatio', title: '财务费用比率', formatter: function (value, row, index) {
                return AssessCommon.pointToPercent(value);
            }
        });
        $("#tb_forecast_cost_analyse_list").bootstrapTable('destroy');
        TableInit("tb_forecast_cost_analyse_list", "${pageContext.request.contextPath}/income/getForecastAnalyseList", cols, {
            type: type,
            formType: incomeIndex.getFormType(),
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
