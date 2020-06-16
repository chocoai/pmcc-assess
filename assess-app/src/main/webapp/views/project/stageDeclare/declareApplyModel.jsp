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

    //权证号拼接
    commonDeclareApplyModel.warrantJoin = function (this_, param) {
        if (!param) {
            return false;
        }
        var frm = $(this_).closest("form");
        switch (param) {
            case "house": {
                var text = "";
                if (commonDeclareApplyModel.isNotBlank(frm.find("input[name='certName']").val())) {
                    return false;
                }
                var location = frm.find("input[name='location']").val();
                var number = frm.find("input[name='number']").val();
                var id = frm.find("select[name='type']").val();
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
                    frm.find("input[name='certName']").val(text);
                } else if (commonDeclareApplyModel.isNotBlank(id)) {
                    AssessCommon.getDataDicInfo(id, function (data) {
                        if (commonDeclareApplyModel.isNotBlank(data)) {
                            text = location + data.name + "字第" + number + "号";
                            frm.find("input[name='certName']").val(text);
                        }
                    });
                }
                break;
            }
            case "land": {
                var text = "";
                var id = frm.find("select.landRightType").val();
                var location = frm.find("input[name='location']").val();
                var year = frm.find("input[name='year']").val();
                if (year) {
                    year = "(" + frm.find("input[name='year']").val() + ")";
                }
                var number = frm.find("input[name='number']").val();
                if (!commonDeclareApplyModel.isNotBlank(number)) {
                    number = "";
                } else {
                    number = "第" + number + "号";
                }
                if (commonDeclareApplyModel.isNotBlank(id)) {
                    AssessCommon.getDataDicInfo(id, function (data) {
                        if (commonDeclareApplyModel.isNotBlank(data)) {
                            text = location + data.name + year + number;
                            frm.find("input[name='landCertName']").val(text);
                        }
                    });
                }
                break;
            }
            case "real": {
                var text = "";
                var province = frm.find("select[name='province']").find("option:selected").text();
                var provinceShort = AssessCommon.provinceForShort(province);
                var location = frm.find("input[name='location']").val();
                var number = frm.find("input[name='number']").val();
                var id = frm.find("select[name='type']").val();
                var year = frm.find("input[name='year']").val();
                if (year) {
                    year = "(" + frm.find("input[name='year']").val() + ")";
                }
                if (!commonDeclareApplyModel.isNotBlank(location)) {
                    location = "";
                }
                if (!commonDeclareApplyModel.isNotBlank(number)) {
                    number = "";
                }
                if (commonDeclareApplyModel.isNotBlank(location) && commonDeclareApplyModel.isNotBlank(number)) {
                    text = provinceShort + year + location + "不动产权" + "第" + number + "号";
                    frm.find("input[name='certName']").val(text);
                }
                break;
            }
            default: {
                break;
            }
        }
    };

    //坐落拼接
    commonDeclareApplyModel.seatJoin = function (this_) {
        var engine = $(this_).closest("form");
        if (engine.find("input[name='beLocated']").val()) return false;
        var district = engine.find("select[name='district'] option:selected").text();
        var city = engine.find("select[name='city'] option:selected").text();
        var unit = engine.find("input[name='unit']").val();
        var floor = engine.find("input[name='floor']").val();
        var roomNumber = engine.find("input[name='roomNumber']").val();
        var streetNumber = engine.find("input[name='streetNumber']").val();
        var attachedNumber = engine.find("input[name='attachedNumber']").val();
        var buildingNumber = engine.find("input[name='buildingNumber']").val();
        if (commonDeclareApplyModel.isNotBlank(unit)) {
            unit = unit + "单元";
        } else {
            unit = "";
        }
        if (commonDeclareApplyModel.isNotBlank(floor)) {
            floor = floor + "层";
        } else {
            floor = "";
        }
        if (!commonDeclareApplyModel.isNotBlank(roomNumber)) {
            roomNumber = "";
        } else {
            roomNumber = roomNumber + "号";
        }
        if (!commonDeclareApplyModel.isNotBlank(streetNumber)) {
            streetNumber = "";
        }
        if (commonDeclareApplyModel.isNotBlank(attachedNumber)) {
            attachedNumber = "附" + attachedNumber + "号";
        } else {
            attachedNumber = "";
        }
        if (commonDeclareApplyModel.isNotBlank(buildingNumber)) {
            buildingNumber = buildingNumber + "栋";
        } else {
            buildingNumber = "";
        }
        text = (streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber).replace(/号+/, '号');
        if (commonDeclareApplyModel.isNotBlank(district)) {
            if (text.indexOf(district) < 0) {
                engine.find("input[name='beLocated']").val(district + text);
            }
        } else if (commonDeclareApplyModel.isNotBlank(city) && text.indexOf(city) < 0) {
            engine.find("input[name='beLocated']").val(city + text);
        }
        engine.find("input[name='beLocated']").val(text);
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
            id3: "declareModelRealEstateCert3",
            handleId: "declareModelHandleRealEstateCert",
            handleId2: "declareModelHandleRealEstateCert2",
            handleId3: "declareModelHandleRealEstateCert3",
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
        },
        declareRealtyCheckList: {
            id: "declareRealtyCheckListCommon",
            handleId: "declareRealtyCheckListModelHandleCommon",
            name: "不动产清单"
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
        getHtml3: function () {
            return $("#" + commonDeclareApplyModel.config.realEstateCert.id3).html();
        }
    };

    /**
     * 房产证
     */
    commonDeclareApplyModel.house = {
        getHtml: function () {
            return $("#" + commonDeclareApplyModel.config.house.id).html();
        }
    };

    /**
     * 土地证
     */
    commonDeclareApplyModel.land = {
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

    //不动产清单
    commonDeclareApplyModel.declareRealtyCheckList = {
        getHtml: function () {
            return $("#" + commonDeclareApplyModel.config.declareRealtyCheckList.id).html();
        }
    }


</script>
<!-- 由于以后申报信息可能经常改变因此对房产证，不动产证,土地证,建设工程规划许可证,建设用地规划许可证,建设工程施工许可证,商品房预售许可证,经济规划指标 -->

<script type="text/html" id="declareModelCivilEngineeringCommon" data-title="土建">
    <div id="declareModelHandleCivilEngineeringCommon">
        <div class="row form-group">
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">省
                        <span class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <select name="province"
                                class="form-control input-full search-select select2 province"
                                required="required">
                            <option value="" name="province">-请选择-</option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">市<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <select name="city"
                                class="form-control input-full search-select select2 city"
                                required="required">
                        </select>
                    </div>
                </div>
            </div>

            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">县<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <select name="district"
                                class="form-control input-full search-select select2 district">
                        </select>
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">占有单位<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input type="text"
                               placeholder="占有单位" name="occupancyUnit" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">项目名称<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input type="text"
                               placeholder="项目名称" name="name" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">坐落<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input type="text"
                               placeholder="坐落" name="beLocated" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">结构<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input type="text"
                               placeholder="结构" name="structure" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label" title="评估面积">建筑面积<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input type="text"
                               placeholder="评估面积(数字)" name="buildArea" class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'
                               required="required">
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">开工日期<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input placeholder="开工日期"
                               name="startDate" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate roomTime" required="required">
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">预期完成日期<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input placeholder="预期完成日期"
                               name="expectedCompletionDate" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate roomTime" required="required">
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">形象进度<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input type="text"
                               placeholder="形象进度" name="speedProgress" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">付款比例<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input type="text"
                               placeholder="付款比例" name="paymentRatio" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">账面价值<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input type="text"
                               placeholder="账面价值" name="bookValue" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">帐面净值<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input type="text"
                               placeholder="帐面净值" name="bookNetValue" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">申报人<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input type="text"
                               placeholder="申报人" name="declarer" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">申报日期<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input placeholder="申报日期"
                               name="declarationDate" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate roomTime" required="required">
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-md-12">
                <div class="form-inline x-valid">
                    <label class="col-sm-1 col-form-label">备注<span
                            class="symbol required"></span></label>
                    <div class="col-sm-11">
                        <textarea class="form-control input-full" name="remark" required="required"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="declareModelequipmentInstallationCommon" data-title="设备安装">
    <div id="declareModelHandleEquipmentInstallationCommon">
        <div class="row form-group">
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">省
                        <span class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <select name="province"
                                class="form-control input-full search-select select2"
                                required="required">
                            <option value="" name="province">-请选择-</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">市<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <select name="city"
                                class="form-control input-full search-select select2"
                                required="required">
                        </select>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">县<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <select name="district"
                                class="form-control input-full search-select select2 district">
                        </select>
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">占有单位<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input type="text"
                               placeholder="占有单位" name="occupancyUnit" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">项目名称<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input type="text"
                               placeholder="项目名称" name="name" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">坐落<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input type="text"
                               placeholder="坐落" name="beLocated" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">规格型号<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input type="text"
                               placeholder="规格型号" name="specificationModel" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">生产厂家<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input type="text"
                               placeholder="生产厂家" name="manufacturer" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">计量单位<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input type="text"
                               placeholder="计量单位" name="measurementUnit" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">数量<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input type="text" data-rule-maxlength="100" data-rule-number='true'
                               placeholder="数量(数字)" name="number" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">开工日期<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input placeholder="开工日期"
                               name="startDate" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate roomTime" required="required">
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">预期完成日期<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input placeholder="预期完成日期"
                               name="expectedCompletionDate" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate roomTime" required="required">
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">账面设备费<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input type="text"
                               placeholder="账面设备费" name="bookEquipmentFee" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">账面资金成本<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input type="text"
                               placeholder="账面资金成本" name="bookCapitalCost" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">账面安装费<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input type="text"
                               placeholder="账面安装费" name="bookInstallationFee" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">其它<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input type="text"
                               placeholder="其它" name="other" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">申报人<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input type="text"
                               placeholder="申报人" name="declarer" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">申报日期<span
                            class="symbol required"></span></label>
                    <div class="col-sm-10">
                        <input placeholder="申报日期"
                               name="declarationDate" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate roomTime" required="required">
                    </div>
                </div>
            </div>

        </div>

        <div class="row form-group">
            <div class="col-md-12">
                <div class="form-inline x-valid">
                    <label class="col-sm-2 col-form-label">备注<span
                            class="symbol required"></span></label>
                    <div class="col-sm-11">
                        <textarea class="form-control input-full" name="remark" required="required"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="buildingConstructionPermitCommon" data-title="建筑工程施工许可证">
    <div id="declareModelHandleBuildingConstructionPermitCommon">


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        证书编号<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="证书编号" name="certificateNumber" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        发证机关<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="发证机关" name="issuingOrgan" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        日期<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="日期"
                               name="date" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate roomTime" required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        建设单位（个人）<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="建设单位（个人）" name="buildUnit" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        建设项目名称<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="建设项目名称" name="name" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        建设地址<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="建设地址" name="buildAddress" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        建设规模<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="建设规模" name="scaleConstruction" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        勘察单位<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="勘察单位" name="reconnaissanceUnit" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        设计单位<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="设计单位" name="designUnit" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        施工单位<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="施工单位" name="constructionUnit" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        监理单位<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="监理单位" name="constructionControlUnit"
                               class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        勘察单位项目负责人<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="勘察单位项目负责人" name="reconnaissanceUnitPerson"
                               class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        设计单位项目负责人<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="设计单位项目负责人" name="designUnitPerson" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        施工单位项目负责人<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="施工单位项目负责人" name="constructionUnitPerson"
                               class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        总监理工程师<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="总监理工程师" name="chiefEngineerConstructionInspection"
                               class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        合同工期<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="合同工期"
                               name="contractPeriod" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate roomTime" required="required">
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                        备注<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                        <textarea name="remark" placeholder="备注" class="form-control input-full"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="preSalePermitCommon" data-title="商品房预售许可证">
    <div id="declareModelHandlePreSalePermitCommon">


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        证书编号<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="证书编号" name="certificateNumber" class="form-control input-full"
                               required="required">
                    </div>

                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        售房单位<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="售房单位" name="salesUnit" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        法定代表人<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="法定代表人" name="legalRepresentative" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        项目坐落<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="项目坐落" name="beLocated" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        项目名称<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="项目名称" name="name" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        预售范围<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="预售范围" name="preSaleScope" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        预售面积<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="预售面积(数字)" data-rule-maxlength="100"
                               data-rule-number='true' name="preSaleArea" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋用途<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="房屋用途" name="housingUse" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        建筑结构<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="建筑结构" name="buildingStructure" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        预售款监管信息<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="预售款监管信息" name="preSaleSupervisionInformation"
                               class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        发证机关<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="发证机关" name="issuingOrgan"
                               class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        日期<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="日期"
                               name="date" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate roomTime" required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        在建工程抵押情况<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="在建工程抵押情况" name="mortgageSituation" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                        备注<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                        <textarea name="remark" placeholder="备注" class="form-control input-full"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="landUsePermitCommon" data-title="建设用地规划许可证">
    <div id="declareModelHandleLandUsePermitCommon">
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        证书编号<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="证书编号" name="certificateNumber" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        发证机关<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="发证机关" name="issuingOrgan" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        日期<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="日期"
                               name="date" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate roomTime" required="required">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        用地单位<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="用地单位" name="unit" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        用地项目名称<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="用地项目名称" name="name" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        用地位置<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="用地位置" name="location" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        用地性质<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="用地性质" name="nature" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        用地面积<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="用地面积" data-rule-maxlength="100" data-rule-number='true'
                               name="area" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        建设规模<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="建设规模" name="scaleConstruction" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                        备注<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                        <textarea name="remark" placeholder="备注" class="form-control input-full"></textarea>
                    </div>
                </div>
            </div>
        </div>

    </div>
</script>

<script type="text/html" id="buildingPermitCommon" data-title="建设工程规划许可证">
    <div id="declareModelHandleBuildingPermitCommon">


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                        证书编号<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                        <input type="text"
                               placeholder="证书编号" name="certificateNumber" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        发证机关<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="发证机关" name="issuingOrgan" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        日期<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="日期"
                               name="date" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate roomTime" required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        建设单位（个人）<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="建设单位（个人）" name="unit" class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        建设项目名称<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="建设项目名称" name="name" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        建设位置
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="建设位置" name="location" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        建设规模
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="建设规模" name="scaleConstruction" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                        备注<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                        <textarea name="remark" placeholder="备注" class="form-control input-full"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="declareModelRealEstateCert" data-title="不动产证">
    <div id="declareModelHandleRealEstateCert">
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        省
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select name="province"
                                class="form-control input-full search-select select2 province"
                                required="required">
                            <option value="" name="province">-请选择-</option>
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        市
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select name="city"
                                class="form-control input-full search-select select2"
                                required="required">
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        县
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select name="district"
                                class="form-control input-full search-select select2 district">
                        </select>
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                        不动产权证号<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                        <div class="input-group">
                            <input type="text" placeholder="不动产权证号" name="certName" class="form-control form-control-sm"
                                   required>
                            <div class="input-group-prepend ">
                                <button class="btn btn-info btn-sm "
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button" onclick="commonDeclareApplyModel.warrantJoin(this,'real');">自动拼接
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        所在地
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="所在地" name="location" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        编号
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" data-rule-maxlength="100" data-rule-number='true'
                               placeholder="编号(数字)" name="number" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        年份
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" required="required" data-rule-maxlength="100"
                               data-rule-number='true' name="year" class="form-control input-full"
                               placeholder="年份(数字如:2018)">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        不动产单元号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="不动产单元号" name="realEstateUnitNumber" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋所有权人
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="房屋所有权人" name="ownership" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        共有情况
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select required="required" name="publicSituation"
                                class="form-control input-full search-select select2 publicSituation">
                        </select>
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        共有情况说明
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="共有情况说明" name="publicSituationRemark" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        登记日期
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="登记日期"
                               name="registrationTime" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate roomTime">
                    </div>
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        街道号
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" required
                               placeholder="街道号" name="streetNumber" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        附号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="附号" name="attachedNumber" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        栋号<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="栋号" required name="buildingNumber" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        单元
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="单元(数字)" name="unit" class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        楼层
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="楼层" name="floor" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="房号" name="roomNumber" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                        坐落<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                        <div class="input-group">
                            <input type="text" placeholder="坐落" name="beLocated" class="form-control form-control-sm"
                                   required>
                            <div class="input-group-prepend ">
                                <button class="btn btn-info btn-sm "
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button" onclick="commonDeclareApplyModel.seatJoin(this);">自动拼接
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋结构
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="房屋结构" name="housingStructure" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋用途类型<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <div class="input-group">
                            <input type="text" name="houseCertUse" class="form-control form-control-sm"
                                   list="realHouseUseList"
                                   required>
                            <datalist id="realHouseUseList"></datalist>
                            <div class="input-group-prepend ">
                                <button class="btn btn-warning btn-sm"
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button"
                                        onclick="$(this).closest('.input-group').find('input').val('');">
                                    清空
                                    <i class="fa "></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋用途类别<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <div class="input-group">
                            <input type="text" name="houseCertUseCategory" class="form-control form-control-sm"
                                   list="houseCertUseCategoryList1" required>
                            <datalist id="houseCertUseCategoryList1"></datalist>
                            <div class="input-group-prepend ">
                                <button class="btn btn-warning btn-sm"
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button"
                                        onclick="$(this).closest('.input-group').find('input').val('');">
                                    清空
                                    <i class="fa "></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋性质<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select name="nature" class="form-control input-full search-select select2 nature" required>
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        证载面积<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" required
                               placeholder="证载面积(数字)" name="evidenceArea" class="form-control input-full"
                               data-rule-maxlength="100"
                               data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        套内面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="套内面积(数字)" name="innerArea" class="form-control input-full"
                               data-rule-maxlength="100"
                               data-rule-number='true'>
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        公摊面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="公摊面积(数字)" name="apportionmentArea" class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        其它(房屋)
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="其它" name="other" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        附记其它
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="附记其它" name="otherNote" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        总层数
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="总层数" name="floorCount" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地取得方式
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select required="required" name="acquisitionType"
                                class="form-control input-full search-select select2 acquisitionType">
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地取得价格
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="土地取得价格" name="acquisitionPrice" class="form-control input-full"
                               data-rule-maxlength="100"
                               data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地用途类型<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <div class="input-group">
                            <input type="text" name="landCertUse" class="form-control form-control-sm"
                                   list="landCertUseList" required>
                            <datalist id="landCertUseList">

                            </datalist>
                            <div class="input-group-prepend ">
                                <button class="btn btn-warning btn-sm"
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button"
                                        onclick="$(this).closest('.input-group').find('input').val('');">
                                    清空
                                    <i class="fa "></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地用途类别
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <div class="input-group">
                            <input type="text" name="landCertUseCategory" list="landCertUseCategoryList" required
                                   class="form-control form-control-sm">
                            <datalist id="landCertUseCategoryList">

                            </datalist>
                            <div class="input-group-prepend ">
                                <button class="btn btn-warning btn-sm"
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button"
                                        onclick="$(this).closest('.input-group').find('input').val('');">
                                    清空
                                    <i class="fa "></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地权利性质
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select required="required" name="landRightNature"
                                class="form-control input-full search-select select2 landRightNature">
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        共用宗地面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="共用宗地面积(数字)" name="useRightArea" class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'>
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        权利类型<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select name="landRightType" class="form-control input-full search-select select2 landRightType"
                                required="required">
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地分摊面积<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" required
                               placeholder="土地分摊面积(数字)" name="landApportionArea" class="form-control input-full"
                               data-rule-maxlength="100"
                               data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地使用年限起
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="土地使用年限起"
                               name="useStartDate" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate roomTime">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地使用年限止<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="土地使用年限止" required
                               name="useEndDate" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate roomTime">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        登记机关
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" required
                               placeholder="登记机关" name="registrationAuthority" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                        记事(土地)
                    </label>
                    <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                        <textarea placeholder="记事" name="memo" class="form-control input-full"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="declareModelRealEstateCert2" data-title="不动产证(样式和上面的不动产有所差异)">
    <div id="declareModelHandleRealEstateCert2">
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        省
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select name="province"
                                class="form-control input-full search-select select2 province"
                                required="required">
                            <option value="" name="province">-请选择-</option>
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        市
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select name="city"
                                class="form-control input-full search-select select2"
                                required="required">
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        县
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select name="district"
                                class="form-control input-full search-select select2 district">
                        </select>
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                        批文文号
                    </label>
                    <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                        <input type="text"
                               placeholder="批文文号" required name="certName" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        所在地
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="所在地" name="location" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        编号
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" data-rule-maxlength="100" data-rule-number='true'
                               placeholder="编号(数字)" name="number" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        不动产单元号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="不动产单元号" name="realEstateUnitNumber" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        权利人
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="权利人" name="ownership" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        登记日期
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="登记日期"
                               name="registrationTime" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate ">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        共有情况
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select required="required" name="publicSituation"
                                class="form-control input-full search-select select2 publicSituation">
                        </select>
                    </div>
                </div>
            </div>
        </div>


        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        街道号
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" required
                               placeholder="街道号" name="streetNumber" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        附号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="附号" name="attachedNumber" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        栋号<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="栋号" required name="buildingNumber" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        单元
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="单元(数字)" name="unit" class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        楼层
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="楼层" name="floor" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="房号" name="roomNumber" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                        坐落<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                        <input type="text" placeholder="坐落" name="beLocated" class="form-control input-full"
                               required>
                        <%--<div class="input-group">--%>
                        <%--<input type="text" placeholder="坐落" name="beLocated" class="form-control form-control-sm"--%>
                        <%--required>--%>
                        <%--<div class="input-group-prepend ">--%>
                        <%--<button class="btn btn-info btn-sm "--%>
                        <%--style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"--%>
                        <%--type="button" onclick="commonDeclareApplyModel.seatJoin(this);">自动拼接--%>
                        <%--</button>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                    </div>
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地用途类型<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <div class="input-group">
                            <input type="text" name="landCertUse" class="form-control form-control-sm"
                                   list="landCertUseList2">
                            <datalist id="landCertUseList2">

                            </datalist>
                            <div class="input-group-prepend ">
                                <button class="btn btn-warning btn-sm"
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button"
                                        onclick="$(this).closest('.input-group').find('input').val('');">
                                    清空
                                    <i class="fa "></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        权利性质
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select required="required" name="landRightNature"
                                class="form-control input-full search-select select2 landRightNature">
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地用途类别<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <div class="input-group">
                            <input type="text" name="landCertUseCategory" list="landCertUseCategoryList2" required
                                   class="form-control form-control-sm">
                            <datalist id="landCertUseCategoryList2">

                            </datalist>
                            <div class="input-group-prepend ">
                                <button class="btn btn-warning btn-sm"
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button"
                                        onclick="$(this).closest('.input-group').find('input').val('');">
                                    清空
                                    <i class="fa "></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        权利类型<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select name="landRightType" class="form-control input-full search-select select2 landRightType"
                                required="required">
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地使用年限起
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="土地使用年限起"
                               name="useStartDate" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate roomTime">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地使用年限止<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="土地使用年限止" required
                               name="useEndDate" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate ">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        共用宗地面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="共用宗地面积(数字)" name="useRightArea" class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        取得价格
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="取得价格" name="acquisitionPrice" class="form-control input-full"
                               data-rule-maxlength="100"
                               data-rule-number='true'>
                    </div>
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        批文名称
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="批文名称" name="approvalName" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        批文机关
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="批文机关" name="approvalMechanism" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        批文时间
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="批文时间" name="approvalTime" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate roomTime" readonly="readonly">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                        记事
                    </label>
                    <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                        <textarea placeholder="记事" name="memo" class="form-control input-full"></textarea>
                    </div>
                </div>
            </div>
        </div>
        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋用途类型
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <div class="input-group">
                            <input type="text" name="houseCertUse" class="form-control form-control-sm"
                                   list="realHouseUseList2">
                            <datalist id="realHouseUseList2"></datalist>
                            <div class="input-group-prepend ">
                                <button class="btn btn-warning btn-sm"
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button"
                                        onclick="$(this).closest('.input-group').find('input').val('');">
                                    清空
                                    <i class="fa "></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋用途类别
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <div class="input-group">
                            <input type="text" name="houseCertUseCategory" class="form-control form-control-sm"
                                   list="houseCertUseCategoryList2">
                            <datalist id="houseCertUseCategoryList2"></datalist>
                            <div class="input-group-prepend ">
                                <button class="btn btn-warning btn-sm"
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button"
                                        onclick="$(this).closest('.input-group').find('input').val('');">
                                    清空
                                    <i class="fa "></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋结构
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="房屋结构" name="housingStructure" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋性质
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select name="nature"
                                class="form-control input-full search-select select2 nature">
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        证载面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="证载面积(数字)" name="evidenceArea" class="form-control input-full"
                               data-rule-maxlength="100"
                               data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        套内面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="套内面积(数字)" name="innerArea" class="form-control input-full"
                               data-rule-maxlength="100"
                               data-rule-number='true'>
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        分摊面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="分摊面积(数字)" name="apportionmentArea" class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        登记机关
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="登记机关" name="registrationAuthority" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        其它
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="其它" name="other" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        附记其它
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="附记其它" name="otherNote" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        总层数
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="总层数(数字)" name="floorCount" class="form-control input-full"
                               data-rule-maxlength="100"
                               data-rule-number='true'>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="declareModelRealEstateCert3" data-title="土地不动产证">
    <div id="declareModelHandleRealEstateCert3">
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        省
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select name="province"
                                class="form-control input-full search-select select2 province"
                                required="required">
                            <option value="" name="province">-请选择-</option>
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        市
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select name="city"
                                class="form-control input-full search-select select2"
                                required="required">
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        县
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select name="district"
                                class="form-control input-full search-select select2 district">
                        </select>
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                        不动产权证号<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                        <div class="input-group">
                            <input type="text" placeholder="不动产权证号" name="certName" class="form-control form-control-sm"
                                   required>
                            <div class="input-group-prepend ">
                                <button class="btn btn-info btn-sm "
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button" onclick="commonDeclareApplyModel.warrantJoin(this,'real');">自动拼接
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        所在地
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="所在地" name="location" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        编号
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" data-rule-maxlength="100" data-rule-number='true'
                               placeholder="编号(数字)" name="number" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        年份
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" required="required" data-rule-maxlength="100"
                               data-rule-number='true' name="year" class="form-control input-full"
                               placeholder="年份(数字如:2018)">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        不动产单元号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="不动产单元号" name="realEstateUnitNumber" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋所有权人
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="房屋所有权人" name="ownership" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        共有情况
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select required="required" name="publicSituation"
                                class="form-control input-full search-select select2 publicSituation">
                        </select>
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        共有情况说明
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="共有情况说明" name="publicSituationRemark" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        登记日期
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="登记日期"
                               name="registrationTime" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate roomTime">
                    </div>
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        街道号
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" required
                               placeholder="街道号" name="streetNumber" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        附号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="附号" name="attachedNumber" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        栋号<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="栋号" required name="buildingNumber" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        单元
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="单元(数字)" name="unit" class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        楼层
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="楼层" name="floor" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="房号" name="roomNumber" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                        坐落<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                        <div class="input-group">
                            <input type="text" placeholder="坐落" name="beLocated" class="form-control form-control-sm"
                                   required>
                            <div class="input-group-prepend ">
                                <button class="btn btn-info btn-sm "
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button" onclick="commonDeclareApplyModel.seatJoin(this);">自动拼接
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地取得方式
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select required="required" name="acquisitionType"
                                class="form-control input-full search-select select2 acquisitionType">
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地取得价格
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="土地取得价格" name="acquisitionPrice" class="form-control input-full"
                               data-rule-maxlength="100"
                               data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地用途类型<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <div class="input-group">
                            <input type="text" name="landCertUse" class="form-control form-control-sm"
                                   list="landCertUseList3" required>
                            <datalist id="landCertUseList3">

                            </datalist>
                            <div class="input-group-prepend ">
                                <button class="btn btn-warning btn-sm"
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button"
                                        onclick="$(this).closest('.input-group').find('input').val('');">
                                    清空
                                    <i class="fa "></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地用途类别
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <div class="input-group">
                            <input type="text" name="landCertUseCategory" list="landCertUseCategoryList3" required
                                   class="form-control form-control-sm">
                            <datalist id="landCertUseCategoryList3">

                            </datalist>
                            <div class="input-group-prepend ">
                                <button class="btn btn-warning btn-sm"
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button"
                                        onclick="$(this).closest('.input-group').find('input').val('');">
                                    清空
                                    <i class="fa "></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地权利性质
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select required="required" name="landRightNature"
                                class="form-control input-full search-select select2 landRightNature">
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        共用宗地面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="共用宗地面积(数字)" name="useRightArea" class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'>
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        权利类型<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select name="landRightType" class="form-control input-full search-select select2 landRightType"
                                required="required">
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地分摊面积<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" required
                               placeholder="土地分摊面积(数字)" name="landApportionArea" class="form-control input-full"
                               data-rule-maxlength="100"
                               data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地使用年限起
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="土地使用年限起"
                               name="useStartDate" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate roomTime">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地使用年限止<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="土地使用年限止" required
                               name="useEndDate" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate roomTime">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        登记机关
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" required
                               placeholder="登记机关" name="registrationAuthority" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                        记事(土地)
                    </label>
                    <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                        <textarea placeholder="记事" name="memo" class="form-control input-full"></textarea>
                    </div>
                </div>
            </div>
        </div>


        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋结构
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="房屋结构" name="housingStructure" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋用途类型
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <div class="input-group">
                            <input type="text" name="houseCertUse" class="form-control form-control-sm"
                                   list="realHouseUseList3">
                            <datalist id="realHouseUseList3"></datalist>
                            <div class="input-group-prepend ">
                                <button class="btn btn-warning btn-sm"
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button"
                                        onclick="$(this).closest('.input-group').find('input').val('');">
                                    清空
                                    <i class="fa "></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋用途类别
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <div class="input-group">
                            <input type="text" name="houseCertUseCategory" class="form-control form-control-sm"
                                   list="houseCertUseCategoryList3" >
                            <datalist id="houseCertUseCategoryList3"></datalist>
                            <div class="input-group-prepend ">
                                <button class="btn btn-warning btn-sm"
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button"
                                        onclick="$(this).closest('.input-group').find('input').val('');">
                                    清空
                                    <i class="fa "></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋性质
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select name="nature" class="form-control input-full search-select select2 nature" >
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        证载面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="证载面积(数字)" name="evidenceArea" class="form-control input-full"
                               data-rule-maxlength="100"
                               data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        套内面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="套内面积(数字)" name="innerArea" class="form-control input-full"
                               data-rule-maxlength="100"
                               data-rule-number='true'>
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        公摊面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="公摊面积(数字)" name="apportionmentArea" class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        其它(房屋)
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="其它" name="other" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        附记其它
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="附记其它" name="otherNote" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        总层数
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="总层数" name="floorCount" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="declareModelHouse" data-title="房产证信息">
    <div id="declareModelHandleHouse">
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        省
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select name="province"
                                class="form-control input-full search-select select2"
                                required="required">
                            <option value="" name="province">-请选择-</option>
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        市
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select name="city"
                                class="form-control input-full search-select select2"
                                required="required">
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        县
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select name="district"
                                class="form-control input-full search-select select2 district">
                        </select>
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                        房产权证号<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                        <div class="input-group">
                            <input type="text" placeholder="房产权证号" name="certName" class="form-control form-control-sm"
                                   required>
                            <div class="input-group-prepend ">
                                <button class="btn btn-info btn-sm "
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button" onclick="commonDeclareApplyModel.warrantJoin(this,'house');">自动拼接
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        所在地
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="所在地" name="location" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        编号
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" data-rule-maxlength="100" data-rule-number='true'
                               placeholder="编号(数字)" name="number" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        类型
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select class="form-control input-full" name="type" required="required">
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
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋所有权人
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="房屋所有权人" name="ownership" class="form-control input-full"
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        建筑面积
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="建筑面积(数字)" name="floorArea" class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'
                               required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        共有情况
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select required="required" name="publicSituation"
                                class="form-control input-full search-select select2 publicSituation">
                        </select>
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        共有情况说明
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="共有情况说明" name="publicSituationRemark" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        登记日期
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="登记日期" name="registrationDate" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate roomTime">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        丘地号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="丘地号" name="groundNum" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>
        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        街道号
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" required
                               placeholder="街道号" name="streetNumber" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        附号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="附号" name="attachedNumber" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        栋号<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="栋号" required name="buildingNumber" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        单元
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="单元(数字)" name="unit" class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        楼层
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="楼层" name="floor" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="房号" name="roomNumber" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                        坐落<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                        <div class="input-group">
                            <input type="text" placeholder="坐落" name="beLocated" class="form-control form-control-sm"
                                   required>
                            <div class="input-group-prepend ">
                                <button class="btn btn-info btn-sm "
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button" onclick="commonDeclareApplyModel.seatJoin(this);">自动拼接
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋结构
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="房屋结构" name="housingStructure" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋用途类型<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <div class="input-group">
                            <input type="text" name="certUse" class="form-control form-control-sm" list="houseUseList"
                                   required="required">
                            <datalist id="houseUseList"></datalist>
                            <div class="input-group-prepend ">
                                <button class="btn btn-warning btn-sm "
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button"
                                        onclick="$(this).closest('.input-group').find('input').val('');">
                                    清空
                                    <i class="fa "></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋用途类别<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <div class="input-group">
                            <input type="text" name="certUseCategory" class="form-control form-control-sm"
                                   list="housecertUseCategoryList"
                                   required>
                            <datalist id="housecertUseCategoryList"></datalist>
                            <div class="input-group-prepend ">
                                <button class="btn btn-warning btn-sm"
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button"
                                        onclick="$(this).closest('.input-group').find('input').val('');">
                                    清空
                                    <i class="fa "></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋性质<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select required="required" name="nature"
                                class="form-control input-full search-select select2 nature">
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        证载面积<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" required
                               placeholder="证载面积(数字)" name="evidenceArea" class="form-control input-full"
                               data-rule-maxlength="100"
                               data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        套内面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="套内面积(数字)" name="innerArea" class="form-control input-full"
                               data-rule-maxlength="100"
                               data-rule-number='true'>
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        公摊面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="公摊面积(数字)" name="publicArea" class="form-control input-full"
                               data-rule-maxlength="100"
                               data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        总层数
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="总层数" name="floorCount" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地取得方式
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select name="landAcquisition"
                                class="form-control input-full search-select select2 acquisitionType">
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        登记机关<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" required="required"
                               placeholder="登记机关" name="registrationAuthority" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        分摊面积<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="分摊面积(数字)" name="apportionmentArea"
                               class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'>
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地使用年限起
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="土地使用年限起"
                               name="useStartDate" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate roomTime">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地使用年限止<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="土地使用年限止" required
                               name="useEndDate" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate roomTime">
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                        其它
                    </label>
                    <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                        <textarea placeholder="其它" name="other" class="form-control input-full"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="declareModelLand" data-title="土地证信息">
    <div id="declareModelHandleLand">
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        省
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select name="province"
                                class="form-control input-full search-select select2"
                                required="required">
                            <option value="" name="province">-请选择-</option>
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        市
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select name="city"
                                class="form-control input-full search-select select2"
                                required="required">
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        县
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select name="district"
                                class="form-control input-full search-select select2 district">
                        </select>
                    </div>
                </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        所在地
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" name="location" required="required" class="form-control input-full"
                               placeholder="所在地">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        类型
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select required="required" name="landRightType"
                                class="form-control input-full search-select select2 landRightType">
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        年份
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" required="required" data-rule-maxlength="100"
                               data-rule-number='true' name="year" class="form-control input-full"
                               placeholder="年份(数字如:2018)">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        编号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" name="number" class="form-control input-full"
                               placeholder="编号">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地使用权人<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" name="ownership" required="required" class="form-control input-full"
                               placeholder="土地使用权人">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        共有情况
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select required="required" name="publicSituation"
                                class="form-control input-full search-select select2 publicSituation">
                        </select>
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                        土地权证号<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                        <div class="input-group">
                            <input type="text" placeholder="土地权证号" name="landCertName"
                                   class="form-control form-control-sm" required>
                            <div class="input-group-prepend ">
                                <button class="btn btn-info btn-sm "
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button" onclick="commonDeclareApplyModel.warrantJoin(this,'land');">自动拼接
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        共有情况说明
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="共有情况说明" name="publicSituationRemark" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        登记日期
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="登记日期" name="registrationDate" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate roomTime">
                    </div>
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        街道号
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" required
                               placeholder="街道号" name="streetNumber" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        附号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="附号" name="attachedNumber" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        栋号<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="栋号" required name="buildingNumber" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        单元
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="单元(数字)" name="unit" class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        楼层
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="楼层" name="floor" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="房号" name="roomNumber" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                        坐落<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                        <div class="input-group">
                            <input type="text" placeholder="坐落" name="beLocated" class="form-control form-control-sm"
                                   required>
                            <div class="input-group-prepend ">
                                <button class="btn btn-info btn-sm "
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button" onclick="commonDeclareApplyModel.seatJoin(this);">自动拼接
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        地号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="地号" name="landNumber" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        图号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="图号" name="graphNumber" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地用途类型<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <div class="input-group">
                            <input type="text" id="certUse" name="certUse" class="form-control form-control-sm"
                                   list="certUseList"
                                   required>
                            <datalist id="certUseList"></datalist>
                            <div class="input-group-prepend ">
                                <button class="btn btn-warning btn-sm "
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button"
                                        onclick="$(this).closest('.input-group').find('input').val('');">
                                    清空
                                    <i class="fa "></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地用途类别<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <div class="input-group">
                            <input type="text" id="certUseCategory" name="certUseCategory" list="certUseCategoryList"
                                   class="form-control form-control-sm" required>
                            <datalist id="certUseCategoryList">

                            </datalist>
                            <div class="input-group-prepend ">
                                <button class="btn btn-warning btn-sm"
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button"
                                        onclick="$(this).closest('.input-group').find('input').val('');">
                                    清空
                                    <i class="fa "></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        <label class="col-sm-2 col-form-label">取得价格</label>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="取得价格" name="acquisitionPrice" class="form-control input-full"
                               data-rule-maxlength="100"
                               data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        权利性质<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select required="required" name="landRightNature"
                                class="form-control input-full search-select select2 landRightNature">
                        </select>
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        终止日期
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="终止日期" name="terminationDate" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate ">
                    </div>

                </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        使用权面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="使用权面积(数字)" name="useRightArea" class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        分摊面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="分摊面积(数字)" name="apportionmentArea"
                               class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        登记机关<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="登记机关" name="registrationAuthority"
                               class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                        记事
                    </label>
                    <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                        <textarea class="form-control input-full" name="memo"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="declareModelLand2" data-title="土地证信息2">
    <div id="declareModelHandleLand2">
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        省
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select name="province"
                                class="form-control input-full search-select select2"
                                required="required">
                            <option value="" name="province">-请选择-</option>
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        市
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select name="city"
                                class="form-control input-full search-select select2"
                                required="required">
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        县
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select name="district"
                                class="form-control input-full search-select select2 district">
                        </select>
                    </div>
                </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                        批文文号
                    </label>
                    <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                        <input type="text" name="landCertName"
                               class="form-control input-full" placeholder="批文文号">
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        所在地
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" name="location" required="required" class="form-control input-full"
                               placeholder="所在地">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        类型
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select required="required" name="landRightType"
                                class="form-control input-full search-select select2 landRightType">
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        年份
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" required="required" data-rule-maxlength="100"
                               data-rule-number='true' name="year" class="form-control input-full"
                               placeholder="年份(数字如:2018)">
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        编号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" name="number" class="form-control input-full"
                               placeholder="编号">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        共有情况
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select required="required" name="publicSituation"
                                class="form-control input-full search-select select2 publicSituation">
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        登记日期
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="登记日期" name="registrationDate" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate roomTime">
                    </div>
                </div>
            </div>
        </div>


        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        街道号/村组
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" required
                               placeholder="街道号/村组" name="streetNumber" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        附号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="附号" name="attachedNumber" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        栋号<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="栋号" required name="buildingNumber" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        单元
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="单元(数字)" name="unit" class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        楼层
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="楼层" name="floor" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="房号" name="roomNumber" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                        坐落<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                        <input type="text" placeholder="坐落" name="beLocated" class="form-control input-full"
                               required>

                        <%--<div class="input-group">--%>
                        <%--<input type="text" placeholder="坐落" name="beLocated" class="form-control form-control-sm"--%>
                        <%--required>--%>
                        <%--<div class="input-group-prepend ">--%>
                        <%--<button class="btn btn-info btn-sm "--%>
                        <%--style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"--%>
                        <%--type="button" onclick="commonDeclareApplyModel.seatJoin(this);">自动拼接--%>
                        <%--</button>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        批文名称
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="批文名称" name="approvalName" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        批文机关
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="批文机关" name="approvalMechanism" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        批文时间
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="批文时间"
                               name="approvalTime" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate roomTime">
                    </div>
                </div>
            </div>
        </div>
        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        地号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="地号" name="landNumber" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        图号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="图号" name="graphNumber" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地用途类型<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <div class="input-group">
                            <input type="text" name="certUse" class="form-control form-control-sm"
                                   list="certUseList2"
                                   required>
                            <datalist id="certUseList2"></datalist>
                            <div class="input-group-prepend ">
                                <button class="btn btn-warning btn-sm "
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button"
                                        onclick="$(this).closest('.input-group').find('input').val('');">
                                    清空
                                    <i class="fa "></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地用途类别<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <div class="input-group">
                            <input type="text" name="certUseCategory" list="certUseCategoryList2"
                                   class="form-control form-control-sm" required>
                            <datalist id="certUseCategoryList2">

                            </datalist>
                            <div class="input-group-prepend ">
                                <button class="btn btn-warning btn-sm"
                                        style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                        type="button"
                                        onclick="$(this).closest('.input-group').find('input').val('');">
                                    清空
                                    <i class="fa "></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        权利性质<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <select required="required" name="landRightNature"
                                class="form-control input-full search-select select2 landRightNature">
                        </select>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        终止日期<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="终止日期"
                               name="terminationDate" data-date-format="yyyy-mm-dd"
                               class="form-control input-full date-picker dbdate roomTime" required="required">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        取得价格
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="取得价格" name="acquisitionPrice" class="form-control input-full"
                               data-rule-maxlength="100"
                               data-rule-number='true'>
                    </div>
                </div>
            </div>
        </div>


        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        使用权面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="使用权面积(数字)" name="useRightArea" class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        分摊面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="分摊面积(数字)" name="apportionmentArea"
                               class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        登记机关<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="登记机关" name="registrationAuthority"
                               class="form-control input-full"
                               required="required">
                    </div>
                </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                        记事
                    </label>
                    <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                        <textarea class="form-control input-full" name="memo"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="declareRealtyCheckListCommon" data-title="不动产清单">
    <div id="declareRealtyCheckListModelHandleCommon">
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label realEstateUnitNumber">
                        不动产单元号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3 realEstateUnitNumber">
                        <input type="text"
                               placeholder="不动产单元号" name="realEstateUnitNumber" class="form-control input-full ">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        所在区
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="所在区" name="district" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        门牌号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="门牌号" name="houseNumber" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        街道<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="街道" name="streetNumber" required="required"
                               class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        附号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="附号(数字)" name="attachedNumber" class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        栋号<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" required placeholder="栋号" name="buildingNumber" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        单元
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="单元(数字)" name="unit" class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        楼层
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="楼层" name="floor" class="form-control input-full" data-rule-maxlength="100"
                               data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="房号" name="roomNumber" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        用途
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="用途" name="certUse" class="form-control input-full">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋建筑面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="房屋建筑面积" name="floorArea" class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        分摊面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text" placeholder="分摊面积" name="apportionmentArea" class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'>
                    </div>
                </div>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        结构
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input type="text"
                               placeholder="结构" name="housingStructure" class="form-control input-full">
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>


</body>
</html>
