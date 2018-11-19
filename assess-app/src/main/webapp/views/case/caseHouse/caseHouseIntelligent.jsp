<%--
 电力通讯网络
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="houseIntelligent.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>电力通讯网络
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
                    <table class="table table-bordered" id="HouseIntelligentList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var houseIntelligent;
    (function () {
        var flag = true;
        houseIntelligent = function () {

        };
        houseIntelligent.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            viewInit: function () {
                houseIntelligent.prototype.loadDataDicList();

            },
            config: function () {
                var data = {};
                data.table = "HouseIntelligentList";
                data.box = "divBoxHouseIntelligent";
                data.frm = "frmHouseIntelligent";
                data.type = "null";//
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'wireErectionName', title: '电线架设方式'});
                cols.push({field: 'switchCircuitName', title: '开关回路'});
                cols.push({field: 'lampsLanternsName', title: '灯具'});
                cols.push({field: 'internalCommunicationName', title: '屋内通讯'});
                cols.push({field: 'monitoringSystemName', title: '监控系统'});
                cols.push({field: 'intelligentSystemName', title: '智能系统'});

                $("#" + houseIntelligent.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseIntelligent.prototype.config().table, "${pageContext.request.contextPath}/caseHouseIntelligent/getCaseHouseIntelligentList", cols, {
                    type: houseIntelligent.prototype.config().type,
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




