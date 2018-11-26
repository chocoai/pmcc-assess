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
    var basicIndexCommon;
    (function () {
        var appId = "${empty basicApply?0:basicApply.id}";
        var estateId = "${empty basicEstate?0:basicEstate.id}";
        var unitId = "${empty basicUnit?0:basicUnit.id}";
        var buildId = "${empty basicBuildingMain?0:basicBuildingMain.id}";
        var houseId = "${empty basicHouse?0:basicHouse.id}";
        var index = null;
        basicIndexCommon = new Object();

        /**
         * 判断字符串以及null等
         */
        basicIndexCommon.isNotBlank = function (item) {
            if (item) {
                if (item == 'undefined') {
                    return false;
                }
                return true;
            }
            return false;
        };
        //注入值 说明是index页面 所有的id为0
        basicIndexCommon.setIndex = function (item) {
            index = item;
        };
        //获取值,为null表示修改页面 如果不为null则表示是index页面(申请页面)
        basicIndexCommon.getIndex = function () {
            return index;
        };
        basicIndexCommon.setAppId = function (item) {
            appId = item;
        };
        basicIndexCommon.getAppId = function () {
            return appId;
        };
        basicIndexCommon.setEstateId = function (item) {
            estateId = item;
        };
        basicIndexCommon.getEstateId = function () {
            return estateId;
        };
        basicIndexCommon.setUnitId = function (item) {
            unitId = item;
        };
        basicIndexCommon.getUnitId = function () {
            return unitId;
        };
        basicIndexCommon.setHouseId = function (item) {
            houseId = item;
        };
        basicIndexCommon.getHouseId = function () {
            return houseId;
        };
        basicIndexCommon.setBuildId = function (number) {
            if (this.isNotBlank(number)) {
                var item = navButtonBuild.getObjArray(number);
                if (this.isNotBlank(item)) {
                    if (this.isNotBlank(item.id)) {
                        buildId = item.id;
                    }
                }
            }
        };
        basicIndexCommon.getBuildId = function () {
            if (!this.isNotBlank(this.getIndex())) {
                return buildId;
            }
            if (this.isNotBlank(this.getIndex())) {
                return "0";
            }
        };
    }());

    basicIndexCommon.config = {
        basicEstate: {
            key: "basicEstate",
            name: "楼盘",
            frm: "basicEstateFrm",
            frmLandState: "basicLandState",
            files: {
                filePlanTotal: "estate_floor_total_plan",//总平面图id和字段
                waterSupplyPlan: "water_supply_plan",//供水平面图id和字段
                powerSupplyPlan: "power_supply_plan",//供电平面图id和字段
                airSupplyPlan: "air_supply_plan",//供气平面图id和字段
                heatingPlan: "heating_plan",//采暖平面图id和字段
                fileAppearance: "estate_floor_Appearance_figure" //外观图id和字段
            }
        },
        basicBuilding: {
            key: "basicBuilding",
            name: "楼栋",
            frm: "basicBuildingFrm",
            files: {
                building_floor_plan: "building_floor_plan",//平面图id和字段 (楼栋)
                building_figure_outside: "building_figure_outside",//外装图id和字段
                building_floor_Appearance_figure: "building_floor_Appearance_figure"//外观图id和字段
            }
        },
        basicUnit: {
            key: "basicUnit",
            name: "单元",
            frm: "basicUnitFrm"
        },
        basicHouse: {
            key: "basicHouse",
            name: "房屋",
            frm: "basicHouseFrm",
            tradingFrm: "basicTradingFrm"
        }
    };


    /**
     * 判断对象 属性
     */
    basicIndexCommon.isNotBlankObjectProperty = function (obj) {
        for (var key in obj) {
            if (this.isNotBlank(obj[key])) {
                return true;
            }
        }
        return false
    };

    /**
     * 判断对象
     */
    basicIndexCommon.isNotBlankObject = function (obj) {
        for (var key in obj) {
            return true;
        }
        return false
    };

    basicIndexCommon.select2Assignment = function (frm, data, name) {
        if (this.isNotBlank(data)) {
            $("#" + frm).find("select." + name).val(data).trigger("change");
            $("#" + frm).find("select[name='" + name + "']").val(data).trigger("change");
            $("#" + frm).find("select[name='" + name + "']").val(data);
        } else {
            $("#" + frm).find("select." + name).val(null).trigger("change");
        }
    };

    basicIndexCommon.uploadFile = function (fieldsName, table, id) {
        FileUtils.uploadFiles({
            target: fieldsName,
            disabledTarget: "btn_submit",
            formData: {
                fieldsName: fieldsName,
                tableName: table,
                tableId: basicIndexCommon.isNotBlank(id) ? id : "0",
                creater: "${currUserAccount}"
            },
            deleteFlag: true
        });
    };
    basicIndexCommon.showFile = function (fieldsName, table, id) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: table,
                tableId: basicIndexCommon.isNotBlank(id) ? id : "0",
                creater: "${currUserAccount}"
            },
            deleteFlag: true
        })
    };
    //开发商选择
    basicIndexCommon.developerSelect = function (this_) {
        assessDeveloper.select(function (row) {
            $(this_).parent().prev().val(row.name);
            $(this_).parent().prev().prev().val(row.id);
        });
    };
    //土地级别选择
    basicIndexCommon.landLevelSelect = function (this_) {
        assessLandLevel.select({
            province: $('#province').val(),
            city: $('#city').val(),
            district: $('#district').val(),
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
        assessBlock.select({
            province: $('#province').val(),
            city: $('#city').val(),
            district: $('#district').val(),
            success: function (row) {
                $(this_).parent().prev().val(row.name);
                $(this_).parent().prev().prev().val(row.id);
            }
        })
    };


    basicIndexCommon.estateSupply = function (name, item) {
        var xItem = "" + item + "";
        if (!this.isNotBlank(name)) {
            return false;
        }
        if (!this.isNotBlank(xItem)) {
            return false;
        }
        var retHtml = '<option value="">-请选择-</option>';
        if (item) {
            retHtml += "<option value='true' selected>" + "有" + "</option>";
        } else {
            retHtml += "<option value='true'>" + "有" + "</option>";
        }
        if (!item) {
            retHtml += "<option value='false' selected>" + "无" + "</option>";
        } else {
            retHtml += "<option value='false'>" + "无" + "</option>";
        }
        $("#" + this.config.basicEstate.frm).find("select[name='" + name + "']").empty().html(retHtml).trigger('change');
    };

    basicIndexCommon.houseLoadList = function () {
        try {
            houseRoom.prototype.loadDataDicList();
            houseWater.prototype.loadDataDicList();
            houseIntelligent.prototype.loadDataDicList();
            houseFaceStreet.prototype.loadDataDicList();
            houseCorollaryEquipment.prototype.loadDataDicList();
            houseNewWind.prototype.loadDataDicList();
            houseAirConditioner.prototype.loadDataDicList();
            houseHeating.prototype.loadDataDicList();
        } catch (e) {
            console.log(e);
            console.log("子项函数不存在请检查");
        }
    };

    basicIndexCommon.houseInit = function (itemA, itemB) {
        houseModelFun.initForm(itemA, itemB);
    };

    basicIndexCommon.houseInitA = function (itemA, itemB) {
        houseModelFun.houseInit(itemA);
        houseModelFun.tradingInit(itemB);
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