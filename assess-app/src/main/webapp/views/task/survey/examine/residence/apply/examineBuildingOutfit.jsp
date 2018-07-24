<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/7/24
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%--
 楼栋外装情况
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
        <h3>楼栋外装情况信息 <button type="button" class="btn btn-success" onclick="examineBuildingOutfit.prototype.showModel()"
                           data-toggle="modal" href="#divBox"> 新增
        </button></h3>
        <div class="clearfix"></div>
    </div>
    <form id="frm_examineBuildingOutfit" class="form-horizontal">
        <div class="form-group">
            <div class="x-valid">
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <table class="table table-bordered" id="ExamineBuildingOutfitList">
                    <!-- cerare document add ajax data-->
                </table>
            </div>
        </div>
    </form>
</div>
</body>


<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">

    var examineBuildingOutfit = function () {

    };
    examineBuildingOutfit.prototype = {
        config:function () {
            var data = {};
            data.table = "ExamineBuildingOutfitList" ;
            data.box = "divBoxExamineBuildingOutfit";
            data.frm = "frmExamineBuildingOutfit";
            data.type = "null" ;//
            return data;
        },
        loadDataDicList:function () {
            var cols = [];
            cols.push({field: 'decorationPartName', title: '装修部位'});
            cols.push({field: 'decoratingMaterialName', title: '装修材料'});
            cols.push({field: 'materialPriceName', title: '材料价格区间'});
            cols.push({field: 'constructionTechnologyName', title: '施工工艺'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="examineBuildingOutfit.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="examineBuildingOutfit.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#"+examineBuildingOutfit.prototype.config().table).bootstrapTable('destroy');
            TableInit(examineBuildingOutfit.prototype.config().table, "${pageContext.request.contextPath}/examineBuildingOutfit/getExamineBuildingOutfitList", cols, {
                type:examineBuildingOutfit.prototype.config().type
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
                url:"${pageContext.request.contextPath}/examineBuildingOutfit/deleteExamineBuildingOutfitById",
                type: "post",
                dataType: "json",
                data: {id:id},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('删除成功');
                        examineBuildingOutfit.prototype.loadDataDicList();
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
            $("#"+examineBuildingOutfit.prototype.config().frm).clearAll();
            $("#"+examineBuildingOutfit.prototype.config().frm+" .type").val(examineBuildingOutfit.prototype.config().type);
            $('#'+examineBuildingOutfit.prototype.config().box).modal("show");
        },
        saveData:function () {
            if (!$("#"+examineBuildingOutfit.prototype.config().frm).valid()){
                return false;
            }
            var data = formParams(examineBuildingOutfit.prototype.config().frm);
            if ($("#declareId").size() > 0){
                data.declareId = $("#declareId").val();
            }
            if ($("#examineType").size() > 0){
                data.examineType = $("#examineType").val();
            }
            $.ajax({
                url:"${pageContext.request.contextPath}/examineBuildingOutfit/saveAndUpdateExamineBuildingOutfit",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#'+examineBuildingOutfit.prototype.config().box).modal('hide');
                        examineBuildingOutfit.prototype.loadDataDicList();
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
                url:"${pageContext.request.contextPath}/examineBuildingOutfit/getExamineBuildingOutfitById",
                type: "get",
                dataType: "json",
                data: {id:id},
                success: function (result) {
                    if (result.ret) {
                        $("#"+examineBuildingOutfit.prototype.config().frm).clearAll();
                        $("#" + examineBuildingOutfit.prototype.config().frm).initForm(result.data);
                        if (result.data.decoratingMaterial == null || result.data.decoratingMaterial == ''){
                            $("#"+examineBuildingOutfit.prototype.config().frm+" .decoratingMaterial").val(null).trigger("change");
                        }else {
                            $("#"+examineBuildingOutfit.prototype.config().frm+" .decoratingMaterial").val(result.data.decoratingMaterial).trigger("change");
                        }
                        if (result.data.materialPrice == null || result.data.materialPrice == ''){
                            $("#"+examineBuildingOutfit.prototype.config().frm+" .materialPrice").val(null).trigger("change");
                        }else {
                            $("#"+examineBuildingOutfit.prototype.config().frm+" .materialPrice").val(result.data.materialPrice).trigger("change");
                        }
                        if (result.data.constructionTechnology == null || result.data.constructionTechnology == ''){
                            $("#"+examineBuildingOutfit.prototype.config().frm+" .constructionTechnology").val(null).trigger("change");
                        }else {
                            $("#"+examineBuildingOutfit.prototype.config().frm+" .constructionTechnology").val(result.data.constructionTechnology).trigger("change");
                        }
                        if (result.data.decorationPart == null || result.data.decorationPart == ''){
                            $("#"+examineBuildingOutfit.prototype.config().frm+" .decorationPart").val(null).trigger("change");
                        }else {
                            $("#"+examineBuildingOutfit.prototype.config().frm+" .decorationPart").val(result.data.decorationPart).trigger("change");
                        }
                        $('#'+examineBuildingOutfit.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init:function () {
            $.ajax({
                url:"${pageContext.request.contextPath}/examineBuildingOutfit/examine_building_decorating_material",
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
                            $("#"+examineBuildingOutfit.prototype.config().frm+" .decoratingMaterial").html(option);
                            $("#"+examineBuildingOutfit.prototype.config().frm+" .decoratingMaterial").select2({ minimumResultsForSearch: -1 });//加载样式
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
            $.ajax({
                url:"${pageContext.request.contextPath}/examineBuildingOutfit/examine_building_material_price",
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
                            $("#"+examineBuildingOutfit.prototype.config().frm+" .materialPrice").html(option);
                            $("#"+examineBuildingOutfit.prototype.config().frm+" .materialPrice").select2({ minimumResultsForSearch: -1 });//加载样式
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
            $.ajax({
                url:"${pageContext.request.contextPath}/examineBuildingOutfit/examine_building_construction_technology",
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
                            $("#"+examineBuildingOutfit.prototype.config().frm+" .constructionTechnology").html(option);
                            $("#"+examineBuildingOutfit.prototype.config().frm+" .constructionTechnology").select2({ minimumResultsForSearch: -1 });//加载样式
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
            $.ajax({
                url:"${pageContext.request.contextPath}/examineBuildingOutfit/examine_building_decoration_part",
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
                            $("#"+examineBuildingOutfit.prototype.config().frm+" .decorationPart").html(option);
                            $("#"+examineBuildingOutfit.prototype.config().frm+" .decorationPart").select2({ minimumResultsForSearch: -1 });//加载样式
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
        examineBuildingOutfit.prototype.loadDataDicList();
        examineBuildingOutfit.prototype.init();
    })

</script>

<div id="divBoxExamineBuildingOutfit" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">楼栋外装情况</h3>
            </div>
            <form id="frmExamineBuildingOutfit" class="form-horizontal">
                <input type="hidden"  name="id">
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
                                            <select required="required" name="decorationPart" class="form-control search-select select2 decorationPart">
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

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            材料价格区间
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
                                            装修材料
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="decoratingMaterial" class="form-control search-select select2 decoratingMaterial">
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
                    <button type="button" class="btn btn-primary" onclick="examineBuildingOutfit.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>