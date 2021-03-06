<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <title>风险分析</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<!-- 列表 -->
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
                                <div class="row">
                                    <div class="col-md-12">
                                        <form id="frmQuery" class="form-horizontal">
                                            <div class="form-group form-inline">
                                                <label for="queryName" class="col-md-1 col-form-label">名称</label>
                                                <div class="col-md-3 p-0">
                                                    <input type="text" data-rule-maxlength="50"
                                                           placeholder="名称" id="queryName" name="queryName"
                                                           class="form-control input-full">
                                                </div>
                                                <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button" onclick="loadReportAnalysisList()"><span class="btn-label"><i class="fa fa-search"></i></span>查询</button>
                                                <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button" data-toggle="modal" onclick="addReportAnalysis()" href="#divBox"><span class="btn-label"><i class="fa fa-plus"></i></span>新增</button>
                                            </div>
                                        </form>
                                        <table class="table table-bordered" id="tb_List">
                                            <!-- cerare document add ajax data-->
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
<%@include file="/views/data/common/projectType.jsp" %>
</body>
<!-- 添加 -->
<div id="divBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">风险分析配置</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form id="frm" class="form-horizontal">
                    <input type="hidden" id="id" name="id" value="0">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-3">
                                                <input required type="text" class="form-control input-full" name="name">
                                            </div>
                                            <div class="col-sm-2">
                                                <div class="form-check" style="justify-content:left">
                                                    <label class="form-check-label">
                                                        <input class="form-check-input" type="checkbox" id="bisModifiable"
                                                               name="bisModifiable" value="true"
                                                               checked="checked">
                                                        <span class="form-check-sign">可修改</span>
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="col-sm-5">
                                                <button type="button" class="btn btn-success btn-sm"
                                                        onclick="projectTypeObj.addTypeHtml();">
                                                    <span class="btn-label"><i class="fa fa-plus"></i></span>添加类型
                                                </button>
                                                <button type="button" class="btn btn-warning btn-sm"
                                                        onclick="showItemable()" data-toggle="modal"><span
                                                        class="btn-label"><i class="fa fa-bars"></i></span>管理子模板
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="system"></div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                委托目的<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="entrustmentPurpose" multiple="multiple"
                                                        class="form-control input-full search-select select2"
                                                        required="required">
                                                    <c:forEach items="${purposeDicList}" var="item">
                                                        <option value="${item.id}">${item.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                key值
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" class="form-control input-full" name="fieldName" placeholder="key值">
                                            </div>
                                            <label class="col-sm-2 col-form-label">
                                                排序
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" class="form-control input-full" name="sorting" placeholder="排序"
                                                       required="required" data-rule-number='true'>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                模板<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <div style="width:99%;height:200px;" id="template"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="saveReportAnalysis()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>
<%@include file="/views/data/common/dataReportTemplateItem.jsp" %>

<script type="application/javascript">
    $(function () {
        loadReportAnalysisList();
    })
    var type = "report_analysis_risk";
    var ue = UE.getEditor('template', {
        toolbars: [
            ['source', 'autotypeset', 'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc']
        ],
        zIndex: 11009,
        initialFrameHeight: 220,
        elementPathEnabled: false,//是否启用元素路径，默认是true显示
        wordCount: false, //是否开启字数统计
        autoHeightEnabled: false,
        autoFloatEnabled: true
    });

    //加载 评估依据 数据列表
    function loadReportAnalysisList() {
        var cols = [];
        cols.push({field: 'name', title: '名称', width: '15%'});
        cols.push({field: 'typeName', title: '项目类型', width: '15%'});
        cols.push({field: 'entrustmentPurposeName', title: '委托目的', width: '15%'});
        cols.push({field: 'template', title: '模板', width: '40%'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<button onclick="editReportAnalysis(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button onclick="removeReportAnalysis(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
                return str;
            }
        });
        $("#tb_List").bootstrapTable('destroy');
        TableInit("tb_List", "${pageContext.request.contextPath}/reportAnalysisRisk/list", cols, {
            name: $('#queryName').val(),
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }

    //删除 评估依据 数据()
    function removeReportAnalysis(id) {
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/reportAnalysis/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        notifySuccess("成功", "删除数据成功");
                        loadReportAnalysisList();//重载 (刷新)
                    }
                    else {
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        })
    }

    //对新增 评估依据 数据处理
    function addReportAnalysis() {
        $("#frm").clearAll();
        $("#bisModifiable").prop("checked", true);
    }
    //新增 评估依据 数据
    function saveReportAnalysis() {
        var data = formParams("frm");
        data.bisModifiable = true;
        if(!$("#bisModifiable").prop("checked")){
            data.bisModifiable = false;
        }
        data.entrustmentPurpose = ',' + data.entrustmentPurpose + ',';//方便like查询
        var type = ',';
        $("#frm").find('[name^=type]').each(function () {
            type += $(this).val() + ',';
        })
        var category = ',';
        $("#frm").find('[name^=category]').each(function () {
            category += $(this).val() + ',';
        })
        data.type = type;//方便like查询
        data.category = category;//方便like查询
        data.template = ue.getContent();
        if ($("#frm").valid()) {
            $.ajax({
                url: "${pageContext.request.contextPath}/reportAnalysisRisk/save",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        loadReportAnalysisList();
                        $('#divBox').modal('hide');
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
    }
    //评估依据 修改
    function editReportAnalysis(id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/reportAnalysis/get",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    $("#frm").clearAll().initForm(result.data);
                    $("#frm").find("[name='entrustmentPurpose']").val(result.data.entrustmentPurpose.split(',')).trigger('change');
                    projectTypeObj.editTypeHtml(result.data.type,result.data.category);
                    var content = result.data.template;
                    setTimeout(function () {
                        ue.setContent(content, false);
                    }, 500);
                    $('#divBox').modal();
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }

    function showItemable() {
        var id = $("#id").val();
        if(!id){
            id = 0;
        }
        dataReportTemplateItem.prototype.showStartModel(id, type);
    }
</script>


</html>
