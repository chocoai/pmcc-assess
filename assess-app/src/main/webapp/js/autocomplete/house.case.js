

/*自动补全控件*/

;(function ($) {
    $.fn.extend({
        apHouse: function (options) {
            var defaults = {
                offset: 0,
                limit: 10,
                caseUnitId:null,
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
                var unitId;
                if (typeof defaults.caseUnitId == 'function') {
                    unitId = defaults.caseUnitId();
                } else {
                    unitId = defaults.caseUnitId;
                }
                $.ajax({
                    url: getContextPath() + "/basicHouse/autoCompleteCaseHouse",
                    type: "get",
                    dataType: "json",
                    data: {
                        offset: defaults.offset,
                        limit: defaults.limit,
                        houseNumber: $(that).val(),
                        unitId:unitId
                    },
                    success: function (result) {
                        if (result.ret,result.data) {
                            response($.map(result.data, function (item) {
                                return {
                                    label: item.name,
                                    value: item.name,
                                    id: item.id
                                }
                            }));
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