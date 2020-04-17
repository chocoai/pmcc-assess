<%--
 楼梯
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    楼梯信息
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
                <table class="table table-bordered" id="BasicUnitStairsList">
                    <!-- cerare document add ajax data-->
                </table>
            </form>
        </div>
    </div>
</div>

<script>

    var unitStairs;

    (function () {
        unitStairs = function () {

        };
        unitStairs.prototype = {
            isNotNull: function (item) {
                if (item) {
                    return true;
                }
                return false;
            },
            config: function () {
                var data = {};
                data.table = "BasicUnitStairsList";
                return data;
            },
            loadDataDicList: function () {
                var cols = commonColumn.unitStairsColumn();
                $("#" + unitStairs.prototype.config().table).bootstrapTable('destroy');
                TableInit(unitStairs.prototype.config().table, getContextPath() + "/basicUnitStairs/getBootstrapTableVo", cols, {
                    unitId: unitCommon.getUnitId()
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
            }
        };

        //绑定事件
        $('#' + unitStairs.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
            unitStairs.prototype.loadDataDicList();
        })
    })();

</script>



