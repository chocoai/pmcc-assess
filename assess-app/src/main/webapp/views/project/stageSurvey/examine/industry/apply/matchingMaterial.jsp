<%--
  原材料及销售条件
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel" id="industryMaterial">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>原料供应及销售条件信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="matchingMaterial.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="MatchingMaterialList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>