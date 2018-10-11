<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/8/9
  Time: 11:27
  To change this template use File | Settings | File Templates.
  建筑安装工程费
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/tree.css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/datagrid.css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/themes/bootstrap/panel.css">
<div class="form-group">
    <div class="col-sm-12">
        <table id="hypothesisDevelopmentBuildID">

        </table>
    </div>
</div>
<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-3 control-label">
            建安成本小计
        </label>
    </div>
    <div class="x-valid">
        <label class="col-sm-3 control-label">
            建筑面积（㎡）
        </label>
    </div>
    <div class="x-valid">
        <label class="col-sm-3 control-label">
            单方造价(元/㎡)
        </label>
    </div>
    <div class="x-valid">
        <label class="col-sm-3 control-label">
            总造价（万元）
        </label>
    </div>

</div>
<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-3 control-label">
            数据计算值:
        </label>
    </div>
    <div class="x-valid">
        <label class="col-sm-3 control-label constructionInstallationEngineeringFeeBAreaClassA">
            0
        </label>
    </div>
    <div class="x-valid">
        <label class="col-sm-3 control-label constructionInstallationEngineeringFeeBCurrencyClassA">
            0
        </label>
    </div>
    <div class="x-valid">
        <label class="col-sm-3 control-label constructionInstallationEngineeringFeeBTotalCostClassA">
            0
        </label>
    </div>
</div>


<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript">

    var hypothesisDevelopmentBuild = new Object();
    hypothesisDevelopmentBuild.isNotNull = function (obj) {
        if (obj == 0) {
            return true;
        }
        if (obj == '') {
            return true;
        }
        if (obj) {
            return true;
        }
        return false;
    };
    hypothesisDevelopmentBuild.config = function () {
        return {
            tableId: "hypothesisDevelopmentBuildID",
            currencyClass: "constructionInstallationEngineeringFeeBCurrencyClassA",//建安成本小计 3个label class
            areaClass: "constructionInstallationEngineeringFeeBAreaClassA",
            totalCostClass: "constructionInstallationEngineeringFeeBTotalCostClassA",
            valuationDateTotalClass: "valuationDateTotalClassA",
            continuedConstructionInvestmentTotalClass: "continuedConstructionInvestmentTotalClassA"
        };
    };

    var editIndex = null; //必须的局部变量

    /**
     * @author:  zch
     * 描述:读取树形表单数据
     * @date: 2018-08-23
     **/
    hypothesisDevelopmentBuild.loadData = function () {
        var data = $('#' + hypothesisDevelopmentBuild.config().tableId).treegrid('getData');
        return data;
    };

    /**
     * @author:  zch
     * 描述:注入修改的数据
     * @date:2018-08-23
     **/
    hypothesisDevelopmentBuild.setServerData = function (data) {
        this.data = data;
    };
    hypothesisDevelopmentBuild.setArea = function (data) {
        $('.' + hypothesisDevelopmentBuild.config().areaClass).html(data);
    };
    hypothesisDevelopmentBuild.getColumns = function () {
        var data = null;
        var precision = 4;//精度
        data = [[
            {field: 'number', title: '序号', width: 50},
            {field: 'name', title: '工程名称', width: '20%'},
            {
                field: 'area',
                title: '建筑面积',
                width: 90,
                editor: {type: "numberbox", options: {precision: precision}},
                styler: function (value, row, index) {
                    return 'background-color:#F0F0F0;color:red;';
                }
            },
            {
                field: 'currency',
                title: ' 单方造价(元/㎡)',
                width: 100,
                editor: {type: "numberbox", options: {precision: precision}},
                styler: function (value, row, index) {
                    return 'background-color:#F0F0F0;color:red;';
                }
            }
        ]];
        return data;
    };

    hypothesisDevelopmentBuild.treeGrIdInit = function (data) {
        $('#' + hypothesisDevelopmentBuild.config().tableId).treegrid({
            iconCls: 'icon-edit',
            nowrap: false,
            width: 1000,
            height: 'auto',
            collapsible: true,
            title: "建安工程费用测算表",
            data: data,
            idField: 'id',//数据表格要有主键
            treeField: 'name',//treegrid 树形结构主键 text
            fitColumns: true,
            striped: true,//显示斑马线
            columns: hypothesisDevelopmentBuild.getColumns(),
            onAfterEdit: function (row, changes) {//在用户完成编辑一行的时候触发
                //根据id获取值
                hypothesisDevelopmentBuild.updateChildren($('#' + hypothesisDevelopmentBuild.config().tableId).treegrid('find', row.id), changes);
            }
        });
    };

    /**
     * @author:  zch
     * 描述:数据表格初始化数据
     * @date:2018-08-13
     **/
    hypothesisDevelopmentBuild.init = function () {
        function isNotNull(obj) {
            if (obj) {
                return true;
            }
            return false;
        }

        var data = this.data;
        var url = "${pageContext.request.contextPath}/marketCost/getBaseDicTree";
        if (isNotNull(data)) {//引用修改数据继续处理
            console.log("引用修改数据!");
            hypothesisDevelopmentBuild.treeGrIdInit(data);
        } else {//说明非修改 需要手动从服务器上获取数据
            console.log("从服务器上获取数据!");
            //说明 由于 easyui 如果是传入的data并且data中是没有初始化过的数据那么无法开启编辑
            $('#' + hypothesisDevelopmentBuild.config().tableId).treegrid({
                iconCls: 'icon-edit',
                nowrap: false,
                // rownumbers: true,
                width: 700,
                height: 'auto',
                collapsible: true,
                title: "建安工程费用测算表",
                url: "${pageContext.request.contextPath}/marketCost/getBaseDicTree",
                method: "get",
                idField: 'id',//数据表格要有主键
                treeField: 'name',//treegrid 树形结构主键 text
                fitColumns: true,
                striped: true,//显示斑马线
                columns: hypothesisDevelopmentBuild.getColumns(),
                onAfterEdit: function (row, changes) {//在用户完成编辑一行的时候触发
                    //根据id获取值
                    hypothesisDevelopmentBuild.updateChildren($('#' + hypothesisDevelopmentBuild.config().tableId).treegrid('find', row.id), changes);
                }
            });
        }
    };

    /**
     * @author:  zch
     * 描述:更新子节点数据
     * @date:2018-08-13
     **/
    hypothesisDevelopmentBuild.updateChildren = function (data, changes) {
        if (hypothesisDevelopmentBuild.isNotNull(data)) {
            var currency = null;
            var area = null;
            if (changes.currency) {//估价时点单价(元/㎡)
                currency = changes.currency;
                if (AssessCommon.isNumber(currency)) {
                    area = data.area;
                } else {
                    Alert("请输入数字!");
                    return false;
                }
            }
            if (changes.area) {//面积
                area = changes.area;
                if (AssessCommon.isNumber(area)) {
                    currency = data.currency;
                } else {
                    Alert("请输入数字!");
                    return false;
                }
            }
            if (hypothesisDevelopmentBuild.isNotNull(area)) {
                if (hypothesisDevelopmentBuild.isNotNull(currency)) {
                    //更新节点值
                    $('#' + hypothesisDevelopmentBuild.config().tableId).treegrid('update', {
                        id: data.id,
                        row: {currency: currency, area: area}
                    });
                    if (!data.parent) {//说明不是父节点
                        hypothesisDevelopmentBuild.updateFather(data);
                    } else {
                        hypothesisDevelopmentBuild.updateDirectFather();
                    }
                }
            }
        }
    };

    hypothesisDevelopmentBuild.updateDirectFather = function () {
        //更新建安成本小计
        hypothesisDevelopmentBuild.totalCalculation();
    };

    /**
     * @author:  zch
     * 描述:更新父节点数据
     * @date:2018-08-13
     **/
    hypothesisDevelopmentBuild.updateFather = function (data) {
        var parent = $('#' + hypothesisDevelopmentBuild.config().tableId).treegrid('getParent', data.id);
        var childrens = parent.children;
        var area = 0;//建筑面积
        var currency = 0;//单方造价
        if (hypothesisDevelopmentBuild.isNotNull(childrens)) {
            $.each(childrens, function (i, n) {
                currency += Number(n.currency);
                area += Number(n.area);
            });
        }
        parent.currency = currency;
        parent.area = area;
        //更新节点值
        $('#' + hypothesisDevelopmentBuild.config().tableId).treegrid('update', {
            id: parent.id,
            row: parent
        });
        //更新建安成本小计
        hypothesisDevelopmentBuild.totalCalculation();
    };

    /**
     * @author:  zch
     * 描述:建安成本小计
     * @date:2018-08-14
     **/
    hypothesisDevelopmentBuild.totalCalculation = function () {
        var area = 0;//建筑面积
        var currency = 0;//单方造价
        $.each($('#' + hypothesisDevelopmentBuild.config().tableId).treegrid('getRoots'), function (i, n) {
            currency += Number(n.currency);
            area += Number(n.area);
        });
        hypothesisDevelopmentBuild.updateHtml({
            area: area,
            currency: currency,
        });
    };
    /**
     * @author:  zch
     * 描述:建安成本小计 写html value
     * @date:2018-08-14
     **/
    hypothesisDevelopmentBuild.updateHtml = function (data) {
        var area = $('.' + hypothesisDevelopmentBuild.config().areaClass).html();
        area = Number(area);
        area += Number(data.area);
        var totalCost = 0;
        totalCost = Number(area) * Number(data.currency);
        $('.' + hypothesisDevelopmentBuild.config().areaClass).html(area);
        $('.' + hypothesisDevelopmentBuild.config().currencyClass).html(data.currency);
        $('.' + hypothesisDevelopmentBuild.config().totalCostClass).html(totalCost);
    };

    /**
     * @author:  zch
     * 描述:获取建安成本小计
     * @date:2018-08-14
     **/
    hypothesisDevelopmentBuild.getCalculatedResults = function () {
        return $('.' + hypothesisDevelopmentBuild.config().totalCostClass).html();
    };


    /**
     * @author:  zch
     * 描述:封装临时的方法到$datagrid上 enableCellEditing,editCell
     * @date:2018-08-13
     **/
    hypothesisDevelopmentBuild.extendOverwrite = function () {
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
    };

    hypothesisDevelopmentBuild.viewInit = function () {
        hypothesisDevelopmentBuild.extendOverwrite();
        hypothesisDevelopmentBuild.init();
        /**
         * @author:  zch
         * 描述:开启单元格编辑
         * @date:2018-08-13
         **/
        $('#' + hypothesisDevelopmentBuild.config().tableId).datagrid().datagrid('enableCellEditing');
    };
</script>