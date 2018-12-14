<%--
  供水
--%>

<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="estateSupplyWater.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>供水信息
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
                    <table class="table table-bordered" id="EstateSupplyWaterList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var estateSupplyWater;
    (function () {
        var flag = true;
        estateSupplyWater = function () {

        };
        estateSupplyWater.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            viewInit: function () {
                estateSupplyWater.prototype.loadDataDicList();
            },
            config: function () {
                var data = {};
                data.table = "EstateSupplyWaterList";
                data.box = "divBoxEstateSupplyWater";
                data.frm = "frmEstateSupplyWater";
                data.type = "estateSupplyWater";//根据 ExamineEstateSupplyEnumType 配置
                return data;
            },
            loadDataDicList: function () {
                var cols = commonColumn.estateSupplyWaterColumn();
                $("#" + estateSupplyWater.prototype.config().table).bootstrapTable('destroy');
                TableInit(estateSupplyWater.prototype.config().table, "${pageContext.request.contextPath}/caseEstateSupply/getCaseEstateSupplyList", cols, {
                    type: estateSupplyWater.prototype.config().type,
                    estateId: ${empty caseEstate.id?0:caseEstate.id},
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





