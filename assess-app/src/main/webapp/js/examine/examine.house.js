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
        return houseCommon.houseForm.find('[name=id]').val()!=null?houseCommon.houseForm.find('[name=id]').val():houseCommon.tableId;
    };

    //户型选择
    houseCommon.selectHuxing = function (_this) {
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

    //户型选择(批量申请时通过unitid)
    houseCommon.selectHuxing2 = function (_this,tableId) {
        $.ajax({
            url: getContextPath() +'/basicUnitHuxing/getUnitId',
            data: {
                tableId: tableId
            },
            success: function (result) {
                if (result.ret) {
                    assessHuxing.selectByBasicUnitId({
                        basicUnitId: result.data,
                        success: function (row) {
                            //1.赋值 2.拷贝附件并显示附件数据
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
                } else {
                    Alert(result.errmsg);
                }
            }
        })

    }

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

    //项目中引用数据
    houseCommon.getDataFromProject = function (applyId, callback) {
        $.ajax({
            url: getContextPath() + '/basicHouse/getDataFromProject',
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

    //项目中引用楼盘(批量)
    houseCommon.batchGetDataFromProject = function (applyId, tableId, callback) {
        $.ajax({
            url: getContextPath() + '/basicHouse/batchGetDataFromProject',
            type: 'get',
            data: {
                applyId: applyId,
                tableId: tableId
            },
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
    houseCommon.initForm = function (data) {
        if (!data || !data.basicHouse) return;
        //基本信息
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
                houseCommon.fileShow(item);
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
                    houseCommon.deleteHouseTagging();//删除新户型图片时清空标注信息
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
                    houseCommon.deleteHouseTagging();//删除新户型图片时清空标注信息
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

    //获取单元的标注信息
    houseCommon.getUnitMarker = function () {
        var unitMarker;
        $.ajax({
            url: getContextPath() + '/basicEstateTagging/getUnitTagging',
            data: {
                applyId: basicCommon.getApplyId(),
                unitPartInMode: 'add'
            },
            async: false,
            success: function (result) {
                if (result.ret) {
                    unitMarker = result.data;
                }
            }
        });
        return unitMarker;
    };

    //获取单元的标注信息
    houseCommon.getUnitMarker2 = function () {
        var unitMarker;
        $.ajax({
            url: getContextPath() + '/basicEstateTagging/getUnitTaggingByHouseTableId',
            data: {
                houseTableId: houseCommon.tableId
            },
            async: false,
            success: function (result) {
                if (result.ret) {
                    unitMarker = result.data;
                }
            }
        });
        return unitMarker;
    };

    /**
     * 户型地图朝向
     */
    houseCommon.orientationFun = function (readonly) {
        $.ajax({
            url: getContextPath() + '/basicEstateTagging/getEstateTaggingList',
            data: {
                applyId: basicCommon.getApplyId(),
                type: "house"
            },
            async: false,
            success: function (result) {
                var data = {};
                if (result.ret && result.data && result.data.length > 0) {
                    data = result.data[0];
                } else {
                    var attachmentId = houseCommon.getMarkersysAttachmentId();
                    if (!attachmentId) {
                        toastr.info("未找到相关户型图片");
                        return;
                    }
                    data.attachmentId = attachmentId;
                    var unitMarker = houseCommon.getUnitMarker();
                    if (!unitMarker) {
                        toastr.info("还未标注单元位置信息");
                        return;
                    }
                    data.lng = unitMarker.lng;
                    data.lat = unitMarker.lat;
                    data.deg = 0;
                }

                var contentUrl = getContextPath() + '/map/houseTagging?';
                contentUrl += 'attachmentId=' + data.attachmentId;
                contentUrl += '&lng=' + data.lng;
                contentUrl += '&lat=' + data.lat;
                contentUrl += '&deg=' + data.deg;
                contentUrl += '&readonly=' + readonly;
                layer.open({
                    type: 2,
                    title: '房屋标注',
                    shade: true,
                    maxmin: true, //开启最大化最小化按钮
                    area: [basicCommon.getMarkerAreaInWidth, basicCommon.getMarkerAreaInHeight],
                    content: contentUrl,
                    success: function (layero) {
                        houseCommon.houseMapiframe = window[layero.find('iframe')[0]['name']];
                    },
                    cancel: function () {
                        if (!readonly) {
                            //到iframe中获取数据
                            $.ajax({
                                url: getContextPath() + '/basicEstateTagging/addBasicEstateTagging',
                                data: {
                                    applyId: basicCommon.getApplyId(),
                                    type: "house",
                                    lng: houseCommon.houseMapiframe.config.position.lng,
                                    lat: houseCommon.houseMapiframe.config.position.lat,
                                    deg: houseCommon.houseMapiframe.config.deg,
                                    attachmentId: houseCommon.houseMapiframe.config.attachmentId,
                                    name: houseCommon.houseForm.find('[name=houseNumber]').val()
                                },
                                success: function (result) {
                                    if (result.ret) {
                                    } else {
                                        Alert(result.errmsg);
                                    }
                                }
                            })
                        }
                    }
                });
            }
        })
    };

    /**
     * 户型地图朝向（通过tableId）
     */
    houseCommon.orientationFun2 = function (readonly, tableId) {
        houseCommon.tableId = tableId;
        $.ajax({
            url: getContextPath() + '/basicEstateTagging/getApplyBatchEstateTaggingsByTableId',
            data: {
                tableId: houseCommon.tableId,
                type: "house"
            },
            async: false,
            success: function (result) {
                var data = {};
                if (result.ret && result.data && result.data.length > 0) {
                    data = result.data[0];
                    console.log(data.id + "=1==")
                } else {
                    var attachmentId = houseCommon.getMarkersysAttachmentId();
                    console.log(attachmentId + "=2==")
                    if (!attachmentId) {
                        toastr.info("未找到相关户型图片");
                        return;
                    }
                    data.attachmentId = attachmentId;
                    var unitMarker = houseCommon.getUnitMarker2();
                    console.log(unitMarker + "=3==")
                    if (!unitMarker) {
                        toastr.info("还未标注单元位置信息");
                        return;
                    }
                    data.lng = unitMarker.lng;
                    data.lat = unitMarker.lat;
                    data.deg = 0;
                }

                var contentUrl = getContextPath() + '/map/houseTagging?';
                contentUrl += 'attachmentId=' + data.attachmentId;
                contentUrl += '&lng=' + data.lng;
                contentUrl += '&lat=' + data.lat;
                contentUrl += '&deg=' + data.deg;
                contentUrl += '&readonly=' + readonly;
                layer.open({
                    type: 2,
                    title: '房屋标注',
                    shade: true,
                    maxmin: true, //开启最大化最小化按钮
                    area: [basicCommon.getMarkerAreaInWidth, basicCommon.getMarkerAreaInHeight],
                    content: contentUrl,
                    success: function (layero) {
                        houseCommon.houseMapiframe = window[layero.find('iframe')[0]['name']];
                    },
                    cancel: function () {
                        if (!readonly) {
                            //到iframe中获取数据
                            $.ajax({
                                url: getContextPath() + '/basicEstateTagging/addBasicEstateTaggingByTableId',
                                data: {
                                    tableId: houseCommon.tableId,
                                    type: "house",
                                    lng: houseCommon.houseMapiframe.config.position.lng,
                                    lat: houseCommon.houseMapiframe.config.position.lat,
                                    deg: houseCommon.houseMapiframe.config.deg,
                                    attachmentId: houseCommon.houseMapiframe.config.attachmentId,
                                    name: houseCommon.houseForm.find('[name=houseNumber]').val()
                                },
                                success: function (result) {
                                    if (result.ret) {

                                    } else {
                                        Alert(result.errmsg);
                                    }
                                }
                            })
                        }
                    }
                });
            }
        })
    };

    //清除房屋标注
    houseCommon.deleteHouseTagging = function () {
        $.ajax({
            url: getContextPath() + '/basicEstateTagging/deleteHouseTagging',
            data: {
                applyId: basicCommon.getApplyId()
            },
            success: function (result) {
                if (result.ret) {

                } else {
                    Alert(result.errmsg);
                }
            }
        })
    };

    houseCommon.onSelect = function (id) {
        Loading.progressShow();
        $.ajax({
            url: getContextPath() + '/basicHouse/appWriteHouse',
            data: {
                applyId: basicCommon.getApplyId(),
                caseHouseId: id
            },
            type: 'post',
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    basicCommon.update({caseHouseId: id, id: basicCommon.getApplyId()}, function () {
                        houseCommon.detail(basicCommon.getApplyId(), function (data) {
                            houseCommon.initForm(data);
                        });
                        basicCommon.basicApplyForm.find("input[name='caseHouseId']").val(id);
                    });
                } else {
                    console.log(result.errmsg);
                    Alert("转移失败!");
                }
            }
        })
    };

    houseCommon.autocompleteStart = function () {
        if ($("#txt_House_search").size() >= 1) {
            $("#txt_House_search").apHouse({
                caseUnitId: function () {
                    return basicCommon.getCaseUnitId();
                },
                onSelect: function (id, name) {
                    houseCommon.onSelect(id);
                }
            });
        }
    };

    //计算单价
    houseCommon.computeUnitPrice = function () {
        var area = houseCommon.houseForm.find('[name=area]').val();
        var tradingTotalPrice = houseCommon.houseTradingForm.find('[name=tradingTotalPrice]').val();
        if (AssessCommon.isNumber(area) && AssessCommon.isNumber(area)) {
            var tradingUnitPrice = parseFloat(tradingTotalPrice) / parseFloat(area);
            houseCommon.houseTradingForm.find('[name=tradingUnitPrice]').val(parseInt(tradingUnitPrice));
        }
    };


    window.houseCommon = houseCommon;
})(jQuery);