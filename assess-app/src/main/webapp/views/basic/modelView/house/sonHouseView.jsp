<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>房间</h4>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" data-toggle="modal"
                onclick="houseRoom.prototype.showModel()"> 新增
        </button>
        <table class="table table-bordered" id="HouseRoomList">
        </table>
    </div>
</div>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>临街（路）状况</h4>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="houseFaceStreet.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="HouseFaceStreetList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel" id="industryCorollaryEquipment">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>房屋配套设备设施信息</h4>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="houseCorollaryEquipment.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="HouseCorollaryEquipmentList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel" id="industryIntelligent">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>电力通讯网络</h4>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="houseIntelligent.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="HouseIntelligentList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel" id="industryWater">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>供水情况</h4>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="houseWater.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="HouseWaterList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel" id="industryHouseWaterDrain">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>排水情况</h4>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="houseWaterDrain.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="HouseWaterDrainList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel" id="industryNewWind">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>新风情况</h4>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="houseNewWind.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="HouseNewWindList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel" id="industryAirConditioner">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>空调情况</h4>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="houseAirConditioner.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="HouseAirConditionerList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel" id="industryHeating">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>供暖情况</h4>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="houseHeating.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="HouseHeatingList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<%@include file="/views/basic/modelView/house/damagedDegreeView.jsp" %>

<div class="x_content">
    <form class="form-horizontal">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    其它附件
                </label>
                <div class="col-sm-11">
                    <input id="otherFile" type="file" multiple="false">
                    <div id="_otherFile"></div>
                </div>
            </div>
        </div>
    </form>
</div>

<script src="${pageContext.request.contextPath}/js/basic/house/sonHouseView.js"></script>

<div id="divBoxHouseRoom" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">房间</h3>
            </div>
            <form id="frmHouseRoom" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            房间类型<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <select  name="roomType" class="form-control search-select select2 roomType" required>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            面积
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="面积" name="area" data-rule-number='true'
                                                   class="form-control" >
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label"
                                               title="层高通常指下层地板面或楼板面到上层楼板面之间的距离。层高减去楼板的厚度或结构层的高度的差,叫做净高。出于降低成本、节约建材和节约土地等考虑,一般住宅层高都在28米左右">
                                            层高(m)
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" data-rule-number="true" placeholder="层高(m)"
                                                   name="layerHeight" class="form-control">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label"
                                               title="层高通常指下层地板面或楼板面到上层楼板面之间的距离。层高减去楼板的厚度或结构层的高度的差,叫做净高。出于降低成本、节约建材和节约土地等考虑,一般住宅层高都在28米左右">
                                            净高(m)
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" data-rule-number="true" placeholder="净高(m)"
                                                   name="clearHeight"
                                                   class="form-control"
                                            >
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            开间/宽(m)
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" data-rule-number="true" placeholder="开间/宽(m)"
                                                   name="opening" class="form-control"
                                            >
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            进深/长(m)
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" data-rule-number="true" placeholder="进深/长(m)"
                                                   name="depth" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            通风
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="通风" name="aeration" class="form-control">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            日照
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="日照" name="sunshine" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            采光
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="采光" name="lighting" class="form-control">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            隔音
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="隔音" name="soundInsulation" class="form-control">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="houseRoom.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="SubclassDivBoxHouseRoom" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="titleContent">房间装修数据</h3>
                <input type="hidden" name="roomId" class="roomId">
            </div>
            <div class="panel-body">
                <span id="toolbarSub">
                    <button type="button" class="btn btn-success"
                            onclick="houseRoom.prototype.showModelSubclassSaveView()"
                            data-toggle="modal" href="#divSubDataDicManage"> 新增
                    </button>
                </span>
                <table class="table table-bordered" id="SubclassHouseRoomList">
                </table>
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
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">房间装修</h3>
            </div>
            <form id="SubclassFrmHouseRoom" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="roomId" class="roomId">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修部位<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="part" class="form-control  part">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            部位描述
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea name="remark" class="form-control"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修材料<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="material" class="form-control  material">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            施工工艺<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="constructionTechnology"
                                                    class="form-control constructionTechnology">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修材料价格区间<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="materialPrice"
                                                    class="form-control materialPrice">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="houseRoom.prototype.subclassSave()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxHouseWater" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">供水情况</h3>
            </div>
            <form id="frmHouseWater" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            给水方式<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="supplyMode" required
                                                    class="form-control search-select select2 supplyMode">

                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            给水管道布置<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="pipingLayout" required
                                                    class="form-control search-select select2 pipingLayout">

                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            给水管材料<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="pipeMaterial" required
                                                    class="form-control search-select select2 pipeMaterial">

                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            给水升压设备
                                        </label>
                                        <div class="col-sm-10">
                                            <select  name="boosterEquipment" class="form-control search-select select2 boosterEquipment">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            前置净水
                                        </label>
                                        <div class="col-sm-10">
                                            <select  name="pretreatedWater" class="form-control search-select select2 pretreatedWater">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            前置净水设备价格区间
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="purificationEquipmentPrice" class="form-control search-select select2 purificationEquipmentPrice">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            消防给水
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="fireWaterSupply" class="form-control search-select select2 fireWaterSupply">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="houseWater.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxHouseWaterDrain" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">排水情况</h3>
            </div>
            <form id="frmHouseWaterDrain" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            排水系统<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required name="drainSystem"
                                                    class="form-control search-select select2 drainSystem">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            类别<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required name="type"
                                                    class="form-control search-select select2 type">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            排水处理方式<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="processingMode"
                                                    class="form-control search-select select2 processingMode">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="houseWaterDrain.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>


<div id="divBoxHouseIntelligent" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">电力通讯网络</h3>
            </div>
            <form id="frmHouseIntelligent" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            开关回路<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="switchCircuit"
                                                    class="form-control search-select select2 switchCircuit">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            铺设方式<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="layingMethod"
                                                    class="form-control search-select select2 layingMethod">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            灯具
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="lampsLanterns" multiple="multiple"
                                                    class="form-control search-select select2 lampsLanterns">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            智能系统<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <button class="btn btn-xs btn-success"
                                                    onclick="houseIntelligent.prototype.appendHTML('',this)"><i
                                                    class="fa fa-plus"></i></button>
                                        </div>
                                    </div>
                                </div>
                                <div style="margin-bottom: 8px;" class="system">
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-2 control-label">
                                                智能系统
                                            </label>
                                            <div class="col-sm-3">
                                                <select  name="intelligentSystem1" class="form-control search-select select2 intelligentSystem1">
                                                </select>
                                            </div>
                                            <div class="col-sm-3">
                                                <select  name="layingMethod1" class="form-control search-select select2 layingMethod1">
                                                </select>
                                            </div>
                                            <div class="col-sm-4">
                                                <input type="button" class="btn btn-warning" value="X"
                                                       onclick="houseIntelligent.prototype.cleanHTMLData(this)">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            描述
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea name="remark" class="form-control"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="houseIntelligent.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxHouseFaceStreet" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">临街（路）状况</h3>
            </div>
            <form id="frmHouseFaceStreet" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            街道（道路）名称
                                        </label>
                                        <div class="col-sm-10">
                                            <button class="btn btn-xs btn-success"
                                                    onclick="houseFaceStreet.prototype.appendHTML('streetName',this)"><i
                                                    class="fa fa-plus"></i></button>
                                        </div>
                                    </div>
                                </div>

                                <div style="margin-bottom: 8px;" class="name">
                                    <div class="form-group" style=" margin-top: 8px;">
                                        <label class="col-md-2 col-sm-2 col-xs-12 control-label">街道（道路）名称</label>
                                        <div class="col-md-10 col-sm-10 col-xs-12 input-group">
                                            <input class="form-control" name="streetName" required="required"
                                                   type="text">
                                            <span class="input-group-btn"><input type="button" class="btn btn-warning"
                                                                                 value="X"
                                                                                 onclick="houseFaceStreet.prototype.cleanHTMLData(this)"></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            街道级别<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="streetLevel"
                                                    class="form-control search-select select2 streetLevel">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            人流量<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="visitorsFlowrate"
                                                    class="form-control search-select select2 visitorsFlowrate">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            交通流量<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="trafficFlow"
                                                    class="form-control search-select select2 trafficFlow">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="houseFaceStreet.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxHouseCorollaryEquipment" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">房屋配套设备设施</h3>
            </div>
            <form id="frmHouseCorollaryEquipment" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            类型<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="type"
                                                    class="form-control type">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            类别<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="category" class="form-control category">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="name" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            参数指标<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="parameterIndex" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            维护状况<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="maintenanceStatus" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            设备用途<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="equipmentUse" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            价格<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="price" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input id="positionDiagramFileID" placeholder="上传附件" class="form-control"
                                                   type="file">
                                            <div id="_positionDiagramFileID"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary"
                            onclick="houseCorollaryEquipment.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxHouseNewWind" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">新风情况</h3>
            </div>
            <form id="frmHouseNewWind" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            设备品牌<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="equipment" placeholder="设备品牌" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            类别<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="category"
                                                    class="form-control search-select select2 category">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            设备价格区间<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="equipmentPrice"
                                                    class="form-control search-select select2 equipmentPrice">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="houseNewWind.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxHouseAirConditioner" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">空调情况</h3>
            </div>
            <form id="frmHouseAirConditioner" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            设备品牌<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="equipment" placeholder="设备品牌" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            类别<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="category"
                                                    class="form-control search-select select2 category">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            设备价格区间<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="equipmentPrice"
                                                    class="form-control search-select select2 equipmentPrice">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="houseAirConditioner.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxHouseHeating" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">供暖情况</h3>
            </div>
            <form id="frmHouseHeating" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            设备品牌<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="equipment" placeholder="设备品牌" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            类别<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="category"
                                                    class="form-control search-select select2 category">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            设备价格区间<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="equipmentPrice"
                                                    class="form-control search-select select2 equipmentPrice">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="houseHeating.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="application/javascript">

    $(function () {

        FileUtils.uploadFiles({
            target: "otherFile",
            onUpload: function (file) {//上传之前触发
                var formData = {
                    tableName: AssessDBKey.BasicHouse,
                    fieldsName: "otherFile",
                    tableId: houseCommon.getHouseId()
                };
                return formData;
            },
            onUploadComplete: function () {
                loadFile();
            }
        });
    })

    //附件
    function loadFile() {
        FileUtils.getFileShows({
            target: "otherFile",
            formData: {
                tableName: AssessDBKey.BasicHouse,
                fieldsName: "otherFile",
                tableId: houseCommon.getHouseId()
            },
            deleteFlag: true
        });
    }
</script>