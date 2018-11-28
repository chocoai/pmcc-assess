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
            <form id="basicApplyFrm" class="form-horizontal">
                <input type="hidden" name="id" value="${basicApply.id}">
                <input type="hidden" name="caseUnitId" value="${basicApply.caseUnitId}">
            </form>
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
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    var objectData = new Object();
    objectData.config = {
        basicApply: {
            frm: "basicApplyFrm"
        },
        basicEstate: {
            key: "estateName",
            name: "楼盘",
            frm: "basicEstateFrm",
            frmLandState: "basicLandState"
        },
        basicBuilding: {
            key: "buildingNumber",
            name: "楼栋",
            frm: "basicBuildingFrm",
            mainFrm: "basicBuildingMainFrm"
        },
        basicUnit: {
            key: "unitNumber",
            name: "单元",
            frm: "basicUnitFrm"
        },
        basicHouse: {
            key: "houseNumber",
            name: "房屋",
            frm: "basicHouseFrm",
            tradingFrm: "basicTradingFrm"
        }
    };


    //收集数据
    objectData.formParams = function () {
        var item = {};
        var basicApply = formParams(objectData.config.basicApply.frm);
        basicApply.estatePartInFlag = basicIndexCommon.partInFlag.estate;
        basicApply.buildingPartInFlag = basicIndexCommon.partInFlag.building;
        basicApply.unitPartInFlag = basicIndexCommon.partInFlag.unit;
        basicApply.housePartInFlag = basicIndexCommon.partInFlag.house;
        item.basicApply = basicApply;
        if (basicApply.estatePartInFlag == true) {
            item.basicEstate = formParams(objectData.config.basicEstate.frm);
            item.basicEstateLandState = formParams(objectData.config.basicEstate.frmLandState);
        }
        if (basicApply.buildingPartInFlag == true) {
            item.basicBuildingMain = formParams(objectData.config.basicBuilding.mainFrm);
            item.basicBuilding = formParams(objectData.config.basicBuilding.frm);
        }
        if (basicApply.unitPartInFlag == true) {
            item.basicUnit = formParams(objectData.config.basicUnit.frm);
        }
        if (basicApply.housePartInFlag == true) {
            item.basicHouse = formParams(objectData.config.basicHouse.frm);
            item.basicTrading = formParams(objectData.config.basicHouse.tradingFrm);
        }
        return item;
    };

    //返回修改
    objectData.editApply = function () {
        if ('${basicApply.id}' != '0') {
            industry.keyApp("${basicApply.type}");

            //初始楼盘信息
            if ('${basicApply.estatePartInFlag}' == 'true') {
                estateCommon.init('${basicApply.id}',basicIndexCommon.showEstateTab);
            }

            //初始楼栋信息
            if ('${basicApply.buildingPartInFlag}' == 'true') {
                buildingCommon.init('${basicApply.id}',basicIndexCommon.showBuildingTab);
            }

            //初始单元信息
            if ('${basicApply.unitPartInFlag}' == 'true') {
                unitCommon.init('${basicApply.id}',basicIndexCommon.showUnitTab);
            }

            //初始房屋信息
            if ('${basicApply.housePartInFlag}' == 'true') {
                houseCommon.init('${basicApply.id}',basicIndexCommon.showHouseTab);
            }
        }
    };


    $(function () {
        objectData.editApply();
    });
</script>
<script type="application/javascript">
    function saveform() {
        var data = {};
        data.formData = JSON.stringify(objectData.formParams());
        var approvalData = formParams('frm_approval');
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
        Alert('确定要关闭流程么？', 2, null, function () {
            AssessCommon.closeProcess('${basicApply.processInsId}', function () {
                Alert('流程关闭成功', 1, null, function () {
                    window.close();
                })
            })
        })
    }
</script>
</html>
