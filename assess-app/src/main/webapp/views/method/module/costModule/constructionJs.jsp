<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    var construction = {};

    construction.target = $("#constructionFrm");
    construction.fixed = 2; //小数点保留2位
    construction.fixedMax = 4; //小数点保留4位
    construction.fixedMin = 0; //小数点保留0位
    construction.incomeCategoryTable = "#landIncomeCategoryTableId";
    construction.infrastructureChildrenTable = "#landMdCostConstructionChildrenTable";
    construction.infrastructureFooterHtml = "#underConstructionMdDevelopmentInfrastructureFooterX";
    construction.engineeringFeeInfoTarget = "#engineeringConstructionInstallationEngineeringFeeInfoTarget";
    construction.incomeCategoryFooterHtml = "#mdCostConstructionMdDevelopmentIncomeCategoryFooter";
    construction.type = 'engineering';

    /**调用比较法**/
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


    /**
     * 建筑安装工程费
     */
    construction.constructionInstallationEngineeringFeeEvent = {
        //显示树详情
        detailsConstructionInstallation: function (id) {
            var mdCalculatingMethodEngineeringCost = $(construction.engineeringFeeInfoTarget).bootstrapTable('getRowByUniqueId', id);
            developmentCommon.getMdArchitecturalObjById(mdCalculatingMethodEngineeringCost.architecturalObjId, function (item) {
                var target = $("#boxMdCostConstruction");
                var data = [];
                try {
                    data = JSON.parse(item.jsonContent);
                } catch (e) {
                    console.log("解析异常!");
                }
                construction.constructionInstallationEngineeringFeeEvent.appendHTML(data, item.price);
                target.find("input[name='id']").val(item.id);
                target.find("input[name='masterId']").val(id);
            });
        },
        //保存树和工程费
        save: function () {
            var target = $("#boxMdCostConstruction");
            var table = target.find("table");
            var value = table.find("tfoot").find("input[name='totalPrice']").first().val();
            if (!$.isNumeric(value)) {
                toastr.success('请重新填写!');
                return false;
            }
            var obj = {};
            obj.databaseName = AssessDBKey.MdCost;
            obj.pid = target.find("input[name='masterId']").val();
            obj.type = construction.type;
            obj.price = Number(value);
            obj.planDetailsId = '${projectPlanDetails.id}';
            obj.id = target.find("input[name='id']").val();
            developmentCommon.saveMdArchitecturalObj2(developmentCommon.architecturalB.getFomData(table), obj, function (item) {
                toastr.success('保存成功!');
            });
            var mdCalculatingMethodEngineeringCost = $(construction.engineeringFeeInfoTarget).bootstrapTable('getRowByUniqueId', obj.pid);
            mdCalculatingMethodEngineeringCost.price = obj.price ;
            developmentCommon.saveMdCalculatingMethodEngineeringCost(mdCalculatingMethodEngineeringCost, function (data) {
                $(construction.engineeringFeeInfoTarget).bootstrapTable('refresh');
                construction.writeMdCalculatingMethodEngineeringCost(data);
            });
            target.modal("hide");
        },
        //树 加载并且树赋值
        appendHTML: function (data, price) {
            var type = construction.target.prev().find("input[name='type']:checked").val();
            var target = $("#boxMdCostConstruction");
            target.find(".panel-body").empty();
            if (data == undefined) {
                data = [];
            }
            if (price == undefined) {
                price = '';
            }
            target.find("input[name='id']").val('');
            target.find("input[name='masterId']").val('');
            developmentCommon.architecturalB.appendHtml(target.find(".panel-body"), data, null, price, function (tr) {
                if (type == cost.one) {
                    $(tr).find("input[name='valuationDateDegreeCompletion']").val('100%').attr({
                        readonly: 'readonly',
                        class: 'form-control',
                        'data-value': '1'
                    });
                }
            });
            target.modal("show");
        }
    };

    /**
     * 工程费表格加载
     */
    construction.loadMdCalculatingMethodEngineeringCostTable = function () {
        <%--var obj = {type: construction.type, planDetailsId: '${projectPlanDetails.id}'};--%>
        var obj = { planDetailsId: '${projectPlanDetails.id}'};
        var cols = [];
        cols.push({
            field: 'id', title: '建筑安装工程费明细', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += "<a class='btn btn-xs btn-success tooltips' data-placement='top' data-original-title='建筑安装工程费明细' onclick='construction.constructionInstallationEngineeringFeeEvent.detailsConstructionInstallation(" + row.id + ")'" + ">" + "<i class='fa fa-pencil-square-o'>" + "建筑安装工程费明细" + "</a>";
                str += '</div>';
                return str;
            }
        });

        developmentCommon.loadMdCalculatingMethodEngineeringCostTable($(construction.engineeringFeeInfoTarget), obj, $("#toolbarMdCalculatingMethodEngineeringCost"), function () {
            construction.writeMdCalculatingMethodEngineeringCost();
        }, cols);
    };

    /**工程费 事件**/
    construction.changeMdCalculatingMethodEngineeringCostField = function (index, id) {
        var value = $(construction.engineeringFeeInfoTarget).find("[data-id='" + "mdCalculatingMethodEngineeringCostField" + id + "']").find("option:selected").val();
        var mdCalculatingMethodEngineeringCost = $(construction.engineeringFeeInfoTarget).bootstrapTable('getRowByUniqueId', id);
        if (value == 1) {
            mdCalculatingMethodEngineeringCost.dataTableName = AssessDBKey.BasicEstate;
        }
        if (value == 2) {
            mdCalculatingMethodEngineeringCost.dataTableName = AssessDBKey.BasicBuilding;
        }
        developmentCommon.saveMdCalculatingMethodEngineeringCost(mdCalculatingMethodEngineeringCost, function () {
            $(construction.engineeringFeeInfoTarget).bootstrapTable('updateCell', {
                field: 'dataTableName',
                value: mdCalculatingMethodEngineeringCost.dataTableName
            });
            $(construction.engineeringFeeInfoTarget).bootstrapTable('refresh');
            toastr.success('绑定成功!');
        });
    };

    construction.showMdCalculatingMethodEngineeringCost = function () {
        var target = $("#boxMdCalculatingMethodEngineeringCost");
        target.modal("show");
        var frm = target.find("form");
        frm.clearAll();
    };

    construction.saveMdCalculatingMethodEngineeringCost = function () {
        var target = $("#boxMdCalculatingMethodEngineeringCost");
        var frm = target.find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        data.planDetailsId = '${projectPlanDetails.id}';
        data.projectId = '${projectPlanDetails.projectId}';
        data.type = construction.type;
        developmentCommon.saveMdCalculatingMethodEngineeringCost(data, function (item) {
            construction.writeMdCalculatingMethodEngineeringCost(item);
            target.modal("hide");
            toastr.info("添加成功!");
            var obj = {};
            obj.databaseName = AssessDBKey.MdDevelopment;
            obj.pid = developmentCommon.isNotBlank('${mdCostVo.mdCostConstruction.id}') ? '${mdCostVo.mdCostConstruction.id}' : '0';
            obj.type = construction.type;
            obj.planDetailsId = '${projectPlanDetails.id}';
            //这里会同时生成 建筑安装工程费 详细情况id
            developmentCommon.saveMdArchitecturalObj2({}, obj, function (n) {
                item.architecturalObjId = n.id;
                developmentCommon.saveMdCalculatingMethodEngineeringCost(item);
                construction.loadMdCalculatingMethodEngineeringCostTable();
            });
        });
    };

    /*工程费和计算**/
    construction.writeMdCalculatingMethodEngineeringCost = function (obj) {
        var arr = $(construction.engineeringFeeInfoTarget).bootstrapTable('getData');
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
        $.each(result, function (i, n) {
            if ($.isNumeric(n.price)) {
                price = math.add(price, math.bignumber(n.price));
            }
        });
        price = Number(price.toString());
        construction.target.find("input[name='constructionInstallationEngineeringFee']").val(price.toFixed(construction.fixed)).trigger('blur');
        construction.target.find("input[name='reconnaissanceDesign']").trigger('blur');
    };

    /**
     * 工程费删除
     */
    construction.delMdCalculatingMethodEngineeringCost = function () {
        var rows = $(construction.engineeringFeeInfoTarget).bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            toastr.info("请选择要删除的数据");
        } else {
            var idArray = [];
            $.each(rows, function (i, item) {
                idArray.push(item.id);
            });
            Alert("确认要删除么？", 2, null, function () {
                developmentCommon.deleteMdCalculatingMethodEngineeringCostById(idArray.join(","), function () {
                    toastr.success('删除成功');
                    $(construction.engineeringFeeInfoTarget).bootstrapTable('refresh');
                    construction.writeMdCalculatingMethodEngineeringCost();
                });
            })
        }
    };

    //经济指标show
    construction.showMdDevelopmentIncomeCategory = function () {
        var economicId = '${mdCostVo.mdCostConstruction.economicId}' ;
        if (economicId){
            economicIndicators.init({economicId:economicId});
        }else {
            economicIndicators.init({
                planDetailsId: '${projectPlanDetails.id}',
                saveCallback: function (economicId) {//经济指标id更新到中间表
                    console.log(economicId);
                    construction.target.find("input[name='economicId']").val(economicId).trigger('blur');
                },
                targetCallback:function () {
                    economicIndicators.autoSummary(true) ;
                }
            });
        }
    } ;



    /**基础设施建设 table*/
    construction.loadMdDevelopmentInfrastructureChildrenTable = function () {
        var pid = developmentCommon.isNotBlank('${mdCostVo.mdCostConstruction.id}') ? '${mdCostVo.mdCostConstruction.id}' : '0';
        var data = {planDetailsId:'${projectPlanDetails.id}',pid:pid} ;
        developmentCommon.infrastructureChildren.loadTable2(data, $(construction.infrastructureChildrenTable), $("#toolbarMdCostConstructionChildrenTable"));
        construction.writeMdDevelopmentInfrastructureChildrenTable();
    };

    /**基础设施建设 delete*/
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

    /**基础设施建设 edit or show data*/
    construction.editMdDevelopmentInfrastructureChildrenTable = function (table, box, flag) {
        var target = $(box);
        var frm = target.find("form");
        frm.clearAll();
        var pid = developmentCommon.isNotBlank('${mdCostVo.mdCostConstruction.id}') ? '${mdCostVo.mdCostConstruction.id}' : '0';
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
    /**基础设施建设  save*/
    construction.saveMdDevelopmentInfrastructureChildrenTable = function (_this) {
        var target = $(_this).parent().parent().parent().parent();
        var frm = target.find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        data.planDetailsId = '${projectPlanDetails.id}';
        data.type = construction.type;
        data.pid = developmentCommon.isNotBlank('${mdCostVo.mdCostConstruction.id}') ? '${mdCostVo.mdCostConstruction.id}' : '0';
        developmentCommon.infrastructureChildren.save(data, function () {
            toastr.success('添加成功!');
            target.modal('hide');
            $(construction.infrastructureChildrenTable).bootstrapTable('refresh');
            construction.writeMdDevelopmentInfrastructureChildrenTable();
        });
    };

    /**基础设施建设  event*/
    construction.writeMdDevelopmentInfrastructureChildrenTable = function () {
        var pid = developmentCommon.isNotBlank('${mdCostVo.mdCostConstruction.id}') ? '${mdCostVo.mdCostConstruction.id}' : '0';
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
            if ('${mdCostVo.mdCostConstruction.infrastructureCost}'){
                if (result == 0){
                    result += Number('${mdCostVo.mdCostConstruction.infrastructureCost}') ;
                }
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
                    console.log(resultValue);
                    var target = $(_this).closest(".input-group");
                    target.find("input[name='residueRatioId']").val(id);
                    target.find("input[name='residueRatio']").val(parseFloat(resultValue) / 100).trigger('blur');
                    target.find("input[name='residueRatioShow']").val(resultValue);
                }
            });
        } catch (e) {
        }
    };


</script>