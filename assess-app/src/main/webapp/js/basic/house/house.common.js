/**
 * Created by asus on 2018/11/24.
 */

;(function ($) {
    var houseCommon = {};
    houseCommon.houseTradingForm = $('#basicTradingFrm');
    houseCommon.houseForm = $('#basicHouseFrm');
    houseCommon.basicApplyForm = $('#basicApplyFrm');
    //附件上传控件id数组
    houseCommon.houseFileControlIdArray = [
        'house_new_huxing_plan',
        'house_img_plan'];

    houseCommon.getHouseId = function () {
        return houseCommon.houseForm.find('[name=id]').val();
    }
    houseCommon.getApplyId = function () {
        return houseCommon.houseForm.find('[name=applyId]').val();
    }

    //添加楼盘
    houseCommon.add = function ($form, callback) {
        $.ajax({
            url: getContextPath() + '/basicHouse/addHouseAndTrading',
            data: {
                houseNumber: $form.find('[name=houseNumber]').val()
            },
            success: function (result) {
                if (result.ret) {
                    houseCommon.showHouseView(result.data);
                    if (callback) {
                        callback();
                    }
                }
            }
        })
    }

    //编辑楼盘
    houseCommon.edit = function ($form, callback) {
        $.ajax({
            url: getContextPath() + '/basicHouse/appWriteHouse',
            data: {caseHouseId: $form.find("input[name='caseHouseId']").val()},
            success: function (result) {
                if (result.ret) {
                    houseCommon.showHouseView(result.data);
                    if (callback) {
                        callback();
                    }
                }
            }
        })
    }

    //楼盘初始化by applyId
    houseCommon.init = function (applyId, callback) {
        $.ajax({
            url: getContextPath() + '/basicHouse/getBasicHouseByApplyId',
            type: 'get',
            data: {applyId: applyId},
            success: function (result) {
                if (result.ret) {
                    houseCommon.showHouseView(result.data);
                    if (callback) {
                        callback();
                    }
                }
            }
        })
    }

    //楼盘明细
    houseCommon.detail = function (applyId) {
        $.ajax({
            url: getContextPath() + '/basicHouse/getBasicHouseByApplyId',
            type: 'get',
            data: {applyId: applyId},
            success: function (result) {
                if (result.ret) {
                    houseCommon.houseForm.initLabel(result.data.basicHouse);
                    houseCommon.houseTradingForm.initLabel(result.data.basicHouseTrading);
                }
            }
        })
    }

    //显示楼盘对应部分信息
    houseCommon.showHouseView = function (data) {
        houseCommon.houseForm.initForm(data.basicHouse, function () {
            //1.初始化下拉框；2.初始化上传控件；3.显示已上传的附件信息；
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseLoadUtility, data.basicHouse.certUse, function (html, data) {
                houseCommon.houseForm.find("select.certUse").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePracticalUse, data.basicHouse.practicalUse, function (html, data) {
                houseCommon.houseForm.find("select.practicalUse").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseEnvironmentUse, data.basicHouse.useEnvironment, function (html, data) {
                houseCommon.houseForm.find("select.useEnvironment").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseNewsHuxing, data.basicHouse.newsHuxing, function (html, data) {
                houseCommon.houseForm.find("select.newsHuxing").empty().html(html).trigger('change');
            });

            //初始化上传控件
            $.each(houseCommon.houseFileControlIdArray, function (i, item) {
                houseCommon.fileUpload(item);
            })

            //附件显示
            $.each(houseCommon.houseFileControlIdArray, function (i, item) {
                houseCommon.fileShow(item);
            })

            if (data.basicHouse.huxingPlan) {
                //显示户型图
                var huxingPlanJson = JSON.parse(data.basicHouse.huxingPlan);
                FileUtils.getFileShows({
                    target: "house_huxing_plan",
                    formData: {
                        tableName: huxingPlanJson.tableName,
                        tableId: huxingPlanJson.id
                    }
                })
            }
        });

        //交易情况
        houseCommon.houseTradingForm.initForm(data.basicHouseTrading, function () {
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHousetaxBurden, data.basicHouseTrading.taxBurden, function (html, data) {
                houseCommon.houseTradingForm.find("select.taxBurden").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.basicHouseTransactionType, data.basicHouseTrading.tradingType, function (html, data) {
                houseCommon.houseTradingForm.find("select.tradingType").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseDescriptionType, data.basicHouseTrading.descriptionType, function (html, data) {
                houseCommon.houseTradingForm.find("select.descriptionType").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseNormalTransaction, data.basicHouseTrading.normalTransaction, function (html, data) {
                houseCommon.houseTradingForm.find("select.normalTransaction").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePaymentMethod, data.basicHouseTrading.paymentMethod, function (html, data) {
                houseCommon.houseTradingForm.find("select.paymentMethod").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseClassificationInformationSources, data.basicHouseTrading.informationType, function (html, data) {
                houseCommon.houseTradingForm.find("select.informationType").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseFinancingConditions, data.basicHouseTrading.financingConditions, function (html, data) {
                houseCommon.houseTradingForm.find("select.financingConditions").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseScopeProperty, data.basicHouseTrading.scopeProperty, function (html, data) {
                houseCommon.houseTradingForm.find("select.scopeProperty").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_information_sources, data.basicHouseTrading.information, function (html, data) {
                houseCommon.houseTradingForm.find("select.information").empty().html(html).trigger('change');
            });
        })
    }


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
            deleteFlag: true
        });
    }

    //附件显示
    houseCommon.fileShow = function (fieldsName, deleteFlag) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.BasicHouse,
                tableId: houseCommon.getHouseId()
            },
            deleteFlag: deleteFlag == undefined ? true : deleteFlag
        })
    }

    //户型选择
    houseCommon.selectHuxing = function (_this) {
        assessHuxing.select({
            basicApplyId: houseCommon.getApplyId(),
            caseUnitId: houseCommon.basicApplyForm.find('[name=caseUnitId]').val(),
            success: function (row) {
                //1.赋值 2.显示附件数据
                $(_this).closest('.input-group').find(':text').val(row.name);
                houseCommon.houseForm.find('[name=orientation]').val(row.orientationName);
                houseCommon.houseForm.find('[name=huxingPlan]').val(JSON.stringify({
                    id: row.id,
                    tableName: row.tableName
                }));
                FileUtils.getFileShows({
                    target: "house_huxing_plan",
                    formData: {
                        tableName: row.tableName,
                        tableId: row.id
                    },
                    deleteFlag: false
                })
            }
        })
    }

    window.houseCommon = houseCommon;
})(jQuery);