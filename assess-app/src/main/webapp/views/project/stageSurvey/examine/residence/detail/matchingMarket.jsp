<%--
  购物商场
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>购物商场信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="MatchingMarketList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script>
    var matchingMarket;
    (function () {
        matchingMarket = function () {

        };
        matchingMarket.prototype = {
            isNotBlank: function (item) {
                if (item) {
                    return true;
                }
                return false;
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
                var cols = commonColumn.matchingMarketColumn();
                $("#" + matchingMarket.prototype.config().table).bootstrapTable('destroy');
                TableInit(matchingMarket.prototype.config().table, "${pageContext.request.contextPath}/basicMatchingLeisurePlace/getBootstrapTableVo", cols, {
                    type: matchingMarket.prototype.config().type,
                    estateId: ${empty basicEstate.id?0:basicEstate.id},
                    approval: true
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
        $('#' + matchingMarket.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            matchingMarket.prototype.loadDataDicList();
        })
    })();
</script>
