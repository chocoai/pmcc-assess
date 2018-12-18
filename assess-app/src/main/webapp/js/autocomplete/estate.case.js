/*自动补全控件*/

;(function ($) {
    $.fn.extend({
        apEstate: function (options) {
            var defaults = {
                offset: 0,
                limit: 10,
                onSelect: function (id, name) {

                }
            };
            defaults = $.extend({}, defaults, options);
            var that = this;
            var params = AssessDefault.autocomplete();
            params.source = function (request, response) {
                $.ajax({
                    url: getContextPath() + "/caseEstate/autoCompleteCaseEstate",
                    type: "get",
                    dataType: "json",
                    data: {
                        offset: defaults.offset,
                        limit: defaults.limit,
                        name: $(that).val()
                    },
                    success: function (data) {
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