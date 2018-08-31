<%--
单元基本情况
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="frm_unit" class="form-horizontal">
    <input type="hidden" data-name="fieldName" value="<%=request.getParameter("fieldName")%>">
    <input type="hidden" name="id" value="${surveyExamineDataInfoVo.examineUnitVo.id}">
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">单元编号<span class="symbol required"></span></label>
            <div class="col-sm-5">
                <input type="text" name="unitNumber" class="form-control" placeholder="单元编号" value="${surveyExamineDataInfoVo.examineUnitVo.unitNumber}">
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">楼梯分布<span class="symbol required"></span></label>
            <div class="col-sm-5">
                <input type="text" name="elevatorHouseholdRatio" class="form-control" placeholder="楼梯分布" value="${surveyExamineDataInfoVo.examineUnitVo.elevatorHouseholdRatio}">
            </div>
        </div>
    </div>

    <div class="ln_solid"></div>
</form>
<script type="text/javascript">

    $(function () {
        ContainerFunForValid.push(Unit.valid);//数据验证方法写入容器
        ContainerFunForGetData.push(Unit.getFormData);//获取数据方法写入容器
    })
</script>
<script type="text/javascript">
    (function ($) {
        //避免方法重复，定义全局变量
        var unit = {
            //验证
            valid:function () {
                var options={
                    msg:"请检查【单元】填写的信息",
                    hiddenValid:true
                };
                return $("#frm_unit").valid(options);
            },

            //获取需要保存的数据
            getFormData: function () {
                var data = formParams("frm_unit");
                data.declareId = $("#declareId").val();
                data.planDetailsId = $("#planDetailsId").val();
                data.examineType = $("#examineType").val();
                var keyValueDto = {};
                keyValueDto.key = $("#frm_unit").find('[data-name="fieldName"]').val();
                keyValueDto.value = data;
                return keyValueDto;
            },

        };
        window.Unit = unit;
    })(jQuery)
</script>