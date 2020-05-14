<%--
  Created by IntelliJ IDEA.
  User: zch
  Date: 2020-5-14
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="boxSchemeJudgeObj" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">权证估价对象</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">估价对象名称</label>
                                            <div class="col-sm-3">
                                                <input class="form-control input-full" type="text" name="name"
                                                       placeholder="估价对象名称">
                                            </div>
                                            <label class="col-sm-1 control-label">权证名称</label>
                                            <div class="col-sm-3">
                                                <input class="form-control input-full" type="text" name="certName"
                                                       placeholder="权证名称">
                                            </div>
                                            <label class="col-sm-1 control-label">所有权人</label>
                                            <div class="col-sm-3">
                                                <input class="form-control input-full" type="text" name="ownership"
                                                       placeholder="所有权人">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">坐落</label>
                                            <div class="col-sm-3">
                                                <input class="form-control input-full" type="text" name="seat"
                                                       placeholder="坐落">

                                            </div>

                                            <button class="btn btn-info btn-sm" type="button"
                                                    onclick="schemeJudgeObj.searchData(this);"><span class="btn-label">
												<i class="fa fa-search"></i>
											</span>搜索
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-sm-12">
                                        <table class="table table-bordered" id="boxSchemeJudgeObjList">
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
                <button type="button" class="btn btn-primary btn-sm" onclick="schemeJudgeObj.select()">
                    确定
                </button>
            </div>

        </div>
    </div>
</div>


<script>
    //选择估价对象
    var schemeJudgeObj = {};

    schemeJudgeObj.targetTable = $("#boxSchemeJudgeObjList");
    schemeJudgeObj.targetBox = $("#boxSchemeJudgeObj");
    schemeJudgeObj.callback = undefined;
    schemeJudgeObj.this_ = undefined;
    schemeJudgeObj.groupId = undefined;

    schemeJudgeObj.init = function (options) {
        var defaultObj = {};
        jQuery.extend(defaultObj, options);
        if (defaultObj.callback) {
            schemeJudgeObj.callback = defaultObj.callback;
        }
        if (defaultObj.this_) {
            schemeJudgeObj.this_ = defaultObj.this_;
        }
        schemeJudgeObj.loadSchemeJudgeObjTable({areaGroupId:options.areaGroupId});
        schemeJudgeObj.targetBox.modal('show');
    };

    schemeJudgeObj.loadSchemeJudgeObjTable = function (options) {
        var table = schemeJudgeObj.targetTable;
        var cols = [];
        cols.push({field: 'name', title: '估价对象名称', width: "22%"});
        cols.push({field: 'certName', title: '权证名称', width: "22%"});
        cols.push({field: 'ownership', title: '所有权人', width: "22%"});
        cols.push({field: 'seat', title: '坐落', width: "22%"});
        var method = {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        };
        table.bootstrapTable('destroy');
        TableInit(table, "${pageContext.request.contextPath}/schemeLiquidationAnalysis/getSchemeJudgeObjList", cols, options, method, true);
    };

    schemeJudgeObj.select = function () {
        var rows = schemeJudgeObj.targetTable.bootstrapTable('getSelections');
        if (rows && rows.length > 0) {
            var idArray = [];
            $.each(rows, function (i, item) {
                idArray.push(item.id);
            })

            var ids = idArray.join(",");
            if (schemeJudgeObj.callback) {
                schemeJudgeObj.callback(schemeJudgeObj.this_, ids);
                schemeJudgeObj.targetBox.modal('hide');
            }

        } else {
            notifyInfo('提示', '至少选择一个');
        }
    }

    schemeJudgeObj.searchData = function (_this) {
        var group = $(_this).closest(".form-horizontal");
        var name = group.find("[name='name']").val();
        var certName = group.find("[name='certName']").val();
        var ownership = group.find("[name='ownership']").val();
        var seat = group.find("[name='seat']").val();
        var data = {areaGroupId: '${areaGroup.id}'};
        if (name) {
            data.name = name;
        }
        if (certName) {
            data.certName = certName;
        }
        if (ownership) {
            data.ownership = ownership;
        }
        if (seat) {
            data.seat = seat;
        }

        schemeJudgeObj.loadSchemeJudgeObjTable(data);
    };
</script>