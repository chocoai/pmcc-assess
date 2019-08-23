<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    var construction = {};

    construction.target = $("#constructionFrm");
    construction.fixed = 2; //小数点保留2位
    construction.fixedMax = 4; //小数点保留4位
    construction.fixedMin = 0; //小数点保留0位
    construction.infrastructureChildrenTable = "#landMdCostConstructionChildrenTable";
    construction.infrastructureFooterHtml = "#underConstructionMdDevelopmentInfrastructureFooterX";
    construction.engineeringFeeInfoTarget = "#engineeringConstructionInstallationEngineeringFeeInfoTarget";
    construction.type = 'engineering' ;

    construction.callCompareMethod = function (this_) {
        var mcId = construction.target.find('[name=mcId]').val();
        var frame = layer.open({
            type: 2,
            title: '比较法',
            shadeClose: true,
            shade: true,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: '${pageContext.request.contextPath}/marketCompare/index?isLand=true&mcId=' + mcId + '&judgeObjectId=${projectPlanDetails.judgeObjectId}',
            cancel: function (index, layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
                if (iframe && iframe.marketCompare && iframe.marketCompare.mcId) {
                    construction.target.find('[name=mcId]').val(iframe.marketCompare.mcId);
                }
            },
            btnAlign: 'c',
            btn: ['保存', '关闭'],
            yes: function (index, layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
                iframe.saveResult(function (mcId, price) {
                    construction.target.find('[name=mcId]').val(mcId);
                    construction.target.find('[name=landPurchasePrice]').val(price);
                    layer.closeAll('iframe');
                });
            },
            btn2: function (index, layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
                if (iframe && iframe.marketCompare && iframe.marketCompare.mcId) {
                    construction.target.find('[name=mcId]').val(iframe.marketCompare.mcId);
                }
            }
        });
        layer.full(frame);
    };

    construction.calculationE6 = function () {
        //=D3*D6/10000
        var d3 = construction.target.find("input[name='developLandAreaTax']").val();
        var d6 = construction.target.find("input[name='landPurchasePrice']").val();
        if (!AssessCommon.isNumber(d3)) {
            return false;
        }
        if (!AssessCommon.isNumber(d6)) {
            return false;
        }
        var c = math.chain(Number(d3)).multiply(Number(d6)).divide(10000);
        construction.target.find("input[name='e6']").val(c.value.toFixed(construction.fixed)).trigger('blur');
    };

    construction.calculationE7 = function () {
        //=ROUND(E6*D7,2)
        var d7 = construction.target.find("input[name='landGetRelevant']").attr("data-value");
        var e6 = construction.target.find("input[name='e6']").val();
        if (!AssessCommon.isNumber(e6)) {
            return false;
        }
        if (!AssessCommon.isNumber(d7)) {
            return false;
        }
        var c = math.chain(e6).multiply(Number(d7));
        construction.target.find("input[name='e7']").val(c.value.toFixed(construction.fixed)).trigger('blur');
    };

    construction.calculationD8 = function () {
        //=E8/D3*10000
        var d3 = construction.target.find("input[name='developLandAreaTax']").val();
        var e8 = construction.target.find("input[name='additionalCostLandAcquisition']").val();
        if (!AssessCommon.isNumber(d3)) {
            return false;
        }
        if (!AssessCommon.isNumber(e8)) {
            return false;
        }
        var c = math.chain(e8).divide(d3).multiply(10000);
        construction.target.find("input[name='d8']").val(c.value.toFixed(construction.fixed)).trigger('blur');
    };

    construction.calculationLandGetCostTotalE9 = function () {
        //=SUM(E6:F8)
        var e6 = construction.target.find("input[name='e6']").val();
        var e7 = construction.target.find("input[name='e7']").val();
        var e8 = construction.target.find("input[name='additionalCostLandAcquisition']").val();
        if (!AssessCommon.isNumber(e6)) {
            return false;
        }
        if (!AssessCommon.isNumber(e7)) {
            return false;
        }
        if (!AssessCommon.isNumber(e8)) {
            return false;
        }
        var c = math.chain(e6).add(e7).add(e8);
        c = c.value.toFixed(construction.fixed);
        construction.target.find("input[name='landGetCostTotal']").val(c).trigger('blur');
        construction.target.find(".landGetCostTotal").html(c);
    };

    construction.calculationE12 = function () {
        //=F3*D12/10000
        var f3 = construction.target.find("input[name='developBuildAreaTax']").val();
        var d12 = construction.target.find("input[name='constructionInstallationEngineeringFee']").val();
        if (!AssessCommon.isNumber(f3)) {
            return false;
        }
        if (!AssessCommon.isNumber(d12)) {
            return false;
        }
        var c = math.chain(Number(f3)).multiply(d12).divide(10000);
        construction.target.find("input[name='e12']").val(c.value.toFixed(construction.fixed)).trigger('blur');
    };

    construction.calculationE11 = function () {
        //=IF(E12=" "," ",ROUND(E12*D11,2))
        var e12 = construction.target.find("input[name='e12']").val();
        var d11 = construction.target.find("input[name='reconnaissanceDesign']").attr("data-value");
        if (!AssessCommon.isNumber(e12)) {
            return false;
        }
        if (!AssessCommon.isNumber(d11)) {
            return false;
        }
        var c = math.chain(e12).multiply(d11);
        construction.target.find("input[name='e11']").val(c.value.toFixed(construction.fixed)).trigger('blur');
    };

    construction.calculationE13 = function () {
        //=$F$3*D13/10000
        var d13 = construction.target.find("input[name='infrastructureCost']").val();
        var f3 = construction.target.find("input[name='developBuildAreaTax']").val();
        if (!AssessCommon.isNumber(d13)) {
            return false;
        }
        if (!AssessCommon.isNumber(f3)) {
            return false;
        }
        var c = math.chain(d13).multiply(f3).divide(10000);
        construction.target.find("input[name='e13']").val(c.value.toFixed(construction.fixed)).trigger('blur');
    };

    construction.calculationE13Select = function (_this) {
        var item = $(_this).find('option:selected');
        var pid = item.attr('data-key');
        var type = item.attr('data-type');
        if (pid) {
            $.ajax({
                url: "${pageContext.request.contextPath}/dataInfrastructureChildren/getDataList",
                type: "get",
                dataType: "json",
                data: {pid: pid, type: type},
                success: function (result) {
                    if (result.ret) {
                        var arr = [];
                        var data = result.data;
                        if (data.length == 0) {
                            return false;
                        }
                        $.each(data, function (i, n) {
                            var obj = {name: n.name, number: n.number, tax: n.tax};
                            obj.planDetailsId = '${projectPlanDetails.id}';
                            obj.type = construction.type;
                            obj.pid = developmentCommon.isNotBlank('${mdCostConstruction.id}') ? '${mdCostConstruction.id}' : '0';
                            arr.push(obj);
                        });
                        developmentCommon.infrastructureChildren.saveArray(arr, function () {
                            toastr.success('添加成功!');
                            $(construction.infrastructureChildrenTable).bootstrapTable('refresh');
                            construction.writeMdDevelopmentInfrastructureChildrenTable();
                        });
                    }
                    else {
                        Alert("失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        } else {
            toastr.success('无子项!');
        }
    };

    construction.calculationE14 = function () {
        //=$F$3*D14/10000
        var d14 = construction.target.find("input[name='infrastructureMatchingCost']").val();
        var f3 = construction.target.find("input[name='developBuildAreaTax']").val();
        if (!AssessCommon.isNumber(d14)) {
            return false;
        }
        if (!AssessCommon.isNumber(f3)) {
            return false;
        }
        var c = math.chain(d14).multiply(f3).divide(10000);
        construction.target.find("input[name='e14']").val(c.value.toFixed(construction.fixed)).trigger('blur');
    };

    construction.calculationE15 = function () {
        //=$F$3*D15/10000
        var d15 = construction.target.find("input[name='devDuring']").val();
        var f3 = construction.target.find("input[name='developBuildAreaTax']").val();
        if (!AssessCommon.isNumber(d15)) {
            return false;
        }
        if (!AssessCommon.isNumber(f3)) {
            return false;
        }
        var c = math.chain(d15).multiply(f3).divide(10000);
        construction.target.find("input[name='e15']").val(c.value.toFixed(construction.fixed)).trigger('blur');
    };

    construction.calculationE15Select = function (_this) {
        var value = $(_this).val();
        if (value) {
            construction.target.find("input[name='devDuring']").val(value).trigger('blur');
        }
    };

    construction.calculationE16 = function () {
        //=$F$3*D16/10000
        var d16 = construction.target.find("input[name='otherEngineeringCost']").val();
        var f3 = construction.target.find("input[name='developBuildAreaTax']").val();
        if (!AssessCommon.isNumber(d16)) {
            return false;
        }
        if (!AssessCommon.isNumber(f3)) {
            return false;
        }
        var c = math.chain(d16).multiply(f3).divide(10000);
        construction.target.find("input[name='e16']").val(c.value.toFixed(construction.fixed)).trigger('blur');
    };

    construction.calculationE17 = function () {
        //=SUM(E11:F16)
        var e11 = construction.target.find("input[name='e11']").val();
        var e12 = construction.target.find("input[name='e12']").val();
        var e13 = construction.target.find("input[name='e13']").val();
        var e14 = construction.target.find("input[name='e14']").val();
        var e15 = construction.target.find("input[name='e15']").val();
        var e16 = construction.target.find("input[name='e16']").val();
        if (!AssessCommon.isNumber(e11)) {
            return false;
        }
        if (!AssessCommon.isNumber(e12)) {
            return false;
        }
        if (!AssessCommon.isNumber(e13)) {
            return false;
        }
        if (!AssessCommon.isNumber(e14)) {
            return false;
        }
        if (!AssessCommon.isNumber(e15)) {
            return false;
        }
        if (!AssessCommon.isNumber(e16)) {
            return false;
        }
        var c = math.chain(e11).add(e12).add(e13).add(e14).add(e15).add(e16);
        c = c.value.toFixed(construction.fixed);
        construction.target.find("input[name='constructionSubtotal']").val(c).trigger('blur');
        construction.target.find(".constructionSubtotal").html(c);
    };

    construction.calculationE18 = function () {
        //=IF(E17=" "," ",ROUND(E17*D18,2))
        var e17 = construction.target.find("input[name='constructionSubtotal']").val();
        var d18 = construction.target.find("input[name='unforeseenExpenses']").attr("data-value");
        var c = math.chain(e17).multiply(d18);
        construction.target.find("input[name='e18']").val(c.value.toFixed(construction.fixed)).trigger('blur');
    };

    construction.calculationE19 = function () {
        //=IF(E17=" "," ",(E9+E17+E18)*D19)
        var e9 = construction.target.find("input[name='landGetCostTotal']").val();
        var e17 = construction.target.find("input[name='constructionSubtotal']").val();
        var e18 = construction.target.find("input[name='e18']").val();
        var d19 = construction.target.find("input[name='managementExpense']").attr("data-value");
        if (!AssessCommon.isNumber(e9)) {
            return false;
        }
        if (!AssessCommon.isNumber(e17)) {
            return false;
        }
        if (!AssessCommon.isNumber(e18)) {
            return false;
        }
        if (!AssessCommon.isNumber(d19)) {
            return false;
        }
        var c = math.multiply(math.chain(e9).add(e17).add(e18).done(), d19);
        construction.target.find("input[name='e19']").val(c.toFixed(construction.fixed)).trigger('blur');
    };

    construction.calculationE21 = function () {
        //=IF(F3=" "," ",(E9+E11+E13)*((1+D21)^H3-1)+(E12+E14+E15+E16+E18+E19)*((1+D21)^(H3/2)-1))
        var e9 = construction.target.find("input[name='landGetCostTotal']").val();
        var e11 = construction.target.find("input[name='e11']").val();
        var e12 = construction.target.find("input[name='e12']").val();
        var e13 = construction.target.find("input[name='e13']").val();
        var e14 = construction.target.find("input[name='e14']").val();
        var e15 = construction.target.find("input[name='e15']").val();
        var e16 = construction.target.find("input[name='e16']").val();
        var e18 = construction.target.find("input[name='e18']").val();
        var e19 = construction.target.find("input[name='e19']").val();
        var d21 = construction.target.find("input[name='interestInvestmentTax']").attr("data-value");
        var h3 = construction.target.find("input[name='developYearNumberTax']").val();

        try {
            var a = Math.pow(1 + math.number(d21), math.number(h3));
            var c1 = math.multiply(math.chain(e9).add(e11).add(e13).done(), math.chain(a).subtract(1).done());
            var b = Math.pow(1 + math.number(d21), math.number(h3) / 2);
            var c2 = math.multiply(math.chain(e12).add(e14).add(e15).add(e16).add(e18).add(e19).done(), math.chain(b).subtract(1).done());
            var c = math.add(c1, c2);
            c = c.toFixed(construction.fixed);
            construction.target.find("input[name='interestInvestment']").val(c).trigger('blur');
            construction.target.find(".interestInvestment").html(c);
        } catch (e) {

        }
    };

    construction.calculationG21 = function () {
        //=ROUND(D20*((1+D21)^(H3/2)-1),5)
        var d20 = construction.target.find("input[name='salesFee']").attr("data-value");
        var d21 = construction.target.find("input[name='interestInvestmentTax']").attr("data-value");
        var h3 = construction.target.find("input[name='developYearNumberTax']").val();
        if (!AssessCommon.isNumber(d20)) {
            return false;
        }
        if (!AssessCommon.isNumber(d21)) {
            return false;
        }
        if (!AssessCommon.isNumber(h3)) {
            return false;
        }
        var a = Math.pow(1 + math.number(d21), math.number(h3) / 2);
        var c = math.multiply(d20, math.subtract(a, 1));
        construction.target.find("input[name='g21']").val(c.toFixed(5)).trigger('blur');
    };

    construction.calculationG23 = function () {
        //=ROUND(D23*D20,5)
        var d23 = construction.target.find("input[name='investmentProfitTax']").attr("data-value");
        var d20 = construction.target.find("input[name='salesFee']").attr("data-value");
        if (!AssessCommon.isNumber(d20)) {
            return false;
        }
        if (!AssessCommon.isNumber(d23)) {
            return false;
        }
        var c = math.chain(d23).multiply(d20).done();
        construction.target.find("input[name='g23']").val(c.toFixed(5)).trigger('blur');
    };

    construction.calculationG24 = function () {
        //=ROUND(D20+G21+D22+G23,4)
        var d20 = construction.target.find("input[name='salesFee']").attr("data-value");
        var d22 = construction.target.find("input[name='salesTaxAndAdditional']").attr("data-value");
        var g21 = construction.target.find("input[name='g21']").val();
        var g23 = construction.target.find("input[name='g23']").val();
        if (!AssessCommon.isNumber(d20)) {
            return false;
        }
        if (!AssessCommon.isNumber(d22)) {
            return false;
        }
        if (!AssessCommon.isNumber(g21)) {
            return false;
        }
        if (!AssessCommon.isNumber(g23)) {
            return false;
        }
        var c = math.chain(d20).add(g21).add(d22).add(g23).done();
        construction.target.find("input[name='g24']").val(c.toFixed(4)).trigger('blur');
    };

    construction.calculationE23 = function () {
        //=IF(D9=" "," ",(E9+E17+E18+E19)*D23)
        var d23 = construction.target.find("input[name='investmentProfitTax']").attr("data-value");
        var e9 = construction.target.find("input[name='landGetCostTotal']").val();
        var e17 = construction.target.find("input[name='constructionSubtotal']").val();
        var e18 = construction.target.find("input[name='e18']").val();
        var e19 = construction.target.find("input[name='e19']").val();
        if (!AssessCommon.isNumber(e9)) {
            return false;
        }
        if (!AssessCommon.isNumber(e17)) {
            return false;
        }
        if (!AssessCommon.isNumber(e18)) {
            return false;
        }
        if (!AssessCommon.isNumber(e19)) {
            return false;
        }
        if (!AssessCommon.isNumber(d23)) {
            return false;
        }
        var c = math.multiply(d23, math.chain(e9).add(e17).add(e18).add(e19).done());
        c = c.toFixed(construction.fixed);
        construction.target.find("input[name='investmentProfit']").val(c).trigger('blur');
        construction.target.find(".investmentProfit").html(c);
    };

    construction.constructionAssessmentValue2calculationE24 = function () {
        //=IF(D9=" "," ",E9+E17+E18+E19+E21+E23)
        var e23 = construction.target.find("input[name='investmentProfit']").val();
        var e21 = construction.target.find("input[name='interestInvestment']").val();
        var e9 = construction.target.find("input[name='landGetCostTotal']").val();
        var e17 = construction.target.find("input[name='constructionSubtotal']").val();
        var e18 = construction.target.find("input[name='e18']").val();
        var e19 = construction.target.find("input[name='e19']").val();
        if (!AssessCommon.isNumber(e21)) {
            return false;
        }
        if (!AssessCommon.isNumber(e23)) {
            return false;
        }
        var c = math.chain(e9).add(e17).add(e18).add(e19).add(e21).add(e23).done();
        construction.target.find("input[name='constructionAssessmentValue2']").val(c.toFixed(construction.fixed)).trigger('blur');
    };

    construction.constructionAssessmentValueCalculationE25 = function () {
        //=IF(E24=" "," ",E24/(1-G24))
        var e24 = construction.target.find("input[name='constructionAssessmentValue2']").val();
        var g24 = construction.target.find("input[name='g24']").val();
        if (!AssessCommon.isNumber(e24)) {
            return false;
        }
        if (!AssessCommon.isNumber(g24)) {
            return false;
        }
        var c = math.chain(e24).divide(1 - math.number(g24)).done();
        c = c.toFixed(construction.fixedMin);
        construction.target.find("input[name='constructionAssessmentValue']").val(c).trigger('blur');
        construction.target.find(".constructionAssessmentValue").html(c);
    };

    construction.constructionAssessmentPriceCorrectingCalculationE26 = function () {
        //=IF(E25=" "," ",E25*10000/F3)
        var e25 = construction.target.find("input[name='constructionAssessmentValue']").val();
        var f3 = construction.target.find("input[name='developBuildAreaTax']").val();
        if (!AssessCommon.isNumber(e25)) {
            return false;
        }
        if (!AssessCommon.isNumber(f3)) {
            return false;
        }
        var c = math.chain(e25).multiply(10000).divide(f3).done();
        var residueRatio = this.target.find("input[name='residueRatio']").val();
        if ($.isNumeric(residueRatio)){
            c = math.multiply(c,residueRatio);
        }
        c = c.toFixed(construction.fixedMin);
        construction.target.find("input[name='constructionAssessmentPriceCorrecting']").val(c).trigger('blur');
        construction.target.find(".constructionAssessmentPriceCorrecting").html(c);
    };

    construction.constructionInstallationEngineeringFeeEvent = {
        detailsConstructionInstallation:function (id) {
            construction.constructionInstallationEngineeringFeeEvent.appendHTML() ;
            developmentCommon.getMdArchitecturalObjById(id,function (item) {
                var target = $("#boxMdCostConstruction");
                target.find("input[name='id']").val(item.id) ;
                var data = {} ;
                try {
                    data = JSON.parse(item.jsonContent) ;
                } catch (e) {
                    console.log("解析异常!");
                }
                developmentCommon.architecturalB.initData(target.find("table"),data) ;
            });
        },
        deleteConstructionInstallation:function (id) {
            developmentCommon.deleteMdArchitecturalObjById(id,function () {
                construction.constructionInstallationEngineeringFeeEvent.loadHtml();
            }) ;
        },
        save: function () {
            var target = $("#boxMdCostConstruction");
            var table = target.find("table");
            var value = table.find("tfoot").find("input[name='totalPrice']").first().val();
            if (!$.isNumeric(value)) {
                toastr.success('请重新填写!');
                return false;
            }
            var obj = {} ;
            obj.databaseName = AssessDBKey.ProjectPlanDetails ;
            obj.pid = '${projectPlanDetails.pid}';
            obj.type = construction.type ;
            obj.price = Number(value) ;
            obj.planDetailsId = '${projectPlanDetails.pid}' ;
            obj.id = target.find("input[name='id']").val();
            developmentCommon.saveMdArchitecturalObj2(developmentCommon.architecturalB.getFomData(table),obj,function (item) {
                toastr.success('保存成功!');
                construction.constructionInstallationEngineeringFeeEvent.loadHtml();
            }) ;
            target.modal("hide");
        },
        loadHtml:function () {
            var obj = { } ;
            obj.databaseName = AssessDBKey.ProjectPlanDetails ;
            obj.type = construction.type ;
            obj.planDetailsId = '${projectPlanDetails.pid}' ;
            developmentCommon.getMdArchitecturalObjList(obj.type,obj.databaseName,obj.pid,obj.planDetailsId,function (data) {
                $(construction.engineeringFeeInfoTarget).empty();
                var html = "";
                var multiply = math.bignumber(0);
                var arr = [] ;
                if (data.length >= 1) {
                    $.each(data, function (i, item) {
                        var resetHtml = $("#constructionInstallationEngineeringFeeInfoModelHtml").html();
                        resetHtml = resetHtml.replace(/{uuid}/g, item.id);
                        resetHtml = resetHtml.replace(/{price}/g, item.price);
                        resetHtml = resetHtml.replace(/{method}/g, "construction.constructionInstallationEngineeringFeeEvent");
                        html += resetHtml;
                        if ($.isNumeric(item.price)){
                            multiply = math.add(multiply, math.bignumber(item.price));
                        }
                        arr.push(item.id) ;
                    });
                }
                multiply = Number(multiply.toString()) ;
                construction.target.find("input[name='constructionInstallationEngineeringFee']").val(multiply.toFixed(construction.fixed)).trigger('blur');
                construction.target.find("input[name='reconnaissanceDesign']").trigger('blur');
                construction.target.find("input[name='constructionInstallationEngineeringFeeIds']").val(arr.join(","));
                $(construction.engineeringFeeInfoTarget).append(html);
            }) ;
        },
        appendHTML:function () {
            var target = $("#boxMdCostConstruction");
            target.find(".panel-body").empty() ;
            target.find(".panel-body").append(developmentCommon.architecturalB.getHtml());
            developmentCommon.architecturalB.treeGirdParse(target);
            target.modal("show");
        }
    };

    construction.loadMdDevelopmentInfrastructureChildrenTable = function () {
        var pid = developmentCommon.isNotBlank('${mdCostConstruction.id}') ? '${mdCostConstruction.id}' : '0';
        developmentCommon.infrastructureChildren.loadTable(pid, '${projectPlanDetails.id}', construction.type, $(construction.infrastructureChildrenTable), $("#toolbarMdCostConstructionChildrenTable"));
        construction.writeMdDevelopmentInfrastructureChildrenTable();
    };

    construction.deleteMdDevelopmentInfrastructureChildrenTable = function (table) {
        var rows = $(table).bootstrapTable('getSelections');
        if (rows.length >= 1) {
            var data = [];
            $.each(rows, function (i, item) {
                data.push(item.id);
            });
            developmentCommon.infrastructureChildren.delete(data, function () {
                toastr.success('删除成功!');
                $(construction.infrastructureChildrenTable).bootstrapTable('refresh');
                construction.writeMdDevelopmentInfrastructureChildrenTable();
            });
        } else {
            toastr.success('至少勾选一个!');
        }
    };

    construction.editMdDevelopmentInfrastructureChildrenTable = function (table, box, flag) {
        var target = $(box);
        var frm = target.find("form");
        var pid = developmentCommon.isNotBlank('${mdCostConstruction.id}') ? '${mdCostConstruction.id}' : '0';
        if (flag) {
            var rows = $(table).bootstrapTable('getSelections');
            if (rows.length == 1) {
                var data = rows[0];
                frm.initForm(data);
                target.find(".modal-footer").empty().append($(construction.infrastructureFooterHtml).html());
                target.modal('show');
            } else {
                toastr.success('勾选一个!');
            }
        } else {
            frm.clearAll();
            frm.initForm({pid: pid});
            target.find(".modal-footer").empty().append($(construction.infrastructureFooterHtml).html());
            target.modal('show');
        }
    };
    construction.saveMdDevelopmentInfrastructureChildrenTable = function (_this) {
        var target = $(_this).parent().parent().parent().parent();
        var frm = target.find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        data.planDetailsId = '${projectPlanDetails.id}';
        data.type = construction.type;
        data.pid = developmentCommon.isNotBlank('${mdCostConstruction.id}') ? '${mdCostConstruction.id}' : '0';
        developmentCommon.infrastructureChildren.save(data, function () {
            toastr.success('添加成功!');
            target.modal('hide');
            $(construction.infrastructureChildrenTable).bootstrapTable('refresh');
            construction.writeMdDevelopmentInfrastructureChildrenTable();
        });
    };

    construction.writeMdDevelopmentInfrastructureChildrenTable = function () {
        var pid = developmentCommon.isNotBlank('${mdCostConstruction.id}') ? '${mdCostConstruction.id}' : '0';
        developmentCommon.infrastructureChildren.getDataList({
            planDetailsId: '${projectPlanDetails.id}',
            pid: pid,
            type: construction.type
        }, function (item) {
            var result = 0;
            if (item.length >= 1) {
                $.each(item, function (i, n) {
                    result += Number(n.number);
                });
            }
            construction.target.find("input[name='infrastructureCost']").val(result).trigger('blur');
        });
    };

    /**
     * 成新率
     */
    construction.callResidueRatio = function (_this, readonly) {
        //获取已使用年限,当前评估基准日-楼栋的竣工时间
        //获取可用年限，根据建筑使用年限配置而来
        try {
            residueRatio.init({
                readonly: readonly,
                residueRatioId: construction.target.find("input[name='residueRatioId']").val(),
//                usedYear: 0,
//                usableYear: 5,
                houseId: '${basicHouseVo.id}',
                success: function (id, resultValue) {
                    construction.target.find("input[name='residueRatioId']").val(id);
                    construction.target.find("input[name='residueRatio']").val(parseFloat(resultValue)/100);
                    construction.target.find("input[name='residueRatioShow']").val(resultValue);
                    construction.constructionAssessmentPriceCorrectingCalculationE26() ;
                }
            });
        } catch (e) {
        }
    };

    /**
     * 触发事件
     */
    construction.inputBlurEvent = function () {
        if (cost.isNotBlank('${mdCostConstruction.id}')){
            console.log('inputBlurEvent') ;
            construction.target.find("input").trigger('blur');
        }
    }


</script>