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
        <table id="underDevelopmentID">

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
            续建投入总价（万元）
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
        <label class="col-sm-3 control-label underDevelopmentAreaClassA">
            0
        </label>
    </div>
    <div class="x-valid">
        <label class="col-sm-3 control-label underDevelopmentIDCurrencyClassA">
            0
        </label>
    </div>
    <div class="x-valid">
        <label class="col-sm-3 control-label underDevelopmentTotalClassA">
            0
        </label>
    </div>
</div>


<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript">

    var underDevelopment = new Object();
    underDevelopment.isNotNull = function (obj) {
        if (obj == 0) {
            return true;
        }
        if (obj) {
            return true;
        }
        return false;
    };
    underDevelopment.config = function () {
        return {
            tableId: "underDevelopmentID",
            currencyClass: "underDevelopmentIDCurrencyClassA",//建安成本小计 3个label class
            areaClass: "underDevelopmentAreaClassA",
            totalCostClass: "underDevelopmentTotalCostClassA",
            valuationDateTotalClass: "underDevelopmentValuationDateTotalClassA",
            continuedConstructionInvestmentTotalClass: "underDevelopmentTotalClassA"
        };
    };

    var editIndexUnderDevelopment = null; //必须的局部变量

    /**
     * @author:  zch
     * 描述:读取树形表单数据
     * @date: 2018-08-23
     **/
    underDevelopment.loadData = function () {
        var data = $('#' + underDevelopment.config().tableId).treegrid('getData');
        return data;
    };

    /**
     * @author:  zch
     * 描述:注入修改的数据
     * @date:2018-08-23
     **/
    underDevelopment.setServerData = function (data) {
        this.data = data;
    };
    underDevelopment.setArea = function (data) {
        $('.' + underDevelopment.config().areaClass).html(data);
    };
    underDevelopment.getColumns = function () {
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
            {field: 'valuationDateCurrency', title: '估价时点单价(元/㎡)', width: 110},
            {field: 'continuedConstructionInvestmentCurrency', title: '续建投入单价(元/㎡)', width: 110}
        ]];
        return data;
    };

    underDevelopment.treeGrIdInit = function (data) {
        $('#' + underDevelopment.config().tableId).treegrid({
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
            columns: underDevelopment.getColumns(),
            onAfterEdit: function (row, changes) {//在用户完成编辑一行的时候触发
                //根据id获取值
                underDevelopment.updateChildren($('#' + underDevelopment.config().tableId).treegrid('find', row.id), changes);
            }
        });
    };

    /**
     * @author:  zch
     * 描述:数据表格初始化数据
     * @date:2018-08-13
     **/
    underDevelopment.init = function () {
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
            underDevelopment.treeGrIdInit(data);
        } else {//说明非修改 需要手动从服务器上获取数据
            console.log("从服务器上获取数据!");
            //说明 由于 easyui 如果是传入的data并且data中是没有初始化过的数据那么无法开启编辑
            $('#' + underDevelopment.config().tableId).treegrid({
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
                columns: underDevelopment.getColumns(),
                onAfterEdit: function (row, changes) {//在用户完成编辑一行的时候触发
                    //根据id获取值
                    underDevelopment.updateChildren($('#' + underDevelopment.config().tableId).treegrid('find', row.id), changes);
                }
            });
        }
    };

    underDevelopment.specialTreatment = function (obj) {
        if (underDevelopment.isNotNull(obj)){
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
    underDevelopment.algs = function (data) {
        var currency = null;
        var area = null;
        var valuationDateDegreeCompletion = null;
        var valuationDateCurrency = null;
        var continuedConstructionInvestmentCurrency = null;
        currency = Number(data.currency);
        area = Number(data.area);
        valuationDateDegreeCompletion = data.valuationDateDegreeCompletion;
        valuationDateDegreeCompletion = underDevelopment.specialTreatment(valuationDateDegreeCompletion);
        valuationDateCurrency = currency * valuationDateDegreeCompletion ;
        continuedConstructionInvestmentCurrency = currency - valuationDateCurrency ;
        data.valuationDateCurrency = valuationDateCurrency;
        data.continuedConstructionInvestmentCurrency = continuedConstructionInvestmentCurrency;
        return data;
    };

    /**
     * @author:  zch
     * 描述:更新子节点数据
     * @date:2018-08-13
     **/
    underDevelopment.updateChildren = function (data, changes) {
        if (underDevelopment.isNotNull(data)) {
            var currency = null;
            var area = null;
            var valuationDateDegreeCompletion = null;
            if (changes.currency) {//单方造价
                currency = changes.currency;
                if (AssessCommon.isNumber(currency)) {
                    area = data.area;
                    valuationDateDegreeCompletion = data.valuationDateDegreeCompletion;
                } else {
                    Alert("请输入数字!");
                    return false;
                }
            }
            if (changes.area) {//面积
                area = changes.area;
                if (AssessCommon.isNumber(area)) {
                    currency = data.currency;
                    valuationDateDegreeCompletion = data.valuationDateDegreeCompletion;
                } else {
                    Alert("请输入数字!");
                    return false;
                }
            }
            if (changes.valuationDateDegreeCompletion) {//面积
                valuationDateDegreeCompletion = changes.valuationDateDegreeCompletion;
                if (underDevelopment.isNotNull(valuationDateDegreeCompletion)) {
                    currency = data.currency;
                    area = data.area;
                } else {
                    Alert("请输入数字!");
                    return false;
                }
            }
            //更新节点值
            $('#' + underDevelopment.config().tableId).treegrid('update', {
                id: data.id,
                row: underDevelopment.algs({
                    currency: currency,
                    area: area,
                    valuationDateDegreeCompletion: valuationDateDegreeCompletion
                })
            });
            if (!data.parent) {//说明不是父节点
                underDevelopment.updateFather(data);
            } else {
                underDevelopment.updateDirectFather();
            }
        }
    };

    underDevelopment.updateDirectFather = function () {
        //更新建安成本小计
        underDevelopment.totalCalculation();
    };

    /**
     * @author:  zch
     * 描述:更新父节点数据
     * @date:2018-08-13
     **/
    underDevelopment.updateFather = function (data) {
        var parent = $('#' + underDevelopment.config().tableId).treegrid('getParent', data.id);
        var childrens = parent.children;
        var area = 0;//建筑面积
        var currency = 0;//单方造价
        var valuationDateDegreeCompletion = 0;
        var valuationDateCurrency = 0;
        var continuedConstructionInvestmentCurrency = 0;
        if (underDevelopment.isNotNull(childrens)) {
            $.each(childrens, function (i, n) {
                currency += Number(n.currency);
                area += Number(n.area);
                continuedConstructionInvestmentCurrency += Number(n.continuedConstructionInvestmentCurrency);
                valuationDateCurrency += Number(n.valuationDateCurrency);
                valuationDateDegreeCompletion += Number(underDevelopment.specialTreatment(n.valuationDateDegreeCompletion));
            });
        }
        parent.currency = currency;
        parent.area = area;
        parent.continuedConstructionInvestmentCurrency = continuedConstructionInvestmentCurrency;
        parent.valuationDateCurrency = valuationDateCurrency;
        parent.valuationDateDegreeCompletion = AssessCommon.pointToPercent(valuationDateDegreeCompletion);
        //更新节点值
        $('#' + underDevelopment.config().tableId).treegrid('update', {
            id: parent.id,
            row: parent
        });
        //更新建安成本小计
        underDevelopment.totalCalculation();
    };

    /**
     * @author:  zch
     * 描述:建安成本小计
     * @date:2018-08-14
     **/
    underDevelopment.totalCalculation = function () {
        var area = 0;//建筑面积
        var currency = 0;//单方造价
        var continuedConstructionInvestmentCurrency = 0;//续建投入总价
        $.each($('#' + underDevelopment.config().tableId).treegrid('getRoots'), function (i, n) {
            currency += Number(n.currency);
            area += Number(n.area);
            continuedConstructionInvestmentCurrency += Number(n.continuedConstructionInvestmentCurrency);
        });
        underDevelopment.updateHtml({
            area: area,
            currency: currency,
            continuedConstructionInvestmentCurrency:continuedConstructionInvestmentCurrency
        });
    };
    /**
     * @author:  zch
     * 描述:建安成本小计 写html value
     * @date:2018-08-14
     **/
    underDevelopment.updateHtml = function (data) {
        var area = $('.' + underDevelopment.config().areaClass).html();
        area = Number(area);
        area += Number(data.area);
        var total = 0;
        total = Number(area) * Number(data.continuedConstructionInvestmentCurrency);
        $('.' + underDevelopment.config().areaClass).html(area);
        $('.' + underDevelopment.config().currencyClass).html(data.currency);
        $('.' + underDevelopment.config().continuedConstructionInvestmentTotalClass).html(total);
    };

    /**
     * @author:  zch
     * 描述:获取建安成本小计
     * @date:2018-08-14
     **/
    underDevelopment.getCalculatedResults = function () {
        return $('.' + underDevelopment.config().continuedConstructionInvestmentTotalClass).html();
    };


    /**
     * @author:  zch
     * 描述:封装临时的方法到$datagrid上 enableCellEditing,editCell
     * @date:2018-08-13
     **/
    underDevelopment.extendOverwrite = function () {
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
                        editIndexUnderDevelopment = index;
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

    underDevelopment.viewInit = function () {
        underDevelopment.extendOverwrite();
        underDevelopment.init();
        /**
         * @author:  zch
         * 描述:开启单元格编辑
         * @date:2018-08-13
         **/
        $('#' + underDevelopment.config().tableId).datagrid().datagrid('enableCellEditing');
    };
</script>