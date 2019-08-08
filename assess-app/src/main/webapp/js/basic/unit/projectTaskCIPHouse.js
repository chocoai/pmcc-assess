/**
 * Created by asus on 2018/11/24.
 */

;(function ($) {
    var projectTaskCIPHouse = {};
    projectTaskCIPHouse.houseTradingForm = $('#basicTradingFrm');
    projectTaskCIPHouse.houseTradingTypeSell = 'ExamineHouseTradingSell';
    projectTaskCIPHouse.houseTradingTypeLease = 'ExamineHouseTradingLease';
    projectTaskCIPHouse.tableId = undefined;
    /**
     * 判断对象
     */
    projectTaskCIPHouse.isNotBlankObject = function (obj) {
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
    projectTaskCIPHouse.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };
    //附件上传控件id数组
    projectTaskCIPHouse.houseFileControlIdArray = [
        AssessUploadKey.HOUSE_TRADING_FILE
    ];

    projectTaskCIPHouse.getHouseId = function () {
        return projectTaskCIPHouse.tableId;
    }

    //房屋初始化byId
    projectTaskCIPHouse.initById = function (id, callback) {
        $.ajax({
            url: getContextPath() + '/basicHouse/getBasicHouseMapById',
            type: 'get',
            data: {id: id},
            success: function (result) {
                projectTaskCIPHouse.tableId = id;
                projectTaskCIPHouse.showHouseView(result.data);
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                }
            }
        })
    }

    //显示交易情况对应部分信息
    projectTaskCIPHouse.showHouseView = function (data) {
console.log(data)
        //完损度数据加载
        damagedDegree.loadDamagedDegreeList();

        //初始化上传控件
        $.each(projectTaskCIPHouse.houseFileControlIdArray, function (i, item) {
            projectTaskCIPHouse.fileUpload(item);
        });

        //附件显示
        $.each(projectTaskCIPHouse.houseFileControlIdArray, function (i, item) {
            projectTaskCIPHouse.fileShow(item);
        });
        //交易情况
        projectTaskCIPHouse.houseTradingForm.initForm(data.basicHouseTrading, function () {
           // if (!data.basicHouseTrading)return;
            projectTaskCIPHouse.changeEvent(data.basicHouseTrading);
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseTransactionSituation, data.basicHouseTrading.transactionSituation, function (html, data) {
                projectTaskCIPHouse.houseTradingForm.find("select.transactionSituation").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseScopeProperty, data.basicHouseTrading.scopeProperty, function (html, data) {
                projectTaskCIPHouse.houseTradingForm.find("select.scopeProperty").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHousetaxBurden, data.basicHouseTrading.taxBurden, function (html, data) {
                projectTaskCIPHouse.houseTradingForm.find("select.taxBurden").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseDescriptionType, data.basicHouseTrading.descriptionType, function (html, data) {
                projectTaskCIPHouse.houseTradingForm.find("select.descriptionType").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseTransactionType, data.basicHouseTrading.tradingType, function (html, data) {
                projectTaskCIPHouse.houseTradingForm.find("select.tradingType").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseFinancingConditions, data.basicHouseTrading.financingConditions, function (html, data) {
                projectTaskCIPHouse.houseTradingForm.find("select.financingConditions").empty().html(html).trigger('change');
            });

            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseInformationSourceType, data.basicHouseTrading.informationType, function (html, data) {
                projectTaskCIPHouse.houseTradingForm.find("select.informationType").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseInformationSourceCategory, data.basicHouseTrading.informationCategory, function (html, data) {
                projectTaskCIPHouse.houseTradingForm.find("select.informationCategory").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePriceConnotation, data.basicHouseTrading.priceConnotation, function (html, data) {
                projectTaskCIPHouse.houseTradingForm.find("select.priceConnotation").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouse_transaction_price_type, data.basicHouseTrading.priceType, function (html, data) {
                projectTaskCIPHouse.houseTradingForm.find("select[name='priceType']").empty().html(html).trigger('change');
            });
        })
    }

    //附件上传
    projectTaskCIPHouse.fileUpload = function (fieldsName) {
        FileUtils.uploadFiles({
            target: fieldsName,
            disabledTarget: "btn_submit",
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.BasicHouse,
                tableId: projectTaskCIPHouse.getHouseId()
            },
            deleteFlag: true,
            onUploadComplete: function () {
                projectTaskCIPHouse.fileShow(fieldsName);
                if (projectTaskCIPHouse.houseFileControlIdArray[1] == fieldsName) {
                    projectTaskCIPHouse.deleteHouseTagging();//删除新户型图片时清空标注信息
                }
            }
        });
    }

    //附件显示
    projectTaskCIPHouse.fileShow = function (fieldsName, deleteFlag) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.BasicHouse,
                tableId: projectTaskCIPHouse.getHouseId()
            },
            deleteFlag: deleteFlag == undefined ? true : deleteFlag,
            deleteSuccess: function (attachemntId) {
                if (projectTaskCIPHouse.houseFileControlIdArray[1] == fieldsName) {
                    projectTaskCIPHouse.deleteHouseTagging();//删除新户型图片时清空标注信息
                }
            }
        })
    }
    
    //下拉框change事件
    projectTaskCIPHouse.changeEvent = function (basicHouseTrading) {
        projectTaskCIPHouse.houseTradingForm.find("select.transactionSituation").off('change').on('change', function () {
            var key = $(this).find("option:selected").attr('key');
            if (key == AssessDicKey.examineHouseTransactionAbnormal) {
                $("#abnormalTransaction").show();
            } else {
                $("#abnormalTransaction").hide();
            }
        })

        projectTaskCIPHouse.houseTradingForm.find('[name=tradingType]').off('change').on('change', function () {
            var key = $(this).find("option:selected").attr('key');
            var paymentMethod = basicHouseTrading == null ? null : basicHouseTrading.paymentMethod;
            if (key == AssessDicKey.examineHouseTransactionTypeSell) {
                projectTaskCIPHouse.houseTradingForm.find('.ExamineHouseTradingSell').show();
                projectTaskCIPHouse.houseTradingForm.find('.ExamineHouseTradingLease').hide();
                projectTaskCIPHouse.loadTradingSellAndLeaseList(AssessDicKey.examineHouseTransactionTypeSell);
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePaymentMethod, basicHouseTrading.paymentMethod, function (html, data) {
                    projectTaskCIPHouse.houseTradingForm.find("select.paymentMethod").empty().html(html).trigger('change');
                });
            } else if (key == AssessDicKey.examineHouseTransactionTypeLease) {
                projectTaskCIPHouse.houseTradingForm.find('.ExamineHouseTradingSell').hide();
                projectTaskCIPHouse.houseTradingForm.find('.ExamineHouseTradingLease').show();
                projectTaskCIPHouse.loadTradingSellAndLeaseList(AssessDicKey.examineHouseTransactionTypeLease);
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePaymentMethodLease, basicHouseTrading.paymentMethod, function (html, data) {
                    projectTaskCIPHouse.houseTradingForm.find("select.paymentMethod").empty().html(html).trigger('change');
                });
            } else {
                projectTaskCIPHouse.houseTradingForm.find('.ExamineHouseTradingSell').hide();
                projectTaskCIPHouse.houseTradingForm.find('.ExamineHouseTradingLease').hide();
            }
        });

        projectTaskCIPHouse.houseTradingForm.find('[name=paymentMethod]').change(function () {
            var key = $(this).find("option:selected").attr('key');
            if (key == AssessDicKey.examineHousePaymentMethodInstallment) {
                $(this).closest('.form-group').children().eq(2).show();
            } else {
                $(this).closest('.form-group').children().eq(2).hide();
            }
        })

        //信息来源类型
        projectTaskCIPHouse.houseTradingForm.find("[name=informationType]").change(function () {
            var key = $(this).find("option:selected").attr('key');
            if (key == AssessDicKey.examineHouseInformationSourceTypeOpen) {
                projectTaskCIPHouse.houseTradingForm.find('.infomationTypeOpen').show();
                projectTaskCIPHouse.houseTradingForm.find('.infomationTypeOther').hide();
            } else {
                projectTaskCIPHouse.houseTradingForm.find('.infomationTypeOpen').hide();
                projectTaskCIPHouse.houseTradingForm.find('.infomationTypeOther').show();
            }
        })
    }

    //新增出售或出租
    projectTaskCIPHouse.addTradingSellAndLease = function () {
        var key = projectTaskCIPHouse.houseTradingForm.find('[name=tradingType]').find("option:selected").attr('key');
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
    }

    //加载出售获取出租
    projectTaskCIPHouse.loadTradingSellAndLeaseList = function (tradingType, readonly) {
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
                    str += "<a class='btn btn-xs btn-warning tooltips' data-placement='top' data-original-title='删除' " + "onclick=projectTaskCIPHouse.deleteTradingSellAndLease(" + row.id + ",'" + tradingType + "'" + ")" + ">";
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
            houseId: projectTaskCIPHouse.getHouseId()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }

    //保存出售或出租
    projectTaskCIPHouse.saveTradingSellAndLease = function () {
        var frmSon = 'frmTradingLeaseAndSell';
        var divBoxSon = 'divBoxTradingLeaseAndSell';
        if (!$("#" + frmSon).valid()) {
            return false;
        }
        var data = formParams(frmSon);
        data.tradingType = data.type;
        data.houseId = projectTaskCIPHouse.getHouseId();
        console.log(data)
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
                    projectTaskCIPHouse.loadTradingSellAndLeaseList(data.type);
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    //删除出售或出租
    projectTaskCIPHouse.deleteTradingSellAndLease = function (id, type) {
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
                    projectTaskCIPHouse.loadTradingSellAndLeaseList(type);
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }


    window.projectTaskCIPHouse = projectTaskCIPHouse;
})(jQuery);
