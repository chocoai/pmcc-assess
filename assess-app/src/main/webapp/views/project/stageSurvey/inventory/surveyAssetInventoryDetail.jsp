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
                    <!-- 填写表单 start -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        ${masterName} 清查业务
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frm_asset" class="form-horizontal">
                                    <input type="hidden" name="id" value="${surveyAssetInventory.id}">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">查看原件<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full"> ${surveyAssetInventory.bisCheckOriginal eq true? '是':'否'}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <c:if test="${surveyAssetInventory.bisCheckOriginal}">
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 col-form-label">
                                                        核对日期<span class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"><fmt:formatDate
                                                                value="${surveyAssetInventory.checkDate}"
                                                                pattern="yyyy-MM-dd"/></label>

                                                    </div>
                                                    <label class="col-sm-1 control-label">
                                                        说明
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full">${surveyAssetInventory.remark}</label>
                                                    </div>
                                                    <label class="col-sm-1 control-label">
                                                        证明文件
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <div id="_checkOriginalFile"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>

                                    <c:if test="${! surveyAssetInventory.bisCheckOriginal}">
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 col-form-label">
                                                        查看方法
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full">${surveyAssetInventory.findMethodName}</label>
                                                    </div>
                                                    <label class="col-sm-1 col-form-label">
                                                        查看结果附件</label>
                                                    <div class="col-sm-3">
                                                        <div id="_networkFindFile"></div>
                                                    </div>
                                                    <label class="col-sm-1 col-form-label">
                                                        查看说明
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full">${surveyAssetInventory.networkRemark}</label>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>


                                    <c:if test="${!empty surveyAssetInventory.networkAddress}">
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 control-label">
                                                        查询的地址
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full">${surveyAssetInventory.networkAddress}</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>

                                    <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                        color="#6f5499" size="10"/>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    分割限制<span class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${surveyAssetInventory.segmentationLimit}</label>
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    影响对象
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${surveyAssetInventory.affectedName}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <c:forEach items="${surveyAssetInventory.influenceFactorRemarkList}" var="item">
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 col-form-label">
                                                        影响要素
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full">${item.key}</label>
                                                    </div>
                                                    <label class="col-sm-1 col-form-label">
                                                        说明
                                                    </label>
                                                    <div class="col-sm-7">
                                                        <label class="form-control input-full">${item.value}</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>






                                </form>
                            </div>
                        </div>
                    </div>
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


                    <!-- 税费、工程、物管欠款调查 start -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        税费、工程、物管欠款调查
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="card-body">
                                <form id="taxesPaymentSurvey" class="form-horizontal">

                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    缴纳情况
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${surveyAssetInventory.paymentStatus}
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="paymentItem">
                                    </div>
                                    <div class="row form-group" id="showUploadFile" style="display: none">
                                        <div class="col-md-12">
                                            <div class="form-inline  x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    附件
                                                </label>
                                                <div class="col-sm-11">
                                                    <div id="_paymentStatusFile"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <!-- 损坏调查表 start -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        损坏调查表
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="card-body">
                                <form id="damageSurvey" class="form-horizontal">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    区位是否损坏
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${surveyAssetInventory.rimIsNormal}
                                                    </label>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                    <div class="zoneBit">

                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    实物状况是否损坏
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${surveyAssetInventory.entityIsDamage}
                                                    </label>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                    <div class="entity">

                                    </div>
                                    <c:if test="${projectInfo.projectCategoryId == houseLand}">
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 col-form-label">
                                                        影响评估的其他事项
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <div class="btn btn-xs btn-success"
                                                             onclick="appendHTML('otherProjectName','otherProjectItem','otherProject',this)">
                                                            <i class="fa fa-plus"></i></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="otherProject">

                                        </div>
                                    </c:if>
                                </form>
                            </div>
                        </div>
                    </div>

                    <!-- 转让限制 start -->
                    <c:if test="${projectInfo.projectCategoryId != houseLand}">
                        <div class="col-md-12">
                            <div class="card full-height">
                                <div class="card-header collapse-link">
                                    <div class="card-head-row">
                                        <div class="card-title">
                                            转让限制
                                        </div>
                                        <div class="card-tools">
                                            <button class="btn  btn-link btn-primary btn-xs"><span
                                                    class="fa fa-angle-down"></span>
                                            </button>
                                        </div>
                                    </div>
                                </div>

                                <div class="card-body">
                                    <form class="form-horizontal">
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1  col-form-label">
                                                        是否有转让限制
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full" id="bisLimit">
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-group showLimit">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1  col-form-label">
                                                        转让限制
                                                    </label>
                                                    <div class="col-sm-11">
                                                        <label class="form-control input-full">${surveyAssetInventory.transferLimit}
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:if>
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
        showLimit();


        if ("${surveyAssetInventory}") {
            showOther();

            writeHTMLData('zoneProjectName', 'zoneProjectItem', 'zoneBit', ${surveyAssetInventory.zoneDamage});
            writeHTMLData('entityProjectName', 'entityProjectItem', 'entity', ${surveyAssetInventory.entityDamage});
            writeHTMLData('otherProjectName', 'otherProjectItem', 'otherProject', ${surveyAssetInventory.otherProject});
            writePaymentHTMLData(${surveyAssetInventory.paymentContent});
        }
    });

    //显示附件通用
    function showFileCommon(tableId) {
        survey.showFile("credentialAccessory" + tableId, AssessDBKey.SurveyAssetInventoryContent, tableId, false);
    }

    function showOther() {
        if ("${surveyAssetInventory.segmentationLimit}" == "可分") {
            $(".showCertificate").show();
            $("#showUse").show();
        } else {
            $(".showCertificate").hide();
            $("#showUse").hide();

        }
        if ("${surveyAssetInventory.paymentStatus}" == "不正常") {
            $("#showUploadFile").show();
        } else {
            $("#showUploadFile").hide();
        }
    }

    function showLimit() {
        if ("${surveyAssetInventory.transferLimit}") {
            $("#bisLimit").text("是");
            $(".showLimit").show();
        } else {
            $("#bisLimit").text("否");
            $(".showLimit").hide();
        }
    }

    function writeHTMLData(projectName, projectItem, item, json) {
        $("." + item).empty();
        var jsonarray = eval(json);
        $.each(jsonarray, function (i, n) {
            var html = "<div class='row form-group' >";
            html += " <div class='col-md-12'>";
            html += "<div class='form-inline x-valid'>";

            html += "<label class='col-sm-1 control-label'>" + "项目" + "</label>";
            html += "<div class='col-sm-3'>";
            html += "<label class='form-control input-full'>" + n[projectName];
            html += "</label>";
            html += "</div>";

            html += "<label class='col-sm-1 control-label'>" + "明细" + "</label>";
            html += "<div class='col-sm-3'>";
            html += "<label class='form-control input-full'>" + n[projectItem];
            html += "</label>";
            html += "</div>";

            html += "</div>";
            html += "</div>";
            html += "</div>";
            $("." + item).append(html);
        })
    }

    function writePaymentHTMLData(json) {
        $(".paymentItem").empty();
        var jsonarray = eval(json);
        $.each(jsonarray, function (i, n) {
            var html = "<div class='row form-group' >";
            html += " <div class='col-md-12'>";
            html += "<div class='form-inline x-valid'>";

            html += "<label class='col-sm-1 control-label'>" + "项目" + "</label>";
            html += "<div class='col-sm-3'>";
            html += "<label class='form-control input-full'>" + n["projectName"];
            html += "</label>";
            html += "</div>";

            html += "<label class='col-sm-1 control-label'>" + "类型" + "</label>";
            html += "<div class='col-sm-3'>";
            html += "<label class='form-control input-full'>" + n["remark"];
            html += "</label>";
            html += "</div>";

            html += "<label class='col-sm-1 control-label'>" + "金额" + "</label>";
            html += "<div class='col-sm-2'>";
            html += "<label class='form-control input-full'>" + n["money"];
            html += "</div>";


            html += "</div>";
            html += "</div>";
            html += "</div>";
            $(".paymentItem").append(html);
        })
    }

</script>
</html>

