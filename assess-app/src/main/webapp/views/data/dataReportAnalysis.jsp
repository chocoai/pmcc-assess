<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body class="nav-md footer_fixed">
<!-- 列表 -->
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                        ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frmQuery" class="form-horizontal">
                        <div class="form-group ">
                            <div>
                                <label class="col-sm-1 control-label">名称</label>
                                <div class="col-sm-2">
                                    <input type="text" id="queryName" class="form-control">
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">类别</label>
                                <div class="col-sm-2">
                                    <select class="form-control" id="queryReportAnalysisType">
                                        <option value="">-请选择-</option>
                                        <c:forEach items="${reportAnalysisTypeList}" var="item">
                                            <option value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <button type="button" class="btn btn-primary" onclick="loadReportAnalysisList()">
                                    查询
                                </button>

                                <button type="button" class="btn btn-success" onclick="addReportAnalysis()"
                                        data-toggle="modal" href="#divBox"> 新增
                                </button>
                            </div>
                        </div>

                    </form>
                    <table class="table table-bordered" id="tb_List">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<!-- 添加 -->
<div id="divBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">报告分析配置</h3>
            </div>
            <form id="frm" class="form-horizontal">
                <input type="hidden" id="id" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">省<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-2">
                                            <select id="province" name="province"
                                                    class="form-control search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">市<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-2">
                                            <select id="city" name="city" class="form-control search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">县</label>
                                        <div class="col-sm-2">
                                            <select id="district" name="district"
                                                    class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10" id="method">
                                            <input required type="text" class="form-control" name="name">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            类别<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <select required class="form-control search-select select2"
                                                    name="reportAnalysisType">
                                                <option value="">请选择</option>
                                                <c:forEach items="${reportAnalysisTypeList}" var="item">
                                                    <option value="${item.id}">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            是否可修改
                                        </label>
                                        <div class="col-sm-4">
                                            <label class="radio-inline">
                                                <input type="checkbox" id="bisModifiable" name="bisModifiable"
                                                       value="true"
                                                       checked="checked">
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            委托目的<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10" id="entrustmentPurpose">
                                            <c:forEach items="${purposeDicList}" var="item">
                                                <span class="checkbox-inline">
                                                <input type="checkbox" id="entrustmentPurpose${item.id}" required
                                                       name="entrustmentPurpose" value="${item.id}"
                                                       class="form-inline">
                                                <label for="entrustmentPurpose${item.id}">${item.name}</label>
                                                </span>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            模版<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <%--<textarea placeholder="请填写模版" class="form-control" id="template"
                                                      name="template" required="required"
                                                      onkeyup="extractTemplateField()"></textarea>
                                            <div class="template-field">

                                            </div>--%>
                                            <div style="width:99%;height:200px;" id="template"></div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="saveReportAnalysis()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    $(function () {
        loadReportAnalysisList();
    })
    var ue = UE.getEditor('template');
    //提取字段
    // function extractTemplateField() {
    //     var text = $("#template").val();
    //     $('.template-field').empty();
    //     var fieldArray = AssessCommon.extractField(text);
    //     if (fieldArray && fieldArray.length > 0) {
    //         var html = '';
    //         $.each(fieldArray, function (i, item) {
    //             field = fieldArray;
    //             html += '<span class="label label-default">' + item + '</span> ';
    //         })
    //         $('.template-field').append(html);
    //     }
    // }

    //加载 评估依据 数据列表
    function loadReportAnalysisList() {
        var cols = [];
        cols.push({
            field: 'provinceName', title: '区域', formatter: function (value, row, index) {
                return AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName)
            }
        });
        cols.push({field: 'name', title: '名称', width: '15%'});
        cols.push({field: 'reportAnalysisTypeName', title: '类别', width: '8%'});
        cols.push({field: 'entrustmentPurposeName', title: '委托目的', width: '15%'});
        cols.push({field: 'template', title: '模板', width: '40%'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="editReportAnalysis(' + row.id + ')"><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="removeReportAnalysis(' + row.id + ')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_List").bootstrapTable('destroy');
        var reportAnalysisType = $("#queryReportAnalysisType").val();
        TableInit("tb_List", "${pageContext.request.contextPath}/reportAnalysis/list", cols, {
            name: $('#queryName').val(),
            reportAnalysisType: reportAnalysisType
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
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/reportAnalysis/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        loadReportAnalysisList();//重载 (刷新)
                    }
                    else {
                        Alert("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        })
    }

    //对新增 评估依据 数据处理
    function addReportAnalysis() {
        $("#frm").clearAll();
        AssessCommon.initAreaInfo({
            provinceTarget: $("#province"),
            cityTarget: $("#city"),
            districtTarget: $("#district"),
            provinceValue: '',
            cityValue: '',
            districtValue: ''
        })
        //extractTemplateField();
    }
    //新增 评估依据 数据
    function saveReportAnalysis() {
        var data = formParams("frm");
        data.entrustmentPurpose = ',' + data.entrustmentPurpose + ',';//方便like查询
        data.template = ue.getContent();
        if ($("#frm").valid()) {
            $.ajax({
                url: "${pageContext.request.contextPath}/reportAnalysis/save",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        loadReportAnalysisList();
                        $('#divBox').modal('hide');
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
                    $("#frm").clearAll();
                    $("#frm").clearAll().initForm(result.data);
                    AssessCommon.initAreaInfo({
                        provinceTarget: $("#province"),
                        cityTarget: $("#city"),
                        districtTarget: $("#district"),
                        provinceValue: result.data.province,
                        cityValue: result.data.city,
                        districtValue: result.data.district
                    })
                    //extractTemplateField();
                    AssessCommon.checkboxToChecked($("#frm").find(":checkbox[name='entrustmentPurpose']"), result.data.entrustmentPurpose.split(','));
                    var content = result.data.template;
                    setTimeout(function () {
                        ue.setContent(content, false);
                    }, 500);
                    $('#divBox').modal();
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }


</script>


</html>
