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
                <th data-options="field:'area',width:80,align:'right',editor:{type:'numberbox',options:{precision:1}}">建筑面积</th>
                <th data-options="field:'currency',width:60,align:'center',editor:{type:'checkbox',options:{on:'P',off:''}}">单方造价(元/㎡)</th>
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
        console.log("item:"+item);
    };
    constructionInstallationEngineeringFeeA.onClickCell = function (index, field) {
        if (constructionInstallationEngineeringFeeA.endEditing()){
            $('#constructionInstallationEngineeringFeeA').datagrid('selectRow', index)
                .datagrid('editCell', {index:index,field:field});
            editIndex = index;
        }
    };
    var editIndex = undefined;
    constructionInstallationEngineeringFeeA.endEditing = function () {
        var flag = false;
        (function () {
            if (editIndex == undefined){return true}
            if ($('#constructionInstallationEngineeringFeeA').datagrid('validateRow', editIndex)){
                $('#constructionInstallationEngineeringFeeA').datagrid('endEdit', editIndex);
                editIndex = undefined;
                flag = true;
            } else {
                flag = false;
            }
        })();
        return flag;
    };
    $.extend($.fn.datagrid.methods, {
        editCell: function(jq,param){
            return jq.each(function(){
                var opts = $(this).datagrid('options');
                var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
                for(var i=0; i<fields.length; i++){
                    var col = $(this).datagrid('getColumnOption', fields[i]);
                    col.editor1 = col.editor;
                    if (fields[i] != param.field){
                        col.editor = null;
                    }
                }
                $(this).datagrid('beginEdit', param.index);
                for(var i=0; i<fields.length; i++){
                    var col = $(this).datagrid('getColumnOption', fields[i]);
                    col.editor = col.editor1;
                }
            });
        }
    });
    constructionInstallationEngineeringFeeA.treegrid = function () {
        var url = "${pageContext.request.contextPath}/projectTaskMarketCost/getBaseDicTree";
        $('#constructionInstallationEngineeringFeeA').treegrid({
            url: url,
            method: 'get',
            title: '建筑安装工程费', //标题
            width: 850, //宽度
            height: 450, //高度
            lines: true,
            animate: true,   //是否开启动画
            collapsible: true,//是否可以折叠
            fitColumns: true, //设置为true，则会自动扩大或缩小列的尺寸以适应网格的宽度并且防止水平滚动。
            rownumbers: true,//设置为true，则显示带有行号的列
            showFooter: true,  //定义是否显示行的底部
            idField: 'id',//定义关键字段来标识树节点
            treeField: 'name',//treeField属性定义哪个字段显示为树形菜单
            onClickCell: function(index,field,value){
                console.log("method:"+"onClickCell") ;
                console.log(index);
                console.log(field);
                console.log(value);
                $(this).datagrid('beginEdit', index);
                var ed = $(this).datagrid('getEditor', {index:index,field:field});
            },
            onDblClickRow: function (field,row) {//双击事件
                console.log("method:"+"onDblClickRow") ;
                console.log(field);
                console.log(row);
            },
            onBeforeEdit:function (row) {//用户开始编辑节点时触发.
                console.log("method:"+"onBeforeEdit") ;
                console.log(row);
            },
            onAfterEdit:function (row,changes) {//用户完成编辑节点后触发.
                console.log("method:"+"onAfterEdit") ;
                console.log(row);
                console.log(changes);
            },
            onCancelEdit:function (row) {//用户放弃编辑节点时触发.
                console.log("method:"+"onCancelEdit") ;
                console.log(row);
            },
            onLoadSuccess: function () {//加载成功之后
                console.log("method:"+"onLoadSuccess") ;
            },
            onLoadError:function () {
                console.log("method:"+"onLoadError") ;
            }
            //由于需要单元格编辑 所以....
            // ,
            // columns: [[
            //     {title: '序号', field: 'number', width: 50},
            //     {title: '工程名称', field: 'name', width: 130},
            //     {
            //         title: '建筑面积', field: 'area', width: 150
            //         // , formatter: function (value, row) {
            //         //     var s = "";
            //         //     s += "<a style='margin-left: 5px;' data-placement='top' data-original-title='面积输入' class='btn btn-xs btn-success tooltips'  onclick='constructionInstallationEngineeringFeeA.buildInput(" + row.id + ")'   ><i class='fa fa-edit'></i></a>";
            //         //     return s;
            //         // }
            //     },
            //     {title: '单方造价(元/㎡)', field: 'currency', width: 160},
            //     {title: '总造价（万元)', field: 'totalCost', width: 160},
            // ]]
        });
    }

    $(function () {
        constructionInstallationEngineeringFeeA.treegrid();
    });
</script>