<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/11/6
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>楼栋内装情况</h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="ExamineUnitDecorateList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>户型</h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="UnitHuxingList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>配备电梯</h4>
    </div>
    <div class="x_content collapse">
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
                cols.push({field: 'location', title: '所在位置'});
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

        //绑定事件
        $('#' + unitDecorate.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            unitDecorate.prototype.loadDataDicList();
        })
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
                cols.push({field: 'name', title: '户型'});
                cols.push({field: 'area', title: '面积'});
                cols.push({field: 'orientationName', title: '朝向'});
                cols.push({field: 'spanLength', title: '跨长'});
                cols.push({field: 'spanWidth', title: '跨宽'});
                cols.push({field: 'spanNumber', title: '跨数'});
                cols.push({field: 'description', title: '描述'});
                cols.push({field: 'fileViewName', title: '户型图'});
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
            isNotNull: function (item) {
                if (item) {
                    return true;
                }
                return false;
            }
        }

        //绑定事件
        $('#' + unitHuxing.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            unitHuxing.prototype.loadDataDicList();
        })
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
                cols.push({field: 'maintenance', title: '电梯维护情况'});
                cols.push({field: 'typeName', title: '电梯类型'});
                cols.push({field: 'brand', title: '电梯品牌'});
                cols.push({field: 'number', title: '电梯数量'});
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

        //绑定事件
        $('#' + unitElevator.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            unitElevator.prototype.loadDataDicList();
        })
    })();

</script>