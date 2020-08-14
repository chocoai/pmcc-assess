<!-- 成本法 -->
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/math/6.0.2/math.js?v=${assessVersion}"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
</head>
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

    construction.handleJquery = function (obj) {
        if (obj instanceof jQuery) {
            return obj;
        } else {
            return $(obj.selector);
        }
    };

    construction.ajaxServerMethod = function (data, url, type, callback) {
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
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    } else {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                }
            },
            error: function (result) {
                if (result.errmsg) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            }
        });
    };

    construction.typeData = function () {
        var target = $(construction.target.selector);
        return target.find("input[name='type']").val();
    };

    construction.selectFun = function (copyId, box) {
        var target = $("#" + box);
        AlertConfirm("是否确认引用", "引用后可继续根据实际情况来编辑", function () {
            construction.ajaxServerMethod({
                copyId: copyId,
                masterId: '${mdCostVo.mdCostConstruction.pid}'
            }, "/mdCostConstruction/copyConstructionById", "post", function () {
                notifySuccess("成功", "引用数据成功!");
                window.location.reload(true); //强制从服务器重新加载当前页面
                target.modal("hide");
            });
        });
    };

    /**调用比较法**/
    construction.callCompareMethod = function (this_) {
        var target = $(construction.target.selector);
        var mcId = target.find('[name=mcId]').val();
        var frame = layer.open({
            type: 2,
            title: '比较法',
            shadeClose: true,
            shade: true,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: '${pageContext.request.contextPath}/marketCompare/index?isLand=false&mcId=' + mcId + '&judgeObjectId=${projectPlanDetails.judgeObjectId}',
            cancel: function (index, layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
                if (iframe && iframe.marketCompare && iframe.marketCompare.mcId) {
                    target.find('[name=mcId]').val(iframe.marketCompare.mcId);
                }
            },
            btnAlign: 'c',
            btn: ['保存', '关闭'],
            yes: function (index, layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
                iframe.saveResult(function (mcId, price) {
                    target.find('[name=mcId]').val(mcId);
                    target.find('[name=landPurchasePrice]').val(price);
                    layer.closeAll('iframe');
                });
            },
            btn2: function (index, layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
                if (iframe && iframe.marketCompare && iframe.marketCompare.mcId) {
                    target.find('[name=mcId]').val(iframe.marketCompare.mcId);
                }
            }
        });
        layer.full(frame);
    };

    construction.calculationE13Select = function (_this) {
        var item = $(_this).find('option:selected');
        var pid = item.attr('data-key');
        var type = item.attr('data-type');
        var master = developmentCommon.isNotBlank('${mdCostVo.mdCostConstruction.id}') ? '${mdCostVo.mdCostConstruction.id}' : '0';
        if (pid) {
            construction.ajaxServerMethod({
                pid: pid,
                type: type
            }, "/dataInfrastructureChildren/getDataList", "get", function (data) {
                var arr = [];
                if (data.length == 0) {
                    return false;
                }
                $.each(data, function (i, n) {
                    var obj = {name: n.name, number: n.number, tax: n.tax};
                    obj.planDetailsId = '${projectPlanDetails.id}';
                    obj.type = construction.type;
                    obj.pid = master;
                    arr.push(obj);
                });
                developmentCommon.infrastructureChildren.saveArray(arr, function () {
                    notifySuccess("成功", "选择成功!");
                    $(construction.infrastructureChildrenTable).bootstrapTable('refresh');
                    construction.writeMdDevelopmentInfrastructureChildrenTable();
                });
            });
        } else {
            notifyWarning("警告", "无子项!");
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
                notifyWarning("警告", "请重新填写!");
                return false;
            }
            var obj = {};
            obj.masterId = target.find("input[name='masterId']").val();
            obj.price = Number(value);
            obj.id = target.find("input[name='id']").val();
            developmentCommon.saveMdArchitecturalObj2(developmentCommon.architecturalB.getFomData(table), obj, function (item) {
                notifySuccess("成功", "保存成功!");
            });
            var mdCalculatingMethodEngineeringCost = $(construction.engineeringFeeInfoTarget).bootstrapTable('getRowByUniqueId', obj.masterId);
            try {
                mdCalculatingMethodEngineeringCost.price = Number(obj.price) / Number(mdCalculatingMethodEngineeringCost.area);
                mdCalculatingMethodEngineeringCost.type = construction.typeData();
            } catch (e) {
            }
            developmentCommon.saveMdCalculatingMethodEngineeringCost(mdCalculatingMethodEngineeringCost, function (data) {
                $(construction.engineeringFeeInfoTarget).bootstrapTable('refresh');
                construction.writeMdCalculatingMethodEngineeringCost(data);
            });
            target.modal("hide");
        },
        //树 加载并且树赋值
        appendHTML: function (data, price) {
            var frm = $(construction.target.selector);
            var type = construction.typeData() ;
            var eleName = 'boxMdCostConstruction';
            var target = $("#" + eleName);
            var table = target.find("div[data-title=" + eleName + "]");
            table.empty() ;
            if (data == undefined) {
                data = [];
            }
            if (price == undefined) {
                price = '';
            }
            target.find("input[name='id']").val('');
            target.find("input[name='masterId']").val('');
            var options = {
                target: table,
                obj: data,
                attribute: null,
                price: price,
                reckon: 'c',
                callback: function (tr) {
                    if (type == cost.one) {
                        var table = $(tr).closest("table");
                        $(tr).find("input[name='valuationDateDegreeCompletion']").val('100%').attr({
                            readonly: 'readonly',
                            class: 'form-control',
                            'data-value': '1'
                        });
                        table.find("caption").find("[data-view-name=" + 'valuationDateDegreeCompletion' + "]").hide();
                    }
                }
            };
            developmentCommon.architecturalB.init(options);
            target.modal("show");
        }
    };

    /**
     * 工程费表格加载
     */
    construction.loadMdCalculatingMethodEngineeringCostTable = function () {
        var obj = {
            projectId: '${projectPlanDetails.projectId}',
            judgeObjectId: '${projectPlanDetails.judgeObjectId}',
            planDetailsId: '${projectPlanDetails.id}',
            type: construction.typeData()
        };
        var cols = [];
        cols.push({
            field: 'id', title: '建筑安装工程费明细', width: "20%", formatter: function (value, row, index) {
                var str = '<button type="button" onclick="construction.constructionInstallationEngineeringFeeEvent.detailsConstructionInstallation(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="建筑安装工程费明细">';
                str += '<i class="fa fa-cog"></i>';
                str += '</button>';
                return str;
            }
        });
        developmentCommon.loadMdCalculatingMethodEngineeringCostTable($(construction.engineeringFeeInfoTarget), obj, $("#toolbarMdCalculatingMethodEngineeringCost"), function () {
            construction.writeMdCalculatingMethodEngineeringCost();
        }, cols);
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
        data.judgeObjectId = '${projectPlanDetails.judgeObjectId}';
        data.projectId = '${projectPlanDetails.projectId}';
        data.planDetailsId = '${projectPlanDetails.id}';
        data.type = construction.typeData();
        if (! data.id){
            data.price = "0";
        }
        developmentCommon.saveMdCalculatingMethodEngineeringCost(data, function (item) {
            construction.writeMdCalculatingMethodEngineeringCost(item);
            target.modal("hide");
            //这里会同时生成 建筑安装工程费 详细情况id
            if (! data.id){
                notifySuccess("成功", "添加成功!");
                developmentCommon.saveMdArchitecturalObj2({}, {price: "0", pid: 0}, function (result) {
                    item.architecturalObjId = result.id;
                    developmentCommon.saveMdCalculatingMethodEngineeringCost(item);
                    construction.loadMdCalculatingMethodEngineeringCostTable();
                });
            } else {
                notifyWarning("提示", "请重新打开工程树计算单价!");
                construction.loadMdCalculatingMethodEngineeringCostTable();
            }
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
        var price = 0;
        var k = 0;
        $.each(result, function (i, n) {
            if ($.isNumeric(n.price)) {
                k++;
                price += parseFloat(n.price);
            }
        });
        if (k != 0) {
            price = price / k;
        }
        var target = $(construction.target.selector);
        target.find("input[name='constructionInstallationEngineeringFee']").val(price.toFixed(construction.fixed)).trigger('blur');
        target.find("input[name='reconnaissanceDesign']").trigger('blur');
    };

    /**
     * 工程费删除
     */
    construction.delMdCalculatingMethodEngineeringCost = function () {
        var rows = $(construction.engineeringFeeInfoTarget).bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            notifyWarning("警告", "请选择要删除的数据!");
        } else {
            AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
                developmentCommon.deleteMdCalculatingMethodEngineeringCostHandle(rows, function () {
                    notifySuccess("成功", "删除成功!");
                    $(construction.engineeringFeeInfoTarget).bootstrapTable('refresh');
                    construction.writeMdCalculatingMethodEngineeringCost();
                });
            });
        }
    };

    construction.editMdCalculatingMethodEngineeringCost = function () {
        var rows = $(construction.engineeringFeeInfoTarget).bootstrapTable('getSelections');
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
    };

    /**
     * 同步假设开发法建筑安装工程费
     */
    construction.setMdCalculatingMethodEngineeringCost = function (flag) {
        var planDetailsId = '${projectPlanDetails.id}';
        var json = {planDetailsId: planDetailsId, type: construction.typeData(), flag: flag};
        $.ajax({
            type: "post",
            url: getContextPath() + "/mdCostConstruction/setMdCalculatingMethodEngineeringCost",
            data: json,
            success: function (result) {
                if (result.ret) {
                    notifySuccess("成功", "同步建筑安装工程费 成功!");
                    construction.loadMdCalculatingMethodEngineeringCostTable();
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    };

    //经济指标show
    construction.showMdDevelopmentIncomeCategory = function () {
        var target = $(construction.target.selector);
        var economicId = '${mdCostVo.mdCostConstruction.economicId}';
        if (!economicId) {
            economicId = target.find("input[name='economicId']").val();

        }
        if (economicId) {
            economicIndicators.init({
                economicId: economicId, targetCallback: function (target) {
                    //target.find("tbody").find("input[name='unitPrice']").attr({disabled:'disabled'});
                }
            });
        } else {
            economicIndicators.init({
                planDetailsId: '${projectPlanDetails.id}',
                saveCallback: function (economicId) {//经济指标id更新到中间表
                    var centerId = '${mdCostVo.mdCostConstruction.centerId}';
                    if (centerId) {
                        declareCommon.declareBuildCenterSaveAndUpdate({indicatorId: economicId, id: centerId});
                    }
                    target.find("input[name='economicId']").val(economicId).trigger('blur');
                },
                targetCallback: function () {
                    economicIndicators.autoSummary(true);
                }
            });
        }

        $('#modalEconomicIndicators').find('.modal-footer').find('button').last().bind('click', function () {
            var data = economicIndicators.getFormData();
            console.log(data) ;
            if (data) {
                cost.initParcelSettingData(data);
                var developLandAreaTax = data.developLandAreaTax;
                if (developLandAreaTax) {
                    target.find("input[name='developLandAreaTax']").val(developLandAreaTax).trigger('blur');
                }
                var developBuildAreaTax = data.developBuildAreaTax;
                if (developBuildAreaTax) {
                    target.find("input[name='developBuildAreaTax']").val(developBuildAreaTax).trigger('blur');
                }
            }
        });
    };


    /**基础设施建设 table*/
    construction.loadMdDevelopmentInfrastructureChildrenTable = function () {
        var pid = developmentCommon.isNotBlank('${mdCostVo.mdCostConstruction.id}') ? '${mdCostVo.mdCostConstruction.id}' : '0';
        var data = {planDetailsId: '${projectPlanDetails.id}', pid: pid};
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
            AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
                developmentCommon.infrastructureChildren.delete(data, function () {
                    notifyInfo('提示', "删除成功!");
                    $(construction.infrastructureChildrenTable).bootstrapTable('refresh');
                    construction.writeMdDevelopmentInfrastructureChildrenTable();
                });
            });
        } else {
            notifyWarning("警告", "至少勾选一个!");

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
                notifyWarning("警告", "至少勾选一个!");
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
            notifySuccess("成功", "添加成功!");
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
            if ('${mdCostVo.mdCostConstruction.infrastructureCost}') {
                if (result == 0) {
                    result += Number('${mdCostVo.mdCostConstruction.infrastructureCost}');
                }
            }
            var target = $(construction.target.selector);
            target.find("input[name='infrastructureCost']").val(result).trigger('blur');
        });
    };

    /**
     * 成新率
     */
    construction.callResidueRatio = function (_this, readonly) {
        //获取已使用年限,当前评估基准日-楼栋的竣工时间
        //获取可用年限，根据建筑使用年限配置而来
        var target = $(construction.target.selector);
        try {
            residueRatio.init({
                readonly: readonly,
                residueRatioId: target.find("input[name='residueRatioId']").val(),
//                usedYear: 0,
//                usableYear: 5,
                houseId: '${basicHouseVo.id}',
                success: function (id, resultValue) {
                    console.log(resultValue);
                    var target = $(_this).closest(".input-group");
                    target.find("input[name='residueRatioId']").val(id);
                    target.find("input[name='residueRatio']").val(resultValue);
                    target.find("input[name='residueRatio']").attr( "data-value",parseFloat(resultValue) / 100).trigger('blur');
                    cost.calculationNumeric(formSerializeArray($(_this).closest("form")), function (data) {
                        cost.initForm(data);
                    });
                }
            });
        } catch (e) {
        }
    };
</script>

<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <%@include file="/views/share/project/projectInfoSimple.jsp" %>
                    <%@include file="/views/share/project/projectPlanDetails.jsp" %>


                    <script src="${pageContext.request.contextPath}/js/method/developmentCommon.js?v=${assessVersion}"></script>
                    <%@include file="/views/method/module/developmentCommon.jsp" %>

                    <%@include file="/views/method/module/economicIndicators.jsp" %>

                    <%@include file="/views/project/tool/residueRatio.jsp" %>

                    <script type="text/javascript"
                            src="${pageContext.request.contextPath}/js/declare/declare.common.js?v=${assessVersion}"></script>

                    <!-- 引入开发法模块 -->
                    <%@include file="/views/method/marketCostIndex.jsp" %>

                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="card full-height">
                            <div class="card-body">

                                <input type="hidden" id="supportInfosJSON" value='${supportInfosJSON}'>

                                <form class="form-horizontal" id="md_cost_form">
                                    <input type="hidden" name="id" value="${mdCostVo.id}">
                                    <div class="row form-group">
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <div class="form-inline x-valid">
                                                <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                                                    单价
                                                </label>
                                                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                                    <input type="text" class="form-control input-full"
                                                           data-rule-number="true" required
                                                           name="price" value="${mdCostVo.price}">
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
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>


<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
<script type="text/javascript">

    var cost = {};
    cost.constructionFrm = $("#constructionFrm");

    cost.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };
    cost.one = 1;
    cost.two = 2;

    cost.isNotBlankObject = function (obj) {
        for (var key in obj) {
            return true;
        }
        return false
    };

    //参数校验
    cost.checkParams = function (that) {
        if (!cost.isNotBlank(that)) {
            return false;
        }
        var value = $(that).val();
        var i = 0;
        if (!cost.isNotBlank(value)) {
            return false;
        }
        if (AssessCommon.isNumber(value)) {
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
        //从服务端获取计算后的数据
        cost.calculationNumeric(formSerializeArray(cost.constructionFrm), function (data) {
            cost.initForm(data);
        });
    };

    cost.checkedFun = function (that, name, flag) {
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

    cost.initForm = function (data) {


//        cost.constructionFrm.initForm(data);

        cost.constructionFrm.find("input[name='landGetCostTotal']").val(data.landGetCostTotal);
        cost.constructionFrm.find(".landGetCostTotal").html(data.landGetCostTotal);
        cost.constructionFrm.find("input[name='constructionSubtotal']").val(data.constructionSubtotal);
        cost.constructionFrm.find(".constructionSubtotal").html(data.constructionSubtotal);
        cost.constructionFrm.find("input[name='interestInvestment']").val(data.interestInvestment);
        cost.constructionFrm.find(".interestInvestment").html(data.interestInvestment);
        cost.constructionFrm.find("input[name='investmentProfit']").val(data.investmentProfit);
        cost.constructionFrm.find(".investmentProfit").html(data.investmentProfit);
        cost.constructionFrm.find("input[name='constructionAssessmentValue']").val(data.constructionAssessmentValue);
        cost.constructionFrm.find(".constructionAssessmentValue").html(data.constructionAssessmentValue);
        cost.constructionFrm.find("input[name='constructionAssessmentPriceCorrecting']").val(data.constructionAssessmentPriceCorrecting);
        cost.constructionFrm.find(".constructionAssessmentPriceCorrecting").html(data.constructionAssessmentPriceCorrecting);

        if ($("#md_cost_form").size() != 0) {
            $("#md_cost_form").find("input[name='price']").val(data.constructionAssessmentPriceCorrecting);
        }

    };

    cost.writeValueEvent = function (value, callback) {
        var target = $("#LAND_ACQUISITION_COST");
        cost.constructionFrm.find("input[name='type']").val(value);
        if (value == cost.one) {
            target.find("input").each(function () {
                var text = $(this).val();
                if (text) {
                    var className = $(this).prop("class");
                    if (className.indexOf('x-percent') != -1) {
                        text = $(this).attr("data-value");
                    }
                    $(this).attr({'data-value': 0, 'obj-value': text}).val(0);
                }
            });
            target.hide();
            var landGetCostTotal = cost.constructionFrm.find("input[name='landGetCostTotal']").val();
            cost.constructionFrm.find("input[name='landGetCostTotal']").val(0).attr({'obj-value': landGetCostTotal});
            cost.constructionFrm.find(".landGetCostTotal").parent().parent().hide();
            cost.constructionFrm.find(".residueRatio").show();
        }
        if (value == cost.two) {
            target.show();
            target.find("input").each(function () {
                var text = $(this).attr("obj-value");
                if (text) {
                    $(this).attr({'data-value': text, 'obj-value': 0}).val(text);
                    var className = $(this).prop("class");
                    if (className.indexOf('x-percent') != -1) {
                        var vv = Number(text) * 100;
                        vv = vv.toFixed(2);
                        vv += "%";
                        $(this).val(vv);
                    }
                }
            });

            var landGetCostTotal2 = cost.constructionFrm.find("input[name='landGetCostTotal']").attr('obj-value');
            cost.constructionFrm.find("input[name='landGetCostTotal']").val(landGetCostTotal2);
            cost.constructionFrm.find(".landGetCostTotal").parent().parent().show();
            cost.constructionFrm.find(".residueRatio").hide();
        }
        if (callback) {
            callback(value);
        }
    };

    cost.initParcelSettingData = function (data) {
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
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"cost.checkedFun(this,'parcelSettingOuter',true)\">";
            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '反选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"cost.checkedFun(this,'parcelSettingOuter',false)\">";
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
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"cost.checkedFun(this,'parcelSettingInner',true)\">";
            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '反选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"cost.checkedFun(this,'parcelSettingInner',false)\">";
            developmentDegreeContentContainer.empty().append(resultHtml);
        });
    };

    cost.calculationNumeric = function (data, callback) {
        $.ajax({
            type: "post",
            url: getContextPath() + "/mdCostConstruction/calculationNumeric",
            data: {fomData: JSON.stringify(data)},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    };


    $(document).ready(function () {
        var target = $(construction.target.selector);
        var tool = $("#costCheckboxTool");
        //建筑安装工程费 估价时点完工程度的设置为100%
        var type = '${mdCostVo.type}';
        if (cost.isNotBlank(type)) {
            tool.find("input[name=typeShow]").each(function (i, ele) {
                var element = $(ele);
                var value = element.attr('data-value');
                if (value == type) {
                    element.attr('checked', 'true');
                } else {
                    element.removeAttr("checked");
                }
            });
            target.find("input[name='type']").val(type);
            cost.writeValueEvent(type, function () {
                construction.loadMdCalculatingMethodEngineeringCostTable();
            });
        }

        tool.find("input[name='typeShow']:radio").change(function () {
            var value = $(this).attr('data-value');
            target.find("input[name='type']").val(value);
            cost.writeValueEvent(value, function () {
                try {
                    construction.loadMdCalculatingMethodEngineeringCostTable();
                } catch (e) {
                    console.log(e);
                }
            });
        });
        if (!cost.isNotBlank('${mdCostVo.mdCostConstruction.id}')) {
            var query = {
                province: '${schemeAreaGroup.province}',
                city: '${schemeAreaGroup.city}',
                district: '${schemeAreaGroup.district}',
                bisNationalUnity: true
            };
            $.ajax({
                type: "get",
                url: "${pageContext.request.contextPath}/dataTaxRateAllocation/specialTreatment",
                data: query,
                success: function (result) {
                    if (result.ret) {
                        if (result.data) {
                            $.each(result.data, function (i, item) {
                                var taxRate = item.taxRate;
                                if (item.taxRate) {
                                    taxRate = Number(item.taxRate) * 100;
                                    taxRate = taxRate + "%";
                                }
                                if (item.typeName == '增值税金及附加') {
                                    if (item.taxRate) {
                                        cost.constructionFrm.find("input[name='salesTaxAndAdditional']").val(taxRate);
                                        cost.constructionFrm.find("input[name='salesTaxAndAdditional']").attr("data-value", item.taxRate);
                                    }
                                }
                                if (item.fieldName == '管理费率') {
                                    if (item.taxRate) {
                                        cost.constructionFrm.find("input[name='managementExpense']").val(taxRate);
                                        cost.constructionFrm.find("input[name='managementExpense']").attr("data-value", item.taxRate);
                                    }
                                }
                            });
                        }
                    } else {
                        AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            });
        }


        construction.loadMdCalculatingMethodEngineeringCostTable();
        construction.loadMdDevelopmentInfrastructureChildrenTable();

        var data = {
            parcelSettingInner: '${mdCostVo.mdCostConstruction.parcelSettingInner}',
            parcelSettingOuter: '${mdCostVo.mdCostConstruction.parcelSettingOuter}'
        };
        cost.initParcelSettingData(data);


    });

</script>
<script type="application/javascript">
    $(function () {
        FileUtils.uploadFiles({
            target: "report_file",
            disabledTarget: "btn_submit",
            formData: {
                tableName: AssessDBKey.MdCost,
                tableId: '${mdCostVo.id}',
                projectId: "${projectInfo.id}"
            },
            editFlag: true,
            deleteFlag: true
        });
        FileUtils.getFileShows({
            target: "report_file",
            formData: {
                tableName: AssessDBKey.MdCost,
                tableId: '${mdCostVo.id}'
            },
            editFlag: true,
            deleteFlag: true
        })
    });
    //提交
    function submit() {
        var data = formSerializeArray(cost.constructionFrm);
        if (!cost.constructionFrm.valid()) {
            return false;
        }
        var item = formSerializeArray($("#md_cost_form"));
        if (item) {
            if (item.price) {
                data.constructionAssessmentPriceCorrecting = item.price;
            }
        }
        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(data));
        } else {
            submitToServer(JSON.stringify(data));
        }
    }
</script>

</html>

