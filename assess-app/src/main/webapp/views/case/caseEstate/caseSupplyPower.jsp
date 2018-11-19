<%--
 供电
--%>

<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="estateSupplyPower.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>供电信息
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
                    <table class="table table-bordered" id="EstateSupplyPowerList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var estateSupplyPower;
    (function () {
        var flag = true;
        estateSupplyPower = function () {

        };
        estateSupplyPower.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            viewInit: function () {
                estateSupplyPower.prototype.loadDataDicList();
            },
            config: function () {
                var data = {};
                data.table = "EstateSupplyPowerList";
                data.box = "divBoxEstateSupplyPower";
                data.frm = "frmEstateSupplyPower";
                data.type = "estateSupplyPower";//根据 ExamineEstateSupplyEnumType 配置
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'name', title: '名称'});
                cols.push({field: 'reputation', title: '供电商信誉'});
                // cols.push({field: 'type', title: '类型'});
                cols.push({field: 'gradeName', title: '供电商等级'});
                cols.push({field: 'lineGradeName', title: '供电线路等级'});
                cols.push({field: 'power', title: '功率'});
                $("#" + estateSupplyPower.prototype.config().table).bootstrapTable('destroy');
                TableInit(estateSupplyPower.prototype.config().table, "${pageContext.request.contextPath}/caseEstateSupply/getCaseEstateSupplyList", cols, {
                    type: estateSupplyPower.prototype.config().type,
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


