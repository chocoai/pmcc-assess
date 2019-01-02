;(function ($) {
    /**
     * @description 楼盘js
     * @author zch
     * @type {{}}
     */
    var assessEstate = {};

    assessEstate.estateForm = $('#frm_estate');
    assessEstate.estateLandStateForm = $('#frm_estateLandState');

    //附件上传控件id数组
    assessEstate.estateFileControlIdArray = [
        'estate_floor_total_plan',
        'estate_floor_Appearance_figure',
        'water_supply_plan',
        'power_supply_plan',
        'air_supply_plan',
        'heating_plan'
    ];

    /**
     * 空字符串检测
     * @param item
     * @returns {boolean}
     */
    assessEstate.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    assessEstate.isNotBlankObject = function (obj) {
        for (var key in obj) {
            return true;
        }
        return false
    };

    assessEstate.getEstateId = function () {
        var id = assessEstate.estateForm.find('[name=id]').val();
        if (id) {
            return id;
        }
        return 0;
    };


    //附件上传
    assessEstate.fileUpload = function (fieldsName, tableName, id) {
        FileUtils.uploadFiles({
            target: fieldsName,
            disabledTarget: "btn_submit",
            formData: {
                fieldsName: fieldsName,
                tableName: tableName,
                tableId: assessEstate.getEstateId()
            },
            deleteFlag: true
        });
    };

    //附件显示
    assessEstate.fileShow = function (fieldsName, tableName, id) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: tableName,
                tableId: assessEstate.getEstateId()
            },
            deleteFlag: true
        })
    };

    /**
     * 楼盘信息 赋值
     * @param data
     */
    assessEstate.initForm = function (data) {
        AssessCommon.initAreaInfo({
            provinceTarget: assessEstate.estateForm.find("select[name='province']"),
            cityTarget: assessEstate.estateForm.find("select[name='city']"),
            districtTarget: assessEstate.estateForm.find("select[name='district']"),
            provinceValue: data.estate.province,
            cityValue: data.estate.city,
            districtValue: data.estate.district
        });
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estate_position, data.estate.position, function (html, data) {
            assessEstate.estateForm.find('select.position').empty().html(html).trigger('change');
        }, true);
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateSupplySituation, data.estate.supplyGas, function (html, data) {
            assessEstate.estateForm.find('select.supplyGas').empty().html(html).trigger('change');
        }, true);
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateSupplySituation, data.estate.supplyPower, function (html, data) {
            assessEstate.estateForm.find('select.supplyPower').empty().html(html).trigger('change');
        }, true);
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateSupplySituation, data.estate.supplyWater, function (html, data) {
            assessEstate.estateForm.find('select.supplyWater').empty().html(html).trigger('change');
        }, true);
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateSupplySituation, data.estate.drainWater, function (html, data) {
            assessEstate.estateForm.find('select.drainWater').empty().html(html).trigger('change');
        }, true);
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateSupplySituation, data.estate.supplyHeating, function (html, data) {
            assessEstate.estateForm.find('select.supplyHeating').empty().html(html).trigger('change');
        }, true);

        $.each(assessEstate.estateFileControlIdArray, function (i, n) {
            assessEstate.fileUpload(n, AssessDBKey.ExamineEstate, data.estate.id);
            assessEstate.fileShow(n, AssessDBKey.ExamineEstate, data.estate.id);
        });

        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estate_total_land_use, data.land.landUseType, function (html, data) {
            assessEstate.estateLandStateForm.find('select.landUseType').empty().html(html).trigger('change');
        }, true);
        AssessCommon.loadDataDicByKey(AssessDicKey.estatePlaneness, data.land.planeness, function (html, data) {
            assessEstate.estateLandStateForm.find('select.planeness').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estateDevelopment_degree, data.land.developmentDegree, function (html, data) {
            assessEstate.estateLandStateForm.find('select.developmentDegree').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estateShape_state, data.land.shapeState, function (html, data) {
            assessEstate.estateLandStateForm.find('select.shapeState').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estateTopographic_terrain, data.land.topographicTerrain, function (html, data) {
            assessEstate.estateLandStateForm.find('select.topographicTerrain').empty().html(html).trigger('change');
        });
        //绑定变更事件
        assessEstate.estateLandStateForm.find("select.landUseType").off('change').on('change', function () {
            AssessCommon.loadDataDicByPid($(this).val(), data.land.landUseCategory, function (html, data) {
                assessEstate.estateLandStateForm.find('select.landUseCategory').empty().html(html).trigger('change');
            });
        });
    };

    /**
     * 选择案例的楼盘后处理方法
     * @param id
     */
    assessEstate.onSelect = function (id) {

    };

    /**
     * 启用自动填充,需要引入
     */
    assessEstate.autocompleteStart = function () {
        $("#txt_estate_search").apEstate({
            onSelect: function (id, name) {
                assessEstate.onSelect(id);
            }
        });
    };

    window.assessEstate = assessEstate;
})(jQuery);