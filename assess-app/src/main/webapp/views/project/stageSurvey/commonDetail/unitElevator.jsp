<%--
 电梯
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    电梯信息
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body" style="display: none">
            <form class="form-horizontal">
                <table class="table table-bordered" id="examineUnitElevatorList">
                    <!-- cerare document add ajax data-->
                </table>
            </form>
        </div>
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
        $('#' + unitElevatorDetail.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
            unitElevatorDetail.prototype.loadDataDicList();
        })
    })();
</script>
