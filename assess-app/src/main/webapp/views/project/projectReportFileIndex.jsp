<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <!-- 估价委托书 start -->
            <div class="col-md-12">
                <div class="card full-height">
                    <div class="card-header collapse-link">
                        <div class="card-head-row">
                            <div class="card-title">
                                估价委托书
                            </div>
                            <div class="card-tools">
                                <button class="btn  btn-link btn-primary btn-xs"><span
                                        class="fa fa-angle-down"></span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <form class="form-horizontal">
                            <div class="row form-group">
                                <div class="col-md-4">
                                    <input id="project_proxy" name="project_proxy" type="file" multiple="false">
                                    <div id="_project_proxy"></div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- 估价委托书及相关证明 start -->
            <div class="col-md-12">
                <div class="card full-height">
                    <div class="card-header collapse-link">
                        <div class="card-head-row">
                            <div class="card-title">
                                估价委托书及相关证明
                            </div>
                            <div class="card-tools">
                                <button class="btn  btn-link btn-primary btn-xs"><span
                                        class="fa fa-angle-down"></span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <form class="form-horizontal">
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 col-form-label">
                                            省
                                        </label>
                                        <div class="col-sm-2">
                                            <select id="queryProvince" class="form-control input-full">
                                            </select>
                                        </div>
                                        <label class="col-sm-1 control-label">
                                            市
                                        </label>
                                        <div class="col-sm-2">
                                            <select id="queryCity" class="form-control input-full">
                                            </select>
                                        </div>
                                        <label class="col-sm-1 control-label">
                                            区
                                        </label>
                                        <div class="col-sm-2">
                                            <select id="queryDistrict" class="form-control input-full">
                                            </select>
                                        </div>
                                        <label class="col-sm-1 control-label">
                                            名称
                                        </label>
                                        <div class="col-sm-2">
                                            <input type="text" class="form-control input-full" id="queryName"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 col-form-label">
                                            坐落
                                        </label>
                                        <div class="col-sm-2">
                                            <input type="text" class="form-control input-full" id="querySeat"/>
                                        </div>
                                        <button style="margin-left: 10px" class="btn btn-info btn-sm" type="button"
                                                onclick="reloadDeclareList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                    </div>
                                </div>

                            </div>
                            <table class="table table-bordered" id="declareTable">
                                <!-- cerare document add ajax data-->
                            </table>

                        </form>
                    </div>
                </div>
            </div>
            <!-- 位置示意图 start -->
            <div class="col-md-12">
                <div class="card full-height">
                    <div class="card-header collapse-link">
                        <div class="card-head-row">
                            <div class="card-title">
                                位置示意图
                            </div>
                            <div class="card-tools">
                                <button class="btn  btn-link btn-primary btn-xs"><span
                                        class="fa fa-angle-down"></span>
                                </button>
                            </div>
                        </div>
                    </div>

                    <div class="card-body">
                        <form class="form-horizontal">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th width='40%'>权证对象</th>
                                    <th width='60%'>位置图</th>
                                </tr>
                                </thead>
                                <tbody id="positionTbody">
                                </tbody>
                            </table>

                        </form>
                    </div>
                </div>
            </div>
            <!-- 实况照片 start -->
            <div class="col-md-12">
                <div class="card full-height">
                    <div class="card-header collapse-link">
                        <div class="card-head-row">
                            <div class="card-title">
                                实况照片
                            </div>
                            <div class="card-tools">
                                <button class="btn  btn-link btn-primary btn-xs"><span
                                        class="fa fa-angle-down"></span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="card-body" id="liveSituationHtml">
                    </div>
                </div>
            </div>
            <!-- 估价中引用的专用文件资料 start -->
            <div class="col-md-12">
                <div class="card full-height">
                    <div class="card-header collapse-link">
                        <div class="card-head-row">
                            <div class="card-title">
                                估价中引用的专用文件资料
                            </div>
                            <div class="card-tools">
                                <button class="btn  btn-link btn-primary btn-xs"><span
                                        class="fa fa-angle-down"></span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="card-body" id="ReportFileCustom">
                    </div>
                </div>
            </div>
            <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">
                <button type="button" id="cancel_btn btn-sm" class="btn btn-default" onclick="window.close()">
                    取消
                </button>
                <button type="button" id="btn_submit btn-sm" class="btn btn-warning" style="margin-left: 10px;" onclick="submit()">
                    保存
                </button>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>

</body>
<div id="addItemModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">添加照片</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmItemFile" class="form-horizontal">
                    <input type="hidden" name="declareRecordId">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 col-form-label">
                                                名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" class="form-control input-full" required
                                                       name="fileName" placeholder="名称">
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                排序<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" class="form-control input-full" required
                                                       name="sorting" placeholder="排序">
                                            </div>
                                            <div class="col-sm-4">
                                                <div class="form-check" style="justify-content:left">
                                                    <label class="form-check-label">
                                                        <input class="form-check-input" type="checkbox" id="bisEnable"
                                                               name="bisEnable" value="true"
                                                               checked="checked">
                                                        <span class="form-check-sign">是否上报告</span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 col-form-label">
                                                对应查勘部位<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-3">
                                                <select name="certifyPart"
                                                        class="form-control input-full search-select certifyPart select2"
                                                        required>
                                                </select>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                附件类别<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-3">
                                                <select name="certifyPartCategory"
                                                        class="form-control input-full search-select certifyPartCategory select2"
                                                        required>
                                                </select>
                                            </div>
                                            <div class="col-sm-4">
                                                <small><input type="button" value="选择查勘照片"
                                                              onclick="getLiveSituationByCertifyPart()"
                                                              class="btn btn-success btn-xs"></small>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                备注
                                            </label>
                                            <div class="col-sm-11">
                                              <textarea placeholder="备注" name="remark"
                                                        class="form-control input-full"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                附件
                                            </label>
                                            <div class="col-sm-11">
                                                <input id="uploadSupplementFile" placeholder="上传附件"
                                                       class="form-control input-full"
                                                       type="file">
                                                <div id="_uploadSupplementFile"></div>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="saveItemFileData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>
<div id="examineFileModalByCertifyPart" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">查勘照片</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="examineFileFrmByCertifyPart" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>文件名称</th>
                                        <th>附件</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody data-id="certifyPart_file">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>

        </div>
    </div>
</div>
<div id="selectPictureTypeModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">模板类型</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmPictureType" class="form-horizontal">
                    <input type="hidden" name="declareRecordId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                类型<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select id='type' name='type'
                                                        class='form-control input-full search-select select2'
                                                        onchange="typeOnchange(this)">
                                                    <option value="-1">-请选择-</option>
                                                    <option value="0">系统</option>
                                                    <option value="1">个人</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <table class="table table-bordered" id="dataLocaleSurveyList">
                                    <!-- cerare document add ajax data-->
                                </table>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>

        </div>
    </div>
</div>
<div id="addPictureModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">添加照片</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmPicture" class="form-horizontal">
                    <input type="hidden" name="declareRecordId">
                    <input type="hidden" name="certifyPartCategory">
                    <input type="hidden" name="id">
                    <input type="hidden" name="bisEnable">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <input type="button" class="btn btn-primary btn-xs"
                                       onclick="correspondingSitePic()"
                                       value="选择对应位置查勘图片">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                附件
                                            </label>
                                            <div class="col-sm-11">
                                                <input id="uploadPicture" placeholder="上传附件"
                                                       class="form-control input-full"
                                                       type="file">
                                                <div id="_uploadPicture"></div>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="savePicture()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>
<div id="divBoxTemplateDetail" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">明细</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmMaster" class="form-horizontal">
                    <input type="hidden" id="masterId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <P>
                                    <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                            data-toggle="modal" onclick="dataLocaleSurveyPicture.prototype.showModel()">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                        新增
                                    </button>
                                </P>
                                <table class="table table-bordered" id="dataLocaleSurveyPictureList">
                                    <!-- cerare document add ajax data-->
                                </table>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>

        </div>
    </div>
</div>
<div id="divBoxSaveTemplate" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">保存到模板</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmTemplateMaster" class="form-horizontal">
                    <input type="hidden" name="declareRecordId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-4">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" data-rule-maxlength="50"
                                                       placeholder="名称" id="name" class="form-control input-full">
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
                <button type="button" class="btn btn-primary btn-sm" onclick="saveToTemplate()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>
<div id="allExamineFileModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">查勘照片</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="allExamineFileFrm" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
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
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>

        </div>
    </div>
</div>

<%@include file="/views/data/dataLocaleSurveyPictureView.jsp" %>
<script type="text/html" id="reportFileCustomHtml">
    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
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
            useDefaultText: false,
            provinceTarget: $("#queryProvince"),
            cityTarget: $("#queryCity"),
            districtTarget: $("#queryDistrict")
        });

        reloadDeclareList();
        uploadFiles(AssessDBKey.ProjectInfo, "${projectInfo.id}", AssessUploadKey.PROJECT_PROXY);
        loadUploadFiles(AssessDBKey.ProjectInfo, "${projectInfo.id}", AssessUploadKey.PROJECT_PROXY);
    });

    function typeOnchange(_this) {
        var type = $(_this).val();
        if (type) {
            loadLocalSurveyTemplateList(type);
        }
    }

    function loadLocalSurveyTemplateList(type) {
        var cols = [];
        cols.push({field: 'name', title: '名称'});
        cols.push({field: 'typeName', title: '类型'});
        cols.push({field: 'sorting', title: '排序'});
        cols.push({field: 'remark', title: '备注'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                str += '<button type="button" class="btn btn-xs btn-info tooltips" style="margin-left: 5px;" data-placement="top" data-original-title="明细" onclick="showTemplateModel(' + row.id + ')"><i class="fa fa-search fa-white"></i></button>';
                str += '<button type="button" class="btn btn-xs btn-primary tooltips" style="margin-left: 5px;" data-placement="top" data-original-title="选择" onclick="affirmPictureTemplate(' + row.id + ')"><i class="fa fa-check fa-white"></i></button>';
                str += '</div>';
                return str;
            }
        });
        $("#dataLocaleSurveyList").bootstrapTable('destroy');
        TableInit("dataLocaleSurveyList", "${pageContext.request.contextPath}/dataLocaleSurvey/getDataLocaleSurveyListBySurvey", cols, {
            type: type,
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }

    function showTemplateModel(id) {
        loadTemplateDetailList(id);
        $('#divBoxTemplateDetail').modal("show");
    }

    function loadTemplateDetailList(id) {
        var cols = [];
        cols.push({field: 'fileName', title: '名称'});
        cols.push({field: 'certifyPartName', title: '对应查勘部位'});
        cols.push({field: 'certifyPartCategoryName', title: '附件类别'});
        cols.push({field: 'sorting', title: '排序'});
        cols.push({field: 'remark', title: '备注'});
        $("#dataLocaleSurveyPictureList").bootstrapTable('destroy');
        TableInit("dataLocaleSurveyPictureList", "${pageContext.request.contextPath}/dataLocaleSurveyPicture/getDataLocaleSurveyPictureList", cols, {
            masterId: id
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }

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
                    AlertError("失败","调用服务端方法失败，失败原因:" + result);
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
                        html += '<input id="uploadlandFile' + declareRecordId + '" class="form-control input-full"type="file">';
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
                    AlertError("失败","调用服务端方法失败，失败原因:" + result);
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
                    AlertError("失败","加载失败，失败原因:" + result);
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
                    AlertError("失败","调用服务端方法失败，失败原因:" + result);
                }
            }
        })
    }

    //移除复印件片
    function removeOwnershipCertFile(id, _this) {
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            $.ajax({
                url: '${pageContext.request.contextPath}/projectReportFile/removeOwnershipCertFile',
                data: {
                    id: id
                },
                success: function (result) {
                    if (result.ret) {
                        $(_this).closest('tr').remove();
                    } else {
                        AlertError("失败","删除数据失败，失败原因:" + result.errmsg);
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
                    AlertError("失败","调用服务端方法失败，失败原因:" + result);
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
                        AlertError("失败","调用服务端方法失败，失败原因:" + result);
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
                    AlertError("失败","调用服务端方法失败，失败原因:" + result);
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
                    AlertSuccess("成功", "保存成功", function () {
                        window.close();
                    });
                } else {
                    AlertError("失败","保存失败，失败原因:" + result);
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
                            '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-info" data-original-title="上传照片" onclick="addPicture(' + item.id + ');"><i class="fa fa-cloud-upload-alt"></i></button>' +
                            '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary" data-original-title="编辑" onclick="getAndInit(' + item.id + ');"><i class="fa fa-pen"></i></button>' +
                            '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning" data-original-title="移除" onclick="removeLiveSituation(' + item.id + ',this)"><i class="fa fa-minus"></i></button></td></tr>';
                    })
                    tbody.empty().append(html);
                } else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result);
                }
            }
        })
    }

    //移除实况照片
    function removeLiveSituation(id, _this) {
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            $.ajax({
                url: '${pageContext.request.contextPath}/scheme/removeLiveSituation',
                data: {
                    id: id
                },
                success: function (result) {
                    if (result.ret) {
                        $(_this).closest('tr').remove();
                    } else {
                        AlertError("失败","删除数据失败，失败原因:" + result.errmsg);
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
                                '<button type="button" class="btn btn-xs btn-info" style="margin-left: 5px;" data-original-title="查看" onclick="FileUtils.showAttachment(' + item.id + ',\'' + item.fileExtension + '\');"><i class="fa fa-search"></i></button>' +
                                '<button type="button" class="btn btn-xs btn-primary" style="margin-left: 5px;" data-original-title="选择" onclick="selectLiveSituation(' + item.id + ',' + declareRecordId + ',\'' + item.reName + '\');"><i class="fa fa-check"></i></button></td></tr>';
                        })
                        $("#allExamineFileFrm").find('tbody[data-id=all_live_situation]').empty().append(html);
                    }
                    $("#allExamineFileModal").modal("show");
                } else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result);
                }
            }
        })
    }

    //预览实况照片图片
    function generateLiveSituation(declareRecordId) {
        Loading.progressShow();
        $.ajax({
            url: '${pageContext.request.contextPath}/scheme/generateLiveSituation',
            data: {
                declareRecordId: declareRecordId
            },
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    if (result.data) {
                        FileUtils.showAttachment(result.data.id, result.data.fileExtension);
                    }

                } else {
                    AlertError("失败","失败原因:" + result);
                }
            }
        })
    }

    //根据类型获取委估对象下实况图片
    function getLiveSituationByCertifyPart() {
        var declareRecordId = $("#frmItemFile").find("input[name='declareRecordId']").val();
        var certifyPart = $("#frmItemFile").find("select[name='certifyPart']").val();
        if (!certifyPart) {
            notifyInfo("提示","先选择查勘部位");
            return;
        }
        $.ajax({
            url: '${pageContext.request.contextPath}/scheme/getLiveSituationByCertifyPart',
            data: {
                certifyPart: certifyPart,
                declareRecordId: declareRecordId
            },
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        var html = '';
                        $.each(result.data, function (i, item) {
                            ++i;
                            html += '<tr><th scope="row">' + i + '</th><td>' + item.reName + '</td><td>' + item.fileName + '</td><td>' +
                                '<button type="button" class="btn btn-xs btn-info" style="margin-left: 5px;" data-original-title="查看" onclick="FileUtils.showAttachment(' + item.id + ',\'' + item.fileExtension + '\');"><i class="fa fa-search"></i></button>' +
                                '<button type="button" class="btn btn-xs btn-primary" style="margin-left: 5px;" data-original-title="选择" onclick="selectLiveSituationByCertifyPart(' + item.id + ');"><i class="fa fa-check"></i></button></td></tr>';
                        })
                        $("#examineFileFrmByCertifyPart").find('tbody[data-id=certifyPart_file]').empty().append(html);
                    }
                    $("#examineFileModalByCertifyPart").modal("show");
                } else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
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
                AlertSuccess("成功", "选择图片成功", function () {
                    loadLiveSituation($('tbody[data-id=' + declareRecordId + '][data-name=live_situation_select]'), declareRecordId);

                });
            }
        })
    }

    //选择查勘部位照片
    function selectLiveSituationByCertifyPart(attachmentId) {
        var reportFileItemId = $("#frmItemFile").find("[name='id']").val();
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
                    notifySuccess("成功", "选择成功");
                    $("#examineFileModalByCertifyPart").modal("hide");
                    loadUploadFiles(AssessDBKey.DeclareRecord, reportFileItemId, "live_situation_select_supplement", "uploadSupplementFile");
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result);
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
                AlertError("失败","调用服务端方法失败，失败原因:" + result);
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
                    notifySuccess("成功", "保存成功");
                    $('#addItemModal').modal('hide');
                    loadLiveSituation($('tbody[data-id="' + data.declareRecordId + '"][data-name=live_situation_select]'), data.declareRecordId);
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
                    AlertError("失败","调用服务端方法失败，失败原因:" + result);
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
            url: "${pageContext.request.contextPath}/baseDataDic/getBestLowDicListByPid",
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
                    AlertError("失败","失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result);
            }
        })

    };

    //选择查勘照片模板类型
    function selectPictureTempale(declareRecordId) {
        $("#frmPictureType").clearAll();
        $("#frmPictureType").find("input[name='declareRecordId']").val(declareRecordId);

        $("#selectPictureTypeModal").modal("show");

    }

    //确认模板
    function affirmPictureTemplate(masterId) {
        if (!$("#frmPictureType").valid()) {
            return false;
        }
        var declareRecordId = $("#frmPictureType").find("input[name='declareRecordId']").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/scheme/affirmPictureTemplate",
            type: "post",
            dataType: "json",
            data: {
                masterId: masterId,
                declareRecordId: declareRecordId
            },
            success: function (result) {
                if (result.ret) {
                    notifySuccess("成功", "模板添加成功");
                    $('#selectPictureTypeModal').modal('hide');
                    loadLiveSituation($('tbody[data-id="' + declareRecordId + '"][data-name=live_situation_select]'), declareRecordId);
                }
                else {
                    AlertError("失败","模板添加失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result);
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
                AlertError("失败","调用服务端方法失败，失败原因:" + result);
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
                    notifySuccess("成功", "保存成功");
                    $('#addPictureModal').modal('hide');
                    loadLiveSituation($('tbody[data-id="' + data.declareRecordId + '"][data-name=live_situation_select]'), data.declareRecordId);
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
                                    '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-info" data-original-title="查看" onclick="FileUtils.showAttachment(' + item.id + ',\'' + item.fileExtension + '\');"><i class="fa fa-search"></i></button>' +
                                    '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary" data-original-title="选择" onclick="selectCorrespondingSitePic(' + item.id + ',' + reportFileItemId + ');"><i class="fa fa-check"></i></button></td></tr>';
                            })
                            $("#allExamineFileFrm").find('tbody[data-id=all_live_situation]').empty().append(html);
                        }
                        $("#allExamineFileModal").modal("show");
                    } else {
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                }
            })
        } else {
            notifyInfo("提示","请先选择查勘部位")
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
                    notifySuccess("成功", "选择成功");
                    $("#allExamineFileModal").modal("hide");
                    loadUploadFiles(AssessDBKey.DeclareRecord, reportFileItemId, "live_situation_select_supplement", "uploadPicture");
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result);
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
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = "";
                str += "<button type='button'  style='margin-left: 5px;' data-placement='top' data-original-title='选择' class='btn btn-xs btn-primary tooltips' ><i class='fa fa-check'></i></button>";
                return str;
            }
        });
        $("#declareTable").bootstrapTable('destroy');
        TableInit("declareTable", "${pageContext.request.contextPath}/declareRecord/getDeclareRecordList", cols, {
            projectId: ${projectId},
            province: $("#queryProvince").val(),
            city: $("#queryCity").val(),
            district: $("#queryDistrict").val(),
            name: $("#queryName").val(),
            seat: $("#querySeat").val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onClickRow: function (row) {
                loadCertAllFile(row);
            },
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

    function loadCertAllFile(declareRecord) {
        loadPositionHtml(declareRecord);
        loadLiveSituationHtml(declareRecord);
        loadOwnershipCertHtml(declareRecord);
        loadLandFileHtml(declareRecord);
        loadReportFileCustomHtml(declareRecord);
    }

    //位置示意图
    function makeJudgeObjectPosition(declareRecordId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/projectReportFile/makeJudgeObjectPosition",
            type: "post",
            dataType: "json",
            data: {
                declareRecordId: declareRecordId
            },
            success: function (result) {
                if (result.ret) {
                    uploadFiles(AssessDBKey.DeclareRecord, declareRecordId, AssessUploadKey.JUDGE_OBJECT_POSITION, "judge_object_position" + declareRecordId);
                    loadUploadFiles(AssessDBKey.DeclareRecord, declareRecordId, AssessUploadKey.JUDGE_OBJECT_POSITION, "judge_object_position" + declareRecordId);

                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    //位置示意图html
    function loadPositionHtml(declareRecord) {
        var html = '';
        html += '<tr><td>' + declareRecord.name;
        html += '<small style="margin-left: 5px;"><button type="button" value="自动生成" onclick="makeJudgeObjectPosition(' + declareRecord.id + ')" class="btn btn-success btn-xs">自动生成</button></small>';
        html += "</td><td>";
        html += '<input type="file" multiple="false"  id="judge_object_position' + declareRecord.id + '">';
        html +=  '<div id="_judge_object_position' + declareRecord.id + '"></div></td></tr>';
        $("#positionTbody").empty().append(html);
        uploadFiles(AssessDBKey.DeclareRecord, declareRecord.id, AssessUploadKey.JUDGE_OBJECT_POSITION, "judge_object_position" + declareRecord.id);
        loadUploadFiles(AssessDBKey.DeclareRecord, declareRecord.id, AssessUploadKey.JUDGE_OBJECT_POSITION, "judge_object_position" + declareRecord.id);
    }

    //实况照片html
    function loadLiveSituationHtml(declareRecord) {
        var html = '';
        html += '<div class="row">';
        html += '<input type="hidden" name="declareRecordId" value="' + declareRecord.id + '">';
        html += '<div class=" col-sm-12">';
        html += '<div class="x_panel">';
        html += '<div class="x_title"><h4>';
        html += '<strong>' + declareRecord.name + '</strong>';
        html += '<small style="margin-left: 5px;"><button type="button" value="新增照片" onclick="addLiveSituationFile(' + declareRecord.id + ')" class="btn btn-success btn-xs">新增照片</button></small>';
        html += '<small style="margin-left: 5px;"><button type="button" value="选择查勘中图片" onclick="getLiveSituationAll(' + declareRecord.id + ')" class="btn btn-primary btn-xs">选择查勘中图片</button></small>';
        html += '<small style="margin-left: 5px;"><button type="button" value="选择模板" onclick="selectPictureTempale(' + declareRecord.id + ')" class="btn btn-primary btn-xs">选择模板</button></small>';
        html += '<small style="margin-left: 5px;"><button type="button" value="保存到模板" onclick="saveToTemplateModal(' + declareRecord.id + ')" class="btn btn-primary btn-xs">保存到模板</button></small>';
        html += '<small style="margin-left: 5px;"><button type="button" value="预览实况图片" onclick="generateLiveSituation(' + declareRecord.id + ')" class="btn btn-primary btn-xs">预览实况图片</button></small></h4>';
        html += '</div><table class="table table-hover"><thead><tr><th width="10%">文件名称</th><th width="10%">排序</th><th width="20%">附件</th><th width="15%">对应查勘部位</th><th width="10%">附件类别</th><th width="10%">是否上报告</th><th width="20%">操作</th></tr></thead>';
        html += '<tbody data-id="' + declareRecord.id + '" data-name="live_situation_select"></tbody></table>';
        html += '</div></div></div>';
        $("#liveSituationHtml").empty().append(html);
        loadLiveSituation($('tbody[data-id=' + declareRecord.id + '][data-name=live_situation_select]'), declareRecord.id);
    }

    function saveToTemplateModal(declareRecordId) {
        $('#divBoxSaveTemplate').modal("show");
        $("#frmTemplateMaster").find("input[name='declareRecordId']").val(declareRecordId);
    }

    //保存到模板
    function saveToTemplate() {
        if ($("#frmTemplateMaster").valid()) {
            var name = $("#frmTemplateMaster").find("#name").val();
            var declareRecordId = $("#frmTemplateMaster").find("input[name='declareRecordId']").val();
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/scheme/saveToTemplate",
                type: "post",
                dataType: "json",
                data: {
                    name: name,
                    declareRecordId: declareRecordId
                },
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        notifySuccess("成功", "保存成功");
                        $('#divBoxSaveTemplate').modal('hide');
                    }
                    else {
                        AlertError("失败","保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    AlertError("失败","调用服务端方法失败，失败原因:" + result);
                }
            })
        }
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
        html += '<input id="uploadOwnershipCertFile' + declareRecord.id + '" class="form-control input-full" type="file">';
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
                        html += '<div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12">';
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
                        html += '<input type="button" class="btn btn-success btn-sm" value="自定义添加"onclick="addReportFileCustom(' + declareRecord.id + ');">';
                        html += '<div class="row report-file-custom' + declareRecord.id + '"></div></div></div></div>';
                        $("#ReportFileCustom").empty().append(html);
                        loadReportFileCustomList(declareRecord.id);
                    } else {
                        AlertError("失败","调用服务端方法失败，失败原因:" + result);
                    }
                }
            })
        }
    }
</script>

</html>

