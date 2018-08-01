<%--
单元基本情况
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form id="frm_unit" class="form-horizontal">
    <input type="hidden" data-name="fieldName" value="<%=request.getParameter("fieldName")%>">
    <input type="hidden" name="id" value="${surveyExamineDataInfoVo.examineUnitVo.id}">
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">单元编号</label>
            <div class="col-sm-5">
                <input type="text" name="unitNumber" class="form-control" placeholder="单元编号" value="${surveyExamineDataInfoVo.examineUnitVo.unitNumber}">
                <%--<textarea name="unitNumber" class="form-control" placeholder="单元编号"></textarea>--%>
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">户梯比</label>
            <div class="col-sm-5">
                <input type="text" name="elevatorHouseholdRatio" class="form-control" placeholder="户梯比" value="${surveyExamineDataInfoVo.examineUnitVo.elevatorHouseholdRatio}">
                <%--<textarea name="unitNumber" class="form-control" placeholder="户梯比">${surveyExamineDataInfoVo.examineUnitVo.elevatorHouseholdRatio}</textarea>--%>
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
                return $("#frm_unit").valid();
            },

            //获取需要保存的数据
            getFormData: function () {
                var data = formParams("frm_unit");
                data.declareId = $("#declareId").val();
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