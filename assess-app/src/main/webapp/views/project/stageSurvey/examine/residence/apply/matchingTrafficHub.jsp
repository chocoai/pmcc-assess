<%--
  交通枢纽
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>交通枢纽信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <form class="form-horizontal">
            <div class="col-sm-3">
                <button type="button" class="btn btn-success" onclick="matchingTrafficHub.prototype.showModel()"
                        data-toggle="modal" href="#divBox"> 新增
                </button>

            </div>

            <table class="table table-bordered" id="MatchingTrafficHubList">
            </table>
        </form>
    </div>
</div>