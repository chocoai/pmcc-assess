;(function ($) {
    /**
     * @description 楼盘js
     * @author zch
     * @type {{}}
     */
    var estateCommon = {};

    estateCommon.estateForm = $('#frm_estate');
    estateCommon.estateLandStateForm = $('#frm_estateLandState');
    estateCommon.estateMapiframe = undefined;//地图标注iframe
    estateCommon.applyId = undefined;
    estateCommon.tableId = undefined;
    estateCommon.tableName = AssessDBKey.BasicEstate;
    //附件上传控件id数组
    estateCommon.estateFileControlIdArray = [];

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
        var id = estateCommon.estateForm.find('[name=id]').val() != null ? estateCommon.estateForm.find('[name=id]').val() : estateCommon.tableId;
        if (id) {
            return id;
        }
        return 0;
    };

    estateCommon.getEstateName = function () {
        var estateName = "";
        var name = "";
        try {
            estateName = basicCommon.basicApplyForm.find('[name=estateName]').val();
        } catch (e) {
        }
        try {
            name = estateCommon.estateForm.find('[name=name]').val();
        } catch (e) {
        }
        if (estateName) {
            return estateName;
        }
        if (name) {
            return name;
        }
    };

    estateCommon.getUploadKey = function (bisDetail,tbType) {
        if(tbType=="estate.land"){
            estateCommon.getFilePartHtml(AssessDicKey.estateLandEstateFile, bisDetail);
        }else{
            estateCommon.getFilePartHtml(AssessDicKey.estateIndustrialFile, bisDetail);
            estateCommon.getFilePartHtml(AssessDicKey.estateNonIndustrialFile, bisDetail);
        }
    };

    estateCommon.getFilePartHtml = function (fieldName, bisDetail) {
        var fileDiv = estateCommon.estateForm.find('#' + fieldName);
        fileDiv.empty();
        var estateFileHtml = '';
        AssessCommon.loadAsyncDataDicByKey(fieldName, '', function (html, resultData) {
            var divLength = Math.ceil(resultData.length / 3);
            for (var j = 0; j < divLength; j++) {
                estateFileHtml += '<div class="row form-group">';
                estateFileHtml += '<div class="col-md-12">';
                estateFileHtml += '<div class="form-inline x-valid">';
                var length = (j + 1) * 3 > resultData.length ? resultData.length : (j + 1) * 3;
                for (var i = j * 3; i < length; i++) {
                    estateFileHtml += '<label class="col-sm-1">' + resultData[i].name + '</label>';
                    estateFileHtml += '<div class="col-sm-3">';
                    if (bisDetail != false) {
                        estateFileHtml += '<input id="' + resultData[i].fieldName + '" placeholder="上传附件" class="form-control input-full" type="file">';
                    }
                    estateFileHtml += '<div id="_' + resultData[i].fieldName + '"></div>';
                    estateFileHtml += '</div>';
                    estateCommon.estateFileControlIdArray.push(resultData[i].fieldName);
                }
                estateFileHtml += "</div>";
                estateFileHtml += "</div>";
                estateFileHtml += "</div>";
            }

        }, false);
        fileDiv.append(estateFileHtml);
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
    estateCommon.fileShow = function (fieldsName, tableName, id, deleteFlag) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: tableName,
                tableId: estateCommon.getEstateId()
            },
            deleteFlag: deleteFlag
        })
    };

    //楼盘初始化by applyId
    estateCommon.init = function (applyId, callback) {
        $.ajax({
            url: getContextPath() + '/basicEstate/getBasicEstateByApplyId',
            type: 'get',
            data: {applyId: applyId},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var basicEstate = data.basicEstate;
                    var basicEstateLandState = data.basicEstateLandState;
                    estateCommon.initForm({estate: basicEstate, land: basicEstateLandState});
                    estateCommon.applyId = applyId;
                    if (callback) {
                        callback(result.data);
                    }
                }
            }
        })
    };

    //楼盘初始化by applyId
    estateCommon.initById = function (id, tbType,callback) {
        $.ajax({
            url: getContextPath() + '/basicEstate/getBasicEstateMapById',
            type: 'get',
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var basicEstate = data.basicEstate;
                    basicEstate.tbType = tbType;
                    var basicEstateLandState = data.basicEstateLandState;
                    estateCommon.initForm({estate: basicEstate, land: basicEstateLandState});
                    if (callback) {
                        callback(result.data);
                    }
                }
            }
        })
    };

    //楼盘详情页面
    estateCommon.initDetailById = function (id, callback, bisDetail,tbType) {
        $.ajax({
            url: getContextPath() + '/basicEstate/getBasicEstateMapById',
            type: 'get',
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var basicEstate = data.basicEstate;
                    basicEstate.tbType = tbType;
                    var basicEstateLandState = data.basicEstateLandState;
                    estateCommon.initForm({estate: basicEstate, land: basicEstateLandState}, bisDetail);
                    if (callback) {
                        callback(result.data);
                    }
                }
            }
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
     * @param bisDetail 是否是详情页面
     */
    estateCommon.initForm = function (data, bisDetail) {
        estateCommon.getUploadKey(bisDetail,data.estate.tbType);
        estateCommon.estateForm.clearAll().initForm(data.estate);
        estateCommon.estateForm.find('.x-percent').each(function () {
            $(this).attr('data-value', data.estate[$(this).attr('name')]);
            AssessCommon.elementParsePercent($(this));
        })
        estateCommon.estateLandStateForm.clearAll().initForm(data.land);
        estateCommon.estateLandStateForm.find('.x-percent').each(function () {
            $(this).attr('data-value', data.land[$(this).attr('name')]);
            AssessCommon.elementParsePercent($(this));
        })
        AssessCommon.initAreaInfo({
            provinceTarget: estateCommon.estateForm.find("select[name='province']"),
            cityTarget: estateCommon.estateForm.find("select[name='city']"),
            districtTarget: estateCommon.estateForm.find("select[name='district']"),
            provinceValue: data.estate.province,
            cityValue: data.estate.city,
            districtValue: data.estate.district
        });
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateLandInfrastructure, '', function (html, resultData) {
            var target = $("#industrySupplyInfoContainer");
            target.empty();

            var resultHtml = '<div>';
            var array = [];
            if (data) {
                if (data.estate.infrastructure) {
                    array = data.estate.infrastructure.split(',');
                }
            }
            resultHtml += "<div class='form-check' style='justify-content:left'>";
            $.each(resultData, function (i, item) {
                resultHtml += "<label class='form-check-label'>";
                resultHtml += "<input class='form-check-input' type='checkbox' name='infrastructure' ";
                if ($.inArray(item.id.toString(), array) > -1) {
                    resultHtml += ' checked="checked" ';
                }
                resultHtml += 'value="' + item.id + '">';
                resultHtml += "<span class='form-check-sign'>" + item.name + "</span>";
            });
            resultHtml += "</div>";
            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '全选或全不选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"estateCommon.checkedFun(this,'infrastructure',true)\">";
            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '反选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"estateCommon.checkedFun(this,'infrastructure',false)\">";
            target.append(resultHtml);
        }, true);
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
        AssessCommon.loadDataDicByKey(AssessDicKey.estate_infrastructureCompleteness, data.estate.infrastructureCompleteness, function (html, data) {
            estateCommon.estateForm.find("select[name='infrastructureCompleteness']").empty().html(html).trigger('change');
        });
        $.each(estateCommon.estateFileControlIdArray, function (i, n) {
            estateCommon.fileUpload(n, AssessDBKey.BasicEstate, data.estate.id);
            estateCommon.fileShow(n, AssessDBKey.BasicEstate, data.estate.id, bisDetail);
        });
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


        // AssessCommon.loadDataListHtml(AssessDicKey.estate_total_land_use, data.land.landUseType, function (html, data) {
        //     estateCommon.estateLandStateForm.find("#landUseTypeList").empty().html(html).trigger('change');
        // }, true);
        // //绑定变更事件
        // estateCommon.estateLandStateForm.find("input[name='landUseType']").off('change').on('change', function () {
        //     AssessCommon.getSonDataList(AssessDicKey.estate_total_land_use, $(this).val(), data.land.landUseCategory, function (html, data) {
        //         estateCommon.estateLandStateForm.find("#landUseCategoryList").empty().html(html).trigger('change');
        //     });
        // });


        //土地开发程度为熟地时选择几通几平
        estateCommon.estateLandStateForm.find('select.developmentDegree').off('change').on('change', function () {
            $("#developmentDegreeContentContainer").empty();
            estateCommon.estateLandStateForm.find("input[name='developmentDegreeRemark']").parent().parent().show();
            var key = $(this).find("option:selected").attr('key');
            if (key == AssessDicKey.estateDevelopment_degreePrepared_land) {
                AssessCommon.loadDataDicByPid($(this).val(), '', function (html, resultData) {
                    if (resultData) {
                        var resultHtml = '<div>';
                        var array = [];
                        if (data) {
                            if (data.land.developmentDegreeContent) {
                                array = data.land.developmentDegreeContent.split(',');
                            }
                        }
                        resultHtml += "<div class='form-check' style='justify-content:left'>";
                        $.each(resultData, function (i, item) {
                            resultHtml += "<label class='form-check-label'>";
                            resultHtml += "<input class='form-check-input' type='checkbox' name='developmentDegreeContent' ";
                            if ($.inArray(item.id.toString(), array) > -1) {
                                resultHtml += ' checked="checked" ';
                            }
                            resultHtml += 'value="' + item.id + '">';
                            resultHtml += "<span class='form-check-sign'>" + item.name + "</span>";
                        });
                        resultHtml += "</div>";
                        resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '全选或全不选' + "</span>";
                        resultHtml += "<input type=\"radio\" name=\"developmentDegreeContentSelect\"  onclick=\"estateCommon.checkedFun(this,'developmentDegreeContent',true)\">";
                        resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '反选' + "</span>";
                        resultHtml += "<input type=\"radio\" name=\"developmentDegreeContentSelect\"  onclick=\"estateCommon.checkedFun(this,'developmentDegreeContent',false)\">";
                        $("#developmentDegreeContentContainer").html(resultHtml);
                    }
                });
                estateCommon.estateLandStateForm.find(".developmentDegreeContent").hide();
            } else {
                estateCommon.estateLandStateForm.find(".developmentDegreeContent").show();
            }
        });

        try {
            estateStreetInfoObj.init(data.estate.id);
        } catch (e) {

        }
        try {
            estateLandCategoryInfo.init(data.land.id);
        } catch (e) {
            console.log(e) ;
        }
        try {
            estateVillageObj.init(data.estate.id);
        } catch (e) {

        }
    };


    estateCommon.cleanHTMLData = function (item) {
        $(item).closest('.form-group').remove();
    };


    //2019-08-23对这个方法进行重构
    estateCommon.checkedFun = function (that, name, flag) {
        var form = $(that).closest("form");
        var target = form.find("[name='" + name + "']:checkbox");
        if (flag) {//全选或者全不选
            var number = 1;
            target.each(function (i, n) {
                if ($(this).prop("checked")) {
                    number++;
                }
            });
            if (number == 1) {
                target.prop("checked", true);
            } else {
                target.prop("checked", false);
            }
        } else {
            //首先让选中的失效
            target.each(function (i, n) {
                if ($(this).prop("checked")) {
                    $(this).prop("disabled", true);
                }
            });
            //然后让没有选中的元素设置为选中
            target.each(function (i, n) {
                if (!$(this).prop("checked")) {
                    $(this).prop("checked", true);
                }
            });
            //最后是让失效的元素恢复,并且使其不选中
            target.each(function (i, n) {
                if ($(this).prop("disabled")) {
                    $(this).prop("disabled", false);
                    $(this).prop("checked", false);
                }
            });
        }
    };

    /**
     * 选择案例的楼盘后处理方法
     * @param id
     */
    estateCommon.onSelect = function (name) {
        caseFun.caseEstate.showModel(name);

    };

    /**
     * 启用自动填充,需要引入
     */
    estateCommon.autocompleteStart = function () {
        if ($("#txt_estate_search").size() >= 1) {
            $("#txt_estate_search").apEstate({
                getProvince: function () {
                    return $("#txt_estate_search").closest('form').find("select[name='province']").val();
                },
                getCity: function () {
                    return $("#txt_estate_search").closest('form').find("select[name='city']").val();
                },
                onSelect: function (id, name) {
                    estateCommon.onSelect(name);
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

    estateCommon.mapNewMarker = function (_this, readonly) {
        var data = {};
        data.drawState = 'marker';
        data.id = $(_this).closest('.form-group').find("[name='mapId']").val();
        data.readonly = readonly;
        data.multiple = false;//不允许多个标记
        data.type = "estate";//兼容以前数据
        data.tableId = estateCommon.estateForm.find("input[name='id']").val();
        data.callback = function (item, result) {
            $(_this).closest('.form-group').find("[name='mapId']").val(item.id);
        };
        toolMapHandleFun.loadMap(data);
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
            area: [examineCommon.getMarkerAreaInWidth, examineCommon.getMarkerAreaInHeight],
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
            area: [examineCommon.getMarkerAreaInWidth, examineCommon.getMarkerAreaInHeight],
            content: contentUrl,
            success: function (layero) {
                estateCommon.estateMapiframe = window[layero.find('iframe')[0]['name']];
            },
            cancel: function () {
                if (!readonly) {
                    //到iframe中获取数据
                    if (estateCommon.estateMapiframe.pathArrayJson) {
                        examineCommon.addBasicEstateTagging({
                            applyId: basicCommon.getApplyId(),
                            type: 'estate',
                            pathArray: estateCommon.estateMapiframe.pathArrayJson,
                            name: estateCommon.getEstateName()
                        }, function () {
                            notifySuccess('成功', '标记成功');
                        });
                    }
                }
            }
        });
    };

    //添加标注
    estateCommon.addMarker = function (lng, lat, pathArray) {
        examineCommon.addBasicEstateTagging({
            tableId: estateCommon.getEstateId(),
            type: 'estate',
            lng: lng,
            lat: lat,
            pathArray: pathArray,
            name: estateCommon.getEstateName()
        }, function () {
            estateCommon.loadMarkerList();//标注成功后，刷新地图上的标注
        })
    };

    //加载标注
    estateCommon.loadMarkerList = function () {
        examineCommon.getApplyBatchEstateTaggingsByTableId({
            tableId: estateCommon.getEstateId(),
            type: 'estate'
        }, function (data) {
            if (estateCommon.estateMapiframe) {
                estateCommon.estateMapiframe.loadMarkerList(data);//标注成功后，刷新地图上的标注
            }
        })
    };

    //楼盘标注（通过tableId）
    estateCommon.mapMarker2 = function (readonly, tableId) {
        estateCommon.tableId = tableId;
        var contentUrl = getContextPath() + '/map/mapMarkerEstateByTableId?tableId=' + estateCommon.tableId + '&tableName=' + estateCommon.tableName;
        if (readonly != true) {
            contentUrl += '&click=estateCommon.addMarker2';
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
                estateCommon.loadMarkerList2(tableId);
            }
        });
    };

    //添加标注（通过tableId）
    estateCommon.addMarker2 = function (lng, lat) {
        examineCommon.addBasicEstateTagging({
            tableId: estateCommon.tableId,
            type: 'estate',
            lng: lng,
            lat: lat,
            name: estateCommon.estateForm.find('[name=name]').val()
        }, function () {
            estateCommon.loadMarkerList2(estateCommon.tableId);//标注成功后，刷新地图上的标注
        });
    };

    //加载标注（通过tableId）
    estateCommon.loadMarkerList2 = function (tableId) {
        examineCommon.getApplyBatchEstateTaggingsByTableId({
            tableId: tableId,
            type: 'estate'
        }, function (data) {
            if (estateCommon.estateMapiframe) {
                estateCommon.estateMapiframe.loadMarkerList(data);//标注成功后，刷新地图上的标注
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
                } else {
                    notifyWaring('警告', result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    };



    //板块选择
    estateCommon.blockSelect = function (this_) {
        var $form = $(this_).closest('form');
        assessBlock.select({
            province: $form.find('[name=province]').val(),
            city: $form.find('[name=city]').val(),
            success: function (row) {
                $(this_).closest('.input-group').find("input[name='blockId']").val(row.id);
                $(this_).closest('.input-group').find("input[name='blockName']").val(row.name);
                estateCommon.estateForm.find("#blockDescription").val(row.remark);
            }
        })
    };

    window.estateCommon = estateCommon;
})(jQuery);