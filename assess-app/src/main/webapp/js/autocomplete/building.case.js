/*自动补全控件*/

;(function ($) {
    $.fn.extend({
        apBuilding: function (options) {
            var defaults = {
                offset: 0,
                limit: 10,
                caseEstateId: null,
                autoSelect: false,
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
                var estateId;
                if (typeof defaults.caseEstateId == 'function') {
                    estateId = defaults.caseEstateId();
                } else {
                    estateId = defaults.caseEstateId;
                }
                $.ajax({
                    url: getContextPath() + "/basicBuilding/autoCompleteCaseBuilding",
                    type: "get",
                    dataType: "json",
                    data: {
                        offset: defaults.offset,
                        limit: defaults.limit,
                        buildingNumber: $(that).val(),
                        estateId: estateId
                    },
                    success: function (result) {
                        if (result.ret) {
                            response($.map(result.data, function (item) {
                                return {
                                    label: item.name,
                                    value: item.name,
                                    id: item.id
                                }
                            }));
                        } else {
                            Alert("调用服务端方法失败，失败原因:" + result.errmsg);
                        }
                    }
                });
            }
            params.select = function (event, ele) {
                that.val(ele.item.label);
                if (defaults.onSelect) {
                    defaults.onSelect(ele.item.id, ele.item.label)
                }
            }
            $(that).autocomplete(params);
            return that;
        }
    })
})(jQuery);