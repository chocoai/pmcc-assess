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
        <h4>电梯信息
        </h4>
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
        unitElevator = function () {

        };
        unitElevator.prototype = {
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
                var cols = commonColumn.unitElevatorColumn();
                $("#" + unitElevator.prototype.config().table).bootstrapTable('destroy');
                TableInit(unitElevator.prototype.config().table, "${pageContext.request.contextPath}/caseUnitElevator/getCaseUnitElevatorList", cols, {
                    unitId: '${empty caseUnit.id?0:caseUnit.id}'
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



</html>

