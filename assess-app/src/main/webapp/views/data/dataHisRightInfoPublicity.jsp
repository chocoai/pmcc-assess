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
                                        <%--<button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"--%>
                                                <%--onclick="dataHisRightInfoPublicity.prototype.loadDataDicList()">--%>
											<%--<span class="btn-label">--%>
												<%--<i class="fa fa-search"></i>--%>
											<%--</span>--%>
                                            <%--查询--%>
                                        <%--</button>--%>
                                        <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                                data-toggle="modal" onclick="dataHisRightInfoPublicity.prototype.showModel()"
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

<script type="text/javascript">
    $(function () {
        dataHisRightInfoPublicity.prototype.loadDataDicList();
    });

    var ue = UE.getEditor('template', {
        toolbars: [
            ['source', 'autotypeset', 'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc']
        ],
        zIndex: 11009,
        initialFrameHeight: 220,
        elementPathEnabled: false,//是否启用元素路径，默认是true显示
        wordCount: false, //是否开启字数统计
        autoHeightEnabled: false,
        autoFloatEnabled: true
    });

    var dataHisRightInfoPublicity = function () {

    };
    dataHisRightInfoPublicity.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_FatherList";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            return data;
        },
        loadDataDicList: function () {
            var cols = [];
            cols.push({
                field: 'provinceName', width: '15%',title: '区域', formatter: function (value, row, index) {
                    return AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName)
                }
            });
            cols.push({field: 'content', title: '模板', width: '70%'});
            cols.push({
                field: 'id', width: '15%',title: '操作', formatter: function (value, row, index) {
                    var str = '<button onclick="dataHisRightInfoPublicity.prototype.getAndInit(' + row.id + ')"  style="margin-left: 5px;"  class="btn btn-icon btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                    str += '<i class="fa fa-pen"></i>';
                    str += '</button>';
                    str += '<button onclick="dataHisRightInfoPublicity.prototype.removeData(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn btn-icon btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                    str += '<i class="fa fa-minus"></i>';
                    str += '</button>';
                    return str;
                }
            });
            $("#" + dataHisRightInfoPublicity.prototype.config().table).bootstrapTable('destroy');
            TableInit(dataHisRightInfoPublicity.prototype.config().table, "${pageContext.request.contextPath}/dataHisRightInfoPublicity/list", cols, {}, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/dataHisRightInfoPublicity/deleteDataHisRightInfoPublicityById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除数据成功");
                            dataHisRightInfoPublicity.prototype.loadDataDicList();
                        }
                        else {
                            AlertError("删除数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        AlertError("调用服务端方法失败，失败原因:" + result);
                    }
                })
            });
        },
        showModel: function () {
            $("#" + dataHisRightInfoPublicity.prototype.config().frm).clearAll();
            $('#' + dataHisRightInfoPublicity.prototype.config().box).modal("show");
            AssessCommon.initAreaInfo({
                provinceTarget: $("#province"),
                cityTarget: $("#city"),
                districtTarget: $("#district"),
                provinceValue: '',
                cityValue: '',
                districtValue: ''
            });
        },
        saveData: function () {
            if (!$("#" + dataHisRightInfoPublicity.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(dataHisRightInfoPublicity.prototype.config().frm);
            data.content = ue.getContent();
            $.ajax({
                url: "${pageContext.request.contextPath}/dataHisRightInfoPublicity/saveAndUpdateDataHisRightInfoPublicity",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        $('#' + dataHisRightInfoPublicity.prototype.config().box).modal('hide');
                        dataHisRightInfoPublicity.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/dataHisRightInfoPublicity/getDataHisRightInfoPublicityById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + dataHisRightInfoPublicity.prototype.config().frm).clearAll();
                        $("#" + dataHisRightInfoPublicity.prototype.config().frm).initForm(result.data);
                        AssessCommon.initAreaInfo({
                            provinceTarget: $("#province"),
                            cityTarget: $("#city"),
                            districtTarget: $("#district"),
                            provinceValue: result.data.province,
                            cityValue: result.data.city,
                            districtValue: result.data.district
                        })

                        var content = result.data.content;
                        setTimeout(function () {
                            ue.setContent(content, false);
                        }, 500);
                        $('#' + dataHisRightInfoPublicity.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        }

    }
</script>
<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">他权信息公示</h4>
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
                                                省<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select id="province" name="province"
                                                        class="form-control input-full  search-select select2"
                                                        required="required">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                市
                                            </label>
                                            <div class="col-sm-10">
                                                <select id="city" name="city" class="form-control input-full  search-select select2"
                                                        required="required">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                县
                                            </label>
                                            <div class="col-sm-10">
                                                <select id="district" name="district"
                                                        class="form-control input-full  search-select select2">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                模版<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-11">
                                                <div style="width:99%;height:200px;" id="template"></div>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="dataHisRightInfoPublicity.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


</html>

