<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/9/13
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <script type="text/javascript" src="/pmcc-crm/js/crm-customer-utils.js"></script>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <div class="x_panel">

                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h2>
                        房屋
                    </h2>
                    <div class="clearfix"></div>
                </div>

                <div class="x_content">
                    <div class="x_title">
                        <h3>房屋基本信息 </h3>
                        <div class="clearfix"></div>
                    </div>
                    <form class="form-horizontal" id="frm_house">
                        <input type="hidden" name="id" value="${caseHouse.id}">

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">房号<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="100" placeholder="房号"
                                           value="${caseHouse.houseNumber}" name="houseNumber"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">所在楼层<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                                           placeholder="所在楼层(请输入数字)"
                                           value="${caseHouse.houseNumber}" name="floor"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">使用环境<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 useEnvironment" name="useEnvironment" required="required">
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">户型选择<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <select class="form-control huxingId" name="huxingId">
                                        </select>
                                        <label class="input-group-addon btn">刷新户型<i
                                                class="fa fa-refresh"></i></label>
                                    </div>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">户型图<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <div class="house_latest_family_plan"></div>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">朝向<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="朝向" readonly="readonly"
                                           value="${caseHouse.orientation}" name="orientation"
                                           class="form-control">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">证载用途<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 certUse" name="certUse"
                                            required="required">
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">实际用途<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 practicalUse" name="practicalUse"
                                            required="required">
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">权益限制<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="100" placeholder="权益限制"
                                           value="${caseHouse.rightInterestsRestriction}"
                                           name="rightInterestsRestriction"
                                           class="form-control">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">房屋出租占用情况途描述<span
                                        class="symbol required"></span></label>
                                <div class="col-sm-11">
                                    <textarea class="form-control" name="description" required="required">
                                        ${caseHouse.description}
                                    </textarea>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">房屋平面图<span class="symbol required"></span></label>
                                <div class="col-sm-5">
                                    <input id="house_house_plan"
                                           required="required" placeholder="上传附件" class="form-control" type="file">
                                    <div id="_house_house_plan"></div>
                                </div>
                            </div>
                        </div>

                    </form>
                </div>

                <div class="x_content">
                    <div class="x_title">
                        <h3>房屋交易信息 </h3>
                        <div class="clearfix"></div>
                    </div>
                    <form class="form-horizontal" id="frm_trading">
                        <input type="hidden" name="id" value="${caseHouseTrading.id}">

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">财产范围<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="财产范围" required="required"
                                           name="scopeProperty" class="form-control"
                                           value="${caseHouseTrading.scopeProperty}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">融资条件<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="融资条件" required="required"
                                           name="financingConditions" class="form-control"
                                           value="${caseHouseTrading.financingConditions}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">税费负担<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 taxBurden" name="taxBurden"
                                            required="required">
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">正常交易<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 normalTransaction"
                                            name="normalTransaction"
                                            required="required">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">说明事项类型<span
                                        class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 descriptionType"
                                            name="descriptionType"
                                            required="required">
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">说明事项内容<span
                                        class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="说明事项内容" required="required"
                                           value="${caseHouseTrading.descriptionContent}"
                                           name="descriptionContent"
                                           class="form-control">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">交易时间<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input required="required" placeholder="交易时间"
                                           name="tradingTime" data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate tradingTime" value="<fmt:formatDate value='${caseHouseTrading.tradingTime}' pattern='yyyy-MM-dd'/>">
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">交易类型<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 tradingType" name="tradingType"
                                            required="required">
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">交易价格<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="交易价格(请输入数字)" required="required"
                                           value="${caseHouseTrading.tradingPrice}" data-rule-number='true'
                                           name="tradingPrice" class="form-control">
                                </div>
                            </div>
                        </div>

                        <div class="form-group CaseHouseTradingSell" style="display: none">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">买方支付的额外税费</label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="买方支付的额外税费"
                                           name="buyerExtraTaxFee" class="form-control" value="${caseHouseTrading.buyerExtraTaxFee}">
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">付款方式<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 paymentMethod" name="paymentMethod"
                                            required="required">
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid totalSale" style="display: none;">
                                <label class="col-sm-1 control-label">出售总额</label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="出售总额"
                                           name="totalSale" class="form-control" value="${caseHouseTrading.totalSale}">
                                </div>
                            </div>
                        </div>

                        <div class="form-group CaseHouseTradingLease" style="display: none">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">承租方支付的额外税费</label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="承租方支付的额外税费"
                                           value="${caseHouseTrading.rentingExtraTaxFee}" name="rentingExtraTaxFee" class="form-control">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">押金</label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="押金"  class="form-control" name="deposit" value="${caseHouseTrading.deposit}">
                                </div>
                            </div>
                        </div>

                        <div class="form-group" id="CaseHouseTradingLeaseAndSellDtoTableSonDiv" style="display: none">
                            <div class="x-valid">
                                <div class="col-sm-1" style="text-align: right;">
                                    <button type="button" class="btn btn-success" data-toggle="modal" href="#divBox" onclick="CaseHouseModelFun.prototype.subShowModel();"> 新增
                                    </button>
                                </div>
                                <div class="col-sm-11">
                                    <table class="table table-bordered" id="CaseHouseTradingLeaseAndSellDtoTableSon">
                                        <!-- cerare document add ajax data-->
                                    </table>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">信息来源分类<span
                                        class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 informationType"
                                            name="informationType"
                                            required="required">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">信息来源<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="信息来源" required="required"
                                           value="${caseHouseTrading.information}" name="information"
                                           class="form-control">
                                </div>
                            </div>
                        </div>

                    </form>
                </div>

            </div>

            <div class="x_panel">
                <div class="x_content">
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-5">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                取消
                            </button>

                            <button id="commit_btn" class="btn btn-success"
                                    onclick="CaseHouseModelFun.prototype.submit();">
                                提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var CaseHouseModelFun = function () {

    }

    CaseHouseModelFun.prototype.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    }

    CaseHouseModelFun.prototype.writeSelectData = function (frm, data, name) {
        if (CaseHouseModelFun.prototype.isEmpty(data)) {
            $("#" + frm + " ." + name).val(data).trigger("change");
        } else {
            $("#" + frm + " ." + name).val(null).trigger("change");
        }
    }

    CaseHouseModelFun.prototype.config = {
        house: {
            frm: function () {
                return "frm_house";//房屋基本信息frm
            },
            getHouseNewLatestFamilyPlan: function () {
                return "house_new_latest_family_plan";//最新户型图id和字段
            },
            getHouseHousePlan: function () {
                return "house_house_plan"; //房屋平面图id和字段
            }
        },
        trading: {
            frm: function () {
                return "frm_trading";//房屋交易基本信息frm
            },
            examineHouseTradingLeaseID: function () {
                return "CaseHouseTradingLease";//房屋出租
            },
            examineHouseTradingSellID: function () {
                return "CaseHouseTradingSell";//房屋出售
            },
            totalSale:function () {
                return "totalSale";//出售总额
            },
            divBoxSon:function () {
                return "divBoxCaseHouseTradingLeaseAndSellDto";//
            },
            tableSon:function () {
                return "CaseHouseTradingLeaseAndSellDtoTableSon" ;
            },
            frmSon:function () {
                return "frmCaseHouseTradingLeaseAndSellDto";
            }
        }

    }
    CaseHouseModelFun.prototype.subShowModel = function () {
        var tradingID = $("#" + CaseHouseModelFun.prototype.config.trading.frm() + " .tradingType").eq(1).val();
        var tradingType = null;
        AssessCommon.getDataDicInfo(tradingID,function (data) {
            tradingType = data.fieldName;
            $("#" + CaseHouseModelFun.prototype.config.trading.frmSon()).clearAll();
            $("#" + CaseHouseModelFun.prototype.config.trading.divBoxSon()).modal("show");
            $("#" + CaseHouseModelFun.prototype.config.trading.frmSon() + " .type").val(tradingType);
            if (tradingType == CaseHouseModelFun.prototype.config.trading.examineHouseTradingLeaseID()) {
                $("#" + CaseHouseModelFun.prototype.config.trading.divBoxSon() + " .lease").show();
                $("#" + CaseHouseModelFun.prototype.config.trading.divBoxSon() + " .sell").hide();
                $("#" + CaseHouseModelFun.prototype.config.trading.divBoxSon() + " .modal-title").html("出租信息");
            }
            if (tradingType == CaseHouseModelFun.prototype.config.trading.examineHouseTradingSellID()) {
                $("#" + CaseHouseModelFun.prototype.config.trading.divBoxSon() + " .lease").hide();
                $("#" + CaseHouseModelFun.prototype.config.trading.divBoxSon() + " .sell").show();
                $("#" + CaseHouseModelFun.prototype.config.trading.divBoxSon() + " .modal-title").html("出售信息");
            }
        })
    }
    CaseHouseModelFun.prototype.subSaveModel = function () {
        if (!$("#" + CaseHouseModelFun.prototype.config.trading.frmSon()).valid()) {
            return false;
        }
        var data = formParams(CaseHouseModelFun.prototype.config.trading.frmSon());
        data.tradingType = data.type;
        console.log(data);
        var url = "${pageContext.request.contextPath}/caseHouse/saveCaseHouseTradingLeaseAndSellDto";
        $.ajax({
            url: url,
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    toastr.success('保存成功');
                    $("#" + CaseHouseModelFun.prototype.config.trading.divBoxSon()).modal("hide");
                    CaseHouseModelFun.prototype.subLoadList(data.type);
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }
    CaseHouseModelFun.prototype.subRemove = function (id,type) {
        $.ajax({
            url: "${pageContext.request.contextPath}/caseHouse/removeCaseHouseTradingLeaseAndSellDto",
            type: "post",
            dataType: "json",
            data: {id:id,type:type},
            success: function (result) {
                if (result.ret) {
                    toastr.success('删除成功');
                    CaseHouseModelFun.prototype.subLoadList(type);
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
    CaseHouseModelFun.prototype.subLoadList = function (type_) {
        var cols = [];
        if (type_ == CaseHouseModelFun.prototype.config.trading.examineHouseTradingSellID()) {
            cols.push({field: 'instalmentInterest', title: '分期支付时间起'});
            cols.push({field: 'instalmentPeriodStartName', title: '分期支付时间止'});
            cols.push({field: 'instalmentPeriodEndName', title: '分期支付利息'});
        }
        if (type_ == CaseHouseModelFun.prototype.config.trading.examineHouseTradingLeaseID()) {
            cols.push({field: 'rentGrowthRate', title: '租金增长比率'});
            cols.push({field: 'rentPaymentTimeStartName', title: '租金支付时间起'});
            cols.push({field: 'rentPaymentTimeEndName', title: '租金支付时间止'});
        }
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += "<a class='btn btn-xs btn-warning tooltips' data-placement='top' data-original-title='删除' "+"onclick=CaseHouseModelFun.prototype.subRemove("+row.id+",'"+row.tradingType+"'"+")"+">"  ;
                str += "<i class='fa fa-minus fa-white'>" +"</i>" ;
                str += "</a>";
                str += '</div>';
                return str;
            }
        });
        $("#" + CaseHouseModelFun.prototype.config.trading.tableSon()).bootstrapTable('destroy');
        TableInit(CaseHouseModelFun.prototype.config.trading.tableSon(), "${pageContext.request.contextPath}/caseHouse/getCaseHouseTradingLeaseAndSellDtoVos", cols, {
            type: type_
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }

    CaseHouseModelFun.prototype.select2 = {
        house: function () {
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseLoadUtility, null, function (html, data) {
                $("#" + CaseHouseModelFun.prototype.config.house.frm() + " .certUse").html(html);
                $("#" + CaseHouseModelFun.prototype.config.house.frm() + " .certUse").select2();//加载样式
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePracticalUse, null, function (html, data) {
                $("#" + CaseHouseModelFun.prototype.config.house.frm() + " .practicalUse").html(html);
                $("#" + CaseHouseModelFun.prototype.config.house.frm() + " .practicalUse").select2();//加载样式
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseEnvironmentUse, "", function (html, data) {
                $("#" + CaseHouseModelFun.prototype.config.house.frm() + " .useEnvironment").html(html);
                $("#" + CaseHouseModelFun.prototype.config.house.frm() + " .useEnvironment").select2();//加载样式
            })
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseNewsHuxing, null, function (html, data) {
                $("#" + CaseHouseModelFun.prototype.config.house.frm() + " .newsHuxing").html(html);
                $("#" + CaseHouseModelFun.prototype.config.house.frm() + " .newsHuxing").select2();//加载样式
            });
        },
        init: function () {
            CaseHouseModelFun.prototype.select2.house();
            CaseHouseModelFun.prototype.select2.trading();
            CaseHouseModelFun.prototype.select2.huXinSelect();
        },
        trading: function () {
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHousetaxBurden, "", function (html, data) {
                $("#" + CaseHouseModelFun.prototype.config.trading.frm() + " .taxBurden").html(html);
                $("#" + CaseHouseModelFun.prototype.config.trading.frm() + " .taxBurden").select2();//加载样式
            });
            //第一次加载
            AssessCommon.loadDataDicByKey(AssessDicKey.casesHouseTransactionType, "", function (html, data) {
                $("#" + CaseHouseModelFun.prototype.config.trading.frm() + " .tradingType").html(html);
                $("#" + CaseHouseModelFun.prototype.config.trading.frm() + " .tradingType").select2();//加载样式
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseDescriptionType, "", function (html, data) {
                $("#" + CaseHouseModelFun.prototype.config.trading.frm() + " .descriptionType").html(html);
                $("#" + CaseHouseModelFun.prototype.config.trading.frm() + " .descriptionType").select2();//加载样式
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseNormalTransaction, "", function (html, data) {
                $("#" + CaseHouseModelFun.prototype.config.trading.frm() + " .normalTransaction").html(html);
                $("#" + CaseHouseModelFun.prototype.config.trading.frm() + " .normalTransaction").select2();//加载样式
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePaymentMethod, "", function (html, data) {
                $("#" + CaseHouseModelFun.prototype.config.trading.frm() + " .paymentMethod").html(html);
                $("#" + CaseHouseModelFun.prototype.config.trading.frm() + " .paymentMethod").select2();//加载样式
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseClassificationInformationSources, "", function (html, data) {
                $("#" + CaseHouseModelFun.prototype.config.trading.frm() + " .informationType").html(html);
                $("#" + CaseHouseModelFun.prototype.config.trading.frm() + " .informationType").select2();//加载样式
            });
            $("#" + CaseHouseModelFun.prototype.config.trading.frm() + " .taxBurden").change(function () {
                var id = $("#" + CaseHouseModelFun.prototype.config.trading.frm() + " .taxBurden").eq(1).val();
                if (CaseHouseModelFun.prototype.isEmpty(id)) {
                    AssessCommon.getDataDicInfo(id, function (item) {
                        var name = item.name;
                        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseNormalTransaction, "", function (html, data) {
                            if (name == '正常承担') {
                                $.each(data, function (i, n) {
                                    if (n.name == "正常") {
                                        CaseHouseModelFun.prototype.writeSelectData(CaseHouseModelFun.prototype.config.trading.frm(), n.id, "normalTransaction");
                                    }
                                });
                            } else {
                                $.each(data, function (i, n) {
                                    if (n.name == "不正常") {
                                        CaseHouseModelFun.prototype.writeSelectData(CaseHouseModelFun.prototype.config.trading.frm(), n.id, "normalTransaction");
                                    }
                                });
                            }
                        });
                    });
                }
            });
            $("#" + CaseHouseModelFun.prototype.config.trading.frm() + " .tradingType").change(function () {
                var tradingID = $("#" + CaseHouseModelFun.prototype.config.trading.frm() + " .tradingType").eq(1).val();
                var tradingType = null;
                AssessCommon.getDataDicInfo(tradingID,function (data) {
                    tradingType = data.fieldName;
                    if (tradingType == CaseHouseModelFun.prototype.config.trading.examineHouseTradingLeaseID()) {
                        $("#"+CaseHouseModelFun.prototype.config.trading.frm() +" ."+CaseHouseModelFun.prototype.config.trading.examineHouseTradingSellID()).hide();
                        $("#"+CaseHouseModelFun.prototype.config.trading.frm() +" ."+CaseHouseModelFun.prototype.config.trading.examineHouseTradingLeaseID()).show();
                        $("#"+CaseHouseModelFun.prototype.config.trading.tableSon()+"Div").show();
                        CaseHouseModelFun.prototype.subLoadList(CaseHouseModelFun.prototype.config.trading.examineHouseTradingLeaseID());
                    }
                    if (tradingType == CaseHouseModelFun.prototype.config.trading.examineHouseTradingSellID()) {
                        $("#"+CaseHouseModelFun.prototype.config.trading.frm() +" ."+CaseHouseModelFun.prototype.config.trading.examineHouseTradingSellID()).show();
                        $("#"+CaseHouseModelFun.prototype.config.trading.frm() +" ."+CaseHouseModelFun.prototype.config.trading.examineHouseTradingLeaseID()).hide();
                        $("#"+CaseHouseModelFun.prototype.config.trading.tableSon()+"Div").hide();
                    }
                })
            });
            $("#" + CaseHouseModelFun.prototype.config.trading.frm() + " .paymentMethod").change(function () {
                var id = $("#" + CaseHouseModelFun.prototype.config.trading.frm() + " .paymentMethod").eq(1).val();
                AssessCommon.getDataDicInfo(id,function (data){
                    if (data.name == '一次性'){
                        $("#"+CaseHouseModelFun.prototype.config.trading.frm()+" ."+CaseHouseModelFun.prototype.config.trading.totalSale()).show();
                        $("#"+CaseHouseModelFun.prototype.config.trading.tableSon()+"Div").hide();
                    }
                    if (data.name == '分期付款'){
                        $("#"+CaseHouseModelFun.prototype.config.trading.frm()+" ."+CaseHouseModelFun.prototype.config.trading.totalSale()).hide();
                        $("#"+CaseHouseModelFun.prototype.config.trading.tableSon()+"Div").show();
                        CaseHouseModelFun.prototype.subLoadList(CaseHouseModelFun.prototype.config.trading.examineHouseTradingSellID());
                    }
                });
            });
        },
        huXinSelect: function () {

        },
        rule: function (item) {
            var text = "";
            if (CaseHouseModelFun.prototype.isEmpty(item.house)) {
                text += item.house + "房-";
            }
            if (CaseHouseModelFun.prototype.isEmpty(item.saloon)) {
                text += item.saloon + "客厅-";
            }
            if (CaseHouseModelFun.prototype.isEmpty(item.kitchen)) {
                text += item.kitchen + "厨房-";
            }
            if (CaseHouseModelFun.prototype.isEmpty(item.toilet)) {
                text += item.toilet + "卫生间-";
            }
            if (CaseHouseModelFun.prototype.isEmpty(item.garden)) {
                text += item.garden + "花园-";
            }
            if (CaseHouseModelFun.prototype.isEmpty(item.balcony)) {
                text += item.balcony + "阳台";
            }
            return text;
        }
    }

    CaseHouseModelFun.prototype.fileModel = {
        uploadFileHouse: function (fieldsName, table) {
            FileUtils.uploadFiles({
                target: fieldsName,
                disabledTarget: "btn_submit",
                formData: {
                    fieldsName: fieldsName,
                    tableName: table,
                    tableId: ${empty caseHouse.id?0:caseHouse.id},
                    creater: "${currUserAccount}"
                },
                deleteFlag: true
            });
        },
        showFileHouse: function (fieldsName, table) {
            FileUtils.getFileShows({
                target: fieldsName,
                formData: {
                    fieldsName: fieldsName,
                    tableName: table,
                    tableId: ${empty caseHouse.id?0:caseHouse.id},
                    creater: "${currUserAccount}"
                },
                deleteFlag: true
            })
        }
    }


    //提交
    CaseHouseModelFun.prototype.submit = function () {
        if (!$("#" + CaseHouseModelFun.prototype.config.house.frm()).valid()) {
            return false;
        }
        if (!$("#" + CaseHouseModelFun.prototype.config.trading.frm()).valid()) {
            return false;
        }
        var house = formParams(CaseHouseModelFun.prototype.config.house.frm());
        var trading = formParams(CaseHouseModelFun.prototype.config.trading.frm());
        var unitId = "${unitId}";
        if (CaseHouseModelFun.prototype.isEmpty(unitId)) {
            house.unitId = unitId;
        }
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/caseHouse/saveAndUpdateCaseHouse",
            data: {formData: JSON.stringify({house: house, trading: trading})},
            success: function (result) {
                if (result.ret) {
                    //保存完后其他动作
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    }

    //编辑或者是复制空数据时
    CaseHouseModelFun.prototype.edit = function () {
        var caseHouse = "${caseHouse}" ;
        if (CaseHouseModelFun.prototype.isEmpty(caseHouse)){
            CaseHouseModelFun.prototype.writeSelectData(CaseHouseModelFun.prototype.config.house.frm(),"${caseHouse.useEnvironment}","useEnvironment");
            CaseHouseModelFun.prototype.writeSelectData(CaseHouseModelFun.prototype.config.house.frm(),"${caseHouse.certUse}","certUse");
            CaseHouseModelFun.prototype.writeSelectData(CaseHouseModelFun.prototype.config.house.frm(),"${caseHouse.practicalUse}","practicalUse");
        }
        var caseHouseTrading = "${caseHouseTrading}" ;
        if (CaseHouseModelFun.prototype.isEmpty(caseHouseTrading)){
            CaseHouseModelFun.prototype.writeSelectData(CaseHouseModelFun.prototype.config.trading.frm(),"${caseHouseTrading.taxBurden}","taxBurden");
            CaseHouseModelFun.prototype.writeSelectData(CaseHouseModelFun.prototype.config.trading.frm(),"${caseHouseTrading.normalTransaction}","normalTransaction");
            CaseHouseModelFun.prototype.writeSelectData(CaseHouseModelFun.prototype.config.trading.frm(),"${caseHouseTrading.descriptionType}","descriptionType");
            CaseHouseModelFun.prototype.writeSelectData(CaseHouseModelFun.prototype.config.trading.frm(),"${caseHouseTrading.paymentMethod}","paymentMethod");
            CaseHouseModelFun.prototype.writeSelectData(CaseHouseModelFun.prototype.config.trading.frm(),"${caseHouseTrading.informationType}","informationType");
        }
    };

    $(function () {
        CaseHouseModelFun.prototype.select2.init();
        CaseHouseModelFun.prototype.fileModel.uploadFileHouse(CaseHouseModelFun.prototype.config.house.getHouseHousePlan(), AssessDBKey.CaseHouse);
        CaseHouseModelFun.prototype.fileModel.showFileHouse(CaseHouseModelFun.prototype.config.house.getHouseHousePlan(), AssessDBKey.CaseHouse);
        CaseHouseModelFun.prototype.fileModel.uploadFileHouse(CaseHouseModelFun.prototype.config.house.getHouseNewLatestFamilyPlan(), AssessDBKey.CaseHouse);
        CaseHouseModelFun.prototype.fileModel.showFileHouse(CaseHouseModelFun.prototype.config.house.getHouseNewLatestFamilyPlan(), AssessDBKey.CaseHouse);
        CaseHouseModelFun.prototype.edit();
    });
</script>
<%@include file="/views/share/main_footer.jsp" %>
</body>
</html>
<div id="divBoxCaseHouseTradingLeaseAndSellDto" class="modal fade bs-example-modal-lg" data-backdrop="static"
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
            <form id="frmCaseHouseTradingLeaseAndSellDto" class="form-horizontal">
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
                    <button type="button" class="btn btn-primary" onclick="CaseHouseModelFun.prototype.subSaveModel()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
