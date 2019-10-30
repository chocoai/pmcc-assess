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
                        <%@include file="landLevelBase.jsp" %>
                    </form>
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


    landLevel.initDataForm = function (data) {
        var frm = $("#landLeveFrm") ;
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


    $(document).ready(function () {
        var jsonData = ${el:toJsonString(dataLandLevel)};
        landLevel.initDataForm(jsonData) ;
    });

</script>
<script type="application/javascript">
    function saveform() {
        var frm = $("#landLeveFrm") ;
        if (!frm.valid()) {
            return false;
        }
        var data = {};
        data.formData = JSON.stringify(formSerializeArray(frm));
        var approvalData = formParams('frm_approval');
        data = $.extend({}, approvalData, data);
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/dataLandLevel/comeInLandLevelEditSubmit",
            type: "post",
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
