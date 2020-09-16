<%--
  Created by IntelliJ IDEA.
  User: zch
  Date: 2020-9-16
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/views/project/tool/rewardRate.jsp"></jsp:include>

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
                            <input type="hidden" name="landLevelContent"
                                   id="landLevelContent"
                                   value='${master.landLevelContent}'>
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">区域及个别修正系数</label>
                            <div class="col-sm-3">
                                <div class="input-group">
                                    <input type="text"
                                           class="form-control x-percent"
                                           name="areaAndSeveralAmend"
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
                                <th width="10%">土地级别类型</th>
                                <th width="10%">土地级别类别</th>
                                <th width="10%">土地级别等级</th>
                                <th width="20%">说明</th>
                                <th width="10%">分值</th>
                                <th width="5%"></th>
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
                <input type="hidden" name="id" value="${master.id}">
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                基准地价(元/㎡)
                            </label>
                            <div class="col-sm-3">
                                <input type="text" placeholder="农用地总面积"
                                       class="form-control input-full"
                                       id="standardPremium" name="standardPremium"
                                       required
                                       onblur="getParcelPrice()"
                                       value="${master.standardPremium}"
                                       data-rule-number="true">
                            </div>
                            <label class="col-sm-1 control-label">
                                说明
                            </label>
                            <div class="col-sm-3">
                                <input type="text"
                                       value="${master.standardPremiumRemark}"
                                       class="form-control input-full"
                                       name="standardPremiumRemark">
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
                                <div class="input-group">
                                    <input type="text" required
                                           class="form-control x-percent"
                                           id="rewardRate"
                                           name="rewardRate" placeholder="还原率"
                                           readonly="readonly"
                                           value="${master.rewardRate}">

                                    <div class="input-group-prepend">
                                        <input type="hidden" name="rewardRateId"
                                               value="${master.rewardRateId}">
                                        <button class="btn btn-info btn-sm "
                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                type="button"
                                                onclick="getRewardRate(this);">还原率测算
                                        </button>
                                    </div>
                                </div>
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
                                <input type="text" placeholder="法定年限"
                                       class="form-control input-full"
                                       id="legalAge"
                                       name="legalAge" required
                                       onblur="getPeriodAmend()"
                                       value="${master.legalAge}"
                                       data-rule-number="true">
                            </div>
                            <label class="col-sm-1 control-label">
                                说明
                            </label>
                            <div class="col-sm-3">
                                <input type="text" value="${master.legalAgeRemark}"
                                       class="form-control input-full"
                                       name="legalAgeRemark">
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
                                <input type="text" placeholder="剩余使用年限"
                                       class="form-control input-full"
                                       id="landSurplusYear"
                                       name="landSurplusYear" required
                                       onblur="getPeriodAmend()"
                                       value="${master.landSurplusYear}"
                                       data-rule-number="true">
                            </div>
                            <label class="col-sm-1 control-label">
                                说明
                            </label>
                            <div class="col-sm-3">
                                <input type="text"
                                       value="${master.landSurplusYearRemark}"
                                       class="form-control input-full"
                                       name="landSurplusYearRemark">
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
                                <input type="text" placeholder="开发程度修正"
                                       class="form-control input-full"
                                       id="developCorrect"
                                       name="developCorrect" required
                                       onblur="getParcelPrice()"
                                       value="${master.developCorrect}"
                                       data-rule-number="true">
                            </div>
                            <label class="col-sm-1 control-label">
                                说明
                            </label>
                            <div class="col-sm-3">
                                <input type="text"
                                       value="${master.developCorrectRemark}"
                                       class="form-control input-full"
                                       name="developCorrectRemark">
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
                                <input type="text" placeholder="委估宗地面积"
                                       class="form-control input-full"
                                       id="evaluationArea"
                                       name="evaluationArea" required
                                       onblur="getParcelPrice()"
                                       value="${master.evaluationArea}"
                                       data-rule-number="true">
                            </div>
                            <label class="col-sm-1 control-label">
                                说明
                            </label>
                            <div class="col-sm-3">
                                <input type="text"
                                       value="${master.evaluationAreaRemark}"
                                       class="form-control input-full"
                                       name="evaluationAreaRemark">
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
                                <input type="text" placeholder="委估对象容积率"
                                       class="form-control input-full"
                                       id="volumetricRate"
                                       name="volumetricRate" required
                                       onblur="getParcelPrice()"
                                       value="${master.volumetricRate}"
                                       data-rule-number="true">
                            </div>
                            <label class="col-sm-1 control-label">
                                说明
                            </label>
                            <div class="col-sm-3">
                                <input type="text"
                                       value="${master.volumetricRateRemark}"
                                       class="form-control input-full"
                                       name="volumetricRateRemark">
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
                                <td id="dateAmend">${dateAmend}</td>
                                <td>年期修正系数</td>
                                <td id="periodAmend">
                                </td>
                            </tr>
                            <tr>
                                <td>容积率修正</td>
                                <td id="volumeFractionAmend">${volumeFractionAmend}</td>
                                <td>委估宗地单价（元/㎡）</td>
                                <td id="parcelPrice">
                                </td>
                            </tr>
                            <tr>
                                <td>委估宗地单价（万元/亩）</td>
                                <td id="parcelBhouPrice">
                                </td>
                                <td>委估宗地总价（万元）</td>
                                <td id="parcelTotalPrice">
                                </td>
                            </tr>
                            <tr>
                                <td>楼面地价</td>
                                <td id="floorPremium">
                                </td>
                                <td>修正差额(%)</td>
                                <td id="correctionDifference">
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


<script type="text/html" id="landLevelTabContentBody">
    <tr class="group">
        <td>{typeName}</td>
        <td class="table-cell">{landLevelTypeName}</td>
        <td>
            <input type="hidden" name="levelDetailId" value="{levelDetailId}">
            <input type="hidden" name="type" value="{type}">
            <input type="hidden" name="classification" value="{classification}">
            <input type="hidden" name="category" value="{category}">
            <select class="form-control input-full" name="landLevelGrade" onchange="landLevelHandle(this);">
                {landLevelGradeHTML}
            </select>
        </td>
        <td>{reamark}</td>
        <td>
            <input type="hidden" class="form-control input-full" name="dataLandLevelAchievement"
                   value="{dataLandLevelAchievement}">
            <input type="text" class="form-control input-full  x-percent" name="landFactorTotalScore"
                   value="{landFactorTotalScore}" onblur="getAreaAndSeveralAmend();">
            <input type="hidden" name="landLevelContent" value='{landLevelContent}'>
        </td>
        <td>
            <input class="btn btn-sm btn-warning" type="button" value="X"
                   onclick="landLevelEmpty(this)">
        </td>
    </tr>
</script>

<script>

    //获取报酬率
    function getRewardRate(_this) {
        var group = $(_this).closest('.input-group');
        rewardRateFunc.calculation(group.find('[name=rewardRateId]').val(), function (data) {
            if (data) {
                group.find('[name=rewardRateId]').val(data.id);
                var element = group.find(':text');
                element.val(data.resultValue);
                AssessCommon.elementParsePoint(group.find('[name=rewardRate]').val(data.resultValue));
                //获取年期修正系数
                getPeriodAmend();
            }
        })
    }

    //获取委估宗地单价（元/㎡）
    function getParcelPrice() {
        //基准地价E4
        var standardPremium = parseFloat($("#standardPremium").val());
        //期日修正系数E5
        var dateAmend = parseFloat($("#dateAmend").text());
        //年期修正系数E6
        var periodAmend = parseFloat($("#periodAmend").text());
        //容积率修正E10
        var volumeFractionAmend = parseFloat($("#volumeFractionAmend").text());
        //区域及个别修正系数E11
        var areaAndSeveralAmend = parseFloat(AssessCommon.percentToPoint($("#areaAndSeveralAmend").val())) ? parseFloat(AssessCommon.percentToPoint($("#areaAndSeveralAmend").val())) : 0;
        //开发程度修正E12
        var developCorrect = parseFloat($("#developCorrect").val()) ? parseFloat($("#developCorrect").val()) : 0;
        //委估宗地面积E15
        var evaluationArea = parseFloat($("#evaluationArea").val());
        //委估对象容积率G17
        var volumetricRate = parseFloat($("#volumetricRate").val());

        if (standardPremium && dateAmend && periodAmend && volumeFractionAmend) {
            var money = standardPremium * dateAmend * periodAmend * volumeFractionAmend * (1 + areaAndSeveralAmend) + developCorrect;
            if (money) {
                //宗地单价
                $("#parcelPrice").text(getSomePlaces(money, 2));
                //宗地亩价
                $("#parcelBhouPrice").text(getBhouPrice(money, 2));
                //宗地总价
                if (evaluationArea) {
                    $("#parcelTotalPrice").text(getSomePlaces(evaluationArea * money / 10000, 2));
                }
                //楼面地价
                if (volumetricRate) {
                    $("#floorPremium").text(getSomePlaces(money / volumetricRate, 2));
                }
                //修正差额
                $("#correctionDifference").text(getSomePlaces((money / standardPremium - 1) * 100, 2) + "%");
            }
        }
    }

    //获取一亩的价
    function getBhouPrice(num, v) {
        return getSomePlaces(num * AssessCommon.BHOU / 10000, v);
    }

    //v取几位小数
    function getSomePlaces(num, v) {
        var vv = Math.pow(10, v);
        return Math.round(num * vv) / vv;
    }

    //获取年期修正系数
    function getPeriodAmend() {
        //E7
        var rewardRate = parseFloat(AssessCommon.percentToPoint($("#rewardRate").val()));
        //E9
        var landSurplusYear = parseFloat($("#landSurplusYear").val());
        //E8
        var legalAge = parseFloat($("#legalAge").val());
        //(1-1/(1+E7)^E9)/(1-1/(1+E7)^E8)
        if (rewardRate && landSurplusYear && legalAge) {
            var temp = 1 / Math.pow(1 + parseFloat(rewardRate), landSurplusYear);

            var temp2 = 1 / Math.pow(1 + parseFloat(rewardRate), legalAge);
            var result = (1 - temp) / (1 - temp2);

            $("#periodAmend").text(getSomePlaces(result, 4))
        }
        //获取委估宗地单价（元/㎡）
        getParcelPrice();
    }

    //土地指数表
    function getLandIndexId() {
        $.ajax({
            url: "${pageContext.request.contextPath}/baseLandPrice/getLandIndexId",
            type: "get",
            dataType: "json",
            data: {judgeObjectId: '${master.judgeObjectId}'},
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
        if (!'${landLevelId}' && !'${levelDetailId}') {
            notifyInfo("提示", "未关联土地级别");
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

        var landLevelContent = $("#landLevelContent").val();
        if (landLevelContent) {
            landLevelLoadHtml2(landLevelContent);
        } else {
            $.ajax({
                url: getContextPath() + "/dataLandLevelDetailAchievement/landLevelFilter",
                type: "get",
                data: {levelDetailId: '${levelDetailId}'},
                success: function (result) {
                    landLevelLoadHtml(result.data);
                }
            })
        }
    }


    function landLevelLoadHtml(data) {
        if (jQuery.isEmptyObject(data)) {
            return false;
        }
        var target = $("#landLevelTabContent");
        target.empty();

        //由于js来筛选 有大量json 解析或者字符串化 影响代码阅读度，因此改为了后台直接处理,第一次的时候有2此筛选分类这样确实代码可读性差
        var rows = [];
        data.forEach(function (dataA, indexM) {
            $.each(dataA, function (i, obj) {
                var item = caseCommon.getLandLevelFilter(obj);
                item.achievement = 0;
                item.grade = '';
                rows.push(item);
                var landLevelBodyHtml = $("#landLevelTabContentBody").html();
                if (landLevelBodyHtml) {
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{dataLandLevelAchievement}/g, item.id);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landFactorTotalScore}/g, AssessCommon.pointToPercent(item.achievement));
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{typeName}/g, item.typeName);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{levelDetailId}/g, AssessCommon.toString(item.levelDetailId));
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{type}/g, AssessCommon.toString(item.type));
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{classification}/g, AssessCommon.toString(item.classification));
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{category}/g, AssessCommon.toString(item.category));
                    var landLevelTypeName = "";
                    if (item.classification) {
                        landLevelTypeName += item.classification;
                    }
                    if (item.category) {
                        landLevelTypeName += "/" + item.category;
                    }
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelTypeName}/g, landLevelTypeName);
                    var text = "";
                    $.each(obj, function (i, n) {
                        text += "等级:" + n.gradeName + "，说明:" + n.reamark + "； \r";
                    });
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{reamark}/g, text);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelContent}/g, JSON.stringify(obj));
                    AssessCommon.loadAsyncDataDicByKey(AssessDicKey.programmeMarketCostapproachGrade, item.grade, function (html, data) {
                        landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelGradeHTML}/g, html);
                        target.append(landLevelBodyHtml);
                    }, false);
                }
            });


        });
        var length = rows.length;// 获取当前表格中tr的个数
        var mark = 0; //要合并的单元格数
        var index = 0; //起始行数
        if (length <= 1) {
        } else {
            for (var i = 0; i < length; i++) {
                var ford = $("#landLevelTableList tr:gt(0):eq(" + i + ") td:eq(0)").text();
                var behind = $("#landLevelTableList tr:gt(0):eq(" + (parseInt(i) + 1) + ") td:eq(0)").text();
                if (ford == behind) {
                    $("#landLevelTableList tr:gt(0):eq(" + (parseInt(i) + 1) + ") td:eq(0)").hide();
                    mark = mark + 1;
                } else if (ford != behind) {
                    index = i - mark;
                    $("#landLevelTableList tr:gt(0):eq(" + index + ") td:eq(0)").attr("rowspan", mark + 1);//+1 操作标识，将当前的行加入到隐藏
                    mark = 0;
                    $("#landLevelTableList tr:gt(0):eq(" + (parseInt(i)) + ") td:eq(0)").hide();
                }
            }
        }
    };

    function landLevelLoadHtml2(jsonStr) {
        var jsonParse = JSON.parse(jsonStr);
        var data = caseCommon.landLevelFilter(jsonParse);
        if (jQuery.isEmptyObject(data)) {
            return false;
        }
        var target = $("#landLevelTabContent");
        target.empty();

        var rows = []
        //由于js来筛选 有大量json 解析或者字符串化 影响代码阅读度，因此改为了后台直接处理,第一次的时候有2此筛选分类这样确实代码可读性差
        data.forEach(function (dataA, indexM) {
            $.each(dataA, function (i, obj) {
                var item;
                obj.forEach(function (value, index) {
                    if (value.modelStr == "update") {
                        item = value;
                        rows.push(item);
                    }
                });
                var landLevelBodyHtml = $("#landLevelTabContentBody").html();
                if (landLevelBodyHtml) {
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{dataLandLevelAchievement}/g, item.id);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landFactorTotalScore}/g, AssessCommon.pointToPercent(item.achievement));
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{typeName}/g, item.typeName);
                    var landLevelTypeName = "";
                    if (item.classification) {
                        landLevelTypeName += item.classification;
                    }
                    if (item.category) {
                        landLevelTypeName += "/" + item.category;
                    }
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelTypeName}/g, landLevelTypeName);
                    var text = "";
                    $.each(obj, function (i, n) {
                        text += "等级:" + n.gradeName + "，说明:" + n.reamark + "； \r";
                    });
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{reamark}/g, text);
                    landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelContent}/g, JSON.stringify(obj));
                    AssessCommon.loadAsyncDataDicByKey(AssessDicKey.programmeMarketCostapproachGrade, item.grade, function (html, data) {
                        landLevelBodyHtml = landLevelBodyHtml.replace(/{landLevelGradeHTML}/g, html);
                        target.append(landLevelBodyHtml);
                    }, false);
                }
            });

        });
        var length = rows.length;// 获取当前表格中tr的个数
        var mark = 0; //要合并的单元格数
        var index = 0; //起始行数
        if (length <= 1) {
        } else {
            for (var i = 0; i < length; i++) {
                var ford = $("#landLevelTableList tr:gt(0):eq(" + i + ") td:eq(0)").text();
                var behind = $("#landLevelTableList tr:gt(0):eq(" + (parseInt(i) + 1) + ") td:eq(0)").text();
                if (ford == behind) {
                    $("#landLevelTableList tr:gt(0):eq(" + (parseInt(i) + 1) + ") td:eq(0)").hide();
                    mark = mark + 1;
                } else if (ford != behind) {
                    index = i - mark;
                    $("#landLevelTableList tr:gt(0):eq(" + index + ") td:eq(0)").attr("rowspan", mark + 1);//+1 操作标识，将当前的行加入到隐藏
                    mark = 0;
                    $("#landLevelTableList tr:gt(0):eq(" + (parseInt(i)) + ") td:eq(0)").hide();
                }
            }
        }
    };


    //计算区域及个别修正系数
    function getAreaAndSeveralAmend() {
        var landLevelContent = [];
        var landFactorTotalScoreResult = 0;

        $("#landLevelTableList").find("input[name='landLevelContent']").each(function (i, n) {
            var group = $(n).closest(".group");
            var dataLandLevelAchievement = group.find("input[name='dataLandLevelAchievement']").val();
            var landFactorTotalScore = AssessCommon.percentToPoint(group.find("input[name='landFactorTotalScore']").val());
            landFactorTotalScoreResult += Number(landFactorTotalScore);
            var obj = JSON.parse($(n).val());
            var dataObject = [];
            obj.forEach(function (value, index) {
                if (value.id == dataLandLevelAchievement) {
                    value.modelStr = "update";
                    value.achievement = landFactorTotalScore;
                } else {
                    delete value.modelStr;
                }
                dataObject.push(value);
            });
            landLevelContent.push(dataObject);
        });
        if (landLevelContent.length >= 1) {
            $("#areaAndSeveralAmend").val(AssessCommon.pointToPercent(landFactorTotalScoreResult));
        } else {
            $("#areaAndSeveralAmend").val('');
        }

        //获取委估宗地单价（元/㎡）
        getParcelPrice();
        getPeriodAmend();
    }

    //删除呀
    function landLevelEmpty(that) {
        $(that).parent().parent().remove();
        getAreaAndSeveralAmend();
    };

    function getLandLevelContent() {
        var landLevelContent = [];

        $("#landLevelTableList").find("input[name='landLevelContent']").each(function (i, n) {
            var group = $(n).closest(".group");
            var dataLandLevelAchievement = group.find("input[name='dataLandLevelAchievement']").val();
            var landFactorTotalScore = AssessCommon.percentToPoint(group.find("input[name='landFactorTotalScore']").val());
            var obj = JSON.parse($(n).val());
            var dataObject = [];
            obj.forEach(function (value, index) {
                if (value.id == dataLandLevelAchievement) {
                    value.modelStr = "update";
                    value.achievement = landFactorTotalScore;
                } else {
                    delete value.modelStr;
                }
                dataObject.push(value);
            });
            landLevelContent.push(dataObject);
        });

        return landLevelContent;
    }

    //change 事件 因素分自动调整
    function landLevelHandle(that) {
        var td = $(that).closest('td');
        var landLevelGrade = td.find("select[name=landLevelGrade]").find("option:selected").val() ;
        var landLevelContent = $(that).closest('tr').find('[name=landLevelContent]').val() ;
        if (! landLevelContent) {
            return false ;
        }
        var objArr = jQuery.parseJSON(landLevelContent) ;
        if (!$.isArray(objArr)) {
            return false ;
        }
        var achievement = $(that).closest('tr').find('[name=landFactorTotalScore]');
        $.each(objArr ,function (k,obj) {
            if (obj.grade == landLevelGrade) {
                achievement.attr('data-value', obj.achievement);
                AssessCommon.elementParsePercent(achievement);
                getAreaAndSeveralAmend() ;
            }
        }) ;
    };

    $(function () {
        //土地指数表
        getLandIndexId();
        //容积率修正系数表
        showVolumetricRateDetailList(${hasVolumeFractionAmendId});
        //因素条件说明及修正系数
        getLandLevelTabContent();
        if (!'${master.areaAndSeveralAmend}') {
            $("#areaAndSeveralAmend").val(AssessCommon.pointToPercent('${landFactorTotalScore}'));
        } else {
            $("#areaAndSeveralAmend").val(AssessCommon.pointToPercent('${master.areaAndSeveralAmend}'));
        }
        if (!'${master.landLevelContent}') {
            $("#landLevelContent").val('${landLevelContent}');
        }
        if (!'${master.standardPremium}') {
            $("#standardPremium").val(getSomePlaces(parseFloat('${standardPremium}'), 2));
        }
        if (!'${master.legalAge}') {
            $("#legalAge").val(getSomePlaces(parseFloat('${legalAge}'), 2));
        }
        if (!'${master.volumetricRate}') {
            $("#volumetricRate").val(getSomePlaces(parseFloat('${volumetricRate}'), 2));
        }

        getPeriodAmend();
    });

</script>