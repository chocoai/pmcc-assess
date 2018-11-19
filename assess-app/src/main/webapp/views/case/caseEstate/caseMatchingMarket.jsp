<%--
  购物商场
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="matchingMarket.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>购物商场信息
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
                    <table class="table table-bordered" id="MatchingMarketList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>
</body>

<script type="application/javascript">

    var matchingMarket;
    (function () {
        var flag = true;
        matchingMarket = function () {

        };
        matchingMarket.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            viewInit: function () {
                matchingMarket.prototype.loadDataDicList();
            },
            config: function () {
                var data = {};
                data.table = "MatchingMarketList";
                data.box = "divBoxMatchingMarket";
                data.frm = "frmMatchingMarket";
                data.type = "matchingMarket";// 根据 ExamineMatchingLeisurePlaceTypeEnum 配置
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'name', title: '购物商场名称'});
                cols.push({field: 'categoryName', title: '购物商场类别'});
                cols.push({field: 'gradeName', title: '购物商场档次'});
                cols.push({field: 'distanceName', title: '购物商场距离'});
                $("#" + matchingMarket.prototype.config().table).bootstrapTable('destroy');
                TableInit(matchingMarket.prototype.config().table, "${pageContext.request.contextPath}/caseMatchingLeisurePlace/getCaseMatchingLeisurePlaceList", cols, {
                    type: matchingMarket.prototype.config().type,
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
