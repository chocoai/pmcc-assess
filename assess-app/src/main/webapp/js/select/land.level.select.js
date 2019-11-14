/**
 * Created by kings on 2018-4-18.
 * 选择土地级别 工具方法
 */
(function ($) {
    var AssessLandLevel = function () {
        this.success = function (data) {

        }
    }

    //弹出选择土地级别窗口
    AssessLandLevel.prototype.select = function (options) {
       Alert("已经取消此控件") ;
    };


    window.assessLandLevel = new AssessLandLevel();
})(jQuery);