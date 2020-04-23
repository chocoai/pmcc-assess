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
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">

                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        基准地价
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frmQuery" class="form-horizontal">
                                    <input type="hidden" name="readOnly" value="${readOnly}">
                                    <input type="hidden" name="status" value="draft">
                                    <input type="hidden" name="processInsId" value="${processInsId}">
                                    <input type="hidden" name="creator" value="${sysUserDto.userAccount}">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">省</label>
                                                <div class="col-sm-2">
                                                    <select name="province"
                                                            class="form-control input-full search-select select2">
                                                    </select>
                                                </div>
                                                <label class="col-sm-1 control-label">市</label>
                                                <div class="col-sm-2">
                                                    <select name="city"
                                                            class="form-control input-full search-select select2">
                                                    </select>
                                                </div>
                                                <label class="col-sm-1 control-label">区县</label>
                                                <div class="col-sm-2">
                                                    <select name="district"
                                                            class="form-control input-full search-select select2">
                                                    </select>
                                                </div>
                                                <label class="col-sm-1 control-label">乡镇街道</label>
                                                <div class="col-sm-2">
                                                    <input placeholder="乡镇名称" class="form-control input-full"
                                                           name="townShipName"
                                                           type="text">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">权利类型</label>
                                                <div class="col-sm-2">
                                                    <select name="landRightType"
                                                            class="form-control input-full search-select select2">
                                                    </select>
                                                </div>

                                                <button type="button" class="btn btn-info btn-sm" style="margin-left: 10px"
                                                        onclick="findQuery();">
                                                    <span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                                    查询
                                                </button>
                                                <button type="button" class="btn btn-success btn-sm" style="margin-left: 5px"
                                                        onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftpLandLevelAreaBaseTemplate);">
                                                     <span class="btn-label">
												<i class="fa fa-cloud-download-alt"></i>
											</span>下载模板
                                                </button>
                                                <button type="button" class="btn btn-info btn-sm" style="margin-left: 5px"
                                                        onclick="$('#ajaxFileUploadDataLand').val('').trigger('click');">
                                                    <span class="btn-label">
												<i class="fa fa-cloud-upload-alt"></i>
											</span>上传
                                                </button>
                                                <button type="button" class="btn btn-success btn-sm" style="margin-left: 5px"
                                                        onclick="landLevel.initDataForm({})"
                                                        data-toggle="modal" href="#divBoxFather">
                                                        <span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>新增
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class=" col-sm-12">
                                            <table class="table table-bordered" id="tb_FatherList">
                                            </table>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class=" col-sm-12">
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
                                        </div>
                                    </div>

                                </form>

                            </div>
                        </div>
                    </div>


                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">

                        <div class="card-body">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">取消</button>
                            <button id="commit_btn" class="btn btn-primary" style="margin-left: 10px;" onclick="submit();">提交</button>
                        </div>
                    </div>
                    <c:if test="${processInsId ne '0'}">
                        <%@include file="/views/share/form_log.jsp" %>
                        <form id="frm_approval">
                            <%@include file="/views/share/ApprovalVariable.jsp" %>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>


<%@include file="/views/data/landModelDir/landModel.jsp" %>
<input type="file" id="ajaxFileUpload" name="file" style="display: none;">
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/views/data/landModelDir/landLevel.js?v=${assessVersion}"></script>
<script type="text/javascript">
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

    function findQuery() {
        landLevel.loadLandLevelList(formSerializeArray(landLevel.config.frmQuery));
    }
</script>


<script type="text/javascript">
    function submit() {
        var result = landLevel.config.table.bootstrapTable('getData');
        if (result.length == 0) {
            notifySuccess('成功','至少添加一条数据');
            return false;
        }
        var data = [];
        $.each(result, function (i, item) {
            data.push(item.id);
        });
        $.ajax({
            url: "${pageContext.request.contextPath}/dataLandLevel/submitDataLandLevel",
            type: "post",
            dataType: "json",
            data: {id: data.join(",")},
            success: function (result) {
                if (result.ret) {
                    //保存完后其他动作
                    AlertSuccess("成功", "提交数据成功", function () {
                        window.open('javascript:window.open("", "_self", "");window.close();', '_self');

                    });
                }
                else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }


</script>
</body>
</html>

