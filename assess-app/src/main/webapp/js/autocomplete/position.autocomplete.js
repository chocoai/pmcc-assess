/**
 * Created by kings on 2018-12-5.
 */
//方位自动完成
;(function ($) {
    $.fn.extend({
        acptPosition: function (options) {
            var defaults = {
                offset: 0,
                limit: 10,
                provinceElement: undefined,
                cityElement: undefined,
                districtElement: undefined,
                onSelect: function (id, name) {

                }
            };
            defaults = $.extend({}, defaults, options);
            var that = this;
            var params = AssessDefault.autocomplete();
            params.source = function (request, response) {
                $.ajax({
                    url: getContextPath() + "/dataPosition/getDataPositionList",
                    type: "get",
                    dataType: "json",
                    data: {
                        offset: defaults.offset,
                        limit: defaults.limit,
                        province: defaults.provinceElement.val(),
                        city: defaults.cityElement.val(),
                        district: defaults.districtElement.val(),
                        name: $(that).val()
                    },
                    success: function (data) {
                        console.log(data);
                        if (data) {
                            var responseArray = [];
                            $.each(data.rows, function (i, item) {
                                responseArray.push({
                                    label: item.name,
                                    id: item.id
                                });
                            });
                            response(responseArray);
                        }
                    }
                });
            }
            params.select = function (event, ele) {
                if (defaults.onSelect) {
                    defaults.onSelect(ele.item.id, ele.item.label)
                }
            }
            params.minLength = 1;
            $(that).autocomplete(params);
            return that;
        }
    })
})(jQuery);
