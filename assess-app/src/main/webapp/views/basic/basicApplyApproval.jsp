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
                                                                onclick="objectData.estate.init()">楼盘</a>
                            </li>
                            <li role="presentation" class=""><a href="#caseBuild" role="tab" id="profile-tab2"
                                                                data-toggle="tab" aria-expanded="false"
                                                                onclick="objectData.build.init(1)">楼栋</a>
                            </li>
                            <li role="presentation" class=""><a href="#caseUnit" role="tab" id="profile-tab3"
                                                                data-toggle="tab" aria-expanded="false">单元</a>
                            </li>
                            <li role="presentation" class=""><a href="#caseHouse" role="tab" id="profile-tab4"
                                                                data-toggle="tab" aria-expanded="false">房屋</a>
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
                                                           name="unitNumber" class="form-control">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">户梯比</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="户梯比" readonly="readonly"
                                                           name="elevatorHouseholdRatio" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="caseHouse" aria-labelledby="profile-tab4">
                                <p>
                                    hjsdjsjsd </p>
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
            name: "房屋"
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
                objectData.showFile(n + "" + navButtonBuild.switchNumber,AssessDBKey.BasicBuilding, data.id);
            });
        };
        navButtonBuild.clearAll = function () {
            $("#" + objectData.config.basicBuilding.frm).clearAll();
        };
    })();


    navButtonBuild.switchNumber = 0;
    navButtonBuild.switchInit = function (target, data,number) {
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
                navButtonBuild.switchInit(target, data,number);
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
                navButtonBuild.switchInit(target, data,number);
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
                navButtonBuild.switchInit(target, data,number);
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
                navButtonBuild.switchInit(target, data,number);
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
        $("."+buildingModel.prototype.config().sonTable).html(navButtonBuild.switchNumber+"部分");
        $("."+buildingModel.prototype.config().examineBuildingSurfaceTable).html(navButtonBuild.switchNumber+"部分");
        $("."+buildingModel.prototype.config().examineBuildingMaintenanceTable).html(navButtonBuild.switchNumber+"部分");
        $("."+buildingModel.prototype.config().examineBuildingFunctionTable).html(navButtonBuild.switchNumber+"部分");
    };

    objectData.build = {
        init: function (number) {
            var target = $("#navButtonBuild button").eq(0)[0];
            navButtonBuild.one(target, number);
        }
    };
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
