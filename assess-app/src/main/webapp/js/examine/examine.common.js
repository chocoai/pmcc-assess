

var examineCommon = {} ;

//开发商选择
examineCommon.developerSelect = function (this_) {
    assessDeveloper.select(function (row) {
        $(this_).parent().prev().val(row.name);
        $(this_).parent().prev().prev().val(row.id);
    });
};

//土地级别选择
examineCommon.landLevelSelect = function (this_) {
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
examineCommon.propertySelect = function (this_) {
    assessProperty.select(function (row) {
        $(this_).parent().prev().val(row.name);
        $(this_).parent().prev().prev().val(row.id);
    });
};
//建造商选择
examineCommon.builderSelect = function (this_) {
    assessBuilder.select(function (row) {
        $(this_).parent().prev().val(row.name);
        $(this_).parent().prev().prev().val(row.id);
    });
};
//板块选择
examineCommon.blockSelect = function (this_) {
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

