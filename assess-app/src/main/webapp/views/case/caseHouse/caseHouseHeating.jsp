<%--
 供暖情况
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="houseHeating.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>供暖情况
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
                    <table class="table table-bordered" id="HouseHeatingList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var houseHeating;
    (function () {
        var flag = true;
        houseHeating = function () {

        };
        houseHeating.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            viewInit: function () {
                houseHeating.prototype.loadDataDicList();

            },
            config: function () {
                var data = {};
                data.table = "HouseHeatingList";
                data.box = "divBoxHouseHeating";
                data.frm = "frmHouseHeating";
                data.type = "houseHeating";//根据 ExamineHouseEquipmentTypeEnum 配置
                return data;
            },
            loadDataDicList: function () {
                var cols = commonColumn.houseHeatingColumn();
                $("#" + houseHeating.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseHeating.prototype.config().table, "${pageContext.request.contextPath}/caseHouseEquipment/getCaseHouseEquipmentList", cols, {
                    type: houseHeating.prototype.config().type,
                    houseId:'${empty caseHouse.id?0:caseHouse.id}'
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
            },

        }
    })();

</script>



</html>





