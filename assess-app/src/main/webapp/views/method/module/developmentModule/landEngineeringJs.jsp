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
    landEngineering.defaultType = 'land' ;

    landEngineering.getTypeData = function (type) {
        var data = formSerializeArray(landEngineering.target) ;
        if (data.type == 1 || (type== '1' && type != undefined)){
            return landEngineering.defaultType ;
        }
        if (data.type == 2 || (type == '2' && type != undefined)){
            return 'engineering' ;
        }
    };



    landEngineering.calculationF22 = function (_this) {
        var item = $(_this).find('option:selected') ;
        var pid = item.attr('data-key') ;
        var type = item.attr('data-type') ;
        if (pid){
            $.ajax({
                url: "${pageContext.request.contextPath}/dataInfrastructureChildren/getDataList",
                type: "get",
                dataType: "json",
                data: {pid:pid,type:type},
                success: function (result) {
                    if (result.ret) {
                        var arr = [] ;
                        var data = result.data ;
                        if (data.length == 0){
                            return false ;
                        }
                        $.each(data,function (i,n) {
                            var obj = {name:n.name,number:n.number,tax:n.tax} ;
                            obj.planDetailsId = '${projectPlanDetails.id}' ;
                            obj.type = landEngineering.getTypeData() ;
                            obj.pid = developmentCommon.isNotBlank('${mdDevelopment.id}')?'${mdDevelopment.id}':'0' ;
                            arr.push(obj) ;
                        });
                        developmentCommon.infrastructureChildren.saveArray(arr,function () {
                            toastr.success('添加成功!');
                            landEngineering.infrastructureChildrenTable.bootstrapTable('refresh');
                            landEngineering.writeMdDevelopmentInfrastructureChildrenTable() ;
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
        }else {
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
            var obj = {} ;
            obj.databaseName = AssessDBKey.MdDevelopment ;
            obj.pid = target.find("input[name='masterId']").val();
            obj.type = landEngineering.getTypeData() ;
            obj.price = Number(value) ;
            obj.planDetailsId = '${projectPlanDetails.id}' ;
            obj.id = target.find("input[name='id']").val();
            var mdCalculatingMethodEngineeringCost = landEngineering.engineeringFeeInfoTarget.bootstrapTable('getRowByUniqueId', obj.pid);
            mdCalculatingMethodEngineeringCost.price = obj.price ;
            if (landEngineering.defaultType == landEngineering.getTypeData()){
                developmentCommon.saveMdArchitecturalObj2(developmentCommon.architecturalA.getFomData(table),obj,function (item) {
                    toastr.success('保存成功!');
                }) ;
            }else {
                developmentCommon.saveMdArchitecturalObj2(developmentCommon.architecturalB.getFomData(table),obj,function (item) {
                    toastr.success('保存成功!');
                }) ;
            }
            developmentCommon.saveMdCalculatingMethodEngineeringCost(mdCalculatingMethodEngineeringCost, function (data) {
                landEngineering.engineeringFeeInfoTarget.bootstrapTable('refresh');
                landEngineering.writeMdCalculatingMethodEngineeringCost(data);
            });
            target.modal("hide");
        },
        appendHTML:function (data, price) {
            var target = $("#boxLandEngineering");
            target.find(".panel-body").empty();
            if (data == undefined) {
                data = [];
            }
            if (price == undefined) {
                price = '';
            }
            target.find("input[name='id']").val('');
            target.find("input[name='masterId']").val('');
            if (landEngineering.defaultType == landEngineering.getTypeData()){
                developmentCommon.architecturalA.appendHtml(target.find(".panel-body"), data, null, price, function (tr) {

                });
            }else {
                developmentCommon.architecturalB.appendHtml(target.find(".panel-body"), data, null, price, function (tr) {

                });
            }
            target.modal("show");
        }
    };

    /**
     * 工程费表格加载
     */
    landEngineering.loadMdCalculatingMethodEngineeringCostTable = function (type) {
        var obj = {type: landEngineering.getTypeData(type), planDetailsId: '${projectPlanDetails.id}'};
        var cols = [];
        cols.push({
            field: 'id', title: '建筑安装工程费明细', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += "<a class='btn btn-xs btn-success tooltips' data-placement='top' data-original-title='建筑安装工程费明细' onclick='landEngineering.constructionInstallationEngineeringFeeEvent.detailsConstructionInstallation(" + row.id + ")'" + ">" + "<i class='fa fa-pencil-square-o'>" + "建筑安装工程费明细" + "</a>";
                str += '</div>';
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
        var frm = target.find("form") ;
        frm.clearAll();
    };

    /*工程费 save*/
    landEngineering.saveMdCalculatingMethodEngineeringCost = function () {
        var target = $("#boxMdCalculatingMethodEngineeringCost");
        var frm = target.find("form") ;
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        data.planDetailsId = '${projectPlanDetails.id}' ;
        data.projectId = '${projectPlanDetails.projectId}' ;
        data.type = landEngineering.getTypeData() ;
        developmentCommon.saveMdCalculatingMethodEngineeringCost(data, function (item) {
            landEngineering.writeMdCalculatingMethodEngineeringCost(item);
            target.modal("hide");
            toastr.info("添加成功!");
            var obj = {} ;
            obj.databaseName = AssessDBKey.MdDevelopment ;
            obj.pid = developmentCommon.isNotBlank('${mdDevelopment.id}')?'${mdDevelopment.id}':'0';
            obj.type = landEngineering.getTypeData() ;
            obj.planDetailsId = '${projectPlanDetails.id}' ;
            //这里会同时生成 建筑安装工程费 详细情况id
            developmentCommon.saveMdArchitecturalObj2({},obj,function (n) {
                item.architecturalObjId = n.id;
                developmentCommon.saveMdCalculatingMethodEngineeringCost(item) ;
                landEngineering.loadMdCalculatingMethodEngineeringCostTable() ;
            }) ;
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
        $.each(result, function (i, n) {
            if ($.isNumeric(n.price)) {
                price = math.add(price, math.bignumber(n.price));
            }
        });
        price = Number(price.toString());
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
            var idArray = [];
            $.each(rows, function (i, item) {
                idArray.push(item.id);
            });
            Alert("确认要删除么？", 2, null, function () {
                developmentCommon.deleteMdCalculatingMethodEngineeringCostById(idArray.join(","), function () {
                    toastr.success('删除成功');
                    landEngineering.engineeringFeeInfoTarget.bootstrapTable('refresh');
                    landEngineering.writeMdCalculatingMethodEngineeringCost();
                });
            })
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
    } ;
    /*基础设施配套费  table load**/
    landEngineering.loadMdDevelopmentInfrastructureChildrenTable = function () {
        var pid = developmentCommon.isNotBlank('${mdDevelopment.id}')?'${mdDevelopment.id}':'0' ;
        developmentCommon.infrastructureChildren.loadTable(pid,'${projectPlanDetails.id}',landEngineering.getTypeData(),landEngineering.infrastructureChildrenTable,$("#toolbarMdDevelopmentInfrastructureChildrenTable")) ;
        landEngineering.writeMdDevelopmentInfrastructureChildrenTable() ;
    };
    /*基础设施配套费  table delete**/
    landEngineering.deleteMdDevelopmentInfrastructureChildrenTable = function (table) {
        var rows = $(table).bootstrapTable('getSelections');
        if (rows.length >= 1) {
            var data = [];
            $.each(rows, function (i, item) {
                data.push(item.id);
            });
            developmentCommon.infrastructureChildren.delete(data , function () {
                toastr.success('删除成功!');
                landEngineering.infrastructureChildrenTable.bootstrapTable('refresh');
                landEngineering.writeMdDevelopmentInfrastructureChildrenTable() ;
            });
        } else {
            toastr.success('至少勾选一个!');
        }
    };
    /*基础设施配套费  table edit**/
    landEngineering.editMdDevelopmentInfrastructureChildrenTable = function (table,box ,flag) {
        var target = $(box) ;
        var frm = target.find("form") ;
        var pid = developmentCommon.isNotBlank('${mdDevelopment.id}')?'${mdDevelopment.id}':'0' ;
        if (flag){
            var rows = $(table).bootstrapTable('getSelections');
            if (rows.length == 1) {
                var data = rows[0];
                frm.initForm(data);
                target.find(".modal-footer").empty().append($(landEngineering.infrastructureFooterHtml).html()) ;
                target.modal('show');
            } else {
                toastr.success('勾选一个!');
            }
        }else {
            frm.clearAll();
            frm.initForm({pid:pid});
            target.find(".modal-footer").empty().append($(landEngineering.infrastructureFooterHtml).html()) ;
            target.modal('show');
        }
    };
    /*基础设施配套费  table save**/
    landEngineering.saveMdDevelopmentInfrastructureChildrenTable = function (_this) {
        var target = $(_this).parent().parent().parent().parent() ;
        var frm = target.find("form") ;
        if (!frm.valid()) {
            return false ;
        }
        var data = formSerializeArray(frm);
        data.planDetailsId = '${projectPlanDetails.id}' ;
        data.type = landEngineering.getTypeData() ;
        data.pid = developmentCommon.isNotBlank('${mdDevelopment.id}')?'${mdDevelopment.id}':'0' ;
        developmentCommon.infrastructureChildren.save(data , function () {
            toastr.success('添加成功!');
            target.modal('hide');
            landEngineering.infrastructureChildrenTable.bootstrapTable('refresh');
            landEngineering.writeMdDevelopmentInfrastructureChildrenTable() ;
        });
    } ;

    /*基础设施配套费  table 测算**/
    landEngineering.writeMdDevelopmentInfrastructureChildrenTable = function () {
        var pid = developmentCommon.isNotBlank('${mdDevelopment.id}')?'${mdDevelopment.id}':'0' ;
        developmentCommon.infrastructureChildren.getDataList({planDetailsId:'${projectPlanDetails.id}',pid:pid,type:landEngineering.getTypeData()} ,function (item) {
            var result = 0;
            if (item.length >= 1){
                $.each(item,function (i,n) {
                    result += Number(n.number) ;
                });
            }
            landEngineering.target.find("input[name='infrastructureCost']").val(result).trigger('blur');
        }) ;
    };

    /*收入类 经济指标 table save**/
    landEngineering.saveMdDevelopmentIncomeCategoryTable = function (_this) {
        var target = $(_this).parent().parent().parent().parent() ;
        var frm = target.find("form") ;
        if (!frm.valid()) {
            return false ;
        }
        var data = formSerializeArray(frm);
        data.planDetailsId = '${projectPlanDetails.id}' ;
        data.type = landEngineering.getTypeData() ;
        data.pid = developmentCommon.isNotBlank('${mdDevelopment.id}')?'${mdDevelopment.id}':'0' ;
        target.modal('hide');
        developmentCommon.loadIncomeCategorySave(data,function (item) {
            toastr.success('添加成功!');
            landEngineering.incomeCategoryTable.bootstrapTable('refresh');
            landEngineering.writeMdDevelopmentIncomeCategoryTable(landEngineering.incomeCategoryTable,item) ;
        },function () {

        }) ;
    };

    /*收入类 经济指标 table delete**/
    landEngineering.deleteMdDevelopmentIncomeCategoryTable = function (table) {
        var rows = $(table).bootstrapTable('getSelections');
        if (rows.length >= 1) {
            var data = [];
            $.each(rows, function (i, item) {
                data.push(item.id);
            });
            var ids = $.map($(table).bootstrapTable('getSelections'), function (row) {
                return row.id
            });
            developmentCommon.deleteIncomeCategory(data,function () {
                $(table).bootstrapTable('remove', {
                    field: 'id',
                    values: ids
                });
                $(table).bootstrapTable('refresh');
                toastr.success('删除成功!');
                landEngineering.writeMdDevelopmentIncomeCategoryTable($(table)) ;
            },function () {
                toastr.success('删除失败!');
            }) ;
        } else {
            toastr.success('至少勾选一个!');
        }
    };

    /*收入类 经济指标 table load**/
    landEngineering.loadIncomeCategoryTable = function () {
        var obj = {type:landEngineering.getTypeData(),planDetailsId:'${projectPlanDetails.id}'} ;
        developmentCommon.loadIncomeCategoryTable(landEngineering.incomeCategoryTable,obj,$("#toolbarLandIncomeCategoryTableId"),function () {
            landEngineering.writeMdDevelopmentIncomeCategoryTable(landEngineering.incomeCategoryTable,null) ;
        }) ;
    } ;

    /*收入类 经济指标 table edit**/
    landEngineering.editMdDevelopmentIncomeCategoryTable = function (table,box ,flag) {
        var target = $(box) ;
        var frm = target.find("form") ;
        var pid = developmentCommon.isNotBlank('${mdDevelopment.id}')?'${mdDevelopment.id}':'0' ;
        if (flag){
            var rows = $(table).bootstrapTable('getSelections');
            if (rows.length == 1) {
                var data = rows[0];
                frm.initForm(data);
                target.find(".modal-footer").empty().append($(landEngineering.incomeCategoryFooterHtml).html()) ;
                target.modal('show');
            } else {
                toastr.success('勾选一个!');
            }
        }else {
            frm.clearAll();
            frm.initForm({pid:pid});
            target.find(".modal-footer").empty().append($(landEngineering.incomeCategoryFooterHtml).html()) ;
            target.modal('show');
        }
    };

    /*收入类 经济指标 table 测算**/
    landEngineering.writeMdDevelopmentIncomeCategoryTable = function (table,obj) {
        var data = table.bootstrapTable('getData') ;
        if (obj){
            data.push(obj) ;
        }
        var plannedBuildingArea = math.bignumber(0);
        var totalSaleableAreaPrice = math.bignumber(0);
        var saleableArea = math.bignumber(0);
        $.each(data,function (i,n) {
            if ($.isNumeric(n.plannedBuildingArea)){
                plannedBuildingArea = math.add(plannedBuildingArea, math.bignumber(n.plannedBuildingArea)) ;
            }
            if ($.isNumeric(n.totalSaleableAreaPrice)){
                totalSaleableAreaPrice = math.add(totalSaleableAreaPrice, math.bignumber(n.totalSaleableAreaPrice)) ;
            }
            if ($.isNumeric(n.saleableArea)){
                saleableArea = math.add(saleableArea, math.bignumber(n.saleableArea)) ;
            }
        });
        plannedBuildingArea = plannedBuildingArea.toString() ;
        totalSaleableAreaPrice = totalSaleableAreaPrice.toString() ;
        saleableArea = saleableArea.toString() ;
        this.target.find("label[name='plannedBuildingArea']").html(plannedBuildingArea);
        this.target.find("label[name='totalSaleableAreaPrice']").html(totalSaleableAreaPrice);
        this.target.find("label[name='saleableArea']").html(saleableArea);
        this.target.find("input[name='plannedBuildingArea']").val(plannedBuildingArea).trigger('blur');
        this.target.find("input[name='totalSaleableAreaPrice']").val(totalSaleableAreaPrice).trigger('blur');
        this.target.find("input[name='saleableArea']").val(saleableArea).trigger('blur');
    };

    /*不可售建筑面积**/
    landEngineering.unsaleableBuildingAreaFunHandle = function () {
        this.target.find("a").each(function (i,item) {
            var target = $(item);
            var dataKey = target.attr("data-key");
            if (dataKey == 'unsaleableBuildingArea'){
                target.editable({
                    type: "text",                //编辑框的类型。支持text|textarea|select|date|checklist等
                    disabled: false,             //是否禁用编辑 ,默认 false
                    emptytext: "不可售建筑面积(请输入数字最多保留两位小数)",          //空值的默认文本
                    mode: "inline",              //编辑框的模式：支持popup和inline两种模式，默认是popup
                    validate: function (value) { //字段验证
                        if ($.isNumeric(value)){
                            landEngineering.target.find("input[name='unsaleableBuildingArea']").val(value).trigger('blur');
                        }else {
                            return '必须是数字';
                        }
                    },
                    display: function (value) {
                        $(this).text(value);
                    }
                });
            }
        });
    };



    /**
     math.sqrt(4) 开方
     math.add( ) 加
     math.subtract( )减
     math.divide( ) 除
     math.multiply( )乘
     */
</script>