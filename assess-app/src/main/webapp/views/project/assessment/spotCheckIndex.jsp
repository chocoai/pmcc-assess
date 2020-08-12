<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
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
                                <form id="frmQuery" class="form-horizontal">
                                    <div class="form-group form-inline">
                                        <label for="queryName" class="col-md-1 col-form-label">名称</label>
                                        <div class="col-md-3 p-0">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="名称" id="queryName" name="queryName"
                                                   class="form-control input-full">
                                        </div>
                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="loadProjectSpotCheckList();">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                        <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                                onclick="showSpotCheckModal();"><span class="btn-label"><i
                                                class="fa fa-plus"></i></span>
                                            新增
                                        </button>
                                    </div>
                                </form>
                                <table class="table table-bordered" id="tbSpotChcekList"></table>
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

<%--填写考核基础信息--%>
<div id="spotCheckModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">抽查考核</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form id="frmSpotCheck" class="form-horizontal">
                    <div class="form-group form-inline">
                        <label class="col-sm-2 col-form-label">抽查月份<span class="symbol required"></span></label>
                        <div class="col-sm-10">
                            <input type="text" data-rule-maxlength="50" placeholder="抽查月份"
                                   name="spotMonth" readonly="readonly" required
                                   class="form-control input-full date-picker dbdate-month"
                                   data-date-format="yyyy-mm">
                        </div>
                    </div>
                    <div class="form-group form-inline">
                        <label class="col-sm-2 col-form-label">被抽查人<span class="symbol required"></span></label>
                        <div class="col-sm-10">
                            <input type="hidden" name="bySpotUser" data-title="account">
                            <input type="text" data-rule-maxlength="50" placeholder="被抽查人"
                                   name="bySpotUserName" required data-title="name"
                                   readonly="readonly" onclick="selectUser(this);"
                                   class="form-control input-full">
                        </div>
                    </div>
                    <div class="form-group form-inline">
                        <label class="col-sm-2 col-form-label">标题<span
                                class="symbol required"></span></label>
                        <div class="col-sm-10">
                            <input type="text" data-rule-maxlength="100" onfocus="titleFocus(this);" placeholder="标题"
                                   name="title"
                                   class="form-control input-full">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="saveSpotCheck();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        loadProjectSpotCheckList();

        //月份选择处理
        DatepickerUtils.initDate($('.dbdate-month'), {
            autoclose: true,
            todayBtn: "linked",
            language: "zh-CN",
            clearBtn: true,
            format: 'yyyy-mm',
            startView: 4,
            minView: 3
        });
    });

    //加载抽查数据列表
    function loadProjectSpotCheckList() {
        var cols = [];
        cols.push({field: 'title', title: '标题', width: '40%'});
        cols.push({field: 'bySpotUserName', title: '被抽查人', width: '20%'});
        cols.push({
            field: 'gmtCreated', title: '抽查时间', width: '20%', formatter: function (value, row, index) {
                return formatDate(row.gmtCreated, true);
            }
        });
        cols.push({
            field: 'status', title: '状态', width: '10%', formatter: function (value, row, index) {
                var str = "";
                switch (value) {
                    case "runing": {
                        str = "<span class='label label-info'>" + "进行中" + "</span>";
                        break;
                    }
                    case "finish": {
                        str = "<span class='label label-success'>" + "已完成" + "</span>";
                        break;
                    }
                    case "draft": {
                        str = "<span class='label label-warning'>" + "草稿" + "</span>";
                        break;
                    }
                }
                return str;
            }
        });
        cols.push({
            field: 'opt', title: '操作', width: '10%', formatter: function (value, row, index) {
                var str = '';
                if(row.status=='draft'){
                    str += '<button type="button" onclick="fillInfo(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="继续填写">';
                    str += '<i class="fa fa-pen"></i>';
                    str += '</button>';
                }
                str += '<button type="button" onclick="viewDetail(' + row.id + ',\''+row.processInsId+'\')"  style="margin-left: 5px;"  class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看详情">';
                str += '<i class="fa fa-search"></i>';
                str += '</button>';
                return str;
            }
        });
        $("#tbSpotChcekList").bootstrapTable('destroy');
        TableInit("tbSpotChcekList", "${pageContext.request.contextPath}/projectSpotCheck/getProjectSpotCheckList", cols, {
            spotChcekId: '${projectSpotChcek.id}'
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    //选择人员
    function selectUser(_this) {
        var div = $(_this).closest("div");
        erpEmployee.select({
            currOrgId: '${companyId}',
            showAllUser: 2,
            onSelected: function (data) {
                div.find("input[data-title='name']").val(data.name);
                div.find("input[data-title='account']").val(data.account);
            }
        });
    }

    //自动生成标题
    function titleFocus(_this) {
        var form = $(_this).closest('form');
        var spotMonth = form.find('[name=spotMonth]').val();
        var name = form.find('[name=bySpotUserName]').val();
        form.find('[name=title]').val(spotMonth + name + "项目抽查考核");
    }

    //显示窗口
    function showSpotCheckModal() {
        $('#frmSpotCheck').clearAll();
        $('#spotCheckModal').modal();
    }

    //保存数据
    function saveSpotCheck() {
        if (!$('#frmSpotCheck').valid()) {
            return false;
        }
        var data = formSerializeArray($('#frmSpotCheck'));
        $.ajax({
            url: '${pageContext.request.contextPath}/projectSpotCheck/saveSpotCheck',
            data: {formData: JSON.stringify(data)},
            type: 'post',
            dataType: 'json',
            success: function (result) {
                if (result.ret) {
                    $('#spotCheckModal').modal('hide');
                    window.open("${pageContext.request.contextPath}/projectSpotCheck/apply?spotId=" + result.data.id);
                } else {
                    AlertError('错误', result.errmsg);
                }
            }
        })
    }

    //继续填写
    function fillInfo(id) {
        window.open('${pageContext.request.contextPath}/projectSpotCheck/apply?spotId=' + id)
    }

    //查看详情
    function viewDetail(id) {
        window.open('${pageContext.request.contextPath}/projectSpotCheck/detail?spotId=' + id)
    }


</script>


</html>
