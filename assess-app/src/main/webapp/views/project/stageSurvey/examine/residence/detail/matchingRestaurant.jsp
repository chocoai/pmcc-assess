<%--
  餐饮
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>餐饮信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="MatchingRestaurantList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script>
    var matchingRestaurant;
    (function () {
        matchingRestaurant = function () {

        };
        matchingRestaurant.prototype = {
            isNotBlank: function (item) {
                if (item) {
                    return true;
                }
                return false;
            },
            config: function () {
                var data = {};
                data.table = "MatchingRestaurantList";
                data.box = "divBoxMatchingRestaurant";
                data.frm = "frmMatchingRestaurant";
                data.type = "matchingRestaurant";// 根据 ExamineMatchingLeisurePlaceTypeEnum 配置
                return data;
            },
            loadDataDicList: function () {
                var cols = commonColumn.matchingRestaurantColumn();
                $("#" + matchingRestaurant.prototype.config().table).bootstrapTable('destroy');
                TableInit(matchingRestaurant.prototype.config().table, "${pageContext.request.contextPath}/basicMatchingLeisurePlace/getBootstrapTableVo", cols, {
                    type: matchingRestaurant.prototype.config().type,
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
        $('#' + matchingRestaurant.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            matchingRestaurant.prototype.loadDataDicList();
        })
    })();
</script>