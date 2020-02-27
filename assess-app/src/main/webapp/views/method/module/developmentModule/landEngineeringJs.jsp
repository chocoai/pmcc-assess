<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    landEngineering.typeData = function () {
        var target = $(landEngineering.target.selector) ;
        return target.find("input[name='type']").val();
    };
    landEngineering.masterId = developmentCommon.isNotBlank('${mdDevelopment.id}') ? '${mdDevelopment.id}' : '0';

    landEngineering.selectFun = function (copyId, box) {
        var target = $("#" + box);
        $.ajax({
            url: "${pageContext.request.contextPath}/mdDevelopment/copyDevelopmentById",
            type: "post",
            dataType: "json",
            data: {copyId: copyId, masterId: '${mdDevelopment.id}'},
            success: function (result) {
                if (result.ret) {
                    window.location.reload(true); //强制从服务器重新加载当前页面
                    target.modal("hide");
                } else {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("错误", "调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    landEngineering.calculationF22 = function (_this) {
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
                            obj.type = landEngineering.typeData();
                            obj.pid = developmentCommon.isNotBlank('${mdDevelopment.id}') ? '${mdDevelopment.id}' : '0';
                            arr.push(obj);
                        });
                        developmentCommon.infrastructureChildren.saveArray(arr, function () {
                            notifySuccess("成功", "引用成功!");
                            landEngineering.infrastructureChildrenTable.bootstrapTable('refresh');
                            landEngineering.writeMdDevelopmentInfrastructureChildrenTable();
                        });
                    }
                    else {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                }
            })
        } else {
            toastr.success('无子项!');
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
                toastr.success('请重新填写!');
                return false;
            }
            var obj = {};
            obj.masterId = target.find("input[name='masterId']").val();
            obj.price = Number(value);
            obj.id = target.find("input[name='id']").val();
            developmentCommon.saveMdArchitecturalObj2(developmentCommon.architecturalB.getFomData(table), obj, function (item) {
                toastr.success('保存成功!');
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
            var target = $("#boxLandEngineering");
            target.find(".card-body").empty();
            if (data == undefined) {
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
                target: target.find(".card-body"),
                obj: data,
                attribute: null,
                price: price,
                reckon: reckon,
                callback: function (tr) {
                }
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
            type: landEngineering.target.find("input[name='type']").val()
        };
        var cols = [];
        cols.push({
            field: 'id', title: '建筑安装工程费明细', width: "20%", formatter: function (value, row, index) {
                var str = '<button type="button" onclick="landEngineering.constructionInstallationEngineeringFeeEvent.detailsConstructionInstallation(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="建筑安装工程费明细">';
                str += '<i class="fa fa-pen"></i>';
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
        data.price = "0";
        developmentCommon.saveMdCalculatingMethodEngineeringCost(data, function (item) {
            landEngineering.writeMdCalculatingMethodEngineeringCost(item);
            target.modal("hide");
            toastr.info("添加成功!");
            //这里会同时生成 建筑安装工程费 详细情况id
            developmentCommon.saveMdArchitecturalObj2({}, {price: "0", pid: 0}, function (result) {
                item.architecturalObjId = result.id;
                developmentCommon.saveMdCalculatingMethodEngineeringCost(item);
                landEngineering.loadMdCalculatingMethodEngineeringCostTable();
            });
        });
    };

    /**
     * 设置工程费
     */
    landEngineering.setMdCalculatingMethodEngineeringCost = function (flag) {
        var planDetailsId = '${projectPlanDetails.id}';
        $.ajax({
            type: "post",
            url: getContextPath() + "/mdDevelopment/setMdCalculatingMethodEngineeringCost",
            data: {planDetailsId: planDetailsId, type: landEngineering.typeData(), flag: flag},
            success: function (result) {
                if (result.ret) {
                    toastr.success('成功');
                    landEngineering.loadMdCalculatingMethodEngineeringCostTable();
                }
            },
            error: function (e) {
                AlertError("错误", "调用服务端方法失败，失败原因:" + e);
            }
        });
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
            toastr.info("请选择要删除的数据");
        } else {
            AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
                developmentCommon.deleteMdCalculatingMethodEngineeringCostHandle(rows, function () {
                    toastr.success('删除成功');
                    landEngineering.engineeringFeeInfoTarget.bootstrapTable('refresh');
                    landEngineering.writeMdCalculatingMethodEngineeringCost();
                });
            });

        }
    };

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
            developmentCommon.infrastructureChildren.delete(data, function () {
                notifySuccess("成功", "删除成功!");
                landEngineering.infrastructureChildrenTable.bootstrapTable('refresh');
                landEngineering.writeMdDevelopmentInfrastructureChildrenTable();
            });
        } else {
            toastr.success('至少勾选一个!');
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
                toastr.success('勾选一个!');
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