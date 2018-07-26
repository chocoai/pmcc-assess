<%--
 临街（路）状况
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
        <h3>临街（路）状况信息 <button type="button" class="btn btn-success" onclick="houseFaceStreet.prototype.showModel()"
                           data-toggle="modal" href="#divBox"> 新增
        </button></h3>
        <div class="clearfix"></div>
    </div>
    <form  class="form-horizontal">
        <div class="form-group">
            <div class="x-valid">
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <table class="table table-bordered" id="HouseFaceStreetList">
                    <!-- cerare document add ajax data-->
                </table>
            </div>
        </div>
    </form>
</div>
</body>


<%--<%@include file="/views/share/main_footer.jsp" %>--%>
<script type="application/javascript">

    var houseFaceStreet = function () {

    };
    houseFaceStreet.prototype = {
        config:function () {
            var data = {};
            data.table = "HouseFaceStreetList" ;
            data.box = "divBoxHouseFaceStreet";
            data.frm = "frmHouseFaceStreet";
            data.type = "null" ;//
            return data;
        },
        loadDataDicList:function () {
            var cols = [];
            cols.push({field: 'streetName', title: '名称'});
            cols.push({field: 'streetLevelName', title: '街道级别'});
            cols.push({field: 'trafficFlowName', title: '交通流量'});
            cols.push({field: 'visitorsFlowrateName', title: '人流量'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="houseFaceStreet.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="houseFaceStreet.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#"+houseFaceStreet.prototype.config().table).bootstrapTable('destroy');//examineHouseFaceStreet
            TableInit(houseFaceStreet.prototype.config().table, "${pageContext.request.contextPath}/examineHouseFaceStreet/getExamineHouseFaceStreetList", cols, {
                type:houseFaceStreet.prototype.config().type
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
                url:"${pageContext.request.contextPath}/examineHouseFaceStreet/deleteExamineHouseFaceStreetById",
                type: "post",
                dataType: "json",
                data: {id:id},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('删除成功');
                        houseFaceStreet.prototype.loadDataDicList();
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
            $("#"+houseFaceStreet.prototype.config().frm).clearAll();
            $("#"+houseFaceStreet.prototype.config().frm+" .type").val(houseFaceStreet.prototype.config().type);
            $("#"+houseFaceStreet.prototype.config().frm+" .name").empty();
            var lableValue = "街道（道路）名称";
            var html = "<div class='form-group' style='margin-top:8px;'>";
            html += "<label class='col-md-2 col-sm-2 col-xs-12 control-label'>" + lableValue + "</label>";
            html += "<div class='col-md-10 col-sm-10 col-xs-12 input-group'>";
            html += "<input type='text' required class='form-control'" + "name='" + 'streetName' + "'" + ">";
            html += "<span class='input-group-btn'>" + "<input class='btn btn-warning' type='button' value='X' onclick='matchingRecreation.prototype.cleanHTMLData(this)'>" + "</span>";
            html += "</div>";
            html += "</div>";
            $("#"+houseFaceStreet.prototype.config().frm+" .name").append(html);
            $('#'+houseFaceStreet.prototype.config().box).modal("show");
        },
        saveData:function () {
            if (!$("#"+houseFaceStreet.prototype.config().frm).valid()){
                return false;
            }
            var data = formParams(houseFaceStreet.prototype.config().frm);
            if ($("#declareId").size() > 0){
                data.declareId = $("#declareId").val();
            }
            if ($("#examineType").size() > 0){
                data.examineType = $("#examineType").val();
            }
            $.ajax({
                url:"${pageContext.request.contextPath}/examineHouseFaceStreet/saveAndUpdateExamineHouseFaceStreet",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#'+houseFaceStreet.prototype.config().box).modal('hide');
                        houseFaceStreet.prototype.loadDataDicList();
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
                url:"${pageContext.request.contextPath}/examineHouseFaceStreet/getExamineHouseFaceStreetById",
                type: "get",
                dataType: "json",
                data: {id:id},
                success: function (result) {
                    if (result.ret) {
                        $("#"+houseFaceStreet.prototype.config().frm).clearAll();
                        $("#" + houseFaceStreet.prototype.config().frm).initForm(result.data);
                        if (result.data.streetLevel == null || result.data.streetLevel == ''){
                            $("#"+houseFaceStreet.prototype.config().frm+" .streetLevel").val(null).trigger("change");
                        }else {
                            $("#"+houseFaceStreet.prototype.config().frm+" .streetLevel").val(result.data.streetLevel).trigger("change");
                        }
                        if (result.data.trafficFlow == null || result.data.trafficFlow == ''){
                            $("#"+houseFaceStreet.prototype.config().frm+" .trafficFlow").val(null).trigger("change");
                        }else {
                            $("#"+houseFaceStreet.prototype.config().frm+" .trafficFlow").val(result.data.trafficFlow).trigger("change");
                        }
                        if (result.data.visitorsFlowrate == null || result.data.visitorsFlowrate == ''){
                            $("#"+houseFaceStreet.prototype.config().frm+" .visitorsFlowrate").val(null).trigger("change");
                        }else {
                            $("#"+houseFaceStreet.prototype.config().frm+" .visitorsFlowrate").val(result.data.visitorsFlowrate).trigger("change");
                        }
                        houseFaceStreet.prototype.writeHTMLData(result.data.streetName);
                        $('#'+houseFaceStreet.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init:function () {
            $.ajax({
                url:"${pageContext.request.contextPath}/examineHouseFaceStreet/examine_house_street_level",
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
                            $("#"+houseFaceStreet.prototype.config().frm+" .streetLevel").html(option);
                            $("#"+houseFaceStreet.prototype.config().frm+" .streetLevel").select2({ minimumResultsForSearch: -1 });//加载样式
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
            $.ajax({
                url:"${pageContext.request.contextPath}/examineHouseFaceStreet/examine_house_traffic_flow",
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
                            $("#"+houseFaceStreet.prototype.config().frm+" .trafficFlow").html(option);
                            $("#"+houseFaceStreet.prototype.config().frm+" .trafficFlow").select2({ minimumResultsForSearch: -1 });//加载样式
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
            $.ajax({
                url:"${pageContext.request.contextPath}/examineHouseFaceStreet/examine_house_visitors_flowrate",
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
                            $("#"+houseFaceStreet.prototype.config().frm+" .visitorsFlowrate").html(option);
                            $("#"+houseFaceStreet.prototype.config().frm+" .visitorsFlowrate").select2({ minimumResultsForSearch: -1 });//加载样式
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })

        },
        appendHTML:function (item,this_) {
            var lableValue = "街道（道路）名称";
            var html = "<div class='form-group' style='margin-top:8px;'>";
            html += "<label class='col-md-2 col-sm-2 col-xs-12 control-label'>" + lableValue + "</label>";
            html += "<div class='col-md-10 col-sm-10 col-xs-12 input-group'>";
            html += "<input type='text' required class='form-control'" + "name='" + item + "'" + ">";
            html += "<span class='input-group-btn'>" + "<input class='btn btn-warning' type='button' value='X' onclick='houseFaceStreet.prototype.cleanHTMLData(this)'>" + "</span>";
            html += "</div>";
            html += "</div>";
            // $(this_).parent().prev().parent().parent().after(html);
            $("#"+houseFaceStreet.prototype.config().frm+" .name").append(html);
        },
        cleanHTMLData:function (item) {
            var value = "";
            $(item).parent().prev().parent().parent().empty();
        },
        writeHTMLData:function (str) {
            $("#"+houseFaceStreet.prototype.config().frm+" .name").empty();
            var strs = str.split(",");
            var length = strs.length;
            var lableValue = "街道（道路）名称";
            var item = "streetName";
            for (var i = 0; i < length; i++) {
                console.log("i:"+i);
                var html = "<div class='form-group' style='margin-top:8px;'>";
                html += "<label class='col-md-2 col-sm-2 col-xs-12 control-label'>" + lableValue + "</label>";
                html += "<div class='col-md-10 col-sm-10 col-xs-12 input-group'>";
                html += "<input type='text' required class='form-control'" + "name='" + item + "' value='" + strs[i] + "'>";
                html += "<span class='input-group-btn'>" + "<input class='btn btn-warning' type='button' value='X' onclick='houseFaceStreet.prototype.cleanHTMLData(this)'>" + "</span>";
                html += "</div>";
                html += "</div>";
                $("#"+houseFaceStreet.prototype.config().frm+" .name").append(html);
            }
        }
    }
    /**
     * 初始化
     */
    $(function () {
        houseFaceStreet.prototype.loadDataDicList();
        houseFaceStreet.prototype.init();
    })

</script>

<div id="divBoxHouseFaceStreet" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">临街（路）状况</h3>
            </div>
            <form id="frmHouseFaceStreet" class="form-horizontal">
                <input type="hidden"  name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <%--<div class="form-group">--%>
                                    <%--<div class="x-valid">--%>
                                        <%--<label class="col-sm-2 control-label">--%>
                                            <%--名称--%>
                                        <%--</label>--%>
                                        <%--<div class="col-sm-10">--%>
                                            <%--<input type="text" name="streetName" class="form-control" required="required">--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            街道（道路）名称
                                        </label>
                                        <div class="col-sm-10">
                                            <button class="btn btn-xs btn-success" onclick="houseFaceStreet.prototype.appendHTML('streetName',this)"><i class="fa fa-plus"></i></button>
                                        </div>
                                    </div>
                                </div>

                                <div style="margin-bottom: 8px;" class="name">
                                    <div class="form-group" style=" margin-top: 8px;">
                                        <label class="col-md-2 col-sm-2 col-xs-12 control-label">街道（道路）名称</label>
                                        <div class="col-md-10 col-sm-10 col-xs-12 input-group">
                                            <input class="form-control" name="streetName" required="required" type="text">
                                            <span class="input-group-btn"><input type="button" class="btn btn-warning" value="X" onclick="houseFaceStreet.prototype.cleanHTMLData(this)"></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            人流量
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="visitorsFlowrate" class="form-control search-select select2 visitorsFlowrate">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            交通流量
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="trafficFlow" class="form-control search-select select2 trafficFlow">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            街道级别
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="streetLevel" class="form-control search-select select2 streetLevel">
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
                    <button type="button" class="btn btn-primary" onclick="houseFaceStreet.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>


