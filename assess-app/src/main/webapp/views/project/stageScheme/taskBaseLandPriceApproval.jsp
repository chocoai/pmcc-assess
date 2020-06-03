<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
< lang="en" class="no-js">
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
                            <div class="card-body" style="display: none">
                                <div class="row col-md-12">
                                    <div class="col-md-6">
                                        <div class="x_title">
                                            土地指数
                                        </div>
                                        <form class="form-horizontal">
                                            <div class="row form-group">
                                                <div class="col-md-12">
                                                    <table class="table table-bordered"
                                                           id="indexDetailTable">
                                                        <!-- cerare document add ajax data-->
                                                    </table>
                                                </div>
                                            </div>
                                        </form>

                                    </div>
                                    <div class="col-md-6">
                                        <div class="x_title">
                                            容积率修正系数表
                                        </div>
                                        <form class="form-horizontal">
                                            <div class="row form-group">
                                                <div class="col-md-12">
                                                    <table class="table table-bordered"
                                                           id="volumetricRateDetailTable">
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
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        因素条件说明及修正系数
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form class="form-horizontal" id="areaAndSeveralAmendForm">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">区域及个别修正系数</label>
                                                <div class="col-sm-3">
                                                    <div class="input-group">
                                                        <input type="text" readonly="readonly"
                                                               class="form-control"
                                                               id="areaAndSeveralAmend">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <table class="table table-bordered" id="landLevelTableList">
                                                <thead>
                                                <tr>
                                                    <th  width="10%">土地级别类型</th>
                                                    <th  width="10%">土地级别类别</th>
                                                    <th  width="10%">土地级别等级</th>
                                                    <th  width="20%">说明</th>
                                                    <th  width="10%">分值</th>
                                                </tr>
                                                </thead>
                                                <tbody id="landLevelTabContent">

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        基准地价因素
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form class="form-horizontal" id="master">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    基准地价(元/㎡)
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${master.standardPremium}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    说明
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${master.standardPremiumRemark}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    土地还原利率
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${master.rewardRate}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    法定年限
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${master.legalAge}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    说明
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${master.legalAgeRemark}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    剩余使用年限
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${master.landSurplusYear}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    说明
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${master.landSurplusYearRemark}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    开发程度修正
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${master.developCorrect}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    说明
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${master.developCorrectRemark}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    委估宗地面积(㎡)
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${master.evaluationArea}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    说明
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${master.evaluationAreaRemark}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">
                                                    委估对象容积率
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${master.volumetricRate}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    说明
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class="form-control input-full">${master.volumetricRateRemark}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        测算结果
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
                                            <table class="table table-bordered">
                                                <tbody>
                                                <tr>
                                                    <td>期日修正系数</td>
                                                    <td id="dateAmend">${master.dateAmend}</td>
                                                    <td>年期修正系数</td>
                                                    <td id="periodAmend">${master.periodAmend}</td>
                                                </tr>
                                                <tr>
                                                    <td>容积率修正</td>
                                                    <td id="volumeFractionAmend">${master.volumeFractionAmend}</td>
                                                    <td>委估宗地单价（元/㎡）</td>
                                                    <td id="parcelPrice">${master.parcelPrice}</td>
                                                </tr>
                                                <tr>
                                                    <td>委估宗地单价（万元/亩）</td>
                                                    <td id="parcelBhouPrice">${master.parcelBhouPrice}</td>
                                                    <td>委估宗地总价（万元）</td>
                                                    <td id="parcelTotalPrice">${master.parcelTotalPrice}</td>
                                                </tr>
                                                <tr>
                                                    <td>楼面地价</td>
                                                    <td id="floorPremium">${master.floorPremium}</td>
                                                    <td>修正差额(%)</td>
                                                    <td id="correctionDifference">${master.correctionDifference}</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <%@include file="/views/share/form_approval.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>


</body>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.estate.js?v=${assessVersion}"></script>
<script type="text/html" id="landLevelTabContentBody">
    <tr class="group">
        <td>
            {typeName}
        </td>
        <td class="table-cell">
            {landLevelTypeName}
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        <td>
            {gradeName}
        </td>
        <td>
            {reamark}
        </td>
        <td>
            <label name="landFactorTotalScore" class="form-control input-full">{landFactorTotalScore}</label>
        </td>
    </tr>
</script>

<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/case/case.common.js?v=${assessVersion}"></script>
<script type="application/javascript">
    function saveform() {
        saveApprovalform("");

    }

    $(function () {
        //土地指数表
        getLandIndexId();
        //容积率修正系数表
        showVolumetricRateDetailList('${hasVolumeFractionAmendId}');
        getLandLevelTabContent();
        $("#areaAndSeveralAmend").val(AssessCommon.pointToPercent('${master.areaAndSeveralAmend}'));
    });

    //v取几位小数
    function getSomePlaces(num, v) {
        var vv = Math.pow(10, v);
        return Math.round(num * vv) / vv;
    }

    //土地指数表
    function getLandIndexId() {
        $.ajax({
            url: "${pageContext.request.contextPath}/baseLandPrice/getLandIndexId",
            type: "get",
            dataType: "json",
            data: {judgeObjectId: '${projectPlanDetails.judgeObjectId}'},
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        showLandIndexDetailList(result.data);
                    }
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })

    }

    function showLandIndexDetailList(landIndexId) {
        var cols = [];
        cols.push({
            field: 'startDate', title: '开始月份', formatter: function (value, row, index) {
                return formatDate(value);
            }
        });
        cols.push({
            field: 'endDate', title: '结束月份', formatter: function (value, row, index) {
                return formatDate(value);
            }
        });
        cols.push({field: 'indexNumber', title: '指数'});
        $("#indexDetailTable").bootstrapTable('destroy');
        TableInit("indexDetailTable", "${pageContext.request.contextPath}/dataHousePriceIndexDetail/getBootstrapTableVo", cols, {
            housePriceId: landIndexId
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };


    //容积率修正系数表
    function showVolumetricRateDetailList(hasVolumeFractionAmendId) {
        var cols = [];
        cols.push({field: 'plotRatio', title: '容积率'});
        cols.push({field: 'correctionFactor', title: '修正系数'});
        $("#volumetricRateDetailTable").bootstrapTable('destroy');
        TableInit("volumetricRateDetailTable", "${pageContext.request.contextPath}/dataLandLevelDetailVolume/getBootstrapTableVo", cols, {
            levelDetailId: hasVolumeFractionAmendId
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };


    //因素条件说明及修正系数
    function getLandLevelTabContent() {
        if(!'${landLevelId}'&&!'${levelDetailId}'){
            notifyInfo("提示","未关联土地级别");
            return false;
        }
        FileUtils.getFileShows({
            target: "select_land_level_file",
            formData: {
                tableName: AssessDBKey.DataLandLevel,
                tableId: '${landLevelId}'
            },
            deleteFlag: false
        })

        var jsonContent = JSON.parse('${master.landLevelContent}');
        var data = caseCommon.landLevelFilter(jsonContent);
        if (jQuery.isEmptyObject(data)) {
            return false;
        }
        //$("#detailAchievementModal").modal();
        var target = $("#landLevelTabContent");
        target.empty();

        //由于js来筛选 有大量json 解析或者字符串化 影响代码阅读度，因此改为了后台直接处理,第一次的时候有2此筛选分类这样确实代码可读性差
        data.forEach(function (dataA, indexM) {
            var rows = [];
            $.each(dataA, function (i, obj) {
                var item;
                obj.forEach(function (value, index) {
                    if (value.modelStr == "update") {
                        item = value;
                        rows.push(item)
                    }
                });
                var landLevelBodyHtml = $("#landLevelTabContentBody").html();
                if (landLevelBodyHtml) {
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landFactorTotalScore}/g, AssessCommon.pointToPercent(item.achievement));
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{typeName}/g, item.typeName);
                    var landLevelTypeName = "";
                    if(item.classification){
                        landLevelTypeName+=item.classification;
                    }
                    if(item.category){
                        landLevelTypeName+="/"+item.category;
                    }
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelTypeName}/g, landLevelTypeName);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{gradeName}/g, item.gradeName);
                    var text = "";
                    $.each(obj, function (i, n) {
                        text += "等级:" + n.gradeName + "，说明:" + n.reamark + "； \r";
                    });
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{reamark}/g, text);
                    target.append(landLevelBodyHtml);
                }
            });
            var length = rows.length;// 获取当前表格中tr的个数

            var mark = 0; //要合并的单元格数

            var index = 0; //起始行数

            if(length <= 1){
            }else{
                for(var i=0;i < length ;i++){
                    var ford = $("#landLevelTableList tr:gt(0):eq("+i+") td:eq(0)").text();
                    var behind = $("#landLevelTableList tr:gt(0):eq("+(parseInt(i)+1)+") td:eq(0)").text();
                    if(ford == behind){
                        $("#landLevelTableList tr:gt(0):eq("+(parseInt(i)+1)+") td:eq(0)").hide();
                        mark = mark +1;
                    }else if(ford != behind){
                        index = i-mark;
                        $("#landLevelTableList tr:gt(0):eq("+index+") td:eq(0)").attr("rowspan",mark+1);//+1 操作标识，将当前的行加入到隐藏
                        mark = 0;
                        $("#landLevelTableList tr:gt(0):eq("+(parseInt(i))+") td:eq(0)").hide();
                    }
                }
            }
        });
    }
</script>

</body>
</html>

