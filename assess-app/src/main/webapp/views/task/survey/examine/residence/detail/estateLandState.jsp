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
                    <input type="text" class="form-control" required="required" name="name" placeholder="名称" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.soil}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地用途<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 landUse" name="landUse" required="required">
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

            <div class="x-valid">
                <label class="col-sm-1 control-label">土地面积<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" data-rule-number='true' placeholder="土地面积(请输入数字)" required="required" name="landArea" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.landArea}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">东至<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="东至" required="required" name="eastTo" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.eastTo}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">南至<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="南至" required="required" name="southTo" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.southTo}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">西至<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="西至" required="required" name="westTo" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.westTo}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">北至<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="北至" required="required" name="northTo" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.northTo}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地形状状况<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="土地形状状况" required="required" name="shapeState" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.shapeState}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地平整度<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="土地平整度" required="required" name="planeness" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.planeness}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地开发程度<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="土地开发程度" required="required" name="developmentDegree" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.developmentDegree}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">开发限制条件<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="开发限制条件" required="required" name="restrictiveCondition" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.restrictiveCondition}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土壤<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="土壤" required="required" name="soil" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.soil}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">地形地势<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.topographicTerrain}" placeholder="地形地势" required="required" name="topographicTerrain">
                </div>
            </div>
        </div>
    </form>
</div>

<script>
    var estateLandState_config ;
    (function () {
        var frm = "frm_estateLandState" ;
        estateLandState_config = {
            getFrm:function () {
                return frm;
            }
        }
    })();
    var estateLandState = Object.create(estateLandState_config);
    estateLandState.init = function () {
        estateLandState.select2LoadData();
        estateLandState.select2InitMethodWrite("${surveyExamineDataInfoVo.examineEstateLandStateVo.landUse}", "landUse");
        estateLandState.select2InitMethodWrite("${surveyExamineDataInfoVo.examineEstateLandStateVo.landLevel}", "landLevel");
        $("#" + estateLandState.getFrm() + " :input").attr("readonly","readonly");
    };
    estateLandState.select2InitMethodWrite = function (data, name) {
        if (estateLandState.select2IsNotNull(data)) {
            if (estateLandState.select2IsNotNull(name)) {
                $("#" + estateLandState.getFrm() + " ." + name).val(data).trigger("change");
            }
        } else {
            $("#" + estateLandState.getFrm() + " ." + name).val(null).trigger("change");
        }
    };
    estateLandState.select2LoadData = function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/examineBuilding/estate_total_land_use",
            type: "get",
            dataType: "json",
            async:false,
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
                        if ($("#" + estateLandState.getFrm() + " .landUse").size() > 0) {
                            $("#" + estateLandState.getFrm() + " .landUse").html(option);
                            $("#" + estateLandState.getFrm() + " .landUse").select2();//加载样式
                        }
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
        $.ajax({
            url: "${pageContext.request.contextPath}/examineBuilding/estate_total_land_level",
            type: "get",
            dataType: "json",
            async:false,
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
                        if ($("#" + estateLandState.getFrm() + " .landLevel").size() > 0) {
                            $("#" + estateLandState.getFrm() + " .landLevel").html(option);
                            $("#" + estateLandState.getFrm() + " .landLevel").select2();//加载样式
                        }
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };
    estateLandState.select2IsNotNull = function (data) {
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

    $(function () {
        estateLandState.init();
    });
</script>
