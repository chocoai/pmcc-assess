/**
 * Created by kings on 2018-12-5.
 */
//部分参数设置默认值

var AssessDefault = {};

//自动完成控件默认参数
AssessDefault.autocomplete = function () {
    var defaults={
        autoFocus: true,
        minLength: 2,
        response: function (event, ui) {
            if ((ui.content.length == 1) && (event.target.value === ui.content[0].label)) {//输入完整自动补全
                ui.item = ui.content[0];
                $(this).data('ui-autocomplete')._trigger('select', 'autocompleteselect', ui);
                //$(this).autocomplete("close");
            }
        }
    };
    return defaults;
}

