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
    var data = this.getSurveyJson();
    return data.basicApplyId;
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
    assessLandLevel.select({
        province: $form.find('[name=province]').val(),
        city: $form.find('[name=city]').val(),
        success: function (data) {
            $(this_).parent().prev().val(data.name);
            $(this_).parent().prev().prev().val(data.id);
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
    if (!estateCommon.estateForm.valid()) {
        toastr.success('楼盘数据不完整');
        return false;
    }
    if (!estateCommon.estateLandStateForm.valid()) {
        toastr.success('楼盘数据不完整');
        return false;
    }
    if (!buildingCommon.buildingForm.valid()) {
        toastr.success('楼栋数据不完整');
        return false;
    }
    if (!unitCommon.unitForm.valid()) {
        toastr.success('单元数据不完整');
        return false;
    }
    if (!houseCommon.houseForm.valid()) {
        toastr.success('房屋数据不完整');
        return false;
    }
    if (houseCommon.houseTradingForm.length > 0 && !houseCommon.houseTradingForm.valid()) {
        toastr.success('房屋数据不完整');
        return false;
    }
    return true;
};

basicCommon.getFormData = function () {
    var item = {};
    item.basicApply = formSerializeArray(basicCommon.basicApplyForm);
    item.basicEstate = formSerializeArray(estateCommon.estateForm);
    item.basicEstateLandState = formSerializeArray(estateCommon.estateLandStateForm);
    item.basicBuilding = formSerializeArray(buildingCommon.buildingForm);
    item.basicUnit = formSerializeArray(unitCommon.unitForm);
    item.basicHouse = formSerializeArray(houseCommon.houseForm);
    item.basicTrading = formSerializeArray(houseCommon.houseTradingForm);
    item.basicDamagedDegree = damagedDegree.getFormData();
    item.survey = basicCommon.getSurveyJson();
    return item;
};

