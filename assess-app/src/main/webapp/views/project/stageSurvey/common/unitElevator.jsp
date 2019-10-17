<%--
 电梯
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>配备电梯</h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="examineUnitElevatorList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script>
    var unitElevatorDetail;
    (function () {
        unitElevatorDetail = function () {

        };
        unitElevatorDetail.prototype = {
            isNotNull:function (item) {
                if (item){
                    return true;
                }
                return false;
            },
            config: function () {
                var data = {};
                data.table = "examineUnitElevatorList";
                data.box = "divBoxExamineUnitElevator";
                data.frm = "frmExamineUnitElevator";
                return data;
            },
            loadDataDicList: function () {
                var cols = commonColumn.unitElevatorColumn();
                $("#" + unitElevatorDetail.prototype.config().table).bootstrapTable('destroy');
                TableInit(unitElevatorDetail.prototype.config().table, "${pageContext.request.contextPath}/basicUnitElevator/getBootstrapTableVo", cols, {
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
        $('#' + unitElevatorDetail.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            unitElevatorDetail.prototype.loadDataDicList();
        })
    })();
</script>
