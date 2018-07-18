<%--
  版块
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form id="frm_block" class="form-horizontal">
    <input type="hidden" data-name="fieldName" value="<%=request.getParameter("fieldName")%>">
    <input type="hidden" name="id">
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">省<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <select name="province" class="form-control search-select select2"
                        required="required">

                </select>
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">市<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <select name="city" class="form-control search-select select2"
                        required="required">

                </select>
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">县</label>
            <div class="col-sm-3">
                <select name="district" class="form-control search-select select2">

                </select>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">版块名称<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" placeholder="版块名称" required
                       name="name"
                       class="form-control">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">方位<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" placeholder="方位" required
                       name="position" class="form-control">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">区域性质<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <select name="regionalNature" class="form-control" required></select>
            </div>
        </div>
    </div>

    <div class="ln_solid"></div>
</form>

<script type="text/javascript">

    $(function () {
        ContainerFunForGetData.push(Block.getData);//获取数据方法写入容器
        Block.loadRegionalNature(1);

        //初始化区域信息
        AssessCommon.initAreaInfo({
            provinceTarget: $("#frm_block").find("[name='province']"),
            cityTarget: $("#frm_block").find("[name='city']"),
            districtTarget: $("#frm_block").find("[name='district']"),
            provinceValue: undefined,
            cityValue: undefined,
            districtValue: undefined
        })
        //版块信息自动补全
        <%--$("#frm_block").find("[name='name']").autocomplete({--%>
        <%--source: function (request, response) {--%>
        <%--$.ajax({--%>
        <%--url: "${pageContext.request.contextPath}/case/autoCompleteBlock",--%>
        <%--type: "get",--%>
        <%--dataType: "json",--%>
        <%--data: {--%>
        <%--maxRows: 10,--%>
        <%--term: request.term--%>
        <%--},--%>
        <%--success: function (result) {--%>
        <%--response($.each(result.data, function (i, item) {--%>
        <%--return {--%>
        <%--label: item.value,--%>
        <%--value: item.key--%>
        <%--}--%>
        <%--}));--%>
        <%--}--%>
        <%--});--%>
        <%--},--%>
        <%--minLength: 2,--%>
        <%--select: function (event, ele) {--%>
        <%--console.log(ele.item);//直接读取案例中对应数据内容--%>
        <%--}--%>
        <%--});--%>
    })
</script>
<script type="text/javascript">
    (function ($) {
        //避免方法重复，定义全局变量
        var block = {
            //获取需要保存的数据
            getData: function (isValid) {
                if (isValid) {
                    if (!$("#frm_block").valid()) {
                        return false;
                    }
                }
                var data = formParams("frm_block");
                data.declareId=$("#declareId").val();
                data.examineType=$("#examineType").val();
                var keyValueDto = {};
                keyValueDto.key = $("#frm_block").find('[data-name="fieldName"]').val();
                keyValueDto.value = data;
                return keyValueDto;
            },

            //加载区域性质
            loadRegionalNature: function (value) {
                AssessCommon.loadDataDicByKey(AssessDicKey.examineBlockRegionalNature, value, function (html, data) {
                    $("#frm_block").find("[name='regionalNature']").append(html);
                })
            }
        };

        window.Block = block;
    })(jQuery)
</script>
