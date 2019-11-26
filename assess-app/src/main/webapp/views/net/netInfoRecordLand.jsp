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
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                        ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frmQuery" class="form-horizontal">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">省</label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <select name="province"
                                            class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">市</label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <select name="city" class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">区县</label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <select name="district"
                                            class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道</label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <input type="text" class="form-control" name="street">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">地块名称</label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <input type="text" class="form-control" name="name">
                                </div>
                            </div>
                            <div class="x-valid">
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <label class="btn btn-primary" onclick="detailInfo.prototype.loadDataInfoList();">
                                        查询
                                    </label>
                                </div>
                            </div>
                        </div>
                    </form>
                    <input type="hidden" id="selectIds">
                    <table class="table table-bordered" id="transaction_List">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    $(function () {
        detailInfo.prototype.loadDataInfoList();
        AssessCommon.initAreaInfo({
            useDefaultText: false,
            provinceTarget: $("select[name='province']"),
            cityTarget: $("select[name='city']"),
            districtTarget: $("select[name='district']")
        })
    });

    var detailInfo = function () {

    };
    detailInfo.prototype = {
        config: function () {
            var data = {};
            data.table = "transaction_List";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            return data;
        },
        loadDataInfoList: function () {
            var cols = [];
            cols.push({
                field: 'provinceName', title: '区域', formatter: function (value, row, index) {
                    var str = AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
                    if (row.street) {
                        str += row.street;
                    }
                    return str;
                }
            });
            cols.push({field: 'area', title: '面积'});
            cols.push({field: 'name', title: '地块名称'});
            cols.push({field: 'dealTypeName', title: '交易方式'});
            cols.push({field: 'currentPrice', title: '成交价'});
            cols.push({
                field: 'other', title: '其他', formatter: function (value, row, index) {
                    var result = '';
                    if (row.parcelNumber) {
                        result += '宗地编号：' + row.parcelNumber + '<br/>';
                    }
                    if (row.parcelSite) {
                        result += '宗地位置：' + row.parcelSite + '<br/>';
                    }
                    if (row.negotiatedDate) {
                        result += '成交(协商)日期：' + formatDate(row.negotiatedDate) + '<br/>';
                    }
                    if (row.consultPrice) {
                        result += '评估价：' + row.consultPrice + '<br/>';
                    }
                    if (row.assessStandardDate) {
                        result += '评估基准日：' + formatDate(row.assessStandardDate) + '<br/>';
                    }
                    if (row.unitPrice) {
                        result += '单价：' + row.unitPrice + '<br/>';
                    }
                    if (row.houseRealizationRatios) {
                        result += '变现率：' + row.houseRealizationRatios + '<br/>';
                    }
                    if (row.realizationCycle) {
                        result += '变现周期：' + row.realizationCycle + '<br/>';
                    }
                    if (row.floorPrice) {
                        result += '楼面地价：' + row.floorPrice + '<br/>';
                    }
                    if (row.landArea) {
                        result += '净用地面积：' + row.landArea + '<br/>';
                    }
                    if (row.plotRatio) {
                        result += '容积率：' + row.plotRatio + '<br/>';
                    }
                    if (row.plotRatioRemark) {
                        result += '容积率说明：' + row.plotRatioRemark + '<br/>';
                    }
                    if (row.greeningRate) {
                        result += '绿化率：' + row.greeningRate*100 + '%<br/>';
                    }
                    if (row.greeningRateRemark) {
                        result += '绿化率说明：' + row.greeningRateRemark + '<br/>';
                    }
                    if (row.buildDensity) {
                        result += '建筑密度：' + row.buildDensity + '<br/>';
                    }
                    if (row.buildDensityRemark) {
                        result += '建筑密度说明：' + row.buildDensityRemark + '<br/>';
                    }
                    if (row.buildHeight) {
                        result += '建筑高度：' + row.buildHeight + '<br/>';
                    }
                    if (row.buildHeightRemark) {
                        result += '建筑高度说明：' + row.buildHeightRemark + '<br/>';
                    }
                    if (row.indexAmount) {
                        result += '指标款(亩)：' + row.indexAmount + '<br/>';
                    }
                    if (row.indexAmountRemark) {
                        result += '指标款(亩)说明：' + row.indexAmountRemark + '<br/>';
                    }
                    return result;
                }
            });
            cols.push({field: 'dealPartInfo', title: '成交对象概况'});
            cols.push({field: 'fileViewName', title: '附件'});
            $("#" + detailInfo.prototype.config().table).bootstrapTable('destroy');
            TableInit(detailInfo.prototype.config().table, "${pageContext.request.contextPath}/netInfoRecordLand/landList", cols, {
                province: $("#frmQuery").find("select[name='province']").val(),
                city: $("#frmQuery").find("select[name='city']").val(),
                district: $("#frmQuery").find("select[name='district']").val(),
                street: $("#frmQuery").find("input[name='street']").val(),
                name: $("#frmQuery").find("input[name='name']").val()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        openItem: function (index) {
            var row = $("#transaction_List").bootstrapTable('getData')[index];
            if (row.sourceSiteUrl) {
                window.open(row.sourceSiteUrl, "");
            }
        }
    }


</script>


</html>
