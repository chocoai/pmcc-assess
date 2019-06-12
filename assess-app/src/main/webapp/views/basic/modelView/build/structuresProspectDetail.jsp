<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
            构造特征
        </label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control">${basicBuilding.vStructura}</label>
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
            结构构造
        </label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <label class="form-control">${basicBuilding.vStructuralConstruction}</label>
        </div>
    </div>
</div>
<div class="vSpecifications">

</div>
<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">基础作法</label>
        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
            <label class="form-control">${basicBuilding.vBasicPractice}</label>
        </div>
    </div>
</div>
<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">结构作法</label>
        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
            <label class="form-control">${basicBuilding.vStructuralPractice}</label>
        </div>
    </div>
</div>
<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">备注</label>
        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
            <label class="form-control">${basicBuilding.remark}</label>
        </div>
    </div>
</div>


<script type="application/javascript">
    $(function () {
        if ('${basicBuilding.vSpecifications}') {
            writeSpecificationsHTMLData(${basicBuilding.vSpecifications});
        }
    })


    function writeSpecificationsHTMLData(json) {
        $(".vSpecifications").empty();
        var jsonarray = eval(json);
        $.each(jsonarray, function (i, n) {
            var html = "<div class='form-group' >";

            html += "<div class='x-valid'>";
            html += "<label class=' col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label'>" + "规格名称" + "</label>";
            html += "<div class=' col-xs-3  col-sm-3  col-md-3  col-lg-3 '>";
            html += "<label class='form-control'>" + n["specificationName"];
            html += "</label>";
            html += "</div>";
            html += "</div>";

            html += "<div class='x-valid'>";
            html += "<label class=' col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label'>" + "规格内容" + "</label>";
            html += "<div class=' col-xs-3  col-sm-3  col-md-3  col-lg-3  '>";
            html += "<label class='form-control'>" + n["specificationContent"];
            html += "</label>";
            html += "</div>";
            html += "</div>";

            html += "</div>";
            $(".vSpecifications").append(html);

        })
    }
</script>