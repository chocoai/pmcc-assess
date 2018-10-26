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
            <%@include file="/views/share/form_head.jsp" %>

            <div class="x_panel" id="basicApplyId">
                <div class="x_title">
                    <h2>
                        <small>案例信息</small>
                    </h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div role="tabpanel" data-example-id="togglable-tabs">
                        <ul class="nav nav-tabs bar_tabs" role="tablist" id="caseTab">
                            <li role="presentation" class=""><a href="#basicEstate" role="tab" id="profile-tab1"
                                                                data-toggle="tab" aria-expanded="true"
                                                                onclick="objectData.estate.init()">楼盘</a>
                            </li>
                            <li role="presentation" class=""><a href="#caseBuild" role="tab" id="profile-tab2"
                                                                data-toggle="tab" aria-expanded="false" onclick="">楼栋</a>
                            </li>
                            <li role="presentation" class=""><a href="#caseUnit" role="tab" id="profile-tab3"
                                                                data-toggle="tab" aria-expanded="false">单元</a>
                            </li>
                            <li role="presentation" class=""><a href="#caseHouse" role="tab" id="profile-tab4"
                                                                data-toggle="tab" aria-expanded="false">房屋</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div role="tabpanel" class="tab-pane fade" id="basicEstate" aria-labelledby="profile-tab1">
                                <div class="x_content">
                                    <div class="x_title">
                                        <h3>楼盘基本信息
                                        </h3>
                                        <div class="clearfix"></div>
                                    </div>
                                    <form class="form-horizontal" id="frm_estate">
                                        <input type="hidden" name="id" value="${basicEstate.id}">
                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">省
                                                </label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="省" readonly="readonly"
                                                           name="provinceName" class="form-control" value="${basicEstate.provinceName}">
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">市</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="市" readonly="readonly"
                                                           name="cityName" class="form-control" value="${basicEstate.cityName}">
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">县</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="县" readonly="readonly"
                                                           name="districtName" class="form-control" value="${basicEstate.districtName}">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">楼盘名称</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="楼盘名称" readonly="readonly"
                                                           name="name" class="form-control" value="${basicEstate.name}">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">楼盘方位</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="楼盘方位" readonly="readonly"
                                                           name="position" class="form-control" value="${basicEstate.position}">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">基础版块</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="楼盘方位" readonly="readonly"
                                                           name="position" class="form-control" value="${basicEstate.blockName}">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">编号</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="编号(请输入数字)" name="number" readonly="readonly"
                                                           class="form-control" value="${basicEstate.number}">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">土地级别</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="土地级别" name="number" readonly="readonly"
                                                           class="form-control" value="${basicEstate.landLevelName}">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">街道</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="街道" readonly="readonly"
                                                           name="street" class="form-control" value="${basicEstate.street}">
                                                </div>
                                            </div>

                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">占地面积</label>
                                                <div class="col-sm-3">
                                                    <input type="text"  readonly="readonly"
                                                           placeholder="占地面积" name="coverAnArea" class="form-control" value="${basicEstate.coverAnArea}">
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">容积率</label>
                                                <div class="col-sm-3">
                                                    <input type="text" readonly="readonly"
                                                           placeholder="容积率" value="${basicEstate.volumetricRate}"
                                                           name="volumetricRate" class="form-control">
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">绿化率</label>
                                                <div class="col-sm-3">
                                                    <input type="text" readonly="readonly"
                                                           placeholder="绿化率" value="${basicEstate.greeningRate}" name="greeningRate" class="form-control">
                                                </div>
                                            </div>
                                        </div>


                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">楼盘概况</label>
                                                <div class="col-sm-11">
                                                    <textarea class="form-control" readonly="readonly" name="description"
                                                              placeholder="楼盘概况">${basicEstate.description}</textarea>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">楼栋数</label>
                                                <div class="col-sm-3">
                                                    <input type="text"
                                                           placeholder="楼栋数" readonly="readonly" name="buildingNumber"
                                                           class="form-control" value="${basicEstate.buildingNumber}">
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">开发商</label>
                                                <div class="col-sm-3">
                                                    <input type="text"
                                                           placeholder="开发商" readonly="readonly" name="developerName"
                                                           class="form-control" value="${basicEstate.developerName}">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">建筑面积</label>
                                                <div class="col-sm-3">
                                                    <input type="text"
                                                           placeholder="建筑面积" readonly="readonly" name="floorArea"
                                                           class="form-control" value="${basicEstate.floorArea}">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">均价</label>
                                                <div class="col-sm-3">
                                                    <input type="text"
                                                           placeholder="均价(请输入数字)" name="averagePrice" readonly="readonly"
                                                           class="form-control" value="${basicEstate.averagePrice}">
                                                </div>
                                            </div>

                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">价格区间</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="价格区间"
                                                           name="priceRange" readonly="readonly" class="form-control"
                                                           value="${basicEstate.priceRange}">
                                                </div>
                                            </div>
                                            <div class="x-valid">
                                                <label class="col-sm-1 control-label">附号</label>
                                                <div class="col-sm-3">
                                                    <input type="text"
                                                           placeholder="附号(请输入数字)" value="${basicEstate.attachNumber}"
                                                           name="attachNumber" readonly="readonly" class="form-control">
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
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="caseBuild" aria-labelledby="profile-tab2">
                                <p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee
                                    squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes
                                    anderson artisan four loko farm-to-table craft beer twee. Qui photo
                                    booth letterpress, commodo enim craft beer mlkshk aliquip</p>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="caseUnit" aria-labelledby="profile-tab3">
                                <p>xxFood truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee
                                    squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes
                                    anderson artisan four loko farm-to-table craft beer twee. Qui photo
                                    booth letterpress, commodo enim craft beer mlkshk </p>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="caseHouse" aria-labelledby="profile-tab4">
                                <p>
                                    hjsdjsjsd </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_content">

                </div>
            </div>

            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    var objectData = new Object();

    objectData.config = {
        id: "basicApplyId",
        option: {},
        basicEstate: {
            key: "basicEstate",
            name: "楼盘",
            frm: "basicEstateFrm",
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
            name: "楼栋"
        },
        basicUnit: {
            key: "basicUnit",
            name: "单元"
        },
        basicHouse: {
            key: "basicHouse",
            name: "房屋"
        }
    };

    objectData.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    objectData.select2Assignment = function (frm, data, name) {
        if (objectData.isNotBlank(data)) {
            $("#" + frm).find("select." + name).val(data).trigger("change");
        } else {
            $("#" + frm).find("select." + name).val(null).trigger("change");
        }
    };


    objectData.showFile = function (fieldsName, table, id) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: table,
                tableId: objectData.isNotBlank(id) ? id : "0",
                creater: "${currUserAccount}"
            },
            deleteFlag: true
        })
    };

    objectData.estate = {
        init:function () {
            $.each(objectData.config.basicEstate.files, function (i, n) {
                objectData.showFile(n, AssessDBKey.BasicEstate, "${basicEstate.id}");
            });
        }
    };
</script>
<script type="application/javascript">

    function saveform() {
        if (!$("#frm_approval").valid()) {
            return false;
        }
        var data = formApproval.getFormData();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApply/projectApprovalSubmit",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    console.info(result);
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                console.info(result);
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        })
    }
</script>
</html>
