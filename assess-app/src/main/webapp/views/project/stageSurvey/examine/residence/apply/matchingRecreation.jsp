<%--
  休闲娱乐
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="matchingRecreation.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>休闲娱乐信息
        </h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content" style="display: none">
        <div>
            <button type="button" class="btn btn-success" onclick="matchingRecreation.prototype.showModel()"
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
                    <table class="table table-bordered" id="MatchingRecreationList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var matchingRecreation;
    (function () {
        var flag = true;
        matchingRecreation = function () {

        };
        matchingRecreation.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            viewInit: function () {
                matchingRecreation.prototype.loadDataDicList();
                if (matchingRecreation.prototype.getFlag()) {
                    matchingRecreation.prototype.init();
                    matchingRecreation.prototype.setFlag(false);
                }
            },
            config: function () {
                var data = {};
                data.table = "MatchingRecreationList";
                data.box = "divBoxMatchingRecreation";
                data.frm = "frmMatchingRecreation";
                data.type = "matchingRecreation";// 根据 ExamineMatchingLeisurePlaceTypeEnum 配置
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'name', title: '休闲娱乐名称'});
                cols.push({field: 'categoryName', title: '休闲娱乐类别'});
                // cols.push({field: 'gradeName', title: '休闲娱乐档次'});
                cols.push({field: 'distanceName', title: '休闲娱乐距离'});
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="matchingRecreation.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="matchingRecreation.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                $("#" + matchingRecreation.prototype.config().table).bootstrapTable('destroy');
                TableInit(matchingRecreation.prototype.config().table, "${pageContext.request.contextPath}/examineMatchingLeisurePlace/getExamineMatchingLeisurePlaceList", cols, {
                    type: matchingRecreation.prototype.config().type,
                    declareId : $("#declareId").val(),
                    planDetailsId : $("#planDetailsId").val(),
                    examineType : $("#examineType").val()
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
                    url: "${pageContext.request.contextPath}/examineMatchingLeisurePlace/deleteExamineMatchingLeisurePlaceById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            matchingRecreation.prototype.loadDataDicList();
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
                $("#" + matchingRecreation.prototype.config().frm).clearAll();
                $("#" + matchingRecreation.prototype.config().frm + " .type").val(matchingRecreation.prototype.config().type);
                $("#" + matchingRecreation.prototype.config().frm + " .name").empty();
                // var size = $("#"+matchingRecreation.prototype.config().frm+" .name .form-group").size();
                // for (var i = 0 ;i < size; i++){
                //     console.log("i:"+i);
                //     if(i==0){
                //         var dom = $("#"+matchingRecreation.prototype.config().frm+" .name .form-group")[i];
                //     }else {
                //         var dom = $("#"+matchingRecreation.prototype.config().frm+" .name .form-group")[i];
                //         // $(dom).empty();
                //         $(dom).remove();
                //     }
                // }

                var lableValue = "休闲娱乐名称";
                var html = "<div class='form-group' style='margin-top:8px;'>";
                html += "<label class='col-md-2 col-sm-2 col-xs-12 control-label'>" + lableValue + "</label>";
                html += "<div class='col-md-10 col-sm-10 col-xs-12 input-group'>";
                html += "<input type='text' required class='form-control'" + "name='" + 'name' + "'" + ">";
                html += "<span class='input-group-btn'>" + "<input class='btn btn-warning' type='button' value='X' onclick='matchingRecreation.prototype.cleanHTMLData(this)'>" + "</span>";
                html += "</div>";
                html += "</div>";
                $("#" + matchingRecreation.prototype.config().frm + " .name").append(html);
                // matchingRecreation.prototype.init();
                $('#' + matchingRecreation.prototype.config().box).modal("show");
            },
            saveData: function () {
                if (!$("#" + matchingRecreation.prototype.config().frm).valid()) {
                    return false;
                }
                var data = formParams(matchingRecreation.prototype.config().frm);
                if ($("#declareId").size() > 0) {
                    data.declareId = $("#declareId").val();
                }
                if ($("#planDetailsId").size() > 0) {
                    data.planDetailsId = $("#planDetailsId").val();
                }
                if ($("#examineType").size() > 0) {
                    data.examineType = $("#examineType").val();
                }
                data.type = matchingRecreation.prototype.config().type;
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineMatchingLeisurePlace/saveAndUpdateExamineMatchingLeisurePlace",
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('保存成功');
                            $('#' + matchingRecreation.prototype.config().box).modal('hide');
                            matchingRecreation.prototype.loadDataDicList();
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
                    url: "${pageContext.request.contextPath}/examineMatchingLeisurePlace/getExamineMatchingLeisurePlaceById",
                    type: "get",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            $("#" + matchingRecreation.prototype.config().frm).clearAll();
                            $("#" + matchingRecreation.prototype.config().frm).initForm(result.data);
                            if (result.data.category == null || result.data.category == '') {
                                $("#" + matchingRecreation.prototype.config().frm + " .category").val(null).trigger("change");
                            } else {
                                $("#" + matchingRecreation.prototype.config().frm + " .category").val(result.data.category).trigger("change");
                            }
                            // if (result.data.grade == null || result.data.grade == ''){
                            //     $("#"+matchingRecreation.prototype.config().frm+" .grade").val(null).trigger("change");
                            // }else {
                            //     $("#"+matchingRecreation.prototype.config().frm+" .grade").val(result.data.grade).trigger("change");
                            // }
                            if (result.data.distance == null || result.data.distance == '') {
                                $("#" + matchingRecreation.prototype.config().frm + " .distance").val(null).trigger("change");
                            } else {
                                $("#" + matchingRecreation.prototype.config().frm + " .distance").val(result.data.distance).trigger("change");
                            }
                            matchingRecreation.prototype.writeHTMLData(result.data.name);
                            $('#' + matchingRecreation.prototype.config().box).modal("show");
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            init: function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineMatchingLeisurePlace/examineMatchingLeisurePlace_category",
                    type: "get",
                    data: {type: matchingRecreation.prototype.config().type},
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
                                $("#" + matchingRecreation.prototype.config().frm + " .category").html(option);
                                $("#" + matchingRecreation.prototype.config().frm + " .category").select2({minimumResultsForSearch: -1});//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
                <%--$.ajax({--%>
                <%--url:"${pageContext.request.contextPath}/examineMatchingLeisurePlace/examineMatchingLeisurePlace_grade",--%>
                <%--type: "get",--%>
                <%--data:{type:matchingRecreation.prototype.config().type},--%>
                <%--dataType: "json",--%>
                <%--success: function (result) {--%>
                <%--if (result.ret) {--%>
                <%--var data = result.data;--%>
                <%--var gradeNum = data.length;--%>
                <%--var option = "<option value=''>请选择</option>";--%>
                <%--if(gradeNum > 0){--%>
                <%--for(var i = 0;i< gradeNum;i++){--%>
                <%--option += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";--%>
                <%--}--%>
                <%--$("#"+matchingRecreation.prototype.config().frm+" .grade").html(option);--%>
                <%--$("#"+matchingRecreation.prototype.config().frm+" .grade").select2({ minimumResultsForSearch: -1 });//加载样式--%>
                <%--}--%>
                <%--}--%>
                <%--},--%>
                <%--error: function (result) {--%>
                <%--Alert("调用服务端方法失败，失败原因:" + result);--%>
                <%--}--%>
                <%--})--%>
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineMatchingLeisurePlace/examineMatchingLeisurePlace_distance",
                    type: "get",
                    data: {type: matchingRecreation.prototype.config().type},
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
                                $("#" + matchingRecreation.prototype.config().frm + " .distance").html(option);
                                $("#" + matchingRecreation.prototype.config().frm + " .distance").select2({minimumResultsForSearch: -1});//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })

            },
            appendHTML: function (item, this_) {
                var lableValue = "休闲娱乐名称";
                var html = "<div class='form-group' style='margin-top:8px;'>";
                html += "<label class='col-md-2 col-sm-2 col-xs-12 control-label'>" + lableValue + "</label>";
                html += "<div class='col-md-10 col-sm-10 col-xs-12 input-group'>";
                html += "<input type='text' required class='form-control'" + "name='" + item + "'" + ">";
                html += "<span class='input-group-btn'>" + "<input class='btn btn-warning' type='button' value='X' onclick='matchingRecreation.prototype.cleanHTMLData(this)'>" + "</span>";
                html += "</div>";
                html += "</div>";
                // $(this_).parent().prev().parent().parent().after(html);
                $("#" + matchingRecreation.prototype.config().frm + " .name").append(html);
            },
            cleanHTMLData: function (item) {
                var value = "";
                $(item).parent().prev().parent().parent().empty();
            },
            writeHTMLData: function (str) {
                $("#" + matchingRecreation.prototype.config().frm + " .name").empty();
                var strs = str.split(",");
                var length = strs.length;
                var lableValue = "休闲娱乐名称";
                var item = "name";
                for (var i = 0; i < length; i++) {
                    console.log("i:" + i);
                    var html = "<div class='form-group' style='margin-top:8px;'>";
                    html += "<label class='col-md-2 col-sm-2 col-xs-12 control-label'>" + lableValue + "</label>";
                    html += "<div class='col-md-10 col-sm-10 col-xs-12 input-group'>";
                    html += "<input type='text' required class='form-control'" + "name='" + item + "' value='" + strs[i] + "'>";
                    html += "<span class='input-group-btn'>" + "<input class='btn btn-warning' type='button' value='X' onclick='matchingRecreation.prototype.cleanHTMLData(this)'>" + "</span>";
                    html += "</div>";
                    html += "</div>";
                    $("#" + matchingRecreation.prototype.config().frm + " .name").append(html);
                }
            }
        }
    })();

</script>

<div id="divBoxMatchingRecreation" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">休闲娱乐</h3>
            </div>
            <form id="frmMatchingRecreation" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            休闲娱乐距离<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="distance"
                                                    class="form-control search-select select2 distance">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <%--<div class="form-group">--%>
                                <%--<div class="x-valid">--%>
                                <%--<label class="col-sm-2 control-label">--%>
                                <%--休闲娱乐档次--%>
                                <%--</label>--%>
                                <%--<div class="col-sm-10">--%>
                                <%--<select required="required" name="grade" class="form-control search-select select2 grade">--%>
                                <%--</select>--%>
                                <%--</div>--%>
                                <%--</div>--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            休闲娱乐类别<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="category"
                                                    class="form-control search-select select2 category">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            休闲娱乐名称
                                        </label>
                                        <div class="col-sm-10">
                                            <button class="btn btn-xs btn-success"
                                                    onclick="matchingRecreation.prototype.appendHTML('name',this)"><i
                                                    class="fa fa-plus"></i></button>
                                        </div>
                                    </div>
                                </div>

                                <div style="margin-bottom: 8px;" class="name">
                                    <div class="form-group" style=" margin-top: 8px;">
                                        <label class="col-md-2 col-sm-2 col-xs-12 control-label">休闲娱乐名称</label>
                                        <div class="col-md-10 col-sm-10 col-xs-12 input-group">
                                            <input class="form-control" name="name" required="required" type="text">
                                            <span class="input-group-btn"><input type="button" class="btn btn-warning"
                                                                                 value="X"
                                                                                 onclick="matchingRecreation.prototype.cleanHTMLData(this)"></span>
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
                    <button type="button" class="btn btn-primary" onclick="matchingRecreation.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>