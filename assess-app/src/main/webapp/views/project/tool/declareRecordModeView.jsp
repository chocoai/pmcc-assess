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
                <h4 class="modal-title">权证选择控件</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmFather" class="form-horizontal">
                    <input type="hidden" id="id" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 control-label">坐落</label>
                                        <div class="col-sm-3">
                                            <input class="form-control input-full" type="text" name="seat"
                                                   placeholder="坐落">
                                        </div>
                                        <label class="col-sm-1 control-label">楼栋号</label>
                                        <div class="col-sm-3">
                                            <input class="form-control input-full" type="text" name="buildingNumber"
                                                   placeholder="楼栋号">
                                        </div>
                                        <label class="col-sm-1 control-label">单元号</label>
                                        <div class="col-sm-3">
                                            <input class="form-control input-full" type="text" name="unit"
                                                   placeholder="单元号">
                                        </div>

                                    </div>

                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 control-label">权证号</label>
                                        <div class="col-sm-3">
                                            <input class="form-control input-full" type="text" name="name"
                                                   placeholder="权证号">
                                        </div>
                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="declareRecordModeObj.searchData(this)">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            搜索
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="col-sm-12">
                                        <table class="table table-bordered" id="boxDeclareRecordModeObjList">
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="declareRecordModeObj.select()">
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
    declareRecordModeObj.singleSelect = undefined;

    declareRecordModeObj.init = function (options,singleSelect) {
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
        declareRecordModeObj.singleSelect = singleSelect;
        declareRecordModeObj.loadDeclareRecordTable({projectId: defaultObj.projectId},singleSelect);
        box.modal('show');
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
            notifyInfo('提示',"至少选择一个");
        }
    };

    declareRecordModeObj.searchData = function (_this) {
        var group = $(_this).closest(".form-horizontal");
//        var province = group.find("select[name='province']").val();
//        var city = group.find("select[name='city']").val();
//        var district = group.find("select[name='district']").val();
        var name = group.find("[name='name']").val();
        var seat = group.find("[name='seat']").val();
        var unit = group.find("[name='unit']").val();
        var buildingNumber = group.find("[name='buildingNumber']").val();
        var data = {projectId: declareRecordModeObj.projectId};
//        if (province) {
//            data.province = province;
//        }
//        if (city) {
//            data.city = city;
//        }
//        if (district) {
//            data.district = district;
//        }
        if (name) {
            data.name = name;
        }
        if (unit) {
            data.unit = unit;
        }
        if (buildingNumber) {
            data.buildingNumber = buildingNumber;
        }
        if (seat) {
            data.seat = seat;
        }
        declareRecordModeObj.loadDeclareRecordTable(data,declareRecordModeObj.singleSelect);
    };

    declareRecordModeObj.loadDeclareRecordTable = function (options,singleSelect) {
        var table = declareRecordModeObj.targetTable;
        if (table instanceof jQuery) {
        } else {
            table = $("#" + table.attr("id"));
        }
        var cols = [];
        cols.push({checkbox: true, width: "5%"});
        cols.push({field: 'name', title: '权证名称', width: "22%"});
        cols.push({field: 'buildingNumber', title: '楼栋号', width: "15%"});
        cols.push({field: 'unit', title: '单元号', width: "15%"});
        cols.push({field: 'ownership', title: '所有权人', width: "15%"});
        cols.push({field: 'seat', title: '坐落', width: "29%"});
//        cols.push({field: 'floorArea', title: '证载面积', width: "9%"});
//        cols.push({field: 'practicalArea', title: '实际面积', width: "9%"});
        var method = {
            showColumns: false,
            showRefresh: false,
            search: false,
            singleSelect: singleSelect?true:false,
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
        TableInit(table, "${pageContext.request.contextPath}/declareRecord/getBootstrapTableVo", cols, options, method);
    };

</script>