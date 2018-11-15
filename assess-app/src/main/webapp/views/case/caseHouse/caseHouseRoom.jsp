<%--
 房间
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="houseRoom.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3> 房间
        </h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">

        <form class="form-horizontal">
            <div class="form-group">
                <div class="x-valid">
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <table class="table table-bordered" id="HouseRoomList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var houseRoom;
    (function () {
        var flag = true;
        var sonFlag = true;
        houseRoom = function () {

        };
        houseRoom.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            setSonFlag: function (sonFlag_) {
                sonFlag = sonFlag_;
            },
            getSonFlag: function () {
                return sonFlag;
            },
            viewInit: function () {
                houseRoom.prototype.loadDataDicList();

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
                var cols = [];
                cols.push({field: 'name', title: '房间名称'});
                cols.push({field: 'roomTypeName', title: '房间类型'});
                cols.push({field: 'area', title: '面积'});
                cols.push({field: 'sunshine', title: '日照'});
                cols.push({field: 'lighting', title: '采光'});
                cols.push({field: 'layerHeight', title: '层高'});
                cols.push({field: 'opening', title: '开间'});
                cols.push({field: 'depth', title: '进深'});
                cols.push({field: 'aeration', title: '通风'});
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="查看" onclick="houseRoom.prototype.showModelSubclass(' + row.id + ',\'tb_List\')"><i class="fa fa-search-minus fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                $("#" + houseRoom.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseRoom.prototype.config().table, "${pageContext.request.contextPath}/caseHouseRoom/getCaseHouseRoomList", cols, {
                    type: houseRoom.prototype.config().type,
                    houseId:'${empty caseHouse.id?0:caseHouse.id}'
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
                if ($('#' + houseRoom.prototype.config().boxSubclass + " .roomId").size() > 0) {
                    $('#' + houseRoom.prototype.config().boxSubclass + " .roomId").val(id);
                }
                $('#' + houseRoom.prototype.config().boxSubclass).modal("show");
            },
            subclassLoadList: function (id) {
                var cols = [];
                cols.push({field: 'materialName', title: '装修材料'});
                cols.push({field: 'constructionTechnologyName', title: '施工工艺'});
                cols.push({field: 'partName', title: '房间装修部位'});
                cols.push({field: 'materialPriceName', title: '装修材料价格区间'});

                $("#" + houseRoom.prototype.config().tableSubclass).bootstrapTable('destroy');
                TableInit(houseRoom.prototype.config().tableSubclass, "${pageContext.request.contextPath}/caseHouseRoom/getCaseHouseRoomDecorateLists", cols, {
                    roomId: id,
                    houseId:'${empty caseHouse.id?0:caseHouse.id}'
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



</html>

