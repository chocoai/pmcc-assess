<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>
            <small>
                房屋基本信息
            </small>
        </h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="basicHouseFrm">
        <div class="saveView" style="display: none">
            <%@include file="/views/basic/modelView/houseSave.jsp" %>
        </div>

        <div class="detailView" style="display: none">
            <%@include file="/views/basic/modelView/houseDetail.jsp" %>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋平面图<span class="symbol required"></span></label>
                <div class="col-sm-5">
                    <input id="house_img_plan"
                           required="required" placeholder="上传附件" class="form-control" type="file">
                    <div id="_house_img_plan"></div>
                </div>
            </div>
        </div>
    </form>
</div>

<div class="x_content">
    <div class="x_title">
        <h3>
            <small>
                房屋交易信息
            </small>
        </h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="basicTradingFrm">
        <div class="saveView" style="display: none">
            <%@include file="/views/basic/modelView/houseTradingSave.jsp" %>
        </div>

        <div class="detailView" style="display: none">
            <%@include file="/views/basic/modelView/houseTradingDetail.jsp" %>
        </div>
    </form>
</div>
<%@include file="/views/basic/modelView/house/sonHouseView.jsp" %>
<script type="text/javascript">

    var houseModelFun = new Object();

    houseModelFun.config = {
        house: {
            frm: "basicHouseFrm",
            houseFileId: "house_img_plan"
        },
        trading: {
            frm: "basicTradingFrm",
            leaseID: "BasicHouseTradingLease",//房屋出租
            sellID: "BasicHouseTradingSell",//房屋出售
            totalSale: "totalSale",//出售总额
            divBoxSon: "divBoxTradingLeaseAndSell",
            tableSon: "tableTradingLeaseAndSell",
            frmSon: "frmTradingLeaseAndSell"
        }
    };

    /**
     * 判断字符串以及null等
     */
    houseModelFun.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    /**
     * 判断对象 属性
     */
    houseModelFun.isNotBlankObjectProperty = function (obj) {
        for (var key in obj) {
            if (this.isNotBlank(obj[key])) {
                return true;
            }
        }
        return false
    };

    /**
     * 判断对象
     */
    houseModelFun.isNotBlankObject = function (obj) {
        for (var key in obj) {
            return true;
        }
        return false
    };

    houseModelFun.uploadFile = function (fieldsName, table, id) {
        FileUtils.uploadFiles({
            target: fieldsName,
            disabledTarget: "btn_submit",
            formData: {
                fieldsName: fieldsName,
                tableName: table,
                tableId: this.isNotBlank(id) ? id : "0",
                creater: "${currUserAccount}"
            },
            deleteFlag: true
        });
    };
    houseModelFun.showFile = function (fieldsName, table, id) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: table,
                tableId: this.isNotBlank(id) ? id : "0",
                creater: "${currUserAccount}"
            },
            deleteFlag: true
        })
    };

    houseModelFun.select2Assignment = function (frm, data, name) {
        if (this.isNotBlank(data)) {
            $("#" + frm).find("select." + name).val(data).trigger("change");
        } else {
            $("#" + frm).find("select." + name).val(null).trigger("change");
        }
    };

    houseModelFun.houseInit = function (item) {
        $("#" + houseModelFun.config.house.frm).clearAll();
        $("#" + houseModelFun.config.house.frm).initForm(item);
        this.uploadFile(houseModelFun.config.house.houseFileId, AssessDBKey.BasicHouse, item.id);
        this.showFile(houseModelFun.config.house.houseFileId, AssessDBKey.BasicHouse, item.id);
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseLoadUtility, item.certUse, function (html, data) {
            $("#" + houseModelFun.config.house.frm).find("select.certUse").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePracticalUse, item.practicalUse, function (html, data) {
            $("#" + houseModelFun.config.house.frm).find("select.practicalUse").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseEnvironmentUse, item.useEnvironment, function (html, data) {
            $("#" + houseModelFun.config.house.frm).find("select.useEnvironment").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseNewsHuxing, item.newsHuxing, function (html, data) {
            $("#" + houseModelFun.config.house.frm).find("select.useEnvironment").empty().html(html).trigger('change');
        });
    };

    houseModelFun.unitHuxingSelectLoad = function (item) {
        var unitId = houseModelFun.isNotBlank(item.id) ? item.id : "0";
        $.ajax({
            url: "${pageContext.request.contextPath}/basicUnitHuxing/basicUnitHuxingList",
            type: "get",
            dataType: "json",
            data: {unitId: unitId},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var retHtml = '<option value="" selected>-请选择-</option>';
                    $.each(result.data, function (i, item) {
                        var houseCategory = "";
                        try {
                            houseCategory = unitHuxing.prototype.rule("formatter", JSON.parse(item.houseCategory));
                        } catch (e) {
                            console.error(e);
                            console.log("函数失效!");
                        }
                        retHtml += ' <option value="' + item.id + '">' + houseCategory + '</option>';
                    });
                    $(item).prev().empty().append(retHtml);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    houseModelFun.tradingInit = function (item) {
        $("#" + this.config.trading.frm).clearAll();
        $("#" + this.config.trading.frm).initForm(item);
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHousetaxBurden, item.taxBurden, function (html, data) {
            $("#" + houseModelFun.config.trading.frm).find("select.taxBurden").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.basicHouseTransactionType, item.tradingType, function (html, data) {
            $("#" + houseModelFun.config.trading.frm).find("select.tradingType").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseDescriptionType, item.descriptionType, function (html, data) {
            $("#" + houseModelFun.config.trading.frm).find("select.descriptionType").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseNormalTransaction, item.normalTransaction, function (html, data) {
            $("#" + houseModelFun.config.trading.frm).find("select.normalTransaction").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePaymentMethod, item.paymentMethod, function (html, data) {
            $("#" + houseModelFun.config.trading.frm).find("select.paymentMethod").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseClassificationInformationSources, item.informationType, function (html, data) {
            $("#" + houseModelFun.config.trading.frm).find("select.informationType").empty().html(html).trigger('change');
        });
    };

    houseModelFun.tradingSellAndLease = {
        show: function () {
            var tradingID = $("#" + houseModelFun.config.trading.frm).find("select.tradingType").val();
            var tradingType = null;
            AssessCommon.getDataDicInfo(tradingID, function (data) {
                tradingType = data.fieldName;
                $("#" + houseModelFun.config.trading.frmSon).clearAll();
                $("#" + houseModelFun.config.trading.divBoxSon).modal("show");
                $("#" + houseModelFun.config.trading.frmSon).find(".type").val(tradingType);
                if (tradingType == houseModelFun.config.trading.leaseID) {
                    $("#" + houseModelFun.config.trading.divBoxSon).find(".lease").show();
                    $("#" + houseModelFun.config.trading.divBoxSon).find(".sell").hide();
                    $("#" + houseModelFun.config.trading.divBoxSon).find(".modal-title").html("出租信息");
                }
                if (tradingType == houseModelFun.config.trading.sellID) {
                    $("#" + houseModelFun.config.trading.divBoxSon).find(".lease").hide();
                    $("#" + houseModelFun.config.trading.divBoxSon).find(".sell").show();
                    $("#" + houseModelFun.config.trading.divBoxSon).find(".modal-title").html("出售信息");
                }
            })
        },
        save: function () {
            if (!$("#" + houseModelFun.config.trading.frmSon).valid()) {
                return false;
            }
            var data = formParams(houseModelFun.config.trading.frmSon);
            var item = formParams(houseModelFun.config.house.frm);
            data.tradingType = data.type;
            data.houseId = houseModelFun.isNotBlank(item.id) ? item.id : "0";
            var url = "${pageContext.request.contextPath}/basicHouseTradingLeaseAndSell/saveAndUpdateHouseTradingLeaseAndSell";
            $.ajax({
                url: url,
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $("#" + houseModelFun.config.trading.divBoxSon).modal("hide");
                        houseModelFun.tradingSellAndLease.subLoadList(data.type);
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
        subLoadList: function (type_) {
            var cols = [];
            if (type_ == houseModelFun.config.trading.leaseID) {
                cols.push({field: 'rentGrowthRate', title: '租金增长比率'});
                cols.push({field: 'rentPaymentTimeStartName', title: '租金支付时间起'});
                cols.push({field: 'rentPaymentTimeEndName', title: '租金支付时间止'});
            }
            if (type_ == houseModelFun.config.trading.sellID) {
                cols.push({field: 'instalmentInterest', title: '分期支付时间起'});
                cols.push({field: 'instalmentPeriodStartName', title: '分期支付时间止'});
                cols.push({field: 'instalmentPeriodEndName', title: '分期支付利息'});
            }
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += "<a class='btn btn-xs btn-warning tooltips' data-placement='top' data-original-title='删除' " + "onclick=houseModelFun.tradingSellAndLease.remove(" + row.id + ",'" + row.tradingType + "'" + ")" + ">";
                    str += "<i class='fa fa-minus fa-white'>" + "</i>";
                    str += "</a>";
                    str += '</div>';
                    return str;
                }
            });
            $("#" + houseModelFun.config.trading.tableSon).bootstrapTable('destroy');
            TableInit(houseModelFun.config.trading.tableSon, "${pageContext.request.contextPath}/basicHouseTradingLeaseAndSell/getLeaseAndSellVos", cols, {
                type: type_, houseId: 0
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        remove: function (id, type) {
            $.ajax({
                url: "${pageContext.request.contextPath}/basicHouseTradingLeaseAndSell/removeLeaseAndSell",
                type: "post",
                dataType: "json",
                data: {id: id, type: type},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('删除成功');
                        houseModelFun.tradingSellAndLease.subLoadList(type);
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        }
    };

    houseModelFun.houseEvent = {
        changeEvent: function () {
            $("#" + houseModelFun.config.house.frm).find(".huxingId").change(function () {
                var id = $("#" + houseModelFun.config.house.frm).find(".huxingId").find("option:selected").val();
                $.ajax({
                    url: "${pageContext.request.contextPath}/basicUnitHuxing/getBasicUnitHuxingById",
                    type: "get",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            if (houseModelFun.isNotBlank(result.data)) {
                                if (houseModelFun.isNotBlank(result.data.fileViewName)) {
                                    $("#" + houseModelFun.config.house.frm).find(".house_latest_family_plan").html(result.data.fileViewName);
                                }
                            }
                        }
                        else {
                            Alert("保存数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
            });
        }
    };

    houseModelFun.tradingEvent = {
        changeEvent: function () {
            $("#" + houseModelFun.config.trading.frm).find("select.taxBurden").change(function () {
                var id = $("#" + houseModelFun.config.trading.frm).find("select.taxBurden").val();
                if (houseModelFun.isNotBlank(id)) {
                    AssessCommon.getDataDicInfo(id, function (item) {
                        var name = item.name;
                        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseNormalTransaction, "", function (html, data) {
                            if (name == '正常承担') {
                                $.each(data, function (i, n) {
                                    if (n.name == "正常") {
                                        houseModelFun.select2Assignment(houseModelFun.config.trading.frm, n.id, "normalTransaction");
                                    }
                                });
                            } else {
                                $.each(data, function (i, n) {
                                    if (n.name == "不正常") {
                                        houseModelFun.select2Assignment(houseModelFun.config.trading.frm, n.id, "normalTransaction");
                                    }
                                });
                            }
                        });
                    });
                }
            });
            $("#" + houseModelFun.config.trading.frm).find("select.tradingType").change(function () {
                var tradingID = $("#" + houseModelFun.config.trading.frm).find("select.tradingType").val();
                var tradingType = null;
                AssessCommon.getDataDicInfo(tradingID, function (data) {
                    tradingType = data.fieldName;
                    if (tradingType == houseModelFun.config.trading.leaseID) {
                        $("#" + houseModelFun.config.trading.frm).find("." + houseModelFun.config.trading.sellID).hide();
                        $("#" + houseModelFun.config.trading.frm).find("." + houseModelFun.config.trading.leaseID).show();
                        $("#" + houseModelFun.config.trading.frm).find("#" + houseModelFun.config.trading.tableSon + "Div").show();
                        houseModelFun.tradingSellAndLease.subLoadList(houseModelFun.config.trading.leaseID);
                    }
                    if (tradingType == houseModelFun.config.trading.sellID) {
                        $("#" + houseModelFun.config.trading.frm).find("." + houseModelFun.config.trading.sellID).show();
                        $("#" + houseModelFun.config.trading.frm).find("." + houseModelFun.config.trading.leaseID).hide();
                        $("#" + houseModelFun.config.trading.frm).find("#" + houseModelFun.config.trading.tableSon + "Div").hide();
                    }
                })
            });
            $("#" + houseModelFun.config.trading.frm).find("select.paymentMethod").change(function () {
                var id = $("#" + houseModelFun.config.trading.frm).find("select.paymentMethod").val();
                AssessCommon.getDataDicInfo(id, function (data) {
                    if (data.name == '一次性') {
                        $("#" + houseModelFun.config.trading.frm).find("input[name='totalSale']").parent().parent().show();
                        $("#" + houseModelFun.config.trading.frm).find("#" + houseModelFun.config.trading.tableSon + "Div").hide();
                    }
                    if (data.name == '分期付款') {
                        $("#" + houseModelFun.config.trading.frm).find("input[name='totalSale']").parent().parent().hide();
                        $("#" + houseModelFun.config.trading.frm).find("#" + houseModelFun.config.trading.tableSon + "Div").show();
                        houseModelFun.tradingSellAndLease.subLoadList(houseModelFun.config.trading.sellID);
                    }
                });
            });
        }
    };

    $(function () {
        houseModelFun.tradingEvent.changeEvent();
        houseModelFun.houseEvent.changeEvent();
    });
</script>

<div id="divBoxTradingLeaseAndSell" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title"></h3>
            </div>
            <form id="frmTradingLeaseAndSell" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type" class="type">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <!--lease -->
                            <div class="panel-body lease">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            租金支付时间起<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input required="required" placeholder="租金支付时间起"
                                                   name="rentPaymentTimeStart" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            租金支付时间止<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input required="required" placeholder="租金支付时间起"
                                                   name="rentPaymentTimeEnd" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            租金增长比率<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" data-rule-number='true' class="form-control"
                                                   name="rentGrowthRate"
                                                   placeholder="租金增长比率(请输入数字)" required="required">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--sell -->
                            <div class="panel-body sell">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            分期支付时间起<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input required="required" placeholder="分期支付时间起"
                                                   name="instalmentPeriodStart" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            分期支付时间起止<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input required="required" placeholder="分期支付时间起止"
                                                   name="instalmentPeriodEnd" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            分期支付利息<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" data-rule-number='true' class="form-control"
                                                   name="instalmentInterest"
                                                   placeholder="分期支付利息(请输入数字)" required="required">
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
                    <button type="button" class="btn btn-primary" onclick="houseModelFun.tradingSellAndLease.save()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>