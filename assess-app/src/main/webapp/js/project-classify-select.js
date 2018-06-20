/**
 * Created by kings on 2018-4-11.
 * 数据字典选择工具
 */

(function ($) {
    var AssessProjectClassify = function () {
        this.defaults = {
            modalName: "数据选择",
            key: "",//获取key值 以下的数据
            pid: 0,//获取pid 以下的数据
            filterKey: [],//过滤值
            multi: false,//是否允许多选
            onSelected: function (nodes) {

            }
        };
        this.setting = {
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pid",
                    rootPId: 0
                }
            },
            async: {
                enable: true,
                url: getContextPath() + "/baseProjectClassify/getProjectClassifyTree",
                autoParam: ["id=pid"],
                otherParam: {
                    "filterKey": "1"
                }
            },
            // 回调函数
            callback: {
                onClick: function (event, treeId, treeNode, clickFlag) {

                }
            }
        };
        this.zTreeObj = {};
    }
    AssessProjectClassify.prototype = {
        select: function (options) {
            this.defaults = $.extend({}, this.defaults, options);
            var that = this;
            var target = $("#assess_select_project_classify_modal");
            if (target.length > 0) {
                $("#assess_select_project_classify_modal").remove();
            }
            if (that.defaults.multi) {
                that.setting.check = {
                    enable: true,
                    autoCheckTrigger: true,
                    chkboxType: {"Y": "", "N": ""}
                };
            }
            var html = '<div id="assess_select_project_classify_modal" class="modal fade bs-example-modal-sm" data-backdrop="static" tabindex="1" role="dialog" aria-hidden="true">';
            html += '<div class="modal-dialog">';
            html += '<div class="modal-content">';
            html += '<div class="modal-header">';
            html += '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><spanaria-hidden="true">&times;</span></button>';
            html += '<h3 class="modal-title">' + that.defaults.modalName + '</h3>';
            html += '</div>';
            html += '<div class="modal-body">';
            html += '<div class="row">';
            html += '<div class="col-md-12">';
            html += '<div class="input-group">';
            html += '<input type="text" id="assess_select_project_classify_query_name" class="form-control">';
            html += '<span class="input-group-btn">';
            html += '<a href="javascript://" onclick="assessProjectClassify.query()" class="btn btn-primary">查询</a>';
            html += '</span>';
            html += '</div>';
            html += '<ul id="assess_select_project_classify_ztree" class="ztree"></ul>';
            html += '</div>';
            html += '</div>';
            html += '</div>';
            html += '<div class="modal-footer">';
            html += '<button type="button" data-dismiss="modal" class="btn btn-default">';
            html += '取消';
            html += '</button>';
            html += '<button type="button" class="btn btn-primary" onclick="assessProjectClassify.getSelected()">';
            html += '确定';
            html += '</button>';
            html += '</div>';
            html += '</div>';
            html += '</div>';
            html += '</div>';
            $(document.body).append(html);
            //console.log(that);

            that.init();
        },

        //查询
        query: function () {
            var queryName = $("#assess_select_project_classify_query_name").val();
            if (queryName) {
                $.ajax({
                    url: getContextPath() + "/baseProjectClassify/queryProjectClassifyTree",
                    data: {
                        name: queryName,
                        key: this.defaults.key,
                        pid: this.defaults.pid,
                        filterKey: this.defaults.filterKey
                    },
                    type: "post",
                    dataType: "json",
                    success: function (result) {
                        $.fn.zTree.init($("#assess_select_project_classify_ztree"), this.setting, result);
                    }
                })
            } else {
                this.init();
            }
        },

        //获取选择后的节点数据
        getSelected: function () {
            var nodes;
            if (this.multi) {
                nodes = this.zTreeObj.getCheckedNodes(true);
            } else {
                nodes = this.zTreeObj.getSelectedNodes();
            }
            if (this.defaults.onSelected) {
                this.defaults.onSelected(nodes);
            }
            $("#assess_select_project_classify_modal").modal("hide");
        },

        init: function () {
            var that = this;
            $.ajax({
                url: getContextPath() + "/baseProjectClassify/getProjectClassifyByKey",
                data: {
                    key: that.defaults.key
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    that.zTreeObj = $.fn.zTree.init($("#assess_select_project_classify_ztree"), that.setting, result);
                    var rootNode = that.zTreeObj.getNodes()[0];
                    that.zTreeObj.expandNode(rootNode, true, false, true);
                    $("#assess_select_project_classify_modal").modal();
                }
            })
        }
    };
    window.assessProjectClassify = new AssessProjectClassify();
})(jQuery);
