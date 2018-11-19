<%--
  房屋配套设备设施
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="houseCorollaryEquipment.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>房屋配套设备设施信息
        </h3>
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
                    <table class="table table-bordered" id="HouseCorollaryEquipmentList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">


    var houseCorollaryEquipment;
    (function () {
        var flag = true;
        var fileID = null;
        houseCorollaryEquipment = function () {

        };
        houseCorollaryEquipment.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            setFileID:function (id_) {
                fileID = id_;
            },
            getFileID:function () {
                if (fileID == null || fileID == ''){
                    return 0;
                }
                return fileID;
            },
            viewInit: function () {
                houseCorollaryEquipment.prototype.loadDataDicList();

            },
            config: function () {
                var data = {};
                data.table = "HouseCorollaryEquipmentList";
                data.box = "divBoxHouseCorollaryEquipment";
                data.frm = "frmHouseCorollaryEquipment";
                data.FileID = "positionDiagramFileID" ;// ExamineFileUpLoadTwoFieldEnum
                data.type = "null";//
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'name', title: '名称'});
                cols.push({field: 'parameterIndexH', title: '参数指标'});
                cols.push({field: 'useH', title: '用途'});
                cols.push({field: 'maintenanceStatus', title: '维护状况'});
                cols.push({field: 'typeName', title: '类型'});
                cols.push({field: 'categoryName', title: '类别'});
                cols.push({field: 'priceName', title: '价格'});
                cols.push({field: 'fileName', title: '位置图'});

                $("#" + houseCorollaryEquipment.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseCorollaryEquipment.prototype.config().table, "${pageContext.request.contextPath}/caseHouseCorollaryEquipment/getCaseHouseCorollaryEquipmentList", cols, {
                    type: houseCorollaryEquipment.prototype.config().type,
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



</html>