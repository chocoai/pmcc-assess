<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="assess.pmcc.com/el-common" prefix="el" %>
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
                        <small>土地级别数据</small>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <form id="frmQuery" class="form-horizontal">
                                <input type="hidden" name="readOnly" value="${readOnly}">
                                <div class="form-group">

                                    <div class="x-valid">
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <div class="btn-group btn-group-justified" role="group">
                                                <div class="btn-group" role="group">
                                                    <button type="button" class="btn btn-primary"
                                                            onclick="landLevel.initDataForm({})"
                                                            data-toggle="modal" href="#divBoxFather"> 新增
                                                    </button>
                                                </div>
                                                <div class="btn-group" role="group">
                                                    <button type="button" class="btn btn-default dropdown-toggle"
                                                            data-toggle="dropdown" aria-haspopup="true"
                                                            aria-expanded="false">excel工具
                                                        <span class="caret"></span>
                                                    </button>
                                                    <ul class="dropdown-menu">
                                                        <li>
                                                            <button type="button" class="btn-default btn"
                                                                    onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftpLandLevelAreaBaseTemplate);">
                                                                土地区域excel模板下载
                                                                <span class="fa-stack fa-lg">
                                                              <i class="fa fa-square-o fa-stack-2x"></i>
                                                              <i class="fa fa fa-cloud-download fa-stack-1x"></i>
                                                                </span>
                                                            </button>
                                                        </li>
                                                        <li>
                                                            <button class="btn-default btn" type="button"
                                                                    onclick="$('#ajaxFileUploadDataLand').val('').trigger('click')">
                                                                土地区域excel模板导入
                                                                <span class="fa-stack fa-lg">
                                                              <i class="fa fa-square-o fa-stack-2x"></i>
                                                              <i class="fa fa fa fa-cloud-upload fa-stack-1x"></i>
                                                                </span>
                                                            </button>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="x-valid">
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <div class="input-group">

                                                <select name="province"
                                                        class="form-control search-select select2">
                                                </select>

                                                <select name="city" class="form-control search-select select2">
                                                </select>

                                                <select name="district"
                                                        class="form-control search-select select2">
                                                </select>
                                            </div>
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
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <div class="input-group">
                                                <span class="input-group-btn">
                                                <input placeholder="乡镇名称" class="form-control" name="townShipName"
                                                       type="text">
                                                </span>
                                                <span class="input-group-btn">
                                                    <button type="button" class="btn btn-success"
                                                            onclick="landLevel.loadLandLevelList({processInsId:'${processInsId}'})">
                                                        查询 <i class="fa fa-search" aria-hidden="true"></i>
                                                    </button>
                                                </span>
                                            </div>
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

                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <input type="file" id="ajaxFileUploadDataLand" name="file" style="display: none;"
                                   onchange="landLevel.importDataLand();">

                            <input type="file" id="ajaxFileUploadLandLevelDetail" name="file" style="display: none;"
                                   onchange="landLevel.importLandLevelDetail(false);">
                        </div>
                    </div>
                </div>
            </div>


            <div class="x_panel">
                <div class="x_content">
                    <div style="text-align: center;">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button id="btn_close" class="btn btn-warning" onclick="closeBasicApp();">
                            关闭流程<i style="margin-left: 10px" class="fa fa-close"></i>
                        </button>
                        <button id="btn_submit" class="btn btn-primary" onclick="saveform();">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </div>

                </div>
            </div>

            <%@include file="/views/share/form_log.jsp" %>
            <form id="frm_approval">
                <%@include file="/views/share/ApprovalVariable.jsp" %>
            </form>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<%@include file="/views/data/landModelDir/landModel.jsp" %>

<input type="file" id="ajaxFileUpload" name="file" style="display: none;">
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>

<script type="text/javascript"
        src="${pageContext.request.contextPath}/views/data/landModelDir/landLevel.js?v=${assessVersion}"></script>
<script type="text/javascript">


    $(document).ready(function () {

        landLevel.loadLandLevelList({processInsId:'${processInsId}'});

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
<script type="application/javascript">
    function saveform() {
        var result = landLevel.config.table.bootstrapTable('getData');
        if (result.length == 0) {
            toastr.success('至少添加一条数据');
            return false;
        }
        var data = [];
        $.each(result, function (i, item) {
            data.push(item.id);
        });
        var approvalData = formParams('frm_approval');
        approvalData = $.extend({}, approvalData, {ids:data.join(",")});
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/dataLandLevel/comeInLandLevelEditSubmit",
            type: "post",
            dataType: "json",
            data: approvalData,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }

    //关闭流程
    function closeBasicApp() {
        Alert('确定要关闭流程么？', 2, null, function () {
            AssessCommon.closeProcess('${basicApply.processInsId}', function () {
                Alert('流程关闭成功', 1, null, function () {
                    window.close();
                })
            })
        })
    }
</script>
</html>
