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
        <table id="constructionEngineering">

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
            单方造价(元/㎡)
        </label>
    </div>
    <div class="x-valid">
        <label class="col-sm-3 control-label">
            估价时点单价（万元)
        </label>
    </div>
    <div class="x-valid">
        <label class="col-sm-3 control-label">
            估价时点完工程度(万元)
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
        <label class="col-sm-3 control-label constructionInstallationEngineeringFeeBCurrencyClassA">
            0
        </label>
    </div>
    <div class="x-valid">
        <label class="col-sm-3 control-label constructionInstallationEngineeringFeeBValuationDateCurrencyClassA">
            0
        </label>
    </div>
    <div class="x-valid">
        <label class="col-sm-3 control-label constructionInstallationEngineeringFeeBValuationDateDegreeCompletionClassA">
            0
        </label>
    </div>
</div>


<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript">

    var constructEngineeringObjectA = new Object();

    constructEngineeringObjectA.isNotNull = function (obj) {
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
    constructEngineeringObjectA.config = function () {
        return {
            tableId: "constructionEngineering",
            currencyClass: "constructionInstallationEngineeringFeeBCurrencyClassA",//建安成本小计 3个label class
            valuationDateCurrencyClass: "constructionInstallationEngineeringFeeBValuationDateCurrencyClassA",
            valuationDateDegreeCompletionClass: "constructionInstallationEngineeringFeeBValuationDateDegreeCompletionClassA"
        };
    };

    constructEngineeringObjectA.specialTreatment = function (obj) {
        if (constructEngineeringObjectA.isNotNull(obj)) {
            var nnn = "" + obj + "";
            var str = nnn.substring(nnn.length - 1, nnn.length);
            if (str == '%') {//检测是否为百分比
                str = AssessCommon.percentToPoint(nnn);
                str = Number(str);
                return str;
            }
            return obj;
        }
        return 0;
    };

    var editIndexConstructEngineeringObjectA = null; //必须的局部变量

    /**
     * @author:  zch
     * 描述:读取树形表单数据
     * @date: 2018-08-23
     **/
    constructEngineeringObjectA.loadData = function () {
        var data = $('#' + constructEngineeringObjectA.config().tableId).treegrid('getData');
        return data;
    };

    /**
     * @author:  zch
     * 描述:注入修改的数据
     * @date:2018-08-23
     **/
    constructEngineeringObjectA.setServerData = function (data) {
        this.data = data;
    };

    constructEngineeringObjectA.getColumns = function () {
        var data = null;
        var precision = 2;//精度
        data = [[
            {field: 'number', title: '序号', width: 50},
            {field: 'name', title: '工程名称', width: '20%'},
            {
                field: 'currency',
                title: ' 单方造价(元/㎡)',
                width: 100,
                editor: {type: "numberbox", options: {precision: precision}},//精度为7
                styler: function (value, row, index) {
                    return 'background-color:#F0F0F0;color:red;';
                }
            },
            {
                field: 'valuationDateDegreeCompletion',
                title: '估价时点完工程度',
                width: 110,
                editor: {type: "text", options: {precision: precision}},
                styler: function (value, row, index) {
                    return 'background-color:#F0F0F0;color:red;';
                }
            },
            {
                field: 'valuationDateCurrency',
                title: '估价时点单价(元/㎡)',
                width: 110
            }
        ]];
        return data;
    };

    constructEngineeringObjectA.treeGrIdInit = function (data) {
        $('#' + constructEngineeringObjectA.config().tableId).treegrid({
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
            columns: constructEngineeringObjectA.getColumns(),
            onAfterEdit: function (row, changes) {//在用户完成编辑一行的时候触发
                //根据id获取值
                constructEngineeringObjectA.updateChildren($('#' + constructEngineeringObjectA.config().tableId).treegrid('find', row.id), changes);
            }
        });
    };

    /**
     * @author:  zch
     * 描述:数据表格初始化数据
     * @date:2018-08-13
     **/
    constructEngineeringObjectA.init = function () {
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
            constructEngineeringObjectA.treeGrIdInit(data);
        } else {//说明非修改 需要手动从服务器上获取数据
            console.log("从服务器上获取数据!");
            //说明 由于 easyui 如果是传入的data并且data中是没有初始化过的数据那么无法开启编辑
            $('#' + constructEngineeringObjectA.config().tableId).treegrid({
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
                columns: constructEngineeringObjectA.getColumns(),
                onAfterEdit: function (row, changes) {//在用户完成编辑一行的时候触发
                    //根据id获取值
                    constructEngineeringObjectA.updateChildren($('#' + constructEngineeringObjectA.config().tableId).treegrid('find', row.id), changes);
                }
            });
        }
    };

    /**
     * @author:  zch
     * 描述:计算方法
     * @date:
     **/
    constructEngineeringObjectA.algs = function (data) {
        var currency;//单方造价
        var valuationDateDegreeCompletion;//估价时点完工程度
        var valuationDateCurrency;//估价时点单价(元/㎡)
        currency = Number(data.currency);
        valuationDateDegreeCompletion = constructEngineeringObjectA.specialTreatment(data.valuationDateDegreeCompletion);
        valuationDateCurrency = currency * valuationDateDegreeCompletion;
        data.valuationDateCurrency = valuationDateCurrency;
        return data;
    };


    /**
     * @author:  zch
     * 描述:更新子节点数据
     * @date:2018-08-13
     **/
    constructEngineeringObjectA.updateChildren = function (data, changes) {
        if (constructEngineeringObjectA.isNotNull(data)) {
            var currency = null;
            var valuationDateDegreeCompletion = null;
            if (changes.currency) {//单方造价
                currency = changes.currency;
                if (AssessCommon.isNumber(currency)) {
                    valuationDateDegreeCompletion = data.valuationDateDegreeCompletion;
                } else {
                    Alert("请输入数字!");
                    return false;
                }
            }
            if (changes.valuationDateDegreeCompletion) {//估价时点完工程度
                valuationDateDegreeCompletion = changes.valuationDateDegreeCompletion;
                if (constructEngineeringObjectA.isNotNull(valuationDateDegreeCompletion)) {
                    currency = data.currency;
                } else {
                    Alert("请输入数字!");
                    return false;
                }
            }
            if (constructEngineeringObjectA.isNotNull(currency)) {
                if (constructEngineeringObjectA.isNotNull(valuationDateDegreeCompletion)) {
                    //更新节点值
                    $('#' + constructEngineeringObjectA.config().tableId).treegrid('update', {
                        id: data.id,
                        row: constructEngineeringObjectA.algs(
                            {
                                currency: currency,
                                valuationDateDegreeCompletion: valuationDateDegreeCompletion
                            })
                    });
                    if (!data.parent) {//说明不是父节点
                        constructEngineeringObjectA.updateFather(data);
                    } else {
                        constructEngineeringObjectA.updateDirectFather();
                    }
                }
            }
        }
    };

    constructEngineeringObjectA.updateDirectFather = function () {
        //更新建安成本小计
        constructEngineeringObjectA.totalCalculation();
    };

    /**
     * @author:  zch
     * 描述:更新父节点数据
     * @date:2018-08-13
     **/
    constructEngineeringObjectA.updateFather = function (data) {
        var parent = $('#' + constructEngineeringObjectA.config().tableId).treegrid('getParent', data.id);
        var childrens = parent.children;
        var currency = 0;//单方造价
        var valuationDateDegreeCompletion = 0;//估价时点完工程度
        var valuationDateCurrency = 0;//估价时点单价(元/㎡)

        if (constructEngineeringObjectA.isNotNull(childrens)) {
            $.each(childrens, function (i, n) {
                currency += Number(n.currency);
                valuationDateCurrency += Number(n.valuationDateCurrency);
                valuationDateDegreeCompletion += Number(constructEngineeringObjectA.specialTreatment(n.valuationDateDegreeCompletion));
            });
        }

        parent.currency = currency;
        parent.valuationDateDegreeCompletion = AssessCommon.pointToPercent(valuationDateDegreeCompletion);
        parent.valuationDateCurrency = valuationDateCurrency;
        //更新节点值
        $('#' + constructEngineeringObjectA.config().tableId).treegrid('update', {
            id: parent.id,
            row: parent
        });
        //更新建安成本小计
        constructEngineeringObjectA.totalCalculation();
    };

    /**
     * @author:  zch
     * 描述:建安成本小计
     * @date:2018-08-14
     **/
    constructEngineeringObjectA.totalCalculation = function () {
        var currency = 0;//单方造价
        var valuationDateDegreeCompletion = "";//估价时点完工程度
        var valuationDateCurrency = 0;//估价时点单价(元/㎡)
        $.each($('#' + constructEngineeringObjectA.config().tableId).treegrid('getRoots'), function (i, n) {
            currency += Number(n.currency);
            valuationDateCurrency += Number(n.valuationDateCurrency);
            valuationDateDegreeCompletion += Number(constructEngineeringObjectA.specialTreatment(n.valuationDateDegreeCompletion));
        });
        constructEngineeringObjectA.updateHtml({
            currency: currency,
            valuationDateDegreeCompletion: valuationDateDegreeCompletion,
            valuationDateCurrency: valuationDateCurrency
        });
    };

    /**
     * @author:  zch
     * 描述:建安成本小计 写html value
     * @date:2018-08-14
     **/
    constructEngineeringObjectA.updateHtml = function (data) {
        $('.' + constructEngineeringObjectA.config().currencyClass).html(data.currency);
        $('.' + constructEngineeringObjectA.config().valuationDateCurrencyClass).html(data.valuationDateCurrency);
        $('.' + constructEngineeringObjectA.config().valuationDateDegreeCompletionClass).html(data.valuationDateDegreeCompletion);
    };

    /**
     * @author:  zch
     * 描述:获取建安成本小计
     * @date:2018-08-14
     **/
    constructEngineeringObjectA.getCalculatedResults = function () {
        return $('.' + constructEngineeringObjectA.config().valuationDateCurrencyClass).html();
    };

    /**
     * @author:  zch
     * 描述:特别处理
     * @date:
     **/
    constructEngineeringObjectA.specialTreatment = function (obj) {
        if (constructEngineeringObjectA.isNotNull(obj)) {
            var nnn = "" + obj + "";
            var str = nnn.substring(nnn.length - 1, nnn.length);
            if (str == '%') {//检测是否为百分比
                str = AssessCommon.percentToPoint(nnn);
                str = Number(str);
                return str;
            }
            return obj;
        }
        return 0;
    };

    /**
     * @author:  zch
     * 描述:封装临时的方法到$datagrid上 enableCellEditing,editCell
     * @date:2018-08-13
     **/
    constructEngineeringObjectA.extendOverwrite = function () {
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
                        editIndexConstructEngineeringObjectA = index;
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

    constructEngineeringObjectA.viewInit = function () {
        constructEngineeringObjectA.extendOverwrite();
        constructEngineeringObjectA.init();
        /**
         * @author:  zch
         * 描述:开启单元格编辑
         * @date:2018-08-13
         **/
        $('#' + constructEngineeringObjectA.config().tableId).datagrid().datagrid('enableCellEditing');
    }
</script>