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
            handleId: "declareModelHandleLand",
            name: "土地证信息"
        },
        realEstateCert: {
            id: "declareModelRealEstateCert",
            handleId: "declareModelHandleRealEstateCert",
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
        economicIndicators: {
            id: "economicIndicatorsModelCommon",
            handleId: "economicIndicatorsModelHandleCommon",
            name: "经济指标"
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
        //不动产权证号
        CertNameSplicing: function (that) {
            var text = "";
            text = $(that).val();
            if (commonDeclareApplyModel.isNotBlank(text)) {
                var engine = $(that).closest("#" + commonDeclareApplyModel.config.realEstateCert.handleId);
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
                if (commonDeclareApplyModel.isNotBlank(id)) {
                    AssessCommon.getDataDicInfo(id, function (data) {
                        if (commonDeclareApplyModel.isNotBlank(data)) {
                            text = location + "不动产权" + data.name + "第" + number + "号";
                            engine.find("input[name='certName']").val(text);
                        }
                    });
                } else {
                    text = location + "不动产权" + id + "第" + number + "号";
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
                var district = engine.find("select[name='district']").val();
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
                /* if (commonDeclareApplyModel.isNotBlank(district)) {
                     AssessCommon.getAreaById(district, function (data) {
                         if (!commonDeclareApplyModel.isNotBlank(data)) {
                             district = "";
                         } else {
                             district = data.name;
                         }
                         text = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                         engine.find("input[name='beLocated']").val(text);
                     });
                 } else {
                     district = "";
                     text = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                     engine.find("input[name='beLocated']").val(text);
                 }*/
                text = streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                engine.find("input[name='beLocated']").val(text);
            }
        },
        getRealEstateColumn: function () {
            var cols = [];
            cols.push({
                field: 'provinceName', title: '区域', formatter: function (value, row, index) {
                    return AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
                }
            });
            cols.push({field: 'evidenceArea', title: '证载面积'});
            cols.push({field: 'certName', title: '不动产权证号'});
            cols.push({field: 'beLocated', title: '房屋坐落'});
            return cols;
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
                if (commonDeclareApplyModel.isNotBlank(id)) {
                    AssessCommon.getDataDicInfo(id, function (data) {
                        if (commonDeclareApplyModel.isNotBlank(data)) {
                            text = location + data.name + "字地" + number + "号";
                            engine.find("input[name='certName']").val(text);
                        }
                    });
                } else {
                    text = location + id + "字地" + number + "号";
                    engine.find("input[name='certName']").val(text);
                }
            }
        },
        //房屋坐落
        beLocatedSplicing: function (that) {
            var text = "";
            text = $(that).val();
            if (commonDeclareApplyModel.isNotBlank(text)) {
                var engine = $(that).closest("#" + commonDeclareApplyModel.config.house.handleId);
                var district = engine.find("select[name='district']").val();
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
               /* if (commonDeclareApplyModel.isNotBlank(district)) {
                    AssessCommon.getAreaById(district, function (data) {
                        if (!commonDeclareApplyModel.isNotBlank(data)) {
                            district = "";
                        } else {
                            district = data.name;
                        }
                        text = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                        engine.find("input[name='beLocated']").val(text);
                    });
                } else {
                    district = "";
                    text = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                    engine.find("input[name='beLocated']").val(text);
                }*/
                text = streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                engine.find("input[name='beLocated']").val(text);
            }
        },
        getHouseColumn: function () {
            var cols = [];
            cols.push({
                field: 'provinceName', title: '区域', formatter: function (value, row, index) {
                    return AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
                }
            });
            cols.push({field: 'certName', title: '房屋权证号'});
            cols.push({field: 'beLocated', title: '房屋坐落'});
            cols.push({field: 'ownership', title: '房屋所有权人'});
            cols.push({field: 'floorArea', title: '建筑面积'});
            cols.push({field: 'planningUseName', title: '规划用途'});
            return cols;
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
                var id = engine.find("select.type").val();
                var location = engine.find("input[name='location']").val();
                var year = engine.find("input[name='year']").val();
                var number = engine.find("input[name='number']").val();
                if (commonDeclareApplyModel.isNotBlank(id)) {
                    AssessCommon.getDataDicInfo(id, function (data) {
                        if (commonDeclareApplyModel.isNotBlank(data)) {
                            text = location + data.name + year + "第" + number + "号";
                            engine.find("input[name='landCertName']").val(text);
                        }
                    });
                } else {
                    text = location + year + "第" + number + "号";
                    engine.find("input[name='landCertName']").val(text);
                }
            }
        },
        //房屋坐落 拼接
        landBeLocatedSplicing: function (that) {
            var text = "";
            text = $(that).val();
            if (commonDeclareApplyModel.isNotBlank(text)) {
                var engine = $(that).closest("#" + commonDeclareApplyModel.config.land.handleId);
                var district = engine.find("select[name='district']").val();
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
               /* if (commonDeclareApplyModel.isNotBlank(district)) {
                    AssessCommon.getAreaById(district, function (data) {
                        if (!commonDeclareApplyModel.isNotBlank(data)) {
                            district = "";
                        } else {
                            district = data.name;
                        }
                        text = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                        engine.find("input[name='beLocated']").val(text);
                    });
                } else {
                    district = "";
                    text = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                    engine.find("input[name='beLocated']").val(text);
                }*/
                text = streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                engine.find("input[name='beLocated']").val(text);
            }
        },
        getHtml: function () {
            return $("#" + commonDeclareApplyModel.config.land.id).html();
        },
        getLandColumn: function () {
            var cols = [];
            cols.push({
                field: 'provinceName', title: '区域', formatter: function (value, row, index) {
                    return AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
                }
            });
            cols.push({field: 'landCertName', title: '土地权证号'});
            cols.push({field: 'beLocated', title: '房屋坐落'});
            cols.push({field: 'landNumber', title: '地号'});
            cols.push({field: 'graphNumber', title: '图号'});
            cols.push({field: 'useRightArea', title: '使用权面积'});
            return cols;
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

    commonDeclareApplyModel.economicIndicators = {
        getHtml: function () {
            return $("#" + commonDeclareApplyModel.config.economicIndicators.id).html();
        },
        /**
         * 添加子项
         * @param _this
         * @param template
         */
        addChild: function (_this, template) {
            var tr = $(_this).closest('tr');
            var childs = tr.treegrid('getChildNodes');
            if (childs.length <= 0) {
                tr.after($('#' + template).html());
            } else {
                //如果最后一个子项下还有子项则在子项的子项后添加元素
                var subChilds = $(childs.get(childs.length - 1)).treegrid('getChildNodes');
                if (subChilds.length <= 0) {
                    $(childs.get(childs.length - 1)).after($('#' + template).html());
                } else {
                    $(subChilds.get(subChilds.length - 1)).after($('#' + template).html());
                }
            }
            commonDeclareApplyModel.economicIndicators.treegirdParse();
        },
        /**
         * 树形表格解析
         */
        treegirdParse: function () {
            $('#' + commonDeclareApplyModel.config.economicIndicators.handleId).find('.tree').treegrid();
        },
        /**
         * 获取表单数据
         * @returns {Array}
         */
        getFormData: function () {
            var dataArray = [];
            $("#" + commonDeclareApplyModel.config.economicIndicators.handleId).find('tbody tr[data-key]').each(function () {
                var data = {};
                data.customKey = $(this).attr('data-key');
                data.content = $(this).find('[name=content]').val();
                if ($(this).find('[name=number]').length > 0) {
                    data.number = $(this).find('[name=number]').val();
                }
                switch (data.customKey) {
                    case "houseBuildingArea":
                    case "nonHouseBuildingArea":
                    case "undergroundBuildingArea":
                    case "undergroundParkingSpace":
                    case "parkingFacilitie":
                        var nodes = $(this).treegrid('getChildNodes');
                        if (nodes && nodes.length > 0) {
                            var childDataArray = [];
                            $.each(nodes, function (i, item) {
                                var child = {};
                                child.name = $(item).find('[name=name]').val();
                                if (child.name) {
                                    if ($(item).find('[name=huxing]').length > 0) {
                                        child.huxing = $(item).find('[name=huxing]').val();
                                    }
                                    if ($(item).find('[name=huxingArea]').length > 0) {
                                        child.huxingArea = $(item).find('[name=huxingArea]').val();
                                    }
                                    if ($(item).find('[name=householdCount]').length > 0) {
                                        child.householdCount = $(item).find('[name=householdCount]').val();
                                    }
                                    if ($(item).find('[name=number]').length > 0) {
                                        child.number = $(item).find('[name=number]').val();
                                    }
                                    child.content = $(item).find('[name=content]').val();
                                    childDataArray.push(child);
                                }
                            })
                            data.childData = JSON.stringify(childDataArray);
                        }
                        break;
                    default:

                        break;
                }
                dataArray.push(data);
            });
            return dataArray;
        },
        /**
         * 填充表单数据
         * @param pid
         * @param callback
         */
        initForm: function (pid, callback) {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/economicIndicators/getEntityListByPid",
                type: 'post',
                data: {
                    pid: pid
                },
                dataType: 'json',
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        $.each(result.data, function (i, item) {
                            var tr = $('#' + commonDeclareApplyModel.config.economicIndicators.handleId).find('tr[data-key=' + item.customKey + ']');
                            tr.find('[name=content]').val(item.content);
                            if (tr.find('[name=number]').length > 0) {
                                tr.find('[name=number]').val(item.number);
                            }
                            if (item.childData) {
                                var template = undefined;
                                switch (item.customKey) {
                                    case "houseBuildingArea":
                                        template = $("#residentialAreaHtml").html();
                                        break
                                    case "nonHouseBuildingArea":
                                        template = $("#nonResidentialAreaHtml").html();
                                        break;
                                    case "undergroundBuildingArea":
                                        template = $("#substructureAreaHtml").html();
                                        break;
                                    case "undergroundParkingSpace":
                                        template = $("#parkingSpaceHtml").html();
                                        break;
                                    case "parkingFacilitie":
                                        template = $("#supportingFacilitieHtml").html();
                                        break;
                                }
                                $.each(JSON.parse(item.childData), function (i, cdData) {
                                    //确定写入位置
                                    var element;
                                    var childs = tr.treegrid('getChildNodes');
                                    if (childs.length <= 0) {
                                        element = $(template).insertAfter(tr);
                                    } else {
                                        //判断最后的子项是否还有子项数据
                                        var subChilds = $(childs.get(childs.length - 1)).treegrid('getChildNodes');
                                        console.log(subChilds);
                                        if (subChilds.length <= 0) {
                                            element = $(template).insertAfter(childs.get(childs.length - 1));
                                        } else {
                                            element = $(template).insertAfter(subChilds.get(subChilds.length - 1));
                                        }
                                    }
                                    element.find('[name=name]').val(cdData.name);
                                    element.find('[name=content]').val(cdData.content);
                                    if (cdData.huxing) {
                                        element.find('[name=huxing]').val(cdData.huxing);
                                    }
                                    if (cdData.huxingArea) {
                                        element.find('[name=huxingArea]').val(cdData.huxingArea);
                                    }
                                    if (cdData.householdCount) {
                                        element.find('[name=householdCount]').val(cdData.householdCount);
                                    }
                                    if (cdData.number) {
                                        element.find('[name=number]').val(cdData.number);
                                    }
                                    commonDeclareApplyModel.economicIndicators.treegirdParse();
                                })
                            }
                        });
                    }
                    if (callback) {
                        commonDeclareApplyModel.economicIndicators.treegirdParse();
                        callback();
                    }
                }
            });
        }
    };


</script>
<!-- 由于以后申报信息可能经常改变因此对房产证，不动产证,土地证,建设工程规划许可证,建设用地规划许可证,建设工程施工许可证,商品房预售许可证,经济规划指标 -->

<script type="text/html" id="declareModelCivilEngineeringCommon" data-title="土建">
    <div id="declareModelHandleCivilEngineeringCommon">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">省
                    <span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select name="province"
                            class="form-control search-select select2 province"
                            required="required">
                        <option value="" name="province">-请选择-</option>
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">市<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select name="city"
                            class="form-control search-select select2 city"
                            required="required">
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">县<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select name="district"
                            class="form-control search-select select2 district">
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">占有单位<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="占有单位" name="occupancyUnit" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">项目名称<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="项目名称" name="name" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">坐落<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="坐落" name="beLocated" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">结构<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="结构" name="structure" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label" title="评估面积">建筑面积<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="评估面积(数字)" name="buildArea" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">开工日期<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input placeholder="开工日期"
                           name="startDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">预期完成日期<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input placeholder="预期完成日期"
                           name="expectedCompletionDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">形象进度<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="形象进度" name="speedProgress" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">付款比例<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="付款比例" name="paymentRatio" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">账面价值<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="账面价值" name="bookValue" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">帐面净值<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="帐面净值" name="bookNetValue" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">申报人<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="申报人" name="declarer" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">申报日期<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input placeholder="申报日期"
                           name="declarationDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">备注<span
                        class="symbol required"></span></label>
                <div class="col-sm-11">
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
                <label class="col-sm-1 control-label">省
                    <span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select name="province"
                            class="form-control search-select select2"
                            required="required">
                        <option value="" name="province">-请选择-</option>
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">市<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select name="city"
                            class="form-control search-select select2"
                            required="required">
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">县<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select name="district"
                            class="form-control search-select select2 district">
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">占有单位<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="占有单位" name="occupancyUnit" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">项目名称<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="项目名称" name="name" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">坐落<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="坐落" name="beLocated" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">规格型号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="规格型号" name="specificationModel" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">生产厂家<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="生产厂家" name="manufacturer" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">计量单位<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="计量单位" name="measurementUnit" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">数量<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="数量(数字)" name="number" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">开工日期<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input placeholder="开工日期"
                           name="startDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">预期完成日期<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input placeholder="预期完成日期"
                           name="expectedCompletionDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">账面设备费<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="账面设备费" name="bookEquipmentFee" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">账面资金成本<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="账面资金成本" name="bookCapitalCost" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">账面安装费<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="账面安装费" name="bookInstallationFee" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">其它<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="其它" name="other" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">申报人<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="申报人" name="declarer" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">申报日期<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input placeholder="申报日期"
                           name="declarationDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">备注<span
                        class="symbol required"></span></label>
                <div class="col-sm-11">
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
                <label class="col-sm-1 control-label">证书编号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="证书编号" name="certificateNumber" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">发证机关<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="发证机关" name="issuingOrgan" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">日期<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input placeholder="日期"
                           name="date" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">建设单位（个人）<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="建设单位（个人）" name="buildUnit" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">建设项目名称<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="建设项目名称" name="name" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">建设地址<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="建设地址" name="buildAddress" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">建设规模<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="建设规模" name="scaleConstruction" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">勘察单位<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="勘察单位" name="reconnaissanceUnit" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">设计单位<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="设计单位" name="designUnit" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">施工单位<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="施工单位" name="constructionUnit" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">监理单位<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="监理单位" name="constructionControlUnit"
                           class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">勘察单位项目负责人<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="勘察单位项目负责人" name="reconnaissanceUnitPerson"
                           class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">设计单位项目负责人<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="设计单位项目负责人" name="designUnitPerson" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">施工单位项目负责人<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="施工单位项目负责人" name="constructionUnitPerson"
                           class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">总监理工程师<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="总监理工程师" name="chiefEngineerConstructionInspection"
                           class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">合同工期<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input placeholder="合同工期"
                           name="contractPeriod" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">备注<span
                        class="symbol required"></span></label>
                <div class="col-sm-11">
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
                <label class="col-sm-1 control-label">证书编号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="证书编号" name="certificateNumber" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">售房单位<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="售房单位" name="salesUnit" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">法定代表人<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="法定代表人" name="legalRepresentative" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">项目坐落<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="项目坐落" name="beLocated" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">项目名称<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="项目名称" name="name" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">预售范围<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="预售范围" name="preSaleScope" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">预售面积<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="预售面积(数字)" data-rule-maxlength="100"
                           data-rule-number='true' name="preSaleArea" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋用途<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="房屋用途" name="housingUse" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">建筑结构<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="建筑结构" name="buildingStructure" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">预售款监管信息<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="预售款监管信息" name="preSaleSupervisionInformation"
                           class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">发证机关<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="发证机关" name="issuingOrgan"
                           class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">日期<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input placeholder="日期"
                           name="date" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">在建工程抵押情况<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="在建工程抵押情况" name="mortgageSituation" class="form-control"
                           required="required">
                </div>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="landUsePermitCommon" data-title="建设用地规划许可证">
    <div id="declareModelHandleLandUsePermitCommon">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">证书编号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="证书编号" name="certificateNumber" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">发证机关<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="发证机关" name="issuingOrgan" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">日期<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input placeholder="日期"
                           name="date" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">用地单位<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="用地单位" name="unit" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">用地项目名称<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="用地项目名称" name="name" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">用地位置<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="用地位置" name="location" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">用地性质<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="用地性质" name="nature" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">用地面积<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="用地面积" data-rule-maxlength="100" data-rule-number='true'
                           name="area" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">建设规模<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="建设规模" name="scaleConstruction" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

    </div>
</script>

<script type="text/html" id="buildingPermitCommon" data-title="建设工程规划许可证">
    <div id="declareModelHandleBuildingPermitCommon">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">证书编号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="证书编号" name="certificateNumber" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">发证机关<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="发证机关" name="issuingOrgan" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">日期<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input placeholder="日期"
                           name="date" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">建设单位（个人）<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="建设单位（个人）" name="unit" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">建设项目名称<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="建设项目名称" name="name" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">建设位置<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="建设位置" name="location" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">建设规模<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="建设规模" name="scaleConstruction" class="form-control"
                           required="required">
                </div>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="declareModelRealEstateCert" data-title="不动产证">
    <div id="declareModelHandleRealEstateCert">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    省<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <select name="province"
                            class="form-control search-select select2 province"
                            required="required">
                        <option value="" name="province">-请选择-</option>
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    市<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <select name="city"
                            class="form-control search-select select2"
                            required="required">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    县(区)
                </label>
                <div class="col-sm-3">
                    <select name="district"
                            class="form-control search-select select2 district"
                            onchange="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this)">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">不动产权证号</label>
                <div class="col-sm-11">
                    <input type="text" readonly="readonly"
                           placeholder="不动产权证号" name="certName" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">所在地<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="所在地" name="location" class="form-control"
                           required="required" onblur="commonDeclareApplyModel.realEstateCert.CertNameSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">编号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="编号(数字)" name="number" class="form-control"
                           required="required" onblur="commonDeclareApplyModel.realEstateCert.CertNameSplicing(this)">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋所有权人<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="房屋所有权人" name="ownership" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">共有情况<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select required="required" name="publicSituation"
                            class="form-control search-select select2 publicSituation">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    登记日期
                </label>
                <div class="col-sm-3">
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
                <label class="col-sm-1 control-label">房屋坐落</label>
                <div class="col-sm-11">
                    <input type="text" readonly="readonly"
                           placeholder="房屋坐落" name="beLocated" class="form-control">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">街道号</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="街道号" name="streetNumber" class="form-control"
                           onblur="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">附号</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="附号(数字)" name="attachedNumber" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           onblur="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">栋号</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="栋号(数字)" name="buildingNumber" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           onblur="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this)">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">单元</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="单元(数字)" name="unit" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           onblur="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼层</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="楼层(数字)" name="floor" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           onblur="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">房号</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="房号(数字)" name="roomNumber" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           onblur="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this)">
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">

            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋用途<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select name="planningUse" required
                            class="form-control search-select select2 planningUse">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋结构</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="房屋结构" name="housingStructure" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋性质</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="房屋性质" name="nature" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">建筑面积<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="建筑面积(数字)" name="floorArea" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">证载面积<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="证载面积(数字)" name="evidenceArea" class="form-control"
                           data-rule-maxlength="100" required
                           data-rule-number='true'>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">套内面积</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="套内面积(数字)" name="innerArea" class="form-control" data-rule-maxlength="100"
                           data-rule-number='true'>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">总层数</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="总层数(数字)" name="floorCount" class="form-control"
                           data-rule-maxlength="100"
                           data-rule-number='true'>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">登记机关</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="登记机关" name="registrationAuthority" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">其它</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="其它" name="other" class="form-control">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">附记其它</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="附记其它" name="otherNote" class="form-control">
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地用途<span
                        class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <select required="required" name="purpose"
                            class="form-control search-select select2 purpose">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    权利性质<span
                        class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <select required="required" name="useRightType"
                            class="form-control search-select select2 useRightType">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">权利类型<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select name="type" class="form-control search-select select2 type" required="required">
                    </select>
                </div>
            </div>

        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地使用年限起
                </label>
                <div class="col-sm-3">
                    <input placeholder="土地使用年限起"
                           name="useStartDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地使用年限止<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input placeholder="土地使用年限止" required
                           name="useEndDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">共用宗地面积</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="共用宗地面积(数字)" name="useRightArea" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">分摊面积</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="分摊面积(数字)" name="apportionmentArea" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">取得价格</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="取得价格" name="acquisitionPrice" class="form-control"
                           data-rule-maxlength="100"
                           data-rule-number='true'>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">记事</label>
                <div class="col-sm-11">
                                            <textarea class="form-control" name="memo">
                                            </textarea>
                </div>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="declareModelHouse" data-title="房产证信息">
    <div id="declareModelHandleHouse">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">省
                    <span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select name="province"
                            class="form-control search-select select2"
                            required="required">
                        <option value="" name="province">-请选择-</option>
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">市<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select name="city"
                            class="form-control search-select select2"
                            required="required">
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">县<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select name="district"
                            class="form-control search-select select2 district"
                            onchange="commonDeclareApplyModel.house.beLocatedSplicing(this)">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">房产权证号</label>
                <div class="col-sm-11">
                    <input type="text" readonly="readonly"
                           placeholder="房产权证号" name="certName" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">所在地<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="所在地" name="location" class="form-control"
                           required="required" onblur="commonDeclareApplyModel.house.certNameSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">编号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                           placeholder="编号(数字)" name="number" class="form-control"
                           required="required" onblur="commonDeclareApplyModel.house.certNameSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">类型<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select name="type" class="form-control search-select select2 type"
                            required="required" onchange="commonDeclareApplyModel.house.certNameSplicing(this)">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋所有权人<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="房屋所有权人" name="ownership" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">建筑面积<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="建筑面积(数字)" name="floorArea" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    登记日期
                </label>
                <div class="col-sm-3">
                    <input placeholder="登记日期" name="registrationDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime">
                </div>
            </div>
        </div>
        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋坐落</label>
                <div class="col-sm-11">
                    <input type="text" readonly="readonly"
                           placeholder="房屋坐落" name="beLocated" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">街道号<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" required
                           placeholder="街道号" name="streetNumber" class="form-control"
                           onblur="commonDeclareApplyModel.house.beLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">附号</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="附号(数字)" name="attachedNumber" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           onblur="commonDeclareApplyModel.house.beLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">栋号</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="栋号(数字)" name="buildingNumber" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           onblur="commonDeclareApplyModel.house.beLocatedSplicing(this)">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">单元</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="单元(数字)" name="unit" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           onblur="commonDeclareApplyModel.house.beLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼层</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="楼层(数字)" name="floor" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           onblur="commonDeclareApplyModel.house.beLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">房号</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="房号(数字)" name="roomNumber" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           onblur="commonDeclareApplyModel.house.beLocatedSplicing(this)">
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">规划用途<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select required="required" name="planningUse"
                            class="form-control search-select select2 planningUse">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋结构</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="房屋结构" name="housingStructure" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋性质</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="房屋性质" name="nature" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">证载面积<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" required
                           placeholder="证载面积(数字)" name="evidenceArea" class="form-control"
                           data-rule-maxlength="100"
                           data-rule-number='true'>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">套内面积</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="套内面积(数字)" name="innerArea" class="form-control"
                           data-rule-maxlength="100"
                           data-rule-number='true'>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">总层数</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="总层数(数字)" name="floorCount" class="form-control" data-rule-maxlength="100"
                           data-rule-number='true'>
                </div>
            </div>
        </div>
        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地使用年限起
                </label>
                <div class="col-sm-3">
                    <input placeholder="土地使用年限起"
                           name="useStartDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地使用年限止<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input placeholder="土地使用年限止" required
                           name="useEndDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">公摊面积</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="公摊面积(数字)" name="publicArea" class="form-control" data-rule-maxlength="100"
                           data-rule-number='true'>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地取得方式</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="土地取得方式" name="landAcquisition" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">登记机关</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="登记机关" name="registrationAuthority" class="form-control">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">其它</label>
                <div class="col-sm-11">
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
                <label class="col-sm-1 control-label">
                    省<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <select name="province"
                            class="form-control search-select select2 province"
                            required="required">
                        <option value="" name="province">-请选择-</option>
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    市<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <select name="city"
                            class="form-control search-select select2 city"
                            required="required">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    县(区)
                </label>
                <div class="col-sm-3">
                    <select name="district"
                            class="form-control search-select select2 district"
                            onchange="commonDeclareApplyModel.land.landBeLocatedSplicing(this)">
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地权证号
                </label>
                <div class="col-sm-11">
                    <input type="text" name="landCertName" readonly="readonly"
                           class="form-control" placeholder="土地权证号">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    所在地<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" name="location" required="required" class="form-control"
                           onblur="commonDeclareApplyModel.land.landCertNameSplicing(this)"
                           placeholder="所在地">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    类型<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <select required="required" name="type"
                            onchange="commonDeclareApplyModel.land.landCertNameSplicing(this)"
                            class="form-control search-select select2 type">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    年份<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" required="required" data-rule-maxlength="100"
                           onblur="commonDeclareApplyModel.land.landCertNameSplicing(this)"
                           data-rule-number='true' name="year" class="form-control"
                           placeholder="年份(数字如:2018)">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    编号<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" required="required" name="number" class="form-control"
                           onblur="commonDeclareApplyModel.land.landCertNameSplicing(this)"
                           placeholder="编号">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地使用权人<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" name="ownership" required="required" class="form-control"
                           placeholder="土地使用权人">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    登记日期
                </label>
                <div class="col-sm-3">
                    <input placeholder="登记日期" name="registrationDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime">
                </div>
            </div>
        </div>
        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋坐落<span class="symbol required"></span></label>
                <div class="col-sm-11">
                    <input type="text" readonly="readonly"
                           placeholder="房屋坐落" name="beLocated" class="form-control">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">街道号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="街道号" name="streetNumber" class="form-control"
                           onblur="commonDeclareApplyModel.land.landBeLocatedSplicing(this)"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">附号</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="附号(数字)" name="attachedNumber" class="form-control"
                           onblur="commonDeclareApplyModel.land.landBeLocatedSplicing(this)"
                           data-rule-maxlength="100" data-rule-number='true'>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">栋号</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="栋号(数字)" name="buildingNumber" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           onblur="commonDeclareApplyModel.land.landBeLocatedSplicing(this)">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">单元</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="单元(数字)" name="unit" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           onblur="commonDeclareApplyModel.land.landBeLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼层</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="楼层(数字)" name="floor" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           onblur="commonDeclareApplyModel.land.landBeLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">房号</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="房号(数字)" name="roomNumber" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           onblur="commonDeclareApplyModel.land.landBeLocatedSplicing(this)">
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">地号</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="地号" name="landNumber" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">图号</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="图号" name="graphNumber" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地用途<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <select required="required" name="purpose"
                            class="form-control search-select select2 purpose">
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    使用权类型<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <select required="required" name="useRightType"
                            class="form-control search-select select2 useRightType">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    终止日期<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input placeholder="终止日期"
                           name="terminationDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">取得价格</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="取得价格" name="acquisitionPrice" class="form-control" data-rule-maxlength="100"
                           data-rule-number='true'>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">使用权面积<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="使用权面积(数字)" name="useRightArea" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">分摊面积</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="分摊面积(数字)" name="apportionmentArea" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">登记机关</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="登记机关" name="registrationAuthority" class="form-control">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">记事</label>
                <div class="col-sm-11">
                    <textarea class="form-control" name="memo"></textarea>
                </div>
            </div>
        </div>
    </div>
</script>

<!-- 经济指标 start  -->
<script type="text/html" id="economicIndicatorsModelCommon">
    <div id="economicIndicatorsModelHandleCommon">
        <input type="hidden" name="pid">
        <input type="hidden" name="planDetailsId">
        <table class="table tree">
            <thead>
            <tr>
                <th>名称</th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <tr class="treegrid-1" data-key="netLandArea">
                <td>一、规划建设净用地面积</td>
                <td></td>
                <td></td>
                <td></td>
                <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
            </tr>
            <tr class="treegrid-2" data-key="planTotalArea">
                <td>二、规划总建筑面积</td>
                <td></td>
                <td></td>
                <td></td>
                <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
            </tr>
            <tr class="treegrid-2-1 treegrid-parent-2" data-key="groundBuildingArea">
                <td>（一）地上计入容积率的建筑面积</td>
                <td></td>
                <td></td>
                <td></td>
                <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
            </tr>
            <tr class="treegrid-2-1-1 treegrid-parent-2-1" data-key="houseBuildingArea">
                <td>1、住宅建筑面积
                    <a class="btn btn-xs btn-warning tooltips" data-placement="top"
                       onclick="commonDeclareApplyModel.economicIndicators.addChild(this,'residentialAreaHtml');"><i
                            class="fa fa-plus fa-white"></i></a></td>
                <th>户型</th>
                <th>户型面积</th>
                <th>户数</th>
                <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
            </tr>
            <tr class="treegrid-2-1-2 treegrid-parent-2-1" data-key="nonHouseBuildingArea">
                <td>2、非住宅建筑面积
                    <a class="btn btn-xs btn-warning tooltips" data-placement="top"
                       onclick="commonDeclareApplyModel.economicIndicators.addChild(this,'nonResidentialAreaHtml');"><i
                            class="fa fa-plus fa-white"></i></a>
                </td>
                <td></td>
                <td></td>
                <td></td>
                <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
            </tr>

            <tr class="treegrid-2-2 treegrid-parent-2" data-key="groundExcludBuildingArea">
                <td>（二）地上不计入容积率的建筑面积</td>
                <td></td>
                <td></td>
                <td></td>
                <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
            </tr>
            <tr class="treegrid-2-2-1 treegrid-parent-2-2" data-key="overheadBuildingArea">
                <td>1、首层架空建筑面积</td>
                <td></td>
                <td></td>
                <td></td>
                <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
            </tr>
            <tr class="treegrid-2-2-2 treegrid-parent-2-2" data-key="heatBuildingArea">
                <td>2、外保温建筑面积</td>
                <td></td>
                <td></td>
                <td></td>
                <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
            </tr>
            <tr class="treegrid-2-3 treegrid-parent-2" data-key="undergroundBuildingArea">
                <td>（三）地下建筑面积及层数
                    <a class="btn btn-xs btn-warning tooltips" data-placement="top"
                       onclick="commonDeclareApplyModel.economicIndicators.addChild(this,'substructureAreaHtml');"><i
                            class="fa fa-plus fa-white"></i></a>
                </td>
                <td></td>
                <td></td>
                <td></td>
                <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
            </tr>
            <tr class="treegrid-3" data-key="volumetricRate">
                <td>三、容积率</td>
                <td></td>
                <td></td>
                <td></td>
                <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"></td>
            </tr>
            <tr class="treegrid-4">
                <td>四、建筑基底面积</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr class="treegrid-4-1 treegrid-parent-4" data-key="buildingBaseTotalArea">
                <td>建筑基地总面积</td>
                <td></td>
                <td></td>
                <td></td>
                <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
            </tr>
            <tr class="treegrid-4-2 treegrid-parent-4" data-key="highMainBaseArea">
                <td>高层主体基底（基座）面积</td>
                <td></td>
                <td></td>
                <td></td>
                <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
            </tr>
            <tr class="treegrid-5">
                <td>五、建筑密度</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr class="treegrid-5-1 treegrid-parent-5" data-key="totalBuildingDensity">
                <td>总建筑密度</td>
                <td></td>
                <td></td>
                <td></td>
                <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> %</td>
            </tr>
            <tr class="treegrid-5-1 treegrid-parent-5" data-key="highMainBuildingDensity">
                <td>高层主体建筑密度</td>
                <td></td>
                <td></td>
                <td></td>
                <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> %</td>
            </tr>
            <tr class="treegrid-6" data-key="greenSpaceRate">
                <td>六、绿地率</td>
                <td></td>
                <td></td>
                <td></td>
                <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> %</td>
            </tr>
            <tr class="treegrid-6-1 treegrid-parent-6" data-key="greenArea">
                <td>绿地面积</td>
                <td></td>
                <td></td>
                <td></td>
                <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
            </tr>
            <tr class="treegrid-7" data-key="mobileParkingSpace">
                <td>七、机动车位</td>
                <td></td>
                <td></td>
                <td>数量</td>
                <td>面积</td>
            </tr>
            <tr class="treegrid-7-1 treegrid-parent-7" data-key="undergroundParkingSpace">
                <td>（一）地下停车位
                    <a class="btn btn-xs btn-warning tooltips" data-placement="top"
                       onclick="commonDeclareApplyModel.economicIndicators.addChild(this,'parkingSpaceHtml');"><i
                            class="fa fa-plus fa-white"></i></a>
                </td>
                <td></td>
                <td></td>
                <td><input type="text" name="number" data-rule-digits="true" style="width: 100px;"> 辆</td>
                <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
            </tr>
            <tr class="treegrid-7-1-1 treegrid-parent-7-1" data-key="residentialParkingSpace">
                <td>（1）住宅停车位</td>
                <td></td>
                <td></td>
                <td><input type="text" name="number" data-rule-digits="true" style="width: 100px;"> 辆</td>
                <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
            </tr>
            <tr class="treegrid-7-1-2 treegrid-parent-7-1" data-key="commercialParkingSpace">
                <td>（2）商业停车位</td>
                <td></td>
                <td></td>
                <td><input type="text" name="number" data-rule-digits="true" style="width: 100px;"> 辆</td>
                <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
            </tr>
            <tr class="treegrid-7-1-3 treegrid-parent-7-1" data-key="parkingFacilitie">
                <td>（3）配套设施停车位
                    <a class="btn btn-xs btn-warning tooltips" data-placement="top"
                       onclick="commonDeclareApplyModel.economicIndicators.addChild(this,'supportingFacilitieHtml');"><i
                            class="fa fa-plus fa-white"></i></a>
                </td>
                <td></td>
                <td></td>
                <td><input type="text" name="number" data-rule-digits="true" style="width: 100px;"> 辆</td>
                <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
            </tr>
            <tr class="treegrid-7-1-3-1 treegrid-parent-7-1-3" data-key="propertyRoomParking">
                <td>物管用房停车位</td>
                <td></td>
                <td></td>
                <td><input type="text" name="number" data-rule-digits="true" style="width: 100px;"> 辆</td>
                <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
            </tr>
            <tr class="treegrid-7-1-3-2 treegrid-parent-7-1-3" data-key="publicHousingParking">
                <td>社区公共服务用房停车位</td>
                <td></td>
                <td></td>
                <td><input type="text" name="number" data-rule-digits="true" style="width: 100px;"> 辆</td>
                <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
            </tr>
            <tr class="treegrid-7-1-3-3 treegrid-parent-7-1-3" data-key="intelligentCommunityParking">
                <td>智慧小区用房停车位</td>
                <td></td>
                <td></td>
                <td><input type="text" name="number" data-rule-digits="true" style="width: 100px;"> 辆</td>
                <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
            </tr>
            </tbody>
        </table>
    </div>
</script>

<%--住宅面积模板--%>
<script type="text/html" id="residentialAreaHtml">
    <tr class="dynamic treegrid-2-1-1-1 treegrid-parent-2-1-1">
        <td>
            <input type="text" name="name" style="width: 100px;">
            <a class="btn btn-xs btn-warning tooltips" data-placement="top"
               onclick="$(this).closest('tr').remove();"><i class="fa fa-minus fa-white"></i></a>
        </td>
        <td><input type="text" name="huxing" style="width: 100px;"></td>
        <td><input type="text" name="huxingArea" data-rule-number="true" style="width: 100px;"></td>
        <td><input type="text" name="householdCount" data-rule-digits="true" style="width: 100px;"></td>
        <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
    </tr>
</script>

<%--非住宅面积模板--%>
<script type="text/html" id="nonResidentialAreaHtml">
    <tr class="dynamic treegrid-2-1-2-1 treegrid-parent-2-1-2">
        <td>
            <input type="text" name="name" style="width: 100px;">
            <a class="btn btn-xs btn-warning tooltips" data-placement="top"
               onclick="$(this).closest('tr').remove();"><i class="fa fa-minus fa-white"></i></a>
        </td>
        <td></td>
        <td></td>
        <td></td>
        <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
    </tr>
</script>

<%--地下建筑面积模板--%>
<script type="text/html" id="substructureAreaHtml">
    <tr class="dynamic treegrid-2-3-1 treegrid-parent-2-3">
        <td>
            <input type="text" name="name" style="width: 100px;">
            <a class="btn btn-xs btn-warning tooltips" data-placement="top"
               onclick="$(this).closest('tr').remove();"><i class="fa fa-minus fa-white"></i></a>
        </td>
        <td></td>
        <td></td>
        <td></td>
        <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
    </tr>
</script>

<%--地下停车位模板--%>
<script type="text/html" id="parkingSpaceHtml">
    <tr class="dynamic treegrid-7-1-1 treegrid-parent-7-1">
        <td>
            <input type="text" name="name" style="width: 100px;">
            <a class="btn btn-xs btn-warning tooltips" data-placement="top"
               onclick="$(this).closest('tr').remove();"><i class="fa fa-minus fa-white"></i></a>
        </td>
        <td></td>
        <td></td>
        <td><input type="text" name="number" data-rule-digits="true" style="width: 100px;"> 辆</td>
        <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
    </tr>
</script>

<%--配套设施停车位模板--%>
<script type="text/html" id="supportingFacilitieHtml">
    <tr class="dynamic treegrid-7-1-3-1 treegrid-parent-7-1-3">
        <td>
            <input type="text" name="name" style="width: 100px;">
            <a class="btn btn-xs btn-warning tooltips" data-placement="top"
               onclick="$(this).closest('tr').remove();"><i class="fa fa-minus fa-white"></i></a>
        </td>
        <td></td>
        <td></td>
        <td><input type="text" name="number" data-rule-digits="true" style="width: 100px;"> 辆</td>
        <td><input type="text" name="content" data-rule-number="true" style="width: 100px;"> ㎡</td>
    </tr>
</script>

<!-- 经济指标 end -->

</body>
</html>
