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
        $("#txt_estate_search").apEstate({
            onSelect: function (id, name) {
                estateCommon.onSelect(id);
            }
        });
        estateCommon.estateForm.find('[name=developerName]').apDeveloper({
            onSelect: function (id, name) {
                estateCommon.estateForm.find('input[name=developer]').val(id);
                estateCommon.estateForm.find('input[name=developerName]').val(name);
            }
        });
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
            area: ['893px', '600px'],
            content: contentUrl,
            success: function (layero) {
                estateCommon.estateMapiframe = window[layero.find('iframe')[0]['name']];
                estateCommon.loadMarkerList();
            }
        });
    };

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

    //土地级别详情 填充并且赋值
    estateCommon.landLevelLoadHtml = function (data) {
        if (jQuery.isEmptyObject(data)) {
            return false;
        }
        var target = $("#landLevelTabContent");
        target.empty();
        target.parent().show();
        $.each(data, function (i, n) {
            var landLevelBodyHtml = $("#landLevelTabContentBody").html();
            landLevelBodyHtml = landLevelBodyHtml.replace(/{reamark}/g, n.reamark);
            landLevelBodyHtml = landLevelBodyHtml.replace(/{achievement}/g, n.achievement);
            landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelChange}/g, n.id);
            AssessCommon.getDataDicInfo(n.type , function (typeData) {
                AssessCommon.getDataDicInfo(n.category , function (categoryData) {
                    AssessCommon.loadAsyncDataDicByKey(AssessDicKey.programmeMarketCostapproachGrade, n.grade, function (html, data) {
                        landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelCategoryName}/g, categoryData.name);
                        landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelCategory}/g, categoryData.id);
                        landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelTypeName}/g, typeData.name);
                        landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelGrade}/g, html);
                        target.append(landLevelBodyHtml);
                    }, true);
                });
            });
        });
    };

    estateCommon.landLevelEmpty = function (that) {
        $(that).parent().parent().remove();
    };

    estateCommon.landLevelHandle = function (that,category) {
        var group = $(that).closest('.group');
        var grade = $(that).val() ;
        if (category) {
            AssessCommon.getDataDicInfo(category, function (data) {
                var obj = JSON.parse(data.remark);
                if (grade) {
                    AssessCommon.getDataDicInfo(grade, function (item) {
                        var value = null;
                        if (item.name == '优') {
                            value = obj.A;
                        }
                        if (item.name == '较优') {
                            value = obj.B;
                        }
                        if (item.name == '一般') {
                            value = obj.C;
                        }
                        if (item.name == '较劣') {
                            value = obj.D;
                        }
                        if (item.name == '劣') {
                            value = obj.E;
                        }
                        if (value) {
                            group.find("input[name='landLevelAchievement']").val(value);
                        }
                    });
                }
            });
        }
    };

    window.estateCommon = estateCommon;
})(jQuery);