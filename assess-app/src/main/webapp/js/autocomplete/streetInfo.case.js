/*自动补全控件*/

;(function ($) {
    $.fn.extend({
        apStreetName: function (options) {
            var defaults = {
                offset: 0,
                limit: 10,
                autoSelect: false,
                getProvince: function () {
                    return null;
                },
                getCity: function () {
                    return null;
                },
                onSelect: function (id, name) {

                }
            };
            defaults = $.extend({}, defaults, options);
            var that = this;
            var params = AssessDefault.autocomplete();
            if (!defaults.autoSelect) {
                params.response = null;
            }
            params.source = function (request, response) {
                $.ajax({
                    url: getContextPath() + "/basicEstateStreetInfo/autoCompleteCaseEstate",
                    type: "get",
                    dataType: "json",
                    data: {
                        offset: defaults.offset,
                        limit: defaults.limit,
                        province: defaults.getProvince(),
                        city: defaults.getCity(),
                        name: $(that).val()
                    },
                    success: function (result) {
                        if (result.ret && result.data) {
                            response($.map(result.data, function (item) {
                                return {
                                    label: item.estateName,
                                    value: item.estateName,
                                    id: item.id
                                }
                            }));
                        }
                    }
                });
            }
            params.select = function (event, ele) {
                that.val(ele.item.label);
                console.log(ele);
                if (defaults.onSelect) {
                    defaults.onSelect(ele.item.id, ele.item.label)
                }
            }
            $(that).autocomplete(params);
            return that;
        }
    })
})(jQuery);