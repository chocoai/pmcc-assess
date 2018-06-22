/**
 * Created by kings on 2018-3-22.
 * 动态表单 操作对应方法
 */
(function ($) {
    var formConfigureUtils = {
        //根据json数据解析出需要列表显示的对象
        parseShowField: function (jsonString) {
            var array = [];
            if (jsonString && jsonString.length > 0) {
                var items = JSON.parse(jsonString);
                $.each(items, function (i, item) {
                    var tempObj = {};
                    tempObj.field = item.name;
                    tempObj.title = item.title;
                    array.push(tempObj);
                })
            }
            return array;
        },

        //获取动态表单html
        getDynamicFormHtml: function (options) {
            var defaluts = {
                formModuleId: undefined,
                readOnly: false,
                jsonValue: undefined,
                success: function () {

                }
            };
            defaluts = $.extend({}, defaluts, options);
            $.ajax({
                url: getContextPath() + "/formConfigure/getDynamicFormHtml",
                type: "post",
                dataType: "json",
                data: {
                    formModuleId: defaluts.formModuleId,
                    readOnly: defaluts.readOnly,
                    jsonValue: defaluts.jsonValue
                },
                success: function (result) {
                    if (result.ret) {
                        defaluts.success(result.data);
                        DatepickerUtils.parse();
                    }
                }
            })
        },

        //加载从表信息列表
        loadDetailInfoList: function (options) {
            var defaluts = {
                readOnly: false,
                formModuleId: undefined,
                fieldList: undefined,
                targetList: undefined,
                targetForm: undefined,
                targetModal: undefined,
                cardView: false,
                foreignKeyName: undefined,
                foreignKeyValue: undefined,
                tableName: undefined,
                beforeEdit: function () {

                },
                beforeView: function () {

                }
            };
            defaluts = $.extend({}, defaluts, options);
            var cols = [];
            var array = FormConfigureUtils.parseShowField(defaluts.fieldList);
            if (array && array.length > 0) {
                $.each(array, function (i, item) {
                    cols.push(item);
                })
            }
            if (!defaluts.readOnly) {
                cols.push({
                    field: 'opt', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a id="btn_edit" class="btn btn-xs btn-success" href="javascript://" ><i class="fa fa-edit">编辑</i></a>';
                        str += '<a id="btn_delete" class="btn btn-xs btn-warning" href="javascript://;" ><i class="fa fa-trash-o"></i>删除</a>';
                        str += '</div>';
                        return str;
                    },
                    events: {
                        'click #btn_edit': function (e, value, row, index) {
                            FormConfigureUtils.editDetailInfo({
                                row: row,
                                targetList: defaluts.targetList,
                                targetForm: defaluts.targetForm,
                                targetModal: defaluts.targetModal,
                                beforeEdit: defaluts.beforeEdit
                            });
                        },
                        'click #btn_delete': function (e, value, row, index) {
                            FormConfigureUtils.deleteDetailInfo({
                                targetList: defaluts.targetList,
                                tableName: defaluts.tableName,
                                tableId: row.id,
                            });
                        }
                    }
                });
            } else {
                cols.push({
                    field: 'opt', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a id="btn_view" class="btn btn-xs btn-success" href="javascript://" ><i class="fa fa-eye">查看</i></a>';
                        str += '</div>';
                        return str;
                    },
                    events: {
                        'click #btn_view': function (e, value, row, index) {
                            FormConfigureUtils.editDetailInfo({
                                row: row,
                                targetList: defaluts.targetList,
                                targetForm: defaluts.targetForm,
                                targetModal: defaluts.targetModal,
                                beforeEdit: defaluts.beforeEdit
                            });
                        }
                    }
                });
            }
            $("#" + defaluts.targetList).bootstrapTable('destroy');
            TableInit(defaluts.targetList, getContextPath() + "/formConfigure/getDetailInfoList", cols, {
                formModuleId: defaluts.formModuleId,
                tableName: defaluts.tableName,
                foreignKeyName: defaluts.foreignKeyName,
                foreignKeyValue: defaluts.foreignKeyValue
            }, {
                showColumns: false,
                showRefresh: false,
                uniqueId: "id",
                cardView: defaluts.cardView,
                search: false
            });
        },

        //新增从表信息
        addDetailInfo: function (options) {
            var defaluts = {
                targetForm: undefined,
                excludeArray: undefined,
                success: function () {

                }
            };
            defaluts = $.extend({}, defaluts, options);
            defaluts.targetForm.clearAll(defaluts.excludeArray);
        },

        //编辑从表信息
        editDetailInfo: function (options) {
            var defaluts = {
                row: undefined,
                excludeArray: undefined,
                targetForm: undefined,
                targetModal: undefined,
                beforeEdit: function () {

                }
            };
            defaluts = $.extend({}, defaluts, options);
            if (defaluts.beforeEdit) {
                defaluts.beforeEdit(defaluts.row);
            }
            var row = defaluts.row;
            defaluts.targetForm.clearAll(defaluts.excludeArray);
            defaluts.targetForm.initForm(row);
            defaluts.targetModal.modal();
        },

        //查看从表信息
        viewDetailInfo: function (options) {
            var defaluts = {
                row: undefined,
                targetForm: undefined,
                targetModal: undefined,
                beforeView: function () {

                }
            };
            defaluts = $.extend({}, defaluts, options);
            if (defaluts.beforeEdit) {
                defaluts.beforeEdit(defaluts.row);
            }
            var row = defaluts.row;
            defaluts.targetForm.clearAll();
            defaluts.targetForm.initForm(row);
            defaluts.targetModal.modal();
        },

        //删除从表信息
        deleteDetailInfo: function (options) {
            var defaluts = {
                targetList: undefined,
                tableName: undefined,
                tableId: undefined,
                success: function () {

                }
            };
            defaluts = $.extend({}, defaluts, options);
            bootbox.confirm("确认要删除么？", function (result) {
                if (result) {
                    Loading.progressShow();
                    $.ajax({
                        url: getContextPath() + "/formConfigure/deleteDetailInfo",
                        type: "post",
                        dataType: "json",
                        data: {tableName: defaluts.tableName, tableId: defaluts.tableId},
                        success: function (result) {
                            Loading.progressHide();
                            if (result.ret) {
                                toastr.success('删除成功');
                                defaluts.targetList.bootstrapTable("refresh");
                            }
                            else {
                                Alert("删除数据失败，失败原因:" + result.errmsg);
                            }
                        },
                        error: function (result) {
                            Loading.progressHide();
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    })
                }
            });
        },

        //保存从表信息
        saveDetailInfo: function (options) {
            var defaluts = {
                targetList: undefined,
                targetForm: undefined,
                targetModal: undefined,
                tableName: undefined,
                formModuleId: undefined,
                foreignKeyName: undefined,
                foreignKeyValue: undefined,
                success: function () {

                }
            };
            defaluts = $.extend({}, defaluts, options);
            if (defaluts.targetForm.valid()) {
                Loading.progressShow();
                var data = {};
                var array = formSerializeArray(defaluts.targetForm);
                array[defaluts.foreignKeyName] = defaluts.foreignKeyValue;
                data.formData = JSON.stringify(array);
                data.tableId = array.id;
                data.tableName = defaluts.tableName;
                data.formModuleId = defaluts.formModuleId;
                $.ajax({
                    url: getContextPath() + "/formConfigure/saveDetailInfo",
                    type: "post",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        Loading.progressHide();
                        if (result.ret) {
                            toastr.success('保存成功');
                            defaluts.targetList.bootstrapTable("refresh");
                            defaluts.targetModal.modal('hide');
                        }
                        else {
                            Alert("保存数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        Loading.progressHide();
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            }
        },

        //循环展示数据
        getData: function () {
            var tables = $('div[data-name="singleTable"]');
            if (tables) {//循环验证
                for (var i = 0; i < tables.length; i++) {
                    if (!tables.eq(i).find("form").valid()) {
                        return false;
                    }
                }
            }
            var data = {};
            data.primaryId = $("#primaryId").val();
            data.phaseId = $("#phaseId").val();
            data.primaryTableName = $("#primaryTableName").val();
            data.singelFormList = [];
            data.multipleFormList = [];
            if (tables) {//循环获取数据
                for (var i = 0; i < tables.length; i++) {
                    var dataTable = {};
                    var form = tables.eq(i).find("form");
                    dataTable.tableName = tables.eq(i).attr("data-table-name");
                    dataTable.formModuleId = tables.eq(i).attr("data-form-list-id");
                    dataTable.formData = formSerializeArray(form);
                    dataTable.tableId = dataTable.formData.id;
                    data.singelFormList.push(dataTable);
                }
            }
            tables = $('div[data-name="multipleTable"]');
            if (tables) {//循环获取数据
                for (var i = 0; i < tables.length; i++) {
                    var dataTable = {};
                    var form = tables.eq(i).find("form");
                    dataTable.tableName = tables.eq(i).attr("data-table-name");
                    dataTable.formModuleId = tables.eq(i).attr("data-form-list-id");
                    dataTable.formData = formSerializeArray(form);
                    dataTable.tableId = dataTable.formData.id;
                    data.multipleFormList.push(dataTable);
                }
            }
            return data;
        },

        //获取字段的json数据
        getFieldJson: function (options) {
            var defaluts = {
                formModuleId: undefined,
                readOnly: false,
                jsonValue: undefined,
                success: function () {

                }
            };
            defaluts = $.extend({}, defaluts, options);
            $.ajax({
                url: getContextPath() + "/formConfigure/getDynamicFormHtml",
                type: "post",
                dataType: "json",
                data: {
                    formModuleId: defaluts.formModuleId,
                    readOnly: defaluts.readOnly,
                    jsonValue: defaluts.jsonValue
                },
                success: function (result) {
                    if (result.ret) {
                        defaluts.success(result.data);
                    }
                }
            })
        }

    };
    window.FormConfigureUtils = formConfigureUtils;
})(jQuery)