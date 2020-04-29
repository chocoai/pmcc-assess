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

                            <input type="file" id="ajaxFileUploadDataLand" name="file"
                                   style="display: none;" onchange="landLevel.importDataLand();">
                            <input type="file" id="ajaxFileUploadLandLevelDetail" name="file"
                                   style="display: none;"
                                   onchange="landLevel.importLandLevelDetail(false);">
                            <input type="file" id="ajaxFileUploadLandLevelDetailAchievement"
                                   name="file"
                                   style="display: none;"
                                   onchange="landLevel.importDataLandDetailAchievement(false);">
                            <input type="file"
                                   id="ajaxFileUploadLandLevelDetailCoefficientVolumeRatio"
                                   name="file" style="display: none;"
                                   onchange="landLevel.importDataAllocationCorrectionCoefficientVolumeRatio(false);">

                            <div class="card-body">
                                <form id="frmQuery" class="form-horizontal">
                                    <input type="hidden" name="readOnly" value="${readOnly}">
                                    <input type="hidden" name="permission" value="${permission}">
                                    <div class="form-group form-inline">
                                        <label class="col-md-1 col-form-label">省</label>
                                        <div class="col-md-2 p-0">
                                            <select name="province"
                                                    class="form-control input-full search-select select2">
                                            </select>
                                        </div>
                                        <label class="col-md-1 col-form-label">市</label>
                                        <div class="col-md-2 p-0">
                                            <select name="city" class="form-control input-full search-select select2">
                                            </select>
                                        </div>
                                        <label class="col-md-1 col-form-label">区县</label>
                                        <div class="col-md-2 p-0">
                                            <select name="district"
                                                    class="form-control input-full search-select select2">
                                            </select>
                                        </div>
                                        <label class="col-md-1 col-form-label">乡镇街道</label>
                                        <div class="col-md-2 p-0">
                                            <input placeholder="乡镇名称" class="form-control input-full" name="townShipName"
                                                   type="text">
                                        </div>
                                    </div>
                                    <div class="form-group form-inline">
                                        <label class="col-md-1 col-form-label">权利类型</label>
                                        <div class="col-md-2 p-0">
                                            <select name="landRightType" class="form-control input-full search-select select2">
                                            </select>
                                        </div>
                                        <label class="col-md-1 col-form-label">创建人</label>
                                        <div class="col-md-2 p-0">
                                            <input placeholder="创建人" class="form-control input-full" name="creator" type="text">
                                        </div>

                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="findQuery()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                        <button type="button" style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                                data-toggle="modal" onclick="comeInLandLevelIndex()">
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

<%@include file="/views/data/landModelDir/landModel.jsp" %>
<input type="file" id="ajaxFileUpload" name="file" style="display: none;">
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/views/data/landModelDir/landLevel.js?v=${assessVersion}"></script>
<script type="text/javascript">
    function findQuery() {
        landLevel.loadLandLevelList({status: '', creator: null, processInsId: null, checkbox: true});
    }

    function comeInLandLevelIndex() {
        var result = landLevel.config.table.bootstrapTable('getSelections');
        var data = [];
        if (result.length > 0) {
            $.each(result, function (i, item) {
                data.push(item.id);
            });
        }
        var href = "${pageContext.request.contextPath}/dataLandLevel/comeInLandLevelIndex";
        if (data.length != 0) {
            href += "?id=" + data.join(",");

            AlertSuccess("成功", "提交成功",function(){
                landLevel.config.table.bootstrapTable('uncheckAll');
            }, function () {
                window.open(href);
            });
        } else {
            window.open(href);
        }
    }

    $(document).ready(function () {
        findQuery();
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
</html>
