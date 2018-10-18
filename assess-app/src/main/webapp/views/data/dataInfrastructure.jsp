<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/7/19
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
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
                                           placeholder="名称" id="queryName" name="queryName"
                                           class="form-control">
                                </div>
                            </div>

                            <div class="col-sm-3">
                                <button type="button" class="btn btn-primary"
                                        onclick="dataObjFun.loadDataList()">
                                    查询
                                </button>

                                <button type="button" class="btn btn-success"
                                        onclick="dataObjFun.showModel();"
                                        data-toggle="modal" href="#divBox"> 新增
                                </button>
                            </div>
                        </div>

                    </form>
                    <table class="table table-bordered" id="tb_FatherList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    $(function () {
        dataObjFun.event.father.init();
        dataObjFun.loadDataList();
    });
    var DataObjFun = function () {

    };
    DataObjFun.prototype.config = {
        father: {
            frm: function () {
                return "frmFather";
            },
            table: function () {
                return "tb_FatherList";
            },
            box: function () {
                return "divBoxFather";
            },
            fileId: "fileIdFatherInfrastructure"
        },
        infrastructureMatchingCostView: "infrastructureMatchingCostView",
        infrastructureMatchingCostView2: "infrastructureMatchingCostView2",
        infrastructureMatchingCostTable:"infrastructureMatchingCostTable",
        infrastructureMatchingCostFrm:"infrastructureMatchingCostFrm",
        infrastructureCostView:"infrastructureCostView",
        infrastructureCostView2:"infrastructureCostView2",
        infrastructureCostTable:"infrastructureCostTable",
        infrastructureCostFrm:"infrastructureCostFrm"
    };
    DataObjFun.prototype.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    };
    DataObjFun.prototype.objectWriteSelectData = function (frm, data, name) {
        if (DataObjFun.prototype.isEmpty(data)) {
            $("#" + frm + " ." + name).val(data).trigger("change");
        } else {
            $("#" + frm + " ." + name).val(null).trigger("change");
        }
    };
    DataObjFun.prototype.event = {
        father: {
            select2Load: function () {
                //使数据校验生效
                $("#" + dataObjFun.config.father.frm()).validate();
                AssessCommon.initAreaInfo({
                    provinceTarget: $("#province"),
                    cityTarget: $("#city"),
                    districtTarget: $("#district"),
                    provinceValue: '',
                    cityValue: '',
                    districtValue: ''
                })
            },
            init: function () {
                DataObjFun.prototype.event.father.select2Load();
            }
        }
    };

    var dataObjFun = new DataObjFun();

    /**
     * @author:  zch
     * 描述:文件显示
     * @date:2018-09-19
     **/
    dataObjFun.showFile = function (target, tableName, id) {
        FileUtils.getFileShows({
            target: target,
            formData: {
                fieldsName: target,
                tableName: tableName,
                tableId: id,
                projectId: 0
            },
            deleteFlag: true
        })
    };

    /**
     * @author:  zch
     * 描述:文件上传
     * @date:2018-09-19
     **/
    dataObjFun.fileUpload = function (target, tableName, id) {
        FileUtils.uploadFiles({
            target: target,
            disabledTarget: "btn_submit",
            onUpload: function (file) {
                var formData = {
                    fieldsName: target,
                    tableName: tableName,
                    tableId: id
                };
                return formData;
            }, onUploadComplete: function (result, file) {
                dataObjFun.showFile(target, tableName, id);
            },
            deleteFlag: true
        });
    };

    dataObjFun.loadDataList = function () {
        var cols = [];
        cols.push({field: 'provinceName', title: '省'});
        cols.push({field: 'cityName', title: '市'});
        cols.push({field: 'districtName', title: '县'});
        cols.push({field: 'dispatchUnit', title: '发文单位'});
        cols.push({field: 'number', title: '文号'});
        cols.push({field: 'fileViewName', title: '附件'});
        cols.push({field: 'startDateName', title: '开始日期'});
        cols.push({field: 'endDateName', title: '结束日期'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="dataObjFun.editDataById(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-success" href="javascript:dataObjFun.infrastructureMatchingCostShowView(' + row.id + ');" >公共配套设施费用列表</i></a>';
                str += '<a class="btn btn-xs btn-success" href="javascript:dataObjFun.infrastructureCostShowView(' + row.id + ');" >基础配套设施费用列表</i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="dataObjFun.deleteDataById(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#" + dataObjFun.config.father.table()).bootstrapTable('destroy');
        TableInit(dataObjFun.config.father.table(), "${pageContext.request.contextPath}/infrastructure/getInfrastructureList", cols, {
            dispatchUnit: $("#queryName").val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

    /**
     * @author:  zch
     * 描述:删除
     * @date:
     **/
    dataObjFun.deleteDataById = function (id) {
        Alert("是否删除", 2, null, function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/infrastructure/deleteInfrastructureById",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('删除成功');
                        dataObjFun.loadDataList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        });
    };

    /**
     * @author:  zch
     * 描述:编辑
     * @date:
     **/
    dataObjFun.editDataById = function (id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/infrastructure/getInfrastructureById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    $("#" + dataObjFun.config.father.frm()).clearAll();
                    var data = result.data;
                    if (dataObjFun.isEmpty(data)) {
                        $("#" + dataObjFun.config.father.frm()).initForm(result.data);
                        AssessCommon.initAreaInfo({
                            provinceTarget: $("#province"),
                            cityTarget: $("#city"),
                            districtTarget: $("#district"),
                            provinceValue: result.data.province,
                            cityValue: result.data.city,
                            districtValue: result.data.district
                        })
                        $("#" + dataObjFun.config.father.frm() + " input[name='startDate']").val(formatDate(data.startDate));
                        $("#" + dataObjFun.config.father.frm() + " input[name='endDate']").val(formatDate(data.endDate));
                        dataObjFun.fileUpload(dataObjFun.config.father.fileId, "tb_data_infrastructure", id);
                        dataObjFun.showFile(dataObjFun.config.father.fileId, "tb_data_infrastructure", id);
                    }
                    $('#' + dataObjFun.config.father.box()).modal("show");
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    };

    /**
     * @author:  zch
     * 描述:保存或者更新
     * @date:
     **/
    dataObjFun.saveAndUpdateData = function () {
        if (!$("#" + dataObjFun.config.father.frm()).valid()) {
            return false;
        }
        var data = formParams(dataObjFun.config.father.frm());
        $.ajax({
            url: "${pageContext.request.contextPath}/infrastructure/saveAndUpdateInfrastructure",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    toastr.success('保存成功');
                    $('#' + dataObjFun.config.father.box()).modal('hide');
                    dataObjFun.loadDataList();
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    };

    /**
     * @author:  zch
     * 描述:显示添加数据的模型
     * @date:
     **/
    dataObjFun.showModel = function () {
        $("#" + dataObjFun.config.father.frm()).clearAll();
        $('#' + dataObjFun.config.father.box()).modal("show");
        dataObjFun.fileUpload(dataObjFun.config.father.fileId, "tb_data_infrastructure", 0);
        dataObjFun.showFile(dataObjFun.config.father.fileId, "tb_data_infrastructure", 0);
    };



    //公共配套设施费用 方法
    dataObjFun.infrastructureMatchingCostShowView = function (id) {
        $('#' + dataObjFun.config.infrastructureMatchingCostView).modal("show");
        dataObjFun.infrastructureMatchingCostList(id);
        $('#' + dataObjFun.config.infrastructureMatchingCostView).find("input[name='pid']").val(id);
    };

    //公共配套设施费用 方法
    dataObjFun.infrastructureMatchingCostSaveAndUpdate = function () {
        if (!$("#" + dataObjFun.config.infrastructureMatchingCostFrm).valid()) {
            return false;
        }
        var data = formParams(dataObjFun.config.infrastructureMatchingCostFrm);
        $.ajax({
            url: "${pageContext.request.contextPath}/infrastructureMatchingCost/addAndEdit",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    toastr.success('成功');
                    dataObjFun.infrastructureMatchingCostList(result.data);
                    $('#' + dataObjFun.config.infrastructureMatchingCostView2).modal("hide");
                }
                else {
                    Alert("失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    };

    //公共配套设施费用 方法
    dataObjFun.infrastructureMatchingCostDelete = function (id) {
        Alert("是否删除", 2, null, function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/infrastructureMatchingCost/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('删除成功');
                        dataObjFun.infrastructureMatchingCostList(result.data);
                    }
                    else {
                        Alert("失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        });
    };

    //公共配套设施费用 方法
    dataObjFun.infrastructureMatchingCostList = function (pid) {
        var cols = [];
        cols.push({field: 'name', title: '名称'});
        cols.push({field: 'number', title: '金额'});

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="dataObjFun.infrastructureMatchingCostDelete(' + row.id + ',\'tb_List1\')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#"+dataObjFun.config.infrastructureMatchingCostTable).bootstrapTable('destroy');
        TableInit(dataObjFun.config.infrastructureMatchingCostTable, "${pageContext.request.contextPath}/infrastructureMatchingCost/list", cols, {
            pid: pid
        }, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    };

    //公共配套设施费用 方法
    dataObjFun.infrastructureMatchingCostAddView = function () {
        $('#' + dataObjFun.config.infrastructureMatchingCostView2).modal("show");
        var pid = $('#' + dataObjFun.config.infrastructureMatchingCostView).find("input[name='pid']").val();
        $("#" + dataObjFun.config.infrastructureMatchingCostFrm).clearAll();
        $("#" + dataObjFun.config.infrastructureMatchingCostFrm).initForm({pid:pid});
    };


    //基础设施费用 方法
    dataObjFun.infrastructureCostShowView = function (id) {
        $('#' + dataObjFun.config.infrastructureCostView).modal("show");
        dataObjFun.infrastructureCostList(id);
        $('#' + dataObjFun.config.infrastructureCostView).find("input[name='pid']").val(id);
    };

    //基础设施费用 方法
    dataObjFun.infrastructureCostSaveAndUpdate = function () {
        if (!$("#" + dataObjFun.config.infrastructureCostFrm).valid()) {
            return false;
        }
        var data = formParams(dataObjFun.config.infrastructureCostFrm);
        $.ajax({
            url: "${pageContext.request.contextPath}/infrastructureCost/addAndEdit",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    toastr.success('成功');
                    dataObjFun.infrastructureCostList(result.data);
                    $('#' + dataObjFun.config.infrastructureCostView2).modal("hide");
                }
                else {
                    Alert("失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    };

    //基础设施费用 方法
    dataObjFun.infrastructureCostDelete = function (id) {
        Alert("是否删除", 2, null, function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/infrastructureCost/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('删除成功');
                        dataObjFun.infrastructureCostList(result.data);
                    }
                    else {
                        Alert("失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        });
    };

    //基础设施费用 方法
    dataObjFun.infrastructureCostList = function (pid) {
        var cols = [];
        cols.push({field: 'name', title: '名称'});
        cols.push({field: 'number', title: '金额'});

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="dataObjFun.infrastructureCostDelete(' + row.id + ',\'tb_List1\')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#"+dataObjFun.config.infrastructureCostTable).bootstrapTable('destroy');
        TableInit(dataObjFun.config.infrastructureCostTable, "${pageContext.request.contextPath}/infrastructureCost/list", cols, {
            pid: pid
        }, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    };

    //基础设施费用 方法
    dataObjFun.infrastructureCostAddView = function () {
        $('#' + dataObjFun.config.infrastructureCostView2).modal("show");
        var pid = $('#' + dataObjFun.config.infrastructureCostView).find("input[name='pid']").val();
        $("#" + dataObjFun.config.infrastructureCostFrm).clearAll();
        $("#" + dataObjFun.config.infrastructureCostFrm).initForm({pid:pid});
    };


</script>

<!-- 基础配套设施费用 add -->
<div id="infrastructureCostView2" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">字段</h3>
            </div>
            <form id="infrastructureCostFrm" class="form-horizontal">
                <div class="panel-body">
                    <div class="form-group">
                        <input type="hidden" name="pid">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                名称<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="name" class="form-control" required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                金额<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="number" class="form-control"
                                       required="required">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="dataObjFun.infrastructureCostSaveAndUpdate()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 基础配套设施费用 list -->
<div id="infrastructureCostView" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">基础配套设施费用列表</h3>
                <input type="hidden" name="pid">
            </div>
            <div class="panel-body">
                <span id="toolbarSub">
                    <button type="button" class="btn btn-success"
                            data-toggle="modal" onclick="dataObjFun.infrastructureCostAddView();"> 新增
                    </button>
                </span>
                <table class="table table-bordered" id="infrastructureCostTable">
                </table>
            </div>
        </div>
    </div>
</div>

<!-- 公共配套设施费用 add -->
<div id="infrastructureMatchingCostView2" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">字段</h3>
            </div>
            <form id="infrastructureMatchingCostFrm" class="form-horizontal">
                <div class="panel-body">
                    <div class="form-group">
                        <input type="hidden" name="pid">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                名称<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="name" class="form-control" required="required">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                金额<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-4">
                                <input type="text" name="number" class="form-control"
                                       required="required">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="dataObjFun.infrastructureMatchingCostSaveAndUpdate()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 公共配套设施费用 list -->
<div id="infrastructureMatchingCostView" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">公共配套设施费用列表</h3>
                <input type="hidden" name="pid">
            </div>
            <div class="panel-body">
                <span>
                    <button type="button" class="btn btn-success"
                            data-toggle="modal" onclick="dataObjFun.infrastructureMatchingCostAddView();"> 新增
                    </button>
                </span>
                <table class="table table-bordered" id="infrastructureMatchingCostTable">
                </table>
            </div>
        </div>
    </div>
</div>

<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">基础设施维护</h3>
            </div>
            <form id="frmFather" class="form-horizontal">
                <input type="hidden" id="id" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">省
                                            <span class="symbol required"></span></label>
                                        <div class="col-sm-10">
                                            <select id="province" name="province"
                                                    class="form-control search-select select2"
                                                    required="required">
                                                <option value="" name="province">-请选择-</option>
                                                <c:forEach items="${ProvinceList}" var="item">
                                                    <c:choose>
                                                        <c:when test="${item.areaId == projectInfo.province}">
                                                            <option value="${item.areaId}"
                                                                    selected="selected">${item.name}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${item.areaId}">${item.name}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">市<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-10">
                                            <select id="city" name="city" class="form-control search-select select2"
                                                    required="required">

                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">县</label>
                                        <div class="col-sm-10">
                                            <select id="district" name="district"
                                                    class="form-control search-select select2">

                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            项目类别<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="projectType"
                                                   placeholder="项目类别" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            发文单位<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="dispatchUnit"
                                                   placeholder="发文单位" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            文号<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" data-rule-number='true'
                                                   name="number"
                                                   placeholder="文号" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            开始日期<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" readonly="readonly"
                                                   class="form-control date-picker dbdate" data-date-format="yyyy-mm-dd"
                                                   name="startDate"
                                                   placeholder="开始日期" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            结束日期<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" readonly="readonly"
                                                   class="form-control date-picker dbdate" data-date-format="yyyy-mm-dd"
                                                   name="endDate"
                                                   placeholder="结束日期" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input id="fileIdFatherInfrastructure" name="fileIdFatherInfrastructure"
                                                   required="required" placeholder="上传附件" class="form-control"
                                                   type="file">
                                            <div id="_fileIdFatherInfrastructure"></div>
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
                    <button type="button" class="btn btn-primary" onclick="dataObjFun.saveAndUpdateData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>
