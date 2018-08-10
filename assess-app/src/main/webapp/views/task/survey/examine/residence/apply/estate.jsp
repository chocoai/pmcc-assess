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
                <input type="text" data-rule-maxlength="100" placeholder="楼盘名称" required
                       value="${surveyExamineDataInfoVo.examineEstateVo.name}" name="name" class="form-control">
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">开发商<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <select class="form-control search-select select2 developerId" name="developerId" required="required">
                </select>
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">街道</label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" placeholder="街道"
                       value="${surveyExamineDataInfoVo.examineEstateVo.street}" name="street" class="form-control">
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">编号<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" data-rule-number='true' placeholder="编号(请输入数字)"
                       value="${surveyExamineDataInfoVo.examineEstateVo.number}" name="number" class="form-control"
                       required="required">
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">附号<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" data-rule-number='true' placeholder="附号(请输入数字)"
                       value="${surveyExamineDataInfoVo.examineEstateVo.attachNumber}" name="attachNumber"
                       required="required"
                       class="form-control">
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">建筑面积<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" data-rule-number='true' placeholder="建筑面积(请输入数字)"
                       value="${surveyExamineDataInfoVo.examineEstateVo.floorArea}" name="floorArea" required="required"
                       class="form-control">
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">占地面积<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" data-rule-number='true' placeholder="占地面积(请输入数字)"
                       value="${surveyExamineDataInfoVo.examineEstateVo.coverAnArea}" name="coverAnArea"
                       required="required"
                       class="form-control">
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">容积率<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" data-rule-number='true' placeholder="容积率(请输入数字)"
                       value="${surveyExamineDataInfoVo.examineEstateVo.volumetricRate}" name="volumetricRate"
                       required="required"
                       class="form-control">
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">绿化率<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" data-rule-number='true' placeholder="绿化率(请输入数字)" required
                       value="${surveyExamineDataInfoVo.examineEstateVo.greeningRate}" name="greeningRate"
                       class="form-control">
            </div>
        </div>
    </div>


    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">楼盘概况<span class="symbol required"></span></label>
            <div class="col-sm-11">
                <textarea class="form-control" required="required" name="description"
                          placeholder="楼盘概况">${surveyExamineDataInfoVo.examineEstateVo.description}</textarea>
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">总栋数</label>
            <div class="col-sm-3">
                <select class="form-control search-select select2 totalBuildingType" name="totalBuildingType">
                </select>
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">总平面图</label>
            <div class="col-sm-5">
                <input id="estate_floor_total_plan" placeholder="上传附件" class="form-control" type="file">
                <div id="_estate_floor_total_plan"></div>
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">外观图</label>
            <div class="col-sm-5">
                <input id="estate_floor_Appearance_figure" placeholder="上传附件" class="form-control" type="file">
                <div id="_estate_floor_Appearance_figure"></div>
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">供水平面图</label>
            <div class="col-sm-5">
                <input id="water_supply_plan" placeholder="上传附件" class="form-control" type="file">
                <div id="_water_supply_plan"></div>
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">供电平面图</label>
            <div class="col-sm-5">
                <input id="power_supply_plan" placeholder="上传附件" class="form-control" type="file">
                <div id="_power_supply_plan"></div>
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">供气平面图</label>
            <div class="col-sm-5">
                <input id="air_supply_plan" placeholder="上传附件" class="form-control" type="file">
                <div id="_air_supply_plan"></div>
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">采暖平面图</label>
            <div class="col-sm-5">
                <input id="heating_plan" placeholder="上传附件" class="form-control" type="file">
                <div id="_heating_plan"></div>
            </div>
        </div>
    </div>

    <div class="ln_solid"></div>
</form>
<script type="text/javascript">
    $(function () {
        ContainerFunForValid.push(Estate.valid);//数据验证方法写入容器
        ContainerFunForGetData.push(Estate.getFormData);//获取数据方法写入容器
        ContainerFunForInit.estate.push(estateFun.init);//初始化方法写入容器
        ContainerFunForInit.estate.push(estateFun.select2Init);//初始化方法写入容器
        ContainerFunForInit.estate.push(estateFun.viewFiles);//初始化方法写入容器

    })
</script>
<script type="text/javascript">
    (function () {
        //上传附件
        function uploadFile(fieldsName) {
            FileUtils.uploadFiles({
                target: fieldsName,
                disabledTarget: "btn_submit",
                formData: {
                    fieldsName: fieldsName,
                    tableName: Estate.config().database_Table,
                    tableId: ${empty surveyExamineDataInfoVo.examineEstateVo?0:surveyExamineDataInfoVo.examineEstateVo.id},
                    creater: "${currUserAccount}"
                },
                deleteFlag: true
            });
        }

        //显示附件
        function showFile(fieldsName) {
            FileUtils.getFileShows({
                target: fieldsName,
                formData: {
                    fieldsName: fieldsName,
                    tableName: Estate.config().database_Table,
                    tableId: ${empty surveyExamineDataInfoVo.examineEstateVo?0:surveyExamineDataInfoVo.examineEstateVo.id},
                    creater: "${currUserAccount}"
                },
                deleteFlag: true
            })
        }

        function estateFun() {

        }
        estateFun.prototype = {
            select2Init: function () {
                estateFun.prototype.select2InitMethodWrite("${surveyExamineDataInfoVo.examineEstateVo.developerId}", "developerId");
                estateFun.prototype.select2InitMethodWrite("${surveyExamineDataInfoVo.examineEstateVo.totalBuildingType}", "totalBuildingType");
            },
            select2InitMethodWrite: function (data, name) {
                if (estateFun.prototype.select2IsNotNull(data)) {
                    if (estateFun.prototype.select2IsNotNull(name)) {
                        $("#" + Estate.config().frm + " ." + name).val(data).trigger("change");
                    }
                } else {
                    if (estateFun.prototype.select2IsNotNull(name)) {
                        $("#" + Estate.config().frm + " ." + name).val(null).trigger("change");
                    }
                }
            },
            select2IsNotNull: function (data) {
                if (data == null) {
                    return false;
                }
                if (data == '') {
                    return false;
                }
                if (data == "") {
                    return false;
                }
                if (data == 0) {
                    return false;
                }
                return true;
            },
            init: function () {
                //主要是载入select2
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuilding/getBuildAndProperty",
                    type: "get",
                    dataType: "json",
                    async: false,
                    data: {type: "DataDeveloper"},
                    success: function (result) {
                        if (result.ret) {
                            var data = result.data;
                            var gradeNum = data.length;
                            var option = "<option value=''>请选择</option>";
                            if (gradeNum > 0) {
                                for (var i = 0; i < gradeNum; i++) {
                                    option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                }
                                if ($("#" + Estate.config().frm + " .developerId").size() > 0) {
                                    $("#" + Estate.config().frm + " .developerId").html(option);
                                    $("#" + Estate.config().frm + " .developerId").select2();//加载样式
                                }
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })

                AssessCommon.loadDataDicByKey(AssessDicKey.estateTotalBuildingType, "${surveyExamineDataInfoVo.examineEstateVo.totalBuildingType}", function (html, data) {
                    $("#" + Estate.config().frm + " .totalBuildingType").html(html);
                    $("#" + Estate.config().frm + " .totalBuildingType").select2();//加载样式
                })

            },
            viewFiles: function () {
                //总平面图
                uploadFile(Estate.config().filePlanTotal);
                showFile(Estate.config().filePlanTotal);
                //外观图
                uploadFile(Estate.config().fileAppearance);
                showFile(Estate.config().fileAppearance);
                //供水平面图
                uploadFile(Estate.config().waterSupplyPlan);
                showFile(Estate.config().waterSupplyPlan);
                //供电平面图
                uploadFile(Estate.config().powerSupplyPlan);
                showFile(Estate.config().powerSupplyPlan);
                //供气平面图
                uploadFile(Estate.config().airSupplyPlan);
                showFile(Estate.config().airSupplyPlan);
                //采暖平面图
                uploadFile(Estate.config().heatingPlan);
                showFile(Estate.config().heatingPlan);
            }
        };

        window.estateFun = new estateFun();
    })()


</script>
<script type="text/javascript">
    (function ($) {
        //避免方法重复，定义全局变量
        var estate = {
            config: function () {
                var data = {};
                data.frm = "frm_estate";
                data.database_Table = AssessDBKey.ExamineEstate;
                //下面根据字段 ExamineFileUpLoadFieldEnum 配置而来 (假如修改必须在此中更新)
                data.filePlanTotal = "estate_floor_total_plan";//总平面图id和字段
                data.waterSupplyPlan = "water_supply_plan";//供水平面图id和字段
                data.powerSupplyPlan = "power_supply_plan";//供电平面图id和字段
                data.airSupplyPlan = "air_supply_plan";//供气平面图id和字段
                data.heatingPlan = "heating_plan";//采暖平面图id和字段
                data.fileAppearance = "estate_floor_Appearance_figure";//外观图id和字段
                return data;
            },
            //验证
            valid: function () {
                var options = {
                    msg: "请检查【楼盘】填写的信息",
                    hiddenValid: true
                };
                return $("#" + estate.config().frm).valid(options);
            },

            //获取需要保存的数据
            getFormData: function () {
                var data = formParams(estate.config().frm);
                data.declareId = $("#declareId").val();
                data.examineType = $("#examineType").val();
                var keyValueDto = {};
                keyValueDto.key = $("#" + estate.config().frm).find('[data-name="fieldName"]').val();
                keyValueDto.value = data;
                return keyValueDto;
            },
            //文件上传处理
            files: function () {
                //已经移除
            }
        };
        window.Estate = estate;
    })(jQuery)
</script>

