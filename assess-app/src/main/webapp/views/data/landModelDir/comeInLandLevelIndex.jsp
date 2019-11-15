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
            <%@include file="/views/share/form_head.jsp" %>
            <div class="x_panel">
                <div class="x_title">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h2>
                        <small>基准地价</small>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <form id="frmQuery" class="form-horizontal">
                                <input type="hidden" name="readOnly" value="${readOnly}">
                                <input type="hidden" name="status" value="draft">
                                <input type="hidden" name="processInsId" value="${processInsId}">
                                <input type="hidden" name="creator"  value="${sysUserDto.userAccount}">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">省</label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <select name="province"
                                                    class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">市</label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <select name="city" class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">区县</label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <select name="district"
                                                    class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">乡镇街道</label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <input placeholder="乡镇名称" class="form-control" name="townShipName"
                                                   type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">权利类型</label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <select name="landRightType" class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                    <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                        <button type="button" class="btn btn-primary"
                                                onclick="findQuery();">
                                            查询
                                        </button>
                                        <a class="btn btn-primary" onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftpLandLevelAreaBaseTemplate);">下载模板</a>
                                        <a class="btn btn-primary" onclick="$('#ajaxFileUploadDataLand').val('').trigger('click');">上传</a>
                                        <button type="button" class="btn btn-success"
                                                onclick="landLevel.initDataForm({})"
                                                data-toggle="modal" href="#divBoxFather"> 新增
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <table class="table table-bordered" id="tb_FatherList">
                            </table>
                        </div>
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <input type="file" id="ajaxFileUploadDataLand" name="file" style="display: none;" onchange="landLevel.importDataLand();">
                            <input type="file" id="ajaxFileUploadLandLevelDetail" name="file" style="display: none;" onchange="landLevel.importLandLevelDetail(false);">
                            <input type="file" id="ajaxFileUploadLandLevelDetailAchievement" name="file" style="display: none;" onchange="landLevel.importDataLandDetailAchievement(false);">
                            <input type="file" id="ajaxFileUploadLandLevelDetailCoefficientVolumeRatio" name="file" style="display: none;" onchange="landLevel.importDataAllocationCorrectionCoefficientVolumeRatio(false);">
                        </div>
                    </div>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_content">
                    <div class="form-group">
                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4   col-xs-offset-5 col-sm-offset-5 col-md-offset-5 col-lg-offset-5">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                取消
                            </button>

                            <button id="commit_btn" class="btn btn-success" onclick="submit();">
                                提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                            </button>
                        </div>
                    </div>
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
<%@include file="/views/data/landModelDir/landModel.jsp" %>
<input type="file" id="ajaxFileUpload" name="file" style="display: none;">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/views/data/landModelDir/landLevel.js?v=${assessVersion}"></script>
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
            toastr.success('至少添加一条数据');
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
                    Alert("提交数据成功!", 1, null, function () {
                        window.open('javascript:window.open("", "_self", "");window.close();', '_self');
                    });
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


</script>
</body>
</html>

