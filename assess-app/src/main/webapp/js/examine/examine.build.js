/**
 * Created by asus on 2018/11/24.
 */

;(function ($) {
    var buildingCommon = {};
    buildingCommon.buildingForm = $('#basicBuildingFrm');
    //附件上传控件id数组
    buildingCommon.buildingFileControlIdArray = [
        AssessUploadKey.BUILDING_FLOOR_PLAN,
        AssessUploadKey.BUILDING_FIGURE_OUTSIDE,
        AssessUploadKey.BUILDING_FLOOR_APPEARANCE_FIGURE
    ];

    buildingCommon.getBuildingId = function () {
        var id = buildingCommon.buildingForm.find('[name=id]').val();
        if (id) {
            return id;
        }
        return 0;
    };

    buildingCommon.getBuildingNumber = function () {
        return buildingCommon.buildingForm.find('[name=buildingNumber]').val();
    };

    buildingCommon.buildingNumberBlur = function (_this) {
        var value = $(_this).val() ;
        buildingCommon.buildingForm.find('[name=buildingName]').val(value+'栋');
    };


    buildingCommon.detail = function (id) {
        $.ajax({
            url: getContextPath() + '/basicBuilding/getBasicBuildingByApplyId',
            type: 'get',
            data: {applyId: id},
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        buildingCommon.initForm(result.data);
                    }
                }
            }
        })
    };


    buildingCommon.initForm = function (data) {
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
            buildingCommon.fileShow(item);
        });
        buildingModelView.prototype.viewInit();
        if (data.vSpecifications) {
            buildingCommon.writeSpecificationsHTMLData(data.vSpecifications);
            buildingCommon.addLableData(data.vSpecifications);
        }
        buildingCommon.getBasicBuildingPropertyServiceItemBootstrapTableVo(buildingCommon.getBuildingId(), $("#basicBuildingPropertyServiceItemTable"));
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
            area: ['97%', '80%'],
            content: contentUrl,
            success: function (layero) {
                buildingCommon.buildingMapiframe = window[layero.find('iframe')[0]['name']];
                buildingCommon.loadMarkerList();
            }
        });
    };

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
    };

    //加载标注
    buildingCommon.loadMarkerList = function () {
        $.ajax({
            url: getContextPath() + '/basicEstateTagging/getEstateTaggingList',
            data: {
                applyId: basicCommon.getApplyId(),
                type: 'building'
            },
            success: function (result) {
                if (result.ret && buildingCommon.buildingMapiframe) {//标注成功后，刷新地图上的标注
                    buildingCommon.buildingMapiframe.loadMarkerList(result.data);
                }
            }
        })
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
    buildingCommon.fileShow = function (fieldsName) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: AssessDBKey.BasicBuilding,
                tableId: buildingCommon.getBuildingId()
            },
            deleteFlag: true
        })
    };

    buildingCommon.onSelect = function (id) {
        Loading.progressShow();
        $.ajax({
            url: getContextPath() + '/basicBuilding/appWriteBuilding',
            data: {
                applyId: basicCommon.getApplyId(),
                caseBuildingId: id
            },
            type: 'post',
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    basicCommon.update({caseBuildingId: id, id: basicCommon.getApplyId()}, function () {
                        basicCommon.basicApplyForm.find("input[name='caseBuildingId']").val(id);
                        buildingCommon.detail(basicCommon.getApplyId());
                    });
                } else {
                    console.log(result.errmsg);
                    Alert("转移失败!");
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
                        toastr.success('删除成功!');
                    }
                    else {
                        Alert("数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        } else {
            toastr.success('至少勾选一个!');
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
                toastr.success('勾选一个!');
            }
        }else {
            var masterId = buildingCommon.buildingForm.find('input[name=property]').val();
            $(box).modal('show');
            dataPropertyModelQuote.initFormDataPropertyServiceItemModalTool($(box).find("form"), {masterId:masterId == undefined?0:masterId,buildingId:buildingCommon.getBuildingId()});
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
            toastr.success('成功!');
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
                Alert("调用服务端方法失败，失败原因:" + result);
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
        $("#txt_building_search").apBuilding({
            caseEstateId: function () {
                return basicCommon.getCaseEstateId();
            },
            onSelect: function (id, name) {
                buildingCommon.onSelect(id);
            }
        });
    };


    window.buildingCommon = buildingCommon;
})(jQuery);