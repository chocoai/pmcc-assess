<%--
  公交
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>公交信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="TransitList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script>

    var matchingTransit;
    (function () {
        matchingTransit = function () {

        };
        matchingTransit.prototype = {
            isNotBlank: function (item) {
                if (item) {
                    return true;
                }
                return false;
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
                TableInit(matchingTransit.prototype.config().table, "${pageContext.request.contextPath}/basicMatchingTraffic/getBootstrapTableVo", cols, {
                    type: matchingTransit.prototype.config().type,
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
        $('#' + matchingTransit.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            matchingTransit.prototype.loadDataDicList();
        })
    })();

</script>