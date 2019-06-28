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
            <div class="page-title" style="margin: 0px">
                <div class="title_left">
                    <h3>
                        估价委托书及相关证明
                    </h3>
                </div>
            </div>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>估价委托书</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content collapse">
                    <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
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
                    <h3>位置示意图</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content collapse">
                    <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6 ">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>权证对象</th>
                                <th>位置图</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${declareRecordList}" var="declareRecord">
                                <tr>
                                    <td>${declareRecord.name} </td>
                                    <td>
                                        <input id="judge_object_position${declareRecord.id}" name="project_proxy"
                                               type="file" multiple="false">
                                        <div id="_judge_object_position${declareRecord.id}"></div>
                                    </td>
                                </tr>
                                <script type="text/javascript">
                                    $(function () {
                                        uploadFiles(AssessDBKey.DeclareRecord, "${declareRecord.id}", AssessUploadKey.JUDGE_OBJECT_POSITION, "judge_object_position${declareRecord.id}");
                                        loadUploadFiles(AssessDBKey.DeclareRecord, "${declareRecord.id}", AssessUploadKey.JUDGE_OBJECT_POSITION, "judge_object_position${declareRecord.id}");
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
                    <h3>实况照片</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content collapse">
                    <c:forEach items="${declareRecordList}" var="declareRecord">
                        <div class="row">
                            <input type="hidden" name="declareRecordId" value="${declareRecord.id}">
                            <div class=" col-xs-6612  col-sm-6612  col-md-6612  col-lg-6612  col-sm-6 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h4><strong>${declareRecord.name}</strong>
                                            <small>
                                                <input type="button" class="btn btn-success btn-xs"
                                                       onclick="addLiveSituationFile(${declareRecord.id})"
                                                       value="新增实况照片">
                                            </small>
                                        </h4>

                                    </div>
                                    <div class="">
                                        <table class="table">
                                            <thead>
                                            <tr>
                                                <th>文件名称</th>
                                                <th>排序</th>
                                                <th>附件</th>
                                                <th>操作</th>
                                            </tr>
                                            </thead>
                                            <tbody data-id="${declareRecord.id}" data-name="live_situation_select">
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <script type="text/javascript">
                            $(function () {
                                //1.加载该委估对象所有相关实况照片 2.加载该委估对象已选择的实况照片
                                loadLiveSituation($('tbody[data-id=${declareRecord.id}][data-name=live_situation_select]'), ${declareRecord.id});
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
                    <h3>权属证明复印件</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content collapse">
                    <c:forEach items="${declareRecordList}" var="declareRecord">
                        <c:if test="${not empty ownershipCertFileList.get(declareRecord.id)}">
                        <div class="row">
                            <div class=" col-xs-6612  col-sm-6612  col-md-6612  col-lg-6612  col-sm-6 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_title"><h4><strong>${declareRecord.name}</strong></h4></div>
                                    <table class="table table-hover">
                                        <thead>
                                        <tr>
                                            <th>序号</th>
                                            <th>文件名称</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${ownershipCertFileList.get(declareRecord.id)}" var="item"
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
                        </c:if>
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
                    <div class="row">
                        <c:forEach items="${declareRecordList}" var="declareRecord">
                            <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6 ">
                                <div class="x_panel">
                                    <div class="x_title"><h4><strong>${declareRecord.name}</strong></h4></div>
                                    <c:if test="${not empty inventoryAddressFileList.get(declareRecord.id)}">
                                        <div class="x_panel">
                                            <div class="x_title">登记与实际地址不一致附件</div>
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
                                                    <c:forEach items="${inventoryAddressFileList.get(declareRecord.id)}"
                                                               var="item" varStatus="i">
                                                        <tr>
                                                            <th>${i.index+1}</th>
                                                            <td>${item.fileName}</td>
                                                            <td>
                                                                <input type="button" class="btn btn-xs btn-primary"
                                                                       value="编辑"
                                                                       onclick="FileUtils.editAttachment(${item.id},'${item.fileExtension}');">
                                                                <input type="button" class="btn btn-xs btn-warning"
                                                                       value="查看"
                                                                       onclick="FileUtils.showAttachment(${item.id},'${item.fileExtension}');">
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </c:if>
                                    <input type="button" class="btn btn-success" value="自定义添加"
                                           onclick="addReportFileCustom(${declareRecord.id});">
                                    <div class="row report-file-custom${declareRecord.id}">
                                    </div>
                                </div>
                            </div>

                            <script type="text/javascript">
                                $(function () {
                                    //1.加载该委估对象所有相关实况照片 2.加载该委估对象已选择的实况照片
                                    loadReportFileCustomList(${declareRecord.id});
                                })
                            </script>
                        </c:forEach>
                    </div>

                </div>
            </div>

            <div class="x_panel">
                <div class="x_content">
                    <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4    col-xs-offset-5 col-sm-offset-5 col-md-offset-5 col-lg-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button id="btn_submit" class="btn btn-warning" onclick="submit();">
                            保存<i style="margin-left: 10px" class="fa fa-save"></i>
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
<div id="addItemModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">添加照片</h3>
            </div>
            <form id="frmItemFile" class="form-horizontal">
                <input type="hidden" name="declareRecordId">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">名称</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control"
                                                   name="fileName" placeholder="名称">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">排序</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control"
                                                   name="sorting" placeholder="排序">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">排序</label>
                                        <div class="col-sm-10">
                                    <textarea placeholder="备注" id="subRemark" name="remark"
                                              class="form-control"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">附件</label>
                                        <div class="col-sm-10">
                                            <input id="uploadSupplementFile" placeholder="上传附件" class="form-control"
                                                   type="file">
                                            <div id="_uploadSupplementFile"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        关闭
                    </button>
                    <button type="button" onclick="saveItemFileData()" class="btn btn-default">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/html" id="reportFileCustomHtml">
    <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6 ">
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
        uploadFiles(AssessDBKey.ProjectInfo, "${projectInfo.id}", AssessUploadKey.PROJECT_PROXY);
        loadUploadFiles(AssessDBKey.ProjectInfo, "${projectInfo.id}", AssessUploadKey.PROJECT_PROXY);
    });


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
                    Alert(result.errmsg);
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
        $.ajax({
            url: '${pageContext.request.contextPath}/projectReportFile/saveReprotFile',
            data: {formData: JSON.stringify(data)},
            success: function (result) {
                if (result.ret) {
                    Alert("保存成功", 1, null, function () {
                        window.close();
                    })
                } else {
                    Alert(result.errmsg);
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
                            '<td><input type="text" name="sorting"  value="' + AssessCommon.toString(item.sorting) + '" onblur="reportFileEditName(' + item.id + ',this);" ></td>' +
                            '<td>' + item.fileViewName + '</td><td>' +
                            '<input type="button" class="btn btn-xs btn-primary" value="编辑" onclick="getAndInit(' + item.id + ');">' +
                            '<input type="button" class="btn btn-xs btn-warning" value="移除" onclick="removeLiveSituation(' + item.id + ',this)"></td></tr>';
                    })
                    tbody.empty().append(html);
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

    function addLiveSituationFile(declareRecordId) {
        $("#frmItemFile").clearAll();
        $("#frmItemFile").find("input[name='declareRecordId']").val(declareRecordId);
        uploadFiles(AssessDBKey.DeclareRecord, 0, "live_situation_select_supplement", "uploadSupplementFile");
        loadUploadFiles(AssessDBKey.DeclareRecord, 0, "live_situation_select_supplement", "uploadSupplementFile");
        $("#addItemModal").modal("show");

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
                    console.log(result.data);
                    uploadFiles(AssessDBKey.DeclareRecord, result.data.id, "live_situation_select_supplement", "uploadSupplementFile");
                    loadUploadFiles(AssessDBKey.DeclareRecord, result.data.id, "live_situation_select_supplement", "uploadSupplementFile");
                    $('#addItemModal').modal("show");
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
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
                    toastr.success('保存成功');
                    $('#addItemModal').modal('hide');
                    loadLiveSituation($('tbody[data-id="' + data.declareRecordId + '"][data-name=live_situation_select]'), data.declareRecordId);
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

    //实况照片改名
    function reportFileEditName(id, _this) {
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

                } else {
                    Alert(result.errmsg);
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
</script>

</html>

