<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--引用案例库信息--%>
<div id="estateModal" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">楼盘</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <form class="form-horizontal" id="frmCaseEstate">
                            <div class="form-group">
                                <table class="table table-bordered" id="estateTable">
                                </table>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>
<div id="buildModal" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">楼栋</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <table class="table table-bordered" id="buildTable">
                                </table>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>
<div id="unitModal" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">单元</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <table class="table table-bordered" id="unitTable">
                                </table>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>
<div id="houseModal" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">房屋</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <table class="table table-bordered" id="houseTable">
                                </table>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var historyInfo = function () {

    };
    historyInfo.prototype.config = {
        father: {
            caseEstate: {
                table: function () {
                    return "estateTable";//楼盘 table
                },
                box: function () {
                    return "estateModal";//楼盘 modal
                }
            },
            caseBuild: {
                table: function () {
                    return "buildTable";
                },
                box: function () {
                    return "buildModal";
                }
            },
            caseUnit: {
                table: function () {
                    return "unitTable";
                },
                box: function () {
                    return "unitModal";
                }
            },
            caseHouse: {
                table: function () {
                    return "houseTable";
                },
                box: function () {
                    return "houseModal";
                }
            }
        }
    };

    historyInfo.prototype.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    }

    historyInfo.prototype.event = {};
    var historyInfo = new historyInfo();

    historyInfo.caseEstate = {
        /**
         * @author:  zch
         * 描述:加载数据列表
         * @date:2018-09-13
         **/
        loadDataList: function (tableName,relevanceId,classify,tbType) {
            var cols = [];
            cols.push({field: 'name', title: '名称'});
            cols.push({
                field: 'area', title: '区域', formatter: function (value, row, index) {
                    return AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
                }
            });
            cols.push({field: 'blockName', title: '版块'});
            cols.push({field: 'averagePrice', title: '均价'});
            cols.push({field: 'coverAnArea', title: '占地面积'});
            cols.push({
                field: 'id', title: '查询', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="详情" onclick="historyInfo.caseEstate.findData(' + row.id + ','+classify+',\''+tbType+'\',\''+tableName+'\')"><i class="fa fa-search fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + historyInfo.config.father.caseEstate.table()).bootstrapTable('destroy');
            TableInit(historyInfo.config.father.caseEstate.table(), "${pageContext.request.contextPath}/basicApplyHistory/getDataByRelevanceId", cols, {
                relevanceId: relevanceId,
                tableName:tableName
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });

        },
        /**
         * @author:  zch
         * 描述:详情数据
         * @date:2018-09-13
         **/
        findData: function (id,classify,tbType,tableName) {
            var url = '${pageContext.request.contextPath}/basicApplyBatch/informationDetail?';
            url += '&formClassify=' + classify;
            url += '&tableId=' + id;
            url += '&tbType=' + tbType;
            url += '&tableName=' + tableName;
            openWin(url, function () {
            })
        },
        showModel: function (relevanceId,classify,tbType) {
            historyInfo.caseEstate.loadDataList("tb_basic_estate",relevanceId,classify,tbType);
            $('#' + historyInfo.config.father.caseEstate.box()).modal("show");
        }
    };


    historyInfo.caseBuild = {
        findData: function (id,classify,tbType,tableName) {
            var url = '${pageContext.request.contextPath}/basicApplyBatch/informationDetail?';
            url += '&formClassify=' + classify;
            url += '&tableId=' + id;
            url += '&tbType=' + tbType;
            url += '&tableName=' + tableName;
            openWin(url, function () {
            })
        },
        loadDataList: function (tableName,relevanceId,classify,tbType) {
            var cols = [];
            cols.push({field: 'buildingNumber', title: '楼栋编号'});
            cols.push({field: 'buildingName', title: '楼栋名称'});
            cols.push({
                field: 'id', title: '查询', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="查看" onclick="historyInfo.caseBuild.findData(' + row.id + ','+classify+',\''+tbType+'\',\''+tableName+'\')"><i class="fa fa-search fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + historyInfo.config.father.caseBuild.table()).bootstrapTable('destroy');
            TableInit(historyInfo.config.father.caseBuild.table(), "${pageContext.request.contextPath}/basicApplyHistory/getDataByRelevanceId", cols, {
                tableName: tableName,
                relevanceId: relevanceId
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        showModel: function (relevanceId,classify,tbType) {
            historyInfo.caseBuild.loadDataList("tb_basic_building",relevanceId,classify,tbType);
            $('#' + historyInfo.config.father.caseBuild.box()).modal("show");
        }
    };

    historyInfo.caseUnit = {
        findData: function (id,classify,tbType,tableName) {
            var url = '${pageContext.request.contextPath}/basicApplyBatch/informationDetail?';
            url += '&formClassify=' + classify;
            url += '&tableId=' + id;
            url += '&tbType=' + tbType;
            url += '&tableName=' + tableName;
            openWin(url, function () {
            })
        },
        loadDataList: function (tableName,relevanceId,classify,tbType) {
            var cols = [];
            cols.push({field: 'unitNumber', title: '单元编号'});
            cols.push({field: 'elevatorHouseholdRatio', title: '梯户比'});
            cols.push({
                field: 'id', title: '查询', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="查看" onclick="historyInfo.caseUnit.findData(' + row.id + ','+classify+',\''+tbType+'\',\''+tableName+'\')"><i class="fa fa-search fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + historyInfo.config.father.caseUnit.table()).bootstrapTable('destroy');
            TableInit(historyInfo.config.father.caseUnit.table(), "${pageContext.request.contextPath}/basicApplyHistory/getDataByRelevanceId", cols, {
                tableName: tableName,
                relevanceId: relevanceId
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        showModel: function (relevanceId,classify,tbType)  {
            historyInfo.caseUnit.loadDataList("tb_basic_unit",relevanceId,classify,tbType) ;
            $('#' + historyInfo.config.father.caseUnit.box()).modal("show");
        }
    };

    historyInfo.caseHouse = {
        findData: function (id,classify,tbType,tableName) {
            var url = '${pageContext.request.contextPath}/basicApplyBatch/informationDetail?';
            url += '&formClassify=' + classify;
            url += '&tableId=' + id;
            url += '&tbType=' + tbType;
            url += '&tableName=' + tableName;
            openWin(url, function () {
            })
        },
        loadDataList: function (tableName,relevanceId,classify,tbType)  {
            var cols = [];
            cols.push({field: 'houseNumber', title: '房号'});
            cols.push({field: 'floor', title: '所在楼层'});
            cols.push({
                field: 'id', title: '查询', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="查看" onclick="historyInfo.caseHouse.findData(' + row.id + ','+classify+',\''+tbType+'\',\''+tableName+'\')"><i class="fa fa-search fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + historyInfo.config.father.caseHouse.table()).bootstrapTable('destroy');
            TableInit(historyInfo.config.father.caseHouse.table(), "${pageContext.request.contextPath}/basicApplyHistory/getDataByRelevanceId", cols, {
                tableName: tableName,
                relevanceId: relevanceId
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        showModel: function (relevanceId,classify,tbType) {
            historyInfo.caseHouse.loadDataList("tb_basic_house",relevanceId,classify,tbType);
            $('#' + historyInfo.config.father.caseHouse.box()).modal("show");
        }
    };


</script>
