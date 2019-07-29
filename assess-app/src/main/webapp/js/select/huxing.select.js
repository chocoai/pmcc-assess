/**
 * Created by kings on 2018-4-18.
 * 选择户型 工具方法
 */
(function ($) {
    var AssessHuxing = function () {
        this.success = function (data) {

        }
    }

    //加载列表数据
    AssessHuxing.prototype.loadHuxingList = function (basicApplyId, caseUnitId) {
        var cols = [];
        cols.push({field: 'name', title: '房型'});
        cols.push({field: 'area', title: '面积'});
        cols.push({field: 'orientationName', title: '朝向'});
        cols.push({field: 'fileViewName', title: '户型图'});
        cols.push({
            field: 'opt', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning" href="javascript://" onclick="assessHuxing.onSelected(' + index + ');"><i class="fa fa-edit"></i>选择</a>';
                str += '</div>';
                return str;
            }
        });
        $("#select_huxing_list").bootstrapTable('destroy');
        TableInit("select_huxing_list", getContextPath() + "/basicUnitHuxing/getSelectHuxingList", cols, {
            basicApplyId: basicApplyId,
            caseUnitId:caseUnitId
        }, {
            showColumns: false,
            showRefresh: false,
            uniqueId: "id",
            search: false
        });
    }

    //加载列表数据ByBasicUnitId
    AssessHuxing.prototype.loadHuxingListByBasicUnitId = function (basicUnitId) {
        var cols = [];
        cols.push({field: 'name', title: '房型'});
        cols.push({field: 'area', title: '面积'});
        cols.push({field: 'orientationName', title: '朝向'});
        cols.push({field: 'fileViewName', title: '户型图'});
        cols.push({
            field: 'opt', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning" href="javascript://" onclick="assessHuxing.onSelected(' + index + ');"><i class="fa fa-edit"></i>选择</a>';
                str += '</div>';
                return str;
            }
        });
        $("#select_huxing_list").bootstrapTable('destroy');
        TableInit("select_huxing_list", getContextPath() + "/basicUnitHuxing/getSelectHuxingListByUnitId", cols, {
            basicUnitId: basicUnitId
        }, {
            showColumns: false,
            showRefresh: false,
            uniqueId: "id",
            search: false
        });
    }

    //弹出选择户型窗口
    AssessHuxing.prototype.select = function (options) {
        var that = this;
        var target = $("#select_huxing_modal");
        if (target.length > 0) {
            $("#select_huxing_modal").remove();
        }
        that.success = options.success;
        var html = '<div id="select_huxing_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" ';
        html += 'role="dialog" data-keyboard="false" tabindex="1" >';
        html += '<div class="modal-dialog  modal-lg">';
        html += '<div class="modal-content">';
        html += '<div class="modal-header">';
        html += '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span';
        html += 'aria-hidden="true">&times;</span></button>';
        html += '<h3 class="modal-title">户型选择</h3>';
        html += '</div>';
        html += '<div class="modal-body">';
        html += '<table id="select_huxing_list" class="table table-bordered">';
        html += '</table>';
        html += '</div>';
        html += '</div>';
        html += '</div>';

        $(document.body).append(html);
        assessHuxing.loadHuxingList(options.basicApplyId,options.caseUnitId);
        $('#select_huxing_modal').modal('show');
    }

    //弹出选择户型窗口
    AssessHuxing.prototype.selectByBasicUnitId = function (options) {
        var that = this;
        var target = $("#select_huxing_modal");
        if (target.length > 0) {
            $("#select_huxing_modal").remove();
        }
        that.success = options.success;
        var html = '<div id="select_huxing_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" ';
        html += 'role="dialog" data-keyboard="false" tabindex="1" >';
        html += '<div class="modal-dialog  modal-lg">';
        html += '<div class="modal-content">';
        html += '<div class="modal-header">';
        html += '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span';
        html += 'aria-hidden="true">&times;</span></button>';
        html += '<h3 class="modal-title">户型选择</h3>';
        html += '</div>';
        html += '<div class="modal-body">';
        html += '<table id="select_huxing_list" class="table table-bordered">';
        html += '</table>';
        html += '</div>';
        html += '</div>';
        html += '</div>';

        $(document.body).append(html);
        assessHuxing.loadHuxingListByBasicUnitId(options.basicUnitId);
        $('#select_huxing_modal').modal('show');
    }
    //选择户型
    AssessHuxing.prototype.onSelected = function (index) {
        var row = $("#select_huxing_list").bootstrapTable('getData')[index];
        if (this.success) {
            this.success(row);
        }
        $('#select_huxing_modal').modal('hide');
    }
    window.assessHuxing = new AssessHuxing();
})(jQuery)