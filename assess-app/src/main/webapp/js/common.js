/**
 * Created by kings on 2018-5-21.
 */
(function ($) {
    var assessCommon = {
        //iframe的宽高自适应
        autoIframeHeight:function (iframe) {
            if (iframe) {
                var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
                if (iframeWin.document.body) {
                    iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
                }
            }
        },
        //提取字段
        extractField:function (text) {
            if(!text) return text;
            var regex = /({.*?})/g;
            var group = text.match(regex);
            var resultArray = [];
            if (group && group.length > 0) {
                $.each(group, function (i, item) {
                    item = item.replace(/^{|}$/g, "");
                    if ($.inArray(item, resultArray) < 0) {
                        resultArray.push(item);
                    }
                })
            }
            return resultArray;
        },

        //替换模板数据
        replaceTemplate:function (text,temp,value) {
            if(!text) return text;
            var regex = '/({'+temp+'})/g';
            return text.replace(eval(regex),value);
        }
    };

    window.AssessCommon = assessCommon;
})(jQuery)



