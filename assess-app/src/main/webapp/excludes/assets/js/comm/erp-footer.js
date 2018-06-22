/**
 * Created by Calvin on 2018/4/21.
 * 页脚菜单功能
 */
var PMCC_MAIN = {
    footer:function () {
        var html = '<div class="modal fade" data-backdrop="static" data-keyboard="false" id="loadingModal">' +
            '    <div style="width: 250px;height:20px; z-index: 20000; position: absolute; text-align: center; left: 50%; top: 50%;margin-left:-100px;margin-top:-10px">' +
            '        <div class="progress progress-striped active" style="margin-bottom: 0;">' +
            '            <div class="progress-bar" style="width: 100%;"></div>' +
            '        </div>' +
            '        <h5 id="loading-progress-info-text">正在处理...</h5>' +
            '    </div>' +
            '</div>';

        html += '<footer>';
        html += '<div class="pull-right">';
        html += '技术支持 <a href="https://www.copowercpa.com">四川协合魔方科技有限公司</a>';
        html += '</div>';
        html += '<div class="clearfix"></div>';
        html += '</footer>';

        html += '<div class="footer clearfix">';
        html += '<div class="footer-inner">';
        html += '</div>';
        html += '</div>';
        return html;
    }
};
var datepickerOptions = {
    date: {
        autoclose: true,
        minView: "month",
        todayBtn: "linked",
        language: "zh-CN",
        clearBtn: true
    },
    dateTime: {
        autoclose: true,
        todayBtn: "linked",
        language: "zh-CN",
        minView: 0,
        clearBtn: true
    }
};



//日期选择
var objs = $('.dbdate');
$.each(objs, function (i, obj) {
    initDatetimepickerDate(obj);
});
var objsS = $('.dbtime');
$.each(objsS, function (i, obj) {
    initDatetimepickerTime(obj);
});

//模态框加拖动,此拖到影响到模态窗口
$(document).on("show.bs.modal", ".modal", function () {
    if ($(this).attr("id") != "loading-progress") {
        $(this).draggable({
            handle: ".modal-header"   // 只能点击头部拖动
        });
        $(this).css({"overflow": "hidden", "left": "", "top": ""}); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
        $(this).find(".modal-header").css({"cursor": "move"});
    }
});




