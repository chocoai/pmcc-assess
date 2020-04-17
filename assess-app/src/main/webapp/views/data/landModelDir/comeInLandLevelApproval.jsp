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
                                                <label class="col-sm-1  control-label">省</label>
                                                <div class="col-sm-2">
                                                    <select name="province"
                                                            class="form-control input-full search-select select2">
                                                    </select>
                                                </div>
                                                <label class="col-sm-1  control-label">市</label>
                                                <div class="col-sm-2">
                                                    <select name="city"
                                                            class="form-control input-full search-select select2">
                                                    </select>
                                                </div>
                                                <label class=" col-sm-1 control-label">区县</label>
                                                <div class="col-sm-2">
                                                    <select name="district"
                                                            class="form-control input-full search-select select2">
                                                    </select>
                                                </div>
                                                <label class="col-sm-1  control-label">乡镇街道</label>
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
                                                <label class="col-sm-1  control-label">权利类型</label>
                                                <div class="col-sm-2">
                                                    <select name="landRightType"
                                                            class="form-control input-full search-select select2">
                                                    </select>
                                                </div>
                                                <div class="col-sm-1">
                                                </div>
                                                <div class="col-sm-2">
                                                    <button type="button" class="btn btn-success btn-sm"
                                                            onclick="findQuery();">
                                                        查询
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <table class="table table-bordered" id="tb_FatherList">
                                                <!-- cerare document add ajax data-->
                                            </table>
                                        </div>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                    <%@include file="/views/share/form_approval.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>

</body>

<%@include file="/views/data/landModelDir/landModelApproval.jsp" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/views/data/landModelDir/landLevel.js?v=${assessVersion}"></script>
<script type="text/javascript">
    $(document).ready(function () {
        landLevel.loadLandLevelList({processInsId: '${processInsId}'});
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
        if (!$("#frm_approval").valid()) {
            return false;
        }

        var data = formApproval.getFormData();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/dataLandLevel/comeInLandLevelApprovalSubmit",
            type: "get",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "提交数据成功", function () {
                        window.close();
                    });
                }
                else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }
</script>
</html>
