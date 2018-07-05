<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-7-5
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_content">
    <ul class="nav nav-tabs bar_tabs">
        <li class="active"><a href="#tab_block" data-toggle="tab">版块</a>
        </li>
        <li><a href="#tab_estate" data-toggle="tab">楼盘</a>
        </li>
        <li><a href="#tab_building" data-toggle="tab">楼栋</a>
        </li>
        <li><a href="#tab_unit" data-toggle="tab">单元</a>
        </li>
        <li><a href="#tab_house" data-toggle="tab">房屋</a>
        </li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane active" id="tab_block">
            版块
            <%@include file="/views/share/examine/block.jsp" %>
        </div>
        <div class="tab-pane" id="tab_estate">
            楼盘
            <%@include file="/views/share/examine/estate.jsp" %>
        </div>
        <div class="tab-pane" id="tab_building">
            楼栋
            <%@include file="/views/share/examine/building.jsp" %>
        </div>
        <div class="tab-pane" id="tab_unit">
            单元
            <%@include file="/views/share/examine/unit.jsp" %>
        </div>
        <div class="tab-pane" id="tab_house">
            房间
            <%@include file="/views/share/examine/house.jsp" %>
        </div>
    </div>
    <div class="clearfix"></div>
</div>
<script type="text/javascript">
    //避免方法重复，定义全局变量
    var examineInfo={

    };
</script>