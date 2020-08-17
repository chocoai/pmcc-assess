<%--
 房间
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    房间
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body" style="display: none">
            <form class="form-horizontal">
                <p>
                    <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                            data-toggle="modal" onclick="houseRoom.prototype.showModel()">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                        新增
                    </button>
                    <button style="margin-left: 5px" class="btn btn-primary btn-sm" type="button"
                            data-toggle="modal" onclick="houseRoom.prototype.autoGenerate()">
                        自动生成
                    </button>
                </p>
                <table class="table table-bordered" id="HouseRoomList">
                    <!-- cerare document add ajax data-->
                </table>
            </form>
        </div>
    </div>
</div>
<div id="divBoxHouseRoom" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 80%">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">房间</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmHouseRoom" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body content">
                                <div style="display: none" class="row form-group common">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <div class="input-group">
                                                    <input type="text" required name="name"
                                                           value="${basicHouseHuxing.name}"
                                                           class="form-control form-control-sm">

                                                    <div class="input-group-append">
                                                        <button class="btn btn-warning btn-sm dropdown-toggle"
                                                                type="button" data-toggle="dropdown"
                                                                aria-haspopup="true" aria-expanded="false">选择
                                                        </button>
                                                        <div class="dropdown-menu" id="nameList">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                房间形状<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <select id='type' name='houseShape'
                                                        onchange="houseRoom.prototype.houseShapeChange()"
                                                        class='form-control input-full' required>
                                                    <option value="">-请选择-</option>
                                                    <option value="矩形">矩形</option>
                                                    <option value="正方形">正方形</option>
                                                    <option value="L型">L型</option>
                                                    <option value="方形弧形组合">方形弧形组合</option>
                                                    <option value="规则多边形">规则多边形</option>
                                                    <option value="不规则">不规则</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group common">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                形状说明<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="形状说明" required
                                                       name="shapeRemark" class="form-control input-full">
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                使用面积(m²)
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="number" data-rule-number="true" placeholder="使用面积"
                                                       name="area" class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group common">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                层高(m)
                                            </label>
                                            <div class="col-sm-4">
                                                <div class="input-group">
                                                    <input type="number" data-rule-number="true" placeholder="层高"
                                                           name="layerHeight" class="form-control form-control-sm">
                                                    <div class="input-group-append">
                                                        <button class="btn btn-warning btn-sm dropdown-toggle"
                                                                type="button" data-toggle="dropdown"
                                                                aria-haspopup="true" aria-expanded="false">选择
                                                        </button>
                                                        <div class="dropdown-menu layerHeightList" >
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                净高(m)
                                            </label>
                                            <div class="col-sm-4">
                                                <div class="input-group">
                                                    <input type="number" placeholder="净高" name="clearHeight"
                                                           data-rule-number="true" class="form-control form-control-sm">
                                                    <div class="input-group-append">
                                                        <button class="btn btn-warning btn-sm dropdown-toggle"
                                                                type="button" data-toggle="dropdown"
                                                                aria-haspopup="true" aria-expanded="false">选择
                                                        </button>
                                                        <div class="dropdown-menu" id="clearHeightList">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group currentFloor">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                所在楼层
                                            </label>
                                            <div class="col-sm-4  common">
                                                <input type="text" placeholder="所在楼层" name="currentFloor"
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group common">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                特殊因素
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="特殊因素"
                                                       name="specialFactors" class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10">
                                <%--住宅，办公--%>
                                <div style="display: none" class="row form-group residence base">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                通风
                                            </label>
                                            <div class="col-sm-4">
                                                <div class="input-group">
                                                    <input type="text" placeholder="通风" name="aeration"
                                                           class="form-control form-control-sm">
                                                    <div class="input-group-append">
                                                        <button class="btn btn-warning btn-sm dropdown-toggle"
                                                                type="button" data-toggle="dropdown"
                                                                aria-haspopup="true" aria-expanded="false">选择
                                                        </button>
                                                        <div class="dropdown-menu" id="residenceAerationList">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                采光
                                            </label>
                                            <div class="col-sm-4">
                                                <div class="input-group">
                                                    <input type="text" placeholder="采光" name="lighting"
                                                           class="form-control form-control-sm">
                                                    <div class="input-group-append">
                                                        <button class="btn btn-warning btn-sm dropdown-toggle"
                                                                type="button" data-toggle="dropdown"
                                                                aria-haspopup="true" aria-expanded="false">选择
                                                        </button>
                                                        <div class="dropdown-menu" id="residenceLightingList">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group residence base">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                日照
                                            </label>
                                            <div class="col-sm-4">
                                                <div class="input-group">
                                                    <input type="text" placeholder="日照" name="sunshine"
                                                           class="form-control form-control-sm">
                                                    <div class="input-group-append">
                                                        <button class="btn btn-warning btn-sm dropdown-toggle"
                                                                type="button" data-toggle="dropdown"
                                                                aria-haspopup="true" aria-expanded="false">选择
                                                        </button>
                                                        <div class="dropdown-menu" id="residenceSunshineList">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                隔音
                                            </label>
                                            <div class="col-sm-4">
                                                <div class="input-group">
                                                    <input type="text" placeholder="隔音" name="soundInsulation"
                                                           class="form-control form-control-sm">
                                                    <div class="input-group-append">
                                                        <button class="btn btn-warning btn-sm dropdown-toggle"
                                                                type="button" data-toggle="dropdown"
                                                                aria-haspopup="true" aria-expanded="false">选择
                                                        </button>
                                                        <div class="dropdown-menu" id="residenceSoundInsulationList">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group residence rule">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                长度
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="长度" name="length"
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                宽度
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="宽度" name="width"
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group residence unruled">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                最长
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="最长" name="length"
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                最宽
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="最宽" name="width"
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%-- 餐饮酒店--%>
                                <div style="display: none;" class="row form-group hotel base">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                通风
                                            </label>
                                            <div class="col-sm-4">
                                                <div class="input-group">
                                                    <input type="text" placeholder="通风" name="aeration"
                                                           class="form-control form-control-sm">
                                                    <div class="input-group-append">
                                                        <button class="btn btn-warning btn-sm dropdown-toggle"
                                                                type="button" data-toggle="dropdown"
                                                                aria-haspopup="true" aria-expanded="false">选择
                                                        </button>
                                                        <div class="dropdown-menu" id="hotelAerationList">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                采光
                                            </label>
                                            <div class="col-sm-4">
                                                <div class="input-group">
                                                    <input type="text" placeholder="采光" name="lighting"
                                                           class="form-control form-control-sm">
                                                    <div class="input-group-append">
                                                        <button class="btn btn-warning btn-sm dropdown-toggle"
                                                                type="button" data-toggle="dropdown"
                                                                aria-haspopup="true" aria-expanded="false">选择
                                                        </button>
                                                        <div class="dropdown-menu" id="hotelLightingList">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group hotel rule">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                长度
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="长度" name="length"
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                宽度
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="宽度" name="width"
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group hotel unruled">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                最长
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="最长" name="length"
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                最宽
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="最宽" name="width"
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%-- 商铺、商场--%>
                                <div style="display: none" class="row form-group store rule">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                开间
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="开间" name="opening"
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                进深
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="进深" name="depth"
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group store unruled">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                最大开间
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="最大开间" name="opening"
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                最小进深
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="最小进深" name="depth"
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group store base">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                相邻位置
                                            </label>
                                            <div class="col-sm-4">
                                                <select class="form-control input-full adjacentPosition"
                                                        name="adjacentPosition">
                                                </select>
                                            </div>

                                            <label class="col-sm-2 control-label">
                                                距离(m)
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="距离" name="distance"
                                                       class="form-control input-full">
                                            </div>
                                            <div class="col-sm-1">
                                                <button class="btn btn-sm btn-success" type="button"
                                                        onclick="houseRoom.prototype.appendHTML()"><i
                                                        class="fa fa-plus"></i></button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="streetNumbers store base">

                                </div>
                                <div style="display: none" class="row form-group store base">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                方位
                                            </label>
                                            <div class="col-sm-4">
                                                <select class="form-control input-full  orientation"
                                                        name="orientation">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%--生产--%>
                                <div style="display: none" class="row form-group production base">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                跨长
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="跨长" name="spanLength"
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                跨数
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="跨数" name="spanNum"
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group production base">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                通风
                                            </label>
                                            <div class="col-sm-4">
                                                <div class="input-group">
                                                    <input type="text" placeholder="通风" name="aeration"
                                                           class="form-control form-control-sm">
                                                    <div class="input-group-append">
                                                        <button class="btn btn-warning btn-sm dropdown-toggle"
                                                                type="button" data-toggle="dropdown"
                                                                aria-haspopup="true" aria-expanded="false">选择
                                                        </button>
                                                        <div class="dropdown-menu" id="productionAerationList">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                采光
                                            </label>
                                            <div class="col-sm-4">
                                                <div class="input-group">
                                                    <input type="text" placeholder="采光" name="lighting"
                                                           class="form-control form-control-sm">
                                                    <div class="input-group-append">
                                                        <button class="btn btn-warning btn-sm dropdown-toggle"
                                                                type="button" data-toggle="dropdown"
                                                                aria-haspopup="true" aria-expanded="false">选择
                                                        </button>
                                                        <div class="dropdown-menu" id="productionLightingList">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group production unruled">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                最大跨距
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="最大跨距" name="maxSpan"
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                最小跨距
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="最小跨距" name="minSpan"
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group production rule">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                标准跨距
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="标准跨距" name="standardSpan"
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%-- 仓储--%>
                                <div style="display: none" class="row form-group storage base">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                计量标准
                                            </label>
                                            <div class="col-sm-4">
                                                <select class="form-control input-full  standardMeasure"
                                                        name="standardMeasure">
                                                </select>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                仓储要求
                                            </label>
                                            <div class="col-sm-4">
                                                <select class="form-control input-full  storageRequest"
                                                        name="storageRequest">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="houseRoomFilePart"></div>
                            </div>
                            <%--<div class="card-body">--%>
                            <%--<div id="houseRoomFilePart"></div>--%>
                            <%--</div>--%>
                        </div>
                    </div>

                </form>

            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="houseRoom.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


<div id="SubclassDivBoxHouseRoom" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">房间装修数据</h4>
                <input type="hidden" name="roomId" class="roomId">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card-body">
                            <p id="toolbarSub">
                                <button type="button" class="btn btn-success btn-sm"
                                        onclick="houseRoom.prototype.showModelSubclassSaveView()"
                                        data-toggle="modal" href="#divSubDataDicManage">
                        <span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                    新增
                                </button>
                            </p>
                            <table class="table table-bordered" id="SubclassHouseRoomList">
                            </table>


                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>

        </div>
    </div>
</div>

<div id="boxSubclassSaveViewHouseRoom" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">房间装修</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="SubclassFrmHouseRoom" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="roomId" class="roomId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                装修部位<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select required="required" name="part"
                                                        class="form-control input-full  part">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                部位描述
                                            </label>
                                            <div class="col-sm-10">
                                                <textarea name="remark" class="form-control input-full"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                装修材料
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="material" class="form-control input-full  material">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                施工工艺
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="constructionTechnology"
                                                        class="form-control input-full constructionTechnology">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                装修材料价格区间
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="materialPrice"
                                                        class="form-control input-full materialPrice">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="houseRoom.prototype.subclassSave()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>



