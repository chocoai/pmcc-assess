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
                        console.log(data);
                        if (data) {
                            var responseArray = [];
                            $.each(data, function (i, item) {
                                responseArray.push({
                                    label: item.name,
                                    type: item.type,
                                    id: item.id
                                });
                            })
                            response(responseArray);
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