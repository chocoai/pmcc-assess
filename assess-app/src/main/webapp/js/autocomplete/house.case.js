

/*自动补全控件*/

;(function ($) {
    $.fn.extend({
        apHouse: function (options) {
            var defaults = {
                offset: 0,
                limit: 10,
                applyBatchId:null,
                type:null,
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
                var applyBatchId;
                if (typeof defaults.applyBatchId == 'function') {
                    applyBatchId = defaults.applyBatchId();
                } else {
                    applyBatchId = defaults.applyBatchId;
                }
                var type;
                if (typeof defaults.type == 'function') {
                    type = defaults.type();
                } else {
                    type = defaults.type;
                }
                $.ajax({
                    url: getContextPath() + "/basicHouse/autoCompleteCaseHouse",
                    type: "get",
                    dataType: "json",
                    data: {
                        offset: defaults.offset,
                        limit: defaults.limit,
                        houseNumber: $(that).val(),
                        type:type,
                        applyBatchId:applyBatchId
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