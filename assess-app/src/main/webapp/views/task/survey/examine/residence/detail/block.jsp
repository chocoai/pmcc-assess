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