/*自动补全控件*/

;(function ($) {
    $.fn.extend({
        apBuilding: function (options) {
            var defaults = {
                offset: 0,
                limit: 10,
                quoteId: null,
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
                var quoteId;
                if (typeof defaults.quoteId == 'function') {
                    quoteId = defaults.quoteId();
                } else {
                    quoteId = defaults.quoteId;
                }
                $.ajax({
                    url: getContextPath() + "/basicApplyBatch/autoCompleteCaseOther",
                    type: "get",
                    dataType: "json",
                    data: {
                        offset: defaults.offset,
                        limit: defaults.limit,
                        name: $(that).val(),
                        quoteId: quoteId
                    },
                    success: function (result) {
                        if (result.ret && result.data) {
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