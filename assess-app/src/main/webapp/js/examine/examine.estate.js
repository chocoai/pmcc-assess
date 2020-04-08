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
    estateCommon.estateFileControlIdArray = [
        AssessUploadKey.ESTATE_FLOOR_TOTAL_PLAN,
        AssessUploadKey.ESTATE_FLOOR_APPEARANCE_FIGURE,
        AssessUploadKey.ESTATE_WATER_SUPPLY_PLAN,
        AssessUploadKey.ESTATE_POWER_SUPPLY_PLAN,
        AssessUploadKey.ESTATE_AIR_SUPPLY_PLAN,
        AssessUploadKey.ESTATE_HEATING_PLAN,
        AssessUploadKey.ESTATE_GATE_ENTRANCE_PLAN
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
    estateCommon.initById = function (id, callback) {
        $.ajax({
            url: getContextPath() + '/basicEstate/getBasicEstateMapById',
            type: 'get',
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var basicEstate = data.basicEstate;
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
    estateCommon.initDetailById = function (id, callback, bisDetail) {
        $.ajax({
            url: getContextPath() + '/basicEstate/getBasicEstateMapById',
            type: 'get',
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var basicEstate = data.basicEstate;
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

        AssessCommon.loadDataListHtml(AssessDicKey.estate_total_land_use, data.land.landUseType, function (html, data) {
            estateCommon.estateLandStateForm.find("#landUseTypeList").empty().html(html).trigger('change');
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
            if (estateCommon.isNotBlank(data.land.landLevelContent)) {
                estateCommon.estateLandStateForm.find("input[name='landLevelContentResult']").val(data.land.landLevelContent);
                estateCommon.estateLandStateForm.find("input[name='landFactorTotalScoreResult']").val(data.land.landFactorTotalScore);
            }
        }
        //绑定变更事件
        estateCommon.estateLandStateForm.find("input[name='landUseType']").off('change').on('change', function () {
            AssessCommon.getSonDataList(AssessDicKey.estate_total_land_use, $(this).val(), data.land.landUseCategory, function (html, data) {
                estateCommon.estateLandStateForm.find("#landUseCategoryList").empty().html(html).trigger('change');
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

        if (bisDetail == false) {
            estateCommon.writeHTMLDataDetail(data.estate.streetNumber, data.estate.attachNumber);
        } else {
            estateCommon.writeHTMLData(data.estate.streetNumber, data.estate.attachNumber);
        }
    };

    estateCommon.writeHTMLData = function (streetNumbers, attachNumbers) {
        estateCommon.estateForm.find(".streetNumbers").empty();
        var strs = streetNumbers.split(",");
        var strs2 = attachNumbers.split(",");
        var length = strs.length;
        estateCommon.estateForm.find("input[name='streetNumber']").val(strs[0]);
        estateCommon.estateForm.find("input[name='attachNumber']").val(strs2[0]);
        for (var i = 1; i < length; i++) {
            if (estateCommon.isNotBlank(strs[i])) {
                var html = "<div class='row form-group'>";
                html += '<div class="col-sm-12">';
                html += '<div class="form-inline x-valid">';

                html += "<label class='col-sm-1 control-label'>街道号<span class='symbol required'></span></label>";
                html += "<div class='col-sm-3'>";
                html += "<input type='text' required class='form-control input-full'" + "name='streetNumber' value='" + strs[i] + "'>";
                html += "</div>";
                html += "<label class='col-sm-1 control-label'>附号</label>";
                html += "<div class='col-sm-3'>";
                html += "<input type='text' class='form-control input-full'" + "name='attachNumber' value='" + strs2[i] + "'>";
                html += "</div>";

                html += "<div class='col-sm-1'>";
                html += "<span class='input-group-btn'>" + "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='estateCommon.cleanHTMLData(this)'>" + "</span>";
                html += "</div>";

                html += "</div>";
                html += "</div>";
                html += "</div>";
                estateCommon.estateForm.find(".streetNumbers").append(html);
            }
        }
    };

    estateCommon.writeHTMLDataDetail = function (streetNumbers, attachNumbers) {
        estateCommon.estateForm.find(".streetNumbers").empty();
        var strs = streetNumbers.split(",");
        var strs2 = attachNumbers.split(",");
        var length = strs.length;
        for (var i = 0; i < length; i++) {
            if (estateCommon.isNotBlank(strs[i])) {
                var html = "<div class='row form-group'>";
                html += '<div class="col-sm-12">';
                html += '<div class="form-inline x-valid">';

                html += "<label class='col-sm-1 control-label'>街道号</label>";
                html += "<div class='col-sm-3'>";
                html += "<label class='form-control input-full'" + "name='streetNumber'>" + strs[i] + "</label>";
                html += "</div>";
                html += "<label class='col-sm-1 control-label'>附号</label>";
                html += "<div class='col-sm-3'>";
                html += "<label class='form-control input-full'" + "name='attachNumber'>" + strs2[i] + "</label>";
                html += "</div>";

                html += "</div>";
                html += "</div>";
                html += "</div>";
                estateCommon.estateForm.find(".streetNumbers").append(html);
            }
        }
    };

    estateCommon.cleanHTMLData = function (item) {
        $(item).closest('.form-group').remove();
    };

    estateCommon.appendHTML = function () {
        var html = "<div class='row form-group'>";
        html += '<div class="col-sm-12">';
        html += '<div class="form-inline x-valid">';

        html += "<label class='col-sm-1 control-label'>街道号<span class='symbol required'></span></label>";
        html += "<div class='col-sm-3'>";
        html += "<input type='text' required class='form-control input-full' placeholder='街道号' name='streetNumber'>";
        html += "</div>";

        html += "<label class='col-sm-1 control-label'>附号</label>";
        html += "<div class='col-sm-3'>";
        html += "<input type='text' class='form-control input-full' placeholder='附号' name='attachNumber'>";
        html += "</div>";

        html += "<div class='col-sm-1'>";
        html += "<span class='input-group-btn'>" + "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='estateCommon.cleanHTMLData(this)'>" + "</span>";
        html += "</div>";

        html += "</div>";
        html += "</div>";
        html += "</div>";
        estateCommon.estateForm.find(".streetNumbers").append(html);
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

    //js数组去重复 ,直接重载在原生js上
    Array.prototype.deleteEle = function () {
        var newArr = this;
        for (var i = newArr.length - 1; i >= 0; i--) {
            var targetNode = newArr[i];
            for (var j = 0; j < i; j++) {
                if (targetNode == newArr[j]) {
                    newArr.splice(i, 1);
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
        random = 0;
        var objArray = [];
        obj.forEach(function (data, index) {
            //这里主要是获取对象长度
            var arr = Object.keys(data);
            objArray.push(arr.length);
        });
        if (objArray.deleteEle().length >= 2) {
            //说明修改过一次
            objArray.sort(function (a, b) {
                return a - b;
            });
            //获取有最大属性值的那个对象
            var max = objArray[objArray.length - 1];
            obj.forEach(function (data, index) {
                //这里主要是获取对象长度
                var arr = Object.keys(data);
                if (max == arr.length) {
                    return data;
                }
            });
        }
        var item = obj[random];
        return item;
    };

    //删除
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
                        group.find("input[name='landFactorTotalScore']").val(AssessCommon.pointToPercent(data.achievement));
                        group.find("input[name='dataLandLevelAchievement']").val(data.id);
                    }
                });
            });
        }
    };

    estateCommon.constructionInstallationEngineeringFeeEvent = {
        loadHtml: function () {
            developmentCommon.getMdArchitecturalObjList2({
                databaseName: AssessDBKey.BasicEstate,
                pid: estateCommon.getEstateId()
            }, function (objArray) {
                if (objArray.length >= 1) {
                    developmentCommon.getMdArchitecturalObjById(objArray[0].id, function (item) {
                        var target = $("#boxLandEngineering_Install");
                        var table = target.find("table");
                        var data = [];
                        try {
                            data = JSON.parse(item.jsonContent);
                        } catch (e) {
                            console.log("解析异常!");
                        }
                        estateCommon.constructionInstallationEngineeringFeeEvent.appendHTML(data, item.id);
                    });
                } else {
                    estateCommon.constructionInstallationEngineeringFeeEvent.appendHTML([], 0, '');
                }
            });
        },
        appendHTML: function (data, id) {
            var target = $("#boxLandEngineering_Install");
            var html = target.html();
            html = html.replace(/'{method}'/g, 'estateCommon.constructionInstallationEngineeringFeeEvent.save()');
            target.empty().append(html);
            target.modal("show");
            target.find(".card-body").empty();
            developmentCommon.architecturalB.appendHtml(target.find(".card-body"), data, null, '', function (tr) {
                var obj = {disable: 'disable', readonly: "readonly", 'class': 'form-control input-full'};
                $(tr).find("input[name='price']").attr(obj);
                $(tr).find("input[name='remark']").attr(obj);
                $(tr).find("input[name='area']").attr(obj);
            });
            target.find("input[name='constructionInstallationEngineeringFeeId']").val(id);
        },
        save: function () {
            var pid = estateCommon.getEstateId();
            var target = $("#boxLandEngineering_Install");
            var table = target.find("table");
            var obj = {};
            obj.databaseName = AssessDBKey.BasicEstate;
            obj.pid = pid;
            obj.id = target.find("input[name='constructionInstallationEngineeringFeeId']").val();
            developmentCommon.saveMdArchitecturalObj2(developmentCommon.architecturalB.getFomData(table), obj, function (item) {
                notifySuccess('成功', '保存成功!');
            });
            target.modal("hide");
        }
    };

    //土地因素
    estateCommon.openLevelDetailModal = function (this_) {
        var landLevelId = $(this_).closest('.input-group').find('input[name="landLevel"]').val();
        if (!landLevelId) {
            alert('请选择土地级别');
        }
        var landLevelContentResult = estateCommon.estateLandStateForm.find("input[name='landLevelContentResult']").val();
        if (estateCommon.isNotBlank(landLevelContentResult)) {
            estateCommon.landLevelLoadEditHtml();
        } else {
            $.ajax({
                url: getContextPath() + "/dataLandLevelDetailAchievement/landLevelFilter",
                type: "get",
                data: {levelDetailId: landLevelId},
                success: function (result) {
                    estateCommon.landLevelLoadHtml(result.data, false);
                }
            })
        }
    };

    estateCommon.landLevelLoadHtml = function (data, beEcho) {
        console.log(data) ;
        if (jQuery.isEmptyObject(data)) {
            return false;
        }
        $("#detailAchievementModal").modal();
        var target = $("#landLevelTabContent");
        target.empty();

        //由于js来筛选 有大量json 解析或者字符串化 影响代码阅读度，因此改为了后台直接处理,第一次的时候有2此筛选分类这样确实代码可读性差
        data.forEach(function (dataA, indexM) {
            $.each(dataA, function (i, obj) {
                //默认选中最优
                var item;
                if (beEcho) {
                    obj.forEach(function (value, index) {
                        if (value.modelStr == "update") {
                            item = value;
                        }
                    })
                } else {
                    item = estateCommon.getLandLevelFilter(obj);
                }

                var landLevelBodyHtml = $("#landLevelTabContentBody").html();
                if (landLevelBodyHtml) {
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{dataLandLevelAchievement}/g, item.id);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landFactorTotalScore}/g, AssessCommon.pointToPercent(item.achievement));
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelCategoryName}/g, item.categoryName);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelTypeName}/g, item.typeName);
                    var text = "";
                    $.each(obj, function (i, n) {
                        text += "等级:" + n.gradeName + "，说明:" + n.reamark + "； \r";
                    });
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{reamark}/g, text);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelContent}/g, JSON.stringify(obj));
                    AssessCommon.loadAsyncDataDicByKey(AssessDicKey.programmeMarketCostapproachGrade, item.grade, function (html, data) {
                        landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelGradeHTML}/g, html);
                        target.append(landLevelBodyHtml);
                    }, false);
                }
            });

        });
    };

    estateCommon.landLevelLoadEditHtml = function () {
        var landLevelContentResult = estateCommon.estateLandStateForm.find("input[name='landLevelContentResult']").val();
        var jsonContent = JSON.parse(landLevelContentResult);
        var data = estateCommon.landLevelFilter(jsonContent);
        estateCommon.landLevelLoadHtml(data, true);
    };

    estateCommon.saveLandLevelTabContent = function () {
        var landLevelContent = [];
        var landFactorTotalScoreResult = 0;

        $("#landLevelContentFrm").find("input[name='landLevelContent']").each(function (i, n) {
            var group = $(n).closest(".group");
            var dataLandLevelAchievement = group.find("input[name='dataLandLevelAchievement']").val();
            var landFactorTotalScore = AssessCommon.percentToPoint(group.find("input[name='landFactorTotalScore']").val());
            landFactorTotalScoreResult += Number(landFactorTotalScore);
            var obj = JSON.parse($(n).val());
            var dataObject = [];
            obj.forEach(function (value, index) {
                if (value.id == dataLandLevelAchievement) {
                    value.modelStr = "update";
                    value.achievement = landFactorTotalScore;
                } else {
                    delete value.modelStr;
                }
                dataObject.push(value);
            });
            landLevelContent.push(dataObject);
        });
        if (landLevelContent.length >= 1) {
            estateCommon.estateLandStateForm.find("input[name='landLevelContentResult']").val(JSON.stringify(landLevelContent));
            estateCommon.estateLandStateForm.find("input[name='landFactorTotalScoreResult']").val(landFactorTotalScoreResult);
        } else {
            estateCommon.estateLandStateForm.find("input[name='landFactorTotalScoreResult']").val('');
            estateCommon.estateLandStateForm.find("input[name='landLevelContentResult']").val('');
        }
        $("#detailAchievementModal").modal('hide');
    }

    //土地因素
    estateCommon.landLevelLoadHtmlApproval = function () {
        var landLevelContent = estateCommon.estateLandStateForm.find("input[name='landLevelContent']").val();

        var jsonContent = JSON.parse(landLevelContent);
        var data = estateCommon.landLevelFilter(jsonContent);
        if (jQuery.isEmptyObject(data)) {
            return false;
        }

        $("#detailAchievementModal").modal();
        var target = $("#landLevelTabContent");
        target.empty();

        //由于js来筛选 有大量json 解析或者字符串化 影响代码阅读度，因此改为了后台直接处理,第一次的时候有2此筛选分类这样确实代码可读性差
        data.forEach(function (dataA, indexM) {
            $.each(dataA, function (i, obj) {
                var item;
                obj.forEach(function (value, index) {
                    if (value.modelStr == "update") {
                        item = value;
                    }
                });
                var landLevelBodyHtml = $("#landLevelTabContentBody").html();
                if (landLevelBodyHtml) {
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelCategoryName}/g, item.category);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelTypeName}/g, item.typeName);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{gradeName}/g, item.gradeName);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landFactorTotalScore}/g, AssessCommon.pointToPercent(item.achievement));
                    var text = "";
                    $.each(obj, function (i, n) {
                        text += "等级:" + n.gradeName + "，说明:" + n.reamark + "； \r";
                    });
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{reamark}/g, text);
                    target.append(landLevelBodyHtml);
                }
            });

        });
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