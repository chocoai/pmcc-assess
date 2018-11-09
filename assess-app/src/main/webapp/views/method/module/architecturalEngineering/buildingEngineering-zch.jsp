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
        <table id="buildingEngineering">

        </table>
    </div>
</div>
<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-5 control-label">
            建安成本小计
        </label>
    </div>
    <div class="x-valid">
        <label class="col-sm-7 control-label">
            单方总造价(元/㎡)
        </label>
    </div>
</div>
<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-5 control-label">
            数据计算值:
        </label>
    </div>
    <div class="x-valid">
        <label class="col-sm-7 control-label constructionInstallationEngineeringFeeBCurrencyClass">
            0
        </label>
    </div>
</div>


<script src="${pageContext.request.contextPath}/assets/jquery-easyui-1.5.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript">

    var constructEngineeringObject = new Object();


    /**
     * @author:  zch
     * 描述:判断是否为数字
     * @date:2018-08-13
     **/
    constructEngineeringObject.isNumber = function (obj) {
        if (constructEngineeringObject.isNotNull(obj)) {
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
    };
    constructEngineeringObject.isNotNull = function (obj) {
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
    constructEngineeringObject.config = function () {
        return {
            tableId: "buildingEngineering",
            currencyClass: "constructionInstallationEngineeringFeeBCurrencyClass",//建安成本小计 3个label class
            areaClass: "constructionInstallationEngineeringFeeBAreaClass",
            totalCostClass: "constructionInstallationEngineeringFeeBTotalCostClass"
        };
    };

    var editIndex = null; //必须的局部变量

    /**
     * @author:  zch
     * 描述:读取树形表单数据
     * @date: 2018-08-23
     **/
    constructEngineeringObject.loadData = function () {
        var data = $('#' + constructEngineeringObject.config().tableId).treegrid('getData');
        return data;
    };
    /**
     * @author:  zch
     * 描述:注入修改的数据
     * @date:2018-08-23
     **/
    constructEngineeringObject.setServerData = function (data) {
        this.data = data;
    }

    constructEngineeringObject.getColumns = function () {
        var data = null;
        var precision = 2;//精度为2
        data = [[
            {field: 'number', title: '序号', width: 50},
            {field: 'name', title: '工程名称', width: '35%'},
            {
                field: 'currency',
                title: ' 单方造价(元/㎡)',
                width: 100,
                editor: {type: "numberbox", options: {precision: precision}},
                styler: function (value, row, index) {
                    return 'background-color:#F0F0F0;color:red;';
                }
            },
        ]];
        return data;
    };

    constructEngineeringObject.treeGrIdInit = function (data) {
        $('#' + constructEngineeringObject.config().tableId).treegrid({
            iconCls: 'icon-edit',
            nowrap: false,
            width: 700,
            height: 'auto',
            collapsible: true,
            title: "建安工程费用测算表",
            data: data,
            idField: 'id',//数据表格要有主键
            treeField: 'name',//treegrid 树形结构主键 text
            fitColumns: true,
            striped: true,//显示斑马线
            columns: constructEngineeringObject.getColumns(),
            onAfterEdit: function (row, changes) {//在用户完成编辑一行的时候触发
                //根据id获取值
                constructEngineeringObject.updateChildren($('#' + constructEngineeringObject.config().tableId).treegrid('find', row.id), changes);
            }
        });
    };
    /**
     * @author:  zch
     * 描述:数据表格初始化数据
     * @date:2018-08-13
     **/
    constructEngineeringObject.init = function () {
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
            constructEngineeringObject.treeGrIdInit(data);
        } else {//说明非修改 需要手动从服务器上获取数据
            //说明 由于 easyui 如果是传入的data并且data中是没有初始化过的数据那么无法开启编辑
            console.log("从服务器上获取数据!");
            $('#' + constructEngineeringObject.config().tableId).treegrid({
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
                columns: constructEngineeringObject.getColumns(),
                onAfterEdit: function (row, changes) {//在用户完成编辑一行的时候触发
                    //根据id获取值
                    constructEngineeringObject.updateChildren($('#' + constructEngineeringObject.config().tableId).treegrid('find', row.id), changes);
                }
            });
        }
    };

    /**
     * @author:  zch
     * 描述:更新子节点数据
     * @date:2018-08-13
     **/
    constructEngineeringObject.updateChildren = function (data, changes) {
        if (constructEngineeringObject.isNotNull(data)) {
            var area = null;
            var currency = null;

            if (changes.currency) {
                currency = changes.currency;
                if (constructEngineeringObject.isNumber(currency)) {

                } else {
                    Alert("请输入数字!");
                    return false;
                }
            }
            if (constructEngineeringObject.isNotNull(currency)) {
                //更新节点值
                $('#' + constructEngineeringObject.config().tableId).treegrid('update', {
                    id: data.id,
                    row: data
                });
                if (!data.parent) {//说明不是父节点
                    constructEngineeringObject.updateFather(data);
                } else {
                    constructEngineeringObject.updateDirectFather(data);
                }
            }


        }
    };
    constructEngineeringObject.updateDirectFather = function (data) {
        var roots = $('#' + constructEngineeringObject.config().tableId).treegrid('getRoots', data.id);
        //更新建安成本小计
        constructEngineeringObject.totalCalculation();
    };

    /**
     * @author:  zch
     * 描述:更新父节点数据
     * @date:2018-08-13
     **/
    constructEngineeringObject.updateFather = function (data) {
        var parent = $('#' + constructEngineeringObject.config().tableId).treegrid('getParent', data.id);
        var childrens = parent.children;
        var currency = 0;
        if (constructEngineeringObject.isNotNull(childrens)) {
            $.each(childrens, function (i, n) {
                currency += parseFloat(constructEngineeringObject.specialTreatment(n.currency));
            });
        }
        parent.currency = currency;
        //更新节点值
        $('#' + constructEngineeringObject.config().tableId).treegrid('update', {
            id: parent.id,
            row: parent
        });
        //更新建安成本小计
        constructEngineeringObject.totalCalculation();
    };

    /**
     * @author:  zch
     * 描述:建安成本小计
     * @date:2018-08-14
     **/
    constructEngineeringObject.totalCalculation = function () {
        var currency = 0;
        var area = 0;
        var totalCost = 0;
        $.each($('#' + constructEngineeringObject.config().tableId).treegrid('getRoots'), function (i, n) {
            currency += parseFloat(constructEngineeringObject.specialTreatment(n.currency));
        });
        constructEngineeringObject.updateHtml(currency, area, totalCost);
    };
    /**
     * @author:  zch
     * 描述:建安成本小计 写html value
     * @date:2018-08-14
     **/
    constructEngineeringObject.updateHtml = function (currency, area, totalCost) {
        $('.' + constructEngineeringObject.config().currencyClass).html(currency);
    };

    /**
     * @author:  zch
     * 描述:获取建安成本小计
     * @date:2018-08-14
     **/
    constructEngineeringObject.getCalculatedResults = function () {
        return $('.' + constructEngineeringObject.config().currencyClass).html();
    };

    /**
     * @author:  zch
     * 描述:特别处理
     * @date:
     **/
    constructEngineeringObject.specialTreatment = function (obj) {
        if (constructEngineeringObject.isNotNull(obj)) {
            return obj;
        }
        return 0;
    };

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
    };

    constructEngineeringObject.viewInit = function () {
        constructEngineeringObject.extendOverwrite();
        constructEngineeringObject.init();
        /**
         * @author:  zch
         * 描述:开启单元格编辑
         * @date:2018-08-13
         **/
        $(function () {
            $('#' + constructEngineeringObject.config().tableId).datagrid().datagrid('enableCellEditing');
        });
    };

</script>
