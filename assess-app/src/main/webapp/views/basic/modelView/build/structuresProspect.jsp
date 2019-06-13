<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
构筑物字段基础信息
--%>


<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
            构造特征
        </label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <input type="text" placeholder="构造特征" name="vStructura"
                   class="form-control" value="${basicBuilding.vStructura}">
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
            结构构造
        </label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <input type="text" placeholder="构造特征" name="vStructuralConstruction"
                   class="form-control" value="${basicBuilding.vStructuralConstruction}">
        </div>
    </div>
</div>
<div class="form-group ">
    <label class=" col-md-1 col-sm-1 col-xs-12 control-label">
        添加规格<span class="symbol required"></span>
    </label>
    <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
        <div class="btn btn-xs btn-success"
             onclick="appendHTML(this)"><i
                class="fa fa-plus"></i></div>
    </div>
</div>
<div class="vSpecifications">

</div>
<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">基础作法</label>
        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                        <textarea class="form-control" name="vBasicPractice"
                                  placeholder="基础作法">${basicBuilding.vBasicPractice}</textarea>
        </div>
    </div>
</div>
<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">结构作法</label>
        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                        <textarea class="form-control" name="vStructuralPractice"
                                  placeholder="结构作法">${basicBuilding.vStructuralPractice}</textarea>
        </div>
    </div>
</div>
<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">备注</label>
        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                        <textarea class="form-control" name="remark"
                                  placeholder="备注">${basicBuilding.remark}</textarea>
        </div>
    </div>
</div>



<script type="application/javascript">
    $(function () {
        if ('${basicBuilding}') {
            writeSpecificationsHTMLData(${basicBuilding.vSpecifications});
        }
    })

    var num = 0;

    function appendHTML(this_) {
        var html = "<div class='form-group' >";
        html += "<div class='x-valid'>";

        html += "<label class=' col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label'>" + "规格名称" + "</label>";
        html += "<div class=' col-xs-3  col-sm-3  col-md-3  col-lg-3 '>";
        html += "<input type='text' required class='form-control' name='specificationName" + num + "'>";
        html += "</div>";

        html += "<label class=' col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label'>" + "规格内容" + "</label>";
        html += "<div class=' col-xs-3  col-sm-3  col-md-3  col-lg-3 '>";
        html += "<input type='text' required class='form-control' name='specificationContent" + num + "'>";
        html += "</div>";

        html += " <div class=' col-xs-2  col-sm-2  col-md-2  col-lg-2 '>";
        html += "<input class='btn btn-warning' type='button' value='X' onclick='cleanHTMLData(this)'>" + "</span>";
        html += "</div>";

        html += "</div>";
        html += "</div>";

        num++;
        $(".vSpecifications").append(html);
    }

    function cleanHTMLData(item) {
        var value = "";
        $(item).parent().parent().parent().remove();
    }

    function writeSpecificationsHTMLData(json) {
        if (!json)return;
        $(".vSpecifications").empty();
        var jsonarray = eval(json);
        $.each(jsonarray, function (i, n) {
            var html = "<div class='form-group' >";

            html += "<div class='x-valid'>";
            html += "<label class='col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label'>" + "规格名称" + "</label>";
            html += "<div class='col-xs-3  col-sm-3  col-md-3  col-lg-3 '>";
            html += "<input type='text' required class='form-control' id='specificationName" + i + "' name='specificationName" + i + "' >";
            html += "</div>";
            html += "</div>";

            html += "<div class='x-valid'>";
            html += "<label class='col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label'>" + "规格内容" + "</label>";
            html += "<div class='col-xs-3  col-sm-3  col-md-3  col-lg-3 '>";
            html += "<input type='text' required class='form-control' id='specificationContent" + i + "' name='specificationContent" + i + "' >";
            html += "</div>";
            html += "</div>";

            html += "<div class='x-valid'>";
            html += " <div class=' col-xs-1  col-sm-1  col-md-1  col-lg-1 '>";
            html += "<input class='btn btn-warning' type='button' value='X' onclick='cleanHTMLData(this)'>" + "</span>";
            html += "</div>";
            html += "</div>";

            html += "</div>";
            $(".vSpecifications").append(html);
        })
    }


</script>