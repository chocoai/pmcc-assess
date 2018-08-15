/**
 * Created by kings on 2018-8-15.
 */
;(function ($) {
    var TreeGridUtils = function () {
        this.treeGrid = undefined;
    }

    //初始化
    TreeGridUtils.prototype.init = function ($treeGrid) {
        var tg = new TreeGridUtils();
        tg.treeGrid = $treeGrid;
        return tg;
    }

    //全选
    TreeGridUtils.prototype.selectAll = function () {
        var treeGrid = this.treeGrid;
        var rows = $(treeGrid).treegrid("getData");
        if (rows) {
            $.each(rows, function (i, item) {
                $(treeGrid).treegrid("checkNode", item.id);
            })
        }
    }

    //全不选
    TreeGridUtils.prototype.unselectAll = function () {
        var treeGrid = this.treeGrid;
        var rows = $(treeGrid).treegrid("getCheckedNodes");
        if (rows) {
            $.each(rows, function (i, item) {
                $(treeGrid).treegrid("uncheckNode", item.id);
            })
        }
    }

    //反选
    TreeGridUtils.prototype.reverseSelect = function () {
        var treeGrid = this.treeGrid;
        var rows = $(treeGrid).treegrid("getCheckedNodes");
        this.selectAll();
        if (rows) {
            $.each(rows, function (i, item) {
                $(treeGrid).treegrid("uncheckNode", item.id);
            })
        }
    }

    //获取所有节点数据
    TreeGridUtils.prototype.getAllNode = function () {
        var treeGrid = this.treeGrid;
        var roots = $(treeGrid).treegrid("getRoots");
        var nodeArray = [];
        if (roots) {
            $.each(roots, function (i, root) {
                nodeArray.push(root);

            })
        }
    }

    //递归获取子节点
    TreeGridUtils.prototype.getChildrenNode = function (nodeArray, currNode) {
        if(currNode){

        }
    }

})(jQuery)