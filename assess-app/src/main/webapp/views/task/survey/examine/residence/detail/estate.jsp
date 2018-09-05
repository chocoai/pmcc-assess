<%--
  楼盘基础信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="frm_estate" class="form-horizontal">
    <input type="hidden" data-name="fieldName" value="<%=request.getParameter("fieldName")%>">
    <input type="hidden" name="id" value="${surveyExamineDataInfoVo.examineEstateVo.id}">
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">楼盘名称<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" placeholder="楼盘名称" readonly="readonly"
                       value="${surveyExamineDataInfoVo.examineEstateVo.name}" name="name" class="form-control">
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">楼盘方位<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text"  placeholder="楼盘方位" readonly="readonly"
                       value="${surveyExamineDataInfoVo.examineEstateVo.position}" name="position" class="form-control">
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">土地级别<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" placeholder="土地级别" readonly="readonly"
                       value="${surveyExamineDataInfoVo.examineEstateVo.landLevelName}" name="landLevelName" class="form-control">
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">编号</label>
            <div class="col-sm-3">
                <input type="text" readonly="readonly" data-rule-maxlength="100" data-rule-number='true' placeholder="编号(请输入数字)"
                       value="${surveyExamineDataInfoVo.examineEstateVo.number}" name="number" class="form-control">
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">附号</label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" readonly="readonly" data-rule-number='true' placeholder="附号(请输入数字)"
                       value="${surveyExamineDataInfoVo.examineEstateVo.attachNumber}" name="attachNumber"
                       class="form-control">
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">街道</label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" placeholder="街道" readonly="readonly"
                       value="${surveyExamineDataInfoVo.examineEstateVo.street}" name="street" class="form-control">
            </div>
        </div>

    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">占地面积</label>
            <div class="col-sm-3">
                <input type="text" readonly="readonly" data-rule-maxlength="100" data-rule-number='true' placeholder="占地面积(请输入数字)"
                       value="${surveyExamineDataInfoVo.examineEstateVo.coverAnArea}" name="coverAnArea"
                       class="form-control">
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">容积率</label>
            <div class="col-sm-3">
                <input type="text" readonly="readonly" data-rule-maxlength="100" data-rule-number='true' placeholder="容积率(请输入数字)"
                       value="${surveyExamineDataInfoVo.examineEstateVo.volumetricRate}" name="volumetricRate"
                       class="form-control">
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">绿化率<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" readonly="readonly" data-rule-maxlength="100" data-rule-number='true' placeholder="绿化率(请输入数字)" required
                       value="${surveyExamineDataInfoVo.examineEstateVo.greeningRate}" name="greeningRate"
                       class="form-control">
            </div>
        </div>
    </div>


    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">楼盘概况<span class="symbol required"></span></label>
            <div class="col-sm-11">
                <textarea class="form-control" readonly="readonly" required="required" name="description" placeholder="楼盘概况">
                    ${surveyExamineDataInfoVo.examineEstateVo.description}
                </textarea>
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">总栋数<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" readonly="readonly"  placeholder="总栋数"
                       value="${surveyExamineDataInfoVo.examineEstateVo.buildingNumber}" name="buildingNumber"
                       class="form-control">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">开发商<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" placeholder="开发商" readonly="readonly"
                       value="${surveyExamineDataInfoVo.examineEstateVo.developerName}" name="developerName" class="form-control">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">建筑面积</label>
            <div class="col-sm-3">
                <input type="text" readonly="readonly" data-rule-maxlength="100" data-rule-number='true' placeholder="建筑面积(请输入数字)"
                       value="${surveyExamineDataInfoVo.examineEstateVo.floorArea}" name="floorArea"
                       class="form-control">
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">总平面图<span class="symbol required"></span></label>
            <div class="col-sm-5">
                <div id="_estate_floor_total_plan"></div>
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">外观图<span class="symbol required"></span></label>
            <div class="col-sm-5">
                <div id="_estate_floor_Appearance_figure"></div>
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">供水平面图<span class="symbol required"></span></label>
            <div class="col-sm-5">
                <div id="_water_supply_plan"></div>
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">供电平面图<span class="symbol required"></span></label>
            <div class="col-sm-5">
                <div id="_power_supply_plan"></div>
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">供气平面图<span class="symbol required"></span></label>
            <div class="col-sm-5">
                <div id="_air_supply_plan"></div>
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">采暖平面图<span class="symbol required"></span></label>
            <div class="col-sm-5">
                <div id="_heating_plan"></div>
            </div>
        </div>
    </div>

    <div class="ln_solid"></div>
</form>

<script>
    var config_estate;
    (function () {
        //局部变量 用做 getter setter
        var flag = true;
        //下面根据字段 ExamineFileUpLoadFieldEnum 配置而来 (假如修改必须在此中更新)
        var filePlanTotal = "estate_floor_total_plan";//总平面图id和字段
        var waterSupplyPlan = "water_supply_plan";//供水平面图id和字段
        var powerSupplyPlan = "power_supply_plan";//供电平面图id和字段
        var airSupplyPlan = "air_supply_plan";//供气平面图id和字段
        var heatingPlan = "heating_plan";//采暖平面图id和字段
        var fileAppearance = "estate_floor_Appearance_figure";//外观图id和字段
        var database_Table = AssessDBKey.ExamineEstate;
        config_estate = {
            frm: "frm_estate",
            getFrm: function () {
                return this.frm;
            }
            ,
            getFlag: function () {
                return flag;
            }
            ,
            getFilePlanTotal: function () {
                return filePlanTotal;
            }
            ,
            getWaterSupplyPlan: function () {
                return waterSupplyPlan;
            }
            ,
            getPowerSupplyPlan: function () {
                return powerSupplyPlan;
            }
            ,
            getAirSupplyPlan: function () {
                return airSupplyPlan;
            }
            ,
            getHeatingPlan: function () {
                return heatingPlan;
            },
            getFileAppearance: function () {
                return fileAppearance;
            },
            getDataBaseName:function () {
                return database_Table;
            }
        };
    })();

    var estate = Object.create(config_estate);

    estate.init = function () {
        estate.showFiles();
    };


    estate.showFiles = function () {
        FileUtils.getFileShows({
            target: estate.getFilePlanTotal(),
            formData: {
                fieldsName:estate.getFilePlanTotal(),
                tableName: estate.getDataBaseName(),
                tableId: ${empty surveyExamineDataInfoVo.examineEstateVo?0:surveyExamineDataInfoVo.examineEstateVo.id},
                projectId: 0,
                creater: "${currUserAccount}"
            },
            deleteFlag: false
        });
        FileUtils.getFileShows({
            target: estate.getWaterSupplyPlan(),
            formData: {
                fieldsName:estate.getWaterSupplyPlan(),
                tableName: estate.getDataBaseName(),
                tableId: ${empty surveyExamineDataInfoVo.examineEstateVo?0:surveyExamineDataInfoVo.examineEstateVo.id},
                projectId: 0,
                creater: "${currUserAccount}"
            },
            deleteFlag: false
        });
        FileUtils.getFileShows({
            target: estate.getPowerSupplyPlan(),
            formData: {
                fieldsName:estate.getPowerSupplyPlan(),
                tableName: estate.getDataBaseName(),
                tableId: ${empty surveyExamineDataInfoVo.examineEstateVo?0:surveyExamineDataInfoVo.examineEstateVo.id},
                projectId: 0,
                creater: "${currUserAccount}"
            },
            deleteFlag: false
        });
        FileUtils.getFileShows({
            target: estate.getAirSupplyPlan(),
            formData: {
                fieldsName:estate.getAirSupplyPlan(),
                tableName: estate.getDataBaseName(),
                tableId: ${empty surveyExamineDataInfoVo.examineEstateVo?0:surveyExamineDataInfoVo.examineEstateVo.id},
                projectId: 0,
                creater: "${currUserAccount}"
            },
            deleteFlag: false
        });
        FileUtils.getFileShows({
            target: estate.getFileAppearance(),
            formData: {
                fieldsName:estate.getFileAppearance(),
                tableName: estate.getDataBaseName(),
                tableId: ${empty surveyExamineDataInfoVo.examineEstateVo?0:surveyExamineDataInfoVo.examineEstateVo.id},
                projectId: 0,
                creater: "${currUserAccount}"
            },
            deleteFlag: false
        });
        FileUtils.getFileShows({
            target: estate.getHeatingPlan(),
            formData: {
                fieldsName:estate.getHeatingPlan(),
                tableName: estate.getDataBaseName(),
                tableId: ${empty surveyExamineDataInfoVo.examineEstateVo?0:surveyExamineDataInfoVo.examineEstateVo.id},
                projectId: 0,
                creater: "${currUserAccount}"
            },
            deleteFlag: false
        });
    };


    $(function () {
       estate.init();
    });
</script>


