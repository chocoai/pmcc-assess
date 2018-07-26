<%--
  楼盘基础信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/views/share/main_css.jsp" %>
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
            <label class="col-sm-1 control-label">街道<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" placeholder="街道" required
                       value="${surveyExamineDataInfoVo.examineEstateVo.street}" name="name" class="form-control">
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">编号<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" data-rule-number='true' placeholder="编号(请输入数字)" required
                       value="${surveyExamineDataInfoVo.examineEstateVo.number}" name="number" class="form-control">
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">附号<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" data-rule-number='true' placeholder="附号(请输入数字)" required
                       value="${surveyExamineDataInfoVo.examineEstateVo.attachNumber}" name="attachNumber"
                       class="form-control">
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">建筑面积<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" data-rule-number='true' placeholder="建筑面积(请输入数字)" required
                       value="${surveyExamineDataInfoVo.examineEstateVo.floorArea}" name="floorArea"
                       class="form-control">
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">占地面积<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" data-rule-number='true' placeholder="占地面积(请输入数字)" required
                       value="${surveyExamineDataInfoVo.examineEstateVo.coverAnArea}" name="coverAnArea"
                       class="form-control">
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">容积率<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" data-rule-number='true' placeholder="容积率(请输入数字)" required
                       value="${surveyExamineDataInfoVo.examineEstateVo.volumetricRate}" name="volumetricRate"
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
                <textarea class="form-control" required="required" name="description" placeholder="楼盘概况">

                </textarea>
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">总栋数<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <select class="form-control search-select select2 totalBuildingType" name="totalBuildingType" required="required">
                </select>
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">总平面图<span class="symbol required"></span></label>
            <div class="col-sm-5">
                <input id="estate_floor_total_plan" name="frm_estate_floor_total_plan"
                       required="required" placeholder="上传附件" class="form-control" type="file">
                <div id="_estate_floor_total_plan"></div>
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">外观图<span class="symbol required"></span></label>
            <div class="col-sm-5">
                <input id="estate_floor_Appearance_figure" name="frm_estate_floor_total_plan"
                       required="required" placeholder="上传附件" class="form-control" type="file">
                <div id="_estate_floor_Appearance_figure"></div>
            </div>
        </div>
    </div>

    <div class="ln_solid"></div>
</form>
<script type="text/javascript">
    $(function () {
        ContainerFunForValid.push(Estate.valid);//数据验证方法写入容器
        ContainerFunForGetData.push(Estate.getFormData);//获取数据方法写入容器
        Estate.files();
        estateFun.prototype.init();
    })
</script>
<script type="text/javascript">

    function estateFun() {

    }
    estateFun.prototype = {
        init:function () {
            //主要是载入select2
            $.ajax({
                url: "${pageContext.request.contextPath}/examineBuilding/getBuildAndProperty",
                type: "get",
                dataType: "json",
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
                                $("#" + Estate.config().frm + " .developerId").select2({minimumResultsForSearch: -1});//加载样式
                            }
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })

            $.ajax({
                url: "${pageContext.request.contextPath}/examineBuilding/estate_total_building_type",
                type: "get",
                dataType: "json",
                data: {type: null},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        var gradeNum = data.length;
                        var option = "<option value=''>请选择</option>";
                        if (gradeNum > 0) {
                            for (var i = 0; i < gradeNum; i++) {
                                option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                            }
                            if ($("#" + Estate.config().frm + " .totalBuildingType").size() > 0) {
                                $("#" + Estate.config().frm + " .totalBuildingType").html(option);
                                $("#" + Estate.config().frm + " .totalBuildingType").select2({minimumResultsForSearch: -1});//加载样式
                            }
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        }
    };

    (function ($) {
        //避免方法重复，定义全局变量
        var estate = {
            config: function () {
                var data = {};
                data.frm = "frm_estate";
                data.database_TableA = "tb_examine_estate";
                data.filePlanTotal = "estate_floor_total_plan";//总平面图id
                data.fileAppearance = "estate_floor_Appearance_figure";//外观图id
                return data;
            },
            //验证
            valid: function () {
                return $("#" + estate.config().frm).valid();
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
            files:function () {
                FileUtils.uploadFiles({
                    target: estate.config().filePlanTotal,
                    disabledTarget: "btn_submit",
                    formData: {
                        tableName: estate.config().database_TableA,
                        tableId: ${empty surveyExamineDataInfoVo.examineEstateVo?0:surveyExamineDataInfoVo.examineEstateVo.id},
                        projectId: 0,
                        creater: "${currUserAccount}"
                    },
                    deleteFlag: true
                });
                FileUtils.getFileShows({
                    target: estate.config().filePlanTotal,
                    formData: {
                        tableName: estate.config().database_TableA,
                        tableId: ${empty surveyExamineDataInfoVo.examineEstateVo?0:surveyExamineDataInfoVo.examineEstateVo.id},
                        projectId: 0,
                        creater: "${currUserAccount}"
                    },
                    deleteFlag: true
                })

                FileUtils.uploadFiles({
                    target: estate.config().fileAppearance,
                    disabledTarget: "btn_submit",
                    formData: {
                        tableName: estate.config().database_TableA,
                        tableId: ${empty surveyExamineDataInfoVo.examineEstateVo?0:surveyExamineDataInfoVo.examineEstateVo.id},
                        projectId: 0,
                        creater: "${currUserAccount}"
                    },
                    deleteFlag: true
                });
                FileUtils.getFileShows({
                    target: estate.config().fileAppearance,
                    formData: {
                        tableName: estate.config().database_TableA,
                        tableId: ${empty surveyExamineDataInfoVo.examineEstateVo?0:surveyExamineDataInfoVo.examineEstateVo.id},
                        projectId: 0,
                        creater: "${currUserAccount}"
                    },
                    deleteFlag: true
                })
            }
        };
        window.Estate = estate;
    })(jQuery)
</script>

