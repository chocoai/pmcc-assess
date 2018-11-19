<%--
  餐饮
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="matchingRestaurant.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>餐饮信息
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
                    <table class="table table-bordered" id="MatchingRestaurantList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var matchingRestaurant;
    (function () {
        var flag = true;
        matchingRestaurant = function () {

        };
        matchingRestaurant.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            viewInit: function () {
                matchingRestaurant.prototype.loadDataDicList();
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
                var cols = [];
                cols.push({field: 'name', title: '餐饮名称'});
                cols.push({field: 'categoryName', title: '餐饮类别'});
                cols.push({field: 'gradeName', title: '餐饮档次'});
                cols.push({field: 'distanceName', title: '餐饮距离'});
                $("#" + matchingRestaurant.prototype.config().table).bootstrapTable('destroy');
                TableInit(matchingRestaurant.prototype.config().table, "${pageContext.request.contextPath}/caseMatchingLeisurePlace/getCaseMatchingLeisurePlaceList", cols, {
                    type: matchingRestaurant.prototype.config().type,
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
