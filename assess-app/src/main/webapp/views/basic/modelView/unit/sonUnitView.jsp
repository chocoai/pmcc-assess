<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <h3>室内公共装修
        <button type="button" class="btn btn-success" onclick="unitDecorate.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
    </h3>
    <div>
        <table class="table table-bordered" id="ExamineUnitDecorateList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <h3>
        户型
        <button type="button" class="btn btn-success" onclick="unitHuxing.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
    </h3>
    <div>
        <table class="table table-bordered" id="UnitHuxingList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <h3>配备电梯
        <button type="button" class="btn btn-success" onclick="unitElevator.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
    </h3>
    <div>
        <table class="table table-bordered" id="ExamineUnitElevatorList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script>
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
                var unitId = null;
                try {
                    var basicUnit = formParams(objectData.config.basicUnit.frm);
                    unitId = unitDecorate.prototype.isNotBlank(basicUnit.id) ? basicUnit.id : "0";
                } catch (e) {
                    console.error(e);
                    console.log("函数失效!");
                }
                $("#" + unitDecorate.prototype.config().table).bootstrapTable('destroy');
                TableInit(unitDecorate.prototype.config().table, "${pageContext.request.contextPath}/basicUnitDecorate/getBootstrapTableVo", cols, {
                    unitId: unitId
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
                    url: "${pageContext.request.contextPath}/basicUnitDecorate/deleteBasicUnitDecorate",
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
                try {
                    var basicUnit = formParams(objectData.config.basicUnit.frm);
                    data.unitId = unitDecorate.prototype.isNotBlank(basicUnit.id) ? basicUnit.id : "0";
                } catch (e) {
                    console.error(e);
                    console.log("函数失效!");
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/basicUnitDecorate/saveAndUpdateBasicUnitDecorate",
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
                    url: "${pageContext.request.contextPath}/basicUnitDecorate/getBasicUnitDecorateById",
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
                cols.push({field: 'orientation', title: '朝向'});
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
                var unitId = null;
                try {
                    var basicUnit = formParams(objectData.config.basicUnit.frm);
                    unitId = unitHuxing.prototype.isNotNull(basicUnit.id) ? basicUnit.id : "0";
                } catch (e) {
                    console.error(e);
                    console.log("函数失效!");
                }
                $("#" + unitHuxing.prototype.config().table).bootstrapTable('destroy');
                TableInit(unitHuxing.prototype.config().table, "${pageContext.request.contextPath}/basicUnitHuxing/getBootstrapTableVo", cols, {
                    unitId: unitId
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
                    url: "${pageContext.request.contextPath}/basicUnitHuxing/deleteBasicUnitHuxing",
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
                try {
                    var basicUnit = formParams(objectData.config.basicUnit.frm);
                    data.unitId = unitHuxing.prototype.isNotNull(basicUnit.id) ? basicUnit.id : "0";
                } catch (e) {
                    console.error(e);
                    console.log("函数失效!");
                }
                data.houseCategory = unitHuxing.prototype.rule("get",data);
                $.ajax({
                    url: "${pageContext.request.contextPath}/basicUnitHuxing/saveAndUpdateBasicUnitHuxing",
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
                    url: "${pageContext.request.contextPath}/basicUnitHuxing/getBasicUnitHuxingById",
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
                objectData.showFile(unitHuxing.prototype.config().unitHuxingFileIDFildName,AssessDBKey.BasicUnitHuxing,item.id);
                objectData.uploadFile(unitHuxing.prototype.config().unitHuxingFileIDFildName,AssessDBKey.BasicUnitHuxing,item.id);
                if (unitHuxing.prototype.isNotNull(item.houseCategory)){
                    unitHuxing.prototype.rule("set",JSON.parse(item.houseCategory));
                }
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseHouse_layout, item.houseLayout, function (html, data) {
                    $("#" + unitHuxing.prototype.config().frm).find('select.houseLayout').empty().html(html).trigger('change');
                });
            }
        }
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
                var unitId = null;
                try {
                    var basicUnit = formParams(objectData.config.basicUnit.frm);
                    unitId = unitElevator.prototype.isNotNull(basicUnit.id) ? basicUnit.id : "0";
                } catch (e) {
                    console.error(e);
                    console.log("函数失效!");
                }
                $("#" + unitElevator.prototype.config().table).bootstrapTable('destroy');
                TableInit(unitElevator.prototype.config().table, "${pageContext.request.contextPath}/basicUnitElevator/getBootstrapTableVo", cols, {
                    unitId: unitId
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
                    url: "${pageContext.request.contextPath}/basicUnitElevator/deleteBasicUnitElevator",
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
                try {
                    var basicUnit = formParams(objectData.config.basicUnit.frm);
                    data.unitId = unitElevator.prototype.isNotNull(basicUnit.id) ? basicUnit.id : "0";
                } catch (e) {
                    console.error(e);
                    console.log("函数失效!");
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/basicUnitElevator/saveAndUpdateBasicUnitElevator",
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
                    url: "${pageContext.request.contextPath}/basicUnitElevator/getBasicUnitElevatorById",
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
            }
        }

    })();

    $(function () {
        unitDecorate.prototype.loadDataDicList();
        unitHuxing.prototype.loadDataDicList();
        unitElevator.prototype.loadDataDicList();
    });
</script>

<div id="divBoxExamineUnitDecorate" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">楼栋内装情况</h3>
            </div>
            <form id="frmExamineUnitDecorate" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修部位<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="decorationPart"
                                                    class="form-control search-select select2 decorationPart">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            施工工艺<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="constructionTechnology"
                                                    class="form-control search-select select2 constructionTechnology">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            材料价格区间<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="materialPriceRange"
                                                    class="form-control search-select select2 materialPriceRange">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修材料<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="decoratingMaterial"
                                                    class="form-control search-select select2 decoratingMaterial">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="unitDecorate.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxUnitHuxing" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">户型</h3>
            </div>
            <form id="frmUnitHuxing" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            面积<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="面积(数字)" data-rule-number='true' name="area"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            跨长<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="跨长(数字)" data-rule-number='true'
                                                   name="spanLength" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            户型描述<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea placeholder="户型描述" name="description"
                                                      class="form-control" required="required">

                                            </textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            跨数<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="跨数(数字)" data-rule-number='true'
                                                   name="spanNumber" class="form-control" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            跨宽<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="跨宽(数字)" data-rule-number='true'
                                                   name="spanWidth" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            户型 (房) <span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <div class="input-group">
                                                <span class="input-group-addon">卧室</span>
                                                <input type="text" name="house" data-rule-number='true' class="form-control"
                                                       required="required">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            户型 (厅)
                                        </label>
                                        <div class="col-sm-4">
                                            <div class="input-group">
                                                <span class="input-group-addon">客厅</span>
                                                <input type="text" name="saloon" data-rule-number='true' class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            户型 (厨)
                                        </label>
                                        <div class="col-sm-4">
                                            <div class="input-group">
                                                <span class="input-group-addon">厨房</span>
                                                <input type="text" name="kitchen" data-rule-number='true' class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            户型 (卫) <span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <div class="input-group">
                                                <span class="input-group-addon">卫生间</span>
                                                <input type="text" name="toilet" data-rule-number='true' class="form-control"
                                                       required="required">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            户型 (花园)
                                        </label>
                                        <div class="col-sm-4">
                                            <div class="input-group">
                                                <span class="input-group-addon">花园</span>
                                                <input type="text" name="garden" data-rule-number='true' class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            户型 (阳台)
                                        </label>
                                        <div class="col-sm-4">
                                            <div class="input-group">
                                                <span class="input-group-addon">阳台</span>
                                                <input type="text" name="balcony" data-rule-number='true' class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            朝向<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="朝向"
                                                   name="orientation" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            户型图<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input id="house_latest_family_planV" name="house_latest_family_planV"
                                                   required="required" placeholder="上传附件" class="form-control"
                                                   type="file">
                                            <div id="_house_latest_family_planV"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="unitHuxing.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxExamineUnitElevator" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">配备电梯</h3>
            </div>
            <form id="frmExamineUnitElevator" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            电梯维护情况<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="maintenance"
                                                   placeholder="电梯维护情况" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            电梯类型<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="type"
                                                   placeholder="电梯类型" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            电梯品牌<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="brand"
                                                   placeholder="电梯品牌" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            电梯数量<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="电梯数量(数字)" data-rule-number='true'
                                                   name="number" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            准载人数<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="准载人数(数字)" data-rule-number='true'
                                                   name="quasiLoadNumber" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            准载重量<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="准载重量(数字)" data-rule-number='true'
                                                   name="quasiLoadWeight" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            运行速度<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="runningSpeed"
                                                   placeholder="运行速度" required="required">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="unitElevator.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
