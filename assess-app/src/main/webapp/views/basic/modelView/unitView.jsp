<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>
            <small>
                单元
            </small>
        </h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="basicUnitFrm">
        <div class="detailView" style="display: none">
            <%@include file="/views/basic/modelView/unitDetail.jsp" %>
        </div>
        <div class="saveView" style="display: none">
            <%@include file="/views/basic/modelView/unitSave.jsp" %>
        </div>
    </form>
</div>

<%@include file="/views/basic/modelView/unit/sonUnitView.jsp" %>