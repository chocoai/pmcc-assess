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
        <li class="active" onclick="Block.init(ExamineInfo.config.blockId)">
            <a href="#tab_content_block" data-toggle="tab">版块</a>
        </li>
        <li class="tab_estate" style="display: none;" onclick="Estate.init(ExamineInfo.config.estateId)">
            <a href="#tab_content_estate" data-toggle="tab">楼盘</a>
        </li>
        <li class="tab_building" style="display: none;" onclick="Building.init(ExamineInfo.config.buildingId)">
            <a href="#tab_content_building" data-toggle="tab">楼栋</a>
        </li>
        <li class="tab_unit" style="display: none;" onclick="Unit.init(ExamineInfo.config.unitId)">
            <a href="#tab_content_unit" data-toggle="tab">单元</a>
        </li>
        <li class="tab_house" style="display: none;" onclick="House.init(ExamineInfo.config.houseId)">
            <a href="#tab_content_house" data-toggle="tab">房屋</a>
        </li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane active" id="tab_content_block">
            <%@include file="/views/share/examine/residence/apply/block.jsp" %>
        </div>
        <div class="tab-pane tab_estate" id="tab_content_estate" style="display: none">
            <%@include file="/views/share/examine/residence/apply/estate.jsp" %>
        </div>
        <div class="tab-pane tab_building" id="tab_content_building" style="display: none">
            <%@include file="/views/share/examine/residence/apply/building.jsp" %>
        </div>
        <div class="tab-pane tab_unit" id="tab_content_unit" style="display: none">
            <%@include file="/views/share/examine/residence/apply/unit.jsp" %>
        </div>
        <div class="tab-pane tab_house" id="tab_content_house" style="display: none">
            <%@include file="/views/share/examine/residence/apply/house.jsp" %>
        </div>
    </div>
    <div class="clearfix"></div>
</div>

<script type="text/javascript">
    $(function () {
        ExamineInfo.initTab();
    })
</script>

<script type="text/javascript">
    (function ($) {
        //判断是否为空
        function isNotEmpty(ele) {
            if (ele) {
                return true;
            } else {
                return false;unde
            }
        }

        //避免方法重复，定义全局变量
        var examineInfo = {
            //初始化tab头部所需参数
            config: {
                blockId: undefined,
                estateId: undefined,
                buildingId: undefined,
                unitId: undefined,
                houseId: undefined
            },

            //初始化tab
            initTab: function () {
                isNotEmpty(examineInfo.config.blockId) ? $('.tab_estate').show() : $('.tab_estate').hide();
                isNotEmpty(examineInfo.config.estateId) ? $('.tab_building').show() : $('.tab_building').hide();
                isNotEmpty(examineInfo.config.buildingId) ? $('.tab_unit').show() : $('.tab_unit').hide();
                isNotEmpty(examineInfo.config.unitId) ? $('.tab_house').show() : $('.tab_house').hide();
            }
        };

        window.ExamineInfo = examineInfo;
    })(jQuery)
</script>