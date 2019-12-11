<%--
  Created by IntelliJ IDEA.
  User: zch
  Date: 2019-12-3
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="boxDeclareRecordModeObj" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">权证选择控件</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <div class="panel-body">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">省</label>
                                        <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                            <select name="province"
                                                    class="form-control search-select select2"></select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">市</label>
                                        <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                            <select name="city"
                                                    class="form-control search-select select2"></select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">县</label>
                                        <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                            <select name="district"
                                                    class="form-control search-select select2"></select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <div class="x-valid">
                                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                                <div class="input-group">
                                                    <input class="form-control" type="text" name="name"
                                                           placeholder="权证号">
                                                    <span class="input-group-addon"
                                                          onclick="declareRecordModeObj.searchData(this);">搜索<i
                                                            class="fa fa-search"></i> </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                        <table class="table table-bordered" id="boxDeclareRecordModeObjList">
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
                <button type="button" class="btn btn-primary" onclick="declareRecordModeObj.select()">
                    确定
                </button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    var declareRecordModeObj = {};

    declareRecordModeObj.targetTable = $("#boxDeclareRecordModeObjList");
    declareRecordModeObj.targetBox = $("#boxDeclareRecordModeObj");

    declareRecordModeObj.callback = undefined;
    declareRecordModeObj.this_ = undefined;
    declareRecordModeObj.ids = undefined;
    declareRecordModeObj.projectId = undefined;

    declareRecordModeObj.init = function (options) {
        var defaultObj = {projectId: '${projectPlanDetails.projectId}'};
        jQuery.extend(defaultObj, options);
        var box = declareRecordModeObj.targetBox;
        if (box instanceof jQuery) {
        } else {
            box = $("#" + box.attr("id"));
        }
        if (defaultObj.callback) {
            declareRecordModeObj.callback = defaultObj.callback;
        }
        if (defaultObj.this_) {
            declareRecordModeObj.this_ = defaultObj.this_;
        }
        if (defaultObj.ids) {
            declareRecordModeObj.ids = defaultObj.ids;
        }
        if (defaultObj.projectId) {
            declareRecordModeObj.projectId = defaultObj.projectId;
        }
        declareRecordModeObj.loadDeclareRecordTable({projectId: defaultObj.projectId});
        box.modal('show');
        AssessCommon.initAreaInfo({
            provinceTarget: box.find("select[name='province']"),
            cityTarget: box.find("select[name='city']"),
            districtTarget: box.find("select[name='district']"),
            provinceValue: null,
            cityValue: null,
            districtValue: null
        });
    };

    /**
     * 确定选择的申报
     */
    declareRecordModeObj.select = function () {
        var box = declareRecordModeObj.targetBox;
        if (box instanceof jQuery) {
        } else {
            box = $("#" + box.attr("id"));
        }
        var table = declareRecordModeObj.targetTable;
        if (table instanceof jQuery) {
        } else {
            table = $("#" + table.attr("id"));
        }
        var rows = table.bootstrapTable('getSelections');
        if (rows.length >= 1) {
            var tempData = [];
            for (var i = 0; i < rows.length; i++) {
                tempData.push(rows[i].id);
            }
            if (declareRecordModeObj.callback) {
                if (declareRecordModeObj.this_) {
                    declareRecordModeObj.callback(declareRecordModeObj.this_, tempData.join(","));
                } else {
                    declareRecordModeObj.callback(tempData.join(","));
                }
                box.modal('hide');
            }
        } else {
            Alert("至少选择一个");
        }
    };

    declareRecordModeObj.searchData = function (_this) {
        var group = $(_this).closest(".form-group");
        var province = group.find("select[name='province']").val();
        var city = group.find("select[name='city']").val();
        var district = group.find("select[name='district']").val();
        var name = group.find("[name='name']").val();
        var data = {projectId: declareRecordModeObj.projectId};
        if (province) {
            data.province = province;
        }
        if (city) {
            data.city = city;
        }
        if (district) {
            data.district = district;
        }
        if (name) {
            data.name = name;
        }
        declareRecordModeObj.loadDeclareRecordTable(data);
    };

    declareRecordModeObj.loadDeclareRecordTable = function (options) {
        var table = declareRecordModeObj.targetTable;
        if (table instanceof jQuery) {
        } else {
            table = $("#" + table.attr("id"));
        }
        var cols = [];
        cols.push({checkbox: true, width: "5%"});
        cols.push({field: 'name', title: '权证名称', width: "22%"});
        cols.push({field: 'buildingNumber', title: '楼栋号', width: "6%"});
        cols.push({field: 'unit', title: '单元号', width: "6%"});
        cols.push({field: 'ownership', title: '所有权人', width: "6%"});
        cols.push({field: 'seat', title: '坐落', width: "19%"});
//        cols.push({field: 'floorArea', title: '证载面积', width: "9%"});
//        cols.push({field: 'practicalArea', title: '实际面积', width: "9%"});
        var method = {
            showColumns: true,
            showRefresh: true,
            search: false,
            onLoadSuccess: function () {//加载成功时执行
                //对曾经选中过的依旧保持选中状态
                if (declareRecordModeObj.ids) {
                    var values = declareRecordModeObj.ids.split(",");
                    var ids = [];
                    $.each(values, function (i, item) {
                        ids.push(parseInt(item));
                    });
                    table.bootstrapTable("checkBy", {field: 'id', values: ids});
                }
            },
            onLoadError: function () {

            }
        };
        table.bootstrapTable('destroy');
        TableInit(table, "${pageContext.request.contextPath}/declareRecord/getDeclareRecordList", cols, options, method);
    };

</script>