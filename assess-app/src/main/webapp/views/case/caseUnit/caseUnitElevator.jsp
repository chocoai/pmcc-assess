<%--
 电梯
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="unitElevator.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>电梯信息
        </h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content collapse">
        <form class="form-horizontal">
            <div class="form-group">
                <div class="x-valid">
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <table class="table table-bordered" id="ExamineUnitElevatorList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var unitElevator;
    (function () {
        var flag = true;
        unitElevator = function () {

        };
        unitElevator.prototype = {
            setFlag:function (flag_) {
                flag = flag_;
            },
            getFlag:function () {
                return flag;
            },
            viewInit: function () {
                unitElevator.prototype.loadDataDicList();
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
                cols.push({field: 'quasiLoadNumber', title: '准载人数'});
                cols.push({field: 'quasiLoadWeight', title: '准载重量'});
                cols.push({field: 'runningSpeed', title: '运行速度'});
                $("#" + unitElevator.prototype.config().table).bootstrapTable('destroy');
                TableInit(unitElevator.prototype.config().table, "${pageContext.request.contextPath}/caseUnitElevator/getCaseUnitElevatorList", cols, {
                    name: $("#queryName").val(),
                    unitId: '${empty caseUnit.id?0:caseUnit.id}'
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
            },

            init:function () {
                
            }
        }

    })();

</script>



</html>

