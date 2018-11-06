<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>
            房间
            <button type="button" class="btn btn-success" data-toggle="modal"
                    onclick="houseRoom.prototype.showModel()"> 新增
            </button>
        </h3>
        <div class="clearfix"></div>
    </div>
    <div>
        <table class="table table-bordered" id="HouseRoomList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script type="application/javascript">

    var houseRoom;
    (function () {
        houseRoom = function () {

        };
        houseRoom.prototype = {
            isEmpty: function (item) {
                if (item) {
                    return true;
                }
                return false;
            },
            config: function () {
                var data = {};
                data.table = "HouseRoomList";
                data.box = "divBoxHouseRoom";
                data.frm = "frmHouseRoom";
                data.tableSubclass = "SubclassHouseRoomList";
                data.boxSubclass = "SubclassDivBoxHouseRoom";
                data.boxSubclassSaveView = "boxSubclassSaveViewHouseRoom";
                data.frmSubclass = "SubclassFrmHouseRoom";
                data.type = "null";//
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'name', title: '房间名称'});
                cols.push({field: 'roomTypeName', title: '房间类型'});
                cols.push({field: 'area', title: '面积'});
                cols.push({field: 'sunshine', title: '日照'});
                cols.push({field: 'lighting', title: '采光'});
                cols.push({field: 'layerHeight', title: '层高'});
                cols.push({field: 'opening', title: '开间'});
                cols.push({field: 'depth', title: '进深'});
                cols.push({field: 'aeration', title: '通风'});
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="houseRoom.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseRoom.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="查看" onclick="houseRoom.prototype.showModelSubclass(' + row.id + ',\'tb_List\')"><i class="fa fa-search-minus fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                $("#" + houseRoom.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseRoom.prototype.config().table, "${pageContext.request.contextPath}/basicHouseRoom/getBootstrapTableVo", cols, {
                    houseId: 0
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
                    url: "${pageContext.request.contextPath}/basicHouseRoom/deleteBasicHouseRoom",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            houseRoom.prototype.loadDataDicList();
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
                $("#" + houseRoom.prototype.config().frm).clearAll();
                $('#' + houseRoom.prototype.config().box).modal("show");
                houseRoom.prototype.init({});
            },
            showModelSubclassSaveView: function () {
                var roomId = $('#' + houseRoom.prototype.config().boxSubclass).find("input[name='roomId']").val();
                houseRoom.prototype.subclassInit({roomId: roomId});
                $('#' + houseRoom.prototype.config().boxSubclassSaveView).modal("show");
            },
            showModelSubclass: function (id) {
                houseRoom.prototype.subclassLoadList(id);
                if ($('#' + houseRoom.prototype.config().boxSubclass).find("input[name='roomId']").size() > 0) {
                    $('#' + houseRoom.prototype.config().boxSubclass).find("input[name='roomId']").val(id);
                }
                $('#' + houseRoom.prototype.config().boxSubclass).modal("show");
            },
            subclassSave: function () {
                if (!$("#" + houseRoom.prototype.config().frmSubclass).valid()) {
                    return false;
                }
                var data = formParams(houseRoom.prototype.config().frmSubclass);
                $.ajax({
                    url: "${pageContext.request.contextPath}/basicHouseRoom/saveAndUpdateBasicHouseRoomDecorate",
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('保存成功');
                            $('#' + houseRoom.prototype.config().boxSubclassSaveView).modal('hide');
                            var item = result.data;
                            if (houseRoom.prototype.isEmpty(item)) {
                                houseRoom.prototype.subclassLoadList(item);
                            }
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
            subclassRemoveData: function (id) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/basicHouseRoom/deleteBasicHouseRoomDecorate",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            houseRoom.prototype.subclassLoadList(result.data);
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
            subclassInit: function (item) {
                $("#" + houseRoom.prototype.config().frmSubclass).clearAll();
                $("#" + houseRoom.prototype.config().frmSubclass).initForm(item);
                AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_decorating_material, item.material, function (html, data) {
                    $("#" + houseRoom.prototype.config().frmSubclass).find('select.material').empty().html(html).trigger('change');
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_material_price, item.materialPrice, function (html, data) {
                    $("#" + houseRoom.prototype.config().frmSubclass).find('select.materialPrice').empty().html(html).trigger('change');
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_construction_technology, item.constructionTechnology, function (html, data) {
                    $("#" + houseRoom.prototype.config().frmSubclass).find('select.constructionTechnology').empty().html(html).trigger('change');
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_decoration_part, item.part, function (html, data) {
                    $("#" + houseRoom.prototype.config().frmSubclass).find('select.part').empty().html(html).trigger('change');
                });
            },
            subclassLoadList: function (id) {
                var cols = [];
                cols.push({field: 'materialName', title: '装修材料'});
                cols.push({field: 'constructionTechnologyName', title: '施工工艺'});
                cols.push({field: 'partName', title: '房间装修部位'});
                cols.push({field: 'materialPriceName', title: '装修材料价格区间'});
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseRoom.prototype.subclassRemoveData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                $("#" + houseRoom.prototype.config().tableSubclass).bootstrapTable('destroy');
                TableInit(houseRoom.prototype.config().tableSubclass, "${pageContext.request.contextPath}/basicHouseRoom/getRoomDecorateBootstrapTableVo", cols, {
                    roomId: id,
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
            },
            saveData: function () {
                if (!$("#" + houseRoom.prototype.config().frm).valid()) {
                    return false;
                }
                var data = formParams(houseRoom.prototype.config().frm);
                data.houseId = "0" ;
                $.ajax({
                    url: "${pageContext.request.contextPath}/basicHouseRoom/saveAndUpdateBasicHouseRoom",
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('保存成功');
                            $('#' + houseRoom.prototype.config().box).modal('hide');
                            houseRoom.prototype.loadDataDicList();
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
                    url: "${pageContext.request.contextPath}/basicHouseRoom/getBasicHouseRoomById",
                    type: "get",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            houseRoom.prototype.init(result.data);
                            $('#' + houseRoom.prototype.config().box).modal("show");
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            init: function (item) {
                $("#" + houseRoom.prototype.config().frm).clearAll();
                $("#" + houseRoom.prototype.config().frm).initForm(item);
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseType, item.roomType, function (html, data) {
                    $("#" + houseRoom.prototype.config().frm).find('select.roomType').empty().html(html).trigger('change');
                });
            }
        }
    })();

</script>

<div id="divBoxHouseRoom" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">房间</h3>
            </div>
            <form id="frmHouseRoom" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            房间名称
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="房间名称" name="name" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            面积
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="面积" name="area" data-rule-number='true'
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            通风
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="通风" name="aeration" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            日照
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="日照" name="sunshine" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            采光
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="采光" name="lighting" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            层高
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="层高" name="layerHeight" class="form-control"
                                            >
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            开间
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="开间" name="opening" class="form-control"
                                            >
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            进深
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="进深" name="depth" class="form-control"
                                            >
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            隔音
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="隔音" name="soundInsulation"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            房间类型
                                        </label>
                                        <div class="col-sm-4">
                                            <select required="required" name="roomType"
                                                    class="form-control search-select select2 roomType">
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
                    <button type="button" class="btn btn-primary" onclick="houseRoom.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="SubclassDivBoxHouseRoom" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="titleContent">房间装修数据</h3>
                <input type="hidden" name="roomId" class="roomId">
            </div>
            <div class="panel-body">
                <span id="toolbarSub">
                    <button type="button" class="btn btn-success"
                            onclick="houseRoom.prototype.showModelSubclassSaveView()"
                            data-toggle="modal" href="#divSubDataDicManage"> 新增
                    </button>
                </span>
                <table class="table table-bordered" id="SubclassHouseRoomList">
                </table>
            </div>
        </div>
    </div>
</div>

<div id="boxSubclassSaveViewHouseRoom" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">房间装修</h3>
            </div>
            <form id="SubclassFrmHouseRoom" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="roomId" class="roomId">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修部位
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="part"
                                                    class="form-control search-select select2 part">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修材料
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="material"
                                                    class="form-control search-select select2 material">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修材料价格区间
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="materialPrice"
                                                    class="form-control search-select select2 materialPrice">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            施工工艺
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="constructionTechnology"
                                                    class="form-control search-select select2 constructionTechnology">
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
                    <button type="button" class="btn btn-primary" onclick="houseRoom.prototype.subclassSave()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>