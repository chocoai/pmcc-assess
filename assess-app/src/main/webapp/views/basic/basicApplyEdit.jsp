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

            <%@include file="/views/basic/basicIndexCommon.jsp" %>

            <div class="x_panel">

                <div class="x_content">
                    <div class="col-sm-5 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button id="btn_close" class="btn btn-warning" onclick="closeBasicApp();">
                            关闭流程<i style="margin-left: 10px" class="fa fa-close"></i>
                        </button>
                        <button id="btn_submit" class="btn btn-primary" onclick="saveform();">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </div>

                </div>
            </div>
            <c:if test="${processInsId ne '0'}">
                <%@include file="/views/share/form_log.jsp" %>
                <form id="frm_approval">
                    <%@include file="/views/share/ApprovalVariable.jsp" %>
                </form>
            </c:if>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    var basicUnit = "${basicUnit}";
    var basicApply = "${basicApply}";
    var basicEstate = "${basicEstate}";
    var basicHouse = "${basicHouse}";
    var basicBuildingMain = "${basicBuildingMain}";

    basicIndexCommon.estateShow();

    if (basicIndexCommon.isNotBlank(basicApply)) {
        var industryA = "${basicApply.industry}";
        if (basicIndexCommon.isNotBlank(industryA)) {
            industry.keyApp(industryA);
        }
    }

    if (basicIndexCommon.isNotBlank(basicUnit)) {
        basicIndexCommon.setUnitId("${basicUnit.id}");
    }

    if (basicIndexCommon.isNotBlank('${oneBasicBuildingJson}')) {
        navButtonBuild.setObjArrayElement(1, JSON.parse('${oneBasicBuildingJson}'));
    } else {
        navButtonBuild.setObjArrayElement(1, {});
    }
    if (basicIndexCommon.isNotBlank('${twoBasicBuildingJson}')) {
        navButtonBuild.setObjArrayElement(2, JSON.parse('${twoBasicBuildingJson}'));
    } else {
        navButtonBuild.setObjArrayElement(2, {});
    }
    if (basicIndexCommon.isNotBlank('${threeBasicBuildingJson}')) {
        navButtonBuild.setObjArrayElement(3, JSON.parse('${threeBasicBuildingJson}'));
    } else {
        navButtonBuild.setObjArrayElement(3, {});
    }
    if (basicIndexCommon.isNotBlank('${fourBasicBuildingJson}')) {
        navButtonBuild.setObjArrayElement(4, JSON.parse('${fourBasicBuildingJson}'));
    } else {
        navButtonBuild.setObjArrayElement(4, {});
    }
    basicIndexCommon.buildingInit(navButtonBuild.getObjArray(1));

    if (basicIndexCommon.isNotBlank(basicEstate)) {
        basicIndexCommon.setEstateId("${basicEstate.id}");
        try {
            basicIndexCommon.estateLandStateInit(JSON.parse('${el:toJsonString(basicEstateLandState)}'));
            basicIndexCommon.estateInit(JSON.parse('${el:toJsonString(basicEstate)}'));
        } catch (e) {
            console.error(e);
        }
    }

    if (basicIndexCommon.isNotBlank(basicHouse)) {
        basicIndexCommon.setHouseId("${basicHouse.id}");
        try {
            var itemA = JSON.parse('${el:toJsonString(basicHouse)}');
            var itemB = JSON.parse('${el:toJsonString(basicHouseTrading)}');
            if (!basicIndexCommon.isNotBlank(itemA)) {
                itemA = {};
            }
            if (!basicIndexCommon.isNotBlank(itemB)) {
                itemB = {};
            }
            basicIndexCommon.houseInitA(itemA, itemB);
        } catch (e) {
            console.info(e);
        }
    }

    $("#profile-tab3").attr("data-toggle", "tab");
    $("#profile-tab1").attr("data-toggle", "tab");
    $("#profile-tab4").attr("data-toggle", "tab");
    $("#profile-tab2").attr("data-toggle", "tab");

    basicIndexCommon.estateLoadList();
    basicIndexCommon.unitLoadList();
    basicIndexCommon.houseLoadList();


</script>
<script type="application/javascript">
    function saveform() {
        if (!$("#frm_approval").valid()) {
            return false;
        }
        var data = {};
        data.formData = JSON.stringify(basicIndexCommon.formParams());
        var approvalData = formParams("frm_approval");
        data = $.extend({}, approvalData, data);
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApply/basicEditSubmit",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }

    //关闭流程
    function closeBasicApp() {
        AssessCommon.closeProcess('${basicApply.processInsId}', function () {
            Alert('流程关闭成功', 1, null, function () {
                window.close();
            })
        })
    }
</script>
</html>
