<%--
  房屋配套设备设施
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel" id="industryCorollaryEquipment">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>房屋配套设备设施信息</h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="HouseCorollaryEquipmentList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>
<script>

    var houseCorollaryEquipment;
    (function () {
        houseCorollaryEquipment = function () {

        };
        houseCorollaryEquipment.prototype = {
            isNotBlank: function (item) {
                if (item) {
                    return true;
                }
                return false;
            },
            config: function () {
                var data = {};
                data.table = "HouseCorollaryEquipmentList";
                data.box = "divBoxHouseCorollaryEquipment";
                data.frm = "frmHouseCorollaryEquipment";
                data.FileID = "positionDiagramFileID";// ExamineFileUpLoadTwoFieldEnum
                return data;
            },
            loadDataDicList: function () {
                var cols = commonColumn.houseCorollaryEquipmentColumn();
                $("#" + houseCorollaryEquipment.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseCorollaryEquipment.prototype.config().table, "${pageContext.request.contextPath}/basicHouseCorollaryEquipment/getBootstrapTableVo", cols, {
                    houseId: '${empty basicHouse.id?0:basicHouse.id}',
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
        $('#' + houseCorollaryEquipment.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            houseCorollaryEquipment.prototype.loadDataDicList();
        })
    })();

</script>
