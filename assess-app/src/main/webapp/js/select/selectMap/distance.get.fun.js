//获取距离

(function ($) {

    var distanceGetFun = {} ;

    /**
     * 截图字符串中的数字
     */
    distanceGetFun.getNumber = function (str) {
        var reg = /[1-9][0-9]*/g;
        return str.match(reg)[0];
    };

    distanceGetFun.get = function (value,callback) {
        var a500 = {};
        var a1000 = {};
        var a1500 = {};
        var a2000 = {};
        var a2000Max = {};
        if (value){
            value = Number(value);
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_distance, null,function (html, item) {
                $.each(item, function (i, v) {
                    var number = distanceGetFun.getNumber(v.name);
                    number = Number(number);
                    if (v.name == '小于等于500m') {
                        a500.number = number;
                        a500.id = v.id;
                    }
                    if (v.name == '小于等于1000m') {
                        a1000.number = number;
                        a1000.id = v.id;
                    }
                    if (v.name == '小于等于1500m') {
                        a1500.number = number;
                        a1500.id = v.id;
                    }
                    if (v.name == '小于等于2000m') {
                        a2000.number = number;
                        a2000.id = v.id;
                    }
                    if (v.name == '大于2000m') {
                        a2000Max.number = number;
                        a2000Max.id = v.id;
                    }
                });
                var id = null;
                //开始判断
                if (value < a500.number){
                    id = a500.id;
                }else if (a500.number <= value && value < a1000.number){
                    id = a1000.id;
                }else if (a1000.number <= value && value < a1500.number){
                    id = a1500.id;
                }else if (a1500.number <= value && value < a2000.number){
                    id = a2000.id;
                }else if (a2000Max.number <= value ){
                    id = a2000Max.id;
                }
                if (id){
                    callback(id);
                }
            });
        }
    };

    window.distanceGetFun = distanceGetFun;
})(jQuery);