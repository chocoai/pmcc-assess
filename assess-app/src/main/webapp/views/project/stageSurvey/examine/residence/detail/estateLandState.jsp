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
                <label class="col-sm-1 control-label">土地名称<span class="symbol readonly"></span></label>
                <div class="col-sm-11">
                    <input type="text" readonly="readonly" class="form-control" name="name" placeholder="名称" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.soil}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地用途类型<span class="symbol readonly"></span></label>
                <div class="col-sm-3">
                    <input type="text" readonly="readonly" class="form-control" placeholder="土地用途类型" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.landUseTypeName}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">土地用途类别<span class="symbol readonly"></span></label>
                <div class="col-sm-3">
                    <input type="text" readonly="readonly" class="form-control" placeholder="土地用途类别" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.landUseCategoryName}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">土地级别<span class="symbol readonly"></span></label>
                <div class="col-sm-3">
                    <input type="text" readonly="readonly" class="form-control" name="landLevelName"  placeholder="土地级别" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.landLevelName}">
                </div>
            </div>

        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">东至<span class="symbol readonly"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="东至" readonly="readonly" name="eastTo" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.eastTo}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">南至<span class="symbol readonly"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="南至" readonly="readonly" name="southTo" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.southTo}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">西至<span class="symbol readonly"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="西至" readonly="readonly" name="westTo" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.westTo}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">北至<span class="symbol readonly"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="北至" readonly="readonly" name="northTo" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.northTo}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地形状<span class="symbol readonly"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="土地形状" readonly="readonly" name="shapeState" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.shapeState}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">地形<span class="symbol readonly"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="地形" readonly="readonly" name="planeness" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.planeness}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地开发程度<span class="symbol readonly"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="土地开发程度" readonly="readonly" name="developmentDegree" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.developmentDegree}">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">开发限制条件<span class="symbol readonly"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="开发限制条件" readonly="readonly" name="restrictiveCondition" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.restrictiveCondition}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土壤<span class="symbol readonly"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="土壤" readonly="readonly" name="soil" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.soil}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">地势<span class="symbol readonly"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.topographicTerrain}" placeholder="地势" readonly="readonly" name="topographicTerrain">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地面积<span class="symbol readonly"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="土地面积(请输入数字)" readonly="readonly" name="landArea" value="${surveyExamineDataInfoVo.examineEstateLandStateVo.landArea}">
                </div>
            </div>
        </div>
    </form>
</div>

<script>
    
</script>
