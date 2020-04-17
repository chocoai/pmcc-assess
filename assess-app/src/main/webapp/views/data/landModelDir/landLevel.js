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

landLevel.admin = "admin";

var currentLandLevelId = 0;
var currentLandLevelDetailId = 0;

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
    $.ajaxFileUpload({
        type: "POST",
        url: getContextPath() + "/dataLandLevel/importData",
        data: {},//要传到后台的参数，没有可以不写
        secureuri: false,//是否启用安全提交，默认为false
        fileElementId: 'ajaxFileUploadDataLand',//文件选择框的id属性
        dataType: 'json',//服务器返回的格式
        async: false,
        success: function (result) {
            if (result.ret) {
                landLevel.config.table.bootstrapTable('refresh');
                notifyInfo('提示',result.data);
            }
        },
        error: function (result, status, e) {
            Loading.progressHide();
            AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
        }
    });
};
landLevel.removeData = function (id) {
    AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
        $.ajax({
            url: getContextPath() + "/dataLandLevel/deleteDataLandLevelById",
            type: "post",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    notifySuccess("成功", "删除数据成功");
                    landLevel.config.table.bootstrapTable('refresh');
                }
                else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            }
        })
    });
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
    $.ajax({
        url: getContextPath() + "/dataLandLevel/saveAndUpdateDataLandLevel",
        type: "post",
        dataType: "json",
        data: {formData: JSON.stringify(data)},
        success: function (result) {
            if (result.ret) {
                AlertSuccess("成功", "数据已成功保存到数据库");
                target.modal('hide');
                landLevel.config.table.bootstrapTable('refresh');
            } else {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        },
        error: function (result) {
            AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
        }
    })
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
    this.initLandLevelDetailForm({landLevelId: id, pid: 0}, "none");
    box.modal();
    var form = box.find("form").first();
    if (form.size() != 0) {
        form.initForm({
            price: '',
            muPrice: '',
            volumeRate: '',
            legalAge: '',
            landAcquisitionProportion: '',
            mainStreet: ''
        });
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
landLevel.initLandLevelDetailForm = function (data, key) {
    var box = landLevel.config.land_level_detail_modal;
    var frm = box.find("form");
    this.initLandLevelDetailBaseForm(frm, data, key);
};

landLevel.initLandLevelDetailBaseForm = function (frm, data, key) {
    var item = formSerializeArray(frm);
    frm.clearAll().initForm(data);
    if (!data.landLevelId) {
        data.landLevelId = item.landLevelId;
    }
    // var parents = [
    //     frm.find("[name='classify']").parent().parent(),
    //     frm.find("[name='volumeRate']").parent().parent(),
    //     frm.find("[name='legalAge']").parent().parent()
    // ];
    // var children = [
    //     frm.find("[name='mainStreet']").parent().parent(),
    //     frm.find("[name='levelRange']").parent().parent(),
    //     frm.find("[name='landAcquisitionProportion']").parent().parent(),
    //     frm.find("[name='muPrice']").parent().parent(),
    //     frm.find("[name='price']").parent().parent(),
    //     frm.find("[name='type']").parent().parent()
    // ];
    // if (key) {
    //     switch (key) {
    //         case 'parent': {
    //             $.each(parents, function (i, ele) {
    //                 ele.show();
    //             });
    //             $.each(children, function (i, ele) {
    //                 ele.hide();
    //             });
    //             break;
    //         }
    //         case 'child': {
    //             $.each(parents, function (i, ele) {
    //                 ele.hide();
    //             });
    //             $.each(children, function (i, ele) {
    //                 ele.show();
    //             });
    //             break;
    //         }
    //         case 'none': {
    //             $.each(parents, function (i, ele) {
    //                 ele.show();
    //             });
    //             $.each(children, function (i, ele) {
    //                 ele.show();
    //             });
    //             break;
    //         }
    //         default:
    //             break;
    //     }
    //}
    AssessCommon.loadDataDicByKey(AssessDicKey.DATA_LAND_LEVEL_CLASSIFY, data.classify, function (html, data) {
        frm.find("select[name='classify']").empty().html(html).trigger('change');
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.DATA_LAND_LEVEL_ROMAN, data.type, function (html, data) {
        frm.find("select[name='type']").empty().html(html).trigger('change');
    });
};

//新增土地级别
landLevel.addLandLevelDetail = function () {
    var box = landLevel.config.land_level_detail_modal;
    // var zTree = $.fn.zTree.getZTreeObj(landLevel.config.tree.prop("id"));
    // var nodes = zTree.getSelectedNodes();
    var data = {};
    var key = "none";
    // if (nodes.length == 1) {
    //     var treeNode = nodes[0];
    //     switch (treeNode.pid) {
    //         case 0: {
    //             key = "child";
    //             data.pid = treeNode.id;
    //             break
    //         }
    //         default: {
    //             key = undefined;
    //             notifySuccess('成功','不能在此层级添加子级!');
    //             break;
    //         }
    //     }
    //
    // }
    if (key) {
        landLevel.initLandLevelDetailForm(data, key);
        box.modal();
    }
};


landLevel.getDataLandLevelDetailById = function (id, callback) {
    $.ajax({
        url: getContextPath() + '/dataLandLevel/getDataLandLevelDetailById',
        data: {id: id},
        method: "get",
        success: function (result) {
            if (result.ret) {
                if (callback) {
                    callback(result.data);
                }
            } else {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        }
    })
};

landLevel.deleteLandLevelDetail = function (id, callback) {
    AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
        Loading.progressShow();
        $.ajax({
            url: getContextPath() + '/dataLandLevel/removeDataLandLevelDetail',
            data: {id: id},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    notifySuccess("成功", "删除数据成功");
                    if (callback) {
                        callback();
                    }
                } else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            }
        })
    })
};


landLevel.saveLandLevelDetail = function () {
    var box = landLevel.config.land_level_detail_modal;
    var frm = box.find("form");
    if (!frm.valid()) {
        return false;
    }
    var data = formSerializeArray(frm);
    data.landLevelId = currentLandLevelId;
    Loading.progressShow();
    $.ajax({
        url: getContextPath() + '/dataLandLevel/saveAndUpdateDataLandLevelDetail',
        data: data,
        method: "post",
        success: function (result) {
            Loading.progressHide();
            if (result.ret) {
                AlertSuccess("成功", "数据已成功保存到数据库");
                // var item = result.data;
                // var zTree = $.fn.zTree.getZTreeObj(landLevel.config.tree.prop("id"));
                // var parentNode = null;
                // if (data.tId) {
                //     parentNode = zTree.getNodeByTId(data.tId);
                // }
                // if (data.id) {
                //     //修改 ==>第一种方式是修改完数据然后直接刷新树,第二种是修改完成后拿到node然后在拿到父级接着删除这个节点然后利用最新节点添加节点,最后最好一种是直接更新节点
                //     parentNode.name = item.name;
                //     zTree.updateNode(parentNode);
                // } else {
                //     //新增
                //     zTree.addNodes(parentNode, item);//当parentNode id为null一般是添加到第一级(参考zTree api 那上面是这样说的)
                // }
                landLevel.loadTree(data.landLevelId);
                // Alert(result.data) ;
                box.modal('hide');
            } else {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        }
    })
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
    $.ajaxFileUpload({
        type: "POST",
        url: getContextPath() + "/dataLandLevel/importLandLevelDetail",
        data: {landLevelId: landLevelDetail.landLevelId},//要传到后台的参数，没有可以不写
        secureuri: false,//是否启用安全提交，默认为false
        fileElementId: target.prop("id"),//文件选择框的id属性
        dataType: 'json',//服务器返回的格式
        async: false,
        success: function (result) {
            if (result.ret) {
                landLevel.loadTree(landLevelDetail.landLevelId);
                notifyInfo('提示',result.data);
            }
        },
        error: function (result, status, e) {
            Loading.progressHide();
            AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
        }
    });
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
    var box = landLevel.config.land_level_detail_tree_modal;
    var form = box.find("form").first();
    landLevel.getDataLandLevelDetailById(treeNode.id, function (data) {
        landLevel.initLandLevelDetailBaseForm(form, data);
    });
}

function zTreeOnRemove() {
    var zTree = $.fn.zTree.getZTreeObj(landLevel.config.tree.prop("id"));
    var nodes = zTree.getSelectedNodes();
    if (nodes.length > 1) {
        notifySuccess('成功','只能选择一个作为层级');
        return false;
    }
    if (nodes.length == 1) {
        var node = nodes[0];
        landLevel.deleteLandLevelDetail(node.id, function () {
            zTree.removeNode(node);
        });
    } else {
        notifySuccess('成功','选择删除层级');
    }
}


function zTreeOnEdit() {
    var box = landLevel.config.land_level_detail_modal;
    var zTree = $.fn.zTree.getZTreeObj(landLevel.config.tree.prop("id"));
    var nodes = zTree.getSelectedNodes();
    if (nodes.length > 1) {
        notifySuccess('成功','只能选择一个作为层级');
        return false;
    }
    if (nodes.length == 1) {
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
    } else {
        notifySuccess('成功','选择编辑层级');
    }
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
                notifySuccess('成功','不能在此层级添加子级!');
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
    var zTreeObj;

    // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
    var setting = {
        //页面上的显示效果
        view: {
            selectedMulti: true,
            removeHoverDom: removeHoverDom,//离开节点时的操作
            expandSpeed: "slow",
            fontCss: {color: "red"}
        },
        check: {
            enable: false
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
    $.ajax({
        url: getContextPath() + "/dataLandLevel/getDataLandLevelDetailTree",
        type: "get",
        dataType: "json",
        data: {landLevelId: currentLandLevelId},
        success: function (result) {
            if (result.ret) {
                $.fn.zTree.destroy();
                zTreeObj = $.fn.zTree.init(landLevel.config.tree, setting, result.data);
                zTreeObj.expandAll(true);
            } else {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        },
        error: function (result) {
            AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
        }
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
 * 刷新
 */
landLevel.treeRefresh = function () {
    var zTree = $.fn.zTree.getZTreeObj(landLevel.config.tree.prop("id"));
    zTree.refresh();
};
//---------------------------土地级别 end---------------------------------------//


//------容积率 method start--------//

landLevel.showDataAllocationCorrectionCoefficientVolumeRatioDetail = function () {
    var box = landLevel.config.land_level_detail_modal;
    var zTree = $.fn.zTree.getZTreeObj(landLevel.config.tree.prop("id"));
    var nodes = zTree.getSelectedNodes();
    if (nodes.length > 1) {
        notifySuccess('成功','只能选择一个作为层级');
        return false;
    }
    if (nodes.length == 1) {
        var treeNode = nodes[0];
        if (treeNode.pid == 0) {
            landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTableBox.find("input[name='levelDetailId']").val(treeNode.id);
            landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTableBox.modal("show");
            landLevel.showDataHousePriceIndexDetailList(treeNode.id);
        } else {
            notifySuccess('成功','第一层级关联容积率系数配置');
        }
    } else {
        notifySuccess('成功','选择层级');
    }
};

landLevel.showDataAllocationCorrectionCoefficientVolumeRatioDetailBox = function () {
    landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailFrm.clearAll();
    var levelDetailId = landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTableBox.find("input[name='levelDetailId']").val();
    landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailFrm.find('input[name=levelDetailId]').val(levelDetailId);
};

landLevel.deleteDataAllocationCorrectionCoefficientVolumeRatioDetail = function (index) {
    var row = landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTable.bootstrapTable('getData')[index];
    AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
        Loading.progressShow();
        $.ajax({
            url: getContextPath() + '/dataLandLevelDetailVolume/delete/' + row.id,
            type: "post",
            data: {_method: "DELETE"},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    notifySuccess("成功", "删除数据成功");
                    landLevel.showDataHousePriceIndexDetailList(row.levelDetailId);
                } else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            }
        })
    })
};

landLevel.importDataAllocationCorrectionCoefficientVolumeRatio = function (flag) {
    var target = $('#ajaxFileUploadLandLevelDetailCoefficientVolumeRatio');
    var levelDetailId = "";
    if (flag) {
        levelDetailId = landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTableBox.find("input[name='levelDetailId']").val();
        target.val('').trigger('click');
        target.attr({levelDetailId: levelDetailId});
        return flag;
    }
    levelDetailId = target.attr("levelDetailId");
    $.ajaxFileUpload({
        type: "POST",
        url: getContextPath() + "/dataLandLevelDetailVolume/importDataAllocationCorrectionCoefficientVolumeRatio",
        data: {levelDetailId: levelDetailId},//要传到后台的参数，没有可以不写
        secureuri: false,//是否启用安全提交，默认为false
        fileElementId: target.prop("id"),//文件选择框的id属性
        dataType: 'json',//服务器返回的格式
        async: false,
        success: function (result) {
            if (result.ret) {
                landLevel.showDataHousePriceIndexDetailList(levelDetailId);
                notifyInfo('提示',result.data);
            }
        },
        error: function (result, status, e) {
            Loading.progressHide();
            AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
        }
    });

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
    $.ajax({
        url: getContextPath() + '/dataLandLevelDetailVolume' + "/save",
        data: data,
        type: "post",
        success: function (result) {
            if (result.ret) {
                AlertSuccess("成功", "数据已成功保存到数据库");
                landLevel.showDataHousePriceIndexDetailList(data.levelDetailId);
                landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailBox.modal("hide");
            } else {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        }
    })
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
            //var editHtml = '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="landLevel.editDataAllocationCorrectionCoefficientVolumeRatioDetail(' + index + ')"><i class="fa fa-edit fa-white"></i></a>';
            var editHtml = '<button onclick="landLevel.editDataAllocationCorrectionCoefficientVolumeRatioDetail(' + index + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
            editHtml += '<i class="fa fa-pen"></i>';
            editHtml += '</button>';
            if (elShow) {
                str += editHtml;
                //str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="landLevel.deleteDataAllocationCorrectionCoefficientVolumeRatioDetail(' + index + ')"><i class="fa fa-minus fa-white"></i></a>';
                str += '<button onclick="landLevel.deleteDataAllocationCorrectionCoefficientVolumeRatioDetail('+index+')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
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
    var nodes = zTree.getSelectedNodes();
    if (nodes.length > 1) {
        notifySuccess('成功','只能选择一个作为层级');
        return false;
    }
    if (nodes.length == 1) {
        var treeNode = nodes[0];
        landLevel.showLandDetailAchievementList(treeNode.id);
        landLevel.config.achievementBoxDetail.find("input[name='levelDetailId']").val(treeNode.id);
        landLevel.config.achievementBoxDetail.modal("show");
    } else {
        notifySuccess('成功','选择层级');
    }
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
    landLevel.config.achievementFrm.find("select[name='type']").off('change').on('change', function () {
        // AssessCommon.loadSonDataListHtml($(this).val(), row.category, function (html, data) {
        //     landLevel.config.achievementFrm.find("#categoryList").empty().html(html).trigger('change');
        // });
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.programmeMarketCostapproachGrade, row.grade, function (html, data) {
        landLevel.config.achievementFrm.find("select[name='grade']").empty().html(html).trigger('change');
    });
    landLevel.config.achievementFrm.find('[name=achievement]').attr('data-value', row.achievement).val(AssessCommon.pointToPercent(row.achievement));
};

//土地级别详情从表 土地因素 删除
landLevel.deleteDataLandDetailAchievement = function (index) {
    var row = landLevel.config.achievementTable.bootstrapTable('getData')[index];
    AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
        Loading.progressShow();
        $.ajax({
            url: getContextPath() + '/dataLandLevelDetailAchievement/delete/' + row.id,
            type: "post",
            data: {_method: "DELETE"},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    notifySuccess("成功", "删除数据成功");
                    landLevel.showLandDetailAchievementList(row.levelDetailId);
                } else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            }
        })
    })
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
    $.ajaxFileUpload({
        type: "POST",
        url: getContextPath() + "/dataLandLevelDetailAchievement/importDataLandDetailAchievement",
        data: {levelDetailId: levelDetailId},//要传到后台的参数，没有可以不写
        secureuri: false,//是否启用安全提交，默认为false
        fileElementId: target.prop("id"),//文件选择框的id属性
        dataType: 'json',//服务器返回的格式
        async: false,
        success: function (result) {
            if (result.ret) {
                landLevel.showLandDetailAchievementList(levelDetailId);
                notifyInfo('提示',result.data);
            }
        },
        error: function (result, status, e) {
            Loading.progressHide();
            AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
        }
    });
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
    cols.push({field: 'typeName', title: '类型'});
    cols.push({field: 'classification', title: '一级类别'});
    cols.push({field: 'category', title: '二级类别'});
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
            //var editHtml = '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="landLevel.editDataLandDetailAchievement(' + index + ')"><i class="fa fa-edit fa-white"></i></a>';
            var editHtml = '<button onclick="landLevel.editDataLandDetailAchievement(' + index + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
            editHtml += '<i class="fa fa-pen"></i>';
            editHtml += '</button>';
            if (elShow) {
                str += editHtml;
                //str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="landLevel.deleteDataLandDetailAchievement(' + index + ')"><i class="fa fa-minus fa-white"></i></a>';
                str += '<button onclick="landLevel.deleteDataLandDetailAchievement(' + index +')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
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
    $.ajax({
        url: getContextPath() + '/dataLandLevelDetailAchievement/save',
        data: {formData: JSON.stringify(data)},
        type: "post",
        success: function (result) {
            if (result.ret) {
                AlertSuccess("成功", "数据已成功保存到数据库");
                landLevel.showLandDetailAchievementList(data.levelDetailId);
                landLevel.config.achievementBox.modal("hide");
            } else {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        }
    })
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





