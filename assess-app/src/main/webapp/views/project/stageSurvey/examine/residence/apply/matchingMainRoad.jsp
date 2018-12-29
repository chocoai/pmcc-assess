<%--
  主干道
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>主干道信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="matchingMainRoad.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="MainRoadList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>
