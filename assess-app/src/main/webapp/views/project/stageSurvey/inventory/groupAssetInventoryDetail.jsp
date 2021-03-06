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
                    <c:if test="${not empty viewSpiltEntity}">
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
                                        <input type="hidden" name="id" value="${viewSpiltEntity.id}">
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 col-form-label">查看原件<span
                                                            class="symbol required"></span></label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full"> ${viewSpiltEntity.bisCheckOriginal eq true? '是':'否'}</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <c:if test="${viewSpiltEntity.bisCheckOriginal}">
                                            <div class="row form-group">
                                                <div class="col-md-12">
                                                    <div class="form-inline x-valid">
                                                        <label class="col-sm-1 col-form-label">
                                                            核对日期<span class="symbol required"></span></label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full"><fmt:formatDate
                                                                    value="${viewSpiltEntity.checkDate}"
                                                                    pattern="yyyy-MM-dd"/></label>

                                                        </div>
                                                        <label class="col-sm-1 control-label">
                                                            说明
                                                        </label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full">${viewSpiltEntity.remark}</label>
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

                                        <c:if test="${! viewSpiltEntity.bisCheckOriginal}">
                                            <div class="row form-group">
                                                <div class="col-md-12">
                                                    <div class="form-inline x-valid">
                                                        <label class="col-sm-1 col-form-label">
                                                            查看方法
                                                        </label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full">${viewSpiltEntity.findMethodName}</label>
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
                                                            <label class="form-control input-full">${viewSpiltEntity.networkRemark}</label>

                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>


                                        <c:if test="${!empty viewSpiltEntity.networkAddress}">
                                            <div class="row form-group">
                                                <div class="col-md-12">
                                                    <div class="form-inline x-valid">
                                                        <label class="col-sm-1 control-label">
                                                            查询的地址
                                                        </label>
                                                        <div class="col-sm-3">
                                                            <label class="form-control input-full">${viewSpiltEntity.networkAddress}</label>
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
                                                        <label class="form-control input-full">${viewSpiltEntity.segmentationLimitName}</label>
                                                    </div>
                                                    <label class="col-sm-1 col-form-label">
                                                        影响对象
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full">${viewSpiltEntity.affectedName}</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <c:forEach items="${viewSpiltEntity.influenceFactorRemarkList}" var="item">
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
                    </c:if>


                    <!-- 税费、工程、物管欠款调查 start -->
                    <c:if test="${not empty taxArrearsEntity}">
                        <div class="col-md-12">
                            <div class="card full-height">
                                <div class="card-header collapse-link">
                                    <div class="card-head-row">
                                        <div class="card-title">
                                            税费、工程、物管欠款调查1
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
                                                        <label class="form-control input-full">${taxArrearsEntity.paymentStatus}
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="paymentItem"></div>
                                        <div class="row form-group" id="showUploadFile">
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
                    </c:if>

                    <!-- 损坏调查表 start -->
                    <c:if test="${not empty damageEntity}">
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
                                                        <label class="form-control input-full">${damageEntity.rimIsNormal}
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="zoneBit"></div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 col-form-label">
                                                        实物状况是否损坏
                                                    </label>
                                                    <div class="col-sm-3">
                                                        <label class="form-control input-full">${damageEntity.entityIsDamage}
                                                        </label>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                        <div class="entity"></div>
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline x-valid">
                                                    <label class="col-sm-1 col-form-label">
                                                        影响评估的其他事项
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="otherProject"></div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <!-- 转让限制 start -->
                    <c:if test="${not empty transferEntity}">
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
                                                        <label class="form-control input-full">${transferEntity.transferLimit}
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
    $(function () {
        var frm = $("#frm_asset");
        //附件
        var arr = ["checkOriginalFile",'paymentStatusFile', "networkFindFile", AssessUploadKey.INVENTORY_PAYMENT_STATUS, AssessUploadKey.INVENTORY_CHECK_ORIGINAL];

        $.each(arr, function (i, n) {
            survey.showFile(n, AssessDBKey.SurveyAssetInfoItem, '${assetInfoGroup.id}', false);
        });
        showLimit();

        if ('${viewSpiltEntity}'.length>0) {
            showOther();
        }

        if ('${taxArrearsEntity}'.length>0) {//税费欠缴
            writePaymentHTMLData('${taxArrearsEntity.paymentContent}');
        }

        if ('${damageEntity}'.length>0) {//损坏调查
            writeHTMLData('zoneProjectName', 'zoneProjectItem', 'zoneBit', '${damageEntity.zoneDamage}');
            writeHTMLData('entityProjectName', 'entityProjectItem', 'entity', '${damageEntity.entityDamage}');
            writeHTMLData('otherProjectName', 'otherProjectItem', 'otherProject', '${damageEntity.otherProject}');
        }
    });

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

    function showOther() {
        if ("${viewSpiltEntity.segmentationLimit}" == "可分") {
            $(".showCertificate").show();
            $("#showUse").show();
        } else {
            $(".showCertificate").hide();
            $("#showUse").hide();

        }
    }

    function showLimit() {
        if ("${transferEntity.transferLimit}") {
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

            var fileName = n.fileName;

            if (fileName) {
                html += "<label class='col-sm-1 control-label'>" + "附件" + "</label>";
                html += "<div class='col-sm-2'>";
                html += "<div  id='" + "_" + fileName + "'>" + "</div>";
                html += "</div>";
            }

            html += "</div>";
            html += "</div>";
            html += "</div>";
            $("." + item).append(html);
            console.log(fileName) ;
            if (fileName) {
                survey.showFile(fileName, AssessDBKey.SurveyAssetInfoItem, '${damageEntity.id}' ,false);
            }
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

