<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <div class="x_panel">

                <div class="x_content">
                    <div class="x_title">
                        <h3>楼盘基本信息</h3>
                        <div class="clearfix"></div>
                    </div>
                    <form class="form-horizontal" id="frm_estate">
                        <input type="hidden" name="id" value="${caseEstate.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">省</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstate.provinceName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">市</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstate.cityName}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">县</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstate.districtName}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">街道</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstate.street}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">街道号</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstate.streetNumber}</label>
                                </div>
                            </div>


                            <div class="x-valid">
                                <label class="col-sm-1 control-label">基础版块</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstate.blockName}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">楼盘名称</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstate.name}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">楼盘方位</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstate.position}</label>
                                </div>
                            </div>
                            <div class="x-valid supplyGas">
                                <label class="col-sm-1 control-label">供气信息</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstate.supplyGas?'有':'无'}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid supplyPower">
                                <label class="col-sm-1 control-label">供电信息</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstate.supplyPower?'有':'无'}</label>
                                </div>
                            </div>

                            <div class="x-valid supplyWater">
                                <label class="col-sm-1 control-label">供排水情况</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstate.supplyWater?'有':'无'}</label>
                                </div>
                            </div>

                            <div class="x-valid supplyHeating">
                                <label class="col-sm-1 control-label">供热信息</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstate.supplyHeating?'有':'无'}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">占地面积</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstate.coverAnArea}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">容积率</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstate.volumetricRate}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">绿化率</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstate.greeningRate}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">楼盘概况</label>
                                <div class="col-sm-11">
                                    <label class="form-control">${caseEstate.description}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">楼栋数</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstate.buildingNumber}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">开发商</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstate.developerName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">建筑面积</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstate.floorArea}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">均价</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstate.averagePrice}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">价格区间</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstate.priceRange}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">附号</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstate.attachNumber}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">总平面图</label>
                                <div class="col-sm-5">
                                    <div id="_estate_floor_total_plan"></div>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">外观图</label>
                                <div class="col-sm-5">
                                    <div id="_estate_floor_Appearance_figure"></div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">供水平面图</label>
                                <div class="col-sm-5">
                                    <div id="_water_supply_plan"></div>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">供电平面图</label>
                                <div class="col-sm-5">
                                    <div id="_power_supply_plan"></div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">供气平面图</label>
                                <div class="col-sm-5">
                                    <div id="_air_supply_plan"></div>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">采暖平面图</label>
                                <div class="col-sm-5">
                                    <div id="_heating_plan"></div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="x_content">
                    <div class="x_title">
                        <h3>楼盘土地实体情况 </h3>
                        <div class="clearfix"></div>
                    </div>
                    <form id="frm_estateLandState" class="form-horizontal">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地名称</label>
                                <div class="col-sm-11">
                                    <label class="form-control">${caseEstateLandState.name}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地用途类型</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstateLandState.landUseTypeName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地用途类别</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstateLandState.landUseCategoryName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地级别</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstateLandState.landLevelName}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">东至</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstateLandState.eastTo}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">南至</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstateLandState.southTo}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">西至</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstateLandState.westTo}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">北至</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstateLandState.northTo}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地形状状况</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstateLandState.shapeState}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地平整度</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstateLandState.planeness}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地开发程度</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstateLandState.developmentDegree}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">开发限制条件</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstateLandState.restrictiveCondition}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土壤</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstateLandState.soil}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">地形地势</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstateLandState.topographicTerrain}</label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">土地面积</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${caseEstateLandState.landArea}</label>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <!-- 网络通信 -->
            <div class="network" style="display:${hasEstateNetworkData?'block':'none'};">
                <%@include file="/views/case/caseEstate/caseNetwork.jsp" %>
            </div>

            <!-- 车位信息 -->
            <div class="parking" style="display:${hasEstateParkingData?'block':'none'};">
                <%@include file="/views/case/caseEstate/caseParking.jsp" %>
            </div>

            <!-- 供气 -->
            <div class="supplygas" style="display:${hasEstateSupplyGas?'block':'none'};">
                <%@include file="/views/case/caseEstate/caseSupplyGas.jsp" %>
            </div>

            <!-- 供热 -->
            <div class="supplyheating" style="display: ${hasEstateSupplyHeating?'block':'none'};">
                <%@include file="/views/case/caseEstate/caseSupplyHeating.jsp" %>
            </div>

            <!-- 供电 -->
            <div class="supplypower" style="display: ${hasEstateSupplyPower?'block':'none'};">
                <%@include file="/views/case/caseEstate/caseSupplyPower.jsp" %>
            </div>

            <!-- 供水 -->
            <div class="supplywater" style="display: ${hasEstateSupplyWater?'block':'none'};">
                <%@include file="/views/case/caseEstate/caseSupplyWater.jsp" %>
            </div>

            <!-- 教育 -->
            <div class="education" style="display: ${hasMatchingEducationData?'block':'none'};">
                <%@include file="/views/case/caseEstate/caseMatchingEducation.jsp" %>
            </div>

            <!-- 环境 -->
            <div class="environment" style="display: ${hasMatchingEnvironmentData?'block':'none'};">
                <%@include file="/views/case/caseEstate/caseMatchingEnvironment.jsp" %>
            </div>

            <!-- 金融 -->
            <div class="finance" style="display: ${hasMatchingFinanceData?'block':'none'};">
                <%@include file="/views/case/caseEstate/caseMatchingFinance.jsp" %>
            </div>

            <!-- 购物商场 -->
            <div class="matchingMarket" style="display: ${hasMatchingLeisurePlaceMarket?'block':'none'};">
                <%@include file="/views/case/caseEstate/caseMatchingMarket.jsp" %>
            </div>

            <!-- 休闲娱乐 -->
            <div class="matchingRecreation" style="display: ${hasMatchingLeisurePlaceRecreation?'block':'none'};">
                <%@include file="/views/case/caseEstate/caseMatchingRecreation.jsp" %>
            </div>

            <!-- 餐饮 -->
            <div class="matchingRestaurant" style="display: ${hasMatchingLeisurePlaceRestaurant?'block':'none'};">
                <%@include file="/views/case/caseEstate/caseMatchingRestaurant.jsp" %>
            </div>

            <!-- 原材料及销售条件 -->
            <div class="material" style="display: ${hasMatchingMaterialData?'block':'none'};">
                <%@include file="/views/case/caseEstate/caseMatchingMaterial.jsp" %>
            </div>

            <!-- 医养条件 -->
            <div class="medical" style="display: ${hasMatchingMedicalData?'block':'none'};">
                <%@include file="/views/case/caseEstate/caseMatchingMedical.jsp" %>
            </div>

            <!-- 公交 -->
            <div class="transit" style="display: ${hasMatchingTrafficTransit?'block':'none'};">
                <%@include file="/views/case/caseEstate/caseMatchingTransit.jsp" %>
            </div>

            <!-- 地铁 -->
            <div class="metro" style="display: ${hasMatchingTrafficMetro?'block':'none'};">
                <%@include file="/views/case/caseEstate/caseMatchingMetro.jsp" %>
            </div>

            <!-- 主干道 -->
            <div class="mainroad" style="display: ${hasMatchingTrafficMainRoad?'block':'none'};">
                <%@include file="/views/case/caseEstate/caseMatchingMainRoad.jsp" %>
            </div>

            <!-- 主要转换 -->
            <div class="mainconversion" style="display: ${hasMatchingTrafficMainConversion?'block':'none'};">
                <%@include file="/views/case/caseEstate/caseMatchingMainConversion.jsp" %>
            </div>

            <!-- 交通枢纽 -->
            <div class="trafficHub" style="display: ${hasMatchingTrafficTrafficHub?'block':'none'};">
                <%@include file="/views/case/caseEstate/caseMatchingTrafficHub.jsp" %>
            </div>

            <div class="x_panel">
                <div class="x_content">
                    <div class="form-group">
                        <div class="col-sm-6 col-sm-offset-6">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                关闭
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var CaseEstateFun = function () {

    };

    CaseEstateFun.prototype.config = {
        estate: {
            frm: function () {
                return "frm_estate";//楼盘基本信息frm
            },
            filePlanTotal: function () {
                return "estate_floor_total_plan";//总平面图id和字段
            },
            waterSupplyPlan: function () {
                return "water_supply_plan";//供水平面图id和字段
            },
            powerSupplyPlan: function () {
                return "power_supply_plan";//供电平面图id和字段
            },
            airSupplyPlan: function () {
                return "air_supply_plan";//供气平面图id和字段
            },
            heatingPlan: function () {
                return "heating_plan";//采暖平面图id和字段
            },
            fileAppearance: function () {
                return "estate_floor_Appearance_figure";//外观图id和字段
            }
        },
        landState: {
            frm: function () {
                return "frm_estateLandState";//土地实体情况
            }
        },
        other: {
            box: function () {
                return "otherDivBox";//其它信息box
            },
            frm: function () {
                return "frmOther";//其它信息frm
            }
        }
    };

    CaseEstateFun.prototype.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    CaseEstateFun.prototype.writeSelectData = function (frm, data, name) {
        if (CaseEstateFun.prototype.isEmpty(data)) {
            $("#" + frm + " ." + name).val(data).trigger("change");
        } else {
            $("#" + frm + " ." + name).val(null).trigger("change");
        }
    };
    CaseEstateFun.prototype.showFile = function (fieldsName, table) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: table,
                tableId: ${empty caseEstate.id?0:caseEstate.id},
                creater: "${currUserAccount}"
            },
            deleteFlag: true
        })
    };

    var caseEstate = new CaseEstateFun();

    //模块 楼盘基本信息
    caseEstate.estateModel = {
        init: function () {
            //总平面图
            caseEstate.showFile(caseEstate.config.estate.filePlanTotal(), AssessDBKey.CaseEstate);
            //外观图
            caseEstate.showFile(caseEstate.config.estate.fileAppearance(), AssessDBKey.CaseEstate);
            //供水平面图
            caseEstate.showFile(caseEstate.config.estate.waterSupplyPlan(), AssessDBKey.CaseEstate);
            //供电平面图
            caseEstate.showFile(caseEstate.config.estate.powerSupplyPlan(), AssessDBKey.CaseEstate);
            //供气平面图
            caseEstate.showFile(caseEstate.config.estate.airSupplyPlan(), AssessDBKey.CaseEstate);
            //采暖平面图
            caseEstate.showFile(caseEstate.config.estate.heatingPlan(), AssessDBKey.CaseEstate);
        }
    };


    var otherFlag = true;
    caseEstate.other = {
        /**
         * @author:  zch
         * 描述:获取选择的子类数据
         * @date:2018-09-17
         **/
        tempSave: function () {
            if ($("#" + caseEstate.config.other.frm()).find('input:checkbox:checked').length <= 0) {
                toastr.info("请选择子项表单");
                return false;
            }
            var data = formParams(caseEstate.config.other.frm());
            console.log(data);
            //处理子类显示问题
            if (caseEstate.isEmpty(data)) {
                if (data.matching) {
                    var matching = data.matching.split(",");
                    $.each(matching, function (i, n) {
                        AssessCommon.getDataDicInfo(n, function (item) {
                            var fieldName = caseEstate.other.subFileName(item.fieldName);
                            var jq = $("." + fieldName);
                            if (jq.size() > 0) {
                                jq.toggle();
                            }
                            console.log(fieldName);
                        });
                    });
                }

                if (data.other) {
                    var other = data.other.split(",");
                    $.each(other, function (i, n) {
                        AssessCommon.getDataDicInfo(n, function (item) {
                            var fieldName = caseEstate.other.subFileName(item.fieldName);
                            var jq = $("." + fieldName);
                            if (jq.size() > 0) {
                                jq.toggle();
                            }
                            console.log(fieldName);
                        });
                    });
                }
            }
            $("#" + caseEstate.config.other.box()).modal("hide");
        },
        showBoxDiv: function () {
            if (caseEstate.other.getOtherFlag()) {
                caseEstate.other.selectInit();
                caseEstate.other.setOtherFlag(false);
            }
            $("#" + caseEstate.config.other.box()).modal("show");
        },
        getOtherFlag: function () {
            return otherFlag;
        },
        setOtherFlag: function (item) {
            otherFlag = item;
        },
        /**
         * @author:  zch
         * 描述:载入需要选择的子类信息
         * @date:2018-09-17
         **/
        selectInit: function () {
            var num = 6;
            AssessCommon.loadDataDicByKey(AssessDicKey.casesEstateOther, "", function (html, data) {
                var resetHtml = "";
                var k = 0;
                $.each(data, function (i, n) {
                    if (i % num == 0) {
                        k++;
                    }
                });
                for (var i = 0; i < k; i++) {
                    resetHtml += "<div class='form-group'>";
                    for (var j = i * num; j < i * num + num; j++) {
                        if (j < data.length) {
                            resetHtml += "<div class='col-sm-2'>";
                            resetHtml += "<span class='checkbox-inline'>";
                            resetHtml += "<input type='checkbox' id='other" + data[j].id + "' name='other' required='required' value='" + data[j].id + "'" + ">";
                            resetHtml += "<label for='other" + data[j].id + "'>" + data[j].name + "</label>";
                            resetHtml += "</span>";
                            resetHtml += "</div>";
                        }
                    }
                    resetHtml += "</div>";
                }
                //HTML
                $("#" + caseEstate.config.other.frm() + "HTMLOther").append(resetHtml);
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.casesEstateMatching, "", function (html, data) {
                var resetHtml = "";
                var k = 0;
                $.each(data, function (i, n) {
                    if (i % num == 0) {
                        k++;
                    }
                });
                for (var i = 0; i < k; i++) {
                    resetHtml += "<div class='form-group'>";
                    for (var j = i * num; j < i * num + num; j++) {
                        if (j < data.length) {
                            resetHtml += "<div class='col-sm-2'>";
                            resetHtml += "<span class='checkbox-inline'>";
                            resetHtml += "<input type='checkbox' id='matching" + data[j].id + "' required='required' name='matching' value='" + data[j].id + "'" + ">";
                            resetHtml += "<label for='matching" + data[j].id + "'>" + data[j].name + "</label>";
                            resetHtml += "</span>";
                            resetHtml += "</div>";
                        }
                    }
                    resetHtml += "</div>";
                }
                //HTML
                $("#" + caseEstate.config.other.frm() + "HTMLMatching").append(resetHtml);
            });
        },
        subFileName: function (upFileName) {
            var index1 = upFileName.lastIndexOf(".");
            var index2 = upFileName.length;
            var suffix = upFileName.substring(index1 + 1, index2);//后缀名
            return suffix;
        }
    };

    $(function () {
        caseEstate.estateModel.init();
    });
</script>
<%@include file="/views/share/main_footer.jsp" %>
</body>
</html>
<div id="otherDivBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">楼盘下属子类</h3>
            </div>
            <form id="frmOther" class="form-horizontal">
                <input type="hidden" id="id" name="id" value="0">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <h3 class="modal-title">其它信息</h3>
                                    </div>
                                </div>
                                <!--xxx -->
                                <div id="frmOtherHTMLOther">

                                </div>
                                <!--xxx -->
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <h3 class="modal-title">配套信息</h3>
                                    </div>
                                </div>
                                <!--xxx -->
                                <div id="frmOtherHTMLMatching">

                                </div>
                                <!--xxx -->
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="caseEstate.other.tempSave();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
