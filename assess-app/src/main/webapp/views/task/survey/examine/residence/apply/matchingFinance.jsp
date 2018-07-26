<%--
  金融服务
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
        <h3>金融服务信息 <button type="button" class="btn btn-success" onclick="matchingFinance.prototype.showModel()"
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
                <table class="table table-bordered" id="MatchingFinanceList">
                    <!-- cerare document add ajax data-->
                </table>
            </div>
        </div>
    </form>
</div>
</body>


<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">

    var matchingFinance = function () {

    };
    matchingFinance.prototype = {
        config:function () {
            var data = {};
            data.table = "MatchingFinanceList" ;
            data.box = "divBoxMatchingFinance";
            data.frm = "frmMatchingFinance";
            data.type = "null" ;//
            return data;
        },
        loadDataDicList:function () {
            var cols = [];
            cols.push({field: 'name', title: '金融名称'});
            cols.push({field: 'categoryName', title: '金融类别'});
            cols.push({field: 'natureName', title: '金融机构性质'});
            cols.push({field: 'serviceContentName', title: '金融服务内容'});
            cols.push({field: 'autoServiceContent', title: '自动服务内容'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="matchingFinance.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="matchingFinance.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#"+matchingFinance.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingFinance.prototype.config().table, "${pageContext.request.contextPath}/examineMatchingFinance/getExamineMatchingFinanceList", cols, {
                type:matchingFinance.prototype.config().type
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
                url:"${pageContext.request.contextPath}/examineMatchingFinance/deleteExamineMatchingFinanceById",
                type: "post",
                dataType: "json",
                data: {id:id},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('删除成功');
                        matchingFinance.prototype.loadDataDicList();
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
            $("#"+matchingFinance.prototype.config().frm).clearAll();
            $("#"+matchingFinance.prototype.config().frm+" .type").val(matchingFinance.prototype.config().type);
            $('#'+matchingFinance.prototype.config().box).modal("show");
        },
        saveData:function () {
            if (!$("#"+matchingFinance.prototype.config().frm).valid()){
                return false;
            }
            var data = formParams(matchingFinance.prototype.config().frm);
            if ($("#declareId").size() > 0){
                data.declareId = $("#declareId").val();
            }
            if ($("#examineType").size() > 0){
                data.examineType = $("#examineType").val();
            }
            $.ajax({
                url:"${pageContext.request.contextPath}/examineMatchingFinance/saveAndUpdateExamineMatchingFinance",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#'+matchingFinance.prototype.config().box).modal('hide');
                        matchingFinance.prototype.loadDataDicList();
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
                url:"${pageContext.request.contextPath}/examineMatchingFinance/getExamineMatchingFinanceById",
                type: "get",
                dataType: "json",
                data: {id:id},
                success: function (result) {
                    if (result.ret) {
                        $("#"+matchingFinance.prototype.config().frm).clearAll();
                        $("#" + matchingFinance.prototype.config().frm).initForm(result.data);
                        if (result.data.serviceContent == null || result.data.serviceContent == ''){
                            $("#"+matchingFinance.prototype.config().frm+" .serviceContent").val(null).trigger("change");
                        }else {
                            $("#"+matchingFinance.prototype.config().frm+" .serviceContent").val(result.data.serviceContent).trigger("change");
                        }
                        if (result.data.nature == null || result.data.nature == ''){
                            $("#"+matchingFinance.prototype.config().frm+" .nature").val(null).trigger("change");
                        }else {
                            $("#"+matchingFinance.prototype.config().frm+" .nature").val(result.data.nature).trigger("change");
                        }
                        if (result.data.category == null || result.data.category == ''){
                            $("#"+matchingFinance.prototype.config().frm+" .category").val(null).trigger("change");
                        }else {
                            $("#"+matchingFinance.prototype.config().frm+" .category").val(result.data.category).trigger("change");
                        }
                        $('#'+matchingFinance.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init:function () {
            $.ajax({
                url:"${pageContext.request.contextPath}/examineMatchingFinance/estate_finance_service_content",
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
                            $("#"+matchingFinance.prototype.config().frm+" .serviceContent").html(option);
                            $("#"+matchingFinance.prototype.config().frm+" .serviceContent").select2({ minimumResultsForSearch: -1 });//加载样式
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
            $.ajax({
                url:"${pageContext.request.contextPath}/examineMatchingFinance/estate_finance_nature",
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
                            $("#"+matchingFinance.prototype.config().frm+" .nature").html(option);
                            $("#"+matchingFinance.prototype.config().frm+" .nature").select2({ minimumResultsForSearch: -1 });//加载样式
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
            $.ajax({
                url:"${pageContext.request.contextPath}/examineMatchingFinance/estate_finance_category",
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
                            $("#"+matchingFinance.prototype.config().frm+" .category").html(option);
                            $("#"+matchingFinance.prototype.config().frm+" .category").select2({ minimumResultsForSearch: -1 });//加载样式
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
        matchingFinance.prototype.loadDataDicList();
        matchingFinance.prototype.init();
    })

</script>

<div id="divBoxMatchingFinance" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">金融服务</h3>
            </div>
            <form id="frmMatchingFinance" class="form-horizontal">
                <input type="hidden"  name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            金融名称
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="name" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            自动服务内容
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="autoServiceContent" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            金融类别
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
                                            金融机构性质
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="nature" class="form-control search-select select2 nature">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            金融服务内容
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="serviceContent" class="form-control search-select select2 serviceContent">
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
                    <button type="button" class="btn btn-primary" onclick="matchingFinance.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>