/**
 * Created by asus on 2018/11/24.
 */

;(function ($) {
    var buildingCommon = {};
    buildingCommon.buildingForm = $('#basicBuildingFrm');
    buildingCommon.buildingMapiframe = undefined;
    buildingCommon.applyId = undefined;
    buildingCommon.tableId = undefined;
    buildingCommon.tableName = AssessDBKey.BasicBuilding;
    //附件上传控件id数组
    buildingCommon.buildingFileControlIdArray = [
    ];

    buildingCommon.getUploadKey = function(bisDetail){
        buildingCommon.getFilePartHtml(AssessDicKey.examineBasicBuilding,bisDetail);
    };

    buildingCommon.getFilePartHtml = function(fieldName,bisDetail){
        var fileDiv = buildingCommon.buildingForm.find('#'+fieldName);
        fileDiv.empty();
        var buildingFileHtml = '';
        AssessCommon.loadAsyncDataDicByKey(fieldName, '', function (html, resultData) {
            var divLength = Math.ceil(resultData.length/3);
            for (var j = 0; j < divLength; j++) {
                buildingFileHtml += '<div class="row form-group">';
                buildingFileHtml += '<div class="col-md-12">';
                buildingFileHtml += '<div class="form-inline x-valid">';
                var length = (j+1)*3>resultData.length?resultData.length:(j+1)*3;
                for (var i = j*3; i < length; i++) {
                    buildingFileHtml += '<label class="col-sm-1">'+resultData[i].name+'</label>';
                    buildingFileHtml += '<div class="col-sm-3">';
                    if (bisDetail != false) {
                        buildingFileHtml += '<input id="' + resultData[i].fieldName + '" placeholder="上传附件" class="form-control input-full" type="file">';
                    }
                    buildingFileHtml += '<div id="_' + resultData[i].fieldName + '"></div>';
                    buildingFileHtml += '</div>';
                    buildingCommon.buildingFileControlIdArray.push(resultData[i].fieldName);
                }
                buildingFileHtml += "</div>";
                buildingFileHtml += "</div>";
                buildingFileHtml += "</div>";
            }

        }, false);
        fileDiv.append(buildingFileHtml);
    };

    buildingCommon.getBuildingId = function () {
        var id = buildingCommon.buildingForm.find('[name=id]').val() != null ? buildingCommon.buildingForm.find('[name=id]').val() : buildingCommon.tableId;
        if (id) {
            return id;
        }
        return 0;
    };

    buildingCommon.getBuildingNumber = function () {
        return buildingCommon.buildingForm.find('[name=buildingNumber]').val();
    };

    buildingCommon.buildingNumberBlur = function (_this) {
        var value = $(_this).val();
        buildingCommon.buildingForm.find('[name=buildingName]').val(value + '栋');
    };


    //楼栋初始化by applyId
    buildingCommon.init = function (applyId, callback) {
        $.ajax({
            url: getContextPath() + '/basicBuilding/getBasicBuildingByApplyId',
            type: 'get',
            data: {applyId: applyId},
            success: function (result) {
                if (result.ret) {
                    buildingCommon.showBuildingView(result.data);
                    buildingCommon.applyId = applyId;
                    if (callback) {
                        callback(result.data);
                    }
                }
            }
        })
    };

    //楼栋初始化by id
    buildingCommon.initById = function (id, callback) {
        $.ajax({
            url: getContextPath() + '/basicBuilding/getBasicBuildingById',
            type: 'get',
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        buildingCommon.showBuildingView(result.data);
                    }
                    if (callback) {
                        callback(result.data);
                    }
                }
            }
        })
    };

    //楼栋详情页
    buildingCommon.initDetailById = function (id, callback,bisDetail) {
        $.ajax({
            url: getContextPath() + '/basicBuilding/getBasicBuildingById',
            type: 'get',
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        buildingCommon.initForm(result.data,bisDetail);
                    }
                    if (callback) {
                        callback(result.data);
                    }
                }
            }
        })
    };

    buildingCommon.detail = function (id, callback) {
        $.ajax({
            url: getContextPath() + '/basicBuilding/getBasicBuildingByApplyId',
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

    buildingCommon.showBuildingView = function (data) {
        buildingCommon.initForm(data);
    };

    //显示楼栋对应部分信息
    buildingCommon.showBuildingDetail = function (id) {
        $.ajax({
            url: getContextPath() + '/basicBuilding/getBasicBuildingById',
            type: 'get',
            async: false,
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    buildingCommon.initForm(result.data);
                }
            }
        })
    };


    buildingCommon.initForm = function (data,bisDetail) {
        try {
            data.estateId = buildingCommon.buildingForm.find("input[name='estateId']").val();
        } catch (e) {
        }
        buildingCommon.getUploadKey(bisDetail);
        buildingCommon.buildingForm.clearAll();
        buildingCommon.buildingForm.initForm(data);
        //1.初始化下拉框；2.初始化上传控件；3.显示已上传的附件信息；4.加载从表数据
        buildingCommon.buildingForm.find("select.buildingStructureType").off('change').on('change', function () {
            AssessCommon.loadDataDicByPid($(this).val(), data.buildingStructureCategory, function (html, data) {
                buildingCommon.buildingForm.find("select.buildingStructureCategory").empty().html(html).trigger('change');
            });
        });
        buildingCommon.buildingForm.find('select.propertyType').off('change').on('change', function () {
            AssessCommon.loadDataDicByPid($(this).val(), data.propertyCategory, function (html, data) {
                buildingCommon.buildingForm.find('select.propertyCategory').empty().html(html).trigger('change');
            });
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_construction_quality, data.constructionQuality, function (html, data) {
            buildingCommon.buildingForm.find('[name=constructionQuality]').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_appearance_style, data.appearanceStyle, function (html, data) {
            buildingCommon.buildingForm.find('[name=appearanceStyle]').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_appearance_new_and_old, data.appearanceNewAndOld, function (html, data) {
            buildingCommon.buildingForm.find('[name=appearanceNewAndOld]').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_type, data.propertyType, function (html, data) {
            buildingCommon.buildingForm.find('select.propertyType').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_structure, data.buildingStructureType, function (html, data) {
            buildingCommon.buildingForm.find('select.buildingStructureType').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.completed_time_type, data.completedTimeType, function (html, data) {
            buildingCommon.buildingForm.find('select.completedTimeType').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_between_distance, data.betweenDistance, function (html, data) {
            buildingCommon.buildingForm.find('select.betweenDistance').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_appearance_new_and_old, data.appearanceNewAndOld, function (html, data) {
            buildingCommon.buildingForm.find('[name=appearanceNewAndOld]').empty().html(html).trigger('change');
        });
        //建筑使用寿命 ---
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.examine_building_residence_data, data.residenceUseYear, function (html, data) {
            buildingCommon.buildingForm.find('select.residenceUseYear').empty().html(html).trigger('change');
        }, false);
        AssessCommon.loadDataDicByKey(AssessDicKey.data_company_reputation, data.propertySocialPrestige, function (html, data) {
            buildingCommon.buildingForm.find("select[name='propertySocialPrestige']").empty().html(html).trigger('change');
        });

        AssessCommon.loadTextAppendDicHtml(AssessDicKey.examine_building_minimumFloorDistance, null, function (html, data) {
            var minimumFloorDistanceList = $("#build_minimumFloorDistance_data");
            minimumFloorDistanceList.empty().html(html).trigger('change');
        }, true);
        $.ajax({
            url: getContextPath() + '/architecture/dataBuildingNewRateList',
            type: 'get',
            success: function (result) {
                if (result.ret) {
                    var retHtml = '<option value="" selected>-请选择-</option>';
                    $.each(result.data, function (i, item) {
                        retHtml += '<option key="' + item.buildingStructure + '" title="' + item.buildingStructure + '" value="' + item.id + '"';
                        if (item.id == data.industryUseYear) {
                            retHtml += 'selected="selected"'
                        }
                        retHtml += '>' + item.buildingStructure + '</option>';
                    });
                    buildingCommon.buildingForm.find('select.industryUseYear').empty().html(retHtml).trigger('change');
                }
            }
        });
        //建筑使用寿命 ---

        //初始化上传控件
        $.each(buildingCommon.buildingFileControlIdArray, function (i, item) {
            buildingCommon.fileUpload(item);
        });

        //附件显示
        $.each(buildingCommon.buildingFileControlIdArray, function (i, item) {
            if(bisDetail==false){
                buildingCommon.fileShow(item,false);
            }else {
                buildingCommon.fileShow(item,true);
            }
        });
        if (data.vSpecifications) {
            buildingCommon.writeSpecificationsHTMLData(data.vSpecifications);
            buildingCommon.addLableData(data.vSpecifications);
        }
        buildingCommon.getBasicBuildingPropertyServiceItemBootstrapTableVo(buildingCommon.getBuildingId(), $("#basicBuildingPropertyServiceItemTable"));
    };

    buildingCommon.minimumFloorDistanceEvent = function (_this) {
        var value = $(_this).val() ;
        if (! value){
            return false ;
        }
        var text = "相邻楼栋高度的{value}倍" ;
        text = text.replace(/{value}/g, value) ;
        buildingCommon.buildingForm.find("[name=betweenDistanceDescription]").val(text);
    };

    buildingCommon.addLableData = function (json) {
        if (json) {
            var jsonarray = eval(json);
            $.each(jsonarray, function (i, n) {
                var specificationNameId = 'specificationName' + i;
                var specificationContentId = 'specificationContent' + i;
                $("#" + specificationNameId).val(n["specificationName"]);
                $("#" + specificationContentId).val(n["specificationContent"]);
            })
        }
    }

    buildingCommon.writeSpecificationsHTMLData = function (json) {
        if (!json)return;
        $(".vSpecifications").empty();
        var jsonarray = eval(json);
        $.each(jsonarray, function (i, n) {
            var html = "<div class='form-group' >";

            html += "<div class='x-valid'>";
            html += "<label class='col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label'>" + "规格名称" + "</label>";
            html += "<div class='col-xs-3  col-sm-3  col-md-3  col-lg-3 '>";
            html += "<input type='text' required class='form-control' id='specificationName" + i + "' name='specificationName" + i + "' >";
            html += "</div>";
            html += "</div>";

            html += "<div class='x-valid'>";
            html += "<label class='col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label'>" + "规格内容" + "</label>";
            html += "<div class='col-xs-3  col-sm-3  col-md-3  col-lg-3 '>";
            html += "<input type='text' required class='form-control' id='specificationContent" + i + "' name='specificationContent" + i + "' >";
            html += "</div>";
            html += "</div>";

            html += "<div class='x-valid'>";
            html += " <div class=' col-xs-1  col-sm-1  col-md-1  col-lg-1 '>";
            html += "<input class='btn btn-warning' type='button' value='X' onclick='cleanHTMLData(this)'>" + "</span>";
            html += "</div>";
            html += "</div>";

            html += "</div>";
            $(".vSpecifications").append(html);
        })
    }

    //楼栋标注
    buildingCommon.mapMarker = function (readonly) {
        var select = {tableId:buildingCommon.buildingForm.find("[name='estateId']").val(),type:"estate"} ;
        examineCommon.getApplyBatchEstateTaggingsByTableId(select,function (data) {
            if (data.length == 0){
                notifyInfo("提示","请先标注楼盘");
                return false;
            }
            var item = data[0] ;
            var params = {estateName:item.name,lng:item.lng , lat:item.lat} ;
            var contentUrl = getContextPath() + '/map/mapMarkerEstate?' + examineCommon.parseParam(params);
            if (readonly != true) {
                contentUrl += '&click=buildingCommon.addMarker';
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
                    buildingCommon.buildingMapiframe = window[layero.find('iframe')[0]['name']];
                    buildingCommon.loadMarkerList();
                }
            });
        }) ;
    };


    buildingCommon.mapNewMarker = function (_this, readonly) {
        var tempObj = {type: "estate", tableId: buildingCommon.buildingForm.find("[name='estateId']").val()};
        toolMapHandleFun.getToolMapHandleListByExample(tempObj, function (result) {
            var objArray = [];
            if (result.length != 0) {
                for (var i = 0; i < result.length; i++) {
                    if (result[i].type != tempObj.type) {
                        continue;
                    }
                    if (result[i].tableId != tempObj.tableId) {
                        continue;
                    }
                    objArray.push(result[i]);
                }
            }
            if (!readonly) {
                if (objArray.length == 0) {
                    notifyInfo("提示","请先标注楼盘");
                    return false;
                }
            }
            var data = {};
            data.drawState = 'marker';
            data.id = $(_this).closest('.form-group').find("[name='mapId']").val();
            data.readonly = readonly;
            data.multiple = false;//不允许多个标记
            data.type = "building";//兼容以前数据
            data.tableId = buildingCommon.getBuildingId();
            data.callback = function (item, result) {
                $(_this).closest('.form-group').find("[name='mapId']").val(item.id);
            };
            if (objArray.length != 0) {
                data.center = {lng: objArray[0].lng, lat: objArray[0].lat};
            }
            toolMapHandleFun.loadMap(data);
        });
    };

    //添加标注
    buildingCommon.addMarker = function (lng, lat) {
        examineCommon.addBasicEstateTagging({
            tableId: buildingCommon.getBuildingId(),
            type: 'building',
            lng: lng,
            lat: lat,
            name: buildingCommon.getBuildingNumber()
        },function () {
            buildingCommon.loadMarkerList();//标注成功后，刷新地图上的标注
        })
    };

    //加载标注
    buildingCommon.loadMarkerList = function () {
        examineCommon.getApplyBatchEstateTaggingsByTableId({
            tableId: buildingCommon.getBuildingId(),
            type: 'building'
        },function (data) {
            if (buildingCommon.buildingMapiframe){
                buildingCommon.buildingMapiframe.loadMarkerList(data);//标注成功后，刷新地图上的标注
            }
        })
    };

    //楼盘标注（通过tableId）
    buildingCommon.mapMarker2 = function (readonly, tableId) {
        buildingCommon.tableId = tableId;
        var contentUrl = getContextPath() + '/map/mapMarkerEstateByTableId?tableId=' + buildingCommon.tableId + '&tableName=' + buildingCommon.tableName;
        if (readonly != true) {
            contentUrl += '&click=buildingCommon.addMarker2';
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
                buildingCommon.estateMapiframe = window[layero.find('iframe')[0]['name']];
                buildingCommon.loadMarkerList2(tableId);
            }
        });
    };

    //添加标注（通过tableId）
    buildingCommon.addMarker2 = function (lng, lat) {
        examineCommon.addBasicEstateTagging({
            tableId: buildingCommon.tableId,
            type: 'building',
            lng: lng,
            lat: lat,
            name: buildingCommon.getBuildingNumber()
        },function () {
            buildingCommon.loadMarkerList2(buildingCommon.tableId);//标注成功后，刷新地图上的标注
        }) ;
    };

    //加载标注（通过tableId）
    buildingCommon.loadMarkerList2 = function (tableId) {
        examineCommon.getApplyBatchEstateTaggingsByTableId({
            tableId: tableId,
            type: 'building'
        },function (data) {
            if (buildingCommon.estateMapiframe){//标注成功后，刷新地图上的标注
                buildingCommon.estateMapiframe.loadMarkerList(data);
            }
        }) ;
    };


    //附件上传
    buildingCommon.fileUpload = function (fieldsName) {
        FileUtils.uploadFiles({
            target: fieldsName,
            disabledTarget: "btn_submit",
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.BasicBuilding,
                tableId: buildingCommon.getBuildingId()
            },
            deleteFlag: true
        });
    };

    //附件显示
    buildingCommon.fileShow = function (fieldsName,deleteFlag) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.BasicBuilding,
                tableId: buildingCommon.getBuildingId()
            },
            deleteFlag: deleteFlag
        })
    };

    buildingCommon.getBasicBuildingPropertyServiceItemBootstrapTableVo = function (buildingId, selectId) {
        var cols = [];
        cols.push({checkbox: true});
        cols.push({field: 'serviceTypeName', title: '服务类型'});
        cols.push({field: 'serviceContentName', title: '服务内容'});
        cols.push({field: 'serviceTime', title: '服务时间'});
        cols.push({field: 'gradeEvaluationName', title: '等级评价'});
        selectId.bootstrapTable('destroy');
        TableInit(selectId.attr("id"), getContextPath() + "/basicBuildingPropertyServiceItem/getBasicBuildingPropertyServiceItemList?buildingId=" + buildingId, cols, {}, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
        var toolbar = $("#toolbarBuildingPropertyServiceItemTable");
        if (toolbar.size() != 0) {
            var bootstrapTable = selectId.closest(".bootstrap-table");
            if (bootstrapTable.size() != 0) {
                var fixedTableToolbar = bootstrapTable.find(".fixed-table-toolbar");
                if (fixedTableToolbar.size() != 0) {
                    fixedTableToolbar.append(toolbar.html());
                }
            }
        }
    };

    buildingCommon.deleteBasicBuildingPropertyServiceItem = function (table) {
        var rows = $(table).bootstrapTable('getSelections');
        if (rows.length >= 1) {
            var data = [];
            $.each(rows, function (i, item) {
                data.push(item.id);
            });
            $.ajax({
                url: getContextPath() + "/basicBuildingPropertyServiceItem/deleteBasicBuildingPropertyServiceItemById",
                type: "post",
                dataType: "json",
                data: {id: data.join(",")},
                success: function (result) {
                    if (result.ret) {
                        $(table).bootstrapTable('refresh');
                        notifySuccess('成功','删除成功!');
                    }
                    else {
                        AlertError("失败","数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            });
        } else {
            notifyInfo('提示','至少勾选一个!');
        }
    };

    buildingCommon.editBasicBuildingPropertyServiceItem = function (table, box, flag) {
        if (flag) {
            var rows = $(table).bootstrapTable('getSelections');
            if (rows.length == 1) {
                var data = rows[0];
                dataPropertyModelQuote.initFormDataPropertyServiceItemModalTool($(box).find("form"), data);
                $(box).modal('show');
            } else {
                notifyInfo('提示','至少勾选一个!');
            }
        } else {
            var masterId = buildingCommon.buildingForm.find('input[name=property]').val();
            $(box).modal('show');
            dataPropertyModelQuote.initFormDataPropertyServiceItemModalTool($(box).find("form"), {
                masterId: masterId == undefined ? 0 : masterId,
                buildingId: buildingCommon.getBuildingId()
            });
        }
    };

    buildingCommon.addBasicBuildingPropertyServiceItem = function (_this) {
        var frm = $(_this).parent().prev().find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        buildingCommon.saveAndUpdateBasicBuildingPropertyServiceItem([data], function () {
            $(_this).parent().parent().parent().parent().modal('hide');
            notifySuccess('成功','成功!');
            $("#basicBuildingPropertyServiceItemTable").bootstrapTable('refresh');
        });
    };

    buildingCommon.saveAndUpdateBasicBuildingPropertyServiceItem = function (data, callback) {
        $.ajax({
            url: getContextPath() + "/basicBuildingPropertyServiceItem/saveAndUpdateBasicBuildingPropertyServiceItem",
            type: "post",
            dataType: "json",
            data: {formData: JSON.stringify(data)},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                }
                else {
                    AlertError("失败","数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    };

    buildingCommon.autocompleteStart = function () {
        buildingCommon.buildingForm.find('input[name=propertyName]').apProperty({
            onSelect: function (id, name) {
                buildingCommon.buildingForm.find('input[name=property]').val(id);
                buildingCommon.buildingForm.find('input[name=propertyName]').val(name);
                if (dataPropertyModelQuote) {
                    dataPropertyModelQuote.getDataProperty(id, function (data) {
                        buildingCommon.buildingForm.find("select[name='propertyCompanyNature']").val(data.companyNature).attr("selected", true).trigger('change');
                        buildingCommon.buildingForm.find("select[name='propertySocialPrestige']").val(data.socialPrestige).trigger('change');
                    });
                    dataPropertyModelQuote.getDataPropertyServiceItemVoList(id, function (data) {
                        var item = [];
                        $.each(data, function (i, n) {
                            var obj = {};
                            $.extend(obj, n);
                            obj.buildingId = buildingCommon.getBuildingId();
                            obj.masterId = id;
                            obj.id = null;
                            item.push(obj);
                        });
                        buildingCommon.saveAndUpdateBasicBuildingPropertyServiceItem(item, function () {
                            $("#basicBuildingPropertyServiceItemTable").bootstrapTable('refresh');
                        });
                    });
                }
            }
        });
        buildingCommon.buildingForm.find('input[name=builderName]').apBuilder({
            onSelect: function (id, name) {
                buildingCommon.buildingForm.find('input[name=builder]').val(id);
                buildingCommon.buildingForm.find('input[name=builderName]').val(name);
            }
        });
    };


    buildingCommon.constructionInstallationEngineeringFeeEvent = {
        loadHtml: function () {
            developmentCommon.getMdArchitecturalObjList2({
                databaseName: AssessDBKey.BasicBuilding,
                pid: buildingCommon.getBuildingId()
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
                        buildingCommon.constructionInstallationEngineeringFeeEvent.appendHTML(data, item.id);
                    });
                } else {
                    buildingCommon.constructionInstallationEngineeringFeeEvent.appendHTML([], 0, '');
                }
            });
        },
        appendHTML: function (data, id) {
            var target = $("#boxLandEngineering_Install");
            var html = target.html();
            html = html.replace(/'{method}'/g, 'buildingCommon.constructionInstallationEngineeringFeeEvent.save()');
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
            var pid = buildingCommon.getBuildingId();
            var target = $("#boxLandEngineering_Install");
            var table = target.find("table");
            var obj = {};
            obj.databaseName = AssessDBKey.BasicBuilding;
            obj.pid = pid;
            obj.id = target.find("input[name='constructionInstallationEngineeringFeeId']").val();
            developmentCommon.saveMdArchitecturalObj2(developmentCommon.architecturalB.getFomData(table), obj, function (item) {
                notifySuccess('成功','保存成功!');
            });
            target.modal("hide");
        }
    };


    window.buildingCommon = buildingCommon;
})(jQuery);