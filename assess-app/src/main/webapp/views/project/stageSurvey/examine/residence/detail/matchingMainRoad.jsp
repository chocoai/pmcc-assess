<%--
  主干道
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>主干道信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="MainRoadList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script>
    var matchingMainRoad;
    (function () {
        matchingMainRoad = function () {

        };
        matchingMainRoad.prototype = {
            isNotBlank: function (item) {
                if (item) {
                    return true;
                }
                return false;
            },
            config: function () {
                var data = {};
                data.table = "MainRoadList";
                data.box = "divBoxMainRoad";
                data.frm = "frmMainRoad";
                data.type = "mainRoad";//根据ExamineMatchingTrafficTypeEnum 配置
                return data;
            },
            loadDataDicList: function () {
                var cols = commonColumn.matchingMainRoadColumn();
                $("#" + matchingMainRoad.prototype.config().table).bootstrapTable('destroy');
                TableInit(matchingMainRoad.prototype.config().table, "${pageContext.request.contextPath}/basicMatchingTraffic/getBootstrapTableVo", cols, {
                    type: matchingMainRoad.prototype.config().type,
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
        $('#' + matchingMainRoad.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            matchingMainRoad.prototype.loadDataDicList();
        })
    })();
</script>