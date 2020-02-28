

/*自动补全控件*/

;(function ($) {
    $.fn.extend({
        apUnit: function (options) {
            var defaults = {
                offset: 0,
                limit: 10,
                caseBuildingId:null,
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
                var caseBuildingId;
                if (typeof defaults.caseBuildingId == 'function') {
                    caseBuildingId = defaults.caseBuildingId();
                } else {
                    caseBuildingId = defaults.caseBuildingId;
                }
                $.ajax({
                    url: getContextPath() + "/basicUnit/autoCompleteCaseUnit",
                    type: "get",
                    dataType: "json",
                    data: {
                        offset: defaults.offset,
                        limit: defaults.limit,
                        unitNumber: $(that).val(),
                        caseBuildingId:caseBuildingId
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
                            AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
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