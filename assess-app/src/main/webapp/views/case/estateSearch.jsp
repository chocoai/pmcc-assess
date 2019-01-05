
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.css">
    <script src='${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.js'></script>
</head>


<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="page-title">
                <div class="title_left">
                    <h2><i class="fa "></i>
                        楼盘案例查询
                    </h2>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i
                                class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h3>楼盘信息
                    </h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form class="form-horizontal" id="frmCaseEstate">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    省
                                </label>
                                <div class="col-sm-2">
                                    <select name="province" class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    市
                                </label>
                                <div class="col-sm-2">
                                    <select name="city" class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">楼盘名称</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" name="search"/>
                                </div>
                            </div>
                            <div class="x-valid">
                                <div class="col-sm-2">
                                    <label class="btn btn-primary" onclick="baseFun.caseEstate.find();">
                                        查询
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <table class="table table-bordered" id="caseEstateTable">
                            </table>
                        </div>
                    </form>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i
                                class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h3>楼栋信息
                    </h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form class="form-horizontal" id="frmCaseBuild">
                        <input type="hidden" name="estateId">
                        <div class="form-group">
                            <div class="x-valid">
                                <div class="col-sm-12">
                                    <table class="table table-bordered" id="caseBuildTable">
                                    </table>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i
                                class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h3>单元信息
                    </h3>
                    <div class="clearfix"></div>
                </div>

                <div class="x_content">
                    <form class="form-horizontal" id="frmCaseUnit">
                        <input type="hidden" name="buildingId">
                        <div class="form-group">
                            <div class="x-valid">
                                <div class="col-sm-12">
                                    <table class="table table-bordered" id="caseUnitTable">
                                    </table>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i
                                class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h3>房屋信息
                    </h3>
                    <div class="clearfix"></div>
                </div>

                <div class="x_content">
                    <form class="form-horizontal" id="frmCaseHouse">
                        <input type="hidden" name="unitId">
                        <div class="form-group">
                            <div class="x-valid">
                                <div class="col-sm-12">
                                    <table class="table table-bordered" id="caseHouseTable">
                                    </table>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/map.position.js"></script>
<script src='${pageContext.request.contextPath}/js/autocomplete/estate.case.js'></script>
</html>
<script type="text/javascript">
    var BaseViewFun = function () {

    };
    BaseViewFun.prototype.config = {
        father: {
            caseBlock: {
                frm: function () {
                    return "frmCaseBlock";
                }
            },
            caseEstate: {
                frm: function () {
                    return "frmCaseEstate";//楼盘 frm
                },
                table: function () {
                    return "caseEstateTable";//楼盘 table
                }
            },
            caseBuild: {
                frm: function () {
                    return "frmCaseBuild";
                },
                table: function () {
                    return "caseBuildTable";
                }
            },
            caseUnit: {
                frm: function () {
                    return "frmCaseUnit";
                },
                table: function () {
                    return "caseUnitTable";
                }
            },
            caseHouse: {
                frm: function () {
                    return "frmCaseHouse";
                },
                table: function () {
                    return "caseHouseTable";
                }
            }
        }
    };

    BaseViewFun.prototype.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    }

    BaseViewFun.prototype.WriteSelectData = function (frm, data, name) {
        if (BaseViewFun.prototype.isEmpty(data)) {
            $("#" + frm + " ." + name).val(data).trigger("change");
        } else {
            $("#" + frm + " ." + name).val(null).trigger("change");
        }
    }

    BaseViewFun.prototype.WriteSelectData2 = function (data, id) {
        if (BaseViewFun.prototype.isEmpty(data)) {
            $("#" + id).val(data).trigger("change");
        } else {
            $("#" + id).val(null).trigger("change");
        }
    }

    BaseViewFun.prototype.event = {
        selectInit: function () {

        }
    };
    var baseFun = new BaseViewFun();

    baseFun.caseBlock = {
        init: function () {

        }
    };

    baseFun.caseEstate = {
        /**
         * @author:  zch
         * 描述:加载数据列表
         * @date:2018-09-13
         **/
        loadDataList: function (flag) {
            var estate = formParams(baseFun.config.father.caseEstate.frm());
            if (!flag) {
                estate = {search: null, city: null, district: null, province: null};
            }
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
            cols.push({field: 'version', title: '版本'});
            cols.push({
                field: 'id', title: '查询', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="详情" onclick="baseFun.caseEstate.findData(' + row.id + ')"><i class="fa fa-search fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="地图" onclick="baseFun.caseEstate.findDataMap(' + row.id + ')"><i class="fa fa-map fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + baseFun.config.father.caseEstate.table()).bootstrapTable('destroy');
            TableInit(baseFun.config.father.caseEstate.table(), "${pageContext.request.contextPath}/caseEstate/getCaseEstateVos", cols, {
                name: estate.search,
                city: estate.city,
                province: estate.province
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                },
                onClickCell: function (field, value, row, element) {
                    baseFun.caseBuild.loadDataList(row.id);
                    $(element).closest('tr').css({"background-color": "powderblue"}).siblings().css({"background-color": ""});
                }
            });

        },
        find: function () {
            var itemVal = $("#" + baseFun.config.father.caseEstate.frm()).find("[name='search']").val();
            baseFun.caseEstate.loadDataList(true);
        },
        /**
         * @author:  zch
         * 描述:自动填充 (使用插件)
         * @date:2018-09-13
         **/
        searchInput: function () {

        },
        /**
         * @author:  zch
         * 描述:详情数据
         * @date:2018-09-13
         **/
        findData: function (id) {
            var href = "${pageContext.request.contextPath}/caseEstate/detailView";
            href += "?id=" + id;
            window.open(href, "");
        },

        findDataMap: function (id) {
            var href = "${pageContext.request.contextPath}/case/estateCaseMap";
            href += "?estateId=" + id;
            window.open(href, "");
        }
    };


    baseFun.caseBuild = {
        findData: function (id) {
            var href = "${pageContext.request.contextPath}/caseBuildingMain/detailView";
            href += "?id=" + id;
            window.open(href, "");
        },
        loadDataList: function (id) {
            if (!baseFun.isEmpty(id)) {
                return false;
            }
            var cols = [];
            cols.push({field: 'buildingNumber', title: '楼栋编号'});
            cols.push({field: 'buildingName', title: '楼栋名称'});
            cols.push({field: 'version', title: '版本'});
            cols.push({
                field: 'id', title: '查询', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="查看" onclick="baseFun.caseBuild.findData(' + row.id + ',\'tb_List\')"><i class="fa fa-search fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + baseFun.config.father.caseBuild.table()).bootstrapTable('destroy');
            TableInit(baseFun.config.father.caseBuild.table(), "${pageContext.request.contextPath}/caseBuildingMain/getBootstrapTableVo", cols, {
                estateId: id
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                },
                onClickCell: function (field, value, row, element) {
                    baseFun.caseUnit.loadDataList(row.id);
                    $(element).closest('tr').css({"background-color": "powderblue"}).siblings().css({"background-color": ""});
                }
            });
        }
    };


    baseFun.caseUnit = {
        findData: function (id) {
            var href = "${pageContext.request.contextPath}/caseUnit/detailView";
            href += "?id=" + id;
            window.open(href, "");
        },
        loadDataList: function (buildingId) {
            var cols = [];
            cols.push({field: 'unitNumber', title: '单元编号'});
            cols.push({field: 'elevatorHouseholdRatio', title: '梯户比'});
            cols.push({field: 'version', title: '版本'});
            cols.push({
                field: 'id', title: '查询', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="查看" onclick="baseFun.caseUnit.findData(' + row.id + ',\'tb_List\')"><i class="fa fa-search fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + baseFun.config.father.caseUnit.table()).bootstrapTable('destroy');
            TableInit(baseFun.config.father.caseUnit.table(), "${pageContext.request.contextPath}/caseUnit/getCaseUnitList", cols, {
                caseBuildingId: buildingId
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                },
                onClickCell: function (field, value, row, element) {
                    baseFun.caseHouse.loadDataList(row.id);
                    $("#" + baseFun.config.father.caseHouse.frm()).initForm({unitId: row.id});
                    $(element).closest('tr').css({"background-color": "powderblue"}).siblings().css({"background-color": ""});
                }
            });
        }
    };

    baseFun.caseHouse = {
        findData: function (id) {
            var href = "${pageContext.request.contextPath}/caseHouse/detailView";
            href += "?id=" + id;
            window.open(href, "");
        },
        loadDataList: function (unitId) {
            var cols = [];
            cols.push({field: 'floor', title: '所在楼层'});
            cols.push({field: 'houseNumber', title: '房号'});
            cols.push({field: 'version', title: '版本'});
            cols.push({
                field: 'id', title: '查询', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="查看" onclick="baseFun.caseHouse.findData(' + row.id + ',\'tb_List\')"><i class="fa fa-search fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + baseFun.config.father.caseHouse.table()).bootstrapTable('destroy');
            TableInit(baseFun.config.father.caseHouse.table(), "${pageContext.request.contextPath}/caseHouse/getCaseHouseList", cols, {
                unitId: unitId
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


    $(function () {
        baseFun.event.selectInit();
        baseFun.caseBuild.loadDataList(null);
        baseFun.caseUnit.loadDataList(null);
        baseFun.caseHouse.loadDataList(null);
        $("#" + baseFun.config.father.caseEstate.frm() + " input[name='search']").apEstate({
            onSelect: function (id, name) {
                $("#" + baseFun.config.father.caseEstate.frm() + " input[name='search']").val(name) ;
            }
        });
        //定位成功回调方法
        try {
            mapPosition.complete(function (data) {
                var province = data.addressComponent.province;
                var city = data.addressComponent.city;
                if (province && city) {
                    AssessCommon.initAreaInfo({
                        provinceTarget: $("#" + baseFun.config.father.caseEstate.frm()).find('[name=province]'),
                        cityTarget: $("#" + baseFun.config.father.caseEstate.frm()).find('[name=city]'),
                        provinceDefaultText: province.replace('省', ''),
                        cityDefaultText: city.replace('市', ''),
                        success: function () {
                            baseFun.caseEstate.loadDataList(true);
                        }
                    });
                }
            })
        } catch (e) {
            baseFun.caseEstate.loadDataList();
        }
    });
</script>
