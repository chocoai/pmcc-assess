<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <%@include file="/views/share/project/projectInfoSimple.jsp" %>
                    <%@include file="/views/share/project/projectPlanDetails.jsp" %>
                    <!-- 公共模块end -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        ${judgeObject.name}
                                        <small>(${judgeObject.evaluationArea}㎡)</small>
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form class="form-horizontal" id="sure_price_form">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th>方法名称</th>
                                            <th>试算价格</th>
                                            <th>权重</th>
                                        </tr>
                                        </thead>
                                        <tbody id="tbody_data_section">
                                        <c:forEach items="${surePriceItemList}" var="item">
                                            <tr>
                                                <td>${item.methodName}</td>
                                                <td>${item.trialPrice}</td>
                                                <td><fmt:formatNumber value="${item.weight*100}" type="currency" pattern=".00"/>%</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                        </div>
                                    </div>
                                    <c:if test="${not empty schemeSurePrice.weightExplain}">
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    权重说明
                                                </label>
                                                <div class="col-sm-11">
                                                    <label class="form-control input-full">${schemeSurePrice.weightExplain}</label>
                                                </div>
                                            </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                最终单价
                                            </label>
                                            <div class="col-sm-3">
                                                <label class="form-control input-full">${schemeSurePrice.price}</label>
                                            </div>
                                        </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <c:if test="${not empty subJudgeObjectList}">
                        <div class="col-md-12">
                            <div class="card full-height">
                                <div class="card-header collapse-link">
                                    <div class="card-head-row">
                                        <div class="card-title">
                                            调整单价
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
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <table id="adjust_factor_table" class="table">
                                                    <thead>
                                                    <tr>
                                                        <th width="10%">权证号</th>
                                                        <th width="10%">证载面积</th>
                                                        <th width="10%">评估面积</th>
                                                        <th width="10%">楼层</th>
                                                        <th width="10%">房号</th>
                                                        <th width="10%">价格</th>
                                                        <th width="40%">因素</th>
                                                        <th width="10%">操作</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${subJudgeObjectList}" var="item">
                                                        <tr data-id="${item.id}">
                                                            <td>${item.name}</td>
                                                            <td>${item.floorArea}</td>
                                                            <td>${item.evaluationArea}</td>
                                                            <td>${item.floor}</td>
                                                            <td>${item.roomNumber}</td>
                                                            <td data-name="price">${item.price}</td>
                                                            <td data-name="coefficient">${item.factor}</td>
                                                            <td><div class="btn btn-xs btn-primary"
                                                                     onclick="determinePrice.getHouseRoomInfo(${item.id})">房间信息
                                                            </div><div class="btn btn-xs btn-primary"
                                                                                             onclick="determinePrice.getHouseId(${item.id})">单价调整
                                                            </div></td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

                    </c:if>
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        确认单价记录
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
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <table class="table table-bordered" id="tb_surePriceRecordList">
                                            </table>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- 公共尾部模块引用 -->
                    <%@include file="/views/share/form_approval.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>

</body>
<div id="divBoxHouseHuxingPriceTable" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">调查信息</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmHouseHuxingPriceTable" class="form-horizontal">
                    <input type="hidden" name="houseId">
                    <input type="hidden" name="tenementType">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row row form-group">
                                    <div class="col-md-12">
                                        <table class="table table-bordered" id="HouseHuxingPriceList">
                                            <!-- cerare document add ajax data-->
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
<div id="divBoxHouseRoomTable" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 70%">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">房间信息</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row row form-group">
                                    <div class="col-md-12">
                                        <table class="table table-bordered" id="HouseRoomList">
                                            <!-- cerare document add ajax data-->
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
<script type="application/javascript">
    $(function () {
        loadDocumentSend ();
    })

    function saveform() {
        saveApprovalform("");
    }


    var determinePrice = {};
    //加载合并对象的明细
    determinePrice.loadJudgeDetailList = function (pid) {
        var cols = [];
        cols.push({field: 'name', title: '权证号'});
        cols.push({field: 'originalPrice', title: '原单价'});
        cols.push({field: 'price', title: '调整单价'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top"  onclick="determinePrice.adjustFactor(' + row.id + ',' + row.declareRecordId + ')"><i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_judge_detail_list").bootstrapTable('destroy');
        TableInit("tb_judge_detail_list", "${pageContext.request.contextPath}/schemeProgramme/getJudgeObjectListByPid", cols, {
            pid: pid
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        });
    };

    //调整因素
    determinePrice.adjustFactor = function (judgeObjectId, declareId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/schemeSurePrice/getCertAdjustmentFactors",
            data: {
                declareId: declareId
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    $("#tbody_factor").empty();
                    $.each(result.data, function (i, item) {
                        var html = $("#adjustFatorTemp").html();
                        html = html.replace(/{factor}/g, AssessCommon.toString(AssessCommon.pointToPercent(item.factor)));
                        html = html.replace(/{remark}/g, AssessCommon.toString(item.remark));
                        $("#tbody_factor").append(html);
                    })
                    $("#judgeObjectId").val(judgeObjectId);
                }
                else {
                    AlertError("获取数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
        $("#modal_factor").modal();
    }

    //确认单价记录
    function loadDocumentSend() {
        var cols = [];
        cols.push({field: 'recordPrice', title: '单价记录'});
        cols.push({
            field: 'gmtCreated', title: '日期', formatter: function (value, row, index) {
                return formatDate(value);
            }
        });
        $("#tb_documentSendList").bootstrapTable('destroy');
        TableInit("tb_surePriceRecordList", "${pageContext.request.contextPath}/schemeSurePriceRecord/getSchemeSurePriceRecordList", cols, {
            planDetailsId: '${projectPlanDetails.id}'
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }

    determinePrice.getHouseId = function (judgeObjectId) {
        $.ajax({
            url: getContextPath() + "/schemeSurePrice/getBasicHouse",
            type: "get",
            dataType: "json",
            data: {judgeObjectId: judgeObjectId},
            success: function (result) {
                if (result.ret) {
                    if (houseHuxingPrice.prototype.isNotNull(result.data)) {
                        determinePrice.getHuxingId(result.data.id);
                    } else {
                        notifyInfo("提示", "标准房号未关联单价表")
                    }
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    determinePrice.getHuxingId = function (houseId) {
        $.ajax({
            url: getContextPath() + "/basicUnitHuxing/getHuxingByHouseId",
            type: "get",
            dataType: "json",
            data: {basicHouseId: houseId},
            success: function (result) {
                if (result.ret) {
                    if (houseHuxingPrice.prototype.isNotNull(result.data)) {
                        $("#" + houseHuxingPrice.prototype.config().tableFrm).find("input[name='houseId']").val(houseId);
                        $("#" + houseHuxingPrice.prototype.config().tableFrm).find("input[name='tenementType']").val(result.data.tenementType);
                        houseHuxingPrice.prototype.showTableModel();
                    } else {
                        notifyInfo("提示", "没有户型信息")
                    }
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    determinePrice.getHouseRoomInfo = function (judgeObjectId) {
        $.ajax({
            url: getContextPath() + "/schemeSurePrice/getBasicHouse",
            type: "get",
            dataType: "json",
            data: {judgeObjectId: judgeObjectId},
            success: function (result) {
                if (result.ret) {
                    if (houseHuxingPrice.prototype.isNotNull(result.data)) {
                        determinePrice.showHouseRoomModal(result.data.id, judgeObjectId);
                    }
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    determinePrice.showHouseRoomModal = function (houseId, judgeObjectId) {
        $.ajax({
            url: getContextPath() + "/basicUnitHuxing/getHuxingByHouseId",
            type: "get",
            dataType: "json",
            data: {basicHouseId: houseId},
            success: function (result) {
                if (result.ret) {
                    if (houseHuxingPrice.prototype.isNotNull(result.data)) {
                        determinePrice.loadHouseRoomList(houseId,result.data.tenementType);
                        $("#divBoxHouseRoomTable").modal("show");
                    } else {
                        notifyInfo("提示", "没有户型信息")
                    }
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    determinePrice.loadHouseRoomList =  function (houseId,tenementType) {
        var cols = commonColumn.houseRoomColumn();
        var temp = [];
        // if(houseCommon.houseHuxingForm.find('select[name="spatialDistribution"]').find("option:selected").text()=="多层"){
        //     cols.push({field: 'currentFloor', title: '所在楼层'});
        // }
        if (tenementType == '住宅' || tenementType == '办公') {
            temp = commonColumn.houseRoomResidence();
        } else if (tenementType == '商铺' || tenementType == '商场'|| tenementType == '车位') {
            temp = commonColumn.houseRoomStore();
        } else if (tenementType == '餐饮酒店') {
            temp = commonColumn.houseRoomHotel();
        } else if (tenementType == '生产') {
            temp = commonColumn.houseRoomProduction();
        } else if (tenementType == '仓储') {
            temp = commonColumn.houseRoomStorage();
        }
        $.each(temp, function (i, item) {
            cols.push(item);
        })
        cols.push({field: 'fileViewName', title: '附件'});
        $("#HouseRoomList").bootstrapTable('destroy');
        TableInit("HouseRoomList", getContextPath() + "/basicHouseRoom/getBootstrapTableVo", cols, {
            houseId: houseId
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }
</script>

<script type="application/javascript">
    houseHuxingPrice = function () {
    };
    houseHuxingPrice.prototype = {
        config: function () {
            var data = {};
            data.table = "HouseHuxingPriceList";
            data.box = "divBoxHouseHuxingPrice";
            data.frm = "frmHouseHuxingPrice";
            data.tableBox = "divBoxHouseHuxingPriceTable";
            data.tableFrm = "frmHouseHuxingPriceTable";
            return data;
        },
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function (houseId) {
            var cols = commonColumn.houseHuxingPriceColumn();
            var tenementType = $("#" + houseHuxingPrice.prototype.config().tableFrm).find("input[name='tenementType']").val();
            var temp = [];
            if (tenementType == '住宅' || tenementType == '办公') {
                temp = commonColumn.houseRoomResidence();
            } else if (tenementType == '商铺' || tenementType == '商场'|| tenementType == '车位') {
                temp = commonColumn.houseRoomStore();
            } else if (tenementType == '餐饮酒店') {
                temp = commonColumn.houseRoomHotel();
            } else if (tenementType == '生产') {
                temp = commonColumn.houseRoomProduction();
            } else if (tenementType == '仓储') {
                temp = commonColumn.houseRoomStorage();
            }
            $.each(temp, function (i, item) {
                cols.push(item);
            })
            cols.push({field: 'price', title: '价格'});
            cols.push({field: 'adjustFactor', title: '因素'});
            $("#" + houseHuxingPrice.prototype.config().table).bootstrapTable('destroy');
            TableInit(houseHuxingPrice.prototype.config().table, getContextPath() + "/basicHouseHuxingPrice/getBootstrapTableVo", cols, {
                houseId: houseId
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        showTableModel: function () {
            var houseId = $("#" + houseHuxingPrice.prototype.config().tableFrm).find("input[name='houseId']").val();
            houseHuxingPrice.prototype.loadDataDicList(houseId);
            $('#' + houseHuxingPrice.prototype.config().tableBox).modal("show");
        },
        isNotNull: function (item) {
            if (item) {
                return true;
            }
            return false;
        }
    }
</script>
</body>
</html>

