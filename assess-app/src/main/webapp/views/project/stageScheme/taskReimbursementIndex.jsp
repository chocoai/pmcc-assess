<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>


<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <%@include file="/views/share/project/projectInfoSimple.jsp" %>
                    <%@include file="/views/share/project/projectPlanDetails.jsp" %>

                    <!-- 填写表单 start -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        ${areaGroup.areaName}

                                        <button onclick="repeatSchemeReimbursementInit(this);"
                                                class="btn btn-success btn-sm" type="button">重新初始化
                                        </button>
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="master" class="form-horizontal">
                                    <input type="hidden" name="id" value="${master.id}">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <table class="table">
                                                <thead>
                                                <tr>
                                                    <th class="hidden-xs">估价对象</th>
                                                    <th class="hidden-xs">已抵押担保的债权数额总价(元)</th>
                                                    <th class="hidden-xs">拖欠的建设工程价款总价(元)</th>
                                                    <th class="hidden-xs">其它法定优先受偿款总价(元)</th>
                                                    <th class="hidden-xs">估价师知悉的法定优先受偿款总价(元)</th>
                                                    <th class="hidden-xs">他权明细</th>
                                                </tr>
                                                </thead>
                                                <tbody id="tbody_data_section">

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-md-1 control-label">
                                                    附件
                                                </label>
                                                <div class="col-md-11">
                                                    <input id="apply_file" type="file" multiple="false">
                                                    <div id="_apply_file">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>

                    <%@include file="/views/share/form_apply.jsp" %>
                    <%@include file="/views/share/form_log.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>

</body>
<div id="divBoxRightItem" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">他权信息</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <table class="table table-bordered" id="RightItemList">
                                    <!-- cerare document add ajax data-->
                                </table>

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
<script type="application/javascript">
    $(function () {
        getItemHtml();
        FileUtils.uploadFiles({
            target: "apply_file",
            formData: {
                tableName: AssessDBKey.SchemeReimbursement,
                tableId: "${master.id}",
                fieldsName: "apply"
            },
            deleteFlag: true
        });
        loadFiles();
    })

    function loadFiles() {
        FileUtils.getFileShows({
            target: "apply_file",
            formData: {
                tableName: AssessDBKey.SchemeReimbursement,
                tableId: "${master.id}",
                fieldsName: "apply"
            },
            deleteFlag: true
        })
    }

    function submit() {
        if (!$("#master").valid()) {
            return false;
        }
        var formData = getFormData();

        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(formData));
        }
        else {
            submitToServer(JSON.stringify(formData));
        }
    }

    function getTotal(id) {
        var mortgagedTotalPriceEle = "mortgagedTotalPrice_" + id;
        var owedTotalPriceEle = "owedTotalPrice_" + id;
        var otherTotalPriceEle = "otherTotalPrice_" + id;
        var knowTotalPriceEle = "knowTotalPrice_" + id;


        var mortgagedTotalPrice = $("#master").find('[name="' + mortgagedTotalPriceEle + '"]').val();
        var owedTotalPrice = $("#master").find('[name="' + owedTotalPriceEle + '"]').val();
        var otherTotalPrice = $("#master").find('[name="' + otherTotalPriceEle + '"]').val();
        var knowTotalPrice = 0;
        if (mortgagedTotalPrice && owedTotalPrice && otherTotalPrice) {
            knowTotalPrice = Number(mortgagedTotalPrice) + Number(owedTotalPrice) + Number(otherTotalPrice);
            $("#master").find('[name="' + knowTotalPriceEle + '"]').val(Number(knowTotalPrice).toFixed(2));
        }

    }

    function repeatSchemeReimbursementInit() {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeReimbursement/repeatSchemeReimbursementInit",
            data: {
                id: "${master.id}"
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    getItemHtml() ;
                    notifySuccess("提示", "重新初始化成功!");
                }else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                if (result.errmsg) {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                } else {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                }
            }
        });
    }

    function getItemHtml() {
        if (! "${master.id}"){
            AlertError("提示","法定优先受偿款没有初始化,进入页面后点击初始化按钮！");
            return false ;
        }
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeReimbursement/getSchemeReimbursementList",
            data: {
                masterId: "${master.id}"
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                $("#tbody_data_section").empty();
                if (result.ret) {
                    var html = "";
                    $.each(result.data, function (i, item) {
                        html += "<tr>";
                        html += "<td class='hidden-xs'>";
                        html += '<input type="hidden" name="id" value="' + item.id + '">';
                        html += item.name;
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += "<input type='text' onblur='getTotal(" + item.id + ");' name='mortgagedTotalPrice_" + item.id + "' value='" + Number(item.mortgagedTotalPrice).toFixed(2) + "' class='form-control'>";
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += "<input type='text' onblur='getTotal(" + item.id + ");' name='owedTotalPrice_" + item.id + "' value='" + Number(item.owedTotalPrice).toFixed(2) + "' class='form-control'>";
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += "<input type='text' onblur='getTotal(" + item.id + ");'  name='otherTotalPrice_" + item.id + "' value='" + Number(item.otherTotalPrice).toFixed(2) + "' class='form-control'>";
                        html += "</td>";

                        html += "<td class='hidden-xs'>";
                        html += "<input type='text' readonly name='knowTotalPrice_" + item.id + "' value='" + Number(item.knowTotalPrice).toFixed(2) + "' class='form-control'>";
                        html += "</td>";

                        html += "<td class='hidden-xs'>";
                        html += '<button type="button" class="btn btn-xs btn-info tooltips"  data-placement="top" data-original-title="查看" onclick="checkRightItem(' + item.inventoryRightRecordId + ')"><i class="fa fa-search fa-white"></i></button>';
                        html += "</td>";

                        html += "</tr>";
                    });
                    $("#tbody_data_section").append(html);
                }
            }
        });
    }

    function checkRightItem(groupId) {
        if (!groupId) {
            alert("未关联他权")
            return;
        }
        loadAssetRightList(groupId);
        $('#divBoxRightItem').modal("show");
    }

    //加载 他项权利列表
    function loadAssetRightList(groupId) {
        var cols = [];
        cols.push({field: 'categoryName', title: '类别'});
        cols.push({field: 'remark', title: '他项权利描述', width: '40%'});
        cols.push({field: 'number', title: '他权证编号'});
        cols.push({field: 'obligor', title: '义务人'});
        cols.push({field: 'obligee', title: '权利人'});
        cols.push({field: 'registerArea', title: '登记面积'});
        cols.push({field: 'rightRank', title: '他权级次'});
        $("#RightItemList").bootstrapTable('destroy');
        TableInit("RightItemList", "${pageContext.request.contextPath}/surveyAssetRightItem/getBootstrapTableVo", cols, {
            projectId: '${projectPlanDetails.projectId}', groupId: groupId
        }, {
            method: "get",
            showColumns: false,
            showRefresh: false,
            search: false,
            striped: true,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        });
    }

    //获取需要保存的数据
    function getFormData() {
        var data = {};
        data.id = "${master.id}";
        data.itemList = [];
        $("#tbody_data_section").find('tr').each(function () {
            var item = {};
            item.id = $(this).find('[name=id]').val();
            item.mortgagedTotalPrice = $(this).find('[name^=mortgagedTotalPrice]').val();
            item.owedTotalPrice = $(this).find('[name^=owedTotalPrice]').val();
            item.otherTotalPrice = $(this).find('[name^=otherTotalPrice]').val();
            item.knowTotalPrice = $(this).find('[name^=knowTotalPrice]').val();
            data.itemList.push(item);
        })
        return data;
    }
</script>

</html>

