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
            <form class="form-horizontal" id="declareApplyForm">
                <input type="hidden" name="planDetailsId" value="${projectPlanDetails.id}">
                <input type="hidden" name="projectId" value="${projectPlanDetails.projectId}">
            </form>
            <!-- 申报各种类型的html视图 -->
            <%@include file="/views/project/stageDeclare/declareApplyModel.jsp" %>
            <!-- 房产证 -->
            <div id="viewDeclareRealtyHouseCert">
                <%@include file="/views/project/stageDeclare/houseDeclarationModel/viewDeclareRealtyHouseCert.jsp" %>
            </div>

            <!-- 土地证 -->
            <div id="viewDeclareRealtyLandCert">
                <%@include file="/views/project/stageDeclare/houseDeclarationModel/viewDeclareRealtyLandCert.jsp" %>
            </div>

            <!-- 不动产证 -->
            <div id="viewDeclareRealtyRealEstateCert">
                <%@include
                        file="/views/project/stageDeclare/houseDeclarationModel/viewDeclareRealtyRealEstateCert.jsp" %>
            </div>

            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <c:choose>
                            <c:when test="${projectPhase.bisUseBox eq false}">
                                <button id="btn_submit" class="btn btn-success" onclick="submit(false);">
                                    直接提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
                                <button id="btn_submit" class="btn btn-primary" onclick="submit(true);">
                                    提交审批<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button id="btn_submit" class="btn btn-success" onclick="submit();">
                                    提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/declare/declare.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/map.position.js"></script>
<input type="file" id="ajaxFileUpload" name="file" style="display: none;">
<script type="text/javascript">
    var config = {
        declare: {
            frm: "declareApplyForm"
        },
        declareRealtyHouseCert: {
            name: "房产证",
            view: "viewDeclareRealtyHouseCert"
        },
        declareRealtyLandCert: {
            name: "土地证",
            view: "viewDeclareRealtyLandCert"
        },
        declareRealtyRealEstateCert: {
            name: "不动产证",
            view: "viewDeclareRealtyRealEstateCert"
        }
    };

    var declareFunObj = {};

    declareFunObj.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    /**
     * @author:  zch
     * 描述:获取申报证书类型
     * @date:2018-10-22
     **/
    declareFunObj.getDeclareType = function (name) {
        var declareType = null;
        $("#" + config.declare.frm + " :checkbox").each(function (j, oo) {
            AssessCommon.getProjectClassifyInfoAsync($(oo).val(), function (data) {
                if (declareFunObj.isEmpty(data)) {
                    if (data.name == name) {
                        declareType = data.id;
                    }
                }
            })
        });
        return declareType;
    };


    declareFunObj.declare = {
        init: function () {
            var num = 6;
            AssessCommon.getProjectClassifyListByFieldName(AssessProjectClassifyKey.singleHousePropertyCertificateType, function (html, data) {
                var resetHtml = "";
                var k = 0;
                $.each(data, function (i, n) {
                    if (i % num == 0) {
                        k++;
                    }
                });
                for (var i = 0; i < k; i++) {
                    resetHtml += "<div class='form-group'>";
                    for (var j = i * num; j < i * num + num; j++) {
                        if (j < data.length) {
                            resetHtml += "<div class='col-sm-2'>";
                            resetHtml += "<span class='checkbox-inline'>";
                            resetHtml += "<input type='checkbox' id='classify" + data[j].id + "' name='other' required='required' value='" + data[j].id + "'" + ">";
                            resetHtml += "<label for='classify" + data[j].id + "'>" + data[j].name + "<label>";
                            resetHtml += "</span>";
                            resetHtml += "</div>";
                        }
                    }
                    resetHtml += "</div>";
                }
                //HTML
                $("#" + config.declare.frm + "HTML").append(resetHtml);
            });
        }
    };
</script>
<script type="application/javascript">
    //提交
    function submit(mustUseBox) {
        //检查是否填写了申报数据
        var rows = $("#" + assessCommonHouse.config.table).bootstrapTable('getData');
        if (rows && rows.length > 0) {
            submitForm(mustUseBox);
            return false;
        }
        rows = $("#" + assessCommonLand.config.table).bootstrapTable('getData');
        if (rows && rows.length > 0) {
            submitForm(mustUseBox);
            return false;
        }
        rows = $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('getData');
        if (rows && rows.length > 0) {
            submitForm(mustUseBox);
            return false;
        }


        toastr.info("请添加相关申报信息");
    }

    //提交表单
    function submitForm(mustUseBox) {
        if ("${processInsId}" != "0") {
            submitEditToServer("");
        }
        else {
            submitToServer("", mustUseBox);
        }
    }

</script>


</html>

