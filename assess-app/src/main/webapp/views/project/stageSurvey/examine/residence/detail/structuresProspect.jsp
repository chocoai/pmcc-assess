<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
 楼栋基础信息
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    构筑物评估查看
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form class="form-horizontal" id="basicBuildingFrm">
                <input type="hidden" name="id">
                <div  class="row form-group">
                    <div class="col-md-12">
                    <div class="form-inline x-valid">
                        <label class="col-sm-1 control-label">
                            构造特征
                        </label>
                        <div class="col-sm-3">
                            <label class="form-control input-full">${basicBuilding.vStructura}</label>
                        </div>
                        <label class="col-sm-1 control-label">
                            结构构造
                        </label>
                        <div class="col-sm-3">
                            <label class="form-control input-full">${basicBuilding.vStructuralConstruction}</label>
                        </div>
                    </div>
                    </div>
                </div>
                <div class="vSpecifications">

                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                    <div class="form-inline x-valid">
                        <label class="col-sm-1 control-label">基础作法</label>
                        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                            <label class="form-control input-full">${basicBuilding.vBasicPractice}</label>
                        </div>
                    </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                    <div class="form-inline x-valid">
                        <label class="col-sm-1 control-label">结构作法</label>
                        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                            <label class="form-control input-full">${basicBuilding.vStructuralPractice}</label>
                        </div>
                    </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                    <div class="form-inline x-valid">
                        <label class="col-sm-1 control-label">备注</label>
                        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                            <label class="form-control input-full">${basicBuilding.remark}</label>
                        </div>
                    </div>
                    </div>
                </div>


            </form>
        </div>
    </div>
</div>



</html>

<script type="application/javascript">
    $(function(){
        if ('${basicBuilding.vSpecifications}') {
            writeSpecificationsHTMLData(${basicBuilding.vSpecifications});
        }
    })


    function writeSpecificationsHTMLData(json) {
        if (!json)return;
        $(".vSpecifications").empty();
        var jsonarray = eval(json);
        $.each(jsonarray, function (i, n) {
            var html = "<div class='row form-group' >";
            html += '<div class="col-md-12">';
            html += "<div class='form-inline x-valid'>";

            html += "<label class='col-sm-1 control-label'>" + "规格名称" + "</label>";
            html += "<div class='col-sm-3'>";
            html += "<label class='form-control input-full'>" + n["specificationName"];
            html += "</label>";
            html += "</div>";

            html += "<label class='col-sm-1 control-label'>" + "规格内容" + "</label>";
            html += "<div class='col-sm-3 '>";
            html += "<label class='form-control input-full'>" + n["specificationContent"];
            html += "</label>";
            html += "</div>";

            html += "</div>";
            html += "</div>";
            html += "</div>";
            $(".vSpecifications").append(html);

        })
    }
</script>