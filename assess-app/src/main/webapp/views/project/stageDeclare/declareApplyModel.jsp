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
                            text = location + "不动产权证" + data.name + "字地" + number + "号";
                            engine.find("input[name='certName']").val(text);
                        }
                    });
                } else {
                    text = location + "不动产权证" + id + "字地" + number + "号";
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
                    floor = floor + "楼";
                }
                if (!commonDeclareApplyModel.isNotBlank(roomNumber)) {
                    roomNumber = "";
                } else {
                    roomNumber = roomNumber + "号";
                }
                if (!commonDeclareApplyModel.isNotBlank(streetNumber)) {
                    streetNumber = "";
                }
                if (!commonDeclareApplyModel.isNotBlank(attachedNumber)) {
                    attachedNumber = "";
                } else {
                    attachedNumber = "附" + attachedNumber;
                }
                if (!commonDeclareApplyModel.isNotBlank(buildingNumber)) {
                    buildingNumber = "";
                } else {
                    buildingNumber = buildingNumber + "栋";
                }
                if (commonDeclareApplyModel.isNotBlank(district)) {
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
                if (commonDeclareApplyModel.isNotBlank(id)) {
                    AssessCommon.getDataDicInfo(id, function (data) {
                        if (commonDeclareApplyModel.isNotBlank(data)) {
                            text = location + "房权证" + data.name + "字地" + number + "号";
                            engine.find("input[name='certName']").val(text);
                        }
                    });
                } else {
                    text = location + "房权证" + id + "字地" + number + "号";
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
                    floor = floor + "楼";
                }
                if (!commonDeclareApplyModel.isNotBlank(roomNumber)) {
                    roomNumber = "";
                } else {
                    roomNumber = roomNumber + "号";
                }
                if (!commonDeclareApplyModel.isNotBlank(streetNumber)) {
                    streetNumber = "";
                }
                if (!commonDeclareApplyModel.isNotBlank(attachedNumber)) {
                    attachedNumber = "";
                } else {
                    attachedNumber = "附" + attachedNumber;
                }
                if (!commonDeclareApplyModel.isNotBlank(buildingNumber)) {
                    buildingNumber = "";
                } else {
                    buildingNumber = buildingNumber + "栋";
                }
                if (commonDeclareApplyModel.isNotBlank(district)) {
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
                var id = engine.find("select.type").val();
                var location = engine.find("input[name='location']").val();
                var year = engine.find("input[name='year']").val();
                var number = engine.find("input[name='number']").val();
                if (commonDeclareApplyModel.isNotBlank(id)) {
                    AssessCommon.getDataDicInfo(id, function (data) {
                        if (declareRealtyLandCert.isEmpty(data)) {
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
                    floor = floor + "楼";
                }
                if (!commonDeclareApplyModel.isNotBlank(roomNumber)) {
                    roomNumber = "";
                } else {
                    roomNumber = roomNumber + "号";
                }
                if (!commonDeclareApplyModel.isNotBlank(streetNumber)) {
                    streetNumber = "";
                }
                if (!commonDeclareApplyModel.isNotBlank(attachedNumber)) {
                    attachedNumber = "";
                } else {
                    attachedNumber = "附" + attachedNumber;
                }
                if (!commonDeclareApplyModel.isNotBlank(buildingNumber)) {
                    buildingNumber = "";
                } else {
                    buildingNumber = buildingNumber + "栋";
                }
                if (commonDeclareApplyModel.isNotBlank(district)) {
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
                }
            }
        },
        getHtml: function () {
            return $("#" + commonDeclareApplyModel.config.land.id).html();
        }

    };


</script>
<!-- 由于以后申报信息可能经常改变因此对房产证，不动产证,土地证,建设工程规划许可证,建设用地规划许可证,建设工程施工许可证,商品房预售许可证,经济规划指标 -->

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
                            class="form-control search-select select2 district" onchange="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this)">
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
            <div class="x-valid">
                <label class="col-sm-1 control-label">房产证类型<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select name="type" class="form-control search-select select2 type"
                            required="required"
                            onchange="commonDeclareApplyModel.realEstateCert.CertNameSplicing(this)">
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
                <label class="col-sm-1 control-label">共有情况<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select required="required" name="publicSituation"
                            class="form-control search-select select2 publicSituation">
                    </select>
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
                <label class="col-sm-1 control-label">街道号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="街道号" name="streetNumber" class="form-control"
                           required="required" onblur="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">附号</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="附号(数字)" name="attachedNumber" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true' onblur="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">栋号</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="栋号(数字)" name="buildingNumber" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true' onblur="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this)">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">单元</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="单元(数字)" name="unit" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true' onblur="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼层</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="楼层(数字)" name="floor" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true' onblur="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">房号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="房号(数字)" name="roomNumber" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           required="required" onblur="commonDeclareApplyModel.realEstateCert.beLocatedSplicing(this)">
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    登记时间<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input placeholder="登记时间"
                           name="registrationTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋性质<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="房屋性质" name="nature" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">规划用途</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="规划用途" name="planningUse" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">总层数<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="总层数(数字)" name="floorCount" class="form-control"
                           required="required" data-rule-maxlength="100"
                           data-rule-number='true'>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">证载面积</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="证载面积(数字)" name="evidenceArea" class="form-control"
                           data-rule-maxlength="100"
                           data-rule-number='true'>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">套内面积<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="套内面积(数字)" name="innerArea" class="form-control"
                           required="required" data-rule-maxlength="100"
                           data-rule-number='true'>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">其它</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="其它" name="other" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地证号<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="土地证号" name="landNumber" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地取得方式</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="土地取得方式" name="landAcquisition" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地使用年限起<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input placeholder="土地使用年限起"
                           name="useStartDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地使用年限止<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input placeholder="土地使用年限止"
                           name="useEndDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">公摊面积</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="公摊面积(数字)" name="publicArea" class="form-control"
                           data-rule-maxlength="100"
                           data-rule-number='true'>
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
            <div class="x-valid">
                <label class="col-sm-1 control-label">登记机关<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="登记机关" name="registrationAuthority" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    登记日期<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input placeholder="登记日期"
                           name="registrationDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">地号</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="地号" name="landNumber" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">图号</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="图号" name="graphNumber" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    用途
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
                <label class="col-sm-1 control-label">取得价格</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="取得价格" name="acquisitionPrice" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    使用权类型
                </label>
                <div class="col-sm-3">
                    <select required="required" name="useRightType"
                            class="form-control search-select select2 useRightType">
                        <option value="请选择">请选择</option>
                        <option value="划拨" name="useRightType">划拨</option>
                        <option value="出证" name="useRightType">出证</option>
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
                <label class="col-sm-1 control-label">独用面积</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="独用面积(数字)" name="acreage" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'>
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
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">记事<span
                        class="symbol required"></span></label>
                <div class="col-sm-11">
                                            <textarea class="form-control" name="memo" required="required">
                                            </textarea>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">不动产单元号<span
                        class="symbol required"></span></label>
                <div class="col-sm-11">
                                            <textarea class="form-control" name="realEstateUnitNumber"
                                                      required="required">
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
                <label class="col-sm-1 control-label">房产权证号<span class="symbol required"></span></label>
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
        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

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
                <label class="col-sm-1 control-label">建筑面积<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="建筑面积(数字)" name="floorArea" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           required="required">
                </div>
            </div>
        </div>

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
                <label class="col-sm-1 control-label">街道号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="街道号" name="streetNumber" class="form-control"
                           required="required" onblur="commonDeclareApplyModel.house.beLocatedSplicing(this)">
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
                <label class="col-sm-1 control-label">
                    登记时间<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input placeholder="登记时间"
                           name="registrationTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋性质</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="房屋性质" name="nature" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">规划用途<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="规划用途" name="planningUse" class="form-control"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">总层数<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="总层数(数字)" name="floorCount" class="form-control"
                           required="required" data-rule-maxlength="100"
                           data-rule-number='true'>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">证载面积</label>
                <div class="col-sm-3">
                    <input type="text"
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
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">其它</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="其它" name="other" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地证号<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="土地证号" name="landNumber" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地取得方式</label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="土地取得方式" name="landAcquisition" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地使用年限起<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input placeholder="土地使用年限起"
                           name="useStartDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地使用年限止<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input placeholder="土地使用年限止"
                           name="useEndDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">公摊面积<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="公摊面积(数字)" name="publicArea" class="form-control"
                           required="required" data-rule-maxlength="100"
                           data-rule-number='true'>
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
            <div class="x-valid">
                <label class="col-sm-1 control-label">登记机关<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="登记机关" name="registrationAuthority" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    登记日期<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input placeholder="登记日期"
                           name="registrationDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
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
                    所在地
                </label>
                <div class="col-sm-3">
                    <input type="text" name="location" required="required" class="form-control"
                           onblur="commonDeclareApplyModel.land.landCertNameSplicing(this)"
                           placeholder="所在地">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地使用权人
                </label>
                <div class="col-sm-3">
                    <input type="text" name="ownership" required="required" class="form-control"
                           placeholder="土地使用权人">
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
                    类型
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
                    年份
                </label>
                <div class="col-sm-3">
                    <input type="text" required="required" data-rule-maxlength="100"
                           onblur="commonDeclareApplyModel.land.landCertNameSplicing(this)"
                           data-rule-number='true' name="year" class="form-control"
                           placeholder="年份(数字如:2018)">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    编号
                </label>
                <div class="col-sm-3">
                    <input type="text" required="required" name="number" class="form-control"
                           onblur="commonDeclareApplyModel.land.landCertNameSplicing(this)"
                           placeholder="编号">
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
                <label class="col-sm-1 control-label">栋号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="栋号(数字)" name="buildingNumber" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           onblur="commonDeclareApplyModel.land.landBeLocatedSplicing(this)"
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">单元<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="单元(数字)" name="unit" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           required="required" onblur="commonDeclareApplyModel.land.landBeLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼层<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="楼层(数字)" name="floor" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           required="required" onblur="commonDeclareApplyModel.land.landBeLocatedSplicing(this)">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">房号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="房号(数字)" name="roomNumber" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           required="required" onblur="commonDeclareApplyModel.land.landBeLocatedSplicing(this)">
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">地号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="地号" name="landNumber" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">图号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="图号" name="graphNumber" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    用途
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
                <label class="col-sm-1 control-label">取得价格<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="取得价格" name="acquisitionPrice" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    使用权类型
                </label>
                <div class="col-sm-3">
                    <select required="required" name="useRightType"
                            class="form-control search-select select2 useRightType">
                        <option value="请选择">请选择</option>
                        <option value="划拨" name="useRightType">划拨</option>
                        <option value="出证" name="useRightType">出证</option>
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
                <label class="col-sm-1 control-label">独用面积<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="独用面积(数字)" name="acreage" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">分摊面积<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="分摊面积(数字)" name="apportionmentArea" class="form-control"
                           data-rule-maxlength="100" data-rule-number='true'
                           required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">记事<span
                        class="symbol required"></span></label>
                <div class="col-sm-11">
                    <textarea class="form-control" name="memo" required="required"></textarea>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">登记机关<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="登记机关" name="registrationAuthority" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    登记日期<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input placeholder="登记日期"
                           name="registrationDate" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime" required="required">
                </div>
            </div>
        </div>
    </div>
</script>

</body>
</html>
