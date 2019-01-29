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
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>估价委托书</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content collapse">
                    <div class="col-sm-4">
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
                    <h3>估价对象位置示意图</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content collapse">
                    <div class="col-sm-6">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>估价对象</th>
                                <th>位置图</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${judgeObjectList}" var="judgeObject">
                                <tr>
                                    <td>${judgeObject.name}</td>
                                    <td>
                                        <input id="judge_object_position${judgeObject.id}" name="project_proxy"
                                               type="file" multiple="false">
                                        <div id="_judge_object_position${judgeObject.id}"></div>
                                    </td>
                                </tr>
                                <script type="text/javascript">
                                    $(function () {
                                        uploadFiles(AssessDBKey.SchemeJudgeObject, "${judgeObject.id}", AssessUploadKey.JUDGE_OBJECT_POSITION, "judge_object_position${judgeObject.id}");
                                        loadUploadFiles(AssessDBKey.SchemeJudgeObject, "${judgeObject.id}", AssessUploadKey.JUDGE_OBJECT_POSITION, "judge_object_position${judgeObject.id}");
                                    })
                                </script>
                            </c:forEach>
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
                    <h3>估价对象实况照片</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content collapse">
                    <c:forEach items="${judgeObjectFullList}" var="judgeObject">
                        <div class="row">
                            <input type="hidden" name="judgeObjectId" value="${judgeObject.id}">
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_title">${judgeObject.name}-全部</div>
                                    <div class="">
                                        <table class="table">
                                            <thead>
                                            <tr>
                                                <th>序号</th>
                                                <th>文件名称</th>
                                                <th>来源</th>
                                                <th>操作</th>
                                            </tr>
                                            </thead>
                                            <tbody data-id="${judgeObject.id}" data-name="live_situation_all">
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_title">${judgeObject.name}-已选</div>
                                    <div class="">
                                        <table class="table">
                                            <thead>
                                            <tr>
                                                <th>文件名称</th>
                                                <th>排序</th>
                                                <th>操作</th>
                                            </tr>
                                            </thead>
                                            <tbody data-id="${judgeObject.id}" data-name="live_situation_select">
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <script type="text/javascript">
                            $(function () {
                                //1.加载该委估对象所有相关实况照片 2.加载该委估对象已选择的实况照片
                                loadLiveSituationAll($('tbody[data-id=${judgeObject.id}][data-name=live_situation_all]'), ${judgeObject.id});
                                loadLiveSituationSelect($('tbody[data-id=${judgeObject.id}][data-name=live_situation_select]'), ${judgeObject.id});
                            })
                        </script>
                    </c:forEach>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>估价对象权属证明复印件</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content collapse">
                    <c:forEach items="${judgeObjectList}" var="judgeObject">
                        <div class="row">
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_title">${judgeObject.name}</div>
                                    <table class="table table-hover">
                                        <thead>
                                        <tr>
                                            <th>序号</th>
                                            <th>文件名称</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${ownershipCertFileList.get(judgeObject.id)}" var="item"
                                                   varStatus="i">
                                            <tr>
                                                <th>${i.index+1}</th>
                                                <td>${item.fileName}</td>
                                                <td>
                                                    <input type="button" class="btn btn-xs btn-primary" value="编辑"
                                                           onclick="FileUtils.editAttachment(${item.id},'${item.fileExtension}');">
                                                    <input type="button" class="btn btn-xs btn-warning" value="查看"
                                                           onclick="FileUtils.showAttachment(${item.id},'${item.fileExtension}');">
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>
                        估价中引用的专用文件资料
                    </h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content collapse">
                    <input type="button" class="btn btn-success" value="添加" onclick="addReportFileCustom();">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="x_panel">
                                <div class="x_title">地址不一致附件</div>
                                <div>
                                    <table class="table table-hover">
                                        <thead>
                                        <tr>
                                            <th>序号</th>
                                            <th>文件名称</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${inventoryAddressFileList}" var="item" varStatus="i">
                                            <tr>
                                                <th>${i.index+1}</th>
                                                <td>${item.fileName}</td>
                                                <td>
                                                    <input type="button" class="btn btn-xs btn-primary" value="编辑"
                                                           onclick="FileUtils.editAttachment(${item.id},'${item.fileExtension}');">
                                                    <input type="button" class="btn btn-xs btn-warning" value="查看"
                                                           onclick="FileUtils.showAttachment(${item.id},'${item.fileExtension}');">
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="x_panel">
                                <div class="x_title">法定优先受偿款附件</div>
                                <div>
                                    <table class="table table-hover">
                                        <thead>
                                        <tr>
                                            <th>序号</th>
                                            <th>文件名称</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${reimbursementFileList}" var="item" varStatus="i">
                                            <tr>
                                                <th>${i.index+1}</th>
                                                <td>${item.fileName}</td>
                                                <td>
                                                    <input type="button" class="btn btn-xs btn-primary" value="编辑"
                                                           onclick="FileUtils.editAttachment(${item.id},'${item.fileExtension}');">
                                                    <input type="button" class="btn btn-xs btn-warning" value="查看"
                                                           onclick="FileUtils.showAttachment(${item.id},'${item.fileExtension}');">
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row report-file-custom">
                    </div>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button id="btn_submit" class="btn btn-success" onclick="submit();">
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
<script type="text/html" id="reportFileCustomHtml">
    <div class="col-sm-6">
        <div class="x_panel">
            <div class="x_title">
                {name}
                <input type="button" class="btn btn-xs btn-warning" value="删除"
                       onclick="deleteReportFileCustom(this,'{id}');"
                       style="margin-left: 5px;">
            </div>
            <div class="col-sm-6">
                <input id="reportFileCustom{id}" name="reportFileCustom" type="file" multiple="false">
                <div id="_reportFileCustom{id}"></div>
            </div>
        </div>
    </div>
</script>
<script type="application/javascript">
    $(function () {
        uploadFiles(AssessDBKey.ProjectInfo, "${projectPlanDetails.projectId}", AssessUploadKey.PROJECT_PROXY);
        loadUploadFiles(AssessDBKey.ProjectInfo, "${projectPlanDetails.projectId}", AssessUploadKey.PROJECT_PROXY);
        loadReportFileCustomList();
    });

    //初始化上传控件
    function uploadFiles(tableName, tableId, fieldsName, target) {
        FileUtils.uploadFiles({
            target: target == undefined ? fieldsName : target,
            disabledTarget: "btn_submit",
            formData: {
                tableName: tableName,
                tableId: tableId,
                fieldsName: fieldsName,
                projectId: "${projectPlanDetails.projectId}"
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
                fieldsName: fieldsName,
                projectId: "${projectPlanDetails.projectId}"
            },
            editFlag: true,
            deleteFlag: true
        })
    }

    //加载委估对象下所有实况照片
    function loadLiveSituationAll(tbody, judgeObjectId) {
        $.ajax({
            url: '${pageContext.request.contextPath}/scheme/getLiveSituationAll',
            data: {
                judgeObjectId: judgeObjectId,
                projectId: '${projectPlanDetails.projectId}'
            },
            success: function (result) {
                if (result.ret) {
                    var html = '';
                    $.each(result.data, function (i, item) {
                        ++i;
                        html += '<tr><th scope="row">' + i + '</th><td>' + item.fileName + '</td><td>' + item.reName + '</td><td>' +
                            '<input type="button" class="btn btn-xs btn-primary" value="查看" onclick="FileUtils.showAttachment(' + item.id + ',\'' + item.fileExtension + '\');">' +
                            '<input type="button" class="btn btn-xs btn-primary" value="选择" onclick="selectLiveSituation(' + item.id + ',' + judgeObjectId + ',\'' + item.fileName + '\');"></td></tr>';
                    })
                    tbody.empty().append(html);
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    }

    //选择估价对象实况照片
    function selectLiveSituation(attachmentId, judgeObjectId, fileName) {
        var tbody = $('tbody[data-id=' + judgeObjectId + '][data-name=live_situation_select]');
        var sorting = tbody.find('tr').length + 1;
        $.ajax({
            url: '${pageContext.request.contextPath}/scheme/selectLiveSituation',
            data: {
                attachmentId: attachmentId,
                judgeObjectId: judgeObjectId,
                fileName: fileName,
                sorting: sorting,
                planDetailsId: '${projectPlanDetails.id}'
            },
            success: function (result) {
                if (result.ret) {
                    var html = '<tr><td>' + fileName + '</td><td><input type="text" data-id="' + result.data + '" data-name="sorting" value="' + sorting + '"></td><td>' +
                        '<input type="button" class="btn btn-xs btn-primary" value="编辑" onclick="FileUtils.editAttachment(' + attachmentId + ',\'' + fileName + '\');">' +
                        '<input type="button" class="btn btn-xs btn-warning" value="移除" onclick="removeLiveSituation(' + result.data + ',this)"></td></tr>';
                    $('tbody[data-id=' + judgeObjectId + '][data-name=live_situation_select]').append(html);
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    }

    //移除实况照片
    function removeLiveSituation(id, _this) {
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
    }

    //加载委估对象下已选实况照片
    function loadLiveSituationSelect(tbody, judgeObjectId) {
        $.ajax({
            url: '${pageContext.request.contextPath}/scheme/getLiveSituationSelect',
            data: {
                judgeObjectId: judgeObjectId
            },
            success: function (result) {
                if (result.ret) {
                    var html = '';
                    $.each(result.data, function (i, item) {
                        html += '<tr><td>' + item.fileName + '</td><td><input type="text" data-id="' + item.id + '" data-name="sorting" value="' + AssessCommon.toString(item.sorting) + '"></td><td>' +
                            '<input type="button" class="btn btn-xs btn-primary" value="编辑" onclick="FileUtils.editAttachment(' + item.id + ',\'' + item.fileExtension + '\');">' +
                            '<input type="button" class="btn btn-xs btn-warning" value="移除" onclick="removeLiveSituation(' + item.id + ',this)"></td></tr>';
                    })
                    tbody.empty().append(html);
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    }

    //加载自定义附件
    function loadReportFileCustomList() {
        $.ajax({
            url: '${pageContext.request.contextPath}/scheme/getReportFileCustomList',
            data: {
                areaId: '${projectPlanDetails.areaId}'
            },
            success: function (result) {
                if (result.ret) {
                    $('.report-file-custom').empty();
                    $.each(result.data, function (i, item) {
                        appendCustomHtml(item.id, item.name);
                    })
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    }

    //添加html
    function appendCustomHtml(id, name) {
        var html = $("#reportFileCustomHtml").html();
        html = html.replace(/{id}/g, id).replace(/{name}/g, name);
        $('.report-file-custom').append(html);
        uploadFiles(AssessDBKey.SchemeReportFileCustom, id, "reportFileCustom" + id);
        loadUploadFiles(AssessDBKey.SchemeReportFileCustom, id, "reportFileCustom" + id);
    }

    //添加自定义块
    function addReportFileCustom() {
        layer.prompt(function (value, index, elem) {
            $.ajax({
                url: '${pageContext.request.contextPath}/scheme/addReportFileCustom',
                data: {
                    name: value,
                    areaId: '${projectPlanDetails.areaId}'
                },
                success: function (result) {
                    if (result.ret) {
                        appendCustomHtml(result.data.id, result.data.name);
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
        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(data));
        }
        else {
            submitToServer(JSON.stringify(data));
        }
    }
</script>

</html>

