<!DOCTYPE html>
<%@ page import="org.omg.CORBA.Request" %><%--
  Created by IntelliJ IDEA.
  User: red
  Date: 2017/06/13
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en" class="no-js">
<!--<![endif]-->
<!-- start: HEAD -->
<head>
    <title>${baseViewDto.currentMenu.name}</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<!-- end: HEAD -->
<!-- start: BODY -->
<body>
<%--<%@include file="share/main_head.jsp" %>--%>
<!-- start: MAIN CONTAINER -->
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
                            <div class="card-body">
                                <form class="form-horizontal">
                                    <div class="form-group form-inline">
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <div class="col-sm-11">
                                                        <input id="bom_upload" name="bom_upload" type="file" multiple="false">
                                                        <div id="_bom_upload">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
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
</html>

<script type="text/javascript">
    $(function () {
        loadFileList();

        FileUtils.uploadFiles({
            target: "bom_upload",
            buttonText: "上传图片",
            multi: false,
            deleteFlag: true,
            editFlag: false,
            showFileList: true,
            formData: {
                tableName: "ocr_ali_table",
                tableId: 0,
                fieldsName: "pmcc-assess"
            },
            onUploadComplete: function (id) {
                $.ajax({
                    url: "/aliocr/aliOcr",
                    data: {
                        fileId: id,
                        method: "tableExcel",
                        appKey: "pmcc-assess",
                        describe: "识别图片表格内容"
                    },
                    type: "get",
                    dataType: "json",
                    success: function (result) {
                        Loading.progressHide();
                        if (result.ret) {
                            loadFileList();
                        } else {
                            Alert("获取数据失败，失败原因:" + result.errmsg, 1, null, null);
                        }
                    },
                    error: function (result) {
                        Loading.progressHide();
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                });
            }
        });
    });

    function loadFileList() {
        FileUtils.getFileShows({
            target: "bom_upload",
            formData: {
                tableName: "ocr_ali_table",
                tableId: 0,
                fieldsName: "pmcc-assess"
            },
            deleteFlag: true
        })
    }
</script>
