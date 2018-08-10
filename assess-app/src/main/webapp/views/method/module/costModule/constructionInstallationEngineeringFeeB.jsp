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
    <h2>Cell Editing in DataGrid</h2>
    <p>Click a cell to start editing.</p>
    <div style="margin:20px 0;"></div>

    <table id="dg" class="easyui-datagrid" title="Cell Editing in DataGrid" >
        <thead>
        <tr>
            <th data-options="field:'itemid',width:80">Item ID</th>
            <th data-options="field:'productid',width:100,editor:'text'">Product</th>
            <th data-options="field:'listprice',width:80,align:'right',editor:{type:'numberbox',options:{precision:1}}">List Price</th>
            <th data-options="field:'unitcost',width:80,align:'right',editor:'numberbox'">Unit Cost</th>
            <th data-options="field:'attr1',width:250,editor:'text'">Attribute</th>
            <th data-options="field:'status',width:60,align:'center',editor:{type:'checkbox',options:{on:'P',off:''}}">Status</th>
        </tr>
        </thead>
    </table>
</div>

<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.new.js"></script>
<script type="text/javascript">
    var data =  {"total":28,"rows":[
            {"productid":"FI-SW-01","productname":"Koi","unitcost":10.00,"status":"P","listprice":36.50,"attr1":"Large","itemid":"EST-1"},
            {"productid":"K9-DL-01","productname":"Dalmation","unitcost":12.00,"status":"P","listprice":18.50,"attr1":"Spotted Adult Female","itemid":"EST-10"},
            {"productid":"RP-SN-01","productname":"Rattlesnake","unitcost":12.00,"status":"P","listprice":38.50,"attr1":"Venomless","itemid":"EST-11"},
            {"productid":"RP-SN-01","productname":"Rattlesnake","unitcost":12.00,"status":"P","listprice":26.50,"attr1":"Rattleless","itemid":"EST-12"},
            {"selected":true,"productid":"RP-LI-02","productname":"Iguana","unitcost":12.00,"status":"P","listprice":35.50,"attr1":"Green Adult","itemid":"EST-13"},
            {"productid":"FL-DSH-01","productname":"Manx","unitcost":12.00,"status":"P","listprice":158.50,"attr1":"Tailless","itemid":"EST-14"},
            {"productid":"FL-DSH-01","productname":"Manx","unitcost":12.00,"status":"P","listprice":83.50,"attr1":"With tail","itemid":"EST-15"},
            {"productid":"FL-DLH-02","productname":"Persian","unitcost":12.00,"status":"P","listprice":23.50,"attr1":"Adult Female","itemid":"EST-16"},
            {"productid":"FL-DLH-02","productname":"Persian","unitcost":12.00,"status":"P","listprice":89.50,"attr1":"Adult Male","itemid":"EST-17"},
            {"productid":"AV-CB-01","productname":"Amazon Parrot","unitcost":92.00,"status":"P","listprice":63.50,"attr1":"Adult Male","itemid":"EST-18"}
        ]}
    ;
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

    constructionInstallationEngineeringFeeA.treegrid = function () {
        var url = "${pageContext.request.contextPath}/projectTaskMarketCost/getBaseDicTree";
        $('#dg').treegrid({
            data:data,
            iconCls:'icon-edit',
            method: 'get',
            queryParams:{},//请求参数
            width: 850, //宽度
            height: 450, //高度
            lines: true,

            // idField: 'id',//定义关键字段来标识树节点
            // treeField: 'name',//treeField属性定义哪个字段显示为树形菜单
            onClickCell: onClickCell,
            onLoadSuccess: function () {//加载成功之后
                console.log("method:"+"onLoadSuccess") ;
            },
            onLoadError:function () {
                console.log("method:"+"onLoadError") ;
            }
        });
    }

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

    var editIndex = undefined;
    function endEditing(){
        if (editIndex == undefined){return true}
        if ($('#dg').datagrid('validateRow', editIndex)){
            $('#dg').datagrid('endEdit', editIndex);
            editIndex = undefined;
            return true;
        } else {
            return false;
        }
    }
    function onClickCell(index, field){
        console.log("onclick");
        console.log(index);
        console.log(field);
        if (endEditing()){
            $('#dg').datagrid('selectRow', index)
                .datagrid('editCell', {index:index,field:field});
            editIndex = index;
        }
    }

    $(function () {
        constructionInstallationEngineeringFeeA.treegrid();
    });
</script>