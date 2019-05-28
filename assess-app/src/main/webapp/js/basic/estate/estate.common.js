/**
 * Created by asus on 2018/11/24.
 */

;(function ($) {
    var estateCommon = {};
    estateCommon.estateForm = $('#basicEstateFrm');
    estateCommon.estateLandStateForm = $('#basicLandState');
    estateCommon.estateMapiframe = undefined;//地图标注iframe
    //附件上传控件id数组
    estateCommon.estateFileControlIdArray = [
        AssessUploadKey.ESTATE_FLOOR_TOTAL_PLAN,
        AssessUploadKey.ESTATE_FLOOR_APPEARANCE_FIGURE,
        AssessUploadKey.ESTATE_WATER_SUPPLY_PLAN,
        AssessUploadKey.ESTATE_POWER_SUPPLY_PLAN,
        AssessUploadKey.ESTATE_AIR_SUPPLY_PLAN,
        AssessUploadKey.ESTATE_HEATING_PLAN
    ];

    estateCommon.getEstateId = function () {
        return estateCommon.estateForm.find('[name=id]').val();
    };

    /**
     * 空字符串检测
     * @param item
     * @returns {boolean}
     */
    estateCommon.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    estateCommon.isNotBlankObject = function (obj) {
        for (var key in obj) {
            return true;
        }
        return false
    };

    //获取楼盘名称
    estateCommon.getEstateName = function () {
        if (basicCommon.basicApplyForm.find('[name=estatePartInMode]').val()) {
            return estateCommon.estateForm.find('[name=name]').val();
        } else {
            return basicCommon.basicApplyForm.find('[name=estateName]').val();
        }
    };

    //添加楼盘
    estateCommon.add = function (_this, callback) {
        var $form = $(_this).closest('form');
        var province = $form.find('[name=province]').val();
        var city = $form.find('[name=city]').val();
        var estateName = $form.find('[name=estateName]').val();
        if (!estateName) {
            toastr.info('请填写楼盘名称！');
            return false;
        }
        $.ajax({
            url: getContextPath() + '/basicEstate/addEstateAndLandstate',
            data: {
                estateName: estateName,
                province: province,
                city: city
            },
            success: function (result) {
                if (result.ret) {
                    estateCommon.showEstateView(result.data);
                    if (callback) {
                        callback($(_this).attr('data-mode'));
                    }
                }
            }
        })
    }

    //升级楼盘
    estateCommon.upgrade = function (_this, callback) {
        var caseEstateId = $(_this).closest('form').find("input[name='caseEstateId']").val();
        var estatePartInMode = $(_this).attr('data-mode');
        if (!caseEstateId) {
            toastr.info('请选择系统中已存在的楼盘信息！');
            return false;
        }
        $.ajax({
            url: getContextPath() + '/basicEstate/appWriteEstate',
            data: {
                caseEstateId: caseEstateId,
                estatePartInMode: estatePartInMode
            },
            success: function (result) {
                if (result.ret) {
                    estateCommon.showEstateView(result.data);
                    if (callback) {
                        callback($(_this).attr('data-mode'));
                    }
                }
            }
        })
    }

    //楼盘初始化by applyId
    estateCommon.init = function (applyId, callback) {
        $.ajax({
            url: getContextPath() + '/basicEstate/getBasicEstateByApplyId',
            type: 'get',
            data: {applyId: applyId},
            success: function (result) {
                if (result.ret) {
                    estateCommon.showEstateView(result.data);
                    if (callback) {
                        callback();
                    }
                }
            }
        })
    }

    //楼盘明细
    estateCommon.detail = function (applyId) {
        $.ajax({
            url: getContextPath() + '/basicEstate/getBasicEstateByApplyId',
            type: 'get',
            data: {applyId: applyId},
            success: function (result) {
                if (result.ret) {
                    estateCommon.estateForm.initForm(result.data.basicEstate, function () {
                        //附件显示
                        $.each(estateCommon.estateFileControlIdArray, function (i, item) {
                            estateCommon.fileShow(item);
                        })
                    });
                    estateCommon.showEstateView(result.data) ;
                    estateCommon.estateLandStateForm.initForm(result.data.basicEstateLandState, function () {
                        if (result.data.basicEstateLandState.developmentDegreeContent) {
                            var array = result.data.basicEstateLandState.developmentDegreeContent.split(',');
                            AssessCommon.loadDataDicByKey(AssessDicKey.estateDevelopment_degreePrepared_land, '', function (html, resultData) {
                                if (resultData) {
                                    var resultHtml = '';
                                    $.each(resultData, function (i, item) {
                                        resultHtml += '<span class="checkbox-inline"><input disabled="disabled" type="checkbox" ';
                                        if ($.inArray(item.id.toString(), array) > -1) {
                                            resultHtml += ' checked="checked" ';
                                        }
                                        resultHtml += ' id="developmentDegreeContent' + item.id + '" name="developmentDegreeContent" value="' + item.id + '">';
                                        resultHtml += '<label for="developmentDegreeContent' + item.id + '">' + item.name + '</label></span>';
                                    })
                                    $("#developmentDegreeContentContainer").html(resultHtml);
                                }
                            })
                        }
                    });
                }
            }
        })
    }

    //显示楼盘对应部分信息
    estateCommon.showEstateView = function (data) {
        estateCommon.estateForm.initForm(data.basicEstate, function () {
            //1.初始化下拉框；2.初始化上传控件；3.显示已上传的附件信息；
            AssessCommon.initAreaInfo({
                provinceTarget: estateCommon.estateForm.find('[name=province]'),
                cityTarget: estateCommon.estateForm.find('[name=city]'),
                districtTarget: estateCommon.estateForm.find('[name=district]'),
                provinceValue: data.basicEstate.province,
                cityValue: data.basicEstate.city,
                districtValue: data.basicEstate.district
            });
            AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estate_position, data.basicEstate.position, function (html, data) {
                estateCommon.estateForm.find('select.position').empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateSupplySituation, data.basicEstate.supplyGas, function (html, data) {
                estateCommon.estateForm.find('select.supplyGas').empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateSupplySituation, data.basicEstate.supplyPower, function (html, data) {
                estateCommon.estateForm.find('select.supplyPower').empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateSupplySituation, data.basicEstate.supplyWater, function (html, data) {
                estateCommon.estateForm.find('select.supplyWater').empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateSupplySituation, data.basicEstate.drainWater, function (html, data) {
                estateCommon.estateForm.find('select.drainWater').empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateSupplySituation, data.basicEstate.supplyHeating, function (html, data) {
                estateCommon.estateForm.find('select.supplyHeating').empty().html(html).trigger('change');
            }, true);

            //初始化上传控件
            $.each(estateCommon.estateFileControlIdArray, function (i, item) {
                estateCommon.fileUpload(item);
            })

            //附件显示
            $.each(estateCommon.estateFileControlIdArray, function (i, item) {
                estateCommon.fileShow(item);
            })
        });
        estateCommon.estateLandStateForm.initForm(data.basicEstateLandState, function () {
            //绑定变更事件
            estateCommon.estateLandStateForm.find("select.landUseType").off('change').on('change', function () {
                AssessCommon.loadDataDicByPid($(this).val(), data.basicEstateLandState.landUseCategory, function (html, data) {
                    estateCommon.estateLandStateForm.find('select.landUseCategory').empty().html(html).trigger('change');
                });
                data.basicEstateLandState.landUseCategory = null;//第一次执行成功后置为空
            });
            //土地形状变更
            estateCommon.estateLandStateForm.find("select.shapeState").off('change').on('change', function () {
                var remark = $(this).find('option:selected').attr('title');
                estateCommon.estateLandStateForm.find("[name=shapeStateRemark]").val(remark);
            });
            //土地开发程度为熟地时选择几通几平
            estateCommon.estateLandStateForm.find('select.developmentDegree').off('change').on('change', function () {
                $("#developmentDegreeContentContainer").empty();
                estateCommon.estateLandStateForm.find("input[name='developmentDegreeRemark']").parent().parent().show();
                var key = $(this).find("option:selected").attr('key');
                if (key == AssessDicKey.estateDevelopment_degreePrepared_land) {
                    AssessCommon.loadDataDicByPid($(this).val(), '', function (html, resultData) {
                        if (resultData) {
                            var resultHtml = '';
                            var array = [];
                            if (data.basicEstateLandState.developmentDegreeContent) {
                                array = data.basicEstateLandState.developmentDegreeContent.split(',');
                            }
                            $.each(resultData, function (i, item) {
                                resultHtml += '<span class="checkbox-inline"><input type="checkbox" ';
                                if ($.inArray(item.id.toString(), array) > -1) {
                                    resultHtml += ' checked="checked" ';
                                }
                                resultHtml += ' id="developmentDegreeContent' + item.id + '" name="developmentDegreeContent" value="' + item.id + '">';
                                resultHtml += '<label for="developmentDegreeContent' + item.id + '">' + item.name + '</label></span>';
                            })
                            $("#developmentDegreeContentContainer").html(resultHtml);
                        }
                    })
                    estateCommon.estateLandStateForm.find("input[name='developmentDegreeRemark']").parent().parent().hide();
                }
            });

            AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estate_total_land_use, data.basicEstateLandState.landUseType, function (html, data) {
                estateCommon.estateLandStateForm.find('select.landUseType').empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadDataDicByKey(AssessDicKey.estatePlaneness, data.basicEstateLandState.planeness, function (html, data) {
                estateCommon.estateLandStateForm.find('select.planeness').empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estateDevelopment_degree, data.basicEstateLandState.developmentDegree, function (html, data) {
                estateCommon.estateLandStateForm.find('select.developmentDegree').empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estateShape_state, data.basicEstateLandState.shapeState, function (html, data) {
                estateCommon.estateLandStateForm.find('select.shapeState').empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estateTopographic_terrain, data.basicEstateLandState.topographicTerrain, function (html, data) {
                estateCommon.estateLandStateForm.find('select.topographicTerrain').empty().html(html).trigger('change');
            });

            AssessCommon.loadDataDicByKey(AssessDicKey.estate_infrastructureCompleteness, data.basicEstateLandState.infrastructureCompleteness, function (html, data) {
                estateCommon.estateLandStateForm.find('select.infrastructureCompleteness').empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estateLandContaminated, data.basicEstateLandState.contaminated, function (html, data) {
                estateCommon.estateLandStateForm.find("select[name='contaminated']").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estateLandPh, data.basicEstateLandState.ph, function (html, data) {
                estateCommon.estateLandStateForm.find("select[name='ph']").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estateLandFertility, data.basicEstateLandState.fertility, function (html, data) {
                estateCommon.estateLandStateForm.find("select[name='fertility']").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estateLandBearingCapacity, data.basicEstateLandState.bearingCapacity, function (html, data) {
                estateCommon.estateLandStateForm.find("select[name='bearingCapacity']").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estateLandBearingHoldOn, data.basicEstateLandState.holdOn, function (html, data) {
                estateCommon.estateLandStateForm.find("select[name='holdOn']").empty().html(html).trigger('change');
            });

            if (estateCommon.isNotBlank(data.basicEstateLandState.landLevelContent)) {
                var obj = {};
                try {
                    console.log(data.basicEstateLandState.landLevelContent);
                    obj = JSON.parse(data.basicEstateLandState.landLevelContent);
                    console.log(obj) ;
                } catch (e) {
                }
                if (estateCommon.isNotBlankObject(obj)) {
                    var objData = estateCommon.landLevelFilter(obj) ;
                    estateCommon.landLevelLoadHtml(objData);
                }
            }

            //绑定变更事件
            estateCommon.estateLandStateForm.find("select.landUseType").off('change').on('change', function () {
                var strArr = ["林地", "园地", "水域", "耕地", "草地"];//来自于实体描述1(1).docx中的规则
                var landUseTypeId = estateCommon.estateLandStateForm.find("select.landUseType").val();
                if (landUseTypeId) {
                    AssessCommon.getDataDicInfo(landUseTypeId, function (landUseTypeData) {
                        var str = strArr.join(",");
                        //当属于数组中的任意一项时显示
                        if (str.indexOf(landUseTypeData.name) != -1) {
                            estateCommon.estateLandStateForm.find("select[name='fertility']").parent().parent().show();
                            estateCommon.estateLandStateForm.find("select[name='holdOn']").parent().parent().hide();
                            estateCommon.estateLandStateForm.find("select[name='bearingCapacity']").parent().parent().hide();
                        } else {
                            estateCommon.estateLandStateForm.find("select[name='fertility']").parent().parent().hide();
                            estateCommon.estateLandStateForm.find("select[name='holdOn']").parent().parent().show();
                            estateCommon.estateLandStateForm.find("select[name='bearingCapacity']").parent().parent().show();
                        }
                    });
                }
                AssessCommon.loadDataDicByPid($(this).val(), data.basicEstateLandState.landUseCategory, function (html, data) {
                    estateCommon.estateLandStateForm.find('select.landUseCategory').empty().html(html).trigger('change');
                });
            });
        })
    }


    //附件上传
    estateCommon.fileUpload = function (fieldsName) {
        FileUtils.uploadFiles({
            target: fieldsName,
            disabledTarget: "btn_submit",
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.BasicEstate,
                tableId: estateCommon.getEstateId()
            },
            deleteFlag: true
        });
    }

    //附件显示
    estateCommon.fileShow = function (fieldsName, deleteFlag) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.BasicEstate,
                tableId: estateCommon.getEstateId()
            },
            deleteFlag: deleteFlag == undefined ? true : deleteFlag
        })
    }

    //楼盘标注
    estateCommon.mapMarker = function (readonly) {
        var contentUrl = getContextPath() + '/map/mapMarkerEstate?estateName=' + estateCommon.getEstateName();
        if (readonly != true) {
            contentUrl += '&click=estateCommon.addMarker';
        }
        layer.open({
            type: 2,
            title: '楼盘标注',
            shadeClose: true,
            shade: true,
            maxmin: true, //开启最大化最小化按钮
            area: [basicCommon.getMarkerAreaInWidth, basicCommon.getMarkerAreaInHeight],
            content: contentUrl,
            success: function (layero) {
                estateCommon.estateMapiframe = window[layero.find('iframe')[0]['name']];
                estateCommon.loadMarkerList();
            }
        });
    }

    //添加标注
    estateCommon.addMarker = function (lng, lat) {
        $.ajax({
            url: getContextPath() + '/basicEstateTagging/addBasicEstateTagging',
            data: {
                applyId: basicCommon.getApplyId(),
                type: 'estate',
                lng: lng,
                lat: lat,
                name: estateCommon.getEstateName()
            },
            success: function (result) {
                if (result.ret) {//标注成功后，刷新地图上的标注
                    estateCommon.loadMarkerList();
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    }

    //加载标注
    estateCommon.loadMarkerList = function () {
        $.ajax({
            url: getContextPath() + '/basicEstateTagging/getEstateTaggingList',
            data: {
                applyId: basicCommon.getApplyId(),
                type: 'estate'
            },
            success: function (result) {
                if (result.ret && estateCommon.estateMapiframe) {//标注成功后，刷新地图上的标注
                    estateCommon.estateMapiframe.loadMarkerList(result.data);
                }
            }
        })
    }

    /**
     * 启用自动填充,需要引入
     */
    estateCommon.autocompleteStart = function () {
        estateCommon.estateForm.find('[name=developerName]').apDeveloper({
            onSelect: function (id, name) {
                estateCommon.estateForm.find('input[name=developer]').val(id);
                estateCommon.estateForm.find('input[name=developerName]').val(name);
            }
        });
    };

    /**
     * 土地级别详情 填充并且赋值
     * 说明:首先进入得是一个大型得数组对象,然后把大型数组对象分组变为2维数组对象，
     * 然后对这个二维数组对象进行遍历在遍历得过程中随机选出一个对象然后在页面上显示这个对象得数据，并且用一个隐藏框把遍历到这个位置得对象json串化保存在这个input中
     * @param data
     */
    estateCommon.landLevelLoadHtml = function (data) {
        if (jQuery.isEmptyObject(data)) {
            return false;
        }
        var target = $("#landLevelTabContent");
        target.empty();
        target.parent().show();

        //由于js来筛选 有大量json 解析或者字符串化 影响代码阅读度，因此改为了后台直接处理,第一次的时候有2此筛选分类这样确实代码可读性差
        data.forEach(function (dataA, indexM) {
            $.each(dataA, function (i, obj) {
                var item = estateCommon.getLandLevelFilter(obj) ;
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
    estateCommon.getLandLevelFilter = function (obj) {
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
    estateCommon.landLevelEmpty = function (that) {
        $(that).parent().parent().remove();
    };

    /**
     * 土地级别详情分类
     * @param list
     * @returns {Array}
     */
    estateCommon.landLevelFilter = function (list) {
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
    estateCommon.landLevelHandle = function (that) {
        var group = $(that).closest('.group');
        var landLevelContent = group.find("input[name='landLevelContent']").val();
        var obj = {};
        try {
            obj = JSON.parse(landLevelContent);
        } catch (e) {
        }
        if (estateCommon.isNotBlankObject(obj)) {
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



    window.estateCommon = estateCommon;
})(jQuery);