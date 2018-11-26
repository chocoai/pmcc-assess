/**
 * Created by asus on 2018/11/24.
 */

;(function ($) {
    var estateCommon = {};
    estateCommon.estateLandStateForm = $('#basicLandState');
    estateCommon.estateForm = $('#basicEstateFrm');
    //附件上传控件id数组
    estateCommon.estateFileControlIdArray = [
        'estate_floor_total_plan',
        'water_supply_plan',
        'power_supply_plan',
        'air_supply_plan',
        'heating_plan',
        'estate_floor_Appearance_figure'];

    estateCommon.getEstateId = function () {
        return estateCommon.estateForm.find('[name=id]').val();
    }

    //添加楼盘
    estateCommon.add = function ($form, callback) {
        $.ajax({
            url: getContextPath() + '/basicEstate/addEstateAndLandstate',
            data: {
                estateName: $form.find('[name=estateName]').val()
            },
            success: function (result) {
                if (result.ret) {
                    estateCommon.showEstateView(result.data);
                    if (callback) {
                        callback();
                    }
                }
            }
        })
    }

    //编辑楼盘
    estateCommon.edit = function ($form, callback) {
        $.ajax({
            url: getContextPath() + '/basicEstate/appWriteEstate',
            data: {caseEstateId: $form.find("input[name='caseEstateId']").val()},
            success: function (result) {
                if (result.ret) {
                    estateCommon.showEstateView(result.data);
                    if (callback) {
                        callback();
                    }
                }
            }
        })
    }

    //楼盘初始化by applyId
    estateCommon.init = function (applyId, callback) {
        $.ajax({
            url: getContextPath() + '/basicEstate/getBasicEstateByApplyId',
            type: 'get',
            data: {applyId: applyId},
            success: function (result) {
                if (result.ret) {
                    estateCommon.showEstateView(result.data);
                    if (callback) {
                        callback();
                    }
                }
            }
        })
    }

    //楼盘明细
    estateCommon.detail = function (applyId) {
        $.ajax({
            url: getContextPath() + '/basicEstate/getBasicEstateByApplyId',
            type: 'get',
            data: {applyId: applyId},
            success: function (result) {
                if (result.ret) {
                    estateCommon.estateForm.initLabel(result.data.estate);
                    estateCommon.estateLandStateForm.initLabel(result.data.landState);
                    estateCommon.showEstateDetail(result.data.estate.id);
                }
            }
        })
    }

    //显示楼盘对应部分信息
    estateCommon.showEstateView = function (data) {
        estateCommon.estateForm.initForm(data.estate, function () {
            //1.初始化下拉框；2.初始化上传控件；3.显示已上传的附件信息；
            AssessCommon.initAreaInfo({
                provinceTarget: estateCommon.estateForm.find('[name=province]'),
                cityTarget: estateCommon.estateForm.find('[name=city]'),
                districtTarget: estateCommon.estateForm.find('[name=district]'),
                provinceValue: data.estate.province,
                cityValue: data.estate.city,
                districtValue: data.estate.district
            });

            //初始化上传控件
            $.each(estateCommon.estateFileControlIdArray, function (i, item) {
                estateCommon.fileUpload(item);
            })
            //------------------------以上部分可只初始化一次

            //附件显示
            $.each(estateCommon.estateFileControlIdArray, function (i, item) {
                estateCommon.fileShow(item);
            })
        });
        estateCommon.estateLandStateForm.initForm(data.landState, function () {
            AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estate_total_land_use, data.landState.landUseType, function (html, data) {
                estateCommon.estateLandStateForm.find('select.landUseType').empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadDataDicByPid(data.landState.landUseType, data.landState.landUseCategory, function (html, data) {
                estateCommon.estateLandStateForm.find('select.landUseCategory').empty().html(html).trigger('change');
            });
            estateCommon.estateLandStateForm.find("select.landUseType").change(function () {
                var id = estateCommon.estateLandStateForm.find("select.landUseType").val();
                AssessCommon.loadDataDicByPid(id, null, function (html, data) {
                    estateCommon.estateLandStateForm.find('select.landUseCategory').empty().html(html).trigger('change');
                });
            });
        })
    }


    //附件上传
    estateCommon.fileUpload = function (fieldsName) {
        FileUtils.uploadFiles({
            target: fieldsName,
            disabledTarget: "btn_submit",
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.BasicEstate,
                tableId: estateCommon.getEstateId()
            },
            deleteFlag: true
        });
    }

    //附件显示
    estateCommon.fileShow = function (fieldsName, deleteFlag) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.BasicEstate,
                tableId: estateCommon.getEstateId()
            },
            deleteFlag: deleteFlag == undefined ? true : deleteFlag
        })
    }

    window.estateCommon = estateCommon;
})(jQuery);