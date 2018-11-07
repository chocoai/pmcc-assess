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
                            <li role="presentation" class=""><a href="#basicEstate" role="tab" id="profile-tab1"
                                                                data-toggle="tab" aria-expanded="true"
                                                                onclick="">楼盘</a>
                            </li>
                            <li role="presentation" class=""><a href="#caseBuild" role="tab" id="profile-tab2"
                                                                data-toggle="tab" aria-expanded="false"
                                                                onclick="">楼栋</a>
                            </li>
                            <li role="presentation" class=""><a href="#caseUnit" role="tab" id="profile-tab3"
                                                                data-toggle="tab" aria-expanded="false"
                                                                onclick="">单元</a>
                            </li>
                            <li role="presentation" class=""><a href="#caseHouse" role="tab" id="profile-tab4"
                                                                data-toggle="tab" aria-expanded="false"
                                                                onclick="">房屋</a>
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
                                                    <input type="text" placeholder="省" readonly="readonly"
                                                           name="provinceName" class="form-control"
                                                           value="${basicEstate.provinceName}">
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">市</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="市" readonly="readonly"
                                                           name="cityName" class="form-control"
                                                           value="${basicEstate.cityName}">
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">县</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="县" readonly="readonly"
                                                           name="districtName" class="form-control"
                                                           value="${basicEstate.districtName}">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">楼盘名称</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="楼盘名称" readonly="readonly"
                                                           name="name" class="form-control" value="${basicEstate.name}">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">楼盘方位</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="楼盘方位" readonly="readonly"
                                                           name="position" class="form-control"
                                                           value="${basicEstate.position}">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">基础版块</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="楼盘方位" readonly="readonly"
                                                           name="position" class="form-control"
                                                           value="${basicEstate.blockName}">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">编号</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="编号(请输入数字)" name="number"
                                                           readonly="readonly"
                                                           class="form-control" value="${basicEstate.number}">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">土地级别</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="土地级别" name="number"
                                                           readonly="readonly"
                                                           class="form-control" value="${basicEstate.landLevelName}">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">街道</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="街道" readonly="readonly"
                                                           name="street" class="form-control"
                                                           value="${basicEstate.street}">
                                                </div>
                                            </div>

                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">占地面积</label>
                                                <div class="col-sm-3">
                                                    <input type="text" readonly="readonly"
                                                           placeholder="占地面积" name="coverAnArea" class="form-control"
                                                           value="${basicEstate.coverAnArea}">
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">容积率</label>
                                                <div class="col-sm-3">
                                                    <input type="text" readonly="readonly"
                                                           placeholder="容积率" value="${basicEstate.volumetricRate}"
                                                           name="volumetricRate" class="form-control">
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">绿化率</label>
                                                <div class="col-sm-3">
                                                    <input type="text" readonly="readonly"
                                                           placeholder="绿化率" value="${basicEstate.greeningRate}"
                                                           name="greeningRate" class="form-control">
                                                </div>
                                            </div>
                                        </div>


                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">楼盘概况</label>
                                                <div class="col-sm-11">
                                                    <textarea class="form-control" readonly="readonly"
                                                              name="description"
                                                              placeholder="楼盘概况">${basicEstate.description}</textarea>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">楼栋数</label>
                                                <div class="col-sm-3">
                                                    <input type="text"
                                                           placeholder="楼栋数" readonly="readonly" name="buildingNumber"
                                                           class="form-control" value="${basicEstate.buildingNumber}">
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">开发商</label>
                                                <div class="col-sm-3">
                                                    <input type="text"
                                                           placeholder="开发商" readonly="readonly" name="developerName"
                                                           class="form-control" value="${basicEstate.developerName}">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">建筑面积</label>
                                                <div class="col-sm-3">
                                                    <input type="text"
                                                           placeholder="建筑面积" readonly="readonly" name="floorArea"
                                                           class="form-control" value="${basicEstate.floorArea}">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">均价</label>
                                                <div class="col-sm-3">
                                                    <input type="text"
                                                           placeholder="均价(请输入数字)" name="averagePrice"
                                                           readonly="readonly"
                                                           class="form-control" value="${basicEstate.averagePrice}">
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">价格区间</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="价格区间"
                                                           name="priceRange" readonly="readonly" class="form-control"
                                                           value="${basicEstate.priceRange}">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">附号</label>
                                                <div class="col-sm-3">
                                                    <input type="text"
                                                           placeholder="附号(请输入数字)" value="${basicEstate.attachNumber}"
                                                           name="attachNumber" readonly="readonly" class="form-control">
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
                                        <input type="hidden" name="id">
                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">单元编号</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="单元编号" readonly="readonly"
                                                           name="unitNumber" class="form-control"
                                                           value="${basicUnit.unitNumber}">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">户梯比</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="户梯比" readonly="readonly"
                                                           name="elevatorHouseholdRatio" class="form-control"
                                                           value="${basicUnit.elevatorHouseholdRatio}">
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
                                                    <input type="text" value="${basicHouse.houseNumber}"
                                                           placeholder="房号"
                                                           name="houseNumber"
                                                           class="form-control" readonly="readonly">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">所在楼层<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <input type="text" value="${basicHouse.floor}" placeholder="所在楼层"
                                                           name="floor"
                                                           class="form-control" readonly="readonly">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">使用环境<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <input type="text" value="${basicHouse.useEnvironmentName}"
                                                           placeholder="使用环境"
                                                           name="useEnvironmentName"
                                                           class="form-control" readonly="readonly">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">户型选择<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <div class="input-group">
                                                        <input type="text"
                                                               placeholder="户型"
                                                               name="huxingName" value=""
                                                               class="form-control" readonly="readonly">
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
                                                    <input type="text" value="${basicHouse.orientation}"
                                                           placeholder="朝向"
                                                           name="orientation"
                                                           class="form-control" readonly="readonly">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">证载用途<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <input type="text" value="${basicHouse.certUseName}"
                                                           placeholder="证载用途"
                                                           name="certUseName"
                                                           class="form-control" readonly="readonly">
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">实际用途<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <input type="text" value="${basicHouse.practicalUseName}"
                                                           placeholder="实际用途"
                                                           name="practicalUseName"
                                                           class="form-control" readonly="readonly">
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">权益限制<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <input type="text" value="${basicHouse.rightInterestsRestriction}"
                                                           placeholder="权益限制"
                                                           name="rightInterestsRestriction"
                                                           class="form-control" readonly="readonly">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">房屋出租占用情况途描述</label>
                                                <div class="col-sm-11">
                                                <textarea class="form-control" name="description" readonly="readonly">
                                                    ${basicHouse.description}
                                                </textarea>
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
                                                    <input type="text" placeholder="财产范围" readonly="readonly"
                                                           name="scopeProperty"
                                                           class="form-control"
                                                           value="${basicHouseTrading.scopeProperty}">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">融资条件<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="融资条件" readonly="readonly"
                                                           name="financingConditions"
                                                           class="form-control"
                                                           value="${basicHouseTrading.financingConditions}">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">税费负担<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="税费负担" readonly="readonly"
                                                           name="taxBurdenName"
                                                           class="form-control"
                                                           value="${basicHouseTrading.taxBurdenName}">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">正常交易<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="正常交易" readonly="readonly"
                                                           name="normalTransactionName"
                                                           class="form-control"
                                                           value="${basicHouseTrading.normalTransactionName}">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">说明事项类型<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="说明事项类型" readonly="readonly"
                                                           name="descriptionTypeName"
                                                           class="form-control"
                                                           value="${basicHouseTrading.descriptionTypeName}">
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">说明事项内容<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="说明事项内容" readonly="readonly"
                                                           name="descriptionContentName"
                                                           class="form-control"
                                                           value="${basicHouseTrading.descriptionContent}">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">交易时间<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="交易时间" readonly="readonly"
                                                           name="tradingTimeName"
                                                           class="form-control"
                                                           value="${basicHouseTrading.tradingTimeName}">
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">交易类型</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="交易类型" readonly="readonly"
                                                           name="tradingTypeName"
                                                           class="form-control"
                                                           value="${basicHouseTrading.tradingTypeName}">
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">交易价格<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="交易价格" readonly="readonly"
                                                           name="tradingPrice"
                                                           class="form-control"
                                                           value="${basicHouseTrading.tradingPrice}">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group BasicHouseTradingSell" style="display: none">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">买方支付的额外税费</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="买方支付的额外税费" readonly="readonly"
                                                           name="buyerExtraTaxFee"
                                                           class="form-control"
                                                           value="${basicHouseTrading.buyerExtraTaxFee}">
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">付款方式<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="付款方式" readonly="readonly"
                                                           name="paymentMethodName"
                                                           class="form-control"
                                                           value="${basicHouseTrading.paymentMethodName}">
                                                </div>
                                            </div>

                                            <div class="x-valid" style="display: none;">
                                                <label class="col-sm-1 control-label">出售总额</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="出售总额" readonly="readonly"
                                                           name="totalSale"
                                                           class="form-control" value="${basicHouseTrading.totalSale}">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group BasicHouseTradingLease" style="display: none">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">承租方支付的额外税费</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="承租方支付的额外税费" readonly="readonly"
                                                           name="rentingExtraTaxFee"
                                                           class="form-control"
                                                           value="${basicHouseTrading.rentingExtraTaxFee}">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">押金</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="押金" readonly="readonly"
                                                           name="deposit"
                                                           class="form-control" value="${basicHouseTrading.deposit}">
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
                                                    <input type="text" placeholder="信息来源分类" readonly="readonly"
                                                           name="informationTypeName"
                                                           class="form-control"
                                                           value="${basicHouseTrading.informationTypeName}">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">信息来源</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="信息来源" readonly="readonly"
                                                           name="information"
                                                           class="form-control"
                                                           value="${basicHouseTrading.information}">
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
