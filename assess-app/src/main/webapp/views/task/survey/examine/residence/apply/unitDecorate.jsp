<%--
 楼栋内装
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
        <h3>楼栋内装情况信息 <button type="button" class="btn btn-success" onclick="unitDecorate.prototype.showModel()"
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
                <table class="table table-bordered" id="ExamineUnitDecorateList">
                    <!-- cerare document add ajax data-->
                </table>
            </div>
        </div>
    </form>
</div>
</body>


<%--<%@include file="/views/share/main_footer.jsp" %>--%>
<script type="application/javascript">

    var unitDecorate = function () {

    };
    unitDecorate.prototype = {
        config:function () {
            var data = {};
            data.table = "ExamineUnitDecorateList" ;
            data.box = "divBoxExamineUnitDecorate";
            data.frm = "frmExamineUnitDecorate";
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
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="unitDecorate.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="unitDecorate.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#"+unitDecorate.prototype.config().table).bootstrapTable('destroy');
            TableInit(unitDecorate.prototype.config().table, "${pageContext.request.contextPath}/examineUnitDecorate/getExamineUnitDecorateList", cols, {
                type:unitDecorate.prototype.config().type
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
                url:"${pageContext.request.contextPath}/examineUnitDecorate/deleteExamineUnitDecorateById",
                type: "post",
                dataType: "json",
                data: {id:id},
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
        showModel:function () {
            $("#"+unitDecorate.prototype.config().frm).clearAll();
            $("#"+unitDecorate.prototype.config().frm+" .type").val(unitDecorate.prototype.config().type);
            $('#'+unitDecorate.prototype.config().box).modal("show");
        },
        saveData:function () {
            if (!$("#"+unitDecorate.prototype.config().frm).valid()){
                return false;
            }
            var data = formParams(unitDecorate.prototype.config().frm);
            if ($("#declareId").size() > 0){
                data.declareId = $("#declareId").val();
            }
            if ($("#examineType").size() > 0){
                data.examineType = $("#examineType").val();
            }
            $.ajax({
                url:"${pageContext.request.contextPath}/examineUnitDecorate/saveAndUpdateExamineUnitDecorate",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#'+unitDecorate.prototype.config().box).modal('hide');
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
        getAndInit:function (id) {
            $.ajax({
                url:"${pageContext.request.contextPath}/examineUnitDecorate/getExamineUnitDecorateById",
                type: "get",
                dataType: "json",
                data: {id:id},
                success: function (result) {
                    if (result.ret) {
                        $("#"+unitDecorate.prototype.config().frm).clearAll();
                        $("#" + unitDecorate.prototype.config().frm).initForm(result.data);
                        //decoratingMaterial
                        if (result.data.decoratingMaterial == null || result.data.decoratingMaterial == ''){
                            $("#"+unitDecorate.prototype.config().frm+" .decoratingMaterial").val(null).trigger("change");
                        }else {
                            $("#"+unitDecorate.prototype.config().frm+" .decoratingMaterial").val(result.data.decoratingMaterial).trigger("change");
                        }
                        if (result.data.materialPriceRange == null || result.data.materialPriceRange == ''){
                            $("#"+unitDecorate.prototype.config().frm+" .materialPriceRange").val(null).trigger("change");
                        }else {
                            $("#"+unitDecorate.prototype.config().frm+" .materialPriceRange").val(result.data.materialPriceRange).trigger("change");
                        }//constructionTechnology
                        if (result.data.constructionTechnology == null || result.data.constructionTechnology == ''){
                            $("#"+unitDecorate.prototype.config().frm+" .constructionTechnology").val(null).trigger("change");
                        }else {
                            $("#"+unitDecorate.prototype.config().frm+" .constructionTechnology").val(result.data.constructionTechnology).trigger("change");
                        }
                        if (result.data.decorationPart == null || result.data.decorationPart == ''){
                            $("#"+unitDecorate.prototype.config().frm+" .decorationPart").val(null).trigger("change");
                        }else {
                            $("#"+unitDecorate.prototype.config().frm+" .decorationPart").val(result.data.decorationPart).trigger("change");
                        }
                        $('#'+unitDecorate.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init:function () {
            $.ajax({
                url:"${pageContext.request.contextPath}/examineUnitDecorate/examine_building_decorating_material",
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
                            $("#"+unitDecorate.prototype.config().frm+" .decoratingMaterial").html(option);
                            $("#"+unitDecorate.prototype.config().frm+" .decoratingMaterial").select2({ minimumResultsForSearch: -1 });//加载样式
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
            $.ajax({
                url:"${pageContext.request.contextPath}/examineUnitDecorate/examine_building_material_price",
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
                            $("#"+unitDecorate.prototype.config().frm+" .materialPriceRange").html(option);
                            $("#"+unitDecorate.prototype.config().frm+" .materialPriceRange").select2({ minimumResultsForSearch: -1 });//加载样式
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
            $.ajax({
                url:"${pageContext.request.contextPath}/examineUnitDecorate/examine_building_construction_technology",
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
                            $("#"+unitDecorate.prototype.config().frm+" .constructionTechnology").html(option);
                            $("#"+unitDecorate.prototype.config().frm+" .constructionTechnology").select2({ minimumResultsForSearch: -1 });//加载样式
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
            $.ajax({
                url:"${pageContext.request.contextPath}/examineUnitDecorate/examine_building_decoration_part",
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
                            $("#"+unitDecorate.prototype.config().frm+" .decorationPart").html(option);
                            $("#"+unitDecorate.prototype.config().frm+" .decorationPart").select2({ minimumResultsForSearch: -1 });//加载样式
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
        unitDecorate.prototype.loadDataDicList();
        unitDecorate.prototype.init();
    })

</script>

<div id="divBoxExamineUnitDecorate" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">楼栋内装情况</h3>
            </div>
            <form id="frmExamineUnitDecorate" class="form-horizontal">
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
                                            <select required="required" name="materialPriceRange" class="form-control search-select select2 materialPriceRange">
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
                    <button type="button" class="btn btn-primary" onclick="unitDecorate.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>

