<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">


<div id="divQuoteListBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 80%">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">房屋案例</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form id="frmCaseBaseHouse" class="form-horizontal">
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-md-1 col-form-label">省</label>
                            <div class="col-md-2 p-0">
                                <select id="province" name="province"
                                        class="form-control input-full search-select select2">
                                </select>
                            </div>
                            <label class="col-md-1 col-form-label">市</label>
                            <div class="col-md-2 p-0">
                                <select id="city" name="city"
                                        class="form-control input-full search-select select2">
                                </select>
                            </div>
                            <label class="col-md-1 col-form-label">区</label>
                            <div class="col-md-2 p-0">
                                <select id="district" name="district"
                                        class="form-control input-full search-select select2">
                                </select>
                            </div>
                            <button style="margin-left: 10px" class="btn btn-info  btn-sm"
                                    type="button"
                                    onclick="quoteHouseCase.loadDataList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                查询
                            </button>
                            <button style="margin-left: 10px" class="btn btn-info btn-sm"
                                    type="button"
                                    onclick="quoteHouseCase.clearQuery()">
                                <span class="btn-label"><i class="fa fa-undo-alt"></i></span>
                                重置
                            </button>
                            <button style="margin-left: 10px"
                                    class="btn btn-sm btn-info  btn-border"
                                    type="button"
                                    onclick="quoteHouseCase.showOtherQuery(this)">
                                                    <span class="btn-label">
												<i class="fa fa-plus-circle"></i>
											</span>
                                高级查询
                            </button>

                        </div>
                    </div>
                </div>
                <div id="showOtherQuery" style="display: none">
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-md-1 col-form-label">楼盘名称</label>
                                <div class="col-md-2 p-0">
                                    <input type="text" class="form-control input-full"
                                           name="fullName"
                                           placeholder="楼盘名称"/>
                                </div>
                                <label class="col-md-1 col-form-label">版块名称</label>
                                <div class="col-md-2 p-0">
                                    <input type="text" class="form-control input-full"
                                           name="blockName"
                                           placeholder="版块名称"/>
                                </div>
                                <label class="col-md-1 col-form-label">街道</label>
                                <div class="col-md-2 p-0">
                                    <input type="text" class="form-control input-full" name="street"
                                           placeholder="街道"/>
                                </div>
                                <label class="col-md-1 col-form-label">交易类型</label>
                                <div class="col-md-2 p-0">
                                    <select name="tradingType"
                                            class="form-control input-full search-select select2">
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-md-1 col-form-label">开始时间</label>
                                <div class="col-md-2 p-0">
                                    <input type="text"
                                           class="form-control input-full date-picker dbdate"
                                           data-date-format="yyyy-mm-dd" name="tradingTimeStart"
                                           placeholder="开始时间"/>
                                </div>
                                <label class="col-md-1 col-form-label">结束时间</label>
                                <div class="col-md-2 p-0">
                                    <input type="text"
                                           class="form-control input-full date-picker dbdate"
                                           data-date-format="yyyy-mm-dd" name="tradingTimeEnd"
                                           placeholder="结束时间"/>
                                </div>
                                <label class="col-md-1 col-form-label">面积(从)</label>
                                <div class="col-md-2 p-0">
                                    <input type="text" class="form-control input-full"
                                           placeholder="从"
                                           id="areaStart"
                                           data-rule-number="true" name="areaStart"/>
                                </div>
                                <label class="col-md-1 col-form-label">面积(到)</label>
                                <div class="col-md-2 p-0">
                                    <input type="text" class="form-control input-full"
                                           placeholder="到"
                                           id="areaEnd"
                                           data-rule-number="true" name="areaEnd"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-md-1 col-form-label">用途</label>
                                <div class="col-md-2 p-0">
                                    <input type="text" class="form-control input-full"
                                           name="houseType"
                                           placeholder="用途"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </form>
                <div class="row form-group ">
                    <div class="col-md-12">
                        <table class="table table-bordered" id="tbCaseBaseHouseList">
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>

        </div>
    </div>
</div>


<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">详细信息</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmCaseHouse" class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 col-form-label">
                                                省
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full" name="provinceName"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                市
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full" name="cityName"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                区
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full" name="districtName"></label>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 col-form-label">
                                                房产类型
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full" name="houseType"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                房产类别
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full" name="houseCategory"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                街道
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full" name="street"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 col-form-label">
                                                面积
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full" name="area"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                楼盘名称
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full" name="estateName"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                交易方式
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full" name="dealTypeName"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 col-form-label">
                                                成交价
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full" name="currentPrice"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                评估价
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full" name="consultPrice"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                评估基准日期
                                            </label>
                                            <div class="col-sm-3">
                                                <input name="assessStandardDate" data-date-format="yyyy-mm-dd"
                                                       class="form-control input-full date-picker dbdate">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 col-form-label">
                                                单价
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full" name="tradingUnitPrice"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                变现率
                                            </label>
                                            <div class="col-sm-3">
                                                <input class="form-control input-full" id="realizationRatios"
                                                       readonly></input>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                变现周期
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full" name="realizationCycle"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                成交对象概况
                                            </label>
                                            <div class="col-sm-11">
                                                <label class="form-control input-full" name="dealPartInfo"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                附件
                                            </label>
                                            <div class="col-sm-11">
                                                <div id="_uploadHouseFile"></div>
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
            </div>

        </div>
    </div>
</div>
</html>
<script type="text/javascript">
    var quoteHouseCase = {};
    var houseFrm = $("#frmCaseBaseHouse");

    quoteHouseCase.openCaseListBox = function(canQuote){
        houseFrm.clearAll();
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseTransactionType, '', function (html, data) {
            houseFrm.find("select[name='tradingType']").empty().html(html).trigger('change');
        });
        AssessCommon.initAreaInfo({
            useDefaultText: false,
            provinceTarget: $("#province"),
            cityTarget: $("#city"),
            districtTarget: $("#district"),

        });
        quoteHouseCase.loadDataList(canQuote);
        $("#divQuoteListBox").modal("show");
    };

    quoteHouseCase.findHouse = function (id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/basic/geBasicFormClassifyParamDto",
            type: "get",
            dataType: "json",
            data: {
                detailId: id
            },
            success: function (result) {
                if (result.ret) {
                    var url = '${pageContext.request.contextPath}/basicApplyBatch/informationDetail?';
                    url += 'applyBatchId=' + result.data.applyBatchId;
                    url += '&formClassify=' + result.data.formClassify;
                    url += '&formType=' + result.data.formType;
                    url += '&tbId=' + result.data.tbId;
                    url += '&tbType=' + result.data.tbType;
                    window.open(url, "");
                }
                else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })


    };

    //清空查询条件
    quoteHouseCase.clearQuery = function () {
        houseFrm.find('input:text').val('');
        houseFrm.find('.select2').select2('val', '').trigger('change');
    };
    
    quoteHouseCase.showOtherQuery = function(_that) {
        $("#showOtherQuery").toggle();
        var c = $(_that).find("i").attr("class");
        if (c.indexOf("plus") != -1) {
            $(_that).find("i").attr("class", "fa fa-minus-circle");
        } else {
            $(_that).find("i").attr("class", "fa fa-plus-circle");
        }

    }

    quoteHouseCase.loadDataList = function (canQuote) {
        var cols = [];
        cols.push({
            field: 'name', title: '名称', formatter: function (value, row, index) {
                var s = row.fullName;
                if (row.creatorName) {
                    s += "<span style='margin-left: 5px;' class='label label-info'>" + row.creatorName + "</span>"
                }
                if (row.approverName) {
                    s += "<span style='margin-left: 5px;' class='label label-danger'>" + row.approverName + "</span>"
                }
                return s;
            }
        });
        cols.push({
            field: 'blockName', title: '版块名称', formatter: function (value, row, index) {
                var s = AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
                if (row.blockName) {
                    s += "/" + row.blockName;
                }
                return s;
            }
        });
        cols.push({field: 'street', title: '街道'});
        cols.push({field: 'houseType', title: '用途'});
        cols.push({field: 'tradingTypeName', title: '交易类型'});
        cols.push({
            field: 'tradingTime', title: '交易时间', formatter: function (value, row, index) {
                return formatDate(value);
            }
        });
        cols.push({field: 'tradingUnitPrice', title: '交易单价'});
        cols.push({field: 'area', title: '面积'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                if (row.bisFromSelf) {
                    str += '<button type="button" onclick="quoteHouseCase.findHouse(' + row.caseHouseId + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看">';
                    str += '<i class="fa fa-search"></i>';
                    str += '</button>';
                    if(true == canQuote){
                        str += '<button type="button" class="btn btn-xs btn-warning tooltips" style="margin-left: 5px;"  data-placement="top" data-original-title="引用" onclick="quoteHouseCase.quote(' + row.id + ')"><i class="fa fa-check"></i></button>';
                    }
                } else {
                    str += '<button type="button" onclick="quoteHouseCase.showItem(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看">';
                    str += '<i class="fa fa-search"></i>';
                    str += '</button>';
                }
                str += '</div>';
                return str;
            }
        });
        $("#tbCaseBaseHouseList").bootstrapTable('destroy');
        TableInit("tbCaseBaseHouseList", "${pageContext.request.contextPath}/basic/getBootstrapTableCaseBaseHouseVo", cols,
            formSerializeArray(houseFrm), {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
    };

    quoteHouseCase.showItem = function (id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/basic/getDataById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    $("#frmCaseHouse").initForm(result.data);
                    $("#frmCaseHouse").find("input[name='assessStandardDate']").attr("disabled", true);
                    $("#realizationRatios").val(AssessCommon.pointToPercent(result.data.realizationRatios));
                    quoteHouseCase.showFile(id);
                    $("#divBoxFather").modal("show");
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    };

    quoteHouseCase.showFile = function (id) {
        FileUtils.getFileShows({
            target: "uploadHouseFile",
            formData: {
                tableName: "tb_basic_house_case_summary",
                tableId: id
            },
            deleteFlag: true
        })
    };

    //引用
    quoteHouseCase.quote = function (id) {
        notifyInfo('提示', '请耐心等待....');
        Loading.progressShow();
        $.ajax({
            url: '${pageContext.request.contextPath}/basicApplyBatch/deleteBatchAllById',
            type: 'post',
            data: {
                applyBatchId: $("#basicBatchApplyFrm").find('[name=id]').val(),
            },
            dataType: 'json',
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    var data = {};
                    data.id = id;
                    data.projectId = "${projectInfo.id}";
                    data.planDetailsId = "${projectPlanDetails.id}";
                    $.ajax({
                        url: '${pageContext.request.contextPath}/basic/quoteHouseCase',
                        type: 'post',
                        data: data,
                        dataType: 'json',
                        success: function (result) {
                            if (result.ret) {
                                AlertSuccess('成功', '引用成功', function () {
                                    window.location.href = window.location.href;
                                })
                            } else {
                                AlertError('失败', '引用失败');
                            }
                        }
                    })
                }
            }
        })
    }
</script>
