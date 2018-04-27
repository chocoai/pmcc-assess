<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body class="nav-md footer_fixed">
<%--<%@include file="share/main_head.jsp" %>--%>
<!-- start: MAIN CONTAINER -->
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
                                <label class="col-sm-1 control-label">
                                    评估假设
                                </label>
                                <div class="col-sm-2">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="评估假设名称" id="queryName" name="queryName"
                                           class="form-control">
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
<div id="divBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">评估假设</h4>
            </div>
            <form id="frm" class="form-horizontal">
                <input type="hidden" id="id" name="id" value="0">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            不适用原因
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea placeholder="请填写不适用原因" class="form-control" id="notApplicableReason" name="notApplicableReason">

                                            </textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            评估假设
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="method" class="form-control" id="method">
                                                <option value="">请选择</option>
                                                <c:forEach items="${useList}" var="item">
                                                    <option value="${item.id}">${item.name}</option>
                                                </c:forEach>
                                            </select>

                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            适用原因
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea placeholder="请填写适用原因" class="form-control" id="applicableReason" name="applicableReason">

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
            <input type="hidden" name="methodIdN" id="methodIdN">
            <div class="panel-body">
        <span id="toolbarSub">
            <button type="button" class="btn btn-success" onclick="addMethodField(id)"
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
                <h4 class="modal-title" id="titleContent2">适用与不适用字段数据</h4>
            </div>
            <form id="firSubA">
            <div class="panel-body">
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">
                            适用与不适用
                            <input type="hidden" name="methodId" id="methodId">
                        </label>
                        <div class="col-sm-10" id="type">
                            <input type="radio" name="type" value="0" checked="checked">适用原因
                            <br>
                            <input type="radio" name="type" value="1">不适用原因
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">
                            字段名称
                        </label>
                        <div class="col-sm-10">
                            <input type="text" id="name" name="name" class="form-control">
                        </div>
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
    </div>
</div>


<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">

    $(function () {
        loadDataDicList();
    })
    //加载 评估假设 数据列表
    function loadDataDicList() {
        var cols = [];
        cols.push({field: 'methodStr', title: '评估假设'});
        cols.push({field: 'applicableReason', title: '适用原因模板'});

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success" href="javascript:setSubDataDic(' + row.id + ');" >查看选项</i></a>';
                str += '<a class="btn btn-xs btn-success" href="javascript:addMethodField(' + row.id + ');" >新增字段</i></a>';
                str += '<a class="btn btn-xs btn-warning" href="javascript:removeData(' + row.id + ',\'tb_List\')">删除</a>';
                str += '<a class="btn btn-xs btn-warning" href="javascript:editHrProfessional(' + row.id + ',\'tb_List\')">修改</a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_List").bootstrapTable('destroy');
        var methodStrChange = $("#queryName").val();
        TableInit("tb_List", "${pageContext.request.contextPath}/evaluationThinking/list", cols, {
            methodStr: methodStrChange
        }, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    }

    //删除 评估假设 数据()
    function removeData(id, tbId) {
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/evaluationThinking/delete",
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

    //对新增 评估假设 数据处理
    function addDataDic() {
        $("#frm").clearAll();
    }
    //新增 评估假设 数据
    function saveSubDataDic() {
        var flag = false;
        var data = formParams("frm");
        data.id = $("#id").val();
        data.applicableReason = $("#applicableReason").val();
        data.method = $("#method option:selected").val()-120;
        alert(data.method);
        data.notApplicableReason = $("#notApplicableReason").val();
        if ($("#frm").valid()) {
            $.ajax({
                url: "${pageContext.request.contextPath}/evaluationThinking/save",
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
    //评估假设修改
    function editHrProfessional(index) {
        $.ajax({
            url: "${pageContext.request.contextPath}/evaluationThinking/get",
            type: "GET",
            dataType: "json",
            data: {id: index},
            success: function (result) {
                Loading.progressHide();
                $('#divBox').modal();
                $("#id").val(result.id);
                $("#name").val(result.name);
                $("#notApplicableReason").val(result.notApplicableReason);
                $("#applicableReason").val(result.applicableReason);
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }
    
    //新增 子项 字段数据
    function addMethodField(id) {
        $("#firSub").clearAll();
        $('#firSub').modal();
        var methodId = document.getElementById("methodId");
        methodId.value = id;
        if (id==null || id=='' || id==0 ){//说明是从选子项添加的
            var methodIdN = document.getElementById("methodIdN");
            methodId.value = methodIdN.value
        }
    }
    //保存新增 子项 字段的数据
    function saveFileld() {
        //firSubA
        var data = formParams("firSubA");
        data.name = $("#name").val();
        data.methodId = $("#methodId").val();
        var typeId = document.getElementById("type");
        var radio = typeId.getElementsByTagName("input");
        for(var i=0;i < radio.length;i++){
            if(radio[i].checked) {//为radio中选中的值
                data.type = radio[i].value
            }
        }
        alert(data.type);
        if ($("#firSubA").valid()){
            $.ajax({
                url: "${pageContext.request.contextPath}/evaluationThinkingNG/addField",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    toastr.success('保存成功');
                    window.location.reload();//自动刷新
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
        var methodIdN = document.getElementById("methodIdN");
        methodIdN.value = pid;
        TableInit("tbDataDicList", "${pageContext.request.contextPath}/evaluationThinkingNG/listField",
            cols, {methodId: pid}, {
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

    //设置子项数据
    function setSubDataDic(pid) {
        $("#divSubDataDic").modal();//显示
        loadSubDataDicList(pid, function () {
            $('#divSubDataDic').modal("show");
        });
    }

    //删除 子项 子项
    function delDataDic(id) {
        Alert("确认要删除么？", 2, null, function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/evaluationThinkingNG/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        window.location.reload();//自动刷新
                        $('#' + id).bootstrapTable("refresh");
                        loadSubDataDicList();//重载 (刷新)
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
    

</script>


</html>
