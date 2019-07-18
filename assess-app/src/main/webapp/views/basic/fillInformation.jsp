<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" id="basicApplyId" role="main" style="margin-left: 0">

                <div class="x_content">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class="x-valid">
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <input type="button" class="btn btn-success" data-mode="reference"
                                           onclick="projectData.prototype.showModel();"
                                           value="引用项目中的楼盘">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>

            <%@include file="/views/basic/fillInformationIndexCommon.jsp" %>
            <div class="x_panel">
                <div class="x_content">
                    <div style="text-align: center;">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            关闭
                        </button>
                        <button class="btn btn-warning" onclick="saveDraft();">
                            保存<i style="margin-left: 10px" class="fa fa-save"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/map.position.js"></script>
<%@include file="/views/share/main_footer.jsp" %>
<jsp:include page="/views/basic/modelView/projectHousesData.jsp"></jsp:include>
</html>
<script type="text/javascript">
    var fillInformation = new Object();

    fillInformation.autocompleteData = function (index) {
        var row = $("#projectCaseItemList").bootstrapTable('getData')[index];
        Loading.progressShow();
        $.ajax({
            url: '${pageContext.request.contextPath}/basicApply/getCaseBasicApply',
            data: {
                id: row.id,
                projectPhaseId: row.projectPhaseId
            },
            type: "get",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    if (result.data != null) {
                        basicCommon.hideAllTab();
                        //初始楼盘信息
                        if("estate"=="${buildingType}"){

                            estateCommon.loadMarkerList(result.data.id);
                            estateCommon.batchGetDataFromProject(result.data.id,'${tableId}',basicCommon.showEstateTab("add"));
                        }

                        //初始楼栋信息
                        if("building"=="${buildingType}") {
                            buildingCommon.loadMarkerList(result.data.id);
                            buildingCommon.batchGetDataFromProject(result.data.id,'${tableId}',basicCommon.showBuildingTab("add"));

                        }
                        //初始单元信息
                        if("unit"=="${buildingType}") {
                            unitCommon.loadMarkerList(result.data.id);
                            unitCommon.batchGetDataFromProject(result.data.id,'${tableId}',basicCommon.showUnitTab("add"));
                        }
                        //初始房屋信息
                        if("house"=="${buildingType}") {
                            houseCommon.batchGetDataFromProject(result.data.id,'${tableId}',basicCommon.showHouseTab("add"));
                        }
                        $('#divBoxProjectItemData').modal('hide');
                        $('#divBoxProjectData').modal('hide');
                    }
                } else {
                    Loading.progressHide();
                    Alert(result.errmsg);
                }
            }
        })
    };

    $(function(){
        industry.keyApp("${type}");
        if("estate"=="${buildingType}"){
            estateCommon.initById("${tableId}", basicCommon.showEstateTab("add"));
        }

        //初始楼栋信息
        if("building"=="${buildingType}") {
            buildingCommon.initById("${tableId}", basicCommon.showBuildingTab("add"));
        }
        //初始单元信息
        if("unit"=="${buildingType}") {
            unitCommon.initById("${tableId}", basicCommon.showUnitTab("add"));
        }
        //初始房屋信息
        if("house"=="${buildingType}") {
            houseCommon.initById("${tableId}", basicCommon.showHouseTab("add"));
        }
    })

</script>

<script type="text/javascript">

    //保存草稿
    function saveDraft() {
        Loading.progressShow();
        var formData = JSON.stringify(getFormData());
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/saveDraft",
            type: "post",
            dataType: "json",
            async: false,
            data: {formData: formData},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("保存数据成功!", 1, null, function () {
                        window.close();
                    });
                } else {
                    Alert(result.errmsg);
                }
            }
        });
    }

    //获取表单数据
    function getFormData() {
        var item = {};
        if ("estate"=="${buildingType}") {
            item.basicEstate = formSerializeArray(estateCommon.estateForm);
            if (estateCommon.estateLandStateForm.size() >= 1) {
                var data = formSerializeArray(estateCommon.estateLandStateForm);
                var landLevelContent = [];
                if (data.landFactorTotalScore) {
                    var landFactorTotalScore = 0;
                    data.landFactorTotalScore.split(",").forEach(function (value, index) {
                        landFactorTotalScore += Number(value);
                    });
                    data.landFactorTotalScore = landFactorTotalScore;
                }
                estateCommon.estateLandStateForm.find("input[name='landLevelContent']").each(function (i, n) {
                    var group = $(n).closest(".group");
                    var dataLandLevelAchievement = group.find("input[name='dataLandLevelAchievement']").val();
                    var obj = JSON.parse($(n).val());
                    var dataObject = [];
                    obj.forEach(function (value, index) {
                        if (value.id == dataLandLevelAchievement) {
                            value.modelStr = "update";
                        }
                        dataObject.push(value);
                    });
                    landLevelContent.push(dataObject);
                });
                if (landLevelContent.length >= 1) {
                    data.landLevelContent = JSON.stringify(landLevelContent);
                }
                item.basicEstateLandState = data;
            }
        }
        if ("building"=="${buildingType}") {
            item.basicBuilding = formSerializeArray(buildingCommon.buildingForm);
            item.basicBuilding.vSpecifications = [];
            buildingCommon.buildingForm.find('.form-group').each(function () {
                var vSpecification = {};
                var specificationName = $(this).find('[name^=specificationName]').val();
                var specificationContent = $(this).find('[name^=specificationContent]').val();
                if (specificationName && specificationContent) {
                    vSpecification.specificationName = specificationName;
                    vSpecification.specificationContent = specificationContent;
                    item.basicBuilding.vSpecifications.push(vSpecification);
                }
            });
        }
        if ("unit"=="${buildingType}") {
            item.basicUnit = formSerializeArray(unitCommon.unitForm);
        }
        if ("house"=="${buildingType}") {
            item.basicHouse = formSerializeArray(houseCommon.houseForm);
            item.basicTrading = formSerializeArray(houseCommon.houseTradingForm);
            item.basicDamagedDegree = damagedDegree.getFormData();
        }
        return item;
    };

</script>
