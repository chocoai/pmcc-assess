<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
 楼栋基础信息
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>
            楼栋基本信息
        </h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="basicBuildingFrm">
        <input type="hidden" name="id">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    楼栋号<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <div class="input-group">
                        <input type="text" id="txt_building_search" data-rule-maxlength="100" placeholder="楼栋号" required="required"
                               name="buildingNumber" class="form-control" onblur="buildingCommon.buildingNumberBlur(this);" value="${basicBuilding.buildingNumber}">
                        <span class="input-group-btn">
                            <div onclick="buildingCommon.mapMarker();" class="btn btn-info"><i
                                    class="fa fa-map-marker"></i> 标注</div>
                        </span>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    楼栋名称<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="楼栋名称" name="buildingName"
                           class="form-control" required="required" value="${basicBuilding.buildingName}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    总层数<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="总层数(数字)" data-rule-number='true'
                           name="floorCount" class="form-control" required="required" value="${basicBuilding.floorCount}">
                </div>
            </div>
        </div>
        <div  class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    物业类型
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="propertyType"
                            class="form-control propertyType">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    物业类别
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="propertyCategory" class="form-control propertyCategory">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    首层位置
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="首层位置(数字)" data-rule-number='true'
                           name="firstFloor" class="form-control" value="${basicBuilding.firstFloor}">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    最高层
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="最高层(数字)" data-rule-number='true'
                           name="maxFloor" class="form-control" value="${basicBuilding.maxFloor}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    套内面积
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="套内面积"
                           name="inJacketArea" class="form-control" value="${basicBuilding.inJacketArea}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    使用面积
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="使用面积"
                           name="useArea" class="form-control" value="${basicBuilding.useArea}">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    所在位置
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="所在位置" name="location" class="form-control" value="${basicBuilding.location}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    建筑使用年限<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <c:if test="${basicApply.type == 0 || basicApply.type==null}">
                        <select name="residenceUseYear" required class="form-control residenceUseYear search-select select2">
                        </select>
                    </c:if>
                    <c:if test="${basicApply.type == 1}">
                        <select name="industryUseYear" required class="form-control industryUseYear search-select select2">
                        </select>
                    </c:if>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    户型区间
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="户型区间" name="unitInterval" class="form-control" value="${basicBuilding.unitInterval}">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    物业费
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="物业费(数字)" name="propertyFee" data-rule-number='true'
                           class="form-control" value="${basicBuilding.propertyFee}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    配套公共设施使用费
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="配套公共设施使用费(数字)" name="facilitiesUseFee"
                           data-rule-number='true' class="form-control" value="${basicBuilding.facilitiesUseFee}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    建筑高度
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="建筑高度(数字)" data-rule-number='true'
                           name="buildingHeight" class="form-control" value="${basicBuilding.buildingHeight}">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    建筑面积
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="建筑面积(数字)" data-rule-number='true'
                           name="buildingArea" class="form-control" value="${basicBuilding.buildingArea}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    占地面积
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="占地面积(数字)" data-rule-number='true'
                           name="coverAnArea" class="form-control" value="${basicBuilding.coverAnArea}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    层高
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="层高(数字)" data-rule-number='true'
                           name="floorHeight" class="form-control" value="${basicBuilding.floorHeight}">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地使用年限
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="土地使用年限(数字)" data-rule-number='true'
                           name="landUseYear" class="form-control" value="${basicBuilding.landUseYear}">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    开盘时间
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="开盘时间"
                           name="openTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate openTime">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    交房时间
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="交房时间"
                           name="roomTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    竣工时间获取方式<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="completedTimeType" required class="form-control completedTimeType search-select select2">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    竣工时间<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="竣工时间"
                           name="beCompletedTime" required data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate beCompletedTime">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    楼间距<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="betweenDistance" required class="form-control betweenDistance search-select select2">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    建筑结构类型
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="buildingStructureType"
                            class="form-control buildingStructureType">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    建筑结构类别
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="buildingStructureCategory" class="form-control buildingStructureCategory">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    建筑公司
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="建筑公司" class="form-control" name="builderName" value="${basicBuilding.builderName}">
                    <input type="hidden" placeholder="建筑公司" class="form-control" name="builder" value="${basicBuilding.builder}">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    工程质量
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="constructionQuality"
                            class="form-control constructionQuality">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    外观风格
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="appearanceStyle"
                            class="form-control appearanceStyle">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    外观新旧
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="appearanceNewAndOld"
                            class="form-control appearanceNewAndOld">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼间距描述</label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                        <textarea class="form-control" name="betweenDistanceDescription"
                                  placeholder="楼间距描述">${basicBuilding.betweenDistanceDescription}</textarea>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    物业公司名称
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" name="propertyName" placeholder="物业公司名称" class="form-control" value="${basicBuilding.propertyName}">
                    <input type="hidden" name="property" placeholder="物业公司 " class="form-control" value="${basicBuilding.property}">
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    物业公司公司性质
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="propertyCompanyNature" class="form-control">
                        <option value="">-请选择-</option>
                        <c:forEach items="${unitPropertiesList}" var="item">
                            <c:choose>
                                <c:when test="${item.id == basicBuilding.propertyCompanyNature}">
                                    <option value="${item.id}"
                                            selected="selected">${item.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${item.id}">${item.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    物业公司社会信誉
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="propertySocialPrestige" class="form-control">
                        <option value="">-请选择-</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    物业服务
                </label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <!-- 这个id是不能更改的bootstrap3 强制如此 toolbar -->
                    <div id="toolbarBuildingPropertyServiceItemTable" style="display: none">
                        <div class="btn-group">
                            <button type="button" class="btn btn-primary" onclick="buildingCommon.deleteBasicBuildingPropertyServiceItem('#basicBuildingPropertyServiceItemTable')">删除</button>
                            <button type="button" class="btn btn-primary" onclick="buildingCommon.editBasicBuildingPropertyServiceItem('#basicBuildingPropertyServiceItemTable','#basicBuildingPropertyServiceItemModalTool',true)">编辑</button>
                            <button type="button" class="btn btn-primary" onclick="buildingCommon.editBasicBuildingPropertyServiceItem('#basicBuildingPropertyServiceItemTable','#basicBuildingPropertyServiceItemModalTool',false)">添加</button>
                        </div>
                    </div>

                    <table class="table table-bordered" id="basicBuildingPropertyServiceItemTable" data-toolbar="#toolbarBuildingPropertyServiceItemTable">

                    </table>
                </div>
            </div>
        </div>
        <div class="form-group" id="navButtonBuildGroupFileId">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">平面图</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input id="building_floor_plan" placeholder="上传附件" class="form-control" type="file">
                    <div id="_building_floor_plan"></div>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">外装图</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input id="building_figure_outside" placeholder="上传附件" class="form-control" type="file">
                    <div id="_building_figure_outside"></div>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">外观图</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input id="building_floor_Appearance_figure" placeholder="上传附件" class="form-control" type="file">
                    <div id="_building_floor_Appearance_figure"></div>
                </div>
            </div>
        </div>
    </form>
</div>

<%@include file="./buildingOutfit.jsp" %>
<%@include file="./buildingFunction.jsp" %>

<div id="basicBuildingPropertyServiceItemModalTool" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">编辑服务内容</h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="masterId">
                    <input type="hidden" name="buildingId">
                    <div class="row">
                        <div class="col-xs-12  col-sm-12  col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 control-label">
                                            服务类型<span class="symbol required"></span>
                                        </label>
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <select required="required" name="serviceType" class="form-control serviceType"
                                                    onchange="">
                                                <option value="">-请选择-</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 control-label">
                                            服务内容<span class="symbol required"></span>
                                        </label>
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <select required="required" name="serviceContent" class="form-control">
                                                <option value="">请先选择类型</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 control-label">
                                            服务时间
                                        </label>
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <input type="text" class="form-control" name="serviceTime"
                                                   placeholder="服务时间" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 control-label">
                                            等级评价<span class="symbol required"></span>
                                        </label>
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <select required="required" name="gradeEvaluation" class="form-control search-select select2">
                                                <option value="">-请选择-</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 control-label">
                                            收费标准<span class="symbol required"></span>
                                        </label>
                                        <div class="col-xs-10  col-sm-10  col-md-10">
                                            <input type="text" class="form-control" name="chargesNotes"
                                                   placeholder="收费标准" required="required">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary"
                        onclick="buildingCommon.addBasicBuildingPropertyServiceItem(this);">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>


</html>

