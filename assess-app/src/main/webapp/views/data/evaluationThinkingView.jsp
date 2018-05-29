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
                                    评估技术思路
                                </label>
                                <div class="col-sm-2">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="技术思路 名称" id="queryName" name="queryName"
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
<div id="divBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">评估技术思路</h3>
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
                                            名称
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="name" id="name"
                                                   placeholder="请填写名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            评估方法
                                        </label>
                                        <div class="col-sm-10" id="method">
                                            <c:forEach items="${useList}" var="item">
                                                    <span class="checkbox-inline">
                                                    <input type="checkbox" name="method"
                                                           value="${item.id}"><label>${item.name}</label>
                                                    </span>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            适用原因模板
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea placeholder="请填写适用原因" class="form-control" id="applicableReason"
                                                      name="applicableReason" required="required" onkeyup="extractApplicableField();">
                                            </textarea>
                                            <div class="applicableReason-field">

                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            不适用原因模板
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea placeholder="请填写不适用原因" class="form-control"
                                                      id="notApplicableReason" name="notApplicableReason"
                                                      required="required" onkeyup="extractNotApplicableField();">
                                            </textarea>
                                            <div class="not-applicableReason-field">

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default" onclick="removeSubDataDic()">
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
<div id="divSubDataDic" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="titleContent">子项数据</h3>
            </div>
            <input type="hidden" name="thinkingIdN" id="thinkingIdN">
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
<div id="firSub" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="titleContent2">字段</h3>
            </div>
            <form id="firSubA">
                <div class="panel-body">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                适用与不适用
                                <input type="hidden" name="thinkingId" id="thinkingId">
                            </label>
                            <div class="col-sm-10">
                                <select id="type" class="form-control" required="required">
                                    <option value="0" selected="selected">适用原因</option>
                                    <option value="1">不适用原因</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">.</div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                字段名称
                            </label>
                            <div class="col-sm-10">
                                <input type="text" id="nameA" name="nameA" class="form-control" required="required">
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
    var field = null;
    var Nofield = null;
    //提取显示适用的字段
    function extractApplicableField() {
        var text=$("#applicableReason").val();
        $('.applicableReason-field').empty();
        var fieldArray = AssessCommon.extractField(text);
        if(fieldArray&&fieldArray.length>0){
            var html='';
            $.each(fieldArray,function (i,item) {
                field  = fieldArray;
                html+='<span class="label label-default">'+item+'</span> ';
            })
            $('.applicableReason-field').append(html);
        }
    }

    //提取显示不适用的字段
    function extractNotApplicableField() {
        var text=$("#notApplicableReason").val();
        $('.not-applicableReason-field').empty();
        var fieldArray = AssessCommon.extractField(text);
        if(fieldArray&&fieldArray.length>0){
            var html='';
            $.each(fieldArray,function (i,item) {
                Nofield  = fieldArray;
                html+='<span class="label label-default">'+item+'</span> ';
            })
            $('.not-applicableReason-field').append(html);
        }
    }

    function fieldExtract(result) {
        var str = "";
        for (var i = 0;i<result.length;i++){
            if (i == result.length-1){
                str += result[i];
            }else {
                str += result[i] +",";
            }
        }
        return str;
    }

    //加载 评估技术思路 数据列表
    function loadDataDicList() {
        var cols = [];
        cols.push({field: 'name', title: '名称'});
        cols.push({field: 'methodStr', title: '评估方法'});

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                // str += '<a class="btn btn-xs btn-info tooltips"  data-placement="top" data-original-title="查看选项" onclick="setSubDataDic(' + row.id + ');" ><i class="fa fa-bars fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="editHrProfessional(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
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
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }

    //删除 评估技术思路 数据()
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

    //取消 新增 评估技术思路
    function removeSubDataDic() {
        field = null;
        Nofield = null;
        $("#divBox").hide();
    }
    //对新增 评估技术思路 数据处理
    function addDataDic() {
        $("#frm").clearAll();
    }
    //新增 评估技术思路 数据
    function saveSubDataDic() {
        var data = formParams("frm");
        data.field = fieldExtract(field);
        data.Nofield = fieldExtract(Nofield);
        data.method = ',' + data.method + ',';//方便like查询
        data.field = fieldExtract(field);
        data.Nofield = fieldExtract(Nofield);
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
    //评估技术思路 修改
    function editHrProfessional(index) {
        $.ajax({
            url: "${pageContext.request.contextPath}/evaluationThinking/get",
            type: "GET",
            dataType: "json",
            data: {id: index},
            success: function (result) {
                Loading.progressHide();
                $('#divBox').modal();
                var methodstr = ""+ result.method +"";
                var methodArr = methodstr.split(",");
                for (var j = 0;j<methodArr.length;j++){
                    $("#method input[name='method']").each(function () {
                        var str = methodArr[j];
                        if (str!=''){
                            if (str == $(this).val()){
                                $(this).attr("checked",true);
                            }
                        }
                    });

                }
                $("#id").val(result.id);
                $("#name").val(result.name);
                $("#notApplicableReason").val(result.notApplicableReason);
                $("#applicableReason").val(result.applicableReason);
                extractApplicableField();
                extractNotApplicableField();
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
        var thinkingId = document.getElementById("thinkingId");
        thinkingId.value = id;
        if (id == null || id == '' || id == 0) {//说明是从选子项添加的
            var thinkingIdN = document.getElementById("thinkingIdN");
            thinkingId.value = thinkingIdN.value
        }
    }
    //保存新增 子项 字段的数据
    function saveFileld() {
        //firSubA
        var data = formParams("firSubA");
        data.name = $("#nameA").val();
        data.thinkingId = $("#thinkingId").val();
        data.type = $("#type option:selected").val();
        if ($("#firSubA").valid()) {
            $.ajax({
                url: "${pageContext.request.contextPath}/evaluationThinkingNG/addField",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        console.info(result);
                        toastr.success('保存成功');
                        $('#firSub').modal('hide');//隐藏
                        var thinkingIdN = document.getElementById("thinkingIdN").value;
                        setSubDataDic(thinkingIdN);
                    } else {
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
        cols.push({field: 'creator', title: '创建人'});
        cols.push({
            field: 'id', title: '操作', width: 200, formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning" href="javascript:delDataDic(' + row.id + ',\'tbDataDicList\')"><i class="fa fa-trash-o"></i>删除</a>';
                str += '</div>';
                return str;
            }
        });
        var thinkingIdN = document.getElementById("thinkingIdN");
        thinkingIdN.value = pid;
        $("#tbDataDicList").bootstrapTable("destroy");
        TableInit("tbDataDicList", "${pageContext.request.contextPath}/evaluationThinkingNG/listField",
            cols, {thinkingId: pid}, {
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
                url: "${pageContext.request.contextPath}/evaluationThinkingNG/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        var thinkingIdN = document.getElementById("thinkingIdN").value;
                        setSubDataDic(thinkingIdN);
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
