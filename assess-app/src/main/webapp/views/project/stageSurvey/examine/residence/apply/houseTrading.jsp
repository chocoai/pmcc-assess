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
                <label class="col-sm-1 control-label">财产范围<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="财产范围" required="required"
                           name="scopeProperty" class="form-control" value="${surveyExamineDataInfoVo.examineHouseTradingVo.scopeProperty}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">融资条件<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="融资条件" required="required"
                           name="financingConditions" class="form-control" value="${surveyExamineDataInfoVo.examineHouseTradingVo.financingConditions}">
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
                    <select class="form-control search-select select2 normalTransaction" name="normalTransaction"
                            required="required">
                    </select>
                </div>
            </div>
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

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">交易时间<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input required="required" placeholder="交易时间"
                           name="tradingTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate" readonly="readonly"
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.tradingTime}">
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
                <label class="col-sm-1 control-label">买方支付的额外税费</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="买方支付的额外税费"
                           name="buyerExtraTaxFee" class="form-control">
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
                           name="totalSale" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group ExamineHouseTradingLease" style="display: none">
            <div class="x-valid">
                <label class="col-sm-1 control-label">承租方支付的额外税费</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="承租方支付的额外税费"
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.rentingExtraTaxFee}"
                           name="rentingExtraTaxFee"
                           class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">押金</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="押金"  class="form-control" name="deposit" value="${surveyExamineDataInfoVo.examineHouseTradingVo.deposit}">
                </div>
            </div>

        </div>

        <div class="form-group" id="ExamineHouseTradingLeaseAndSellTableSondiv" style="display: none">
            <div class="x-valid">
                <div class="col-sm-1" style="text-align: right;">
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
                <label class="col-sm-1 control-label">信息来源分类<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 informationType" name="informationType"
                            required="required">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">信息来源<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="信息来源" required="required"
                           value="${surveyExamineDataInfoVo.examineHouseTradingVo.information}"
                           name="information"
                           class="form-control">
                </div>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript">
    $(function () {
        ContainerFunForValid.push(ExamineHouseTrading.valid);//数据验证方法写入容器
        ContainerFunForGetData.push(ExamineHouseTrading.getFormData);//获取数据方法写入容器
        ContainerFunForInit.house.push(examineHouseTrading.prototype.init);//初始化方法写入容器
    });
</script>

<script type="text/javascript">
    var examineHouseTrading = function () {

    };
    examineHouseTrading.prototype = {
        saveView:function () {
            //examineHouseTrading.prototype.select2InitMethodWrite("${surveyExamineDataInfoVo.examineHouseTradingVo.taxBurden}","taxBurden");
            examineHouseTrading.prototype.select2InitMethodWrite("${surveyExamineDataInfoVo.examineHouseTradingVo.normalTransaction}","normalTransaction");
            examineHouseTrading.prototype.select2InitMethodWrite("${surveyExamineDataInfoVo.examineHouseTradingVo.descriptionType}","descriptionType");
            examineHouseTrading.prototype.select2InitMethodWrite("${surveyExamineDataInfoVo.examineHouseTradingVo.tradingType}","tradingType");
            examineHouseTrading.prototype.select2InitMethodWrite("${surveyExamineDataInfoVo.examineHouseTradingVo.informationType}","informationType");
        },
        select2InitMethodWrite:function (data,name) {
            console.log(""+data+" "+name);
            if (examineHouseTrading.prototype.isEmpty(data)){
                if (examineHouseTrading.prototype.isEmpty(name)){
                    $("#"+examineHouseTrading.prototype.config().frm).find("select."+name).val(data).trigger("change");
                }
            }else {
                if (examineHouseTrading.prototype.isEmpty(name)){
                    $("#"+examineHouseTrading.prototype.config().frm).find("select."+name).val(null).trigger("change");
                }
            }
        },
        isEmpty:function (data) {
            if (data){
                return true;
            }
            return false;
        },
        config: function () {
            return {
                frm: "frm_houseTrading",
                frmSon: "frm_ExamineHouseTradingLeaseAndSell",
                divBoxSon: "divBoxExamineHouseTradingLeaseAndSell",
                tableSon: "ExamineHouseTradingLeaseAndSellTableSon",
                totalSale:"totalSale",
                examineHouseTradingSellID: "ExamineHouseTradingSell",//根据 ExamineHouseTradingSellAndLeaseDtoTypeEnum配置(key需要与数据字典配置一致)
                examineHouseTradingLeaseID: "ExamineHouseTradingLease"
            };
        },
        //子类列表加载
        subLoadList:function (type_) {
            var cols = [];
            if (type_ == examineHouseTrading.prototype.config().examineHouseTradingSellID) {
                cols.push({field: 'instalmentInterest', title: '分期支付时间起'});
                cols.push({field: 'instalmentPeriodStartName', title: '分期支付时间止'});
                cols.push({field: 'instalmentPeriodEndName', title: '分期支付利息'});
            }
            if (type_ == examineHouseTrading.prototype.config().examineHouseTradingLeaseID) {
                cols.push({field: 'rentGrowthRate', title: '租金增长比率'});
                cols.push({field: 'rentPaymentTimeStartName', title: '租金支付时间起'});
                cols.push({field: 'rentPaymentTimeEndName', title: '租金支付时间止'});
            }
            var data = {} ;
            if ($("#declareId").size() > 0){
                data.declareId = $("#declareId").val();
            }
            if ($("#planDetailsId").size() > 0){
                data.planDetailsId = $("#planDetailsId").val();
            }
            if ($("#examineType").size() > 0){
                data.examineType = $("#examineType").val();
            }
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += "<a class='btn btn-xs btn-warning tooltips' data-placement='top' data-original-title='删除' "+"onclick=examineHouseTrading.prototype.subRemove("+row.id+",'"+row.tradingType+"'"+")"+">"  ;
                    str += "<i class='fa fa-minus fa-white'>" +"</i>" ;
                    str += "</a>";
                    str += '</div>';
                    return str;
                }
            });
            $("#" + examineHouseTrading.prototype.config().tableSon).bootstrapTable('destroy');
            TableInit(examineHouseTrading.prototype.config().tableSon, "${pageContext.request.contextPath}/examineHouse/getExamineHouseTradingSellAndLeaseDtoList", cols, {
                type: type_,examineType:data.examineType,declareId:data.declareId,planDetailsId:data.planDetailsId
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        //子类 删除
        subRemove:function (id,type) {
            console.log("id:"+id+" type:"+type);
            $.ajax({
                url: "${pageContext.request.contextPath}/examineHouse/deleteExamineHouseTradingSellAndLeaseDtoById",
                type: "post",
                dataType: "json",
                data: {id:id,type:type},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('删除成功');
                        examineHouseTrading.prototype.subLoadList(type);
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        },
        subShowModel:function () {
            var tradingID = $("#" + examineHouseTrading.prototype.config().frm + " .tradingType").eq(1).val();
            var tradingType = null;
            $.ajax({
                url: "${pageContext.request.contextPath}/examineHouse/getBaseDataDicById",
                type: "get",
                data:{id:tradingID},
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        tradingType = data.fieldName;
                        $("#" + examineHouseTrading.prototype.config().frmSon).clearAll();
                        $("#" + examineHouseTrading.prototype.config().divBoxSon).modal("show");
                        $("#" + examineHouseTrading.prototype.config().frmSon + " .type").val(tradingType);
                        if (tradingType == examineHouseTrading.prototype.config().examineHouseTradingLeaseID) {
                            $("#" + examineHouseTrading.prototype.config().divBoxSon + " .lease").show();
                            $("#" + examineHouseTrading.prototype.config().divBoxSon + " .sell").hide();
                            $("#" + examineHouseTrading.prototype.config().divBoxSon + " .modal-title").html("出租信息");
                        }
                        if (tradingType == examineHouseTrading.prototype.config().examineHouseTradingSellID) {
                            $("#" + examineHouseTrading.prototype.config().divBoxSon + " .lease").hide();
                            $("#" + examineHouseTrading.prototype.config().divBoxSon + " .sell").show();
                            $("#" + examineHouseTrading.prototype.config().divBoxSon + " .modal-title").html("出售信息");
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        },
        //子类添加
        subSave: function () {
            if (!$("#" + examineHouseTrading.prototype.config().frmSon).valid()) {
                return false;
            }
            var data = formParams(examineHouseTrading.prototype.config().frmSon);
            if ($("#declareId").size() > 0) {
                data.declareId = $("#declareId").val();
            }
            if ($("#examineType").size() > 0) {
                data.examineType = $("#examineType").val();
            }
            if ($("#planDetailsId").size() > 0){
                data.planDetailsId = $("#planDetailsId").val();
            }
            if ($("#" + examineHouseTrading.prototype.config().frmSon + " .type").size() > 0) {
                data.tradingType = data.type;
            }
            var url = "${pageContext.request.contextPath}/examineHouse/saveAndUpdatetExamineHouseTradingSellAndLeaseDto";
            $.ajax({
                url: url,
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $("#" + examineHouseTrading.prototype.config().divBoxSon).modal("hide");
                        examineHouseTrading.prototype.subLoadList(data.type);
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

        init: function () {
            //第一次加载
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseTransactionType,"",function (html,data) {
                $("#" + examineHouseTrading.prototype.config().frm).find("select.tradingType").html(html);
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseDescriptionType,"",function (html,data) {
                $("#" + examineHouseTrading.prototype.config().frm).find("select.descriptionType").html(html);
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHousetaxBurden,"${surveyExamineDataInfoVo.examineHouseTradingVo.taxBurden}",function (html,data) {
                $("#" + examineHouseTrading.prototype.config().frm).find("select.taxBurden").html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseNormalTransaction,"",function (html,data) {
                $("#" + examineHouseTrading.prototype.config().frm).find("select.normalTransaction").html(html);
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePaymentMethod,"",function (html,data) {
                $("#" + examineHouseTrading.prototype.config().frm).find("select.paymentMethod").html(html);
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseClassificationInformationSources,"",function (html,data) {
                $("#" + examineHouseTrading.prototype.config().frm).find("select.informationType").html(html);
            });
            $("#" + examineHouseTrading.prototype.config().frm + " .tradingType").change(function () {
                var tradingID = $("#" + examineHouseTrading.prototype.config().frm + " .tradingType").eq(1).val();
                var tradingType = null;
                AssessCommon.getDataDicInfo(tradingID,function (data) {
                    tradingType = data.fieldName;
                    if (tradingType == examineHouseTrading.prototype.config().examineHouseTradingLeaseID) {
                        $("#"+examineHouseTrading.prototype.config().tableSon+"div").show();
                        $("#"+examineHouseTrading.prototype.config().frm +" ."+examineHouseTrading.prototype.config().examineHouseTradingSellID).hide();
                        $("#"+examineHouseTrading.prototype.config().frm +" ."+examineHouseTrading.prototype.config().examineHouseTradingLeaseID).show();
                        examineHouseTrading.prototype.subLoadList(examineHouseTrading.prototype.config().examineHouseTradingLeaseID);
                    }
                    if (tradingType == examineHouseTrading.prototype.config().examineHouseTradingSellID) {
                        $("#"+examineHouseTrading.prototype.config().tableSon+"div").hide();
                        $("#"+examineHouseTrading.prototype.config().frm +" ."+examineHouseTrading.prototype.config().examineHouseTradingSellID).show();
                        $("#"+examineHouseTrading.prototype.config().frm +" ."+examineHouseTrading.prototype.config().examineHouseTradingLeaseID).hide();
                        // examineHouseTrading.prototype.subLoadList(examineHouseTrading.prototype.config().examineHouseTradingSellID);
                    }
                })
            });
            $("#" + examineHouseTrading.prototype.config().frm + " .paymentMethod").change(function () {
                var id = $("#" + examineHouseTrading.prototype.config().frm + " .paymentMethod").eq(1).val();
                AssessCommon.getDataDicInfo(id,function (data){
                    if (data.name == '一次性'){
                        $("."+examineHouseTrading.prototype.config().totalSale).show();
                        $("#"+examineHouseTrading.prototype.config().tableSon+"div").hide();
                    }
                    if (data.name == '分期付款'){
                        $("."+examineHouseTrading.prototype.config().totalSale).hide();
                        $("#"+examineHouseTrading.prototype.config().tableSon+"div").show();
                        examineHouseTrading.prototype.subLoadList(examineHouseTrading.prototype.config().examineHouseTradingSellID);
                    }
                });
            });
            $("#" + examineHouseTrading.prototype.config().frm + " .taxBurden").change(function () {
                var id = $("#" + examineHouseTrading.prototype.config().frm + " .taxBurden").eq(1).val();
                if (examineHouseTrading.prototype.isEmpty(id)){
                    AssessCommon.getDataDicInfo(id,function (item){
                       var name = item.name;
                        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseNormalTransaction,"",function (html,data) {
                            if (name == '正常承担'){
                                $.each(data,function (i,n) {
                                    if (n.name == "正常"){
                                        examineHouseTrading.prototype.select2InitMethodWrite(n.id,"normalTransaction");
                                    }
                                });
                            }else {
                                $.each(data,function (i,n) {
                                    if (n.name == "不正常"){
                                        examineHouseTrading.prototype.select2InitMethodWrite(n.id,"normalTransaction");
                                    }
                                });
                            }
                        });
                    });
                }
            });
            examineHouseTrading.prototype.saveView();
        }
    };

    (function ($) {
        //避免方法重复，定义全局变量
        var examineHouseTradingV = {
            //验证
            valid: function () {
                var options={
                    msg:"请检查【房屋交易信息】填写的信息",
                    hiddenValid:true
                };
                return $("#" + examineHouseTrading.prototype.config().frm).valid(options);
            },
            //获取需要保存的数据
            getFormData: function () {
                var data = formParams(examineHouseTrading.prototype.config().frm);
                data.declareId = $("#declareId").val();
                data.planDetailsId = $("#planDetailsId").val();
                data.examineType = $("#examineType").val();
                var keyValueDto = {};
                keyValueDto.key = $("#" + examineHouseTrading.prototype.config().frm).find('[data-name="fieldName"]').val();
                keyValueDto.value = data;
                return keyValueDto;
            }
        };
        window.ExamineHouseTrading = examineHouseTradingV;
    })(jQuery)
</script>

<div id="divBoxExamineHouseTradingLeaseAndSell" class="modal fade bs-example-modal-lg" data-backdrop="static"
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
            <form id="frm_ExamineHouseTradingLeaseAndSell" class="form-horizontal">
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
                    <button type="button" class="btn btn-primary" onclick="examineHouseTrading.prototype.subSave()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>