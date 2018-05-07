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
                                    发文单位
                                </label>
                                <div class="col-sm-2">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="发文单位" id="queryName" name="queryName"
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
                <h4 class="modal-title">字典管理</h4>
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
                                            省
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="省" class="form-control" id="province"
                                                   name="province">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            市
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="市" class="form-control" id="city"
                                                   name="city"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            县
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="县" class="form-control" id="district"
                                                   name="district">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            发文单位<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" required data-rule-maxlength="50" placeholder="发文单位"
                                                   id="dispatchUnit" name="dispatchUnit" class="form-control">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            文号
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea placeholder="文号" class="form-control" id="number" name="number">

                                            </textarea>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            文件名称
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea placeholder="文件名称" class="form-control" id="fileName"
                                                      name="fileName">

                                            </textarea>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            项目类别
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea placeholder="项目类别" class="form-control" id="projectType"
                                                      name="projectType">

                                            </textarea>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            执行开始日期
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="执行开始日期" id="startDate" name="startDate"
                                                   data-date-format="yyyy-mm-dd" class="form-control date-picker dbdate"
                                                   readonly="readonly">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            执行结束日期
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="执行结束日期" id="endDate" name="endDate"
                                                   data-date-format="yyyy-mm-dd" class="form-control date-picker dbdate"
                                                   readonly="readonly">
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


<!--基础设施及公共配套设施维护数据子项数据 ===========-->
<div id="divSubDataDic" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="titleContent">基础设施维护数据</h4>
            </div>
            <div class="panel-body">
                <table class="table table-bordered" id="tbDataDicList">
                </table>
            </div>
        </div>
    </div>
</div>


<%@include file="/views/share/main_footer.jsp" %>

<script src="${pageContext.request.contextPath}/excludes/assets/plugins/laydate/laydate.js"></script>
<script type="application/javascript">

    $(function () {
        loadDataDicList();
    })

    //加载 基础设施及公共配套设施维护 数据列表
    function loadDataDicList() {
        var cols = [];
        cols.push({field: 'province', title: '省'});
        cols.push({field: 'city', title: '市'});
        cols.push({field: 'district', title: '县'});
        cols.push({field: 'dispatchUnit', title: '发文单位'});
        cols.push({field: 'number', title: '文号'});
        cols.push({field: 'fileName', title: '文件名称'});
        cols.push({field: 'projectType', title: '项目类别'});
        /*日期转换*/
        cols.push({
            field: 'startDate', title: '执行开始日期', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        cols.push({
            field: 'endDate', title: '执行结束日期', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success" href="javascript:editHrProfessional(' + index + ');" >编辑</i></a>';
                str += '<a class="btn btn-xs btn-warning" href="javascript:deleteBestUseDescription(' + row.id + ',\'tb_List\')">删除</a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_List").bootstrapTable('destroy');
        TableInit("tb_List", "${pageContext.request.contextPath}/infrastructure/getInfrastructure", cols, {
            name: $("#queryName").val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    }

    //删除 基础设施及公共配套设施维护数据
    function deleteBestUseDescription(id, tbId) {
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/infrastructure/deleteInfrastructure",
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

    //对新增 基础设施及公共配套设施维护数据处理
    function addDataDic() {
        $("#frm").clearAll();
    }

    //新增 基础设施及公共配套设施维护数据
    function saveSubDataDic() {
        var flag = false;
        var data = $("#frm").serialize();

        if ($("#frm").valid()) {
            $.ajax({
                url: "${pageContext.request.contextPath}/infrastructure/addInfrastructure",
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

    //-------------------------------------------------------------------------------------
    //编辑基础设施维护数据
    function editDataDic(id) {
        $("#frm").clearAll();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/infrastructure/getInfrastructure",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    $("#id").val(id);
                    $("#name").val(result.data.name);
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

    function editHrProfessional(index) {
        var row = $("#tb_List").bootstrapTable('getData')[index];
        $("#frm").clearAll();
        $("#frm").initForm(row);
        $("#startDate").val(formatDate(row.startDate, false));
        $("#endDate").val(formatDate(row.endDate, false));
        $('#divBox').modal();
    }

    function isNot(val) {
        if (val != null) {
            if (val != '') {
                return true;
            }
        }
        return false;
    }


</script>


</html>
