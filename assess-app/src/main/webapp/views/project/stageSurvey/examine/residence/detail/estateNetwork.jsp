<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>通信网络</h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="examineEstateNetworkList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script>
    var estateNetwork;
    (function () {
        estateNetwork = function () {

        };
        estateNetwork.prototype = {
            config: function () {
                var data = {};
                data.table = "examineEstateNetworkList";
                data.box = "divBoxExamineEstateNetwork";
                data.frm = "frmExamineEstateNetwork";
                return data;
            },
            isNotBlank: function (item) {
                if (item) {
                    return true;
                }
                return false;
            },
            loadDataDicList: function () {
                var cols = commonColumn.estateNetworkColumn();
                $("#" + estateNetwork.prototype.config().table).bootstrapTable('destroy');
                TableInit(estateNetwork.prototype.config().table, "${pageContext.request.contextPath}/basicEstateNetwork/getBootstrapTableVo", cols, {
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
        };

        //绑定事件
        $('#' + estateNetwork.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            estateNetwork.prototype.loadDataDicList();
        })
    })();
</script>
