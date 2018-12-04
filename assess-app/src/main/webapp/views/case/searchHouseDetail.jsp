<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">

            <div class="x_panel">
                <div class="x_content">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class="col-sm-3 ">
                                <input type="button" class="btn btn-default" onclick="findUnit()" value="单元详情">
                            </div>

                            <div class="col-sm-3 ">
                                <input type="button" value="建筑详情" class="btn btn-default" onclick="findBuild('${caseUnit.buildingMainId}')">
                            </div>

                            <div class="form-group">
                                <div class="col-sm-3 ">
                                    <input type="button" value="楼盘详情" class="btn btn-default" onclick="findEstate('${caseBuildingMain.estateId}')">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <%@include file="/views/case/caseHouse/houseDetail.jsp" %>

            <div class="x_panel">
                <div class="x_content">
                    <div class="form-group">
                        <div class="col-sm-6 col-sm-offset-6">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                关闭
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">


    /**
     * 单元详情
     */
    function findUnit() {
        var href = "${pageContext.request.contextPath}/case/findUnitView";
        href += "?id=${caseHouse.unitId}";
        window.open(href, "");
    }

    /**
     * 建筑详情
     * @param id
     */
    function findBuild(id) {
        console.log(id);
        if (id) {
            var href = "${pageContext.request.contextPath}/case/findBuildView";
            href += "?id=" + id;
            window.open(href, "");
        } else {
            Alert("无数据");
            return false;
        }
    }

    /**
     * 楼盘详情
     * @param id
     */
    function findEstate(id) {
        if (id) {
            var href = "${pageContext.request.contextPath}/case/findEstate";
            href += "?id=" + id;
            window.open(href, "");
        } else {
            Alert("无数据");
            return false;
        }
    }


</script>

</html>
