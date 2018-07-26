<%--
 土地实体情况
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/views/share/main_css.jsp" %>
<div class="x_content">
    <div class="x_title">
        <h3>土地实体情况 </h3>
        <div class="clearfix"></div>
    </div>
    <form id="frm_estateLandState" class="form-horizontal">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地名称<span class="symbol required"></span></label>
                <div class="col-sm-11">
                    <input type="text" class="form-control" required="required" name="name" placeholder="名称">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地用途<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 landUse" name="landUse" required="required">
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">土地级别<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 landLevel" name="landLevel" required="required">
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">土地面积<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="土地面积" required="required" name="landArea">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">东至<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="东至" required="required" name="eastTo">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">南至<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="南至" required="required" name="southTo">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">西至<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="西至" required="required" name="westTo">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">北至<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="北至" required="required" name="northTo">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地形状状况<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="土地形状状况" required="required" name="shapeState">
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
    $(function () {
        ContainerFunForValid.push(EstateLandState.valid);//数据验证方法写入容器
        ContainerFunForGetData.push(EstateLandState.getFormData);//获取数据方法写入容器
        estateLandState.prototype.init();
    })
</script>
<script type="text/javascript">
    function estateLandState() {

    }
    estateLandState.prototype.init = function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/examineBuilding/estate_total_land_use",
            type: "get",
            dataType: "json",
            data: {type: "DataDeveloper"},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var gradeNum = data.length;
                    var option = "<option value=''>请选择</option>";
                    if (gradeNum > 0) {
                        for (var i = 0; i < gradeNum; i++) {
                            option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                        }
                        if ($("#" + EstateLandState.config().frm + " .landUse").size() > 0) {
                            $("#" + EstateLandState.config().frm + " .landUse").html(option);
                            $("#" + EstateLandState.config().frm + " .landUse").select2({minimumResultsForSearch: -1});//加载样式
                        }
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });

        $.ajax({
            url: "${pageContext.request.contextPath}/examineBuilding/estate_total_land_level",
            type: "get",
            dataType: "json",
            data: {type: "DataDeveloper"},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var gradeNum = data.length;
                    var option = "<option value=''>请选择</option>";
                    if (gradeNum > 0) {
                        for (var i = 0; i < gradeNum; i++) {
                            option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                        }
                        if ($("#" + EstateLandState.config().frm + " .landLevel").size() > 0) {
                            $("#" + EstateLandState.config().frm + " .landLevel").html(option);
                            $("#" + EstateLandState.config().frm + " .landLevel").select2({minimumResultsForSearch: -1});//加载样式
                        }
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    (function ($) {
        //避免方法重复，定义全局变量
        var estateLandState = {
            config: function () {
                var data = {};
                data.frm = "frm_estateLandState";
                return data;
            },
            //验证
            valid: function () {
                return $("#" + estateLandState.config().frm).valid();
            },

            //获取需要保存的数据
            getFormData: function () {
                var data = formParams(estate.config().frm);
                data.declareId = $("#declareId").val();
                data.examineType = $("#examineType").val();
                var keyValueDto = {};
                keyValueDto.key = $("#" + estateLandState.config().frm).find('[data-name="fieldName"]').val();
                keyValueDto.value = data;
                return keyValueDto;
            }
        };

        window.EstateLandState = estateLandState;
    })(jQuery)
</script>



