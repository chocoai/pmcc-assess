/**
 * Created by kings on 2018-12-5.
 */
(function ($) {
    var caseCommon = {};

    caseCommon.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    caseCommon.isNotBlankObject = function (obj) {
        for (var key in obj) {
            return true;
        }
        return false
    };

    caseCommon.fileUpload = function (target, tableName, id,deleteFlag) {
        FileUtils.uploadFiles({
            target: target,
            disabledTarget: "btn_submit",
            formData: {
                tableName: tableName,
                tableId: id
            },
            deleteFlag: deleteFlag
        });
        // FileUtils.uploadFiles({
        //     target: target,
        //     disabledTarget: "btn_submit",
        //     onUpload: function (file) {
        //         var formData = {
        //             fieldsName: target,
        //             tableName: tableName,
        //             tableId: id
        //         };
        //         return formData;
        //     }, onUploadComplete: function (result, file) {
        //
        //     },
        //     deleteFlag: true
        // });
    };

    caseCommon.showFile = function (target, tableName, id , deleteFlag) {
        FileUtils.getFileShows({
            target: target,
            formData: {
                fieldsName: target,
                tableName: tableName,
                tableId: id,
                projectId: 0
            },
            deleteFlag: deleteFlag
        })
    };

    //查看地图标注
    caseCommon.viewMapMarker = function (dataId, type, name) {
        layer.open({
            type: 2,
            title: '位置标注',
            shade: true,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: getContextPath() + '/map/mapMarkerEstate',
            success: function (layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
                $.ajax({
                    url: getContextPath() + '/caseEstate/getEstateTaggingList',
                    type: 'get',
                    data: {
                        dataId: dataId,
                        type: type,
                        name: name
                    },
                    success: function (result) {
                        if (result && iframe) {
                            iframe.loadMarkerList(result.rows);
                        }
                    }
                })
            }
        });
    };

    /**
     * 土地级别详情 填充并且赋值
     * 说明:首先进入得是一个大型得数组对象,然后把大型数组对象分组变为2维数组对象，
     * 然后对这个二维数组对象进行遍历在遍历得过程中随机选出一个对象然后在页面上显示这个对象得数据，并且用一个隐藏框把遍历到这个位置得对象json串化保存在这个input中
     * @param data
     */
    caseCommon.landLevelLoadHtml = function (data) {
        if (jQuery.isEmptyObject(data)) {
            return false;
        }
        var target = $("#landLevelTabContent");
        target.empty();
        target.parent().show();

        //由于js来筛选 有大量json 解析或者字符串化 影响代码阅读度，因此改为了后台直接处理,第一次的时候有2此筛选分类这样确实代码可读性差
        data.forEach(function (dataA, indexM) {
            $.each(dataA, function (i, obj) {
                var item = caseCommon.getLandLevelFilter(obj) ;
                var landLevelBodyHtml = $("#landLevelTabContentBody").html();
                landLevelBodyHtml = landLevelBodyHtml.replace(/{dataLandLevelAchievement}/g, item.id);
                landLevelBodyHtml = landLevelBodyHtml.replace(/{landFactorTotalScore}/g, item.achievement);
                landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelCategoryName}/g, item.category);
                landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelTypeName}/g, item.typeName);
                landLevelBodyHtml = landLevelBodyHtml.replace(/{gradeName}/g, item.gradeName);
                var text = "" ;
                $.each(obj , function ( i ,n) {
                    text += "等级:"+n.gradeName +"，说明:"+n.reamark +"； \r";
                });
                landLevelBodyHtml = landLevelBodyHtml.replace(/{reamark}/g, text);
                landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelContent}/g, JSON.stringify(obj));
                AssessCommon.loadAsyncDataDicByKey(AssessDicKey.programmeMarketCostapproachGrade, item.grade, function (html, data) {
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelGradeHTML}/g, html);
                    target.append(landLevelBodyHtml);
                }, false);
            });

            if (indexM == 0) {
                target.find("tr").first().find("td").first().attr("rowspan", dataA.length);
                target.find("tr").each(function (i, n) {
                    if (i != 0) {
                        $(n).find("td").first().remove();
                    }
                });
            }
            if (indexM == 1) {
                var length = data[0].length;
                target.find("tr").eq(length).find("td").first().attr("rowspan", dataA.length);
                target.find("tr").each(function (i, n) {
                    if (i > length) {
                        $(n).find("td").first().remove();
                    }
                });
            }
        });
    };

    //js数组去重复 ,直接重载在原生js上
    Array.prototype.deleteEle=function(){
        var newArr = this;
        for (var i=newArr.length-1; i>=0; i--)
        {
            var targetNode = newArr[i];
            for (var j=0; j<i; j++)
            {
                if(targetNode == newArr[j]){
                    newArr.splice(i,1);
                    break;
                }
            }
        }
        return newArr;
    };

    /**
     * 获取数组中随机的一个对象或者数组中曾经被选中的那个对象,原则是在收集数据的时候对选中元素进行添加元素属性
     * @param obj
     * @returns {*}
     */
    caseCommon.getLandLevelFilter = function (obj) {
        var random = Math.random() * (obj.length - 1);
        random = Math.round(random);
        if (random >= 0 && random <= obj.length - 1) {
        } else {
            random = 0;
        }
        var objArray = [];
        obj.forEach(function (data, index) {
            //这里主要是获取对象长度
            var arr = Object.keys(data);
            objArray.push(arr.length) ;
        });
        if (objArray.deleteEle().length >= 2){
            //说明修改过一次
            objArray.sort(function (a, b) {
                return a-b;
            });
            //获取有最大属性值的那个对象
            var max = objArray[objArray.length - 1];
            obj.forEach(function (data, index) {
                //这里主要是获取对象长度
                var arr = Object.keys(data);
                if (max == arr.length){
                    return data ;
                }
            });
        }
        var item = obj[random];
        return item;
    };

    //删除呀
    caseCommon.landLevelEmpty = function (that) {
        $(that).parent().parent().remove();
    };

    /**
     * 土地级别详情分类
     * @param list
     * @returns {Array}
     */
    caseCommon.landLevelFilter = function (list) {
        var flag = 0, data = [];
        for (var i = 0; i < list.length; i++) {
            var az = '';
            for (var j = 0; j < data.length; j++) {
                if (data[j][0].type == list[i].type) {
                    flag = 1;
                    az = j;
                    break;
                }
            }
            if (flag == 1) {
                data[az].push(list[i]);
                flag = 0;
            } else if (flag == 0) {
                var wdy = [];
                wdy.push(list[i]);
                data.push(wdy);
            }
        }
        return data;
    };


    //change 事件 随着等级变化页面展示不同内容
    caseCommon.landLevelHandle = function (that) {
        var group = $(that).closest('.group');
        var landLevelContent = group.find("input[name='landLevelContent']").val();
        var obj = {};
        try {
            obj = JSON.parse(landLevelContent);
        } catch (e) {
        }
        if (caseCommon.isNotBlankObject(obj)) {
            AssessCommon.getDataDicInfo($(that).val(), function (item) {
                obj.forEach(function (data, index) {
                    if (data.gradeName == item.name) {
                        group.find("input[name='landFactorTotalScore']").val(data.achievement);
                        group.find("input[name='dataLandLevelAchievement']").val(data.id);
                    }
                });
            });
        }
    };



    window.caseCommon = caseCommon;
})(jQuery)