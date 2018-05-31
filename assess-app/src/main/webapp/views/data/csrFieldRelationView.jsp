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
                                    名称
                                </label>
                                <div class="col-sm-2">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="名称" id="queryName" name="queryName"
                                           class="form-control">
                                </div>
                            </div>

                            <div class="col-sm-3">
                                <button type="button" class="btn btn-primary" onclick="loadcsrFieldRelationList();">
                                    查询
                                </button>
                                <button type="button" class="btn btn-success" onclick="addcsrFieldRelation();"
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
                                            显示名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="displayName" id="displayName" placeholder="显示名称"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            别名<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="anotherName" id="anotherName" placeholder="别名"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            表
                                        </label>
                                        <div class="col-sm-10">
                                            <select class="form-control search-select select2" name="tableName" id="tableName"
                                                    required="required">
                                                <option value="">请选择</option>
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
                                            <select class="form-control search-select select2" name="fieldName" id="fieldName" required="required">
                                                <option value="">请选择</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            备注<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea class="form-control" id="remark" name="remark" required="required" placeholder="备注">

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
                    <button type="button" class="btn btn-primary" onclick="saveCsrFieldRelation()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/datadic-select.js"></script>
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">

    $(function () {
        loadcsrFieldRelationList();

        $("#tableName").select2();
        $("#fieldName").select2();
        tableNameChange();
    })

    //选择对应的表单类型
    function selectFormType() {
        assessDataDic.select({
            key: "assess.class",
            onSelected: function (nodes) {
                $("#formTypeId").val(nodes[0].id);
                $("#formTypeName").val(nodes[0].name);
            }
        })
    }


    //加载 债权独立模块 字段关联关系 数据列表
    function loadcsrFieldRelationList() {
        var cols = [];
        cols.push({field: 'displayName', title: '名称'});
        cols.push({field: 'anotherName', title: '别名'});
        cols.push({field: 'tableName', title: '表名'});
        cols.push({field: 'fieldName', title: '字段名'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑"  onclick="editcsrFieldRelation(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除"  onclick="removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_List").bootstrapTable('destroy');
        var queryName = $("#queryName").val();
        TableInit("tb_List", "${pageContext.request.contextPath}/csrFieldRelation/list", cols, {
            displayName: queryName
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }

    //删除 债权独立模块 字段关联关系 数据()
    function removeData(id) {
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/csrFieldRelation/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        loadcsrFieldRelationList();//重载 (刷新)
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

    //对新增 债权独立模块 字段关联关系 数据处理
    function addcsrFieldRelation() {
        $("#frm").clearAll();
    }
    //新增 债权独立模块 字段关联关系 数据
    function saveCsrFieldRelation() {
        var data = formParams("frm")
        if ($("#frm").valid()) {
            $.ajax({
                url: "${pageContext.request.contextPath}/csrFieldRelation/saveAndUpdate",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        loadcsrFieldRelationList();
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
    //债权独立模块 字段关联关系 修改
    function editcsrFieldRelation(index) {
        $.ajax({
            url: "${pageContext.request.contextPath}/csrFieldRelation/get",
            type: "GET",
            dataType: "json",
            data: {id: index},
            success: function (result) {
                Loading.progressHide();
                $("#frm").clearAll();
                $("#frm").initForm(result);
                $('#divBox').modal();
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    function appendChildElement(item) {
        var TableFieldElement = document.getElementById("fieldName");
        var len = item.length;
        for (var i = 0; i < len; i++) {
            var optionLen = $("#fieldName option").size();
            var fieldElment = document.createElement("option");
            fieldElment.setAttribute("value", item[i].key);
            fieldElment.appendChild(document.createTextNode(item[i].key));
            TableFieldElement.appendChild(fieldElment);

        }
        console.info(len);
    }
    function removeChild() {
       $("#fieldName").empty();
    }
    function tableNameChange() {
        $("#tableName").change(function () {
            //检测  然后操作
            removeChild();
            var selected = $(this).children('option:selected').val();
            var data = "tableName=" + selected;
            $.ajax({
                url: "${pageContext.request.contextPath}/csrFieldRelation/listTableField",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    appendChildElement(result);
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        });
    }
</script>
</html>
