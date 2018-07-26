<%--
 房间
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <!-- 包含此文件时需要删除掉css -->
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body>
<div class="x_content">
    <div class="x_title">
        <h3>房间信息 <button type="button" class="btn btn-success" onclick="houseRoom.prototype.showModel()"
                           data-toggle="modal" href="#divBox"> 新增
        </button></h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal">
        <div class="form-group">
            <div class="x-valid">
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <table class="table table-bordered" id="HouseRoomList">
                    <!-- cerare document add ajax data-->
                </table>
            </div>
        </div>
    </form>
</div>
</body>


<%--<%@include file="/views/share/main_footer.jsp" %>--%>
<script type="application/javascript">

    var houseRoom = function () {

    };
    houseRoom.prototype = {
        config:function () {
            var data = {};
            data.table = "HouseRoomList" ;
            data.box = "divBoxHouseRoom";
            data.frm = "frmHouseRoom";
            data.tableSubclass = "SubclassHouseRoomList" ;
            data.boxSubclass = "SubclassDivBoxHouseRoom";
            data.boxSubclassSaveView = "boxSubclassSaveViewHouseRoom";
            data.frmSubclass = "SubclassFrmHouseRoom";
            data.type = "null" ;//
            return data;
        },
        loadDataDicList:function () {
            var cols = [];
            cols.push({field: 'name', title: '房间名称'});
            cols.push({field: 'roomTypeName', title: '房间类型'});
            cols.push({field: 'area', title: '面积'});
            cols.push({field: 'orientation', title: '朝向'});
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
            $("#"+houseRoom.prototype.config().table).bootstrapTable('destroy');
            TableInit(houseRoom.prototype.config().table, "${pageContext.request.contextPath}/examineHouseRoom/getExamineHouseRoomList", cols, {
                type:houseRoom.prototype.config().type
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData:function (id) {
            $.ajax({
                url:"${pageContext.request.contextPath}/examineHouseRoom/deleteExamineHouseRoomById",
                type: "post",
                dataType: "json",
                data: {id:id},
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
        showModel:function () {
            $("#"+houseRoom.prototype.config().frm).clearAll();
            $("#"+houseRoom.prototype.config().frm+" .type").val(houseRoom.prototype.config().type);
            $('#'+houseRoom.prototype.config().box).modal("show");
        },
        showModelSubclassSaveView:function () {
            houseRoom.prototype.subclassInit();
            if ($('#'+houseRoom.prototype.config().boxSubclass+" .roomId").size() > 0){
                var roomId = $('#'+houseRoom.prototype.config().boxSubclass+" .roomId").val();
                $("#"+houseRoom.prototype.config().frmSubclass).clearAll();
                $("#"+houseRoom.prototype.config().frmSubclass+" .roomId").val(roomId);
            }else {
                $("#"+houseRoom.prototype.config().frmSubclass).clearAll();
            }
            $('#'+houseRoom.prototype.config().boxSubclassSaveView).modal("show");
        },
        showModelSubclass:function (id) {
            houseRoom.prototype.subclassLoadList();
            if ($('#'+houseRoom.prototype.config().boxSubclass+" .roomId").size() > 0){
                $('#'+houseRoom.prototype.config().boxSubclass+" .roomId").val(id);
            }
            $('#'+houseRoom.prototype.config().boxSubclass).modal("show");
        },
        subclassSave:function () {
            if (!$("#"+houseRoom.prototype.config().frmSubclass).valid()){
                return false;
            }
            var data = formParams(houseRoom.prototype.config().frmSubclass);
            if ($("#declareId").size() > 0){
                data.declareId = $("#declareId").val();
            }
            if ($("#examineType").size() > 0){
                data.examineType = $("#examineType").val();
            }
            $.ajax({
                url:"${pageContext.request.contextPath}/examineHouseRoom/saveAndUpdateExamineHouseRoomDecorate",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#'+houseRoom.prototype.config().boxSubclassSaveView).modal('hide');
                        houseRoom.prototype.subclassLoadList();
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
        subclassRemoveData:function (id) {
            $.ajax({
                url:"${pageContext.request.contextPath}/examineHouseRoom/deleteExamineHouseRoomDecorateById",
                type: "post",
                dataType: "json",
                data: {id:id},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('删除成功');
                        houseRoom.prototype.subclassLoadList();
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
        subclassInit:function () {
            $.ajax({
                url:"${pageContext.request.contextPath}/examineHouseRoom/examine_building_decorating_material",
                type: "get",
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        var gradeNum = data.length;
                        var option = "<option value=''>请选择</option>";
                        if(gradeNum > 0){
                            for(var i = 0;i< gradeNum;i++){
                                option += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
                            }
                            $("#"+houseRoom.prototype.config().frmSubclass+" .material").html(option);
                            $("#"+houseRoom.prototype.config().frmSubclass+" .material").select2({ minimumResultsForSearch: -1 });//加载样式
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
            $.ajax({
                url:"${pageContext.request.contextPath}/examineHouseRoom/examine_building_material_price",
                type: "get",
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        var gradeNum = data.length;
                        var option = "<option value=''>请选择</option>";
                        if(gradeNum > 0){
                            for(var i = 0;i< gradeNum;i++){
                                option += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
                            }
                            $("#"+houseRoom.prototype.config().frmSubclass+" .materialPrice").html(option);
                            $("#"+houseRoom.prototype.config().frmSubclass+" .materialPrice").select2({ minimumResultsForSearch: -1 });//加载样式
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
            $.ajax({
                url:"${pageContext.request.contextPath}/examineHouseRoom/examine_building_construction_technology",
                type: "get",
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        var gradeNum = data.length;
                        var option = "<option value=''>请选择</option>";
                        if(gradeNum > 0){
                            for(var i = 0;i< gradeNum;i++){
                                option += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
                            }
                            $("#"+houseRoom.prototype.config().frmSubclass+" .constructionTechnology").html(option);
                            $("#"+houseRoom.prototype.config().frmSubclass+" .constructionTechnology").select2({ minimumResultsForSearch: -1 });//加载样式
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
            $.ajax({
                url:"${pageContext.request.contextPath}/examineHouseRoom/examine_building_decoration_part",
                type: "get",
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        var gradeNum = data.length;
                        var option = "<option value=''>请选择</option>";
                        if(gradeNum > 0){
                            for(var i = 0;i< gradeNum;i++){
                                option += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
                            }
                            $("#"+houseRoom.prototype.config().frmSubclass+" .part").html(option);
                            $("#"+houseRoom.prototype.config().frmSubclass+" .part").select2({ minimumResultsForSearch: -1 });//加载样式
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        },
        subclassLoadList:function () {
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
            $("#"+houseRoom.prototype.config().tableSubclass).bootstrapTable('destroy');
            TableInit(houseRoom.prototype.config().tableSubclass, "${pageContext.request.contextPath}/examineHouseRoom/getExamineHouseRoomDecorateLists", cols, {
                type:null
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        saveData:function () {
            if (!$("#"+houseRoom.prototype.config().frm).valid()){
                return false;
            }
            var data = formParams(houseRoom.prototype.config().frm);
            if ($("#declareId").size() > 0){
                data.declareId = $("#declareId").val();
            }
            if ($("#examineType").size() > 0){
                data.examineType = $("#examineType").val();
            }
            $.ajax({
                url:"${pageContext.request.contextPath}/examineHouseRoom/saveAndUpdateExamineHouseRoom",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#'+houseRoom.prototype.config().box).modal('hide');
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
        getAndInit:function (id) {
            $.ajax({
                url:"${pageContext.request.contextPath}/examineHouseRoom/getExamineHouseRoomById",
                type: "get",
                dataType: "json",
                data: {id:id},
                success: function (result) {
                    if (result.ret) {
                        $("#"+houseRoom.prototype.config().frm).clearAll();
                        $("#" + houseRoom.prototype.config().frm).initForm(result.data);
                        if (result.data.roomType == null || result.data.roomType == ''){
                            $("#"+houseRoom.prototype.config().frm+" .roomType").val(null).trigger("change");
                        }else {
                            $("#"+houseRoom.prototype.config().frm+" .roomType").val(result.data.roomType).trigger("change");
                        }
                        $('#'+houseRoom.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init:function () {
            $.ajax({
                url:"${pageContext.request.contextPath}/examineHouseRoom/examine_unit_house_type",
                type: "get",
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        var gradeNum = data.length;
                        var option = "<option value=''>请选择</option>";
                        if(gradeNum > 0){
                            for(var i = 0;i< gradeNum;i++){
                                option += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
                            }
                            $("#"+houseRoom.prototype.config().frm+" .roomType").html(option);
                            $("#"+houseRoom.prototype.config().frm+" .roomType").select2({ minimumResultsForSearch: -1 });//加载样式
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })



        }
    }
    /**
     * 初始化
     */
    $(function () {
        houseRoom.prototype.loadDataDicList();
        houseRoom.prototype.init();
    })

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
                <input type="hidden"  name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            房间名称
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="房间名称" name="name" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            面积
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="面积" name="area" data-rule-number='true' class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            朝向
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="朝向" name="orientation" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            通风
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="通风" name="aeration" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            光照
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="光照" name="illumination" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            隔音
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="隔音" name="soundInsulation" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            房间类型
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="roomType" class="form-control search-select select2 roomType">
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

<div id="SubclassDivBoxHouseRoom" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
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
                    <button type="button" class="btn btn-success" onclick="houseRoom.prototype.showModelSubclassSaveView()"
                            data-toggle="modal" href="#divSubDataDicManage"> 新增
                    </button>
                </span>
                <table class="table table-bordered" id="SubclassHouseRoomList">
                </table>
            </div>
        </div>
    </div>
</div>

<div id="boxSubclassSaveViewHouseRoom" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">房间装修</h3>
            </div>
            <form id="SubclassFrmHouseRoom" class="form-horizontal">
                <input type="hidden"  name="id">
                <input type="hidden"  name="roomId" class="roomId">
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
                                            <select required="required" name="part" class="form-control search-select select2 part">
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
                                            <select required="required" name="material" class="form-control search-select select2 material">
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
                                            <select required="required" name="materialPrice" class="form-control search-select select2 materialPrice">
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
                                            <select required="required" name="constructionTechnology" class="form-control search-select select2 constructionTechnology">
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

</html>

