/**
 * Created by kings on 2018-11-9.
 */

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
                tableId: objectData.isNotBlank(id) ? id : "0",
                // creater: "${currUserAccount}"
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
                // creater: "${currUserAccount}"
            },
            deleteFlag: true
        })
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
        data.part = switchNumber;
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
    buildingModel.prototype.viewInit();
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
    $("."+buildingModel.prototype.config().sonTable).html(navButtonBuild.switchNumber+"部分");
    $("."+buildingModel.prototype.config().examineBuildingSurfaceTable).html(navButtonBuild.switchNumber+"部分");
    $("."+buildingModel.prototype.config().examineBuildingMaintenanceTable).html(navButtonBuild.switchNumber+"部分");
    $("."+buildingModel.prototype.config().examineBuildingFunctionTable).html(navButtonBuild.switchNumber+"部分");
};


