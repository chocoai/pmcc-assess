<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2019-1-9
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/html" id="residueRatioHtml">
    <form class="form-horizontal" id="residue_ratio_form">
        <div class="modal-body">
            <div class="row">
                <div class="col-md-12">
                    <div class="panel-header">
                        <input name="houseId" id="residueRatioHouseId" type="hidden">
                        <input name="id" type="hidden">
                        <div class="form-group">
                            <div class="x-valid">
                                <span class="col-sm-2 col-sm-offset-2 radio-inline">
                                            <input id="residueRatioType0" type="radio" name="type" value="0">
                                            <label for="residueRatioType0">年限法</label>
                                        </span>
                                <span class="col-sm-2  radio-inline">
                                            <input id="residueRatioType1" type="radio" name="type" value="1">
                                            <label for="residueRatioType1">观察法</label>
                                        </span>
                                <span class="col-sm-2 radio-inline">
                                            <input id="residueRatioType2" type="radio" name="type" value="2">
                                            <label for="residueRatioType2">综合法</label>
                                        </span>
                                <span class="col-sm-2 radio-inline" id="residue_ratio_cxl"></span>
                                <input name="resultValue" id="residue_ratio_resultValue" type="hidden">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-12" id="residue_ratio_part1">
                    <div class="x_title">年限法</div>
                    <div class="row form-inline form-group">
                        <label class="col-sm-2 control-label">
                            残值率
                        </label>
                        <div class="col-sm-2  x-valid">
                            <input type="text" class="form-control x-percent" name="residualRatio" id="residue_residual_ratio"
                                   required="required"
                                   placeholder="残值率" onblur="residueRatio.getAgeLimitCxl()">
                        </div>
                        <label class="col-sm-2 control-label">
                            已使用年限
                        </label>
                        <div class="col-sm-2  x-valid">
                            <input type="text" class="form-control" name="usedYear" id="residue_ratio_usedYear"
                                   data-rule-number='true' required="required"
                                   placeholder="已使用年限" onblur="residueRatio.getAgeLimitCxl()">
                        </div>
                        <label class="col-sm-2 control-label">
                            可用年限
                        </label>
                        <div class="col-sm-2  x-valid">
                            <input type="text" class="form-control" name="usableYear" id="residue_ratio_usableYear"
                                   data-rule-number='true' required="required"
                                   placeholder="可用年限" onblur="residueRatio.getAgeLimitCxl()">
                        </div>
                    </div>

                    <div class="row form-inline form-group" style="display: none;" id="residue_ratio_weight1">
                        <label class="col-sm-2 control-label">
                            权重
                        </label>
                        <div class="col-sm-2  x-valid">
                            <input type="text" class="form-control" id="residue_ratio_ageRate"
                                   name="ageRate" data-rule-number='true' required="required"
                                   placeholder="权重" onblur="residueRatio.changeRate()">
                        </div>
                    </div>
                </div>

                <div class="col-md-12" id="residue_ratio_part2" style="display: none;">
                    <div class="x_title">观察法</div>
                    <div class="row form-inline form-group" style="display: none;" id="residue_ratio_weight2">
                        <label class="col-sm-2 control-label">
                            权重
                        </label>
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="residue_ratio_observeRate" readonly
                                   name="observeRate" data-rule-number='true' placeholder="权重">
                        </div>
                    </div>
                    <h4>结构完损部分</h4>
                    <input type="hidden" name="residueRatioStructuralScore" id="residueRatioStructuralScore">
                    <table id="residueRatioStructural" class="table table-bordered"></table>
                    <h4>装修部分</h4>
                    <input type="hidden" name="residueRatioDecorationScore" id="residueRatioDecorationScore">
                    <table id="residueRatioDecoration" class="table table-bordered"></table>
                    <h4>设备部分</h4>
                    <input type="hidden" name="residueRatioEquipmentScore" id="residueRatioEquipmentScore">
                    <table id="residueRatioEquipment" class="table table-bordered"></table>
                    <h4>其他</h4>
                    <input type="hidden" name="residueRatioOtherScore" id="residueRatioOtherScore">
                    <table id="residueRatioOther" class="table table-bordered"></table>

                </div>
            </div>
        </div>
    </form>
</script>

<script type="text/javascript">
    var residueRatio = {};
    //年限法成新率 1-已使用年限/可用年限
    //观察法成新率 取得完损度数据自动计算
    //综合法成新率 根据权重自动计算
    residueRatio.init = function (options) {
        var defaults = {
            readonly: false,
            residueRatioId: 42,//数据id
            usedYear: undefined,//已使用年限
            usableYear: undefined,//可用年限
            houseId: undefined,//完损度关联的房屋id
            success: function (id, resultValue) {
                defaults.residueRatioId = id;

            }
        };
        defaults = $.extend({}, defaults, options);

        var index = layer.open({
            type: 1,
            title: '成新率',
            area: ['1220px', '540px'],
            offset: 't',
            btn: ['保存'],
            yes: function (index) {
                //保存对应数据
                if (!$("#residue_ratio_form").valid()) {
                    return false;
                }
                residueRatio.saveData(defaults.success);
                layer.close(index);
            },
            content: $("#residueRatioHtml").html(),
            success: function () {
                residueRatio.initMasterData(defaults.residueRatioId, defaults.houseId, defaults.success);
                //填充数据
                $('#residue_ratio_form').find('[name=usedYear]').val(defaults.usedYear);
                $('#residue_ratio_form').find('[name=usableYear]').val(defaults.usableYear);
                $('#residue_ratio_form').find('[name=houseId]').val(defaults.houseId);

                residueRatio.ratioChange();
            }
        });
    }
    //加载列表数据
    residueRatio.loadList = function (residueRatioId, tableName, type) {
        var cols = [];
        cols.push({field: 'categoryName', title: '类别'});
        cols.push({field: 'standardScore', title: '标准分'});
        cols.push({
            field: 'entityConditionName', title: '实体状况', width: '20%',
            formatter: function (value, row, index) {
                return '<div class="x-valid">' +
                    '<input placeholder="实体状况" class="form-control"  style="width: 100px" name=entityConditionName' + row.category + ' value=' + row.entityConditionName + ' >' +
                    '</div>';
            }
        });
        cols.push({
            field: 'entityConditionContent', title: '实体状况内容', width: '20%',
            formatter: function (value, row, index) {
                return '<div class="x-valid">' +
                    '<input placeholder="实体状况内容" class="form-control"  style="width: 100px" name=entityConditionContent' + row.category + ' value=' + row.entityConditionContent + ' >' +
                    '</div>';
            }
        });
        cols.push({
            field: 'scores', title: '打分', width: 100,
            formatter: function (value, row, index) {
                return '<div class="x-valid">' +
                    '<input data-rule-number="true" placeholder="分数" class="form-control"  data-rule-range="[0,' + row.standardScore + ']"' +
                    'required style="width: 100px" name=scores' + row.category + '  id=scores' + row.category + ' onblur=" residueRatio.checkNumberData(' + index + ',\'' + tableName + '\')" value=' + row.score + '  >' +
                    '</div>';
            }
        });
        $("#" + tableName).bootstrapTable('destroy');
        TableInit(tableName, "${pageContext.request.contextPath}/residueRatio/getObserveList", cols, {
            residueRatioId: residueRatioId,
            type: type
        }, {
            showColumns: false,
            showRefresh: false,
            pagination: false,
            uniqueId: "id",
            search: false
        });
    }
    residueRatio.saveData = function (callbak) {
        var data = formParams("residue_ratio_form");
        data.residualRatio = AssessCommon.percentToPoint($("#residue_residual_ratio").val());
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/residueRatio/saveResidueRatio",
            type: "post",
            dataType: "json",
            data: {
                formData: JSON.stringify(data)
            },
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('保存成功');
                    if (callbak) {
                        callbak(result.data.id, result.data.resultValue);
                    }
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }
    residueRatio.ratioChange = function () {
        $("#residueRatioType0").attr("checked", "checked");//默认第一个选中
        $('input:radio[name="type"]').change(function () {
            //清空成新率
            $("#residue_ratio_cxl").text("");
            $("#residue_ratio_resultValue").val("")
            if ($("#residueRatioType0").is(":checked")) {
                $("#residue_ratio_part1").show();
                $("#residue_ratio_part2").hide();
                $("#residue_ratio_weight1").hide();
                $("#residue_ratio_weight2").hide();
                //成新率
                residueRatio.getAgeLimitCxl();


            }
            if ($("#residueRatioType1").is(":checked")) {
                $("#residue_ratio_part1").hide();
                $("#residue_ratio_part2").show();
                $("#residue_ratio_weight1").hide();
                $("#residue_ratio_weight2").hide();

                residueRatio.getObserveCxl();
            }
            if ($("#residueRatioType2").is(":checked")) {
                $("#residue_ratio_part1").show();
                $("#residue_ratio_part2").show();
                $("#residue_ratio_weight1").show();
                $("#residue_ratio_weight2").show();
                residueRatio.compositeCxl();
            }
        })
    }
    residueRatio.ratioShow = function (type) {
        if (type == 0) {
            $("#residueRatioType0").attr("checked", "checked");
            $("#residue_ratio_part1").show();
            $("#residue_ratio_part2").hide();
            $("#residue_ratio_weight1").hide();
            $("#residue_ratio_weight2").hide();
        } else if (type == 1) {
            $("#residueRatioType1").attr("checked", "checked");
            $("#residue_ratio_part1").hide();
            $("#residue_ratio_part2").show();
            $("#residue_ratio_weight1").hide();
            $("#residue_ratio_weight2").hide();
        } else {
            $("#residueRatioType2").attr("checked", "checked");
            $("#residue_ratio_part1").show();
            $("#residue_ratio_part2").show();
            $("#residue_ratio_weight1").show();
            $("#residue_ratio_weight2").show();
        }
    }

    //权重改变
    residueRatio.changeRate = function () {
        var ageRate = Number($("#residue_ratio_ageRate").val());
        if (ageRate >= 0 && ageRate <= 1) {
            $("#residue_ratio_observeRate").val(1 - ageRate);
            if ($("#residue_ratio_form").valid()) {
                residueRatio.compositeCxl();
            }
        } else {
            alert("请正确输入")
            $("#residue_ratio_ageRate").val("");
            $("#residue_ratio_observeRate").val("");
        }
    }


    //分数填写
    residueRatio.checkNumberData = function (index, tableName) {
        var row = $("#" + tableName).bootstrapTable('getData')[index];
        var id = "scores" + row.category;
        var reallyScore = $("#" + id).val();
        if (!row.standardScore) {
            row.standardScore = 0;
        }
        if (0 <= reallyScore && reallyScore <= row.standardScore) {
            residueRatio.getTableScore(row.weight, tableName);
            residueRatio.getObserveCxl();
            if ($("#residueRatioType2").is(":checked")) {
                residueRatio.compositeCxl();
            }
            return true;
        }
        return false;
    }

    //计算每部分得分
    residueRatio.getTableScore = function (weight, tableName) {
        var finished = true;
        var tableScore = 0;
        var arr = $("#" + tableName + " input[name^=scores]");
        $.each(arr, function (i, item) {
            tableScore += Number(item.value);
            if (!item.value) {
                finished = false;
            }
        });
        $("#" + tableName + "Score").attr("value", tableScore * weight);
        if (finished == false) {
            $("#" + tableName + "Score").removeAttr("value")
        }
        return tableScore * weight;
    }


    //年限法成新率
    residueRatio.getAgeLimitCxl = function () {
        var residual = Number(AssessCommon.percentToPoint($("#residue_residual_ratio").val()));
        var usedYear = Number($("#residue_ratio_usedYear").val());
        var usableYear = Number($("#residue_ratio_usableYear").val());
        if ($("#residue_ratio_usedYear").val() && $("#residue_ratio_usableYear").val() && $("#residue_residual_ratio").val()) {
            if (usedYear > usableYear) {
                $("#residue_ratio_usedYear").val("");
                $("#residue_ratio_usableYear").val("");
                alert("可用年限不能小于已使用年限");
            } else {
                var ageLimitCxl = 1 - (1-residual)*(usedYear / usableYear);
                if ($("#residueRatioType0").is(":checked")) {
                    $("#residue_ratio_cxl").text((ageLimitCxl * 100).toFixed(2) + "%");
                    $("#residue_ratio_resultValue").val((ageLimitCxl * 100).toFixed(2) + "%");
                }
                return ageLimitCxl * 100;
            }
        }
        return -1;
    }


    //观察法成新率
    residueRatio.getObserveCxl = function () {
        if ($("#residue_ratio_form").valid()) {
            var structuralScore = Number($("#residueRatioStructuralScore").val());
            var decorationScore = Number($("#residueRatioDecorationScore").val());
            var equipmentScore = Number($("#residueRatioEquipmentScore").val());
            var otherScore = Number($("#residueRatioOtherScore").val());
            var observeCxl = structuralScore + decorationScore + equipmentScore + otherScore;
            if (structuralScore >= 0 &&
                decorationScore >= 0 &&
                equipmentScore >= 0 &&
                otherScore >= 0) {
                if (!$("#residueRatioType0").is(":checked")) {
                    observeCxl = residueRatio.getLevel(observeCxl);
                }
                return observeCxl;
            }
        }
        return -1;
    }


    //综合法
    residueRatio.compositeCxl = function () {
        if ($("#residue_ratio_form").valid()) {
            var ageLimitCxl = residueRatio.getAgeLimitCxl();
            var observeCxl = residueRatio.getObserveCxl();
            var ageRate = $("#residue_ratio_ageRate").val();
            var observeRate = $("#residue_ratio_observeRate").val();
            if (observeCxl >= 0 && ageLimitCxl >= 0 && ageRate >= 0 && observeRate >= 0) {
                $("#residue_ratio_cxl").text((ageLimitCxl * ageRate + observeCxl * observeRate).toFixed(2) + "%");
                $("#residue_ratio_resultValue").val((ageLimitCxl * ageRate + observeCxl * observeRate).toFixed(2) + "%");
            }
        }
    }

    residueRatio.getLevel = function (data) {
        var level = 50;
        if (85 < data && data <= 100) {
            level = 90;
        }
        if (65 < data && data <= 85) {
            level = 75;
        }
        if (50 < data && data <= 65) {
            level = 60;
        }
        $("#residue_ratio_cxl").text(level + "%");
        $("#residue_ratio_resultValue").val(level + "%");
        return level;
    }
    //初始化成新率主表及子表数据
    residueRatio.initMasterData = function (residueRatioId, houseId, callbak) {
        $.ajax({
            url: "${pageContext.request.contextPath}/residueRatio/initMasterData",
            type: "post",
            dataType: "json",
            data: {
                residueRatioId: residueRatioId,
                houseId: houseId
            },
            success: function (result) {
                if (result.ret) {
                    if (callbak) {
                        callbak(result.data.id, result.data.resultValue);
                    }
                    $('#residue_ratio_form').find('[name=id]').val(result.data.id);
                    residueRatio.loadList(result.data.id, "residueRatioStructural", AssessDicKey.damaged_degree_structural_part);
                    residueRatio.loadList(result.data.id, "residueRatioDecoration", AssessDicKey.damaged_degree_decoration_part);
                    residueRatio.loadList(result.data.id, "residueRatioEquipment", AssessDicKey.damaged_degree_equipment_part);
                    residueRatio.loadList(result.data.id, "residueRatioOther", AssessDicKey.damaged_degree_other);

                    residueRatio.initAgeLimit(result.data.id);
                    residueRatio.initObserve(result.data.id, houseId);

                }
                else {
                    Alert("初始化数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }


    //观察法回显数据
    residueRatio.initObserve = function (residueRatioId, houseId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/residueRatio/initObserve",
            type: "post",
            dataType: "json",
            data: {
                residueRatioId: residueRatioId,
                houseId: houseId
            },
            success: function (result) {
                if (result.ret) {
                    var serializeArray = $("#residue_ratio_form").serializeArray();
                    for (var i = 0; i < serializeArray.length; i++) {
                        var name = serializeArray[i].name;
                        if (result.data[name]) {
                            var value = result.data[name];
                            $("[name='" + name + "']").val(value);
                        }

                    }
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    //年限法回显数据
    residueRatio.initAgeLimit = function (residueRatioId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/residueRatio/initAgeLimit",
            type: "post",
            dataType: "json",
            data: {
                residueRatioId: residueRatioId,
            },
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        $("#residue_ratio_form").initForm(result.data);
                       $("#residue_residual_ratio").val( AssessCommon.pointToPercent(result.data.residualRatio))
                        if (result.data.resultValue) {
                            $("#residue_ratio_cxl").text(result.data.resultValue);
                        }
                        residueRatio.ratioShow(result.data.type);
                    }
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }


</script>
