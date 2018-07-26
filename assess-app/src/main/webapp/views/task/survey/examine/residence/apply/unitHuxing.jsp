<%--
 户型
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
        <h3>户型信息 <button type="button" class="btn btn-success" onclick="unitHuxing.prototype.showModel()"
                           data-toggle="modal" href="#divBox"> 新增
        </button></h3>
        <div class="clearfix"></div>
    </div>
    <form id="frm_unitHuxing" class="form-horizontal">
        <div class="form-group">
            <div class="x-valid">
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <table class="table table-bordered" id="UnitHuxingList">
                    <!-- cerare document add ajax data-->
                </table>
            </div>
        </div>
    </form>
</div>
</body>


<%--<%@include file="/views/share/main_footer.jsp" %>--%>
<script type="application/javascript">

    var unitHuxing = function () {

    };
    unitHuxing.prototype = {
        config:function () {
            var data = {};
            data.table = "UnitHuxingList" ;
            data.box = "divBoxUnitHuxing";
            data.frm = "frmUnitHuxing";
            data.type = "null" ;//
            return data;
        },
        loadDataDicList:function () {
            var cols = [];
            cols.push({field: 'description', title: '描述'});
            cols.push({field: 'houseLayoutName', title: '房型'});
            cols.push({field: 'spanLength', title: '跨长'});
            cols.push({field: 'spanWidth', title: '跨宽'});
            cols.push({field: 'spanNumber', title: '跨数'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="unitHuxing.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="unitHuxing.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#"+unitHuxing.prototype.config().table).bootstrapTable('destroy');
            TableInit(unitHuxing.prototype.config().table, "${pageContext.request.contextPath}/examineUnitHuxing/getExamineUnitHuxingList", cols, {
                type:unitHuxing.prototype.config().type
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
                url:"${pageContext.request.contextPath}/examineUnitHuxing/deleteExamineUnitHuxingById",
                type: "post",
                dataType: "json",
                data: {id:id},
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
        showModel:function () {
            $("#"+unitHuxing.prototype.config().frm).clearAll();
            $("#"+unitHuxing.prototype.config().frm+" .type").val(unitHuxing.prototype.config().type);
            $('#'+unitHuxing.prototype.config().box).modal("show");
        },
        saveData:function () {
            if (!$("#"+unitHuxing.prototype.config().frm).valid()){
                return false;
            }
            var data = formParams(unitHuxing.prototype.config().frm);
            if ($("#declareId").size() > 0){
                data.declareId = $("#declareId").val();
            }
            if ($("#examineType").size() > 0){
                data.examineType = $("#examineType").val();
            }
            $.ajax({
                url:"${pageContext.request.contextPath}/examineUnitHuxing/saveAndUpdateExamineUnitHuxing",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#'+unitHuxing.prototype.config().box).modal('hide');
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
        getAndInit:function (id) {
            $.ajax({
                url:"${pageContext.request.contextPath}/examineUnitHuxing/getExamineUnitHuxingById",
                type: "get",
                dataType: "json",
                data: {id:id},
                success: function (result) {
                    if (result.ret) {
                        $("#"+unitHuxing.prototype.config().frm).clearAll();
                        $("#" + unitHuxing.prototype.config().frm).initForm(result.data);
                        if (result.data.houseLayout == null || result.data.houseLayout == ''){
                            $("#"+unitHuxing.prototype.config().frm+" .houseLayout").val(null).trigger("change");
                        }else {
                            $("#"+unitHuxing.prototype.config().frm+" .houseLayout").val(result.data.houseLayout).trigger("change");
                        }
                        $('#'+unitHuxing.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init:function () {
            $.ajax({
                url:"${pageContext.request.contextPath}/examineUnitHuxing/examine_unit_house_layout",
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
                            $("#"+unitHuxing.prototype.config().frm+" .houseLayout").html(option);
                            $("#"+unitHuxing.prototype.config().frm+" .houseLayout").select2({ minimumResultsForSearch: -1 });//加载样式
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
        unitHuxing.prototype.loadDataDicList();
        unitHuxing.prototype.init();
    })

</script>

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
                <input type="hidden"  name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            面积
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="面积(数字)" data-rule-number='true' name="area" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            跨长
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="跨长(数字)" data-rule-number='true' name="spanLength" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            跨宽
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="跨宽(数字)" data-rule-number='true' name="spanWidth" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            跨数
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="跨数(数字)" data-rule-number='true' name="spanNumber" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            户型描述
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="户型描述" name="description" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            户型内容
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="houseLayout" class="form-control search-select select2 houseLayout">
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
                    <button type="button" class="btn btn-primary" onclick="unitHuxing.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>

