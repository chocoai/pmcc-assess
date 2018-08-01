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
                       name="name" value="${surveyExamineDataInfoVo.examineBlockVo.name}"
                       class="form-control">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">方位<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" placeholder="方位" required
                       value="${surveyExamineDataInfoVo.examineBlockVo.position}"
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
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">区域描述</label>
            <div class="col-sm-11">
                <textarea name="remark" class="form-control" placeholder="区域描述">${surveyExamineDataInfoVo.examineBlockVo.remark}</textarea>
            </div>
        </div>
    </div>
    <div class="ln_solid"></div>
</form>

<script>
    var config_block = {
        frm : "frm_block",
        getFrm:function () {
            return this.frm;
        }
    }
    var block = Object.create(config_block);
    //加载初始化数据
    block.init = function () {
        block.initAreaInfo();
        block.loadRegionalNature("${surveyExamineDataInfoVo.examineBlockVo.regionalNature}");
        $("#"+block.getFrm()+" :input").attr("readonly","readonly");
    };
    //加载区域性质
    block.loadRegionalNature = function (value) {
        AssessCommon.loadDataDicByKey(AssessDicKey.examineBlockRegionalNature, value, function (html, data) {
            $("#"+block.getFrm()).find("[name='regionalNature']").append(html);
        });
    };
    block.initAreaInfo = function () {
        //初始化区域信息
        AssessCommon.initAreaInfo({
            provinceTarget: $("#"+block.getFrm()).find("[name='province']"),
            cityTarget: $("#"+block.getFrm()).find("[name='city']"),
            districtTarget: $("#"+block.getFrm()).find("[name='district']"),
            provinceValue: "${surveyExamineDataInfoVo.examineBlockVo.province}",
            cityValue: "${surveyExamineDataInfoVo.examineBlockVo.city}",
            districtValue: "${surveyExamineDataInfoVo.examineBlockVo.district}"
        })
    };

    $(function () {
        // block.init();
    });
</script>