<%--
  地铁
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="matchingMetro.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>地铁信息</h4>
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
                    <table class="table table-bordered" id="MatchingMetroList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var matchingMetro;
    (function () {
        var flag = true;
        matchingMetro = function () {

        };
        matchingMetro.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            viewInit: function () {
                matchingMetro.prototype.loadDataDicList();
            },
            config: function () {
                var data = {};
                data.table = "MatchingMetroList";
                data.box = "divBoxMatchingMetro";
                data.frm = "frmMatchingMetro";
                data.type = "metro";//根据 ExamineMatchingTrafficTypeEnum 配置
                return data;
            },
            loadDataDicList: function () {
                var cols = commonColumn.matchingTransitColumn();
                $("#" + matchingMetro.prototype.config().table).bootstrapTable('destroy');
                TableInit(matchingMetro.prototype.config().table, "${pageContext.request.contextPath}/caseMatchingTraffic/getCaseMatchingTrafficList", cols, {
                    type: matchingMetro.prototype.config().type,
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








