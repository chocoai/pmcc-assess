<%--
  Created by IntelliJ IDEA.
  User: huhao
  Date: 2018/9/3
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    项目提成
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form id="project_master_form" class="form-horizontal">
                <input type="hidden" name="id" value="${projectXlxCommission.id}">
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 col-form-label">
                                发票号码
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectXlxCommission.invoiceNumber}</label>
                            </div>
                            <label class="col-sm-1 col-form-label">
                                发票总金额
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectXlxCommission.invoiceTotalMoney}</label>
                            </div>
                            <label class="col-sm-1 col-form-label">
                                本项目金额
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectXlxCommission.projectMoney}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 col-form-label">
                                收款确认
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectXlxCommission.paymentConfirmation}</label>
                            </div>
                            <label class="col-sm-1 col-form-label">
                                报告文号
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectXlxCommission.reportNumber}</label>
                            </div>
                            <label class="col-sm-1 col-form-label">
                                未提成确定
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectXlxCommission.notCommissionConfirm}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 col-form-label">
                                未返佣确定
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectXlxCommission.notRebateConfirm}</label>
                            </div>
                            <label class="col-sm-1 col-form-label">
                                确定时间
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">
                                    <fmt:formatDate value="${projectXlxCommission.confirmTime}"
                                                    pattern="yyyy-MM-dd"/>
                                </label>
                            </div>
                            <label class="col-sm-1 col-form-label">
                                报告装订日
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">
                                    <fmt:formatDate value="${projectXlxCommission.reportBindDate}"
                                                    pattern="yyyy-MM-dd"/>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 col-form-label">
                                归档日期
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">
                                    <fmt:formatDate value="${projectXlxCommission.pigeonholeDate}"
                                                    pattern="yyyy-MM-dd"/>
                                </label>

                            </div>
                            <label class="col-sm-1 col-form-label">
                                逾期归档
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectXlxCommission.overduePigeonhole}</label>

                            </div>
                            <label class="col-sm-1 col-form-label">
                                归档确定
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectXlxCommission.pigeonholeConfirm}</label>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="x_title">返佣结算比例</div>
                <div class="x_content">
                    <div class="row form-group">
                        <div class="col-md-12">
                            <table class="table table-bordered" id="tb_rebateRatioList">
                                <!-- cerare document add ajax data-->
                            </table>
                        </div>
                    </div>
                </div>
                <div class="x_title">提成结算比例</div>
                <div class="x_content">
                    <div class="row form-group">
                        <div class="col-md-12">
                            <table class="table table-bordered" id="tb_commissionRatioList">
                                <!-- cerare document add ajax data-->
                            </table>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


<script type="text/javascript">
    $(function () {
        commissionRatio.prototype.loadDataDicList();
    });
    var commissionRatio = function () {

    };
    commissionRatio.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_commissionRatioList";
            data.box = "divCommissionRatioBox";
            data.frm = "commissionRatioFrm";
            return data;
        },
        loadDataDicList: function () {
            var cols = [];
            cols.push({field: 'name', width: '20%', title: '姓名'});
            cols.push({field: 'money', width: '20%', title: '金额'});
            cols.push({field: 'coefficient', width: '40%', title: '系数'});
            $("#" + commissionRatio.prototype.config().table).bootstrapTable('destroy');
            TableInit(commissionRatio.prototype.config().table, "${pageContext.request.contextPath}/projectXlxCommissionRatio/getList", cols, {
                masterId: '${projectXlxCommission.id}'
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        }
    }
</script>
<script type="text/javascript">
    $(function () {
        rebateRatio.prototype.loadDataDicList();
    });
    var rebateRatio = function () {

    };
    rebateRatio.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_rebateRatioList";
            data.box = "divRebateRatioBox";
            data.frm = "rebateRatioFrm";
            return data;
        },
        loadDataDicList: function () {
            var cols = [];
            cols.push({field: 'name', width: '40%', title: '姓名'});
            cols.push({field: 'money', width: '40%', title: '金额'});
            $("#" + rebateRatio.prototype.config().table).bootstrapTable('destroy');
            TableInit(rebateRatio.prototype.config().table, "${pageContext.request.contextPath}/projectXlxRebateRatio/getList", cols, {
                masterId: '${projectXlxCommission.id}'
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        }
    }
</script>