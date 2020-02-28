<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
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
                                        <label for="queryName" class="col-md-1 col-form-label">客户名称</label>
                                        <div class="col-md-3 p-0">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="客户名称" id="queryName" name="queryName"
                                                   class="form-control input-full">
                                        </div>
                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="loadCustomerFieldList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                        <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                                data-toggle="modal" onclick="addCustomerField()"
                                                href="#modalCustomerField">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                            新增
                                        </button>
                                    </div>
                                </form>
                                <table class="table table-bordered" id="tbList">
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
<!-- start: org modal -->
<div id="modalCustomerField" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">客户字段</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form id='frm' class='form-horizontal'>
                    <input type='hidden' id='id' name='id' value="0">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                        客户名称<span class="symbol required"></span>
                                    </label>
                                    <div class="col-md-10 col-sm-10 col-xs-12 input-group">
                                        <div class="input-group">
                                            <input type="hidden" name="customerId">
                                            <input type="text" readonly="readonly" required="required"
                                                   placeholder="单位" class="form-control" name="customerName"
                                                   onclick="selectCustomer(this)">
                                            <span class="input-group-btn">
                                                    <button type="button" class="btn btn-info docs-tooltip"
                                                            data-toggle="tooltip"
                                                            data-original-title="选择"
                                                            onclick="selectCustomer(this)">
                                                         <i class="fa fa-search"></i>
                                                    </button>
                                                </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                        业务类型
                                    </label>
                                    <div class="col-md-10 col-sm-10 col-xs-12 input-group">
                                        <button type="button" class="btn btn-sm btn-success" onclick="appendHTML('业务类型','businessType',this)"><span class="btn-label"><i class="fa fa-plus"></i></span>添加业务类型</button>
                                    </div>
                                </div>
                            </div>
                            <div style="margin-bottom: 8px;" class="businessType">
                            </div>
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-sm-2 control-label">
                                        评估类型
                                    </label>
                                    <div class="col-md-10 col-sm-10 col-xs-12 input-group">
                                        <button type="button" class="btn btn-sm btn-success" onclick="appendHTML('评估类型','assessType',this)"><span class="btn-label"><i class="fa fa-plus"></i></span>添加评估类型</button>
                                    </div>
                                </div>
                            </div>
                            <div style="margin-bottom: 8px;" class="assessType"></div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="saveCustomerField()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<script type="text/javascript" src="/pmcc-crm/js/crm-customer-utils.js?v=1.0"></script>
<script type="application/javascript">
    $(function () {
        loadCustomerFieldList();
    })

    //加载列表数据
    function loadCustomerFieldList() {
        var cols = [];
        cols.push({field: 'customerName', title: '客户名称'});
        cols.push({field: 'businessType', title: '业务类型'});
        cols.push({field: 'assessType', title: '评估类型'});
        cols.push({
            field: 'opt', title: '操作', formatter: function (value, row, index) {
                var str = '<button onclick="getAndInit(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button onclick="delCustomerField(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
                return str;
            }
        });
        $("#tbList").bootstrapTable('destroy');
        TableInit("tbList", "${pageContext.request.contextPath}/dataCustomerField/getDataCustomerFieldList", cols, {
            queryName: $("#queryName").val()
        }, {
            showColumns: false,
            showRefresh: false,
            uniqueId: "id",
            search: false
        });
    }

    //新增
    function addCustomerField() {
        $("#frm").clearAll();
        $("#id").val("0");
        $(".businessType").empty();
        var lableValue = "业务类型";
        var html = "<div class='form-group' style='margin-top:8px;'>";
        html += "<label class='col-md-2 col-sm-2 col-xs-12 control-label'>" + lableValue + "</label>";
        html += "<div class='col-md-10 col-sm-10 col-xs-12 input-group'>";
        html += "<input type='text' required class='form-control'" + "name='" + 'businessType' + "'" + ">";
        html += "<span class='input-group-btn'>" + "<button class='btn btn-warning' type='button'  onclick='cleanHTMLData(this)'><i class=\"fa fa-minus\"></i></button></span>";
        html += "</div>";
        html += "</div>";
        $(".businessType").append(html);

        $(".assessType").empty();
        var lableValue = "评估类型";
        var html = "<div class='form-group' style='margin-top:8px;'>";
        html += "<label class='col-md-2 col-sm-2 col-xs-12 control-label'>" + lableValue + "</label>";
        html += "<div class='col-md-10 col-sm-10 col-xs-12 input-group'>";
        html += "<input type='text' required class='form-control'" + "name='" + 'assessType' + "'" + ">";
        html += "<span class='input-group-btn'>" + "<button class='btn btn-warning' type='button' onclick='cleanHTMLData(this)'><i class=\"fa fa-minus\"></i></button></span>";
        html += "</div>";
        html += "</div>";
        $(".assessType").append(html);
    }

    //删除
    function delCustomerField(id) {
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/dataCustomerField/deleteCustomerField",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        notifySuccess("成功", "删除数据成功");
                        $('#tbList').bootstrapTable("refresh");
                    }
                    else {
                        AlertError("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })

        });
    }

    //保存
    function saveCustomerField() {
        if ($("#frm").valid()) {
            Loading.progressShow();
            var data = formParams("frm");
            $.ajax({
                url: "${pageContext.request.contextPath}/dataCustomerField/saveCustomerField",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        $('#tbList').bootstrapTable("refresh");
                        $('#modalCustomerField').modal('hide');
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        }
    }

    function appendHTML(name, item, this_) {
        var lableValue = name;
        var html = "<div class='form-group' style='margin-top:8px;'>";
        html += "<label class='col-md-2 col-sm-2 col-xs-12 control-label'>" + lableValue + "</label>";
        html += "<div class='col-md-10 col-sm-10 col-xs-12 input-group'>";
        html += "<input type='text' required class='form-control'" + "name='" + item + "'" + ">";
        html += "<span class='input-group-btn'>" + "<button class='btn btn-warning' type='button' onclick='cleanHTMLData(this)'><i class=\"fa fa-minus\"></i></button></span>";
        html += "</div>";
        html += "</div>";

        var a = "input[name=" + "'" + item + "'" + "]";

        $("." + item).append(html);
    }

    function cleanHTMLData(item) {
        var value = "";
        $(item).closest('.form-group').empty();
    }

    function writeHTMLData(str, name, item) {
        $("." + item).empty();
        var strs = str.split(",");
        var length = strs.length;
        var lableValue = name;
        for (var i = 0; i < length; i++) {
            var html = "<div class='form-group' style='margin-top:8px;'>";
            html += "<label class='col-md-2 col-sm-2 col-xs-12 control-label'>" + lableValue + "</label>";
            html += "<div class='col-md-10 col-sm-10 col-xs-12 input-group'>";
            html += "<input type='text' required class='form-control'" + "name='" + item + "' value='" + strs[i] + "'>";
            html += "<span class='input-group-btn'>" + "<button class='btn btn-warning' type='button'  onclick='cleanHTMLData(this)'><i class=\"fa fa-minus\"></i></button></span>";
            html += "</div>";
            html += "</div>";
            $("." + item).append(html);
        }
    }

    function init(item) {
        $("#frm").clearAll();
        $("#frm").initForm(item);
        if (item.businessType) {
            writeHTMLData(item.businessType, "业务类型", "businessType");
        }
        if (item.assessType) {
            writeHTMLData(item.assessType, "评估类型", "assessType");
        }
    }

    function getAndInit(id) {
        $.ajax({
            url: getContextPath() + "/dataCustomerField/getCustomerFieldById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        init(result.data);
                    } else {
                        init({});
                    }
                    $('#modalCustomerField').modal("show");
                }
            },
            error: function (result) {
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    function selectCustomer(this_) {
        //选择客户
        crmCustomer.select({
            multi: false,//是否允许多选
            companyId: '${companyId}',
            onSelected: function (nodes) {
                $(this_).closest('.input-group').find("input[name='customerId']").val(nodes[0].id);
                $(this_).closest('.input-group').find("input[name='customerName']").val(nodes[0].name);
                $.ajax({
                    type: "get",
                    url: "${pageContext.request.contextPath}/initiateCrmCustomer/getCrmCustomerDto",
                    data: "crmId=" + nodes[0].id,
                    success: function (msg) {

                    }
                });
            }
        });
    };
</script>

</body>
</html>


