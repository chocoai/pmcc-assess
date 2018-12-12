/**
 * Created by asus on 2018/11/24.
 */

;(function ($) {
    var houseCommon = {};
    houseCommon.houseTradingForm = $('#basicTradingFrm');
    houseCommon.houseForm = $('#basicHouseFrm');
    houseCommon.houseTradingTypeSell = 'ExamineHouseTradingSell';
    houseCommon.houseTradingTypeLease = 'ExamineHouseTradingLease';
    //附件上传控件id数组
    houseCommon.houseFileControlIdArray = [
        'house_huxing_plan',
        'house_new_huxing_plan',
        'house_img_plan'];

    houseCommon.getHouseId = function () {
        return houseCommon.houseForm.find('[name=id]').val();
    }
    houseCommon.getApplyId = function () {
        return houseCommon.houseForm.find('[name=applyId]').val();
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
    }

    //升级房屋
    houseCommon.upgrade = function (_this, callback) {
        var caseHouseId = $(_this).closest('form').find("input[name='caseHouseId']").val()
        if (!caseHouseId) {
            toastr.info('请选择系统中已存在的房屋信息！');
            return false;
        }
        $.ajax({
            url: getContextPath() + '/basicHouse/appWriteHouse',
            data: {caseHouseId: caseHouseId},
            success: function (result) {
                if (result.ret) {
                    houseCommon.showHouseView(result.data);
                    if (callback) {
                        callback($(_this).attr('data-mode'));
                    }
                }
            }
        })
    }

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
                        callback();
                    }
                }
            }
        })
    }

    //房屋明细
    houseCommon.detail = function (applyId) {
        $.ajax({
            url: getContextPath() + '/basicHouse/getBasicHouseByApplyId',
            type: 'get',
            data: {applyId: applyId},
            success: function (result) {
                if (result.ret) {
                    houseCommon.houseForm.initForm(result.data.basicHouse, function () {
                        //附件显示
                        $.each(houseCommon.houseFileControlIdArray, function (i, item) {
                            houseCommon.fileShow(item, false);
                        })
                    })
                    houseCommon.houseTradingForm.initForm(result.data.basicHouseTrading);
                }
            }
        })
    }

    //显示房屋对应部分信息
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
        });

        //交易情况
        houseCommon.houseTradingForm.initForm(data.basicHouseTrading, function () {
            houseCommon.changeEvent(data.basicHouseTrading);
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHousetaxBurden, data.basicHouseTrading.taxBurden, function (html, data) {
                houseCommon.houseTradingForm.find("select.taxBurden").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseTransactionType, data.basicHouseTrading.tradingType, function (html, data) {
                houseCommon.houseTradingForm.find("select.tradingType").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseDescriptionType, data.basicHouseTrading.descriptionType, function (html, data) {
                houseCommon.houseTradingForm.find("select.descriptionType").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseNormalTransaction, data.basicHouseTrading.normalTransaction, function (html, data) {
                houseCommon.houseTradingForm.find("select.normalTransaction").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseFinancingConditions, data.basicHouseTrading.financingConditions, function (html, data) {
                houseCommon.houseTradingForm.find("select.financingConditions").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseScopeProperty, data.basicHouseTrading.scopeProperty, function (html, data) {
                houseCommon.houseTradingForm.find("select.scopeProperty").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseInformationSourceType, data.basicHouseTrading.informationType, function (html, data) {
                houseCommon.houseTradingForm.find("select.informationType").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseInformationSourceCategory, data.basicHouseTrading.informationCategory, function (html, data) {
                houseCommon.houseTradingForm.find("select.informationCategory").empty().html(html).trigger('change');
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
            caseUnitId: basicCommon.basicApplyForm.find('[name=caseUnitId]').val(),
            success: function (row) {
                //1.赋值 2.拷贝附件并显示附件数据
                $(_this).closest('.input-group').find(':text').val(row.name);
                houseCommon.houseForm.find('[name=orientation]').val(row.orientationName);
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
    }

    //下拉框change事件
    houseCommon.changeEvent = function (basicHouseTrading) {
        houseCommon.houseTradingForm.find('[name=tradingType]').change(function () {
            var text = $(this).find("option:selected").text();
            var paymentMethod = basicHouseTrading == null ? null : basicHouseTrading.paymentMethod;
            if (text == '出售') {
                houseCommon.houseTradingForm.find('.' + houseCommon.houseTradingTypeSell).show();
                houseCommon.houseTradingForm.find('.' + houseCommon.houseTradingTypeLease).hide();
                houseCommon.loadTradingSellAndLeaseList(houseCommon.houseTradingTypeSell);
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePaymentMethod, basicHouseTrading.paymentMethod, function (html, data) {
                    houseCommon.houseTradingForm.find("select.paymentMethod").empty().html(html).trigger('change');
                });

            } else if (text == '出租') {
                houseCommon.houseTradingForm.find('.' + houseCommon.houseTradingTypeSell).hide();
                $('.' + houseCommon.houseTradingTypeLease).show();
                houseCommon.loadTradingSellAndLeaseList(houseCommon.houseTradingTypeLease);
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePaymentMethodLease, basicHouseTrading.paymentMethod, function (html, data) {
                    houseCommon.houseTradingForm.find("select.paymentMethod").empty().html(html).trigger('change');
                });
            } else {
                houseCommon.houseTradingForm.find('.' + houseCommon.houseTradingTypeSell).hide();
                houseCommon.houseTradingForm.find('.' + houseCommon.houseTradingTypeLease).hide();
            }
        })

        houseCommon.houseTradingForm.find('[name=paymentMethod]').change(function () {
            var text = $(this).find("option:selected").text();
            if (text == '分期付款') {
                $(this).closest('.form-group').children().eq(2).show();
            } else {
                $(this).closest('.form-group').children().eq(2).hide();
            }
        })

        //信息来源类型
        houseCommon.houseTradingForm.find("[name=informationType]").change(function () {
            var text = $(this).find("option:selected").text();
            if (text) {
                if (text == '公开信息') {
                    houseCommon.houseTradingForm.find('.infomationTypeOpen').show();
                    houseCommon.houseTradingForm.find('.infomationTypeOther').hide();
                } else {
                    houseCommon.houseTradingForm.find('.infomationTypeOpen').hide();
                    houseCommon.houseTradingForm.find('.infomationTypeOther').show();
                }
            }
        })
    }

    //新增出售或出租
    houseCommon.addTradingSellAndLease = function () {
        var tradingID = houseCommon.houseTradingForm.find('[name=tradingType]').val();
        var tradingType = null;
        AssessCommon.getDataDicInfo(tradingID, function (data) {
            tradingType = data.fieldName;
            var frmSon = 'frmTradingLeaseAndSell';
            var divBoxSon = 'divBoxTradingLeaseAndSell';
            $("#" + frmSon).clearAll().find(".type").val(tradingType);
            $("#" + divBoxSon).modal("show");
            if (tradingType == houseCommon.houseTradingTypeSell) {
                $("#" + divBoxSon).find(".lease").show();
                $("#" + divBoxSon).find(".sell").hide();
                $("#" + divBoxSon).find(".modal-title").html("出租信息");
            }
            if (tradingType == houseCommon.houseTradingTypeLease) {
                $("#" + divBoxSon).find(".lease").hide();
                $("#" + divBoxSon).find(".sell").show();
                $("#" + divBoxSon).find(".modal-title").html("出售信息");
            }
        })
    }

    //加载出售获取出租
    houseCommon.loadTradingSellAndLeaseList = function (type) {
        var cols = [];
        var tbListId = '';
        if (type == houseCommon.houseTradingTypeSell) {
            tbListId = 'tableTradingSell';
            cols.push({field: 'instalmentInterest', title: '分期支付时间起'});
            cols.push({field: 'instalmentPeriodStartName', title: '分期支付时间止'});
            cols.push({field: 'instalmentPeriodEndName', title: '分期支付利息'});
        }
        if (type == houseCommon.houseTradingTypeLease) {
            tbListId = 'tableTradingLease';
            cols.push({field: 'rentGrowthRate', title: '租金增长比率'});
            cols.push({field: 'rentPaymentTimeStartName', title: '租金支付时间起'});
            cols.push({field: 'rentPaymentTimeEndName', title: '租金支付时间止'});
        }
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += "<a class='btn btn-xs btn-warning tooltips' data-placement='top' data-original-title='删除' " + "onclick=houseCommon.deleteTradingSellAndLease(" + row.id + ",'" + type + "'" + ")" + ">";
                str += "<i class='fa fa-minus fa-white'>" + "</i>";
                str += "</a>";
                str += '</div>';
                return str;
            }
        });
        $("#" + tbListId).bootstrapTable('destroy');
        TableInit(tbListId, getContextPath() + "/basicHouseTradingLeaseAndSell/getLeaseAndSellVos", cols, {
            type: type,
            houseId: houseCommon.getHouseId()
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
    }

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
    }

    houseCommon.addMarker = function (lng,lat,attachmentId,deg) {
        var data = {type:"house",applyId:houseCommon.getApplyId(),name:formSerializeArray(houseCommon.houseForm).houseNumber};
        data.lng = lng;
        data.lat = lat;
        data.deg = deg;
        data.attachmentId = attachmentId;
        $.ajax({
            url: getContextPath() + '/basicEstateTagging/addBasicEstateTagging',
            data: data,
            success: function (result) {
                if (result.ret) {

                } else {
                    Alert(result.errmsg);
                }
            }
        })
    };

    houseCommon.orientationFun = function () {
        var unitId = unitCommon.getUnitId();
        var sysAttachmentId = "";
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
                if (result.ret) {
                    if (result.data) {
                        console.log(result.data);
                        for (var i = 0; i < result.data.length; i++) {
                            if (result.data[i].fieldsName == houseCommon.houseFileControlIdArray[1]) {
                                //后缀必须为图片
                                if (AssessCommon.checkImgFile(result.data[i].fileName)){
                                    sysAttachmentId = result.data[i].id;
                                    break;
                                }
                            }
                        }
                        if (sysAttachmentId){
                            //.....
                        }else {
                            //假如未找到需要的情况下新户型那么找另一种
                            for (var i = 0; i < result.data.length; i++) {
                                if (result.data[i].fieldsName == houseCommon.houseFileControlIdArray[0]) {
                                    //后缀必须为图片
                                    if (AssessCommon.checkImgFile(result.data[i].fileName)){
                                        sysAttachmentId = result.data[i].id;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
        var contentUrl = getContextPath() + '/map/houseTagging?sysAttachmentId='+sysAttachmentId+"&unitId="+unitId+"&click=houseCommon.addMarker";
        layer.open({
            type: 2,
            title: '房屋标注',
            shadeClose: true,
            shade: true,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: contentUrl,
            success: function () {

            }
        });
    }

    window.houseCommon = houseCommon;
})(jQuery);