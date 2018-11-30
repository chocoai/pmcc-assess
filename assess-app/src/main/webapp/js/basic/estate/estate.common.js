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
    estateCommon.add = function (_this, callback) {
        var estateName = $(_this).closest('form').find('[name=estateName]').val();
        if (!estateName) {
            toastr.info('请填写楼盘名称！');
            return false;
        }
        $.ajax({
            url: getContextPath() + '/basicEstate/addEstateAndLandstate',
            data: {
                estateName: estateName
            },
            success: function (result) {
                if (result.ret) {
                    estateCommon.showEstateView(result.data);
                    if (callback) {
                        callback($(_this).attr('data-mode'));
                    }
                }
            }
        })
    }

    //升级楼盘
    estateCommon.upgrade = function (_this, callback) {
        var caseEstateId = $(_this).closest('form').find("input[name='caseEstateId']").val();
        if(!caseEstateId){
            toastr.info('请选择系统中已存在的楼盘信息！');
            return false;
        }
        $.ajax({
            url: getContextPath() + '/basicEstate/appWriteEstate',
            data: {caseEstateId: caseEstateId},
            success: function (result) {
                if (result.ret) {
                    estateCommon.showEstateView(result.data);
                    if (callback) {
                        callback($(_this).attr('data-mode'));
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
                    estateCommon.estateForm.initLabel(result.data.basicEstate);
                    estateCommon.estateLandStateForm.initLabel(result.data.basicEstateLandState);
                }
            }
        })
    }

    //显示楼盘对应部分信息
    estateCommon.showEstateView = function (data) {
        estateCommon.estateForm.initForm(data.basicEstate, function () {
            //1.初始化下拉框；2.初始化上传控件；3.显示已上传的附件信息；
            AssessCommon.initAreaInfo({
                provinceTarget: estateCommon.estateForm.find('[name=province]'),
                cityTarget: estateCommon.estateForm.find('[name=city]'),
                districtTarget: estateCommon.estateForm.find('[name=district]'),
                provinceValue: data.basicEstate.province,
                cityValue: data.basicEstate.city,
                districtValue: data.basicEstate.district
            });

            AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateSupplySituation, data.basicEstate.supplyGas, function (html, data) {
                estateCommon.estateForm.find('select.supplyGas').empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateSupplySituation, data.basicEstate.supplyPower, function (html, data) {
                estateCommon.estateForm.find('select.supplyPower').empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateSupplySituation, data.basicEstate.supplyWater, function (html, data) {
                estateCommon.estateForm.find('select.supplyWater').empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateSupplySituation, data.basicEstate.supplyHeating, function (html, data) {
                estateCommon.estateForm.find('select.supplyHeating').empty().html(html).trigger('change');
            }, true);

            //初始化上传控件
            $.each(estateCommon.estateFileControlIdArray, function (i, item) {
                estateCommon.fileUpload(item);
            })

            //附件显示
            $.each(estateCommon.estateFileControlIdArray, function (i, item) {
                estateCommon.fileShow(item);
            })
        });
        estateCommon.estateLandStateForm.initForm(data.basicEstateLandState, function () {
            //绑定变更事件
            estateCommon.estateLandStateForm.find("select.landUseType").change(function () {
                var id = estateCommon.estateLandStateForm.find("select.landUseType").val();
                AssessCommon.loadDataDicByPid(id, data.basicEstateLandState.landUseCategory, function (html, data) {
                    estateCommon.estateLandStateForm.find('select.landUseCategory').empty().html(html).trigger('change');
                });
                data.basicEstateLandState.landUseCategory = null;//第一次执行成功后置为空
            });
            AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estate_total_land_use, data.basicEstateLandState.landUseType, function (html, data) {
                estateCommon.estateLandStateForm.find('select.landUseType').empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadDataDicByKey(AssessDicKey.estatePlaneness, data.basicEstateLandState.planeness, function (html, data) {
                estateCommon.estateLandStateForm.find('select.planeness').empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estateDevelopment_degree, data.basicEstateLandState.developmentDegree, function (html, data) {
                estateCommon.estateLandStateForm.find('select.developmentDegree').empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estateShape_state, data.basicEstateLandState.shapeState, function (html, data) {
                estateCommon.estateLandStateForm.find('select.shapeState').empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estateTopographic_terrain, data.basicEstateLandState.topographicTerrain, function (html, data) {
                estateCommon.estateLandStateForm.find('select.topographicTerrain').empty().html(html).trigger('change');
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