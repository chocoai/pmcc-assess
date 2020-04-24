<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="LandUseCategoryListBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 70%">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">明细数据列表</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal" id="LandUseCategoryListBoxFrm">
                    <input type="hidden" name="landUseTypeId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="col-md-12">
                                    <table class="table table-bordered" id="tb_LandUseCategoryList">
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>

        </div>
    </div>
</div>


<script type="text/javascript">
    $(function () {
        landUseType.loadDataList();
    });

    var LandUseType = function () {

    };
    LandUseType.prototype.config = {
        father: {
            frm: function () {
                return "frmFather";
            },
            table: function () {
                return "tb_LandUseTypeList";
            },
            box: function () {
                return "divBoxUseTypeFather";
            }
        },
        son: {
            frm: function () {
                return "frmSon";
            },
            table: function () {
                return "tb_LandUseCategoryList";
            },
            box: function () {
                return "divBoxUseCategorySon";
            },
            tableBox: function () {
                return "LandUseCategoryListBox";
            },
            tableFrm: function () {
                return "LandUseCategoryListBoxFrm";
            },
        }
    }
    LandUseType.prototype.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    }

    var landUseType = new LandUseType();

    landUseType.loadDataList = function () {
        var cols = [];
        cols.push({field: 'landUseType', title: '土地用途类型'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<button type="button" onclick="landUseType.showSonBoxModel(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="子项">';
                str += '<i class="fa fa-search"></i>';
                str += '</button>';
                return str;
            }
        });
        $("#" + landUseType.config.father.table()).bootstrapTable('destroy');
        TableInit(landUseType.config.father.table(), "${pageContext.request.contextPath}/basicEstateLandUseType/getBootstrapTableVo", cols, {
            estateId: estateCommon.getEstateId()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }


    landUseType.showSonBoxModel = function (id) {
        $("#" + landUseType.config.son.tableFrm()).clearAll();
        landUseType.loadSonDataList(id);
        $('#' + landUseType.config.son.tableBox()).modal("show");

    }


    landUseType.loadSonDataList = function (landUseTypeId) {
        var cols = [];
        cols.push({field: 'landUseCategory', title: '土地用途类型'});
        cols.push({field: 'landLevelName', title: '土地级别'});
        cols.push({
            field: 'acquisitionTime', title: '土地取得时间',formatter: function (value, row, index) {
                return formatDate(row.acquisitionTime, false);
            }
        });
        cols.push({field: 'landUseYear', title: '土地使用年限'});
        cols.push({field: 'landShape', title: '土地形状'});
        cols.push({
            field: 'developTime', title: '开发时间',formatter: function (value, row, index) {
                return formatDate(row.developTime, false);
            }
        });
        cols.push({field: 'plotRatio', title: '容积率'});
        cols.push({field: 'buildingDensity', title: '建筑密度'});
        cols.push({
            field: 'greeningRate', title: '绿地率', formatter: function (value, row, index) {
                if (value != null || value != undefined) {
                    return AssessCommon.pointToPercent(value);
                }
            }
        });
        cols.push({field: 'compatibilityType', title: '兼容类型'});
        cols.push({
            field: 'compatibilityRate', title: '兼容比例', formatter: function (value, row, index) {
                if (value != null || value != undefined) {
                    return AssessCommon.pointToPercent(value);
                }
            }
        });
        cols.push({field: 'heightPermitted', title: '建筑限高'});
        $("#" + landUseType.config.son.table()).bootstrapTable('destroy');
        TableInit(landUseType.config.son.table(), "${pageContext.request.contextPath}/basicEstateLandUseCategory/getBootstrapTableVo", cols, {
            landUseTypeId: landUseTypeId
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }

</script>


