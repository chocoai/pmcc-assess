;(function ($) {
    /**
     * @description 楼盘js
     * @author zch
     * @type {{}}
     */
    var estateCommon = {};

    estateCommon.estateForm = $('#frm_estate');
    estateCommon.estateLandStateForm = $('#frm_estateLandState');

    //附件上传控件id数组
    estateCommon.estateFileControlIdArray = [
        AssessUploadKey.ESTATE_FLOOR_TOTAL_PLAN,
        AssessUploadKey.ESTATE_FLOOR_APPEARANCE_FIGURE,
        AssessUploadKey.ESTATE_WATER_SUPPLY_PLAN,
        AssessUploadKey.ESTATE_POWER_SUPPLY_PLAN,
        AssessUploadKey.ESTATE_AIR_SUPPLY_PLAN,
        AssessUploadKey.ESTATE_HEATING_PLAN
    ];

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

    estateCommon.getEstateId = function () {
        var id = estateCommon.estateForm.find('[name=id]').val();
        if (id) {
            return id;
        }
        return 0;
    };

    estateCommon.getEstateName = function () {
        return estateCommon.estateForm.find('[name=name]').val();
    };


    //附件上传
    estateCommon.fileUpload = function (fieldsName, tableName, id) {
        FileUtils.uploadFiles({
            target: fieldsName,
            disabledTarget: "btn_submit",
            formData: {
                fieldsName: fieldsName,
                tableName: tableName,
                tableId: estateCommon.getEstateId()
            },
            deleteFlag: true
        });
    };

    //附件显示
    estateCommon.fileShow = function (fieldsName, tableName, id) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: tableName,
                tableId: estateCommon.getEstateId()
            },
            deleteFlag: true
        })
    };

    estateCommon.detail = function (id, callback) {
        $.ajax({
            url: getContextPath() + '/basicEstate/getBasicEstateByApplyId',
            type: 'get',
            data: {applyId: id},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                }
            }
        })
    };

    /**
     * 楼盘信息 赋值
     * @param data
     */
    estateCommon.initForm = function (data) {
        estateCommon.estateForm.clearAll();
        estateCommon.estateLandStateForm.clearAll();
        estateCommon.estateForm.initForm(data.estate);
        // estateCommon.getLocationDescribe(data.estate.blockId);
        estateCommon.estateLandStateForm.initForm(data.land);
        AssessCommon.initAreaInfo({
            provinceTarget: estateCommon.estateForm.find("select[name='province']"),
            cityTarget: estateCommon.estateForm.find("select[name='city']"),
            districtTarget: estateCommon.estateForm.find("select[name='district']"),
            provinceValue: data.estate.province,
            cityValue: data.estate.city,
            districtValue: data.estate.district
        });
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estate_position, data.estate.position, function (html, data) {
            estateCommon.estateForm.find('select.position').empty().html(html).trigger('change');
        }, true);
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateSupplySituation, data.estate.supplyGas, function (html, data) {
            estateCommon.estateForm.find('select.supplyGas').empty().html(html).trigger('change');
        }, true);
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateSupplySituation, data.estate.supplyPower, function (html, data) {
            estateCommon.estateForm.find('select.supplyPower').empty().html(html).trigger('change');
        }, true);
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateSupplySituation, data.estate.supplyWater, function (html, data) {
            estateCommon.estateForm.find('select.supplyWater').empty().html(html).trigger('change');
        }, true);
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateSupplySituation, data.estate.drainWater, function (html, data) {
            estateCommon.estateForm.find('select.drainWater').empty().html(html).trigger('change');
        }, true);
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateSupplySituation, data.estate.supplyHeating, function (html, data) {
            estateCommon.estateForm.find('select.supplyHeating').empty().html(html).trigger('change');
        }, true);
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateSupplySituation, data.estate.supplyCommunication, function (html, data) {
            estateCommon.estateForm.find('select.supplyCommunication').empty().html(html).trigger('change');
        }, true);
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateSupplySituation, data.estate.supplyRoad, function (html, data) {
            estateCommon.estateForm.find('select.supplyRoad').empty().html(html).trigger('change');
        }, true);

        $.each(estateCommon.estateFileControlIdArray, function (i, n) {
            estateCommon.fileUpload(n, AssessDBKey.BasicEstate, data.estate.id);
            estateCommon.fileShow(n, AssessDBKey.BasicEstate, data.estate.id);
        });

        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estate_total_land_use, data.land.landUseType, function (html, data) {
            estateCommon.estateLandStateForm.find('select.landUseType').empty().html(html).trigger('change');
        }, true);
        AssessCommon.loadDataDicByKey(AssessDicKey.estatePlaneness, data.land.planeness, function (html, data) {
            estateCommon.estateLandStateForm.find('select.planeness').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estateDevelopment_degree, data.land.developmentDegree, function (html, data) {
            estateCommon.estateLandStateForm.find('select.developmentDegree').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estateShape_state, data.land.shapeState, function (html, data) {
            estateCommon.estateLandStateForm.find('select.shapeState').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estateTopographic_terrain, data.land.topographicTerrain, function (html, data) {
            estateCommon.estateLandStateForm.find('select.topographicTerrain').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estate_infrastructureCompleteness, data.land.infrastructureCompleteness, function (html, data) {
            estateCommon.estateLandStateForm.find('select.infrastructureCompleteness').empty().html(html).trigger('change');
        });

        AssessCommon.loadDataDicByKey(AssessDicKey.estateLandContaminated, data.land.contaminated, function (html, data) {
            estateCommon.estateLandStateForm.find("select[name='contaminated']").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estateLandPh, data.land.ph, function (html, data) {
            estateCommon.estateLandStateForm.find("select[name='ph']").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estateLandFertility, data.land.fertility, function (html, data) {
            estateCommon.estateLandStateForm.find("select[name='fertility']").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estateLandBearingCapacity, data.land.bearingCapacity, function (html, data) {
            estateCommon.estateLandStateForm.find("select[name='bearingCapacity']").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estateLandBearingHoldOn, data.land.holdOn, function (html, data) {
            estateCommon.estateLandStateForm.find("select[name='holdOn']").empty().html(html).trigger('change');
        });
        if (estateCommon.isNotBlank(data.land.landLevelContent)) {
            var obj = {};
            try {
                obj = JSON.parse(data.land.landLevelContent);
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
            AssessCommon.loadDataDicByPid($(this).val(), data.land.landUseCategory, function (html, data) {
                estateCommon.estateLandStateForm.find('select.landUseCategory').empty().html(html).trigger('change');
            });
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
                        if (data.land.developmentDegreeContent) {
                            array = data.land.developmentDegreeContent.split(',');
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
                });
                estateCommon.estateLandStateForm.find("input[name='developmentDegreeRemark']").parent().parent().hide();
            }
        });
    };

    /**
     * 选择案例的楼盘后处理方法
     * @param id
     */
    estateCommon.onSelect = function (id) {
        Loading.progressShow();
        $.ajax({
            url: getContextPath() + '/basicEstate/appWriteEstate',
            data: {
                applyId: basicCommon.getApplyId(),
                caseEstateId: id
            },
            type: 'post',
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    estateCommon.detail(basicCommon.getApplyId(), function (data) {
                        basicCommon.update({caseEstateId: id, id: basicCommon.getApplyId()}, function () {
                            estateCommon.initForm({estate: data.basicEstate, land: data.basicEstateLandState});
                            basicCommon.basicApplyForm.find("input[name='caseEstateId']").val(id);
                        });
                    });
                } else {
                    console.log(result.errmsg);
                    Alert("转移失败!");
                }
            }
        })
    };

    /**
     * 启用自动填充,需要引入
     */
    estateCommon.autocompleteStart = function () {
        if ($("#txt_estate_search").size() >= 1) {
            $("#txt_estate_search").apEstate({
                onSelect: function (id, name) {
                    estateCommon.onSelect(id);
                }
            });
        }
        if (estateCommon.estateForm.find('[name=developerName]').size() >= 1) {
            estateCommon.estateForm.find('[name=developerName]').apDeveloper({
                onSelect: function (id, name) {
                    estateCommon.estateForm.find('input[name=developer]').val(id);
                    estateCommon.estateForm.find('input[name=developerName]').val(name);
                }
            });
        }
    };

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
    };

    //土地标注画区块
    estateCommon.mapLandMarker = function (readonly) {
        var contentUrl = getContextPath() + '/map/landTagging?applyId=' + basicCommon.getApplyId() + "&readonly=" + readonly;
        layer.open({
            type: 2,
            title: '土地标注画区块',
            shadeClose: true,
            shade: true,
            maxmin: true, //开启最大化最小化按钮
            area: [basicCommon.getMarkerAreaInWidth, basicCommon.getMarkerAreaInHeight],
            content: contentUrl,
            success: function (layero) {
                estateCommon.estateMapiframe = window[layero.find('iframe')[0]['name']];
            },
            cancel: function () {
                if (!readonly) {
                    //到iframe中获取数据
                    if (estateCommon.estateMapiframe.pathArrayJson) {
                        $.ajax({
                            url: getContextPath() + '/basicEstateTagging/addBasicEstateTagging',
                            data: {
                                applyId: basicCommon.getApplyId(),
                                type: 'estate',
                                pathArray: estateCommon.estateMapiframe.pathArrayJson,
                                name: estateCommon.getEstateName()
                            },
                            success: function (result) {
                                if (result.ret) {
                                    toastr.success('标记成功');
                                } else {
                                    Alert(result.errmsg);
                                }
                            }
                        })
                    }
                }
            }
        });
    };

    //添加标注
    estateCommon.addMarker = function (lng, lat, pathArray) {
        $.ajax({
            url: getContextPath() + '/basicEstateTagging/addBasicEstateTagging',
            data: {
                applyId: basicCommon.getApplyId(),
                type: 'estate',
                lng: lng,
                lat: lat,
                pathArray: pathArray,
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
    };

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
    };

    //获取区位描述
    estateCommon.getLocationDescribe = function (blockId) {
        $.ajax({
            url: getContextPath() + '/dataBlock/getDataBlockById',
            type: "get",
            dataType: "json",
            data: {
                id: blockId,
            },
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        estateCommon.estateForm.find("textarea[name='locationDescribe']").val(result.data.remark);
                    }
                }
                else {
                    toastr.warning(result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
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