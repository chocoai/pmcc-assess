/**
 * Created by kings on 2018-3-14.
 */
//封装日期utils
(function () {
    var dateUtils = {
        initDatetimepickerDate: function (obj, param) {
            initDatetimepickerDate(obj, param);
        },
        initDatetimepickerTime: function (obj, param) {
            initDatetimepickerTime(obj, param);
        },
        dateSectionChoose: function (objStart, objEnd, param) {
            dateSectionChoose(objStart, objEnd, param);
        }
    };
    window.DateUtils = dateUtils;
})(jQuery)

function initDatetimepickerDate(obj, param) {
    var defaluts = datepickerOptions.date;
    if ($(obj).attr("data-date-startView")) {
        defaluts.startView = $(obj).attr("data-date-startView");
    }
    if ($(obj).attr("data-date-minView")) {
        defaluts.minView = $(obj).attr("data-date-minView");
    }
    if (param) {
        defaluts = $.extend({}, defaluts, param);
    }
    $(obj).datetimepicker(defaluts);
    $(obj).attr("readonly", "readonly");
}

function initDatetimepickerTime(obj, param) {
    var defaluts = datepickerOptions.dateTime;
    if (param) {
        defaluts = $.extend({}, defaluts, param);
    }
    $(obj).datetimepicker(defaluts);
    $(obj).attr("readonly", "readonly");
}

//区间选择
function dateSectionChoose(objStart, objEnd, param) {
    $(objStart).click(function () {
        $(objStart).datetimepicker('setEndDate', formatDate($(objEnd).val(), true));
    })
    $(objEnd).click(function () {
        $(objEnd).datetimepicker('setStartDate', formatDate($(objStart).val(), true));
    })
}