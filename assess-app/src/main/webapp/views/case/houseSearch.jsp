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
                                    用途
                                </label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <input type="text" class="form-control" name="houseType" placeholder="用途"/>
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
                                    面积
                                </label>
                                <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                                    <input type="text" class="form-control" placeholder="从"
                                           id="areaStart"
                                           data-rule-number="true" name="areaStart"/>
                                </div>
                                <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                                    <input type="text" class="form-control" placeholder="到" id="areaEnd"
                                           data-rule-number="true" name="areaEnd"/>
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

<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">详细信息</h3>
            </div>
            <form id="frmFather" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            省
                                        </label>
                                        <div class="col-sm-2">
                                            <label class="form-control" name="provinceName"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            市
                                        </label>
                                        <div class="col-sm-2">
                                            <label class="form-control" name="cityName"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            区
                                        </label>
                                        <div class="col-sm-2">
                                            <label class="form-control" name="districtName"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            房产类型
                                        </label>
                                        <div class="col-sm-2">
                                            <label class="form-control" name="houseType"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            房产类别
                                        </label>
                                        <div class="col-sm-2">
                                            <label class="form-control" name="houseCategory"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            街道
                                        </label>
                                        <div class="col-sm-2">
                                            <label class="form-control" name="street"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            面积
                                        </label>
                                        <div class="col-sm-2">
                                            <label class="form-control" name="area"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            楼盘名称
                                        </label>
                                        <div class="col-sm-2">
                                            <label class="form-control" name="estateName"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            交易方式
                                        </label>
                                        <div class="col-sm-2">
                                            <label class="form-control" name="dealTypeName"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            成交价
                                        </label>
                                        <div class="col-sm-2">
                                            <label class="form-control" name="currentPrice"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            评估价
                                        </label>
                                        <div class="col-sm-2">
                                            <label class="form-control" name="consultPrice"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            评估基准日期
                                        </label>
                                        <div class="col-sm-2">
                                            <input name="assessStandardDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            单价
                                        </label>
                                        <div class="col-sm-2">
                                            <label class="form-control" name="tradingUnitPrice"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            变现率
                                        </label>
                                        <div class="col-sm-2">
                                            <input class="form-control" id="realizationRatios" readonly></input>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            变现周期
                                        </label>
                                        <div class="col-sm-2">
                                            <label class="form-control" name="realizationCycle"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            成交对象概况
                                        </label>
                                        <div class="col-sm-11">
                                            <label class="form-control" name="dealPartInfo"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            附件
                                        </label>
                                        <div class="col-sm-10">
                                            <div id="_uploadHouseFile"></div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        关闭
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/map.position.js?v=${assessVersion}"></script>
</html>
<script type="text/javascript">
    $(function () {
        AssessCommon.initAreaInfo({
            useDefaultText: false,
            provinceTarget: $("#province"),
            cityTarget: $("#city"),
            districtTarget: $("#district"),
            success: function () {
                houseSearch.loadDataList();
            }
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseTransactionType, '', function (html, data) {
            $("#frmCaseBaseHouse").find("select[name='tradingType']").empty().html(html).trigger('change');
        });
    });
    var houseSearch = {};
    var houseFrm = $("#frmCaseBaseHouse");

    houseSearch.findHouse = function (id) {
        var href = "${pageContext.request.contextPath}/basicHouse/detailView";
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
        cols.push({
            field: 'name', title: '名称', formatter: function (value, row, index) {
                var s = row.fullName;
                if (row.creatorName) {
                    s += "<label style='padding: 5px;' class='label label-info'>" + row.creatorName + "</label>"
                }
                return s;
            }
        });
        cols.push({
            field: 'blockName', title: '版块名称', formatter: function (value, row, index) {
                var s = AssessCommon.getAreaFullName(row.provinceName,row.cityName,row.districtName);
                if (row.blockName) {
                    s += "/" + row.blockName;
                }
                return s;
            }
        });
        cols.push({field: 'street', title: '街道'});
        cols.push({field: 'houseType', title: '用途'});
        cols.push({field: 'tradingTypeName', title: '交易类型'});
        cols.push({
            field: 'tradingTime', title: '交易时间', formatter: function (value, row, index) {
                return formatDate(value);
            }
        });
        cols.push({field: 'tradingUnitPrice', title: '交易单价'});
        cols.push({field: 'area', title: '面积'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                if (row.caseHouseId) {
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="查看" onclick="houseSearch.findHouse(' + row.caseHouseId + ')"><i class="fa fa-search fa-white"></i></a>';
                } else {
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="查看" onclick="houseSearch.showItem(' + row.id + ')"><i class="fa fa-search fa-white"></i></a>';
                }
                str += '</div>';
                return str;
            }
        });
        $("#tbCaseBaseHouseList").bootstrapTable('destroy');
        TableInit("tbCaseBaseHouseList", "${pageContext.request.contextPath}/basic/getBootstrapTableCaseBaseHouseVo", cols,
            formSerializeArray(houseFrm), {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
    };

    houseSearch.showItem = function (id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/basic/getDataById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    $("#frmFather").initForm(result.data);
                    $("#frmFather").find("input[name='assessStandardDate']").attr("disabled", true);
                    $("#realizationRatios").val(AssessCommon.pointToPercent(result.data.realizationRatios));
                    houseSearch.showFile(id);
                    $("#divBoxFather").modal("show");
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    };


    houseSearch.showFile = function (id) {
        FileUtils.getFileShows({
            target: "uploadHouseFile",
            formData: {
                tableName: "tb_basic_house_case_summary",
                tableId: id
            },
            deleteFlag: true
        })
    };
</script>
