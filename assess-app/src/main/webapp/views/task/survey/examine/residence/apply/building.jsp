<%--
 楼栋基础信息
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <!-- 包含此文件时需要删除掉css -->
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="examineBuilding_.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>楼栋基础信息
        </h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content" style="display: none">
        <div>
            <button type="button" class="btn btn-success" onclick="examineBuilding_.prototype.showModel()"
                    data-toggle="modal" href="#divBox"> 新增
            </button>
        </div>
        <form class="form-horizontal">
            <div class="form-group">
                <div class="x-valid">
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <table class="table table-bordered" id="ExamineBuilding_List">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>
</body>


<%--<%@include file="/views/share/main_footer.jsp" %>--%>
<script type="application/javascript">

    var examineBuilding_ ;
    (function () {
        var flag = true;
        examineBuilding_ = function () {

        };
        examineBuilding_.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            viewInit: function () {
                examineBuilding_.prototype.loadDataDicList();
                if (examineBuilding_.prototype.getFlag()){
                    examineBuilding_.prototype.init();
                    examineBuilding_.prototype.setFlag(false);
                }
            },
            config: function () {
                var data = {};
                data.table = "ExamineBuilding_List";
                data.box = "divBoxExamineBuilding_";
                data.frm = "frmExamineBuilding_";
                data.type = "null";//
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'builderName', title: '建造商'});
                cols.push({field: 'propertyName', title: '物业公司'});
                cols.push({field: 'buildingCategoryName', title: '建筑类别'});
                cols.push({field: 'buildingStructureName', title: '建筑结构'});
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="examineBuilding_.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="examineBuilding_.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                $("#" + examineBuilding_.prototype.config().table).bootstrapTable('destroy');
                TableInit(examineBuilding_.prototype.config().table, "${pageContext.request.contextPath}/examineBuilding/getExamineBuildingList", cols, {
                    type: examineBuilding_.prototype.config().type
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
                    url: "${pageContext.request.contextPath}/examineBuilding/deleteExamineBuildingById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            examineBuilding_.prototype.loadDataDicList();
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
                $("#" + examineBuilding_.prototype.config().frm).clearAll();
                $("#" + examineBuilding_.prototype.config().frm + " .type").val(examineBuilding_.prototype.config().type);
                $('#' + examineBuilding_.prototype.config().box).modal("show");
            },
            saveData: function () {
                if (!$("#" + examineBuilding_.prototype.config().frm).valid()) {
                    return false;
                }
                var data = formParams(examineBuilding_.prototype.config().frm);
                if ($("#declareId").size() > 0) {
                    data.declareId = $("#declareId").val();
                }
                if ($("#examineType").size() > 0) {
                    data.examineType = $("#examineType").val();
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuilding/saveAndUpdateExamineBuilding",
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('保存成功');
                            $('#' + examineBuilding_.prototype.config().box).modal('hide');
                            examineBuilding_.prototype.loadDataDicList();
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
                    url: "${pageContext.request.contextPath}/examineBuilding/getExamineBuildingById",
                    type: "get",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            $("#" + examineBuilding_.prototype.config().frm).clearAll();
                            $("#" + examineBuilding_.prototype.config().frm).initForm(result.data);
                            if (result.data.buildingCategory == null || result.data.buildingCategory == '') {
                                $("#" + examineBuilding_.prototype.config().frm + " .buildingCategory").val(null).trigger("change");
                            } else {
                                $("#" + examineBuilding_.prototype.config().frm + " .buildingCategory").val(result.data.buildingCategory).trigger("change");
                            }
                            if (result.data.buildingStructurePid == null || result.data.buildingStructurePid == '') {
                                $("#" + examineBuilding_.prototype.config().frm + " .buildingStructure").val(null).trigger("change");
                            } else {
                                $("#" + examineBuilding_.prototype.config().frm + " .buildingStructure").val(result.data.buildingStructurePid).trigger("change");
                            }
                            if (result.data.buildingStructure == null || result.data.buildingStructure == '') {
                                $("#" + examineBuilding_.prototype.config().frm + "buildingStructure").val(null).trigger("change");
                            } else {
                                console.log(result.data.buildingStructure);
                                console.log(result.data);
                                $("#" + examineBuilding_.prototype.config().frm + "buildingStructure").val(result.data.buildingStructure).trigger("change");
                                // $("#"+examineBuilding_.prototype.config().frm+" .buildingStructureV").val(result.data.buildingStructure).trigger("change");
                                // $("#frmExamineBuilding_buildingStructure").val(result.data.buildingStructure).trigger("change");
                            }
                            if (result.data.propertyType == null || result.data.propertyType == '') {
                                $("#" + examineBuilding_.prototype.config().frm + " .propertyType").val(null).trigger("change");
                            } else {
                                $("#" + examineBuilding_.prototype.config().frm + " .propertyType").val(result.data.propertyType).trigger("change");
                            }
                            if (result.data.builderId == null || result.data.builderId == '') {
                                $("#" + examineBuilding_.prototype.config().frm + " .builderId").val(null).trigger("change");
                            } else {
                                $("#" + examineBuilding_.prototype.config().frm + " .builderId").val(result.data.builderId).trigger("change");
                            }
                            if (result.data.propertyId == null || result.data.propertyId == '') {
                                $("#" + examineBuilding_.prototype.config().frm + " .propertyId").val(null).trigger("change");
                            } else {
                                $("#" + examineBuilding_.prototype.config().frm + " .propertyId").val(result.data.propertyId).trigger("change");
                            }
                            $('#' + examineBuilding_.prototype.config().box).modal("show");
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            init: function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuilding/estate_examineBuilding_category",
                    type: "get",
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                            var data = result.data;
                            var gradeNum = data.length;
                            var option = "<option value=''>请选择</option>";
                            if (gradeNum > 0) {
                                for (var i = 0; i < gradeNum; i++) {
                                    option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                }
                                $("#" + examineBuilding_.prototype.config().frm + " .buildingCategory").html(option);
                                $("#" + examineBuilding_.prototype.config().frm + " .buildingCategory").select2({minimumResultsForSearch: -1});//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuilding/estate_building_structure",
                    type: "get",
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                            var data = result.data;
                            var gradeNum = data.length;
                            var option = "<option value=''>请选择</option>";
                            if (gradeNum > 0) {
                                for (var i = 0; i < gradeNum; i++) {
                                    option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                }
                                $("#" + examineBuilding_.prototype.config().frm + " .buildingStructure").html(option);
                                $("#" + examineBuilding_.prototype.config().frm + " .buildingStructure").select2({minimumResultsForSearch: -1});//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
                $("#" + examineBuilding_.prototype.config().frm + "buildingStructure").select2({minimumResultsForSearch: -1});//加载样式
                $("#" + examineBuilding_.prototype.config().frm + " .buildingStructure").change(function () {
                    /**
                     * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
                     **/
                    var id = $("#" + examineBuilding_.prototype.config().frm + " .buildingStructure").eq(1).val();
                    if (id != null && id != '' && id != 0) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/examineBuilding/getBasisList",
                            dataType: "JSON",
                            data: {'id': id},
                            type: "GET",
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    var gradeNum = data.length;
                                    var option = "<option value=''>请选择</option>";
                                    if (gradeNum > 0) {
                                        for (var i = 0; i < gradeNum; i++) {
                                            option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                        }
                                        // $("#"+examineBuilding_.prototype.config().frm+"buildingStructure").empty();
                                        $("#" + examineBuilding_.prototype.config().frm + "buildingStructure").html(option);
                                        $("#" + examineBuilding_.prototype.config().frm + "buildingStructure").select2({minimumResultsForSearch: -1});//加载样式
                                    }

                                }
                            },
                            error: function (e) {
                                Alert("调用服务端方法失败，失败原因:" + e);
                            }
                        });
                    }
                });
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuilding/estate_building_type",
                    type: "get",
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                            var data = result.data;
                            var gradeNum = data.length;
                            var option = "<option value=''>请选择</option>";
                            if (gradeNum > 0) {
                                for (var i = 0; i < gradeNum; i++) {
                                    option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                }
                                $("#" + examineBuilding_.prototype.config().frm + " .propertyType").html(option);
                                $("#" + examineBuilding_.prototype.config().frm + " .propertyType").select2({minimumResultsForSearch: -1});//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuilding/getBuildAndProperty",
                    type: "get",
                    dataType: "json",
                    data: {type: "DataBuilder"},
                    success: function (result) {
                        if (result.ret) {
                            var data = result.data;
                            var gradeNum = data.length;
                            var option = "<option value=''>请选择</option>";
                            if (gradeNum > 0) {
                                for (var i = 0; i < gradeNum; i++) {
                                    option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                }
                                $("#" + examineBuilding_.prototype.config().frm + " .builderId").html(option);
                                $("#" + examineBuilding_.prototype.config().frm + " .builderId").select2({minimumResultsForSearch: -1});//加载样式
                            }
                            $.ajax({
                                url: "${pageContext.request.contextPath}/examineBuilding/getBuildAndProperty",
                                type: "get",
                                dataType: "json",
                                data: {type: "DataProperty"},
                                success: function (result) {
                                    if (result.ret) {
                                        var data = result.data;
                                        var gradeNum = data.length;
                                        var option = "<option value=''>请选择</option>";
                                        if (gradeNum > 0) {
                                            for (var i = 0; i < gradeNum; i++) {
                                                option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                            }
                                            $("#" + examineBuilding_.prototype.config().frm + " .propertyId").html(option);
                                            $("#" + examineBuilding_.prototype.config().frm + " .propertyId").select2({minimumResultsForSearch: -1});//加载样式
                                        }
                                    }
                                },
                                error: function (result) {
                                    Alert("调用服务端方法失败，失败原因:" + result);
                                }
                            })
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })

            }
        }
    })();

</script>

<div id="divBoxExamineBuilding_" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">楼栋基础</h3>
            </div>
            <form id="frmExamineBuilding_" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            楼栋号
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="楼栋号" name="buildingNumber"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            户型区间
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="户型区间" name="unitInterval"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            物业费
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="物业费(数字)" name="propertyFee"
                                                   data-rule-number='true' class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            公共设施使用费
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="公共设施使用费(数字)" name="facilitiesUseFee"
                                                   data-rule-number='true' class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            楼层起
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="楼层起(数字)" name="floorStart"
                                                   data-rule-number='true' class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            楼层止
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="楼层止(数字)" name="floorEnd"
                                                   data-rule-number='true' class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            总层数
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="总层数(数字)" data-rule-number='true'
                                                   name="floorCount" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            建筑高度
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="建筑高度(数字)" data-rule-number='true'
                                                   name="buildingHeight" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            建筑面积
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="建筑面积(数字)" data-rule-number='true'
                                                   name="buildingArea" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            占地面积
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="占地面积(数字)" data-rule-number='true'
                                                   name="coverAnArea" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            层高
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="层高(数字)" data-rule-number='true'
                                                   name="floorHeight" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            径深
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="径深(数字)" data-rule-number='true'
                                                   name="diameterDepth" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            土地使用年限
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="土地使用年限(数字)" data-rule-number='true'
                                                   name="landUseYear" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            净高
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="净高(数字)" data-rule-number='true'
                                                   name="netHeight" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            所在位置
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="所在位置" name="location" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            开盘时间
                                        </label>
                                        <div class="col-sm-10">
                                            <input required="required" placeholder="开盘时间"
                                                   name="openTime" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            交房时间
                                        </label>
                                        <div class="col-sm-10">
                                            <input required="required" placeholder="开盘时间"
                                                   name="roomTime" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            物业类型
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="propertyType"
                                                    class="form-control search-select select2 propertyType">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            建筑结构上级
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="buildingStructurePid"
                                                    class="form-control search-select select2 buildingStructure">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            建筑结构(下级)
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" id="frmExamineBuilding_buildingStructure"
                                                    name="buildingStructure"
                                                    class="form-control search-select select2 buildingStructureV">
                                                <option>请先选择建筑结构上级</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            楼栋基础 建筑类别
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="buildingCategory"
                                                    class="form-control search-select select2 buildingCategory">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            楼栋基础 建筑公司
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="builderId"
                                                    class="form-control search-select select2 builderId">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            楼栋基础 物业公司
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="propertyId"
                                                    class="form-control search-select select2 propertyId">
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
                    <button type="button" class="btn btn-primary" onclick="examineBuilding_.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>

