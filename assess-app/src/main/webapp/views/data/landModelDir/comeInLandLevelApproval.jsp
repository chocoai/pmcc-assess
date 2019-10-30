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
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                    </ul>
                    <h3>
                        土地级别
                    </h3>
                    <div class="clearfix"></div>
                </div>

                <div class="x_content">
                    <form class="form-horizontal" id="landLeveFrm">

                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">省
                                    <span class="symbol required"></span></label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">
                                        ${dataLandLevel.provinceName}
                                    </label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">市<span
                                        class="symbol required"></span></label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">
                                        ${dataLandLevel.cityName}
                                    </label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">县</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">
                                        ${dataLandLevel.districtName}
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">乡镇名称</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">
                                        ${dataLandLevel.townShipName}
                                    </label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">权利类型</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">
                                        ${dataLandLevel.landRightTypeName}
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">估价期日</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">
                                        <fmt:formatDate value="${dataLandLevel.valuationDate}" pattern="yyyy年MM月dd日"/>
                                    </label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">发布日期</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">
                                        <fmt:formatDate value="${dataLandLevel.releaseDate}" pattern="yyyy年MM月dd日"/>
                                    </label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">执行时间</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">
                                        <fmt:formatDate value="${dataLandLevel.executionTime}" pattern="yyyy年MM月dd日"/>
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">标题</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">
                                        ${dataLandLevel.title}
                                    </label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">文号</label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">
                                        ${dataLandLevel.wordSymbol}
                                    </label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    基准地价定义
                                </label>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <label class="form-control">
                                        ${dataLandLevel.landDefinition}
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附件</label>
                                <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                    <div id="_uploadFile"></div>
                                </div>
                            </div>
                        </div>

                    </form>
                </div>

            </div>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    var landLevel = {};

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


    landLevel.initDataForm = function (data) {
        var frm = $("#landLeveFrm");
        frm.clearAll();
        frm.initForm(data);

        var files = ['uploadFile'];
        $.each(files, function (i, item) {
            landLevel.showFile(item, AssessDBKey.DataLandLevel, data.id, true, true, item);
            landLevel.fileUpload(item, AssessDBKey.DataLandLevel, data.id, true, item);
        });

    };

    $(document).ready(function () {
        landLevel.initDataForm({id: "${dataLandLevel.id}"});
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
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    Alert(result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        })
    }
</script>
</html>
