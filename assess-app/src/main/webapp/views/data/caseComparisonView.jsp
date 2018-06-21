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
        <%--<jsp:include page="${pageContext.request.contextPath}/views/share/main_navigation.jsp" flush="true"/>--%>
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="row">
                <div class="x_panel">
                    <div class="x_title">
                        <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                            ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                        </h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <div class="col-md-3">
                            <div class="input-group">
                                <input type="text" id="queryName" class="form-control">
                                <span class="input-group-btn">
                              <a href="javascript://" onclick="queryProjectClassify()" class="btn btn-primary">查询</a>
                            </span>
                            </div>
                            <ul id="ztree" class="ztree"></ul>
                        </div>
                        <div class="col-md-9">
                            <!-- start: DYNAMIC TABLE PANEL -->
                            <div class="x_panel">
                                <div class="x_title">
                                    案例配置
                                </div>

                                <%--<div id="customer_view" class="form-horizontal">--%>

                                <%--</div>--%>
                                <div class="x_content" id="case_comparison" style="display: none;">
                                    <button type="button" class="btn btn-success" data-toggle="modal"
                                            href="#divBox"
                                            onclick="addCaseComparison();"> 新增
                                    </button>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <table id="tb_List" class="table table-bordered"></table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
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
                <input type="hidden" id="exploreFormType" name="exploreFormType" value="">
                <input type="hidden" id="id" name="id" value="0">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="name" id="name" placeholder="名称"
                                                   class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            查勘数据模板<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea placeholder="模板内容" class="form-control" id="exploreExplain"
                                                      name="exploreExplain" required="required"
                                                      onkeyup="extractTemplateExplore()"></textarea>
                                            <div class="template-explore">

                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            案例数据模板<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea placeholder="模板内容" class="form-control" id="caseExplain"
                                                      name="caseExplain" required="required"
                                                      onkeyup="extractTemplateCase()"></textarea>
                                            <div class="template-case">

                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">选择案例<span class="symbol required"></span></label>
                                        <div class="col-sm-10">
                                            <div class="input-group">
                                                <input type="hidden" id="caseFormType" name="caseFormType" value="">
                                                <input type="text" class="form-control" readonly="readonly"
                                                       value="" required="required"
                                                       id="caseFormTypeName" name="caseFormTypeName" maxlength="200">
                                                <span class="input-group-btn">
                                            <button type="button" class="btn btn-default docs-tooltip"
                                                    data-toggle="tooltip"
                                                    data-original-title="选择" onclick="selectCase()">
                                            <i class="fa fa-search"></i>
                                            </button>
                                            <button type="button" class="btn btn-default docs-tooltip"
                                                    onclick="$(this).closest('.input-group').find('input').val('');"
                                                    data-toggle="tooltip" data-original-title="清除">
                                            <i class="fa fa-trash-o"></i>
                                            </button>
                                        </span>
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
                        <button type="button" class="btn btn-primary" onclick="saveCaseComparison()">
                            保存
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 显示字段列表 -->
<div id="divSubDataDic" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="titleContent">字段管理</h3>
            </div>
            <input type="hidden" name="caseId" id="caseId">
            <div class="panel-body">
        <span id="toolbarSub">
            <button type="button" class="btn btn-success" onclick="addMethodField()"
                    data-toggle="modal" href="#firSub"> 新增
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
     data-height="300"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width: 520px;">
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
                            <input type="hidden" id="" name="id" value="">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            字段名称
                                            <input type="hidden" name="caseId" id="caseIdNG">
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" id="uName" name="uName" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            表
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" id="tableName" name="tableName" class="form-control"
                                                   readonly="readonly">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            表字段
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" id="fieldName" name="fieldName" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            类型
                                        </label>
                                        <div class="col-sm-10">
                                            <select class="form-control" required id="type" name="type">
                                                <option value="">请选择</option>
                                                <option value="0">查勘</option>
                                                <option value="1">案例</option>
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
<input type="hidden" id="exploreParentNodeId">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/project-classify-select.js"></script>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    var zTreeObj;
    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pid",
                rootPId: 0
            }
        },
        async: {
            enable: true,
            url: "${pageContext.request.contextPath}/baseProjectClassify/getProjectClassifyTreeByPid",
            autoParam: ["id=pid"],
            otherParam: {
                "filterKey": [AssessProjectClassifyKey.declare, AssessProjectClassifyKey.case].join()
            }
        },
        // 回调函数
        callback: {
            onClick: function (event, treeId, treeNode, clickFlag) {
                //显示配置信息
                if (treeNode.key == AssessProjectClassifyKey.explore) {
                    var parentKeyNode = AssessCommon.getParentNodeByKey(treeNode, AssessProjectClassifyKey.explore);
                    if (parentKeyNode) {
                        $("#exploreParentNodeId").val(parentKeyNode.id);
                    }
                    loadCaseComparisonList(treeNode.id);
                } else {
                    $("#case_comparison").hide();
                }
            }
        }
    };

    $(document).ready(function () {
        ztreeInit();
    });

    //初始化
    function ztreeInit() {
        $.ajax({
            url: getContextPath() + "/baseProjectClassify/getProjectClassifyTreeByKey",
            data: {
                key: AssessProjectClassifyKey.single
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                zTreeObj = $.fn.zTree.init($("#ztree"), setting, result);
                //展开第一级
                var rootNode = zTreeObj.getNodes()[0];
                zTreeObj.expandNode(rootNode, true, false, true);
            }
        })
    }

    //查询项目分类
    function queryProjectClassify() {
        var queryName = $("#queryName").val();
        if (queryName) {
            $.ajax({
                url: "${pageContext.request.contextPath}/baseProjectClassify/queryProjectClassifyTree",
                data: {
                    name: queryName,
                    key: AssessProjectClassifyKey.single,
                    "filterKey": [AssessProjectClassifyKey.declare, AssessProjectClassifyKey.case].join()
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    zTreeObj = $.fn.zTree.init($("#ztree"), setting, result);
                    //展开第一级
                    var rootNode = zTreeObj.getNodes()[0];
                    zTreeObj.expandNode(rootNode, true, false, true);
                }
            })
        } else {
            ztreeInit();
        }
    }
</script>
<script type="application/javascript">
    //加载 案例对比配置 基础数据列表
    function loadCaseComparisonList(exploreFormType) {
        var cols = [];
        cols.push({field: 'name', title: '名称'});
        cols.push({field: 'exploreExplain', title: '查勘数据模板'});
        cols.push({field: 'caseExplain', title: '案例数据模板'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-info tooltips" data-placement="top" data-original-title="查看选项"  onclick="setSubDataDic(' + index + ');" ><i class="fa fa-bars fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑"  onclick="editCaseComparison(' + index + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除"  onclick="removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_List").bootstrapTable('destroy');
        TableInit("tb_List", "${pageContext.request.contextPath}/caseComparison/list", cols, {
            exploreFormType: exploreFormType
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $("#case_comparison").show();
                $("#frm").find('[name="exploreFormType"]').attr("value", exploreFormType);
                $('.tooltips').tooltip();
            }
        });
    }

    //对新增 案例对比配置 数据处理
    function addCaseComparison() {
        $('.template-explore').empty();
        $('.template-case').empty();
        var value = $("#frm").find('[name="exploreFormType"]').attr("value");
        $("#frm").clearAll();
        $("#frm").find('[name="exploreFormType"]').attr("value", value);
    }

    //提取查勘字段
    function extractTemplateExplore() {
        $('.template-explore').empty();
        var text = $("#exploreExplain").val();
        var exploreArray = AssessCommon.extractField(text);
        if (exploreArray && exploreArray.length > 0) {
            var html = '';
            $.each(exploreArray, function (i, item) {
//                field  = exploreArray;
                html += '<span class="label label-default" style="font-weight:bold">' + item + '</span> ';
            })
            $('.template-explore').append(html);
        }
    }

    //提取案例字段
    function extractTemplateCase() {
        $('.template-case').empty();
        var text = $("#caseExplain").val();
        var caseArray = AssessCommon.extractField(text);
        if (caseArray && caseArray.length > 0) {
            var html = '';
            $.each(caseArray, function (i, item) {
//                field  = exploreArray;
                html += '<span class="label label-default" style="font-weight:bold">' + item + '</span> ';
            })
            $('.template-case').append(html);
        }
    }


    //新增 案例对比配置 数据
    function saveCaseComparison() {
        var data = formParams("frm");
        console.log(data);
//        return false;
        if ($("#frm").valid()) {
            $.ajax({
                url: "${pageContext.request.contextPath}/caseComparison/save",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        loadCaseComparisonList(data.exploreFormType);
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

    //删除 案例对比配置 数据()
    function removeData(id) {
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
                        var value = $("#frm").find('[name="exploreFormType"]').attr("value");
                        loadCaseComparisonList(value);//重载 (刷新)
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

    //案例对比配置 修改
    function editCaseComparison(index) {
        var row = $("#tb_List").bootstrapTable('getData')[index];
        console.log(row);
        $("#frm").clearAll();
        $("#frm").initForm(row);
        $('#divBox').modal();
    }


    //------------------------------------------------------------------------------------------------------------------

    //加载子项节点数据
    function loadSubDataDicList(pid, fn) {
        var cols = [];
        cols.push({field: 'uName', title: '名称'});
        cols.push({field: 'tableName', title: '表名称'});
        cols.push({field: 'fieldName', title: '字段名称'});
        cols.push({field: 'typeName', title: '类型'});
        cols.push({
            field: 'id', title: '操作', width: 200, formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑"  onclick="editCaseComparisonDic(' + index + ',\'tbDataDicList\')"><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除"  onclick="delDataDic(' + row.id + ',\'tbDataDicList\')"><i class="fa fa-minus fa-white"></i></a>';

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

                }
            });
    }

    //设置子项数据
    function setSubDataDic(index) {
        console.log(index);
        var row = $("#tb_List").bootstrapTable('getData')[index];
        if (row != undefined) {
            $("#tableName").val(row.tableName);
            $("#divSubDataDic").modal();//显示
            $("#tbDataDicList").clearAll();//清除数据
            loadSubDataDicList(row.id, function () {
                $('#divSubDataDic').modal("show");
            });
        } else {
            $("#divSubDataDic").modal();//显示
            $("#tbDataDicList").clearAll();//清除数据
            loadSubDataDicList(index, function () {
                $('#divSubDataDic').modal("show");
            });
        }
    }

    //新增 子项 字段数据
    function addMethodField() {
//        $("#type").select2();
        var caseId = document.getElementById("caseId");
        var value = $("#firSubA").find('[name="tableName"]').val();
        $("#firSub").clearAll();
        $("#firSubA").find('[name="tableName"]').val(value);
//        $('#firSub').modal();
        var caseIdNG = document.getElementById("caseIdNG");
        caseIdNG.value = caseId.value;
    }
    //保存新增 子项 字段的数据
    function saveFileld() {
        var data = formParams("firSubA");
        console.log(data);
//        return false;
        if ($("#firSubA").valid()) {
            $.ajax({
                url: "${pageContext.request.contextPath}/caseComparisonNG/addField",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    console.info(result);
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#firSub').modal('hide');//隐藏
                        var caseId = document.getElementById("caseId").value;
                        console.log(caseId);
                        setSubDataDic(caseId);
                    } else {
                        toastr.warning(result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        }
    }

    //案例对比子项配置 修改
    function editCaseComparisonDic(index) {
        var row = $("#tbDataDicList").bootstrapTable('getData')[index];
        console.log(row);
//        var value = $("#firSubA").find('[name="tableName"]').val();
        $("#firSub").clearAll();
//        $("#firSubA").find('[name="tableName"]').val(value);
        $("#firSub").initForm(row);
        $('#firSub').modal();
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
                        console.log(caseId);
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

    //选择案例
    function selectCase() {
        assessProjectClassify.select({
            modalName: "案例选择",
            pid: $("#exploreParentNodeId").val(),
            filterKey: [AssessProjectClassifyKey.declare, AssessProjectClassifyKey.explore],
            onSelected: function (nodes) {
                $("#caseFormTypeName").val(nodes[0].name);
                $("#caseFormType").val(nodes[0].id);
            }
        })
    }

</script>
</html>
