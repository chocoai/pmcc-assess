<%--
  金融服务
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="matchingFinance.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>金融服务信息</h4>
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
                    <table class="table table-bordered" id="MatchingFinanceList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var matchingFinance;
    (function () {
        var flag = true;
        matchingFinance = function () {

        };
        matchingFinance.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            viewInit: function () {
                matchingFinance.prototype.loadDataDicList();
            },
            config: function () {
                var data = {};
                data.table = "MatchingFinanceList";
                data.box = "divBoxMatchingFinance";
                data.frm = "frmMatchingFinance";
                data.type = "null";//
                return data;
            },
            loadDataDicList: function () {
                var cols = commonColumn.matchingFinanceColumn();
                $("#" + matchingFinance.prototype.config().table).bootstrapTable('destroy');
                TableInit(matchingFinance.prototype.config().table, "${pageContext.request.contextPath}/caseMatchingFinance/getCaseMatchingFinanceList", cols, {
                    type: matchingFinance.prototype.config().type,
                    estateId: ${empty caseEstate.id?0:caseEstate.id},
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