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
                            <div class="col-sm-3">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-primary dropdown-toggle"
                                            data-toggle="dropdown">导入发文登记表
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li>
                                            <a onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftProjectDispatchRegister)"><span>下载模板</span></a>
                                        </li>
                                        <li>
                                            <a onclick="$('#ajaxFileUploadHouseLand').val('').trigger('click')"><span>导入</span></a>
                                        </li>
                                    </ul>
                                </div>
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
<input type="file" id="ajaxFileUploadHouseLand" name="file" style="display: none;"
       onchange="dataObjFun.inputFileLand();">
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
<script type="text/javascript">
    $(function () {
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
            }
        }
    }
    DataObjFun.prototype.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    }

    var dataObjFun = new DataObjFun();


    dataObjFun.loadDataList = function () {
        var cols = [];
        cols.push({field: 'dispatchDate', title: '发文日期'});
        cols.push({field: 'dispatchNumber', title: '发文号'});
        cols.push({field: 'businessType', title: '业务类型'});
        cols.push({field: 'entrustPurpose', title: '委托目的'});
        cols.push({field: 'projectName', title: '项目名称'});
        cols.push({field: 'clientCompany', title: '客户公司'});
        cols.push({field: 'entrustUnit', title: '委托单位'});
        cols.push({field: 'assessArea', title: '评估面积'});
        cols.push({field: 'assessAmount', title: '评估额(元)'});
        cols.push({field: 'sendNumber', title: '外送份数'});
        cols.push({field: 'remainNumber', title: '留存份数'});
        cols.push({field: 'operator', title: '经办人'});
        cols.push({field: 'approver', title: '审批人'});
        cols.push({field: 'depositPerson', title: '交存人'});
        cols.push({field: 'redactPerson', title: '编存人'});
        cols.push({field: 'pigeonholeDate', title: '归档日期'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="dataObjFun.editDataById(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="dataObjFun.deleteDataById(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#" + dataObjFun.config.father.table()).bootstrapTable('destroy');
        TableInit(dataObjFun.config.father.table(), "${pageContext.request.contextPath}/dataDispatchRegister/getDataDispatchRegisterList", cols, {}, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }
    /**
     * @author:  zch
     * 描述:删除
     * @date:
     **/
    dataObjFun.deleteDataById = function (id) {
        Alert("确认删除!", 2, null, function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/dataDispatchRegister/deleteDataDispatchRegisterById",
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
            url: "${pageContext.request.contextPath}/dataDispatchRegister/getDataDispatchRegisterById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    $("#" + dataObjFun.config.father.frm()).clearAll();
                    var data = result.data;
                    if (dataObjFun.isEmpty(data)) {
                        $("#" + dataObjFun.config.father.frm()).initForm(result.data);
                    }
                    $('#' + dataObjFun.config.father.box()).modal("show");
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }
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
            url: "${pageContext.request.contextPath}/dataDispatchRegister/saveAndUpdateDataDispatchRegister",
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
    }
    /**
     * @author:  zch
     * 描述:显示添加数据的模型
     * @date:
     **/
    dataObjFun.showModel = function () {
        $("#" + dataObjFun.config.father.frm()).clearAll();
        $('#' + dataObjFun.config.father.box()).modal("show");
    }

    /**
     * excel导入处理
     */
    dataObjFun.inputFileLand = function () {
        $.ajaxFileUpload({
            type: "POST",
            url: getContextPath() + "/dataDispatchRegister/importData",
            data: {
            },//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: 'ajaxFileUploadHouseLand',//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                if (result.ret) {
                    dataObjFun.loadDataList();
                    Alert(result.data);
                }
            },
            error: function (result, status, e) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };
</script>
<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">建筑安装费计费维护</h3>
            </div>
            <form id="frmFather" class="form-horizontal">
                <input type="hidden" id="id" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            发文日期
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control"
                                                   name="dispatchDate" placeholder="发文日期">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            发文号
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control"
                                                   name="dispatchNumber" placeholder="发文号">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            业务类型
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control"
                                                   name="businessType" placeholder="业务类型">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            委托目的
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control"
                                                   name="entrustPurpose" placeholder="委托目的">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            项目名称
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control"
                                                   name="projectName" placeholder="项目名称">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            客户公司
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control"
                                                   name="clientCompany" placeholder="客户公司">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            委托单位
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control"
                                                   name="entrustUnit" placeholder="委托单位">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            评估面积
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control"
                                                   name="assessArea" placeholder="评估面积">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            评估额(元)
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control"
                                                   name="assessAmount" placeholder="评估额(元)">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            外送份数
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control"
                                                   name="sendNumber" placeholder="外送份数">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            留存份数
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control"
                                                   name="remainNumber" placeholder="留存份数">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            经办人
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control"
                                                   name="operator" placeholder="经办人">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            审批人
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control"
                                                   name="approver" placeholder="审批人">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            交存人
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control"
                                                   name="depositPerson" placeholder="交存人">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            编存人
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control"
                                                   name="redactPerson" placeholder="编存人">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            归档日期
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control"
                                                   name="pigeonholeDate" placeholder="归档日期">
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
