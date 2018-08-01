<%--
 房屋交易信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>房屋交易信息 </h3>
        <div class="clearfix"></div>
    </div>
    <form id="frm_houseTrading" class="form-horizontal">
        <input type="hidden" data-name="fieldName" value="<%=request.getParameter("fieldName")%>">
        <input type="hidden" name="id" value="${surveyExamineDataInfoVo.examineHouseTradingVo.id}">

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">交易时间<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input required="required" placeholder="交易时间"
                           name="tradingTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate tradingTime">
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
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.tradingPrice}" data-rule-number='true'
                           name="tradingPrice"
                           class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group ExamineHouseTradingSell" style="display: none">
            <div class="x-valid">
                <label class="col-sm-1 control-label">买方支付的额外税费<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="买方支付的额外税费" required="required"
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.buyerExtraTaxFee}"
                           name="buyerExtraTaxFee"
                           class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group ExamineHouseTradingLease" style="display: none">
            <div class="x-valid">
                <label class="col-sm-1 control-label">承租方支付的额外税费<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="承租方支付的额外税费" required="required"
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.rentingExtraTaxFee}"
                           name="rentingExtraTaxFee"
                           class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">押金<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="押金" required="required" class="form-control" name="deposit" value="${surveyExamineDataInfoVo.examineHouseTradingVo.deposit}">
                </div>
            </div>

        </div>

        <div class="form-group" style="display: none">
            <div class="x-valid">
                <div class="col-sm-1">
                    <button type="button" class="btn btn-success" data-toggle="modal" href="#divBox" onclick="examineHouseTrading.prototype.subShowModel();"> 新增
                    </button>
                </div>
                <div class="col-sm-11">
                    <table class="table table-bordered" id="ExamineHouseTradingLeaseAndSellTableSon">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">说明事项类型<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 descriptionType" name="descriptionType"
                            required="required">
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">说明事项内容<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="说明事项内容" required="required"
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.descriptionContent}"
                           name="descriptionContent"
                           class="form-control">
                </div>
            </div>
        </div>
    </form>
</div>



