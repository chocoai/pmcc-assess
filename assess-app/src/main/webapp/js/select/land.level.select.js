/**
 * Created by kings on 2018-4-18.
 * 选择土地级别 工具方法
 */
(function ($) {
    var AssessLandLevel = function () {
        this.success = function (data) {

        }
    }

    //加载列表数据
    AssessLandLevel.prototype.loadLandLevelList = function (province, city, district) {
        var cols = [];
        cols.push({field: 'classify', title: '大类', width: '10%'});
        cols.push({field: 'type', title: '类型', width: '10%'});
        cols.push({field: 'category', title: '类别', width: '10%'});
        cols.push({
            field: 'levelRange', title: '级别范围', width: '50%', formatter: function (value, row, index) {
                return '<span title="' + row.mainStreet + '">' + value + '</span>';
            }
        });
        cols.push({
            field: 'opt', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning" href="javascript://" onclick="assessLandLevel.onSelected(' + index + ');"><i class="fa fa-edit"></i>选择</a>';
                str += '</div>';
                return str;
            }
        });
        $("#select_land_level_list").bootstrapTable('destroy');
        TableInit("select_land_level_list", getContextPath() + "/dataLandLevel/getDataLandLevelDetailListByArea", cols, {
            province: province,
            city: city,
            district: district
        }, {
            showColumns: false,
            showRefresh: false,
            uniqueId: "id",
            search: false,
            onLoadSuccess: function (data) {
                if (data && data.rows && data.rows.length > 0) {
                    FileUtils.getFileShows({
                        target: "select_land_level_file",
                        formData: {
                            tableName: AssessDBKey.DataLandLevel,
                            tableId: data.rows[0].landLevelId
                        },
                        deleteFlag: false
                    })
                }
            }
        });
    }

    //弹出选择土地级别窗口
    AssessLandLevel.prototype.select = function (options) {
        var that = this;
        var target = $("#select_land_level_modal");
        if (target.length > 0) {
            $("#select_land_level_modal").remove();
        }
        that.success = options.success;
        var html = '<div id="select_land_level_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" ';
        html += 'role="dialog" data-keyboard="false" tabindex="1" >';
        html += '<div class="modal-dialog  modal-lg">';
        html += '<div class="modal-content">';
        html += '<div class="modal-header">';
        html += '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span';
        html += 'aria-hidden="true">&times;</span></button>';
        html += '<h3 class="modal-title">土地级别选择</h3>';
        html += '</div>';
        html += '<div class="modal-body">';
        html += '<div class="form-group form-horizontal">';
        html += '<div class="x-valid">';
        html += '<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">';
        html += '<div id="_select_land_level_file"></div>';
        html += '</div>';
        html += '</div>';
        html += '</div>';
        html += '<table id="select_land_level_list" class="table table-bordered">';
        html += '</table>';
        html += '</div>';
        html += '</div>';
        html += '</div>';

        $(document.body).append(html);

        assessLandLevel.loadLandLevelList(options.province, options.city, options.district);
        $('#select_land_level_modal').modal('show');
    }

    //选择土地级别
    AssessLandLevel.prototype.onSelected = function (index) {
        var row = $("#select_land_level_list").bootstrapTable('getData')[index];
        if (this.success) {
            var data = {};
            data.id = row.id;
            if (row.classify) {
                data.name = row.classify;
            }
            if (row.type) {
                data.name += '/' + row.type;
            }
            if (row.category) {
                data.name += '/' + row.category;
            }
            this.success(data);
        }
        $('#select_land_level_modal').modal('hide');
    }
    window.assessLandLevel = new AssessLandLevel();
})(jQuery)