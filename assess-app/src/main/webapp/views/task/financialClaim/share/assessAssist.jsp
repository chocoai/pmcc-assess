<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="frm_assessAssist" class="form-horizontal">
    <input type="hidden" value="0" name="id">
    <div class="form-group">
        <label class="col-sm-1 control-label">
            评估方法<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" required
                       placeholder="评估方法" name="appraisalMethod" class="form-control">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            评估数量<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" required
                       placeholder="评估数量" data-rule-number='true' onblur="complex('pawnPrice','pawnArea','pawnAmount')" name="pawnArea" class="form-control">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            市场单价<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" required
                       placeholder="市场单价" data-rule-number='true' onblur="complex('pawnPrice','pawnArea','pawnAmount')" name="pawnPrice" class="form-control">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            市场价值判断
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="市场价值判断" readonly="readonly" name="pawnAmount" class="form-control">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            变现系数<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" required
                       placeholder="变现系数" data-rule-number='true' onblur="complex('pawnAmount','pawnRealizationCoefficient','pawnRealization')" name="pawnRealizationCoefficient" class="form-control">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            变现价值
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text"
                       placeholder="变现价值" readonly="readonly" name="pawnRealization" class="form-control">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            拍卖费系数<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text" required
                       placeholder="拍卖费系数" data-rule-number='true'
                       onblur="complex('pawnRealization','disposeAuctionCoefficient','disposeAuction')" name="disposeAuctionCoefficient"
                       class="form-control">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            拍卖费
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text"
                       placeholder="拍卖费" readonly="readonly" name="disposeAuction" class="form-control fee">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            诉讼费系数<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text" required
                       placeholder="诉讼费系数" data-rule-number='true' onblur="complex('pawnRealization','disposeLawsuitCoefficient','disposeLawsuit')" name="disposeLawsuitCoefficient"
                       class="form-control">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            诉讼费
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text"
                       placeholder="诉讼费" readonly="readonly" name="disposeLawsuit" class="form-control fee">
            </div>
        </div>

    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            执行费系数<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text" required
                       placeholder="执行费系数" data-rule-number='true' onblur="complex('pawnRealization','disposeExecuteCoefficient','disposeExecute')" name="disposeExecuteCoefficient"
                       class="form-control">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            执行费
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text"
                       placeholder="执行费" readonly="readonly" name="disposeExecute" class="form-control fee">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            司法鉴定费系数<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text" required
                       placeholder="司法鉴定费系数" data-rule-number='true' onblur="complex('pawnRealization','disposeAuthenticateCofficient','disposeAuthenticate')" name="disposeAuthenticateCofficient"
                       class="form-control">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            司法鉴定费
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text"
                       placeholder="司法鉴定费" readonly="readonly" name="disposeAuthenticate" class="form-control fee">
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label">
            其它费用系数<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text" required
                       placeholder="其它费用系数" data-rule-number='true' onblur="complex('pawnRealization','dispoetOtherCofficient','dispoetOther')" name="dispoetOtherCofficient" class="form-control">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            其它费用
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text"
                       placeholder="其它费用" readonly="readonly" name="dispoetOther" class="form-control fee">
            </div>
        </div>
        <input type="hidden" name="dispoetAmount" placeholder="处理相关费用小计">
        <label class="col-sm-1 control-label">
            契税及印花税系数<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text" required
                       placeholder="契税及印花税系数" data-rule-number='true' onblur="complex('pawnRealization','taxStampCofficient','taxStamp')" name="taxStampCofficient" class="form-control">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            契税及印花税
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text"
                       placeholder="契税及印花税" readonly="readonly" name="taxStamp" class="form-control tax">
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label">
            增值税及附加税系数<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text" required
                       placeholder="增值税及附加税系数" data-rule-number='true' onblur="complex('pawnRealization','taxAddedvalueCofficient','taxAddedvalue')" name="taxAddedvalueCofficient"
                       class="form-control">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            增值税及附加税
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text"
                       placeholder="增值税及附加税" readonly="readonly" name="taxAddedvalue" class="form-control tax">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            土地增值税系数<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text" required
                       placeholder="土地增值税系数" data-rule-number='true' onblur="complex('pawnRealization','taxLandCofficient','taxLand')" name="taxLandCofficient" class="form-control">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            土地增值税
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text"
                       placeholder="土地增值税" readonly="readonly" name="taxLand" class="form-control tax">
            </div>
        </div>
        <input type="hidden" name="taxAmount" placeholder="税金小计">
        <input type="hidden" name="costAmount" placeholder="费用合计（处置费用+税金）">
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            抵押物变现净收入额<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text" required
                       placeholder="抵押物变现净收入" data-rule-number='true' name="pawnRealizationValueAmount" class="form-control">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            预计追偿借款人一般净收入<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text" required
                       placeholder="预计追偿借款人一般净收入" data-rule-number='true' name="recoveryIncomeOrdinary" class="form-control">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            预计追偿全部保证人净收入<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text" required
                       placeholder="预计追偿全部保证人净收入" data-rule-number='true' name="recoveryIncomeAll" class="form-control">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            预计可追偿第三方净收入<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text" required
                       placeholder="预计可追偿第三方净收入" data-rule-number='true' name="recoveryIncomeThirdParty" class="form-control">
            </div>
        </div>


    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label">
            预计可追偿净收入合计<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text" required
                       placeholder="预计可追偿净收入合计" data-rule-number='true' name="recoveryIncomAmount" class="form-control">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            不良贷款预计受偿收加金额合计<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text" required
                       placeholder="不良贷款预计受偿收加金额合计" data-rule-number='true' name="recoveryBadLoans" class="form-control">
            </div>
        </div>
        <label class="col-sm-1 control-label">
            预计收回时间<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-2">
                <input type="text" required
                       placeholder="预计收回时间" data-date-format='yyyy-mm-dd' name="recoveryLimit" class="form-control dbdate">
            </div>
        </div>
    </div>
    <div class="form-group">
        <label
                class='col-sm-1 control-label'>附件</label>
        <div class='col-sm-11'>
            <input id="assessAssistUpload" name="assessAssistUpload" type="file" multiple="false">
            <div id="_assessAssistUpload">
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label">
            实际工时<span class="symbol required"></span>
        </label>
        <div class="x-valid">
            <div class="col-sm-3">
                <input type="text" required
                       placeholder="实际工时" data-rule-number='true' name="actualHours" class="form-control" maxlength="3">
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label">
            成果描述
        </label>
        <div class="x-valid">
            <div class="col-sm-11">
                                        <textarea required placeholder="成果描述" name="taskRemarks"
                                                  class="form-control"></textarea>
            </div>
        </div>
    </div>

    <div class="col-sm-4 col-sm-offset-5">
        <a href="javascript:;" class="btn btn-warning" onclick="assessAssistSubmit();">
            保存
        </a>
    </div>
</form>
<table id="tb_loadassessAssist" class="table table-bordered">
</table>
<script type="text/javascript">
    $(function () {
        loadassessAssist();
        FileUtils.uploadFiles({
            target: "assessAssistUpload",
            showFileList: false
        }, {
            onUpload: function (file) {//上传之前触发
                var formData = {
                    tableName: "tb_csr_calculation",
                    creater: "${currUserAccount}",
                    tableId: $("#frm_assessAssist [name$='id']").val()
                };
                $("#assessAssistUpload").data('uploadifive').settings.formData = formData;   //动态更改formData的值
            },
            onUploadComplete: function () {
                loadassessAssistFiles($("#frm_assessAssist [name$='id']").val());
            }
        });
    });
    function loadassessAssistFiles(tableId) {
        FileUtils.getFileShows({
            target: "assessAssistUpload",
            formData: {
                tableName: "tb_csr_calculation",
                creater: "${currUserAccount}",
                tableId: tableId
            },
            deleteFlag: true
        });
    }
    function loadassessAssist() {
        var cols = [];
        cols.push({field: 'id', title: 'id', visible: false});
        cols.push({field: 'appraisalMethod', title: '评估方法'});
        cols.push({field: 'pawnArea', title: '评估数量'});
        cols.push({field: 'pawnPrice', title: '市场单价'});
        cols.push({field: 'pawnAmount', title: '市场价值判断'});
        cols.push({field: 'pawnRealization', title: '变现价值'});
        cols.push({field: 'dispoetAmount', title: '处理相关费用小计'});
        cols.push({field: 'taxAmount', title: '税金小计'});
        cols.push({field: 'costAmount', title: '费用合计（处置费用+税金）'});
        cols.push({field: 'pawnRealizationValueAmount', title: '抵押物变现净收入额'});
        cols.push({field: 'recoveryIncomAmount', title: '预计可追偿净收入合计'});
        cols.push({field: 'recoveryBadLoans', title: '不良贷款预计受偿收回金额合计'});
        cols.push({field: 'recoveryLimit', title: '预计收回时间'});
        cols.push({
            field: 'opation', title: '操作',
            formatter: function (value, row, index) {

                var str = "<a onclick='loadassessAssistDeleteItems(" + row.id + ")' style='margin-left: 5px;' data-toggle='tooltip' data-placement='top' data-original-title='删除'  class='btn btn-xs btn-warning tooltips' ><i class='fa fa-minus fa-white'></i></a>";
                return str;
            }
        });
        TableInit("tb_loadassessAssist", "${pageContext.request.contextPath}/csrCalculation/getCsrCalculation", cols,
            {
                borrowerId: "${planDetailsParent.projectPhaseId}",
                detailsId: $("#assessAssist_details_id").val()
            }, {
                search: false,
                showRefresh: false,
                onClickRow: function (row) {
                    $("#frm_assessAssist").initForm(row);
                    loadassessAssistFiles(row.id);
                }
            });
    }
    function loadassessAssistDeleteItems(id) {
        Alert("确认要删除么,删除后数据将不可恢复？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/csrCalculation/deleteCsrCalculation",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        loadassessAssistReload();
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
    function feeAmount() {
        var objs = $("#frm_assessAssist .fee");
        var dispoetAmount = 0;
        $.each(objs, function (i, j) {
            if (!isNaN(parseFloat($(j).val()))) {
                dispoetAmount += parseFloat($(j).val());
            }
        })
        $("#frm_assessAssist [name$='dispoetAmount']").val(dispoetAmount.toFixed(2));
    }
    function taxAmount() {
        var objs = $("#frm_assessAssist .tax");
        var taxAmount = 0;
        $.each(objs, function (i, j) {
            if (!isNaN(parseFloat($(j).val()))) {
                taxAmount += parseFloat($(j).val());
            }
        })
        $("#frm_assessAssist [name$='taxAmount']").val(taxAmount.toFixed(2));
    }

    function complex(a, b, c) {

        //a*b=c
        var multiplicand = parseFloat($("#frm_assessAssist [name$='" + a + "']").val());//被乘数
        if (isNaN(multiplicand)) {
            multiplicand = 0;
        }
        var multiplier = parseFloat($("#frm_assessAssist [name$='" + b + "']").val());//乘数
        if (isNaN(multiplier)) {
            multiplier = 0;
        }
        var product = multiplicand * multiplier;//积
        $("#frm_assessAssist [name$='" + c + "']").val(product.toFixed(2));
    }

    function loadassessAssistReload() {
        TableReload("tb_loadassessAssist");
    }
    function assessAssistSubmit() {
        if (!$("#frm_assessAssist").valid()) {
            return false;
        }
        Loading.progressShow();
        feeAmount();//计算费用合计
        taxAmount();//计算税金合计
        var dispoetAmount = parseFloat($("#frm_assessAssist [name$='dispoetAmount']").val());
        var taxAmountF = parseFloat($("#frm_assessAssist [name$='taxAmount']").val());
        var costAmount = dispoetAmount + taxAmountF;
        $("#frm_assessAssist [name$='costAmount']").val(costAmount.toFixed(2));

        var data = formParams("frm_assessAssist");
        data["bisImport"] = false;
        data["borrowerId"] = "${planDetailsParent.projectPhaseId}";//该项业务特殊，存储的内容为客户编号
        data["detailsId"] = $("#assessAssist_details_id").val();

        //costAmount
        $.ajax({
            url: "${pageContext.request.contextPath}/csrCalculation/saveLoanCalculation",
            data: data,
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('保存成功');
                    loadassessAssistReload();
                    var actualHours = $("#frm_assessAssist [name$='actualHours']").val();
                    var taskRemarks = $("#frm_assessAssist [name$='taskRemarks']").val();
                    $("#frm_assessAssist").clearAll();
                    $("#frm_assessAssist").validate();
                    $("#frm_assessAssist [name$='actualHours']").val(actualHours);
                    $("#frm_assessAssist [name$='taskRemarks']").val(taskRemarks);
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }
</script>