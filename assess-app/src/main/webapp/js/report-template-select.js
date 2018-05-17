/**
 * Created by kings on 2018-4-18.
 */
(function ($) {

    //加载列表数据
    function loadSelectFormList() {
        var cols = [];
        cols.push({field: 'name', title: '名称'});
        cols.push({field: 'typeName', title: '类型'});
        cols.push({field: 'categoryName', title: '类别'});
        cols.push({
            field: 'opt', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning" href="javascript://" onclick="reportTemplate.onSelected(' + index + ');"><i class="fa fa-edit"></i>选择</a>';
                str += '</div>';
                return str;
            }
        });
        $("#report_template_select_list").bootstrapTable('destroy');
        TableInit("report_template_select_list", getContextPath()+"/reportTemplate/getTemplateList", cols, {
            type: $("#queryType").val(),
            category: $("#queryCategory").val()
        }, {
            showColumns: false,
            showRefresh: false,
            uniqueId: "id",
            search: false,
            onLoadSuccess: function () {
                $('#report_template_select_modal').modal('show');
            }
        });
    }


    var ReportTemplate = function () {
        this.callback = function (row) {

        }
    }

    ReportTemplate.prototype.select = function (callback) {
        var that = this;
        var target = $("#report_template_select_modal");
        if (target.length > 0) {
            $("#report_template_select_modal").remove();
        }
        this.callback = callback;
        var html = '<div id="report_template_select_modal" class="modal fade bs-example-modal-sm" data-backdrop="static" aria-hidden="true" data-height="600"';
        html += 'role="dialog" data-keyboard="false" tabindex="1" style="display: none;">';
        html += '<div class="modal-dialog " style="width: 1000px;">';
        html += '<div class="modal-content">';
        html += '<div class="modal-header">';
        html += '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span';
        html += 'aria-hidden="true">&times;</span></button>';
        html += '<h4 class="modal-title">报告模板选择</h4>';
        html += '</div>';
        html += '<div class="modal-body">';
        html += '<form class="form-horizontal">';
        html += '<div class="form-group ">';
        html += '<div>';
        html += '<label class="col-sm-1 control-label">';
        html += '名称';
        html += '</label>';
        html += '<div class="col-sm-3">';
        html += '<input type="text" name="queryName" id="queryName"';
        html += 'class="form-control">';
        html += '</div>';
        html += '<label class="col-sm-1 control-label">';
        html += '分组';
        html += '</label>';
        html += '<div class="col-sm-3">';
        html += '<select name="queryGroup" id="queryGroup" class="form-control">';
        html += '</select>';
        html += '</div>';
        html += '</div>';
        html += '<div class="col-sm-3">';
        html += '<button type="button" class="btn btn-primary btn-query-data">';
        html += '查询';
        html += '</button>';
        html += '</div>';
        html += '</div>';
        html += '</form>';
        html += '<table id="report_template_select_list" class="table table-bordered">';
        html += '</table>';
        html += '</div>';
        html += '<div class="modal-footer">';
        html += '<button type="button" data-dismiss="modal" class="btn btn-default">';
        html += '取消';
        html += '</button>';
        html += '</div>';
        html += '</div>';
        html += '</div>';
        html += '</div>';

        $(document.body).append(html);
        $("#report_template_select_modal").find(".btn-query-data").off('click').on('click', function () {
            loadSelectFormList();
        })
        loadSelectFormList();
    }
    ReportTemplate.prototype.onSelected = function (index) {
        var row = $("#report_template_select_list").bootstrapTable('getData')[index];
        if (this.callback) {
            this.callback(row);
        }
        $('#report_template_select_modal').modal('hide');
    }
    window.reportTemplate = new ReportTemplate();
})(jQuery)