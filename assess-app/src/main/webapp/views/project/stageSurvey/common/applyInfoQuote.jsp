<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--引用案例库中的楼盘--%>
<div id="caseEstateModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">楼盘</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="row form-group ">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                名称
                            </label>
                            <div class="col-sm-5">
                                <input type="hidden" name="province">
                                <input type="hidden" name="city">
                                <input type="hidden" name="applyBatchDetailId">
                                <input type="text" data-rule-maxlength="50"
                                       placeholder="名称" name="name"
                                       class="form-control input-full">
                            </div>
                            <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                    onclick="applyInfoQuote.loadCaseEstateList();">
                                <span class="btn-label"><i class="fa fa-search"></i></span>
                                查询
                            </button>
                        </div>
                    </div>
                </div>
                <div class="row form-group ">
                    <div class="col-md-12">
                        <table class="table table-bordered" id="tbCaseEstateList">
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
<%--引用案例库中的楼栋、单元、房屋等信息--%>
<div id="caseOtherModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">案例数据列表</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="row form-group ">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                名称
                            </label>
                            <div class="col-sm-5">
                                <input type="hidden" name="quoteId">
                                <input type="hidden" name="applyBatchDetailId">
                                <input type="text" data-rule-maxlength="50"
                                       placeholder="名称" name="name"
                                       class="form-control input-full">
                            </div>
                            <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                    onclick="applyInfoQuote.loadCaseOtherList()">
                                <span class="btn-label"><i class="fa fa-search"></i></span>
                                查询
                            </button>
                        </div>
                    </div>
                </div>
                <div class="row form-group ">
                    <div class="col-md-12">
                        <table class="table table-bordered" id="tbCaseOtherList">
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
<%--引用备选案例信息--%>
<div id="caseAlternativeModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">备选案例列表</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="row form-group ">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                名称
                            </label>
                            <div class="col-sm-5">
                                <input type="hidden" name="quoteId">
                                <input type="hidden" name="applyBatchDetailId">
                                <input type="text" data-rule-maxlength="50"
                                       placeholder="名称" name="name"
                                       class="form-control input-full">
                            </div>
                            <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                    onclick="applyInfoQuote.loadCaseAlternativeList()">
                                <span class="btn-label"><i class="fa fa-search"></i></span>
                                查询
                            </button>
                        </div>
                    </div>
                </div>
                <div class="row form-group ">
                    <div class="col-md-12">
                        <table class="table table-bordered" id="tbCaseAlternativeList">
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

<script type="text/javascript">
    var applyInfoQuote = {};

    //显示楼盘案例弹窗
    applyInfoQuote.showCaseEstateModal = function (province, city, search, applyBatchDetailId) {
        var modal = $("#caseEstateModal");
        modal.find('[name=province]').val(province);
        modal.find('[name=city]').val(city);
        modal.find('[name=name]').val(search);
        modal.find('[name=applyBatchDetailId]').val(applyBatchDetailId);
        applyInfoQuote.loadCaseEstateList();
        modal.modal();
    }

    //加载楼盘案例数据
    applyInfoQuote.loadCaseEstateList = function () {
        var cols = [];
        cols.push({field: 'estateName', title: '名称', width: '80%'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-info tooltips"  data-placement="top" data-original-title="查看" onclick="applyInfoQuote.viewCaseEstate(' + row.id + ')"><i class="fa fa-search fa-white"></i></button>';
                str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="引用" onclick="applyInfoQuote.quoteCaseEstate(' + row.id + ')"><i class="fa fa-check fa-white"></i></button>';
                str += '</div>';
                return str;
            }
        });
        $("#tbCaseEstateList").bootstrapTable('destroy');
        TableInit("tbCaseEstateList", "${pageContext.request.contextPath}/basicApplyBatch/getCaseEstateListByName", cols, {
            province: $("#caseEstateModal").find('[name=province]').val(),
            city: $("#caseEstateModal").find('[name=city]').val(),
            name: $("#caseEstateModal").find('[name=name]').val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }

    //查看案例楼盘信息
    applyInfoQuote.viewCaseEstate = function (applyBatchId) {
        window.open('${pageContext.request.contextPath}/basic/checkCaseDetail?applyBatchId=' + applyBatchId);
    };

    //引用案例楼盘信息
    applyInfoQuote.quoteCaseEstate = function (applyBatchId) {
        var modal = $("#caseEstateModal");
        $.ajax({
            url: '${pageContext.request.contextPath}/basicEstate/quoteCaseEstate',
            type: 'get',
            data: {
                sourceApplyBatchId: applyBatchId,
                targetApplyBatchDetailId: modal.find('[name=applyBatchDetailId]').val()
            },
            dataType: 'json',
            success: function (result) {
                if (result.ret) {
                    AlertSuccess("成功", "引用数据成功", function () {
                        location.href = location.href;
                    });
                }
            }
        })
    }
    //------------------------------------------------------------------------------------------------------------
    //显示楼栋、单元、房屋案例弹窗
    applyInfoQuote.showCaseOtherModal = function (quoteId, applyBatchDetailId) {
        var modal = $("#caseOtherModal");
        modal.find('[name=quoteId]').val(quoteId);
        modal.find('[name=applyBatchDetailId]').val(applyBatchDetailId);
        applyInfoQuote.loadCaseOtherList();
        modal.modal();
    }

    //加载楼栋、单元、房屋案例数据
    applyInfoQuote.loadCaseOtherList = function () {
        var cols = [];
        cols.push({field: 'name', title: '名称', width: '80%'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-info tooltips"  data-placement="top" data-original-title="查看" onclick="applyInfoQuote.viewCaseOther(' + row.id + ')"><i class="fa fa-search fa-white"></i></button>';
                str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="引用" onclick="applyInfoQuote.quoteCaseOther(' + row.id + ')"><i class="fa fa-check fa-white"></i></button>';
                str += '</div>';
                return str;
            }
        });
        $("#tbCaseOtherList").bootstrapTable('destroy');
        TableInit("tbCaseOtherList", "${pageContext.request.contextPath}/basicApplyBatch/getCaseOtherListByName", cols, {
            quoteId: $("#caseOtherModal").find('[name=quoteId]').val(),
            name: $("#caseOtherModal").find('[name=name]').val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }

    //查看案例楼栋、单元、房屋信息
    applyInfoQuote.viewCaseOther = function (applyBatchDetailId) {
        window.open('${pageContext.request.contextPath}/basicApplyBatch/informationView?applyBatchDetailId=' + applyBatchDetailId);
    };

    //引用案例楼栋、单元、房屋信息
    applyInfoQuote.quoteCaseOther = function (applyBatchDetailId) {
        var modal = $("#caseOtherModal");
        $.ajax({
            url: '${pageContext.request.contextPath}/basicApplyBatch/quoteCaseOther',
            type: 'get',
            data: {
                sourceApplyBatchDetailId: applyBatchDetailId,
                targetApplyBatchDetailId: modal.find('[name=applyBatchDetailId]').val()
            },
            dataType: 'json',
            success: function (result) {
                if (result.ret) {
                    AlertSuccess("成功", "引用数据成功", function () {
                        location.href = location.href;
                    });
                }
            }
        })
    }
    //------------------------------------------------------------------------------------------------------------
    //显示备选案例弹窗
    applyInfoQuote.showCaseAlternativeModal = function (applyBatchDetailId) {
        var modal = $("#caseAlternativeModal");
        modal.find('[name=applyBatchDetailId]').val(applyBatchDetailId);
        applyInfoQuote.loadCaseAlternativeList();
        modal.modal();
    }

    //加载备选案例数据列表
    applyInfoQuote.loadCaseAlternativeList = function () {
        var cols = [];
        cols.push({field: 'name', title: '名称', width: '80%'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<button type="button" style="margin-left: 10px;" class="btn btn-xs btn-info tooltips"  data-placement="top" data-original-title="查看" onclick="applyInfoQuote.viewCaseAlternative(' + row.batchDetailId + ')"><i class="fa fa-search fa-white"></i></button>';
                str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="引用" onclick="applyInfoQuote.quoteCaseAlternative(' + row.batchDetailId + ')"><i class="fa fa-check"></i></button>';
                str += '<button type="button" style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"   data-placement="bottom" data-original-title="删除" onclick="applyInfoQuote.removeCaseAlternative(' + row.id + ')" >';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
                str += '</div>';
                return str;
            }
        });
        $("#tbCaseAlternativeList").bootstrapTable('destroy');
        TableInit("tbCaseAlternativeList", "${pageContext.request.contextPath}/basicAlternativeCase/getBasicAlternativeCaseList", cols, {
            name: $("#caseAlternativeModal").find('[name=name]').val(),
            applyBatchDetailId: $("#caseAlternativeModal").find('[name=applyBatchDetailId]').val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }

    //删除备选案例数据
    applyInfoQuote.removeCaseAlternative = function (id) {
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/basicAlternativeCase/deleteDataById",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除数据成功");
                        applyInfoQuote.loadCaseAlternativeList();
                    } else {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        })
    }

    //查看备选案例信息
    applyInfoQuote.viewCaseAlternative = function (applyBatchDetailId) {
        window.open('${pageContext.request.contextPath}/basicApplyBatch/informationView?applyBatchDetailId=' + applyBatchDetailId);
    }

    //引用备选案例
    applyInfoQuote.quoteCaseAlternative = function (applyBatchDetailId) {
        var modal = $("#caseAlternativeModal");
        $.ajax({
            url: '${pageContext.request.contextPath}/basicApplyBatch/quoteCaseOther',
            type: 'get',
            data: {
                sourceApplyBatchDetailId: applyBatchDetailId,
                targetApplyBatchDetailId: modal.find('[name=applyBatchDetailId]').val()
            },
            dataType: 'json',
            success: function (result) {
                if (result.ret) {
                    AlertSuccess("成功", "引用数据成功", function () {
                        location.href = location.href;
                    });
                }
            }
        })
    }

</script>

