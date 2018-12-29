<%--
  环境因素
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>环境因素信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="matchingEnvironment.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="MatchingEnvironmentList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>
