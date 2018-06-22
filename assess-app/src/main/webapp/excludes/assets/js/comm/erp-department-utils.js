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
        this.selectedNode = undefined;
        var target = $("#select_department_modal");
        if (target.length > 0) {
            $("#select_department_modal").remove();
        }
        this.defaults = $.extend({}, this.defaults, options);

        var html = '<div id="select_department_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog" aria-hidden="true">';
        html += '<div class="modal-dialog modal-lg">';
        html += '<div class="modal-content">';
        html += '<div class="modal-header">';
        html += '<h3 class="modal-title">部门选择</h3>';
        html += '</div>';
        html += '<div class="modal-body">';
        html += '<div class="row">';
        html += ' <div class="col-md-12">';
        html += '<div id="select_department_tree"></div>';
        html += '</div>';
        html += '</div>';
        html += '</div>';
        html += '<div class="modal-footer">';
        html += '<button type="button" data-dismiss="modal" class="btn btn-default">';
        html += '取消';
        html += '</button>';
        html += '<button type="button" class="btn btn-primary select_deparment_modal_ok" >';
        html += '保存';
        html += '</button>';
        html += '</div>';
        html += '</div>';
        html += '</div>';
        html += '</div>';
        $(document.body).append(html);
        $("#select_department_modal").find(".select_deparment_modal_ok").off('click').on('click', function () {
            if (that.defaults.onSelected) {
                var nodes = $('#select_department_tree').treeview('getSelected');
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