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
<div class="form-group">
    <div class="col-sm-12">
        <table id="hypothesisDevelopmentBuildID">

        </table>
    </div>
</div>
<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-2 control-label">
            建安成本小计
        </label>
    </div>
    <div class="x-valid">
        <label class="col-sm-2 control-label">
            建筑面积（㎡）
        </label>
    </div>
    <div class="x-valid">
        <label class="col-sm-2 control-label">
            单方造价(元/㎡)
        </label>
    </div>
    <div class="x-valid">
        <label class="col-sm-2 control-label">
            总造价（万元）
        </label>
    </div>
    <div class="x-valid">
        <label class="col-sm-2 control-label">
            估价时点总价（万元)
        </label>
    </div>
    <div class="x-valid">
        <label class="col-sm-2 control-label">
            续建投入总价(万元)
        </label>
    </div>
</div>
<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-2 control-label">
            数据计算值:
        </label>
    </div>
    <div class="x-valid">
        <label class="col-sm-2 control-label constructionInstallationEngineeringFeeBAreaClassA">
            0
        </label>
    </div>
    <div class="x-valid">
        <label class="col-sm-2 control-label constructionInstallationEngineeringFeeBCurrencyClassA">
            0
        </label>
    </div>
    <div class="x-valid">
        <label class="col-sm-2 control-label constructionInstallationEngineeringFeeBTotalCostClassA">
            0
        </label>
    </div>
    <div class="x-valid">
        <label class="col-sm-2 control-label valuationDateTotalClassA">
            0
        </label>
    </div>
    <div class="x-valid">
        <label class="col-sm-2 control-label continuedConstructionInvestmentTotalClassA">
            0
        </label>
    </div>
</div>


<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript">

    var hypothesisDevelopmentBuild = new Object();

    /**
     * @author:  zch
     * 描述:加法
     * @date:2018-08-13
     **/
    hypothesisDevelopmentBuild.add = function (arg1, arg2) {
        arg1 = arg1.toString(), arg2 = arg2.toString();
        var arg1Arr = arg1.split("."), arg2Arr = arg2.split("."), d1 = arg1Arr.length == 2 ? arg1Arr[1] : "",
            d2 = arg2Arr.length == 2 ? arg2Arr[1] : "";
        var maxLen = Math.max(d1.length, d2.length);
        var m = Math.pow(10, maxLen);
        var result = Number(((arg1 * m + arg2 * m) / m).toFixed(maxLen));
        var d = arguments[2];
        return typeof d === "number" ? Number((result).toFixed(d)) : result;
    }

    /**
     * @author:  zch
     * 参数：arg1：除数；arg2被除数；d要保留的小数位数（可以不传此参数，如果不传则不处理小数位数)
     * 描述:除法函数，用来得到精确的除法结果 如:3,2 = 1.5
     * @date:2018-08-15
     **/
    hypothesisDevelopmentBuild.div = function (arg1, arg2) {
        if (arg1 != 0) {//除数不能为0
            var r1 = arg1.toString(), r2 = arg2.toString(), m, resultVal, d = arguments[2];
            m = (r2.split(".")[1] ? r2.split(".")[1].length : 0) - (r1.split(".")[1] ? r1.split(".")[1].length : 0);
            resultVal = Number(r1.replace(".", "")) / Number(r2.replace(".", "")) * Math.pow(10, m);
            return typeof d !== "number" ? Number(resultVal) : Number(resultVal.toFixed(parseInt(d)));
        } else {
            return "";
        }
    }

    /**
     * @author:  zch
     * 描述:乘法
     * 参数：arg1：第一个乘数；arg2第二个乘数；d要保留的小数位数（可以不传此参数，如果不传则不处理小数位数)
     * @date:2018-08-13
     **/
    hypothesisDevelopmentBuild.mul = function (arg1, arg2) {
        if (hypothesisDevelopmentBuild.isNumber(arg1)) {
            if (hypothesisDevelopmentBuild.isNumber(arg2)) {
                var r1 = arg1.toString(), r2 = arg2.toString(), m, resultVal, d = arguments[2];
                m = (r1.split(".")[1] ? r1.split(".")[1].length : 0) + (r2.split(".")[1] ? r2.split(".")[1].length : 0);
                resultVal = Number(r1.replace(".", "")) * Number(r2.replace(".", "")) / Math.pow(10, m);
                return typeof d !== "number" ? Number(resultVal) : Number(resultVal.toFixed(parseInt(d)));
            }
        }
    }

    /**
     * @author:  zch
     * 描述:百分数转小数
     * @date:
     **/
    hypothesisDevelopmentBuild.toPoint = function (percent) {
        var str = percent.replace("%", "");
        str = str / 100;
        return str;
    }
    /**
     * @author:  zch
     * 描述:小数转百分数
     * *这里需要先用Number进行数据类型转换，然后去指定截取转换后的小数点后几位(按照四舍五入)，这里是截取一位，0.1266转换后会变成12.7%
     * @date:
     **/
    hypothesisDevelopmentBuild.toPercent = function (point) {
        var str = Number(point * 100).toFixed(1);
        str += "%";
        return str;
    }

    /**
     * @author:  zch
     * 描述:判断是否为数字
     * @date:2018-08-13
     **/
    hypothesisDevelopmentBuild.isNumber = function (obj) {
        if (hypothesisDevelopmentBuild.isNotNull(obj)) {
            var regPos = /^\d+(\.\d+)?$/; //非负浮点数
            var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
            if (obj == 0) {
                return true;
            }
            if (obj == '0') {
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
    }
    hypothesisDevelopmentBuild.getColumns = function () {
        var data = null;
        data = [[
            {field: 'number', title: '序号', width: 50},
            {field: 'name', title: '工程名称', width: '20%'},
            {
                field: 'area',
                title: '建筑面积',
                width: 90,
                editor: {type: "numberbox", options: {precision: 7}},//精度为7
                styler: function (value, row, index) {
                    return 'background-color:#F0F0F0;color:red;';
                }
            },
            {field: 'currency', title: ' 单方造价(元/㎡)', width: 100},
            {field: 'totalCost', title: '总造价', width: 120},
            {field: 'valuationDateDegreeCompletion', title: '估价时点完工程度', width: 110},
            {field: 'valuationDateTotal', title: '估价时点总价（万元)', width: 110},
            {
                field: 'valuationDateCurrency',
                title: '估价时点单价(元/㎡)',
                width: 110,
                editor: {type: "numberbox", options: {precision: 7}},//精度为7
                styler: function (value, row, index) {
                    return 'background-color:#F0F0F0;color:red;';
                }
            },
            {field: 'continuedConstructionInvestmentTotal', title: '续建投入总价(万元)', width: 110},
            {
                field: 'continuedConstructionInvestmentCurrency',
                title: '续建投入单价(元/㎡)',
                width: 110,
                editor: {type: "numberbox", options: {precision: 7}},//精度为7
                styler: function (value, row, index) {
                    return 'background-color:#F0F0F0;color:red;';
                }
            },
        ]];
        return data;
    }
    hypothesisDevelopmentBuild.treeGrIdInit = function (data) {
        $('#' + hypothesisDevelopmentBuild.config().tableId).treegrid({
            iconCls: 'icon-edit',
            nowrap: false,
            width: 1000,
            height: 'auto',
            collapsible: true,
            title: "建安工程费用测算表",
            data:data,
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
    }

    /**
     * @author:  zch
     * 描述:计算方法
     * @date:
     **/
    hypothesisDevelopmentBuild.algs = function (data) {
        var area;//建筑面积
        var totalCost;//总造价
        var currency;//单方造价
        var valuationDateDegreeCompletion;//估价时点完工程度
        var valuationDateTotal;//估价时点总价（万元）
        var valuationDateCurrency;//估价时点单价(元/㎡)
        var continuedConstructionInvestmentTotal;//续建投入总价（万元）
        var continuedConstructionInvestmentCurrency;//续建投入单价（元/㎡）

        area = data.area;
        valuationDateCurrency = data.valuationDateCurrency;
        continuedConstructionInvestmentCurrency = data.continuedConstructionInvestmentCurrency;

        /**
         * 计算原则:
         * 续建投入总价 = (续建投入单价 * 建筑面积)
         * 估价时点总价 = (估价时点单价 * 建筑面积)
         * 单方造价 = (续建投入单价 + 估价时点单价)
         // * 总造价 = (单方造价 * 建筑面积)
         * 总造价 = (续建投入总价 + 估价时点总价)
         * 估价时点完工程度 = (估价时点总价 / 总造价)
         * 输入值: 续建投入单价,估价时点单价,建筑面积
         **/

        currency = hypothesisDevelopmentBuild.add(valuationDateCurrency, continuedConstructionInvestmentCurrency);
        continuedConstructionInvestmentTotal = hypothesisDevelopmentBuild.mul(continuedConstructionInvestmentCurrency, area);
        valuationDateTotal = hypothesisDevelopmentBuild.mul(valuationDateCurrency, area);
        totalCost = hypothesisDevelopmentBuild.add(continuedConstructionInvestmentTotal, valuationDateTotal);
        var temp = hypothesisDevelopmentBuild.div(valuationDateTotal, totalCost);
        valuationDateDegreeCompletion = hypothesisDevelopmentBuild.toPercent(temp);

        var algs4 = {};
        algs4.area = area;
        algs4.totalCost = totalCost;
        algs4.continuedConstructionInvestmentTotal = continuedConstructionInvestmentTotal;
        algs4.valuationDateTotal = valuationDateTotal;
        algs4.valuationDateDegreeCompletion = valuationDateDegreeCompletion;
        algs4.valuationDateCurrency = valuationDateCurrency;
        algs4.continuedConstructionInvestmentCurrency = continuedConstructionInvestmentCurrency;
        algs4.currency = currency;
        return algs4;
    }


    /**
     * @author:  zch
     * 描述:更新子节点数据
     * @date:2018-08-13
     **/
    hypothesisDevelopmentBuild.updateChildren = function (data, changes) {
        if (hypothesisDevelopmentBuild.isNotNull(data)) {
            var continuedConstructionInvestmentCurrency = null;
            var valuationDateCurrency = null;
            var area = null;
            if (changes.continuedConstructionInvestmentCurrency) {//续建投入单价
                continuedConstructionInvestmentCurrency = changes.continuedConstructionInvestmentCurrency;
                if (hypothesisDevelopmentBuild.isNumber(continuedConstructionInvestmentCurrency)) {
                    valuationDateCurrency = data.valuationDateCurrency;
                    area = data.area;
                } else {
                    Alert("请输入数字!");
                    return false;
                }
            }
            if (changes.valuationDateCurrency) {//估价时点单价(元/㎡)
                valuationDateCurrency = changes.valuationDateCurrency;
                if (hypothesisDevelopmentBuild.isNumber(valuationDateCurrency)) {
                    continuedConstructionInvestmentCurrency = data.continuedConstructionInvestmentCurrency;
                    area = data.area;
                } else {
                    Alert("请输入数字!");
                    return false;
                }
            }
            if (changes.area) {//面积
                area = changes.area;
                if (hypothesisDevelopmentBuild.isNumber(area)) {
                    continuedConstructionInvestmentCurrency = data.continuedConstructionInvestmentCurrency;
                    valuationDateCurrency = data.valuationDateCurrency;
                } else {
                    Alert("请输入数字!");
                    return false;
                }
            }
            if (hypothesisDevelopmentBuild.isNotNull(continuedConstructionInvestmentCurrency)) {
                if (hypothesisDevelopmentBuild.isNotNull(valuationDateCurrency)) {
                    if (hypothesisDevelopmentBuild.isNotNull(area)) {
                        //更新节点值
                        $('#' + hypothesisDevelopmentBuild.config().tableId).treegrid('update', {
                            id: data.id,
                            row: hypothesisDevelopmentBuild.algs(
                                {
                                    area: area,
                                    continuedConstructionInvestmentCurrency: continuedConstructionInvestmentCurrency,
                                    valuationDateCurrency: valuationDateCurrency
                                })
                        });
                        if (!data.parent) {//说明不是父节点
                            hypothesisDevelopmentBuild.updateFather(data);
                        } else {
                            hypothesisDevelopmentBuild.updateDirectFather();
                        }
                    }
                }
            }
        }
    };

    hypothesisDevelopmentBuild.updateDirectFather = function () {
        //更新建安成本小计
        hypothesisDevelopmentBuild.totalCalculation();
    }

    /**
     * @author:  zch
     * 描述:更新父节点数据
     * @date:2018-08-13
     **/
    hypothesisDevelopmentBuild.updateFather = function (data) {
        var parent = $('#' + hypothesisDevelopmentBuild.config().tableId).treegrid('getParent', data.id);
        var childrens = parent.children;
        var area = 0;//建筑面积
        var totalCost = 0;//总造价
        var currency = 0;//单方造价
        var valuationDateDegreeCompletion = "";//估价时点完工程度
        var valuationDateTotal = 0;//估价时点总价（万元）
        var valuationDateCurrency = 0;//估价时点单价(元/㎡)
        var continuedConstructionInvestmentTotal = 0;//续建投入总价（万元）
        var continuedConstructionInvestmentCurrency = 0;//续建投入单价（元/㎡）

        if (hypothesisDevelopmentBuild.isNotNull(childrens)) {
            $.each(childrens, function (i, n) {
                currency = hypothesisDevelopmentBuild.add(currency, hypothesisDevelopmentBuild.specialTreatment(n.currency));
                area = hypothesisDevelopmentBuild.add(area, hypothesisDevelopmentBuild.specialTreatment(n.area));
                totalCost = hypothesisDevelopmentBuild.add(totalCost, hypothesisDevelopmentBuild.specialTreatment(n.totalCost));
                valuationDateTotal = hypothesisDevelopmentBuild.add(valuationDateTotal, hypothesisDevelopmentBuild.specialTreatment(n.valuationDateTotal));
                valuationDateCurrency = hypothesisDevelopmentBuild.add(valuationDateCurrency, hypothesisDevelopmentBuild.specialTreatment(n.valuationDateCurrency));
                continuedConstructionInvestmentTotal = hypothesisDevelopmentBuild.add(continuedConstructionInvestmentTotal, hypothesisDevelopmentBuild.specialTreatment(n.continuedConstructionInvestmentTotal));
                continuedConstructionInvestmentCurrency = hypothesisDevelopmentBuild.add(continuedConstructionInvestmentCurrency, hypothesisDevelopmentBuild.specialTreatment(n.continuedConstructionInvestmentCurrency));
            });
        }
        var temp = hypothesisDevelopmentBuild.div(valuationDateTotal, totalCost);
        valuationDateDegreeCompletion = hypothesisDevelopmentBuild.toPercent(temp);

        parent.currency = currency;
        parent.area = area;
        parent.totalCost = totalCost;
        parent.valuationDateDegreeCompletion = valuationDateDegreeCompletion;
        parent.valuationDateTotal = valuationDateTotal;
        parent.valuationDateCurrency = valuationDateCurrency;
        parent.continuedConstructionInvestmentTotal = continuedConstructionInvestmentTotal;
        parent.continuedConstructionInvestmentCurrency = continuedConstructionInvestmentCurrency;
        //更新节点值
        $('#' + hypothesisDevelopmentBuild.config().tableId).treegrid('update', {
            id: parent.id,
            row: parent
        });
        //更新建安成本小计
        hypothesisDevelopmentBuild.totalCalculation();
    }

    /**
     * @author:  zch
     * 描述:建安成本小计
     * @date:2018-08-14
     **/
    hypothesisDevelopmentBuild.totalCalculation = function () {
        var area = 0;//建筑面积
        var totalCost = 0;//总造价
        var currency = 0;//单方造价
        var valuationDateDegreeCompletion = "";//估价时点完工程度
        var valuationDateTotal = 0;//估价时点总价（万元）
        var valuationDateCurrency = 0;//估价时点单价(元/㎡)
        var continuedConstructionInvestmentTotal = 0;//续建投入总价（万元）
        var continuedConstructionInvestmentCurrency = 0;//续建投入单价（元/㎡）

        $.each($('#' + hypothesisDevelopmentBuild.config().tableId).treegrid('getRoots'), function (i, n) {
            currency = hypothesisDevelopmentBuild.add(currency, hypothesisDevelopmentBuild.specialTreatment(n.currency));
            area = hypothesisDevelopmentBuild.add(area, hypothesisDevelopmentBuild.specialTreatment(n.area));
            totalCost = hypothesisDevelopmentBuild.add(totalCost, hypothesisDevelopmentBuild.specialTreatment(n.totalCost));
            valuationDateTotal = hypothesisDevelopmentBuild.add(valuationDateTotal, hypothesisDevelopmentBuild.specialTreatment(n.valuationDateTotal));
            valuationDateCurrency = hypothesisDevelopmentBuild.add(valuationDateCurrency, hypothesisDevelopmentBuild.specialTreatment(n.valuationDateCurrency));
            continuedConstructionInvestmentTotal = hypothesisDevelopmentBuild.add(continuedConstructionInvestmentTotal, hypothesisDevelopmentBuild.specialTreatment(n.continuedConstructionInvestmentTotal));
            continuedConstructionInvestmentCurrency = hypothesisDevelopmentBuild.add(continuedConstructionInvestmentCurrency, hypothesisDevelopmentBuild.specialTreatment(n.continuedConstructionInvestmentCurrency));
        });
        var temp = hypothesisDevelopmentBuild.div(valuationDateTotal, totalCost);
        valuationDateDegreeCompletion = hypothesisDevelopmentBuild.toPercent(temp);

        hypothesisDevelopmentBuild.updateHtml({
            area: area,
            totalCost: totalCost,
            currency: currency,
            valuationDateDegreeCompletion: valuationDateDegreeCompletion,
            valuationDateTotal: valuationDateTotal,
            valuationDateCurrency: valuationDateCurrency,
            continuedConstructionInvestmentTotal: continuedConstructionInvestmentTotal,
            continuedConstructionInvestmentCurrency: continuedConstructionInvestmentCurrency
        });
    }
    /**
     * @author:  zch
     * 描述:建安成本小计 写html value
     * @date:2018-08-14
     **/
    hypothesisDevelopmentBuild.updateHtml = function (data) {
        $('.' + hypothesisDevelopmentBuild.config().areaClass).html(data.area);
        $('.' + hypothesisDevelopmentBuild.config().currencyClass).html(data.currency);
        $('.' + hypothesisDevelopmentBuild.config().totalCostClass).html(data.totalCost);
        $('.' + hypothesisDevelopmentBuild.config().valuationDateTotalClass).html(data.valuationDateTotal);
        $('.' + hypothesisDevelopmentBuild.config().continuedConstructionInvestmentTotalClass).html(data.continuedConstructionInvestmentTotal);
    }

    /**
     * @author:  zch
     * 描述:获取建安成本小计
     * @date:2018-08-14
     **/
    hypothesisDevelopmentBuild.getCalculatedResults = function () {
        return $('.' + hypothesisDevelopmentBuild.config().totalCostClass).html();
    }

    /**
     * @author:  zch
     * 描述:特别处理
     * @date:
     **/
    hypothesisDevelopmentBuild.specialTreatment = function (obj) {
        if (hypothesisDevelopmentBuild.isNotNull(obj)) {
            return obj;
        }
        return 0;
    }

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
    }

    hypothesisDevelopmentBuild.viewInit = function () {
        hypothesisDevelopmentBuild.extendOverwrite();
        hypothesisDevelopmentBuild.init();
        /**
         * @author:  zch
         * 描述:开启单元格编辑
         * @date:2018-08-13
         **/
        $('#' + hypothesisDevelopmentBuild.config().tableId).datagrid().datagrid('enableCellEditing');
    }

    $(function () {
        // hypothesisDevelopmentBuild.viewInit();
    })
</script>