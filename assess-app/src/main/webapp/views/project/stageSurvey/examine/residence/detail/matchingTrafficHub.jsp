<%--
  交通枢纽
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>交通枢纽信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="MatchingTrafficHubList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script>
    var matchingTrafficHub;
    (function () {
        matchingTrafficHub = function () {

        };
        matchingTrafficHub.prototype = {
            isNotBlank: function (item) {
                if (item) {
                    return true;
                }
                return false;
            },
            config: function () {
                var data = {};
                data.table = "MatchingTrafficHubList";
                data.box = "divBoxMatchingTrafficHub";
                data.frm = "frmMatchingTrafficHub";
                data.type = "trafficHub";//根据ExamineMatchingTrafficTypeEnum 配置
                return data;
            },
            loadDataDicList: function () {
                var cols = commonColumn.matchingTrafficHubColumn();
                $("#" + matchingTrafficHub.prototype.config().table).bootstrapTable('destroy');
                TableInit(matchingTrafficHub.prototype.config().table, "${pageContext.request.contextPath}/basicMatchingTraffic/getBootstrapTableVo", cols, {
                    type: matchingTrafficHub.prototype.config().type,
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
        $('#' + matchingTrafficHub.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            matchingTrafficHub.prototype.loadDataDicList();
        })
    })();
</script>