
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

                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h2>
                        单元
                    </h2>
                    <div class="clearfix"></div>
                </div>

                <div class="x_content">
                    <div class="x_title">
                        <h3>单元基本信息 </h3>
                        <div class="clearfix"></div>
                    </div>
                    <form class="form-horizontal" id="frm_Unit">
                        <input type="hidden" name="id" value="${caseUnit.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">单元编号</label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="单元编号" readonly="readonly"
                                           name="unitNumber" class="form-control" value="${caseUnit.unitNumber}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">梯户比</label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="梯户比" readonly="readonly"
                                           name="elevatorHouseholdRatio" class="form-control" value="${caseUnit.elevatorHouseholdRatio}">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <!-- 楼栋内装信息	 -->
            <div style="display: ${hasUnitDecorateData?'block':'none'};">
                <%@include file="/views/case/caseUnit/caseUnitDecorate.jsp" %>
            </div>

            <!-- 户型信息 -->
            <div style="display: ${hasUnitHuxingData?'block':'none'};">
                <%@include file="/views/case/caseUnit/caseUnitHuxing.jsp" %>
            </div>

            <!-- 电梯信息 -->
            <div style="display: ${hasUnitElevatorData?'block':'none'};">
                <%@include file="/views/case/caseUnit/caseUnitElevator.jsp" %>
            </div>

            <div class="x_panel">
                <div class="x_content">
                    <div class="form-group">
                        <div class="col-sm-6 col-sm-offset-5">
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
<script>


</script>
<%@include file="/views/share/main_footer.jsp" %>
</body>
</html>
