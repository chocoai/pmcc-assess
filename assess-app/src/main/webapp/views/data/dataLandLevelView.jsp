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
                        <div class="col-sm-3">
                            <button type="button" class="btn btn-success"
                                    onclick="landLevel.showModel()"
                                    data-toggle="modal" href="#divBox"> 新增
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
    <!-- end: MAIN CONTAINER -->
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    $(function () {
        landLevel.loadLandLevelList();
    });

    var landLevel = {
        config: function () {
            var data = {};
            data.table = "tb_FatherList";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            return data;
        },

        loadLandLevelList: function () {
            var cols = [];
            cols.push({field: 'provinceName', title: '省'});
            cols.push({field: 'cityName', title: '市'});
            cols.push({field: 'districtName', title: '县'});
            cols.push({
                field: 'valuationDate', title: '估价期日', formatter: function (value, row, index) {
                    return formatDate(value);
                }
            });
            cols.push({
                field: 'releaseDate', title: '发布日期', formatter: function (value, row, index) {
                    return formatDate(value);
                }
            });
            cols.push({field: 'fileViewName', title: '附件'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="landLevel.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="landLevel.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="查看" onclick="landLevel.showLandLevelDetailListModal(' + row.id + ')"><i class="fa fa-th-list fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + landLevel.config().table).bootstrapTable('destroy');
            TableInit(landLevel.config().table, "${pageContext.request.contextPath}/dataLandLevel/getDataLandLevelList", cols, {
                province: $("#queryProvince").val(),
                city: $("#queryCity").val(),
                district: $("#queryDistrict").val()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            Alert("确认删除!", 2, null, function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/dataLandLevel/deleteDataLandLevelById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            landLevel.loadLandLevelList();
                        }
                        else {
                            Alert(result.errmsg);
                        }
                    }
                })
            });
        },
        showModel: function () {
            $("#" + landLevel.config().frm).clearAll();
            AssessCommon.initAreaInfo({
                provinceTarget: $("#province"),
                cityTarget: $("#city"),
                districtTarget: $("#district"),
                provinceValue: '',
                cityValue: '',
                districtValue: ''
            });

            FileUtils.uploadFiles({
                target: "uploadFile",
                onUpload: function (file) {
                    var formData = {
                        tableName: AssessDBKey.DataLandLevel,
                        tableId: 0
                    };
                    return formData;
                }, onUploadComplete: function (result, file) {
                    landLevel.showFileList(0);
                },
                deleteFlag: true
            });
            $('#' + landLevel.config().box).modal("show");
        },

        //显示附件列表
        showFileList: function (tableId) {
            FileUtils.getFileShows({
                target: "uploadFile",
                formData: {
                    tableName: AssessDBKey.DataLandLevel,
                    tableId: tableId
                },
                deleteFlag: true
            })
        },

        saveData: function () {
            if (!$("#" + landLevel.config().frm).valid()) {
                return false;
            }
            var data = formParams(landLevel.config().frm);
            $.ajax({
                url: "${pageContext.request.contextPath}/dataLandLevel/saveAndUpdateDataLandLevel",
                type: "post",
                dataType: "json",
                data: {formData: JSON.stringify(data)},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#' + landLevel.config().box).modal('hide');
                        landLevel.loadLandLevelList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/dataLandLevel/getDataLandLevelById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + landLevel.config().frm).clearAll();
                        $("#" + landLevel.config().frm).initForm(result.data);
                        AssessCommon.initAreaInfo({
                            provinceTarget: $("#province"),
                            cityTarget: $("#city"),
                            districtTarget: $("#district"),
                            provinceValue: result.data.province,
                            cityValue: result.data.city,
                            districtValue: result.data.district
                        });
                        FileUtils.uploadFiles({
                            target: "uploadFile",
                            onUpload: function (file) {
                                var formData = {
                                    tableName: AssessDBKey.DataLandLevel,
                                    tableId: id
                                };
                                return formData;
                            }, onUploadComplete: function (data, file) {
                                landLevel.showFileList(id);
                            },
                            deleteFlag: true
                        });
                        landLevel.showFileList(id);
                        $('#' + landLevel.config().box).modal("show");
                    }
                }
            })
        },

        //加载土地级别信息
        loadLandLevelDetailList: function () {
            var cols = [];
            cols.push({field: 'classify', title: '大类', width: '6%'});
            cols.push({field: 'type', title: '类型', width: '6%'});
            cols.push({field: 'category', title: '类别', width: '6%'});
            cols.push({field: 'levelRange', title: '级别范围', width: '70%', formatter: function (value, row, index) {
                return '<span title="'+row.mainStreet+'">'+value+'</span>';
            }});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="landLevel.editLandLevelDetail(' + index + ')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="landLevel.deleteLandLevelDetail(' + row.id + ')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#land_level_detail_list").bootstrapTable('destroy');
            TableInit("land_level_detail_list", "${pageContext.request.contextPath}/dataLandLevel/getDataLandLevelDetailList", cols, {
                landLevelId: $('#land_level_detail_form').find('[name=landLevelId]').val()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },

        //显示土地级别列表窗口
        showLandLevelDetailListModal: function (landLevelId) {
            $('#land_level_detail_form').find('[name=landLevelId]').val(landLevelId);
            landLevel.loadLandLevelDetailList();
            $('#land_level_detail_list_modal').modal();
        },

        //新增土地级别
        addLandLevelDetail: function () {
            $('#land_level_detail_form').clearAll(['landLevelId']);
            $('#land_level_detail_modal').modal();
        },

        //编辑土地级别
        editLandLevelDetail: function (index) {
            var row = $("#land_level_detail_list").bootstrapTable('getData')[index];
            $("#land_level_detail_form").clearAll(['landLevelId']);
            $("#land_level_detail_form").initForm(row);
            $('#land_level_detail_modal').modal();
        },

        //保存土地级别
        saveLandLevelDetail: function () {
            Loading.progressShow();
            $.ajax({
                url: '${pageContext.request.contextPath}/dataLandLevel/saveAndUpdateDataLandLevelDetail',
                data: formSerializeArray($("#land_level_detail_form")),
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('保存成功');
                        landLevel.loadLandLevelDetailList();
                        $('#land_level_detail_modal').modal('hide');
                    } else {
                        Alert(result.errmsg);
                    }
                }
            })
        },

        //删除土地级别
        deleteLandLevelDetail: function (id) {
            Alert('确认要删除么？', 2, null, function () {
                Loading.progressShow();
                $.ajax({
                    url: '${pageContext.request.contextPath}/dataLandLevel/removeDataLandLevelDetail',
                    data: {id: id},
                    success: function (result) {
                        Loading.progressHide();
                        if (result.ret) {
                            toastr.success('删除成功');
                            landLevel.loadLandLevelDetailList();
                        } else {
                            Alert(result.errmsg);
                        }
                    }
                })
            })
        }
    }
</script>
<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土地级别区域</h3>
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
                                        <label class="col-sm-2 control-label">估价期日</label>
                                        <div class="col-sm-10">
                                            <input type="text" readonly="readonly"
                                                   class="form-control date-picker dbdate" data-date-format="yyyy-mm-dd"
                                                   name="valuationDate" placeholder="估价期日">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">发布日期</label>
                                        <div class="col-sm-10">
                                            <input type="text" readonly="readonly"
                                                   class="form-control date-picker dbdate" data-date-format="yyyy-mm-dd"
                                                   name="releaseDate" placeholder="发布日期">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">附件</label>
                                        <div class="col-sm-10">
                                            <input id="uploadFile" placeholder="上传附件" class="form-control" type="file">
                                            <div id="_uploadFile"></div>
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
                    <button type="button" class="btn btn-primary" onclick="landLevel.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="land_level_detail_list_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土地级别内容</h3>
            </div>
            <div class="modal-body">
                <div type="button" class="btn btn-success"
                     onclick="landLevel.addLandLevelDetail()"
                     data-toggle="modal" href="#divBox"> 新增
                </div>
                <table class="table table-bordered" id="land_level_detail_list">
                    <!-- cerare document add ajax data-->
                </table>
            </div>
        </div>
    </div>
</div>

<div id="land_level_detail_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土地级别内容</h3>
            </div>
            <form id="land_level_detail_form" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="landLevelId">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">大类
                                            <span class="symbol required"></span></label>
                                        <div class="col-sm-10">
                                            <input type="text" required class="form-control" name="classify"
                                                   placeholder="大类">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">类型<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-10">
                                            <input type="text" required class="form-control" name="type"
                                                   placeholder="类型">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">类别</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="category" placeholder="类别">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            级别范围
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea class="form-control" name="levelRange"
                                                      placeholder="级别范围"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            主要街道
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea class="form-control" name="mainStreet"
                                                      placeholder="主要街道"></textarea>
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
                    <button type="button" class="btn btn-primary" onclick="landLevel.saveLandLevelDetail()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>
