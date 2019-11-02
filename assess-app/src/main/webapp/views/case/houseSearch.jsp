<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
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
                        房屋案例查询
                    </h2>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <form class="form-horizontal" id="frmCaseBaseHouse">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    省
                                </label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <select id="province" name="province" class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    市
                                </label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <select id="city" name="city" class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    区
                                </label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <select id="district" name="district" class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">版块名称</label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <input type="text" class="form-control" name="blockName" placeholder="版块名称"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼盘名称</label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <input type="text" class="form-control" name="fullName" placeholder="楼盘名称"/>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道名称</label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <input type="text" class="form-control" name="street" placeholder="街道名称"/>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    实际用途
                                </label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <select name="practicalUse"
                                            class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    交易类型
                                </label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <select name="tradingType"
                                            class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    交易时间
                                </label>
                                <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                                    <input type="text" class="form-control date-picker dbdate"
                                           data-date-format="yyyy-mm-dd" name="tradingTimeStart" placeholder="开始时间"/>
                                </div>
                                <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                                    <input type="text" class="form-control date-picker dbdate"
                                           data-date-format="yyyy-mm-dd" name="tradingTimeEnd" placeholder="结束时间"/>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    交易单价
                                </label>
                                <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                                    <input type="text" class="form-control" placeholder="从"
                                           id="tradingUnitPriceStart"
                                           data-rule-number="true" name="tradingUnitPriceStart"/>
                                </div>
                                <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                                    <input type="text" class="form-control" placeholder="到" id="tradingUnitPriceEnd"
                                           data-rule-number="true" name="tradingUnitPriceEnd"/>
                                </div>
                            </div>
                            <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-xs-offset-1 col-sm-offset-1 col-md-offset-1 col-lg-offset-1">
                                <label class="btn btn-primary" onclick="houseSearch.loadDataList()">
                                    查询
                                </label>
                                <label class="btn btn-primary" onclick="houseSearch.clearQuery()">
                                    清空
                                </label>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="x_content">
                    <div class="form-group">
                        <table class="table table-bordered" id="tbCaseBaseHouseList">
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/map.position.js?v=${assessVersion}"></script>
</html>
<script type="text/javascript">
    $(function () {
        AssessCommon.initAreaInfo({
            provinceTarget: $("#province"),
            cityTarget: $("#city"),
            districtTarget: $("#district"),
            success: function () {
                houseSearch.loadDataList();
            }
        });
    });
    var houseSearch = {};
    var houseFrm = $("#frmCaseBaseHouse");

    houseSearch.findHouse = function (id) {
        var href = "${pageContext.request.contextPath}/caseHouse/detailView";
        href += "?id=" + id;
        window.open(href, "");
    };

    //清空查询条件
    houseSearch.clearQuery = function () {
        houseFrm.find('input:text').val('');
        houseFrm.find('.select2').select2('val', '').trigger('change');
    };

    houseSearch.loadDataList = function () {
        if (!houseFrm.valid()) {
            return false;
        }
        var cols = [];
        cols.push({field: 'fullName', title: '名称'});
        cols.push({field: 'blockName', title: '版块名称'});
        cols.push({field: 'street', title: '街道'});
        cols.push({field: 'practicalUseName', title: '实际用途'});
        cols.push({field: 'tradingTypeName', title: '交易类型'});
        cols.push({
            field: 'tradingTime', title: '交易时间', formatter: function (value, row, index) {
                return formatDate(value);
            }
        });
        cols.push({field: 'tradingUnitPrice', title: '交易单价'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="查看" onclick="houseSearch.findHouse(' + row.caseHouseId + ')"><i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tbCaseBaseHouseList").bootstrapTable('destroy');
        TableInit("tbCaseBaseHouseList", "${pageContext.request.contextPath}/case/getBootstrapTableCaseBaseHouseVo", cols,
            formSerializeArray(houseFrm), {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
    };

</script>
