<%--
教育条件
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>教育条件信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <form class="form-horizontal">
            <div class="col-sm-3">
                <div type="button" class="btn btn-success"
                     onclick="matchingEducation.prototype.showModel()"
                     data-toggle="modal" href="#divBox"> 新增
                </div>
            </div>
            <table class="table table-bordered" id="MatchingEducationList">
            </table>
        </form>
    </div>
</div>