<%--
 户型
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    户型
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body" style="display: none">
            <form class="form-horizontal">
                <table class="table table-bordered" id="UnitHuxingListDetail">
                    <!-- cerare document add ajax data-->
                </table>
            </form>
        </div>
    </div>
</div>

<script>


    var unitHuxingDetail;
    (function () {
        unitHuxingDetail = function () {
        };
        unitHuxingDetail.prototype = {
            config: function () {
                var data = {};
                data.table = "UnitHuxingListDetail";
                data.box = "divBoxUnitHuxing";
                data.frm = "frmUnitHuxing";
                data.unitHuxingFileIDFildName = "house_latest_family_planV";
                return data;
            },
            loadDataDicList: function () {
                var cols = commonColumn.unitHuxingColumn();
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="调查信息" onclick="unitHuxingPriceDetail.prototype.showTableModel(' + row.id + ',\'tb_List\')"><i class="fa fa-arrow-right fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
                $("#" + unitHuxingDetail.prototype.config().table).bootstrapTable('destroy');
                TableInit(unitHuxingDetail.prototype.config().table, "${pageContext.request.contextPath}/basicUnitHuxing/getBootstrapTableVo", cols, {
                    unitId: ${empty basicUnit.id?0:basicUnit.id},
                    approval:true
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
            },
            isNotNull: function (item) {
                if (item) {
                    return true;
                }
                return false;
            }
        }

        //绑定事件
        $('#' + unitHuxingDetail.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
            unitHuxingDetail.prototype.loadDataDicList();
        })
    })();


</script>
