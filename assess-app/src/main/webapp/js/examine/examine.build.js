/**
 * Created by asus on 2018/11/24.
 */

;(function ($) {
    var buildingCommon = {};
    buildingCommon.buildingMainForm = $('#basicBuildingMainFrm');
    buildingCommon.buildingForm = $('#basicBuildingFrm');
    //附件上传控件id数组
    buildingCommon.buildingFileControlIdArray = ['building_floor_plan', 'building_figure_outside', 'building_floor_Appearance_figure'];

    buildingCommon.getBuildingId = function () {
        var id = buildingCommon.buildingForm.find('[name=id]').val();
        if (id){
            return id;
        }
        return 0;
    };


    buildingCommon.initForm = function (data) {
        //1.初始化下拉框；2.初始化上传控件；3.显示已上传的附件信息；4.加载从表数据
        buildingCommon.buildingForm.find("select.buildingStructureType").off('change').on('change', function () {
            AssessCommon.loadDataDicByPid($(this).val(), data.buildingStructureCategory, function (html, data) {
                buildingCommon.buildingForm.find("select.buildingStructureCategory").empty().html(html).trigger('change');
            });
            result.data.buildingStructureCategory = null;
        });
        buildingCommon.buildingForm.find('select.propertyType').off('change').on('change', function () {
            AssessCommon.loadDataDicByPid($(this).val(), data.propertyCategory, function (html, data) {
                buildingCommon.buildingForm.find('select.propertyCategory').empty().html(html).trigger('change');
            });
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_type, data.propertyType, function (html, data) {
            buildingCommon.buildingForm.find('select.propertyType').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_structure, data.buildingStructureType, function (html, data) {
            buildingCommon.buildingForm.find('select.buildingStructureType').empty().html(html).trigger('change');
        });

        //初始化上传控件
        $.each(buildingCommon.buildingFileControlIdArray, function (i, item) {
            buildingCommon.fileUpload(item);
        });

        //附件显示
        $.each(buildingCommon.buildingFileControlIdArray, function (i, item) {
            buildingCommon.fileShow(item);
        });
    };


    //附件上传
    buildingCommon.fileUpload = function (fieldsName) {
        FileUtils.uploadFiles({
            target: fieldsName,
            disabledTarget: "btn_submit",
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.ExamineBuilding,
                tableId: buildingCommon.getBuildingId()
            },
            deleteFlag: true
        });
    };

    //附件显示
    buildingCommon.fileShow = function (fieldsName) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.ExamineBuilding,
                tableId: buildingCommon.getBuildingId()
            },
            deleteFlag: true
        })
    };


    window.buildingCommon = buildingCommon;
})(jQuery);