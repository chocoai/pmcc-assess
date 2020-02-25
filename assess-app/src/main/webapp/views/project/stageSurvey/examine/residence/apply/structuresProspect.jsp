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
                    <button class="btn btn-icon btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form class="form-horizontal" id="basicBuildingFrm">
                <input type="hidden" name="id" value="${basicBuilding.id}">
                <input type="hidden" name="estateId" value="${basicApplyBatch.estateId}">

                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                构造特征
                            </label>
                            <div class="col-sm-3 ">
                                <input type="text" placeholder="构造特征" name="vStructura"
                                       class="form-control input-full" value="${basicBuilding.vStructura}">
                            </div>
                            <label class="col-sm-1 control-label">
                                结构构造
                            </label>
                            <div class="col-sm-3 ">
                                <input type="text" placeholder="构造特征" name="vStructuralConstruction"
                                       class="form-control input-full" value="${basicBuilding.vStructuralConstruction}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group ">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                添加规格<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-2 ">
                                <div class="btn btn-xs btn-success btn-sm"
                                     onclick="appendHTML(this)"><i
                                        class="fa fa-plus"></i></div>
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
                            <div class=" col-sm-11 ">
                        <textarea class="form-control input-full" name="vBasicPractice"
                                  placeholder="基础作法">${basicBuilding.vBasicPractice}</textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">结构作法</label>
                            <div class=" col-sm-11 ">
                        <textarea class="form-control input-full" name="vStructuralPractice"
                                  placeholder="结构作法">${basicBuilding.vStructuralPractice}</textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">备注</label>
                            <div class=" col-sm-11 ">
                        <textarea class="form-control input-full" name="remark"
                                  placeholder="备注">${basicBuilding.remark}</textarea>
                            </div>
                        </div>
                    </div>
                </div>


            </form>
        </div>
    </div>
</div>


<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.build.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js?v=${assessVersion}"></script>
</html>

<script type="application/javascript">
    $(function () {
        if ('${basicBuilding}') {
            writeSpecificationsHTMLData(${basicBuilding.vSpecifications});
        }
    })

    var num = 0;

    function appendHTML(this_) {
        var html = "<div class='row form-group' >";
        html += '<div class="col-md-12">';
        html += "<div class='form-inline x-valid'>";

        html += "<label class='col-sm-1 control-label'>" + "规格名称" + "</label>";
        html += "<div class='col-sm-3 '>";
        html += "<input type='text' required class='form-control input-full' name='specificationName" + num + "'>";
        html += "</div>";

        html += "<label class='col-sm-1 control-label'>" + "规格内容" + "</label>";
        html += "<div class='col-sm-3 '>";
        html += "<input type='text' required class='form-control input-full' name='specificationContent" + num + "'>";
        html += "</div>";

        html += " <div class='col-sm-2 '>";
        html += "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='cleanHTMLData(this)'>" + "</span>";
        html += "</div>";

        html += "</div>";
        html += "</div>";
        html += "</div>";

        num++;
        $(".vSpecifications").append(html);
    }

    function cleanHTMLData(item) {
        var value = "";
        $(item).closest('.form-group').remove();
    }

    function writeSpecificationsHTMLData(json) {
        if (!json) return;
        $(".vSpecifications").empty();
        var jsonarray = eval(json);
        console.log(jsonarray + "==33==")
        $.each(jsonarray, function (i, n) {
            var html = "<div class='row form-group' >";
            html += '<div class="col-md-12">';
            html += "<div class='form-inline x-valid'>";

            html += "<label class='col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label'>" + "规格名称" + "</label>";
            html += "<div class='col-xs-3  col-sm-3  col-md-3  col-lg-3 '>";
            html += "<input type='text' required class='form-control input-full' value='" + n["specificationName"] + "' id='specificationName" + i + "' name='specificationName" + i + "' >";
            html += "</div>";

            html += "<label class='col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label'>" + "规格内容" + "</label>";
            html += "<div class='col-xs-3  col-sm-3  col-md-3  col-lg-3 '>";
            html += "<input type='text' required class='form-control input-full' value='" + n["specificationContent"] + "' id='specificationContent" + i + "' name='specificationContent" + i + "' >";
            html += "</div>";

            html += " <div class='col-sm-1'>";
            html += "<input class='btn btn-warning' type='button' value='X' onclick='cleanHTMLData(this)'>" + "</span>";
            html += "</div>";

            html += "</div>";
            html += "</div>";
            html += "</div>";
            $(".vSpecifications").append(html);
            num++;
        })
    }


</script>