(function ($) {
    var ErpApprovalModel = function () {
        this.defaults = {
            onSelected: function (content) {

            }
        };
    }

    ErpApprovalModel.prototype.select = function (options) {
        if ($("#erp_approval_model").length > 0) {
            $("#erp_approval_model").modal();
        }
        else {
            var that = this;
            this.defaults = $.extend({}, this.defaults, options);

            var html = "<div id='erp_approval_model' class='modal fade bs-example-modal-lg' data-backdrop='static' tabindex='1' role='dialog' aria-hidden='true'>";
            html += "<div class='modal-dialog modal-lg'>";
            html += "<div class='modal-content'>";
            html += "<div class='modal-header'>";
            html += "<h4 class='modal-title'>审批模板</h4>";
            html += "</div>";
            html += "<div class='modal-body'>";
            html += "<table id='tb_erp_approval_model' class='table table-bordered'>";

            html += "</table>";
            html += "</div>";
            html += '<div class="modal-footer">';
            html += '<button type="button" data-dismiss="modal" class="btn btn-default">';
            html += '取消';
            html += '</button>';
            html += '</div>';
            html += "</div>";
            html += "</div>";
            html += "</div>";

            $(document.body).append(html);

            var cols = [];
            cols.push({field: 'approvalContent', width: '95%', title: '审批意见'});
            TableInit("tb_erp_approval_model", "/SysApprovalModel/getSysApproval", cols,
                {}, {
                    onLoadSuccess: function () {
                        $("#erp_approval_model").modal();
                    },
                    onDblClickRow: function (item) {
                        that.defaults.onSelected(item.approvalContent);
                        $("#erp_approval_model").modal("hide");
                    }
                });
        }
    }
    window.erpApprovalModel = new ErpApprovalModel();
})(jQuery);








