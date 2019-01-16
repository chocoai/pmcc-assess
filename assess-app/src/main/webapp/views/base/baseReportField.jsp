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
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
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
                                    名称
                                </label>
                                <div class="col-sm-2">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="名称" id="queryName" name="queryName"
                                           class="form-control">
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">
                                    字段名称
                                </label>
                                <div class="col-sm-2">
                                    <input type="text" data-rule-number="true" data-rule-maxlength="50"
                                           placeholder="字段名称" id="queryFieldName" name="queryFieldName"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <button type="button" class="btn btn-primary" onclick="reloadDataDicList()">
                                    查询
                                </button>
                                <%--<button type="button" class="btn btn-primary" onclick="queryReset()">--%>
                                <%--重置--%>
                                <%--</button>--%>
                                <button type="button" class="btn btn-success" onclick="addDataDic()"
                                        data-toggle="modal" href="#divBox"> 新增
                                </button>
                            </div>
                        </div>

                    </form>
                    <table class="table table-bordered" id="tb_List">

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
                <h4 class="modal-title">报告字典管理</h4>
            </div>
            <form id="frm" class="form-horizontal">
                <input type="hidden" id="id" name="id" value="0">
                <input type="hidden" id="pid" name="pid">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" required data-rule-maxlength="50" placeholder="名称"
                                                   id="name" name="name" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            替换类型<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-9">
                                            <select id="replaceType" name="replaceType" class="form-control"
                                                    required>
                                                <option value="default" selected="selected">默认选择</option>
                                                <c:forEach items="${replaceType}" var="item">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            文本key<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" data-rule-maxlength="50" placeholder="文本key"
                                                   id="fieldName" name="fieldName" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            是否启用
                                        </label>
                                        <div class="col-sm-9">
                                            <label class="radio-inline">
                                                <input type="checkbox" id="bisEnable" name="bisEnable" value="true"
                                                       checked="checked">
                                            </label>

                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            排序
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" data-rule-digits="true" placeholder="排序"
                                                   id="sorting" name="sorting" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            自定义key
                                        </label>
                                        <div class="col-sm-9">
                                    <textarea placeholder="自定义key" id="remark" name="remark"
                                              class="form-control"></textarea>
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
                    <button type="button" class="btn btn-primary" onclick="saveDataDic()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!--子项管理-->
<div id="divSubDataDic" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="titleContent">报告子项数据</h3>
            </div>
            <div class="panel-body">
        <span id="toolbarSub">
            <button type="button" class="btn btn-success" onclick="addSubDataDic()"
                    data-toggle="modal" href="#divSubDataDicManage"> 新增
            </button>
        </span>
                <table class="table table-bordered" id="tbDataDicList">
                </table>
            </div>
        </div>
    </div>
</div>
<div id="divSubDataDicManage" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">报告子项管理</h3>
            </div>
            <input type="hidden" id="mainId" name="mainId" value="0">
            <form id="frmSub" class="form-horizontal">
                <input type="hidden" id="subId" name="id" value="0">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div>
                                        <label class="col-sm-3 control-label">
                                            名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-8">
                                            <input type="text" required data-rule-maxlength="50" placeholder="名称"
                                                   id="subName" name="name" class="form-control">
                                        </div>
                                        <div class="col-sm-1">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            替换类型<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-8">
                                            <select id="subReplaceType" name="replaceType" class="form-control"
                                                    required>
                                                <c:forEach items="${replaceType}" var="item">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            文本key
                                        </label>
                                        <div class="col-sm-8">
                                            <input type="text" data-rule-maxlength="100" placeholder="文本key"
                                                   id="subFieldName" name="fieldName" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div>
                                        <label class="col-sm-3 control-label">
                                            是否启用<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-8">
                                            <label class="radio-inline">
                                                <input type="checkbox" id="subBisEnable" name="bisEnable" value="true"
                                                       checked="checked">
                                            </label>
                                        </div>
                                        <div class="col-sm-1">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            排序
                                        </label>
                                        <div class="col-sm-8">
                                            <input type="text" data-rule-digits="true" placeholder="排序"
                                                   id="subSorting" name="sorting" class="form-control">
                                        </div>
                                        <div class="col-sm-1">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            自定义key
                                        </label>
                                        <div class="col-sm-8">
                                    <textarea placeholder="自定义key" id="subRemark" name="remark"
                                              class="form-control"></textarea>
                                        </div>
                                        <div class="col-sm-1">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            模板
                                        </label>
                                    </div>
                                    <div class="col-sm-8">
                                        <div id="baseReportField_id_File"></div>
                                        <div id="_baseReportField_id_File"></div>
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
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    $(function () {
        loadDataDicList();
    });

    //加载代理数据列表
    function loadDataDicList() {
        var cols = [];
        cols.push({field: 'name', title: '名称'});
        cols.push({
            field: 'bisEnable', title: '是否启用', formatter: function (value) {
                return getBoolChs(value);
            }
        });
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success" href="javascript:setSubDataDic(' + row.id + ');" >查看子项</i></a>';
                str += '<a class="btn btn-xs btn-success" href="javascript:editDataDic(' + row.id + ');" >编辑</i></a>';
                str += '<a class="btn btn-xs btn-warning" href="javascript:delDataDic(' + row.id + ',\'tb_List\')">删除</a>';
                str += '</div>';
                return str;
            }
        });
        TableInit("tb_List", "${pageContext.request.contextPath}/baseReportField/getBaseReportFieldList", cols, {
            fieldName: $("#queryFieldName").val(),
            name: $("#queryName").val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    }

    //刷新数据列表
    function reloadDataDicList() {
        var opt = {
            url: "${pageContext.request.contextPath}/baseReportField/getBaseReportFieldList",
            query: {
                fieldName: $("#queryFieldName").val(),
                name: $("#queryName").val()
            }
        };
        $("#tb_List").bootstrapTable("refresh", opt);
    }

    //保存数据
    function saveDataDic() {
        if ($("#frm").valid()) {
            var data = formParams("frm");
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/baseReportField/saveBaseReportField",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('保存成功');
                        TableReload("tb_List");
                        $('#divBox').modal('hide');
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        }
    }

    //新增字典数据
    function addDataDic() {
        $("#frm").clearAll();
        $("#id").val("0");
        $("#bisEnable").prop("checked", true);
    }

    //编辑字典数据
    function editDataDic(id) {
        $("#frm").clearAll();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/baseReportField/getBaseReportField",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    $("#id").val(id);
                    $("#name").val(result.data.name);
                    $("#replaceType").val(result.data.replaceType);
                    $("#fieldName").val(result.data.fieldName);
                    $("#bisEnable").prop("checked", result.data.bisEnable);
                    $("#sorting").val(result.data.sorting);
                    $("#remark").val(result.data.remark);
                    $('#divBox').modal();
                }
                else {
                    Alert("获取数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    //删除字典数据
    function delDataDic(id, tbId) {
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/baseReportField/delBaseReportField",
                type: "post",
                dataType: "json",
                data: {id: id, _method: "DELETE"},//restful要求(防止如在国产浏览器包裹IE内核低版本不支持情况)
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
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

    //重置查询条件
    function queryReset() {
        $("#queryId").val("");
        $("#groupKey").val("");
        $("#queryName").val("");
    }

    //设置子项数据
    function setSubDataDic(pid) {
        $("#mainId").val(pid);
        getDataDicLevel(pid);
        loadSubDataDicList(pid, function () {
            $('#divSubDataDic').modal("show");
        });
    }

    //加载节点角色数据
    function loadSubDataDicList(pid, fn) {
        var cols = [];
        cols.push({field: 'name', title: '名称'});
        cols.push({field: 'fieldName', title: '文本key'});
        cols.push({
            field: 'bisEnable', title: '是否启用', formatter: function (value) {
                return getBoolChs(value);
            }
        });
        cols.push({field: 'sorting', title: '排序'});
        cols.push({field: 'remark', title: '自定义key'});
        cols.push({
            field: 'id', title: '操作', width: 200, formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success" href="javascript:setSubDataDic(' + row.id + ');" ><i class="fa fa-edit">查看子项</i></a>';
                str += '<a class="btn btn-xs btn-success" href="javascript:editSubDataDic(' + row.id + ');" ><i class="fa fa-edit">编辑</i></a>';
                str += '<a class="btn btn-xs btn-warning" href="javascript:delDataDic(' + row.id + ',\'tbDataDicList\')"><i class="fa fa-trash-o"></i>删除</a>';
                str += '</div>';
                return str;
            }
        });
        $("#tbDataDicList").bootstrapTable("destroy");
        TableInit("tbDataDicList", "${pageContext.request.contextPath}/baseReportField/getBaseReportFieldListByPid",
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

    //新增子项
    function addSubDataDic() {
        $("#frmSub").clearAll();
        $("#subId").val("0");
        fileShowAndUpload(0);
        $("#subBisEnable").prop("checked", true);
    }

    //编辑子项
    function editSubDataDic(id) {
        $("#frmSub").clearAll();
        var row = $("#tbDataDicList").bootstrapTable("getRowByUniqueId", id);
        if (row) {
            $("#frmSub").initForm(row);
            fileShowAndUpload(id);
            $("#subBisEnable").prop("checked", row.bisEnable);
        }
        $('#divSubDataDicManage').modal();
    }


    //保存子项
    function saveSubDataDic() {
        if ($("#frmSub").valid()) {
            var data = {};
            data.id = $("#subId").val();
            data.pid = $("#mainId").val();
            data.name = $("#subName").val();
            data.replaceType = $("#subReplaceType").val();
            data.itemKey = $("#subItemKey").val();
            data.sorting = $("#subSorting").val();
            data.bisEnable = $("#subBisEnable").prop("checked");
            data.remark = $("#subRemark").val();
            if ($("#subFieldName").val()) {
                data.fieldName = $("#subFieldName").val();
            }
            if (!validData('frmSub')) {
                return false;
            }
            $.ajax({
                url: "${pageContext.request.contextPath}/baseReportField/saveBaseReportField",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        TableReload("tbDataDicList");
                        $('#divSubDataDicManage').modal('hide');
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

    var strLevelHtml = "";

    //获取字典层级
    function getDataDicLevel(id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/baseReportField/getBaseReportFieldLevel",
            type: "post",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    strLevelHtml = "";
                    if (result.data) {
                        if (result.data.keyValueDto) {
                            getDataDicLevelRecursion(result.data.keyValueDto);
                        }
                        strLevelHtml += '<a href="javascript:setSubDataDic(' + result.data.key + ')">' + result.data.value + '</a>' + ">";
                        $("#titleContent").html(strLevelHtml.replace(/>$/, ""));
                    }
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

    function getDataDicLevelRecursion(keyValueDto) {
        if (keyValueDto) {
            getDataDicLevelRecursion(keyValueDto.keyValueDto);
            strLevelHtml += '<a href="javascript:setSubDataDic(' + keyValueDto.key + ')">' + keyValueDto.value + '</a>' + ">";
        }
    }

    /**
     * 特殊校验
     * @param frm
     */
    function validData(frm) {
        if (frm) {
            var $Frm = $("#" + frm);
            var replaceType = $Frm.find("select[name='replaceType'] option:selected");
            var fieldName = $Frm.find("input[name='fieldName']").val();
            var remark = $Frm.find("textarea[name='remark']").val();
            var html = "<span class='help-block' for='for'>" + "该字段为必填项" + "</span>";
            if (replaceType) {
                if (replaceType.text() == '文本') {
                    if (fieldName) {
                        if (remark){
                            Alert("自定义不能填写");
                            return false;
                        }
                        if($("#_baseReportField_id_File").children().size() >= 1){
                            Alert("模板不能上传");
                            return false;
                        }
                        return true;
                    }else {
                        $Frm.find("input[name='fieldName']").after(html.replace(/for/g, "fieldName"));
                        return false;
                    }
                }
                //---------------------------
                if (replaceType.text() == '附件') {
                    if($("#_baseReportField_id_File").children().size() >= 1){
                        if (remark){
                            Alert("自定义不能填写");
                            return false;
                        }
                        return true;
                    }else {
                        Alert("当类型选择为附件时,模板必须上传");
                        return false;
                    }
                }
                //------------------------------
                if (replaceType.text() == '自定义') {
                   if (remark){
                       if (fieldName){
                           Alert("文本不能填写");
                           return false;
                       }
                       if($("#_baseReportField_id_File").children().size() >= 1){
                           Alert("模板不能上传");
                           return false;
                       }
                       return true;
                   }else {
                       $Frm.find("textarea[name='remark']").after(html.replace(/for/g, "remark"));
                       return false;
                   }
                }
            }
        }
        return false;
    }

    function isNotBlank(item) {
        if (item){
            return true;
        }
        return false;
    }

    function fileShowAndUpload(id) {
        FileUtils.uploadFiles({
            target: 'baseReportField_id_File',
            disabledTarget: "btn_submit",
            formData: {
                fieldsName: 'baseReportField_id_File',
                tableName: AssessDBKey.BaseReportField,
                tableId: isNotBlank(id)?id:"0"
            },
            deleteFlag: true
        });
        FileUtils.getFileShows({
            target: 'baseReportField_id_File',
            formData: {
                fieldsName: 'baseReportField_id_File',
                tableName: AssessDBKey.BaseReportField,
                tableId: isNotBlank(id)?id:"0"
            },
            deleteFlag: true
        });
    }
</script>


</html>
