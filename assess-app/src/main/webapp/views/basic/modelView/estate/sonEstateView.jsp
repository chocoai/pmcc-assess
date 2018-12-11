<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>通信网络</h4>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="estateNetwork.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="examineEstateNetworkList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>车位</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="estateParking.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="estateParkingList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>环境因素信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="matchingEnvironment.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="MatchingEnvironmentList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>金融服务信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="matchingFinance.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="MatchingFinanceList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div id="industryMatchingInfo" style="display: none;">
    <div class="x_panel">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i
                        class="fa fa-chevron-up"></i></a></li>
            </ul>
            <h4>教育条件信息</h4>
            <div class="clearfix"></div>
        </div>
        <div class="x_content collapse">
            <button type="button" class="btn btn-success" onclick="matchingEducation.prototype.showModel()"
                    data-toggle="modal" href="#divBox"> 新增
            </button>
            <table class="table table-bordered" id="MatchingEducationList">
                <!-- cerare document add ajax data-->
            </table>
        </div>
    </div>
    <div class="x_panel">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i
                        class="fa fa-chevron-up"></i></a></li>
            </ul>
            <h4>休闲娱乐信息</h4>
            <div class="clearfix"></div>
        </div>
        <div class="x_content collapse">
            <button type="button" class="btn btn-success" onclick="matchingRecreation.prototype.showModel()"
                    data-toggle="modal" href="#divBox"> 新增
            </button>
            <table class="table table-bordered" id="MatchingRecreationList">
                <!-- cerare document add ajax data-->
            </table>
        </div>
    </div>
    <div class="x_panel">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i
                        class="fa fa-chevron-up"></i></a></li>
            </ul>
            <h4>餐饮信息</h4>
            <div class="clearfix"></div>
        </div>
        <div class="x_content collapse">
            <button type="button" class="btn btn-success" onclick="matchingRestaurant.prototype.showModel()"
                    data-toggle="modal" href="#divBox"> 新增
            </button>
            <table class="table table-bordered" id="MatchingRestaurantList">
                <!-- cerare document add ajax data-->
            </table>
        </div>
    </div>
    <div class="x_panel">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i
                        class="fa fa-chevron-up"></i></a></li>
            </ul>
            <h4>购物商场信息</h4>
            <div class="clearfix"></div>
        </div>
        <div class="x_content collapse">
            <button type="button" class="btn btn-success" onclick="matchingMarket.prototype.showModel()"
                    data-toggle="modal" href="#divBox"> 新增
            </button>
            <table class="table table-bordered" id="MatchingMarketList">
                <!-- cerare document add ajax data-->
            </table>
        </div>
    </div>
    <div class="x_panel">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i
                        class="fa fa-chevron-up"></i></a></li>
            </ul>
            <h4>医养条件信息</h4>
            <div class="clearfix"></div>
        </div>
        <div class="x_content collapse">
            <button type="button" class="btn btn-success" onclick="matchingMedical.prototype.showModel()"
                    data-toggle="modal" href="#divBox"> 新增
            </button>
            <table class="table table-bordered" id="MatchingMedicalList">
                <!-- cerare document add ajax data-->
            </table>
        </div>
    </div>
</div>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>公交信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="matchingTransit.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="TransitList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>交通枢纽信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="matchingTrafficHub.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="MatchingTrafficHubList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>地铁信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="matchingMetro.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="MatchingMetroList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>主干道信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="matchingMainRoad.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="MainRoadList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>主要转换信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="matchingMainConversion.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="MatchingMainConversionList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel" id="industrySupplyWater">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>供水情况</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="estateSupplyWater.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="EstateSupplyWaterList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel" id="industryDrainWater">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>排水情况</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="estateDrainWater.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="EstateDrainWaterList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel" id="industrySupplyPower">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>供电信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="estateSupplyPower.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="EstateSupplyPowerList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel" id="industrySupplyHeating">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>供热信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="estateSupplyHeating.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="EstateSupplyHeatingList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel" id="industrySupplyGas">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>供气信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="estateSupplyGas.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="EstateSupplyGasList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel" id="industryMaterial">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>原料供应及销售条件信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="matchingMaterial.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="MatchingMaterialList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/basic/estate/sonEstateView.js"></script>

<div id="divBoxEstateSupplyGas" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">供气</h3>
            </div>
            <form id="frmEstateSupplyGas" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type" class="type">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供气商名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="name"
                                                   placeholder="供应商名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供气保障等级<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="lineGrade"
                                                    class="form-control search-select select2 lineGrade">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供气商信誉<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="reputation"
                                                    class="form-control search-select select2 reputation">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供气商等级<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="grade"
                                                    class="form-control search-select select2 grade">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供应量或功率
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="power" class="form-control" data-rule-number='true' placeholder="供应量或功率" >
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
                    <button type="button" class="btn btn-primary" onclick="estateSupplyGas.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxEstateSupplyHeating" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">供热</h3>
            </div>
            <form id="frmEstateSupplyHeating" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type" class="type">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供热商名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="name"
                                                   placeholder="供应商名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供热保障等级<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="lineGrade"
                                                    class="form-control search-select select2 lineGrade">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供热商信誉<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="reputation"
                                                    class="form-control search-select select2 reputation">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供热商等级<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="grade"
                                                    class="form-control search-select select2 grade">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供应量或功率<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="power" class="form-control" data-rule-number='true' placeholder="供应量或功率" >
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
                    <button type="button" class="btn btn-primary" onclick="estateSupplyHeating.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxEstateSupplyPower" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">供电</h3>
            </div>
            <form id="frmEstateSupplyPower" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type" class="type">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供电商名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="name"
                                                   placeholder="供应商名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供电保障等级<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="lineGrade"
                                                    class="form-control search-select select2 lineGrade">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供电商信誉<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="reputation"
                                                    class="form-control search-select select2 reputation">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供电商等级<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="grade"
                                                    class="form-control search-select select2 grade">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供应量或功率
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="power" class="form-control" data-rule-number='true' placeholder="供应量或功率" >
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
                    <button type="button" class="btn btn-primary" onclick="estateSupplyPower.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxEstateSupplyWater" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">供水</h3>
            </div>
            <form id="frmEstateSupplyWater" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type" class="type">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供水商名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="name"
                                                   placeholder="供应商名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供水保障等级<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="lineGrade"
                                                    class="form-control search-select select2 lineGrade">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供水商信誉<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="reputation"
                                                    class="form-control search-select select2 reputation">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供水商等级<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="grade"
                                                    class="form-control search-select select2 grade">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            供应量或功率
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="power" class="form-control" data-rule-number='true' placeholder="供应量或功率" >
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
                    <button type="button" class="btn btn-primary" onclick="estateSupplyWater.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxEstateDrainWater" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">排水</h3>
            </div>
            <form id="frmEstateDrainWater" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type" class="type">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            废水处理商名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="name"
                                                   placeholder="废水处理商名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            排水保障等级<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="lineGrade"
                                                    class="form-control search-select select2 lineGrade">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            废水处理商信誉<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="reputation"
                                                    class="form-control search-select select2 reputation">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            废水处理商等级<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="grade"
                                                    class="form-control search-select select2 grade">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            排水量或功率
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="power" class="form-control" data-rule-number='true' placeholder="排水量或功率" >
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
                    <button type="button" class="btn btn-primary" onclick="estateDrainWater.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxEstateParking" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">车位</h3>
            </div>
            <form id="frmEstateParking" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            车位位置
                                        </label>
                                        <div class="col-sm-10">
                                            <select class="form-control search-select select2 location"
                                                    name="location">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            车位类型
                                        </label>
                                        <div class="col-sm-10">
                                            <select class="form-control search-select select2 parkingType"
                                                    name="parkingType">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            车位数量
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="number"
                                                   placeholder="车位数量" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            平面图
                                        </label>
                                        <div class="col-sm-10">
                                            <input id="house_estateParking" name="house_estateParking"
                                                   placeholder="上传附件" class="form-control"
                                                   type="file">
                                            <div id="_house_estateParking"></div>
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
                    <button type="button" class="btn btn-primary" onclick="estateParking.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxExamineEstateNetwork" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">通信网络</h3>
            </div>
            <form id="frmExamineEstateNetwork" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            通信网络名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="name"
                                                   placeholder="通信网络名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            服务内容<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="serviceContent"
                                                   placeholder="服务内容" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            通信网络指标参数<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="indexParameter"
                                                   placeholder="通信网络指标参数" required="required">
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
                    <button type="button" class="btn btn-primary" onclick="estateNetwork.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxMatchingEducation" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">教育条件</h3>
            </div>
            <form id="frmMatchingEducation" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type" class="type">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            学校名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="schoolName"
                                                   placeholder="学校名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            学校性质<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="schoolNature"
                                                    class="form-control search-select select2 schoolNature">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            学校级次<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="schoolGradation"
                                                    class="form-control search-select select2 schoolGradation">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            学校等级<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="schoolLevel"
                                                    class="form-control search-select select2 schoolLevel">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            距离<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="distance"
                                                    class="form-control search-select select2 distance">
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
                    <button type="button" class="btn btn-primary" onclick="matchingEducation.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxMatchingEnvironment" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">环境因素</h3>
            </div>
            <form id="frmMatchingEnvironment" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            环境类型<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="type"
                                                    class="form-control search-select select2 type">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            影响因素<span class="symbol required"></span>
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
                                            影响程度<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="influenceDegree"
                                                    class="form-control search-select select2 influenceDegree">
                                            </select>
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
                    <button type="button" class="btn btn-primary" onclick="matchingEnvironment.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxMatchingFinance" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">金融服务</h3>
            </div>
            <form id="frmMatchingFinance" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            金融名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="name" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            金融类别<span class="symbol required"></span>
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
                                            金融机构性质<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="nature"
                                                    class="form-control search-select select2 nature">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            服务类别<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="serviceContent"
                                                    class="form-control search-select select2 serviceContent">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            服务内容
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea class="form-control" name="autoServiceContent"></textarea>
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
                    <button type="button" class="btn btn-primary" onclick="matchingFinance.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>


<div id="divBoxMatchingRecreation" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">休闲娱乐</h3>
            </div>
            <form id="frmMatchingRecreation" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            休闲娱乐名称
                                        </label>
                                        <div class="col-sm-10">
                                            <button class="btn btn-xs btn-success"
                                                    onclick="matchingRecreation.prototype.appendHTML('name',this)"><i
                                                    class="fa fa-plus"></i></button>
                                        </div>
                                    </div>
                                </div>

                                <div style="margin-bottom: 8px;" class="name">
                                    <div class="form-group" style=" margin-top: 8px;">
                                        <div class="x-valid">
                                            <label class="col-md-2 control-label">休闲娱乐名称</label>
                                            <div class="col-sm-10 ">
                                                <div class="input-group">
                                                    <input class="form-control" name="name" required="required"
                                                           type="text">
                                                    <span class="input-group-btn">
                                                         <input type="button" class="btn btn-warning" value="X"
                                                                onclick="matchingRecreation.prototype.cleanHTMLData(this)"></span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            休闲娱乐类别<span class="symbol required"></span>
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
                                            休闲娱乐距离<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="distance"
                                                    class="form-control search-select select2 distance">
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
                    <button type="button" class="btn btn-primary" onclick="matchingRecreation.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxMatchingRestaurant" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">餐饮</h3>
            </div>
            <form id="frmMatchingRestaurant" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            餐饮名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <button class="btn btn-xs btn-success"
                                                    onclick="matchingRestaurant.prototype.appendHTML('name',this)"><i
                                                    class="fa fa-plus"></i></button>
                                        </div>
                                    </div>
                                </div>
                                <div style="margin-bottom: 8px;" class="name">
                                    <div class="form-group" style=" margin-top: 8px;">
                                        <label class="col-md-2 col-sm-2 col-xs-12 control-label">餐饮名称</label>
                                        <div class="col-md-10 col-sm-10 col-xs-12 input-group">
                                            <input class="form-control" name="name" required="required" type="text">
                                            <span class="input-group-btn"><input type="button" class="btn btn-warning"
                                                                                 value="X"
                                                                                 onclick="matchingRestaurant.prototype.cleanHTMLData(this)"></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            餐饮类别<span class="symbol required"></span>
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
                                            餐饮档次<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="grade"
                                                    class="form-control search-select select2 grade">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            餐饮距离<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="distance"
                                                    class="form-control search-select select2 distance">
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
                    <button type="button" class="btn btn-primary" onclick="matchingRestaurant.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxMatchingMarket" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">购物商场</h3>
            </div>
            <form id="frmMatchingMarket" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            购物商场名称
                                        </label>
                                        <div class="col-sm-10">
                                            <button class="btn btn-xs btn-success"
                                                    onclick="matchingMarket.prototype.appendHTML('name',this)"><i
                                                    class="fa fa-plus"></i></button>
                                        </div>
                                    </div>
                                </div>

                                <div style="margin-bottom: 8px;" class="name">
                                    <div class="form-group" style=" margin-top: 8px;">
                                        <label class="col-md-2 col-sm-2 col-xs-12 control-label">购物商场名称</label>
                                        <div class="col-md-10 col-sm-10 col-xs-12 input-group">
                                            <input class="form-control" name="name" required="required" type="text">
                                            <span class="input-group-btn"><input type="button" class="btn btn-warning"
                                                                                 value="X"
                                                                                 onclick="matchingMarket.prototype.cleanHTMLData(this)"></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            购物商场类别<span class="symbol required"></span>
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
                                            购物商场档次<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="grade"
                                                    class="form-control search-select select2 grade">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            购物商场距离<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="distance"
                                                    class="form-control search-select select2 distance">
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
                    <button type="button" class="btn btn-primary" onclick="matchingMarket.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxMatchingMaterial" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">原料供应及销售条件</h3>
            </div>
            <form id="frmMatchingMaterial" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
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
                                            规模<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="scale"
                                                    class="form-control search-select select2 scale">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            距离<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="distance"
                                                    class="form-control search-select select2 distance">
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
                    <button type="button" class="btn btn-primary" onclick="matchingMaterial.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxMatchingMedical" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">医养条件</h3>
            </div>
            <form id="frmMatchingMedical" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            机构名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="organizationName" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            机构等级<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="organizationLevel"
                                                    class="form-control search-select select2 organizationLevel">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            机构床位数<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="bedNumber"
                                                    class="form-control search-select select2 bedNumber">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            机构距离<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="distance"
                                                    class="form-control search-select select2 distance">
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
                    <button type="button" class="btn btn-primary" onclick="matchingMedical.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxTransit" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">公交</h3>
            </div>
            <form id="frmTransit" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type" class="type">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            公交站名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="name"
                                                   placeholder="名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            距大门距离<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="distance"
                                                    class="form-control search-select select2 distance">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            所在线路<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <button class="btn btn-xs btn-success"
                                                    onclick="matchingTransit.prototype.appendHTML('theLine',this)"><i
                                                    class="fa fa-plus"></i></button>
                                        </div>
                                    </div>
                                </div>

                                <div style="margin-bottom: 8px;" class="theLine">
                                    <div class="form-group" style=" margin-top: 8px;">
                                        <label class="col-md-2 col-sm-2 col-xs-12 control-label">所在线路</label>
                                        <div class="col-md-10 col-sm-10 col-xs-12 input-group">
                                            <input class="form-control" name="theLine" required="required" type="text">
                                            <span class="input-group-btn">
                                                <input type="button" class="btn btn-warning" value="X"
                                                       onclick="matchingTransit.prototype.cleanHTMLData(this)">
                                            </span>
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
                    <button type="button" class="btn btn-primary" onclick="matchingTransit.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxMatchingTrafficHub" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">交通枢纽</h3>
            </div>
            <form id="frmMatchingTrafficHub" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type" class="type">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="name"
                                                   placeholder="名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            性质<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="nature"
                                                    class="form-control search-select select2 nature">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            距离<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="distance"
                                                    class="form-control search-select select2 distance">
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
                    <button type="button" class="btn btn-primary" onclick="matchingTrafficHub.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxMatchingMetro" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">地铁</h3>
            </div>
            <form id="frmMatchingMetro" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type" class="type">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            地铁站名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="name"
                                                   placeholder="名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            距大门距离<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="distance"
                                                    class="form-control search-select select2 distance">
                                            </select>
                                        </div>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            所在线路<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <button class="btn btn-xs btn-success"
                                                    onclick="matchingMetro.prototype.appendHTML('theLine',this)"><i
                                                    class="fa fa-plus"></i></button>
                                        </div>
                                    </div>
                                </div>

                                <div style="margin-bottom: 8px;" class="theLine">
                                    <div class="form-group" style=" margin-top: 8px;">
                                        <label class="col-md-2 col-sm-2 col-xs-12 control-label">所在线路</label>
                                        <div class="col-md-10 col-sm-10 col-xs-12 input-group">
                                            <input class="form-control" name="theLine" required="required" type="text">
                                            <span class="input-group-btn">
                                                <input type="button" class="btn btn-warning" value="X"
                                                       onclick="matchingMetro.prototype.cleanHTMLData(this)">
                                            </span>
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
                    <button type="button" class="btn btn-primary" onclick="matchingMetro.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxMainRoad" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">主干道</h3>
            </div>
            <form id="frmMainRoad" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type" class="type">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="name"
                                                   placeholder="名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            距离<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="distance"
                                                    class="form-control search-select select2 distance">
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
                    <button type="button" class="btn btn-primary" onclick="matchingMainRoad.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxMatchingMainConversion" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">主要转换互通桥</h3>
            </div>
            <form id="frmMatchingMainConversion" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="type" class="type">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="name"
                                                   placeholder="名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            距离<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="distance"
                                                    class="form-control search-select select2 distance">
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
                    <button type="button" class="btn btn-primary" onclick="matchingMainConversion.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>