<%--
  主干道
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
        <h3>主干道信息 <button type="button" class="btn btn-success" onclick="matchingMainRoad.prototype.showModel()"
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
                <table class="table table-bordered" id="MainRoadList">
                    <!-- cerare document add ajax data-->
                </table>
            </div>
        </div>
    </form>
</div>
</body>


<%--<%@include file="/views/share/main_footer.jsp" %>--%>
<script type="application/javascript">

    var matchingMainRoad = function () {

    };
    matchingMainRoad.prototype = {
        config:function () {
            var data = {};
            data.table = "MainRoadList" ;
            data.box = "divBoxMainRoad";
            data.frm = "frmMainRoad";
            data.type = "mainRoad" ;//根据ExamineMatchingTrafficTypeEnum 配置
            return data;
        },
        loadDataDicList:function () {
            var cols = [];
            cols.push({field: 'name', title: '名称'});
            cols.push({field: 'distanceName', title: '距离'});
            // cols.push({field: 'type', title: '类型'});
            cols.push({field: 'lineName', title: '线路名称'});
            cols.push({field: 'theLine', title: '所在线路'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="matchingMainRoad.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="matchingMainRoad.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#"+matchingMainRoad.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingMainRoad.prototype.config().table, "${pageContext.request.contextPath}/examineMatchingTraffic/getExamineMatchingTrafficList", cols, {
                type:matchingMainRoad.prototype.config().type
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
                url:"${pageContext.request.contextPath}/examineMatchingTraffic/deleteExamineMatchingTrafficById",
                type: "post",
                dataType: "json",
                data: {id:id},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('删除成功');
                        matchingMainRoad.prototype.loadDataDicList();
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
            $("#"+matchingMainRoad.prototype.config().frm).clearAll();
            $("#"+matchingMainRoad.prototype.config().frm+" .type").val(matchingMainRoad.prototype.config().type);
            $('#'+matchingMainRoad.prototype.config().box).modal("show");
        },
        saveData:function () {
            if (!$("#"+matchingMainRoad.prototype.config().frm).valid()){
                return false;
            }
            var data = formParams(matchingMainRoad.prototype.config().frm);
            if ($("#declareId").size() > 0){
                data.declareId = $("#declareId").val();
            }
            if ($("#examineType").size() > 0){
                data.examineType = $("#examineType").val();
            }
            $.ajax({
                url:"${pageContext.request.contextPath}/examineMatchingTraffic/saveAndUpdateExamineMatchingTraffic",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#'+matchingMainRoad.prototype.config().box).modal('hide');
                        matchingMainRoad.prototype.loadDataDicList();
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
                url:"${pageContext.request.contextPath}/examineMatchingTraffic/getExamineMatchingTrafficById",
                type: "get",
                dataType: "json",
                data: {id:id},
                success: function (result) {
                    if (result.ret) {
                        $("#"+matchingMainRoad.prototype.config().frm).clearAll();
                        $("#" + matchingMainRoad.prototype.config().frm).initForm(result.data);
                        if (result.data.distance == null || result.data.distance == ''){
                            $("#"+matchingMainRoad.prototype.config().frm+" .distance").val(null).trigger("change");
                        }else {
                            $("#"+matchingMainRoad.prototype.config().frm+" .distance").val(result.data.distance).trigger("change");
                        }
                        $('#'+matchingMainRoad.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init:function () {
            $.ajax({
                url:"${pageContext.request.contextPath}/examineMatchingTraffic/estate_distance",
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
                            $("#"+matchingMainRoad.prototype.config().frm+" .distance").html(option);
                            $("#"+matchingMainRoad.prototype.config().frm+" .distance").select2({ minimumResultsForSearch: -1 });//加载样式
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
        matchingMainRoad.prototype.loadDataDicList();
        matchingMainRoad.prototype.init();
    })

</script>

<div id="divBoxMainRoad" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">主干道</h3>
            </div>
            <form id="frmMainRoad" class="form-horizontal">
                <input type="hidden"  name="id">
                <input type="hidden"  name="type" class="type">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            名称
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="name"
                                                   placeholder="名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            距离
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="distance" class="form-control search-select select2 distance">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            线路名称
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="lineName" class="form-control"
                                                   placeholder="线路名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            所在线路
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="theLine" class="form-control"
                                                   placeholder="所在线路" required="required">
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
                    <button type="button" class="btn btn-primary" onclick="matchingMainRoad.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>







