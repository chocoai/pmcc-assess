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
     aria-hidden="true" data-height="300">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">基础设施维护</h3>
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
                                            省<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="province" class="form-control" id="province">
                                                <option selected="selected" value="">请选择</option>
                                                <c:forEach items="${provinceList}" var="item">
                                                    <option value="${item.id}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                            <span for="province" class="help-block">该字段为必填项</span>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            市<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="city" class="form-control" id="city" required="required">
                                                <option selected="selected" value="">请选择</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            县
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="district" class="form-control" id="district"
                                                    required="required">
                                                <option selected="selected" value="">请选择</option>
                                            </select>
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
                                            文号<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input placeholder="文号" class="form-control" id="number" name="number"/>
                                            <span for="number" class="help-block">该字段为必填项</span>
                                        </div>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            项目类别
                                        </label>
                                        <div class="col-sm-10">

                                            <input placeholder="项目类别" class="form-control" id="projectType"
                                                   name="projectType"/>

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

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            附件
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="file" name="fileName" id="fileName" placeholder="上传附件"
                                                   class="form-control" required="required">
                                            <div id="_fileName"></div>
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

<!-- 显示基础设施费用列表 -->
<div id="divSubDataDic" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="titleContent">基础设施费用</h3>

                <div>
                    <label class="col-sm-1 control-label">
                        名称
                    </label>
                    <div class="col-sm-2">
                        <input type="text" data-rule-maxlength="50"
                               placeholder="名称" id="queryName1" name="queryName1"
                               class="form-control">
                    </div>
                </div>
            </div>
            <div class="panel-body">
        <span id="toolbarSub">
            <button type="button" class="btn btn-success"
                    data-toggle="modal" href="#firSub1"> 新增
            </button>
        </span>
                <table class="table table-bordered" id="tbDataDicList">
                </table>
            </div>
        </div>
    </div>
</div>

<!-- 基础设施费用 添加 ===========-->
<div id="firSub1" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="titleContent2">基础设施费</h3>
            </div>

            <div class="panel-body">
                <form id="firSubA" class="form-horizontal">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                名称<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" id="name" name="name" class="form-control" required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                费用<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" id="numberCost" name="number" class="form-control"
                                       required="required">
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
            <table class="table table-bordered" id="tb_List1">
                <!-- cerare document add ajax data-->
            </table>
        </div>
    </div>
</div>

<!-- 显示公共设施费用列表 -->
<div id="divSubDataDic1" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="titleContent1">公共设施费用</h3>

                <div>
                    <label class="col-sm-1 control-label">
                        名称
                    </label>
                    <div class="col-sm-2">
                        <input type="text" data-rule-maxlength="50"
                               placeholder="名称" id="queryName2" name="queryName2"
                               class="form-control">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 公共设施费用 添加 ===========-->
<div id="firSub2" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="titleContent3">字段</h3>
            </div>
            <form id="firSubA1" class="form-horizontal">
                <div class="panel-body">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                名称<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" id="name1" name="name" class="form-control" required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                金额<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" id="numberCost1" name="number" class="form-control"
                                       required="required">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="saveFileld1()">
                        保存
                    </button>
                </div>
            </form>
            <div class="panel-body">
                <table class="table table-bordered" id="tb_List2">
                    <!-- cerare document add ajax data-->
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

    function fileUpload() {
        FileUtils.uploadFiles({
            target: "fileName",
            disabledTarget: "btn_submit",
            formData: {
                tableName: "tb_data_infrastructure",
                tableId: 0,
                fieldsName: "file_name"
            },
            deleteFlag: true
        });
    }
    //加载基础设施费用列表
    function loadInfratructureCost() {
        var cols = [];
        cols.push({field: 'name', title: '名称'});
        cols.push({field: 'number', title: '费用'});

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑" onclick="editHrProfessional1(' + index + ');" ><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="deleteBestUseDescription1(' + row.id + ',\'tb_List1\')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_List1").bootstrapTable('destroy');
        var methodStrChange = "";
        TableInit("tb_List1", "${pageContext.request.contextPath}/infrastructureCost/list", cols, {
            methodStr: methodStrChange
        }, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
        $('#firSub1').modal();
    }

    //对新增 基础设施费用处理
    function firSub1() {
        $("#firSubA").clearAll();
    }
    //新增基础设施费用
    function saveFileld() {
        var flag = false;
        var data = $("#firSubA").serialize();
        if ($("#firSubA").valid()) {
            $.ajax({
                url: "${pageContext.request.contextPath}/infrastructureCost/addAndEdit",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        loadInfratructureCost();
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

    //删除 基础设施费用
    function deleteBestUseDescription1(id, tbId1) {
        Alert("确认要删除么？", 2, null, function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/infrastructureCost/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        loadInfratructureCost();//重载 (刷新)
                        $('#' + tbId1).bootstrapTable("refresh");
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

    //基础设施费用 修改
    function editHrProfessional1(index) {
        $("#firSubA").clearAll();
        $('#firSub1').modal();
        $.ajax({
            url: "${pageContext.request.contextPath}/infrastructureCost/list",
            type: "POST",
            dataType: "json",
            data: {id: index},
            success: function (result) {
                Loading.progressHide();
                $('#firSub1').modal();
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


    //加载公共设施费用列表
    function loadInfratructurematchingCost() {
        var cols = [];
        cols.push({field: 'name', title: '名称'});
        cols.push({field: 'number', title: '金额'});

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑" onclick="editHrProfessional2(' + index + ');" ><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="deleteBestUseDescription2(' + row.id + ',\'tb_List1\')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_List2").bootstrapTable('destroy');
        var name = "";
        TableInit("tb_List2", "${pageContext.request.contextPath}/infrastructureMatchingCost/list", cols, {
            name: name
        }, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
        $('#firSub2').modal();
    }

    //对新增 公共设施费用处理
    function firSub2() {
        $("#firSubA1").clearAll();
    }
    //新增公共设施费用
    function saveFileld1() {
        var flag = false;
        var data = $("#firSubA1").serialize();
        if ($("#firSubA1").valid()) {
            $.ajax({
                url: "${pageContext.request.contextPath}/infrastructureMatchingCost/addAndEdit",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        loadInfratructurematchingCost();
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

    //删除 公共设施费用
    function deleteBestUseDescription2(id, tbId2) {
        Alert("确认要删除么？", 2, null, function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/infrastructureMatchingCost/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {

                        toastr.success('删除成功');
                        loadInfratructurematchingCost();//重载 (刷新)
                        $('#' + tbId2).bootstrapTable("refresh");
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

    //公共设施费用 修改//
    function editHrProfessional2(index) {
        $("#firSubA1").clearAll();
        $('#firSub2').modal();
        $.ajax({
            url: "${pageContext.request.contextPath}/infrastructureMatchingCost/list",
            type: "POST",
            dataType: "json",
            data: {id: index},
            success: function (result) {
                Loading.progressHide();
                $('#firSub2').modal();
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

    //加载 基础设施及公共配套设施维护 数据列表
    function loadDataDicList() {
        var cols = [];
        cols.push({field: 'provinceName', title: '省'});
        cols.push({field: 'cityName', title: '市'});
        cols.push({field: 'districtName', title: '县'});
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
                str += '<a class="btn btn-xs btn-success" href="javascript:loadInfratructureCost(' + index + ');" >基础设施费</i></a>';
                str += '<a class="btn btn-xs btn-success" href="javascript:loadInfratructurematchingCost(' + index + ');" >公共配套设施费</i></a>';
                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="editHrProfessional(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="deleteBestUseDescription(' + row.id + ',\'tb_List1\')"><i class="fa fa-minus fa-white"></i></a>';
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
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
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
        fileUpload();
    }

    function selectProvince() {
        $("#province").change(function () {//监听 选择的省份
            //检测  然后操作
            removeChild();//删除市
            removeChild_district();//删除县
            var selected = $(this).children('option:selected').val();//获取到省
            var data = "pid=" + selected;
            $.ajax({
                url: "${pageContext.request.contextPath}/infrastructure/getAreaList",
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
    selectProvince();
    function selectCity() {
        $("#city").change(function () {//监听 选择的城市
            //检测  然后操作
            removeChild_district();
            var selected = $(this).children('option:selected').val();//获取到城市
            var data = "pid=" + selected;
            $.ajax({
                url: "${pageContext.request.contextPath}/infrastructure/getAreaList",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    console.info(result);
                    appendChildElement_district(result);
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        });
    }
    selectCity();
    function removeChild() { //删除市
        var optionLen = $("#city option").size();
        if (optionLen > 2) {
            $("#city option").remove();//当大于2时 应该是已经选择一次了 所以删除元素
        }
    }
    function removeChild_district() {//删除县或者区
        var optionLen = $("#district option").size();
        if (optionLen > 2) {
            $("#district option").remove();//当大于2时 应该是已经选择一次了 所以删除元素
        }
    }
    function appendChildElement(item) {//市添加
        var TableField = $("#city");
        var TableFieldElement = document.getElementById("city");
        var len = item.length;
        for (var i = 0; i < len; i++) {
            var optionLen = $("#city option").size();
            var fieldElment = document.createElement("option");
            fieldElment.setAttribute("value", item[i].areaId);
            fieldElment.appendChild(document.createTextNode(item[i].name));
            TableFieldElement.appendChild(fieldElment);

        }
    }

    function appendChildElement_district(item) {//县或者区域添加
        var TableField = $("#district");
        var TableFieldElement = document.getElementById("district");
        var len = item.length;
        for (var i = 0; i < len; i++) {
            var optionLen = $("#district option").size();
            var fieldElment = document.createElement("option");
            fieldElment.setAttribute("value", item[i].id);
            fieldElment.appendChild(document.createTextNode(item[i].name));
            TableFieldElement.appendChild(fieldElment);

        }
    }

    //新增 基础设施及公共配套设施维护数据
    function saveSubDataDic() {
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
                    alert();
                    $("#id").val(id);
                    $("#name").val(result.data.name);
                    // $("#fieldName").val(result.data.fieldName);
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

    function editHrProfessional(id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/infrastructure/get",
            type: "POST",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                console.log(result);
                $("#frm").clearAll();
                $("#startDate").val(formatDate(result.startDate, false));
                $("#endDate").val(formatDate(result.endDate, false));
                $("#dispatchUnit").val(result.dispatchUnit);
                $("#projectType").val(result.projectType);
                $("#number").val(result.number);
                $("#id").val(result.id);
                $("#province").append("<option selected='selected'" + 'value=' + result.province + ">" + result.provinceName + "</option>");
                $("#district").append("<option selected='selected'" + 'value=' + result.district + ">" + result.districtName + "</option>");
                $("#city").append("<option selected='selected'" + 'value=' + result.city + ">" + result.cityName + "</option>");
                FileUtils.uploadFiles({
                    target: "fileName",
                    disabledTarget: "btn_submit",
                    formData: {
                        tableName: "tb_data_infrastructure",
                        tableId: 0,
                        fieldsName: "file_name"
                    },
                    deleteFlag: true
                });
                FileUtils.getFileShows({
                    target: "fileName",
                    formData: {
                        tableName: "tb_data_infrastructure",
                        tableId: id,
                        fieldsName: "file_name"
                    },
                    deleteFlag: true
                })
                $('#divBox').modal();
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
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
