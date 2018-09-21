<%--
  版块
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="frm_block" class="form-horizontal">
    <input type="hidden" data-name="fieldName" value="<%=request.getParameter("fieldName")%>">
    <input type="hidden" name="id" value="${surveyExamineDataInfoVo.examineBlockVo.id}">
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">省<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <label class="form-control">${surveyExamineDataInfoVo.examineBlockVo.provinceName}</label>
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">市<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <label class="form-control">${surveyExamineDataInfoVo.examineBlockVo.cityName}</label>
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">县</label>
            <div class="col-sm-3">
                <label class="form-control">${surveyExamineDataInfoVo.examineBlockVo.districtName}</label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">版块名称<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <label class="form-control">${surveyExamineDataInfoVo.examineBlockVo.name}</label>
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">方位<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <label class="form-control">${surveyExamineDataInfoVo.examineBlockVo.position}</label>
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">区域性质<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <label class="form-control">${surveyExamineDataInfoVo.examineBlockVo.regionalNatureName}</label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">区域描述</label>
            <div class="col-sm-11">
                <label class="form-control">${surveyExamineDataInfoVo.examineBlockVo.remark}</label>
            </div>
        </div>
    </div>
    <div class="ln_solid"></div>
</form>

<script>

</script>