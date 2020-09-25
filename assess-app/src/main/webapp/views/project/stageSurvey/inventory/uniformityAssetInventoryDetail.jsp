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
            <div class="page-inner">
                <div class="row mt--2">
                    <!-- 清查内容 start -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        清查内容
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frm_asset_inventory_content" class="form-horizontal">
                                    <table id="tb_List">
                                        <thead>
                                        <tr>
                                            <th style="width: 6%">是否一致</th>
                                            <th style="width: 10%">一致性内容</th>
                                            <th style="width: 10%">登记</th>
                                            <th style="width: 10%">实际</th>
                                            <th style="width: 10%">差异原因</th>
                                            <th style="width: 10%">证明文件</th>
                                            <th style="width: 10%">证明文件附件</th>
                                            <th style="width: 5%">证明人</th>
                                            <th style="width: 8%">调查时间</th>
                                            <th style="width: 6%">确认一致</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${surveyAssetInventoryContentVos}" var="item" varStatus="s">
                                        <tr>
                                            <td>
                                                <label class="form-control input-full">${item.areConsistent}</label>
                                            </td>
                                            <td>
                                                <label class="form-control input-full">${item.inventoryContentName}</label>
                                            </td>
                                            <td>
                                                <label class="form-control input-full">${item.registration}</label>
                                            </td>
                                            <td>
                                                <label class="form-control input-full">${item.actual}</label>
                                            </td>
                                            <c:if test="${item.areConsistent != '一致'}">
                                                <td>
                                                    <label class="form-control input-full">${item.differenceReason}</label>
                                                </td>
                                                <td>
                                                    <label class="form-control input-full">${item.credential}</label>
                                                </td>
                                                <td>
                                                    <div id="_credentialAccessory${item.id}"></div>
                                                    <script type="text/javascript">
                                                        $(function () {
                                                            //清查内容附件加载
                                                            showFileCommon("${item.id}");
                                                        })
                                                    </script>
                                                </td>
                                                <td>
                                                    <label class="form-control input-full">${item.voucher}</label>
                                                </td>
                                                <td>
                                                    <label class="form-control input-full">
                                                        <fmt:formatDate value="${item.surveyTime}"
                                                                        pattern="yyyy-MM-dd"/>
                                                    </label>
                                                </td>
                                                <td>
                                                    <label class="form-control input-full">${item.sureConsistent}</label>
                                                </td>
                                            </c:if>
                                        </tr>
                                        </c:forEach>
                                    </table>
                                </form>
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

    var survey = {};

    survey.frm = $("#frm_asset");

    survey.handleJquery = function (obj) {
        if (obj instanceof jQuery) {
            return obj;
        } else {
            return $(obj.selector);
        }
    };

    survey.fileUpload = function (fieldsName, tableName, id, deleteFlag) {
        FileUtils.uploadFiles({
            target: fieldsName,
            disabledTarget: "btn_submit",
            formData: {
                tableName: tableName,
                tableId: id,
                fieldsName: fieldsName,
                // projectId: id
            },
            deleteFlag: deleteFlag
        });
    };

    survey.showFile = function (fieldsName, tableName, id, deleteFlag) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                tableName: tableName,
                tableId: id,
                fieldsName: fieldsName,
                // projectId: id
            },
            deleteFlag: deleteFlag
        })
    };

    survey.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    $(function () {
        var frm = $("#frm_asset");
        //附件
        var arr = ["checkOriginalFile", "paymentStatusFile", "networkFindFile", AssessUploadKey.INVENTORY_PAYMENT_STATUS, AssessUploadKey.INVENTORY_CHECK_ORIGINAL];

        $.each(arr, function (i, n) {
            survey.showFile(n, AssessDBKey.SurveyAssetInventory, '${surveyAssetInventory.id}', false);
        });
    });

    //显示附件通用
    function showFileCommon(tableId) {
        survey.showFile("credentialAccessory" + tableId, AssessDBKey.SurveyAssetInventoryContent, tableId, false);
    }

</script>
</html>

