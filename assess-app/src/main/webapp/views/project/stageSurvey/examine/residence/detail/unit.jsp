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
                <input type="text" name="unitNumber" readonly="readonly" class="form-control" placeholder="单元编号" value="${surveyExamineDataInfoVo.examineUnitVo.unitNumber}">
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">梯户比</label>
            <div class="col-sm-5">
                <input type="text" name="elevatorHouseholdRatio" readonly="readonly" class="form-control" placeholder="梯户比" value="${surveyExamineDataInfoVo.examineUnitVo.elevatorHouseholdRatio}">
            </div>
        </div>
    </div>

    <div class="ln_solid"></div>
</form>

<script>
    var unit_config ;
    (function () {
        var frm = "frm_unit" ;
        unit_config = {
            getFrm:function () {
                return frm;
            }
        }
    })();
    var unit = Object.create(unit_config);
    unit.init = function () {
        $("#" + unit.getFrm() + " :input").attr("readonly","readonly");
    };


    $(function () {
        unit.init();
    });
</script>

