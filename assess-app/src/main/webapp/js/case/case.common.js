/**
 * Created by kings on 2018-12-5.
 */
(function ($) {
    var caseCommon = {};

    //查看地图标注
    caseCommon.viewMapMarker = function (estateId, type, name) {
        layer.open({
            type: 2,
            title: '位置标注',
            shadeClose: true,
            shade: true,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: getContextPath() + '/map/mapMarkerEstate',
            success: function (layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
                $.ajax({
                    url: getContextPath() + '/basicEstateTagging/getEstateTaggingList',
                    data: {
                        estateId: estateId,
                        type: type,
                        name: name
                    },
                    success: function (result) {
                        if (result.ret && iframe) {
                            iframe.loadMarkerList(result.data);
                        }
                    }
                })
            }
        });
    }

    window.caseCommon = caseCommon;
})(jQuery)