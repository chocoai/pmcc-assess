
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
                                <label class="col-sm-1 control-label">
                                    省
                                </label>
                                <div class="col-sm-2">
                                    <select id="province" name="province" class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    市
                                </label>
                                <div class="col-sm-2">
                                    <select id="city" name="city" class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    区
                                </label>
                                <div class="col-sm-2">
                                    <select id="distrct" name="distrct" class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">版块名称</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" name="blockName"/>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">楼盘名称</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" name="fullName"/>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">街道名称</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" name="street"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    实际用途
                                </label>
                                <div class="col-sm-2">
                                    <select id="practicalUse" name="practicalUse"
                                            class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    交易类型
                                </label>
                                <div class="col-sm-2">
                                    <select id="tradingType" name="tradingType"
                                            class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    交易单价
                                </label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="tradingUnitPriceMin"
                                           style="width: 45%;display:inline;"/>~
                                    <input type="text" class="form-control" id="tradingUnitPriceMax" style="width: 45%;display:inline;"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    交易时间
                                </label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="tradingTimeBegin"
                                           style="width: 45%;display:inline;"/>~
                                    <input type="text" class="form-control" id="tradingTimeEnd" style="width: 45%;display:inline;"/>
                                </div>
                            </div>
                        </div>
                        <div class="x-valid">
                            <div class="col-sm-2">
                                <label class="btn btn-primary" onclick="">
                                    查询
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <table class="table table-bordered" id="tbCaseBaseHouseList">
                            </table>
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
</html>
<script type="text/javascript">
    var houseSearch = {};
    houseSearch.loadDataList = function () {
        var cols = [];
        cols.push({field: 'fullName', title: '名称'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="查看" onclick=""><i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tbCaseBaseHouseList").bootstrapTable('destroy');
        TableInit("tbCaseBaseHouseList", "", cols, {
            unitId: 214
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };


    $(function () {
        //定位成功回调方法
        try {
            mapPosition.complete(function (data) {
                var province = data.addressComponent.province;
                var city = data.addressComponent.city;
                if (province && city) {
                    AssessCommon.initAreaInfo({
                        provinceTarget: $("#province"),
                        cityTarget: $("#city"),
                        provinceDefaultText: province.replace('省', ''),
                        cityDefaultText: city.replace('市', ''),
                        success: function () {
                            houseSearch.loadDataList();
                        }
                    });
                }
            })
        } catch (e) {
            houseSearch.loadDataList();
        }
    });
</script>
