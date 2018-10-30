<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>楼栋基本信息 </h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼栋编号
                </label>
                <div class="col-sm-3">
                    <input type="hidden" name="caseBuildingMainId" id="caseBuildingMainId">
                    <input type="text" placeholder="楼栋编号 (必要的查询下面楼栋所需)" name="identifier"
                           class="form-control" id="identifier">
                </div>
            </div>
        </div>
    </form>

    <form class="form-horizontal" id="basicBuildFrm">
        <input type="hidden" name="id">
        <div class="form-group" id="navButtonBuild">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                </label>
                <div class="col-sm-2">
                    <div class="btn-group" data-toggle="buttons">
                        <button class="btn btn-default" onclick="navButtonBuild.one(this,1)">楼栋基础</button>
                    </div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                </label>
                <div class="col-sm-2">
                    <div class="btn-group" data-toggle="buttons">
                        <button class="btn btn-default" onclick="navButtonBuild.two(this,2)">第二部分</button>
                    </div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                </label>
                <div class="col-sm-2">
                    <div class="btn-group" data-toggle="buttons">
                        <button class="btn btn-default" onclick="navButtonBuild.three(this,3)">第三部分</button>
                    </div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                </label>
                <div class="col-sm-2">
                    <div class="btn-group" data-toggle="buttons">
                        <div class="btn-group" data-toggle="buttons">
                            <button class="btn btn-default" onclick="navButtonBuild.four(this,4)">第四部分</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
                    户型区间<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="户型区间" name="unitInterval"
                           class="form-control" required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
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
                    公共设施使用费<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="公共设施使用费(数字)" name="facilitiesUseFee"
                           data-rule-number='true' class="form-control" required="required">
                </div>
            </div>

        </div>

        <div class="form-group">
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
                    所在位置<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="所在位置" name="location" class="form-control"
                           required="required">
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
                    <select name="buildingStructureLower"
                            class="form-control search-select select2 buildingStructureLower">
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

<script type="text/javascript">
    var navButtonBuild;
    (function () {
        navButtonBuild = new Object();
        navButtonBuild.flag = true;
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
                            // item[fieldsName] = data.id ;
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
                    tableId: objectData.isNotBlank(id) ? id : "0",
                    creater: "${currUserAccount}"
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
                    creater: "${currUserAccount}"
                },
                deleteFlag: true
            })
        };
        //生成从minNum到maxNum的随机数 (请尽量设置大一些 以免重复)
        navButtonBuild.randomNum = function (minNum, maxNum) {
            switch (arguments.length) {
                case 1:
                    return parseInt(Math.random() * minNum + 1, 10);
                    break;
                case 2:
                    return parseInt(Math.random() * (maxNum - minNum + 1) + minNum, 10);
                    break;
                default:
                    return 0;
                    break;
            }
        };
        //编号 规则
        navButtonBuild.rule = function (identifierNumber) {
            var date = new Date();
            //利用了时间轴 和 随机数 的积 来生成 唯一编号
            var str = Date.now().toString();
            str = str.substring(str.length - 6, str.length);
            var identifier = "";
            identifier = navButtonBuild.randomNum(100, 90000);
            identifier = parseInt(identifier) * parseInt(str);
            identifier = identifierNumber + ":" + identifier;
            return identifier;
        };
        //编号 write
        navButtonBuild.identifierWrite = function () {
            var identifier = $("#identifier").val();
            if (navButtonBuild.isNotBlank(identifier)) {
                identifier = navButtonBuild.rule(identifier);
                $("#identifier").val(identifier);
            }
        };
        var objArray = [{}, {}, {}, {}, {}];
        //获取存储的所有数据
        navButtonBuild.getAllObjArray = function () {
            return objArray;
        };
        /**
         * @author:  zch
         * 描述:根据索引获取数据
         **/
        navButtonBuild.getObjArray = function (index) {
            var data = null;
            if (navButtonBuild.isNotBlank(index)) {
                data = objArray[index];
                return data;
            }
        };
        /**
         * @author:  zch
         * 描述:设置数据
         **/
        navButtonBuild.setObjArrayElement = function (index, data) {
            objArray[index] = data;
        };

        //收集数据
        navButtonBuild.tempSaveData = function () {
            var data = formParams(objectData.config.basicBuilding.frm);
            var switchNumber = navButtonBuild.switchNumber;
            if (navButtonBuild.isNotBlank(switchNumber)) {
                navButtonBuild.setObjArrayElement(switchNumber, data);
            }
        };
        //赋值
        navButtonBuild.initData = function (switchNumber) {
            var data = navButtonBuild.getObjArray(switchNumber);
            $("#" + objectData.config.basicBuilding.frm).initForm(data);
            objectData.select2Assignment(objectData.config.basicBuilding.frm, data.buildingCategory, "buildingCategory");
            objectData.select2Assignment(objectData.config.basicBuilding.frm, data.buildingStructure, "buildingStructure");
            objectData.select2Assignment(objectData.config.basicBuilding.frm, data.propertyType, "propertyType");
            objectData.select2Assignment(objectData.config.basicBuilding.frm, data.buildingStructureLower, "buildingStructureLower");
            objectData.select2Assignment(objectData.config.basicBuilding.frm, data.builderId, "builderId");
            objectData.select2Assignment(objectData.config.basicBuilding.frm, data.propertyId, "propertyId");
            $.each(objectData.config.basicBuilding.files, function (i, n) {
                navButtonBuild.uploadFile2(n + "" + navButtonBuild.switchNumber, data.id);
                navButtonBuild.showFile(n + "" + navButtonBuild.switchNumber, data.id);
            });
        };
        navButtonBuild.clearAll = function () {
            $("#" + objectData.config.basicBuilding.frm).clearAll();
        };
        navButtonBuild.inputBlur = function () {
            $("#" + objectData.config.basicBuilding.frm).find("input").each(function (i, n) {
                $(n).blur(function () {
                    var str = $(n).val();
                    if (navButtonBuild.isNotBlank(str)) {
                        navButtonBuild.tempSaveData();
                    }
                });
            });
            $("#" + objectData.config.basicBuilding.frm).find("select").each(function (i, n) {
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
                fieldsName = objectData.config.basicBuilding.files.building_floor_plan + "" + navButtonBuild.switchNumber;
            }
            if (num == 1) {
                labelName = "外装图";
                fieldsName = objectData.config.basicBuilding.files.building_figure_outside + "" + navButtonBuild.switchNumber;
            }
            if (num == 2) {
                labelName = "外观图";
                fieldsName = objectData.config.basicBuilding.files.building_floor_Appearance_figure + "" + navButtonBuild.switchNumber;
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
    };
    navButtonBuild.init = function () {
        if (navButtonBuild.flag){
            this.inputBlur();
            $("#identifier").bind("blur", navButtonBuild.identifierWrite);
            $.ajax({
                url: "${pageContext.request.contextPath}/basicBuilding/initBuilding",
                type: "post",
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        toastr.success('楼栋初始化成功!');
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
            navButtonBuild.flag = false;
        }
    };

</script>