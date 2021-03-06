/**
 * Created by kings on 2018-11-9.
 */


var matchingMainConversion;
;(function () {
    matchingMainConversion = function () {

    };
    matchingMainConversion.prototype = {
        config: function () {
            var data = {};
            data.table = "MatchingMainConversionList";
            data.box = "divBoxMatchingMainConversion";
            data.frm = "frmMatchingMainConversion";
            data.type = "mainConversion";//根据ExamineMatchingTrafficTypeEnum 配置
            return data;
        },
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function () {
            var cols = commonColumn.matchingMainConversionColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="matchingMainConversion.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="matchingMainConversion.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + matchingMainConversion.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingMainConversion.prototype.config().table, getContextPath() + "/basicMatchingTraffic/getBootstrapTableVo", cols, {
                type: matchingMainConversion.prototype.config().type,
                estateId: estateCommon.getEstateId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicMatchingTraffic/deleteBasicMatchingTraffic",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                       notifySuccess("成功", "删除成功");
                        matchingMainConversion.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            matchingMainConversion.prototype.init({});
            $("#" + matchingMainConversion.prototype.config().frm + " .type").val(matchingMainConversion.prototype.config().type);
            $('#' + matchingMainConversion.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + matchingMainConversion.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(matchingMainConversion.prototype.config().frm,true);
            data.estateId = estateCommon.getEstateId();
            $.ajax({
                url: getContextPath() + "/basicMatchingTraffic/saveAndUpdateBasicMatchingTraffic",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "保存成功");
                        $('#' + matchingMainConversion.prototype.config().box).modal('hide');
                        matchingMainConversion.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicMatchingTraffic/getBasicMatchingTrafficById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (matchingMainConversion.prototype.isNotBlank(result.data)) {
                            matchingMainConversion.prototype.init(result.data);
                        } else {
                            matchingMainConversion.prototype.init({});
                        }
                        $('#' + matchingMainConversion.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + matchingMainConversion.prototype.config().frm).clearAll();
            $("#" + matchingMainConversion.prototype.config().frm).initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_distance, item.distance, function (html, data) {
                $("#" + matchingMainConversion.prototype.config().frm).find("select.distance").empty().html(html).trigger('change');
            });
        }
    }

    //绑定事件
    $('#' + matchingMainConversion.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        matchingMainConversion.prototype.loadDataDicList();
    })
})();

var matchingMainRoad;
;(function () {
    matchingMainRoad = function () {

    };
    matchingMainRoad.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "MainRoadList";
            data.box = "divBoxMainRoad";
            data.frm = "frmMainRoad";
            data.type = "mainRoad";//根据ExamineMatchingTrafficTypeEnum 配置
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.matchingMainRoadColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="matchingMainRoad.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="matchingMainRoad.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + matchingMainRoad.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingMainRoad.prototype.config().table, getContextPath() + "/basicMatchingTraffic/getBootstrapTableVo", cols, {
                type: matchingMainRoad.prototype.config().type,
                estateId: estateCommon.getEstateId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicMatchingTraffic/deleteBasicMatchingTraffic",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        matchingMainRoad.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            matchingMainRoad.prototype.init({});
            $("#" + matchingMainRoad.prototype.config().frm + " .type").val(matchingMainRoad.prototype.config().type);
            $('#' + matchingMainRoad.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + matchingMainRoad.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(matchingMainRoad.prototype.config().frm,true);
            data.estateId = estateCommon.getEstateId();
            $.ajax({
                url: getContextPath() + "/basicMatchingTraffic/saveAndUpdateBasicMatchingTraffic",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "保存成功");
                        $('#' + matchingMainRoad.prototype.config().box).modal('hide');
                        matchingMainRoad.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicMatchingTraffic/getBasicMatchingTrafficById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (matchingMainRoad.prototype.isNotBlank(result.data)) {
                            matchingMainRoad.prototype.init(result.data);
                        } else {
                            matchingMainRoad.prototype.init({});
                        }
                        $('#' + matchingMainRoad.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + matchingMainRoad.prototype.config().frm).clearAll();
            $("#" + matchingMainRoad.prototype.config().frm).initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_traffic_flow, item.trafficFlow, function (html, data) {
                $("#" + matchingMainRoad.prototype.config().frm).find("select.trafficFlow").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examine_house_visitors_flowrate, item.visitorsFlowrate, function (html, data) {
                $("#" + matchingMainRoad.prototype.config().frm).find("select.visitorsFlowrate").empty().html(html).trigger('change');
            });
            AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estate_position, item.position, function (html, data) {
                $("#" + matchingMainRoad.prototype.config().frm).find('select.position').empty().html(html).trigger('change');
            }, true);
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_distance, item.distance, function (html, data) {
                $("#" + matchingMainRoad.prototype.config().frm).find("select.distance").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_mainRoad_limitSpeial, item.limitSpeial, function (html, data) {
                $("#" + matchingMainRoad.prototype.config().frm).find("select.limitSpeial").empty().html(html).trigger('change');
            });
            $("#" + matchingMainRoad.prototype.config().frm).find("#limitBasicMatchingTraffic2").val(true);
            $("#" + matchingMainRoad.prototype.config().frm).find("#limitBasicMatchingTraffic1").val(false);
            if (item.flag){
                $("#" + matchingMainRoad.prototype.config().frm).find("#limitBasicMatchingTraffic2").attr('checked', 'checked');
            }else {
                $("#" + matchingMainRoad.prototype.config().frm).find("#limitBasicMatchingTraffic1").attr('checked', 'checked');
            }
        },
        showLimit:function (that) {
            var item = $(that);
            console.log(item.val()+'==')
            if (item.val() == 'true'){
                $(that).parent().parent().parent().parent().parent().parent().next().show();
            }else {
                $(that).parent().parent().parent().parent().parent().parent().next().hide();
            }
        }
    }

    //绑定事件
    $('#' + matchingMainRoad.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        matchingMainRoad.prototype.loadDataDicList();
    })
})();

var matchingMetro;
;(function () {
    matchingMetro = function () {

    };
    matchingMetro.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "MatchingMetroList";
            data.box = "divBoxMatchingMetro";
            data.frm = "frmMatchingMetro";
            data.type = "metro";//根据 ExamineMatchingTrafficTypeEnum 配置
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.matchingMetroColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="matchingMetro.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="matchingMetro.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + matchingMetro.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingMetro.prototype.config().table, getContextPath() + "/basicMatchingTraffic/getBootstrapTableVo", cols, {
                type: matchingMetro.prototype.config().type,
                estateId: estateCommon.getEstateId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            },true);
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicMatchingTraffic/deleteBasicMatchingTraffic",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        matchingMetro.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        clear:function () {
            var data = $("#" + matchingMetro.prototype.config().table).bootstrapTable('getSelections');
            var ids = "";
            if (data.length >= 1){
                $.each(data, function (i, n) {
                    if (i == data.length - 1) {
                        ids += n.id;
                    } else {
                        ids += n.id + ",";
                    }
                });
                $.ajax({
                    url: getContextPath() + "/basicMatchingTraffic/removeIds",
                    type: "post",
                    dataType: "json",
                    data: {ids: ids},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除成功");
                            matchingMetro.prototype.loadDataDicList();
                        }
                    }
                })
            }else {
                AlertError("至少选择一条数据!") ;
            }
        },
        showModel: function () {
            matchingMetro.prototype.init({});
            $("#" + matchingMetro.prototype.config().frm + " .type").val(matchingMetro.prototype.config().type);
            $("#" + matchingMetro.prototype.config().frm + " .theLine").empty();
            var lableValue = "所在线路";
            var html = "<div class='row form-group' style='margin-top:8px;'>";
            html += '<div class="col-sm-12">';
            html += '<div class="form-inline x-valid">';
            html += "<label class='col-sm-2 control-label'>" + lableValue + "</label>";
            html += "<div class='col-sm-8'>";
            html += "<input type='text' required class='form-control input-full'" + "name='" + 'theLine' + "'" + ">";
            html += "</div>";

            html += "<div class='col-sm-2'>";
            html += "<span class='input-group-btn'>" + "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='matchingMetro.prototype.cleanHTMLData(this)'>" + "</span>";
            html += "</div>";

            html += "</div>";
            html += "</div>";
            html += "</div>";
            $("#" + matchingMetro.prototype.config().frm + " .theLine").append(html);
            $('#' + matchingMetro.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + matchingMetro.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(matchingMetro.prototype.config().frm,true);
            data.estateId = estateCommon.getEstateId();
            $.ajax({
                url: getContextPath() + "/basicMatchingTraffic/saveAndUpdateBasicMatchingTraffic",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                       notifySuccess("成功", "保存成功");
                        $('#' + matchingMetro.prototype.config().box).modal('hide');
                        matchingMetro.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicMatchingTraffic/getBasicMatchingTrafficById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (matchingMetro.prototype.isNotBlank(result.data)) {
                            matchingMetro.prototype.init(result.data);
                        } else {
                            matchingMetro.prototype.init({});
                        }
                        $('#' + matchingMetro.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        appendHTML: function (item, this_) {
            var lableValue = "所在线路";
            var html = "<div class='row form-group' style='margin-top:8px;'>";
            html += '<div class="col-sm-12">';
            html += '<div class="form-inline x-valid">';
            html += "<label class='col-sm-2 control-label'>" + lableValue + "</label>";
            html += "<div class='col-sm-8'>";
            html += "<input type='text' required class='form-control input-full'" + "name='" + item + "'" + ">";
            html += "</div>";

            html += "<div class='col-sm-2'>";
            html += "<span class='input-group-btn'>" + "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='matchingMetro.prototype.cleanHTMLData(this)'>" + "</span>";
            html += "</div>";

            html += "</div>";
            html += "</div>";
            html += "</div>";
            $("#" + matchingMetro.prototype.config().frm + " .theLine").append(html);
        },
        cleanHTMLData: function (item) {
            var value = "";
            $(item).closest('.form-group').remove();
        },
        writeHTMLData: function (str) {
            $("#" + matchingMetro.prototype.config().frm + " .theLine").empty();
            var strs = str.split(",");
            var length = strs.length;
            var lableValue = "所在线路";
            var item = "theLine";
            for (var i = 0; i < length; i++) {
                console.log("i:" + i);
                var html = "<div class='row form-group' style='margin-top:8px;'>";
                html += '<div class="col-sm-12">';
                html += '<div class="form-inline x-valid">';
                html += "<label class='col-sm-2 control-label'>" + lableValue + "</label>";
                html += "<div class='col-sm-8'>";
                html += "<input type='text' required class='form-control input-full'" + "name='" + item + "' value='" + strs[i] + "'>";
                html += "</div>";

                html += "<div class='col-sm-2'>";
                html += "<span class='input-group-btn'>" + "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='matchingMetro.prototype.cleanHTMLData(this)'>" + "</span>";
                html += "</div>";

                html += "</div>";
                html += "</div>";
                html += "</div>";
                $("#" + matchingMetro.prototype.config().frm + " .theLine").append(html);
            }
        },
        init: function (item) {
            $("#" + matchingMetro.prototype.config().frm).clearAll();
            $("#" + matchingMetro.prototype.config().frm).initForm(item);
            if (matchingMetro.prototype.isNotBlank(item.theLine)) {
                matchingMetro.prototype.writeHTMLData(item.theLine);
            }
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_distance, item.distance, function (html, data) {
                $("#" + matchingMetro.prototype.config().frm).find("select.distance").empty().html(html).trigger('change');
            });
        }
    }

    //绑定事件
    $('#' + matchingMetro.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        matchingMetro.prototype.loadDataDicList();
    })
})();

var matchingTrafficHub;//交通枢纽信息
;(function () {
    matchingTrafficHub = function () {

    };
    matchingTrafficHub.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "MatchingTrafficHubList";
            data.box = "divBoxMatchingTrafficHub";
            data.frm = "frmMatchingTrafficHub";
            data.type = "trafficHub";//根据ExamineMatchingTrafficTypeEnum 配置
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.matchingTrafficHubColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="matchingTrafficHub.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="matchingTrafficHub.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + matchingTrafficHub.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingTrafficHub.prototype.config().table, getContextPath() + "/basicMatchingTraffic/getBootstrapTableVo", cols, {
                type: matchingTrafficHub.prototype.config().type,
                estateId: estateCommon.getEstateId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            },true);
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicMatchingTraffic/deleteBasicMatchingTraffic",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        matchingTrafficHub.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        clear:function () {
            var data = $("#" + matchingTrafficHub.prototype.config().table).bootstrapTable('getSelections');
            var ids = "";
            if (data.length >= 1){
                $.each(data, function (i, n) {
                    if (i == data.length - 1) {
                        ids += n.id;
                    } else {
                        ids += n.id + ",";
                    }
                });
                $.ajax({
                    url: getContextPath() + "/basicMatchingTraffic/removeIds",
                    type: "post",
                    dataType: "json",
                    data: {ids: ids},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除成功");
                            matchingTrafficHub.prototype.loadDataDicList();
                        }
                    }
                })
            }else {
                AlertError("至少选择一条数据!") ;
            }
        },
        showModel: function () {
            matchingTrafficHub.prototype.init({});
            $("#" + matchingTrafficHub.prototype.config().frm + " .type").val(matchingTrafficHub.prototype.config().type);
            $('#' + matchingTrafficHub.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + matchingTrafficHub.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(matchingTrafficHub.prototype.config().frm,true);
            data.estateId = estateCommon.getEstateId();
            $.ajax({
                url: getContextPath() + "/basicMatchingTraffic/saveAndUpdateBasicMatchingTraffic",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                       notifySuccess("成功", "保存成功");
                        $('#' + matchingTrafficHub.prototype.config().box).modal('hide');
                        matchingTrafficHub.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicMatchingTraffic/getBasicMatchingTrafficById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (matchingTrafficHub.prototype.isNotBlank(result.data)) {
                            matchingTrafficHub.prototype.init(result.data);
                        } else {
                            matchingTrafficHub.prototype.init({});
                        }
                        $('#' + matchingTrafficHub.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + matchingTrafficHub.prototype.config().frm).clearAll();
            $("#" + matchingTrafficHub.prototype.config().frm).initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_distance, item.distance, function (html, data) {
                $("#" + matchingTrafficHub.prototype.config().frm).find("select.distance").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_traffic_nature, item.nature, function (html, data) {
                $("#" + matchingTrafficHub.prototype.config().frm).find("select.nature").empty().html(html).trigger('change');
            });
        }
    }

    //绑定事件
    $('#' + matchingTrafficHub.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        matchingTrafficHub.prototype.loadDataDicList();
    })
})();


var matchingTransit;
;(function () {
    matchingTransit = function () {

    };
    matchingTransit.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "TransitList";
            data.box = "divBoxTransit";
            data.frm = "frmTransit";
            data.type = "transit";//根据ExamineMatchingTrafficTypeEnum 配置
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.matchingTransitColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="matchingTransit.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="matchingTransit.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + matchingTransit.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingTransit.prototype.config().table, getContextPath() + "/basicMatchingTraffic/getBootstrapTableVo", cols, {
                type: matchingTransit.prototype.config().type,
                estateId: estateCommon.getEstateId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            },true);
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicMatchingTraffic/deleteBasicMatchingTraffic",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        matchingTransit.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        clear: function () {
            var data = $("#" + matchingTransit.prototype.config().table).bootstrapTable('getSelections');
            var ids = "";
            if (data.length >= 1){
                $.each(data, function (i, n) {
                    if (i == data.length - 1) {
                        ids += n.id;
                    } else {
                        ids += n.id + ",";
                    }
                });
                $.ajax({
                    url: getContextPath() + "/basicMatchingTraffic/removeIds",
                    type: "post",
                    dataType: "json",
                    data: {ids: ids},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除成功");
                            matchingTransit.prototype.loadDataDicList();
                        }
                    }
                })
            }else {
                AlertError("至少选择一条数据!") ;
            }
        },
        showModel: function () {
            matchingTransit.prototype.init({});
            $("#" + matchingTransit.prototype.config().frm + " .type").val(matchingTransit.prototype.config().type);
            $("#" + matchingTransit.prototype.config().frm + " .theLine").empty();
            var lableValue = "所在线路";
            var html = "<div class='row form-group' style='margin-top:8px;'>";
            html += '<div class="col-sm-12">';
            html += '<div class="form-inline x-valid">';
            html += "<label class='col-sm-2 control-label'>" + lableValue + "</label>";
            html += "<div class='col-sm-8'>";
            html += "<input type='text' required class='form-control input-full'" + "name='" + 'theLine' + "'" + ">";
            html += "</div>";

            html += "<div class='col-sm-2'>";
            html += "<span class='input-group-btn'>" + "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='matchingTransit.prototype.cleanHTMLData(this)'>" + "</span>";
            html += "</div>";

            html += "</div>";
            html += "</div>";
            html += "</div>";
            $("#" + matchingTransit.prototype.config().frm + " .theLine").append(html);
            $('#' + matchingTransit.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + matchingTransit.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(matchingTransit.prototype.config().frm,true);
            data.estateId = estateCommon.getEstateId();
            $.ajax({
                url: getContextPath() + "/basicMatchingTraffic/saveAndUpdateBasicMatchingTraffic",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                       notifySuccess("成功", "保存成功");
                        $('#' + matchingTransit.prototype.config().box).modal('hide');
                        matchingTransit.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicMatchingTraffic/getBasicMatchingTrafficById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (matchingTransit.prototype.isNotBlank(result.data)) {
                            matchingTransit.prototype.init(result.data);
                        } else {
                            matchingTransit.prototype.init({});
                        }
                        $('#' + matchingTransit.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        appendHTML: function (item, this_) {
            var lableValue = "所在线路";
            var html = "<div class='row form-group' style='margin-top:8px;'>";
            html += '<div class="col-sm-12">';
            html += '<div class="form-inline x-valid">';
            html += "<label class='col-sm-2 control-label'>" + lableValue + "</label>";
            html += "<div class='col-sm-8'>";
            html += "<input type='text' required class='form-control input-full'" + "name='" + item + "'" + ">";
            html += "</div>";

            html += "<div class='col-sm-2'>";
            html += "<span class='input-group-btn'>" + "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='matchingTransit.prototype.cleanHTMLData(this)'>" + "</span>";
            html += "</div>";

            html += "</div>";
            html += "</div>";
            html += "</div>";
            $("#" + matchingTransit.prototype.config().frm + " .theLine").append(html);
        },
        cleanHTMLData: function (item) {
            var value = "";
            $(item).closest('.form-group').remove();
        },
        writeHTMLData: function (str) {
            $("#" + matchingTransit.prototype.config().frm + " .theLine").empty();
            var strs = str.split(",");
            var length = strs.length;
            var lableValue = "所在线路";
            var item = "theLine";
            for (var i = 0; i < length; i++) {
                console.log("i:" + i);
                var html = "<div class='row form-group' style='margin-top:8px;'>";
                html += '<div class="col-sm-12">';
                html += '<div class="form-inline x-valid">';
                html += "<label class='col-sm-2 control-label'>" + lableValue + "</label>";
                html += "<div class='col-sm-8'>";
                html += "<input type='text' required class='form-control input-full'" + "name='" + item + "' value='" + strs[i] + "'>";
                html += "</div>";

                html += "<div class='col-sm-2'>";
                html += "<span class='input-group-btn'>" + "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='matchingTransit.prototype.cleanHTMLData(this)'>" + "</span>";
                html += "</div>";

                html += "</div>";
                html += "</div>";
                html += "</div>";
                $("#" + matchingTransit.prototype.config().frm + " .theLine").append(html);
            }
        },
        init: function (item) {
            $("#" + matchingTransit.prototype.config().frm).clearAll();
            $("#" + matchingTransit.prototype.config().frm).initForm(item);
            if (matchingTransit.prototype.isNotBlank(item.theLine)) {
                matchingTransit.prototype.writeHTMLData(item.theLine);
            }
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_distance, item.distance, function (html, data) {
                $("#" + matchingTransit.prototype.config().frm).find("select.distance").empty().html(html).trigger('change');
            });
        }
    }

    //绑定事件
    $('#' + matchingTransit.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        matchingTransit.prototype.loadDataDicList();
    })
})();


var matchingMedical;//医疗
;(function () {
    matchingMedical = function () {

    };
    matchingMedical.prototype = {
        config: function () {
            var data = {};
            data.table = "MatchingMedicalList";
            data.box = "divBoxMatchingMedical";
            data.frm = "frmMatchingMedical";
            data.type = "null";//
            return data;
        },
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function () {
            var cols = commonColumn.matchingMedicalColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="matchingMedical.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="matchingMedical.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + matchingMedical.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingMedical.prototype.config().table, getContextPath() + "/basicMatchingMedical/getBootstrapTableVo", cols, {
                estateId: estateCommon.getEstateId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            },true);
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicMatchingMedical/deleteBasicMatchingMedical",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        matchingMedical.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        clear:function () {
            var data = $("#" + matchingMedical.prototype.config().table).bootstrapTable('getSelections');
            var ids = "";
            if (data.length >= 1){
                $.each(data, function (i, n) {
                    if (i == data.length - 1) {
                        ids += n.id;
                    } else {
                        ids += n.id + ",";
                    }
                });
                $.ajax({
                    url: getContextPath() + "/basicMatchingMedical/removeIds",
                    type: "post",
                    dataType: "json",
                    data: {ids: ids},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除成功");
                            matchingMedical.prototype.loadDataDicList();
                        }
                    }
                });
            }else {
                AlertError("至少选择一条数据!") ;
            }
        },
        showModel: function () {
            matchingMedical.prototype.init({});
            $('#' + matchingMedical.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + matchingMedical.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(matchingMedical.prototype.config().frm,true);
            data.estateId = estateCommon.getEstateId();
            $.ajax({
                url: getContextPath() + "/basicMatchingMedical/saveAndUpdateBasicMatchingMedical",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                       notifySuccess("成功", "保存成功");
                        $('#' + matchingMedical.prototype.config().box).modal('hide');
                        matchingMedical.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicMatchingMedical/getBasicMatchingMedicalById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (matchingMedical.prototype.isNotBlank(result.data)) {
                            matchingMedical.prototype.init(result.data);
                        } else {
                            matchingMedical.prototype.init({});
                        }
                        $('#' + matchingMedical.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + matchingMedical.prototype.config().frm).clearAll();
            $("#" + matchingMedical.prototype.config().frm).initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_examinematchingmedical_level, item.organizationLevel, function (html, data) {
                $("#" + matchingMedical.prototype.config().frm).find("select.organizationLevel").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_examinematchingmedical_bedNumber, item.bedNumber, function (html, data) {
                $("#" + matchingMedical.prototype.config().frm).find("select.bedNumber").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_examinematchingmedical_distance, item.distance, function (html, data) {
                $("#" + matchingMedical.prototype.config().frm).find("select.distance").empty().html(html).trigger('change');
            });
        }
    }

    //绑定事件
    $('#' + matchingMedical.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        matchingMedical.prototype.loadDataDicList();
    })
})();

var matchingMaterial;
;(function () {
    matchingMaterial = function () {

    };
    matchingMaterial.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "MatchingMaterialList";
            data.box = "divBoxMatchingMaterial";
            data.frm = "frmMatchingMaterial";
            data.type = "null";//
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.matchingMaterialColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="matchingMaterial.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="matchingMaterial.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + matchingMaterial.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingMaterial.prototype.config().table, getContextPath() + "/basicMatchingMaterial/getBootstrapTableVo", cols, {
                estateId: estateCommon.getEstateId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicMatchingMaterial/deleteBasicMatchingMaterial",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        matchingMaterial.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            matchingMaterial.prototype.init({});
            $('#' + matchingMaterial.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + matchingMaterial.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(matchingMaterial.prototype.config().frm,true);
            data.estateId = estateCommon.getEstateId();
            $.ajax({
                url: getContextPath() + "/basicMatchingMaterial/saveAndUpdateBasicMatchingMaterial",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                       notifySuccess("成功", "保存成功");
                        $('#' + matchingMaterial.prototype.config().box).modal('hide');
                        matchingMaterial.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicMatchingMaterial/getBasicMatchingMaterialById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (matchingMaterial.prototype.isNotBlank(result.data)) {
                            matchingMaterial.prototype.init(result.data);
                        } else {
                            matchingMaterial.prototype.init({});
                        }
                        $('#' + matchingMaterial.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + matchingMaterial.prototype.config().frm).clearAll();
            $("#" + matchingMaterial.prototype.config().frm).initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_supply_new_distance, item.distance, function (html, data) {
                $("#" + matchingMaterial.prototype.config().frm).find("select.distance").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_supply_new_scale, item.scale, function (html, data) {
                $("#" + matchingMaterial.prototype.config().frm).find("select.scale").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_supply_new_type, item.category, function (html, data) {
                $("#" + matchingMaterial.prototype.config().frm).find("select.category").empty().html(html).trigger('change');
            });
        }
    }

    //绑定事件
    $('#' + matchingMaterial.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        matchingMaterial.prototype.loadDataDicList();
    })
})();


var matchingMarket; //商场
;(function () {
    matchingMarket = function () {

    };
    matchingMarket.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "MatchingMarketList";
            data.box = "divBoxMatchingMarket";
            data.frm = "frmMatchingMarket";
            data.type = "matchingMarket";// 根据 ExamineMatchingLeisurePlaceTypeEnum 配置
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.matchingMarketColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="matchingMarket.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="matchingMarket.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + matchingMarket.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingMarket.prototype.config().table, getContextPath() + "/basicMatchingLeisurePlace/getBootstrapTableVo", cols, {
                type: matchingMarket.prototype.config().type,
                estateId: estateCommon.getEstateId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            },true);
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicMatchingLeisurePlace/deleteBasicMatchingLeisurePlace",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        matchingMarket.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        clear:function () {
            var data = $("#" + matchingMarket.prototype.config().table).bootstrapTable('getSelections');
            var ids = "";
            if (data.length >= 1){
                $.each(data, function (i, n) {
                    if (i == data.length - 1) {
                        ids += n.id;
                    } else {
                        ids += n.id + ",";
                    }
                });
                $.ajax({
                    url: getContextPath() + "/basicMatchingLeisurePlace/removeIds",
                    type: "post",
                    dataType: "json",
                    data: {ids: ids},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除成功");
                            matchingMarket.prototype.loadDataDicList();
                        }
                    }
                });
            }else {
                AlertError("至少选择一条数据!") ;
            }
        },
        showModel: function () {
            matchingMarket.prototype.init({});
            $("#" + matchingMarket.prototype.config().frm + " .type").val(matchingMarket.prototype.config().type);
            $("#" + matchingMarket.prototype.config().frm + " .name").empty();
            var lableValue = "购物商场名称";
            var html = "<div class='row form-group' style='margin-top:8px;'>";
            html += '<div class="col-sm-12">';
            html += '<div class="form-inline x-valid">';
            html += "<label class='col-sm-2 control-label'>" + lableValue + "</label>";
            html += "<div class='col-sm-8'>";
            html += "<input type='text' required class='form-control input-full'" + "name='" + 'name' + "'" + ">";
            html += "</div>";

            html += "<div class='col-sm-2'>";
            html += "<span class='input-group-btn'>" + "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='matchingRestaurant.prototype.cleanHTMLData(this)'>" + "</span>";
            html += "</div>";

            html += "</div>";
            html += "</div>";
            html += "</div>";
            $("#" + matchingMarket.prototype.config().frm + " .name").append(html);
            $('#' + matchingMarket.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + matchingMarket.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(matchingMarket.prototype.config().frm,true);
            data.estateId = estateCommon.getEstateId();
            data.type = matchingMarket.prototype.config().type;
            $.ajax({
                url: getContextPath() + "/basicMatchingLeisurePlace/saveAndUpdateBasicMatchingLeisurePlace",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                       notifySuccess("成功", "保存成功");
                        $('#' + matchingMarket.prototype.config().box).modal('hide');
                        matchingMarket.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicMatchingLeisurePlace/getBasicMatchingLeisurePlaceById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (matchingMarket.prototype.isNotBlank(result.data)) {
                            matchingMarket.prototype.init(result.data);
                        } else {
                            matchingMarket.prototype.init({});
                        }
                        $('#' + matchingMarket.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + matchingMarket.prototype.config().frm).clearAll();
            $("#" + matchingMarket.prototype.config().frm).initForm(item);
            if (matchingMarket.prototype.isNotBlank(item.name)) {
                matchingMarket.prototype.writeHTMLData(item.name);
            }
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_shop_category, item.category, function (html, data) {
                $("#" + matchingMarket.prototype.config().frm).find("select.category").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_shop_grade, item.grade, function (html, data) {
                $("#" + matchingMarket.prototype.config().frm).find("select.grade").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_shop_or_entertainment_or_dining_distance, item.distance, function (html, data) {
                $("#" + matchingMarket.prototype.config().frm).find("select.distance").empty().html(html).trigger('change');
            });
        },
        appendHTML: function (item, this_) {
            var lableValue = "购物商场名称";
            var html = "<div class='row form-group' style='margin-top:8px;'>";
            html += '<div class="col-sm-12">';
            html += '<div class="form-inline x-valid">';
            html += "<label class='col-sm-2 control-label'>" + lableValue + "</label>";
            html += "<div class='col-sm-8'>";
            html += "<input type='text' required class='form-control input-full'" + "name='" + item + "'" + ">";
            html += "</div>";

            html += "<div class='col-sm-2'>";
            html += "<span class='input-group-btn'>" + "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='matchingRestaurant.prototype.cleanHTMLData(this)'>" + "</span>";
            html += "</div>";

            html += "</div>";
            html += "</div>";
            html += "</div>";
            $("#" + matchingMarket.prototype.config().frm + " .name").append(html);
        },
        cleanHTMLData: function (item) {
            var value = "";
            $(item).closest('.form-group').remove();
        },
        writeHTMLData: function (str) {
            $("#" + matchingMarket.prototype.config().frm + " .name").empty();
            var strs = str.split(",");
            var length = strs.length;
            var lableValue = "购物商场名称";
            var item = "name";
            for (var i = 0; i < length; i++) {
                console.log("i:" + i);
                var html = "<div class='row form-group' style='margin-top:8px;'>";
                html += '<div class="col-sm-12">';
                html += '<div class="form-inline x-valid">';
                html += "<label class='col-sm-2 control-label'>" + lableValue + "</label>";
                html += "<div class='col-sm-8'>";
                html += "<input type='text' required class='form-control input-full'" + "name='" + item + "' value='" + strs[i] + "'>";
                html += "</div>";

                html += "<div class='col-sm-2'>";
                html += "<span class='input-group-btn'>" + "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='matchingRestaurant.prototype.cleanHTMLData(this)'>" + "</span>";
                html += "</div>";

                html += "</div>";
                html += "</div>";
                html += "</div>";
                $("#" + matchingMarket.prototype.config().frm + " .name").append(html);
            }
        }
    }

    //绑定事件
    $('#' + matchingMarket.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        matchingMarket.prototype.loadDataDicList();
        //用做高德地图抓取数据type
        AssessCommon.loadDataDicByKey(AssessDicKey.estate_shop_category, null, function (html, data) {
            $("#" + matchingMarket.prototype.config().table).closest("form").find('select.category').empty().html(html).trigger('change');
        });
    })
})();

var matchingRestaurant;//餐饮
;(function () {
    matchingRestaurant = function () {

    };
    matchingRestaurant.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "MatchingRestaurantList";
            data.box = "divBoxMatchingRestaurant";
            data.frm = "frmMatchingRestaurant";
            data.type = "matchingRestaurant";// 根据 ExamineMatchingLeisurePlaceTypeEnum 配置
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.matchingRestaurantColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="matchingRestaurant.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="matchingRestaurant.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + matchingRestaurant.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingRestaurant.prototype.config().table, getContextPath() + "/basicMatchingLeisurePlace/getBootstrapTableVo", cols, {
                type: matchingRestaurant.prototype.config().type,
                estateId: estateCommon.getEstateId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            },true);
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicMatchingLeisurePlace/deleteBasicMatchingLeisurePlace",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        matchingRestaurant.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        clear:function () {
            var data = $("#" + matchingRestaurant.prototype.config().table).bootstrapTable('getSelections');
            var ids = "";
            if (data.length >= 1){
                $.each(data, function (i, n) {
                    if (i == data.length - 1) {
                        ids += n.id;
                    } else {
                        ids += n.id + ",";
                    }
                });
                $.ajax({
                    url: getContextPath() + "/basicMatchingLeisurePlace/removeIds",
                    type: "post",
                    dataType: "json",
                    data: {ids: ids},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除成功");
                            matchingRestaurant.prototype.loadDataDicList();
                        }
                    }
                });
            }else {
                AlertError("至少选择一条数据!") ;
            }
        },
        showModel: function () {
            matchingRestaurant.prototype.init({});
            $("#" + matchingRestaurant.prototype.config().frm + " .type").val(matchingRestaurant.prototype.config().type);
            $("#" + matchingRestaurant.prototype.config().frm + " .name").empty();
            var lableValue = "餐饮名称";
            var html = "<div class='row form-group' style='margin-top:8px;'>";
            html += '<div class="col-sm-12">';
            html += '<div class="form-inline x-valid">';
            html += "<label class='col-sm-2 control-label'>" + lableValue + "</label>";
            html += "<div class='col-sm-8'>";
            html += "<input type='text' required class='form-control input-full'" + "name='" + 'name' + "'" + ">";
            html += "</div>";

            html += "<div class='col-sm-2'>";
            html += "<span class='input-group-btn'>" + "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='matchingRestaurant.prototype.cleanHTMLData(this)'>" + "</span>";
            html += "</div>";

            html += "</div>";
            html += "</div>";
            html += "</div>";
            $("#" + matchingRestaurant.prototype.config().frm + " .name").append(html);
            $('#' + matchingRestaurant.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + matchingRestaurant.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(matchingRestaurant.prototype.config().frm,true);
            data.estateId = estateCommon.getEstateId();
            data.type = matchingRestaurant.prototype.config().type;
            $.ajax({
                url: getContextPath() + "/basicMatchingLeisurePlace/saveAndUpdateBasicMatchingLeisurePlace",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                       notifySuccess("成功", "保存成功");
                        $('#' + matchingRestaurant.prototype.config().box).modal('hide');
                        matchingRestaurant.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicMatchingLeisurePlace/getBasicMatchingLeisurePlaceById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (matchingRestaurant.prototype.isNotBlank(result.data)) {
                            matchingRestaurant.prototype.init(result.data);
                        } else {
                            matchingRestaurant.prototype.init({});
                        }
                        $('#' + matchingRestaurant.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + matchingRestaurant.prototype.config().frm).clearAll();
            $("#" + matchingRestaurant.prototype.config().frm).initForm(item);
            if (matchingRestaurant.prototype.isNotBlank(item.name)) {
                matchingRestaurant.prototype.writeHTMLData(item.name);
            }
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_dining_category, item.category, function (html, data) {
                $("#" + matchingRestaurant.prototype.config().frm).find("select.category").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_dining_grade, item.grade, function (html, data) {
                $("#" + matchingRestaurant.prototype.config().frm).find("select.grade").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_shop_or_entertainment_or_dining_distance, item.distance, function (html, data) {
                $("#" + matchingRestaurant.prototype.config().frm).find("select.distance").empty().html(html).trigger('change');
            });
        },
        appendHTML: function (item, this_) {
            var lableValue = "餐饮名称";
            var html = "<div class='row form-group' style='margin-top:8px;'>";
            html += '<div class="col-sm-12">';
            html += '<div class="form-inline x-valid">';
            html += "<label class='col-sm-2 control-label'>" + lableValue + "</label>";
            html += "<div class='col-sm-8'>";
            html += "<input type='text' required class='form-control input-full'" + "name='" + item + "'" + ">";
            html += "</div>";

            html += "<div class='col-sm-2'>";
            html += "<span class='input-group-btn'>" + "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='matchingRestaurant.prototype.cleanHTMLData(this)'>" + "</span>";
            html += "</div>";

            html += "</div>";
            html += "</div>";
            html += "</div>";
            $("#" + matchingRestaurant.prototype.config().frm + " .name").append(html);
        },
        cleanHTMLData: function (item) {
            var value = "";
            $(item).closest('.form-group').remove();
        },
        writeHTMLData: function (str) {
            $("#" + matchingRestaurant.prototype.config().frm + " .name").empty();
            var strs = str.split(",");
            var length = strs.length;
            var lableValue = "餐饮名称";
            var item = "name";
            for (var i = 0; i < length; i++) {
                console.log("i:" + i);
                var html = "<div class='row form-group' style='margin-top:8px;'>";
                html += '<div class="col-sm-12">';
                html += '<div class="form-inline x-valid">';
                html += "<label class='col-sm-2 control-label'>" + lableValue + "</label>";
                html += "<div class='col-sm-8'>";
                html += "<input type='text' required class='form-control input-full'" + "name='" + item + "' value='" + strs[i] + "'>";
                html += "</div>";

                html += "<div class='col-sm-2'>";
                html += "<span class='input-group-btn'>" + "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='matchingRestaurant.prototype.cleanHTMLData(this)'>" + "</span>";
                html += "</div>";

                html += "</div>";
                html += "</div>";
                html += "</div>";
                $("#" + matchingRestaurant.prototype.config().frm + " .name").append(html);
            }
        }
    }

    //绑定事件
    $('#' + matchingRestaurant.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        matchingRestaurant.prototype.loadDataDicList();
        //用做高德地图抓取数据type
        AssessCommon.loadDataDicByKey(AssessDicKey.estate_dining_category, null, function (html, data) {
            $("#" + matchingRestaurant.prototype.config().table).closest("form").find('select.category').empty().html(html).trigger('change');
        });
    })
})();

var matchingRecreation;//娱乐
;(function () {
    matchingRecreation = function () {

    };
    matchingRecreation.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "MatchingRecreationList";
            data.box = "divBoxMatchingRecreation";
            data.frm = "frmMatchingRecreation";
            data.type = "matchingRecreation";// 根据 ExamineMatchingLeisurePlaceTypeEnum 配置
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.matchingRecreationColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="matchingRecreation.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="matchingRecreation.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + matchingRecreation.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingRecreation.prototype.config().table, getContextPath() + "/basicMatchingLeisurePlace/getBootstrapTableVo", cols, {
                type: matchingRecreation.prototype.config().type,
                estateId: estateCommon.getEstateId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            },true);
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicMatchingLeisurePlace/deleteBasicMatchingLeisurePlace",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        matchingRecreation.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        clear:function () {
            var data = $("#" + matchingRecreation.prototype.config().table).bootstrapTable('getSelections');
            var ids = "";
            if (data.length >= 1){
                $.each(data, function (i, n) {
                    if (i == data.length - 1) {
                        ids += n.id;
                    } else {
                        ids += n.id + ",";
                    }
                });
                $.ajax({
                    url: getContextPath() + "/basicMatchingLeisurePlace/removeIds",
                    type: "post",
                    dataType: "json",
                    data: {ids: ids},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除成功");
                            matchingRecreation.prototype.loadDataDicList();
                        }
                    }
                });
            }else {
                AlertError("至少选择一条数据!") ;
            }
        },
        showModel: function () {
            matchingRecreation.prototype.init({});
            $("#" + matchingRecreation.prototype.config().frm + " .type").val(matchingRecreation.prototype.config().type);
            $("#" + matchingRecreation.prototype.config().frm + " .name").empty();
            var lableValue = "休闲娱乐名称";
            var html = "<div class='row form-group' style='margin-top:8px;'>";
            html += '<div class="col-sm-12">';
            html += '<div class="form-inline x-valid">'
            ;
            html += "<label class='col-sm-2 control-label'>" + lableValue + "</label>";
            html += "<div class='col-sm-8'>";
            html += "<input type='text' required class='form-control input-full'" + "name='" + 'name' + "'" + ">";
            html += "</div>";

            html += "<div class='col-sm-2'>";
            html += "<span class='input-group-btn'>" + "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='matchingRecreation.prototype.cleanHTMLData(this)'>" + "</span>";
            html += "</div>";

            html += "</div>";
            html += "</div>";
            html += "</div>";
            $("#" + matchingRecreation.prototype.config().frm + " .name").append(html);
            $('#' + matchingRecreation.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + matchingRecreation.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(matchingRecreation.prototype.config().frm,true);
            data.estateId = estateCommon.getEstateId();
            data.type = matchingRecreation.prototype.config().type;
            $.ajax({
                url: getContextPath() + "/basicMatchingLeisurePlace/saveAndUpdateBasicMatchingLeisurePlace",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                       notifySuccess("成功", "保存成功");
                        $('#' + matchingRecreation.prototype.config().box).modal('hide');
                        matchingRecreation.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicMatchingLeisurePlace/getBasicMatchingLeisurePlaceById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (matchingRecreation.prototype.isNotBlank(result.data)) {
                            matchingRecreation.prototype.init(result.data);
                        } else {
                            matchingRecreation.prototype.init({});
                        }
                        $('#' + matchingRecreation.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + matchingRecreation.prototype.config().frm).clearAll();
            $("#" + matchingRecreation.prototype.config().frm).initForm(item);
            if (matchingRecreation.prototype.isNotBlank(item.name)) {
                matchingRecreation.prototype.writeHTMLData(item.name);
            }
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_entertainment_category, item.category, function (html, data) {
                $("#" + matchingRecreation.prototype.config().frm).find("select.category").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_shop_or_entertainment_or_dining_distance, item.distance, function (html, data) {
                $("#" + matchingRecreation.prototype.config().frm).find("select.distance").empty().html(html).trigger('change');
            });
        },
        appendHTML: function (item, this_) {
            var lableValue = "休闲娱乐名称";
            var html = "<div class='row form-group' style='margin-top:8px;'>";
            html += '<div class="col-sm-12">';
            html += '<div class="form-inline x-valid">'
            ;
            html += "<label class='col-sm-2 control-label'>" + lableValue + "</label>";
            html += "<div class='col-sm-8'>";
            html += "<input type='text' required class='form-control input-full'" + "name='" + item + "'" + ">";
            html += "</div>";

            html += "<div class='col-sm-2'>";
            html += "<span class='input-group-btn'>" + "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='matchingRecreation.prototype.cleanHTMLData(this)'>" + "</span>";
            html += "</div>";

            html += "</div>";
            html += "</div>";
            html += "</div>";
            $("#" + matchingRecreation.prototype.config().frm + " .name").append(html);
        },
        cleanHTMLData: function (item) {
            var value = "";
            $(item).closest('.form-group').remove();
        },
        writeHTMLData: function (str) {
            $("#" + matchingRecreation.prototype.config().frm + " .name").empty();
            var strs = str.split(",");
            var length = strs.length;
            var lableValue = "休闲娱乐名称";
            var item = "name";
            for (var i = 0; i < length; i++) {
                console.log("i:" + i);
                var html = "<div class='row form-group' style='margin-top:8px;'>";
                html += '<div class="col-sm-12">';
                html += '<div class="form-inline x-valid">';
                html += "<label class='col-sm-2 control-label'>" + lableValue + "</label>";
                html += "<div class='col-sm-8'>";
                html += "<input type='text' required class='form-control input-full'" + "name='" + item + "' value='" + strs[i] + "'>";
                html += "</div>";

                html += "<div class='col-sm-2'>";
                html += "<span class='input-group-btn'>" + "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='matchingRecreation.prototype.cleanHTMLData(this)'>" + "</span>";
                html += "</div>";

                html += "</div>";
                html += "</div>";
                html += "</div>";
                $("#" + matchingRecreation.prototype.config().frm + " .name").append(html);
            }
        }
    }

    //绑定事件
    $('#' + matchingRecreation.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        matchingRecreation.prototype.loadDataDicList();
        //用做高德地图抓取数据type
        AssessCommon.loadDataDicByKey(AssessDicKey.estate_entertainment_category, null, function (html, data) {
            $("#" + matchingRecreation.prototype.config().table).closest("form").find('select.category').empty().html(html).trigger('change');
        });
    })
})();


var matchingFinance;
;(function () {
    matchingFinance = function () {

    };
    matchingFinance.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "MatchingFinanceList";
            data.box = "divBoxMatchingFinance";
            data.frm = "frmMatchingFinance";
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.matchingFinanceColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="matchingFinance.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="matchingFinance.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + matchingFinance.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingFinance.prototype.config().table, getContextPath() + "/basicMatchingFinance/getBootstrapTableVo", cols, {
                estateId: estateCommon.getEstateId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            },true);
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicMatchingFinance/deleteBasicMatchingFinance",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        matchingFinance.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        clear:function () {
            var data = $("#" + matchingFinance.prototype.config().table).bootstrapTable('getSelections');
            var ids = "";
            if (data.length >= 1){
                $.each(data, function (i, n) {
                    if (i == data.length - 1) {
                        ids += n.id;
                    } else {
                        ids += n.id + ",";
                    }
                });
                $.ajax({
                    url: getContextPath() + "/basicMatchingFinance/removeIds",
                    type: "post",
                    dataType: "json",
                    data: {ids: ids},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除成功");
                            matchingFinance.prototype.loadDataDicList();
                        }
                    }
                })
            }else {
                AlertError("至少选择一条数据!") ;
            }
        },
        showModel: function () {
            matchingFinance.prototype.init({});
            $('#' + matchingFinance.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + matchingFinance.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(matchingFinance.prototype.config().frm,true);
            data.estateId = estateCommon.getEstateId();
            $.ajax({
                url: getContextPath() + "/basicMatchingFinance/saveAndUpdateBasicMatchingFinance",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                       notifySuccess("成功", "保存成功");
                        $('#' + matchingFinance.prototype.config().box).modal('hide');
                        matchingFinance.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicMatchingFinance/getBasicMatchingFinanceById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (matchingFinance.prototype.isNotBlank(result.data)) {
                            matchingFinance.prototype.init(result.data);
                        } else {
                            matchingFinance.prototype.init({});
                        }
                        $('#' + matchingFinance.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + matchingFinance.prototype.config().frm).clearAll();
            $("#" + matchingFinance.prototype.config().frm).initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_finance_service_content, item.serviceContent, function (html, data) {
                $("#" + matchingFinance.prototype.config().frm).find("select.serviceContent").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_finance_nature, item.nature, function (html, data) {
                $("#" + matchingFinance.prototype.config().frm).find("select.nature").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_finance_category, item.category, function (html, data) {
                $("#" + matchingFinance.prototype.config().frm).find("select.category").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_distance, item.distance, function (html, data) {
                $("#" + matchingFinance.prototype.config().frm).find("select.distance").empty().html(html).trigger('change');
            });
        }
    };

    //绑定事件
    $('#' + matchingFinance.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        //用做高德地图抓取数据type
        AssessCommon.loadDataDicByKey(AssessDicKey.estate_finance_category, null, function (html, data) {
            $("#" + matchingFinance.prototype.config().table).closest("form").find('select.category').empty().html(html).trigger('change');
        });
        matchingFinance.prototype.loadDataDicList();
    })
})();

var matchingEnvironment;
;(function () {
    matchingEnvironment = function () {

    };
    matchingEnvironment.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "MatchingEnvironmentList";
            data.box = "divBoxMatchingEnvironment";
            data.frm = "frmMatchingEnvironment";
            data.type = "null";//
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.matchingEnvironmentColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="matchingEnvironment.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="matchingEnvironment.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + matchingEnvironment.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingEnvironment.prototype.config().table, getContextPath() + "/basicMatchingEnvironment/getBootstrapTableVo", cols, {
                estateId: estateCommon.getEstateId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicMatchingEnvironment/deleteBasicMatchingEnvironment",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        matchingEnvironment.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            matchingEnvironment.prototype.init({});
            $('#' + matchingEnvironment.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + matchingEnvironment.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(matchingEnvironment.prototype.config().frm,true);
            data.estateId = estateCommon.getEstateId();
            $.ajax({
                url: getContextPath() + "/basicMatchingEnvironment/saveAndUpdateBasicMatchingEnvironment",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                       notifySuccess("成功", "保存成功");
                        $('#' + matchingEnvironment.prototype.config().box).modal('hide');
                        matchingEnvironment.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicMatchingEnvironment/getBasicMatchingEnvironmentById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (matchingEnvironment.prototype.isNotBlank(result.data)) {
                            matchingEnvironment.prototype.init(result.data);
                        } else {
                            matchingEnvironment.prototype.init({});
                        }
                        $('#' + matchingEnvironment.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + matchingEnvironment.prototype.config().frm).find("select.category").empty();//清空影响因素
            $("#" + matchingEnvironment.prototype.config().frm).clearAll().initForm(item);
            $("#" + matchingEnvironment.prototype.config().frm).find("select.type").off('change').on('change', function () {
                AssessCommon.loadDataDicByPid($(this).val(), item.category, function (html, data) {
                    $("#" + matchingEnvironment.prototype.config().frm).find("select.category").empty().html(html);
                });
                item.category = null;
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_environment_type, item.type, function (html, data) {
                $("#" + matchingEnvironment.prototype.config().frm).find("select.type").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_environment_influence_degree, item.influenceDegree, function (html, data) {
                $("#" + matchingEnvironment.prototype.config().frm).find("select.influenceDegree").empty().html(html);
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_environment_result, item.humanImpact, function (html, data) {
                $("#" + matchingEnvironment.prototype.config().frm).find("select.humanImpact").empty().html(html);
            });
        },
        initRemarkInfo:function () {
            var frm = $("#" + matchingEnvironment.prototype.config().frm) ;
            var category = frm.find("select[name='category']").val();
            var influenceDegree = frm.find("select[name='influenceDegree']").val();
            if (category){
                if (influenceDegree){
                    AssessCommon.getDataDicInfo(category,function (item) {
                        AssessCommon.getDataDicInfo(influenceDegree,function (n) {
                            if (item.remark){
                                var data = JSON.parse(item.remark);
                                if (n.name == "无影响"){
                                    frm.find("textarea").each(function () {
                                        if ($(this).attr("name") == "remark"){
                                            $(this).val(data.first);
                                        }
                                    });
                                }
                                if (n.name == "影响一般"){
                                    frm.find("textarea").each(function () {
                                        if ($(this).attr("name") == "remark"){
                                            $(this).val(data.second);
                                        }
                                    });
                                }
                                if (n.name == "影响较大"){
                                    //third == >
                                    frm.find("textarea").each(function () {
                                        if ($(this).attr("name") == "remark"){
                                            $(this).val(data.thild);
                                        }
                                    });
                                }
                                if (n.name == "有重大影响"){
                                    frm.find("textarea").each(function () {
                                        if ($(this).attr("name") == "remark"){
                                            $(this).val(data.fourth);
                                        }
                                    });
                                }
                            }
                        });
                    });
                }
            }
        }
    }

    //绑定事件
    $('#' + matchingEnvironment.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {

        matchingEnvironment.prototype.loadDataDicList();
    })
})();

var matchingEducation;
;(function () {
    matchingEducation = function () {

    };
    matchingEducation.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "MatchingEducationList";
            data.box = "divBoxMatchingEducation";
            data.frm = "frmMatchingEducation";
            data.type = "null";//
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.matchingEducationColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="matchingEducation.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="matchingEducation.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + matchingEducation.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingEducation.prototype.config().table, getContextPath() + "/basicMatchingEducation/getBootstrapTableVo", cols, {
                estateId: estateCommon.getEstateId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            },true);
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicMatchingEducation/deleteBasicMatchingEducation",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        matchingEducation.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        clear:function () {
            var data = $("#" + matchingEducation.prototype.config().table).bootstrapTable('getSelections');
            var ids = "";
            if (data.length >= 1){
                $.each(data, function (i, n) {
                    if (i == data.length - 1) {
                        ids += n.id;
                    } else {
                        ids += n.id + ",";
                    }
                });
                $.ajax({
                    url: getContextPath() + "/basicMatchingEducation/removeIds",
                    type: "post",
                    dataType: "json",
                    data: {ids: ids},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除成功");
                            matchingEducation.prototype.loadDataDicList();
                        }
                    }
                });
            }else {
                AlertError("至少选择一条数据!") ;
            }
        },
        showModel: function () {
            matchingEducation.prototype.init({});
            $('#' + matchingEducation.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + matchingEducation.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(matchingEducation.prototype.config().frm,true);
            data.estateId = estateCommon.getEstateId();
            $.ajax({
                url: getContextPath() + "/basicMatchingEducation/saveAndUpdateBasicMatchingEducation",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                       notifySuccess("成功", "保存成功");
                        $('#' + matchingEducation.prototype.config().box).modal('hide');
                        matchingEducation.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicMatchingEducation/getBasicMatchingEducationById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (matchingEducation.prototype.isNotBlank(result.data)) {
                            matchingEducation.prototype.init(result.data);
                        } else {
                            matchingEducation.prototype.init({});
                        }
                        $('#' + matchingEducation.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + matchingEducation.prototype.config().frm).clearAll();
            $("#" + matchingEducation.prototype.config().frm).initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_school_nature, item.schoolNature, function (html, data) {
                $("#" + matchingEducation.prototype.config().frm).find("select.schoolNature").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_school_gradation, item.schoolGradation, function (html, data) {
                $("#" + matchingEducation.prototype.config().frm).find("select.schoolGradation").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_school_level, item.schoolLevel, function (html, data) {
                $("#" + matchingEducation.prototype.config().frm).find("select.schoolLevel").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_distance, item.distance, function (html, data) {
                $("#" + matchingEducation.prototype.config().frm).find("select.distance").empty().html(html).trigger('change');
            });
        }
    };

    //绑定事件
    $('#' + matchingEducation.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        matchingEducation.prototype.loadDataDicList();
        //用做高德地图抓取数据type
        AssessCommon.loadDataDicByKey(AssessDicKey.estate_school_gradation, null, function (html, data) {
            $("#" + matchingEducation.prototype.config().table).closest("form").find('select.schoolGradation').empty().html(html).trigger('change');
        });
    })
})();

var estateNetwork;
;(function () {
    estateNetwork = function () {

    };
    estateNetwork.prototype = {
        config: function () {
            var data = {};
            data.table = "examineEstateNetworkList";
            data.box = "divBoxExamineEstateNetwork";
            data.frm = "frmExamineEstateNetwork";
            return data;
        },
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function () {
            var cols = commonColumn.estateNetworkColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="estateNetwork.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="estateNetwork.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + estateNetwork.prototype.config().table).bootstrapTable('destroy');
            TableInit(estateNetwork.prototype.config().table, getContextPath() + "/basicEstateNetwork/getBootstrapTableVo", cols, {
                estateId: estateCommon.getEstateId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicEstateNetwork/deleteBasicEstateNetwork",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除成功");
                        estateNetwork.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            estateNetwork.prototype.init({});
            $('#' + estateNetwork.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + estateNetwork.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(estateNetwork.prototype.config().frm,true);
            data.estateId = estateCommon.getEstateId();
            $.ajax({
                url: getContextPath() + "/basicEstateNetwork/saveAndUpdateBasicEstateNetwork",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                       notifySuccess("成功", "保存成功");
                        $('#' + estateNetwork.prototype.config().box).modal('hide');
                        estateNetwork.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicEstateNetwork/getBasicEstateNetworkById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        estateNetwork.prototype.init(result.data);
                        $('#' + estateNetwork.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + estateNetwork.prototype.config().frm).clearAll();
            $("#" + estateNetwork.prototype.config().frm).initForm(item,function () {
                AssessCommon.loadDataDicByKey(AssessDicKey.estate_network_supplier, item.supplier, function (html, data) {
                    $("#" + estateNetwork.prototype.config().frm).find("select.supplier").empty().html(html).trigger('change');
                });

                AssessCommon.loadDataDicByKey(AssessDicKey.estate_network_service_content, item.serviceContent, function (html, data) {
                    $("#" + estateNetwork.prototype.config().frm).find("select.serviceContent").empty().html(html).trigger('change');
                });
            });
        }
    }

    //绑定事件
    $('#' + estateNetwork.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        estateNetwork.prototype.loadDataDicList();
    })
})();

var estateParking;
;(function () {
    estateParking = function () {

    };
    estateParking.prototype = {
        config: function () {
            var data = {};
            data.table = "estateParkingList";
            data.box = "divBoxEstateParking";
            data.frm = "frmEstateParking";
            data.fileIDName = "house_estateParking";
            return data;
        },
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function () {
            var cols = commonColumn.estateParkingColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="estateParking.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="estateParking.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + estateParking.prototype.config().table).bootstrapTable('destroy');
            TableInit(estateParking.prototype.config().table, getContextPath() + "/basicEstateParking/getBootstrapTableVo", cols, {
                estateId: estateCommon.getEstateId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicEstateParking/deleteBasicEstateParking",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                       notifySuccess("成功", "删除成功");
                        estateParking.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            $('#' + estateParking.prototype.config().box).modal("show");
            estateParking.prototype.init({});
        },
        saveData: function () {
            if (!$("#" + estateParking.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(estateParking.prototype.config().frm,true);
            data.estateId = estateCommon.getEstateId();
            $.ajax({
                url: getContextPath() + "/basicEstateParking/saveAndUpdateBasicEstateParking",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                       notifySuccess("成功", "保存成功");
                        $('#' + estateParking.prototype.config().box).modal('hide');
                        estateParking.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicEstateParking/getBasicEstateParkingById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (estateParking.prototype.isNotBlank(result.data)) {
                            estateParking.prototype.init(result.data);
                        } else {
                            estateParking.prototype.init({});
                        }
                        $('#' + estateParking.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + estateParking.prototype.config().frm).clearAll();
            $("#" + estateParking.prototype.config().frm).initForm(item);

            AssessCommon.loadDataDicByKey(AssessDicKey.estate_car_location, item.location, function (html, data) {
                $("#" + estateParking.prototype.config().frm).find("select.location").empty().html(html).trigger('change');
            });

            AssessCommon.loadDataDicByKey(AssessDicKey.estate_car_type, item.parkingType, function (html, data) {
                $("#" + estateParking.prototype.config().frm).find("select.parkingType").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_car_parkingEstate, item.parkingEstate, function (html, data) {
                $("#" + estateParking.prototype.config().frm).find("select.parkingEstate").empty().html(html).trigger('change');
            });

            FileUtils.uploadFiles({
                target: estateParking.prototype.config().fileIDName,
                disabledTarget: "btn_submit",
                formData: {
                    fieldsName: estateParking.prototype.config().fileIDName,
                    tableName: AssessDBKey.BasicEstateParking,
                    tableId: estateParking.prototype.isNotBlank(item.id) ? item.id : "0"
                },
                deleteFlag: true
            });

            FileUtils.getFileShows({
                target: estateParking.prototype.config().fileIDName,
                formData: {
                    fieldsName: estateParking.prototype.config().fileIDName,
                    tableName: AssessDBKey.BasicEstateParking,
                    tableId: estateParking.prototype.isNotBlank(item.id) ? item.id : "0"
                },
                deleteFlag: true
            })
        }
    }

    //绑定事件
    $('#' + estateParking.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        estateParking.prototype.loadDataDicList();
    })
})();


var estateSupplyWater;
;(function () {
    estateSupplyWater = function () {

    };
    estateSupplyWater.prototype = {
        config: function () {
            var data = {};
            data.table = "EstateSupplyWaterList";
            data.box = "divBoxEstateSupplyWater";
            data.frm = "frmEstateSupplyWater";
            data.type = "estateSupplyWater";//根据 ExamineEstateSupplyEnumType 配置
            return data;
        },
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function () {
            var cols = commonColumn.estateSupplyWaterColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="estateSupplyWater.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="estateSupplyWater.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            var estateId = estateCommon.getEstateId();
            $("#" + estateSupplyWater.prototype.config().table).bootstrapTable('destroy');
            TableInit(estateSupplyWater.prototype.config().table, getContextPath() + "/basicEstateSupply/getBootstrapTableVo", cols, {
                type: estateSupplyWater.prototype.config().type,
                estateId: estateId
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicEstateSupply/deleteBasicEstateSupply",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                       notifySuccess("成功", "删除成功");
                        estateSupplyWater.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            estateSupplyWater.prototype.init({});
            $("#" + estateSupplyWater.prototype.config().frm).find(".type").val(estateSupplyWater.prototype.config().type);
            $('#' + estateSupplyWater.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + estateSupplyWater.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(estateSupplyWater.prototype.config().frm,true);
            data.estateId = estateCommon.getEstateId();
            $.ajax({
                url: getContextPath() + "/basicEstateSupply/saveAndUpdateBasicEstateSupply",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                       notifySuccess("成功", "保存成功");
                        $('#' + estateSupplyWater.prototype.config().box).modal('hide');
                        estateSupplyWater.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicEstateSupply/getBasicEstateSupplyById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (estateSupplyWater.prototype.isNotBlank(result.data)) {
                            estateSupplyWater.prototype.init(result.data);
                        } else {
                            estateSupplyWater.prototype.init({});
                        }
                        $('#' + estateSupplyWater.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + estateSupplyWater.prototype.config().frm).clearAll();
            $("#" + estateSupplyWater.prototype.config().frm).initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_line_water_supply_pipe_grade, item.lineGrade, function (html, data) {
                $("#" + estateSupplyWater.prototype.config().frm).find("select.lineGrade").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.data_company_reputation, item.reputation, function (html, data) {
                $("#" + estateSupplyWater.prototype.config().frm).find("select.reputation").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_supplier_grade, item.grade, function (html, data) {
                $("#" + estateSupplyWater.prototype.config().frm).find("select.grade").empty().html(html).trigger('change');
            });
        }
    }

    //绑定事件
    $('#' + estateSupplyWater.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        estateSupplyWater.prototype.loadDataDicList();
    })
})();


var estateDrainWater;
;(function () {
    estateDrainWater = function () {
    };
    estateDrainWater.prototype = {
        config: function () {
            var data = {};
            data.table = "EstateDrainWaterList";
            data.box = "divBoxEstateDrainWater";
            data.frm = "frmEstateDrainWater";
            data.type = "estateDrainWater";
            return data;
        },
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function () {
            var cols = commonColumn.estateDrainWaterColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="estateDrainWater.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="estateDrainWater.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            var estateId = estateCommon.getEstateId();
            $("#" + estateDrainWater.prototype.config().table).bootstrapTable('destroy');
            TableInit(estateDrainWater.prototype.config().table, getContextPath() + "/basicEstateSupply/getBootstrapTableVo", cols, {
                type: estateDrainWater.prototype.config().type,
                estateId: estateId
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicEstateSupply/deleteBasicEstateSupply",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                       notifySuccess("成功", "删除成功");
                        estateDrainWater.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            estateDrainWater.prototype.init({});
            $("#" + estateDrainWater.prototype.config().frm).find(".type").val(estateDrainWater.prototype.config().type);
            $('#' + estateDrainWater.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + estateDrainWater.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(estateDrainWater.prototype.config().frm,true);
            data.estateId = estateCommon.getEstateId();
            $.ajax({
                url: getContextPath() + "/basicEstateSupply/saveAndUpdateBasicEstateSupply",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                       notifySuccess("成功", "保存成功");
                        $('#' + estateDrainWater.prototype.config().box).modal('hide');
                        estateDrainWater.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicEstateSupply/getBasicEstateSupplyById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (estateDrainWater.prototype.isNotBlank(result.data)) {
                            estateDrainWater.prototype.init(result.data);
                        } else {
                            estateDrainWater.prototype.init({});
                        }
                        $('#' + estateDrainWater.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + estateDrainWater.prototype.config().frm).clearAll();
            $("#" + estateDrainWater.prototype.config().frm).initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_line_water_supply_pipe_grade, item.lineGrade, function (html, data) {
                $("#" + estateDrainWater.prototype.config().frm).find("select.lineGrade").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.data_company_reputation, item.reputation, function (html, data) {
                $("#" + estateDrainWater.prototype.config().frm).find("select.reputation").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_supplier_grade, item.grade, function (html, data) {
                $("#" + estateDrainWater.prototype.config().frm).find("select.grade").empty().html(html).trigger('change');
            });
        }
    }

    //绑定事件
    $('#' + estateDrainWater.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        estateDrainWater.prototype.loadDataDicList();
    })
})();

var estateSupplyPower;
;(function () {
    estateSupplyPower = function () {

    };
    estateSupplyPower.prototype = {
        config: function () {
            var data = {};
            data.table = "EstateSupplyPowerList";
            data.box = "divBoxEstateSupplyPower";
            data.frm = "frmEstateSupplyPower";
            data.type = "estateSupplyPower";//根据 ExamineEstateSupplyEnumType 配置
            return data;
        },
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function () {
            var cols = commonColumn.estateSupplyPowerColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="estateSupplyPower.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="estateSupplyPower.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            var estateId = estateCommon.getEstateId();
            $("#" + estateSupplyPower.prototype.config().table).bootstrapTable('destroy');
            TableInit(estateSupplyPower.prototype.config().table, getContextPath() + "/basicEstateSupply/getBootstrapTableVo", cols, {
                type: estateSupplyPower.prototype.config().type,
                estateId: estateId
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicEstateSupply/deleteBasicEstateSupply",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                       notifySuccess("成功", "删除成功");
                        estateSupplyPower.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            estateSupplyPower.prototype.init({});
            $("#" + estateSupplyPower.prototype.config().frm + " .type").val(estateSupplyPower.prototype.config().type);
            $('#' + estateSupplyPower.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + estateSupplyPower.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(estateSupplyPower.prototype.config().frm,true);
            data.estateId = estateCommon.getEstateId();
            $.ajax({
                url: getContextPath() + "/basicEstateSupply/saveAndUpdateBasicEstateSupply",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                       notifySuccess("成功", "保存成功");
                        $('#' + estateSupplyPower.prototype.config().box).modal('hide');
                        estateSupplyPower.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicEstateSupply/getBasicEstateSupplyById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (estateSupplyPower.prototype.isNotBlank(result.data)) {
                            estateSupplyPower.prototype.init(result.data);
                        } else {
                            estateSupplyPower.prototype.init({});
                        }
                        $('#' + estateSupplyPower.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + estateSupplyPower.prototype.config().frm).clearAll();
            $("#" + estateSupplyPower.prototype.config().frm).initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_line_water_supply_pipe_grade, item.lineGrade, function (html, data) {
                $("#" + estateSupplyPower.prototype.config().frm).find("select.lineGrade").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.data_company_reputation, item.reputation, function (html, data) {
                $("#" + estateSupplyPower.prototype.config().frm).find("select.reputation").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_supplier_grade, item.grade, function (html, data) {
                $("#" + estateSupplyPower.prototype.config().frm).find("select.grade").empty().html(html).trigger('change');
            });
        }
    }

    //绑定事件
    $('#' + estateSupplyPower.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        estateSupplyPower.prototype.loadDataDicList();
    })
})();

var estateSupplyHeating;
;(function () {
    estateSupplyHeating = function () {
    };
    estateSupplyHeating.prototype = {
        config: function () {
            var data = {};
            data.table = "EstateSupplyHeatingList";
            data.box = "divBoxEstateSupplyHeating";
            data.frm = "frmEstateSupplyHeating";
            data.type = "estateSupplyHeating";//根据 ExamineEstateSupplyEnumType 配置
            return data;
        },
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function () {
            var cols = commonColumn.estateSupplyHeatingColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="estateSupplyHeating.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="estateSupplyHeating.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            var estateId = estateCommon.getEstateId();
            $("#" + estateSupplyHeating.prototype.config().table).bootstrapTable('destroy');
            TableInit(estateSupplyHeating.prototype.config().table, getContextPath() + "/basicEstateSupply/getBootstrapTableVo", cols, {
                type: estateSupplyHeating.prototype.config().type,
                estateId: estateId
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicEstateSupply/deleteBasicEstateSupply",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                       notifySuccess("成功", "删除成功");
                        estateSupplyHeating.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            estateSupplyHeating.prototype.init({});
            $("#" + estateSupplyHeating.prototype.config().frm + " .type").val(estateSupplyHeating.prototype.config().type);
            $('#' + estateSupplyHeating.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + estateSupplyHeating.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(estateSupplyHeating.prototype.config().frm,true);
            data.estateId = estateCommon.getEstateId();
            $.ajax({
                url: getContextPath() + "/basicEstateSupply/saveAndUpdateBasicEstateSupply",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                       notifySuccess("成功", "保存成功");
                        $('#' + estateSupplyHeating.prototype.config().box).modal('hide');
                        estateSupplyHeating.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicEstateSupply/getBasicEstateSupplyById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (estateSupplyHeating.prototype.isNotBlank(result.data)) {
                            estateSupplyHeating.prototype.init(result.data);
                        } else {
                            estateSupplyHeating.prototype.init({});
                        }
                        $('#' + estateSupplyHeating.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + estateSupplyHeating.prototype.config().frm).clearAll();
            $("#" + estateSupplyHeating.prototype.config().frm).initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_line_water_supply_pipe_grade, item.lineGrade, function (html, data) {
                $("#" + estateSupplyHeating.prototype.config().frm).find("select.lineGrade").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.data_company_reputation, item.reputation, function (html, data) {
                $("#" + estateSupplyHeating.prototype.config().frm).find("select.reputation").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_supplier_grade, item.grade, function (html, data) {
                $("#" + estateSupplyHeating.prototype.config().frm).find("select.grade").empty().html(html).trigger('change');
            });
        }
    }

    //绑定事件
    $('#' + estateSupplyHeating.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        estateSupplyHeating.prototype.loadDataDicList();
    })
})();

var estateSupplyGas;
;(function () {
    estateSupplyGas = function () {
    };
    estateSupplyGas.prototype = {
        config: function () {
            var data = {};
            data.table = "EstateSupplyGasList";
            data.box = "divBoxEstateSupplyGas";
            data.frm = "frmEstateSupplyGas";
            data.type = "estateSupplyGas";//根据 ExamineEstateSupplyEnumType 配置
            return data;
        },
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function () {
            var cols = commonColumn.estateSupplyGasColumn();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="estateSupplyGas.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="estateSupplyGas.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            var estateId = estateCommon.getEstateId();
            $("#" + estateSupplyGas.prototype.config().table).bootstrapTable('destroy');
            TableInit(estateSupplyGas.prototype.config().table, getContextPath() + "/basicEstateSupply/getBootstrapTableVo", cols, {
                estateId: estateId,
                type: estateSupplyGas.prototype.config().type
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicEstateSupply/deleteBasicEstateSupply",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                       notifySuccess("成功", "删除成功");
                        estateSupplyGas.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            estateSupplyGas.prototype.init({});
            $("#" + estateSupplyGas.prototype.config().frm + " .type").val(estateSupplyGas.prototype.config().type);
            $('#' + estateSupplyGas.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + estateSupplyGas.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(estateSupplyGas.prototype.config().frm,true);
            data.estateId = estateCommon.getEstateId();
            $.ajax({
                url: getContextPath() + "/basicEstateSupply/saveAndUpdateBasicEstateSupply",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                       notifySuccess("成功", "保存成功");
                        $('#' + estateSupplyGas.prototype.config().box).modal('hide');
                        estateSupplyGas.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicEstateSupply/getBasicEstateSupplyById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (estateSupplyGas.prototype.isNotBlank(result.data)) {
                            estateSupplyGas.prototype.init(result.data);
                        } else {
                            estateSupplyGas.prototype.init({});
                        }
                        $('#' + estateSupplyGas.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + estateSupplyGas.prototype.config().frm).clearAll();
            $("#" + estateSupplyGas.prototype.config().frm).initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_line_water_supply_pipe_grade, item.lineGrade, function (html, data) {
                $("#" + estateSupplyGas.prototype.config().frm).find("select.lineGrade").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.data_company_reputation, item.reputation, function (html, data) {
                $("#" + estateSupplyGas.prototype.config().frm).find("select.reputation").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_supplier_grade, item.grade, function (html, data) {
                $("#" + estateSupplyGas.prototype.config().frm).find("select.grade").empty().html(html).trigger('change');
            });
        }
    }

    //绑定事件
    $('#' + estateSupplyGas.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        estateSupplyGas.prototype.loadDataDicList();
    })
})();

var estateInvestigation;
;(function () {
    estateInvestigation = function () {
    };
    estateInvestigation.prototype = {
        config: function () {
            var data = {};
            data.table = "EstateInvestigationList";
            data.box = "divBoxEstateInvestigation";
            data.frm = "frmEstateInvestigation";
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.estateInvestigation();
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="estateInvestigation.prototype.getAndInit(' + row.id + ')"><i class="fa fa-pen"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="estateInvestigation.prototype.removeData(' + row.id + ')"><i class="fa fa-minus"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + estateInvestigation.prototype.config().table).bootstrapTable('destroy');
            TableInit(estateInvestigation.prototype.config().table, getContextPath() + "/basicEstateInvestigation/getEstateInvestigationListByEstateId", cols, {
                estateId: estateCommon.getEstateId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            $.ajax({
                url: getContextPath() + "/basicEstateInvestigation/deleteBasicEstateInvestigation",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                       notifySuccess("成功", "删除成功");
                        estateInvestigation.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showModel: function () {
            $("#" + estateInvestigation.prototype.config().frm).clearAll();
            var estateId = estateCommon.getEstateId();
            $("#" + estateInvestigation.prototype.config().frm).find("input[name='estateId']").val(estateId);
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseLoadUtility, '', function (html, data) {
                $("#" + estateInvestigation.prototype.config().frm).find('select.planningUse').empty().html(html).trigger('change');
            });
            $('#' + estateInvestigation.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + estateInvestigation.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(estateInvestigation.prototype.config().frm,true);
            data.estateId = estateCommon.getEstateId();
            $.ajax({
                url: getContextPath() + "/basicEstateInvestigation/saveAndUpdateBasicEstateInvestigation",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                       notifySuccess("成功", "保存成功");
                        $('#' + estateInvestigation.prototype.config().box).modal('hide');
                        estateInvestigation.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        isNotNull: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        getAndInit: function (id) {
            $.ajax({
                url: getContextPath() + "/basicEstateInvestigation/getBasicEstateInvestigationById",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (estateInvestigation.prototype.isNotNull(data)) {
                            estateInvestigation.prototype.init(data);
                        } else {
                            estateInvestigation.prototype.init({});
                        }
                        $('#' + estateInvestigation.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        init: function (item) {
            $("#" + estateInvestigation.prototype.config().frm).clearAll();
            $("#" + estateInvestigation.prototype.config().frm).initForm(item);
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseLoadUtility, item.planningUse, function (html, data) {
                $("#" + estateInvestigation.prototype.config().frm).find('select.planningUse').empty().html(html).trigger('change');
            });
        },
        importData : function () {
            $.ajaxFileUpload({
                type: "POST",
                url: getContextPath() + "/basicEstateInvestigation/importData",
                data: {
                    estateId : estateCommon.getEstateId()
                },//要传到后台的参数，没有可以不写
                secureuri: false,//是否启用安全提交，默认为false
                fileElementId: 'ajaxFileUploadInvestigation',//文件选择框的id属性
                dataType: 'json',//服务器返回的格式
                async: false,
                success: function (result) {
                    if (result.ret) {
                        estateInvestigation.prototype.loadDataDicList();
                        notifySuccess(result.data);
                    }
                },
                error: function (result, status, e) {
                    Loading.progressHide();
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            });
        }
    }

    //绑定事件
    $('#' + estateInvestigation.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        estateInvestigation.prototype.loadDataDicList();
    })

})();
