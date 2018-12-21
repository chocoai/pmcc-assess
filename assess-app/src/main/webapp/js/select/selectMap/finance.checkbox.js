
//金融服务

(function ($) {


    function AssessMatchingFinance() {

    }

    AssessMatchingFinance.prototype.select = function (that) {
        $.ajax({
            url: getContextPath() + '/basicEstateTagging/getEstateTaggingList',
            data: {
                applyId: basicCommon.getApplyId(),
                type: 'estate'
            },
            success: function (result) {
                if (result.ret && estateCommon.estateMapiframe) {
                    if (result.data.length >= 1) {
                        var data = result.data[0];
                        if (data) {
                            var options = {
                                distance: $(that).parent().prev().val(),
                                lng: data.lng,
                                lat: data.lat
                            };
                            AssessMatchingFinance.prototype.appendHtml(options);
                        }
                    } else {
                        Alert("无标记");
                    }
                }
            }
        });
    };

    AssessMatchingFinance.prototype.appendHtml = function (options) {
        var target = $("#select_matchingfinance_modal");
        if (target.length > 0) {
            $("#select_matchingfinance_modal").remove();
        }

    };

    window.assessMatchingFinance = new AssessMatchingFinance();
})(jQuery);