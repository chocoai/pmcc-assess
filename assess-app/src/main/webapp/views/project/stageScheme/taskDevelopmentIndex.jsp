<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/math/6.0.2/math.js?v=${assessVersion}"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
</head>

<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <%@include file="/views/share/project/projectInfoSimple.jsp" %>
                    <%@include file="/views/share/project/projectPlanDetails.jsp" %>
                    <!-- 引入假设开发法模块 -->
                    <%@include file="/views/method/marketDevelopmentIndex.jsp" %>
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="card full-height">
                            <div class="card-body">
                                <input type="hidden" id="supportInfosJSON" value='${supportInfosJSON}'>
                                <form class="form-horizontal" id="md_development_form">
                                    <input type="hidden" name="id" value="${mdDevelopment.id}">
                                    <div class="row form-group">
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <div class="form-inline x-valid">
                                                <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                                                    单价
                                                </label>
                                                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                    <input type="text" class="form-control input-full"
                                                           data-rule-number="true" required
                                                           name="price" value="${mdDevelopment.price}">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <div class="form-inline x-valid">
                                                <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                                                    附件
                                                </label>
                                                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                    <input id="report_file" name="report_file" type="file"
                                                           multiple="false">
                                                    <div id="_report_file"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>


                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="card full-height">
                            <div class="card-body">
                                <%@include file="/views/share/form_apply.jsp" %>
                            </div>
                        </div>
                    </div>
                    <%@include file="/views/share/form_log.jsp" %>
                    <%@include file="/views/method/module/economicIndicators.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>

</body>

<script src="${pageContext.request.contextPath}/js/method/developmentCommon.js?v=${assessVersion}"></script>

<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/declare/declare.common.js?v=${assessVersion}"></script>

<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js?v=${assessVersion}"></script>
<%@include file="/views/method/module/developmentCommon.jsp" %>
<%@include file="/views/project/tool/rewardRate.jsp" %>
<%@include file="/views/method/module/economicIndicators.jsp" %>

<script>
    var landEngineering = {};
    landEngineering.target = $("#mdDevelopmentLandFrm");
    landEngineering.infrastructureChildrenTable = $("#landMdDevelopmentInfrastructureChildrenTable");
    landEngineering.infrastructureFooterHtml = "#landEngineeringMdDevelopmentInfrastructureFooter";
    landEngineering.incomeCategoryTable = $("#landIncomeCategoryTableId");
    landEngineering.incomeCategoryFooterHtml = "#landMdDevelopmentIncomeCategoryFooter";
    landEngineering.engineeringFeeInfoTarget = $("#landConstructionInstallationEngineeringFeeInfoTarget");
    landEngineering.fixed = 2; //小数点保留2位
    landEngineering.fixedMax = 4; //小数点保留4位
    landEngineering.fixedMin = 0; //小数点保留0位
    landEngineering.defaultType = '1';

    landEngineering.ajaxServerMethod = function (data, url, type, callback) {
        $.ajax({
            type: type,
            url: '${pageContext.request.contextPath}' + url,
            data: data,
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    if (result.errmsg) {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }else {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                    }
                }
            },
            error: function (result) {
                if (result.errmsg) {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                }else {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                }
            }
        });
    };

    landEngineering.typeData = function () {
        var target = $(landEngineering.target.selector);
        return target.find("input[name='type']").val();
    };
    landEngineering.masterId = developmentCommon.isNotBlank('${mdDevelopment.id}') ? '${mdDevelopment.id}' : '0';

    landEngineering.selectFun = function (copyId, box) {
        var target = $("#" + box);
        AlertConfirm("是否确认引用", "引用后可继续根据实际情况来编辑", function () {
            landEngineering.ajaxServerMethod({copyId: copyId, masterId: '${mdDevelopment.id}'},'/mdDevelopment/copyDevelopmentById',"post",function () {
                notifySuccess("成功","引用数据成功!");
                window.location.reload(true); //强制从服务器重新加载当前页面
                target.modal("hide");
            });
        });
    };

    landEngineering.calculationF22 = function (_this) {
        var item = $(_this).find('option:selected');
        var pid = item.attr('data-key');
        var type = item.attr('data-type');
        if (pid) {
            landEngineering.ajaxServerMethod({pid: pid, type: type},"/dataInfrastructureChildren/getDataList","get",function (data) {
                var arr = [];
                if (data.length == 0) {
                    return false;
                }
                $.each(data, function (i, n) {
                    var obj = {name: n.name, number: n.number, tax: n.tax};
                    obj.planDetailsId = '${projectPlanDetails.id}';
                    obj.type = landEngineering.typeData();
                    obj.pid = developmentCommon.isNotBlank('${mdDevelopment.id}') ? '${mdDevelopment.id}' : '0';
                    arr.push(obj);
                });
                developmentCommon.infrastructureChildren.saveArray(arr, function () {
                    notifySuccess("成功", "引用成功!");
                    landEngineering.infrastructureChildrenTable.bootstrapTable('refresh');
                    landEngineering.writeMdDevelopmentInfrastructureChildrenTable();
                });
            }) ;
        } else {
            notifyInfo("提示","无子项");
        }
    };

    /*建筑安装工程费*/
    landEngineering.constructionInstallationEngineeringFeeEvent = {
        detailsConstructionInstallation: function (id) {
            var mdCalculatingMethodEngineeringCost = landEngineering.engineeringFeeInfoTarget.bootstrapTable('getRowByUniqueId', id);
            developmentCommon.getMdArchitecturalObjById(mdCalculatingMethodEngineeringCost.architecturalObjId, function (item) {
                var target = $("#boxLandEngineering");
                var data = [];
                try {
                    data = JSON.parse(item.jsonContent);
                } catch (e) {
                    console.log("解析异常!");
                }
                landEngineering.constructionInstallationEngineeringFeeEvent.appendHTML(data, item.price);
                target.find("input[name='id']").val(item.id);
                target.find("input[name='masterId']").val(id);
            });
        },
        save: function () {
            var target = $("#boxLandEngineering");
            var table = target.find("table");
            var value = table.find("tfoot").find("input[name='totalPrice']").first().val();
            if (!$.isNumeric(value)) {
                notifyInfo("提示","请重新填写!");
                return false;
            }
            var obj = {};
            obj.masterId = target.find("input[name='masterId']").val();
            obj.price = Number(value);
            obj.id = target.find("input[name='id']").val();
            developmentCommon.saveMdArchitecturalObj2(developmentCommon.architecturalB.getFomData(table), obj, function (item) {
                notifySuccess("成功","保存成功!");
            });
            var mdCalculatingMethodEngineeringCost = landEngineering.engineeringFeeInfoTarget.bootstrapTable('getRowByUniqueId', obj.masterId);
            try {
                mdCalculatingMethodEngineeringCost.price = Number(obj.price) / Number(mdCalculatingMethodEngineeringCost.area);
            } catch (e) {
                console.log(e);
            }
            developmentCommon.saveMdCalculatingMethodEngineeringCost(mdCalculatingMethodEngineeringCost, function (data) {
                landEngineering.engineeringFeeInfoTarget.bootstrapTable('refresh');
                landEngineering.writeMdCalculatingMethodEngineeringCost(data);
            });
            target.modal("hide");
        },
        appendHTML: function (data, price) {
            var eleName = 'boxLandEngineering';
            var target = $("#" + eleName);
            var table = target.find("div[data-title=" + eleName + "]");
            table.empty() ;
            if (data == undefined || data == null || data == '') {
                data = [];
            }
            if (price == undefined) {
                price = '';
            }
            target.find("input[name='id']").val('');
            target.find("input[name='masterId']").val('');
            var type = landEngineering.typeData();
            var reckon = "b";
            if (landEngineering.defaultType == type) {
                reckon = "a";
            }
            var options = {
                target: table,
                obj: data,
                attribute: null,
                price: price,
                reckon: reckon
            };
            developmentCommon.architecturalB.init(options);
            target.modal("show");
        }
    };

    /**
     * 工程费表格加载
     */
    landEngineering.loadMdCalculatingMethodEngineeringCostTable = function () {
        var obj = {
            projectId: '${projectPlanDetails.projectId}',
            judgeObjectId: '${projectPlanDetails.judgeObjectId}',
            planDetailsId: '${projectPlanDetails.id}',
            type: landEngineering.typeData()
        };
        var cols = [];
        cols.push({
            field: 'id', title: '建筑安装工程费明细', width: "20%", formatter: function (value, row, index) {
                var str = '<button type="button" onclick="landEngineering.constructionInstallationEngineeringFeeEvent.detailsConstructionInstallation(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="建筑安装工程费明细">';
                str += '<i class="fa fa-cog"></i>';
                str += '</button>';
                return str;
            }
        });
        developmentCommon.loadMdCalculatingMethodEngineeringCostTable(landEngineering.engineeringFeeInfoTarget, obj, $("#toolbarMdCalculatingMethodEngineeringCostLand"), function () {
            landEngineering.writeMdCalculatingMethodEngineeringCost();
        }, cols);
    };

    /*工程费 show*/
    landEngineering.showMdCalculatingMethodEngineeringCost = function () {
        var target = $("#boxMdCalculatingMethodEngineeringCost");
        target.modal("show");
        var frm = target.find("form");
        frm.clearAll();
    };

    /*工程费 save*/
    landEngineering.saveMdCalculatingMethodEngineeringCost = function () {
        var target = $("#boxMdCalculatingMethodEngineeringCost");
        var frm = target.find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        data.judgeObjectId = '${projectPlanDetails.judgeObjectId}';
        data.projectId = '${projectPlanDetails.projectId}';
        data.planDetailsId = '${projectPlanDetails.id}';
        data.type = landEngineering.typeData();
        if (! data.id){
            data.price = "0";
        }
        developmentCommon.saveMdCalculatingMethodEngineeringCost(data, function (item) {
            landEngineering.writeMdCalculatingMethodEngineeringCost(item);
            target.modal("hide");
            if (! data.id){
                notifySuccess("成功", "添加成功!");
                //这里会同时生成 建筑安装工程费 详细情况id
                developmentCommon.saveMdArchitecturalObj2({}, {price: "0", pid: 0}, function (result) {
                    item.architecturalObjId = result.id;
                    developmentCommon.saveMdCalculatingMethodEngineeringCost(item);
                    landEngineering.loadMdCalculatingMethodEngineeringCostTable();
                });
            }else {
                notifyWarning("提示", "请重新打开工程树计算单价!");
                landEngineering.loadMdCalculatingMethodEngineeringCostTable();
            }
        });
    };

    /**
     * 设置工程费
     */
    landEngineering.setMdCalculatingMethodEngineeringCost = function (flag) {
        var planDetailsId = '${projectPlanDetails.id}';
        landEngineering.ajaxServerMethod({planDetailsId: planDetailsId, type: landEngineering.typeData(), flag: flag},"/mdDevelopment/setMdCalculatingMethodEngineeringCost","post",function () {
            notifySuccess("成功", "同步建筑安装工程费 成功!");
            landEngineering.loadMdCalculatingMethodEngineeringCostTable();
        }) ;
    };


    /*工程费和计算**/
    landEngineering.writeMdCalculatingMethodEngineeringCost = function (obj) {
        var arr = landEngineering.engineeringFeeInfoTarget.bootstrapTable('getData');
        if (obj) {
            arr.push(obj);
        }
        //js去重
        var result = [];
        var obj = {};
        for (var i = 0; i < arr.length; i++) {
            if (!obj[arr[i].id]) {
                result.push(arr[i]);
                obj[arr[i].id] = true;
            }
        }
        var price = math.bignumber(0);
        var k = 0;
        $.each(result, function (i, n) {
            if ($.isNumeric(n.price)) {
                k++;
                price = math.add(price, math.bignumber(n.price));
            }
        });
        price = Number(price.toString());
        if (k != 0) {
            price = price / k;
        }
        landEngineering.target.find("input[name='constructionInstallationEngineeringFee']").val(price.toFixed(landEngineering.fixed)).trigger('blur');
        landEngineering.target.find("input[name='reconnaissanceDesign']").trigger('blur');
    };

    /**
     * 工程费删除
     */
    landEngineering.delMdCalculatingMethodEngineeringCost = function () {
        var rows = landEngineering.engineeringFeeInfoTarget.bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            notifyInfo("提示","请选择要删除的数据");
        } else {
            AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
                developmentCommon.deleteMdCalculatingMethodEngineeringCostHandle(rows, function () {
                    notifySuccess("成功","删除成功!");
                    landEngineering.engineeringFeeInfoTarget.bootstrapTable('refresh');
                    landEngineering.writeMdCalculatingMethodEngineeringCost();
                });
            });

        }
    };

    landEngineering.editMdCalculatingMethodEngineeringCost = function() {
        var rows = landEngineering.engineeringFeeInfoTarget.bootstrapTable('getSelections');
        console.log(rows) ;
        if (!rows || rows.length != 1) {
            notifyWarning("警告", "选择一条数据进行编辑!");
        } else {
            var target = $("#boxMdCalculatingMethodEngineeringCost");
            target.modal("show");
            var frm = target.find("form");
            frm.clearAll();
            frm.initForm(rows[0]) ;
        }
    } ;

    /*土地还原率或者报酬率**/
    landEngineering.getRewardRate = function (_this) {
        var group = $(_this).closest('.input-group');
        rewardRateFunc.calculation(group.find('[name=rewardRateId]').val(), function (data) {
            if (data) {
                AssessCommon.elementParsePoint(group.find('[name=remunerationRate]').val(data.resultValue));
                group.find('[name=rewardRateId]').val(data.id);
                group.find('[name=remunerationRate]').val(data.resultValue).trigger('blur');
            }
        })
    };
    /*基础设施配套费  table load**/
    landEngineering.loadMdDevelopmentInfrastructureChildrenTable = function () {
        var pid = landEngineering.masterId;
        developmentCommon.infrastructureChildren.loadTable2(
            {planDetailsId: '${projectPlanDetails.id}', pid: pid},
            landEngineering.infrastructureChildrenTable,
            $("#toolbarMdDevelopmentInfrastructureChildrenTable")
        );
        landEngineering.writeMdDevelopmentInfrastructureChildrenTable();
    };
    /*基础设施配套费  table delete**/
    landEngineering.deleteMdDevelopmentInfrastructureChildrenTable = function (table) {
        var rows = $(table).bootstrapTable('getSelections');
        if (rows.length >= 1) {
            var data = [];
            $.each(rows, function (i, item) {
                data.push(item.id);
            });
            AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
                developmentCommon.infrastructureChildren.delete(data, function () {
                    notifySuccess("成功", "删除成功!");
                    landEngineering.infrastructureChildrenTable.bootstrapTable('refresh');
                    landEngineering.writeMdDevelopmentInfrastructureChildrenTable();
                });
            });
        } else {
            notifySuccess('成功','至少勾选一个!');
        }
    };
    /*基础设施配套费  table edit**/
    landEngineering.editMdDevelopmentInfrastructureChildrenTable = function (table, box, flag) {
        var target = $(box);
        var frm = target.find("form");
        var pid = landEngineering.masterId;
        if (flag) {
            var rows = $(table).bootstrapTable('getSelections');
            if (rows.length == 1) {
                var data = rows[0];
                frm.initForm(data);
                target.find(".modal-footer").empty().append($(landEngineering.infrastructureFooterHtml).html());
                target.modal('show');
            } else {
                notifyInfo("提示","至少勾选一个!");
            }
        } else {
            frm.clearAll();
            frm.initForm({pid: pid});
            target.find(".modal-footer").empty().append($(landEngineering.infrastructureFooterHtml).html());
            target.modal('show');
        }
    };
    /*基础设施配套费  table save**/
    landEngineering.saveMdDevelopmentInfrastructureChildrenTable = function (_this) {
        var target = $(_this).parent().parent().parent().parent();
        var frm = target.find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        data.planDetailsId = '${projectPlanDetails.id}';
        data.pid = landEngineering.masterId;
        developmentCommon.infrastructureChildren.save(data, function () {
            notifySuccess("成功", "添加成功!");
            target.modal('hide');
            landEngineering.infrastructureChildrenTable.bootstrapTable('refresh');
            landEngineering.writeMdDevelopmentInfrastructureChildrenTable();
        });
    };

    /*基础设施配套费  table 测算**/
    landEngineering.writeMdDevelopmentInfrastructureChildrenTable = function () {
        var pid = landEngineering.masterId;
        developmentCommon.infrastructureChildren.getDataList({
            planDetailsId: '${projectPlanDetails.id}',
            pid: pid
        }, function (item) {
            var result = 0;
            if (item.length >= 1) {
                $.each(item, function (i, n) {
                    result += Number(n.number);
                });
            }
            if ('${mdDevelopment.infrastructureCost}') {
                if (result == 0) {
                    result += Number('${mdDevelopment.infrastructureCost}');
                }
            }
            landEngineering.target.find("input[name='infrastructureCost']").val(result).trigger('blur');
        });
    };

    //经济指标show
    landEngineering.showMdDevelopmentIncomeCategory = function () {
        var economicId = '${mdDevelopment.economicId}';
        if (!economicId) {
            economicId = landEngineering.target.find("input[name='economicId']").val();
        }
        if (economicId) {
            economicIndicators.init({economicId: economicId});
        } else {
            economicIndicators.init({
                planDetailsId: '${projectPlanDetails.id}',
                saveCallback: function (economicId) {//经济指标id更新到中间表
                    var centerId = '${mdDevelopment.centerId}';
                    if (centerId) {
                        declareCommon.declareBuildCenterSaveAndUpdate({indicatorId: economicId, id: centerId});
                    }
                    landEngineering.target.find("input[name='economicId']").val(economicId).trigger('blur');
                },
                targetCallback: function () {
                    economicIndicators.autoSummary(true);
                }
            });
        }
        $('#modalEconomicIndicators').find('.modal-footer').find('button').last().bind('click', function () {
            var data = economicIndicators.getFormData();
            if (data) {
                development.initParcelSettingData(data);
                landEngineering.target.find("a[data-key='plannedBuildingArea']").html(data.plannedBuildingArea);
                landEngineering.target.find("a[data-key='totalSaleableAreaPrice']").html(data.totalSaleableAreaPrice);
                landEngineering.target.find("a[data-key='saleableArea']").html(data.saleableArea);

                landEngineering.target.find("input[name='plannedBuildingArea']").val(data.plannedBuildingArea).trigger('blur');
                landEngineering.target.find("input[name='totalSaleableAreaPrice']").val(data.totalSaleableAreaPrice).trigger('blur');
                landEngineering.target.find("input[name='saleableArea']").val(data.saleableArea).trigger('blur');
            }
        });
    };

</script>

<script>


    var development = {};

    development.config = {
        frm: "#mdDevelopmentLandFrm"
    };
    /**
     * 空字符串检测
     * @param item
     * @returns {boolean}
     */
    development.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    development.isNotBlankObject = function (obj) {
        for (var key in obj) {
            return true;
        }
        return false
    };

    function loadParamsValue(that, t) {
        var value = $(that).val();
        var i = 0;
        if (!development.isNotBlank(value)) {
            if (t != 'not') {
                $(that).attr("data-value", '0');
                $(that).val(0);
            }
        }
        if (development.isNotBlank(value)) {
            if ($.isNumeric(value)) {
                i++;
            }
            var reg = new RegExp(/^[0-9]+\.?[0-9]*%$/);
            if (reg.test(value)) {
                i++;
            }
            if (i == 0) {
                alert("不符合，必须是数字!");
                $(that).attr("data-value", '');
                $(that).val('');
                return false;
            }
        }
        var target = $(development.config.frm);
        calculationNumeric(formSerializeArray(target), function (data) {
            target.find("input[name='projectConstructionPeriod']").val(data.projectConstructionPeriod);

            target.find("td[name='constructionCostSubtotal']").html(data.constructionCostSubtotal);
            target.find("input[name='constructionCostSubtotal']").val(data.constructionCostSubtotal);

            target.find("td[name='interestInvestment']").html(data.interestInvestment);
            target.find("input[name='interestInvestment']").val(data.interestInvestment);

            target.find("td[name='investmentProfit']").html(data.investmentProfit);
            target.find("input[name='investmentProfit']").val(data.investmentProfit);


            target.find("td[name='assessPrice']").html(data.assessPrice);
            target.find("input[name='assessPrice']").val(data.assessPrice);
            try {
                if (data.assessPrice) {
                    if (data.price == 0) {
                        data.price = data.assessPrice;
                    }
                }
            } catch (e) {
            }
            target.find("td[name='price']").html(data.price);
            target.find("input[name='price']").val(data.price);
            if ($("#md_development_form").size() != 0) {
                $("#md_development_form").find("input[name='price']").val(data.price);
            }
        });
    }

    function checkParams(that) {
        var value = $(that).val();
        if (!development.isNotBlank(that)) {
            return false;
        }
        if (!development.isNotBlank(value)) {
            $(that).attr("data-value", '');
            $(that).val('');
        }
        loadParamsValue(that, 'not');
    }

    function calculationNumeric(data, callback) {
        $.ajax({
            type: "post",
            url: getContextPath() + "/mdDevelopment/calculationNumeric",
            data: {fomData: JSON.stringify(data)},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                }
            },
            error: function (result) {
                AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }


    development.valid = function (callback) {
        var frm = $(development.config.frm);
        if (!frm.valid()) {
            return false;
        }
        if (callback) {
            callback();
        }
    };

    development.getFomData = function () {
        var frm = $(development.config.frm);
        var data = formSerializeArray(frm);
        return data;
    };

    development.initData = function () {
        var frm = $(development.config.frm);
        if (!development.isNotBlank('${mdDevelopment.id}')) {
            $.ajax({
                type: "get",
                url: "${pageContext.request.contextPath}/dataTaxRateAllocation/specialTreatment",
                data: {
                    province: '${schemeAreaGroup.province}',
                    city: '${schemeAreaGroup.city}',
                    district: '${schemeAreaGroup.district}',
                    bisNationalUnity: true
                },
                success: function (result) {
                    if (result.ret) {
                        if (result.data) {
                            $.each(result.data, function (i, item) {
                                var taxRate = item.taxRate;
                                if (item.taxRate) {
                                    taxRate = Number(item.taxRate) * 100;
                                    taxRate = taxRate + "%";
                                }
                                if (item.typeName == '增值税') {
                                    if (item.taxRate) {
                                        frm.find("input[name='landValueAddedTax']").val(taxRate);
                                        frm.find("input[name='landValueAddedTax']").attr("data-value", item.taxRate);
                                    }
                                }
                                if (item.typeName == '契税') {
                                    if (item.taxRate) {
                                        frm.find("input[name='deedTaxRate']").val(taxRate);
                                        frm.find("input[name='deedTaxRate']").attr("data-value", item.taxRate);
                                    }
                                }
                                if (item.typeName == '所得税') {
                                    if (item.taxRate) {
                                        frm.find("input[name='projectDevelopmentIncomeTax']").val(taxRate);
                                        frm.find("input[name='projectDevelopmentIncomeTax']").attr("data-value", item.taxRate);
                                    }
                                }
                                if (item.typeName == '其它税费') {
                                    if (item.taxRate) {
                                        frm.find("input[name='otherEngineeringCost']").val(taxRate);
                                        frm.find("input[name='otherEngineeringCost']").attr("data-value", item.taxRate);
                                    }
                                }
                            });
                        }
                    } else {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);

                    }
                },
                error: function (result) {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + e);
                }
            });
        }
        landEngineering.loadMdDevelopmentInfrastructureChildrenTable();
        landEngineering.loadMdCalculatingMethodEngineeringCostTable();
    };

    development.writeValueEvent = function (value, callback) {
        var frm = $(development.config.frm);
        frm.find("input[name='type']").val(value);
        if (value == 1) {
            $(".showLandArea").show();
            var managementExpense = frm.find("input[placeholder='续建管理费率']");
            managementExpense.attr({placeholder: "管理费率"});
            managementExpense.closest(".form-group").find("label").first().text("管理费率");

            var landGetRelevant = frm.find("input[placeholder='在建工程修复费用']");
            landGetRelevant.attr({placeholder: "土地取得附加成本"});
            landGetRelevant.closest(".form-group").find("label").first().text("土地取得附加成本(万元)");

            var interestInvestmentTax = frm.find("input[placeholder='续建投资利息率']");
            interestInvestmentTax.attr({placeholder: "投资利息率"});
            interestInvestmentTax.closest(".form-group").find("label").first().text("投资利息率");

            var investmentProfitTax = frm.find("input[placeholder='续建投资利润率']");
            investmentProfitTax.attr({placeholder: "投资利润率"});
            investmentProfitTax.closest(".form-group").find("label").first().text("投资利润率");
        }
        if (value == 2) {
            $(".showLandArea").hide();
            var managementExpense = frm.find("input[placeholder='管理费率']");
            managementExpense.attr({placeholder: "续建管理费率"});
            managementExpense.closest(".form-group").find("label").first().text("续建管理费率");

            var landGetRelevant = frm.find("input[placeholder='土地取得附加成本']");
            landGetRelevant.attr({placeholder: "在建工程修复费用"});
            landGetRelevant.closest(".form-group").find("label").first().text("在建工程修复费用(万元)");

            var interestInvestmentTax = frm.find("input[placeholder='投资利息率']");
            interestInvestmentTax.attr({placeholder: "续建投资利息率"});
            interestInvestmentTax.closest(".form-group").find("label").first().text("续建投资利息率");

            var investmentProfitTax = frm.find("input[placeholder='投资利润率']");
            investmentProfitTax.attr({placeholder: "续建投资利润率"});
            investmentProfitTax.closest(".form-group").find("label").first().text("续建投资利润率");
        }
        if (callback) {
            callback(value);
        }
    };

    development.checkedFun = function (that, name, flag) {
        var form = $(that).closest("form");
        var target = form.find("[name='" + name + "']:checkbox");
        if (flag) {//全选或者全不选
            var number = 1;
            target.each(function (i, n) {
                if ($(this).prop("checked")) {
                    number++;
                }
            });
            if (number == 1) {
                target.prop("checked", true);
            } else {
                target.prop("checked", false);
            }
        } else {
            //首先让选中的失效
            target.each(function (i, n) {
                if ($(this).prop("checked")) {
                    $(this).prop("disabled", true);
                }
            });
            //然后让没有选中的元素设置为选中
            target.each(function (i, n) {
                if (!$(this).prop("checked")) {
                    $(this).prop("checked", true);
                }
            });
            //最后是让失效的元素恢复,并且使其不选中
            target.each(function (i, n) {
                if ($(this).prop("disabled")) {
                    $(this).prop("disabled", false);
                    $(this).prop("checked", false);
                }
            });
        }
    };

    development.initParcelSettingData = function (data) {
        var industrySupplyInfoContainer = $("#industrySupplyInfoContainer_BBBBB");
        var developmentDegreeContentContainer = $("#developmentDegreeContentContainer_BBBBB");
        industrySupplyInfoContainer.empty();
        developmentDegreeContentContainer.empty();
        //宗地外设定
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateLandInfrastructure, '', function (html, resultData) {
            var resultHtml = '<div>';
            var array = [];
            if (data) {
                if (data.parcelSettingOuter) {
                    array = data.parcelSettingOuter.split(',');
                }
            }
            resultHtml += "<div class='form-check' style='justify-content:left'>";
            $.each(resultData, function (i, item) {
                resultHtml += "<label class='form-check-label'>";
                resultHtml += "<input class='form-check-input' type='checkbox' name='parcelSettingOuter' ";
                if ($.inArray(item.id.toString(), array) > -1) {
                    resultHtml += ' checked="checked" ';
                }
                resultHtml += 'value="' + item.id + '">';
                resultHtml += "<span class='form-check-sign'>" + item.name + "</span>";
            });
            resultHtml += "</div>";
            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '全选或全不选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"development.checkedFun(this,'parcelSettingOuter',true)\">";
            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '反选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"development.checkedFun(this,'parcelSettingOuter',false)\">";
            industrySupplyInfoContainer.empty().append(resultHtml);
        }, true);
        //宗地内设定
        AssessCommon.loadDataDicByKey(AssessDicKey.estateDevelopment_degreePrepared_land, '', function (html, resultData) {
            var resultHtml = '<div>';
            var array = [];
            if (data) {
                if (data.parcelSettingInner) {
                    array = data.parcelSettingInner.split(',');
                }
            }
            resultHtml += "<div class='form-check' style='justify-content:left'>";
            $.each(resultData, function (i, item) {
                resultHtml += "<label class='form-check-label'>";
                resultHtml += "<input class='form-check-input' type='checkbox' name='parcelSettingInner' ";
                if ($.inArray(item.id.toString(), array) > -1) {
                    resultHtml += ' checked="checked" ';
                }
                resultHtml += 'value="' + item.id + '">';
                resultHtml += "<span class='form-check-sign'>" + item.name + "</span>";
            });
            resultHtml += "</div>";
            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '全选或全不选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"development.checkedFun(this,'parcelSettingInner',true)\">";
            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '反选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"development.checkedFun(this,'parcelSettingInner',false)\">";
            developmentDegreeContentContainer.empty().append(resultHtml);
        });
    };

    $(document).ready(function () {

        development.initData();

        var tool = $("#developmentCheckboxTool");
        var frm = $(development.config.frm);
        var type = '${mdDevelopment.type}';
        if (development.isNotBlank(type)) {
            tool.find("input[name=typeShow]").each(function (i, ele) {
                var element = $(ele);
                var value = element.attr('data-value');
                if (value == type) {
                    element.attr('checked', 'true');
                } else {
                    element.removeAttr("checked");
                }
            });
            development.writeValueEvent(type, function () {
                landEngineering.loadMdCalculatingMethodEngineeringCostTable();
                landEngineering.infrastructureChildrenTable.bootstrapTable('refresh');
            });
        } else {
            landEngineering.loadMdCalculatingMethodEngineeringCostTable();
        }

        tool.find("input[type='radio'][name='typeShow']").change(function () {
            var value = $(this).attr('data-value');
            development.writeValueEvent(value, function () {
                landEngineering.loadMdCalculatingMethodEngineeringCostTable();
            });
        });


        var data = {
            parcelSettingInner: '${mdDevelopment.parcelSettingInner}',
            parcelSettingOuter: '${mdDevelopment.parcelSettingOuter}'
        };
        development.initParcelSettingData(data);
    });

</script>

<script type="application/javascript">
    $(function () {
        FileUtils.uploadFiles({
            target: "report_file",
            disabledTarget: "btn_submit",
            formData: {
                tableName: AssessDBKey.MdDevelopment,
                tableId: '${mdDevelopment.id}',
                projectId: "${projectInfo.id}"
            },
            editFlag: true,
            deleteFlag: true
        });
        FileUtils.getFileShows({
            target: "report_file",
            formData: {
                tableName: AssessDBKey.MdDevelopment,
                tableId: '${mdDevelopment.id}'
            },
            editFlag: true,
            deleteFlag: true
        })
    });

    function submit() {
        var item = formSerializeArray($("#md_development_form"));
        var data = development.getFomData();
        development.valid(function () {
            if (item) {
                if ($.isNumeric(item.price)) {
                    data.price = item.price;
                }
            }
            if ("${processInsId}" != "0") {
                submitEditToServer(JSON.stringify(data));
            } else {
                submitToServer(JSON.stringify(data));
            }
        });
    }
</script>

</html>

