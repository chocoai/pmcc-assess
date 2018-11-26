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
            var cols = [];
            cols.push({field: 'decorationPartName', title: '装修部位'});
            cols.push({field: 'decoratingMaterialName', title: '装修材料'});
            cols.push({field: 'materialPriceName', title: '材料价格区间'});
            cols.push({field: 'location', title: '所在位置'});
            cols.push({field: 'constructionTechnologyName', title: '施工工艺'});
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
            TableInit(unitDecorate.prototype.config().table, getContextPath()+"/basicUnitDecorate/getBootstrapTableVo", cols, {
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
                url: getContextPath()+"/basicUnitDecorate/deleteBasicUnitDecorate",
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
            var data = formParams(unitDecorate.prototype.config().frm);
            data.unitId = unitCommon.getUnitId() ;
            $.ajax({
                url: getContextPath()+"/basicUnitDecorate/saveAndUpdateBasicUnitDecorate",
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
                url: getContextPath()+"/basicUnitDecorate/getBasicUnitDecorateById",
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
            $("#" + unitDecorate.prototype.config().frm).clearAll();
            $("#" + unitDecorate.prototype.config().frm).initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_decorating_material, item.decoratingMaterial, function (html, data) {
                $("#" + unitDecorate.prototype.config().frm).find('select.decoratingMaterial').empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_material_price, item.materialPriceRange, function (html, data) {
                $("#" + unitDecorate.prototype.config().frm).find('select.materialPriceRange').empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_construction_technology, item.constructionTechnology, function (html, data) {
                $("#" + unitDecorate.prototype.config().frm).find('select.constructionTechnology').empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_decoration_part, item.decorationPart, function (html, data) {
                $("#" + unitDecorate.prototype.config().frm).find('select.decorationPart').empty().html(html).trigger('change');
            });
        }
    }

    //绑定事件
    $('#'+unitDecorate.prototype.config().table).closest('.x_panel').bind('click',function () {
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
            var cols = [];
            cols.push({field: 'description', title: '描述'});
            cols.push({field: 'spanLength', title: '跨长'});
            cols.push({field: 'orientationName', title: '朝向'});
            cols.push({field: 'houseLayoutName', title: '房型'});
            cols.push({field: 'spanWidth', title: '跨宽'});
            cols.push({field: 'spanNumber', title: '跨数'});
            cols.push({field: 'fileViewName', title: '户型图'});
            cols.push({
                field: 'houseCategory', title: '房型', formatter: function (value, row, index) {
                    var str = "";
                    if (unitHuxing.prototype.isNotNull(row.houseCategory)){
                        str = unitHuxing.prototype.rule("formatter",JSON.parse(row.houseCategory));
                    }
                    return str;
                }
            });
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="unitHuxing.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="unitHuxing.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + unitHuxing.prototype.config().table).bootstrapTable('destroy');
            TableInit(unitHuxing.prototype.config().table, getContextPath()+"/basicUnitHuxing/getBootstrapTableVo", cols, {
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
                url: getContextPath()+"/basicUnitHuxing/deleteBasicUnitHuxing",
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
            var data = formParams(unitHuxing.prototype.config().frm);
            data.unitId = unitCommon.getUnitId() ;
            data.houseCategory = unitHuxing.prototype.rule("get",data);
            $.ajax({
                url: getContextPath()+"/basicUnitHuxing/saveAndUpdateBasicUnitHuxing",
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
         * 描述:户型的类别填写方式【】房【】厅【】厨【】卫【】花园【】阳台
         * @date:
         **/
        rule: function (flag, item) {
            var text = "";
            //格式化
            if (flag == "formatter") {
                if (unitHuxing.prototype.isNotNull(item.house)) {
                    text += item.house + "房-";
                }
                if (unitHuxing.prototype.isNotNull(item.saloon)) {
                    text += item.saloon + "客厅-";
                }
                if (unitHuxing.prototype.isNotNull(item.kitchen)) {
                    text += item.kitchen + "厨房-";
                }
                if (unitHuxing.prototype.isNotNull(item.toilet)) {
                    text += item.toilet + "卫生间-";
                }
                if (unitHuxing.prototype.isNotNull(item.garden)) {
                    text += item.garden + "花园-";
                }
                if (unitHuxing.prototype.isNotNull(item.balcony)) {
                    text += item.balcony + "阳台";
                }
                return text;
            }
            //转为json存入数据库
            if (flag == "get"){
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
            if (flag == "set"){
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
                url: getContextPath()+"/basicUnitHuxing/getBasicUnitHuxingById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data ;
                        if (unitHuxing.prototype.isNotNull(data)){
                            unitHuxing.prototype.init(data);
                        }else {
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
            $("#" + unitHuxing.prototype.config().frm).initForm(item);
            FileUtils.uploadFiles({
                target: unitHuxing.prototype.config().unitHuxingFileIDFildName,
                disabledTarget: "btn_submit",
                formData: {
                    fieldsName: unitHuxing.prototype.config().unitHuxingFileIDFildName,
                    tableName: AssessDBKey.BasicUnitHuxing,
                    tableId: item.id
                },
                deleteFlag: true
            });

            FileUtils.getFileShows({
                target: unitHuxing.prototype.config().unitHuxingFileIDFildName,
                formData: {
                    fieldsName: unitHuxing.prototype.config().unitHuxingFileIDFildName,
                    tableName: AssessDBKey.BasicUnitHuxing,
                    tableId: item.id
                },
                deleteFlag: true
            })

            if (unitHuxing.prototype.isNotNull(item.houseCategory)){
                unitHuxing.prototype.rule("set",JSON.parse(item.houseCategory));
            }
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseHouse_layout, item.houseLayout, function (html, data) {
                $("#" + unitHuxing.prototype.config().frm).find('select.houseLayout').empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineUnitOrientation, item.orientation, function (html, data) {
                $("#" + unitHuxing.prototype.config().frm).find('select.orientation').empty().html(html).trigger('change');
            });
        }
    }

    //绑定事件
    $('#'+unitHuxing.prototype.config().table).closest('.x_panel').bind('click',function () {
        unitHuxing.prototype.loadDataDicList();
    })
})();

var unitElevator;
(function () {
    unitElevator = function () {

    };
    unitElevator.prototype = {
        isNotNull:function (item) {
            if (item){
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
            var cols = [];
            cols.push({field: 'number', title: '电梯数量'});
            cols.push({field: 'typeName', title: '电梯类型'});
            cols.push({field: 'quasiLoadNumber', title: '准载人数'});
            cols.push({field: 'quasiLoadWeight', title: '准载重量'});
            cols.push({field: 'runningSpeed', title: '运行速度'});
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
            TableInit(unitElevator.prototype.config().table, getContextPath()+"/basicUnitElevator/getBootstrapTableVo", cols, {
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
                url: getContextPath()+"/basicUnitElevator/deleteBasicUnitElevator",
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
            var data = formParams(unitElevator.prototype.config().frm);
            data.unitId = unitCommon.getUnitId();
            $.ajax({
                url: getContextPath()+"/basicUnitElevator/saveAndUpdateBasicUnitElevator",
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
                url: getContextPath()+"/basicUnitElevator/getBasicUnitElevatorById",
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
        init:function (item) {
            $("#" + unitElevator.prototype.config().frm).clearAll();
            $("#" + unitElevator.prototype.config().frm).initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.examineUnitElevatorType, item.type, function (html, data) {
                $("#" + unitElevator.prototype.config().frm).find('select.type').empty().html(html).trigger('change');
            });
        }
    }

    //绑定事件
    $('#'+unitHuxing.prototype.config().table).closest('.x_panel').bind('click',function () {
        unitHuxing.prototype.loadDataDicList();
    })
})();