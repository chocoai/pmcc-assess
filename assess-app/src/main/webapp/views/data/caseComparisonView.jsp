<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body class="nav-md footer_fixed">
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
                                    案例对比
                                </label>
                                <div class="col-sm-2">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="案例对比 名称" id="queryName" name="queryName"
                                           class="form-control">
                                </div>
                            </div>

                            <div class="col-sm-3">
                                <button type="button" class="btn btn-primary" onclick="loadDataDicList()">
                                    查询
                                </button>

                                <button type="button" class="btn btn-success" onclick="addDataDic()"
                                        data-toggle="modal" href="#divBox"> 新增
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
                <h3 class="modal-title">案例对比配置</h3>
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
                                            合并名称
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="name" id="name" placeholder="合并名称" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            说明
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea placeholder="请填写说明" class="form-control" id="uExplain" name="uExplain" required="required">

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
                <h3 class="modal-title" id="titleContent">子项数据</h3>
            </div>
            <input type="hidden" name="caseId" id="caseId">
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
<div id="firSub" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true" >
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="titleContent2">字段</h3>
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
                                        <input type="hidden" name="caseId" id="caseIdNG">
                                    </label>
                                    <div class="col-sm-10">
                                        <input type="text" id="nameA" name="nameA" class="form-control" required="required">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                        表
                                    </label>
                                    <div class="col-sm-10">
                                        <select class="form-control" name="tableName" id="tableName" required="required">
                                            <option>请选择</option>
                                            <c:forEach items="${userList}" var="tableVar">
                                                <option value="${tableVar.key}">${tableVar.key}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                        表字段
                                    </label>
                                    <div class="col-sm-10">
                                        <select class="form-control" name="TableField" id="TableField" required="required">
                                            <option>请选择</option>
                                        </select>
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
    //加载 案例对比配置 数据列表
    function loadDataDicList() {
        var cols = [];
        cols.push({field: 'name', title: '名称'});
        cols.push({field: 'uExplain', title: '说明'});
        cols.push({field: 'typeStr', title: '类型'});

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-info tooltips" data-placement="top" data-original-title="查看选项"  onclick="setSubDataDic(' + row.id + ');" ><i class="fa fa-bars fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑"  onclick="editHrProfessional(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除"  onclick="removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_List").bootstrapTable('destroy');
        var queryName = $("#queryName").val();
        TableInit("tb_List", "${pageContext.request.contextPath}/caseComparison/list", cols, {
            methodStr: queryName
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess:function() {
                $('.tooltips').tooltip();
            }
        });
    }

    //删除 案例对比配置 数据()
    function removeData(id, tbId) {
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/caseComparison/delete",
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

    //对新增 案例对比配置 数据处理
    function addDataDic() {
        $("#frm").clearAll();
    }
    //新增 案例对比配置 数据
    function saveSubDataDic() {
        var flag = false;
        var data = formParams("frm");
        data.id = $("#id").val();
        data.name = $("#name").val();
        data.uExplain = $("#uExplain").val();
        console.info(data.type);
        if ($("#frm").valid()) {
            $.ajax({
                url: "${pageContext.request.contextPath}/caseComparison/save",
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
    //案例对比配置 修改
    function editHrProfessional(index) {
        $.ajax({
            url: "${pageContext.request.contextPath}/caseComparison/get",
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

    function appendChildElement(item) {
        var TableField = $("#TableField");
        var TableFieldElement = document.getElementById("TableField");
        var len = item.length;
        for (var i = 0 ; i < len;i++){
            var optionLen = $("#TableField option").size();
            var fieldElment = document.createElement("option");
            fieldElment.setAttribute("value",item[i].key);
            fieldElment.appendChild(document.createTextNode(item[i].key));
            TableFieldElement.appendChild(fieldElment);

        }
        console.info(len);
    }
    function removeChild() {
        var optionLen = $("#TableField option").size();
        if (optionLen>2){
            $("#TableField option").remove();//当大于2时 应该是已经选择一次了 所以删除元素
        }
    }
    function tableNameA() {
        $("#tableName").change(function () {
            //检测  然后操作
            removeChild();
            var selected= $(this).children('option:selected').val();
            var data = "tableName="+selected;
            $.ajax({
                url: "${pageContext.request.contextPath}/caseComparisonNG/listTableField",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    console.info(result);
                    appendChildElement(result);
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        });
    }
    tableNameA();
//    $("#firSub .form-group").css({"margin-top":"20px","margin-bottom":"40px"});

    //新增 子项 字段数据
    function addMethodField() {
        var caseId = document.getElementById("caseId");
        $("#firSub").clearAll();
        $('#firSub').modal();
        var caseIdNG = document.getElementById("caseIdNG");
        caseIdNG.value = caseId.value;
    }
    //保存新增 子项 字段的数据
    function saveFileld() {
        var data = formParams("firSubA");//应该是自动form参数
        data.uName = $("#nameA").val();
//        data.caseId = $("#caseId").val();
        data.tableName = $("#tableName option:selected").val();
        data.name = $("#TableField option:selected").val();
        if ($("#firSubA").valid()){
            $.ajax({
                url: "${pageContext.request.contextPath}/caseComparisonNG/addField",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    console.info(result);
                    if (result.ret){
                        toastr.success('保存成功');
                        $('#firSub').modal('hide');//隐藏
                        var caseId = document.getElementById("caseId").value;
                        setSubDataDic(caseId);
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
        cols.push({field: 'uName', title: '用户自定义名称'});
        cols.push({
            field: 'id', title: '操作', width: 200, formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning" href="javascript:delDataDic(' + row.id + ',\'tbDataDicList\')"><i class="fa fa-trash-o"></i>删除</a>';
                str += '</div>';
                return str;
            }
        });
        var caseId = document.getElementById("caseId");
        caseId.value = pid;
        $("#tbDataDicList").bootstrapTable("destroy");
        TableInit("tbDataDicList", "${pageContext.request.contextPath}/caseComparisonNG/listField",
            cols, {caseId: pid}, {
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
        $("#tbDataDicList").clearAll();//清除数据
        loadSubDataDicList(pid, function () {
            $('#divSubDataDic').modal("show");
        });
    }

    //删除 子项 子项
    function delDataDic(id) {
        Alert("确认要删除么？", 2, null, function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/caseComparisonNG/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        var caseId = document.getElementById("caseId").value;
                        setSubDataDic(caseId);
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
