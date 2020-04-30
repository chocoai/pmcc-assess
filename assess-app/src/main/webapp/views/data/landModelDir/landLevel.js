/**
 * Created by zch on 2019-11-1.
 */

var ue = undefined;

if ($("#landDefinition").size() != 0) {
    ue = UE.getEditor('landDefinition', {
        toolbars: [
            ['source', 'autotypeset', 'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc']
        ],
        zIndex: 11009,
        initialFrameHeight: 120,
        elementPathEnabled: false,//是否启用元素路径，默认是true显示
        wordCount: false, //是否开启字数统计
        autoHeightEnabled: false,
        autoFloatEnabled: true
    });
}


var landLevel = {};

landLevel.run = function (data, url, type, callback, funParams, errorCallback) {
    Loading.progressShow();
    $.ajax({
        type: type,
        url: getContextPath() + url,
        data: data,
        success: function (result) {
            Loading.progressHide();
            if (result.ret) {
                if (funParams) {
                    if (funParams == 'save') {
                        notifySuccess("成功", "保存数据成功!");
                    }
                    if (funParams == 'add') {
                        notifySuccess("成功", "添加数据成功!");
                    }
                    if (funParams == 'update') {
                        notifySuccess("成功", "修改数据成功!");
                    }
                    if (funParams == 'query') {
                        notifySuccess("成功", "查询数据成功!");
                    }
                    if (funParams == 'delete') {
                        notifySuccess("成功", "删除数据成功!");
                    }
                }
                if (callback) {
                    callback(result.data);
                }
            } else {
                if (result.errmsg) {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                } else {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                }
                if (errorCallback) {
                    errorCallback();
                }
            }
        },
        error: function (result) {
            Loading.progressHide();
            if (result.errmsg) {
                AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
            } else {
                AlertError("错误", "调用服务端方法失败，失败原因:" + result);
            }
        }
    });
};
landLevel.ajaxServerFun = function (data, url, type, callback, funParams, errorCallback) {
    var deleteParams = false;
    if (funParams) {
        if (funParams == 'delete') {
            deleteParams = true;
        }
    }
    if (deleteParams) {
        AlertConfirm("是否确认删除当前数据", "删除相应的数据后将不可恢复", function (flag) {
            landLevel.run(data, url, type, callback, funParams, errorCallback);
        });
    } else {
        landLevel.run(data, url, type, callback, funParams, errorCallback);
    }
};

landLevel.ajaxServerMethod = function (data, url, type, callback, errorCallback) {
    landLevel.ajaxServerFun(data, url, type, callback, null, errorCallback);
};

landLevel.ajaxFileUploadCommon = function (data, fileElementId, url, callback, flag) {
    Loading.progressShow();
    $.ajaxFileUpload({
        type: "POST",
        url: getContextPath() + url,
        data: data,//要传到后台的参数，没有可以不写
        secureuri: false,//是否启用安全提交，默认为false
        fileElementId: fileElementId,//文件选择框的id属性
        dataType: 'json',//服务器返回的格式
        async: false,
        success: function (result) {
            Loading.progressHide();
            if (result.ret) {
                if (callback) {
                    callback(result.data);
                }
                if (flag) {

                } else {
                    AlertSuccess("导入情况", result.data);
                }
            } else {
                if (result.errmsg) {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                } else {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                }
            }
        },
        error: function (result, status, e) {
            Loading.progressHide();
            if (result.errmsg) {
                AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
            } else {
                AlertError("错误", "调用服务端方法失败，失败原因:" + result);
            }
        }
    });
};

landLevel.ajaxFileUploadCommonFun = function (data, fileElementId, url, callback) {
    landLevel.ajaxFileUploadCommon(data, fileElementId, url, callback, true);
};

landLevel.admin = "admin";

var currentLandLevelId = 0;

landLevel.showFile = function (target, tableName, id, deleteFlag, editFlag, signatureFlag, fieldsName) {
    FileUtils.getFileShows({
        target: target,
        formData: {
            tableName: tableName,
            tableId: id,
            fieldsName: fieldsName
            // projectId: id
        },
        signatureFlag: signatureFlag,
        deleteFlag: deleteFlag,
        editFlag: editFlag
    })
};


landLevel.fileUpload = function (target, tableName, id, deleteFlag, editFlag, fieldsName) {
    if (!id) {
        id = 0;
    }
    FileUtils.uploadFiles({
        target: target,
        disabledTarget: "btn_submit",
        formData: {
            tableName: tableName,
            tableId: id,
            fieldsName: fieldsName
            // projectId: id
        },
        deleteFlag: deleteFlag,
        editFlag: editFlag
    });
    // FileUtils.uploadFiles({
    //     target: target,
    //     disabledTarget: "btn_submit",
    //     onUpload: function (file) {
    //         var formData = {
    //             fieldsName: target,
    //             tableName: tableName,
    //             tableId: id
    //         };
    //         return formData;
    //     }, onUploadComplete: function (result, file) {
    //
    //     },
    //     deleteFlag: true
    // });
};


landLevel.config = {
    table: $("#tb_FatherList"),
    box: $("#divBoxFather"),
    frmQuery: $("#frmQuery"),

    land_level_detail_list: $("#land_level_detail_list"),
    land_level_detail_modal: $("#land_level_detail_modal"),
    land_level_detail_tree_modal: $("#treeLandLevelDetailListModal"),
    tree: $("#treeLandLevelDetail"),


    achievementTable: $("#achievementTable"),
    achievementBox: $("#achievementBox"),
    achievementBoxDetail: $("#achievementBoxDetail"),
    achievementFrm: $("#achievementFrm"),


    dataAllocationCorrectionCoefficientVolumeRatioDetailTableBox: $("#dataAllocationCorrectionCoefficientVolumeRatioDetailTableBox"),
    dataAllocationCorrectionCoefficientVolumeRatioDetailTable: $("#dataAllocationCorrectionCoefficientVolumeRatioDetailTable"),
    dataAllocationCorrectionCoefficientVolumeRatioDetailBox: $("#dataAllocationCorrectionCoefficientVolumeRatioDetailBox"),
    dataAllocationCorrectionCoefficientVolumeRatioDetailFrm: $("#dataAllocationCorrectionCoefficientVolumeRatioDetailFrm")
};


//--------------------------------------------土地 base start-----------------------------------//
landLevel.importDataLand = function () {
    landLevel.ajaxFileUploadCommon({}, 'ajaxFileUploadDataLand', "/dataLandLevel/importData", function (data) {
        landLevel.config.table.bootstrapTable('refresh');
    }, false);
};
landLevel.removeData = function (id) {
    landLevel.ajaxServerFun({id: id}, "/dataLandLevel/deleteDataLandLevelById", "post", function () {
        landLevel.config.table.bootstrapTable('refresh');
    }, "delete");
};
landLevel.initDataForm = function (data) {
    var target = landLevel.config.box;
    var frm = target.find("form");
    frm.clearAll();
    frm.initForm(data);
    if (data.landDefinition) {
        setTimeout(function () {
            ue.setContent(data.landDefinition, false);
        }, 500);
    }
    var files = ['uploadFile'];
    $.each(files, function (i, item) {
        landLevel.showFile(item, AssessDBKey.DataLandLevel, data.id, true, true, item);
        landLevel.fileUpload(item, AssessDBKey.DataLandLevel, data.id, true, item);
    });
    AssessCommon.initAreaInfo({
        provinceTarget: frm.find("select[name='province']"),
        cityTarget: frm.find("select[name='city']"),
        districtTarget: frm.find("select[name='district']"),
        provinceValue: data.province,
        cityValue: data.city,
        districtValue: data.district
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareLandCertificateType, data.landRightType, function (html, data) {
        frm.find("select[name='landRightType']").empty().html(html).trigger('change');
    });
};
landLevel.saveData = function () {
    var target = landLevel.config.box;
    var frm = target.find("form");
    if (!frm.valid()) {
        return false;
    }
    var data = formSerializeArray(frm);
    data.status = 'draft';
    data.landDefinition = ue.getContent();
    landLevel.ajaxServerMethod({formData: JSON.stringify(data)}, "/dataLandLevel/saveAndUpdateDataLandLevel", "post", function () {
        AlertSuccess("成功", "数据已成功保存到数据库");
        target.modal('hide');
        landLevel.config.table.bootstrapTable('refresh');
    });
};
landLevel.editData = function (id) {
    var target = landLevel.config.box;
    var data = landLevel.config.table.bootstrapTable('getRowByUniqueId', id);
    this.initDataForm(data);
    target.modal('show');
};
//--------------------------------------------土地 base end-----------------------------------//

//---------------------------土地级别start---------------------------------------//
landLevel.loadLandLevelList = function (select) {
    if (select == undefined || select == null) {
        select = {};
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
    cols.push({field: 'landRightTypeName', title: '权利类型'});
    cols.push({
        field: 'valuationDate', title: '估价期日', formatter: function (value, row, index) {
            return formatDate(value);
        }
    });
    cols.push({
        field: 'releaseDate', title: '发布日期', formatter: function (value, row, index) {
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
        field: 'status', title: '状态', formatter: function (value, row, index) {
            var str = "";
            switch (value) {
                case "runing": {
                    str = "<span class='label label-info'>" + "进行中" + "</span>";
                    break;
                }
                case "finish": {
                    str = "<span class='label label-success'>" + "已完成" + "</span>";
                    break;
                }
                case "draft": {
                    str = "<span class='label label-warning'>" + "草稿" + "</span>";
                    break;
                }
            }
            return str;
        }
    });
    cols.push({field: 'creatorName', title: '创建人'});
    var elShow = landLevel.getElShow();
    var formData = formParams("frmQuery");
    cols.push({
        field: 'id', title: '操作', formatter: function (value, row, index) {
            var str = '<div class="btn-margin">';
            var editHtml = '<button type="button" onclick="landLevel.editData(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
            editHtml += '<i class="fa fa-pen"></i>';
            editHtml += '</button>';
            if (elShow) {
                str += editHtml;
                str += '<button type="button" onclick="landLevel.removeData(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
            }
            if (!elShow) {
                if (formData.permission) {
                    if (formData.permission == row.creator) {
                        str += editHtml;
                    }
                }
            }
            str += '<button type="button" onclick="landLevel.treeLandLevelDetailListModal(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="基准地价">';
            str += '<i class="fa fa-search"></i>';
            str += '</button>';
            return str;
        }
    });

    landLevel.config.table.bootstrapTable('destroy');
    var query = formSerializeArray(landLevel.config.frmQuery);
    //只获取有值的对象属性
    var tempObj = Object.keys(query);
    for (var i = 0; i < tempObj.length; i++) {
        var key = tempObj[i];
        var value = query[key];
        //判断是否存在值
        if (value) {
            select[key] = value;//动态添加属性
        }
    }
    var method = {
        showColumns: false,
        showRefresh: false,
        search: false,
        onLoadSuccess: function () {
            $('.tooltips').tooltip();
        }
    };
    TableInit(landLevel.config.table, getContextPath() + "/dataLandLevel/getDataLandLevelListVos", cols, select, method);
};


landLevel.treeLandLevelDetailListModal = function (id) {
    currentLandLevelId = id;
    var box = landLevel.config.land_level_detail_tree_modal;
    this.loadTree(id);
    this.initLandLevelDetailForm({landLevelId: id, pid: 0});
    box.modal();
    var form = box.find("form").first();
    if (form.size() != 0) {
        var data = {
            price: '',
            muPrice: '',
            volumeRate: '',
            legalAge: '',
            landAcquisitionProportion: '',
            mainStreet: '',
            floorPrice: ''
        };
        form.initForm(data);
        landLevel.initLandLevelDetailBaseForm(form, data);
    }
    var elShow = landLevel.getElShow();
    if (!elShow) {
        box.find("a").each(function (i, a) {
            if ($(a).attr("data-permission")) {
                $(a).hide();
            }
        });
    }

};

//土地级别赋值
landLevel.initLandLevelDetailForm = function (data) {
    var box = landLevel.config.land_level_detail_modal;
    var frm = box.find("form");
    this.initLandLevelDetailBaseForm(frm, data);
};

landLevel.initLandLevelDetailBaseForm = function (frm, data) {
    var item = formSerializeArray(frm);
    frm.clearAll().initForm(data);
    if (!data.landLevelId) {
        data.landLevelId = item.landLevelId;
    }
    var detailNameList = $("#LandLevelDetailNameList");
    if (data.pid) {
        AssessCommon.loadDataDicByKey(AssessDicKey.DATA_LAND_LEVEL_ROMAN, null, function (html, data) {
            html = '';
            html += '<option value="" selected>-请选择-</option>';
            $.each(data, function (i, item) {
                html += "<option value='" + item.name + "'>" + item.name + "</option>";
            });
            detailNameList.empty().html(html).trigger('change');
        });
    } else {
        AssessCommon.loadDataDicByKey(AssessDicKey.DATA_LAND_LEVEL_CLASSIFY, null, function (html, data) {
            html = '';
            html += '<option value="" selected>-请选择-</option>';
            $.each(data, function (i, item) {
                html += "<option value='" + item.name + "'>" + item.name + "</option>";
            });
            detailNameList.empty().html(html).trigger('change');
        });
    }
    frm.validate();
};

//新增土地级别
landLevel.addLandLevelDetail = function () {
    var box = landLevel.config.land_level_detail_modal;
    var zTree = $.fn.zTree.getZTreeObj(landLevel.config.tree.prop("id"));
    // var nodes = zTree.getSelectedNodes();
    var nodes = zTree.getCheckedNodes(true);
    var data = {pid: 0};
    if (nodes.length > 1) {
        notifyInfo('提示', '只能勾选一个节点');
        return false;
    }
    if (nodes.length == 1) {
        data.pid = nodes[0].id;
    }
    landLevel.initLandLevelDetailForm(data);
    box.modal();
};


landLevel.getDataLandLevelDetailById = function (id, callback) {
    landLevel.ajaxServerMethod({id: id}, '/dataLandLevel/getDataLandLevelDetailById', "get", callback);
};

landLevel.deleteLandLevelDetail = function (id, callback) {
    landLevel.ajaxServerFun({id: id}, '/dataLandLevel/removeDataLandLevelDetail', "post", callback, "delete");
};


landLevel.saveLandLevelDetail = function () {
    var box = landLevel.config.land_level_detail_modal;
    var frm = box.find("form");
    if (!frm.valid()) {
        return false;
    }
    var data = formSerializeArray(frm);
    data.landLevelId = currentLandLevelId;
    landLevel.ajaxServerMethod(data, '/dataLandLevel/saveAndUpdateDataLandLevelDetail', "post", function () {
        landLevel.loadTree(data.landLevelId);
        box.modal('hide');
    });
};

landLevel.importLandLevelDetail = function (flag) {
    var box = landLevel.config.land_level_detail_modal;
    var frm = box.find("form");
    var landLevelDetail = formSerializeArray(frm);
    var target = $('#ajaxFileUploadLandLevelDetail');
    if (flag) {
        target.val('').trigger('click');
        return flag;
    }
    landLevel.ajaxFileUploadCommon({landLevelId: landLevelDetail.landLevelId}, target.prop("id"), "/dataLandLevel/importLandLevelDetail", function () {
        landLevel.loadTree(landLevelDetail.landLevelId);
    }, false);
};


/**
 * 配合 addHoverDom()
 * @param treeId
 * @param treeNode
 */
function removeHoverDom(treeId, treeNode) {
    $("#addBtn_" + treeNode.tId).unbind().remove();
    $("#editBtn_" + treeNode.tId).unbind().remove();
    $("#deleteBtn_" + treeNode.tId).unbind().remove();
    // $("#factorBtn_" + treeNode.tId).unbind().remove();
    // $("#coefficientBtn_" + treeNode.tId).unbind().remove();
}

function zTreeOnClick(event, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj(landLevel.config.tree.prop("id"));
    var box = landLevel.config.land_level_detail_tree_modal;
    var form = box.find("form").first();
    landLevel.getDataLandLevelDetailById(treeNode.id, function (data) {
        landLevel.initLandLevelDetailBaseForm(form, data);
    });
    zTree.checkNode(treeNode, true, true);
}

function zTreeOnRemove() {
    var zTree = $.fn.zTree.getZTreeObj(landLevel.config.tree.prop("id"));
    // var nodes = zTree.getSelectedNodes();
    var nodes = zTree.getCheckedNodes(true);
    if (nodes.length == 0) {
        notifyInfo('提示', '至少勾选一个节点');
        return false;
    }
    var ids = [];
    $.each(nodes, function (i, node) {
        ids.push(node.id);
    });
    landLevel.deleteLandLevelDetail(ids.join(","), function () {
        $.each(nodes, function (i, node) {
            zTree.removeNode(node);
        });
    });
}


function zTreeOnEdit() {
    var zTree = $.fn.zTree.getZTreeObj(landLevel.config.tree.prop("id"));
    // var nodes = zTree.getSelectedNodes();
    var nodes = zTree.getCheckedNodes(true);
    if (nodes.length != 1) {
        notifyInfo('提示', '只勾选一个节点');
        return false;
    }
    var box = landLevel.config.land_level_detail_modal;
    var treeNode = nodes[0];
    landLevel.getDataLandLevelDetailById(treeNode.id, function (data) {
        data.tId = treeNode.tId;
        switch (treeNode.pid) {
            case 0: {
                landLevel.initLandLevelDetailForm(data, "parent");
                break
            }
            default: {
                landLevel.initLandLevelDetailForm(data, "child");
                break;
            }
        }
        box.modal();
    });
}

/**
 * 非常重要的添加操作dom
 * @param treeId
 * @param treeNode
 */
function addHoverDom(treeId, treeNode) {
    var element = $("#" + treeNode.tId + "_span"); //获取节点信息
    if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
    var html = "";
    var elShow = landLevel.getElShow();

    html += "<span class=' button add' id='addBtn_" + treeNode.tId + "' title='添加数据'>" + "</span>";//定义添加按钮
    html += "<span class=' button edit' id='editBtn_" + treeNode.tId + "' title='编辑数据'>" + "</span>";//定义编辑按钮
    html += "<span class=' button remove' id='deleteBtn_" + treeNode.tId + "' title='删除数据'>" + "</span>";//定义删除按钮


    // html += "<span style='margin-left:5px;' class='  ' id='factorBtn_" + treeNode.tId + "' title='所关联土地因素数据'>" + "土地因素数据" + "<i class=''></i>" + "</span>";//所关联土地因素数据
    // html += "<span style='margin-left:5px;' class='  ' id='coefficientBtn_" + treeNode.tId + "' title='所关联土地容积率数据'>" + "土地容积率数据" + "<i class=''></i>" + "</span>";//所关联土地容积率数据


    element.after(html); //加载添加按钮

    var addBtn = $("#addBtn_" + treeNode.tId);
    var editBtn = $("#editBtn_" + treeNode.tId);
    var deleteBtn = $("#deleteBtn_" + treeNode.tId);
    // var factorBtn = $("#factorBtn_" + treeNode.tId);
    // var coefficientBtn = $("#coefficientBtn_" + treeNode.tId);
    if (!elShow) {
        var array = [addBtn, editBtn, deleteBtn];
        $.each(array, function (i, item) {
            item.hide();
        })
    }
    var box = landLevel.config.land_level_detail_modal;
    //绑定添加事件，并定义添加操作
    if (addBtn) addBtn.bind("click", function (e) {
        switch (treeNode.pid) {
            case 0: {
                landLevel.initLandLevelDetailForm({pid: treeNode.id, tId: treeNode.tId}, "child");
                box.modal();
                break
            }
            default: {
                notifySuccess('成功', '不能在此层级添加子级!');
                break;
            }
        }
    });
    //编辑
    if (editBtn) editBtn.bind("click", function (e) {
        landLevel.getDataLandLevelDetailById(treeNode.id, function (data) {
            data.tId = treeNode.tId;
            switch (treeNode.pid) {
                case 0: {
                    landLevel.initLandLevelDetailForm(data, "parent");
                    break
                }
                default: {
                    landLevel.initLandLevelDetailForm(data, "child");
                    break;
                }
            }
            box.modal();
        });
    });
    //删除
    if (deleteBtn) deleteBtn.bind("click", function (e) {
        var zTree = $.fn.zTree.getZTreeObj(landLevel.config.tree.prop("id"));
        landLevel.deleteLandLevelDetail(treeNode.id, function () {
            zTree.removeNode(treeNode);
        });
    });
    // if (factorBtn) factorBtn.bind("click", function (e) {
    //     landLevel.showDataLandDetailAchievementDetail(treeNode.id);
    // });

    // if (coefficientBtn) coefficientBtn.bind("click", function (e) {
    //     landLevel.showDataAllocationCorrectionCoefficientVolumeRatioDetail(treeNode.id);
    // });
}


landLevel.loadTree = function () {
    // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
    var setting = {
        //页面上的显示效果
        view: {},
        check: {
            enable: true,
            chkStyle: "checkbox",
            chkboxType: {"Y": "", "N": ""}//必须设为null ,这样可以真正意义上让复选框不影响父级和子级,哪个被点击了就勾选哪个
        },
        callback: {
            onClick: zTreeOnClick
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
    landLevel.ajaxServerMethod({landLevelId: currentLandLevelId}, "/dataLandLevel/getDataLandLevelDetailTree", "get", function (data) {
        var zTreeObj = null;
        zTreeObj = $.fn.zTree.getZTreeObj(landLevel.config.tree.prop("id"));
        if (zTreeObj != null) {
            zTreeObj.destroy();
        }
        zTreeObj = $.fn.zTree.init(landLevel.config.tree, setting, data);
        zTreeObj.expandAll(true);
    });
};

/**
 * 展开或者收缩
 * @param flag
 */
landLevel.treeExpandAll = function (flag) {
    var zTree = $.fn.zTree.getZTreeObj(landLevel.config.tree.prop("id"));
    zTree.expandAll(flag);
};

/**
 * 清除子数据 clear child data
 * @param _this
 */
landLevel.clearNodeChild = function (_this) {
    var zTreeObj = $.fn.zTree.getZTreeObj(landLevel.config.tree.prop("id"));
    var nodes = zTreeObj.getCheckedNodes(true);
    if (nodes.length == 0) {
        notifyInfo('提示', '勾选至少一个节点');
        return false;
    }
    var ids = [];
    $.each(nodes, function (i, node) {
        ids.push(node.id);
    });
    landLevel.ajaxServerMethod({id: ids.join(",")}, "/dataLandLevel/clearNodeChildDataLandLevelDetail", "post", function () {
        notifyInfo('提示', '清除成功');
        $.each(nodes, function (i, treeNode) {
            zTreeObj.checkNode(treeNode, false, true);
        });
    });
};

/**
 * 刷新
 */
landLevel.treeRefresh = function () {
    var zTree = $.fn.zTree.getZTreeObj(landLevel.config.tree.prop("id"));
    // zTree.refresh();
    landLevel.loadTree();
};

/**
 * 全选
 */
landLevel.treeCheckAllNodes = function (_this) {
    var btn = $(_this);
    var value = btn.attr("data-value");
    var treeObj = $.fn.zTree.getZTreeObj(landLevel.config.tree.prop("id"));
    if (value) {
        treeObj.checkAllNodes(false);
        btn.attr("data-value", "");
    } else {
        treeObj.checkAllNodes(true);
        btn.attr("data-value", "yes");
    }
};
//---------------------------土地级别 end---------------------------------------//


//------容积率 method start--------//

landLevel.showDataAllocationCorrectionCoefficientVolumeRatioDetail = function () {
    var box = landLevel.config.land_level_detail_modal;
    var zTree = $.fn.zTree.getZTreeObj(landLevel.config.tree.prop("id"));
    var nodes = zTree.getSelectedNodes();
    if (nodes.length != 1) {
        notifyInfo('提示', '请选择一个节点');
        return false;
    }
    var treeNode = nodes[0];
    landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTableBox.find("input[name='levelDetailId']").val(treeNode.id);
    landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTableBox.modal("show");
    landLevel.showDataHousePriceIndexDetailList(treeNode.id);
};

landLevel.showDataAllocationCorrectionCoefficientVolumeRatioDetailBox = function () {
    landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailFrm.clearAll();
    var levelDetailId = landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTableBox.find("input[name='levelDetailId']").val();
    landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailFrm.find('input[name=levelDetailId]').val(levelDetailId);
};

landLevel.deleteDataAllocationCorrectionCoefficientVolumeRatioDetail = function (index) {
    var row = landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTable.bootstrapTable('getData')[index];
    landLevel.ajaxServerFun({_method: "DELETE"}, '/dataLandLevelDetailVolume/delete/' + row.id, "post", function () {
        landLevel.showDataHousePriceIndexDetailList(row.levelDetailId);
    }, "delete");
};

landLevel.importDataAllocationCorrectionCoefficientVolumeRatio = function (flag) {
    var target = $('#ajaxFileUploadLandLevelDetailCoefficientVolumeRatio');
    var levelDetailId = "";
    var zTreeObj = $.fn.zTree.getZTreeObj(landLevel.config.tree.prop("id"));
    if (flag) {
        var nodes = zTreeObj.getCheckedNodes(true);
        if (nodes.length != 1) {
            notifyInfo('提示', '勾选一个');
            return false;
        }
        levelDetailId = nodes[0].id;
        target.val('').trigger('click');
        target.attr({levelDetailId: levelDetailId});
        return flag;
    }
    levelDetailId = target.attr("levelDetailId");
    landLevel.ajaxFileUploadCommon({levelDetailId: levelDetailId}, target.prop("id"), "/dataLandLevelDetailVolume/importDataAllocationCorrectionCoefficientVolumeRatio", function () {
        landLevel.showDataHousePriceIndexDetailList(levelDetailId);
    }, false);
};

landLevel.editDataAllocationCorrectionCoefficientVolumeRatioDetail = function (index) {
    var row = landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTable.bootstrapTable('getData')[index];
    var frm = landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailFrm;
    frm.clearAll();
    frm.initForm(row);
    landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailBox.modal("show");
};

landLevel.saveDataAllocationCorrectionCoefficientVolumeRatioDetail = function () {
    var frm = landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailFrm;
    var data = formSerializeArray(frm);
    if (!frm.valid()) {
        return false;
    }
    landLevel.ajaxServerFun(data, '/dataLandLevelDetailVolume' + "/save", "post", function () {
        landLevel.showDataHousePriceIndexDetailList(data.levelDetailId);
        landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailBox.modal("hide");
    });
};

landLevel.showDataHousePriceIndexDetailList = function (levelDetailId) {
    var cols = [];
    cols.push({field: 'plotRatio', title: '容积率'});
    cols.push({field: 'correctionFactor', title: '修正系数'});
    var elShow = landLevel.getElShow();
    var formData = formParams("frmQuery");
    cols.push({
        field: 'id', title: '操作', formatter: function (value, row, index) {
            var str = '<div class="btn-margin">';
            var editHtml = '<button type="button" onclick="landLevel.editDataAllocationCorrectionCoefficientVolumeRatioDetail(' + index + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
            editHtml += '<i class="fa fa-pen"></i>';
            editHtml += '</button>';
            if (elShow) {
                str += editHtml;
                str += '<button type="button" onclick="landLevel.deleteDataAllocationCorrectionCoefficientVolumeRatioDetail(' + index + ')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
            }
            if (!elShow) {
                if (formData.permission) {
                    if (formData.permission == row.creator) {
                        str += editHtml;
                    }
                }
            }
            str += '</div>';
            return str;
        }
    });
    landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTable.bootstrapTable('destroy');
    TableInit(landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTable, getContextPath() + "/dataLandLevelDetailVolume/getBootstrapTableVo", cols, {
        levelDetailId: levelDetailId
    }, {
        showColumns: false,
        showRefresh: false,
        search: false,
        onLoadSuccess: function () {
            $('.tooltips').tooltip();
        }
    });
};
//------容积率 method end--------//


//------ 土地因素 method start--------//
landLevel.showDataLandDetailAchievementDetail = function () {
    var box = landLevel.config.land_level_detail_modal;
    var zTree = $.fn.zTree.getZTreeObj(landLevel.config.tree.prop("id"));
    var nodes = zTree.getCheckedNodes(true);
    if (nodes.length != 1) {
        notifyInfo('提示', '仅勾选一个');
        return false;
    }
    var treeNode = nodes[0];
    landLevel.showLandDetailAchievementList(treeNode.id);
    landLevel.config.achievementBoxDetail.find("input[name='levelDetailId']").val(treeNode.id);
    landLevel.config.achievementBoxDetail.modal("show");
};
landLevel.showDataLandDetailAchievement = function () {
    var levelDetailId = landLevel.config.achievementBoxDetail.find("input[name='levelDetailId']").val();
    landLevel.showLandDetailAchievementList(levelDetailId);
    landLevel.initFormLandDetailAchievement({levelDetailId: levelDetailId});
    landLevel.config.achievementBox.modal("show");
};

landLevel.initFormLandDetailAchievement = function (row) {
    landLevel.config.achievementFrm.clearAll();
    landLevel.config.achievementFrm.initForm(row);
    AssessCommon.loadDataDicByKey(AssessDicKey.programmeMarketCostapproachFactor, row.type, function (html, data) {
        landLevel.config.achievementFrm.find("select[name='type']").empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.programmeMarketCostapproachGrade, row.grade, function (html, data) {
        landLevel.config.achievementFrm.find("select[name='grade']").empty().html(html).trigger('change');
    });
    landLevel.config.achievementFrm.find('[name=achievement]').attr('data-value', row.achievement).val(AssessCommon.pointToPercent(row.achievement));
};

//土地级别详情从表 土地因素 删除
landLevel.deleteDataLandDetailAchievement = function (index) {
    var row = landLevel.config.achievementTable.bootstrapTable('getData')[index];
    landLevel.ajaxServerFun({_method: "DELETE"}, '/dataLandLevelDetailAchievement/delete/' + row.id, "post", function () {
        landLevel.showLandDetailAchievementList(row.levelDetailId);
    }, "delete");
};

landLevel.downloadDataLandDetailAchievementFile = function (this_) {
    //AssessCommon.downloadFileTemplate(AssessFTKey.ftpLandLevelDetailBaseAchievementTemplate)
    var zTree = $.fn.zTree.getZTreeObj(landLevel.config.tree.prop("id"));
    var nodes = zTree.getCheckedNodes(true);
    if (nodes.length == 0) {
        notifyInfo('提示', '至少勾选一个节点');
        return false;
    }
    var ids = [] ;
    $.each(nodes,function (j,node) {
        ids.push(node.id) ;
    }) ;
    landLevel.ajaxServerFun({id:ids.join(",")}, "/dataLandLevelDetailAchievement/downloadDataLandDetailAchievementFile", "post", function (fileId) {
        FileUtils.downAttachments(fileId);
        FileUtils.deleteFile({attachmentId: fileId});
    });

};

landLevel.importDataLandDetailAchievement = function (flag) {
    var target = $('#ajaxFileUploadLandLevelDetailAchievement');
    var levelDetailId = "";
    if (flag) {
        levelDetailId = landLevel.config.achievementBoxDetail.find("input[name='levelDetailId']").val();
        target.val('').trigger('click');
        target.attr({levelDetailId: levelDetailId});
        return flag;
    }
    levelDetailId = target.attr("levelDetailId");
    landLevel.ajaxFileUploadCommon({landLevelId: currentLandLevelId}, target.prop("id"), "/dataLandLevelDetailAchievement/importDataLandDetailAchievement", function () {
        // landLevel.showLandDetailAchievementList(levelDetailId);
        landLevel.loadTree();

    }, false);
};

landLevel.editDataLandDetailAchievement = function (index) {
    landLevel.config.achievementFrm.clearAll(['levelDetailId']);
    var row = landLevel.config.achievementTable.bootstrapTable('getData')[index];
    landLevel.initFormLandDetailAchievement(row);
    landLevel.config.achievementBox.modal("show");
};

//土地级别详情从表 土地因素
landLevel.showLandDetailAchievementList = function (levelDetailId) {
    var cols = [];
    cols.push({field: 'typeName', title: '因素类型'});
    cols.push({field: 'classification', title: '因素一级'});
    cols.push({field: 'category', title: '因素二级'});
    cols.push({field: 'gradeName', title: '等级'});
    cols.push({field: 'reamark', title: '说明'});
    cols.push({
        field: 'achievement', title: '分值', formatter: function (value, row, index) {
            if (value != null || value != undefined) {
                return AssessCommon.pointToPercent(value);
            }
        }
    });
    var elShow = landLevel.getElShow();
    var formData = formParams("frmQuery");
    cols.push({
        field: 'id', title: '操作', formatter: function (value, row, index) {
            var str = '<div class="btn-margin">';
            var editHtml = '<button type="button" onclick="landLevel.editDataLandDetailAchievement(' + index + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
            editHtml += '<i class="fa fa-pen"></i>';
            editHtml += '</button>';
            if (elShow) {
                str += editHtml;
                str += '<button type="button" onclick="landLevel.deleteDataLandDetailAchievement(' + index + ')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
            }
            if (!elShow) {
                if (formData.permission) {
                    if (formData.permission == row.creator) {
                        str += editHtml;
                    }
                }
            }
            str += '</div>';
            return str;
        }
    });


    landLevel.config.achievementTable.bootstrapTable('destroy');
    TableInit(landLevel.config.achievementTable, getContextPath() + "/dataLandLevelDetailAchievement/getBootstrapTableVo", cols, {
        levelDetailId: levelDetailId
    }, {
        showColumns: false,
        showRefresh: false,
        search: false,
        onLoadSuccess: function () {
            $('.tooltips').tooltip();
        }
    });
};

//土地级别详情从表 土地因素 保存或者更新
landLevel.saveDataLandDetailAchievement = function () {
    var data = formSerializeArray(landLevel.config.achievementFrm);
    if (!landLevel.config.achievementFrm.valid()) {
        return false;
    }
    landLevel.ajaxServerFun({formData: JSON.stringify(data)}, '/dataLandLevelDetailAchievement/save', "post", function () {
        landLevel.showLandDetailAchievementList(data.levelDetailId);
        landLevel.config.achievementBox.modal("hide");
    }, "save");
};
//------ 土地因素 method end--------//


landLevel.getElShow = function () {
    var elShow = true;
    try {
        var formData = formParams("frmQuery");
        if (formData.readOnly == 'true') {
            elShow = false;
        }
        if (formData.permission) {
            if (formData.permission == landLevel.admin) {
                elShow = true;
            }
        }
    } catch (e) {
        console.log(e);
    }
    return elShow;
};





