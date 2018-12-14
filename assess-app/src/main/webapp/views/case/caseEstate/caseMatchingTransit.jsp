<%--
  公交
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="matchingTransit.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>公交信息</h4>
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
                    <table class="table table-bordered" id="TransitList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var matchingTransit;
    (function () {
        var flag = true;
        matchingTransit = function () {

        };
        matchingTransit.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            viewInit: function () {
                matchingTransit.prototype.loadDataDicList();
            },
            config: function () {
                var data = {};
                data.table = "TransitList";
                data.box = "divBoxTransit";
                data.frm = "frmTransit";
                data.type = "transit";//根据ExamineMatchingTrafficTypeEnum 配置
                return data;
            },
            loadDataDicList: function () {
                var cols = commonColumn.matchingTransitColumn();
                $("#" + matchingTransit.prototype.config().table).bootstrapTable('destroy');
                TableInit(matchingTransit.prototype.config().table, "${pageContext.request.contextPath}/caseMatchingTraffic/getCaseMatchingTrafficList", cols, {
                    type: matchingTransit.prototype.config().type,
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




