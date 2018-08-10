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

<div>
    <table class="easyui-treegrid easyui-datagrid" id="constructionInstallationEngineeringFeeA">
        <thead>
        <tr>
            <th data-options="field:'number',width:40">序号</th>
            <th data-options="field:'name',width:130,editor:'text'">工程名称</th>
            <th data-options="field:'area',align:'center',editor:'numberbox'" width="20%">
                <font class="my-datagrid-header">建筑面积（只能填写数字）</font>
            </th>
            <th data-options="field:'currency',width:60,align:'center',editor:{type:'checkbox',options:{on:'P',off:''}}">
                单方造价(元/㎡)
            </th>
            <th data-options="field:'totalCost',width:60,align:'center'">总造价（万元)</th>
        </tr>
        </thead>
    </table>
</div>

<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript">
    var constructionInstallationEngineeringFeeA = new Object();
    constructionInstallationEngineeringFeeA.isNotNull = function (obj) {
        if (obj == null) {
            return false;
        }
        if (obj == '') {
            return false;
        }
        if (obj == "") {
            return false;
        }
        if (obj == 'undefined') {
            return false;
        }
        return true;
    }
    constructionInstallationEngineeringFeeA.buildInput = function (item) {
        console.log("item:" + item);
    };

    /**
     * @author:  zch
     * 描述:封装临时的方法到$上
     * @date:2018-08-10
     **/
    $.extend($.fn.datagrid.methods, {
        editCell: function (jq, param) {
            return jq.each(function () {
                var opts = $(this).datagrid('options');
                console.log("编辑单元格!");
                console.log(param);
                console.log(jq);
                console.log(opts);
                var fields = $(this).datagrid('getColumnFields', true).concat($(this).datagrid('getColumnFields'));
                console.log("fields:" + fields);
                for (var i = 0; i < fields.length; i++) {
                    var col = $(this).datagrid('getColumnOption', fields[i]);
                    console.log("col:==");
                    console.log(col);
                    col.editor1 = col.editor;
                    if (fields[i] != param.field) {
                        col.editor = null;
                    }
                }
                console.log("");
                console.log($(this));
                $(this).datagrid('beginEdit', param.index);
                for (var i = 0; i < fields.length; i++) {
                    var col = $(this).datagrid('getColumnOption', fields[i]);
                    col.editor = col.editor1;
                }
            });
        }
    });

    var editIndex = undefined;

    function submitForm(index, row, changes) {

    }

    /**
     * @author:  zch
     * 描述:双击单元格事件
     * @date:2018-08-10
     **/
    constructionInstallationEngineeringFeeA.onClickCell = function (index, field) {
        console.log("method:constructionInstallationEngineeringFeeA.onClickCell");
        console.log(index);
        console.log(field);
        if (constructionInstallationEngineeringFeeA.endEditing()) {
            $('#constructionInstallationEngineeringFeeA').datagrid('selectRow', index).datagrid('editCell', {
                index: index,
                field: field
            });
            editIndex = index;
            $(this).datagrid('beginEdit', index);
        }
    };

    /**
     * @author:  zch
     * 描述:校验是否需要结束编辑 (该方法用于关闭上一个焦点的editing状态)
     * @date:2018-08-10
     **/
    constructionInstallationEngineeringFeeA.endEditing = function () {
        var flag = false;
        if (editIndex == undefined) {
            return true
        }
        if ($('#constructionInstallationEngineeringFeeA').datagrid('validateRow', editIndex)) {
            $('#constructionInstallationEngineeringFeeA').datagrid('endEdit', editIndex);
            editIndex = undefined;
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    };

    /**
     * @author:  zch
     * 描述:单元格失去焦点执行的方法
     * @date:2018-08-10
     **/
    constructionInstallationEngineeringFeeA.onAfterEdit = function (index, row, changes) {
        var updated = $('#constructionInstallationEngineeringFeeA').datagrid('getChanges', 'updated');
        console.log("constructionInstallationEngineeringFeeA.onAfterEdit");
        console.log(updated);
        if (updated.length < 1) {
            $('#constructionInstallationEngineeringFeeA').datagrid('unselectAll');
            return;
        } else {
            // 传值
            submitForm(index, row, changes);
        }
    }

    /**
     * @author:  zch
     * 描述:用户开始编辑节点时触发
     * @date:2018-08-10
     **/
    constructionInstallationEngineeringFeeA.onBeforeEdit = function (index, row, changes) {
        //获的焦点
        console.log("constructionInstallationEngineeringFeeA.onBeforeEdit");
        console.log(row);
        console.log(changes);
        console.log(index);
    }

    /**
     * @author:  zch
     * 描述:easyui树形表格编辑 初始化
     * @date:2018-08-10
     **/
    constructionInstallationEngineeringFeeA.treegrid = function () {
        var url = "${pageContext.request.contextPath}/marketCost/getBaseDicTree";
        $('#constructionInstallationEngineeringFeeA').treegrid({
            url: url,
            method: 'get',
            // data:data ,
            title: '建筑安装工程费', //标题
            queryParams: {},//请求参数
            width: 850, //宽度
            height: 450, //高度
            lines: true,
            singleSelect: false, //只允许选中一行
            animate: true,   //是否开启动画
            collapsible: true,//是否可以折叠
            fitColumns: true, //设置为true，则会自动扩大或缩小列的尺寸以适应网格的宽度并且防止水平滚动。
            rownumbers: true,//设置为true，则显示带有行号的列
            showFooter: true,  //定义是否显示行的底部
            idField: 'id',//定义关键字段来标识树节点
            treeField: 'name',//treeField属性定义哪个字段显示为树形菜单
            onClickCell: constructionInstallationEngineeringFeeA.onClickCell,
            onDblClickRow: function (field, row) {//双击事件
            },
            onBeforeEdit: constructionInstallationEngineeringFeeA.onBeforeEdit, //用户开始编辑节点时触发.
            onAfterEdit: constructionInstallationEngineeringFeeA.onAfterEdit, //用户完成编辑节点后触发.
            onCancelEdit: function (row) {//用户放弃编辑节点时触发.
                console.log("method:" + "onCancelEdit");
            },
            onLoadSuccess: function () {//加载成功之后
                console.log("method:" + "onLoadSuccess");
            },
            onLoadError: function () {
                console.log("method:" + "onLoadError");
            }

        });
    }

    $(function () {
        // constructionInstallationEngineeringFeeA.treegrid();
    });
</script>