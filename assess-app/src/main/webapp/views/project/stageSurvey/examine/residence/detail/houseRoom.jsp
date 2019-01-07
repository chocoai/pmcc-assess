<%--
 房间
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>房间</h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="HouseRoomList">
        </table>
    </div>
</div>

<script>

    var houseRoom;
    (function () {
        houseRoom = function () {

        };
        houseRoom.prototype = {
            isEmpty: function (item) {
                if (item) {
                    return true;
                }
                return false;
            },
            config: function () {
                var data = {};
                data.table = "HouseRoomList";
                data.box = "divBoxHouseRoom";
                data.frm = "frmHouseRoom";
                data.tableSubclass = "SubclassHouseRoomList";
                data.boxSubclass = "SubclassDivBoxHouseRoom";
                data.boxSubclassSaveView = "boxSubclassSaveViewHouseRoom";
                data.frmSubclass = "SubclassFrmHouseRoom";
                data.type = "null";//
                return data;
            },
            loadDataDicList: function () {
                var cols = commonColumn.houseRoomColumn();
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="查看" onclick="houseRoom.prototype.showModelSubclass(' + row.id + ',\'tb_List\')"><i class="fa fa-gavel fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                $("#" + houseRoom.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseRoom.prototype.config().table, "${pageContext.request.contextPath}/basicHouseRoom/getBootstrapTableVo", cols, {
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
            },
            showModelSubclass: function (id) {
                houseRoom.prototype.subclassLoadList(id);
                if ($('#' + houseRoom.prototype.config().boxSubclass).find("input[name='roomId']").size() > 0) {
                    $('#' + houseRoom.prototype.config().boxSubclass).find("input[name='roomId']").val(id);
                }
                $('#' + houseRoom.prototype.config().boxSubclass).modal("show");
            },
            subclassLoadList: function (id) {
                var cols = commonColumn.houseRoomDecorateColumn();
                $("#" + houseRoom.prototype.config().tableSubclass).bootstrapTable('destroy');
                TableInit(houseRoom.prototype.config().tableSubclass, "${pageContext.request.contextPath}/basicHouseRoom/getRoomDecorateBootstrapTableVo", cols, {
                    roomId: id,
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
        $('#' + houseRoom.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            houseRoom.prototype.loadDataDicList();
        })
    })();

</script>

<div id="SubclassDivBoxHouseRoom" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="titleContent">房间装修数据</h3>
                <input type="hidden" name="roomId" class="roomId">
            </div>
            <div class="panel-body">
                <table class="table table-bordered" id="SubclassHouseRoomList">
                </table>
            </div>
        </div>
    </div>
</div>
