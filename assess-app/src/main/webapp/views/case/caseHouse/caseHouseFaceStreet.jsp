<%--
 临街（路）状况
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="houseFaceStreet.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>临街（路）
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
                    <table class="table table-bordered" id="HouseFaceStreetList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var houseFaceStreet;
    (function () {
        var flag = true;
        houseFaceStreet = function () {

        };
        houseFaceStreet.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            viewInit: function () {
                houseFaceStreet.prototype.loadDataDicList();
            },
            config: function () {
                var data = {};
                data.table = "HouseFaceStreetList";
                data.box = "divBoxHouseFaceStreet";
                data.frm = "frmHouseFaceStreet";
                data.type = "null";//
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'streetName', title: '名称'});
                cols.push({field: 'streetLevelName', title: '街道级别'});
                cols.push({field: 'trafficFlowName', title: '交通流量'});
                cols.push({field: 'visitorsFlowrateName', title: '人流量'});
                $("#" + houseFaceStreet.prototype.config().table).bootstrapTable('destroy');//examineHouseFaceStreet
                TableInit(houseFaceStreet.prototype.config().table, "${pageContext.request.contextPath}/caseHouseFaceStreet/getCaseHouseFaceStreetList", cols, {
                    type: houseFaceStreet.prototype.config().type,
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


