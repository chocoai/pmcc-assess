<!-- 成本法 -->
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/math/6.0.2/math.js"></script>
    <link href="${pageContext.request.contextPath}/assets/x-editable/css/bootstrap-editable.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <!-- 引入成本法模块 -->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>成本法</h3>
                    <div class="clearfix"></div>
                </div>

                <div class="x_content">
                    <form class="form-horizontal" id="costMethodFrm">
                        <div class="col-sm-12 form-group">
                            <span class="col-sm-1">
                                <label>建筑形态</label>
                            </span>
                            <span class="col-sm-2 col-sm-offset-1 checkbox-inline">
                                <input type="radio" id="building" name="type" value="1">
                                <label for="building">建筑物</label>
                            </span>
                            <span class="col-sm-2  checkbox-inline">
                                <input type="radio" id="construction" name="type" value="2" checked="checked">
                                <label for="construction">在建工程</label>
                            </span>
                        </div>
                    </form>
                </div>

                <div class="x_content">
                    <form class="form-horizontal" id="buildingFrm">

                    </form>
                </div>


                <div class="x_content">
                    <form class="form-horizontal" id="constructionFrm">
                        <%@include file="/views/method/module/costModule/constructionJs.jsp" %>
                        <%@include file="/views/method/module/costModule/construction.jsp" %>
                    </form>
                </div>
            </div>



            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button id="btn_submit" class="btn btn-success" onclick="submit();">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script src="${pageContext.request.contextPath}/assets/x-editable/js/bootstrap-editable.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
<script type="text/javascript">

    var cost = {} ;

    cost.frm = $("#costMethodFrm") ;
    cost.buildingFrm = $("#buildingFrm") ;
    cost.constructionFrm = $("#constructionFrm") ;

    cost.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    cost.isNotBlankObject = function (obj) {
        for (var key in obj) {
            return true;
        }
        return false
    };

    $(document).ready(function () {
        cost.frm.find("input[type='radio'][name='type']").change(function () {
            var data = formSerializeArray(cost.frm);
            if (data.type == '1') {
                cost.buildingFrm.show() ;
                cost.constructionFrm.hide() ;
            }
            if (data.type == '2') {
                cost.buildingFrm.hide() ;
                cost.constructionFrm.show() ;
            }
        });
    });

</script>
<script type="application/javascript">

    //提交
    function submit() {
        var data = {};

        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(data));
        }
        else {
            submitToServer(JSON.stringify(data));
        }
    }
</script>

</html>

