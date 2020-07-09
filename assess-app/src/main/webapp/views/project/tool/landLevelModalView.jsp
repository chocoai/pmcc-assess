<%@ page contentType="text/html;charset=UTF-8" language="java" %>




<div id="tool_select_land_level_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 70%">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">基准地价</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class=" col-sm-1">省</label>
                                            <div class=" col-sm-3">
                                                <select name="province"
                                                        class="form-control input-full  ">
                                                </select>
                                            </div>
                                            <label class=" col-sm-1">市</label>
                                            <div class=" col-sm-3">
                                                <select name="city" class="form-control input-full  ">
                                                </select>
                                            </div>
                                            <label class=" col-sm-1">县</label>
                                            <div class=" col-sm-3">
                                                <select name="district"
                                                        class="form-control input-full  ">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class=" col-sm-1">乡镇/街道</label>
                                            <div class=" col-sm-3">
                                                <input placeholder="乡镇/街道" class="form-control input-full" name="townShipName"
                                                       type="text">
                                            </div>
                                            <button type="button" class="btn btn-success btn-sm"
                                                    onclick="assessLandLevelTool.loadLandLevelList(null);">
                                                查询 <i class="fa fa-search" aria-hidden="true"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="col-sm-8 pull-left">
                                            <table class="table table-bordered" id="tool_select_land_level_list">
                                                <!-- cerare document add ajax data-->
                                            </table>
                                        </div>

                                        <div class="col-sm-4 pull-right">
                                            <ul id="tool_select_land_level_list_tree" class="ztree"></ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>

        </div>
    </div>
</div>


<script>
    /**
     * Created by kings on 2018-4-18.
     * 选择土地级别 工具方法
     */
    (function ($) {
        var AssessLandLevelTool = function () {
        };
        AssessLandLevelTool.prototype.success = undefined;
        AssessLandLevelTool.prototype.box = $('#tool_select_land_level_modal');
        AssessLandLevelTool.prototype.table = $('#tool_select_land_level_list');
        AssessLandLevelTool.prototype.tree = $('#tool_select_land_level_list_tree');

        //加载列表数据
        AssessLandLevelTool.prototype.loadLandLevelList = function (select) {
            if (!select) {
                var frm = AssessLandLevelTool.prototype.box.find("form").first();
                var data = formSerializeArray(frm);
//                data.status = "finish";
                data.status = "";
                select = data;
            }
            var cols = [];
            cols.push({
                field: 'provinceName', title: '区域', formatter: function (value, row, index) {
                    var areaFullName = AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
                    if (row.townShipName) {
                        areaFullName = areaFullName + row.townShipName;
                    }
                    return areaFullName;
                }
            });
            cols.push({
                field: 'valuationDate', title: '估价期日', formatter: function (value, row, index) {
                    return formatDate(value);
                }
            });
            cols.push({
                field: 'executionTime', title: '执行时间', formatter: function (value, row, index) {
                    return formatDate(value);
                }
            });
            cols.push({field: 'fileViewName', title: '附件'});
            cols.push({
                field: 'opt', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-warning" href="javascript://" onclick="assessLandLevelTool.onLoadTree(' + index + ');"><i class="fa fa-tree fa-white"></i>选择</a>';
                    str += '</div>';
                    return str;
                }
            });
            var table = AssessLandLevelTool.prototype.table;
            table.bootstrapTable('destroy');
            TableInit(table, getContextPath() + "/dataLandLevel/getDataLandLevelListVos", cols, select, {
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
        };

        //弹出选择土地级别窗口
        AssessLandLevelTool.prototype.select = function (options) {
            var that = this;
            var target = AssessLandLevelTool.prototype.box;
            var data = {province: options.province, city: options.city, district: options.district, status: 'finish'};
            assessLandLevelTool.loadLandLevelList(data);

            AssessCommon.initAreaInfo({
                provinceTarget: target.find("select[name='province']"),
                cityTarget: target.find("select[name='city']"),
                districtTarget: target.find("select[name='district']"),
                provinceValue: data.province,
                cityValue: data.city,
                districtValue: data.district
            });
            AssessLandLevelTool.prototype.success = options.success;
            target.modal('show');
        };

        //选择树
        AssessLandLevelTool.prototype.onLoadTree = function (index) {
            var row = AssessLandLevelTool.prototype.table.bootstrapTable('getData')[index];
            var zTreeObj = undefined;

            var setting = {
                //页面上的显示效果
                view: {
                    expandSpeed: "slow",
                    fontCss: {color: "red"}
                },
                check: {
                    enable: false
                },
                callback: {
                    onClick: assessLandLevelTool.onSelected
                },
                data: {
                    key: {
                        name: "name"
                    },
                    simpleData: {//json 数据必须设置
                        enable: true,//true / false 分别表示 使用 / 不使用 简单数据模式
                        idKey: "id",
                        pIdKey: "pid",
                        rootPId: 0
                    }
                }
            };
            $.ajax({
                url: getContextPath() + "/dataLandLevel/getDataLandLevelDetailTree",
                type: "get",
                dataType: "json",
                data: {landLevelId: row.id},
                success: function (result) {
                    if (result.ret) {
                        try {
                            $.fn.zTree.destroy();
                        } catch (e) {
                        }
                        zTreeObj = $.fn.zTree.init(AssessLandLevelTool.prototype.tree, setting, result.data);
                        zTreeObj.expandAll(true);
                    } else {
                        Alert("获取数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            });

        };

        AssessLandLevelTool.prototype.treeExpandAll = function (flag) {
            try {
                var zTree = $.fn.zTree.getZTreeObj(AssessLandLevelTool.prototype.tree.prop("id"));
                zTree.expandAll(flag);
            } catch (e) {
            }
        };

        AssessLandLevelTool.prototype.treeRefresh = function () {
            try {
                var zTree = $.fn.zTree.getZTreeObj(AssessLandLevelTool.prototype.tree.prop("id"));
                zTree.refresh();
            } catch (e) {
            }
        };


        //选择土地级别
        AssessLandLevelTool.prototype.onSelected = function (event, treeId, treeNode) {
            if (AssessLandLevelTool.prototype.success) {
                AssessLandLevelTool.prototype.success(treeNode);
            }
            AssessLandLevelTool.prototype.box.modal('hide');
        };
        window.assessLandLevelTool = new AssessLandLevelTool();
    })(jQuery);
</script>