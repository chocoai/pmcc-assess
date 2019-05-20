<%--
  车位信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>车位</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="estateParkingList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script>
    var estateParking;
    (function () {
        estateParking = function () {

        };
        estateParking.prototype = {
            config: function () {
                var data = {};
                data.table = "estateParkingList";
                data.box = "divBoxEstateParking";
                data.frm = "frmEstateParking";
                data.fileIDName = "house_estateParking";
                return data;
            },
            isNotBlank: function (item) {
                if (item) {
                    return true;
                }
                return false;
            },
            loadDataDicList: function () {
                var cols = commonColumn.estateParkingColumn();
                $("#" + estateParking.prototype.config().table).bootstrapTable('destroy');
                TableInit(estateParking.prototype.config().table, "${pageContext.request.contextPath}/basicEstateParking/getBootstrapTableVo", cols, {
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
        $('#' + estateParking.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            estateParking.prototype.loadDataDicList();
        })
    })();
</script>
