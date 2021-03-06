/**
 * Created by asus on 2018/11/24.
 */

;(function ($) {
    var houseCommon = {};
    houseCommon.houseTradingForm = $('#basicTradingFrm');
    houseCommon.houseForm = $('#basicHouseFrm');
    houseCommon.houseHuxingForm = $('#basicHouseHuxing');
    houseCommon.landCategoryInfoForm = $('#landCategoryInfoFrm');
    houseCommon.houseTradingTypeSell = 'ExamineHouseTradingSell';
    houseCommon.houseTradingTypeLease = 'ExamineHouseTradingLease';
    houseCommon.houseMapiframe = undefined;
    houseCommon.tableId = undefined;
    /**
     * 判断对象
     */
    houseCommon.isNotBlankObject = function (obj) {
        for (var key in obj) {
            return true;
        }
        return false
    };

    /**
     * 空字符串检测
     * @param item
     * @returns {boolean}
     */
    houseCommon.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    //附件上传控件id数组
    houseCommon.houseFileControlIdArray = [];

    houseCommon.getUploadKey = function (bisDetail) {
        houseCommon.getFilePartHtml(AssessDicKey.examineHouseFilePart, bisDetail);
        houseCommon.getFilePartHtml(AssessDicKey.examineHouseHuxingFilePart, bisDetail);
        houseCommon.getFilePartHtml(AssessDicKey.examineHouseTradingFilePart, bisDetail);
    };

    houseCommon.getFilePartHtml = function (fieldName, bisDetail) {
        var fileDiv = $('#' + fieldName);
        fileDiv.empty();
        var houseFileHtml = '';
        AssessCommon.loadAsyncDataDicByKey(fieldName, '', function (html, resultData) {

            var groupIndex = 2;//分成2个一组
            var result = [];
            for (var k = 0, len = resultData.length; k < len; k += groupIndex) {
                result.push(resultData.slice(k, k + groupIndex));
            }
            $.each(result, function (i, data) {
                houseFileHtml += '<div class="row form-group">';
                houseFileHtml += '<div class="col-md-12">';
                houseFileHtml += '<div class="form-inline x-valid">';
                $.each(data, function (j, item) {
                    houseFileHtml += '<label class="col-sm-1 col-md-2">' + item.name + '</label>';
                    houseFileHtml += '<div class="col-sm-5 col-md-4">';
                    if (bisDetail != false) {
                        houseFileHtml += '<input id="' + item.fieldName + '" placeholder="上传附件" class="form-control input-full" type="file">';
                    }
                    houseFileHtml += '<div id="_' + item.fieldName + '"></div>';
                    houseFileHtml += '</div>';
                    houseCommon.houseFileControlIdArray.push(item.fieldName);
                });
                houseFileHtml += "</div>";
                houseFileHtml += "</div>";
                houseFileHtml += "</div>";
            });

        }, false);
        fileDiv.append(houseFileHtml);
    };

    houseCommon.getHouseId = function () {
        return houseCommon.houseForm.find('[name=id]').val() != null ? houseCommon.houseForm.find('[name=id]').val() : houseCommon.tableId;
    };

    houseCommon.getUnitId = function () {
        return houseCommon.houseForm.find('[name=unitId]').val();
    };

    //户型选择
    houseCommon.selectHuxing = function (_this) {
        assessHuxing.selectByBasicUnitId({
            basicUnitId: houseCommon.getUnitId(),
            success: function (row) {
                //1.赋值 2.拷贝附件并显示附件数据
                $(_this).closest('.input-group').find('[name=huxingId]').val(row.id);
                $(_this).closest('.input-group').find(':text').val(row.name);
                houseCommon.houseForm.find('[name=area]').val(row.area);
                houseCommon.houseForm.find('[name=orientation]').val(row.orientation).trigger('change');
                $.ajax({
                    url: getContextPath() + '/basicHouse/copyHuxingPlan',
                    data: {
                        sourceTableId: row.id,
                        sourceTableName: row.tableName,
                        targetTableId: houseCommon.getHouseId(),
                        fieldsName: houseCommon.houseFileControlIdArray[0]
                    },
                    success: function (result) {
                        houseCommon.fileShow(houseCommon.houseFileControlIdArray[0], false);
                    }
                })
            }
        })
    };

    //户型选择
    houseCommon.selectHuxingAlone = function (_this) {
        assessHuxing.select({
            basicApplyId: basicCommon.getApplyId(),
            caseUnitId: null,
            success: function (row) {
                //1.赋值 2.拷贝附件并显示附件数据
                $(_this).closest('.input-group').find('[name=huxingId]').val(row.id);
                $(_this).closest('.input-group').find(':text').val(row.name);
                houseCommon.houseForm.find('[name=area]').val(row.area);
                houseCommon.houseForm.find('[name=orientation]').val(row.orientation).trigger('change');
                $.ajax({
                    url: getContextPath() + '/basicHouse/copyHuxingPlan',
                    data: {
                        sourceTableId: row.id,
                        sourceTableName: row.tableName,
                        targetTableId: houseCommon.getHouseId(),
                        fieldsName: houseCommon.houseFileControlIdArray[0]
                    },
                    success: function (result) {
                        houseCommon.fileShow(houseCommon.houseFileControlIdArray[0], false);
                        houseCommon.deleteHouseTagging();
                    }
                })
            }
        })
    };

    //房屋初始化by applyId
    houseCommon.init = function (applyId, callback) {
        $.ajax({
            url: getContextPath() + '/basicHouse/getBasicHouseByApplyId',
            type: 'get',
            data: {applyId: applyId},
            success: function (result) {
                if (result.ret) {
                    houseCommon.showHouseView(result.data);
                    if (callback) {
                        callback(result.data);
                    }
                }
            }
        })
    };

    //房屋初始化by applyId
    houseCommon.initById = function (id, callback) {
        $.ajax({
            url: getContextPath() + '/basicHouse/getBasicHouseMapById',
            type: 'get',
            data: {id: id},
            success: function (result) {
                houseCommon.showHouseView(result.data);
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                }
            }
        })
    };

    //房屋详情页
    houseCommon.initDetailById = function (id, callback, bisDetail) {
        $.ajax({
            url: getContextPath() + '/basicHouse/getBasicHouseMapById',
            type: 'get',
            data: {id: id},
            success: function (result) {
                houseCommon.initForm(result.data, bisDetail);
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                }
            }
        })
    };

    //房屋明细
    houseCommon.detail = function (applyId, callback) {
        $.ajax({
            url: getContextPath() + '/basicHouse/getBasicHouseByApplyId',
            type: 'get',
            data: {applyId: applyId},
            success: function (result) {
                if (result.ret) {
                    houseCommon.initForm(result.data);
                    if (callback) {
                        callback(result.data);
                    }
                }
            }
        })
    };

    houseCommon.showHouseView = function (data) {
        houseCommon.initForm(data);
    };

    //房屋初始化以及赋值
    houseCommon.initForm = function (data, bisDetail) {
        houseCommon.getUploadKey(bisDetail);
        if (!data || !data.basicHouse) return;
        //基本信息
        houseCommon.houseForm.initForm(data.basicHouse, function () {
            //1.初始化下拉框；2.初始化上传控件；3.显示已上传的附件信息；
            AssessCommon.loadTextAppendDicHtml(AssessDicKey.examineHouseLoadUtility, null, function (html, data) {
                houseCommon.houseForm.find("#certUseList").empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadTextAppendDicHtml(AssessDicKey.examineHousePracticalUse, null, function (html, data) {
                houseCommon.houseForm.find("#practicalUseList").empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseEnvironmentUse, data.basicHouse.useEnvironment, function (html, data) {
                houseCommon.houseForm.find("select.useEnvironment").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineCommonOrientation, data.basicHouse.orientation, function (html, data) {
                houseCommon.houseForm.find("select.orientation").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseResearchType, data.basicHouse.researchType, function (html, data) {
                houseCommon.houseForm.find("select.researchType").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseSpatialDistribution, data.basicHouse.spatialDistribution, function (html, data) {
                houseCommon.houseForm.find("select.spatialDistribution").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseUseCondition, data.basicHouse.useCondition, function (html, data) {
                houseCommon.houseForm.find("select.useCondition").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseDecorateSituation, data.basicHouse.decorateSituation, function (html, data) {
                houseCommon.houseForm.find("select.decorateSituation").empty().html(html).trigger('change');
            });
            if (!houseCommon.isNotBlank(data.basicHouse.decorateSituationDescription)) {
                //变更
                houseCommon.houseForm.find("select.decorateSituation").off('change').on('change', function () {
                    if ($(this).find('option:selected').val()) {
                        var description = $(this).find('option:selected').text();
                        houseCommon.houseForm.find("[name=decorateSituationDescription]").val(description);
                    }
                });
            }
            houseCommon.showUseCondition(data);
            houseCommon.showHouseDecorate(data);

            //初始化上传控件
            $.each(houseCommon.houseFileControlIdArray, function (i, item) {
                houseCommon.fileUpload(item);
                if (bisDetail == false) {
                    houseCommon.fileShow(item, false);
                } else {
                    houseCommon.fileShow(item, true);
                }
            });
        });

        //交易情况
        houseCommon.houseTradingForm.initForm(data.basicHouseTrading, function () {
            if (data.basicHouseTrading != null) {
                houseCommon.changeEvent(data.basicHouseTrading);
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseTransactionSituation, data.basicHouseTrading.transactionSituation, function (html, data) {
                    houseCommon.houseTradingForm.find("select.transactionSituation").empty().html(html).trigger('change');
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseScopeProperty, data.basicHouseTrading.scopeProperty, function (html, data) {
                    houseCommon.houseTradingForm.find("select.scopeProperty").empty().html(html).trigger('change');
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHousetaxBurden, data.basicHouseTrading.taxBurden, function (html, data) {
                    houseCommon.houseTradingForm.find("select.taxBurden").empty().html(html).trigger('change');
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseDescriptionType, data.basicHouseTrading.descriptionType, function (html, data) {
                    houseCommon.houseTradingForm.find("select.descriptionType").empty().html(html).trigger('change');
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseTransactionType, data.basicHouseTrading.tradingType, function (html, data) {
                    houseCommon.houseTradingForm.find("select.tradingType").empty().html(html).trigger('change');
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseTransactionTransactionLevel, data.basicHouseTrading.transactionLevel, function (html, data) {
                    houseCommon.houseTradingForm.find("select[name=transactionLevel]").empty().html(html).trigger('change');
                }, true);
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseFinancingConditions, data.basicHouseTrading.financingConditions, function (html, data) {
                    houseCommon.houseTradingForm.find("select.financingConditions").empty().html(html).trigger('change');
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseInformationSourceType, data.basicHouseTrading.informationType, function (html, data) {
                    houseCommon.houseTradingForm.find("select.informationType").empty().html(html).trigger('change');
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseInformationSourceCategory, data.basicHouseTrading.informationCategory, function (html, data) {
                    houseCommon.houseTradingForm.find("select.informationCategory").empty().html(html).trigger('change');
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePriceConnotation, data.basicHouseTrading.priceConnotation, function (html, data) {
                    houseCommon.houseTradingForm.find("select.priceConnotation").empty().html(html).trigger('change');
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHouse_transaction_price_type, data.basicHouseTrading.priceType, function (html, data) {
                    houseCommon.houseTradingForm.find("select[name='priceType']").empty().html(html).trigger('change');
                });
                houseCommon.showPriceConnotationUnit(data);
                if (bisDetail == false) {
                    houseCommon.showTradingCondition(data.basicHouseTrading);
                    houseCommon.loadTradingSellAndLeaseList(AssessDicKey.examineHouseTransactionTypeSell, true);
                    houseCommon.loadTradingSellAndLeaseList(AssessDicKey.examineHouseTransactionTypeLease, true);

                }
                houseCommon.loadRestrictionsHTML(data.basicHouseTrading, houseCommon.houseTradingForm);
            }
        });

        //户型
        houseCommon.houseHuxingForm.initForm(data.basicHouseHuxing, function () {
            if (data.basicHouseHuxing != null) {
                AssessCommon.loadTextAppendDicHtml(AssessDicKey.examineHouseTenementType, null, function (html, data) {
                    houseCommon.houseHuxingForm.find("#tenementTypeList").empty().html(html).trigger('change');
                }, true);
                AssessCommon.loadDataDicByKey(AssessDicKey.examineCommonOrientation, data.basicHouseHuxing.orientation, function (html, data) {
                    houseCommon.houseHuxingForm.find("select.orientation").empty().html(html).trigger('change');
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseSpatialDistribution, data.basicHouseHuxing.spatialDistribution, function (html, data) {
                    houseCommon.houseHuxingForm.find("select.spatialDistribution").empty().html(html).trigger('change');
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseUtilitiesMeasure, data.basicHouseHuxing.utilitiesMeasure, function (html, data) {
                    houseCommon.houseHuxingForm.find("select.utilitiesMeasure").empty().html(html).trigger('change');
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseUtilitiesType, data.basicHouseHuxing.utilitiesType, function (html, data) {
                    houseCommon.houseHuxingForm.find("select.utilitiesType").empty().html(html).trigger('change');
                });
                houseCommon.showUtilitiesType(data);
                houseCommon.tenementTypeChange(data);
            }

        });

        //土地类型
        houseCommon.landCategoryInfoForm.initForm(data.landCategoryInfo, function () {
            if (data.landCategoryInfo != null) {
                AssessCommon.loadTextAppendDicHtml(AssessDicKey.estate_compatibility_rate, null, function (html, data) {
                    houseCommon.landCategoryInfoForm.find("#compatibilityTypeList").empty().html(html).trigger('change');
                }, false);
                AssessCommon.loadTextAppendDicHtml(AssessDicKey.estate_total_land_use, null, function (html, data) {
                    houseCommon.landCategoryInfoForm.find("#landUseTypeList").empty().html(html).trigger('change');
                }, false);
            }
        });
        //完损度数据加载
        try {
            damagedDegree.loadDamagedDegreeList();
        } catch (e) {
        }
    };

    houseCommon.landUseTypeChange = function () {
        var value = houseCommon.landCategoryInfoForm.find('[name=landUseType]').val();
        AssessCommon.getSonTextAppendDicList(AssessDicKey.estate_total_land_use, value, null, function (html, data) {
            houseCommon.landCategoryInfoForm.find("#landUseCategoryList").empty().html(html).trigger('change');
        });
    }

    houseCommon.showUseCondition = function (data) {
        if (houseCommon.isNotBlank(data.basicHouse.useCondition)) {
            var strArr = ["出租", "自用"];//来自于实体描述1(1).docx中的规则
            var useConditionId = data.basicHouse.useCondition;
            if (useConditionId) {
                AssessCommon.getDataDicInfo(useConditionId, function (useConditionData) {
                    var str = strArr.join(",");
                    //当属于数组中的任意一项时显示
                    if (str.indexOf(useConditionData.name) != -1) {
                        $("#useConditionDescription").parent().parent().parent().parent().hide();
                    } else {
                        $("#useConditionDescription").parent().parent().parent().parent().show();
                    }
                });
            }
        } else {
            $("#useConditionDescription").parent().parent().parent().parent().hide();
        }

        //绑定变更事件
        houseCommon.houseForm.find("select.useCondition").off('change').on('change', function () {
            var strArr = ["出租", "自用"];//来自于实体描述1(1).docx中的规则
            var useConditionId = houseCommon.houseForm.find("select.useCondition").val();
            if (useConditionId) {
                AssessCommon.getDataDicInfo(useConditionId, function (useConditionData) {
                    var str = strArr.join(",");
                    //当属于数组中的任意一项时显示
                    if (str.indexOf(useConditionData.name) != -1) {
                        $("#useConditionDescription").parent().parent().parent().parent().hide();
                    } else {
                        $("#useConditionDescription").parent().parent().parent().parent().show();
                    }
                });
            }
        });
    };

    houseCommon.showHouseDecorate = function (data) {
        if (houseCommon.isNotBlank(data.basicHouse.decorateSituation)) {
            var strArr = ["清水", "毛坯"];//来自于实体描述1(1).docx中的规则
            var decorateSituationId = data.basicHouse.decorateSituation;
            if (decorateSituationId) {
                AssessCommon.getDataDicInfo(decorateSituationId, function (decorateSituationData) {
                    var str = strArr.join(",");
                    //当属于数组中的任意一项时显示
                    if (str.indexOf(decorateSituationData.name) > -1) {
                        $("#showHouseDecorate").hide();
                    } else {
                        $("#showHouseDecorate").show();
                    }
                });
            }
        }

        //绑定变更事件
        houseCommon.houseForm.find("select.decorateSituation").off('change').on('change', function () {
            var strArr = ["清水", "毛坯"];//来自于实体描述1(1).docx中的规则
            var decorateSituationId = houseCommon.houseForm.find("select.decorateSituation").val();
            if (decorateSituationId) {
                AssessCommon.getDataDicInfo(decorateSituationId, function (decorateSituationData) {
                    var str = strArr.join(",");
                    //当属于数组中的任意一项时显示
                    if (str.indexOf(decorateSituationData.name) > -1) {
                        $("#showHouseDecorate").hide();
                    } else {
                        $("#showHouseDecorate").show();
                    }
                });
            }
            if ($(this).find('option:selected').val()) {
                var description = $(this).find('option:selected').text();
                houseCommon.houseForm.find("[name=decorateSituationDescription]").val(description);
            }
        });
    }

    houseCommon.getPriceExportColumns = function (tenementType) {
        if (tenementType) {
            AssessCommon.ajaxServerMethod({
                tenementType: tenementType,
                num: 1
            }, "/basicTenementType/getTenementTypeValue", "get", function (value) {
                houseCommon.houseHuxingForm.find("input[name='priceExportColumns']").val(JSON.stringify(value));
            });
        }
    };

    houseCommon.tenementTypeChange = function (data) {
        if (houseCommon.isNotBlank(data.basicHouseHuxing.tenementType)) {
            houseCommon.getPriceExportColumns(data.basicHouseHuxing.tenementType);
        }

        //绑定变更事件
        houseCommon.houseHuxingForm.find("input[name='tenementType']").off('change').on('change', function () {
            var tenementType = houseCommon.houseHuxingForm.find("input[name='tenementType']").val();
            houseCommon.getPriceExportColumns(tenementType);
            //房间及单价确定表的列表字段
            houseHuxingPrice.prototype.loadDataDicList();
            houseRoom.prototype.loadDataDicList();
        });
    };

    houseCommon.showUtilitiesType = function (data) {
        if (houseCommon.isNotBlank(data.basicHouseHuxing.utilitiesMeasure)) {
            var strArr = ["非标准"];//来自于实体描述1(1).docx中的规则
            var utilitiesMeasureId = data.basicHouseHuxing.utilitiesMeasure;
            if (utilitiesMeasureId) {
                AssessCommon.getDataDicInfo(utilitiesMeasureId, function (utilitiesMeasureData) {
                    var str = strArr.join(",");
                    //当属于数组中的任意一项时显示
                    if (str.indexOf(utilitiesMeasureData.name) > -1) {
                        $(".utilitiesTypeContent").show();
                    } else {
                        $(".utilitiesTypeContent").hide();
                    }
                });
            }
        } else {
            $("#utilitiesTypeContent").hide();
        }

        //绑定变更事件
        houseCommon.houseHuxingForm.find("select.utilitiesMeasure").off('change').on('change', function () {
            var strArr = ["非标准"];//来自于实体描述1(1).docx中的规则
            var utilitiesMeasureId = houseCommon.houseHuxingForm.find("select.utilitiesMeasure").val();
            if (utilitiesMeasureId) {
                AssessCommon.getDataDicInfo(utilitiesMeasureId, function (utilitiesMeasureData) {
                    var str = strArr.join(",");
                    //当属于数组中的任意一项时显示
                    if (str.indexOf(utilitiesMeasureData.name) > -1) {
                        $(".utilitiesTypeContent").show();
                    } else {
                        $(".utilitiesTypeContent").hide();
                    }
                });
            }
        });
    }

    houseCommon.showPriceConnotationUnit = function (data) {
        if (houseCommon.isNotBlank(data.basicHouseTrading.priceConnotation)) {
            var strArr = ["建筑面积单价", "套内面积单价"];//来自于实体描述1(1).docx中的规则
            var priceConnotationId = data.basicHouseTrading.priceConnotation;
            if (priceConnotationId) {
                AssessCommon.getDataDicInfo(priceConnotationId, function (priceConnotationData) {
                    var str = strArr.join(",");
                    //当属于数组中的任意一项时显示
                    if (str.indexOf(priceConnotationData.name) != -1) {
                        houseCommon.houseTradingForm.find(".priceConnotationUnitContent").hide();
                    } else {
                        houseCommon.houseTradingForm.find(".priceConnotationUnitContent").show();
                    }
                });
            }
        }
        //绑定变更事件
        houseCommon.houseTradingForm.find("select[name='priceConnotation']").off('change').on('change', function () {
            var strArr = ["建筑面积单价", "套内面积单价"];//来自于实体描述1(1).docx中的规则
            var priceConnotationId = houseCommon.houseTradingForm.find("select.priceConnotation").val();
            if (priceConnotationId) {
                AssessCommon.getDataDicInfo(priceConnotationId, function (priceConnotationData) {
                    var str = strArr.join(",");
                    //当属于数组中的任意一项时显示
                    if (str.indexOf(priceConnotationData.name) != -1) {
                        houseCommon.houseTradingForm.find(".priceConnotationUnitContent").hide();
                    } else {
                        houseCommon.houseTradingForm.find(".priceConnotationUnitContent").show();
                    }
                });
            }
        });

    }

    houseCommon.showTradingCondition = function (data) {
        var value = data.paymentMethod;
        AssessCommon.getDataDicInfo(value, function (bicData) {
            var key = bicData.fieldName;
            if (key == AssessDicKey.examineHousePaymentMethodInstallment) {
                $(this).closest('.form-group').children().eq(2).show();
            } else {
                $(this).closest('.form-group').children().eq(2).hide();
            }
            if (key == AssessDicKey.examineHousePaymentMethodDisposable || key == AssessDicKey.examineHousePaymentMethodLeaseDisposable) {
                houseCommon.houseTradingForm.find('.tradingCondition').hide();
            } else {
                houseCommon.houseTradingForm.find('.tradingCondition').show();
            }
        });

    };

    //附件上传
    houseCommon.fileUpload = function (fieldsName) {
        FileUtils.uploadFiles({
            target: fieldsName,
            disabledTarget: "btn_submit",
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.BasicHouse,
                tableId: houseCommon.getHouseId()
            },
            deleteFlag: true,
            onUploadComplete: function () {
                houseCommon.fileShow(fieldsName);
            }
        });
    };

    //显示土地位置等字段
    houseCommon.showReplenishLand = function (projectTypeId) {
        AssessCommon.getProjectClassifyInfo(projectTypeId, function (data) {
            var projectTypeField = data.fieldName;
            switch (projectTypeField) {
                //房产
                case AssessProjectClassifyKey.singleHousePropertyCertificateTypeSimple: {
                    $("#replenishLand").hide();
                    break;
                }
                //土地
                case AssessProjectClassifyKey.singleHouseLandCertificateTypeSimple: {
                    break;
                }
            }
        });
    };

    //附件显示
    houseCommon.fileShow = function (fieldsName, deleteFlag) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.BasicHouse,
                tableId: houseCommon.getHouseId()
            },
            deleteFlag: deleteFlag == undefined ? true : deleteFlag,
        })
    };


    //下拉框change事件
    houseCommon.changeEvent = function (basicHouseTrading) {
        houseCommon.houseTradingForm.find("select.transactionSituation").off('change').on('change', function () {
            var key = $(this).find("option:selected").attr('key');
            if (key == AssessDicKey.examineHouseTransactionAbnormal) {
                $("#abnormalTransaction").show();
            } else {
                $("#abnormalTransaction").hide();
            }
        });

        houseCommon.houseTradingForm.find('[name=tradingType]').off('change').on('change', function () {
            var key = $(this).find("option:selected").attr('key');
            var paymentMethod = basicHouseTrading == null ? null : basicHouseTrading.paymentMethod;
            if (key == AssessDicKey.examineHouseTransactionTypeSell) {
                houseCommon.houseTradingForm.find('.ExamineHouseTradingSell').show();
                houseCommon.houseTradingForm.find('.ExamineHouseTradingLease').hide();
                houseCommon.loadTradingSellAndLeaseList(AssessDicKey.examineHouseTransactionTypeSell);
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePaymentMethod, basicHouseTrading.paymentMethod, function (html, data) {
                    houseCommon.houseTradingForm.find("select.paymentMethod").empty().html(html).trigger('change');
                });
            } else if (key == AssessDicKey.examineHouseTransactionTypeLease) {
                houseCommon.houseTradingForm.find('.ExamineHouseTradingSell').hide();
                houseCommon.houseTradingForm.find('.ExamineHouseTradingLease').show();
                houseCommon.loadTradingSellAndLeaseList(AssessDicKey.examineHouseTransactionTypeLease);
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePaymentMethodLease, basicHouseTrading.paymentMethod, function (html, data) {
                    houseCommon.houseTradingForm.find("select.paymentMethod").empty().html(html).trigger('change');
                });
            } else {
                houseCommon.houseTradingForm.find('.ExamineHouseTradingSell').hide();
                houseCommon.houseTradingForm.find('.ExamineHouseTradingLease').hide();
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePaymentMethod, basicHouseTrading.paymentMethod, function (html, data) {
                    houseCommon.houseTradingForm.find("select.paymentMethod").empty().html(html).trigger('change');
                });
            }
        });

        houseCommon.houseTradingForm.find('[name=paymentMethod]').change(function () {
            var key = $(this).find("option:selected").attr('key');
            if (key == AssessDicKey.examineHousePaymentMethodInstallment) {
                $(this).closest('.form-group').children().eq(2).show();
            } else {
                $(this).closest('.form-group').children().eq(2).hide();
            }
            if (key == AssessDicKey.examineHousePaymentMethodDisposable || key == AssessDicKey.examineHousePaymentMethodLeaseDisposable) {
                houseCommon.houseTradingForm.find('.tradingCondition').hide();
            } else {
                houseCommon.houseTradingForm.find('.tradingCondition').show();
            }
        });

        //信息来源类型
        houseCommon.houseTradingForm.find("[name=informationType]").change(function () {
            var key = $(this).find("option:selected").attr('key');
            if (key == AssessDicKey.examineHouseInformationSourceTypeOpen) {
                houseCommon.houseTradingForm.find('.infomationTypeOpen').show();
                houseCommon.houseTradingForm.find('.infomationTypeOther').hide();
            } else {
                houseCommon.houseTradingForm.find('.infomationTypeOpen').hide();
                houseCommon.houseTradingForm.find('.infomationTypeOther').show();
            }
        })
    };

    //新增出售或出租
    houseCommon.addTradingSellAndLease = function () {
        var key = houseCommon.houseTradingForm.find('[name=tradingType]').find("option:selected").attr('key');
        var frmSon = 'frmTradingLeaseAndSell';
        var divBoxSon = 'divBoxTradingLeaseAndSell';
        $("#" + frmSon).clearAll().find(".type").val(key);
        if (key == AssessDicKey.examineHouseTransactionTypeSell) {
            $("#" + divBoxSon).find(".lease").hide();
            $("#" + divBoxSon).find(".sell").show();
            $("#" + divBoxSon).find(".modal-title").html("出售信息");
        }
        if (key == AssessDicKey.examineHouseTransactionTypeLease) {
            $("#" + divBoxSon).find(".lease").show();
            $("#" + divBoxSon).find(".sell").hide();
            $("#" + divBoxSon).find(".modal-title").html("出租信息");
        }
        $("#" + divBoxSon).modal("show");
    };

    //加载出售获取出租
    houseCommon.loadTradingSellAndLeaseList = function (tradingType, readonly) {
        var cols = [];
        var tbListId = '';
        if (tradingType == AssessDicKey.examineHouseTransactionTypeSell) {
            tbListId = 'tableTradingSell';
            cols.push({field: 'instalmentInterest', title: '分期支付时间起'});
            cols.push({field: 'instalmentPeriodStartName', title: '分期支付时间止'});
            cols.push({field: 'instalmentPeriodEndName', title: '分期支付利息'});
        }
        if (tradingType == AssessDicKey.examineHouseTransactionTypeLease) {
            tbListId = 'tableTradingLease';
            cols.push({field: 'rentGrowthRate', title: '租金增长比率'});
            cols.push({field: 'rentPaymentTimeStartName', title: '租金支付时间起'});
            cols.push({field: 'rentPaymentTimeEndName', title: '租金支付时间止'});
        }
        if (!readonly) {
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += "<a class='btn btn-xs btn-warning tooltips' data-placement='top' data-original-title='删除' " + "onclick=houseCommon.deleteTradingSellAndLease(" + row.id + ",'" + tradingType + "'" + ")" + ">";
                    str += "<i class='fa fa-minus fa-white'>" + "</i>";
                    str += "</a>";
                    str += '</div>';
                    return str;
                }
            });
        }
        $("#" + tbListId).bootstrapTable('destroy');
        TableInit(tbListId, getContextPath() + "/basicHouseTradingLeaseAndSell/getLeaseAndSellVos", cols, {
            type: tradingType,
            houseId: houseCommon.getHouseId()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

    //保存出售或出租
    houseCommon.saveTradingSellAndLease = function () {
        var frmSon = 'frmTradingLeaseAndSell';
        var divBoxSon = 'divBoxTradingLeaseAndSell';
        if (!$("#" + frmSon).valid()) {
            return false;
        }
        var data = formParams(frmSon);
        data.tradingType = data.type;
        data.houseId = houseCommon.getHouseId();
        var url = getContextPath() + "/basicHouseTradingLeaseAndSell/saveAndUpdateHouseTradingLeaseAndSell";
        $.ajax({
            url: url,
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    notifySuccess('成功', '保存成功');
                    $("#" + divBoxSon).modal("hide");
                    houseCommon.loadTradingSellAndLeaseList(data.type);
                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    };

    //删除出售或出租
    houseCommon.deleteTradingSellAndLease = function (id, type) {
        $.ajax({
            url: getContextPath() + "/basicHouseTradingLeaseAndSell/removeLeaseAndSell",
            type: "post",
            dataType: "json",
            data: {
                id: id,
                type: type
            },
            success: function (result) {
                if (result.ret) {
                    notifySuccess('成功', '删除成功');
                    houseCommon.loadTradingSellAndLeaseList(type);
                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    };

    /**
     * 获取所需的户型图
     * @param callback
     */
    houseCommon.getMarkersysAttachmentId = function (callback) {
        var sysAttachmentId;
        $.ajax({
            url: getContextPath() + "/public/getSysAttachmentDtoList",
            type: "get",
            dataType: "json",
            async: false,
            data: {
                tableId: houseCommon.getHouseId(),
                tableName: AssessDBKey.BasicHouse
            },
            success: function (result) {
                if (result.ret && result.data) {
                    for (var i = 0; i < result.data.length; i++) {
                        if (result.data[i].fieldsName == houseCommon.houseFileControlIdArray[1]) {
                            //后缀必须为图片
                            if (AssessCommon.checkImgFile(result.data[i].fileName)) {
                                sysAttachmentId = result.data[i].id;
                                break;
                            }
                        }
                    }
                    if (!sysAttachmentId) {
                        //假如未找到需要的情况下新户型那么找另一种
                        for (var i = 0; i < result.data.length; i++) {
                            if (result.data[i].fieldsName == houseCommon.houseFileControlIdArray[0]) {
                                //后缀必须为图片
                                if (AssessCommon.checkImgFile(result.data[i].fileName)) {
                                    sysAttachmentId = result.data[i].id;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        });
        return sysAttachmentId;
    };


    houseCommon.orientationFun = function (readonly) {
        var select = {tableId: houseCommon.getUnitId(), type: "unit"};
        examineCommon.getApplyBatchEstateTaggingsByTableId(select, function (unitMarkerArray) {
            if (unitMarkerArray.length == 0) {
                notifyInfo("请先标注单元");
                return false;
            }
            var unitMarker = unitMarkerArray[0];
            if (!unitMarker) {
                notifyInfo('提示', "还未标注单元位置信息");
                return;
            }
            var attachmentId = houseCommon.getMarkersysAttachmentId();
            if (!attachmentId) {
                notifyInfo('提示', "未找到相关户型图片");
                return;
            }
            examineCommon.getApplyBatchEstateTaggingsByTableId({
                tableId: houseCommon.getHouseId(), type: "house"
            }, function (result) {
                var data = {
                    attachmentId: attachmentId,
                    lng: unitMarker.lng,
                    lat: unitMarker.lat,
                    deg: 0,
                    readonly: readonly
                };
                if (result.length > 0) {
                    if (result[0].deg) {
                        data.deg = result[0].deg;
                    }
                    if (result[0].attachmentId) {
                        data.attachmentId = result[0].attachmentId;
                    }
                }
                var contentUrl = getContextPath() + '/map/houseTagging?' + examineCommon.parseParam(data);
                layer.open({
                    type: 2,
                    title: '房屋标注',
                    shade: true,
                    maxmin: true, //开启最大化最小化按钮
                    area: [examineCommon.getMarkerAreaInWidth, examineCommon.getMarkerAreaInHeight],
                    content: contentUrl,
                    success: function (layero) {
                        houseCommon.houseMapiframe = window[layero.find('iframe')[0]['name']];
                    },
                    cancel: function () {
                        if (!readonly) {
                            //到iframe中获取数据
                            var resultData = {
                                tableId: houseCommon.getHouseId(),
                                type: "house",
                                lng: houseCommon.houseMapiframe.config.position.lng,
                                lat: houseCommon.houseMapiframe.config.position.lat,
                                deg: houseCommon.houseMapiframe.config.deg,
                                attachmentId: houseCommon.houseMapiframe.config.attachmentId,
                                name: houseCommon.houseForm.find('[name=houseNumber]').val()
                            };
                            examineCommon.addBasicEstateTagging(resultData, function () {
                                notifyInfo('提示', "标注成功信息!");
                            });
                        }
                    }
                });
            });
        });
    };

    /**
     * 户型地图朝向
     */
    houseCommon.orientationFun2 = function (readonly) {
        toolMapHandleFun.getToolMapHandleListByExample({
            tableId: houseCommon.getUnitId(),
            type: "unit"
        }, function (unitMarker) {
            if (!unitMarker) {
                notifyInfo('提示', "还未标注单元位置信息");
                return;
            }
            toolMapHandleFun.getToolMapHandleListByExample({
                tableId: houseCommon.getHouseId(),
                type: "house"
            }, function (result) {
                var attachmentId = houseCommon.getMarkersysAttachmentId();
                if (!attachmentId) {
                    notifyInfo('提示', "未找到相关户型图片");
                    return;
                }
                var data = {
                    attachmentId: attachmentId,
                    lng: unitMarker[0].lng,
                    lat: unitMarker[0].lat,
                    deg: 0,
                    readonly: readonly
                };
                if (result.length != 0) {
                    data.deg = result[0].deg;
                    data.attachmentId = result[0].attachmentId;
                }
                var contentUrl = getContextPath() + '/map/houseTagging?' + examineCommon.parseParam(data);
                layer.open({
                    type: 2,
                    title: '房屋标注',
                    shade: true,
                    maxmin: true, //开启最大化最小化按钮
                    area: [examineCommon.getMarkerAreaInWidth, examineCommon.getMarkerAreaInHeight],
                    content: contentUrl,
                    success: function (layero) {
                        houseCommon.houseMapiframe = window[layero.find('iframe')[0]['name']];
                    },
                    cancel: function () {
                        if (!readonly) {
                            //到iframe中获取数据
                            var saveData = {
                                tableId: houseCommon.getHouseId(),
                                type: "house",
                                lng: houseCommon.houseMapiframe.config.position.lng,
                                lat: houseCommon.houseMapiframe.config.position.lat,
                                deg: houseCommon.houseMapiframe.config.deg,
                                attachmentId: houseCommon.houseMapiframe.config.attachmentId
                            };
                            toolMapHandleFun.removeToolMapHandle({
                                type: saveData.type,
                                tableId: saveData.tableId
                            }, function () {
                                toolMapHandleFun.saveData(saveData, function () {
                                    notifyInfo('提示', "成功！");
                                });
                            });
                        }
                    }
                });
            });
        });
    };

    //计算单价
    houseCommon.computeUnitPrice = function (_this) {
        var area = houseCommon.houseForm.find('[name=area]').val();
        if (!area) {
            area = houseCommon.houseTradingForm.find('[id=tempLandArea]').val();
        }
        var tradingTotalPrice = $(_this).closest('form').find('[name=tradingTotalPrice]').val();
        if (AssessCommon.isNumber(area) && AssessCommon.isNumber(tradingTotalPrice)) {
            var tradingUnitPrice = parseFloat(tradingTotalPrice) / parseFloat(area);
            $(_this).closest('form').find('[name=tradingUnitPrice]').val(parseInt(tradingUnitPrice));
            houseCommon.computePerMuPrice(_this);
        }
    };

    //计算每亩单价
    houseCommon.computePerMuPrice = function (_this) {
        var unitPrice = $(_this).closest('form').find('[name=tradingUnitPrice]').val();
        if (AssessCommon.isNumber(unitPrice)) {
            var value = (parseFloat(unitPrice) * AssessCommon.BHOU).toFixed(2);
            $(_this).closest('form').find('[name=perMuPrice]').val(value);
        }
    };

    //户型专有字段初始化
    houseCommon.huxingSpecialPartInit = function () {
        var tenementType = houseCommon.houseHuxingForm.find("input[name='tenementType']").val();
        if (!tenementType) {
            notifyInfo('提示', "请先填写物业类型！");
            return;
        }
        var html = ' <option value="">-请选择-</option>';
        AssessCommon.ajaxServerMethod({
            tenementType: tenementType,
            num: 3
        }, "/basicTenementType/getTenementTypeValue", "get", function (value) {
            html += value;
            $('#huxingSpecialPart').empty().append(html);
        });
    };

    houseCommon.huxingSpecialPartChange = function (_this, isCategory) {
        var $form = $(_this).closest('form');
        var $option = $(_this).find('option:selected');
        var name = AssessCommon.toString($(_this).val());
        var descName = AssessCommon.toString($option.attr('data-desc'));
        var dataChild = $option.attr('data-child');
        if (dataChild) {
            $("#huxingSpecialPartCategoryDiv").show();
            var array = dataChild.split(',');
            var html = ' <option value="">-请选择-</option>';
            $.each(array, function (i, item) {
                html += '<option value="' + item + '">' + item + '</option>';
            })
            $("#huxingSpecialPartCategory").empty().append(html);
        } else {
            if (!isCategory) {
                $("#huxingSpecialPartCategoryDiv").hide();
            }
            houseCommon.appendHuxingItemHtml($form, name, null, descName);
        }
    };

    houseCommon.huxingCommonPartChange = function (_this) {
        var form = $(_this).closest('form');
        var name = AssessCommon.toString($(_this).val());
        var descName = AssessCommon.toString($(_this).find('option:selected').attr('data-desc'));
        var cbxs = form.find('.form-check').find(':checkbox:checked');
        if (cbxs.length > 0) {
            $.each(cbxs, function (i, item) {
                var prefix = $(item).closest('.form-check-label').find('.form-check-sign').text();
                houseCommon.appendHuxingItemHtml(form, prefix + name, null, descName);
            })
        } else {
            houseCommon.appendHuxingItemHtml(form, name, null, descName);
        }
    }

    houseCommon.appendHuxingItemHtml = function ($form, name, number, descName) {
        var html = '<div class="col-md-6 item">' +
            ' <div class="form-inline">' +
            '    <label class="col-sm-4" data-desc="{descName}">{name}</label>' +
            '    <div class="col-sm-6">' +
            '       <input type="text" placeholder="个数" name="number" value="{number}"  data-rule-number="true" class="form-control input-full">' +
            '    </div>' +
            '    <label class="col-sm-2">' +
            '        <input class="btn btn-sm btn-warning" type="button" value="X" onclick="houseCommon.removeHuxingItemHtml(this);">' +
            '    </label>' +
            ' </div>' +
            ' </div>';
        var itemLength = $form.find('.item').length;
        if (itemLength % 2 == 0) {
            $form.append('<div class="row form-group"></div>');
        }
        var name = AssessCommon.toString(name);
        var number = AssessCommon.toString(number);
        var descName = AssessCommon.toString(descName);
        html = html.replace(/{name}/, name).replace(/{number}/, number).replace(/{descName}/, descName);
        $form.find('.form-group:last').append(html);
    }

    houseCommon.removeHuxingItemHtml = function (_this) {
        var group = $(_this).closest('.form-group');
        if (group.find('.item').length <= 1) {
            group.remove();
        } else {
            $(_this).closest('.item').remove();
        }
    }

    //拼接户型名称
    houseCommon.spliceHouseHuxing = function (_this) {
        var items = $(_this).closest('.modal-content').find('form').find('.item');
        var jsonArray = [];
        var huxingName = '';
        $.each(items, function (i, item) {
            var value = $(item).find('[name=number]').val();
            if (value) {
                var keyValue = {};
                var desc = $.trim($(item).find('label').attr('data-desc'));
                var text = $.trim($(item).find('label').text());
                keyValue.key = text;
                keyValue.explain = text;
                if (desc) {
                    keyValue.explain = desc;
                }
                keyValue.value = value;
                huxingName += value + keyValue.explain;
                jsonArray.push(keyValue);
            }
        })
        houseCommon.houseHuxingForm.find("input[name='name']").val(huxingName.replace(/,$/, ''));
        houseCommon.houseHuxingForm.find("input[name='huxingData']").val(JSON.stringify(jsonArray));
        $("#divBoxHouseHuxing").modal("hide");
    };

    //回显户型名称
    houseCommon.displayHouseHuxing = function (_this) {
        var $form = $(_this).closest('form');
        var huxingData = $form.find('[name=huxingData]').val();
        if (huxingData) {
            var array = JSON.parse(huxingData);
            $('#frmHouseHuxing').find('.item').closest('.form-group').remove();
            $.each(array, function (i, item) {
                houseCommon.appendHuxingItemHtml($('#frmHouseHuxing'), item.key, item.value, item.explain);
            })
        }
        houseCommon.huxingSpecialPartInit();
        $("#divBoxHouseHuxing").modal();
    }

    //户型信息管理--------------------------------------------------------------------------------------------
    //显示户型列表弹窗
    houseCommon.huxingFileControlArray = [];
    //户型附件上传
    houseCommon.huxingFileUpload = function (huxingId) {
        if (houseCommon.huxingFileControlArray.length <= 0) {
            return;
        }
        $.each(houseCommon.huxingFileControlArray, function (i, item) {
            FileUtils.uploadFiles({
                target: item,
                disabledTarget: "btn_submit",
                formData: {
                    fieldsName: item,
                    tableName: AssessDBKey.BasicUnitHuxing,
                    tableId: huxingId == undefined ? 0 : huxingId
                },
                deleteFlag: true,
                onUploadComplete: function () {
                    houseCommon.huxingFileShow(huxingId);
                }
            });
        })
    }
    //户型附件显示
    houseCommon.huxingFileShow = function (huxingId) {
        if (houseCommon.huxingFileControlArray.length <= 0) {
            return;
        }
        $.each(houseCommon.huxingFileControlArray, function (i, item) {
            FileUtils.getFileShows({
                target: item,
                formData: {
                    fieldsName: item,
                    tableName: AssessDBKey.BasicUnitHuxing,
                    tableId: huxingId == undefined ? 0 : huxingId
                },
                deleteFlag: true
            })
        })
    }

    houseCommon.showHuxingListModal = function () {
        houseCommon.loadHuxingList();
        $('#divBoxHuxingListModal').modal();
    }

    //加载户型列表
    houseCommon.loadHuxingList = function () {
        var cols = [];
        cols.push({
            field: 'tenementType', title: '物业类型', width: '10%', formatter: function (value, row, index) {
                if (row.creatorName) {
                    return value + '<span style="padding: 5px;" class="label label-info">' + row.creatorName + '</span>';
                } else {
                    return value;
                }
            }
        });
        cols.push({field: 'name', title: '户型名称', width: '20%'});
        cols.push({field: 'area', title: '面积', width: '10%'});
        cols.push({field: 'orientationName', title: '朝向', width: '10%'});
        cols.push({field: 'spatialDistributionName', title: '空间布局', width: '10%'});
        cols.push({field: 'quantity', title: '户数', width: '10%'});
        cols.push({field: 'fileViewName', title: '附件', width: '10%'});
        cols.push({
            field: 'id', width: '10%', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="选择" onclick="houseCommon.referenceHuxing(' + row.id + ')"><i class="fa fa-check"></i></button>';
                if (row.canManage) {
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="houseCommon.showHuxingManageModal(' + row.id + ')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="删除" onclick="houseCommon.deleteHuxing(' + row.id + ')"><i class="fa fa-minus"></i></button>';
                }
                str += '</div>';
                return str;
            }
        });
        $("#tbHuxingList").bootstrapTable('destroy');
        TableInit("tbHuxingList", getContextPath() + "/basicUnitHuxing/getBootstrapTableVo", cols, {
            applyBatchId: $('#divBoxHuxingListModal').find('[name=applyBatchId]').val(),
            name: $('#divBoxHuxingListModal').find('[name=name]').val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function (data) {
                $('.tooltips').tooltip();
            }
        });
    }

    //显示户型管理弹窗
    houseCommon.showHuxingManageModal = function (huxingId) {
        houseCommon.houseHuxingForm.clearAll();//清空表单
        houseCommon.getFilePartHtml(AssessDicKey.examineHouseHuxingFilePart, houseCommon.huxingFileControlArray, true);
        if (huxingId) {
            $.getJSON(getContextPath() + "/basicUnitHuxing/getBasicUnitHuxingById", {huxingId: huxingId}, function (result) {
                if (result.ret && result.data) {
                    houseCommon.houseHuxingForm.initForm(result.data);//初始化表单
                    AssessCommon.loadTextAppendDicHtml(AssessDicKey.examineHouseTenementType, null, function (html, data) {
                        houseCommon.houseHuxingForm.find("#tenementTypeList").empty().html(html).trigger('change');
                    }, true);
                    AssessCommon.loadDataDicByKey(AssessDicKey.examineCommonOrientation, result.data.orientation, function (html, data) {
                        houseCommon.houseHuxingForm.find("select.orientation").empty().html(html).trigger('change');
                    });
                    AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseSpatialDistribution, result.data.spatialDistribution, function (html, data) {
                        houseCommon.houseHuxingForm.find("select.spatialDistribution").empty().html(html).trigger('change');
                    });
                    AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseUtilitiesMeasure, result.data.utilitiesMeasure, function (html, data) {
                        houseCommon.houseHuxingForm.find("select.utilitiesMeasure").empty().html(html).trigger('change');
                    });
                    AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseUtilitiesType, result.data.utilitiesType, function (html, data) {
                        houseCommon.houseHuxingForm.find("select.utilitiesType").empty().html(html).trigger('change');
                    });
                    houseCommon.showUtilitiesType(result.data);
                    houseCommon.tenementTypeChange(result.data);
                }
            })
        } else {
            AssessCommon.loadTextAppendDicHtml(AssessDicKey.examineHouseTenementType, null, function (html, data) {
                houseCommon.houseHuxingForm.find("#tenementTypeList").empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadDataDicByKey(AssessDicKey.examineCommonOrientation, null, function (html, data) {
                houseCommon.houseHuxingForm.find("select.orientation").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseSpatialDistribution, null, function (html, data) {
                houseCommon.houseHuxingForm.find("select.spatialDistribution").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseUtilitiesMeasure, null, function (html, data) {
                houseCommon.houseHuxingForm.find("select.utilitiesMeasure").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseUtilitiesType, null, function (html, data) {
                houseCommon.houseHuxingForm.find("select.utilitiesType").empty().html(html).trigger('change');
            });
            houseCommon.showUtilitiesType({});
            houseCommon.tenementTypeChange({});
        }
        houseCommon.huxingFileUpload(huxingId);
        houseCommon.huxingFileShow(huxingId);
        $('#divBoxHuxingManageModal').modal();
    }

    //保存户型信息
    houseCommon.saveHuxing = function (_this) {
        var data = formSerializeArray($(_this).closest('.modal-content').find('form'));
        data.applyBatchId = $('#divBoxHuxingListModal').find('[name=applyBatchId]').val();
        data.planDetailsId = $('#divBoxHuxingListModal').find('[name=planDetailsId]').val();
        $.post(getContextPath() + "/basicUnitHuxing/saveBasicUnitHuxing", {formData: JSON.stringify(data)}, function (result) {
            if (result.ret) {
                notifySuccess('成功', '保存成功');
                houseCommon.loadHuxingList();
                $('#divBoxHuxingManageModal').modal('hide');
            } else {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }

    //删除户型信息
    houseCommon.deleteHuxing = function (huxingId) {
        AlertConfirm("确认要删除么", "删除后数据将不可恢复", function () {
            $.post(getContextPath() + "/basicUnitHuxing/deleteBasicUnitHuxing", {huxingId: huxingId}, function (result) {
                if (result.ret) {
                    notifySuccess('成功', '删除成功');
                    $('#divBoxHuxingManageModal').modal('hide');
                    houseCommon.loadHuxingList();
                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        })
    }

    //引用户型信息
    houseCommon.referenceHuxing = function (huxingId) {
        $.post(getContextPath() + "/basicUnitHuxing/referenceHuxing", {
            huxingId: huxingId,
            houseId: houseCommon.getHouseId()
        }, function (result) {
            if (result.ret) {
                notifySuccess('成功', '引用成功');
                $('#divBoxHuxingListModal').modal('hide');
                houseCommon.showCurrHuxing(huxingId);
            } else {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }

    //显示出当前户型信息
    houseCommon.showCurrHuxing = function (huxingId) {
        $.getJSON(getContextPath() + "/basicUnitHuxing/getBasicUnitHuxingById", {huxingId: huxingId}, function (result) {
            if (result.ret && result.data) {
                houseCommon.houseForm.initForm(result.data);//初始化表单
            }
        })
    }

    houseCommon.appendRestrictionsHTML = function (that) {
        var html = houseCommon.getRestrictionsHTML({});
        var group = $(that).closest(".form-group");
        group.after(html);
    };

    houseCommon.loadRestrictionsHTML = function (data, target) {
        var restrictions = [];
        var restrictionsRemark = [];
        if (data.restrictions){
            restrictions = (data.restrictions+"").split(",") ;
        }
        if (data.restrictionsRemark){
            restrictionsRemark = (data.restrictionsRemark+"").split(",") ;
        }
        var ele = target.find("[name="+'restrictions'+"]") ;
        if (ele.size() == 0){
           return false ;
        }
        var url = ""+window.location.href +""; /* 获取完整URL */
        var group = ele.closest(".form-group") ;
        for (var i = 0; i < restrictions.length ; i++) {
            var obj = {restrictions:restrictions[i],restrictionsRemark:restrictionsRemark[i]} ;
            if (i == 0){
                if (url.indexOf('Edit') != -1) {
                    target.find("[name="+'restrictions'+"]").val(obj.restrictions);
                    target.find("[name="+'restrictionsRemark'+"]").val(obj.restrictionsRemark);
                }else {
                    target.find("[name="+'restrictions'+"]").html(obj.restrictions);
                    target.find("[name="+'restrictionsRemark'+"]").html(obj.restrictionsRemark);
                }
            }else {
                group.after(houseCommon.getRestrictionsHTML(obj)) ;
            }
        }
    };


    houseCommon.getRestrictionsHTML = function (data) {
        var fields = ['restrictions', 'restrictionsRemark'];
        var parentHtml = "";
        parentHtml += "<div class='row form-group'>";
        parentHtml += "<div class='col-md-12'>";
        parentHtml += "<div class='form-inline x-valid'>";
        var url = ""+window.location.href +""; /* 获取完整URL */
        var edit = url.indexOf('Edit') != -1 ;
        $.each(fields, function (k, field) {
            var html = "";
            var name = "";
            var value = '' ;
            if (data[field]) {
                value = data[field] ;
            }
            html += "<label class='col-sm-1'>{name}</label>";
            if (field == 'restrictions') {
                name = "限制事项";
            }
            if (field == 'restrictionsRemark') {
                name = "限制说明";
            }
            if (edit) {
                html += "<div class='col-sm-3'><input type='text' placeholder='{name}' name='{field}' value='{value}' class='form-control input-full'></div>";
            } else {
                html += "<div class='col-sm-3'><label class='form-control input-full'>{value}</label></div>";
            }
            html = html.replace(/{name}/g, name);
            html = html.replace(/{value}/g, value);
            html = html.replace(/{field}/g, field);
            parentHtml += html;
        });
        if (edit) {
            parentHtml += "<div class='col-sm-3'><span class='input-group-btn'> <input type='button' class='btn btn-warning btn-sm' value='X' onclick='houseCommon.appendRestrictionsClear(this);'> </span></div>";
        }
        parentHtml += "</div>";
        parentHtml += "</div>";
        parentHtml += "</div>";
        return parentHtml;
    };
    houseCommon.appendRestrictionsClear = function (that) {
        var group = $(that).closest(".form-group");
        group.remove();
    };
    //户型信息管理--------------------------------------------------------------------------------------------

    window.houseCommon = houseCommon;
})(jQuery);
