<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${pageContext.request.contextPath}/js/basic/industry.js"></script>
<script src="${pageContext.request.contextPath}/js/developer.select.js"></script>
<script src="${pageContext.request.contextPath}/js/builder.select.js"></script>
<script src="${pageContext.request.contextPath}/js/property.select.js"></script>
<script src="${pageContext.request.contextPath}/js/land.level.select.js"></script>
<script src="${pageContext.request.contextPath}/js/block.select.js"></script>
<div class="x_panel">
    <div class="x_title">
        <h2>
            <small>信息</small>
        </h2>
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
            </li>
        </ul>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div role="tabpanel" data-example-id="togglable-tabs">
            <ul class="nav nav-tabs bar_tabs" role="tablist" id="caseTab">
                <li role="presentation" class=""><a href="#caseEstate" role="tab" id="profile-tab1"
                                                    aria-expanded="true">楼盘</a>
                </li>
                <li role="presentation" class=""><a href="#caseBuild" role="tab" id="profile-tab2"
                                                    aria-expanded="false">楼栋</a>
                </li>
                <li role="presentation" class=""><a href="#caseUnit" role="tab" id="profile-tab3"
                                                    aria-expanded="false">单元</a>
                </li>
                <li role="presentation" class=""><a href="#caseHouse" role="tab" id="profile-tab4"
                                                    aria-expanded="false">房屋</a>
                </li>
            </ul>
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane fade" id="caseEstate" aria-labelledby="profile-tab1">
                    <div>
                        <%@include file="/views/basic/modelView/estateView.jsp" %>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane fade" id="caseBuild" aria-labelledby="profile-tab2">
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
        // AssessCommon.initAreaInfo({
        //     provinceTarget: $("#province"),
        //     cityTarget: $("#city"),
        //     districtTarget: $("#district1"),
        //     provinceValue: 25,
        //     cityValue: 321,
        //     districtValue: 3342
        // });



        var appId = "";
        var estateId = "0";
        var unitId = "0";
        var houseId = "0";
        var buildId = "0";
        basicIndexCommon = new Object();

        /**
         * 判断字符串以及null等
         */
        basicIndexCommon.isNotBlank = function (item) {
            if (item) {
                return true;
            }
            return false;
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
                    if (this.isNotBlank(item.id)){
                        buildId = item.id ;
                    }
                }
            }
        };
        basicIndexCommon.getBuildId = function () {
            return buildId;
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
            frm: "basicBuildFrm",
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
            province: 25,
            city: 321,
            district: undefined,
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
            navButtonBuild.tempSaveData();
        });
    };
    //建造商选择
    basicIndexCommon.builderSelect = function (this_) {
        assessBuilder.select(function (row) {
            $(this_).parent().prev().val(row.name);
            $(this_).parent().prev().prev().val(row.id);
            navButtonBuild.tempSaveData();
        });
    };
    //板块选择
    basicIndexCommon.blockSelect = function (this_) {
        assessBlock.select({
            province: 25,
            city: 321,
            district: undefined,
            success: function (row) {
                $(this_).parent().prev().val(row.name);
                $(this_).parent().prev().prev().val(row.id);
            }
        })
    };

    basicIndexCommon.estateLoadList = function () {
        try {
            estateNetwork.prototype.loadDataDicList();
            estateParking.prototype.loadDataDicList();
            estateSupplyWater.prototype.loadDataDicList();
            estateSupplyPower.prototype.loadDataDicList();
            estateSupplyHeating.prototype.loadDataDicList();
            estateSupplyGas.prototype.loadDataDicList();
            matchingEducation.prototype.loadDataDicList();
            matchingEnvironment.prototype.loadDataDicList();
            matchingFinance.prototype.loadDataDicList();
            matchingRecreation.prototype.loadDataDicList();
            matchingRestaurant.prototype.loadDataDicList();
            matchingMarket.prototype.loadDataDicList();
            matchingMaterial.prototype.loadDataDicList();
            matchingMedical.prototype.loadDataDicList();
            matchingTransit.prototype.loadDataDicList();
            matchingTrafficHub.prototype.loadDataDicList();
            matchingMetro.prototype.loadDataDicList();
            matchingMainRoad.prototype.loadDataDicList();
            matchingMainConversion.prototype.loadDataDicList();
        } catch (e) {
            console.log(e);
            console.log("子项函数不存在请检查");
        }
    };

    basicIndexCommon.estateShow = function () {
        $('#caseTab a:first').tab('show');
        $("#profile-tab1").attr("data-toggle", "tab");
    };

    basicIndexCommon.estateInit = function (item) {
        this.select2Assignment(this.config.basicEstate.frm, item.supplyHeating, "supplyHeating");
        this.select2Assignment(this.config.basicEstate.frm, item.supplyPower, "supplyPower");
        this.select2Assignment(this.config.basicEstate.frm, item.supplyWater, "supplyWater");
        this.select2Assignment(this.config.basicEstate.frm, item.supplyGas, "supplyGas");
        basicIndexCommon.estateSupply("supplyHeating",item.supplyHeating);
        $.each(basicIndexCommon.config.basicEstate.files, function (i, n) {
            basicIndexCommon.uploadFile(n, AssessDBKey.BasicEstate, basicIndexCommon.isNotBlank(item.id) ? item.id : 0);
        });
        console.log(item);
        try {
            AssessCommon.initAreaInfo({
                provinceTarget: $("#province"),
                cityTarget: $("#city"),
                districtTarget: $("#district1"),
                provinceValue:25,
                cityValue:321,
                districtValue:item.district
            });
        } catch (e) {
        }
    };

    basicIndexCommon.estateSupply = function (name,item) {
        var retHtml = '<option value="" selected>-请选择-</option>';
        if (item == 'true'){
            retHtml += "<option value='true' selected>"+"有"+"</option>" ;
        }else {
            retHtml += "<option value='true'>"+"有"+"</option>" ;
        }
        if (item == 'false'){
            retHtml += "<option value='false' selected>"+"无1"+"</option>" ;
        }else {
            retHtml += "<option value='false'>"+"无"+"</option>" ;
        }
        $("#"+basicIndexCommon.config.basicEstate.frm).find("select[name='"+name+"']").empty().html(retHtml);
    };

    basicIndexCommon.estateLandStateInit = function (item) {
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estate_total_land_use, item.landUseType, function (html, data) {
            $("#" + basicIndexCommon.config.basicEstate.frmLandState).find('select.landUseType').empty().html(html);
            basicIndexCommon.select2Assignment(basicIndexCommon.config.basicEstate.frmLandState, item.landUseType, "landUseType");
        }, true);
        if (this.isNotBlank(item.landUseType)) {
            AssessCommon.loadDataDicByPid(item.landUseType, item.landUseCategory, function (html, data) {
                $("#" + basicIndexCommon.config.basicEstate.frmLandState).find('select.landUseCategory').empty().html(html);
                basicIndexCommon.select2Assignment(basicIndexCommon.config.basicEstate.frmLandState, item.landUseCategory, "landUseCategory");
            });
        }
        $("#" + this.config.basicEstate.frmLandState).find("select.landUseType").change(function () {
            var id = $("#" + basicIndexCommon.config.basicEstate.frmLandState).find("select.landUseType").val();
            AssessCommon.loadDataDicByPid(id, null, function (html, data) {
                $("#" + basicIndexCommon.config.basicEstate.frmLandState).find('select.landUseCategory').empty().html(html);
                basicIndexCommon.select2Assignment(basicIndexCommon.config.basicEstate.frmLandState, item.landUseCategory, "landUseCategory");
            });
        });
    };

    basicIndexCommon.buildingInit = function (item) {
        AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_category, item.buildingCategory, function (html, data) {
            $("#" + basicIndexCommon.config.basicBuilding.frm).find('select.buildingCategory').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_structure, item.buildingStructure, function (html, data) {
            $("#" + basicIndexCommon.config.basicBuilding.frm).find('select.buildingStructure').empty().html(html).trigger('change');
            $("#" + basicIndexCommon.config.basicBuilding.frm).find('select.buildingStructure').val(item.buildingStructure).trigger("change");
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_type, item.propertyType, function (html, data) {
            $("#" + basicIndexCommon.config.basicBuilding.frm).find('select.propertyType').empty().html(html).trigger('change');
        });
        $("#" + this.config.basicBuilding.frm).find("select.buildingStructure").change(function () {
            /**
             * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
             **/
            var id = $("#" + basicIndexCommon.config.basicBuilding.frm).find("select.buildingStructure").val();
            if (basicIndexCommon.isNotBlank(id)) {
                AssessCommon.loadDataDicByPid(id, item.buildingStructureLower, function (html, data) {
                    $("#" + basicIndexCommon.config.basicBuilding.frm).find("select.buildingStructureLower").empty().html(html);
                });
            }
        });
        AssessCommon.loadDataDicByPid(item.buildingStructure, item.buildingStructureLower, function (html, data) {
            $("#" + basicIndexCommon.config.basicBuilding.frm).find("select.buildingStructureLower").empty().html(html).trigger('change');
        });
        navButtonBuild.inputBlur();
        navButtonBuild.one($("#navButtonBuild button").eq(0)[0], 1);
    };

    basicIndexCommon.buildingShow = function () {
        $('#caseTab a').eq(1).tab('show');
        $("#profile-tab2").attr("data-toggle", "tab");
    };

    basicIndexCommon.buildMainWrite = function (item) {
        $("#identifier").val(item.identifier);
        $("#caseBuildingMainId").val(item.id);
    };

    basicIndexCommon.unitShow = function () {
        $("#profile-tab3").attr("data-toggle", "tab");
        $('#caseTab a').eq(2).tab('show');
    };

    basicIndexCommon.unitLoadList = function () {
        try {
            unitDecorate.prototype.loadDataDicList();
            unitHuxing.prototype.loadDataDicList();
            unitElevator.prototype.loadDataDicList();
        } catch (e) {
            console.log(e);
            console.log("子项函数不存在请检查");
        }
    };

    basicIndexCommon.houseShow = function () {
        $('#caseTab a').eq(3).tab('show');
        $("#profile-tab4").attr("data-toggle", "tab");
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
        houseModelFun.houseInit(itemA);
        houseModelFun.tradingInit(itemB);
    };


</script>