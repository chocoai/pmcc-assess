<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <div class="x_panel">
                <div class="x_title">
                    <h3> 估价委托书及相关证明</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="x_title">
                        <h4>点击选择权证</h4>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <form class="form-horizontal">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    省
                                </label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <select id="queryProvince" class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    市
                                </label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <select id="queryCity" class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    区
                                </label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <select id="queryDistrict" class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">名称</label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <input type="text" class="form-control" id="queryName"/>
                                </div>
                            </div>
                        </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">坐落</label>
                                    <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                        <input type="text" class="form-control" id="querySeat"/>
                                    </div>
                                </div>
                            <div class="x-valid">
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <label class="btn btn-primary" onclick="reloadDeclareList();">
                                        查询
                                    </label>
                                </div>
                            </div>
                        </div>
                        <table class="table table-bordered" id="declareTable">
                            <!-- cerare document add ajax data-->
                        </table>
                        </form>
                    </div>


                    <div class="x_panel">
                        <div class="x_title collapse-link">
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                            </ul>
                            <h4>估价委托书</h4>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content collapse">
                            <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                <input id="project_proxy" name="project_proxy" type="file" multiple="false">
                                <div id="_project_proxy"></div>
                            </div>
                        </div>
                    </div>
                    <div class="x_panel">
                        <div class="x_title collapse-link">
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                            </ul>
                            <h4>位置示意图</h4>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content collapse">
                            <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6 ">
                                <table class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th>权证对象</th>
                                        <th>位置图</th>
                                    </tr>
                                    </thead>
                                    <tbody id="positionTbody">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="x_panel">
                        <div class="x_title collapse-link">
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                            </ul>
                            <h4>实况照片</h4>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content collapse" id="liveSituationHtml">
                        </div>
                    </div>
                    <div class="x_panel">
                        <div class="x_title collapse-link">
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                            </ul>
                            <h4>权属证明复印件</h4>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content collapse" id="ownershipCertHtml">

                        </div>
                    </div>
                    <div class="x_panel">
                        <div class="x_title collapse-link">
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                            </ul>
                            <h4>关联土地证附件</h4>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content collapse" id="landFileHtml">
                        </div>
                    </div>
                    <div class="x_panel">
                        <div class="x_title collapse-link">
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                            </ul>
                            <h4>
                                估价中引用的专用文件资料
                            </h4>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content collapse" id="ReportFileCustom">


                        </div>
                    </div>

                </div>
            </div>

            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button id="btn_submit" class="btn btn-success" onclick="submit()">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>

<div id="addItemModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">添加照片</h3>
            </div>
            <form id="frmItemFile" class="form-horizontal">
                <input type="hidden" name="declareRecordId">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">名称<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" required
                                                   name="fileName" placeholder="名称">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">排序<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" required
                                                   name="sorting" placeholder="排序">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">对应查勘部位<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select name="certifyPart"
                                                    class="form-control search-select certifyPart select2" required>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">附件类别<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select name="certifyPartCategory"
                                                    class="form-control search-select certifyPartCategory select2"
                                                    required>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            是否上报告
                                        </label>
                                        <div class="col-sm-3">
                                            <label class="checkbox-inline">
                                                <input type="checkbox" id="bisEnable" name="bisEnable"
                                                       value="true" checked>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">备注</label>
                                        <div class="col-sm-10">
                                    <textarea placeholder="备注" name="remark"
                                              class="form-control"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">附件</label>
                                        <div class="col-sm-10">
                                            <input id="uploadSupplementFile" placeholder="上传附件" class="form-control"
                                                   type="file">
                                            <div id="_uploadSupplementFile"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        关闭
                    </button>
                    <button type="button" onclick="saveItemFileData()" class="btn btn-primary">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<div id="allExamineFileModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">查勘照片</h3>
            </div>
            <form id="allExamineFileFrm" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th>序号</th>
                                            <th>文件名称</th>
                                            <th>附件</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody data-id="all_live_situation">
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        关闭
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<div id="selectPictureTypeModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">模板类型</h3>
            </div>
            <form id="frmPictureType" class="form-horizontal">
                <input type="hidden" name="declareRecordId">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">类型<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select name="type" class="form-control search-select select2" required>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        关闭
                    </button>
                    <button type="button" onclick="affirmPictureTemplate()" class="btn btn-primary">
                        确认
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<div id="addPictureModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">添加照片</h3>
            </div>
            <form id="frmPicture" class="form-horizontal">
                <input type="hidden" name="declareRecordId">
                <input type="hidden" name="certifyPartCategory">
                <input type="hidden" name="id">
                <input type="hidden" name="bisEnable">
                <div class="modal-body">
                    <input type="button" class="btn btn-primary btn-xs"
                           onclick="correspondingSitePic()"
                           value="选择对应位置查勘图片">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">附件</label>
                                        <div class="col-sm-10">
                                            <input id="uploadPicture" placeholder="上传附件" class="form-control"
                                                   type="file">
                                            <div id="_uploadPicture"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        关闭
                    </button>
                    <button type="button" onclick="savePicture()" class="btn btn-primary">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/html" id="reportFileCustomHtml">
    <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6 ">
        <div class="x_panel">
            <div class="x_title">
                <label>{name}</label>
                <input type="button" class="btn btn-xs btn-warning pull-right" value="删除"
                       onclick="deleteReportFileCustom(this,'{id}');"
                       style="margin-left: 5px;">
            </div>
            <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6 ">
                <input id="reportFileCustom{id}" name="reportFileCustom" type="file" multiple="false">
                <div id="_reportFileCustom{id}"></div>
            </div>
        </div>
    </div>
</script>
<script type="application/javascript">
    $(function () {
        AssessCommon.initAreaInfo({
            provinceTarget: $("#queryProvince"),
            cityTarget: $("#queryCity"),
            districtTarget: $("#queryDistrict"),
            provinceValue: '',
            cityValue: '',
            districtValue: ''
        });

        reloadDeclareList();
        uploadFiles(AssessDBKey.ProjectInfo, "${projectInfo.id}", AssessUploadKey.PROJECT_PROXY);
        loadUploadFiles(AssessDBKey.ProjectInfo, "${projectInfo.id}", AssessUploadKey.PROJECT_PROXY);
    });


    function getLandFileAllByDeclareRecord(tbody, declareRecordId) {
        $.ajax({
            url: '${pageContext.request.contextPath}/projectReportFile/getLandCertId',
            data: {
                declareRecordId: declareRecordId
            },
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        getLandFileAll(tbody, result.data);
                    }
                } else {
                    Alert(result.errmsg);
                }
            }
        })

    }

    function uploadLandFile(tableName, target, tbody, declareRecordId) {
        $.ajax({
            url: '${pageContext.request.contextPath}/projectReportFile/getLandCertId',
            data: {
                declareRecordId: declareRecordId
            },
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        var html = '';
                        html += '<label class="col-sm-1 control-label">附件</label>';
                        html += '<div class="col-sm-10">';
                        html += '<input id="uploadlandFile' + declareRecordId + '" class="form-control"type="file">';
                        html += '<div id="uploadlandFile' + declareRecordId + '">';
                        html += '</div>';
                        html += '</div>';

                        $('div[data-id=' + declareRecordId + '][data-name=land_file_btn]').empty().append(html);
                        var tableId = result.data
                        FileUtils.uploadFiles({
                            target: target,
                            disabledTarget: "btn_submit",
                            formData: {
                                tableName: tableName,
                                tableId: tableId,
                            },
                            onUploadComplete: function () {
                                getLandFileAll(tbody, tableId);
                            },
                            editFlag: true,
                            deleteFlag: true
                        });
                    } else {
                        var html = '';
                        html += '<label class="control-label">未关联土地证</label>';
                        $('div[data-id=' + declareRecordId + '][data-name=land_file_btn]').empty().append(html);
                    }
                } else {
                    Alert(result.errmsg);
                }
            }
        })

    }

    //加载土地证附件
    function getLandFileAll(tbody, tableId) {
        $.ajax({
            url: '${pageContext.request.contextPath}/projectReportFile/getLandFileAll',
            data: {
                tableId: tableId
            },
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        var html = '';
                        $.each(result.data, function (i, item) {
                            ++i;
                            html += '<tr><th scope="row">' + i + '</th><td>' + item.fileName + '</td><td>' +
                                '<input type="button" class="btn btn-xs btn-primary" value="编辑" onclick="FileUtils.editAttachment(' + item.id + ',\'' + item.fileExtension + '\');">' +
                                '<input type="button" class="btn btn-xs btn-primary" value="查看" onclick="FileUtils.showAttachment(' + item.id + ',\'' + item.fileExtension + '\');">' +
                                '<input type="button" class="btn btn-xs btn-warning" value="移除" onclick="removeOwnershipCertFile(' + item.id + ',this)"></td></tr>';
                        })
                        tbody.empty().append(html);
                    }
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    }

    function uploadOwnershipCertFile(tableName, tableId, target, tbody, declareRecordId) {
        FileUtils.uploadFiles({
            target: target == undefined ? fieldsName : target,
            disabledTarget: "btn_submit",
            formData: {
                tableName: tableName,
                tableId: tableId,
                projectId: "${projectInfo.id}"
            },
            onUploadComplete: function () {
                getOwnershipCertFileAll(tbody, declareRecordId);
            },
            editFlag: true,
            deleteFlag: true
        });
    }

    //加载复印件
    function getOwnershipCertFileAll(tbody, declareRecordId) {
        $.ajax({
            url: '${pageContext.request.contextPath}/projectReportFile/getOwnershipCertFileAll',
            data: {
                declareRecordId: declareRecordId
            },
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        var html = '';
                        $.each(result.data, function (i, item) {
                            ++i;
                            html += '<tr><th scope="row">' + i + '</th><td>' + item.fileName + '</td><td>' +
                                '<input type="button" class="btn btn-xs btn-primary" value="编辑" onclick="FileUtils.editAttachment(' + item.id + ',\'' + item.fileExtension + '\');">' +
                                '<input type="button" class="btn btn-xs btn-primary" value="查看" onclick="FileUtils.showAttachment(' + item.id + ',\'' + item.fileExtension + '\');">' +
                                '<input type="button" class="btn btn-xs btn-warning" value="移除" onclick="removeOwnershipCertFile(' + item.id + ',this)"></td></tr>';
                        })
                        tbody.empty().append(html);
                    }
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    }

    //移除复印件片
    function removeOwnershipCertFile(id, _this) {
        Alert("确认删除", 2, null, function () {
            $.ajax({
                url: '${pageContext.request.contextPath}/projectReportFile/removeOwnershipCertFile',
                data: {
                    id: id
                },
                success: function (result) {
                    if (result.ret) {
                        $(_this).closest('tr').remove();
                    } else {
                        Alert(result.errmsg);
                    }
                }
            })
        });
    }

    //加载自定义附件
    function loadReportFileCustomList(declareRecordId) {
        $.ajax({
            url: '${pageContext.request.contextPath}/scheme/getReportFileCustomList',
            data: {
                declareRecordId: declareRecordId
            },
            success: function (result) {
                if (result.ret) {
                    $('.report-file-custom').empty();
                    $.each(result.data, function (i, item) {
                        appendCustomHtml(item.id, item.name, declareRecordId);
                    })
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    }

    //添加html
    function appendCustomHtml(id, name, declareRecordId) {
        var html = $("#reportFileCustomHtml").html();
        html = html.replace(/{id}/g, id).replace(/{name}/g, name);
        $('.report-file-custom' + declareRecordId).append(html);
        uploadFiles(AssessDBKey.SchemeReportFileCustom, id, "reportFileCustom" + id);
        loadUploadFiles(AssessDBKey.SchemeReportFileCustom, id, "reportFileCustom" + id);
    }

    //添加自定义块
    function addReportFileCustom(declareRecordId) {
        layer.prompt(function (value, index, elem) {
            $.ajax({
                url: '${pageContext.request.contextPath}/scheme/addReportFileCustom',
                data: {
                    name: value,
                    declareRecordId: declareRecordId
                },
                success: function (result) {
                    if (result.ret) {
                        appendCustomHtml(result.data.id, result.data.name, declareRecordId);
                        layer.close(index);
                    } else {
                        Alert(result.errmsg);
                    }
                }
            })
        });
    }

    //删除自定义块
    function deleteReportFileCustom(_this, id) {
        $.ajax({
            url: '${pageContext.request.contextPath}/scheme/deleteReportFileCustom',
            data: {
                id: id
            },
            success: function (result) {
                if (result.ret) {
                    $(_this).closest('.x_panel').parent().remove();
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    }


    //提交
    function submit() {
        var data = {};
        data.reportFileItemList = [];
        $('[data-name=sorting]').each(function () {
            var reportFileItem = {};
            reportFileItem.id = $(this).attr('data-id');
            reportFileItem.sorting = $(this).val();
            data.reportFileItemList.push(reportFileItem);
        })
        $.ajax({
            url: '${pageContext.request.contextPath}/projectReportFile/saveReprotFile',
            data: {formData: JSON.stringify(data)},
            success: function (result) {
                if (result.ret) {
                    Alert("保存成功", 1, null, function () {
                        window.close();
                    })
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    }

    //加载实况照片
    function loadLiveSituation(tbody, declareRecordId) {
        $.ajax({
            url: '${pageContext.request.contextPath}/scheme/getListByDeclareRecordId',
            data: {
                declareRecordId: declareRecordId
            },
            success: function (result) {
                if (result.ret) {
                    var html = '';
                    $.each(result.data, function (i, item) {
                        html += '<tr><td><input type="text" name="fileName" value="' + item.fileName + '"  onblur="reportFileEditName(' + item.id + ',this);"></td>' +
                            '<td><input type="text" name="sorting"  value="' + AssessCommon.toString(item.sorting) + '" onblur="reportFileEditName(' + item.id + ',this,' + declareRecordId + ');" ></td>' +
                            '<td>' + item.fileViewName + '</td>' + '<td>' + item.certifyPartName + '</td>' + '<td>' + item.certifyPartCategoryName + '</td>' + '<td>' + item.bisEnableName + '</td><td>' +
                            '<input type="button" class="btn btn-xs btn-primary" value="上传照片" onclick="addPicture(' + item.id + ');">' +
                            '<input type="button" class="btn btn-xs btn-primary" value="编辑" onclick="getAndInit(' + item.id + ');">' +
                            '<input type="button" class="btn btn-xs btn-warning" value="移除" onclick="removeLiveSituation(' + item.id + ',this)"></td></tr>';
                    })
                    tbody.empty().append(html);
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    }

    //移除实况照片
    function removeLiveSituation(id, _this) {
        Alert("确认删除", 2, null, function () {
            $.ajax({
                url: '${pageContext.request.contextPath}/scheme/removeLiveSituation',
                data: {
                    id: id
                },
                success: function (result) {
                    if (result.ret) {
                        $(_this).closest('tr').remove();
                    } else {
                        Alert(result.errmsg);
                    }
                }
            })
        });
    }

    function addLiveSituationFile(declareRecordId) {
        $("#frmItemFile").clearAll();
        $("#frmItemFile").find("input[name='declareRecordId']").val(declareRecordId);
        uploadFiles(AssessDBKey.DeclareRecord, 0, "live_situation_select_supplement", "uploadSupplementFile");
        loadUploadFiles(AssessDBKey.DeclareRecord, 0, "live_situation_select_supplement", "uploadSupplementFile");
        AssessCommon.loadDataDicByKey(AssessDicKey.certifyPart, '', function (html, data) {
            $("#frmItemFile").find("select[name='certifyPart']").empty().html(html).trigger('change');
        });
        //绑定变更事件
        $("#frmItemFile").find("select[name='certifyPart']").on('change', function () {
            getCategory($(this).val(), false);
        });
        $("#addItemModal").modal("show");

    }

    //加载查勘所有实况照片
    function getLiveSituationAll(declareRecordId) {
        $.ajax({
            url: '${pageContext.request.contextPath}/scheme/getLiveSituationAll',
            data: {
                declareRecordId: declareRecordId
            },
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        var html = '';
                        $.each(result.data, function (i, item) {
                            ++i;
                            html += '<tr><th scope="row">' + i + '</th><td>' + item.reName + '</td><td>' + item.fileName + '</td><td>' +
                                '<input type="button" class="btn btn-xs btn-primary" value="查看" onclick="FileUtils.showAttachment(' + item.id + ',\'' + item.fileExtension + '\');">' +
                                '<input type="button" class="btn btn-xs btn-primary" value="选择" onclick="selectLiveSituation(' + item.id + ',' + declareRecordId + ',\'' + item.reName + '\');"></td></tr>';
                        })
                        $("#allExamineFileFrm").find('tbody[data-id=all_live_situation]').empty().append(html);
                    }
                    $("#allExamineFileModal").modal("show");
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    }

    //选择查勘中照片
    function selectLiveSituation(attachmentId, declareRecordId, fileName) {
        Loading.progressShow();
        $.ajax({
            url: '${pageContext.request.contextPath}/scheme/selectLiveSituation',
            data: {
                attachmentId: attachmentId,
                declareRecordId: declareRecordId,
                fileName: fileName
            },
            success: function (result) {
                Loading.progressHide();
                Alert("选择图片成功!", 1, null, function () {
                    loadLiveSituation($('tbody[data-id=' + declareRecordId + '][data-name=live_situation_select]'), declareRecordId);
                });
            }
        })
    }

    //修改实况照片
    function getAndInit(id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/scheme/getSchemeReportFileItemById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    $("#frmItemFile").clearAll();
                    $("#frmItemFile").initForm(result.data);
                    AssessCommon.loadDataDicByKey(AssessDicKey.certifyPart, result.data.certifyPart, function (html, data) {
                        $("#frmItemFile").find("select[name='certifyPart']").empty().html(html).trigger('change');
                    });
                    //绑定变更事件
                    $("#frmItemFile").find("select[name='certifyPart']").on('change', function () {
                        getCategory($(this).val(), result.data.certifyPartCategory);
                    });
                    uploadFiles(AssessDBKey.DeclareRecord, result.data.id, "live_situation_select_supplement", "uploadSupplementFile");
                    loadUploadFiles(AssessDBKey.DeclareRecord, result.data.id, "live_situation_select_supplement", "uploadSupplementFile");
                    $('#addItemModal').modal("show");
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    //保存实况照片
    function saveItemFileData() {
        if (!$("#frmItemFile").valid()) {
            return false;
        }
        var data = formParams("frmItemFile");
        $.ajax({
            url: "${pageContext.request.contextPath}/scheme/saveToReportFileItem",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    toastr.success('保存成功');
                    $('#addItemModal').modal('hide');
                    loadLiveSituation($('tbody[data-id="' + data.declareRecordId + '"][data-name=live_situation_select]'), data.declareRecordId);
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

    //实况照片改名
    function reportFileEditName(id, _this, declareRecordId) {
        var newName = $(_this).closest("tr").find("input[name='fileName']").val();
        var newSorting = $(_this).closest("tr").find("input[name='sorting']").val();
        $.ajax({
            url: '${pageContext.request.contextPath}/scheme/reportFileEditName',
            data: {
                id: id,
                newName: newName,
                newSorting: newSorting
            },
            success: function (result) {
                if (result.ret) {
                    loadLiveSituation($('tbody[data-id=' + declareRecordId + '][data-name=live_situation_select]'), declareRecordId);
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    }

    //初始化上传控件
    function uploadFiles(tableName, tableId, fieldsName, target) {
        FileUtils.uploadFiles({
            target: target == undefined ? fieldsName : target,
            disabledTarget: "btn_submit",
            formData: {
                tableName: tableName,
                tableId: tableId,
                fieldsName: fieldsName,
                projectId: "${projectInfo.id}"
            },
            editFlag: true,
            deleteFlag: true
        });
    }

    //显示附件
    function loadUploadFiles(tableName, tableId, fieldsName, target) {
        FileUtils.getFileShows({
            target: target == undefined ? fieldsName : target,
            formData: {
                tableName: tableName,
                tableId: tableId,
                fieldsName: fieldsName
            },
            editFlag: true,
            deleteFlag: true
        })
    }

    function getCategory(pid, value) {
        if (!pid) {
            var option = "<option value=''>-先选择对应查勘部位-</option>";
            $("#frmItemFile").find("select[name='certifyPartCategory']").html(option);
            $("#frmItemFile").find("select[name='certifyPartCategory']").val(['']).trigger('change');
            return false;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/baseDataDic/getCacheDataDicListByPid",
            type: "get",
            dataType: "json",
            data: {pid: pid},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    if (data.length >= 1) {
                        var option = "<option value=''>-请选择-</option>";
                        for (var i = 0; i < data.length; i++) {
                            option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                        }
                        $("#frmItemFile").find("select[name='certifyPartCategory']").html(option);
                        if (value) {
                            $("#frmItemFile").find("select[name='certifyPartCategory']").val([value]).trigger('change');
                        } else {
                            $("#frmItemFile").find("select[name='certifyPartCategory']").val(['']).trigger('change');
                        }
                    }

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

    //选择查勘照片模板类型
    function selectPictureTempale(declareRecordId) {
        $("#frmPictureType").clearAll();
        $("#frmPictureType").find("input[name='declareRecordId']").val(declareRecordId);
        AssessCommon.loadDataDicByKey(AssessDicKey.DATA_LOCALE_SURVEY_PICTURE_TEMPLATE, '', function (html, data) {
            $("#frmPictureType").find("select[name='type']").empty().html(html).trigger('change');
        });
        $("#selectPictureTypeModal").modal("show");

    }

    //确认模板
    function affirmPictureTemplate() {
        if (!$("#frmPictureType").valid()) {
            return false;
        }
        var type = $("#frmPictureType").find("select[name='type']").val();
        var declareRecordId = $("#frmPictureType").find("input[name='declareRecordId']").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/scheme/affirmPictureTemplate",
            type: "post",
            dataType: "json",
            data: {
                type: type,
                declareRecordId: declareRecordId
            },
            success: function (result) {
                if (result.ret) {
                    toastr.success('模板添加成功');
                    $('#selectPictureTypeModal').modal('hide');
                    loadLiveSituation($('tbody[data-id="' + declareRecordId + '"][data-name=live_situation_select]'), declareRecordId);
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

    //上传实况照片
    function addPicture(id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/scheme/getSchemeReportFileItemById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    $("#frmPicture").clearAll();
                    $("#frmPicture").initForm(result.data);
                    uploadFiles(AssessDBKey.DeclareRecord, result.data.id, "live_situation_select_supplement", "uploadPicture");
                    loadUploadFiles(AssessDBKey.DeclareRecord, result.data.id, "live_situation_select_supplement", "uploadPicture");
                    $('#addPictureModal').modal("show");
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    //保存上传照片
    function savePicture() {
        var data = formParams("frmPicture");
        $.ajax({
            url: "${pageContext.request.contextPath}/scheme/saveToReportFileItem",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    toastr.success('保存成功');
                    $('#addPictureModal').modal('hide');
                    loadLiveSituation($('tbody[data-id="' + data.declareRecordId + '"][data-name=live_situation_select]'), data.declareRecordId);
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

    //加载指定位置查勘图片
    function correspondingSitePic() {
        var declareRecordId = $("#frmPicture").find("[name='declareRecordId']").val();
        var certifyPartCategory = $("#frmPicture").find("[name='certifyPartCategory']").val();
        if (certifyPartCategory) {
            $.ajax({
                url: '${pageContext.request.contextPath}/scheme/correspondingSitePic',
                data: {
                    declareRecordId: declareRecordId,
                    certifyPartCategory: certifyPartCategory
                },
                success: function (result) {
                    if (result.ret) {
                        if (result.data) {
                            var reportFileItemId = $("#frmPicture").find("[name='id']").val();

                            var html = '';
                            $.each(result.data, function (i, item) {
                                ++i;
                                html += '<tr><th scope="row">' + i + '</th><td>' + item.reName + '</td><td>' + item.fileName + '</td><td>' +
                                    '<input type="button" class="btn btn-xs btn-primary" value="查看" onclick="FileUtils.showAttachment(' + item.id + ',\'' + item.fileExtension + '\');">' +
                                    '<input type="button" class="btn btn-xs btn-primary" value="选择" onclick="selectCorrespondingSitePic(' + item.id + ',' + reportFileItemId + ');"></td></tr>';
                            })
                            $("#allExamineFileFrm").find('tbody[data-id=all_live_situation]').empty().append(html);
                        }
                        $("#allExamineFileModal").modal("show");
                    } else {
                        Alert(result.errmsg);
                    }
                }
            })
        } else {
            alert("请先选择查勘部位")
        }
    }

    //选择指定位置查勘照片
    function selectCorrespondingSitePic(attachmentId, reportFileItemId) {
        Loading.progressShow();
        $.ajax({
            url: '${pageContext.request.contextPath}/scheme/selectCorrespondingSitePic',
            data: {
                attachmentId: attachmentId,
                reportFileItemId: reportFileItemId
            },
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('选择成功');
                    $("#allExamineFileModal").modal("hide");
                    loadUploadFiles(AssessDBKey.DeclareRecord, reportFileItemId, "live_situation_select_supplement", "uploadPicture");
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    //权证列表
    function reloadDeclareList() {
        var cols = [];
        cols.push({
            field: 'area', title: '区域', formatter: function (value, row, index) {
                return AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
            }
        });
        cols.push({field: 'name', title: '名称'});
        cols.push({field: 'seat', title: '坐落'});
        cols.push({field: 'certUse', title: '证载用途'});
        $("#declareTable").bootstrapTable('destroy');
        TableInit("declareTable", "${pageContext.request.contextPath}/declareRecord/getDeclareRecordList", cols, {
            projectId: ${projectId},
            province: $("#queryProvince").val(),
            city: $("#queryCity").val(),
            district: $("#queryDistrict").val(),
            name: $("#queryName").val(),
            seat: $("#querySeat").val(),

        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onClickRow: function (row) {
                makeJudgeObjectPosition(row);
                loadLiveSituationHtml(row);
                loadOwnershipCertHtml(row);
                loadLandFileHtml(row);
                loadReportFileCustomHtml(row);
            },
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

    //位置示意图
    function makeJudgeObjectPosition(declareRecord) {
        $.ajax({
            url: "${pageContext.request.contextPath}/projectReportFile/makeJudgeObjectPosition",
            type: "post",
            dataType: "json",
            data: {
                declareRecordId:declareRecord.id
            },
            success: function (result) {
                if (result.ret) {
                    loadPositionHtml(declareRecord);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }
    //位置示意图html
    function loadPositionHtml(declareRecord) {
        var html = '';
        html += '<tr><td>' + declareRecord.name + '</td><td>' +
            '<input type="file" multiple="false"  id="judge_object_position' + declareRecord.id + '">' +
            '<div id="_judge_object_position' + declareRecord.id + '"></div></td></tr>';
        $("#positionTbody").empty().append(html);
        uploadFiles(AssessDBKey.DeclareRecord, declareRecord.id, AssessUploadKey.JUDGE_OBJECT_POSITION, "judge_object_position" + declareRecord.id);
        loadUploadFiles(AssessDBKey.DeclareRecord, declareRecord.id, AssessUploadKey.JUDGE_OBJECT_POSITION, "judge_object_position" + declareRecord.id);
    }

    //实况照片html
    function loadLiveSituationHtml(declareRecord) {
        var html = '';
        html += '<div class="row">';
        html += '<input type="hidden" name="declareRecordId" value="' + declareRecord.id + '">';
        html += '<div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10  col-sm-10 col-xs-10">';
        html += '<div class="x_panel">';
        html += '<div class="x_title"><h4>';
        html += '<strong>' + declareRecord.name + '</strong>';
        html += '<small><input type="button" value="新增照片" onclick="addLiveSituationFile(' + declareRecord.id + ')" class="btn btn-success btn-xs"></small>';
        html += '<small><input type="button" value="选择查勘中图片" onclick="getLiveSituationAll(' + declareRecord.id + ')" class="btn btn-success btn-xs"></small>';
        html += '<small><input type="button" value="新增查勘照片模板" onclick="selectPictureTempale(' + declareRecord.id + ')" class="btn btn-success btn-xs"></small></h4>';
        html += '</div><table class="table table-hover"><thead><tr><th width="10%">文件名称</th><th width="10%">排序</th><th width="20%">附件</th><th width="15%">对应查勘部位</th><th width="10%">附件类别</th><th width="10%">是否上报告</th><th width="20%">操作</th></tr></thead>';
        html += '<tbody data-id="' + declareRecord.id + '" data-name="live_situation_select"></tbody></table>';
        html += '</div></div></div>';
        $("#liveSituationHtml").empty().append(html);
        loadLiveSituation($('tbody[data-id=' + declareRecord.id + '][data-name=live_situation_select]'), declareRecord.id);
    }

    //权属证明复印件html
    function loadOwnershipCertHtml(declareRecord) {
        var html = '';
        html += '<div class="row">';
        html += '<div class="col-xs-6612  col-sm-6612  col-md-6612  col-lg-6612  col-sm-6 col-xs-12">';
        html += '<div class="x_panel">';
        html += '<div class="x_title"><h4><strong>' + declareRecord.name + '</strong></h4></div>';
        html += '<table class="table table-hover"><thead><tr><th width="20%">序号</th><th width="35%">文件名称</th><th width="45%">操作</th></tr></thead>';
        html += '<tbody data-id="' + declareRecord.id + '" data-name="ownership_cert_file_list"></tbody></table>';
        html += '<div class="x-valid">';
        html += '<label class="col-sm-1 control-label">复印件</label>';
        html += '<div class="col-sm-10">';
        html += '<input id="uploadOwnershipCertFile' + declareRecord.id + '" class="form-control" type="file">';
        html += '<div id="_uploadOwnershipCertFile' + declareRecord.id + '"></div>';
        html += '</div></div></div></div>';

        $("#ownershipCertHtml").empty().append(html);
        uploadOwnershipCertFile(declareRecord.dataTableName, declareRecord.dataTableId, "uploadOwnershipCertFile" + declareRecord.id, $('tbody[data-id=' + declareRecord.id + '][data-name=ownership_cert_file_list]'), declareRecord.id);
        getOwnershipCertFileAll($('tbody[data-id=' + declareRecord.id + '][data-name=ownership_cert_file_list]'), declareRecord.id);
    }

    //关联土地证附件html
    function loadLandFileHtml(declareRecord) {
        var html = '';
        html += '<div class="row">';
        html += '<div class="col-xs-6612  col-sm-6612  col-md-6612  col-lg-6612  col-sm-6 col-xs-12">';
        html += '<div class="x_panel">';
        html += '<div class="x_title"><h4><strong>' + declareRecord.name + '</strong></h4></div>';
        html += '<table class="table table-hover"><thead><tr><th width="20%">序号</th><th width="35%">文件名称</th><th width="45%">操作</th></tr></thead>';
        html += '<tbody data-id="' + declareRecord.id + '" data-name="land_file_list"></tbody></table>';
        html += '<div class="x-valid" data-id="' + declareRecord.id + '" data-name="land_file_btn">';
        html += '</div></div></div></div>';

        $("#landFileHtml").empty().append(html);
        uploadLandFile(AssessDBKey.DeclareRealtyLandCert, "uploadlandFile" + declareRecord.id, $('tbody[data-id=' + declareRecord.id + '][data-name=land_file_list]'), declareRecord.id);
        getLandFileAllByDeclareRecord($('tbody[data-id=' + declareRecord.id + '][data-name=land_file_list]'), declareRecord.id);
    }

    //估价中引用的专用文件资料html
    function loadReportFileCustomHtml(declareRecord) {
        if (declareRecord) {
            $.ajax({
                url: '${pageContext.request.contextPath}/projectReportFile/getAddressFileListByDeclareRecordId',
                type: "post",
                dataType: "json",
                data: {
                    declareRecordId: declareRecord.id,
                },
                success: function (result) {
                    if (result.ret) {
                        var html = '';
                        html += '<div class="row">';
                        html += '<div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10">';
                        html += '<div class="x_panel">';
                        html += '<div class="x_title"><h4><strong>' + declareRecord.name + '</strong></h4></div>';
                        if (result.data) {
                            $.each(result.data, function (i, item) {
                                ++i;
                                html += '<div class="x_panel"><div class="x_title">登记与实际地址不一致附件</div><div>';
                                html += '<table class="table table-hover"><thead><tr><th width="20%">序号</th><th width="35%">文件名称</th><th width="45%">操作</th></tr></thead><tbody>';
                                html += '<tr><th>' + i + '</th><td>' + item.fileName + '</td><td>';
                                html += '<input type="button" class="btn btn-xs btn-primary"value="编辑"onclick="FileUtils.editAttachment(' + item.id + ',\'' + item.fileExtension + '\');">';
                                html += '<input type="button" class="btn btn-xs btn-warning"value="查看"onclick="FileUtils.showAttachment(' + item.id + ',\'' + item.fileExtension + '\');">';
                                html += '</td></tr></tbody></table></div></div>';
                            })
                        }
                        html += '<input type="button" class="btn btn-success" value="自定义添加"onclick="addReportFileCustom(' + declareRecord.id + ');">';
                        html += '<div class="row report-file-custom' + declareRecord.id + '"></div></div></div></div>';
                        $("#ReportFileCustom").empty().append(html);
                        loadReportFileCustomList(declareRecord.id);
                    } else {
                        Alert(result.errmsg);
                    }
                }
            })
        }
    }
</script>

</html>

