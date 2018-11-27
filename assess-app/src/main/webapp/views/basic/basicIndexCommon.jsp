<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${pageContext.request.contextPath}/js/basic/industry.js"></script>
<script src="${pageContext.request.contextPath}/js/developer.select.js"></script>
<script src="${pageContext.request.contextPath}/js/builder.select.js"></script>
<script src="${pageContext.request.contextPath}/js/property.select.js"></script>
<script src="${pageContext.request.contextPath}/js/land.level.select.js"></script>
<script src="${pageContext.request.contextPath}/js/block.select.js"></script>
<div class="x_panel">
    <div class="x_title">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
            </li>
        </ul>
        <h3>
            ${basicApply.id eq 0?'申请信息':basicApply.fullName}
        </h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div role="tabpanel" id="contentTabPanel" data-example-id="togglable-tabs">
            <ul class="nav nav-tabs bar_tabs" role="tablist" id="caseTab">
                <li role="presentation" style="display: none;">
                    <a href="#caseEstate" role="tab" data-toggle="tab" id="profile-tab1" aria-expanded="true">
                        楼盘
                        <c:if test="${basicApply.id eq 0}">
                            <i class="fa fa-close" onclick="basicIndexCommon.hideTab(this);"
                               style="margin-left: 20px;cursor: pointer;"></i>
                        </c:if>
                    </a>
                </li>
                <li role="presentation" style="display: none;">
                    <a href="#caseBuilding" role="tab" data-toggle="tab" id="profile-tab2" aria-expanded="true">
                        楼栋
                        <c:if test="${basicApply.id eq 0}">
                            <i class="fa fa-close" onclick="basicIndexCommon.hideTab(this);"
                               style="margin-left: 20px;cursor: pointer;"></i>
                        </c:if>
                    </a>
                </li>
                <li role="presentation" style="display: none;">
                    <a href="#caseUnit" role="tab" data-toggle="tab" id="profile-tab3" aria-expanded="true">
                        单元
                        <c:if test="${basicApply.id eq 0}">
                            <i class="fa fa-close" onclick="basicIndexCommon.hideTab(this);"
                               style="margin-left: 20px;cursor: pointer;"></i>
                        </c:if>
                    </a>
                </li>
                <li role="presentation" style="display: none;">
                    <a href="#caseHouse" role="tab" data-toggle="tab" id="profile-tab4" aria-expanded="true">
                        房屋
                        <c:if test="${basicApply.id eq 0}">
                            <i class="fa fa-close" onclick="basicIndexCommon.hideTab(this);"
                               style="margin-left: 20px;cursor: pointer;"></i>
                        </c:if>
                    </a>
                </li>
            </ul>
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane fade" id="caseEstate" aria-labelledby="profile-tab1">
                    <div>
                        <%@include file="/views/basic/modelView/estateView.jsp" %>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane fade" id="caseBuilding" aria-labelledby="profile-tab2">
                    <div>
                        <%@include file="/views/basic/modelView/buildingView.jsp" %>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane fade" id="caseUnit" aria-labelledby="profile-tab3">
                    <div>
                        <%@include file="/views/basic/modelView/unitView.jsp" %>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane fade" id="caseHouse" aria-labelledby="profile-tab4">
                    <div>
                        <%@include file="/views/basic/modelView/houseView.jsp" %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 此页面用做修改 和 申请页面引用 -->
<script type="text/javascript">
    var basicIndexCommon = {};

    //开发商选择
    basicIndexCommon.developerSelect = function (this_) {
        assessDeveloper.select(function (row) {
            $(this_).parent().prev().val(row.name);
            $(this_).parent().prev().prev().val(row.id);
        });
    };

    //土地级别选择
    basicIndexCommon.landLevelSelect = function (this_) {
        var $form = $(this_).closest('form');
        assessLandLevel.select({
            province: $form.find('[name=province]').val(),
            city: $form.find('[name=city]').val(),
            district: $form.find('[name=district]').val(),
            success: function (data) {
                $(this_).parent().prev().val(data.name);
                $(this_).parent().prev().prev().val(data.id);
            }
        })
    };
    //物业选择
    basicIndexCommon.propertySelect = function (this_) {
        assessProperty.select(function (row) {
            $(this_).parent().prev().val(row.name);
            $(this_).parent().prev().prev().val(row.id);
        });
    };
    //建造商选择
    basicIndexCommon.builderSelect = function (this_) {
        assessBuilder.select(function (row) {
            $(this_).parent().prev().val(row.name);
            $(this_).parent().prev().prev().val(row.id);
        });
    };
    //板块选择
    basicIndexCommon.blockSelect = function (this_) {
        var $form = $(this_).closest('form');
        assessBlock.select({
            province: $form.find('[name=province]').val(),
            city: $form.find('[name=city]').val(),
            district: $form.find('[name=district]').val(),
            success: function (row) {
                $(this_).parent().prev().val(row.name);
                $(this_).parent().prev().prev().val(row.id);
            }
        })
    };

    //是否参与标志
    basicIndexCommon.partInFlag = {
        estate: '${basicApply.estatePartInFlag}',
        building: '${basicApply.buildingPartInFlag}',
        unit: '${basicApply.unitPartInFlag}',
        house: '${basicApply.housePartInFlag}'
    };

    //显示楼盘tab
    basicIndexCommon.showEstateTab = function () {
        $('#contentTabPanel').find('[id=caseEstate]').addClass('active');
        var a = $('#contentTabPanel').find('[href=#caseEstate]');
        a.closest('li').show();
        a.tab('show');
        basicIndexCommon.partInFlag.estate = true;
    }

    //显示楼栋tab
    basicIndexCommon.showBuildingTab = function () {
        $('#contentTabPanel').find('[id=caseBuilding]').addClass('active');
        var a = $('#contentTabPanel').find('[href=#caseBuilding]');
        a.closest('li').show();
        a.tab('show');
        basicIndexCommon.partInFlag.building = true;
    }

    //显示单元tab
    basicIndexCommon.showUnitTab = function () {
        $('#contentTabPanel').find('[id=caseUnit]').addClass('active');
        var a = $('#contentTabPanel').find('[href=#caseUnit]');
        a.closest('li').show();
        a.tab('show');
        basicIndexCommon.partInFlag.unit = true;
    }

    //显示房屋tab
    basicIndexCommon.showHouseTab = function () {
        $('#contentTabPanel').find('[id=caseHouse]').addClass('active');
        var a = $('#contentTabPanel').find('[href=#caseHouse]');
        a.closest('li').show();
        a.tab('show');
        basicIndexCommon.partInFlag.house = true;
    }

    //隐藏tab标签
    basicIndexCommon.hideTab = function (_this) {
        var a = $(_this).closest('a');
        var href = a.attr('href').replace('#', '');
        switch (href) {
            case "#caseEstate":
                basicIndexCommon.partInFlag.estate = false;
                break;
            case "#caseBuilding":
                basicIndexCommon.partInFlag.building = false;
                break;
            case "#caseUnit":
                basicIndexCommon.partInFlag.unit = false;
                break;
            case "#caseHouse":
                basicIndexCommon.partInFlag.house = false;
                break;
        }
        a.closest('[role="tabpanel"]').find('[id=' + href + ']').removeClass('active');
        a.closest('li').hide();
    }
</script>