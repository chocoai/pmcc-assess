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
            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>${areaGroup.areaName}</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="master" class="form-horizontal">
                        <table class="table">
                            <thead>
                            <tr>
                                <th class="hidden-xs">估价对象</th>
                                <th class="hidden-xs">假定未设立法定优先受偿权总价(元)</th>
                                <th class="hidden-xs">已抵押担保的债权数额总价(元)</th>
                                <th class="hidden-xs">拖欠的建设工程价款总价(元)</th>
                                <th class="hidden-xs">其它法定优先受偿款总价(元)</th>
                                <th class="hidden-xs">估价师知悉的法定优先受偿款总价(元)</th>
                                <th class="hidden-xs">抵押价值总价(元)</th>
                            </tr>
                            </thead>
                            <tbody id="tbody_data_section">

                            </tbody>
                        </table>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                    附件
                                </label>
                                <div class="col-md-11 col-sm-11 col-xs-12">
                                    <input id="apply_file" type="file" multiple="false">
                                    <div id="_apply_file">
                                    </div>
                                </div>
                            </div>
                        </div>
                            <input type="hidden" name="id" value="${master.id}">
                    </form>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-5 col-sm-offset-5">
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
        var formData = getFormData() ;

        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(formData));
        }
        else {
            submitToServer(JSON.stringify(formData));
        }
    }

    function getTotal(id) {
        var notSetUpTotalPriceEle = "notSetUpTotalPrice_"+id;
        var mortgagedTotalPriceEle = "mortgagedTotalPrice_"+id;
        var owedTotalPriceEle = "owedTotalPrice_"+id;
        var otherTotalPriceEle = "otherTotalPrice_"+id;
        var knowTotalPriceEle = "knowTotalPrice_"+id;
        var mortgageTotalPriceEle = "mortgageTotalPrice_"+id;


        var notSetUpTotalPrice = $("#master").find('[name="'+ notSetUpTotalPriceEle +'"]').val();
        var mortgagedTotalPrice = $("#master").find('[name="'+ mortgagedTotalPriceEle +'"]').val();
        var owedTotalPrice = $("#master").find('[name="'+ owedTotalPriceEle +'"]').val();
        var otherTotalPrice = $("#master").find('[name="'+ otherTotalPriceEle +'"]').val();
        var knowTotalPrice = 0;
        if (mortgagedTotalPrice && owedTotalPrice && otherTotalPrice) {
            knowTotalPrice = Number(mortgagedTotalPrice) + Number(owedTotalPrice) + Number(otherTotalPrice);
            $("#master").find('[name="'+ knowTotalPriceEle +'"]').val(Number(knowTotalPrice).toFixed(2));
        }
        if (notSetUpTotalPrice && mortgagedTotalPrice && owedTotalPrice && otherTotalPrice) {
            var mortgageTotalPrice = Number(notSetUpTotalPrice) - Number(knowTotalPrice);
            $("#master").find('[name="'+ mortgageTotalPriceEle +'"]').val(Number(mortgageTotalPrice).toFixed(2));
        }
    }

    function getItemHtml() {
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
                        html += item.judgeObjectName;
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += "<input type='text' onblur='getTotal("+item.id+");'  name='notSetUpTotalPrice_" + item.id + "' value='" + Number(item.notSetUpTotalPrice).toFixed(2) + "' class='form-control'>";
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += "<input type='text' onblur='getTotal("+item.id+");' name='mortgagedTotalPrice_" + item.id + "' value='" + Number(item.mortgagedTotalPrice).toFixed(2) + "' class='form-control'>";
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += "<input type='text' onblur='getTotal("+item.id+");' name='owedTotalPrice_" + item.id + "' value='" + Number(item.owedTotalPrice).toFixed(2) + "' class='form-control'>";
                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += "<input type='text' onblur='getTotal("+item.id+");'  name='otherTotalPrice_" + item.id + "' value='" + Number(item.otherTotalPrice).toFixed(2) + "' class='form-control'>";
                        html += "</td>";

                        html += "</td>";
                        html += "<td class='hidden-xs'>";
                        html += "<input type='text' readonly name='knowTotalPrice_" + item.id + "' value='" + Number(item.knowTotalPrice).toFixed(2) + "' class='form-control'>";
                        html += "</td>";

                        html += "<td class='hidden-xs'>";
                        html += "<div class='x-valid'>";
                        html += "<input type='text' readonly  name='mortgageTotalPrice_" + item.id + "' value='" + Number(item.mortgageTotalPrice).toFixed(2) + "' class='form-control'>";
                        html += "</div>";
                        html += "</td>";
                        html += "</tr>";
                    });
                    $("#tbody_data_section").append(html);
                }
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
            item.notSetUpTotalPrice = $(this).find('[name^=notSetUpTotalPrice]').val();
            item.mortgagedTotalPrice = $(this).find('[name^=mortgagedTotalPrice]').val();
            item.owedTotalPrice = $(this).find('[name^=owedTotalPrice]').val();
            item.otherTotalPrice = $(this).find('[name^=otherTotalPrice]').val();
            item.knowTotalPrice = $(this).find('[name^=knowTotalPrice]').val();
            item.mortgageTotalPrice = $(this).find('[name^=mortgageTotalPrice]').val();
            data.itemList.push(item);
        })
        return data;
    }
</script>

</html>

