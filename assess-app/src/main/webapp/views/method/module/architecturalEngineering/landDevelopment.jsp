<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/8/9
  Time: 11:27
  To change this template use File | Settings | File Templates.
  建筑安装工程费
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="form-group">
    <div class="col-sm-12">
        <table id="landEngineeringDevelopmentID" class="table tree">
            <thead>
            <tr>
                <th>工程名称</th>
                <th>单方造价(元/㎡)</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
            <tfoot>
                <tr>
                    <th>合计</th>
                    <th>123</th>
                </tr>
            </tfoot>
        </table>
    </div>
</div>
<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-6 control-label">
            数据计算值:
        </label>
    </div>
    <div class="x-valid">
        <label class="col-sm-6 control-label constructionInstallationEngineeringFeeBCurrencyClassA">
            0
        </label>
    </div>
</div>

<script type="text/javascript">


    var landEngineeringDevelopment = {};
    landEngineeringDevelopment.treeViewHtml = '';
    landEngineeringDevelopment.viewInit = function () {
        //1.读取数据 2.将数据初始化成树形结构
        $.ajax({
            url: '${pageContext.request.contextPath}/marketCost/getTreeView',
            type: 'get',
            success: function (result) {
                if (result.ret) {
                    var html = ''
                    $.each(result.data.nodes, function (i, item) {
                        landEngineeringDevelopment.treeViewHtml += '<tr class="treegrid-' + item.level + '-' + item.id + '">';
                        landEngineeringDevelopment.treeViewHtml += '<td>' + item.text + '</td>';
                        landEngineeringDevelopment.treeViewHtml += '<td><input type="text" data-rule-number="true"></td>';
                        landEngineeringDevelopment.treeViewHtml += '</tr>';
                        if (item.nodes) {
                            landEngineeringDevelopment.recursionTreeView(item.nodes);
                        }
                    });
                    $("#landEngineeringDevelopmentID").find('tbody').empty().append(landEngineeringDevelopment.treeViewHtml);
                    $("#landEngineeringDevelopmentID").treegrid();
                }
            }
        })
    }

    //递归设置treeview的html
    landEngineeringDevelopment.recursionTreeView = function (nodes) {
        if (nodes && nodes.length > 0) {
            $.each(nodes, function (i, item) {
                landEngineeringDevelopment.treeViewHtml += '<tr class="treegrid-' + item.level + '-' + item.id + ' treegrid-parent-' + item.level + '">';
                landEngineeringDevelopment.treeViewHtml += '<td>' + item.text + '</td>';
                landEngineeringDevelopment.treeViewHtml += '<td><input type="text" data-rule-number="true"></td>';
                landEngineeringDevelopment.treeViewHtml += '</tr>';
                if (item.nodes) {
                    landEngineeringDevelopment.recursionTreeView(item.nodes);
                }
            })
        }
    }


</script>


<%--<script type="text/javascript">--%>

<%--var landEngineeringDevelopment = new Object();--%>
<%--landEngineeringDevelopment.isNotNull = function (obj) {--%>
<%--if (obj == 0) {--%>
<%--return true;--%>
<%--}--%>
<%--if (obj == '') {--%>
<%--return true;--%>
<%--}--%>
<%--if (obj) {--%>
<%--return true;--%>
<%--}--%>
<%--return false;--%>
<%--};--%>
<%--landEngineeringDevelopment.config = function () {--%>
<%--return {--%>
<%--tableId: "landEngineeringDevelopmentID",--%>
<%--currencyClass: "constructionInstallationEngineeringFeeBCurrencyClassA",//建安成本小计 3个label class--%>
<%--areaClass: "constructionInstallationEngineeringFeeBAreaClassA",--%>
<%--totalCostClass: "constructionInstallationEngineeringFeeBTotalCostClassA",--%>
<%--valuationDateTotalClass: "valuationDateTotalClassA",--%>
<%--continuedConstructionInvestmentTotalClass: "continuedConstructionInvestmentTotalClassA"--%>
<%--};--%>
<%--};--%>

<%--var editIndex = null; //必须的局部变量--%>

<%--/**--%>
<%--* @author:  zch--%>
<%--* 描述:读取树形表单数据--%>
<%--* @date: 2018-08-23--%>
<%--**/--%>
<%--landEngineeringDevelopment.loadData = function () {--%>
<%--var data = $('#' + landEngineeringDevelopment.config().tableId).treegrid('getData');--%>
<%--return data;--%>
<%--};--%>

<%--/**--%>
<%--* @author:  zch--%>
<%--* 描述:注入修改的数据--%>
<%--* @date:2018-08-23--%>
<%--**/--%>
<%--landEngineeringDevelopment.setServerData = function (data) {--%>
<%--this.data = data;--%>
<%--};--%>
<%--landEngineeringDevelopment.setArea = function (data) {--%>
<%--$('.' + landEngineeringDevelopment.config().areaClass).html(data);--%>
<%--};--%>
<%--landEngineeringDevelopment.getColumns = function () {--%>
<%--var data = null;--%>
<%--var precision = 2;//精度--%>
<%--data = [[--%>
<%--{field: 'number', title: '序号', width: 50},--%>
<%--{field: 'name', title: '工程名称', width: 200},--%>
<%--{--%>
<%--field: 'currency',--%>
<%--title: ' 单方造价(元/㎡)',--%>
<%--width: 100,--%>
<%--editor: {type: "numberbox", options: {precision: precision}},--%>
<%--styler: function (value, row, index) {--%>
<%--return 'background-color:#F0F0F0;color:red;';--%>
<%--}--%>
<%--}--%>
<%--]];--%>
<%--return data;--%>
<%--};--%>

<%--landEngineeringDevelopment.treeGrIdInit = function (data) {--%>
<%--$('#' + landEngineeringDevelopment.config().tableId).treegrid({--%>
<%--iconCls: 'icon-edit',--%>
<%--nowrap: false,--%>
<%--width: 1000,--%>
<%--height: 'auto',--%>
<%--collapsible: true,--%>
<%--title: "建安工程费用测算表",--%>
<%--data: data,--%>
<%--idField: 'id',//数据表格要有主键--%>
<%--treeField: 'name',//treegrid 树形结构主键 text--%>
<%--fitColumns: true,--%>
<%--striped: true,//显示斑马线--%>
<%--columns: landEngineeringDevelopment.getColumns(),--%>
<%--onAfterEdit: function (row, changes) {//在用户完成编辑一行的时候触发--%>
<%--//根据id获取值--%>
<%--landEngineeringDevelopment.updateChildren($('#' + landEngineeringDevelopment.config().tableId).treegrid('find', row.id), changes);--%>
<%--}--%>
<%--});--%>
<%--};--%>

<%--/**--%>
<%--* @author:  zch--%>
<%--* 描述:数据表格初始化数据--%>
<%--* @date:2018-08-13--%>
<%--**/--%>
<%--landEngineeringDevelopment.init = function () {--%>
<%--function isNotNull(obj) {--%>
<%--if (obj) {--%>
<%--return true;--%>
<%--}--%>
<%--return false;--%>
<%--}--%>

<%--var data = this.data;--%>
<%--var url = "${pageContext.request.contextPath}/marketCost/getBaseDicTree";--%>
<%--if (isNotNull(data)) {//引用修改数据继续处理--%>
<%--console.log("引用修改数据!");--%>
<%--landEngineeringDevelopment.treeGrIdInit(data);--%>
<%--} else {//说明非修改 需要手动从服务器上获取数据--%>
<%--console.log("从服务器上获取数据!");--%>
<%--//说明 由于 easyui 如果是传入的data并且data中是没有初始化过的数据那么无法开启编辑--%>
<%--$('#' + landEngineeringDevelopment.config().tableId).treegrid({--%>
<%--iconCls: 'icon-edit',--%>
<%--nowrap: false,--%>
<%--// rownumbers: true,--%>
<%--width: 700,--%>
<%--height: 'auto',--%>
<%--collapsible: true,--%>
<%--title: "建安工程费用测算表",--%>
<%--url: "${pageContext.request.contextPath}/marketCost/getBaseDicTree",--%>
<%--method: "get",--%>
<%--idField: 'id',//数据表格要有主键--%>
<%--treeField: 'name',//treegrid 树形结构主键 text--%>
<%--fitColumns: true,--%>
<%--striped: true,//显示斑马线--%>
<%--columns: landEngineeringDevelopment.getColumns(),--%>
<%--onAfterEdit: function (row, changes) {//在用户完成编辑一行的时候触发--%>
<%--//根据id获取值--%>
<%--landEngineeringDevelopment.updateChildren($('#' + landEngineeringDevelopment.config().tableId).treegrid('find', row.id), changes);--%>
<%--}--%>
<%--});--%>
<%--}--%>
<%--};--%>

<%--/**--%>
<%--* @author:  zch--%>
<%--* 描述:更新子节点数据--%>
<%--* @date:2018-08-13--%>
<%--**/--%>
<%--landEngineeringDevelopment.updateChildren = function (data, changes) {--%>
<%--if (landEngineeringDevelopment.isNotNull(data)) {--%>
<%--var currency = null;--%>
<%--if (changes.currency) {//单方造价--%>
<%--currency = changes.currency;--%>
<%--if (AssessCommon.isNumber(currency)) {--%>
<%--} else {--%>
<%--Alert("请输入数字!");--%>
<%--return false;--%>
<%--}--%>
<%--}--%>
<%--if (landEngineeringDevelopment.isNotNull(currency)) {--%>
<%--//更新节点值--%>
<%--$('#' + landEngineeringDevelopment.config().tableId).treegrid('update', {--%>
<%--id: data.id,--%>
<%--row: {currency: currency}--%>
<%--});--%>
<%--if (!data.parent) {//说明不是父节点--%>
<%--landEngineeringDevelopment.updateFather(data);--%>
<%--} else {--%>
<%--landEngineeringDevelopment.updateDirectFather();--%>
<%--}--%>
<%--}--%>
<%--}--%>
<%--};--%>

<%--landEngineeringDevelopment.updateDirectFather = function () {--%>
<%--//更新建安成本小计--%>
<%--landEngineeringDevelopment.totalCalculation();--%>
<%--};--%>

<%--/**--%>
<%--* @author:  zch--%>
<%--* 描述:更新父节点数据--%>
<%--* @date:2018-08-13--%>
<%--**/--%>
<%--landEngineeringDevelopment.updateFather = function (data) {--%>
<%--var parent = $('#' + landEngineeringDevelopment.config().tableId).treegrid('getParent', data.id);--%>
<%--var childrens = parent.children;--%>
<%--var currency = 0;//单方造价--%>
<%--if (landEngineeringDevelopment.isNotNull(childrens)) {--%>
<%--$.each(childrens, function (i, n) {--%>
<%--currency += Number(n.currency);--%>
<%--});--%>
<%--}--%>
<%--parent.currency = currency;--%>
<%--//更新节点值--%>
<%--$('#' + landEngineeringDevelopment.config().tableId).treegrid('update', {--%>
<%--id: parent.id,--%>
<%--row: parent--%>
<%--});--%>
<%--//更新建安成本小计--%>
<%--landEngineeringDevelopment.totalCalculation();--%>
<%--};--%>

<%--/**--%>
<%--* @author:  zch--%>
<%--* 描述:建安成本小计--%>
<%--* @date:2018-08-14--%>
<%--**/--%>
<%--landEngineeringDevelopment.totalCalculation = function () {--%>
<%--var currency = 0;//单方造价--%>
<%--$.each($('#' + landEngineeringDevelopment.config().tableId).treegrid('getRoots'), function (i, n) {--%>
<%--currency += Number(n.currency);--%>
<%--});--%>
<%--landEngineeringDevelopment.updateHtml({--%>
<%--currency: currency--%>
<%--});--%>
<%--};--%>
<%--/**--%>
<%--* @author:  zch--%>
<%--* 描述:建安成本小计 写html value--%>
<%--* @date:2018-08-14--%>
<%--**/--%>
<%--landEngineeringDevelopment.updateHtml = function (data) {--%>
<%--$('.' + landEngineeringDevelopment.config().currencyClass).html(data.currency);--%>
<%--};--%>

<%--/**--%>
<%--* @author:  zch--%>
<%--* 描述:获取建安成本小计--%>
<%--* @date:2018-08-14--%>
<%--**/--%>
<%--landEngineeringDevelopment.getCalculatedResults = function () {--%>
<%--return $('.' + landEngineeringDevelopment.config().currencyClass).html();--%>
<%--};--%>


<%--/**--%>
<%--* @author:  zch--%>
<%--* 描述:封装临时的方法到$datagrid上 enableCellEditing,editCell--%>
<%--* @date:2018-08-13--%>
<%--**/--%>
<%--landEngineeringDevelopment.extendOverwrite = function () {--%>
<%--$.extend($.fn.datagrid.methods, {--%>
<%--editCell: function (jq, param) {--%>
<%--return jq.each(function () {--%>
<%--var opts = $(this).datagrid('options');--%>
<%--var fields = $(this).datagrid('getColumnFields', true).concat($(this).datagrid('getColumnFields'));--%>
<%--for (var i = 0; i < fields.length; i++) {--%>
<%--var col = $(this).datagrid('getColumnOption', fields[i]);--%>
<%--col.editor1 = col.editor;--%>
<%--if (fields[i] != param.field) {--%>
<%--col.editor = null;--%>
<%--}--%>
<%--}--%>
<%--$(this).datagrid('beginEdit', param.index);--%>
<%--var ed = $(this).datagrid('getEditor', param);--%>
<%--if (ed) {--%>
<%--if ($(ed.target).hasClass('textbox-f')) {--%>
<%--$(ed.target).textbox('textbox').focus();--%>
<%--} else {--%>
<%--$(ed.target).focus();--%>
<%--}--%>
<%--}--%>
<%--for (var i = 0; i < fields.length; i++) {--%>
<%--var col = $(this).datagrid('getColumnOption', fields[i]);--%>
<%--col.editor = col.editor1;--%>
<%--}--%>
<%--});--%>
<%--},--%>
<%--enableCellEditing: function (jq) {--%>
<%--return jq.each(function () {--%>
<%--var dg = $(this);--%>
<%--var opts = dg.datagrid('options');--%>
<%--opts.oldOnClickCell = opts.onClickCell;--%>
<%--opts.onClickCell = function (index, field) {--%>
<%--editIndex = index;--%>
<%--if (opts.editIndex != undefined) {--%>
<%--if (dg.datagrid('validateRow', opts.editIndex)) {--%>
<%--dg.datagrid('endEdit', opts.editIndex);--%>
<%--opts.editIndex = undefined;--%>
<%--} else {--%>
<%--return;--%>
<%--}--%>
<%--}--%>
<%--dg.datagrid('selectRow', index).datagrid('editCell', {--%>
<%--index: index,--%>
<%--field: field--%>
<%--});--%>
<%--opts.editIndex = index;--%>
<%--opts.oldOnClickCell.call(this, index, field);--%>
<%--}--%>
<%--});--%>
<%--}--%>
<%--});--%>
<%--};--%>

<%--landEngineeringDevelopment.viewInit = function () {--%>
<%--landEngineeringDevelopment.extendOverwrite();--%>
<%--landEngineeringDevelopment.init();--%>
<%--/**--%>
<%--* @author:  zch--%>
<%--* 描述:开启单元格编辑--%>
<%--* @date:2018-08-13--%>
<%--**/--%>
<%--$('#' + landEngineeringDevelopment.config().tableId).datagrid().datagrid('enableCellEditing');--%>
<%--};--%>
<%--</script>--%>