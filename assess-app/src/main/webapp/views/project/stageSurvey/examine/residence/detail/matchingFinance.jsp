<%--
  金融服务
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>金融服务信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="MatchingFinanceList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script>

    var matchingFinance;
    (function () {
        matchingFinance = function () {

        };
        matchingFinance.prototype = {
            isNotBlank: function (item) {
                if (item) {
                    return true;
                }
                return false;
            },
            config: function () {
                var data = {};
                data.table = "MatchingFinanceList";
                data.box = "divBoxMatchingFinance";
                data.frm = "frmMatchingFinance";
                return data;
            },
            loadDataDicList: function () {
                var cols = commonColumn.matchingFinanceColumn();
                $("#" + matchingFinance.prototype.config().table).bootstrapTable('destroy');
                TableInit(matchingFinance.prototype.config().table, "${pageContext.request.contextPath}/basicMatchingFinance/getBootstrapTableVo", cols, {
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
            },
        }

        //绑定事件
        $('#' + matchingFinance.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            matchingFinance.prototype.loadDataDicList();
        })
    })();

</script>