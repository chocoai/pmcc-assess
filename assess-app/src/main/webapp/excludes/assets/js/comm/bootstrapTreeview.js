/**
 * Created by Calvin on 2017/7/25.
 * 树型操作
 */

//设置选中树的某个节点
function treeView_setValue(objs, currVlaue) {
    var treeObj;
    if (objs instanceof jQuery) {
        treeObj = objs;
    } else {
        treeObj = $("#" + objs);
    }
    var nodes = treeObj.treeview('getEnabled');
    if(currVlaue+"") {
        currVlaue=currVlaue+"";
        var array = currVlaue.split(',');
        for (var i = 0; i < nodes.length; i++) {
            if ($.inArray(nodes[i].id + "", array) > -1) {
                treeObj.treeview('selectNode', [nodes[i], {silent: true}]);
                treeView_getparentNode_p(treeObj, nodes[i]);
            }
        }
    }
}

function treeView_getparentNode_p(treeObjs, node) {
    var parentNode = treeObjs.treeview('getParent', node);
    if (parentNode.nodeId > 0) {
        treeObjs.treeview('expandNode', [parentNode, {silent: true}]);
        treeView_getparentNode_p(treeObjs, parentNode);
    }
}

//递归获取所有的结点id
function getchildNodesIdArr(node) {
    var ts = [];
    if (node.nodes) {
        for (x in node.nodes) {
            ts.push(node.nodes[x].nodeId)
            if (node.nodes[x].nodes) {
                var getNodeDieDai = getchildNodesIdArr(node.nodes[x]);
                for (j in getNodeDieDai) {
                    ts.push(getNodeDieDai[j]);
                }
            }
        }
    } else {
        ts.push(node.nodeId);
    }
    return ts;
}

function initTreeView(objs,contextPath, currOrgId, single, fn) {
    var treeObj;
    if (objs instanceof jQuery) {
        treeObj = objs;
    } else {
        treeObj = $("#" + objs);
    }
    $.ajax({
        url:contextPath+ "/department/getDepartmentTree" ,
        data:{pid:currOrgId},
        type: "get",
        dataType: "json",
        success: function (result) {

            if (result.ret) {
                treeObj.treeview(
                    {
                        data: [result.data],
                        levels: 2,
                        multiSelect: single,
                        showCheckbox: single,
                        onNodeChecked: function (event, node) {
                            var selectNodes = getchildNodesIdArr(node);//获取所有子节点
                            selectNodes.push(node.nodeId);
                            if (selectNodes) { //子节点不为空，则选中所有子节点
                                treeObj.treeview('checkNode', [selectNodes, {silent: true}]);
                                treeObj.treeview('selectNode', [selectNodes, {silent: true}]);
                            }
                        },
                        onNodeUnchecked: function (event, node) {
                            var selectNodes = getchildNodesIdArr(node);//获取所有子节点
                            selectNodes.push(node.nodeId);
                            if (selectNodes) { //子节点不为空，则取消选中所有子节点
                                treeObj.treeview('uncheckNode', [selectNodes, {silent: true}]);
                                treeObj.treeview('unselectNode', [selectNodes, {silent: true}]);
                            }
                        }
                    });
                fn(treeObj);
            }
            else {
                Alert("加载树失败，失败原因：" + result.errmsg, 1, null, null);
            }
        },
        error: function (result) {
            Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
        }
    });
}


function initBaseTreeView(objs, url, data, single, fn) {
    // Loading.progressShow();
    var treeObj;
    if (objs instanceof jQuery) {
        treeObj = objs;
    } else {
        treeObj = $("#" + objs);
    }
    $.ajax({
        url: url,
        type: "get",
        dataType: "json",
        data: data,
        success: function (result) {
            if (result.ret) {
                var treeData;
                if ($.isArray(result.data)) {
                    treeData = result.data;

                } else {
                    treeData = [result.data];

                }

                treeObj.treeview({
                        data: treeData,
                        levels: 2,
                        multiSelect: single,
                        showCheckbox: single,
                        onNodeChecked: function (event, node) {
                            var selectNodes = getchildNodesIdArr(node);//获取所有子节点
                            selectNodes.push(node.nodeId);
                            if (selectNodes) { //子节点不为空，则选中所有子节点
                                treeObj.treeview('checkNode', [selectNodes, {silent: true}]);
                                treeObj.treeview('selectNode', [selectNodes, {silent: true}]);
                            }
                        },
                        onNodeUnchecked: function (event, node) {
                            var selectNodes = getchildNodesIdArr(node);//获取所有子节点
                            selectNodes.push(node.nodeId);
                            if (selectNodes) { //子节点不为空，则取消选中所有子节点
                                treeObj.treeview('uncheckNode', [selectNodes, {silent: true}]);
                                treeObj.treeview('unselectNode', [selectNodes, {silent: true}]);
                            }
                        }
                    });
                fn(treeObj);
                // Loading.progressHide();
            }
            else {
                // Loading.progressHide();
                Alert("加载树失败，失败原因：" + result.errmsg, 1, null, null);
            }
        },
        error: function (result) {
            // Loading.progressHide();
            Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
        }
    });
}

//取某一节点的值
function treeView_getValues(objs) {
    var treeObj;
    if (objs instanceof jQuery) {
        treeObj = objs;
    } else {
        treeObj = $("#" + objs);
    }
    var nodes = treeObj.treeview('getSelected');
    return nodes;
}

