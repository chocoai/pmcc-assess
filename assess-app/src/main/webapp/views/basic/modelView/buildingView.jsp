<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>
            <small>
                楼栋基本信息
            </small>
        </h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="basicBuildingMainFrm">
        <input type="hidden" name="id">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼栋号<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="楼栋号" name="buildingNumber"
                           class="form-control" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼栋名称<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="楼栋名称" name="buildingName"
                           class="form-control" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    总层数<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="总层数(数字)" data-rule-number='true'
                           name="floorCount" class="form-control" required="required">
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
                           required="required">
                </div>
            </div>
        </div>
    </form>
    <div style="margin-bottom: 10px; border-bottom:2px solid #E6E9ED;"></div>
    <form class="form-horizontal" id="basicBuildingFrm">
        <input type="hidden" name="id">
        <div class="form-group" style="display: none;">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼层起<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="楼层起(数字)" name="floorStart"
                           data-rule-number='true' class="form-control" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼层止<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="楼层止(数字)" name="floorEnd"
                           data-rule-number='true' class="form-control" required="required">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    户型区间<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="户型区间" name="unitInterval"
                           class="form-control" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    物业费<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="物业费(数字)" name="propertyFee"
                           data-rule-number='true' class="form-control" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    配套公共设施使用费
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="配套公共设施使用费(数字)" name="facilitiesUseFee"
                           data-rule-number='true' class="form-control">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑高度<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="建筑高度(数字)" data-rule-number='true'
                           name="buildingHeight" class="form-control" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑面积<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="建筑面积(数字)" data-rule-number='true'
                           name="buildingArea" class="form-control" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    占地面积<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="占地面积(数字)" data-rule-number='true'
                           name="coverAnArea" class="form-control" required="required">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    层高<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="层高(数字)" data-rule-number='true'
                           name="floorHeight" class="form-control" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    径深<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="径深(数字)" data-rule-number='true'
                           name="diameterDepth" class="form-control" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地使用年限<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="土地使用年限(数字)" data-rule-number='true'
                           name="landUseYear" class="form-control" required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
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
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    竣工时间<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input placeholder="竣工时间"
                           name="beCompletedTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate beCompletedTime" required="required">
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
                    建筑结构类型<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <select name="buildingStructure"
                            class="form-control search-select select2 buildingStructure">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑结构类别<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <select name="buildingStructureLower"
                            class="form-control search-select select2 buildingStructureLower">
                        <option>建筑类别</option>
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
                    <div class="input-group">
                        <input type="hidden" name="builderId">
                        <input type="text" readonly="readonly"
                               placeholder="建筑公司" class="form-control" name="dataBuildingName">
                        <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择"
                                onclick="basicIndexCommon.builderSelect(this)">
                        <i class="fa fa-search"></i>
                        </button>
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
                <label class="col-sm-1 control-label">
                    物业公司<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <input type="hidden" name="propertyId">
                        <input type="text" readonly="readonly" name="propertyName"
                               placeholder="物业公司" class="form-control">
                        <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择"
                                onclick="basicIndexCommon.propertySelect(this)">
                        <i class="fa fa-search"></i>
                        </button>
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

        <div class="form-group" id="navButtonBuildGroupFileId">
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
<%@include file="/views/basic/modelView/build/sonBuildView.jsp" %>

<script type="text/javascript">
    var buildingMain = {};
</script>


<script type="text/javascript">

    var navButtonBuild;
    (function () {
        navButtonBuild = new Object();
        navButtonBuild.groupFileId = "navButtonBuildGroupFileId";
        navButtonBuild.isNotBlank = function (item) {
            if (item) {
                return true;
            }
            return false;
        };
        navButtonBuild.uploadFile = function (fieldsName, id) {
            FileUtils.uploadFiles({
                target: fieldsName,
                disabledTarget: "btn_submit",
                onUpload: function (file) {
                    var formData = {
                        fieldsName: fieldsName,
                        tableName: AssessDBKey.BasicBuilding,
                        tableId: navButtonBuild.isNotBlank(id) ? id : "0"
                    };
                    return formData;
                }, onUploadComplete: function (result, file) {
                    navButtonBuild.showFile(fieldsName, id);
                    if (navButtonBuild.isNotBlank(result)) {
                        AssessCommon.getSysAttachmentDto(result, function (data) {
                            var switchNumber = navButtonBuild.switchNumber;
                            var item = navButtonBuild.getObjArray(switchNumber);
                        });
                    }
                },
                deleteFlag: true
            });
        };
        navButtonBuild.uploadFile2 = function (fieldsName, id) {
            FileUtils.uploadFiles({
                target: fieldsName,
                disabledTarget: "btn_submit",
                formData: {
                    fieldsName: fieldsName,
                    tableName: AssessDBKey.BasicBuilding,
                    tableId: basicIndexCommon.isNotBlank(id) ? id : "0",
                },
                deleteFlag: true
            });
        };
        navButtonBuild.showFile = function (fieldsName, id) {
            FileUtils.getFileShows({
                target: fieldsName,
                formData: {
                    fieldsName: fieldsName,
                    tableName: AssessDBKey.BasicBuilding,
                    tableId: navButtonBuild.isNotBlank(id) ? id : "0",
                },
                deleteFlag: true
            })
        };
        <%--var objArray = [JSON.parse('${el:toJsonString(oneBasicBuilding)}'),--%>
        <%--JSON.parse('${el:toJsonString(twoBasicBuilding)}'),--%>
        <%--JSON.parse('${el:toJsonString(threeBasicBuilding)}'),--%>
        <%--JSON.parse('${el:toJsonString(fourBasicBuilding)}')];--%>
        navButtonBuild.objArray = [{}, {}, {}, {}];
        if ('${not empty basicBuildingList}' == 'true') {
            navButtonBuild.objArray = JSON.parse('${el:toJsonString(basicBuildingList)}');
        }
        //获取存储的所有数据
        navButtonBuild.getAllObjArray = function () {
            return navButtonBuild.objArray;
        };
        /**
         * @author:  zch
         * 描述:根据索引获取数据
         **/
        navButtonBuild.getObjArray = function (index) {
            var data = null;
            data = navButtonBuild.objArray[index];
            return data;
        };
        /**
         * @author:  zch
         * 描述:设置数据
         **/
        navButtonBuild.setObjArrayElement = function (index, data) {
            navButtonBuild.objArray[index] = data;
        };

        //收集数据
        navButtonBuild.tempSaveData = function () {
            var data = formParams(basicIndexCommon.config.basicBuilding.frm);
            var switchNumber = navButtonBuild.switchNumber;
            data.part = switchNumber;
            if (navButtonBuild.isNotBlank(switchNumber)) {
                navButtonBuild.setObjArrayElement(switchNumber, data);
            }
        };
        //赋值
        navButtonBuild.initData = function (switchNumber) {
            var data = navButtonBuild.getObjArray(switchNumber);
            $("#" + basicIndexCommon.config.basicBuilding.frm).initForm(data);
            basicIndexCommon.select2Assignment(basicIndexCommon.config.basicBuilding.frm, data.buildingCategory, "buildingCategory");
            basicIndexCommon.select2Assignment(basicIndexCommon.config.basicBuilding.frm, data.buildingStructure, "buildingStructure");
            basicIndexCommon.select2Assignment(basicIndexCommon.config.basicBuilding.frm, data.propertyType, "propertyType");
            AssessCommon.loadDataDicByPid(data.buildingStructure, data.buildingStructureLower, function (html, data) {
                $("#" + basicIndexCommon.config.basicBuilding.frm).find("select.buildingStructureLower").empty().html(html).trigger('change');
            });
            $("#" + basicIndexCommon.config.basicBuilding.frm).find("input[name='beCompletedTime']").val(formatDate(data.beCompletedTime));
            $("#" + basicIndexCommon.config.basicBuilding.frm).find("input[name='openTime']").val(formatDate(data.openTime));
            $("#" + basicIndexCommon.config.basicBuilding.frm).find("input[name='roomTime']").val(formatDate(data.roomTime));
            $.each(basicIndexCommon.config.basicBuilding.files, function (i, n) {
                // navButtonBuild.showFile(n + "" + navButtonBuild.switchNumber, data.id);
                navButtonBuild.showFile(n + "" + navButtonBuild.switchNumber, basicIndexCommon.getBuildId());
                // navButtonBuild.uploadFile2(n + "" + navButtonBuild.switchNumber, data.id);
                navButtonBuild.uploadFile2(n + "" + navButtonBuild.switchNumber, basicIndexCommon.getBuildId());
            });
        };
        navButtonBuild.clearAll = function () {
            $("#" + basicIndexCommon.config.basicBuilding.frm).clearAll();
        };
        navButtonBuild.inputBlur = function () {
            $("#" + basicIndexCommon.config.basicBuilding.frm).find("input").each(function (i, n) {
                $(n).blur(function () {
                    var str = $(n).val();
                    if (navButtonBuild.isNotBlank(str)) {
                        navButtonBuild.tempSaveData();
                    }
                });
            });
            $("#" + basicIndexCommon.config.basicBuilding.frm).find("select").each(function (i, n) {
                $(n).change(function () {
                    var str = $(n).val();
                    if (navButtonBuild.isNotBlank(str)) {
                        navButtonBuild.tempSaveData();
                    }
                });
            });
        };

        function writeUpdateFileId(num) {
            var fieldsName = "";
            var labelName = "";
            if (num == 0) {
                labelName = "平面图";
                fieldsName = basicIndexCommon.config.basicBuilding.files.building_floor_plan + "" + navButtonBuild.switchNumber;
            }
            if (num == 1) {
                labelName = "外装图";
                fieldsName = basicIndexCommon.config.basicBuilding.files.building_figure_outside + "" + navButtonBuild.switchNumber;
            }
            if (num == 2) {
                labelName = "外观图";
                fieldsName = basicIndexCommon.config.basicBuilding.files.building_floor_Appearance_figure + "" + navButtonBuild.switchNumber;
            }
            var label = "<label class='col-sm-1 control-label'>" + labelName + "</label>";
            var div = "<div class='col-sm-3'>";
            div += "<input placeholder='上传附件' class='form-control' type='file' id='" + fieldsName + "' name='" + fieldsName + "'>";
            div += "<div id='" + "_" + fieldsName + "'>" + "</div>";
            div += "</div>";
            return label.concat(div);
        }

        //每次切换更改附件 id
        navButtonBuild.updateFileId = function () {
            var html = "";
            for (var i = 0; i <= 2; i++) {
                html += "<div class='x-valid'>";
                html += writeUpdateFileId(i);
                html += "</div>";
            }
            $("#" + navButtonBuild.groupFileId).empty().append(html);
        };
    })();


    //楼栋切换号码
    navButtonBuild.switchNumber = 0;
    navButtonBuild.switchInit = function (target, number) {
        navButtonBuild.clearAll();
        navButtonBuild.switchNumber = number;
        navButtonBuild.updateFileId();
        navButtonBuild.initData(navButtonBuild.switchNumber);
        navButtonBuild.dataButtonWrite(target);
        basicIndexCommon.setBuildId(number);
        buildingModel.prototype.viewInit();
        // console.log(navButtonBuild.getAllObjArray());
    };
    //第一栋
    navButtonBuild.one = function (target, number) {
        navButtonBuild.switchInit(target, number);
    };
    //第二栋
    navButtonBuild.two = function (target, number) {
        navButtonBuild.switchInit(target, number);
    };
    //第三栋
    navButtonBuild.three = function (target, number) {
        navButtonBuild.switchInit(target, number);
    };
    //第四栋
    navButtonBuild.four = function (target, number) {
        navButtonBuild.switchInit(target, number);
    };
    navButtonBuild.dataButtonWrite = function (target) {
        $.each($("#navButtonBuild button"), function (i, n) {
            $(n).removeClass();
            $(n).addClass("btn btn-default");
        });
        //改变按钮颜色
        $(target).removeClass();
        $(target).addClass("btn btn-primary");
        $("." + buildingModel.prototype.config().sonTable).html(navButtonBuild.switchNumber + "部分");
        $("." + buildingModel.prototype.config().examineBuildingSurfaceTable).html(navButtonBuild.switchNumber + "部分");
        $("." + buildingModel.prototype.config().examineBuildingMaintenanceTable).html(navButtonBuild.switchNumber + "部分");
        $("." + buildingModel.prototype.config().examineBuildingFunctionTable).html(navButtonBuild.switchNumber + "部分");
    };
</script>