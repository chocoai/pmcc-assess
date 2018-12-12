/**
 * Created by kings on 2018-12-5.
 */
//新风品牌自动完成
;(function ($) {
    $.fn.extend({
        apNewWindBrand: function (options) {
            var defaults = {
                onSelect: function (id, name) {

                }
            };
            defaults = $.extend({}, defaults, options);
            var that = this;
            var params = AssessDefault.autocomplete();
            params.source = function (request, response) {
                AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_wind_brand, '', function (html, data) {
                    var responseArray = [];
                    $.each(data, function (i, item) {
                        responseArray.push({
                            label: item.name,
                            id: item.id
                        });
                    });
                    params.source = responseArray;
                    response(responseArray);
                })
            }
            params.select = function (event, ele) {
                that.val(ele.item.label);
                if (defaults.onSelect) {
                    defaults.onSelect(ele.item.id, ele.item.label)
                }
            }
            params.autoFocus = false;
            params.appendTo = $(that).closest('form');
            params.minLength = 0;
            $(that).autocomplete(params);
            return that;
        }
    })
})(jQuery);
