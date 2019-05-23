/**
 * Created by kings on 2018-4-18.
 * 选择版块 工具方法
 */
(function ($) {
    var AssessBlock = function () {
        this.success = function (data) {

        }
    }

    //加载列表数据
    AssessBlock.prototype.loadBlockList = function (province, city, district) {
        var cols = [];
        cols.push({field: 'name', title: '名称'});
        cols.push({field: 'regionalNature', title: '性质'});
        cols.push({field: 'remark', title: '描述'});
        cols.push({
            field: 'opt', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning" href="javascript://" onclick="assessBlock.onSelected(' + index + ');"><i class="fa fa-edit"></i>选择</a>';
                str += '</div>';
                return str;
            }
        });
        $("#select_block_list").bootstrapTable('destroy');
        TableInit("select_block_list", getContextPath() + "/dataBlock/getDataBlockList", cols, {
            province: province,
            city: city,
            district: district,
            name: $('#select_block_modal').find('[name=name]').val()
        }, {
            showColumns: false,
            showRefresh: false,
            uniqueId: "id",
            search: false
        });
    }

    //弹出选择版块窗口
    AssessBlock.prototype.select = function (options) {
        var that = this;
        var target = $("#select_block_modal");
        if (target.length > 0) {
            $("#select_block_modal").remove();
        }
        that.success = options.success;
        var html = '<div id="select_block_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" ';
        html += 'role="dialog" data-keyboard="false" tabindex="1" >';
        html += '<div class="modal-dialog  modal-lg">';
        html += '<div class="modal-content">';
        html += '<div class="modal-header">';
        html += '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span';
        html += 'aria-hidden="true">&times;</span></button>';
        html += '<h3 class="modal-title">版块选择</h3>';
        html += '</div>';
        html += '<div class="modal-body">';
        html += '<div class="form-horizontal">';
        html += '<div class="form-group ">';
        html += '<div>';
        html += '<label class="col-xs-1 col-sm-1 col-md-1 col-lg-1 control-label">';
        html += '名称';
        html += '</label>';
        html += '<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">';
        html += '<input type="text" name="name" class="form-control">';
        html += '</div>';
        html += '<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">';
        html += '<div type="button" class="btn btn-primary"';
        html += 'onclick="assessBlock.loadBlockList();">';
        html += '查询';
        html += '</div>';
        html += '</div>';
        html += '</div>';
        html += '</div>';
        html += '</div>';
        html += '<table id="select_block_list" class="table table-bordered">';
        html += '</table>';
        html += '</div>';
        html += '</div>';
        html += '</div>';

        $(document.body).append(html);
        assessBlock.loadBlockList(options.province,options.city,options.district);
        $('#select_block_modal').modal('show');
    }

    //选择版块
    AssessBlock.prototype.onSelected = function (index) {
        var row = $("#select_block_list").bootstrapTable('getData')[index];
        if (this.success) {
            this.success(row);
        }
        $('#select_block_modal').modal('hide');
    }
    window.assessBlock = new AssessBlock();
})(jQuery)