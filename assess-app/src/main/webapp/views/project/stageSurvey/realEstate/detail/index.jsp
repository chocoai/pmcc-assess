<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <title>详情</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <div class="page-title" style="margin: 0px">
                <div class="title_left">
                    <h2>
                        详情
                    </h2>
                </div>
            </div>
            <div class="x_panel">

                <div class="x_content">
                    <div role="tabpanel" id="contentTabPanel" data-example-id="togglable-tabs">
                        <ul class="nav nav-tabs bar_tabs" role="tablist" id="caseTab">
                            <li role="presentation" style="display: none;">
                                <a href="#caseEstate" role="tab" data-toggle="tab" id="profile-tab1" aria-expanded="true">
                                    楼盘
                                    <i class="fa fa-close" style="margin-left: 20px;cursor: pointer;"></i>
                                </a>
                            </li>
                            <li role="presentation" style="display: none;">
                                <a href="#caseBuilding" role="tab" data-toggle="tab" id="profile-tab2" aria-expanded="true">
                                    楼栋
                                    <i class="fa fa-close" style="margin-left: 20px;cursor: pointer;"></i>
                                </a>
                            </li>
                            <li role="presentation" style="display: none;">
                                <a href="#caseUnit" role="tab" data-toggle="tab" id="profile-tab3" aria-expanded="true">
                                    单元
                                    <i class="fa fa-close" style="margin-left: 20px;cursor: pointer;"></i>
                                </a>
                            </li>
                            <li role="presentation" style="display: none;">
                                <a href="#caseHouse" role="tab" data-toggle="tab" id="profile-tab4" aria-expanded="true">
                                    房屋
                                    <i class="fa fa-close" style="margin-left: 20px;cursor: pointer;"></i>
                                </a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div role="tabpanel" class="tab-pane fade" id="caseEstate" aria-labelledby="profile-tab1">
                                <div>
                                    <%@include file="/views/project/stageSurvey/realEstate/detail/estate.jsp" %>
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="caseBuilding" aria-labelledby="profile-tab2">
                                <div>
                                    <%@include file="/views/project/stageSurvey/realEstate/detail/building.jsp" %>
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="caseUnit" aria-labelledby="profile-tab3">
                                <div>
                                    <%@include file="/views/project/stageSurvey/realEstate/detail/unit.jsp" %>
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="caseHouse" aria-labelledby="profile-tab4">
                                <div>
                                    <%@include file="/views/project/stageSurvey/realEstate/detail/house.jsp" %>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div style="text-align: center;">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            关闭
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
</html>
<script type="text/javascript">

</script>

