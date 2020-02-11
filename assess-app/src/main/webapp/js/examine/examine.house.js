/**
 * Created by asus on 2018/11/24.
 */

;(function ($) {
    var houseCommon = {};
    houseCommon.houseTradingForm = $('#basicTradingFrm');
    houseCommon.houseForm = $('#basicHouseFrm');
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
    houseCommon.houseFileControlIdArray = [
        AssessUploadKey.HOUSE_HUXING_PLAN,
        AssessUploadKey.HOUSE_NEW_HUXING_PLAN,
        AssessUploadKey.HOUSE_IMG_PLAN,
        AssessUploadKey.HOUSE_DECORATE,
        AssessUploadKey.HOUSE_FILE,
        AssessUploadKey.HOUSE_TRADING_FILE
    ];

    houseCommon.getHouseId = function () {
        return houseCommon.houseForm.find('[name=id]').val() != null ? houseCommon.houseForm.find('[name=id]').val() : houseCommon.tableId;
    };

    houseCommon.getUnitId = function (callback) {
        examineCommon.getBasicApplyBatchDetailList({
            tableId: houseCommon.getHouseId(),
            applyBatchId: houseCommon.houseForm.find("input[name='applyBatchId']").val()
        }, function (itemA) {
            if (itemA.length == 0) {
                return false;
            }
            examineCommon.getBasicApplyBatchDetailList({id: itemA[0].pid}, function (itemB) {
                if (itemB.length == 0) {
                    return false;
                }
                if (callback) {
                    callback(itemB[0]);
                }
            });
        });
    };

    //户型选择
    houseCommon.selectHuxing = function (_this) {
        houseCommon.getUnitId(function (data) {
            assessHuxing.selectByBasicUnitId({
                basicUnitId: data.tableId,
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
        });
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


    //添加房屋
    houseCommon.add = function (_this, callback) {
        var houseNumber = $(_this).closest('form').find('[name=houseNumber]').val();
        if (!houseNumber) {
            toastr.info('请填写房屋编号！');
            return false;
        }
        $.ajax({
            url: getContextPath() + '/basicHouse/addHouseAndTrading',
            data: {
                houseNumber: houseNumber
            },
            success: function (result) {
                if (result.ret) {
                    houseCommon.showHouseView(result.data);
                    if (callback) {
                        callback($(_this).attr('data-mode'));
                    }
                }
            }
        })
    };

    //升级房屋
    houseCommon.upgrade = function (_this, callback) {
        var caseHouseId = $(_this).closest('form').find("input[name='caseHouseId']").val();
        var housePartInMode = $(_this).attr('data-mode');
        if (!caseHouseId) {
            toastr.info('请选择系统中已存在的房屋信息！');
            return false;
        }
        $.ajax({
            url: getContextPath() + '/basicHouse/appWriteHouse',
            data: {
                caseHouseId: caseHouseId,
                housePartInMode: housePartInMode
            },
            success: function (result) {
                if (result.ret) {
                    houseCommon.showHouseView(result.data);
                    if (callback) {
                        callback($(_this).attr('data-mode'));
                    }
                }
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
        if (!data || !data.basicHouse) return;
        //基本信息
        houseCommon.houseForm.initForm(data.basicHouse, function () {
            //1.初始化下拉框；2.初始化上传控件；3.显示已上传的附件信息；
            AssessCommon.loadDataListHtml(AssessDicKey.examineHouseLoadUtility, data.basicHouse.certUse, function (html, data) {
                houseCommon.houseForm.find("#certUseList").empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadDataListHtml(AssessDicKey.examineHousePracticalUse, data.basicHouse.practicalUse, function (html, data) {
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
            }
        });

        //完损度数据加载
        try {
            damagedDegree.loadDamagedDegreeList();
        } catch (e) {
        }
    };

    houseCommon.showUseCondition = function (data) {
        if (houseCommon.isNotBlank(data.basicHouse.useCondition)) {
            var strArr = ["出租", "自用"];//来自于实体描述1(1).docx中的规则
            var useConditionId = data.basicHouse.useCondition;
            if (useConditionId) {
                AssessCommon.getDataDicInfo(useConditionId, function (useConditionData) {
                    var str = strArr.join(",");
                    //当属于数组中的任意一项时显示
                    if (str.indexOf(useConditionData.name) != -1) {
                        $("#useConditionDescription").parent().parent().hide();
                    } else {
                        $("#useConditionDescription").parent().parent().show();
                    }
                });
            }
        } else {
            $("#useConditionDescription").parent().parent().hide();
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
                        $("#useConditionDescription").parent().parent().hide();
                    } else {
                        $("#useConditionDescription").parent().parent().show();
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
                        $("#priceConnotationUnit").parent().parent().hide();
                    } else {
                        $("#priceConnotationUnit").parent().parent().show();
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
                        $("#priceConnotationUnit").parent().parent().hide();
                    } else {
                        $("#priceConnotationUnit").parent().parent().show();
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
                if (houseCommon.houseFileControlIdArray[1] == fieldsName) {
                    toolMapHandleFun.removeToolMapHandle({
                        type: "house",
                        tableId: houseCommon.getHouseId()
                    }, function () {

                    });
                }
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
            deleteSuccess: function (attachemntId) {
                if (houseCommon.houseFileControlIdArray[1] == fieldsName) {
                    toolMapHandleFun.removeToolMapHandle({
                        type: "house",
                        tableId: houseCommon.getHouseId()
                    }, function () {

                    });
                }
            }
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
                    toastr.success('保存成功');
                    $("#" + divBoxSon).modal("hide");
                    houseCommon.loadTradingSellAndLeaseList(data.type);
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
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
                    toastr.success('删除成功');
                    houseCommon.loadTradingSellAndLeaseList(type);
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
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
        houseCommon.getUnitId(function (unitInfo) {
            var select = {tableId: unitInfo.tableId, type: "unit"};
            examineCommon.getApplyBatchEstateTaggingsByTableId(select, function (unitMarkerArray) {
                if (unitMarkerArray.length == 0) {
                    Alert("请先标注单元");
                    return false;
                }
                var unitMarker = unitMarkerArray[0];
                if (!unitMarker) {
                    toastr.info("还未标注单元位置信息");
                    return;
                }
                var attachmentId = houseCommon.getMarkersysAttachmentId();
                if (!attachmentId) {
                    toastr.info("未找到相关户型图片");
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
                                    toastr.info("标注成功信息!");
                                });
                            }
                        }
                    });
                });
            });
        });
    };

    /**
     * 户型地图朝向
     */
    houseCommon.orientationFun2 = function (readonly) {
        houseCommon.getUnitId(function (unitInfo) {
            toolMapHandleFun.getToolMapHandleListByExample({
                tableId: unitInfo.tableId,
                type: "unit"
            }, function (unitMarker) {
                if (!unitMarker) {
                    toastr.info("还未标注单元位置信息");
                    return;
                }
                toolMapHandleFun.getToolMapHandleListByExample({
                    tableId: houseCommon.getHouseId(),
                    type: "house"
                }, function (result) {
                    var attachmentId = houseCommon.getMarkersysAttachmentId();
                    if (!attachmentId) {
                        toastr.info("未找到相关户型图片");
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
                                        toastr.info("成功！");
                                    });
                                });
                            }
                        }
                    });
                });
            });
        });
    };

    //计算单价
    houseCommon.computeUnitPrice = function () {
        var area = houseCommon.houseForm.find('[name=area]').val();
        var tradingTotalPrice = houseCommon.houseTradingForm.find('[name=tradingTotalPrice]').val();
        if (AssessCommon.isNumber(area) && AssessCommon.isNumber(tradingTotalPrice)) {
            var tradingUnitPrice = parseFloat(tradingTotalPrice) / parseFloat(area);
            houseCommon.houseTradingForm.find('[name=tradingUnitPrice]').val(parseInt(tradingUnitPrice));
        }
    };

    //打开户型字段modal
    houseCommon.openHuxingModal = function(){
        var huxingData = houseCommon.houseForm.find("input[name='huxingData']").val();
        $("#divBoxHuxing").clearAll();
        if(huxingData){
            $("#frmHuxing").initForm(JSON.parse(huxingData));
        }
        $("#divBoxHuxing").modal("show");
    };

    //拼接户型字段
    houseCommon.spliceHuxing = function(){
        if (!$("#frmHuxing").valid()) {
            return false;
        }
        var text = "";
        if (houseCommon.isNotBlank($("#frmHuxing").find("input[name='house']").val())) {
            text += $("#frmHuxing").find("input[name='house']").val() + "房";
        }
        if (houseCommon.isNotBlank($("#frmHuxing").find("input[name='saloon']").val())) {
            text += $("#frmHuxing").find("input[name='saloon']").val() + "客厅";
        }
        if (houseCommon.isNotBlank($("#frmHuxing").find("input[name='kitchen']").val())) {
            text += $("#frmHuxing").find("input[name='kitchen']").val() + "厨房";
        }
        if (houseCommon.isNotBlank($("#frmHuxing").find("input[name='toilet']").val())) {
            text += $("#frmHuxing").find("input[name='toilet']").val() + "卫生间";
        }
        if (houseCommon.isNotBlank($("#frmHuxing").find("input[name='garden']").val())) {
            text += $("#frmHuxing").find("input[name='garden']").val() + "花园";
        }
        if (houseCommon.isNotBlank($("#frmHuxing").find("input[name='balcony']").val())) {
            text += $("#frmHuxing").find("input[name='balcony']").val() + "阳台";
        }
        houseCommon.houseForm.find("input[name='newHuxingName']").val(text);
        $("#divBoxHuxing").modal('hide');
        var huxingData = JSON.stringify(formParams("frmHuxing"));
        houseCommon.houseForm.find("input[name='huxingData']").val(huxingData);
    };


    window.houseCommon = houseCommon;
})(jQuery);