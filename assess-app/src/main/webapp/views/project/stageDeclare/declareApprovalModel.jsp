<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<script type="text/javascript">
    var commonDeclareApprovalModel = {};

    /**
     * 空字符串检测
     * @param item
     * @returns {boolean}
     */
    commonDeclareApprovalModel.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    commonDeclareApprovalModel.isNotBlankObject = function (obj) {
        for (var key in obj) {
            return true;
        }
        return false
    };

    commonDeclareApprovalModel.config = {
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
        },
        declareRealtyCheckList: {
            id: "declareRealtyCheckListCommon",
            handleId: "declareRealtyCheckListModelHandleCommon",
            name: "不动产清单"
        }
    };

    /**
     * 不动产
     * @type {{getHtml: commonDeclareApprovalModel.realEstateCert.getHtml}}
     */
    commonDeclareApprovalModel.realEstateCert = {
        getHtml: function () {
            return $("#" + commonDeclareApprovalModel.config.realEstateCert.id).html();
        },
        getHtml2: function () {
            return $("#" + commonDeclareApprovalModel.config.realEstateCert.id2).html();
        }
    };

    /**
     * 房产证
     * @type {{getHtml: commonDeclareApprovalModel.house.getHtml}}
     */
    commonDeclareApprovalModel.house = {
        getHtml: function () {
            return $("#" + commonDeclareApprovalModel.config.house.id).html();
        }
    };

    /**
     * 土地证
     * @type {{getHtml: commonDeclareApprovalModel.land.getHtml}}
     */
    commonDeclareApprovalModel.land = {
        getHtml: function () {
            return $("#" + commonDeclareApprovalModel.config.land.id).html();
        },
        getHtml2: function () {
            return $("#" + commonDeclareApprovalModel.config.land.id2).html();
        }
    };

    /**
     * 土建
     * @type {{getHtml: commonDeclareApprovalModel.civilEngineering.getHtml}}
     */
    commonDeclareApprovalModel.civilEngineering = {
        getHtml: function () {
            return $("#" + commonDeclareApprovalModel.config.civilEngineering.id).html();
        }
    };

    /**
     * 设备安装
     * @type {{getHtml: commonDeclareApprovalModel.equipmentInstallation.getHtml}}
     */
    commonDeclareApprovalModel.equipmentInstallation = {
        getHtml: function () {
            return $("#" + commonDeclareApprovalModel.config.equipmentInstallation.id).html();
        }
    };

    /**
     * 建筑工程施工许可证
     * @type {{getHtml: commonDeclareApprovalModel.buildingConstructionPermit.getHtml}}
     */
    commonDeclareApprovalModel.buildingConstructionPermit = {
        getHtml: function () {
            return $("#" + commonDeclareApprovalModel.config.buildingConstructionPermit.id).html();
        }
    };

    /**
     * 商品房预售许可证
     * @type {{getHtml: commonDeclareApprovalModel.preSalePermit.getHtml}}
     */
    commonDeclareApprovalModel.preSalePermit = {
        getHtml: function () {
            return $("#" + commonDeclareApprovalModel.config.preSalePermit.id).html();
        }
    };

    /**
     * 建设用地规划许可证
     * @type {{getHtml: commonDeclareApprovalModel.landUsePermit.getHtml}}
     */
    commonDeclareApprovalModel.landUsePermit = {
        getHtml: function () {
            return $("#" + commonDeclareApprovalModel.config.landUsePermit.id).html();
        }
    };

    /**
     * 建设工程规划许可证
     * @type {{getHtml: commonDeclareApprovalModel.buildingPermit.getHtml}}
     */
    commonDeclareApprovalModel.buildingPermit = {
        getHtml: function () {
            return $("#" + commonDeclareApprovalModel.config.buildingPermit.id).html();
        }
    };

    //不动产清单
    commonDeclareApprovalModel.declareRealtyCheckList = {
        getHtml: function () {
            return $("#" + commonDeclareApprovalModel.config.declareRealtyCheckList.id).html();
        }
    }
</script>

<script id="declareModelHouse" type="text/html" data-title="房产证label">
    <div id="declareModelHandleHouse">
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        省
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label class="form-control input-full" name="provinceName"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        市
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label class="form-control input-full" name="cityName"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        县
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label class="form-control input-full" name="districtName"></label>
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
                        <label type="text" placeholder="房产权证号" name="certName" class="form-control input-full"></label>
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
                        <label
                                placeholder="所在地" name="location" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        编号
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label name="number" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        类型
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label
                                name="typeName" class="form-control input-full"></label>
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
                        <label
                                placeholder="房屋所有权人" name="ownership" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        建筑面积
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="建筑面积(数字)" name="floorArea" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        共有情况
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label
                                name="publicSituationName" class="form-control input-full"></label>
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
                        <label type="text"
                               placeholder="共有情况说明" name="publicSituationRemark"
                               class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        登记日期
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="登记日期" name="registrationDate" data-date-format="yyyy-mm-dd"
                               class="form-control input-full " readonly="readonly">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        丘地号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text" placeholder="丘地号" name="groundNum" class="form-control input-full"></label>
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
                        <label type="text"
                               placeholder="街道号" name="streetNumber" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        附号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="附号(数字)" name="attachedNumber" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        栋号
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="栋号" name="buildingNumber" class="form-control input-full"></label>
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
                        <label type="text"
                               placeholder="单元(数字)" name="unit" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        楼层
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="楼层" name="floor" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房号
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text" placeholder="房号" name="roomNumber" class="form-control input-full"></label>
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
                        <label type="text" placeholder="坐落" name="beLocated" class="form-control input-full"></label>
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
                        <label type="text"
                               placeholder="房屋结构" name="housingStructure" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋用途类型<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <div class="input-group">
                            <label name="certUse" class="form-control input-full"></label>
                        </div>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋用途类别<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <div class="input-group">
                            <label name="certUseCategory" class="form-control input-full"></label>
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
                        <label type="text"
                               placeholder="总层数" name="natureName" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        证载面积<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="证载面积(数字)" name="evidenceArea" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        套内面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="套内面积(数字)" name="innerArea" class="form-control input-full"></label>
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
                        <label type="text"
                               placeholder="公摊面积(数字)" name="publicArea" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        总层数
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="总层数" name="floorCount" class="form-control input-full"></label>
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
                        <label type="text"
                               placeholder="登记机关" name="landAcquisitionName" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        登记机关<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="登记机关" name="registrationAuthority" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        分摊面积<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text" placeholder="分摊面积(数字)" name="apportionmentArea"
                               class="form-control input-full"></label>
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
                        <input placeholder="土地使用年限起" readonly="readonly"
                               name="useStartDate" data-date-format="yyyy-mm-dd"
                               class="form-control input-full ">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地使用年限止<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="土地使用年限止" readonly="readonly"
                               name="useEndDate" data-date-format="yyyy-mm-dd"
                               class="form-control input-full ">
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
                        <label placeholder="其它" name="other" class="form-control input-full"></label>
                    </div>
                </div>
            </div>
        </div>

    </div>
</script>

<script id="declareModelLand" type="text/html" data-title="土地证label">
    <div id="declareModelHandleLand">
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        省
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label class="form-control input-full" name="provinceName"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        市
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label class="form-control input-full" name="cityName"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        县
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label class="form-control input-full" name="districtName"></label>
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
                        <label type="text" name="location" class="form-control input-full"
                               placeholder="所在地"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        类型
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text" name="landRightTypeName" class="form-control input-full"
                               placeholder="类型"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        年份
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text" required="required" data-rule-maxlength="100" name="year"
                               class="form-control input-full"
                               placeholder="年份(数字如:2018)"></label>
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
                        <label type="text" name="number" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地使用权人<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text" name="ownership" required="required" class="form-control input-full"
                               placeholder="土地使用权人"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        共有情况
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text" name="publicSituationName" required="required"
                               class="form-control input-full"
                               placeholder="共有情况"></label>
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
                        <label type="text" name="landCertName" class="form-control input-full" placeholder="土地权证号"
                               required="required"></label>
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
                        <label type="text"
                               placeholder="共有情况说明" name="publicSituationRemark"
                               class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        登记日期
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="登记日期" name="registrationDate" data-date-format="yyyy-mm-dd"
                               class="form-control input-full " readonly="readonly">
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
                        <label type="text"
                               placeholder="街道号" name="streetNumber" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        附号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="附号(数字)" name="attachedNumber" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        栋号
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="栋号" name="buildingNumber" class="form-control input-full"></label>
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
                        <label type="text"
                               placeholder="单元(数字)" name="unit" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        楼层
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="楼层" name="floor" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房号
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text" placeholder="房号" name="roomNumber" class="form-control input-full"></label>
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
                        <label type="text" placeholder="坐落" name="beLocated" class="form-control input-full"></label>
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
                        <label type="text" placeholder="地号" name="landNumber" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        图号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text" placeholder="图号" name="graphNumber" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地用途类型<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text" name="certUse" class="form-control input-full"></label>
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
                        <label type="text" name="certUseCategoryName"
                               class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        <label class="col-sm-2 col-form-label">取得价格</label>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="取得价格" name="acquisitionPrice" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        权利性质<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="取得价格" name="landRightNatureName" class="form-control input-full"></label>
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
                               class="form-control input-full " readonly="readonly">
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
                        <label type="text"
                               placeholder="使用权面积(数字)" name="useRightArea" class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        分摊面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text" placeholder="分摊面积(数字)" name="apportionmentArea"
                               class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        登记机关<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text" placeholder="登记机关" name="registrationAuthority"
                               class="form-control input-full"></label>
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
                        <label class="form-control input-full" name="memo"></label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>

<script id="declareModelRealEstateCert" type="text/html" data-title="不动产证label">
    <div id="declareModelHandleRealEstateCert">
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        省
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label class="form-control input-full" name="provinceName"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        市
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label class="form-control input-full" name="cityName"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        县
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label class="form-control input-full" name="districtName"></label>
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
                        <label type="text" placeholder="不动产权证号" name="certName" class="form-control input-full"></label>
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
                        <label type="text"
                               placeholder="所在地" name="location" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        编号
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label name="number" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        年份
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label data-rule-number='true' name="year" class="form-control input-full"></label>
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
                        <label type="text"
                               placeholder="不动产单元号" name="realEstateUnitNumber" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋所有权人
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="房屋所有权人" name="ownership" class="form-control input-full"
                               required="required"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        共有情况
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="publicSituationName" class="form-control input-full"></label>
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
                        <label  name="publicSituationRemark" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        登记日期
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="登记日期"
                               name="registrationTime" data-date-format="yyyy-mm-dd"
                               class="form-control input-full" readonly="readonly">
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
                        <label type="text" required
                               placeholder="街道号" name="streetNumber" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        附号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="附号(数字)" name="attachedNumber" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        栋号
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text" required
                               placeholder="栋号" name="buildingNumber" class="form-control input-full"></label>
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
                        <label type="text"
                               placeholder="单元(数字)" name="unit" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        楼层
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="楼层" name="floor" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房号
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text" placeholder="房号" name="roomNumber" class="form-control input-full"></label>
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
                        <label type="text" placeholder="坐落" name="beLocated" class="form-control input-full"
                               required="required"></label>
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
                        <label type="text"
                               placeholder="房屋结构" name="housingStructure" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋用途类型<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               name="houseCertUse" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋用途类别<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               name="houseCertUseCategory" class="form-control input-full"></label>
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
                        <label type="text"
                                name="natureName" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        证载面积<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text" required
                               placeholder="证载面积(数字)" name="evidenceArea" class="form-control input-full"
                               data-rule-maxlength="100"
                               data-rule-number='true'></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        套内面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="套内面积(数字)" name="innerArea" class="form-control input-full"
                               data-rule-maxlength="100"
                               data-rule-number='true'></label>
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
                        <label type="text"
                               placeholder="公摊面积(数字)" name="apportionmentArea" class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        其它(房屋)
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="其它" name="other" class="form-control input-full"></label>
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
                        <label type="text"
                               placeholder="附记其它" name="otherNote" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        总层数
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text" placeholder="总层数" name="floorCount" class="form-control input-full"></label>
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
                        <label type="text" placeholder="土地取得方式" name="acquisitionType" class="form-control input-full"
                               data-rule-maxlength="100"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地取得价格
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text" placeholder="土地取得价格" name="acquisitionPrice" class="form-control input-full"
                               data-rule-maxlength="100"
                               data-rule-number='true'></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地用途类型<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text" name="landCertUse"
                               class="form-control input-full"></label>
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
                        <label type="text" name="landCertUseCategory"
                               class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地权利性质
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label name="landRightNatureName" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        共用宗地面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="共用宗地面积(数字)" name="useRightArea" class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'></label>
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
                        <label name="landRightTypeName" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地分摊面积<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text" required
                               placeholder="土地分摊面积(数字)" name="landApportionArea" class="form-control input-full"
                               data-rule-maxlength="100"
                               data-rule-number='true'></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地使用年限起
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="土地使用年限起"
                               name="useStartDate" data-date-format="yyyy-mm-dd"
                               class="form-control input-full " readonly="readonly">
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
                        <input placeholder="土地使用年限止"
                               name="useEndDate" data-date-format="yyyy-mm-dd"
                               class="form-control input-full " readonly="readonly">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        登记机关
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="登记机关" name="registrationAuthority" class="form-control input-full"></label>
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
                        <label placeholder="记事" name="memo" class="form-control input-full"></label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>

<script id="declareModelCivilEngineeringCommon" type="text/html" data-title="土建">
    <div id="declareModelHandleCivilEngineeringCommon">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    省
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="provinceName"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    市
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="cityName"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    县(区)
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="districtName"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">占有单位</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="occupancyUnit"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">项目名称</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="name"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">坐落</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="beLocated"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">结构</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="structure"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label" title="评估面积">建筑面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="buildArea"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">开工日期</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="startDate"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">预期完成日期</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" name="expectedCompletionDate" class="form-control" readonly="readonly">
                    <%--<label class="form-control" name="expectedCompletionDate"></label>--%>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">形象进度</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="speedProgress"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">付款比例</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="paymentRatio"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">账面价值</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="bookValue"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">帐面净值</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="bookNetValue"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">申报人</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="declarer"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">申报日期</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <%--<label class="form-control" name="declarationDate"></label>--%>
                    <input type="text" name="declarationDate" class="form-control" readonly="readonly">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">备注</label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <label class="form-control" name="remark"></label>
                </div>
            </div>
        </div>
    </div>
</script>

<script id="declareModelequipmentInstallationCommon" type="text/html" data-title="设备安装">
    <div id="declareModelHandleEquipmentInstallationCommon">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    省
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="provinceName"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    市
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="cityName"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    县(区)
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="districtName"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">占有单位</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="occupancyUnit"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">项目名称</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="name"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">坐落</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="beLocated"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">规格型号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="specificationModel"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">生产厂家</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="manufacturer"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">计量单位</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="measurementUnit"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">数量</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="number"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">开工日期</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" name="startDate" class="form-control" readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">预期完成日期</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" name="expectedCompletionDate" class="form-control" readonly="readonly">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">账面设备费</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="bookEquipmentFee"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">账面资金成本</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="bookCapitalCost"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">账面安装费</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="bookInstallationFee"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">其它</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="other"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">申报人</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="declarer"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">申报日期</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" name="declarationDate" class="form-control" readonly="readonly">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">备注</label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <label class="form-control" name="remark"></label>
                </div>
            </div>
        </div>
    </div>
</script>

<script id="buildingConstructionPermitCommon" type="text/html" data-title="建筑工程施工许可证">
    <div id="declareModelHandleBuildingConstructionPermitCommon">
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        证书编号<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="证书编号" name="certificateNumber" class="form-control input-full"
                               required="required"></label>
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
                        <label type="text"
                               placeholder="发证机关" name="issuingOrgan" class="form-control input-full"
                               required="required"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        日期<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="日期"
                               name="date" data-date-format="yyyy-mm-dd"
                               class="form-control input-full " required="required" readonly="readonly">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        建设单位（个人）<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="建设单位（个人）" name="buildUnit" class="form-control input-full"
                               required="required"></label>
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
                        <label type="text"
                               placeholder="建设项目名称" name="name" class="form-control input-full"
                               required="required"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        建设地址<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="建设地址" name="buildAddress" class="form-control input-full"
                               required="required"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        建设规模<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="建设规模" name="scaleConstruction" class="form-control input-full"
                               required="required"></label>
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
                        <label type="text"
                               placeholder="勘察单位" name="reconnaissanceUnit" class="form-control input-full"
                               required="required"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        设计单位<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="设计单位" name="designUnit" class="form-control input-full"
                               required="required"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        施工单位<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="施工单位" name="constructionUnit" class="form-control input-full"
                               required="required"></label>
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
                        <label type="text"
                               placeholder="监理单位" name="constructionControlUnit"
                               class="form-control input-full"
                               required="required"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        勘察单位项目负责人<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="勘察单位项目负责人" name="reconnaissanceUnitPerson"
                               class="form-control input-full"
                               required="required"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        设计单位项目负责人<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="设计单位项目负责人" name="designUnitPerson" class="form-control input-full"
                               required="required"></label>
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
                        <label type="text"
                               placeholder="施工单位项目负责人" name="constructionUnitPerson"
                               class="form-control input-full"
                               required="required"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        总监理工程师<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="总监理工程师" name="chiefEngineerConstructionInspection"
                               class="form-control input-full"
                               required="required"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        合同工期<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="合同工期"
                               name="contractPeriod" data-date-format="yyyy-mm-dd"
                               class="form-control input-full " readonly="readonly">
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
                        <label name="remark" placeholder="备注" class="form-control input-full"></label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>

<script id="preSalePermitCommon" type="text/html" data-title="商品房预售许可证">
    <div id="declareModelHandlePreSalePermitCommon">
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        证书编号<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="证书编号" name="certificateNumber" class="form-control input-full"
                               required="required"></label>
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
                        <label type="text"
                               placeholder="售房单位" name="salesUnit" class="form-control input-full"
                               required="required"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        法定代表人<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="法定代表人" name="legalRepresentative" class="form-control input-full"
                               required="required"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        项目坐落<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="项目坐落" name="beLocated" class="form-control input-full"
                               required="required"></label>
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
                        <label type="text"
                               placeholder="项目名称" name="name" class="form-control input-full"
                               required="required"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        预售范围<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="预售范围" name="preSaleScope" class="form-control input-full"
                               required="required"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        预售面积<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="预售面积(数字)" data-rule-maxlength="100"
                               data-rule-number='true' name="preSaleArea" class="form-control input-full"
                               required="required"></label>
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
                        <label type="text"
                               placeholder="房屋用途" name="housingUse" class="form-control input-full"
                               required="required"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        建筑结构<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="建筑结构" name="buildingStructure" class="form-control input-full"
                               required="required"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        预售款监管信息<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="预售款监管信息" name="preSaleSupervisionInformation"
                               class="form-control input-full"
                               required="required"></label>
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
                        <label type="text"
                               placeholder="发证机关" name="issuingOrgan"
                               class="form-control input-full"
                               required="required"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        日期<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="日期"
                               name="date" data-date-format="yyyy-mm-dd"
                               class="form-control input-full " readonly="readonly">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        在建工程抵押情况<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="在建工程抵押情况" name="mortgageSituation" class="form-control input-full"
                               required="required"></label>
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
                        <label name="remark" placeholder="备注" class="form-control input-full"></label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>

<script id="landUsePermitCommon" type="text/html" data-title="建设用地规划许可证">
    <div id="declareModelHandleLandUsePermitCommon">
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        证书编号<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="证书编号" name="certificateNumber" class="form-control input-full"
                               required="required"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        发证机关<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="发证机关" name="issuingOrgan" class="form-control input-full"
                               required="required"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        日期<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="日期"
                               name="date" data-date-format="yyyy-mm-dd"
                               class="form-control input-full " readonly="readonly">
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
                        <label type="text"
                               placeholder="用地单位" name="unit" class="form-control input-full"
                               required="required"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        用地项目名称<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="用地项目名称" name="name" class="form-control input-full"
                               required="required"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        用地位置<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="用地位置" name="location" class="form-control input-full"
                               required="required"></label>
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
                        <label type="text"
                               placeholder="用地性质" name="nature" class="form-control input-full"
                               required="required"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        用地面积<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="用地面积" data-rule-maxlength="100" data-rule-number='true'
                               name="area" class="form-control input-full"
                               required="required"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        建设规模<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="建设规模" name="scaleConstruction" class="form-control input-full"
                               required="required"></label>
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
                        <label name="remark" placeholder="备注" class="form-control input-full"></label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>

<script id="buildingPermitCommon" type="text/html" data-title="建设工程规划许可证">
    <div id="declareModelHandleBuildingPermitCommon">
        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                        证书编号<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                        <label type="text"
                               placeholder="证书编号" name="certificateNumber" class="form-control input-full"
                               required="required"></label>
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
                        <label type="text"
                               placeholder="发证机关" name="issuingOrgan" class="form-control input-full"
                               required="required"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        日期<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <input placeholder="日期"
                               name="date" data-date-format="yyyy-mm-dd"
                               class="form-control input-full " readonly="readonly">
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        建设单位（个人）<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="建设单位（个人）" name="unit" class="form-control input-full"
                               required="required"></label>
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
                        <label type="text"
                               placeholder="建设项目名称" name="name" class="form-control input-full"
                               required="required"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        建设位置
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="建设位置" name="location" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        建设规模
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label type="text"
                               placeholder="建设规模" name="scaleConstruction" class="form-control input-full"></label>
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
                        <label name="remark" placeholder="备注" class="form-control input-full"></label>
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
                        <label  name="realEstateUnitNumber" class="form-control input-full "></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        所在区
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="district" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        门牌号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label name="houseNumber" class="form-control input-full"></label>
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
                        <label  name="streetNumber" required="required" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        附号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="attachedNumber" class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        栋号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label name="buildingNumber" class="form-control input-full" ></label>
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
                        <label  name="unit" class="form-control input-full"
                               data-rule-maxlength="100" data-rule-number='true'></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        楼层
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="floor" class="form-control input-full" data-rule-maxlength="100" data-rule-number='true'></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="roomNumber" class="form-control input-full"></label>
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
                        <label  name="certUse" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋建筑面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label name="floorArea" class="form-control input-full" data-rule-maxlength="100" data-rule-number='true'></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        分摊面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="apportionmentArea" class="form-control input-full" data-rule-maxlength="100" data-rule-number='true'></label>
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
                        <label  name="housingStructure" class="form-control input-full"></label>
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
                        <label class="form-control input-full" name="provinceName"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        市
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label class="form-control input-full" name="cityName"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        县
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label class="form-control input-full" name="districtName"></label>
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
                        <label  name="certName" class="form-control input-full"></label>
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
                        <label  name="location" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        编号
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="number" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        不动产单元号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="realEstateUnitNumber" class="form-control input-full"></label>
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
                        <label  name="ownership" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        登记日期
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="registrationTime" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        共有情况
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="publicSituationName" class="form-control input-full"></label>
                    </div>
                </div>
            </div>
        </div>


        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <%--<div class="row form-group">--%>
            <%--<div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">--%>
                <%--<div class="form-inline x-valid">--%>
                    <%--<label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">--%>
                        <%--街道号--%>
                        <%--<span class="symbol required"></span>--%>
                    <%--</label>--%>
                    <%--<div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">--%>
                        <%--<label--%>
                                <%--name="streetNumber" class="form-control input-full"></label>--%>
                    <%--</div>--%>
                    <%--<label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">--%>
                        <%--附号--%>
                    <%--</label>--%>
                    <%--<div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">--%>
                        <%--<label--%>
                               <%--name="attachedNumber" class="form-control input-full"></label>--%>
                    <%--</div>--%>
                    <%--<label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">--%>
                        <%--栋号--%>
                        <%--<span class="symbol required"></span>--%>
                    <%--</label>--%>
                    <%--<div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">--%>
                        <%--<label--%>
                                <%--name="buildingNumber" class="form-control input-full"></label>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>



        <%--<div class="row form-group">--%>
            <%--<div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">--%>
                <%--<div class="form-inline x-valid">--%>
                    <%--<label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">--%>
                        <%--单元--%>
                    <%--</label>--%>
                    <%--<div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">--%>
                        <%--<label  name="unit" class="form-control input-full"></label>--%>
                    <%--</div>--%>
                    <%--<label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">--%>
                        <%--楼层--%>
                    <%--</label>--%>
                    <%--<div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">--%>
                        <%--<label  name="floor" class="form-control input-full"></label>--%>
                    <%--</div>--%>
                    <%--<label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">--%>
                        <%--房号--%>
                        <%--<span class="symbol required"></span>--%>
                    <%--</label>--%>
                    <%--<div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">--%>
                        <%--<label  name="roomNumber" class="form-control input-full"></label>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>



        <div class="row form-group">
            <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                <div class="form-inline x-valid">
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                        坐落<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                        <label  name="beLocated" class="form-control input-full"></label>
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
                        <label  name="landCertUse" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        权利性质
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="landRightNatureName" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地用途类别<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="landCertUseCategory" class="form-control input-full"></label>
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
                        <label  name="landRightTypeName" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地使用年限起
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="useStartDate" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地使用年限止<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="useEndDate" class="form-control input-full"></label>
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
                        <label  name="useRightArea" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        取得价格
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="acquisitionPrice" class="form-control input-full"></label>
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
                        <label  name="approvalName" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        批文机关
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="approvalMechanism" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        批文时间
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="approvalTime" class="form-control input-full"></label>
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
                        <label name="memo" class="form-control input-full"></label>
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
                        房屋用途类型<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label name="houseCertUse" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋用途类别<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label name="houseCertUseCategory" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房屋结构
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label name="housingStructure" class="form-control input-full"></label>
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
                        <label  name="natureName" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        证载面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="evidenceArea" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        套内面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="innerArea" class="form-control input-full"></label>
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
                        <label name="apportionmentArea" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        登记机关
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="registrationAuthority" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        其它
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="other" class="form-control input-full"></label>
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
                        <label  name="otherNote" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        总层数
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="floorCount" class="form-control input-full"></label>
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
                        <label class="form-control input-full" name="provinceName"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        市
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label class="form-control input-full" name="cityName"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        县
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label class="form-control input-full" name="districtName"></label>
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
                        <label  name="landCertName" class="form-control input-full"></label>
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
                        <label  name="location" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        类型
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="landRightTypeName" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        年份
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="year" class="form-control input-full"></label>
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
                        <label  name="number" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        共有情况
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="publicSituationName" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        登记日期
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="registrationDate" class="form-control input-full"></label>
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
                        <label  name="streetNumber" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        附号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="attachedNumber" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        栋号
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="buildingNumber" class="form-control input-full"></label>
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
                        <label  name="unit" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        楼层
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="floor" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        房号
                        <span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="roomNumber" class="form-control input-full"></label>
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
                        <label  name="beLocated" class="form-control input-full"></label>
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
                        <label  name="approvalName" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        批文机关
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="approvalMechanism" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        批文时间
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="approvalTime" class="form-control input-full"></label>
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
                        <label  name="landNumber" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        图号
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="graphNumber" class="form-control input-full"></label>
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
                        <label  name="certUse" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        土地用途类别<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="certUseCategory" class="form-control input-full"></label>
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
                        <label  name="landRightNatureName" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        终止日期<span class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="terminationDate" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        取得价格
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="acquisitionPrice" class="form-control input-full"></label>
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
                        <label  name="useRightArea" class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        分摊面积
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="apportionmentArea"
                               class="form-control input-full"></label>
                    </div>
                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                        登记机关<span
                            class="symbol required"></span>
                    </label>
                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                        <label  name="registrationAuthority"
                               class="form-control input-full"
                               required="required"></label>
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
                        <label class="form-control input-full" name="memo"></label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>

</body>
</html>
