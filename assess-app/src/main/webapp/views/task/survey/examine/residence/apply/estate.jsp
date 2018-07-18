<%--
  楼盘基础信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<form id="frm_estate" class="form-horizontal">
    <input type="hidden" data-name="fieldName" value="<%=request.getParameter("fieldName")%>">
    <input type="hidden" name="id">
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">楼盘名称<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" placeholder="楼盘名称" required
                     value="${surveyExamineDataInfoVo.examineEstateVo.name}"  name="name" class="form-control">
            </div>
        </div>
    </div>

    <div class="ln_solid"></div>
</form>

<script type="text/javascript">
    $(function () {
        ContainerFunForGetData.push(Estate.getData);//获取数据方法写入容器
    })
</script>
<script type="text/javascript">
    (function ($) {
        //避免方法重复，定义全局变量
        var estate = {
            //获取需要保存的数据
            getData: function (isValid) {
                if (isValid) {
                    if (!$("#frm_estate").valid()) {
                        return false;
                    }
                }
                var data = formParams("frm_estate");
                data.declareId=$("#declareId").val();
                data.examineType=$("#examineType").val();
                var keyValueDto = {};
                keyValueDto.key = $("#frm_estate").find('[data-name="fieldName"]').val();
                keyValueDto.value = data;
                return keyValueDto;
            }
        };

        window.Estate = estate;
    })(jQuery)
</script>

