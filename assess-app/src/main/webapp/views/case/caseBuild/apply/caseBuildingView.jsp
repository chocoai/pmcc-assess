<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/9/11
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <script type="text/javascript" src="/pmcc-crm/js/crm-customer-utils.js"></script>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h2>
                        楼栋
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="x_title">
                        <h3>楼盘基本信息 </h3>
                        <div class="clearfix"></div>
                    </div>
                    <form class="form-horizontal" id="frm_build">
                        <input type="hidden" name="id" value="${caseBuilding.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    楼栋号<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="楼栋号" name="buildingNumber"
                                           class="form-control" required="required" value="${caseBuilding.buildingNumber}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    户型区间<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="户型区间" name="unitInterval"
                                           class="form-control" required="required" value="${caseBuilding.unitInterval}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    物业费<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="物业费(数字)" name="propertyFee" value="${caseBuilding.propertyFee}"
                                           data-rule-number='true' class="form-control" required="required">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    公共设施使用费<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="公共设施使用费(数字)" name="facilitiesUseFee"
                                           data-rule-number='true' class="form-control" required="required" value="${caseBuilding.facilitiesUseFee}">
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    楼层起<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="楼层起(数字)" name="floorStart" value="${caseBuilding.floorStart}"
                                           data-rule-number='true' class="form-control" required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    编号
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="编号" name="identifier"
                                           class="form-control" value="${caseBuilding.identifier}">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    楼层止<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="楼层止(数字)" name="floorEnd" value="${caseBuilding.floorEnd}"
                                           data-rule-number='true' class="form-control" required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    总层数<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="总层数(数字)" data-rule-number='true'
                                           name="floorCount" class="form-control" required="required" value="${caseBuilding.floorCount}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    建筑高度<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="建筑高度(数字)" data-rule-number='true'
                                           name="buildingHeight" class="form-control" required="required" value="${caseBuilding.buildingHeight}">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    建筑面积<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="建筑面积(数字)" data-rule-number='true'
                                           name="buildingArea" class="form-control" required="required" value="${caseBuilding.buildingArea}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    占地面积<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="占地面积(数字)" data-rule-number='true'
                                           name="coverAnArea" class="form-control" required="required" value="${caseBuilding.coverAnArea}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    层高<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="层高(数字)" data-rule-number='true'
                                           name="floorHeight" class="form-control" required="required" value="${caseBuilding.floorHeight}">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    径深<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="径深(数字)" data-rule-number='true'
                                           name="diameterDepth" class="form-control" required="required" value="${caseBuilding.diameterDepth}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    土地使用年限<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="土地使用年限(数字)" data-rule-number='true'
                                           name="landUseYear" class="form-control" required="required" value="${caseBuilding.landUseYear}">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    所在位置<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="所在位置" name="location" class="form-control"
                                           required="required" value="${caseBuilding.location}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    开盘时间<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input placeholder="开盘时间"
                                           name="openTime" data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate openTime" required="required">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    交房时间<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input placeholder="交房时间"
                                           name="roomTime" data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate roomTime" required="required">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    物业类型<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <select name="propertyType" required="required"
                                            class="form-control search-select select2 propertyType">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    建筑结构上级<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <select name="buildingStructure"
                                            class="form-control search-select select2 buildingStructure">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    建筑结构(下级)<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <select id="frm_buildbuildingStructure"
                                            name="buildingStructurePid"
                                            class="form-control search-select select2 buildingstructurePid">
                                        <option>请先选择建筑结构上级</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    建筑类别<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <select name="buildingCategory" required="required"
                                            class="form-control search-select select2 buildingCategory">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    建筑公司<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <select name="builderId" required="required"
                                            class="form-control search-select select2 builderId">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    物业公司<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <select name="propertyId" required="required"
                                            class="form-control search-select select2 propertyId">
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">平面图<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input id="building_floor_plan" name="frm_estate_floor_total_plan"
                                           required="required" placeholder="上传附件" class="form-control" type="file">
                                    <div id="_building_floor_plan"></div>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">外装图<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input id="building_figure_outside" name="frm_estate_floor_total_plan"
                                           required="required" placeholder="上传附件" class="form-control" type="file">
                                    <div id="_building_figure_outside"></div>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">外观图<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input id="building_floor_Appearance_figure" name="frm_estate_floor_total_plan"
                                           required="required" placeholder="上传附件" class="form-control" type="file">
                                    <div id="_building_floor_Appearance_figure"></div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>


                <div class="x_panel">
                    <div class="x_content">
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-5">
                                <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                    取消
                                </button>

                                <button id="commit_btn" class="btn btn-success" onclick="build.submit();">
                                    提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        var CaseBuildFun = function () {

        }

        CaseBuildFun.prototype.config = {
            build: {
                frm: function () {
                    return "frm_build";//楼盘基本信息frm
                },
                building_floor_plan: function () {
                    return "building_floor_plan";//平面图id和字段 (楼栋) 根据 ExamineFileUpLoadFieldEnum 配置
                },
                building_figure_outside: function () {
                    return "building_figure_outside";//外装图id和字段
                },
                building_floor_Appearance_figure: function () {
                    return "building_floor_Appearance_figure";//外观图id和字段
                }
            }
        }

        CaseBuildFun.prototype.isEmpty = function (item) {
            if (item) {
                return true;
            }
            return false;
        }

        CaseBuildFun.prototype.writeSelectData = function (frm, data, name) {
            if (CaseBuildFun.prototype.isEmpty(data)) {
                $("#" + frm + " ." + name).val(data).trigger("change");
            } else {
                $("#" + frm + " ." + name).val(null).trigger("change");
            }
        }

        CaseBuildFun.prototype.select2Init = function () {
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_category, null, function (html, data) {
                $("#" + CaseBuildFun.prototype.config.build.frm() + " .buildingCategory").html(html);
                $("#" + CaseBuildFun.prototype.config.build.frm() + " .buildingCategory").select2();//加载样式
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_structure, null, function (html, data) {
                $("#" + CaseBuildFun.prototype.config.build.frm() + " .buildingStructure").html(html);
                $("#" + CaseBuildFun.prototype.config.build.frm() + " .buildingStructure").select2();//加载样式
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_type, null, function (html, data) {
                $("#" + CaseBuildFun.prototype.config.build.frm() + " .propertyType").html(html);
                $("#" + CaseBuildFun.prototype.config.build.frm() + " .propertyType").select2();//加载样式
            });
            $("#" + CaseBuildFun.prototype.config.build.frm() + "buildingStructure").select2({minimumResultsForSearch: -1});//加载样式
            $("#" + CaseBuildFun.prototype.config.build.frm() + " .buildingStructure").change(function () {
                /**
                 * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
                 **/
                var id = $("#" + CaseBuildFun.prototype.config.build.frm() + " .buildingStructure").eq(1).val();
                if (id != null && id != '' && id != 0) {
                    AssessCommon.loadDataDicByPid(id, null, function (html, data) {
                        $("#" + CaseBuildFun.prototype.config.build.frm() + "buildingStructure").html(html);
                        $("#" + CaseBuildFun.prototype.config.build.frm() + "buildingStructure").select2();//加载样式
                    });
                }
            });
            $.ajax({
                url: "${pageContext.request.contextPath}/examineBuilding/getBuildAndProperty",
                type: "get",
                dataType: "json",
                data: {type: "type"},
                success: function (result) {
                    if (result.ret) {
                        var item = result.data;
                        var option = "";
                        if (item.DataBuilder.length > 0) {
                            option = "<option value=''>请选择</option>";
                            var data = item.DataBuilder;
                            for (var i = 0; i < item.DataBuilder.length; i++) {
                                option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                            }
                            $("#" + CaseBuildFun.prototype.config.build.frm() + " .builderId").html(option);
                            $("#" + CaseBuildFun.prototype.config.build.frm() + " .builderId").select2();//加载样式
                            option = "";
                        }
                        if (item.DataProperty.length > 0) {
                            option = "<option value=''>请选择</option>";
                            var data = item.DataProperty;
                            for (var i = 0; i < item.DataProperty.length; i++) {
                                option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                            }
                            $("#" + CaseBuildFun.prototype.config.build.frm() + " .propertyId").html(option);
                            $("#" + CaseBuildFun.prototype.config.build.frm() + " .propertyId").select2();//加载样式
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        }

        CaseBuildFun.prototype.uploadFile = function (fieldsName, table) {
            FileUtils.uploadFiles({
                target: fieldsName,
                disabledTarget: "btn_submit",
                formData: {
                    fieldsName: fieldsName,
                    tableName: table,
                    tableId: ${empty caseBuilding.id?0:caseBuilding.id},
                    creater: "${currUserAccount}"
                },
                deleteFlag: true
            });
        }
        CaseBuildFun.prototype.showFile = function (fieldsName, table) {
            FileUtils.getFileShows({
                target: fieldsName,
                formData: {
                    fieldsName: fieldsName,
                    tableName: table,
                    tableId: ${empty caseBuilding.id?0:caseBuilding.id},
                    creater: "${currUserAccount}"
                },
                deleteFlag: true
            })
        }

        var build = new CaseBuildFun();

        //编辑时
        build.edit = function () {
            var caseBuilding = "${caseBuilding}";
            if (build.isEmpty(caseBuilding)) {
                $("#" + build.config.build.frm() + " .openTime").val(formatDate("${caseBuilding.openTime}"));
                $("#" + build.config.build.frm() + " .roomTime").val(formatDate("${caseBuilding.roomTime}"));
                build.writeSelectData(build.config.build.frm(),"${caseBuilding.propertyType}","propertyType");
            }
        }
        //总提交
        build.submit = function () {
            if (!$("#" + build.config.build.frm()).valid()) {
                return false;
            }
            var estateId = "${estateId}" ;
            var data = formParams(build.config.build.frm());
            if (build.isEmpty(estateId)){
                data.estateId = estateId;
            }
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/caseBuilding/saveAndUpdateCaseBuilding",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        //保存完后其他动作
                        Alert("提交数据成功!", 1, null, function () {
                            window.close();
                        });
                    } else {
                        Alert("保存失败:" + result.errmsg);
                    }
                },
                error: function (e) {
                    Alert("调用服务端方法失败，失败原因:" + e);
                }
            });
        }

        $(function () {
            //使校验生效
            $("#" + build.config.build.frm()).validate();
            //select2 初始化
            build.select2Init();
            build.edit();
            build.uploadFile(build.config.build.building_floor_plan(), AssessDBKey.CaseBuilding);
            build.showFile(build.config.build.building_floor_plan(), AssessDBKey.CaseBuilding);
            build.uploadFile(build.config.build.building_figure_outside(), AssessDBKey.CaseBuilding);
            build.showFile(build.config.build.building_figure_outside(), AssessDBKey.CaseBuilding);
            build.uploadFile(build.config.build.building_floor_Appearance_figure(), AssessDBKey.CaseBuilding);
            build.showFile(build.config.build.building_floor_Appearance_figure(), AssessDBKey.CaseBuilding);
        });
    </script>
    <%@include file="/views/share/main_footer.jsp" %>
</body>
</html>
