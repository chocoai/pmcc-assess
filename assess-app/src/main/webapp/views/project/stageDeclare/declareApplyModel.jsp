<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body>
<script type="text/javascript">
    var commonDeclareApplyModel = {};

    /**
     * 空字符串检测
     * @param item
     * @returns {boolean}
     */
    commonDeclareApplyModel.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    commonDeclareApplyModel.isNotBlankObject = function (obj) {
        for (var key in obj) {
            return true;
        }
        return false
    };

    commonDeclareApplyModel.config = {
        house: {
            id: "declareModelHouse",
            handleId: "declareModelHandleHouse",
            name: "房产证信息"
        },
        land: {
            id: "declareModelLand",
            id2: "declareModelLand2",
            handleId: "declareModelHandleLand",
            handleId2: "declareModelHandleLand2",
            name: "土地证信息"
        },
        realEstateCert: {
            id: "declareModelRealEstateCert",
            id2: "declareModelRealEstateCert2",
            handleId: "declareModelHandleRealEstateCert",
            handleId2: "declareModelHandleRealEstateCert2",
            name: "不动产证"
        },
        civilEngineering: {
            id: "declareModelCivilEngineeringCommon",
            handleId: "declareModelHandleCivilEngineeringCommon",
            name: "土建"
        },
        equipmentInstallation: {
            id: "declareModelequipmentInstallationCommon",
            handleId: "declareModelHandleEquipmentInstallationCommon",
            name: "设备安装"
        },
        buildingConstructionPermit: {
            id: "buildingConstructionPermitCommon",
            handleId: "declareModelHandleBuildingConstructionPermitCommon",
            name: "建筑工程施工许可证"
        },
        preSalePermit: {
            id: "preSalePermitCommon",
            handleId: "declareModelHandlePreSalePermitCommon",
            name: "商品房预售许可证"
        },
        landUsePermit: {
            id: "landUsePermitCommon",
            handleId: "declareModelHandleLandUsePermitCommon",
            name: "建设用地规划许可证"
        },
        buildingPermit: {
            id: "buildingPermitCommon",
            handleId: "declareModelHandleBuildingPermitCommon",
            name: "建设工程规划许可证"
        }
    };

    /**
     * @author:  zch
     * 描述:不动产
     **/
    commonDeclareApplyModel.realEstateCert = {
        getHtml: function () {
            return $("#" + commonDeclareApplyModel.config.realEstateCert.id).html();
        },
        getHtml2: function () {
            return $("#" + commonDeclareApplyModel.config.realEstateCert.id2).html();
        },
        //不动产权证号
        CertNameSplicing: function (that) {
            var text = "";
            text = $(that).val();
            if (commonDeclareApplyModel.isNotBlank(text)) {
                var engine = $(that).closest("#" + commonDeclareApplyModel.config.realEstateCert.handleId);
                if (engine.size() == 0) {
                    // engine = $(that).closest("#" + commonDeclareApplyModel.config.realEstateCert.handleId2);
                }
                var province = engine.find("select[name='province']").find("option:selected").text();
                var provinceShort = AssessCommon.provinceForShort(province);
                var location = engine.find("input[name='location']").val();
                var number = engine.find("input[name='number']").val();
                var id = engine.find("select[name='type']").val();
                var year = engine.find("input[name='year']").val();
                if (year) {
                    year = "(" + engine.find("input[name='year']").val() + ")";
                }
                if (!commonDeclareApplyModel.isNotBlank(location)) {
                    location = "";
                }
                if (!commonDeclareApplyModel.isNotBlank(number)) {
                    number = "";
                }
                if (commonDeclareApplyModel.isNotBlank(location) && commonDeclareApplyModel.isNotBlank(number)) {
                    text = provinceShort + year + location + "不动产权" + "第" + number + "号";
                    engine.find("input[name='certName']").val(text);
                }
            }
        },
        //座落
        beLocatedSplicing: function (that) {
            var text = "";
            text = $(that).val();
            if (commonDeclareApplyModel.isNotBlank(text)) {
                var engine = $(that).closest("#" + commonDeclareApplyModel.config.realEstateCert.handleId);
                if (engine.size() == 0) {
                    engine = $(that).closest("#" + commonDeclareApplyModel.config.realEstateCert.handleId2);
                }
                var district = engine.find("select[name='district'] option:selected").val();
                var city = engine.find("select[name='city'] option:selected").val();
                var unit = engine.find("input[name='unit']").val();
                var floor = engine.find("input[name='floor']").val();
                var roomNumber = engine.find("input[name='roomNumber']").val();
                var streetNumber = engine.find("input[name='streetNumber']").val();
                var attachedNumber = engine.find("input[name='attachedNumber']").val();
                var buildingNumber = engine.find("input[name='buildingNumber']").val();
                if (!commonDeclareApplyModel.isNotBlank(unit)) {
                    unit = "";
                } else {
                    unit = unit + "单元";
                }
                if (!commonDeclareApplyModel.isNotBlank(floor)) {
                    floor = "";
                } else {
                    floor = floor + "层";
                }
                if (!commonDeclareApplyModel.isNotBlank(roomNumber)) {
                    roomNumber = "";
                } else {
                    roomNumber = roomNumber + "号";
                }
                if (!commonDeclareApplyModel.isNotBlank(streetNumber)) {
                    streetNumber = "";
                } else {
                    var char = streetNumber.charAt(streetNumber.length - 1)
                    if (char != "号") {
                        streetNumber = streetNumber + "号";
                        engine.find("input[name='streetNumber']").val(streetNumber);
                    }
                }
                if (!commonDeclareApplyModel.isNotBlank(attachedNumber)) {
                    attachedNumber = "";
                } else {
                    attachedNumber = "附" + attachedNumber + "号";
                }
                if (!commonDeclareApplyModel.isNotBlank(buildingNumber)) {
                    buildingNumber = "";
                } else {
                    buildingNumber = buildingNumber + "栋";
                }
                var key = 1;
                if (commonDeclareApplyModel.isNotBlank(district)) {
                    key = 1;
                }
                if (commonDeclareApplyModel.isNotBlank(city)) {
                    key = 2;
                }
                //城市和县都不为null
                if (commonDeclareApplyModel.isNotBlank(city) && commonDeclareApplyModel.isNotBlank(district)) {
                    key = 3;
                }
                //城市和县都为null
                if (!commonDeclareApplyModel.isNotBlank(city) && !commonDeclareApplyModel.isNotBlank(district)) {
                    key = 4;
                }
                text = streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                switch (key) {
                    case 1:
                        AssessCommon.getAreaById(district, function (data) {
                            text = data.name + text;
                            engine.find("input[name='beLocated']").val(text);
                        });
                        break;
                    case 2:
                        AssessCommon.getAreaById(city, function (data) {
                            text = data.name + text;
                            engine.find("input[name='beLocated']").val(text);
                        });
                        break;
                    case 3:
                        AssessCommon.getAreaById(district, function (districtData) {
                            AssessCommon.getAreaById(city, function (cityData) {
                                text = cityData.name + districtData.name + text;
                                engine.find("input[name='beLocated']").val(text);
                            });
                        });
                        break;
                    case 4:
                        engine.find("input[name='beLocated']").val(text);
                        break;
                    default:
                        break;
                }
            }
        }
    };

    /**
     * 房产证
     */
    commonDeclareApplyModel.house = {
        getHtml: function () {
            return $("#" + commonDeclareApplyModel.config.house.id).html();
        },
        //房产权证号
        certNameSplicing: function (that) {
            var text = "";
            text = $(that).val();
            if (commonDeclareApplyModel.isNotBlank(text)) {
                var engine = $(that).closest("#" + commonDeclareApplyModel.config.house.handleId);
                var location = engine.find("input[name='location']").val();
                var number = engine.find("input[name='number']").val();
                var id = engine.find("select[name='type']").val();
                if (!commonDeclareApplyModel.isNotBlank(location)) {
                    location = "";
                }
                if (!commonDeclareApplyModel.isNotBlank(number)) {
                    number = "";
                }
                if (!commonDeclareApplyModel.isNotBlank(id)) {
                    id = "";
                }
                if (id == "0") {
                    text = location + "字第" + number + "号";
                    engine.find("input[name='certName']").val(text);
                } else if (commonDeclareApplyModel.isNotBlank(id)) {
                    AssessCommon.getDataDicInfo(id, function (data) {
                        if (commonDeclareApplyModel.isNotBlank(data)) {
                            text = location + data.name + "字第" + number + "号";
                            engine.find("input[name='certName']").val(text);
                        }
                    });
                }
            }
        },
        //坐落
        beLocatedSplicing: function (that) {
            var text = "";
            text = $(that).val();
            if (commonDeclareApplyModel.isNotBlank(text)) {
                var engine = $(that).closest("#" + commonDeclareApplyModel.config.house.handleId);
                var district = engine.find("select[name='district'] option:selected").val();
                var city = engine.find("select[name='city'] option:selected").val();
                var unit = engine.find("input[name='unit']").val();
                var floor = engine.find("input[name='floor']").val();
                var roomNumber = engine.find("input[name='roomNumber']").val();
                var streetNumber = engine.find("input[name='streetNumber']").val();
                var attachedNumber = engine.find("input[name='attachedNumber']").val();
                var buildingNumber = engine.find("input[name='buildingNumber']").val();
                if (!commonDeclareApplyModel.isNotBlank(unit)) {
                    unit = "";
                } else {
                    unit = unit + "单元";
                }
                if (!commonDeclareApplyModel.isNotBlank(floor)) {
                    floor = "";
                } else {
                    floor = floor + "层";
                }
                if (!commonDeclareApplyModel.isNotBlank(roomNumber)) {
                    roomNumber = "";
                } else {
                    roomNumber = roomNumber + "号";
                }
                if (!commonDeclareApplyModel.isNotBlank(streetNumber)) {
                    streetNumber = "";
                } else {
                    var char = streetNumber.charAt(streetNumber.length - 1)
                    if (char != "号") {
                        streetNumber = streetNumber + "号";
                        engine.find("input[name='streetNumber']").val(streetNumber);
                    }
                }
                if (!commonDeclareApplyModel.isNotBlank(attachedNumber)) {
                    attachedNumber = "";
                } else {
                    attachedNumber = "附" + attachedNumber + "号";
                }
                if (!commonDeclareApplyModel.isNotBlank(buildingNumber)) {
                    buildingNumber = "";
                } else {
                    buildingNumber = buildingNumber + "栋";
                }
                var key = 1;
                if (commonDeclareApplyModel.isNotBlank(district)) {
                    key = 1;
                }
                if (commonDeclareApplyModel.isNotBlank(city)) {
                    key = 2;
                }
                //城市和县都不为null
                if (commonDeclareApplyModel.isNotBlank(city) && commonDeclareApplyModel.isNotBlank(district)) {
                    key = 3;
                }
                //城市和县都为null
                if (!commonDeclareApplyModel.isNotBlank(city) && !commonDeclareApplyModel.isNotBlank(district)) {
                    key = 4;
                }
                text = streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                switch (key) {
                    case 1:
                        AssessCommon.getAreaById(district, function (data) {
                            text = data.name + text;
                            engine.find("input[name='beLocated']").val(text);
                        });
                        break;
                    case 2:
                        AssessCommon.getAreaById(city, function (data) {
                            text = data.name + text;
                            engine.find("input[name='beLocated']").val(text);
                        });
                        break;
                    case 3:
                        AssessCommon.getAreaById(district, function (districtData) {
                            AssessCommon.getAreaById(city, function (cityData) {
                                text = cityData.name + districtData.name + text;
                                engine.find("input[name='beLocated']").val(text);
                            });
                        });
                        break;
                    case 4:
                        engine.find("input[name='beLocated']").val(text);
                        break;
                    default:
                        break;
                }
            }
        }
    };

    /**
     * 土地证
     */
    commonDeclareApplyModel.land = {
        //土地权证号 拼接
        landCertNameSplicing: function (that) {
            var text = "";
            text = $(that).val();
            if (commonDeclareApplyModel.isNotBlank(text)) {
                var engine = $(that).closest("#" + commonDeclareApplyModel.config.land.handleId);
                if (engine.size() == 0) {
                    // engine = $(that).closest("#" + commonDeclareApplyModel.config.land.handleId2);
                }
                var id = engine.find("select.landRightType").val();
                var location = engine.find("input[name='location']").val();
                var year = engine.find("input[name='year']").val();
                if (year) {
                    year = "(" + engine.find("input[name='year']").val() + ")";
                }
                var number = engine.find("input[name='number']").val();
                if (!commonDeclareApplyModel.isNotBlank(number)) {
                    number = "";
                } else {
                    number = "第" + number + "号";
                }
                if (commonDeclareApplyModel.isNotBlank(id)) {
                    AssessCommon.getDataDicInfo(id, function (data) {
                        if (commonDeclareApplyModel.isNotBlank(data)) {
                            text = location + data.name + year + number;
                            engine.find("input[name='landCertName']").val(text);
                        }
                    });
                }


            }
        },
        //坐落 拼接
        landBeLocatedSplicing: function (that) {
            var text = "";
            text = $(that).val();
            if (commonDeclareApplyModel.isNotBlank(text)) {
                var engine = $(that).closest("#" + commonDeclareApplyModel.config.land.handleId);
                if (engine.size() == 0) {
                    engine = $(that).closest("#" + commonDeclareApplyModel.config.land.handleId2);
                }
                var district = engine.find("select[name='district'] option:selected").val();
                var city = engine.find("select[name='city'] option:selected").val();
                var unit = engine.find("input[name='unit']").val();
                var floor = engine.find("input[name='floor']").val();
                var roomNumber = engine.find("input[name='roomNumber']").val();
                var streetNumber = engine.find("input[name='streetNumber']").val();
                var attachedNumber = engine.find("input[name='attachedNumber']").val();
                var buildingNumber = engine.find("input[name='buildingNumber']").val();
                if (!commonDeclareApplyModel.isNotBlank(unit)) {
                    unit = "";
                } else {
                    unit = unit + "单元";
                }
                if (!commonDeclareApplyModel.isNotBlank(floor)) {
                    floor = "";
                } else {
                    floor = floor + "层";
                }
                if (!commonDeclareApplyModel.isNotBlank(roomNumber)) {
                    roomNumber = "";
                } else {
                    roomNumber = roomNumber + "号";
                }
                if (!commonDeclareApplyModel.isNotBlank(streetNumber)) {
                    streetNumber = "";
                } else {
                    var char = streetNumber.charAt(streetNumber.length - 1)
                    if (char != "号") {
                        streetNumber = streetNumber + "号";
                        engine.find("input[name='streetNumber']").val(streetNumber);
                    }
                }
                if (!commonDeclareApplyModel.isNotBlank(attachedNumber)) {
                    attachedNumber = "";
                } else {
                    attachedNumber = "附" + attachedNumber + "号";

                }
                if (!commonDeclareApplyModel.isNotBlank(buildingNumber)) {
                    buildingNumber = "";
                } else {
                    buildingNumber = buildingNumber + "栋";
                }
                var key = 1;
                if (commonDeclareApplyModel.isNotBlank(district)) {
                    key = 1;
                }
                if (commonDeclareApplyModel.isNotBlank(city)) {
                    key = 2;
                }
                //城市和县都不为null
                if (commonDeclareApplyModel.isNotBlank(city) && commonDeclareApplyModel.isNotBlank(district)) {
                    key = 3;
                }
                //城市和县都为null
                if (!commonDeclareApplyModel.isNotBlank(city) && !commonDeclareApplyModel.isNotBlank(district)) {
                    key = 4;
                }
                text = streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                switch (key) {
                    case 1:
                        AssessCommon.getAreaById(district, function (data) {
                            text = data.name + text;
                            engine.find("input[name='beLocated']").val(text);
                        });
                        break;
                    case 2:
                        AssessCommon.getAreaById(city, function (data) {
                            text = data.name + text;
                            engine.find("input[name='beLocated']").val(text);
                        });
                        break;
                    case 3:
                        AssessCommon.getAreaById(district, function (districtData) {
                            AssessCommon.getAreaById(city, function (cityData) {
                                text = cityData.name + districtData.name + text;
                                engine.find("input[name='beLocated']").val(text);
                            });
                        });
                        break;
                    case 4:
                        engine.find("input[name='beLocated']").val(text);
                        break;
                    default:
                        break;
                }
            }
        },
        getHtml: function () {
            return $("#" + commonDeclareApplyModel.config.land.id).html();
        },
        getHtml2: function () {
            return $("#" + commonDeclareApplyModel.config.land.id2).html();
        }
    };

    /**
     * 土建
     * @type {{getHtml: commonDeclareApplyModel.civilEngineering.getHtml}}
     */
    commonDeclareApplyModel.civilEngineering = {
        getHtml: function () {
            return $("#" + commonDeclareApplyModel.config.civilEngineering.id).html();
        }
    };

    /**
     * 设备安装
     * @type {{getHtml: commonDeclareApplyModel.equipmentInstallation.getHtml}}
     */
    commonDeclareApplyModel.equipmentInstallation = {
        getHtml: function () {
            return $("#" + commonDeclareApplyModel.config.equipmentInstallation.id).html();
        }
    };

    /**
     * 建筑工程施工许可证
     * @type {{getHtml: commonDeclareApplyModel.buildingConstructionPermit.getHtml}}
     */
    commonDeclareApplyModel.buildingConstructionPermit = {
        getHtml: function () {
            return $("#" + commonDeclareApplyModel.config.buildingConstructionPermit.id).html();
        }
    };

    /**
     * 商品房预售许可证
     * @type {{getHtml: commonDeclareApplyModel.preSalePermit.getHtml}}
     */
    commonDeclareApplyModel.preSalePermit = {
        getHtml: function () {
            return $("#" + commonDeclareApplyModel.config.preSalePermit.id).html();
        }
    };

    /**
     * 建设用地规划许可证
     * @type {{getHtml: commonDeclareApplyModel.landUsePermit.getHtml}}
     */
    commonDeclareApplyModel.landUsePermit = {
        getHtml: function () {
            return $("#" + commonDeclareApplyModel.config.landUsePermit.id).html();
        }
    };

    /**
     * 建设工程规划许可证
     * @type {{getHtml: commonDeclareApplyModel.buildingPermit.getHtml}}
     */
    commonDeclareApplyModel.buildingPermit = {
        getHtml: function () {
            return $("#" + commonDeclareApplyModel.config.buildingPermit.id).html();
        }
    };


</script>
<!-- 由于以后申报信息可能经常改变因此对房产证，不动产证,土地证,建设工程规划许可证,建设用地规划许可证,建设工程施工许可证,商品房预售许可证,经济规划指标 -->

<script type="text/html" id="declareModelCivilEngineeringCommon" data-title="土建">
    <div id="declareModelHandleCivilEngineeringCommon">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">省
                    <span class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="province"
                            class="form-control search-select select2 province"
                            required="required">
                        <option value="" name="province">-请选择-</option>
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">市<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="city"
                            class="form-control search-select select2 city"
                            required="required">
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">县<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="district"
                            class="form-control search-select select2 district">
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">占有单位<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="占有单位" name="occupancyUnit" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">项目名称<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="项目名称" name="name" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">坐落<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="坐落" name="beLocated" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">结构<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="结构" name="structure" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label" title="评估面积">建筑面积<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="评估面积(数字)" name="buildArea" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">开工日期<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="开工日期"
                           name="startDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">预期完成日期<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="预期完成日期"
                           name="expectedCompletionDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">形象进度<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="形象进度" name="speedProgress" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">付款比例<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="付款比例" name="paymentRatio" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">账面价值<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="账面价值" name="bookValue" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">帐面净值<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="帐面净值" name="bookNetValue" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">申报人<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="申报人" name="declarer" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">申报日期<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="申报日期"
                           name="declarationDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">备注<span
                        class="symbol required"></span></label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <textarea class="form-control" name="remark" required="required"></textarea>
                </div>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="declareModelequipmentInstallationCommon" data-title="设备安装">
    <div id="declareModelHandleEquipmentInstallationCommon">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">省
                    <span class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="province"
                            class="form-control search-select select2"
                            required="required">
                        <option value="" name="province">-请选择-</option>
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">市<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="city"
                            class="form-control search-select select2"
                            required="required">
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">县<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="district"
                            class="form-control search-select select2 district">
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">占有单位<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="占有单位" name="occupancyUnit" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">项目名称<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="项目名称" name="name" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">坐落<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="坐落" name="beLocated" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">规格型号<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="规格型号" name="specificationModel" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">生产厂家<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="生产厂家" name="manufacturer" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">计量单位<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="计量单位" name="measurementUnit" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">数量<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="数量(数字)" name="number" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">开工日期<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="开工日期"
                           name="startDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">预期完成日期<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="预期完成日期"
                           name="expectedCompletionDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">账面设备费<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="账面设备费" name="bookEquipmentFee" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">账面资金成本<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="账面资金成本" name="bookCapitalCost" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">账面安装费<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="账面安装费" name="bookInstallationFee" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">其它<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="其它" name="other" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">申报人<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="申报人" name="declarer" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">申报日期<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="申报日期"
                           name="declarationDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">备注<span
                        class="symbol required"></span></label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <textarea class="form-control" name="remark" required="required"></textarea>
                </div>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="buildingConstructionPermitCommon" data-title="建筑工程施工许可证">
    <div id="declareModelHandleBuildingConstructionPermitCommon">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">证书编号<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="证书编号" name="certificateNumber" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">发证机关<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="发证机关" name="issuingOrgan" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">日期<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="日期"
                           name="date" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建设单位（个人）<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="建设单位（个人）" name="buildUnit" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建设项目名称<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="建设项目名称" name="name" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建设地址<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="建设地址" name="buildAddress" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建设规模<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="建设规模" name="scaleConstruction" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">勘察单位<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="勘察单位" name="reconnaissanceUnit" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">设计单位<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="设计单位" name="designUnit" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">施工单位<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="施工单位" name="constructionUnit" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">监理单位<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="监理单位" name="constructionControlUnit"
                           class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">勘察单位项目负责人<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="勘察单位项目负责人" name="reconnaissanceUnitPerson"
                           class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">设计单位项目负责人<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="设计单位项目负责人" name="designUnitPerson" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">施工单位项目负责人<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="施工单位项目负责人" name="constructionUnitPerson"
                           class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">总监理工程师<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="总监理工程师" name="chiefEngineerConstructionInspection"
                           class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">合同工期<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="合同工期"
                           name="contractPeriod" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">备注<span
                        class="symbol required"></span></label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <textarea name="remark" placeholder="备注" class="form-control"></textarea>
                </div>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="preSalePermitCommon" data-title="商品房预售许可证">
    <div id="declareModelHandlePreSalePermitCommon">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">证书编号<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="证书编号" name="certificateNumber" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">售房单位<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="售房单位" name="salesUnit" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">法定代表人<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="法定代表人" name="legalRepresentative" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">项目坐落<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="项目坐落" name="beLocated" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">项目名称<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="项目名称" name="name" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">预售范围<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="预售范围" name="preSaleScope" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">预售面积<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="预售面积(数字)" data-rule-maxlength="100"
                           data-rule-number='true' name="preSaleArea" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋用途<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="房屋用途" name="housingUse" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑结构<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="建筑结构" name="buildingStructure" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">预售款监管信息<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="预售款监管信息" name="preSaleSupervisionInformation"
                           class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">发证机关<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="发证机关" name="issuingOrgan"
                           class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">日期<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="日期"
                           name="date" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">在建工程抵押情况<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="在建工程抵押情况" name="mortgageSituation" class="form-control"
                           required="required">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">备注<span
                        class="symbol required"></span></label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <textarea name="remark" placeholder="备注" class="form-control"></textarea>
                </div>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="landUsePermitCommon" data-title="建设用地规划许可证">
    <div id="declareModelHandleLandUsePermitCommon">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">证书编号<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="证书编号" name="certificateNumber" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">发证机关<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="发证机关" name="issuingOrgan" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">日期<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="日期"
                           name="date" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">用地单位<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="用地单位" name="unit" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">用地项目名称<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="用地项目名称" name="name" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">用地位置<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="用地位置" name="location" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">用地性质<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="用地性质" name="nature" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">用地面积<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="用地面积" data-rule-maxlength="100" data-rule-number='true'
                           name="area" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建设规模<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="建设规模" name="scaleConstruction" class="form-control"
                           required="required">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">备注<span
                        class="symbol required"></span></label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <textarea name="remark" placeholder="备注" class="form-control"></textarea>
                </div>
            </div>
        </div>

    </div>
</script>

<script type="text/html" id="buildingPermitCommon" data-title="建设工程规划许可证">
    <div id="declareModelHandleBuildingPermitCommon">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">证书编号<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="证书编号" name="certificateNumber" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">发证机关<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="发证机关" name="issuingOrgan" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">日期<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="日期"
                           name="date" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建设单位（个人）<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="建设单位（个人）" name="unit" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建设项目名称<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="建设项目名称" name="name" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建设位置</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="建设位置" name="location" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建设规模</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="建设规模" name="scaleConstruction" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">备注<span
                        class="symbol required"></span></label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <textarea name="remark" placeholder="备注" class="form-control"></textarea>
                </div>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="declareModelRealEstateCert" data-title="不动产证">
    <div id="declareModelHandleRealEstateCert">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    省<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="province" onchange="commonDeclareApplyModel.realEstateCert.CertNameSplicing(this)"
                            class="form-control search-select select2 province"
                            required="required">
                        <option value="" name="province">-请选择-</option>
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    市<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="city"
                            class="form-control search-select select2"
                            required="required"
                            onchange="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this);">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    县(区)
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="district"
                            class="form-control search-select select2 district"
                            onchange="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this);commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this);">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">不动产权证号<span
                        class="symbol required"></span></label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <input type="text" placeholder="不动产权证号" name="certName" class="form-control" required>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">所在地<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="所在地" name="location" class="form-control"
                           required="required" onblur="commonDeclareApplyModel.realEstateCert.CertNameSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">编号<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="编号(数字)" name="number" class="form-control"
                           required="required" onblur="commonDeclareApplyModel.realEstateCert.CertNameSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    年份<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" required="required" data-rule-maxlength="100"
                           onblur="commonDeclareApplyModel.realEstateCert.CertNameSplicing(this)"
                           data-rule-number='true' name="year" class="form-control"
                           placeholder="年份(数字如:2018)">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">不动产单元号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="不动产单元号" name="realEstateUnitNumber" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋所有权人<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="房屋所有权人" name="ownership" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">共有情况<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select required="required" name="publicSituation"
                            class="form-control search-select select2 publicSituation">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">共有情况说明</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="共有情况说明" name="publicSituationRemark" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    登记日期
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="登记日期"
                           name="registrationTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime">
                </div>
            </div>
        </div>
        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道号<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" required
                           placeholder="街道号" name="streetNumber" class="form-control"
                           onblur="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="附号(数字)" name="attachedNumber" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           onblur="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">栋号<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" required
                           placeholder="栋号" name="buildingNumber" class="form-control"
                           onblur="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this)">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">单元</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="单元(数字)" name="unit" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           onblur="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼层</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="楼层(数字)" name="floor" class="form-control"
                           data-rule-maxlength="100"
                           onblur="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房号<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" required
                           name="roomNumber" class="form-control" placeholder="房号"
                           onblur="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this)">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">坐落<span
                        class="symbol required"></span></label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <input type="text" required
                           placeholder="坐落" name="beLocated" class="form-control">
                </div>
            </div>
        </div>
        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    房屋用途类型<span class="symbol required"></span>
                </label>
                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                    <div class="input-group">
                        <input type="text" name="houseCertUse" class="form-control" list="realHouseUseList" required>
                        <datalist id="realHouseUseList">

                        </datalist>
                        <span class="input-group-btn">
                                                <button type="button" class="btn btn-default docs-tooltip"
                                                        onclick="$(this).closest('.input-group').find('input').val('');"
                                                        data-toggle="tooltip" data-original-title="清除">
                                                <i class="fa fa-trash-o"></i>
                                                </button>
                                            </span>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    房屋用途类别
                </label>
                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                    <div class="input-group">
                        <input type="text" name="houseCertUseCategory" class="form-control"
                               list="houseCertUseCategoryList1">
                        <datalist id="houseCertUseCategoryList1">

                        </datalist>
                        <span class="input-group-btn">
                                                <button type="button" class="btn btn-default docs-tooltip"
                                                        onclick="$(this).closest('.input-group').find('input').val('');"
                                                        data-toggle="tooltip" data-original-title="清除">
                                                <i class="fa fa-trash-o"></i>
                                                </button>
                                            </span>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋结构</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="房屋结构" name="housingStructure" class="form-control">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋性质
                    <span class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="nature" class="form-control search-select select2 nature" required>
                    </select>
                </div>
            </div>
            <%--<div class="x-valid">--%>
            <%--<label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑面积</label>--%>
            <%--<div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">--%>
            <%--<input type="text"--%>
            <%--placeholder="建筑面积(数字)" name="floorArea" class="form-control"--%>
            <%--data-rule-maxlength="100" data-rule-number='true'>--%>
            <%--</div>--%>
            <%--</div>--%>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">证载面积
                    <span class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" required
                           placeholder="证载面积(数字)" name="evidenceArea" class="form-control"
                           data-rule-maxlength="100"
                           data-rule-number='true'>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">套内面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="套内面积(数字)" name="innerArea" class="form-control" data-rule-maxlength="100"
                           data-rule-number='true'>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">分摊面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="分摊面积(数字)" name="apportionmentArea" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">其它(房屋)</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="其它" name="other" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附记(其他)</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="附记其它" name="otherNote" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">总层数</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="总层数" name="floorCount" class="form-control">
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地取得方式</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="土地取得方式" name="acquisitionType" class="form-control"
                           data-rule-maxlength="100">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地取得价格</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="土地取得价格" name="acquisitionPrice" class="form-control"
                           data-rule-maxlength="100"
                           data-rule-number='true'>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地用途类型<span class="symbol required"></span>
                </label>
                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                    <div class="input-group">
                        <input type="text" name="landCertUse" class="form-control" list="landCertUseList" required>
                        <datalist id="landCertUseList">

                        </datalist>
                        <span class="input-group-btn">
                                                <button type="button" class="btn btn-default docs-tooltip"
                                                        onclick="$(this).closest('.input-group').find('input').val('');"
                                                        data-toggle="tooltip" data-original-title="清除">
                                                <i class="fa fa-trash-o"></i>
                                                </button>
                                            </span>
                    </div>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地用途类别<span class="symbol required"></span>
                </label>
                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                    <div class="input-group">
                        <input type="text" name="landCertUseCategory" list="landCertUseCategoryList" required
                               class="form-control">
                        <datalist id="landCertUseCategoryList">

                        </datalist>
                        <span class="input-group-btn">
                                                <button type="button" class="btn btn-default docs-tooltip"
                                                        onclick="$(this).closest('.input-group').find('input').val('');"
                                                        data-toggle="tooltip" data-original-title="清除">
                                                <i class="fa fa-trash-o"></i>
                                                </button>
                                            </span>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地权利性质<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select required="required" name="landRightNature"
                            class="form-control search-select select2 landRightNature">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">共用宗地面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="共用宗地面积(数字)" name="useRightArea" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">权利类型<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="landRightType" class="form-control search-select select2 landRightType"
                            required="required">
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地分摊面积
                    <span class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" required
                           placeholder="土地分摊面积(数字)" name="landApportionArea" class="form-control"
                           data-rule-maxlength="100"
                           data-rule-number='true'>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地使用年限起
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="土地使用年限起"
                           name="useStartDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地使用年限止<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="土地使用年限止" required
                           name="useEndDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime">
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">登记机关
                    <span class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" required
                           placeholder="登记机关" name="registrationAuthority" class="form-control">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">记事(土地)</label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                                            <textarea class="form-control" name="memo">
                                            </textarea>
                </div>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="declareModelRealEstateCert2" data-title="不动产证(样式和上面的不动产有所差异)">
    <div id="declareModelHandleRealEstateCert2">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    省<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="province"
                            class="form-control search-select select2 province"
                            required="required">
                        <option value="" name="province">-请选择-</option>
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    市<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="city"
                            class="form-control search-select select2"
                            required="required">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    县(区)
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="district"
                            class="form-control search-select select2 district"
                            onchange="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this)">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">批文文号<span
                        class="symbol required"></span></label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <input type="text"
                           placeholder="批文文号" required name="certName" class="form-control">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">所在地</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="所在地" name="location" class="form-control"
                           onblur="commonDeclareApplyModel.realEstateCert.CertNameSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">编号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="编号(数字)" name="number" class="form-control"
                           onblur="commonDeclareApplyModel.realEstateCert.CertNameSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">不动产单元号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="不动产单元号" name="realEstateUnitNumber" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">权利人</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="权利人" name="ownership" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">共有情况<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select required="required" name="publicSituation"
                            class="form-control search-select select2 publicSituation">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    登记日期
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="登记日期"
                           name="registrationTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime">
                </div>
            </div>
        </div>
        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">坐落</label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <input type="text" readonly="readonly"
                           placeholder="坐落" name="beLocated" class="form-control">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="街道号" name="streetNumber" class="form-control"
                           onblur="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="附号(数字)" name="attachedNumber" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           onblur="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">栋号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="栋号(数字)" name="buildingNumber" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           onblur="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this)">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">单元</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="单元(数字)" name="unit" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           onblur="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼层</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="楼层(数字)" name="floor" class="form-control"
                           data-rule-maxlength="100"
                           onblur="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="房号" name="roomNumber" class="form-control"
                           onblur="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this)">
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地用途类型<span class="symbol required"></span>
                </label>
                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                    <div class="input-group">
                        <input type="text" name="landCertUse" class="form-control" list="landCertUseList2">
                        <datalist id="landCertUseList2">

                        </datalist>
                        <span class="input-group-btn">
                                                <button type="button" class="btn btn-default docs-tooltip"
                                                        onclick="$(this).closest('.input-group').find('input').val('');"
                                                        data-toggle="tooltip" data-original-title="清除">
                                                <i class="fa fa-trash-o"></i>
                                                </button>
                                            </span>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地用途类别<span class="symbol required"></span>
                </label>
                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                    <div class="input-group">
                        <input type="text" name="landCertUseCategory" list="landCertUseCategoryList2" required
                               class="form-control">
                        <datalist id="landCertUseCategoryList2">

                        </datalist>
                        <span class="input-group-btn">
                                                <button type="button" class="btn btn-default docs-tooltip"
                                                        onclick="$(this).closest('.input-group').find('input').val('');"
                                                        data-toggle="tooltip" data-original-title="清除">
                                                <i class="fa fa-trash-o"></i>
                                                </button>
                                            </span>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    权利性质<span
                        class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select required="required" name="landRightNature"
                            class="form-control search-select select2 landRightNature">
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">权利类型<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="landRightType" class="form-control search-select select2 landRightType"
                            required="required">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地使用年限起<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="土地使用年限起"
                           name="useStartDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地使用年限止<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="土地使用年限止" required
                           name="useEndDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">共用宗地面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="共用宗地面积(数字)" name="useRightArea" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">取得价格</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="取得价格" name="acquisitionPrice" class="form-control"
                           data-rule-maxlength="100"
                           data-rule-number='true'>
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>
        <div class="form-group">

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">批文名称 </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="批文名称" name="approvalName" class="form-control">
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">批文机关 </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="批文机关" name="approvalMechanism" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">批文时间 </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="批文时间"
                           name="approvalTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">记事</label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                                            <textarea class="form-control" name="memo">
                                            </textarea>
                </div>
            </div>
        </div>
        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    房屋用途类型<span class="symbol required"></span>
                </label>
                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                    <div class="input-group">
                        <input type="text" name="houseCertUse" class="form-control" list="realHouseUseList2">
                        <datalist id="realHouseUseList2">

                        </datalist>
                        <span class="input-group-btn">
                                                <button type="button" class="btn btn-default docs-tooltip"
                                                        onclick="$(this).closest('.input-group').find('input').val('');"
                                                        data-toggle="tooltip" data-original-title="清除">
                                                <i class="fa fa-trash-o"></i>
                                                </button>
                                            </span>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    房屋用途类别
                </label>
                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                    <div class="input-group">
                        <input type="text" name="houseCertUseCategory" class="form-control"
                               list="houseCertUseCategoryList2">
                        <datalist id="houseCertUseCategoryList2">

                        </datalist>
                        <span class="input-group-btn">
                                                <button type="button" class="btn btn-default docs-tooltip"
                                                        onclick="$(this).closest('.input-group').find('input').val('');"
                                                        data-toggle="tooltip" data-original-title="清除">
                                                <i class="fa fa-trash-o"></i>
                                                </button>
                                            </span>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋结构</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="房屋结构" name="housingStructure" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋性质</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="nature"
                            class="form-control search-select select2 nature">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">证载面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="证载面积(数字)" name="evidenceArea" class="form-control"
                           data-rule-maxlength="100"
                           data-rule-number='true'>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">套内面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="套内面积(数字)" name="innerArea" class="form-control" data-rule-maxlength="100"
                           data-rule-number='true'>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">分摊面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="分摊面积(数字)" name="apportionmentArea" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">登记机关</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="登记机关" name="registrationAuthority" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">其它</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="其它" name="other" class="form-control">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附记其它</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="附记其它" name="otherNote" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">总层数</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="总层数(数字)" name="floorCount" class="form-control"
                           data-rule-maxlength="100"
                           data-rule-number='true'>
                </div>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="declareModelHouse" data-title="房产证信息">
    <div id="declareModelHandleHouse">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">省
                    <span class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="province"
                            class="form-control search-select select2"
                            required="required">
                        <option value="" name="province">-请选择-</option>
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">市<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="city"
                            class="form-control search-select select2"
                            required="required" onchange="commonDeclareApplyModel.house.beLocatedSplicing(this)">
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">县</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="district"
                            class="form-control search-select select2 district"
                            onchange="commonDeclareApplyModel.house.beLocatedSplicing(this)">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房产权证号<span
                        class="symbol required"></span></label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <input type="text" placeholder="房产权证号" name="certName" class="form-control" required>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">所在地<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="所在地" name="location" class="form-control"
                           required="required" onblur="commonDeclareApplyModel.house.certNameSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">编号<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="编号(数字)" name="number" class="form-control"
                           required="required" onblur="commonDeclareApplyModel.house.certNameSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">类型<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select class="form-control" name="type" required="required"
                            onchange="commonDeclareApplyModel.house.certNameSplicing(this)">
                        <option value="0">--请选择--</option>
                        <c:if test="${not empty certificateTypes}">
                            <c:forEach items="${certificateTypes}" var="item">
                                <option value="${item.id}">${item.name}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋所有权人<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="房屋所有权人" name="ownership" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑面积<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="建筑面积(数字)" name="floorArea" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">共有情况<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select required="required" name="publicSituation"
                            class="form-control search-select select2 publicSituation">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">共有情况说明</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="共有情况说明" name="publicSituationRemark" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    登记日期
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="登记日期" name="registrationDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">丘地号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="丘地号" name="groundNum" class="form-control">
                </div>
            </div>
        </div>
        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道号<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" required
                           placeholder="街道号" name="streetNumber" class="form-control"
                           onblur="commonDeclareApplyModel.house.beLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="附号(数字)" name="attachedNumber" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           onblur="commonDeclareApplyModel.house.beLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">栋号<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" required="required"
                           placeholder="栋号" name="buildingNumber" class="form-control"
                           onblur="commonDeclareApplyModel.house.beLocatedSplicing(this)">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">单元</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="单元(数字)" name="unit" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           onblur="commonDeclareApplyModel.house.beLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼层</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="楼层" name="floor" class="form-control"
                           onblur="commonDeclareApplyModel.house.beLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房号<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="房号" name="roomNumber" class="form-control"
                           onblur="commonDeclareApplyModel.house.beLocatedSplicing(this)" required="required">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">坐落<span
                        class="symbol required"></span></label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <input type="text" placeholder="坐落" name="beLocated" class="form-control" required="required">
                </div>
            </div>
        </div>
        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋结构</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="房屋结构" name="housingStructure" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    房屋用途类型<span class="symbol required"></span>
                </label>
                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                    <div class="input-group">
                        <input type="text" name="certUse" class="form-control" list="houseUseList" required="required">
                        <datalist id="houseUseList">

                        </datalist>
                        <span class="input-group-btn">
                                                <button type="button" class="btn btn-default docs-tooltip"
                                                        onclick="$(this).closest('.input-group').find('input').val('');"
                                                        data-toggle="tooltip" data-original-title="清除">
                                                <i class="fa fa-trash-o"></i>
                                                </button>
                                            </span>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    房屋用途类别
                </label>
                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                    <div class="input-group">
                        <input type="text" name="certUseCategory" class="form-control" list="housecertUseCategoryList">
                        <datalist id="housecertUseCategoryList">

                        </datalist>
                        <span class="input-group-btn">
                                                <button type="button" class="btn btn-default docs-tooltip"
                                                        onclick="$(this).closest('.input-group').find('input').val('');"
                                                        data-toggle="tooltip" data-original-title="清除">
                                                <i class="fa fa-trash-o"></i>
                                                </button>
                                            </span>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋性质<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select required="required" name="nature"
                            class="form-control search-select select2 nature">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">证载面积<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" required
                           placeholder="证载面积(数字)" name="evidenceArea" class="form-control"
                           data-rule-maxlength="100"
                           data-rule-number='true'>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">套内面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="套内面积(数字)" name="innerArea" class="form-control"
                           data-rule-maxlength="100"
                           data-rule-number='true'>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">公摊面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="公摊面积(数字)" name="publicArea" class="form-control" data-rule-maxlength="100"
                           data-rule-number='true'>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">总层数</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="总层数" name="floorCount" class="form-control">
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地取得方式</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="landAcquisition"
                            class="form-control search-select select2 landAcquisition">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">登记机关<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" required="required"
                           placeholder="登记机关" name="registrationAuthority" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">分摊面积<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" required="required"
                           placeholder="分摊面积(数字)" name="apportionmentArea" class="form-control"
                           data-rule-maxlength="100"
                           data-rule-number='true'>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地使用年限起
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="土地使用年限起"
                           name="useStartDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地使用年限止<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="土地使用年限止" required
                           name="useEndDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">其它</label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <textarea placeholder="其它" name="other" class="form-control"></textarea>
                </div>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="declareModelLand" data-title="土地证信息">
    <div id="declareModelHandleLand">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    省<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="province"
                            class="form-control search-select select2 province"
                            required="required">
                        <option value="" name="province">-请选择-</option>
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    市<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="city"
                            class="form-control search-select select2 city"
                            required="required" onchange="commonDeclareApplyModel.land.landBeLocatedSplicing(this)">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    县(区)
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="district"
                            class="form-control search-select select2 district"
                            onchange="commonDeclareApplyModel.land.landBeLocatedSplicing(this)">
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    所在地<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" name="location" required="required" class="form-control"
                           onblur="commonDeclareApplyModel.land.landCertNameSplicing(this)"
                           placeholder="所在地">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    类型<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select required="required" name="landRightType"
                            onchange="commonDeclareApplyModel.land.landCertNameSplicing(this)"
                            class="form-control search-select select2 landRightType">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    年份<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" required="required" data-rule-maxlength="100"
                           onblur="commonDeclareApplyModel.land.landCertNameSplicing(this)"
                           data-rule-number='true' name="year" class="form-control"
                           placeholder="年份(数字如:2018)">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    编号
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" name="number" class="form-control"
                           onblur="commonDeclareApplyModel.land.landCertNameSplicing(this)"
                           placeholder="编号">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地使用权人<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" name="ownership" required="required" class="form-control"
                           placeholder="土地使用权人">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">共有情况<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select required="required" name="publicSituation"
                            class="form-control search-select select2 publicSituation">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地权证号<span class="symbol required"></span>
                </label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <input type="text" name="landCertName" class="form-control" placeholder="土地权证号" required="required">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">共有情况说明</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="共有情况说明" name="publicSituationRemark" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    登记日期
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="登记日期" name="registrationDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime">
                </div>
            </div>
        </div>
        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道号<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="街道号" name="streetNumber" class="form-control"
                           onblur="commonDeclareApplyModel.land.landBeLocatedSplicing(this)"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="附号(数字)" name="attachedNumber" class="form-control"
                           onblur="commonDeclareApplyModel.land.landBeLocatedSplicing(this)"
                           data-rule-maxlength="100" data-rule-number='true'>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label"><span
                        class="symbol required"></span>栋号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" required="required"
                           placeholder="栋号" name="buildingNumber" class="form-control"
                           onblur="commonDeclareApplyModel.land.landBeLocatedSplicing(this)">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">单元</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="单元(数字)" name="unit" class="form-control"
                           data-rule-maxlength="100"
                           onblur="commonDeclareApplyModel.land.landBeLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼层</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="楼层(数字)" name="floor" class="form-control"
                           data-rule-maxlength="100"
                           onblur="commonDeclareApplyModel.land.landBeLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="房号" name="roomNumber" class="form-control"
                           onblur="commonDeclareApplyModel.land.landBeLocatedSplicing(this)">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">坐落<span
                        class="symbol required"></span></label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <input type="text" placeholder="坐落" name="beLocated" class="form-control" required="required">
                </div>
            </div>
        </div>
        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">地号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="地号" name="landNumber" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">图号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="图号" name="graphNumber" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地用途类型<span class="symbol required"></span>
                </label>
                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                    <div class="input-group">
                        <input type="text" id="certUse" name="certUse" class="form-control" list="certUseList" required>
                        <datalist id="certUseList">

                        </datalist>
                        <span class="input-group-btn">
                                                <button type="button" class="btn btn-default docs-tooltip"
                                                        onclick="$(this).closest('.input-group').find('input').val('');"
                                                        data-toggle="tooltip" data-original-title="清除">
                                                <i class="fa fa-trash-o"></i>
                                                </button>
                                            </span>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地用途类别<span class="symbol required"></span>
                </label>
                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                    <div class="input-group">
                        <input type="text" id="certUseCategory" name="certUseCategory" list="certUseCategoryList"
                               class="form-control" required>
                        <datalist id="certUseCategoryList">

                        </datalist>
                        <span class="input-group-btn">
                                                <button type="button" class="btn btn-default docs-tooltip"
                                                        onclick="$(this).closest('.input-group').find('input').val('');"
                                                        data-toggle="tooltip" data-original-title="清除">
                                                <i class="fa fa-trash-o"></i>
                                                </button>
                                            </span>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">取得价格</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="取得价格" name="acquisitionPrice" class="form-control" data-rule-maxlength="100"
                           data-rule-number='true'>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    权利性质<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select required="required" name="landRightNature"
                            class="form-control search-select select2 landRightNature">
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">使用权面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="使用权面积(数字)" name="useRightArea" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">分摊面积<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" required="required"
                           placeholder="分摊面积(数字)" name="apportionmentArea" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">登记机关<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="登记机关" name="registrationAuthority" class="form-control"
                           required="required">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">记事</label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <textarea class="form-control" name="memo"></textarea>
                </div>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="declareModelLand2" data-title="土地证信息2">
    <div id="declareModelHandleLand2">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    省<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="province"
                            class="form-control search-select select2 province"
                            required="required">
                        <option value="" name="province">-请选择-</option>
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    市<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="city"
                            class="form-control search-select select2 city"
                            required="required">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    县(区)
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="district"
                            class="form-control search-select select2 district"
                            onchange="commonDeclareApplyModel.land.landBeLocatedSplicing(this)">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    批文文号
                </label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <input type="text" name="landCertName"
                           class="form-control" placeholder="批文文号">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    所在地
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" name="location" class="form-control"
                           onblur="commonDeclareApplyModel.land.landCertNameSplicing(this)"
                           placeholder="所在地">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    类型
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select name="landRightType"
                            onchange="commonDeclareApplyModel.land.landCertNameSplicing(this)"
                            class="form-control search-select select2 landRightType">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    年份
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" data-rule-maxlength="100"
                           onblur="commonDeclareApplyModel.land.landCertNameSplicing(this)"
                           data-rule-number='true' name="year" class="form-control"
                           placeholder="年份(数字如:2018)">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    编号
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" name="number" class="form-control"
                           onblur="commonDeclareApplyModel.land.landCertNameSplicing(this)"
                           placeholder="编号">
                </div>
            </div>
            <%--<div class="x-valid">--%>
            <%--<label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">--%>
            <%--土地使用权人<span class="symbol required"></span>--%>
            <%--</label>--%>
            <%--<div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">--%>
            <%--<input type="text" name="ownership" required="required" class="form-control"--%>
            <%--placeholder="土地使用权人">--%>
            <%--</div>--%>
            <%--</div>--%>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">共有情况</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select required="required" name="publicSituation"
                            class="form-control search-select select2 publicSituation">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    登记日期
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="登记日期" name="registrationDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime">
                </div>
            </div>
        </div>
        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">坐落<span
                        class="symbol required"></span></label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <input type="text"
                           placeholder="坐落" name="beLocated" class="form-control">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道号/村组<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="街道号/村组" name="streetNumber" class="form-control"
                           onblur="commonDeclareApplyModel.land.landBeLocatedSplicing(this)"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="附号(数字)" name="attachedNumber" class="form-control"
                           onblur="commonDeclareApplyModel.land.landBeLocatedSplicing(this)"
                           data-rule-maxlength="100" data-rule-number='true'>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">栋号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="栋号(数字)" name="buildingNumber" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           onblur="commonDeclareApplyModel.land.landBeLocatedSplicing(this)">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">单元</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="单元(数字)" name="unit" class="form-control"
                           data-rule-maxlength="100"
                           onblur="commonDeclareApplyModel.land.landBeLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼层</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="楼层(数字)" name="floor" class="form-control"
                           data-rule-maxlength="100"
                           onblur="commonDeclareApplyModel.land.landBeLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="房号" name="roomNumber" class="form-control"
                           onblur="commonDeclareApplyModel.land.landBeLocatedSplicing(this)">
                </div>
            </div>
        </div>
        <div class="form-group">

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">批文名称 </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="批文名称" name="approvalName" class="form-control">
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">批文机关 </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="批文机关" name="approvalMechanism" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">批文时间 </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="批文时间"
                           name="approvalTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime">
                </div>
            </div>
        </div>
        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">地号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="地号" name="landNumber" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">图号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="图号" name="graphNumber" class="form-control">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地用途类型<span class="symbol required"></span>
                </label>
                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                    <div class="input-group">
                        <input type="text" name="certUse" class="form-control" list="certUseList2">
                        <datalist id="certUseList2">

                        </datalist>
                        <span class="input-group-btn">
                                                <button type="button" class="btn btn-default docs-tooltip"
                                                        onclick="$(this).closest('.input-group').find('input').val('');"
                                                        data-toggle="tooltip" data-original-title="清除">
                                                <i class="fa fa-trash-o"></i>
                                                </button>
                                            </span>
                    </div>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地用途类别<span class="symbol required"></span>
                </label>
                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                    <div class="input-group">
                        <input type="text" name="certUseCategory" list="certUseCategoryList2"
                               class="form-control">
                        <datalist id="certUseCategoryList2">

                        </datalist>
                        <span class="input-group-btn">
                                                <button type="button" class="btn btn-default docs-tooltip"
                                                        onclick="$(this).closest('.input-group').find('input').val('');"
                                                        data-toggle="tooltip" data-original-title="清除">
                                                <i class="fa fa-trash-o"></i>
                                                </button>
                                            </span>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    权利性质<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <select required="required" name="landRightNature"
                            class="form-control search-select select2 landRightNature">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    终止日期<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input placeholder="终止日期"
                           name="terminationDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">取得价格</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="取得价格" name="acquisitionPrice" class="form-control" data-rule-maxlength="100"
                           data-rule-number='true'>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">使用权面积<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="使用权面积(数字)" name="useRightArea" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">分摊面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text"
                           placeholder="分摊面积(数字)" name="apportionmentArea" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">登记机关</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" placeholder="登记机关" name="registrationAuthority" class="form-control">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">记事</label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <textarea class="form-control" name="memo"></textarea>
                </div>
            </div>
        </div>
    </div>
</script>


</body>
</html>
