<%--
  Created by IntelliJ IDEA.
  User: huhao
  Date: 2018/9/3
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>案例整理</title>

    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        案列整理
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form class="form-horizontal">
                                    <h3>房产</h3>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <table class="table table-bordered" id="house_list">
                                                <!-- cerare document add ajax data-->
                                            </table>
                                        </div>
                                    </div>
                                    <h3>土地</h3>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <table class="table table-bordered" id="land_list">
                                                <!-- cerare document add ajax data-->
                                            </table>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 公共尾部模块引用 -->
            <%@include file="/views/share/form_approval.jsp" %>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>

</body>
</html>
<script type="text/javascript">
    function saveform() {
        if (!$("#frm_approval").valid()) {
            return false;
        }
        var data = formApproval.getFormData();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/netInfoAssignTask/approvalCommit",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "提交数据成功", function () {
                        window.close();
                    });
                } else {
                    AlertError("失败", "保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }

    $(function () {
        detailInfo.prototype.loadDataHouseList();
        detailInfo.prototype.loadDataLandList();
    })

    var detailInfo = function () {
    };
    detailInfo.prototype = {
        loadDataHouseList: function () {
            var cols = [];
            cols.push({
                field: 'info1', title: '信息1', formatter: function (value, row, index) {
                    var result = '';
                    var str = AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
                    if (row.street) {
                        str += row.street;
                    }
                    if (str) {
                        result += '位置：' + str + '<br/>';
                    }
                    if (row.belongType) {
                        result += '房产类型：' + row.belongType + '<br/>';
                    }
                    if (row.belongCategory) {
                        result += '房产类别：' + row.belongCategory + '<br/>';
                    }
                    if (row.area) {
                        result += '面积：' + row.area + '<br/>';
                    }

                    return result;
                }
            });
            cols.push({
                field: 'info2', title: '信息2', formatter: function (value, row, index) {
                    var result = '';
                    var str = '';
                    if (row.name) {
                        str += row.name;
                    }
                    if (row.buildingNumber) {
                        str += row.buildingNumber + "栋";
                    }
                    if (row.unitNumber) {
                        str += row.unitNumber + "单元";
                    }
                    if (row.houseNumber) {
                        str += row.houseNumber + "号";
                    }

                    if (str) {
                        result += '楼盘：' + str + '<br/>';
                    }
                    if (row.dealType) {
                        result += '交易方式：' + row.dealTypeName + '<br/>';
                    }
                    if (row.currentPrice) {
                        result += '成交总价：' + row.currentPrice + '<br/>';
                    }
                    if (row.negotiatedDate) {
                        result += '成交(协商)日期：' + formatDate(row.negotiatedDate) + '<br/>';
                    }
                    return result;
                }
            });

            cols.push({
                field: 'other3', title: '信息3', formatter: function (value, row, index) {
                    var result = '';

                    if (row.consultPrice) {
                        result += '评估总价：' + row.consultPrice + '<br/>';
                    }
                    if (row.assessStandardDate) {
                        result += '评估基准日期：' + formatDate(row.assessStandardDate) + '<br/>';
                    }
                    if (row.unitPrice) {
                        result += '成交单价：' + row.unitPrice + '<br/>';
                    }
                    if (row.houseRealizationRatios) {
                        result += '变现率：' + row.houseRealizationRatios * 100 + '%<br/>';
                    }
                    return result;
                }
            });
            cols.push({
                field: 'other4', title: '信息4', formatter: function (value, row, index) {
                    var result = '';
                    if (row.realizationCycle) {
                        result += '变现周期：' + row.realizationCycle + '<br/>';
                    }
                    if (row.tradingTypeName) {
                        result += '交易类型：' + row.tradingTypeName + '<br/>';
                    }
                    if (row.purchaseLimitStatus) {
                        result += '限购状态：' + row.purchaseLimitStatus + '<br/>';
                    }
                    if (row.dealPartInfo) {
                        result += '成交对象概况：' + row.dealPartInfo + '<br/>';
                    }
                    return result;
                }
            });
            cols.push({field: 'fileViewName', title: '附件'});
            if ('${netInfoAssignTask.source}' == 'net') {
                cols.push({
                    field: 'id', title: '查看网址', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="查看网址" target="_blank" href="' + row.sourceSiteUrl + '" ><i class="fa fa-eye fa-white"></i></a>';
                        str += '</div>';
                        return str;
                    }
                });
            }
            $("#house_list").bootstrapTable('destroy');
            TableInit("house_list", "${pageContext.request.contextPath}/netInfoAssignTask/getHouseListByAssignTaskId", cols, {
                assignTaskId: '${netInfoAssignTask.id}'
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },

        loadDataLandList: function () {
            var cols = [];
            cols.push({
                field: 'info1', title: '信息1', formatter: function (value, row, index) {
                    var result = '';
                    var str = AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
                    if (row.street) {
                        str += row.street;
                    }
                    if (str) {
                        result += '位置：' + str + '<br/>';
                    }
                    if (row.parcelSite) {
                        result += '宗地位置：' + row.parcelSite + '<br/>';
                    }
                    if (row.name) {
                        result += '地块名称：' + row.name + '<br/>';
                    }
                    if (row.parcelNumber) {
                        result += '宗地编号：' + row.parcelNumber + '<br/>';
                    }
                    if (row.landPurpose) {
                        result += '土地性质：' + row.landPurpose + '<br/>';
                    }
                    if (row.belongType) {
                        result += '土地类型：' + row.belongType + '<br/>';
                    }
                    if (row.belongCategory) {
                        result += '土地类别：' + row.belongCategory + '<br/>';
                    }
                    if (row.dealType) {
                        result += '交易方式：' + row.dealTypeName + '<br/>';
                    }
                    return result;
                }
            });

            cols.push({
                field: 'info2', title: '信息2', formatter: function (value, row, index) {
                    var result = '';
                    if (row.negotiatedDate) {
                        result += '成交(协商)日期：' + formatDate(row.negotiatedDate) + '<br/>';
                    }
                    if (row.area) {
                        result += '面积：' + row.area + '<br/>';
                    }
                    if (row.areaUnit) {
                        result += '单位：' + row.areaUnit + '<br/>';
                    }
                    if (row.landArea) {
                        result += '净用地面积：' + row.landArea + '<br/>';
                    }
                    if (row.landAreaUnit) {
                        result += '净用地面积单位：' + row.landAreaUnit + '<br/>';
                    }
                    if (row.currentPrice) {
                        result += '成交总价（万元）：' + row.currentPrice + '<br/>';
                    }
                    if (row.unitPrice) {
                        result += '成交单价（元/㎡）：' + row.unitPrice + '<br/>';
                    }
                    if (row.unitPriceMu) {
                        result += '成交单价（万元/每亩）：' + row.unitPriceMu + '<br/>';
                    }
                    return result;
                }
            });
            cols.push({
                field: 'info3', title: '信息3', formatter: function (value, row, index) {
                    var result = '';
                    if (row.floorPrice) {
                        result += '成交楼面地价（元/㎡）：' + row.floorPrice + '<br/>';
                    }
                    if (row.assessStandardDate) {
                        result += '评估基准日：' + formatDate(row.assessStandardDate) + '<br/>';
                    }
                    if (row.consultPrice) {
                        result += '评估起拍单价（元/㎡）：' + row.consultPrice + '<br/>';
                    }
                    if (row.consultPriceMu) {
                        result += '评估起拍单价（万元/每亩）：' + row.consultPriceMu + '<br/>';
                    }
                    if (row.realizationCycle) {
                        result += '变现周期：' + row.realizationCycle + '<br/>';
                    }
                    if (row.landRealizationRatios) {
                        result += '变现率：' + row.landRealizationRatios * 100 + '%<br/>';
                    }
                    if (row.plotRatio) {
                        result += '容积率：' + row.plotRatio + '<br/>';
                    }
                    if (row.plotRatioRemark) {
                        result += '容积率说明：' + row.plotRatioRemark + '<br/>';
                    }
                    return result;
                }
            });
            cols.push({
                field: 'info4', title: '信息4', formatter: function (value, row, index) {
                    var result = '';
                    if (row.greeningRate) {
                        result += '绿化率：' + row.greeningRate * 100 + '%<br/>';
                    }
                    if (row.greeningRateRemark) {
                        result += '绿化率说明：' + row.greeningRateRemark + '<br/>';
                    }
                    if (row.buildDensity) {
                        result += '建筑密度：' + row.buildDensity * 100 + '%<br/>';
                    }
                    if (row.buildDensityRemark) {
                        result += '建筑密度说明：' + row.buildDensityRemark + '<br/>';
                    }
                    if (row.buildHeight) {
                        result += '建筑高度(米)：' + row.buildHeight + '<br/>';
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
                    if (row.dealPartInfo) {
                        result += '成交对象概况：' + row.dealPartInfo + '<br/>';
                    }
                    return result;
                }
            });

            cols.push({field: 'fileViewName', title: '附件'});
            if ('${netInfoAssignTask.source}' == 'net') {
                cols.push({
                    field: 'id', title: '查看网址', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<button type="button" class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="查看网址" onclick="detailInfo.prototype.openItem(' + row.masterId + ')"><i class="fa fa-eye fa-white"></i></button>';
                        str += '</div>';
                        return str;
                    }
                });
            }
            $("#land_list").bootstrapTable('destroy');
            TableInit("land_list", "${pageContext.request.contextPath}/netInfoAssignTask/getLandListByAssignTaskId", cols, {
                assignTaskId: '${netInfoAssignTask.id}'
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        openItem: function (id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/netInfoRecordController/getNetInfoRecordById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        if (result.data.sourceSiteUrl) {
                            window.open(result.data.sourceSiteUrl, "");
                        }
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            })
        }

    }
</script>