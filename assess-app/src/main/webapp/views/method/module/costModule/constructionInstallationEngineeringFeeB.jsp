<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/8/9
  Time: 11:27
  To change this template use File | Settings | File Templates.
  建筑安装工程费
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/views/share/main_css.jsp" %>
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/tree.css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/datagrid.css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/panel.css">

<table id="constructionInstallationEngineeringFeeB"
       style="width:700px;height:auto">

</table>

<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript">

    var constructEngineeringObject = new Object();

    /**
     * @author:  zch
     * 描述:加法
     * @date:2018-08-13
     **/
    constructEngineeringObject.add = function (arg1, arg2) {
        if (constructEngineeringObject.isNumber(arg1)) {
            if (constructEngineeringObject.isNumber(arg2)) {
                arg1 = arg1.toString(), arg2 = arg2.toString();
                var arg1Arr = arg1.split("."), arg2Arr = arg2.split("."), d1 = arg1Arr.length == 2 ? arg1Arr[1] : "",
                    d2 = arg2Arr.length == 2 ? arg2Arr[1] : "";
                var maxLen = Math.max(d1.length, d2.length);
                var m = Math.pow(10, maxLen);
                var result = Number(((arg1 * m + arg2 * m) / m).toFixed(maxLen));
                var d = arguments[2];
                return typeof d === "number" ? Number((result).toFixed(d)) : result;
            }
        }
    }

    /**
     * @author:  zch
     * 描述:乘法
     * 参数：arg1：第一个乘数；arg2第二个乘数；d要保留的小数位数（可以不传此参数，如果不传则不处理小数位数)
     * @date:2018-08-13
     **/
    constructEngineeringObject.mul = function (arg1, arg2) {
        if (constructEngineeringObject.isNumber(arg1)) {
            if (constructEngineeringObject.isNumber(arg2)) {
                var r1 = arg1.toString(), r2 = arg2.toString(), m, resultVal, d = arguments[2];
                m = (r1.split(".")[1] ? r1.split(".")[1].length : 0) + (r2.split(".")[1] ? r2.split(".")[1].length : 0);
                resultVal = Number(r1.replace(".", "")) * Number(r2.replace(".", "")) / Math.pow(10, m);
                return typeof d !== "number" ? Number(resultVal) : Number(resultVal.toFixed(parseInt(d)));
            }
        }
    }

    /**
     * @author:  zch
     * 描述:判断是否为数字
     * @date:2018-08-13
     **/
    constructEngineeringObject.isNumber = function (obj) {
        if (constructEngineeringObject.isNotNull(obj)) {
            var regPos = /^\d+(\.\d+)?$/; //非负浮点数
            var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
            if (obj==0){
                return true;
            }
            if (obj=='0'){
                return true;
            }
            if (regPos.test(obj) || regNeg.test(obj)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    constructEngineeringObject.isNotNull = function (obj) {
        if (obj) {
            return true;
        }
        return false;
    };
    constructEngineeringObject.config = function () {
        return {
            tableId: "constructionInstallationEngineeringFeeB"
        };
    };

    var editIndex = null; //必须的局部变量

    /**
     * @author:  zch
     * 描述:数据表格初始化数据
     * @date:2018-08-13
     **/
    constructEngineeringObject.init = function () {
        $('#' + constructEngineeringObject.config().tableId).treegrid({
            iconCls: 'icon-edit',
            nowrap: false,
            rownumbers: true,
            collapsible: true,
            title: "建安工程费用测算表",
            url: "${pageContext.request.contextPath}/marketCost/getBaseDicTree",
            method: "get",
            idField: 'id',//数据表格要有主键
            treeField: 'name',//treegrid 树形结构主键 text
            fitColumns: true,
            striped: true,//显示斑马线
            columns: [[
                {field: 'number', title: '序号', width: 50},
                {field: 'name', title: '工程名称', width: 120},
                {
                    field: 'area',
                    title: '建筑面积',
                    width: 120,
                    editor: {type: "numberbox", options: {precision: 1}},
                    styler: function (value, row, index) {
                        return 'background-color:#F0F0F0;color:red;';
                    }
                },
                {
                    field: 'currency',
                    title: ' 单方造价(元/㎡)',
                    width: 120,
                    editor: {type: "numberbox", options: {precision: 1}},
                    styler: function (value, row, index) {
                        return 'background-color:#F0F0F0;color:red;';
                    }
                },
                {field: 'totalCost', title: '总造价', width: 120}
            ]],
            onAfterEdit: function (row, changes) {//在用户完成编辑一行的时候触发
                //根据id获取值
                constructEngineeringObject.updateChildren($('#' + constructEngineeringObject.config().tableId).treegrid('find', row.id),changes);
            }
        });
    }

    /**
    * @author:  zch
    * 描述:更新子数据
    * @date:2018-08-13
    **/
    constructEngineeringObject.updateChildren = function (data,changes) {
        // if ()
        if (constructEngineeringObject.isNotNull(data)){
            var area = data.area;
            var currency = data.currency;
            console.info("area:"+area);
            console.info("currency:"+currency);
            if (constructEngineeringObject.isNumber(area)) {
                console.info("test1:"+data.parent);
                if (constructEngineeringObject.isNumber(currency)) {
                    console.info("test2:"+data.parent);
                    data.totalCost = constructEngineeringObject.mul(data.area, data.currency);
                    //更新节点值
                    $('#' + constructEngineeringObject.config().tableId).treegrid('update', {
                        id: data.id,
                        row: data
                    });
                    if (!data.parent){//说明不是父节点
                        constructEngineeringObject.updateFather(data);
                    }
                }
            }
        }
    };

    /**
    * @author:  zch
    * 描述:更新父数据
    * @date:2018-08-13
    **/
    constructEngineeringObject.updateFather = function (data) {
        var parent = $('#' + constructEngineeringObject.config().tableId).treegrid('getParent', data.id) ;
        var childrens = parent.children;

        var currency = null;
        var area = null;
        var totalCost = null;
        if (constructEngineeringObject.isNotNull(childrens)){
            $.each( childrens, function(i, n){
                currency = n.currency;
                area = n.area;
                totalCost = n.totalCost;
            });
        }
        parent.currency = currency;
        parent.area = area;
        parent.totalCost = totalCost;
        //更新节点值
        $('#' + constructEngineeringObject.config().tableId).treegrid('update', {
            id: parent.id,
            row: parent
        });
    }

    /**
     * @author:  zch
     * 描述:封装临时的方法到$datagrid上 enableCellEditing,editCell
     * @date:2018-08-13
     **/
    constructEngineeringObject.extendOverwrite = function () {
        $.extend($.fn.datagrid.methods, {
            editCell: function (jq, param) {
                return jq.each(function () {
                    var opts = $(this).datagrid('options');
                    var fields = $(this).datagrid('getColumnFields', true).concat($(this).datagrid('getColumnFields'));
                    for (var i = 0; i < fields.length; i++) {
                        var col = $(this).datagrid('getColumnOption', fields[i]);
                        col.editor1 = col.editor;
                        if (fields[i] != param.field) {
                            col.editor = null;
                        }
                    }
                    $(this).datagrid('beginEdit', param.index);
                    var ed = $(this).datagrid('getEditor', param);
                    if (ed) {
                        if ($(ed.target).hasClass('textbox-f')) {
                            $(ed.target).textbox('textbox').focus();
                        } else {
                            $(ed.target).focus();
                        }
                    }
                    for (var i = 0; i < fields.length; i++) {
                        var col = $(this).datagrid('getColumnOption', fields[i]);
                        col.editor = col.editor1;
                    }
                });
            },
            enableCellEditing: function (jq) {
                return jq.each(function () {
                    var dg = $(this);
                    var opts = dg.datagrid('options');
                    opts.oldOnClickCell = opts.onClickCell;
                    opts.onClickCell = function (index, field) {
                        editIndex = index;
                        if (opts.editIndex != undefined) {
                            if (dg.datagrid('validateRow', opts.editIndex)) {
                                dg.datagrid('endEdit', opts.editIndex);
                                opts.editIndex = undefined;
                            } else {
                                return;
                            }
                        }
                        dg.datagrid('selectRow', index).datagrid('editCell', {
                            index: index,
                            field: field
                        });
                        opts.editIndex = index;
                        opts.oldOnClickCell.call(this, index, field);
                    }
                });
            }
        });
    }

    $(function () {
        constructEngineeringObject.extendOverwrite();
        constructEngineeringObject.init();
        /**
         * @author:  zch
         * 描述:开启单元格编辑
         * @date:2018-08-13
         **/
        $('#constructionInstallationEngineeringFeeB').datagrid().datagrid('enableCellEditing');
    })
</script>