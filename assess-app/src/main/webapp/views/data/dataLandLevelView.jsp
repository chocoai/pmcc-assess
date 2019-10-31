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
                                        <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                                            <input placeholder="乡镇名称" class="form-control" name="townShipName"
                                                   type="text">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">权利类型</label>
                                        <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                                            <select name="landRightType" class="form-control search-select select2">
                                            </select>
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

    var landLevel = {};

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
            if (!id){
                id = 0;
            }
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
            achievementFrm: $("#achievementFrm"),


            dataAllocationCorrectionCoefficientVolumeRatioDetailTableBox: $("#dataAllocationCorrectionCoefficientVolumeRatioDetailTableBox"),
            dataAllocationCorrectionCoefficientVolumeRatioDetailTable: $("#dataAllocationCorrectionCoefficientVolumeRatioDetailTable"),
            dataAllocationCorrectionCoefficientVolumeRatioDetailBox: $("#dataAllocationCorrectionCoefficientVolumeRatioDetailBox"),
            dataAllocationCorrectionCoefficientVolumeRatioDetailFrm: $("#dataAllocationCorrectionCoefficientVolumeRatioDetailFrm")
        };

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
            cols.push({field: 'landRightTypeName', title: '权利类型'});
            cols.push({field: 'townShipName', title: '乡镇名称'});
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
            var query = formSerializeArray(landLevel.config.frmQuery);
            //只获取有值的对象属性
            var tempObj = Object.keys(query);
            var select = {} ;
            for (var i = 0; i < tempObj.length; i++) {
                    var key = tempObj[i] ;
                    var value = query[key] ;
                    //判断是否存在值
                    if (value){
                        select[key]  = value;//动态添加属性
                    }
            }
            var method = {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            };
            TableInit(landLevel.config.table, "${pageContext.request.contextPath}/dataLandLevel/getDataLandLevelListVos", cols, select, method);
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
            var target = landLevel.config.box;
            var frm = target.find("form");
            frm.clearAll();
            frm.initForm(data);
            if (data.landDefinition) {
                setTimeout(function () {
                    ue.setContent(data.landDefinition, false);
                }, 500);
            }
            var files = ['uploadFile'];
            $.each(files, function (i, item) {
                landLevel.showFile(item, AssessDBKey.DataLandLevel, data.id, true, true, item);
                landLevel.fileUpload(item, AssessDBKey.DataLandLevel, data.id, true, item);
            });
            AssessCommon.initAreaInfo({
                provinceTarget: frm.find("select[name='province']"),
                cityTarget: frm.find("select[name='city']"),
                districtTarget: frm.find("select[name='district']"),
                provinceValue: data.province,
                cityValue: data.city,
                districtValue: data.district
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareLandCertificateType, data.landRightType, function (html, data) {
                frm.find("select[name='landRightType']").empty().html(html).trigger('change');
            });
        };

        landLevel.saveData = function () {
            var target = landLevel.config.box;
            var frm = target.find("form");
            if (!frm.valid()) {
                return false;
            }
            var data = formSerializeArray(frm);
            data.landDefinition = ue.getContent();
            $.ajax({
                url: "${pageContext.request.contextPath}/dataLandLevel/submitDataLandLevel",
                type: "get",
                dataType: "json",
                data: {formData: JSON.stringify(data)},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('实例添加成功,审批通过之后即可使用');
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
            var target = landLevel.config.box;
            var data = landLevel.config.table.bootstrapTable('getRowByUniqueId', id);
            this.initDataForm(data);
            target.modal('show');
        };

        //加载土地级别信息
        landLevel.loadLandLevelDetailList = function (pid) {
            var cols = [];
            cols.push({
                field: 'classifyName', title: '大类', formatter: function (value, row, index) {
                    return '<span title="' + row.levelRange + '">' + value + '</span>';
                }
            });
            cols.push({field: 'typeName', title: '类型'});
//            cols.push({field: 'category', title: '类别'});
            cols.push({field: 'price', title: '平方价'});
            cols.push({field: 'volumeRate', title: '容积率'});
            cols.push({field: 'legalAge', title: '法定使用年限'});
            cols.push({field: 'landAcquisitionProportion', title: '征地比例'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success" href="javascript:landLevel.setSubDataDic(' + row.id + ');" ><i class="fa fa-edit">查看子项</i></a>';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="landLevel.editLandLevelDetail(' + index + ')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="landLevel.deleteLandLevelDetail(' + row.id + ')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-success" href="javascript:landLevel.showDataLandDetailAchievementDetail(' + row.id + ');" ><i class="fa fa-th-list fa-white">查看关联子项 土地因素</i></a>';
                    str += '<a class="btn btn-xs btn-success" href="javascript:landLevel.showDataAllocationCorrectionCoefficientVolumeRatioDetail(' + row.id + ');" ><i class="fa fa-th-list fa-white">查看关联子项 容积率修正系数</i></a>';
                    str += '</div>';
                    return str;
                }
            });
            var box = landLevel.config.land_level_detail_modal;
            var frm = box.find("form");
            var query = {landLevelId: frm.find('[name=landLevelId]').val()};
            if (pid){
                query.pid = pid;
            }
            var method = {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            };
            landLevel.config.land_level_detail_list.bootstrapTable('destroy');
            TableInit(landLevel.config.land_level_detail_list, "${pageContext.request.contextPath}/dataLandLevel/getDataLandLevelDetailList", cols, query, method);
        };

        //显示土地级别列表窗口
        landLevel.showLandLevelDetailListModal = function (landLevelId) {
            var box = landLevel.config.land_level_detail_list_modal;
            var box2 = landLevel.config.land_level_detail_modal;
            var frm = box2.find("form");
            frm.find('[name=landLevelId]').val(landLevelId);
            landLevel.loadLandLevelDetailList();
            box.modal();
        };

        //新增土地级别
        landLevel.addLandLevelDetail = function () {
            var box = landLevel.config.land_level_detail_modal;
            landLevel.initLandLevelDetailForm({}) ;
            box.modal();
        };

        //编辑土地级别
        landLevel.editLandLevelDetail = function (index) {
            var box = landLevel.config.land_level_detail_modal;
            var row = landLevel.config.land_level_detail_list.bootstrapTable('getData')[index];
            landLevel.initLandLevelDetailForm(row) ;
            box.modal();
        };

        //土地级别赋值
        landLevel.initLandLevelDetailForm = function (data) {
            var box = landLevel.config.land_level_detail_modal;
            var frm = box.find("form");
            var item = formSerializeArray(frm) ;
            frm.clearAll();
            data.landLevelId = item.landLevelId ;
            data.pid = item.pid ;
            frm.initForm(data);

            AssessCommon.loadDataDicByKey(AssessDicKey.DATA_LAND_LEVEL_CLASSIFY, data.landRightType, function (html, data) {
                var ele = frm.find("select[name='classify']") ;
                ele.empty().html(html).trigger('change');
                if (item.pid){
                    landLevel.getDataLandLevelDetailById(item.pid,function (result) {
                        ele.val(result.classify).trigger("change") ;
                    });
                }
            });

            AssessCommon.loadDataDicByKey(AssessDicKey.DATA_LAND_LEVEL_ROMAN, data.landRightType, function (html, data) {
                frm.find("select[name='type']").empty().html(html).trigger('change');
            });
        };

        landLevel.getDataLandLevelDetailById = function (id ,callback) {
            $.ajax({
                url: '${pageContext.request.contextPath}/dataLandLevel/getDataLandLevelDetailById',
                data: {id:id},
                method:"get",
                success: function (result) {
                    if (result.ret) {
                        if (callback){
                            callback(result.data) ;
                        }
                    } else {
                        Alert(result.errmsg);
                    }
                }
            })
        };

        //保存土地级别
        landLevel.saveLandLevelDetail = function () {
            var box = landLevel.config.land_level_detail_modal;
            var frm = box.find("form");
            if (!frm.valid()) {
                return false;
            }
            var data = formSerializeArray(frm) ;
            Loading.progressShow();
            $.ajax({
                url: '${pageContext.request.contextPath}/dataLandLevel/saveAndUpdateDataLandLevelDetail',
                data: data,
                method:"post" ,
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('保存成功');
                        if (data.pid){
                            landLevel.loadLandLevelDetailList(data.pid);
                        }else {
                            landLevel.loadLandLevelDetailList();
                        }
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

        landLevel.setSubDataDic = function (pid) {
            var box = landLevel.config.land_level_detail_modal;
            var frm = box.find("form");
            frm.find("[name='pid']").val(pid) ;
            landLevel.loadLandLevelDetailList(pid);
            this.getLevelHtml(pid, landLevel.config.land_level_detail_list_modal.find("h3[name='titleContent']"));
        };

        var strLevelHtml = "";
        landLevel.getLevelHtml = function (id, target) {
            $.ajax({
                url: "${pageContext.request.contextPath}/dataLandLevel/getDataLandLevelDetailLevel",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        strLevelHtml = "";
                        if (result.data) {
                            if (result.data.keyValueDto) {
                                landLevel.getDataDicLevelRecursion(result.data.keyValueDto);
                            }
                            strLevelHtml += '<a href="javascript:landLevel.setSubDataDic(' + result.data.key + ')">' + result.data.value + '</a>' + ">";
                            target.html(strLevelHtml.replace(/>$/, ""));
                        }
                    } else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        };

        landLevel.getDataDicLevelRecursion = function (keyValueDto) {
            if (keyValueDto) {
                landLevel.getDataDicLevelRecursion(keyValueDto.keyValueDto);
                strLevelHtml += '<a href="javascript:landLevel.setSubDataDic(' + keyValueDto.key + ')">' + keyValueDto.value + '</a>' + ">";
            }
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

        landLevel.showDataAllocationCorrectionCoefficientVolumeRatioDetail = function (id) {
            landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTableBox.find("input[name='allocationVolumeRatioId']").val(id);
            landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTableBox.modal("show");
            landLevel.showDataHousePriceIndexDetailList(id);
        };

        landLevel.showDataAllocationCorrectionCoefficientVolumeRatioDetailBox = function () {
            landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailFrm.clearAll();
            var allocationVolumeRatioId = landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTableBox.find("input[name='allocationVolumeRatioId']").val();
            landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailFrm.find('input[name=allocationVolumeRatioId]').val(allocationVolumeRatioId);
        };

        landLevel.deleteDataAllocationCorrectionCoefficientVolumeRatioDetail = function (index) {
            var row = landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTable.bootstrapTable('getData')[index];
            Alert('确认要删除么？', 2, null, function () {
                Loading.progressShow();
                $.ajax({
                    url: '${pageContext.request.contextPath}/dataAllocationCorrectionCoefficientVolumeRatioDetail/delete/' + row.id,
                    type: "post",
                    data: {_method: "DELETE"},
                    success: function (result) {
                        Loading.progressHide();
                        if (result.ret) {
                            toastr.success('删除成功');
                            landLevel.showDataHousePriceIndexDetailList(row.allocationVolumeRatioId);
                        } else {
                            Alert(result.errmsg);
                        }
                    }
                })
            })
        };

        landLevel.editDataAllocationCorrectionCoefficientVolumeRatioDetail = function (index) {
            var row = landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTable.bootstrapTable('getData')[index];
            var frm = landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailFrm;
            frm.clearAll();
            frm.initForm(row);
            landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailBox.modal("show");
        };

        landLevel.saveDataAllocationCorrectionCoefficientVolumeRatioDetail = function () {
            var frm = landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailFrm;
            var data = formSerializeArray(frm);
            if (!frm.valid()) {
                return false;
            }
            $.ajax({
                url: '${pageContext.request.contextPath}/dataAllocationCorrectionCoefficientVolumeRatioDetail' +"/save",
                data: data,
                type: "post",
                success: function (result) {
                    if (result.ret) {
                        toastr.success('成功');
                        landLevel.showDataHousePriceIndexDetailList(data.allocationVolumeRatioId);
                        landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailBox.modal("hide");
                    } else {
                        Alert(result.errmsg);
                    }
                }
            })
        };

        landLevel.showDataHousePriceIndexDetailList = function (allocationVolumeRatioId) {
            var cols = [];
            cols.push({field: 'plotRatio', title: '容积率'});
            cols.push({field: 'correctionFactor', title: '修正系数'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="landLevel.editDataAllocationCorrectionCoefficientVolumeRatioDetail(' + index + ')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="landLevel.deleteDataAllocationCorrectionCoefficientVolumeRatioDetail(' + index + ')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTable.bootstrapTable('destroy');
            TableInit(landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTable, "${pageContext.request.contextPath}/dataAllocationCorrectionCoefficientVolumeRatioDetail/getBootstrapTableVo", cols, {
                allocationVolumeRatioId: allocationVolumeRatioId
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        };

        landLevel.loadLandLevelList();

        (function (frm, data) {
            AssessCommon.initAreaInfo({
                provinceTarget: frm.find("select[name='province']"),
                cityTarget: frm.find("select[name='city']"),
                districtTarget: frm.find("select[name='district']"),
                provinceValue: data.province,
                cityValue: data.city,
                districtValue: data.district
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareLandCertificateType, data.landRightType, function (html, data) {
                frm.find("select[name='landRightType']").empty().html(html).trigger('change');
            });
        }(landLevel.config.frmQuery, {}));
    });

</script>

<%@include file="/views/data/landModelDir/landModel.jsp" %>

</html>
