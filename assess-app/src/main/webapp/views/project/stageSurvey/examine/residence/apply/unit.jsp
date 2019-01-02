<%--
单元基本情况
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="frm_unit" class="form-horizontal">
    <input type="hidden" name="id" value="${surveyExamineDataInfoVo.examineUnitVo.id}">
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">单元编号<span class="symbol required"></span></label>
            <div class="col-sm-5">
                <input type="text" name="unitNumber" class="form-control" placeholder="单元编号" value="${surveyExamineDataInfoVo.examineUnitVo.unitNumber}">
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">梯户比<span class="symbol required"></span></label>
            <div class="col-sm-5">
                <input type="text" name="elevatorHouseholdRatio" class="form-control" placeholder="梯户比" value="${surveyExamineDataInfoVo.examineUnitVo.elevatorHouseholdRatio}">
            </div>
        </div>
    </div>

    <div class="ln_solid"></div>
</form>

<script type="text/javascript">

</script>