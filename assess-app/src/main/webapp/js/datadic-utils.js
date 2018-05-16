/**
 * Created by kings on 2018-5-15.
 */
var baseDicUtils={
    loadDataDicByPid:function (pid,value,callback) {
        if (pid) {
            $.ajax({
                url: getContextPath()+"/baseDataDic/getCacheDataDicListByPid",
                type: "get",
                dataType: "json",
                data: {
                    pid: pid
                },
                success: function (result) {
                    if (result.ret) {
                        var retHtml = '<option value="" selected>-请选择-</option>';
                        $.each(result.data, function (i, item) {
                            if (item.id === value) {
                                retHtml += ' <option value="' + item.id + '" selected="selected">' + item.name + '</option>';
                            } else {
                                retHtml += ' <option value="' + item.id + '">' + item.name + '</option>';
                            }
                        });
                        if(callback){
                            callback(retHtml, result.data);
                        }

                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        }
    }
}
