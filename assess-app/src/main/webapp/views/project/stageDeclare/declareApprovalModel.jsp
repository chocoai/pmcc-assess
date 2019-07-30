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
        declareEconomicIndicators:{
            idA: "declareEconomicIndicatorsModelCommonA",
            idB: "declareEconomicIndicatorsModelCommonB",
            name: "经济指标",
            tree:"declareEconomicIndicatorsModelCommonBTree",
            handleId:"declareEconomicIndicatorsModelCommonHandleA"
        }
    };

    commonDeclareApprovalModel.declareEconomicIndicators = {
        getHtmlA:function () {
            var html = $("#"+commonDeclareApprovalModel.config.declareEconomicIndicators.idA).html() ;
            return html ;
        },
        getHtmlB:function () {
            var html = $("#"+commonDeclareApprovalModel.config.declareEconomicIndicators.idB).html() ;
            return html ;
        } ,
        treeGirdParse:function (target) {
            if (target){
                target.find('#' + commonDeclareApprovalModel.config.declareEconomicIndicators.tree).treegrid();
            }else {
                $('#' + commonDeclareApprovalModel.config.declareEconomicIndicators.tree).treegrid();
            }
        },
        initFormContent:function (arrData,frm) {
            var table = frm.find("table");
            var tbody = table.find('tbody');
            tbody.find("tr").each(function (i, tr) {
                var dataKey = $(tr).attr('data-key');
                var role = $(tr).attr('data-role');
                var text = $(tr).find("td").first().text();
                $.each(arrData, function (i, item) {
                    if (item.customKey == dataKey && item.name == text) {
                        $(tr).find("td").find("input[name='planIndex']").first().val(item.planIndex);
                        $(tr).find("td").find("input[name='remark']").first().val(item.remark);
                        $(tr).find("td").find("input[name='salabilityNumber']").first().val(item.salabilityNumber);
                        $(tr).find("td").find("input[name='assessSalabilityNumber']").first().val(item.assessSalabilityNumber);
                        if ($(tr).find("td").find("input[name='id']").size() != 0) {
                            $(tr).find("td").find("input[name='id']").first().val(item.id);
                        }
                        if (commonDeclareApprovalModel.isNotBlank(item.childData)) {
                            var childData = JSON.parse(item.childData);
                            var template = item.customKey + 'Template';
                            if (commonDeclareApprovalModel.isNotBlankObject(childData)) {
                                $.each(childData, function (i, cdData) {
                                    var element = $("#" + template).html();
                                    element = element.replace('{name}', cdData.name);
                                    element = element.replace('{salabilityNumber}', cdData.salabilityNumber);
                                    element = element.replace('{assessSalabilityNumber}', cdData.assessSalabilityNumber);
                                    element = element.replace('{planIndex}', cdData.planIndex);
                                    element = element.replace('{remark}', cdData.remark);
                                    //确定写入位置
                                    var childs = $(tr).treegrid('getChildNodes');
                                    if (childs.length <= 0) {
                                        $(tr).after(element);
                                    } else {
                                        //如果最后一个子项下还有子项则在子项的子项后添加元素
                                        var subChilds = $(childs.get(childs.length - 1)).treegrid('getChildNodes');
                                        if (subChilds.length <= 0) {
                                            $(childs.get(childs.length - 1)).after(element);
                                        } else {
                                            $(subChilds.get(subChilds.length - 1)).after(element);
                                        }
                                    }
                                    commonDeclareApprovalModel.declareEconomicIndicators.treeGirdParse(frm);
                                });
                            }
                        }
                    }
                });
            });
        }
    } ;

    /**
     * 不动产
     * @type {{getHtml: commonDeclareApprovalModel.realEstateCert.getHtml}}
     */
    commonDeclareApprovalModel.realEstateCert = {
        getHtml: function () {
            return $("#" + commonDeclareApprovalModel.config.realEstateCert.id).html();
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
</script>

<script id="declareModelHouse" type="text/html" data-title="房产证label">
    <div id="declareModelHandleHouse">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">省
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="provinceName"></label>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">市</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="cityName"></label>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">县</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="districtName"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房产权证号</label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <label class="form-control" name="certName"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">所在地</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="location"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">编号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="number"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">类型</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="typeName"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋所有权人</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="ownership"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="floorArea"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">共有情况</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="publicSituationName"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    登记日期
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="registrationDate"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">丘地号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="groundNum"></label>
                </div>
            </div>
        </div>
        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋坐落</label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <label class="form-control" name="beLocated"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="streetNumber"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="attachedNumber"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">栋号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="buildingNumber"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">单元</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="unit"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼层</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="floor"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="roomNumber"></label>
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋结构</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="housingStructure"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋用途类型</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="planningUseName"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋用途类别</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="certUseCategoryName"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋性质</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="natureName"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">证载面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="evidenceArea"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">套内面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="innerArea"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">公摊面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="publicArea"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">总层数</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="floorCount"></label>
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地取得方式</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="landAcquisitionName"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">登记机关</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="registrationAuthority"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">分摊面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="apportionmentArea"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地使用年限起
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="useStartDate"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地使用年限止
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="useEndDate" id="useEndDate_d"></label>
                </div>
            </div>
        </div>


        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">其它</label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <label class="form-control" name="other"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                房产证
            </label>
            <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                <div id="_declareRealtyHouseCertFileId"></div>
            </div>
        </div>
    </div>
</script>

<script id="declareModelLand" type="text/html" data-title="土地证label">
    <div id="declareModelHandleLand">
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
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地权证号
                </label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <label class="form-control" name="landCertName"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    所在地
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="location"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    类型
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="landRightTypeName"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    年份
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="year"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    编号
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="number"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地使用权人
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="ownership"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">共有情况</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="publicSituationName"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                登记日期
            </label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <label class="form-control" name="registrationDate"></label>
            </div>
        </div>
        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋坐落</label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <label class="form-control" name="beLocated"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="streetNumber"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="attachedNumber"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">栋号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="buildingNumber"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">单元</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="unit"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼层</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="floor"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="roomNumber"></label>
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">地号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="landNumber"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">图号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="graphNumber"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地用途类型
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="purposeName"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地用途类别
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="certUseCategoryName"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">取得价格</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="acquisitionPrice"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    权利性质
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="landRightNatureName"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    终止日期
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="terminationDate" id="terminationDate_d"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">使用权面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="useRightArea"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">分摊面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="apportionmentArea"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">登记机关</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="registrationAuthority"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">记事</label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <label class="form-control" name="memo"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                土地证
            </label>
            <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                <div id="_sonDeclareRealtyLandCertFileId"></div>
            </div>
        </div>
    </div>
</script>

<script id="declareModelRealEstateCert" type="text/html" data-title="不动产证label">
    <div id="declareModelHandleRealEstateCert">
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
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">不动产权证号</label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <label class="form-control" name="certName"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">所在地</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="location"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">编号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="number"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">年份</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="year" class="form-control"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">不动产单元号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="realEstateUnitNumber" class="form-control"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋所有权人</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="ownership"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">共有情况</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="publicSituationName"></label>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    登记日期</span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <%--<label class="form-control" name="registrationTime"></label>--%>
                    <input type="text" name="registrationTimeFmt" class="form-control" readonly="readonly">
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋坐落</label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <label class="form-control" name="beLocated"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="streetNumber"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="attachedNumber"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">栋号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="buildingNumber"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">单元</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="unit"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼层</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="floor"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="roomNumber"></label>
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋用途类型</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="planningUseName"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    房屋用途类别<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="houseCertUseCategoryName"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋结构</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="housingStructure"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋性质</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="natureName"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="floorArea"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">证载面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="evidenceArea"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">套内面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="innerArea"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">分摊面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="apportionmentArea"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">登记机关</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="registrationAuthority"></label>
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
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附记其它</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="otherNote"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">总层数</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="floorCount"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">取得价格</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="acquisitionPrice"></label>
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地用途类型</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="purposeName"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地用途类别<span class="symbol required"></span>
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="landCertUseCategoryName"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">权利性质</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="useRightTypeName"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">权利类型</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="typeName"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">共用宗地面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="useRightArea"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地使用年限起
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <%--<label class="form-control" name="useStartDate"></label>--%>
                    <input type="text" name="useStartDateFmt" class="form-control" readonly="readonly">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地分摊面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control" name="landApportionArea"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    土地使用年限止
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <%--<label class="form-control" name="useEndDate"></label>--%>
                    <input type="text" name="useEndDateFmt" id="useEndDateFmt_d" class="form-control"
                           readonly="readonly">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">记事</label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <label class="form-control" name="memo"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                不动产证
            </label>
            <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                <div id="_declareRealtyRealEstateCertFileId"></div>
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
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">证书编号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="certificateNumber" class="form-control"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">发证机关</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="issuingOrgan" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">日期</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" name="date" class="form-control" readonly="readonly">
                    <%--<label name="date" class="form-control"></label>--%>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建设单位（个人）</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="buildUnit" class="form-control"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建设项目名称</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="name" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建设地址</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="buildAddress" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建设规模</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="scaleConstruction" class="form-control"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">勘察单位</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="reconnaissanceUnit" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">设计单位</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="designUnit" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">施工单位</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="constructionUnit" class="form-control"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">监理单位</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="constructionControlUnit" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">勘察单位项目负责人</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="reconnaissanceUnitPerson" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">设计单位项目负责人</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="designUnitPerson" class="form-control"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">施工单位项目负责人</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="constructionUnitPerson" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">总监理工程师</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="chiefEngineerConstructionInspection" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">合同工期</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <input type="text" name="contractPeriod" class="form-control" readonly="readonly">
                    <%--<label name="contractPeriod" class="form-control"></label>--%>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">备注</label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <label name="remark" class="form-control"></label>
                </div>
            </div>
        </div>
    </div>
</script>

<script id="preSalePermitCommon" type="text/html" data-title="商品房预售许可证">
    <div id="declareModelHandlePreSalePermitCommon">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">证书编号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="certificateNumber" class="form-control"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">售房单位</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="salesUnit" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">法定代表人</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="legalRepresentative" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">项目坐落</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="beLocated" class="form-control"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">项目名称</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="name" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">预售范围</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="preSaleScope" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">预售面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="preSaleArea" class="form-control"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋用途</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="housingUse" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑结构</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="buildingStructure" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">预售款监管信息</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="preSaleSupervisionInformation" class="form-control"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">发证机关</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="issuingOrgan" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">日期</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <%--<label name="date" class="form-control"></label>--%>
                    <input type="text" name="date" class="form-control" readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">在建工程抵押情况</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="mortgageSituation" class="form-control"></label>
                </div>
            </div>
        </div>
    </div>
</script>

<script id="landUsePermitCommon" type="text/html" data-title="建设用地规划许可证">
    <div id="declareModelHandleLandUsePermitCommon">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">证书编号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="certificateNumber" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">发证机关</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="issuingOrgan" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">日期</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <%--<label name="date" class="form-control"></label>--%>
                    <input type="text" name="date" class="form-control" readonly="readonly">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">用地单位</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="unit" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">用地项目名称</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="name" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">用地位置</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="location" class="form-control"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">用地性质</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="nature" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">用地面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="area" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建设规模</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="scaleConstruction" class="form-control"></label>
                </div>
            </div>
        </div>
    </div>
</script>

<script id="buildingPermitCommon" type="text/html" data-title="建设工程规划许可证">
    <div id="declareModelHandleBuildingPermitCommon">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">证书编号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="certificateNumber" class="form-control"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">发证机关</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="issuingOrgan" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">日期</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <%--<label name="date" class="form-control"></label>--%>
                    <input type="text" name="date" class="form-control" readonly="readonly">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建设单位（个人）</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="unit" class="form-control"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建设项目名称</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="name" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建设位置</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="location" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建设规模</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="scaleConstruction" class="form-control"></label>
                </div>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="declareEconomicIndicatorsModelCommonB" data-title="经济指标content 或者经济指标2">

    <table class="table tree" id="declareEconomicIndicatorsModelCommonBTree">
        <thead>
        <tr>
            <th>规划项目名称</th>
            <th>规划指标</th>
            <th>可出售面积</th>
            <th>个数</th>
            <th>备注</th>
        </tr>
        </thead>
        <tbody>
        <tr class="treegrid-1" data-key="groundBuildingArea" data-title="地上计入容积率建筑面积" data-role="parent">
            <td>一: 地上计入容积率建筑面积</td>
            <td><input type="text" readonly="readonly" disabled="disabled" placeholder="规划指标" name="planIndex" data-rule-number="true" style="width: 100px;">
            </td>
            <td><input type="text" readonly="readonly" disabled="disabled" placeholder="可出售面积" name="salabilityNumber" data-rule-number="true"
                       style="width: 100px;"> ㎡
            </td>
            <td><input type="text" readonly="readonly" disabled="disabled" placeholder="个数" name="assessSalabilityNumber" data-rule-number="true"
                       style="width: 100px;">
            </td>
            <td><input type="text" readonly="readonly" disabled="disabled" placeholder="备注" name="remark" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-2" data-key="groundExcludBuildingArea" data-title="地上不计入容积率建筑面积" data-role="parent">
            <td>二: 地上不计入容积率建筑面积</td>
            <td><input type="text" readonly="readonly" disabled="disabled" placeholder="规划指标" name="planIndex" data-rule-number="true" style="width: 100px;">
            </td>
            <td><input type="text" readonly="readonly" disabled="disabled" placeholder="可出售面积" name="salabilityNumber" data-rule-number="true"
                       style="width: 100px;"> ㎡
            </td>
            <td><input type="text" readonly="readonly" disabled="disabled" placeholder="个数" name="assessSalabilityNumber" data-rule-number="true"
                       style="width: 100px;">
            </td>
            <td><input type="text" readonly="readonly" disabled="disabled" placeholder="备注" name="remark" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-3" data-key="undergroundBuildingArea" data-title="地下建筑面积" data-role="parent">
            <td>三: 地下建筑面积</td>
            <td><input readonly="readonly" disabled="disabled" type="text" placeholder="规划指标" name="planIndex" data-rule-number="true" style="width: 100px;">
            </td>
            <td><input readonly="readonly" disabled="disabled" type="text" placeholder="可出售面积" name="salabilityNumber" data-rule-number="true"
                       style="width: 100px;"> ㎡
            </td>
            <td><input readonly="readonly" disabled="disabled" type="text" placeholder="个数" name="assessSalabilityNumber" data-rule-number="true"
                       style="width: 100px;">
            </td>
            <td><input readonly="readonly" disabled="disabled" type="text" placeholder="备注" name="remark" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-4" data-key="undergroundIncludedBuildingArea" data-title="地下不计入建筑面积" data-role="parent">
            <td>四: 地下不计入建筑面积</td>
            <td><input readonly="readonly" disabled="disabled" type="text" placeholder="规划指标" name="planIndex" data-rule-number="true" style="width: 100px;">
            </td>
            <td><input readonly="readonly" disabled="disabled" type="text" placeholder="可出售面积" name="salabilityNumber" data-rule-number="true"
                       style="width: 100px;"> ㎡
            </td>
            <td><input readonly="readonly" disabled="disabled" type="text" placeholder="个数" name="assessSalabilityNumber" data-rule-number="true"
                       style="width: 100px;">
            </td>
            <td><input readonly="readonly" disabled="disabled" type="text" placeholder="备注" name="remark" style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-5" data-key="otherBuildingArea" data-role="parent">
            <td>五: 其他</td>
            <td><input readonly="readonly" disabled="disabled" type="text" placeholder="规划指标" name="planIndex" data-rule-number="true" style="width: 100px;">
            </td>
            <td><input readonly="readonly" disabled="disabled" type="text" placeholder="可出售面积" name="salabilityNumber" data-rule-number="true"
                       style="width: 100px;"> ㎡
            </td>
            <td><input readonly="readonly" disabled="disabled" type="text" placeholder="个数" name="assessSalabilityNumber" data-rule-number="true"
                       style="width: 100px;">
            </td>
            <td><input readonly="readonly" disabled="disabled" type="text" placeholder="备注" name="remark" style="width: 100px;"></td>
        </tr>
        </tbody>
    </table>
</script>
<script type="text/html" id="otherBuildingAreaTemplate" data-title="其他（可自己定义具体名称）模板">
    <tr class="dynamic treegrid-5-1 treegrid-parent-5" data-key="otherBuildingArea" data-role="child">
        <td>
            <input type="text" readonly="readonly" disabled="disabled" name="name" placeholder="名称" value="{name}" style="width: 100px;">
        </td>
        <td><input type="text" readonly="readonly" disabled="disabled" value="{planIndex}" name="planIndex" data-rule-number="true" style="width: 100px;"></td>
        <td><input type="text" readonly="readonly" disabled="disabled" value="{salabilityNumber}" name="salabilityNumber" data-rule-number="true" style="width: 100px;"> ㎡
        </td>
        <td><input type="text" readonly="readonly" disabled="disabled" value="{assessSalabilityNumber}" name="assessSalabilityNumber" data-rule-number="true" style="width: 100px;">
        </td>
        <td><input type="text" readonly="readonly" disabled="disabled" value="{remark}" placeholder="备注" name="remark" style="width: 100px;"></td>
    </tr>
</script>
<script type="text/html" id="undergroundIncludedBuildingAreaTemplate" data-title="地下不计入建筑面积">
    <tr class="dynamic treegrid-4-1 treegrid-parent-4" data-key="undergroundIncludedBuildingArea" data-role="child">
        <td>
            <input type="text" readonly="readonly" disabled="disabled" name="name" placeholder="名称" value="{name}" style="width: 100px;">
        </td>
        <td><input type="text" readonly="readonly" disabled="disabled" value="{planIndex}" name="planIndex" data-rule-number="true" style="width: 100px;"></td>
        <td><input type="text" readonly="readonly" disabled="disabled" value="{salabilityNumber}" name="salabilityNumber" data-rule-number="true" style="width: 100px;"> ㎡
        </td>
        <td><input type="text" readonly="readonly" disabled="disabled" value="{assessSalabilityNumber}" name="assessSalabilityNumber" data-rule-number="true" style="width: 100px;">
        </td>
        <td><input type="text" readonly="readonly" disabled="disabled" value="{remark}" placeholder="备注" name="remark" style="width: 100px;"></td>
    </tr>
</script>
<script type="text/html" id="undergroundBuildingAreaTemplate" data-title="地下建筑面积">
    <tr class="dynamic treegrid-3-1 treegrid-parent-3" data-key="undergroundBuildingArea" data-role="child">
        <td>
            <input type="text" readonly="readonly" disabled="disabled" name="name" placeholder="名称" value="{name}" style="width: 100px;">
        </td>
        <td><input type="text" readonly="readonly" disabled="disabled" value="{planIndex}" name="planIndex" data-rule-number="true" style="width: 100px;"></td>
        <td><input type="text" readonly="readonly" disabled="disabled" value="{salabilityNumber}" name="salabilityNumber" data-rule-number="true" style="width: 100px;"> ㎡
        </td>
        <td><input type="text" readonly="readonly" disabled="disabled" value="{assessSalabilityNumber}" name="assessSalabilityNumber" data-rule-number="true" style="width: 100px;">
        </td>
        <td><input type="text" readonly="readonly" disabled="disabled" value="{remark}" placeholder="备注" name="remark" style="width: 100px;"></td>
    </tr>
</script>
<script type="text/html" id="groundExcludBuildingAreaTemplate" data-title="地上不计入容积率建筑面积">
    <tr class="dynamic treegrid-2-1 treegrid-parent-2" data-key="groundExcludBuildingArea" data-role="child">
        <td>
            <input type="text" readonly="readonly" disabled="disabled" name="name" placeholder="名称" value="{name}" style="width: 100px;">
        </td>
        <td><input type="text" readonly="readonly" disabled="disabled" value="{planIndex}" name="planIndex" data-rule-number="true" style="width: 100px;"></td>
        <td><input type="text" readonly="readonly" disabled="disabled" value="{salabilityNumber}" name="salabilityNumber" data-rule-number="true" style="width: 100px;"> ㎡
        </td>
        <td><input type="text" readonly="readonly" disabled="disabled" value="{assessSalabilityNumber}" name="assessSalabilityNumber" data-rule-number="true" style="width: 100px;">
        </td>
        <td><input type="text" readonly="readonly" disabled="disabled" value="{remark}" placeholder="备注" name="remark" style="width: 100px;"></td>
    </tr>
</script>
<script type="text/html" id="groundBuildingAreaTemplate" data-title="地上计入容积率建筑面积">
    <tr class="dynamic treegrid-1-1 treegrid-parent-1" data-key="groundBuildingArea" data-role="child">
        <td>
            <input type="text" readonly="readonly" disabled="disabled" name="name" placeholder="名称" value="{name}" style="width: 100px;">
        </td>
        <td><input type="text" readonly="readonly" disabled="disabled" value="{planIndex}" name="planIndex" data-rule-number="true" style="width: 100px;"></td>
        <td><input type="text" readonly="readonly" disabled="disabled" value="{salabilityNumber}" name="salabilityNumber" data-rule-number="true" style="width: 100px;"> ㎡
        </td>
        <td><input type="text" readonly="readonly" disabled="disabled" value="{assessSalabilityNumber}" name="assessSalabilityNumber" data-rule-number="true" style="width: 100px;">
        </td>
        <td><input type="text" readonly="readonly" disabled="disabled" value="{remark}" placeholder="备注" name="remark" style="width: 100px;"></td>
    </tr>
</script>
<script type="text/html" id="declareEconomicIndicatorsModelCommonA" data-title="经济指标head 或者经济指标1">
    <div id="declareEconomicIndicatorsModelCommonHandleA">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地用途</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="certUse" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">项目名称<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="name" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">项目档次（楼盘)</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="grade" class="form-control"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑结构</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="buildingStructure" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label" >建筑限高（m）</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="buildingHeightLimit" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label" >建筑基底占地面积（㎡)</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="buildingBaseCoverage" class="form-control"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">设定容积率</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="setVolumetricRate" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">容积率</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="volumetricRate" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑密度</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="buildingDensity" class="form-control"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">绿地率</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="greenSpaceRate" class="form-control"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">规划日期<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <%--<label name="planDate" class="form-control"></label>--%>
                    <input type="text" name="planDate" readonly="readonly" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label" >规划总建筑面积（㎡）</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="planTotalBuildArea" class="form-control"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label" >规划建设净用地面积（㎡）</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="planNetConstructionLandArea" class="form-control"></label>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label" >评估总建筑面积（㎡）<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="assessTotalBuildArea" class="form-control"></label>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label" >评估用地面积（㎡）<span
                        class="symbol required"></span></label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label name="assessUseLandArea" class="form-control"></label>
                </div>
            </div>
        </div>
    </div>
</script>

</body>
</html>
