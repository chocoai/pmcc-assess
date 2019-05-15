var basicCommon = {};

basicCommon.basicApplyForm = $('#basicApplyFrm');

/**
 * 获取案例或者查勘 entity json
 * @returns {*|jQuery}
 */
basicCommon.getSurveyJson = function () {
    var surveySceneExploreJson = $("#surveySceneExploreJson").val();
    var surveyCaseStudyJson = $("#surveyCaseStudyJson").val();
    if (surveySceneExploreJson) {
        return JSON.parse(surveySceneExploreJson);
    }
    if (surveyCaseStudyJson) {
        return JSON.parse(surveyCaseStudyJson);
    }
};

/**
 * 更新申报数据
 * @param data
 * @param callback
 */
basicCommon.update = function (data, callback) {
    $.ajax({
        url: getContextPath() + '/basicApply/saveAndUpdate',
        data: data,
        type: 'post',
        success: function (result) {
            if (result.ret) {
                callback(result.data);
            } else {
                console.log(result.errmsg);
                Alert(result.errmsg);
            }
        }
    })
};

basicCommon.getApplyId = function () {
    return basicCommon.basicApplyForm.find('[name=id]').val();
};

basicCommon.getPlanDetailsId = function () {
    var data = this.getSurveyJson();
    return data.planDetailsId;
};

basicCommon.getProjectId = function () {
    var data = this.getSurveyJson();
    return data.projectId;
};

basicCommon.getCaseEstateId = function () {
    return basicCommon.basicApplyForm.find("input[name='caseEstateId']").val();
};

basicCommon.getcaseBuildingId = function () {
    return basicCommon.basicApplyForm.find("input[name='caseBuildingId']").val();
};

basicCommon.getCaseUnitId = function () {
    return basicCommon.basicApplyForm.find("input[name='caseUnitId']").val();
};

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
            $.ajax({
                url: getContextPath() + "/dataLandDetailAchievement/list",
                type: "get",
                data: {levelDetailId: data.id},
                success: function (result) {
                    estateCommon.landLevelLoadHtml(result.data);
                }
            })
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

basicCommon.valid = function () {
    $('.task_examine_item_tab a[data-name=estate]').tab('show');

    if (estateCommon){
        if (estateCommon.estateForm.size() >= 1) {
            if (!estateCommon.estateForm.valid('楼盘数据不完整')) {
                return false;
            }
        }
    }

    if (estateCommon){
        if (estateCommon.estateLandStateForm.size() >= 1) {
            if (!estateCommon.estateLandStateForm.valid('楼盘数据不完整')) {
                return false;
            }
        }
    }

    $('.task_examine_item_tab a[data-name=building]').tab('show');

    try {
        if (buildingCommon) {
            if (buildingCommon.buildingForm.size() >= 1) {
                if (!buildingCommon.buildingForm.valid('楼栋数据不完整')) {
                    return false;
                }
            }
        }
    } catch (e) {
    }
    $('.task_examine_item_tab a[data-name=unit]').tab('show');

    try {
        if (unitCommon) {
            if (unitCommon.unitForm.size() >= 1) {
                if (!unitCommon.unitForm.valid('单元数据不完整')) {
                    return false;
                }
            }
        }
    } catch (e) {
    }

    $('.task_examine_item_tab a[data-name=house]').tab('show');
    if (houseCommon){
        if (houseCommon.houseForm.size() >= 1) {
            if (!houseCommon.houseForm.valid('房屋数据不完整')) {
                return false;
            }
        }
    }

    if (houseCommon){
        if (houseCommon.houseTradingForm.size() >= 1) {
            if (houseCommon.houseTradingForm.length > 0 && !houseCommon.houseTradingForm.valid('房屋数据不完整')) {
                return false;
            }
        }
    }
    return true;
};

basicCommon.getFormData = function () {
    var item = {};
    if (basicCommon.basicApplyForm.size() >= 1) {
        item.basicApply = formSerializeArray(basicCommon.basicApplyForm);
    }

    if (estateCommon){
        if (estateCommon.estateForm.size() >= 1) {
            item.basicEstate = formSerializeArray(estateCommon.estateForm);
        }

        if (estateCommon.estateLandStateForm.size() >= 1) {
            item.basicEstateLandState = formSerializeArray(estateCommon.estateLandStateForm);
        }
    }

    try {
        if (buildingCommon) {
            if (buildingCommon.buildingForm.size() >= 1) {
                item.basicBuilding = formSerializeArray(buildingCommon.buildingForm);
            }
        }
    } catch (e) {
    }

    try {
        if (unitCommon) {
            if (unitCommon.unitForm.size() >= 1) {
                item.basicUnit = formSerializeArray(unitCommon.unitForm);
            }
        }
    } catch (e) {
    }

    if (houseCommon){
        if (houseCommon.houseForm.size() >= 1) {
            item.basicHouse = formSerializeArray(houseCommon.houseForm);
        }

    }

    if (houseCommon){
        if (houseCommon.houseTradingForm.size() >= 1) {
            var data = formSerializeArray(houseCommon.houseTradingForm);
            try {
                var str = data.id.split(",");
                if (str.length > 1){
                    data.id = str[0];
                }
            } catch (e) {
            }
            item.basicTrading = data;
        }
    }

    if (damagedDegree){
        item.basicDamagedDegree = damagedDegree.getFormData();
    }

    item.survey = basicCommon.getSurveyJson();
    return item;
};

