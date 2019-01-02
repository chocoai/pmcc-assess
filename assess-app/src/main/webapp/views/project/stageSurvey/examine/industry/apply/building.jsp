<%--
 楼栋基础信息
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <form class="form-horizontal" id="basicBuildingMainFrm">
        <input type="hidden" name="id">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼栋号<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <input type="text" data-rule-maxlength="100" placeholder="楼栋号" required="required"
                               name="buildingNumber" class="form-control" onblur="$(this).val($(this).val().replace('栋',''));">
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼栋名称<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="楼栋名称" name="buildingName"
                           class="form-control" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    总层数<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="总层数(数字)" data-rule-number='true'
                           name="floorCount" class="form-control" required="required">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    所在位置
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="所在位置" name="location" class="form-control">
                </div>
            </div>
        </div>
    </form>
    <div style="margin-bottom: 10px; border-bottom:2px solid #E6E9ED;"></div>
    <form class="form-horizontal" id="basicBuildingFrm">
        <input type="hidden" name="id">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    户型区间
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="户型区间" name="unitInterval" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    物业费
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="物业费(数字)" name="propertyFee" data-rule-number='true'
                           class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    配套公共设施使用费
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="配套公共设施使用费(数字)" name="facilitiesUseFee"
                           data-rule-number='true' class="form-control">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑高度
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="建筑高度(数字)" data-rule-number='true'
                           name="buildingHeight" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑面积
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="建筑面积(数字)" data-rule-number='true'
                           name="buildingArea" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    占地面积
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="占地面积(数字)" data-rule-number='true'
                           name="coverAnArea" class="form-control">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    层高
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="层高(数字)" data-rule-number='true'
                           name="floorHeight" class="form-control" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    进深
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="进深(数字)" data-rule-number='true'
                           name="diameterDepth" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地使用年限
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="土地使用年限(数字)" data-rule-number='true'
                           name="landUseYear" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    开盘时间
                </label>
                <div class="col-sm-3">
                    <input placeholder="开盘时间"
                           name="openTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate openTime">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    交房时间
                </label>
                <div class="col-sm-3">
                    <input placeholder="交房时间"
                           name="roomTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    竣工时间
                </label>
                <div class="col-sm-3">
                    <input placeholder="竣工时间"
                           name="beCompletedTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate beCompletedTime">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    物业类型
                </label>
                <div class="col-sm-3">
                    <select name="propertyType"
                            class="form-control propertyType">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    物业类别
                </label>
                <div class="col-sm-3">
                    <select name="propertyCategory" class="form-control propertyCategory">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    物业公司
                </label>
                <div class="col-sm-3">
                    <input type="text" name="property" placeholder="物业公司" class="form-control">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑结构类型
                </label>
                <div class="col-sm-3">
                    <select name="buildingStructureType"
                            class="form-control buildingStructureType">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑结构类别
                </label>
                <div class="col-sm-3">
                    <select name="buildingStructureCategory" class="form-control buildingStructureCategory">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑公司
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="建筑公司" class="form-control" name="builder">
                </div>
            </div>

        </div>

        <div class="form-group" id="navButtonBuildGroupFileId">
            <div class="x-valid">
                <label class="col-sm-1 control-label">平面图</label>
                <div class="col-sm-3">
                    <input id="building_floor_plan" placeholder="上传附件" class="form-control" type="file">
                    <div id="_building_floor_plan"></div>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">外装图</label>
                <div class="col-sm-3">
                    <input id="building_figure_outside" placeholder="上传附件" class="form-control" type="file">
                    <div id="_building_figure_outside"></div>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">外观图</label>
                <div class="col-sm-3">
                    <input id="building_floor_Appearance_figure" placeholder="上传附件" class="form-control" type="file">
                    <div id="_building_floor_Appearance_figure"></div>
                </div>
            </div>
        </div>
    </form>
</div>
<div class="x_content">
    <div class="x_title">
        <h3>
            楼栋外装 <label class="control-label ExamineBuildingOutfitList"></label>
            <button type="button" class="btn btn-success" data-toggle="modal"
                    onclick=""> 新增
            </button>
        </h3>
        <div class="clearfix"></div>
    </div>
    <div>
        <table class="table table-bordered" id="ExamineBuildingOutfitList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>
<div class="x_content">
    <div class="x_title">
        <h3>
            屋面结构 <label class="control-label ExamineBuildingSurfaceList"></label>
            <button type="button" class="btn btn-success" data-toggle="modal"
                    onclick=""> 新增
            </button>
        </h3>
        <div class="clearfix"></div>
    </div>
    <div>
        <table class="table table-bordered" id="ExamineBuildingSurfaceList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>
<div class="x_content">
    <div class="x_title">
        <h3>
            围护结构 <label class="control-label ExamineBuildingMaintenanceList"></label>
            <button type="button" class="btn btn-success" data-toggle="modal"
                    onclick=""> 新增
            </button>
        </h3>
        <div class="clearfix"></div>
    </div>
    <div>
        <table class="table table-bordered" id="ExamineBuildingMaintenanceList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <div class="x_title">
        <h3>
            建筑功能 <label class="control-label examineBuildingFunctionList"></label>
            <button type="button" class="btn btn-success" data-toggle="modal"
                    onclick=""> 新增
            </button>
        </h3>
        <div class="clearfix"></div>
    </div>
    <div>
        <table class="table table-bordered" id="examineBuildingFunctionList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script type="application/javascript">
</script>
<script type="application/javascript">



</script>

<div id="divBoxExamineBuildingOutfit" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">楼栋外装情况</h3>
            </div>
            <form id="frmExamineBuildingOutfit" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修部位
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="decorationPart"
                                                    class="form-control search-select select2 decorationPart">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            施工工艺
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="constructionTechnology"
                                                    class="form-control search-select select2 constructionTechnology">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            材料价格区间
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="materialPrice"
                                                    class="form-control search-select select2 materialPrice">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修材料
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="decoratingMaterial"
                                                    class="form-control search-select select2 decoratingMaterial">
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
                    <button type="button" class="btn btn-primary"
                            onclick="examineBuilding_.prototype.sonModelMethod.buildingOutfit.saveData();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxExamineBuildingMaintenance" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">围护结构</h3>
            </div>
            <form id="ExamineBuildingMaintenanceFrm" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            名称
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="名称"
                                                   name="name" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            分类
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
                                            材质
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="materialQuality"
                                                    class="form-control search-select select2 materialQuality">
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
                    <button type="button" class="btn btn-primary"
                            onclick="examineBuilding_.prototype.sonModelMethod.buildingMaintenance.saveData();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxExamineBuildingSurface" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">屋面结构</h3>
            </div>
            <form id="ExamineBuildingSurfaceFrm" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            屋面结构
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="structure"
                                                    class="form-control search-select select2 structure">
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
                                            <input type="text" placeholder="描述"
                                                   name="description" class="form-control" required="required">
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
                            onclick="examineBuilding_.prototype.sonModelMethod.buildingSurface.saveData();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="examineBuildingFunction" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">建筑功能</h3>
            </div>
            <form id="examineBuildingFunctionFrm" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            保温
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="保温"
                                                   name="heatPreservation" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            防水
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="防水"
                                                   name="waterProof" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            隔热
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="隔热"
                                                   name="heatInsulation" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修部位
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="decorationPart"
                                                    class="form-control search-select select2 decorationPart">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            施工工艺
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="constructionTechnology"
                                                    class="form-control search-select select2 constructionTechnology">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            材料价格区间
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="materialPrice"
                                                    class="form-control search-select select2 materialPrice">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修材料
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="decoratingMaterial"
                                                    class="form-control search-select select2 decoratingMaterial">
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
                    <button type="button" class="btn btn-primary"
                            onclick="examineBuilding_.prototype.sonModelMethod.buildingFunction.saveData();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>

