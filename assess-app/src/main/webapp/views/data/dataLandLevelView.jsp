
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
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <form id="frmQuery" class="form-horizontal">
                                <div class="form-group">

                                    <div class="x-valid">
                                        <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                                            <button type="button" class="btn btn-primary"
                                                    onclick="landLevel.initDataForm({})"
                                                    data-toggle="modal" href="#divBoxFather"> 新增
                                            </button>
                                        </div>
                                    </div>

                                    <div class="x-valid">
                                        <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                                            <button type="button" class="btn btn-success"
                                                    onclick="landLevel.loadLandLevelList()">
                                                查询 <i class="fa fa-search" aria-hidden="true"></i>
                                            </button>
                                        </div>
                                    </div>

                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">省</label>
                                        <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                                            <select name="province"
                                                    class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">市</label>
                                        <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                                            <select name="city" class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">县</label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <select name="district"
                                                    class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <table class="table table-bordered" id="tb_FatherList">
                                <!-- cerare document add ajax data-->
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" defer="defer">

    var landLevel = {} ;

    $(document).ready(function () {
        landLevel.showFile = function (target, tableName, id, deleteFlag, editFlag, signatureFlag, fieldsName) {
            FileUtils.getFileShows({
                target: target,
                formData: {
                    tableName: tableName,
                    tableId: id,
                    fieldsName: fieldsName
                    // projectId: id
                },
                signatureFlag: signatureFlag,
                deleteFlag: deleteFlag,
                editFlag: editFlag
            })
        };


        landLevel.fileUpload = function (target, tableName, id, deleteFlag, editFlag, fieldsName) {
            FileUtils.uploadFiles({
                target: target,
                disabledTarget: "btn_submit",
                formData: {
                    tableName: tableName,
                    tableId: id,
                    fieldsName: fieldsName
                    // projectId: id
                },
                deleteFlag: deleteFlag,
                editFlag: editFlag
            });
            // FileUtils.uploadFiles({
            //     target: target,
            //     disabledTarget: "btn_submit",
            //     onUpload: function (file) {
            //         var formData = {
            //             fieldsName: target,
            //             tableName: tableName,
            //             tableId: id
            //         };
            //         return formData;
            //     }, onUploadComplete: function (result, file) {
            //
            //     },
            //     deleteFlag: true
            // });
        };

        var ue = UE.getEditor('landDefinition', {
            toolbars: [
                ['source', 'autotypeset', 'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc']
            ],
            zIndex: 11009,
            initialFrameHeight: 120,
            elementPathEnabled: false,//是否启用元素路径，默认是true显示
            wordCount: false, //是否开启字数统计
            autoHeightEnabled: false,
            autoFloatEnabled: true
        });


        landLevel.config = {
            table: $("#tb_FatherList"),
            box: $("#divBoxFather"),
            frmQuery: $("#frmQuery"),

            land_level_detail_list: $("#land_level_detail_list"),
            land_level_detail_modal: $("#land_level_detail_modal"),
            land_level_detail_list_modal: $("#land_level_detail_list_modal"),


            achievementTable: $("#achievementTable"),
            achievementBox: $("#achievementBox"),
            achievementBoxDetail: $("#achievementBoxDetail"),
            achievementFrm: $("#achievementFrm")
        } ;

        landLevel.loadLandLevelList = function () {
            var cols = [];
            cols.push({
                field: 'provinceName', title: '省', formatter: function (value, row, index) {
                    return AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
                }
            });
            cols.push({field: 'title', title: '标题'});
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
            cols.push({
                field: 'executionTime', title: '执行时间', formatter: function (value, row, index) {
                    return formatDate(value);
                }
            });
            cols.push({field: 'fileViewName', title: '附件'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="landLevel.editData(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="landLevel.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="查看" onclick="landLevel.showLandLevelDetailListModal(' + row.id + ')"><i class="fa fa-th-list fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            landLevel.config.table.bootstrapTable('destroy');
            var query = formSerializeArray(landLevel.config.frmQuery) ;
            var method = {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            } ;
            TableInit(landLevel.config.table, "${pageContext.request.contextPath}/dataLandLevel/getDataLandLevelListVos", cols, query, method);
        };

        landLevel.removeData = function (id) {
            Alert("确认删除!", 2, null, function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/dataLandLevel/deleteDataLandLevelById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            landLevel.config.table.bootstrapTable('refresh');
                        }
                        else {
                            Alert(result.errmsg);
                        }
                    }
                })
            });
        };

        landLevel.initDataForm = function (data) {
            var target = landLevel.config.box ;
            var frm = target.find("form") ;
            frm.clearAll();
            frm.initForm(data);
            if (data.landDefinition){
                setTimeout(function () {
                    ue.setContent(data.landDefinition, false);
                }, 500);
            }
            var files = ['uploadFile'] ;
            $.each(files,function (i,item) {
                landLevel.showFile(item,AssessDBKey.DataLandLevel,data.id,true,true,item) ;
                landLevel.fileUpload(item,AssessDBKey.DataLandLevel,data.id,true,item) ;
            });
            AssessCommon.initAreaInfo({
                provinceTarget: frm.find("select[name='province']"),
                cityTarget: frm.find("select[name='city']"),
                districtTarget: frm.find("select[name='district']"),
                provinceValue: data.province,
                cityValue: data.city,
                districtValue: data.district
            });
        };

        landLevel.saveData = function () {
            var target = landLevel.config.box ;
            var frm = target.find("form") ;
            if (!frm.valid()) {
                return false;
            }
            var data = formSerializeArray(frm);
            data.landDefinition = ue.getContent();
            $.ajax({
                url: "${pageContext.request.contextPath}/dataLandLevel/saveAndUpdateDataLandLevel",
                type: "post",
                dataType: "json",
                data: {formData: JSON.stringify(data)},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        target.modal('hide');
                        landLevel.config.table.bootstrapTable('refresh');
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

        landLevel.editData = function (id) {
            var target = landLevel.config.box ;
            var data = landLevel.config.table.bootstrapTable('getRowByUniqueId', id);
            this.initDataForm(data) ;
            target.modal('show');
        };

        //加载土地级别信息
        landLevel.loadLandLevelDetailList = function () {
            var cols = [];
            cols.push({
                field: 'classify', title: '大类', formatter: function (value, row, index) {
                    return '<span title="' + row.levelRange + '">' + value + '</span>';
                }
            });
            cols.push({field: 'type', title: '类型'});
            cols.push({field: 'category', title: '类别'});
            cols.push({field: 'price', title: '单价'});
            cols.push({field: 'landAcquisitionProportion', title: '征地比例'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="landLevel.editLandLevelDetail(' + index + ')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="landLevel.deleteLandLevelDetail(' + row.id + ')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="查看" onclick="landLevel.showDataLandDetailAchievementDetail(' + row.id + ')"><i class="fa fa-th-list fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            var box = landLevel.config.land_level_detail_modal ;
            var frm = box.find("form") ;
            var query = {landLevelId:frm.find('[name=landLevelId]').val()} ;
            var method = {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            } ;
            landLevel.config.land_level_detail_list.bootstrapTable('destroy');
            TableInit(landLevel.config.land_level_detail_list, "${pageContext.request.contextPath}/dataLandLevel/getDataLandLevelDetailList", cols, query, method);
        };

        //显示土地级别列表窗口
        landLevel.showLandLevelDetailListModal = function (landLevelId) {
            var box = landLevel.config.land_level_detail_list_modal ;
            var box2 = landLevel.config.land_level_detail_modal ;
            var frm = box2.find("form") ;
            frm.find('[name=landLevelId]').val(landLevelId);
            landLevel.loadLandLevelDetailList();
            box.modal();
        };

        //新增土地级别
        landLevel.addLandLevelDetail = function () {
            var box = landLevel.config.land_level_detail_modal ;
            var frm = box.find("form") ;
            frm.clearAll(['landLevelId']);
            box.modal();
        };

        //编辑土地级别
        landLevel.editLandLevelDetail = function (index) {
            var box = landLevel.config.land_level_detail_modal ;
            var frm = box.find("form") ;
            var row = landLevel.config.land_level_detail_list.bootstrapTable('getData')[index];
            frm.clearAll(['landLevelId']);
            frm.initForm(row);
            box.modal();
        };

        //保存土地级别
        landLevel.saveLandLevelDetail = function () {
            var box = landLevel.config.land_level_detail_modal ;
            var frm = box.find("form") ;
            if (!frm.valid()) {
                return false;
            }
            Loading.progressShow();
            $.ajax({
                url: '${pageContext.request.contextPath}/dataLandLevel/saveAndUpdateDataLandLevelDetail',
                data: formSerializeArray(frm),
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('保存成功');
                        landLevel.loadLandLevelDetailList();
                        box.modal('hide');
                    } else {
                        Alert(result.errmsg);
                    }
                }
            })
        };

        //删除土地级别
        landLevel.deleteLandLevelDetail = function (id) {
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
        };

        landLevel.showDataLandDetailAchievementDetail = function (id) {
            landLevel.showLandDetailAchievementList(id);
            landLevel.config.achievementBoxDetail.find("input[name='levelDetailId']").val(id);
            landLevel.config.achievementBoxDetail.modal("show");
        };
        //显示 土地级别详情从表 box
        landLevel.showDataLandDetailAchievement = function () {
            var levelDetailId = landLevel.config.achievementBoxDetail.find("input[name='levelDetailId']").val();
            landLevel.showLandDetailAchievementList(levelDetailId);
            landLevel.initFormLandDetailAchievement({levelDetailId: levelDetailId});
            landLevel.config.achievementBox.modal("show");
        };

        landLevel.initFormLandDetailAchievement = function (row) {
            landLevel.config.achievementFrm.clearAll();
            landLevel.config.achievementFrm.initForm(row);
            AssessCommon.loadDataDicByKey(AssessDicKey.programmeMarketCostapproachFactor, row.type, function (html, data) {
                landLevel.config.achievementFrm.find("select[name='type']").empty().html(html).trigger('change');
            });
            landLevel.config.achievementFrm.find("select[name='type']").off('change').on('change', function () {
                AssessCommon.loadDataDicByPid($(this).val(), row.category, function (html, data) {
                    landLevel.config.achievementFrm.find("select[name='category']").empty().html(html).trigger('change');
                    landLevel.config.achievementFrm.find("select.category").empty().html(html).trigger('change');
                });
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.programmeMarketCostapproachGrade, row.grade, function (html, data) {
                landLevel.config.achievementFrm.find("select[name='grade']").empty().html(html).trigger('change');
            });
            landLevel.config.achievementFrm.find("select[name='grade']").off('change').on('change', function () {
                var category = landLevel.config.achievementFrm.find("select[name='category']").val();
                var grade = landLevel.config.achievementFrm.find("select[name='grade']").val();
                if (category) {
                    AssessCommon.getDataDicInfo(category, function (data) {
                        var obj = JSON.parse(data.remark);
                        if (grade) {
                            AssessCommon.getDataDicInfo(grade, function (item) {
                                var value = null;
                                if (item.name == '优') {
                                    value = obj.A;
                                }
                                if (item.name == '较优') {
                                    value = obj.B;
                                }
                                if (item.name == '一般') {
                                    value = obj.C;
                                }
                                if (item.name == '较劣') {
                                    value = obj.D;
                                }
                                if (item.name == '劣') {
                                    value = obj.E;
                                }
                                if (value) {
                                    landLevel.config.achievementFrm.find("input[name='achievement']").val(value);
                                }
                            });
                        }
                    });
                }
            });
        };

        //土地级别详情从表 删除
        landLevel.deleteDataLandDetailAchievement = function (index) {
            var row = landLevel.config.achievementTable.bootstrapTable('getData')[index];
            Alert('确认要删除么？', 2, null, function () {
                Loading.progressShow();
                $.ajax({
                    url: '${pageContext.request.contextPath}/dataLandDetailAchievement/delete/' + row.id,
                    type: "post",
                    data: {_method: "DELETE"},
                    success: function (result) {
                        Loading.progressHide();
                        if (result.ret) {
                            toastr.success('删除成功');
                            landLevel.showLandDetailAchievementList(row.levelDetailId);
                        } else {
                            Alert(result.errmsg);
                        }
                    }
                })
            })
        };

        landLevel.editDataLandDetailAchievement = function (index) {
            landLevel.config.achievementFrm.clearAll(['levelDetailId']);
            var row = landLevel.config.achievementTable.bootstrapTable('getData')[index];
            landLevel.initFormLandDetailAchievement(row);
            landLevel.config.achievementBox.modal("show");
        };

        //土地级别详情从表
        landLevel.showLandDetailAchievementList = function (levelDetailId) {
            var cols = [];
            cols.push({field: 'typeName', title: '类型'});
            cols.push({field: 'categoryName', title: '类别'});
            cols.push({field: 'gradeName', title: '等级'});
            cols.push({field: 'reamark', title: '说明'});
            cols.push({field: 'achievement', title: '分值'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="landLevel.editDataLandDetailAchievement(' + index + ')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="landLevel.deleteDataLandDetailAchievement(' + index + ')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            landLevel.config.achievementTable.bootstrapTable('destroy');
            TableInit(landLevel.config.achievementTable, "${pageContext.request.contextPath}/dataLandDetailAchievement/getBootstrapTableVo", cols, {
                levelDetailId: levelDetailId
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        };

        //土地级别详情从表 保存或者更新
        landLevel.saveDataLandDetailAchievement = function () {
            var data = formSerializeArray(landLevel.config.achievementFrm);
            if (!landLevel.config.achievementFrm.valid()) {
                return false;
            }
            $.ajax({
                url: '${pageContext.request.contextPath}/dataLandDetailAchievement/save',
                data: {formData: JSON.stringify(data)},
                type: "post",
                success: function (result) {
                    if (result.ret) {
                        toastr.success('成功');
                        landLevel.showLandDetailAchievementList(data.levelDetailId);
                        landLevel.config.achievementBox.modal("hide");
                    } else {
                        Alert(result.errmsg);
                    }
                }
            })
        };

        landLevel.loadLandLevelList();

        (function (frm,data) {
            AssessCommon.initAreaInfo({
                provinceTarget: frm.find("select[name='province']"),
                cityTarget: frm.find("select[name='city']"),
                districtTarget: frm.find("select[name='district']"),
                provinceValue: data.province,
                cityValue: data.city,
                districtValue: data.district
            });
        }(landLevel.config.frmQuery,{})) ;
    });

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
            <form class="form-horizontal">
                <input type="hidden" id="id" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">省
                                            <span class="symbol required"></span></label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <select name="province"
                                                    class="form-control search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">市<span
                                                class="symbol required"></span></label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <select name="city" class="form-control search-select select2"
                                                    required="required">

                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">县</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <select name="district"
                                                    class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">估价期日</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <input type="text" readonly="readonly"
                                                   class="form-control date-picker dbdate" data-date-format="yyyy-mm-dd"
                                                   name="valuationDate" placeholder="估价期日">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">发布日期</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <input type="text" readonly="readonly"
                                                   class="form-control date-picker dbdate" data-date-format="yyyy-mm-dd"
                                                   name="releaseDate" placeholder="发布日期">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">执行时间</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <input type="text" readonly="readonly"
                                                   class="form-control date-picker dbdate" data-date-format="yyyy-mm-dd"
                                                   name="executionTime" placeholder="执行时间">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">标题</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <input placeholder="标题" class="form-control" name="title" type="text">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">文号</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <input placeholder="文号" class="form-control" name="wordSymbol" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            基准地价定义
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <div style="width:99%;height:200px;" id="landDefinition"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附件</label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
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
                     data-toggle="modal" > 新增
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
            <form class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="landLevelId">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">大类
                                            <span class="symbol required"></span></label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input type="text" required class="form-control" name="classify"
                                                   placeholder="大类">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">类型<span
                                                class="symbol required"></span></label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input type="text" required class="form-control" name="type"
                                                   placeholder="类型">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">类别</label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input type="text" class="form-control" name="category" placeholder="类别">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">单价</label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input type="text" data-rule-number='true' class="form-control" name="price"
                                                   placeholder="单价">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">征地比例</label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input type="text" class="form-control" name="landAcquisitionProportion"
                                                   placeholder="征地比例">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            级别范围
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <textarea class="form-control" name="levelRange"
                                                      placeholder="级别范围"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            主要街道
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
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

<div id="achievementBoxDetail" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土地级别分值列表</h3>
                <input type="hidden" name="levelDetailId">
            </div>
            <div class="modal-body">
                <div type="button" class="btn btn-success"
                     onclick="landLevel.showDataLandDetailAchievement()"
                     data-toggle="modal" href="#divBox"> 新增
                </div>
                <table class="table table-bordered" id="achievementTable">
                    <!-- cerare document add ajax data-->
                </table>
            </div>
        </div>
    </div>
</div>

<div id="achievementBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土地级别分值</h3>
            </div>
            <form id="achievementFrm" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="levelDetailId">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">类型<span
                                                class="symbol required"></span></label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <select name="type" required
                                                    class="form-control search-select select2 type">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">类别<span
                                                class="symbol required"></span></label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <select name="category" required
                                                    class="form-control search-select select2 category">
                                                <option>请先选择类型</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">等级<span
                                                class="symbol required"></span></label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <select name="grade" required
                                                    class="form-control search-select select2 grade">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">分值<span
                                                class="symbol required"></span></label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input type="text" data-rule-number='true' required class="form-control"
                                                   name="achievement"
                                                   placeholder="分值">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">说明</label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <textarea class="form-control" name="reamark"
                                                      placeholder="说明"></textarea>
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
                    <button type="button" class="btn btn-primary" onclick="landLevel.saveDataLandDetailAchievement()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>
