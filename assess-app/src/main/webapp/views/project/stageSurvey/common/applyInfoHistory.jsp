<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--引用案例库信息--%>
<div id="estateModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">楼盘</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmCaseEstate" class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <table class="table table-bordered" id="estateTable">
                                    </table>
                                    </div>
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
<div id="buildModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">楼栋</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <table class="table table-bordered" id="buildTable">
                                    </table>
                                    </div>
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
<div id="unitModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">单元</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <table class="table table-bordered" id="unitTable">
                                    </table>
                                    </div>
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
<div id="houseModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">房屋</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <table class="table table-bordered" id="houseTable">
                                    </table>
                                    </div>
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
        loadDataList: function (tableName,relevanceId,classify,tbType,applyBatchId) {
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
            cols.push({field: 'creatorName', title: '创建人'});
            cols.push({
                field: 'gmtCreated', title: '创建日期', formatter: function (value, row, index) {
                    return formatDate(value, false);
                }
            });
            cols.push({
                field: 'id', title: '查询', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<button type="button" class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="详情" onclick="historyInfo.caseEstate.findData(' + row.id + ','+classify+',\''+tbType+'\',\''+tableName+'\',\''+applyBatchId+'\')"><i class="fa fa-search"></i></button>';
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
        findData: function (id,classify,tbType,tableName,applyBatchId) {
            var url = '${pageContext.request.contextPath}/basicApplyBatch/informationDetail?';
            url += '&formClassify=' + classify;
            url += '&tableId=' + id;
            url += '&tbType=' + tbType;
            url += '&tableName=' + tableName;
            url += '&applyBatchId=' + applyBatchId;
            url += '&isHistory=true';
            openWin(url, function () {
            })
        },
        showModel: function (relevanceId,classify,tbType,applyBatchId) {
            historyInfo.caseEstate.loadDataList("tb_basic_estate",relevanceId,classify,tbType,applyBatchId);
            $('#' + historyInfo.config.father.caseEstate.box()).modal("show");
        }
    };


    historyInfo.caseBuild = {
        findData: function (id,classify,tbType,tableName,applyBatchId) {
            var url = '${pageContext.request.contextPath}/basicApplyBatch/informationDetail?';
            url += '&formClassify=' + classify;
            url += '&tableId=' + id;
            url += '&tbType=' + tbType;
            url += '&tableName=' + tableName;
            url += '&applyBatchId=' + applyBatchId;
            url += '&isHistory=true';
            openWin(url, function () {
            })
        },
        loadDataList: function (tableName,relevanceId,classify,tbType,applyBatchId) {
            var cols = [];
            cols.push({field: 'buildingNumber', title: '楼栋编号'});
            cols.push({field: 'buildingName', title: '楼栋名称'});
            cols.push({field: 'creatorName', title: '创建人'});
            cols.push({
                field: 'gmtCreated', title: '创建日期', formatter: function (value, row, index) {
                    return formatDate(value, false);
                }
            });
            cols.push({
                field: 'id', title: '查询', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<button type="button" class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="查看" onclick="historyInfo.caseBuild.findData(' + row.id + ','+classify+',\''+tbType+'\',\''+tableName+'\',\''+applyBatchId+'\')"><i class="fa fa-search fa-white"></i></button>';
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
        showModel: function (relevanceId,classify,tbType,applyBatchId) {
            historyInfo.caseBuild.loadDataList("tb_basic_building",relevanceId,classify,tbType,applyBatchId);
            $('#' + historyInfo.config.father.caseBuild.box()).modal("show");
        }
    };

    historyInfo.caseUnit = {
        findData: function (id,classify,tbType,tableName,applyBatchId) {
            var url = '${pageContext.request.contextPath}/basicApplyBatch/informationDetail?';
            url += '&formClassify=' + classify;
            url += '&tableId=' + id;
            url += '&tbType=' + tbType;
            url += '&tableName=' + tableName;
            url += '&applyBatchId=' + applyBatchId;
            url += '&isHistory=true';
            openWin(url, function () {
            })
        },
        loadDataList: function (tableName,relevanceId,classify,tbType,applyBatchId) {
            var cols = [];
            cols.push({field: 'unitNumber', title: '单元编号'});
            cols.push({field: 'elevatorHouseholdRatio', title: '梯户比'});
            cols.push({field: 'creatorName', title: '创建人'});
            cols.push({
                field: 'gmtCreated', title: '创建日期', formatter: function (value, row, index) {
                    return formatDate(value, false);
                }
            });
            cols.push({
                field: 'id', title: '查询', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<button type="button" class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="查看" onclick="historyInfo.caseUnit.findData(' + row.id + ','+classify+',\''+tbType+'\',\''+tableName+'\',\''+applyBatchId+'\')"><i class="fa fa-search fa-white"></i></button>';
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
        showModel: function (relevanceId,classify,tbType,applyBatchId)  {
            historyInfo.caseUnit.loadDataList("tb_basic_unit",relevanceId,classify,tbType,applyBatchId) ;
            $('#' + historyInfo.config.father.caseUnit.box()).modal("show");
        }
    };

    historyInfo.caseHouse = {
        findData: function (id,classify,tbType,tableName,applyBatchId) {
            var url = '${pageContext.request.contextPath}/basicApplyBatch/informationDetail?';
            url += '&formClassify=' + classify;
            url += '&tableId=' + id;
            url += '&tbType=' + tbType;
            url += '&tableName=' + tableName;
            url += '&applyBatchId=' + applyBatchId;
            url += '&isHistory=true';
            openWin(url, function () {
            })
        },
        loadDataList: function (tableName,relevanceId,classify,tbType,applyBatchId)  {
            var cols = [];
            cols.push({field: 'houseNumber', title: '房号'});
            cols.push({field: 'floor', title: '所在楼层'});
            cols.push({field: 'creatorName', title: '创建人'});
            cols.push({
                field: 'gmtCreated', title: '创建日期', formatter: function (value, row, index) {
                    return formatDate(value, false);
                }
            });
            cols.push({
                field: 'id', title: '查询', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<button type="button" class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="查看" onclick="historyInfo.caseHouse.findData(' + row.id + ','+classify+',\''+tbType+'\',\''+tableName+'\',\''+applyBatchId+'\')"><i class="fa fa-search fa-white"></i></button>';
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
        showModel: function (relevanceId,classify,tbType,applyBatchId) {
            historyInfo.caseHouse.loadDataList("tb_basic_house",relevanceId,classify,tbType,applyBatchId);
            $('#' + historyInfo.config.father.caseHouse.box()).modal("show");
        }
    };


</script>
