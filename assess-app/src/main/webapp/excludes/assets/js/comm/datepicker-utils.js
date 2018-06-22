/**
 * Created by kings on 2018-4-4.
 */
(function ($) {
    var datepickerOptions = {
        date: {
            autoclose: true,
            minView: "month",
            todayBtn: "linked",
            language: "zh-CN",
            clearBtn: true
        },
        dateTime: {
            autoclose: true,
            todayBtn: "linked",
            language: "zh-CN",
            minView: 0,
            clearBtn: true
        }
    };

    var datepickerUtils = {
        parse: function () {
            //日期选择
            var objs = $('.dbdate');
            $.each(objs, function (i, obj) {
                datepickerUtils.initDate(obj);
            });
            var objsS = $('.dbtime');
            $.each(objsS, function (i, obj) {
                datepickerUtils.initTime(obj);
            });
        },
        initTime: function (obj, param) {
            var defaluts = datepickerOptions.dateTime;
            if (param) {
                defaluts = $.extend({}, defaluts, param);
            }
            $(obj).datetimepicker(defaluts);
            $(obj).attr("readonly", "readonly");
        },
        initDate: function (obj, param) {
            var defaluts = datepickerOptions.date;
            if (param) {
                defaluts = $.extend({}, defaluts, param);
            }
            $(obj).datetimepicker(defaluts);
            $(obj).attr("readonly", "readonly");
        },
        sectionChoose: function (objStart, objEnd) {
            $(objStart).click(function () {
                $(objStart).datetimepicker('setEndDate', formatDate($(objEnd).val(), true));
            })
            $(objEnd).click(function () {
                $(objEnd).datetimepicker('setStartDate', formatDate($(objStart).val(), true));
            })
        }
    };

    window.DatepickerUtils = datepickerUtils;
})(jQuery)