var unitDecorate;
(function () {
    unitDecorate = function () {

    };
    unitDecorate.prototype = {
        config: function () {
            var data = {};
            data.table = "ExamineUnitDecorateList";
            data.box = "divBoxExamineUnitDecorate";
            data.frm = "frmExamineUnitDecorate";
            return data;
        },
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function () {
            var cols = commonColumn.unitDecorateColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="unitDecorate.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="unitDecorate.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + unitDecorate.prototype.config().table).bootstrapTable('destroy');
            TableInit(unitDecorate.prototype.config().table, getContextPath() + "/basicUnitDecorate/getBootstrapTableVo", cols, {
                unitId: unitCommon.getUnitId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicUnitDecorate/deleteBasicUnitDecorate",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('删除成功');
                        unitDecorate.prototype.loadDataDicList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            unitDecorate.prototype.init({});
            $('#' + unitDecorate.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + unitDecorate.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(unitDecorate.prototype.config().frm, true);
            data.unitId = unitCommon.getUnitId();
            $.ajax({
                url: getContextPath() + "/basicUnitDecorate/saveAndUpdateBasicUnitDecorate",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#' + unitDecorate.prototype.config().box).modal('hide');
                        unitDecorate.prototype.loadDataDicList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicUnitDecorate/getBasicUnitDecorateById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (unitDecorate.prototype.isNotBlank(result.data)) {
                            unitDecorate.prototype.init(result.data);
                        } else {
                            unitDecorate.prototype.init({});
                        }
                        $('#' + unitDecorate.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + unitDecorate.prototype.config().frm).clearAll().initForm(item, function () {
                $("#" + unitDecorate.prototype.config().frm).find('select.decoratingMaterial').off('change').on('change', function () {
                    AssessCommon.loadDataDicByPid($(this).val(), item.constructionTechnology, function (html, data) {
                        $("#" + unitDecorate.prototype.config().frm).find('select.constructionTechnology').empty().html(html).trigger('change');
                    });
                });
                $("#" + unitDecorate.prototype.config().frm).find('select.constructionTechnology').off('change').on('change', function () {
                    AssessCommon.loadDataDicByPid($(this).val(), item.materialPriceRange, function (html, data) {
                        $("#" + unitDecorate.prototype.config().frm).find('select.materialPriceRange').empty().html(html).trigger('change');
                    });
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.examineUnitInteriorDecorationPart, item.decorationPart, function (html, data) {
                    $("#" + unitDecorate.prototype.config().frm).find('select.decorationPart').empty().html(html).trigger('change');
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.examineUnitInteriorDecorationMaterial, item.decoratingMaterial, function (html, data) {
                    $("#" + unitDecorate.prototype.config().frm).find('select.decoratingMaterial').empty().html(html).trigger('change');
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.examineCommonMaterialGrade, item.materialGrade, function (html, data) {
                    $("#" + unitDecorate.prototype.config().frm).find('select.materialGrade').empty().html(html).trigger('change');
                });
            });
        }
    }

    //绑定事件
    $('#' + unitDecorate.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        unitDecorate.prototype.loadDataDicList();
    })
})();
////----------------------------------
var unitHuxing;
(function () {
    unitHuxing = function () {
    };
    unitHuxing.prototype = {
        config: function () {
            var data = {};
            data.table = "UnitHuxingList";
            data.box = "divBoxUnitHuxing";
            data.frm = "frmUnitHuxing";
            data.unitHuxingFileIDFildName = "house_latest_family_planV";
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.unitHuxingColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="unitHuxing.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="unitHuxing.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="单价调查" onclick="unitHuxingPrice.prototype.showTableModel(' + row.id + ',\'tb_List\')"><i class="fa fa-arrow-right fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="下载房屋模板" onclick="unitHuxing.prototype.getAttachmentId(' + row.id + ',\'tb_List\')">房屋模板</a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + unitHuxing.prototype.config().table).bootstrapTable('destroy');
            TableInit(unitHuxing.prototype.config().table, getContextPath() + "/basicUnitHuxing/getBootstrapTableVo", cols, {
                unitId: unitCommon.getUnitId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicUnitHuxing/deleteBasicUnitHuxing",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('删除成功');
                        unitHuxing.prototype.loadDataDicList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            unitHuxing.prototype.init({});
            $('#' + unitHuxing.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + unitHuxing.prototype.config().frm).valid()) {
                return false;
            }
            if ($("#_" + unitHuxing.prototype.config().unitHuxingFileIDFildName).html().length <= 0) {
                toastr.info("请上传户型图");
                return false;
            }

            var data = formParams(unitHuxing.prototype.config().frm, true);
            data.unitId = unitCommon.getUnitId();
            data.houseCategory = unitHuxing.prototype.rule("get", data);
            var key = $("#" + unitHuxing.prototype.config().frm).find('select.type').find('option:selected').attr('key');
            if (key == AssessDicKey.examineUnitHuxingTypeProduction) {
                data.name = "跨长" + data.spanLength + ",跨宽" + data.spanWidth + ",跨数" + data.spanNumber;
            } else if (key == AssessDicKey.examineUnitHuxingTypeOffice) {
                data.name = "开间" + data.bay + ",进深" + data.deep;
            } else {
                data.name = unitHuxing.prototype.rule("formatter", data);
            }
            $.ajax({
                url: getContextPath() + "/basicUnitHuxing/saveAndUpdateBasicUnitHuxing",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#' + unitHuxing.prototype.config().box).modal('hide');
                        unitHuxing.prototype.loadDataDicList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        /**
         * @author:  zch
         * 描述:户型的类别填写方式【】室【】厅【】厨【】卫【】花园【】阳台
         * @date:
         **/
        rule: function (flag, item) {
            var text = "";
            //格式化
            if (flag == "formatter") {
                if (unitHuxing.prototype.isNotNull(item.house)) {
                    text += item.house + "室";
                }
                if (unitHuxing.prototype.isNotNull(item.saloon)) {
                    text += item.saloon + "厅";
                }
                if (unitHuxing.prototype.isNotNull(item.kitchen)) {
                    text += item.kitchen + "厨";
                }
                if (unitHuxing.prototype.isNotNull(item.toilet)) {
                    text += item.toilet + "卫";
                }
                if (unitHuxing.prototype.isNotNull(item.garden)) {
                    text += item.garden + "花园";
                }
                if (unitHuxing.prototype.isNotNull(item.balcony)) {
                    text += item.balcony + "阳台";
                }
                return text;
            }
            //转为json存入数据库
            if (flag == "get") {
                var data = {};
                if (unitHuxing.prototype.isNotNull(item.house)) {
                    data.house = item.house;
                }
                if (unitHuxing.prototype.isNotNull(item.saloon)) {
                    data.saloon = item.saloon;
                }
                if (unitHuxing.prototype.isNotNull(item.kitchen)) {
                    data.kitchen = item.kitchen;
                }
                if (unitHuxing.prototype.isNotNull(item.toilet)) {
                    data.toilet = item.toilet;
                }
                if (unitHuxing.prototype.isNotNull(item.garden)) {
                    data.garden = item.garden;
                }
                if (unitHuxing.prototype.isNotNull(item.balcony)) {
                    data.balcony = item.balcony;
                }
                return JSON.stringify(data);
            }
            //赋值
            if (flag == "set") {
                if (unitHuxing.prototype.isNotNull(item.house)) {
                    $("#" + unitHuxing.prototype.config().frm + " input[name='house']").val(item.house);
                }
                if (unitHuxing.prototype.isNotNull(item.saloon)) {
                    $("#" + unitHuxing.prototype.config().frm + " input[name='saloon']").val(item.saloon);
                }
                if (unitHuxing.prototype.isNotNull(item.kitchen)) {
                    $("#" + unitHuxing.prototype.config().frm + " input[name='kitchen']").val(item.kitchen);
                }
                if (unitHuxing.prototype.isNotNull(item.toilet)) {
                    $("#" + unitHuxing.prototype.config().frm + " input[name='toilet']").val(item.toilet);
                }
                if (unitHuxing.prototype.isNotNull(item.garden)) {
                    $("#" + unitHuxing.prototype.config().frm + " input[name='garden']").val(item.garden);
                }
                if (unitHuxing.prototype.isNotNull(item.balcony)) {
                    $("#" + unitHuxing.prototype.config().frm + " input[name='balcony']").val(item.balcony);
                }
            }
        },
        isNotNull: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicUnitHuxing/getBasicUnitHuxingById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (unitHuxing.prototype.isNotNull(data)) {
                            unitHuxing.prototype.init(data);
                        } else {
                            unitHuxing.prototype.init({});
                        }
                        $('#' + unitHuxing.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + unitHuxing.prototype.config().frm).clearAll();
            $("#" + unitHuxing.prototype.config().frm).initForm(item, function () {
                $("#" + unitHuxing.prototype.config().frm).find('select.type').off('change').on('change', function () {
                    var key = $(this).find('option:selected').attr('key');
                    if (key == AssessDicKey.examineUnitHuxingTypeStay) {
                        $("#huxingTypeStay").show();
                        $("#huxingTypeProduction,#huxingTypeOffice").hide();
                    } else if (key == AssessDicKey.examineUnitHuxingTypeProduction) {
                        $("#huxingTypeProduction").show();
                        $("#huxingTypeStay,#huxingTypeOffice").hide();
                    } else if (key == AssessDicKey.examineUnitHuxingTypeOffice) {
                        $("#huxingTypeOffice").show();
                        $("#huxingTypeProduction,#huxingTypeStay").hide();
                    } else {
                        if (basicCommon.basicApplyForm.find('[name=type]:checked').val() == 1) {
                            $("#huxingTypeStay,#huxingTypeProduction,#huxingTypeOffice").hide();
                        }
                    }
                })
            });
            FileUtils.uploadFiles({
                target: unitHuxing.prototype.config().unitHuxingFileIDFildName,
                disabledTarget: "btn_submit",
                formData: {
                    fieldsName: unitHuxing.prototype.config().unitHuxingFileIDFildName,
                    tableName: AssessDBKey.BasicUnitHuxing,
                    tableId: unitHuxing.prototype.isNotNull(item.id) ? item.id : 0
                },
                deleteFlag: true
            });

            FileUtils.getFileShows({
                target: unitHuxing.prototype.config().unitHuxingFileIDFildName,
                formData: {
                    fieldsName: unitHuxing.prototype.config().unitHuxingFileIDFildName,
                    tableName: AssessDBKey.BasicUnitHuxing,
                    tableId: unitHuxing.prototype.isNotNull(item.id) ? item.id : 0
                },
                deleteFlag: true
            })

            if (unitHuxing.prototype.isNotNull(item.houseCategory)) {
                unitHuxing.prototype.rule("set", JSON.parse(item.houseCategory));
            }

            AssessCommon.loadDataDicByKey(AssessDicKey.examineUnitHuxingType, item.type, function (html, data) {
                $("#" + unitHuxing.prototype.config().frm).find('select.type').empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineCommonOrientation, item.orientation, function (html, data) {
                $("#" + unitHuxing.prototype.config().frm).find('select.orientation').empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseSpatialDistribution, item.spatialDistribution, function (html, data) {
                $("#" + unitHuxing.prototype.config().frm).find('select.spatialDistribution').empty().html(html).trigger('change');
            });
        },
        //下载附件模板
        getAttachmentId: function (tableId) {
            $.ajax({
                url: getContextPath() + "/basicUnitHuxing/getAttachmentId",
                type: "get",
                data: {tableId: tableId},
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        window.open(FileUtils.config.downloadUrl + result.data);
                    }
                }
            })
        },
        importHouseBtn: function () {
            $('#ajaxFileUploadHouse').val('').trigger('click');
        },
        importHouse: function () {
            Loading.progressShow();
            $.ajaxFileUpload({
                type: "POST",
                url: getContextPath() + "/basicUnitHuxing/importHouse",
                data: {
                    unitId: unitCommon.getUnitId()
                },//要传到后台的参数，没有可以不写
                secureuri: false,//是否启用安全提交，默认为false
                fileElementId: 'ajaxFileUploadHouse',//文件选择框的id属性
                dataType: 'json',//服务器返回的格式
                async: false,
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        Alert(result.data);
                    }
                },
                error: function (result, status, e) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        }
    }

    //绑定事件
    $('#' + unitHuxing.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        unitHuxing.prototype.loadDataDicList();
    })
})();

var unitHuxingPrice;
(function () {
    unitHuxingPrice = function () {
    };
    unitHuxingPrice.prototype = {
        config: function () {
            var data = {};
            data.table = "UnitHuxingPriceList";
            data.box = "divBoxUnitHuxingPrice";
            data.frm = "frmUnitHuxingPrice";
            data.tableBox = "divBoxUnitHuxingPriceTable";
            data.tableFrm = "frmUnitHuxingPriceTable";
            return data;
        },
        loadDataDicList: function (unitHuxingId) {
            var cols = commonColumn.estateInvestigation();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="unitHuxingPrice.prototype.getAndInit(' + row.id + ',\'' + unitHuxingId + '\',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="unitHuxingPrice.prototype.removeData(' + row.id + ',\'' + unitHuxingId + '\',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + unitHuxingPrice.prototype.config().table).bootstrapTable('destroy');
            TableInit(unitHuxingPrice.prototype.config().table, getContextPath() + "/basicEstateInvestigation/getEstateInvestigationList", cols, {
                unitHuxingId: unitHuxingId
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id, unitHuxingId) {
            $.ajax({
                url: getContextPath() + "/basicEstateInvestigation/deleteBasicEstateInvestigation",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('删除成功');
                        unitHuxingPrice.prototype.loadDataDicList(unitHuxingId);
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showTableModel: function (unitHuxingId) {
            unitHuxingPrice.prototype.loadDataDicList(unitHuxingId);
            $("#" + unitHuxingPrice.prototype.config().tableFrm).find("input[name='unitHuxingId']").val(unitHuxingId);
            $('#' + unitHuxingPrice.prototype.config().tableBox).modal("show");
        },
        showModel: function () {
            $("#" + unitHuxingPrice.prototype.config().frm).clearAll();
            var unitHuxingId = $("#" + unitHuxingPrice.prototype.config().tableFrm).find("input[name='unitHuxingId']").val();
            $("#" + unitHuxingPrice.prototype.config().frm).find("input[name='huxingId']").val(unitHuxingId);
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseLoadUtility, '', function (html, data) {
                $("#" + unitHuxingPrice.prototype.config().frm).find('select.planningUse').empty().html(html).trigger('change');
            });
            $('#' + unitHuxingPrice.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + unitHuxingPrice.prototype.config().frm).valid()) {
                return false;
            }
            var unitHuxingId = $("#" + unitHuxingPrice.prototype.config().frm).find("input[name='huxingId']").val()
            var data = formParams(unitHuxingPrice.prototype.config().frm, true);
            $.ajax({
                url: getContextPath() + "/basicEstateInvestigation/saveAndUpdateBasicEstateInvestigation",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#' + unitHuxingPrice.prototype.config().box).modal('hide');
                        unitHuxingPrice.prototype.loadDataDicList(unitHuxingId);
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        isNotNull: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        getAndInit: function (id, unitHuxingId) {
            $.ajax({
                url: getContextPath() + "/basicEstateInvestigation/getBasicEstateInvestigationById",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (unitHuxingPrice.prototype.isNotNull(data)) {
                            unitHuxingPrice.prototype.init(data, unitHuxingId);
                        } else {
                            unitHuxingPrice.prototype.init({});
                        }
                        $('#' + unitHuxingPrice.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item, unitHuxingId) {
            $("#" + unitHuxingPrice.prototype.config().frm).clearAll();
            $("#" + unitHuxingPrice.prototype.config().frm).find("input[name='huxingId']").val(unitHuxingId);
            $("#" + unitHuxingPrice.prototype.config().frm).initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseLoadUtility, item.planningUse, function (html, data) {
                $("#" + unitHuxingPrice.prototype.config().frm).find('select.planningUse').empty().html(html).trigger('change');
            });
        },
        importData: function () {
            var unitHuxingId = $("#" + unitHuxingPrice.prototype.config().tableFrm).find("input[name='unitHuxingId']").val();
            $.ajaxFileUpload({
                type: "POST",
                url: getContextPath() + "/basicEstateInvestigation/importData",
                data: {
                    huxingId: unitHuxingId
                },//要传到后台的参数，没有可以不写
                secureuri: false,//是否启用安全提交，默认为false
                fileElementId: 'ajaxFileUpload',//文件选择框的id属性
                dataType: 'json',//服务器返回的格式
                async: false,
                success: function (result) {
                    if (result.ret) {
                        unitHuxingPrice.prototype.loadDataDicList(unitHuxingId);
                        Alert(result.data);
                    }
                },
                error: function (result, status, e) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        }
    }

})();


var unitElevator;
(function () {
    unitElevator = function () {

    };
    unitElevator.prototype = {
        isNotNull: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "ExamineUnitElevatorList";
            data.box = "divBoxExamineUnitElevator";
            data.frm = "frmExamineUnitElevator";
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.unitElevatorColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="unitElevator.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="unitElevator.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + unitElevator.prototype.config().table).bootstrapTable('destroy');
            TableInit(unitElevator.prototype.config().table, getContextPath() + "/basicUnitElevator/getBootstrapTableVo", cols, {
                unitId: unitCommon.getUnitId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicUnitElevator/deleteBasicUnitElevator",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('删除成功');
                        unitElevator.prototype.loadDataDicList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            unitElevator.prototype.init({});
            $('#' + unitElevator.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + unitElevator.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(unitElevator.prototype.config().frm, true);
            data.unitId = unitCommon.getUnitId();
            $.ajax({
                url: getContextPath() + "/basicUnitElevator/saveAndUpdateBasicUnitElevator",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#' + unitElevator.prototype.config().box).modal('hide');
                        unitElevator.prototype.loadDataDicList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicUnitElevator/getBasicUnitElevatorById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        unitElevator.prototype.init(result.data);
                        $('#' + unitElevator.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + unitElevator.prototype.config().frm).clearAll().initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.examineCommonMaintenanceSituation, item.maintenance, function (html, data) {
                $("#" + unitElevator.prototype.config().frm).find('select.maintenance').empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineUnitElevatorType, item.type, function (html, data) {
                $("#" + unitElevator.prototype.config().frm).find('select.type').empty().html(html).trigger('change');
            });
        }
    }

    //绑定事件
    $('#' + unitElevator.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        unitElevator.prototype.loadDataDicList();
    })
})();