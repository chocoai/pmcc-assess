<%--
  供热
--%>

<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="estateSupplyHeating.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>供热信息
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
                    <table class="table table-bordered" id="EstateSupplyHeatingList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var estateSupplyHeating;
    (function () {
        var flag = true;
        estateSupplyHeating = function () {

        };
        estateSupplyHeating.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            viewInit: function () {
                estateSupplyHeating.prototype.loadDataDicList();
            },
            config: function () {
                var data = {};
                data.table = "EstateSupplyHeatingList";
                data.box = "divBoxEstateSupplyHeating";
                data.frm = "frmEstateSupplyHeating";
                data.type = "estateSupplyHeating";//根据 ExamineEstateSupplyEnumType 配置
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'name', title: '供应商名称'});
                cols.push({field: 'lineGradeName', title: '供热保障等级'});
                cols.push({field: 'reputationName', title: '供热商信誉'});
                cols.push({field: 'gradeName', title: '供热商等级'});
                cols.push({field: 'power', title: '供应量或功率'});
                $("#" + estateSupplyHeating.prototype.config().table).bootstrapTable('destroy');
                TableInit(estateSupplyHeating.prototype.config().table, "${pageContext.request.contextPath}/caseEstateSupply/getCaseEstateSupplyList", cols, {
                    type: estateSupplyHeating.prototype.config().type,
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

