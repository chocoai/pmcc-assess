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

            <div class="x_panel" id="basicApplyId">
                <div class="x_title">
                    <h2>
                        <small>案例信息</small>
                    </h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div role="tabpanel" data-example-id="togglable-tabs">
                        <ul class="nav nav-tabs bar_tabs" role="tablist" id="caseTab">
                            <li role="presentation" class=""><a href="#basicEstate" role="tab" id="profile-tab1" aria-expanded="true"
                                                                onclick="objectData.estate.init()">楼盘</a>
                            </li>
                            <li role="presentation" class=""><a href="#caseBuild" role="tab" id="profile-tab2" aria-expanded="false"
                                                                onclick="objectData.build.init(1)">楼栋</a>
                            </li>
                            <li role="presentation" class=""><a href="#caseUnit" role="tab" id="profile-tab3" aria-expanded="false"
                                                                onclick="objectData.unit.init()">单元</a>
                            </li>
                            <li role="presentation" class=""><a href="#caseHouse" role="tab" id="profile-tab4" aria-expanded="false"
                                                                onclick="objectData.house.init();">房屋</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div role="tabpanel" class="tab-pane fade" id="basicEstate" aria-labelledby="profile-tab1">
                                <div class="x_content">
                                    <div class="x_title">
                                        <h3>楼盘基本信息
                                        </h3>
                                        <div class="clearfix"></div>
                                    </div>
                                    <form class="form-horizontal" id="frm_estate">
                                        <input type="hidden" name="id" value="${basicEstate.id}">
                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">省
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstate.provinceName}</label>
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">市</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstate.cityName}</label>
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">县</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstate.districtName}</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">街道</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstate.street}</label>
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">街道号</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstate.streetNumber}</label>
                                                </div>
                                            </div>


                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">基础版块</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstate.blockName}</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">楼盘名称</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstate.name}</label>
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">楼盘方位</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstate.position}</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">占地面积</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstate.coverAnArea}</label>
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">容积率</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstate.volumetricRate}</label>
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">绿化率</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstate.greeningRate}</label>
                                                </div>
                                            </div>
                                        </div>


                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">楼盘概况</label>
                                                <div class="col-sm-11">
                                                    <label class="form-control">${basicEstate.description}</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">楼栋数</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstate.buildingNumber}</label>
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">开发商</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstate.developerName}</label>
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">建筑面积</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstate.floorArea}</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">均价</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstate.averagePrice}</label>
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">价格区间</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstate.priceRange}</label>
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">附号</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstate.attachNumber}</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">总平面图</label>
                                                <div class="col-sm-5">
                                                    <div id="_estate_floor_total_plan"></div>
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">外观图</label>
                                                <div class="col-sm-5">
                                                    <div id="_estate_floor_Appearance_figure"></div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">供水平面图</label>
                                                <div class="col-sm-5">
                                                    <div id="_water_supply_plan"></div>
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">供电平面图</label>
                                                <div class="col-sm-5">
                                                    <div id="_power_supply_plan"></div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">供气平面图</label>
                                                <div class="col-sm-5">
                                                    <div id="_air_supply_plan"></div>
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">采暖平面图</label>
                                                <div class="col-sm-5">
                                                    <div id="_heating_plan"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>


                                <div class="x_content">
                                    <div class="x_title">
                                        <h3>
                                            <small>
                                                土地交易基本信息
                                            </small>
                                        </h3>
                                        <div class="clearfix"></div>
                                    </div>
                                    <form class="form-horizontal" id="basicLandState">
                                        <input type="hidden" name="id">
                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">土地名称<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-11">
                                                    <label class="form-control">${basicEstateLandState.name}</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">土地用途类型<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstateLandState.landUseTypeName}</label>
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">土地用途类别<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstateLandState.landUseCategoryName}</label>
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">土地级别<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstateLandState.landLevelName}</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">东至<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstateLandState.eastTo}</label>
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">南至<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstateLandState.southTo}</label>
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">西至<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstateLandState.westTo}</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">北至<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstateLandState.northTo}</label>
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">土地形状状况<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstateLandState.shapeState}</label>
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">土地平整度<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstateLandState.planeness}</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">土地开发程度<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstateLandState.developmentDegree}</label>
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">开发限制条件<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstateLandState.restrictiveCondition}</label>
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">土壤<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstateLandState.soil}</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">地形地势<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstateLandState.topographicTerrain}</label>
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">土地面积<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicEstateLandState.landArea}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <%@include file="/views/basic/modelView/estate/sonEstateApprovalView.jsp" %>

                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="caseBuild" aria-labelledby="profile-tab2">
                                <div class="x_content">
                                    <div class="x_title">
                                        <h3>楼栋基本信息 </h3>
                                        <div class="clearfix"></div>
                                    </div>

                                    <form class="form-horizontal" id="basicBuildFrm">
                                        <input type="hidden" name="id">
                                        <div class="form-group" id="navButtonBuild">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                </label>
                                                <div class="col-sm-2">
                                                    <div class="btn-group" data-toggle="buttons">
                                                        <button class="btn btn-default"
                                                                onclick="navButtonBuild.one(this,1)">楼栋基础
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                </label>
                                                <div class="col-sm-2">
                                                    <div class="btn-group" data-toggle="buttons">
                                                        <button class="btn btn-default"
                                                                onclick="navButtonBuild.two(this,2)">第二部分
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                </label>
                                                <div class="col-sm-2">
                                                    <div class="btn-group" data-toggle="buttons">
                                                        <button class="btn btn-default"
                                                                onclick="navButtonBuild.three(this,3)">第三部分
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                </label>
                                                <div class="col-sm-2">
                                                    <div class="btn-group" data-toggle="buttons">
                                                        <div class="btn-group" data-toggle="buttons">
                                                            <button class="btn btn-default"
                                                                    onclick="navButtonBuild.four(this,4)">第四部分
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                    楼栋号
                                                </label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="楼栋号" name="buildingNumber"
                                                           class="form-control" readonly="readonly">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                    楼栋名称
                                                </label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="楼栋名称" name="buildingName"
                                                           class="form-control" readonly="readonly">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                    户型区间
                                                </label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="户型区间" name="unitInterval"
                                                           class="form-control" readonly="readonly">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                    物业费
                                                </label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="物业费(数字)" name="propertyFee"
                                                           class="form-control"
                                                           readonly="readonly">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                    公共设施使用费
                                                </label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="公共设施使用费(数字)" name="facilitiesUseFee"
                                                           data-rule-number='true' class="form-control"
                                                           readonly="readonly">
                                                </div>
                                            </div>

                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                    楼层起
                                                </label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="楼层起(数字)" name="floorStart"
                                                           class="form-control"
                                                           readonly="readonly">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                    楼层止
                                                </label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="楼层止(数字)" name="floorEnd"
                                                           class="form-control"
                                                           readonly="readonly">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                    总层数
                                                </label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="总层数(数字)"
                                                           name="floorCount" class="form-control" readonly="readonly">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                    建筑高度
                                                </label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="建筑高度(数字)"
                                                           name="buildingHeight" class="form-control"
                                                           readonly="readonly">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                    建筑面积
                                                </label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="建筑面积(数字)"
                                                           name="buildingArea" class="form-control" readonly="readonly">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                    占地面积
                                                </label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="占地面积(数字)"
                                                           name="coverAnArea" class="form-control" readonly="readonly">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                    层高
                                                </label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="层高(数字)"
                                                           name="floorHeight" class="form-control" readonly="readonly">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                    径深
                                                </label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="径深(数字)"
                                                           name="diameterDepth" class="form-control"
                                                           readonly="readonly">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                    土地使用年限
                                                </label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="土地使用年限(数字)" data-rule-number='true'
                                                           name="landUseYear" class="form-control" readonly="readonly">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                    所在位置
                                                </label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="所在位置" name="location"
                                                           class="form-control"
                                                           readonly="readonly">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                    开盘时间<span class="symbol readonly"></span>
                                                </label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="开盘时间" name="openTimeName"
                                                           class="form-control" readonly="readonly">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                    交房时间<span class="symbol readonly"></span>
                                                </label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="交房时间" name="roomTimeName"
                                                           class="form-control" readonly="readonly">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                    物业类型
                                                </label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="物业类型" name="propertyTypeName"
                                                           class="form-control" readonly="readonly">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                    建筑结构上级
                                                </label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="建筑结构上级" name="buildingStructureName"
                                                           class="form-control" readonly="readonly">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                    建筑结构(下级)
                                                </label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="建筑结构下级"
                                                           name="buildingStructureLowerName"
                                                           class="form-control" readonly="readonly">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                    建筑类别
                                                </label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="建筑类别" name="buildingCategoryName"
                                                           class="form-control" readonly="readonly">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                    建筑公司
                                                </label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="建筑公司" name="dataBuildingName"
                                                           class="form-control" readonly="readonly">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">
                                                    物业公司
                                                </label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="物业公司" name="propertyName"
                                                           class="form-control" readonly="readonly">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group" id="navButtonBuildGroupFileId">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">平面图<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <input id="building_floor_plan" name="frm_estate_floor_total_plan"
                                                           required="required" placeholder="上传附件" class="form-control"
                                                           type="file">
                                                    <div id="_building_floor_plan"></div>
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">外装图<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <input id="building_figure_outside"
                                                           name="frm_estate_floor_total_plan"
                                                           required="required" placeholder="上传附件" class="form-control"
                                                           type="file">
                                                    <div id="_building_figure_outside"></div>
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">外观图<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <input id="building_floor_Appearance_figure"
                                                           name="frm_estate_floor_total_plan"
                                                           required="required" placeholder="上传附件" class="form-control"
                                                           type="file">
                                                    <div id="_building_floor_Appearance_figure"></div>
                                                </div>
                                            </div>
                                        </div>

                                    </form>
                                </div>
                                <%@include file="/views/basic/modelView/build/sonBuildApprovalView.jsp" %>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="caseUnit" aria-labelledby="profile-tab3">
                                <div class="x_content">
                                    <div class="x_title">
                                        <h3>单元基本信息 </h3>
                                        <div class="clearfix"></div>
                                    </div>
                                    <form class="form-horizontal">
                                        <input type="hidden" name="id" value="${basicUnit.id}">
                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">单元编号</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicUnit.unitNumber}</label>
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">户梯比</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicUnit.elevatorHouseholdRatio}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <%@include file="/views/basic/modelView/unit/sonUnitApprovalView.jsp" %>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="caseHouse" aria-labelledby="profile-tab4">
                                <div class="x_content">
                                    <div class="x_title">
                                        <h3>
                                            <small>
                                                房屋基本信息
                                            </small>
                                        </h3>
                                        <div class="clearfix"></div>
                                    </div>
                                    <form class="form-horizontal" id="basicHouseFrm">
                                        <input type="hidden" name="id" value="${basicHouse.id}">

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">房号<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicHouse.houseNumber}</label>
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">所在楼层<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicHouse.floor}</label>
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">使用环境<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicHouse.useEnvironmentName}</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">户型选择<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <div class="input-group">
                                                        <label class="form-control">${basicHouse.huxingName}</label>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <div class="col-sm-3 house_latest_family_plan">

                                                </div>
                                            </div>


                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">朝向<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicHouse.orientation}</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">证载用途<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicHouse.certUseName}</label>
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">实际用途<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicHouse.practicalUseName}</label>
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">权益限制<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicHouse.rightInterestsRestriction}</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">房屋出租占用情况途描述</label>
                                                <div class="col-sm-11">
                                                    <label class="form-control">${basicHouse.description}</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">房屋平面图<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-5">
                                                    <div id="_house_img_plan"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>

                                <div class="x_content">
                                    <div class="x_title">
                                        <h3>
                                            <small>
                                                房屋交易信息
                                            </small>
                                        </h3>
                                        <div class="clearfix"></div>
                                    </div>
                                    <form class="form-horizontal" id="basicTradingFrm">
                                        <input type="hidden" name="id" value="${basicHouseTrading.id}">
                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">财产范围<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicHouseTrading.scopeProperty}</label>
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">融资条件<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicHouseTrading.financingConditions}</label>
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">税费负担<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicHouseTrading.taxBurdenName}</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">正常交易<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicHouseTrading.normalTransactionName}</label>
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">说明事项类型<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicHouseTrading.descriptionTypeName}</label>
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">说明事项内容<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicHouseTrading.descriptionContent}</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">交易时间<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicHouseTrading.tradingTimeName}</label>
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">交易类型</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicHouseTrading.tradingTypeName}</label>
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">交易价格<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicHouseTrading.tradingPrice}</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group BasicHouseTradingSell" style="display: none">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">买方支付的额外税费</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicHouseTrading.buyerExtraTaxFee}</label>
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">付款方式<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicHouseTrading.paymentMethodName}</label>
                                                </div>
                                            </div>

                                            <div class="x-valid" style="display: none;">
                                                <label class="col-sm-1 control-label">出售总额</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicHouseTrading.totalSale}</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group BasicHouseTradingLease" style="display: none">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">承租方支付的额外税费</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicHouseTrading.rentingExtraTaxFee}</label>
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">押金</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicHouseTrading.deposit}</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group" id="tableTradingLeaseAndSellDiv" style="display: none">
                                            <div class="x-valid">
                                                <div class="col-sm-1" style="text-align: right;">
                                                </div>
                                                <div class="col-sm-11">
                                                    <table class="table table-bordered" id="tableTradingLeaseAndSell">
                                                        <!-- cerare document add ajax data-->
                                                    </table>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">信息来源分类<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicHouseTrading.informationTypeName}</label>
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">信息来源</label>
                                                <div class="col-sm-3">
                                                    <label class="form-control">${basicHouseTrading.information}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <%@include file="/views/basic/modelView/house/sonHouseApprovalView.jsp" %>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_content">

                </div>
            </div>

            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    var objectData = new Object();

    objectData.config = {
        id: "basicApplyId",
        option: {},
        basicEstate: {
            key: "basicEstate",
            name: "楼盘",
            frm: "basicEstateFrm",
            frmLandState: "basicLandState",
            files: {
                filePlanTotal: "estate_floor_total_plan",//总平面图id和字段
                waterSupplyPlan: "water_supply_plan",//供水平面图id和字段
                powerSupplyPlan: "power_supply_plan",//供电平面图id和字段
                airSupplyPlan: "air_supply_plan",//供气平面图id和字段
                heatingPlan: "heating_plan",//采暖平面图id和字段
                fileAppearance: "estate_floor_Appearance_figure" //外观图id和字段
            }
        },
        basicBuilding: {
            key: "basicBuilding",
            name: "楼栋",
            frm: "basicBuildFrm",
            files: {
                building_floor_plan: "building_floor_plan",//平面图id和字段 (楼栋)
                building_figure_outside: "building_figure_outside",//外装图id和字段
                building_floor_Appearance_figure: "building_floor_Appearance_figure"//外观图id和字段
            }
        },
        basicUnit: {
            key: "basicUnit",
            name: "单元"
        },
        basicHouse: {
            key: "basicHouse",
            name: "房屋",
            frm: "basicHouseFrm",
            tradingFrm: "basicTradingFrm",
            leaseID: "BasicHouseTradingLease",//房屋出租
            sellID: "BasicHouseTradingSell",//房屋出售
            totalSale: "totalSale",//出售总额
            divBoxSon: "divBoxTradingLeaseAndSell",
            tableSon: "tableTradingLeaseAndSell",
            frmSon: "frmTradingLeaseAndSell",
            houseFileId: "house_img_plan"
        }
    };

    /**
     * 判断字符串以及null等
     */
    objectData.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    /**
     * 判断对象 属性
     */
    objectData.isNotBlankObjectProperty = function (obj) {
        for (var key in obj) {
            if (objectData.isNotBlank(obj[key])) {
                return true;
            }
        }
        return false
    };

    /**
     * 判断对象
     */
    objectData.isNotBlankObject = function (obj) {
        for (var key in obj) {
            return true;
        }
        return false
    };

    objectData.select2Assignment = function (frm, data, name) {
        if (objectData.isNotBlank(data)) {
            $("#" + frm).find("select." + name).val(data).trigger("change");
        } else {
            $("#" + frm).find("select." + name).val(null).trigger("change");
        }
    };


    objectData.showFile = function (fieldsName, table, id) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: table,
                tableId: objectData.isNotBlank(id) ? id : "0",
                creater: "${currUserAccount}"
            },
            deleteFlag: false
        })
    };

    objectData.estate = {
        init: function () {
            $.each(objectData.config.basicEstate.files, function (i, n) {
                objectData.showFile(n, AssessDBKey.BasicEstate, "${basicEstate.id}");
            });
            estateNetwork.prototype.loadDataDicList();
            estateParking.prototype.loadDataDicList();
            estateSupplyWater.prototype.loadDataDicList();
            estateSupplyPower.prototype.loadDataDicList();
            estateSupplyHeating.prototype.loadDataDicList();
            estateSupplyGas.prototype.loadDataDicList();
            matchingEducation.prototype.loadDataDicList();
            matchingEnvironment.prototype.loadDataDicList();
            matchingFinance.prototype.loadDataDicList();
            matchingRecreation.prototype.loadDataDicList();
            matchingRestaurant.prototype.loadDataDicList();
            matchingMarket.prototype.loadDataDicList();
            matchingMaterial.prototype.loadDataDicList();
            matchingMedical.prototype.loadDataDicList();
            matchingTransit.prototype.loadDataDicList();
            matchingTrafficHub.prototype.loadDataDicList();
            matchingMetro.prototype.loadDataDicList();
            matchingMainRoad.prototype.loadDataDicList();
            matchingMainConversion.prototype.loadDataDicList();
        }
    };

    var navButtonBuild;
    (function () {
        navButtonBuild = new Object();
        navButtonBuild.groupFileId = "navButtonBuildGroupFileId";
        navButtonBuild.isNotBlank = function (item) {
            if (item) {
                return true;
            }
            return false;
        };

        function writeUpdateFileId(num) {
            var fieldsName = "";
            var labelName = "";
            if (num == 0) {
                labelName = "平面图";
                fieldsName = objectData.config.basicBuilding.files.building_floor_plan + "" + navButtonBuild.switchNumber;
            }
            if (num == 1) {
                labelName = "外装图";
                fieldsName = objectData.config.basicBuilding.files.building_figure_outside + "" + navButtonBuild.switchNumber;
            }
            if (num == 2) {
                labelName = "外观图";
                fieldsName = objectData.config.basicBuilding.files.building_floor_Appearance_figure + "" + navButtonBuild.switchNumber;
            }
            var label = "<label class='col-sm-1 control-label'>" + labelName + "</label>";
            var div = "<div class='col-sm-3'>";
            div += "<div id='" + "_" + fieldsName + "'>" + "</div>";
            div += "</div>";
            return label.concat(div);
        };

        //每次切换更改附件 id
        navButtonBuild.updateFileId = function () {
            var html = "";
            for (var i = 0; i <= 2; i++) {
                html += "<div class='x-valid'>";
                html += writeUpdateFileId(i);
                html += "</div>";
            }
            $("#" + navButtonBuild.groupFileId).empty().append(html);
        };
        //赋值
        navButtonBuild.initData = function (data) {
            $("#" + objectData.config.basicBuilding.frm).initForm(data);
            $.each(objectData.config.basicBuilding.files, function (i, n) {
                objectData.showFile(n + "" + navButtonBuild.switchNumber, AssessDBKey.BasicBuilding, data.id);
            });
        };
        navButtonBuild.clearAll = function () {
            $("#" + objectData.config.basicBuilding.frm).clearAll();
        };
    })();


    navButtonBuild.switchNumber = 0;
    navButtonBuild.switchInit = function (target, data, number) {
        if (objectData.isNotBlank(data)) {
            navButtonBuild.clearAll();
            navButtonBuild.switchNumber = number;
            navButtonBuild.updateFileId();
            navButtonBuild.initData(data);
            navButtonBuild.dataButtonWrite(target);
            buildingModel.prototype.viewInit();
        } else {
            toastr.success('无数据!');
        }
    };
    //第一栋
    navButtonBuild.one = function (target, number) {
        if (number == '1') {
            if (objectData.isNotBlank('${oneBasicBuildingJson}')) {
                var data = JSON.parse('${oneBasicBuildingJson}');
                navButtonBuild.switchInit(target, data, number);
            } else {
                toastr.success('无数据!');
            }
        }
    };
    //第二栋
    navButtonBuild.two = function (target, number) {
        if (number == '2') {
            if (objectData.isNotBlank('${twoBasicBuildingJson}')) {
                var data = JSON.parse('${twoBasicBuildingJson}');
                navButtonBuild.switchInit(target, data, number);
            } else {
                toastr.success('无数据!');
            }
        }
    };
    //第三栋
    navButtonBuild.three = function (target, number) {
        if (number == '3') {
            if (objectData.isNotBlank('${threeBasicBuildingJson}')) {
                var data = JSON.parse('${threeBasicBuildingJson}');
                navButtonBuild.switchInit(target, data, number);
            } else {
                toastr.success('无数据!');
            }
        }
    };
    //第四栋
    navButtonBuild.four = function (target, number) {
        if (number == '4') {
            if (objectData.isNotBlank('${fourBasicBuildingJson}')) {
                var data = JSON.parse('${fourBasicBuildingJson}');
                navButtonBuild.switchInit(target, data, number);
            } else {
                toastr.success('无数据!');
            }
        }
    };
    navButtonBuild.dataButtonWrite = function (target) {
        $.each($("#navButtonBuild button"), function (i, n) {
            $(n).removeClass();
            $(n).addClass("btn btn-default");
        });
        //改变按钮颜色
        $(target).removeClass();
        $(target).addClass("btn btn-primary");
        $("." + buildingModel.prototype.config().sonTable).html(navButtonBuild.switchNumber + "部分");
        $("." + buildingModel.prototype.config().examineBuildingSurfaceTable).html(navButtonBuild.switchNumber + "部分");
        $("." + buildingModel.prototype.config().examineBuildingMaintenanceTable).html(navButtonBuild.switchNumber + "部分");
        $("." + buildingModel.prototype.config().examineBuildingFunctionTable).html(navButtonBuild.switchNumber + "部分");
    };

    objectData.build = {
        init: function (number) {
            var target = $("#navButtonBuild button").eq(0)[0];
            navButtonBuild.one(target, number);
        }
    };

    objectData.unit = {
        init: function () {
            unitDecorate.prototype.loadDataDicList();
            unitHuxing.prototype.loadDataDicList();
            unitElevator.prototype.loadDataDicList();
        }
    };

    objectData.house = {
        init: function () {
            houseRoom.prototype.loadDataDicList();
            houseWater.prototype.loadDataDicList();
            houseIntelligent.prototype.loadDataDicList();
            houseFaceStreet.prototype.loadDataDicList();
            houseCorollaryEquipment.prototype.loadDataDicList();
            houseNewWind.prototype.loadDataDicList();
            houseAirConditioner.prototype.loadDataDicList();
            houseHeating.prototype.loadDataDicList();
            objectData.showFile(objectData.config.basicHouse.houseFileId, AssessDBKey.BasicHouse, '${empty basicHouse.id?0:basicHouse.id}');
            var tradingID = "${basicHouseTrading.tradingType}";
            var tradingType = null;
            AssessCommon.getDataDicInfo(tradingID, function (data) {
                tradingType = data.fieldName;
                if (tradingType == objectData.config.basicHouse.leaseID) {
                    $("#" + objectData.config.basicHouse.tradingFrm).find("." + objectData.config.basicHouse.sellID).hide();
                    $("#" + objectData.config.basicHouse.tradingFrm).find("." + objectData.config.basicHouse.leaseID).show();
                    $("#" + objectData.config.basicHouse.tableSon + "Div").show();
                    objectData.house.subLoadList(objectData.config.basicHouse.leaseID);
                }
                if (tradingType == objectData.config.basicHouse.sellID) {
                    $("#" + objectData.config.basicHouse.tradingFrm).find("." + objectData.config.basicHouse.sellID).show();
                    $("#" + objectData.config.basicHouse.tradingFrm).find("." + objectData.config.basicHouse.leaseID).hide();
                    $("#" + objectData.config.basicHouse.tradingFrm).find("#" + objectData.config.basicHouse.tableSon + "Div").hide();
                }
            });
            AssessCommon.getDataDicInfo("${basicHouseTrading.paymentMethod}", function (data) {
                if (data.name == '一次性') {
                    $("#" + objectData.config.basicHouse.tradingFrm).find("input[name='totalSale']").parent().parent().show();
                    $("#" + objectData.config.basicHouse.tradingFrm).find("#" + objectData.config.basicHouse.tableSon + "Div").hide();
                }
                if (data.name == '分期付款') {
                    $("#" + objectData.config.basicHouse.tradingFrm).find("input[name='totalSale']").parent().parent().hide();
                    $("#" + objectData.config.basicHouse.tableSon + "Div").show();
                    objectData.house.subLoadList(objectData.config.basicHouse.sellID);
                }
            });
            $.ajax({
                url: "${pageContext.request.contextPath}/basicUnitHuxing/getBasicUnitHuxingById",
                type: "get",
                dataType: "json",
                data: {id: '${empty basicHouse.huxingId?0:basicHouse.huxingId}'},
                success: function (result) {
                    if (result.ret) {
                        if (objectData.isNotBlank(result.data)) {
                            if (objectData.isNotBlank(result.data.fileViewName)) {
                                $("#" + objectData.config.basicHouse.frm).find(".house_latest_family_plan").html(result.data.fileViewName);
                            }
                        }
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        },
        subLoadList: function (type_) {
            var cols = [];
            if (type_ == objectData.config.basicHouse.leaseID) {
                cols.push({field: 'rentGrowthRate', title: '租金增长比率'});
                cols.push({field: 'rentPaymentTimeStartName', title: '租金支付时间起'});
                cols.push({field: 'rentPaymentTimeEndName', title: '租金支付时间止'});
            }
            if (type_ == objectData.config.basicHouse.sellID) {
                cols.push({field: 'instalmentInterest', title: '分期支付时间起'});
                cols.push({field: 'instalmentPeriodStartName', title: '分期支付时间止'});
                cols.push({field: 'instalmentPeriodEndName', title: '分期支付利息'});
            }
            $("#" + objectData.config.basicHouse.tableSon).bootstrapTable('destroy');
            TableInit(objectData.config.basicHouse.tableSon, "${pageContext.request.contextPath}/basicHouseTradingLeaseAndSell/getLeaseAndSellVos", cols, {
                type: type_, houseId: '${empty basicHouse.id?0:basicHouse.id}'
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        }
    };


    $(function () {
        //选项卡处理
        var basicUnit = "${basicUnit}" ;
        if (objectData.isNotBlank(basicUnit)){
            $("#profile-tab3").attr("data-toggle","tab");
            $('#caseTab a').eq(2).tab('show');
            objectData.unit.init();
        }

        var basicEstate = "${basicEstate}" ;
        if (objectData.isNotBlank(basicEstate)){
            $("#profile-tab1").attr("data-toggle","tab");
            $('#caseTab a').eq(0).tab('show');
            objectData.estate.init();
        }

        var basicHouse = "${basicHouse}" ;
        if (objectData.isNotBlank(basicHouse)){
            $("#profile-tab4").attr("data-toggle","tab");
            $('#caseTab a').eq(3).tab('show');
            objectData.house.init();
        }

        var oneBasicBuilding = "${oneBasicBuilding}" ;
        var twoBasicBuilding = "${twoBasicBuilding}" ;
        var threeBasicBuilding = "${threeBasicBuilding}" ;
        var fourBasicBuilding = "${fourBasicBuilding}" ;
        if (objectData.isNotBlank(oneBasicBuilding) || objectData.isNotBlank(twoBasicBuilding) || objectData.isNotBlank(threeBasicBuilding) || objectData.isNotBlank(fourBasicBuilding)){
            $("#profile-tab2").attr("data-toggle","tab");
            $('#caseTab a').eq(1).tab('show');
            navButtonBuild.one($("#navButtonBuild").find("button").eq(0)[0],1);
        }
    });
</script>
<script type="application/javascript">

    function saveform() {
        if (!$("#frm_approval").valid()) {
            return false;
        }
        var data = formApproval.getFormData();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApply/projectApprovalSubmit",
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
        })
    }
</script>
</html>
