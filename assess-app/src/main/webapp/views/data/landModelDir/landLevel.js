/**
 * Created by zch on 2019-11-1.
 */

var ue = UE.getEditor('landDefinition', {
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


var landLevel = {};


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
                Alert(result.data);
            }
        },
        error: function (result, status, e) {
            Loading.progressHide();
            Alert("调用服务端方法失败，失败原因:" + result);
        }
    });
};
landLevel.removeData = function (id) {
    Alert("确认删除!", 2, null, function () {
        $.ajax({
            url: getContextPath() + "/dataLandLevel/deleteDataLandLevelById",
            type: "post",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    toastr.success('删除成功');
                    landLevel.config.table.bootstrapTable('refresh');
                }
                else {
                    Alert(result.errmsg);
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
                toastr.success('添加成功');
                target.modal('hide');
                landLevel.config.table.bootstrapTable('refresh');
            } else {
                Alert("保存数据失败，失败原因:" + result.errmsg);
            }
        },
        error: function (result) {
            Alert("调用服务端方法失败，失败原因:" + result.errmsg);
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
landLevel.loadLandLevelList = function (creator, status) {
    var cols = [];
    cols.push({
        field: 'provinceName', title: '省', formatter: function (value, row, index) {
            return AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
        }
    });
    cols.push({field: 'title', title: '标题'});
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
    cols.push({field: 'landRightTypeName', title: '权利类型'});
    cols.push({field: 'townShipName', title: '乡镇名称'});
    cols.push({
        field: 'id', title: '操作', formatter: function (value, row, index) {
            var str = '<div class="btn-margin">';
            str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="landLevel.editData(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
            str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="landLevel.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';

            str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="土地级别详细情况" onclick="landLevel.treeLandLevelDetailListModal(' + row.id + ')"><i class="fa  fa-tree fa-white"></i></a>';
            str += '</div>';
            return str;
        }
    });
    landLevel.config.table.bootstrapTable('destroy');
    var query = formSerializeArray(landLevel.config.frmQuery);
    //只获取有值的对象属性
    var tempObj = Object.keys(query);
    var select = {creator: creator};
    for (var i = 0; i < tempObj.length; i++) {
        var key = tempObj[i];
        var value = query[key];
        //判断是否存在值
        if (value) {
            select[key] = value;//动态添加属性
        }
    }
    if (status) {
        select.status = status;
    }
    var method = {
        showColumns: true,
        showRefresh: true,
        search: false,
        onLoadSuccess: function () {
            $('.tooltips').tooltip();
        }
    };
    TableInit(landLevel.config.table, getContextPath() + "/dataLandLevel/getDataLandLevelListVos", cols, select, method);
};


landLevel.treeLandLevelDetailListModal = function (id) {
    this.loadTree(id);
    this.initLandLevelDetailForm({landLevelId: id, pid: 0});
    landLevel.config.land_level_detail_tree_modal.modal();
};

//土地级别赋值
landLevel.initLandLevelDetailForm = function (data) {
    var box = landLevel.config.land_level_detail_modal;
    var frm = box.find("form");
    var item = formSerializeArray(frm);
    frm.clearAll();
    if (!data.landLevelId) {
        data.landLevelId = item.landLevelId;
    }
    if (!data.pid) {
        data.pid = item.pid;
    }
    frm.initForm(data);

    AssessCommon.loadDataDicByKey(AssessDicKey.DATA_LAND_LEVEL_CLASSIFY, data.landRightType, function (html, data) {
        var ele = frm.find("select[name='classify']");
        ele.empty().html(html).trigger('change');
        if (item.pid) {
            landLevel.getDataLandLevelDetailById(item.pid, function (result) {
                ele.val(result.classify).trigger("change");
            });
        }
    });

    AssessCommon.loadDataDicByKey(AssessDicKey.DATA_LAND_LEVEL_ROMAN, data.landRightType, function (html, data) {
        frm.find("select[name='type']").empty().html(html).trigger('change');
    });
};

//新增土地级别
landLevel.addLandLevelDetail = function (data) {
    var box = landLevel.config.land_level_detail_modal;
    landLevel.initLandLevelDetailForm(data);
    box.modal();
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
                Alert(result.errmsg);
            }
        }
    })
};

landLevel.deleteLandLevelDetail = function (id, callback) {
    Alert('确认要删除么？', 2, null, function () {
        Loading.progressShow();
        $.ajax({
            url: getContextPath() + '/dataLandLevel/removeDataLandLevelDetail',
            data: {id: id},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('删除成功');
                    if (callback) {
                        callback();
                    }
                } else {
                    Alert(result.errmsg);
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
    Loading.progressShow();
    $.ajax({
        url: getContextPath() + '/dataLandLevel/saveAndUpdateDataLandLevelDetail',
        data: data,
        method: "post",
        success: function (result) {
            Loading.progressHide();
            if (result.ret) {
                toastr.success('保存成功');
                var item = result.data;
                var zTree = $.fn.zTree.getZTreeObj(landLevel.config.tree.prop("id"));
                var parentNode = null;
                if (data.tId) {
                    parentNode = zTree.getNodeByTId(data.tId);
                }
                if (data.id) {
                    //修改 ==>第一种方式是修改完数据然后直接刷新树,第二种是修改完成后拿到node然后在拿到父级接着删除这个节点然后利用最新节点添加节点,最后最好一种是直接更新节点
                    parentNode.name = item.name;
                    zTree.updateNode(parentNode);
                } else {
                    //新增
                    zTree.addNodes(parentNode, item);//当parentNode id为null一般是添加到第一级(参考zTree api 那上面是这样说的)
                }
                box.modal('hide');
            } else {
                Alert(result.errmsg);
            }
        }
    })
};

landLevel.importLandLevelDetail = function (flag) {
    var box = landLevel.config.land_level_detail_modal;
    var frm = box.find("form");
    var landLevelDetail = formSerializeArray(frm);
    var target = $('#ajaxFileUploadLandLevelDetail');
    var zTree = $.fn.zTree.getZTreeObj(landLevel.config.tree.prop("id"));
    if (flag) {
        var nodes = zTree.getSelectedNodes();
        if (nodes.length > 1) {
            toastr.success('只能选择一个作为层级');
            return flag;
        }
        if (nodes.length == 1) {
            var node = nodes[0];
            target.attr({tId: node.tId});
        }
        target.val('').trigger('click');
        return flag;
    }

    var tId = target.attr("tId");
    var pid = 0;
    var parentNode = null;
    if (tId) {
        parentNode = zTree.getNodeByTId(tId);
        pid = parentNode.id;
    }
    $.ajaxFileUpload({
        type: "POST",
        url: getContextPath() + "/dataLandLevel/importLandLevelDetail",
        data: {landLevelId:landLevelDetail.landLevelId,pid:pid},//要传到后台的参数，没有可以不写
        secureuri: false,//是否启用安全提交，默认为false
        fileElementId: target.prop("id"),//文件选择框的id属性
        dataType: 'json',//服务器返回的格式
        async: false,
        success: function (result) {
            if (result.ret) {
                var dataValue = result.data;
                var item = dataValue.item;
                var info = dataValue.info;
                if (item.length != 0) {
                    $.each(item, function (i, data) {
                        zTree.addNodes(parentNode, data);//当parentNode id为null一般是添加到第一级(参考zTree api 那上面是这样说的)
                    });
                }
                Alert(info);
            }
        },
        error: function (result, status, e) {
            Loading.progressHide();
            Alert("调用服务端方法失败，失败原因:" + result);
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
    $("#factorBtn_" + treeNode.tId).unbind().remove();
    $("#coefficientBtn_" + treeNode.tId).unbind().remove();
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
    html += "<span class=' button add' id='addBtn_" + treeNode.tId + "' title='添加数据'>" + "</span>";//定义添加按钮
    html += "<span class=' button edit' id='editBtn_" + treeNode.tId + "' title='编辑数据'>" + "</span>";//定义编辑按钮
    html += "<span class=' button remove' id='deleteBtn_" + treeNode.tId + "' title='删除数据'>" + "</span>";//定义删除按钮


    html += "<span style='margin-left:5px;' class='  ' id='factorBtn_" + treeNode.tId + "' title='所关联土地因素数据'>" + "土地因素数据" + "<i class=''></i>" + "</span>";//所关联土地因素数据
    html += "<span style='margin-left:5px;' class='  ' id='coefficientBtn_" + treeNode.tId + "' title='所关联土地容积率数据'>" + "土地容积率数据" + "<i class=''></i>" + "</span>";//所关联土地容积率数据


    element.after(html); //加载添加按钮

    var addBtn = $("#addBtn_" + treeNode.tId);
    var editBtn = $("#editBtn_" + treeNode.tId);
    var deleteBtn = $("#deleteBtn_" + treeNode.tId);
    var factorBtn = $("#factorBtn_" + treeNode.tId);
    var coefficientBtn = $("#coefficientBtn_" + treeNode.tId);
    //绑定添加事件，并定义添加操作
    if (addBtn) addBtn.bind("click", function (e) {
        landLevel.addLandLevelDetail({pid: treeNode.id, tId: treeNode.tId});
    });
    //编辑
    if (editBtn) editBtn.bind("click", function (e) {
        landLevel.getDataLandLevelDetailById(treeNode.id, function (data) {
            data.tId = treeNode.tId;
            landLevel.addLandLevelDetail(data);
        });
    });
    //删除
    if (deleteBtn) deleteBtn.bind("click", function (e) {
        var zTree = $.fn.zTree.getZTreeObj(landLevel.config.tree.prop("id"));
        landLevel.deleteLandLevelDetail(treeNode.id, function () {
            zTree.removeNode(treeNode);
        });
    });
    if (factorBtn) factorBtn.bind("click", function (e) {
        landLevel.showDataLandDetailAchievementDetail(treeNode.id);
    });
    if (coefficientBtn) coefficientBtn.bind("click", function (e) {
        landLevel.showDataAllocationCorrectionCoefficientVolumeRatioDetail(treeNode.id);
    });
}


landLevel.loadTree = function (landLevelId) {
    var zTreeObj;

    // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
    var setting = {
        //页面上的显示效果
        view: {
            selectedMulti: true,
            addHoverDom: addHoverDom, //当鼠标移动到节点上时，显示用户自定义控件
            removeHoverDom: removeHoverDom,//离开节点时的操作
            expandSpeed: "slow",
            fontCss: {color: "red"}
        },
        check: {
            enable: false
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
        data: {landLevelId: landLevelId},
        success: function (result) {
            if (result.ret) {
                zTreeObj = $.fn.zTree.init(landLevel.config.tree, setting, result.data);
            } else {
                Alert("获取数据失败，失败原因:" + result.errmsg);
            }
        },
        error: function (result) {
            Alert("调用服务端方法失败，失败原因:" + result.errmsg);
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

landLevel.showDataAllocationCorrectionCoefficientVolumeRatioDetail = function (id) {
    landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTableBox.find("input[name='allocationVolumeRatioId']").val(id);
    landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTableBox.modal("show");
    landLevel.showDataHousePriceIndexDetailList(id);
};

landLevel.showDataAllocationCorrectionCoefficientVolumeRatioDetailBox = function () {
    landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailFrm.clearAll();
    var allocationVolumeRatioId = landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTableBox.find("input[name='allocationVolumeRatioId']").val();
    landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailFrm.find('input[name=allocationVolumeRatioId]').val(allocationVolumeRatioId);
};

landLevel.deleteDataAllocationCorrectionCoefficientVolumeRatioDetail = function (index) {
    var row = landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTable.bootstrapTable('getData')[index];
    Alert('确认要删除么？', 2, null, function () {
        Loading.progressShow();
        $.ajax({
            url: getContextPath() + '/dataAllocationCorrectionCoefficientVolumeRatioDetail/delete/' + row.id,
            type: "post",
            data: {_method: "DELETE"},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('删除成功');
                    landLevel.showDataHousePriceIndexDetailList(row.allocationVolumeRatioId);
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    })
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
        url: getContextPath() + '/dataAllocationCorrectionCoefficientVolumeRatioDetail' + "/save",
        data: data,
        type: "post",
        success: function (result) {
            if (result.ret) {
                toastr.success('成功');
                landLevel.showDataHousePriceIndexDetailList(data.allocationVolumeRatioId);
                landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailBox.modal("hide");
            } else {
                Alert(result.errmsg);
            }
        }
    })
};

landLevel.showDataHousePriceIndexDetailList = function (allocationVolumeRatioId) {
    var cols = [];
    cols.push({field: 'plotRatio', title: '容积率'});
    cols.push({field: 'correctionFactor', title: '修正系数'});
    cols.push({
        field: 'id', title: '操作', formatter: function (value, row, index) {
            var str = '<div class="btn-margin">';
            str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="landLevel.editDataAllocationCorrectionCoefficientVolumeRatioDetail(' + index + ')"><i class="fa fa-edit fa-white"></i></a>';
            str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="landLevel.deleteDataAllocationCorrectionCoefficientVolumeRatioDetail(' + index + ')"><i class="fa fa-minus fa-white"></i></a>';
            str += '</div>';
            return str;
        }
    });
    landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTable.bootstrapTable('destroy');
    TableInit(landLevel.config.dataAllocationCorrectionCoefficientVolumeRatioDetailTable, getContextPath() + "/dataAllocationCorrectionCoefficientVolumeRatioDetail/getBootstrapTableVo", cols, {
        allocationVolumeRatioId: allocationVolumeRatioId
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
landLevel.showDataLandDetailAchievementDetail = function (id) {
    landLevel.showLandDetailAchievementList(id);
    landLevel.config.achievementBoxDetail.find("input[name='levelDetailId']").val(id);
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
    landLevel.config.achievementFrm.find("select[name='type']").off('change').on('change', function () {
        AssessCommon.loadDataDicByPid($(this).val(), row.category, function (html, data) {
            landLevel.config.achievementFrm.find("select[name='category']").empty().html(html).trigger('change');
            landLevel.config.achievementFrm.find("select.category").empty().html(html).trigger('change');
        });
    });
    AssessCommon.loadDataDicByKey(AssessDicKey.programmeMarketCostapproachGrade, row.grade, function (html, data) {
        landLevel.config.achievementFrm.find("select[name='grade']").empty().html(html).trigger('change');
    });
    landLevel.config.achievementFrm.find("select[name='grade']").off('change').on('change', function () {
        var category = landLevel.config.achievementFrm.find("select[name='category']").val();
        var grade = landLevel.config.achievementFrm.find("select[name='grade']").val();
        if (category) {
            AssessCommon.getDataDicInfo(category, function (data) {
                var obj = JSON.parse(data.remark);
                if (grade) {
                    AssessCommon.getDataDicInfo(grade, function (item) {
                        var value = null;
                        if (item.name == '优') {
                            value = obj.A;
                        }
                        if (item.name == '较优') {
                            value = obj.B;
                        }
                        if (item.name == '一般') {
                            value = obj.C;
                        }
                        if (item.name == '较劣') {
                            value = obj.D;
                        }
                        if (item.name == '劣') {
                            value = obj.E;
                        }
                        if (value) {
                            landLevel.config.achievementFrm.find("input[name='achievement']").val(value);
                        }
                    });
                }
            });
        }
    });
};

//土地级别详情从表 删除
landLevel.deleteDataLandDetailAchievement = function (index) {
    var row = landLevel.config.achievementTable.bootstrapTable('getData')[index];
    Alert('确认要删除么？', 2, null, function () {
        Loading.progressShow();
        $.ajax({
            url: getContextPath() + '/dataLandDetailAchievement/delete/' + row.id,
            type: "post",
            data: {_method: "DELETE"},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('删除成功');
                    landLevel.showLandDetailAchievementList(row.levelDetailId);
                } else {
                    Alert(result.errmsg);
                }
            }
        })
    })
};

landLevel.editDataLandDetailAchievement = function (index) {
    landLevel.config.achievementFrm.clearAll(['levelDetailId']);
    var row = landLevel.config.achievementTable.bootstrapTable('getData')[index];
    landLevel.initFormLandDetailAchievement(row);
    landLevel.config.achievementBox.modal("show");
};

//土地级别详情从表
landLevel.showLandDetailAchievementList = function (levelDetailId) {
    var cols = [];
    cols.push({field: 'typeName', title: '类型'});
    cols.push({field: 'categoryName', title: '类别'});
    cols.push({field: 'gradeName', title: '等级'});
    cols.push({field: 'reamark', title: '说明'});
    cols.push({field: 'achievement', title: '分值'});
    cols.push({
        field: 'id', title: '操作', formatter: function (value, row, index) {
            var str = '<div class="btn-margin">';
            str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="landLevel.editDataLandDetailAchievement(' + index + ')"><i class="fa fa-edit fa-white"></i></a>';
            str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="landLevel.deleteDataLandDetailAchievement(' + index + ')"><i class="fa fa-minus fa-white"></i></a>';
            str += '</div>';
            return str;
        }
    });
    landLevel.config.achievementTable.bootstrapTable('destroy');
    TableInit(landLevel.config.achievementTable, getContextPath() + "/dataLandDetailAchievement/getBootstrapTableVo", cols, {
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

//土地级别详情从表 保存或者更新
landLevel.saveDataLandDetailAchievement = function () {
    var data = formSerializeArray(landLevel.config.achievementFrm);
    if (!landLevel.config.achievementFrm.valid()) {
        return false;
    }
    $.ajax({
        url: getContextPath() + '/dataLandDetailAchievement/save',
        data: {formData: JSON.stringify(data)},
        type: "post",
        success: function (result) {
            if (result.ret) {
                toastr.success('成功');
                landLevel.showLandDetailAchievementList(data.levelDetailId);
                landLevel.config.achievementBox.modal("hide");
            } else {
                Alert(result.errmsg);
            }
        }
    })
};
//------ 土地因素 method end--------//





