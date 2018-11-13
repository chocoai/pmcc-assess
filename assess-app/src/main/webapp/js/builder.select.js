/**
 * Created by kings on 2018-4-18.
 * 选择建造商 工具方法
 */
(function ($) {
    var AssessBuilder = function () {
        this.callback = function (row) {

        }
    }

    //加载列表数据
    AssessBuilder.prototype.loadBuilderList=function () {
        var cols = [];
        cols.push({field: 'name', title: '名称'});
        cols.push({field: 'companyNature', title: '公司性质'});
        cols.push({field: 'socialPrestige', title: '社会信誉'});
        cols.push({
            field: 'opt', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning" href="javascript://" onclick="assessBuilder.onSelected(' + index + ');"><i class="fa fa-edit"></i>选择</a>';
                str += '</div>';
                return str;
            }
        });
        $("#select_builder_list").bootstrapTable('destroy');
        TableInit("select_builder_list", getContextPath() + "/dataBuilder/getDataBuilderList", cols, {
            name: $('#select_builder_modal').find('[name=name]').val()
        }, {
            showColumns: false,
            showRefresh: false,
            uniqueId: "id",
            search: false
        });
    }

    //弹出选择开发商窗口
    AssessBuilder.prototype.select = function (callback) {
        var that = this;
        var target = $("#select_builder_modal");
        if (target.length > 0) {
            $("#select_builder_modal").remove();
        }
        that.callback = callback;
        var html = '<div id="select_builder_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" ';
        html += 'role="dialog" data-keyboard="false" tabindex="1" >';
        html += '<div class="modal-dialog  modal-lg">';
        html += '<div class="modal-content">';
        html += '<div class="modal-header">';
        html += '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span';
        html += 'aria-hidden="true">&times;</span></button>';
        html += '<h3 class="modal-title">建造商选择</h3>';
        html += '</div>';
        html += '<div class="modal-body">';
        html += '<form class="form-horizontal">';
        html += '<div class="form-group ">';
        html += '<div>';
        html += '<label class="col-sm-1 control-label">';
        html += '名称';
        html += '</label>';
        html += '<div class="col-sm-3">';
        html += '<input type="text" name="name" class="form-control">';
        html += '</div>';
        html += '<div class="col-sm-3">';
        html += '<button type="button" class="btn btn-primary"';
        html += 'onclick="assessBuilder.loadBuilderList();">';
        html += '查询';
        html += '</button>';
        html += '</div>';
        html += '</div>';
        html += '</div>';
        html += '</form>';
        html += '<table id="select_builder_list" class="table table-bordered">';
        html += '</table>';
        html += '</div>';
        html += '</div>';
        html += '</div>';

        $(document.body).append(html);
        assessBuilder.loadBuilderList();
        $('#select_builder_modal').modal('show');
    }

    //选择开发商
    AssessBuilder.prototype.onSelected = function (index) {
        var row = $("#select_builder_list").bootstrapTable('getData')[index];
        if (this.callback) {
            this.callback(row);
        }
        $('#select_builder_modal').modal('hide');
    }


    window.assessBuilder = new AssessBuilder();
})(jQuery)