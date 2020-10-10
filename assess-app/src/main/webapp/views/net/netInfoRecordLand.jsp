<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body>
<div class="wrapper">
    <%@include file="/views/share/main_navigation.jsp" %>
    <%@include file="/views/share/main_head.jsp" %>
    <div class="main-panel">
        <div class="content">
            <div class="panel-header bg-primary-gradient">
                <div class="page-inner py-5">
                </div>
            </div>
            <div class="page-inner mt--5">
                <div class="row mt--2">

                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">${baseViewDto.currentMenu.name}</div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frmQuery" class="form-horizontal">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-md-1 col-form-label">省</label>
                                                <div class="col-md-2">
                                                    <select name="province"
                                                            class="form-control input-full search-select select2">
                                                    </select>
                                                </div>
                                                <label class="col-md-1 col-form-label">市</label>
                                                <div class="col-md-2">
                                                    <select name="city"
                                                            class="form-control input-full search-select select2">
                                                    </select>
                                                </div>
                                                <label class="col-md-1 col-form-label">区县</label>
                                                <div class="col-md-2">
                                                    <select name="district"
                                                            class="form-control input-full search-select select2">
                                                    </select>
                                                </div>
                                                <label class="col-md-1 col-form-label">街道</label>
                                                <div class="col-md-2">
                                                    <input type="text" class="form-control input-full" name="street">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-md-1 col-form-label">土地类型</label>
                                                <div class="col-md-2">
                                                    <select name="belongType"
                                                            class="form-control input-full search-select select2">
                                                    </select>
                                                </div>
                                                <label class="col-md-1 col-form-label">土地类别</label>
                                                <div class="col-md-2">
                                                    <select name="belongCategory"
                                                            class="form-control input-full search-select select2">
                                                    </select>
                                                </div>
                                                <label class="col-md-1 col-form-label">交易方式</label>
                                                <div class="col-md-2">
                                                    <select name="dealType"
                                                            class="form-control input-full search-select select2">
                                                    </select>
                                                </div>
                                                <label class="col-md-1 col-form-label">开始时间</label>
                                                <div class="col-md-2">
                                                    <input name="negotiatedDateStart" data-date-format="yyyy-mm-dd"
                                                           class="form-control input-full date-picker dbdate">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-md-1 col-form-label">结束时间</label>
                                                <div class="col-md-2">
                                                    <input name="negotiatedDateEnd" data-date-format="yyyy-mm-dd"
                                                           class="form-control input-full date-picker dbdate">
                                                </div>

                                                <button style="margin-left: 10px" class="btn btn-info  btn-sm"
                                                        type="button"
                                                        onclick="detailInfo.prototype.loadDataInfoList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                                    查询
                                                </button>

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
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>


</body>


<script type="text/javascript">
    $(function () {
        detailInfo.prototype.loadDataInfoList();
        AssessCommon.initAreaInfo({
            useDefaultText: false,
            provinceTarget: $("select[name='province']"),
            cityTarget: $("select[name='city']"),
            districtTarget: $("select[name='district']")
        })
        AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, '', function (html, data) {
            $("#frmQuery").find("select[name='belongType']").empty().html(html).trigger('change');
        });
        $("#frmQuery").find("select[name='belongType']").off('change').on('change', function () {
            AssessCommon.loadSonDataListHtml($(this).val(), "", function (html, data) {
                $("#frmQuery").find("select[name='belongCategory']").empty().html(html).trigger('change');
            });
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.dataDealType, '', function (html, data) {
            $("#frmQuery").find("select[name='dealType']").empty().html(html).trigger('change');
        });
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
                field: 'parcelSite', title: '宗地位置', formatter: function (value, row, index) {
                    var s = '';
                    if (value) {
                        s += value;
                    }
                    if (row.creatorName) {
                        s += "<span style='margin-left: 5px;' class='label label-info'>" + row.creatorName + "</span>"
                    }
                    if (row.approverName) {
                        s += "<span style='margin-left: 5px;' class='label label-danger'>" + row.approverName + "</span>"
                    }
                    return s;
                }
            });
            cols.push({field: 'belongType', title: '土地类型'});
            cols.push({field: 'belongCategory', title: '土地类别'});
            cols.push({field: 'dealTypeName', title: '交易方式'});

            cols.push({
                field: 'negotiatedDate', title: '交易时间', formatter: function (value, row, index) {
                    return formatDate(row.negotiatedDate, false);
                }
            });
            cols.push({field: 'landAreaCentiare', title: '净用地面积(㎡)'});
            cols.push({field: 'landAreaMu', title: '净用地面积(亩)'});
            cols.push({field: 'unitPrice', title: '成交单价（元/㎡）'});
            cols.push({field: 'unitPriceMu', title: '成交单价（元/亩）'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<button onclick="detailInfo.prototype.getAndInit(' + row.id + ')" style="margin-left: 5px;" class="btn btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看">';
                    str += '<i class="fa fa-search"></i>';
                    str += '</button>';
                    str += '<button onclick="detailInfo.prototype.upgradeApply(' + index + ')" style="margin-left: 5px;" class="btn btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="升级">';
                    str += '升级';
                    str += '</button>';
                    return str;
                }
            });
            $("#" + detailInfo.prototype.config().table).bootstrapTable('destroy');
            TableInit(detailInfo.prototype.config().table, "${pageContext.request.contextPath}/netInfoRecordLand/landList", cols, {
                province: $("#frmQuery").find("select[name='province']").val(),
                city: $("#frmQuery").find("select[name='city']").val(),
                district: $("#frmQuery").find("select[name='district']").val(),
                street: $("#frmQuery").find("input[name='street']").val(),

                belongType: $("#frmQuery").find("select[name='belongType']").val(),
                belongCategory: $("#frmQuery").find("select[name='belongCategory']").val(),
                dealType: $("#frmQuery").find("select[name='dealType']").val(),
                negotiatedDateStart: $("#frmQuery").find("input[name='negotiatedDateStart']").val(),
                negotiatedDateEnd: $("#frmQuery").find("input[name='negotiatedDateEnd']").val()
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
        }, getAndInit: function (id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/netInfoRecordLand/getDataById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#frmFather").clearAll().initForm(result.data);
                        $("#frmFather").find("input").attr("disabled", true);
                        $("#frmFather").find("select").attr("disabled", true);
                        $("#frmFather").find("textarea").attr("disabled", true);
                        $("#frmFather").find('[name=buildDensityStr]').text(AssessCommon.pointToPercent(result.data.buildDensity));
                        detailInfo.prototype.showFile("uploadLandFile", "tb_net_info_record_land", id);
                        $('#divBoxFather').modal("show");
                    }
                },
                error: function (result) {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result);
                }
            })
        }, showFile: function (target, tableName, id) {
            FileUtils.getFileShows({
                target: target,
                formData: {
                    tableName: tableName,
                    tableId: id
                },
                deleteFlag: true
            })
        },
        upgradeApply: function (index) {
            var row = $("#transaction_List").bootstrapTable('getData')[index];
            var href = "${pageContext.request.contextPath}/netInfoUpgrade/apply";
            href += "?dataId=" + row.id + "&type=" + row.type + "&masterDataId=" + row.masterId;
            window.open(href, "");
        }
    }

</script>

<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 80%">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">详情信息</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmFather" class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 col-form-label">
                                                省
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" name="provinceName"
                                                       class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                市
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" name="cityName"
                                                       class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                区
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" name="districtName"
                                                       class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                街道
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" name="street"
                                                       class="form-control input-full"></label>
                                            </div>

                                        </div>
                                    </div>

                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 col-form-label">
                                                宗地位置
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" name="parcelSite"
                                                       class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                地块名称
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" name="name" class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                宗地编号
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" name="parcelNumber"
                                                       class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                土地性质
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" name="landPurpose" placeholder="出让、划拨"
                                                       class="form-control input-full"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 col-form-label">
                                                土地类型
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" name="belongType"
                                                       class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                土地类别
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" name="belongCategory"
                                                       class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                交易方式
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" name="dealTypeName"
                                                       class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                成交(协商)日期
                                            </label>
                                            <div class="col-sm-2">
                                                <label name="negotiatedDate" data-date-format="yyyy-mm-dd"
                                                       id="landNegotiatedDate"
                                                       class="form-control input-full date-picker dbdate"></label>
                                            </div>

                                        </div>
                                    </div>

                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 col-form-label">
                                                面积
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" data-rule-number="true" data-rule-maxlength="50"
                                                       id="landArea" name="area"
                                                       class="form-control input-full"></label></div>
                                            <label class="col-sm-1 col-form-label">
                                                单位
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" name="areaUnit" placeholder="平方米、亩"
                                                       class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                净用地面积
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" data-rule-number="true" data-rule-maxlength="50"
                                                       name="landArea" class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                净用地面积单位
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" name="landAreaUnit" placeholder="平方米、亩"
                                                       class="form-control input-full"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 col-form-label">
                                                成交总价（万元）
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" data-rule-number="true" data-rule-maxlength="50"
                                                       id="landCurrentPrice"
                                                       name="currentPrice" class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                成交单价（元/㎡）
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" data-rule-number="true" data-rule-maxlength="50"
                                                       name="unitPrice" class="form-control input-full"
                                                       id="landUnitPrice"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                成交单价（万元/每亩）
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" data-rule-number="true" data-rule-maxlength="50"
                                                       name="unitPriceMu" class="form-control input-full"
                                                       id="unitPriceMu"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                成交楼面地价（元/㎡）
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" data-rule-number="true" data-rule-maxlength="50"
                                                       name="floorPrice" class="form-control input-full"></label>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 col-form-label">
                                                评估基准日期
                                            </label>
                                            <div class="col-sm-2">
                                                <label name="assessStandardDate" data-date-format="yyyy-mm-dd"
                                                       id="landAssessStandardDate"
                                                       class="form-control input-full date-picker dbdate"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                评估起拍单价（元/㎡）
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" data-rule-number="true" data-rule-maxlength="50"
                                                       name="consultPrice" id="landConsultPrice"
                                                       class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                评估起拍单价（万元/每亩）
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" data-rule-number="true" data-rule-maxlength="50"
                                                       name="consultPriceMu" id="consultPriceMu"
                                                       class="form-control input-full"></label>
                                            </div>


                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 col-form-label">
                                                变现周期
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" name="realizationCycle" id="landRealizationCycle"
                                                       class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                变现率
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" name="realizationRatiosStr"
                                                       class="form-control input-full "></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                容积率
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" name="plotRatio" data-rule-number="true"
                                                       data-rule-maxlength="50" class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                容积率说明
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" name="plotRatioRemark"
                                                       class="form-control input-full"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 col-form-label">
                                                绿化率
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" name="greeningRateStr"
                                                       class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                绿化率说明
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" name="greeningRateRemark"
                                                       class="form-control input-full"></label></div>
                                            <label class="col-sm-1 col-form-label">
                                                建筑密度
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" name="buildDensityStr"
                                                       class="form-control input-full"></label></div>
                                            <label class="col-sm-1 col-form-label">
                                                建筑密度说明
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" name="buildDensityRemark"
                                                       class="form-control input-full"></label></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 col-form-label">
                                                建筑高度(米)
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" name="buildHeight"
                                                       class="form-control input-full"
                                                       data-rule-number="true" data-rule-maxlength="50"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                建筑高度说明
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" name="buildHeightRemark"
                                                       class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                指标款(亩)
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" name="indexAmount"
                                                       class="form-control input-full"
                                                       data-rule-number="true" data-rule-maxlength="50"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                指标款说明
                                            </label>
                                            <div class="col-sm-2">
                                                <label type="text" name="indexAmountRemark"
                                                       class="form-control input-full"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                成交对象概况
                                            </label>
                                            <div class="col-sm-11">
                                                <label name="dealPartInfo" id="landDealPartInfo"
                                                       class="form-control input-full"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                附件
                                            </label>
                                            <div class="col-sm-11">
                                                <div id="_uploadLandFile"></div>
                                            </div>
                                        </div>
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

</html>
