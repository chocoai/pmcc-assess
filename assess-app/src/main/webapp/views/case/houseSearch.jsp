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
                                    <select id="district" name="district" class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">版块名称</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" name="blockName" placeholder="版块名称"/>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">楼盘名称</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" name="fullName" placeholder="楼盘名称"/>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">街道名称</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" name="street" placeholder="街道名称"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    实际用途
                                </label>
                                <div class="col-sm-2">
                                    <select name="practicalUse"
                                            class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    交易类型
                                </label>
                                <div class="col-sm-2">
                                    <select name="tradingType"
                                            class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    交易单价
                                </label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" placeholder="从" id="tradingUnitPriceStart"
                                           style="width: 45%;display:inline;"/>~
                                    <input type="text" class="form-control" placeholder="到" id="tradingUnitPriceEnd" style="width: 45%;display:inline;"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    交易时间
                                </label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control date-picker dbdate" data-date-format="yyyy-mm-dd" name="tradingTimeStart" placeholder="开始时间"
                                           style="width: 45%;display:inline;"/>~
                                    <input type="text" class="form-control date-picker dbdate" data-date-format="yyyy-mm-dd" name="tradingTimeEnd" placeholder="结束时间" style="width: 52%;display:inline;"/>
                                </div>
                            </div>
                        </div>
                        <div class="x-valid">
                            <div class="col-sm-2">
                                <label class="btn btn-primary" onclick="houseSearch.loadDataList()">
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

    houseSearch.isNotBlank = function (item) {
        if (item){
            return true;
        }
        return false;
    };

    houseSearch.findHouse = function (id) {
        var href = "${pageContext.request.contextPath}/case/findHouseView";
        href += "?id=" + id;
        window.open(href, "");
    };

    houseSearch.loadDataList = function () {
        var cols = [];
        cols.push({field: 'fullName', title: '名称'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="查看" onclick="houseSearch.findHouse(' + row.id + ',\'tb_List\')"><i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tbCaseBaseHouseList").bootstrapTable('destroy');
        TableInit("tbCaseBaseHouseList", "${pageContext.request.contextPath}/case/getBootstrapTableCaseBaseHouseVo", cols, {
            blockName: $("#frmCaseBaseHouse").find("input[name='blockName']").val() ,
            fullName: $("#frmCaseBaseHouse").find("input[name='fullName']").val() ,
            street: $("#frmCaseBaseHouse").find("input[name='street']").val(),
            tradingUnitPriceStart: $("#frmCaseBaseHouse").find("input[name='tradingUnitPriceStart']").val(),
            tradingUnitPriceEnd: $("#frmCaseBaseHouse").find("input[name='tradingUnitPriceEnd']").val(),
            tradingTimeStart: $("#frmCaseBaseHouse").find("input[name='tradingTimeStart']").val(),
            tradingTimeEnd: $("#frmCaseBaseHouse").find("input[name='tradingTimeEnd']").val(),
            practicalUse:$("#frmCaseBaseHouse").find("select[name='practicalUse']").val(),
            tradingType:$("#frmCaseBaseHouse").find("select[name='tradingType']").val(),
            province:$("#frmCaseBaseHouse").find("select[name='province']").val(),
            city:$("#frmCaseBaseHouse").find("select[name='city']").val(),
            district:$("#frmCaseBaseHouse").find("select[name='district']").val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

    houseSearch.loadData = function (data) {
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePracticalUse, data.practicalUse, function (html, data) {
            $("#frmCaseBaseHouse").find("select[name='practicalUse']").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseTransactionType, data.tradingType, function (html, data) {
            $("#frmCaseBaseHouse").find("select[name='tradingType']").empty().html(html).trigger('change');
        });
        var province = data.province;
        var city = data.city;
        if (province && city) {
            AssessCommon.initAreaInfo({
                provinceTarget: $("#province"),
                cityTarget: $("#city"),
                districtTarget: $("#district"),
                provinceDefaultText: province.replace('省', ''),
                cityDefaultText: city.replace('市', ''),
                success: function () {
                    houseSearch.loadDataList();
                }
            });
        }
    };


    $(function () {
        //定位成功回调方法
        try {
            mapPosition.complete(function (data) {
                $.extend(data,{city:data.addressComponent.city,province:data.addressComponent.province});
                houseSearch.loadData(data);
            })
        } catch (e) {
            houseSearch.loadDataList();
        }
    });
</script>
