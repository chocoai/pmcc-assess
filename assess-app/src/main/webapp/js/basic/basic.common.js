/**
 * Created by kings on 2018-11-30.
 */

var basicCommon = {};
basicCommon.basicApplyForm = $('#basicApplyFrm');
basicCommon.contentTabPanel = $('#contentTabPanel');

basicCommon.getApplyId = function () {
    return basicCommon.basicApplyForm.find('[name=id]').val();
}

basicCommon.getMarkerAreaInHeight = '80%';
basicCommon.getMarkerAreaInWidth = '80%';

//开发商选择
basicCommon.developerSelect = function (this_) {
    assessDeveloper.select(function (row) {
        $(this_).parent().prev().val(row.name);
        $(this_).parent().prev().prev().val(row.id);
    });
};

//土地级别选择
basicCommon.landLevelSelect = function (this_) {
    var $form = $(this_).closest('form');
    var formGroup = $(this_).closest('.form-group');
    assessLandLevel.select({
        province: $form.find('[name=province]').val(),
        city: $form.find('[name=city]').val(),
        success: function (data) {
            formGroup.find("input[name='landLevel']").val(data.id);
            formGroup.find("input[name='landLevelName']").val(data.name);
            // $.ajax({
            //     url: getContextPath() + "/dataLandDetailAchievement/landLevelFilter",
            //     type: "get",
            //     data: {levelDetailId: data.id},
            //     success: function (result) {
            //         estateCommon.landLevelLoadHtml(result.data);
            //     }
            // })
        }
    })
};
//物业选择
basicCommon.propertySelect = function (this_) {
    assessProperty.select(function (row) {
        $(this_).parent().prev().val(row.name);
        $(this_).parent().prev().prev().val(row.id);
    });
};
//建造商选择
basicCommon.builderSelect = function (this_) {
    assessBuilder.select(function (row) {
        $(this_).parent().prev().val(row.name);
        $(this_).parent().prev().prev().val(row.id);
    });
};
//板块选择
basicCommon.blockSelect = function (this_) {
    var $form = $(this_).closest('form');
    assessBlock.select({
        province: $form.find('[name=province]').val(),
        city: $form.find('[name=city]').val(),
        success: function (row) {
            $(this_).parent().prev().val(row.name);
            $(this_).parent().prev().prev().val(row.id);
        }
    })
};

//显示楼盘tab
basicCommon.showEstateTab = function (mode) {
    basicCommon.contentTabPanel.find('[id=caseEstate]').addClass('active');
    var a = basicCommon.contentTabPanel.find('[href=#caseEstate]');
    a.closest('li').show();
    a.tab('show');
    if (mode)
        basicCommon.basicApplyForm.find('[name=estatePartInMode]').val(mode);
}

//显示楼栋tab
basicCommon.showBuildingTab = function (mode) {
    basicCommon.contentTabPanel.find('[id=caseBuilding]').addClass('active');
    var a = basicCommon.contentTabPanel.find('[href=#caseBuilding]');
    a.closest('li').show();
    a.tab('show');
    if (mode)
        basicCommon.basicApplyForm.find('[name=buildingPartInMode]').val(mode);
}

//显示单元tab
basicCommon.showUnitTab = function (mode) {
    basicCommon.contentTabPanel.find('[id=caseUnit]').addClass('active');
    var a = basicCommon.contentTabPanel.find('[href=#caseUnit]');
    a.closest('li').show();
    a.tab('show');
    if (mode)
        basicCommon.basicApplyForm.find('[name=unitPartInMode]').val(mode);
}

//显示房屋tab
basicCommon.showHouseTab = function (mode) {
    basicCommon.contentTabPanel.find('[id=caseHouse]').addClass('active');
    var a = basicCommon.contentTabPanel.find('[href=#caseHouse]');
    a.closest('li').show();
    a.tab('show');
    if (mode)
        basicCommon.basicApplyForm.find('[name=housePartInMode]').val(mode);
}

//隐藏tab标签
basicCommon.hideTab = function (_this) {
    var a = $(_this).closest('a');
    var href = a.attr('href').replace('#', '');
    switch (href) {
        case "caseEstate":
            basicCommon.basicApplyForm.find('[name=estatePartInMode]').val('');
            break;
        case "caseBuilding":
            basicCommon.basicApplyForm.find('[name=buildingPartInMode]').val('');
            break;
        case "caseUnit":
            basicCommon.basicApplyForm.find('[name=unitPartInMode]').val('');
            break;
        case "caseHouse":
            basicCommon.basicApplyForm.find('[name=housePartInMode]').val('');
            break;
    }
    a.closest('[role="tabpanel"]').find('[id=' + href + ']').removeClass('active');
    a.closest('li').hide();
    return false;
}

//隐藏所有标签
basicCommon.hideAllTab = function () {
    basicCommon.basicApplyForm.find('[name=estatePartInMode]').val('');
    basicCommon.basicApplyForm.find('[name=buildingPartInMode]').val('');
    basicCommon.basicApplyForm.find('[name=unitPartInMode]').val('');
    basicCommon.basicApplyForm.find('[name=housePartInMode]').val('');
    $("#contentTabPanel").find('[role="tabpanel"]').find('[id="caseEstate"]').removeClass('active');
    $("#contentTabPanel").find('[role="tabpanel"]').find('[id="caseBuilding"]').removeClass('active');
    $("#contentTabPanel").find('[role="tabpanel"]').find('[id="caseUnit"]').removeClass('active');
    $("#contentTabPanel").find('[role="tabpanel"]').find('[id="caseHouse"]').removeClass('active');
    $("#contentTabPanel").find('li').hide();
}

//数据验证
//1.验证是否有申请信息
//2.验证表单数据是否填写完整
//3.验证填写的数据是否正确
//4.验证楼盘、楼栋、单元、房屋这些信息是否正确关联
//5.数据是否重复申请，包含案例的中的数据以及正在提交申请的数据

//处理数据验证方案
//1.有部分字段有验证但为隐藏时则不做验证，有一部分字段有验证属性隐藏时依然需要被验证
//2.保存时只验证这些字段是否填写规范，而不验证数据是否必填。当为提交时则都需要验证

//保存草稿时验证
basicCommon.saveDraftValid = function () {
    var applyForm = formSerializeArray(basicCommon.basicApplyForm);
    if (!basicCommon.hasApplyData(applyForm)) {
        toastr.info('还未填写任何申请信息');
        return false;
    }
    if (!basicCommon.isRelation(applyForm)) {
        return false;
    }

    //取消必填项验证
    basicCommon.contentTabPanel.find('[required]').each(function () {
        $(this).removeAttr('required').attr('data-required', 'required');
    })
    if (!basicCommon.isComplete(applyForm, true)) {
        return false;
    }
    return true;
}

//提交表单时验证
basicCommon.submitFormValid = function () {
    var applyForm = formSerializeArray(basicCommon.basicApplyForm);
    if (!basicCommon.hasApplyData(applyForm)) {
        toastr.info('还未填写任何申请信息');
        return false;
    }
    if (!basicCommon.isRelation(applyForm)) {
        return false;
    }

    //添加必填项验证
    basicCommon.contentTabPanel.find('[data-required]').each(function () {
        $(this).attr('required', 'required').removeAttr('data-required');
    })
    if (!basicCommon.isComplete(applyForm, false)) {
        return false;
    }

    return true;
}

//是否有申请信息
basicCommon.hasApplyData = function (applyForm) {
    if (applyForm.estatePartInMode || applyForm.buildingPartInMode || applyForm.unitPartInMode || applyForm.housePartInMode) {
        return true;
    } else {
        return false;
    }
}

//表单数据是否填写完整
basicCommon.isComplete = function (applyForm, isDraft) {
    var options = {}
    if (applyForm.estatePartInMode) {
        basicCommon.showEstateTab();
        options.msg = '请检查楼盘基本信息';
        if (!estateCommon.estateForm.valid(options)) {
            return false;
        }
        if (!isDraft) {
            // if (estateCommon.estateForm.find('#_' + estateCommon.estateFileControlIdArray[0]).html().length <= 0) {
            //     toastr.info('请上传楼盘总平图');
            //     return false;
            // }
            if (estateCommon.estateForm.find('#_' + estateCommon.estateFileControlIdArray[1]).html().length <= 0) {
                toastr.info('请上传楼盘外观图');
                return false;
            }
        }
        options.msg = '请检查土地基本信息';
        if (!estateCommon.estateLandStateForm.valid(options)) {
            return false;
        }
    }

    if (applyForm.buildingPartInMode && applyForm.type != 2) {
        basicCommon.showBuildingTab();
        options.msg = '请检查楼栋信息';
        if (!buildingCommon.buildingForm.valid(options.msg)) {
            return false;
        }
    }
    if (applyForm.unitPartInMode) {
        basicCommon.showUnitTab();
        options.msg = '请检查单元信息';
        if (!unitCommon.unitForm.valid(options.msg)) {
            return false;
        }
    }
    if (applyForm.housePartInMode) {
        basicCommon.showHouseTab();
        options.msg = '请检查房屋基本信息';
        if (!houseCommon.houseForm.valid(options.msg)) {
            return false;
        }
        options.msg = '请检查房屋交易信息';
        if (!houseCommon.houseTradingForm.valid(options.msg)) {
            return false;
        }
    }
    return true;
}

//楼盘、楼栋、单元、房屋这些信息是否正确关联
basicCommon.isRelation = function (applyForm) {
    //从下到上依次确定是否被关联上
    if (applyForm.housePartInMode) {
        if (applyForm.housePartInMode == 'upgrade') {//如果是升级，则必须有caseHouseId
            if (!applyForm.caseHouseId) {
                toastr.info('未找到房屋升级的源案例信息');
                return false;
            }
        } else {//如果是新增，单元也必须为新增或者选择了案例中的数据
            if (!applyForm.unitPartInMode && !applyForm.caseUnitId) {
                toastr.info('房屋信息未关联到所属单元中');
                return false;
            }
        }
    }

    if (applyForm.unitPartInMode) {
        if (applyForm.unitPartInMode == 'upgrade') {//如果是升级，则必须有caseUnitId
            if (!applyForm.caseUnitId) {
                toastr.info('未找到单元升级的源案例信息');
                return false;
            }
        } else {//如果是新增，楼栋也必须为新增或者选择了案例中的数据
            if (!applyForm.buildingPartInMode && !applyForm.caseBuildingId) {
                toastr.info('单元信息未关联到所属楼栋中');
                return false;
            }
        }
    }

    if (applyForm.buildingPartInMode) {
        if (applyForm.buildingPartInMode == 'upgrade') {//如果是升级，则必须有caseBuildingId
            if (!applyForm.caseBuildingId) {
                toastr.info('未找到楼栋升级的源案例信息');
                return false;
            }
        } else {//如果是新增，楼盘也必须为新增或者选择了案例中的数据
            if (!applyForm.estatePartInMode && !applyForm.caseEstateId) {
                toastr.info('楼栋信息未关联到所属楼盘中');
                return false;
            }
        }
    }
    return true;
}

//获取表单数据
basicCommon.getFormData = function () {
    var item = {};
    var basicApply = formSerializeArray(basicCommon.basicApplyForm);
    var isRetain = false;
    if (!basicApply.housePartInMode) {
        basicApply.houseNumber = '';
        if (!basicApply.unitPartInMode) {
            basicApply.unitNumber = '';
            if (!basicApply.buildingPartInMode) {
                basicApply.buildingNumber = '';
            }
        }
    }
    item.basicApply = basicApply;
    if (basicApply.estatePartInMode) {
        item.basicEstate = formSerializeArray(estateCommon.estateForm);
        if (estateCommon.estateLandStateForm.size() >= 1) {
            var data = formSerializeArray(estateCommon.estateLandStateForm);
            var landLevelContent = [];
            if (data.landFactorTotalScore) {
                var landFactorTotalScore = 0;
                data.landFactorTotalScore.split(",").forEach(function (value, index) {
                    landFactorTotalScore += Number(value);
                });
                data.landFactorTotalScore = landFactorTotalScore;
            }
            estateCommon.estateLandStateForm.find("input[name='landLevelContent']").each(function (i, n) {
                var group = $(n).closest(".group");
                var dataLandLevelAchievement = group.find("input[name='dataLandLevelAchievement']").val();
                var obj = JSON.parse($(n).val());
                var dataObject = [];
                obj.forEach(function (value, index) {
                    if (value.id == dataLandLevelAchievement) {
                        value.modelStr = "update";
                    }
                    dataObject.push(value);
                });
                landLevelContent.push(dataObject);
            });
            if (landLevelContent.length >= 1) {
                data.landLevelContent = JSON.stringify(landLevelContent);
            }
            item.basicEstateLandState = data;
        }
    }
    if (basicApply.buildingPartInMode) {
        item.basicBuilding = formSerializeArray(buildingCommon.buildingForm);
        item.basicBuilding.vSpecifications = [];
        buildingCommon.buildingForm.find('.form-group').each(function () {
            var vSpecification = {};
            var specificationName = $(this).find('[name^=specificationName]').val();
            var specificationContent = $(this).find('[name^=specificationContent]').val();
            if (specificationName && specificationContent) {
                vSpecification.specificationName = specificationName;
                vSpecification.specificationContent = specificationContent;
                item.basicBuilding.vSpecifications.push(vSpecification);
            }
        });
    }
    if (basicApply.unitPartInMode) {
        item.basicUnit = formSerializeArray(unitCommon.unitForm);
    }
    if (basicApply.housePartInMode) {
        item.basicHouse = formSerializeArray(houseCommon.houseForm);
        item.basicTrading = formSerializeArray(houseCommon.houseTradingForm);
        item.basicDamagedDegree = damagedDegree.getFormData();
    }
    return item;
};




