<%--
 供暖情况
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
        <h3>供暖情况信息 <button type="button" class="btn btn-success" onclick="houseHeating.prototype.showModel()"
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
                <table class="table table-bordered" id="HouseHeatingList">
                    <!-- cerare document add ajax data-->
                </table>
            </div>
        </div>
    </form>
</div>
</body>


<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">

    var houseHeating = function () {

    };
    houseHeating.prototype = {
        config:function () {
            var data = {};
            data.table = "HouseHeatingList" ;
            data.box = "divBoxHouseHeating";
            data.frm = "frmHouseHeating";
            data.type = "houseHeating" ;//根据 ExamineHouseEquipmentTypeEnum 配置
            return data;
        },
        loadDataDicList:function () {
            var cols = [];
            cols.push({field: 'equipment', title: '名称'});
            cols.push({field: 'categoryName', title: '类别'});
            cols.push({field: 'equipmentPriceName', title: '设备价格区间'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="houseHeating.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseHeating.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#"+houseHeating.prototype.config().table).bootstrapTable('destroy');
            TableInit(houseHeating.prototype.config().table, "${pageContext.request.contextPath}/examineHouseEquipment/getExamineHouseEquipmentList", cols, {
                type:houseHeating.prototype.config().type
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
                url:"${pageContext.request.contextPath}/examineHouseEquipment/deleteExamineHouseEquipmentById",
                type: "post",
                dataType: "json",
                data: {id:id},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('删除成功');
                        houseHeating.prototype.loadDataDicList();
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
            $("#"+houseHeating.prototype.config().frm).clearAll();
            $('#'+houseHeating.prototype.config().box).modal("show");
        },
        saveData:function () {
            if (!$("#"+houseHeating.prototype.config().frm).valid()){
                return false;
            }
            var data = formParams(houseHeating.prototype.config().frm);
            data.type = houseHeating.prototype.config().type;
            if ($("#declareId").size() > 0){
                data.declareId = $("#declareId").val();
            }
            if ($("#examineType").size() > 0){
                data.examineType = $("#examineType").val();
            }
            $.ajax({
                url:"${pageContext.request.contextPath}/examineHouseEquipment/saveAndUpdateExamineHouseEquipment",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#'+houseHeating.prototype.config().box).modal('hide');
                        houseHeating.prototype.loadDataDicList();
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
                url:"${pageContext.request.contextPath}/examineHouseEquipment/getExamineHouseEquipmentById",
                type: "get",
                dataType: "json",
                data: {id:id},
                success: function (result) {
                    if (result.ret) {
                        $("#"+houseHeating.prototype.config().frm).clearAll();
                        $("#" + houseHeating.prototype.config().frm).initForm(result.data);
                        if (result.data.equipmentPrice == null || result.data.equipmentPrice == ''){
                            $("#"+houseHeating.prototype.config().frm+" .equipmentPrice").val(null).trigger("change");
                        }else {
                            $("#"+houseHeating.prototype.config().frm+" .equipmentPrice").val(result.data.equipmentPrice).trigger("change");
                        }
                        if (result.data.category == null || result.data.category == ''){
                            $("#"+houseHeating.prototype.config().frm+" .category").val(null).trigger("change");
                        }else {
                            $("#"+houseHeating.prototype.config().frm+" .category").val(result.data.category).trigger("change");
                        }
                        $('#'+houseHeating.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init:function () {
            $.ajax({
                url:"${pageContext.request.contextPath}/examineHouseEquipment/examine_house_equipment_price_range",
                type: "get",
                data:{type:houseHeating.prototype.config().type},
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
                            $("#"+houseHeating.prototype.config().frm+" .equipmentPrice").html(option);
                            $("#"+houseHeating.prototype.config().frm+" .equipmentPrice").select2({ minimumResultsForSearch: -1 });//加载样式
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
            $.ajax({
                url:"${pageContext.request.contextPath}/examineHouseEquipment/examineHouseEquipment_grade",
                type: "get",
                data:{type:houseHeating.prototype.config().type},
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
                            $("#"+houseHeating.prototype.config().frm+" .category").html(option);
                            $("#"+houseHeating.prototype.config().frm+" .category").select2({ minimumResultsForSearch: -1 });//加载样式
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
        houseHeating.prototype.loadDataDicList();
        houseHeating.prototype.init();
    })

</script>

<div id="divBoxHouseHeating" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">供暖情况</h3>
            </div>
            <form id="frmHouseHeating" class="form-horizontal">
                <input type="hidden"  name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            设备名称
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="equipment" placeholder="设备名称" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            类别
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="category" class="form-control search-select select2 category">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            设备价格区间
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="equipmentPrice" class="form-control search-select select2 equipmentPrice">
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
                    <button type="button" class="btn btn-primary" onclick="houseHeating.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>





