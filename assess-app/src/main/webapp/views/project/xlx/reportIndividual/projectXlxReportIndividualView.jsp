<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<div id="divBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 55%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">报告信息详情</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-md-1  control-label">
                                    项目名称
                                </label>
                                <div class="col-md-3">
                                    <label class="form-control input-full" name="projectName"></label>
                                </div>

                                <label class="col-md-1  control-label">
                                    文号
                                </label>
                                <div class="col-md-3">
                                    <label class="form-control input-full" name="numberValue"></label>
                                </div>
                                <label class="col-md-1  control-label">
                                    委托单位
                                </label>
                                <div class="col-md-3">
                                    <label class="form-control input-full" name="entrustedUnit"></label>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div style="display: none" class="house">
                        <%@include file="detail/house.jsp" %>
                    </div>

                    <div style="display: none" class="land">
                        <%@include file="detail/land.jsp" %>
                    </div>

                    <div style="display: none" class="assets">
                        <%@include file="detail/assets.jsp" %>
                    </div>


                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-md-1  control-label">
                                    承办表
                                </label>
                                <div class="col-md-3">
                                    <label class="form-control input-full" name="undertakeSheet"></label>
                                </div>
                                <label class="col-md-1  control-label">
                                    备注
                                </label>
                                <div class="col-md-7">
                                    <label class="form-control input-full" name="remark"></label>
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
            </div>

        </div>
    </div>
</div>

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
                                <form id="query_form" class="form-horizontal">
                                    <div class="form-group form-inline">

                                        <label class="col-md-1 col-form-label">项目类型</label>
                                        <div class="col-md-3 p-0">
                                            <select class="form-control input-full" required name="assessType">
                                                <option value="">--请选择--</option>
                                                <c:forEach var="classItem" items="${keyValueDtoList}">
                                                    <c:forEach var="typeItem" items="${classItem.keyValueDtos}">
                                                        <c:if test="${not empty typeItem.keyValueDtos}">
                                                            <c:forEach var="categoryItem"
                                                                       items="${typeItem.keyValueDtos}">
                                                                <option value="${categoryItem.key}">${categoryItem.value}</option>
                                                            </c:forEach>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <label class="col-md-1 col-form-label">项目名称</label>
                                        <div class="col-md-2 p-0">
                                            <input name="projectName"
                                                   class="form-control input-full" placeholder="项目名称"/>
                                        </div>
                                        <label class="col-md-1 col-form-label">文号</label>
                                        <div class="col-md-2 p-0">
                                            <input name="numberValue" class="form-control input-full"
                                                   placeholder="文号"/>
                                        </div>
                                        <div class="col-md-2 p-0">
                                            <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                    onclick="loadDataDicList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                                查询
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
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>

</body>

<script type="application/javascript">

    $(function () {
        loadDataDicList();
    });

    //加载代理数据列表
    function loadDataDicList() {
        var data = formParams("query_form");
        var arr = Object.keys(data);
        $.each(arr, function (i, item) {
            if (!data[item]) {
                data[item] = undefined;
            }
        });
        var cols = [];
        cols.push({field: 'projectName', title: '项目名称', width: '5%'});
        cols.push({field: 'entrustedUnit', title: '委托单位', width: '5%'});
        cols.push({field: 'reportScore', title: '报告份数', width: '5%'});
        cols.push({
            field: 'reportModificationDate', title: '报告修改日期', width: '10%', formatter: function (value, row, index) {
                return formatDate(row.reportModificationDate, true);
            }
        });
        cols.push({
            field: 'reportBindingDate', title: '报告装订日期', width: '10%', formatter: function (value, row, index) {
                return formatDate(row.reportBindingDate, true);
            }
        });
        cols.push({
            field: 'filingDate', title: '归档日期', width: '10%', formatter: function (value, row, index) {
                return formatDate(row.filingDate, true);
            }
        });

        cols.push({field: 'reportPeople', title: '报告人', width: '5%'});
        cols.push({field: 'numberValue', title: '文号', width: '5%'});
        cols.push({field: 'assessTotalToll', title: '收费', width: '5%'});
        cols.push({field: 'assessTypeName', title: '类型', width: '5%'});
        cols.push({
            field: 'id', title: '查看详情', formatter: function (value, row, index) {
                var str = '<button onclick="findDataDic(' + row.id + ')" style="margin-left: 5px;" class="btn   btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看子项">';
                str += '<i class="fa fa-search"></i>';
                str += '</button>';
                return str;
            }
        });
        var target = $("#tb_List");
        target.bootstrapTable('destroy');
        TableInit(target, "${pageContext.request.contextPath}/projectXlxReportIndividual/getBootstrapTableVo", cols, data, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }

    function findDataDic(id) {
        var item = $("#tb_List").bootstrapTable('getRowByUniqueId', id);
        console.log(item);
        var box = $("#divBox") ;
        var frm = box.find("form") ;
        frm.clearAll();
        frm.initForm(item);
        try {
            frm.find("label[name='reportModificationDate']").html(formatDate(item.reportModificationDate));
            frm.find("label[name='reportBindingDate']").html(formatDate(item.reportBindingDate));
            frm.find("label[name='filingDate']").html(formatDate(item.filingDate));
            frm.find("label[name='contractSigningDate']").html(formatDate(item.contractSigningDate));
            frm.find("label[name='appraisalPeriod']").html(formatDate(item.appraisalPeriod));
            frm.find("label[name='valueTimePoint']").html(formatDate(item.valueTimePoint));
        } catch (e) {
        }
        box.modal("show");
        frm.find("."+item.assessType).show() ;
    }


</script>
</html>
