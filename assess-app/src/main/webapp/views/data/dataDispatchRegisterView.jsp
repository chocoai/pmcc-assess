<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body>
<div class="wrapper">
    <%@include file="/views/share/main_navigation.jsp" %>
    <%@include file="/views/share/main_head.jsp" %>
    <div class="main-panel">
        <div class="content">
            <div class="panel-header bg-primary-gradient">
                <div class="page-inner py-5">
                </div>
            </div>
            <div class="page-inner mt--5">
                <div class="row mt--2">

                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">${baseViewDto.currentMenu.name}</div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frmQuery" class="form-horizontal">
                                    <div class="form-group form-inline">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-primary dropdown-toggle btn-sm"
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
                                        <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                                data-toggle="modal" onclick="dataObjFun.showModel()"
                                                href="#divBoxFather">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                            新增
                                        </button>
                                    </div>


                                </form>
                                <table class="table table-bordered" id="tb_FatherList">
                                    <!-- cerare document add ajax data-->
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>

</body>
<input type="file" id="ajaxFileUploadHouseLand" name="file" style="display: none;"
       onchange="dataObjFun.inputFileLand();">

<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
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
                var str = '<button onclick="dataObjFun.editDataById(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button onclick="dataObjFun.deleteDataById(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
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
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/dataDispatchRegister/deleteDataDispatchRegisterById",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除数据成功");
                        dataObjFun.loadDataList();
                    }
                    else {
                        AlertError("失败","删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result);
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
                AlertError("失败","调用服务端方法失败，失败原因:" + result);
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
                    AlertSuccess("成功", "数据已成功保存到数据库");
                    $('#' + dataObjFun.config.father.box()).modal('hide');
                    dataObjFun.loadDataList();
                }
                else {
                    AlertError("失败","保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result);
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
            data: {},//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: 'ajaxFileUploadHouseLand',//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                if (result.ret) {
                    dataObjFun.loadDataList();
                    notifyInfo("提示",result.data);
                }
            },
            error: function (result, status, e) {
                Loading.progressHide();
                AlertError("失败","调用服务端方法失败，失败原因:" + result);
            }
        });
    };
</script>
<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">建筑安装费计费维护</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmFather" class="form-horizontal">
                    <input type="hidden" id="id" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                发文日期
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full"
                                                       name="dispatchDate" placeholder="发文日期">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                发文号
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full"
                                                       name="dispatchNumber" placeholder="发文号">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                业务类型
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full"
                                                       name="businessType" placeholder="业务类型">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                委托目的
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full"
                                                       name="entrustPurpose" placeholder="委托目的">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                项目名称
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full"
                                                       name="projectName" placeholder="项目名称">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                客户公司
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full"
                                                       name="clientCompany" placeholder="客户公司">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                委托单位
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full"
                                                       name="entrustUnit" placeholder="委托单位">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                评估面积
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full"
                                                       name="assessArea" placeholder="评估面积">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                评估额(元)
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full"
                                                       name="assessAmount" placeholder="评估额(元)">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                外送份数
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full"
                                                       name="sendNumber" placeholder="外送份数">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                留存份数
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full"
                                                       name="remainNumber" placeholder="留存份数">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                经办人
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full"
                                                       name="operator" placeholder="经办人">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                审批人
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full"
                                                       name="approver" placeholder="审批人">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                交存人
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full"
                                                       name="depositPerson" placeholder="交存人">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                编存人
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full"
                                                       name="redactPerson" placeholder="编存人">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                归档日期
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full"
                                                       name="pigeonholeDate" placeholder="归档日期">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="dataObjFun.saveAndUpdateData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


</html>
