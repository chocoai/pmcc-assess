(function ($) {
    var ErpDepartment = function () {
        this.defaults = {
            multi: false,//是否允许多选
            currOrgId: 1,//当前组织id
            value: undefined,//初始值
            onSelected: function (node) {

            }
        };
    }



    ErpDepartment.prototype.select = function (options) {
        var that = this;
        var target = $("#select_employee_modal");
        if (target.length > 0) {
            $("#select_employee_modal").remove();
        }
        this.defaults = $.extend({}, this.defaults, options);

        var html = '<div id="select_employee_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog" aria-hidden="true">';
        html += '<div class="modal-dialog modal-lg">';
        html += '<div class="modal-content">';
        html += '<div class="modal-header">';
        html += '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span';
        html += 'aria-hidden="true">&times;</span></button>';
        html += '<h4 class="modal-title">人员选择</h4>';
        html += '</div>';
        html += '<div class="panel-body" style="padding: 0px">';
        html += '<input id="select_employee_tags" type="text" class="tags">';
        html += '<input id="select_employee_single" type="hidden">';
        html += '<div id="select_employee_tree" class="col-md-3" style="height: 360px;overflow: auto;">';
        html += '</div>';
        html += '<div class="col-md-9">';
        html += '<table id="select_employee_table" class="table table-bordered">';
        html += '</table>';
        html += '</div>';
        html += '</div>';
        html += '<div class="modal-footer" style="margin-top: 0px">';
        html += '<button type="button" data-dismiss="modal" class="btn btn-default">';
        html += '取消';
        html += '</button>';
        html += '<button type="button" class="btn btn-primary select_employee_modal_ok" >';
        html += '保存';
        html += '</button>';
        html += '</div>';
        html += '</div>';
        html += '</div>';
        html += '</div>';
        $(document.body).append(html);
        $("#select_employee_modal").find(".select_employee_modal_ok").off('click').on('click', function () {
            if (that.defaults.onSelected) {
                var nodes = $('#select_employee_tree').treeview('getSelected');
                if (!nodes) {
                    Alert("请选择部门");
                }
                that.defaults.onSelected(nodes);
            }
            $("#select_department_modal").modal("hide");
        });
        initBaseTreeView("select_department_tree",
            "/department/getDepartmentTree/", {pid: that.defaults.currOrgId}, that.defaults.multi, function (objs) {
                treeView_setValue("select_department_tree", that.defaults.value);
                $("#select_department_modal").modal();
            });
    }
    window.erpDepartment = new ErpDepartment();
})(jQuery)