<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body class="nav-md footer_fixed">
<!-- 列表 -->
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="x_panel">
                <div class="x_title">
                    <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                        ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frmQuery" class="form-horizontal">
                        <div class="form-group ">
                            <div>
                                <label class="col-sm-1 control-label">报告类别</label>
                                <div class="col-sm-2">
                                    <select required class="form-control" id="queryName">
                                    <option value="">请选择</option>
                                    <c:forEach items="${categoryList}" var="item">
                                        <option value="${item.id}">${item.name}</option>
                                    </c:forEach>
                                </select>
                                </div>
                            </div>

                            <div class="col-sm-3">
                                <button type="button" class="btn btn-primary" onclick="loadDataDicList()">
                                    查询
                                </button>

                                <button type="button" class="btn btn-success" onclick="addDataDic()"
                                        data-toggle="modal" href="#divBox"> 新增数据
                                </button>
                            </div>
                        </div>

                    </form>
                    <table class="table table-bordered" id="tb_List">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<!-- 添加 -->
<div id="divBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">报告分析配置</h4>
            </div>
            <form id="frm" class="form-horizontal">
                <input type="hidden" id="id" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            报告分析类别
                                        </label>
                                        <div class="col-sm-10">
                                            <select required class="form-control" onchange="getFieldByPid()" name="category" id="category">
                                                <option value="">请选择</option>
                                                <c:forEach items="${categoryList}" var="item">
                                                    <option value="${item.id}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            字段
                                        </label>
                                        <div class="col-sm-10" id="method">

                                            <select id="categoryField" name="categoryField" class="form-control" required="required">
                                                <option value="0">请选择</option>
                                            </select>

                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            模版
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea placeholder="请填写模版" class="form-control" id="template" name="template" required="required">

                                            </textarea>
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
                    <button type="button" class="btn btn-primary" onclick="saveSubDataDic()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 显示子项列表 -->
<div id="divSubDataDic" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="titleContent">子项数据</h4>
            </div>
            <input type="hidden" name="analysisNumber" id="analysisNumber">
            <div class="panel-body">
        <span id="toolbarSub">
            <button type="button" class="btn btn-success" onclick="addMethodField()"
                    data-toggle="modal" href="#divSubDataDicManage"> 新增
            </button>
        </span>
                <table class="table table-bordered" id="tbDataDicList">
                </table>
            </div>
        </div>
    </div>
</div>


<!-- 子项数据 添加 ===========-->
<div id="firSub" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="titleContent2">字段</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <form id="firSubA" name="firSubA" class="form-horizontal">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            字段名称
                                            <input type="hidden" name="analysisId" id="analysisId">
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" id="name" name="name" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                            <div class="modal-footer">
                                <button type="button" data-dismiss="modal" class="btn btn-default">
                                    取消
                                </button>
                                <button type="button" class="btn btn-primary" onclick="saveFileld()">
                                    保存
                                </button>
                            </div>
                        </form>
                    </div>

                </div><!--row -->
            </div>
        </div>
    </div>
</div>


<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">

    $(function () {
        loadDataDicList();
    })
    //加载报告分析数据列表
    function loadDataDicList() {
        var cols = [];
        cols.push({field: 'categoryName', title: '类别'});
        cols.push({field: 'categoryFieldName', title: '字段'});
        cols.push({field: 'template', title: '模版'});

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success" href="javascript:setSubDataDic(' + row.id + ');" >查看选项</i></a>';
                str += '<a class="btn btn-xs btn-warning" href="javascript:removeData(' + row.id + ',\'tb_List\')">删除</a>';
                str += '<a class="btn btn-xs btn-warning" href="javascript:editReportAnalysis(' + row.id + ',\'tb_List\')">修改</a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_List").bootstrapTable('destroy');
        TableInit("tb_List", "${pageContext.request.contextPath}/reportAnalysis/getList", cols, {
            keyWord: $("#queryName").val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    }

    //删除报告分析数据
    function removeData(id, tbId) {
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/reportAnalysis/deleteReportAnalysis",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        loadDataDicList();//重载 (刷新)
                        $('#' + tbId).bootstrapTable("refresh");
                    }
                    else {
                        Alert("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        })
    }

    //清除新增表单缓存
    function addDataDic() {
        $("#categoryField").html("");
        $("#frm").clearAll();
    }
    //新增报告分析数据
    function saveSubDataDic() {
        var flag = false;
        var data = formParams("frm");
        if ($("#frm").valid()) {
            $.ajax({
                url: "${pageContext.request.contextPath}/reportAnalysis/save",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        loadDataDicList();
                        $('#divBox').modal('hide');
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        }
    }
    //修改
    function editReportAnalysis(index) {
        $.ajax({
            url: "${pageContext.request.contextPath}/reportAnalysis/get",
            type: "GET",
            dataType: "json",
            data: {id: index},
            success: function (result) {
                Loading.progressHide();
                $("#frm").clearAll();
                $('#divBox').modal();
                $("#id").val(result.id);
                getFieldByPid(result.category);
                $("#category").val(result.category);
                $("#categoryField").val(result.categoryField);
                $("#template").val(result.template);
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }
    /**
     * 根据类别获取子项字段
     */
    function getFieldByPid(id){
        var pid = $("#category").val();
        if(pid == null || pid == ""){
            pid = id;
        }
        $("#categoryField").html("");
        $.ajax({
            url: "${pageContext.request.contextPath}/reportAnalysis/getFieldByPid",
            type: "post",
            dataType: "json",
            data:{pid:pid},
            success: function (result) {
                for(var i =0;i<result.data.length;i++){
                    $("#categoryField").append("<option value='"+result.data[i].id+"'>"+result.data[i].name+"</option>");
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }








    //新增 子项 字段数据
    function addMethodField() {
        $("#firSub").clearAll();
        $('#firSub').modal();
    }
    //保存新增 子项 字段的数据
    function saveFileld() {
        var data = formParams("firSubA");//应该是自动form参数
        data.analysisId = $("#analysisNumber").val();
        if ($("#firSubA").valid()){
            $.ajax({
                url: "${pageContext.request.contextPath}/reportAnalysisField/addField",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    console.info(result);
                    if (result.ret){
                        toastr.success('保存成功');
                        $('#firSub').modal('hide');//隐藏
                        var analysisId = $("#analysisNumber").val();
                        setSubDataDic(analysisId);
                    }else {
                        toastr.success('调用服务端方法失败');
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        }
    }

    //加载子项节点数据
    function loadSubDataDicList(pid, fn) {
        var cols = [];
        cols.push({field: 'name', title: '名称'});
        cols.push({
            field: 'id', title: '操作', width: 200, formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning" href="javascript:delDataDic(' + row.id + ',\'tbDataDicList\')"><i class="fa fa-trash-o"></i>删除</a>';
                str += '</div>';
                return str;
            }
        });
        $("#analysisNumber").val(pid);
        $("#tbDataDicList").bootstrapTable("destroy");
        TableInit("tbDataDicList", "${pageContext.request.contextPath}/reportAnalysisField/getFieldList",
            cols, {pid: pid}, {
                showRefresh: false,                  //是否显示刷新按钮
                toolbar: '#toolbarSub',
                uniqueId: "id",
                onLoadSuccess: function () {
                    if (fn) {
                        fn();
                    }
                }
            });
    }


    //删除 子项 子项
    function delDataDic(id) {
        Alert("确认要删除么？", 2, null, function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/reportAnalysisField/deleteField",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        var analysisId = $("#analysisNumber").val();
                        setSubDataDic(analysisId);
                    }
                    else {
                        Alert("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        })
    }
    //设置子项数据
    function setSubDataDic(pid) {
        $("#divSubDataDic").modal();//显示
        $("#tbDataDicList").clearAll();//清除数据
        loadSubDataDicList(pid, function () {
            $('#divSubDataDic').modal("show");
        });
    }














    function isNot(val) {
        if (val!=null){
            if (val!=''){
                return true;
            }
        }
        return false;
    }


</script>


</html>
