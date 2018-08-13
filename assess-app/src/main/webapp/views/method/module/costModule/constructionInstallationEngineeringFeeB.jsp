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
      href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/easyui.css">
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
            title:"必须双击",
            url: "${pageContext.request.contextPath}/marketCost/getBaseDicTree",
            method: "get",
            idField: 'id',//数据表格要有主键
            treeField: 'name',//treegrid 树形结构主键 text
            fitColumns: true,
            striped:true,//显示斑马线
            columns: [[
                {field: 'number', title: '序号', width: 50},
                {field: 'name', title: '工程名称', width: 120},
                {field: 'area', title: '建筑面积', width: 120, editor: {type: "numberbox", options: {precision: 1}}},
                {
                    field: 'currency',
                    title: ' 单方造价(元/㎡)',
                    width: 120,
                    editor: {type: "numberbox", options: {precision: 1}}
                },
                {field: 'totalCost', title: '总造价', width: 120}
            ]],
            styler:function (value,row,index) {
                return 'background-color:#ffee00;color:red;';
            }
        });
    }

    /**
     * @author:  zch
     * 描述:封装临时的方法到$datagrid上
     * @date:2018-08-13
     **/
    $.extend($.fn.datagrid.methods, {
        editCell: function (jq, param) {
            return jq.each(function () {
                var opts = $(this).datagrid('options');
                console.log("数据收集" + 2);
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
                    console.log("数据收集" + 1);
                    console.info(opts);
                    console.info(index);
                    console.info(field);
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

    $(function () {
        constructEngineeringObject.init();
        /**
        * @author:  zch
        * 描述:开启单元格编辑
        * @date:2018-08-13
        **/
        $('#constructionInstallationEngineeringFeeB').datagrid().datagrid('enableCellEditing');
    })
</script>