<%--
 土地实体情况
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>土地实体情况 </h3>
        <div class="clearfix"></div>
    </div>
    <form id="frm_estateLandState" class="form-horizontal">
        <input type="hidden" name="id" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.id}">
        <input type="hidden" data-name="fieldName" value="<%=request.getParameter("fieldName")%>">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地名称<span class="symbol required"></span></label>
                <div class="col-sm-11">
                    <input type="text" class="form-control" required="required" name="name" placeholder="名称"
                           value="${surveyExamineDataInfoVo.examineEstateLandStateVo.soil}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地用途类型<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 landUseType" name="landUseType"
                            required="required">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地用途类别<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 landUseCategory" name="landUseCategory"
                            required="required">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地级别<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 landLevel" name="landLevel" required="required">
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">东至<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="东至" required="required" name="eastTo"
                           value="${surveyExamineDataInfoVo.examineEstateLandStateVo.eastTo}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">南至<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="南至" required="required" name="southTo"
                           value="${surveyExamineDataInfoVo.examineEstateLandStateVo.southTo}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">西至<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="西至" required="required" name="westTo"
                           value="${surveyExamineDataInfoVo.examineEstateLandStateVo.westTo}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">北至<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="北至" required="required" name="northTo"
                           value="${surveyExamineDataInfoVo.examineEstateLandStateVo.northTo}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地形状状况<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="土地形状状况" required="required" name="shapeState"
                           value="${surveyExamineDataInfoVo.examineEstateLandStateVo.shapeState}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地平整度<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="土地平整度" required="required" name="planeness"
                           value="${surveyExamineDataInfoVo.examineEstateLandStateVo.planeness}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地开发程度<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="土地开发程度" required="required"
                           name="developmentDegree"
                           value="${surveyExamineDataInfoVo.examineEstateLandStateVo.developmentDegree}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">开发限制条件<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="开发限制条件" required="required"
                           name="restrictiveCondition"
                           value="${surveyExamineDataInfoVo.examineEstateLandStateVo.restrictiveCondition}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土壤<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="土壤" required="required" name="soil"
                           value="${surveyExamineDataInfoVo.examineEstateLandStateVo.soil}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">地形地势<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control"
                           value="${surveyExamineDataInfoVo.examineEstateLandStateVo.topographicTerrain}"
                           placeholder="地形地势" required="required" name="topographicTerrain">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">土地面积<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" data-rule-number='true' placeholder="土地面积(请输入数字)"
                           required="required" name="landArea"
                           value="${surveyExamineDataInfoVo.examineEstateLandStateVo.landArea}">
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
    $(function () {
        ContainerFunForValid.push(EstateLandState.valid);//数据验证方法写入容器
        ContainerFunForGetData.push(EstateLandState.getFormData);//获取数据方法写入容器
        ContainerFunForInit.estate.push(estateLandState.prototype.init);//初始化方法写入容器

    })
</script>
<script type="text/javascript">
    function estateLandState() {

    }
    //页面保存数据后 展示数据
    estateLandState.prototype.saveShowData = function () {
        estateLandState.prototype.select2InitMethodWrite("${surveyExamineDataInfoVo.examineEstateLandStateVo.landUseType}", "landUse");
        estateLandState.prototype.select2InitMethodWrite("${surveyExamineDataInfoVo.examineEstateLandStateVo.landLevel}", "landLevel");
    };
    estateLandState.prototype.select2InitMethodWrite = function (data, name) {
        if (estateLandState.prototype.select2IsNotNull(data)) {
            if (estateLandState.prototype.select2IsNotNull(name)) {
                $("#" + EstateLandState.config().frm + " ." + name).val(data).trigger("change");
            }
        } else {
            if (estateLandState.prototype.select2IsNotNull(name)) {
                $("#" + EstateLandState.config().frm + " ." + name).val(null).trigger("change");
            }
        }
    };
    estateLandState.prototype.select2IsNotNull = function (data) {
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
    };
    estateLandState.prototype.init = function () {
        var landUseType = "${surveyExamineDataInfoVo.examineEstateLandStateVo.landUseType}";//土地用途类型
        var landUseCategory = "${surveyExamineDataInfoVo.examineEstateLandStateVo.landUseCategory}";//土地用途类别
        $("#" + EstateLandState.config().frm).find("select.landUseType").change(function () {
            AssessCommon.loadDataDicByPid($(this).val(), null, function (html, data) {
                $("#" + EstateLandState.config().frm).find("select.landUseCategory").empty().html(html).trigger('change');
            });
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, landUseType, function (html, optionArray) {
            $("#" + EstateLandState.config().frm).find("select.landUseType").html(html).trigger('change');

            AssessCommon.loadDataDicByPid(landUseType, landUseCategory, function (html, data) {
                $("#" + EstateLandState.config().frm).find("select.landUseCategory").append(html).trigger('change');
            });
        });

        $.ajax({
            url: "${pageContext.request.contextPath}/dataLandLevel/listDataLandLevel",
            type: "get",
            dataType: "json",
            async: false,
            data: {
                province: "${projectInfo.province}",
                city: "${projectInfo.city}",
                district: "${projectInfo.district}"
            },
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var gradeNum = data.length;
                    var option = "<option value=''>请选择</option>";
                    if (gradeNum > 0) {
                        for (var i = 0; i < gradeNum; i++) {
                            if('${surveyExamineDataInfoVo.examineEstateLandStateVo.landLevel}'==data[i].id){
                                option += "<option selected='selected' value='" + data[i].id + "'>" + data[i].leve + "</option>";
                            }else {
                                option += "<option value='" + data[i].id + "'>" + data[i].leve + "</option>";
                            }
                        }
                        $("#" + EstateLandState.config().frm).find("select.landLevel").empty().html(option).trigger('change');
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
        estateLandState.prototype.saveShowData();
    };

    (function ($) {
        //避免方法重复，定义全局变量
        var estateLandState = {
            config: function () {
                var data = {};
                data.frm = "frm_estateLandState";
                return data;
            },
            //验证
            valid: function () {
                var options = {
                    msg: "请检查【土地实体情况】填写的信息",
                    hiddenValid: true
                };
                return $("#" + estateLandState.config().frm).valid(options);
            },

            //获取需要保存的数据
            getFormData: function () {
                var data = formParams(estateLandState.config().frm);
                data.declareId = $("#declareId").val();
                data.planDetailsId = $("#planDetailsId").val();
                data.examineType = $("#examineType").val();
                var keyValueDto = {};
                keyValueDto.key = $("#" + estateLandState.config().frm).find('[data-name="fieldName"]').val();
                keyValueDto.value = data;
                return keyValueDto;
            }
        };

        window.EstateLandState = estateLandState;
    })(jQuery)
</script>



