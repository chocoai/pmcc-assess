<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="row">
                <div class="col-xs-12">
                    <div class="x_panel">
                        <div class="x_title collapse-link">
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                            </ul>
                            <h2>${baseViewDto.currentMenu.name}</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <form class="form-horizontal">
                                <div class="form-group ">
                                    <div>
                                        <label class="col-sm-1 control-label">
                                            客户名称
                                        </label>
                                        <div class="col-sm-2">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="客户名称" id="queryName" name="queryName"
                                                   class="form-control">
                                        </div>
                                    </div>
                                    <div class="col-sm-3">
                                        <button type="button" class="btn btn-primary"
                                                onclick="loadCustomerFieldList()">
                                            查询
                                        </button>
                                        <button type="button" class="btn btn-success" data-toggle="modal"
                                                href="#modalCustomerField"
                                                onclick="addCustomerField();"> 新增
                                        </button>
                                    </div>
                                </div>
                            </form>
                            <table id="tbList" class="table table-bordered"></table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <input type='hidden' id='CustomerFieldId' name='CustomerFieldId' value="0">
    <!-- end: MAIN CONTAINER -->
</div>

</body>
<%@include file="/views/share/main_footer.jsp" %>

<!-- start: org modal -->
<div id="modalCustomerField" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class='modal-header'>
                <button type='button' class='close' data-dismiss='modal' aria-label='Close'><span
                        aria-hidden='true'>&times;</span></button>
                <h3 class='modal-title'>客户字段</h3></div>
            <form id='frm' class='form-horizontal'>
                <input type='hidden' id='id' name='id' value="0">
                <div class='modal-body'>
                    <div class='row'>
                        <div class='col-md-12'>
                            <div class='panel-body'>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            客户名称<span class="symbol required"></span>
                                        </label>
                                        <%--<div class="col-md-10 col-sm-10 col-xs-12 input-group">
                                            <input name='customerName' required class='form-control' required>
                                        </div>--%>
                                        <div class="col-md-10 col-sm-10 col-xs-12 input-group">
                                            <div class="input-group">
                                                <input type="hidden" name="customerId">
                                                <input type="text" readonly="readonly" required="required"
                                                       placeholder="单位" class="form-control" name="customerName"
                                                       onclick="selectCustomer(this)">
                                                <span class="input-group-btn">
                                <button type="button" class="btn btn-default docs-tooltip"
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
                                            <button class="btn btn-xs btn-success"
                                                    onclick="appendHTML('业务类型','businessType',this)"><i
                                                    class="fa fa-plus"></i></button>
                                        </div>
                                    </div>
                                </div>

                                <div style="margin-bottom: 8px;" class="businessType">
                                    <div class="form-group" style=" margin-top: 8px;">
                                        <div class="x-valid">

                                            <label class="col-md-2 control-label">业务类型</label>
                                            <div class="col-md-10 col-sm-10 col-xs-12 input-group">
                                                <div class="input-group">
                                                    <input class="form-control" name="businessType"
                                                           required="required"
                                                           type="text">
                                                    <span class="input-group-btn">
                                                         <input type="button" class="btn btn-warning" value="X"
                                                                onclick="cleanHTMLData(this)"></span>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            评估类型
                                        </label>
                                        <div class="col-md-10 col-sm-10 col-xs-12 input-group">
                                            <button class="btn btn-xs btn-success"
                                                    onclick="appendHTML('评估类型','assessType',this)"><i
                                                    class="fa fa-plus"></i></button>
                                        </div>
                                    </div>
                                </div>
                                <div style="margin-bottom: 8px;" class="assessType">
                                    <div class="form-group" style=" margin-top: 8px;">
                                        <div class="x-valid">
                                            <label class="col-md-2 control-label">评估类型</label>
                                            <div class="col-md-10 col-sm-10 col-xs-12 input-group ">
                                                <div class="input-group">
                                                    <input class="form-control" name="assessType" required="required"
                                                           type="text">
                                                    <span class="input-group-btn">
                                                           <input type="button" class="btn btn-warning" value="X"
                                                                  onclick="cleanHTMLData(this)"></span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class='modal-footer'>
                <button type='button' data-dismiss='modal' class='btn btn-default'>取消</button>
                <button type='button' class='btn btn-primary save_custom_model' onclick="saveCustomerField();"
                        id='btn_save_bid_organization_agency'>保存
                </button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/pmcc-crm/js/crm-customer-utils.js"></script>
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
                var str = "";
                str += "<a style='margin-left: 5px;' data-placement='top' data-original-title='编辑' class='btn btn-xs btn-success tooltips' onclick='getAndInit(" + row.id + ")'   ><i class='fa fa-edit fa-white'></i></a>";
                str += "<a style='margin-left: 5px;' data-placement='top' data-original-title='删除'  class='btn btn-xs btn-warning tooltips'  onclick='delCustomerField(" + row.id + ")'   ><i class='fa fa-minus fa-white'></i></a>";
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
        html += "<span class='input-group-btn'>" + "<input class='btn btn-warning' type='button' value='X' onclick='cleanHTMLData(this)'>" + "</span>";
        html += "</div>";
        html += "</div>";
        $(".businessType").append(html);

        $(".assessType").empty();
        var lableValue = "评估类型";
        var html = "<div class='form-group' style='margin-top:8px;'>";
        html += "<label class='col-md-2 col-sm-2 col-xs-12 control-label'>" + lableValue + "</label>";
        html += "<div class='col-md-10 col-sm-10 col-xs-12 input-group'>";
        html += "<input type='text' required class='form-control'" + "name='" + 'assessType' + "'" + ">";
        html += "<span class='input-group-btn'>" + "<input class='btn btn-warning' type='button' value='X' onclick='cleanHTMLData(this)'>" + "</span>";
        html += "</div>";
        html += "</div>";
        $(".assessType").append(html);

    }

    //删除
    function delCustomerField(id) {
        bootbox.confirm("确认要删除么？", function (result) {
            if (result) {
                Loading.progressShow();
                $.ajax({
                    url: "${pageContext.request.contextPath}/dataCustomerField/deleteCustomerField",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        Loading.progressHide();
                        if (result.ret) {
                            toastr.success('删除成功');
                            $('#tbList').bootstrapTable("refresh");
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
            }
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
                        toastr.success('保存成功');
                        $('#tbList').bootstrapTable("refresh");
                        $('#modalCustomerField').modal('hide');
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
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
        html += "<span class='input-group-btn'>" + "<input class='btn btn-warning' type='button' value='X' onclick='cleanHTMLData(this)'>" + "</span>";
        html += "</div>";
        html += "</div>";

        var a = "input[name=" + "'" + item + "'" + "]";

        $("." + item).append(html);
    }

    function cleanHTMLData(item) {
        var value = "";
        $(item).parent().prev().parent().parent().empty();
    }

    function writeHTMLData(str, name, item) {
        $("." + item).empty();
        var strs = str.split(",");
        var length = strs.length;
        var lableValue = name;
        for (var i = 0; i < length; i++) {
            console.log("i:" + i);
            var html = "<div class='form-group' style='margin-top:8px;'>";
            html += "<label class='col-md-2 col-sm-2 col-xs-12 control-label'>" + lableValue + "</label>";
            html += "<div class='col-md-10 col-sm-10 col-xs-12 input-group'>";
            html += "<input type='text' required class='form-control'" + "name='" + item + "' value='" + strs[i] + "'>";
            html += "<span class='input-group-btn'>" + "<input class='btn btn-warning' type='button' value='X' onclick='cleanHTMLData(this)'>" + "</span>";
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
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    function selectCustomer(this_) {
        //选择客户
        crmCustomer.select({
            multi: false,//是否允许多选
            companyId:3,
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


