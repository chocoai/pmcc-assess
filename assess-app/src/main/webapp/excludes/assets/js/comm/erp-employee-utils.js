(function ($) {

    //初始化tag
    function initTag(obj) {
        $(obj).tagsInput({
            width: "auto",
            interactive: false,
            autosize: false,
            minheight: "50px",
            height: 50
        });
    }

    //加载人员列表信息
    function loadErpEmployeeList(options) {
        var defaults = {
            currOrgId: 1,//当前组织id
            multi: false,//是否允许多选
            modalElement: "",
            tableElement: ""
        };
        defaults = $.extend({}, this.defaults, options);

        var cols = [];
        cols.push({field: "userAccount", title: "员工账号"});
        cols.push({field: "userName", title: "员工姓名"});
        var data = {departmentId: defaults.currOrgId};
        var parms = {
            pageSize: 5,
            pageList: [5],
            showColumns: false,
            toolbar: '',
            showTotalPages: false,
            onClickRow: function (row, $element) {
                var tagText = row.userName + "_" + row.userAccount;
                var $tag = $("#" + defaults.modalElement).find(".tags");
                if (defaults.multi) {
                    if ($tag.val() && $tag.tagExist(tagText)) {
                        $tag.removeTag(tagText);
                    } else {
                        $tag.addTag(tagText);
                    }
                } else {
                    $tag.importTags(tagText, true);
                }
            },
            onLoadSuccess: function () {
                Loading.progressHide();
                $("#" + defaults.modalElement).modal({backdrop: 'static', keyboard: false});
            }
        };
        $("#" + defaults.tableElement).bootstrapTable("destroy");//销毁
        TableInit(defaults.tableElement, "/sysUser/getUserInfoByDepartmentId", cols, data, parms);
    }

    var ErpEmployee = function () {
        this.defaults = {
            multi: false,//是否允许多选
            currOrgId: 1,//当前组织id
            value: undefined,//初始值
            departmentNode: undefined,
            onSelected: function (node) {

            }
        };
    }

    //选人方法
    ErpEmployee.prototype.select = function (options) {
        var that = this;
        var target = $("#erp_select_employee_modal");
        if (target.length > 0) {
            $("#erp_select_employee_modal").remove();
        }
        this.defaults = $.extend({}, this.defaults, options);

        var html = '<div id="erp_select_employee_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog" aria-hidden="true">';
        html += '<div class="modal-dialog modal-lg">';
        html += '<div class="modal-content">';
        html += '<div class="modal-header">';
        html += '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span';
        html += 'aria-hidden="true">&times;</span></button>';
        html += '<h3 class="modal-title">ERP人员选择</h3>';
        html += '</div>';
        html += '<div class="x_content" style="padding: 0px">';
        html += '<input  type="text" class="tags">';
        html += '<div id="erp_select_employee_tree" class="col-md-3" style="height: 360px;overflow: auto;">';
        html += '</div>';
        html += '<div class="col-md-9">';
        html += '<table id="erp_select_employee_table" class="table table-bordered">';
        html += '</table>';
        html += '</div>';
        html += '</div>';
        html += '<div class="modal-footer" style="margin-top: 0px">';
        html += '<button type="button" data-dismiss="modal" class="btn btn-default">';
        html += '取消';
        html += '</button>';
        html += '<button type="button" class="btn btn-primary erp_select_employee_modal_ok" >';
        html += '保存';
        html += '</button>';
        html += '</div>';
        html += '</div>';
        html += '</div>';
        html += '</div>';

        $(document.body).append(html);
        var tagElement = $("#erp_select_employee_modal").find(".tags");
        initTag(tagElement);
        if(that.defaults.value){
            tagElement.importTags(that.defaults.value, true);
        }
        $("#erp_select_employee_modal").find(".erp_select_employee_modal_ok").off('click').on('click', function () {
            if (that.defaults.onSelected) {
                var tags = tagElement.val();
                tagElement.importTags('', true);
                var data = {};
                data["base"] = tags;
                var acount = "";
                var name = "";
                var aTags = tags.split(',');
                for (var i = 0; i < aTags.length; i++) {
                    acount += aTags[i].split('_')[1] + ",";
                    name += aTags[i].split('_')[0] + ",";
                }
                data["account"] = acount.substring(0, acount.length - 1);
                data["name"] = name.substring(0, name.length - 1);
                data["department"] = that.defaults.departmentNode;
                that.defaults.onSelected(data);
            }
            $("#erp_select_employee_modal").modal("hide");
        });
        loadErpEmployeeList({
            currOrgId: that.defaults.currOrgId,
            multi: that.defaults.multi,//是否允许多选
            modalElement: "erp_select_employee_modal",
            tableElement: "erp_select_employee_table"
        })
        initBaseTreeView("erp_select_employee_tree", "/department/getDepartmentTree",
            {pid: that.defaults.currOrgId}, false, function (objs) {
                objs.on('nodeSelected', function (event, node) {
                    that.defaults.departmentNode = node;
                    loadErpEmployeeList({
                        currOrgId: node.id,//当前组织id
                        multi: that.defaults.multi,//是否允许多选
                        modalElement: "erp_select_employee_modal",
                        tableElement: "erp_select_employee_table"
                    })
                });
            });
    }
    window.erpEmployee = new ErpEmployee();
})(jQuery)