<%--
 供排水情况
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="houseWater.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3> 供排水情况
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
                    <table class="table table-bordered" id="HouseWaterList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var houseWater;
    (function () {
        var flag = true;
        houseWater = function () {

        };
        houseWater.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            viewInit: function () {
                houseWater.prototype.loadDataDicList();

            },
            config: function () {
                var data = {};
                data.table = "HouseWaterList";
                data.box = "divBoxHouseWater";
                data.frm = "frmHouseWater";
                data.type = "null";//
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'natrueIntakePointNumber', title: '自然区间取水点数'});
                cols.push({field: 'intakePointNumber', title: '取水点数'});
                cols.push({field: 'supplyErectionMethodName', title: '供水管架设方式'});
                cols.push({field: 'pretreatedWaterName', title: '前置净水'});
                cols.push({field: 'drainageCircuitName', title: '排水回路'});

                $("#" + houseWater.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseWater.prototype.config().table, "${pageContext.request.contextPath}/caseHouseWater/getCaseHouseWaterList", cols, {
                    type: houseWater.prototype.config().type,
                    houseId:'${empty caseHouse.id?0:caseHouse.id}'
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

