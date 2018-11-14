<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/11/6
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <h3>楼栋内装情况
    </h3>
    <div>
        <table class="table table-bordered" id="ExamineUnitDecorateList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <h3>
        户型
    </h3>
    <div>
        <table class="table table-bordered" id="UnitHuxingList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <h3>配备电梯
    </h3>
    <div>
        <table class="table table-bordered" id="ExamineUnitElevatorList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script>
    var unitDecorate;
    (function () {
        unitDecorate = function () {

        };
        unitDecorate.prototype = {
            config: function () {
                var data = {};
                data.table = "ExamineUnitDecorateList";
                data.box = "divBoxExamineUnitDecorate";
                data.frm = "frmExamineUnitDecorate";
                return data;
            },
            isNotBlank: function (item) {
                if (item) {
                    return true;
                }
                return false;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'decorationPartName', title: '装修部位'});
                cols.push({field: 'decoratingMaterialName', title: '装修材料'});
                cols.push({field: 'materialPriceName', title: '材料价格区间'});
                cols.push({field: 'constructionTechnologyName', title: '施工工艺'});
                $("#" + unitDecorate.prototype.config().table).bootstrapTable('destroy');
                TableInit(unitDecorate.prototype.config().table, "${pageContext.request.contextPath}/basicUnitDecorate/getBootstrapTableVo", cols, {
                    unitId: ${empty basicUnit.id?0:basicUnit.id},
                    approval:true
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
            }
        }
    })();
    ////----------------------------------
    var unitHuxing;
    (function () {
        unitHuxing = function () {
        };
        unitHuxing.prototype = {
            config: function () {
                var data = {};
                data.table = "UnitHuxingList";
                data.box = "divBoxUnitHuxing";
                data.frm = "frmUnitHuxing";
                data.unitHuxingFileIDFildName = "house_latest_family_planV";
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'description', title: '描述'});
                cols.push({field: 'spanLength', title: '跨长'});
                cols.push({field: 'orientationName', title: '朝向'});
                cols.push({field: 'spanWidth', title: '跨宽'});
                cols.push({field: 'spanNumber', title: '跨数'});
                cols.push({field: 'fileViewName', title: '户型图'});
                cols.push({
                    field: 'houseCategory', title: '房型', formatter: function (value, row, index) {
                        var str = "";
                        if (unitHuxing.prototype.isNotNull(row.houseCategory)){
                            str = unitHuxing.prototype.rule("formatter",JSON.parse(row.houseCategory));
                        }
                        return str;
                    }
                });
                $("#" + unitHuxing.prototype.config().table).bootstrapTable('destroy');
                TableInit(unitHuxing.prototype.config().table, "${pageContext.request.contextPath}/basicUnitHuxing/getBootstrapTableVo", cols, {
                    unitId: ${empty basicUnit.id?0:basicUnit.id},
                    approval:true
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
            },
            /**
             * @author:  zch
             * 描述:户型的类别填写方式【】房【】厅【】厨【】卫【】花园【】阳台
             * @date:
             **/
            rule: function (flag, item) {
                var text = "";
                //格式化
                if (flag == "formatter") {
                    if (unitHuxing.prototype.isNotNull(item.house)) {
                        text += item.house + "房-";
                    }
                    if (unitHuxing.prototype.isNotNull(item.saloon)) {
                        text += item.saloon + "客厅-";
                    }
                    if (unitHuxing.prototype.isNotNull(item.kitchen)) {
                        text += item.kitchen + "厨房-";
                    }
                    if (unitHuxing.prototype.isNotNull(item.toilet)) {
                        text += item.toilet + "卫生间-";
                    }
                    if (unitHuxing.prototype.isNotNull(item.garden)) {
                        text += item.garden + "花园-";
                    }
                    if (unitHuxing.prototype.isNotNull(item.balcony)) {
                        text += item.balcony + "阳台";
                    }
                    return text;
                }
                //转为json存入数据库
                if (flag == "get"){
                    var data = {};
                    if (unitHuxing.prototype.isNotNull(item.house)) {
                        data.house = item.house;
                    }
                    if (unitHuxing.prototype.isNotNull(item.saloon)) {
                        data.saloon = item.saloon;
                    }
                    if (unitHuxing.prototype.isNotNull(item.kitchen)) {
                        data.kitchen = item.kitchen;
                    }
                    if (unitHuxing.prototype.isNotNull(item.toilet)) {
                        data.toilet = item.toilet;
                    }
                    if (unitHuxing.prototype.isNotNull(item.garden)) {
                        data.garden = item.garden;
                    }
                    if (unitHuxing.prototype.isNotNull(item.balcony)) {
                        data.balcony = item.balcony;
                    }
                    return JSON.stringify(data);
                }
                //赋值
                if (flag == "set"){
                    if (unitHuxing.prototype.isNotNull(item.house)) {
                        $("#" + unitHuxing.prototype.config().frm + " input[name='house']").val(item.house);
                    }
                    if (unitHuxing.prototype.isNotNull(item.saloon)) {
                        $("#" + unitHuxing.prototype.config().frm + " input[name='saloon']").val(item.saloon);
                    }
                    if (unitHuxing.prototype.isNotNull(item.kitchen)) {
                        $("#" + unitHuxing.prototype.config().frm + " input[name='kitchen']").val(item.kitchen);
                    }
                    if (unitHuxing.prototype.isNotNull(item.toilet)) {
                        $("#" + unitHuxing.prototype.config().frm + " input[name='toilet']").val(item.toilet);
                    }
                    if (unitHuxing.prototype.isNotNull(item.garden)) {
                        $("#" + unitHuxing.prototype.config().frm + " input[name='garden']").val(item.garden);
                    }
                    if (unitHuxing.prototype.isNotNull(item.balcony)) {
                        $("#" + unitHuxing.prototype.config().frm + " input[name='balcony']").val(item.balcony);
                    }
                }
            },
            isNotNull: function (item) {
                if (item) {
                    return true;
                }
                return false;
            }
        }
    })();

    var unitElevator;
    (function () {
        unitElevator = function () {

        };
        unitElevator.prototype = {
            isNotNull:function (item) {
                if (item){
                    return true;
                }
                return false;
            },
            config: function () {
                var data = {};
                data.table = "ExamineUnitElevatorList";
                data.box = "divBoxExamineUnitElevator";
                data.frm = "frmExamineUnitElevator";
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'number', title: '电梯数量'});
                cols.push({field: 'typeName', title: '电梯类型'});
                cols.push({field: 'quasiLoadNumber', title: '准载人数'});
                cols.push({field: 'quasiLoadWeight', title: '准载重量'});
                cols.push({field: 'runningSpeed', title: '运行速度'});
                $("#" + unitElevator.prototype.config().table).bootstrapTable('destroy');
                TableInit(unitElevator.prototype.config().table, "${pageContext.request.contextPath}/basicUnitElevator/getBootstrapTableVo", cols, {
                    unitId: ${empty basicUnit.id?0:basicUnit.id},
                    approval:true
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
            }
        }
    })();

</script>