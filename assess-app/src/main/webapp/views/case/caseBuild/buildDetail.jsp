<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_content">
        <div class="x_title">
            <h3>楼栋基本信息 </h3>
            <div class="clearfix"></div>
        </div>
        <form class="form-horizontal" id="basicBuildingFrm">
            <input type="hidden" name="id" value="${caseBuilding.id}">
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        楼栋号
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <div class="input-group">
                            <label class="form-control" name="buildingNumber">${caseBuilding.buildingNumber}</label>
                            <span class="input-group-btn">
                            <div onclick="caseCommon.viewMapMarker('${caseBuilding.id}','building','');" class="btn btn-info"><i
                                    class="fa fa-map-marker"></i> 标注</div>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        楼栋名称
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control" name="buildingName">${caseBuilding.buildingName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        总层数
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control" name="floorCount">${caseBuilding.floorCount}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        所在位置
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control" name="location">${caseBuilding.location}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        建筑使用年限
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <c:if test="${!empty caseBuilding.residenceUseYearName}">
                            <label class="form-control" name="residenceUseYearName">${caseBuilding.residenceUseYearName}</label>
                        </c:if>
                        <c:if test="${!empty caseBuilding.industryUseYearName}">
                            <label class="form-control" name="industryUseYearName">${caseBuilding.industryUseYearName}</label>
                        </c:if>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        户型区间
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control" name="unitInterval">${caseBuilding.unitInterval}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        物业费
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control" name="propertyFee">${caseBuilding.propertyFee}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        配套公共设施使用费
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control" name="facilitiesUseFee">${caseBuilding.facilitiesUseFee}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        建筑高度
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control" name="buildingHeight">${caseBuilding.buildingHeight}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        建筑面积
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control" name="buildingArea">${caseBuilding.buildingArea}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        占地面积
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control" name="coverAnArea">${caseBuilding.coverAnArea}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        层高
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control" name="floorHeight">${caseBuilding.floorHeight}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        进深
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control" name="diameterDepth">${caseBuilding.diameterDepth}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        土地使用年限
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control" name="landUseYear">${caseBuilding.landUseYear}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        开盘时间
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control dbdate" name="openTime">
                            <fmt:formatDate
                                    value='${caseBuilding.openTime}' pattern='yyyy-MM-dd'/>
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        交房时间
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control dbdate" name="roomTime">
                            <fmt:formatDate
                                    value='${caseBuilding.roomTime}' pattern='yyyy-MM-dd'/>
                        </label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        竣工时间获取方式
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control" name="completedTimeTypeName">${caseBuilding.completedTimeTypeName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        竣工时间
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">

                        <label class="form-control dbdate" name="beCompletedTime"><fmt:formatDate
                                value='${caseBuilding.beCompletedTime}' pattern='yyyy-MM-dd'/></label>

                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        物业类型
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control" name="propertyTypeName">${caseBuilding.propertyTypeName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        物业类别
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control" name="propertyCategoryName">${caseBuilding.propertyCategoryName}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        物业公司
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control" name="propertyName">${caseBuilding.propertyName}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        物业公司公司性质
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control" name="propertyName">${caseBuilding.propertyCompanyNatureName}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        物业公司社会信誉
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control" name="propertyName">${caseBuilding.propertySocialPrestigeName}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        建筑结构类型
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control" name="buildingStructureTypeName">${caseBuilding.buildingStructureTypeName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        建筑结构类别
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control" name="buildingStructureCategoryName">${caseBuilding.buildingStructureCategoryName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                        建筑公司
                    </label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control" name="builder">${caseBuilding.builderName}</label>
                    </div>
                </div>
            </div>

            <div class="form-group" id="navButtonBuildGroupFileId">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">平面图</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <div id="_building_floor_plan"></div>
                    </div>
                </div>

                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">外装图</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <div id="_building_figure_outside"></div>
                    </div>
                </div>

                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">外观图</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <div id="_building_floor_Appearance_figure"></div>
                    </div>
                </div>
            </div>
        </form>

        <%@include file="/views/case/caseBuild/sonBuildDetail.jsp" %>
    </div>

</div>
<script>
    $(function () {
        fileShow(AssessUploadKey.BUILDING_FLOOR_PLAN);
        fileShow(AssessUploadKey.BUILDING_FIGURE_OUTSIDE);
        fileShow(AssessUploadKey.BUILDING_FLOOR_APPEARANCE_FIGURE);
        buildingModel.prototype.viewInit();
    });

    function fileShow(fieldsName) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.CaseBuilding,
                tableId: '${caseBuilding.id}'
            },
            deleteFlag: false
        })
    }
</script>
