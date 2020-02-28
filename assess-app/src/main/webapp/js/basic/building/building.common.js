/**
 * Created by asus on 2018/11/24.
 */

;(function ($) {
    var buildingCommon = {};
    buildingCommon.buildingForm = $('#basicBuildingFrm');
    buildingCommon.buildingMapiframe = undefined;
    buildingCommon.applyId = undefined;
    buildingCommon.tableId = undefined;
    buildingCommon.tableName = 'tb_basic_building';
    //附件上传控件id数组
    buildingCommon.buildingFileControlIdArray = [
        AssessUploadKey.BUILDING_FLOOR_PLAN,
        AssessUploadKey.BUILDING_FIGURE_OUTSIDE,
        AssessUploadKey.BUILDING_FLOOR_APPEARANCE_FIGURE
    ];

    buildingCommon.getBuildingId = function () {
        return buildingCommon.buildingForm.find('[name=id]').val();
    }
    buildingCommon.getBuildingNumber = function () {
        return buildingCommon.buildingForm.find('[name=buildingNumber]').val();
    }

    //添加楼栋
    buildingCommon.add = function (_this, callback) {
        var buildingNumber = $(_this).closest('form').find('[name=buildingNumber]').val();
        if (!buildingNumber) {
            toastr.info('请填写楼栋编号！');
            return false;
        }
        $.ajax({
            url: getContextPath() + '/basicBuilding/addBuilding',
            data: {
                buildingNumber: buildingNumber
            },
            success: function (result) {
                if (result.ret) {
                    buildingCommon.showBuildingView(result.data);
                    if (callback) {
                        callback($(_this).attr('data-mode'));
                    }
                }
            }
        })
    }

    //升级楼栋
    buildingCommon.upgrade = function (_this, callback) {
        var caseBuildingId = $(_this).closest('form').find("input[name='caseBuildingId']").val();
        var buildingPartInMode = $(_this).attr('data-mode');
        if (!caseBuildingId) {
            toastr.info('请选择系统中已存在的楼栋信息！');
            return false;
        }
        $.ajax({
            url: getContextPath() + '/basicBuilding/appWriteBuilding',
            data: {
                caseBuildingId: caseBuildingId,
                buildingPartInMode: buildingPartInMode
            },
            success: function (result) {
                if (result.ret) {
                    buildingCommon.showBuildingView(result.data);
                    if (callback) {
                        callback($(_this).attr('data-mode'));
                    }
                }
            }
        })
    }

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
    }

    //楼栋初始化by id
    buildingCommon.initById = function (id, callback) {
        $.ajax({
            url: getContextPath() + '/basicBuilding/getBasicBuildingById',
            type: 'get',
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    buildingCommon.showBuildingView(result.data);
                    if (callback) {
                        callback(result.data);
                    }
                }
            }
        })
    }

    //项目中引用数据
    buildingCommon.getDataFromProject = function (applyId, callback) {
        $.ajax({
            url: getContextPath() + '/basicBuilding/getDataFromProject',
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
    }

    //项目中引用楼盘(批量)
    buildingCommon.batchGetDataFromProject = function (applyId, tableId, callback) {
        $.ajax({
            url: getContextPath() + '/basicBuilding/batchGetDataFromProject',
            type: 'get',
            data: {applyId: applyId,
                tableId: tableId},
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
    }

    //楼栋明细
    buildingCommon.detail = function (applyId) {
        $.ajax({
            url: getContextPath() + '/basicBuilding/getBasicBuildingByApplyId',
            type: 'get',
            data: {applyId: applyId},
            success: function (result) {
                if (result.ret) {
                    buildingCommon.showBuildingDetail(result.data.id);
                    try {
                        buildingCommon.getBasicBuildingPropertyServiceItemBootstrapTableVo(result.data.id, $("#basicBuildingPropertyServiceItemTable"));
                    } catch (e) {
                    }
                }
            }
        })
    }

    //显示楼栋对应部分信息
    buildingCommon.showBuildingView = function (data) {
        buildingCommon.buildingForm.initForm(data, function () {
            //1.初始化下拉框；2.初始化上传控件；3.显示已上传的附件信息；4.加载从表数据
            buildingCommon.buildingForm.find("select.buildingStructureType").off('change').on('change', function () {
                AssessCommon.loadDataDicByPid($(this).val(), data.buildingStructureCategory, function (html, data) {
                    buildingCommon.buildingForm.find("select.buildingStructureCategory").empty().html(html).trigger('change');
                });
                data.buildingStructureCategory = null;
            });
            buildingCommon.buildingForm.find('select.propertyType').off('change').on('change', function () {
                AssessCommon.loadDataDicByPid($(this).val(), data.propertyCategory, function (html, data) {
                    buildingCommon.buildingForm.find('select.propertyCategory').empty().html(html).trigger('change');
                });
                data.propertyCategory = null;
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_type, data.propertyType, function (html, data) {
                buildingCommon.buildingForm.find('select.propertyType').empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_structure, data.buildingStructureType, function (html, data) {
                buildingCommon.buildingForm.find('select.buildingStructureType').empty().html(html).trigger('change');
            });
            //建筑使用寿命 ---
            AssessCommon.loadAsyncDataDicByKey(AssessDicKey.examine_building_residence_data, data.residenceUseYear, function (html, data) {
                buildingCommon.buildingForm.find('select.residenceUseYear').empty().html(html).trigger('change');
            }, false);
            AssessCommon.loadDataDicByKey(AssessDicKey.completed_time_type, data.completedTimeType, function (html, data) {
                buildingCommon.buildingForm.find('select.completedTimeType').empty().html(html).trigger('change');
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
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_between_distance, data.betweenDistance, function (html, data) {
                buildingCommon.buildingForm.find('select.betweenDistance').empty().html(html).trigger('change');
            });
            console.log(data.vSpecifications);
            if(data.vSpecifications) {
                buildingCommon.writeSpecificationsHTMLData(data.vSpecifications);
                buildingCommon.addLableData(data.vSpecifications);
            }
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
            })

            //附件显示
            $.each(buildingCommon.buildingFileControlIdArray, function (i, item) {
                buildingCommon.fileShow(item);
            })
            buildingModelView.prototype.viewInit(); //加载从表数据
        });
    }

    buildingCommon.addLableData = function(json) {
        var jsonarray = eval(json);
        $.each(jsonarray, function (i, n) {
            var specificationNameId = 'specificationName'+i;
            var specificationContentId = 'specificationContent'+i;
            $("#"+specificationNameId).val(n["specificationName"]);
            $("#"+specificationContentId).val(n["specificationContent"]);
        })
    }

    buildingCommon.writeSpecificationsHTMLData = function(json) {
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
    //显示楼栋对应部分信息
    buildingCommon.showBuildingDetail = function (id) {
        $.ajax({
            url: getContextPath() + '/basicBuilding/getBasicBuildingById',
            type: 'get',
            async: false,
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    buildingCommon.buildingForm.initForm(result.data, function () {
                        //附件显示
                        $.each(buildingCommon.buildingFileControlIdArray, function (i, item) {
                            buildingCommon.fileShow(item);
                        })
                    });
                }
            }
        })
    }

    //附件上传
    buildingCommon.fileUpload = function (fieldsName) {
        FileUtils.uploadFiles({
            target: fieldsName,
            disabledTarget: "btn_submit",
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.BasicBuilding,
                tableId: buildingCommon.buildingForm.find('[name=id]').val()
            },
            deleteFlag: true
        });
    }

    //附件显示
    buildingCommon.fileShow = function (fieldsName, deleteFlag) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.BasicBuilding,
                tableId: buildingCommon.buildingForm.find('[name=id]').val()
            },
            deleteFlag: deleteFlag == undefined ? true : deleteFlag
        })
    }

    //楼栋标注
    buildingCommon.mapMarker = function (readonly) {
        var contentUrl = getContextPath() + '/map/mapMarkerEstate?estateName=' + estateCommon.getEstateName();
        if (readonly != true) {
            contentUrl += '&click=buildingCommon.addMarker';
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
                buildingCommon.buildingMapiframe = window[layero.find('iframe')[0]['name']];
                buildingCommon.loadMarkerList(buildingCommon.applyId);
            }
        });
    }

    //添加标注
    buildingCommon.addMarker = function (lng, lat) {
        $.ajax({
            url: getContextPath() + '/basicEstateTagging/addBasicEstateTagging',
            data: {
                applyId: basicCommon.getApplyId(),
                type: 'building',
                lng: lng,
                lat: lat,
                name: buildingCommon.getBuildingNumber()
            },
            success: function (result) {
                if (result.ret) {//标注成功后，刷新地图上的标注
                    buildingCommon.loadMarkerList();
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    }

    //加载标注
    buildingCommon.loadMarkerList = function (applyId) {
        $.ajax({
            url: getContextPath() + '/basicEstateTagging/getEstateTaggingList',
            data: {
                applyId: applyId?applyId:basicCommon.getApplyId(),
                type: 'building'
            },
            success: function (result) {
                if (result.ret && buildingCommon.buildingMapiframe) {//标注成功后，刷新地图上的标注
                    buildingCommon.buildingMapiframe.loadMarkerList(result.data);
                }
            }
        })
    }

    //楼盘标注（通过tableId）
    buildingCommon.mapMarker2 = function (readonly,tableId) {
        buildingCommon.tableId = tableId;
        console.log(tableId+"===");
        var contentUrl = getContextPath() + '/map/mapMarkerEstateByTableId?tableId=' + buildingCommon.tableId+'&tableName='+buildingCommon.tableName;
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
    }

    //添加标注（通过tableId）
    buildingCommon.addMarker2 = function (lng, lat) {
        $.ajax({
            url: getContextPath() + '/basicEstateTagging/addBasicEstateTaggingByTableId',
            data: {
                tableId: buildingCommon.tableId,
                type: 'building',
                lng: lng,
                lat: lat,
                name: buildingCommon.getBuildingNumber()
            },
            success: function (result) {
                if (result.ret) {//标注成功后，刷新地图上的标注
                    buildingCommon.loadMarkerList2(buildingCommon.tableId);
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    }

    //加载标注（通过tableId）
    buildingCommon.loadMarkerList2 = function (tableId) {
        $.ajax({
            url: getContextPath() + '/basicEstateTagging/getApplyBatchEstateTaggingsByTableId',
            data: {
                tableId: tableId,
                type: 'building'
            },
            success: function (result) {
                if (result.ret && buildingCommon.estateMapiframe) {//标注成功后，刷新地图上的标注
                    buildingCommon.estateMapiframe.loadMarkerList(result.data);
                }
            }
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
            showColumns: true,
            showRefresh: true,
            search: false
        });
        var toolbar = $("#toolbarBuildingPropertyServiceItemTable");
        if (toolbar.size() != 0){
            var bootstrapTable = selectId.closest(".bootstrap-table") ;
            if (bootstrapTable.size() != 0){
                var fixedTableToolbar = bootstrapTable.find(".fixed-table-toolbar") ;
                if (fixedTableToolbar.size() != 0){
                    fixedTableToolbar.append(toolbar.html()) ;
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
                        Alert("数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            });
        } else {
            notifySuccess('成功','至少勾选一个!');
        }
    };

    buildingCommon.editBasicBuildingPropertyServiceItem = function (table, box,flag) {
        if (flag){
            var rows = $(table).bootstrapTable('getSelections');
            if (rows.length == 1) {
                var data = rows[0];
                dataPropertyModelQuote.initFormDataPropertyServiceItemModalTool($(box).find("form"), data);
                $(box).modal('show');
            } else {
                notifySuccess('成功','只能勾选一个!');
            }
        }else {
            var masterId = buildingCommon.buildingForm.find('input[name=property]').val();
            if (masterId){
                $(box).modal('show');
                dataPropertyModelQuote.initFormDataPropertyServiceItemModalTool($(box).find("form"), {masterId:masterId,buildingId:buildingCommon.getBuildingId()});
            }else {
                notifySuccess('成功','物业公司必选选择!');
            }
        }
    };

    buildingCommon.addBasicBuildingPropertyServiceItem = function (_this) {
        var frm = $(_this).parent().prev().find("form");
        if (!frm.valid()) {
            return false ;
        }
        var data = formSerializeArray(frm);
        buildingCommon.saveAndUpdateBasicBuildingPropertyServiceItem([data] , function () {
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
                    Alert("数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    };

    buildingCommon.autocompleteStart = function () {
        buildingCommon.buildingForm.find('input[name=propertyName]').apProperty({
            onSelect:function (id, name){
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
                            buildingCommon.getBasicBuildingPropertyServiceItemBootstrapTableVo(buildingCommon.getBuildingId(), $("#basicBuildingPropertyServiceItemTable"));
                        });
                    });
                }
            }
        });
        buildingCommon.buildingForm.find('input[name=builderName]').apBuilder({
            onSelect:function (id, name) {
                buildingCommon.buildingForm.find('input[name=builder]').val(id);
                buildingCommon.buildingForm.find('input[name=builderName]').val(name);
            }
        });
    };

    window.buildingCommon = buildingCommon;
})(jQuery);