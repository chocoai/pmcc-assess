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
                        <input name="houseId" id="houseId" type="hidden">
                        <div class="form-group">
                            <div class="x-valid">
                                <span class="col-sm-2 col-sm-offset-2 radio-inline">
                                            <input id="residueRatioType0" type="radio" name="method" value="0">
                                            <label for="residueRatioType0">年限法</label>
                                        </span>
                                <span class="col-sm-2  radio-inline">
                                            <input id="residueRatioType1" type="radio" name="method" value="1">
                                            <label for="residueRatioType1">观察法</label>
                                        </span>
                                <span class="col-sm-2 radio-inline">
                                            <input id="residueRatioType2" type="radio" name="method" value="2">
                                            <label for="residueRatioType2">综合法</label>
                                        </span>
                                <span class="col-sm-2 radio-inline" id="cxl"></span>
                                <input name="resultValue" id="resultValue" type="hidden">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-12" id="part1">
                    <div class="x_title">年限法</div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                已使用年限
                            </label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="usedYear" id="usedYear"
                                       data-rule-number='true' required="required"
                                       placeholder="已使用年限" onblur="getAgeLimitCxl()">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                可用年限
                            </label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="usableYear" id="usableYear"
                                       data-rule-number='true' required="required"
                                       placeholder="可用年限" onblur="getAgeLimitCxl()">
                            </div>
                        </div>
                    </div>

                    <div class="form-group" style="display: none;" id="residue_ratio_weight1">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                权重
                            </label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="ageRate"
                                       name="ageRate" data-rule-number='true' required="required"
                                       placeholder="权重" onblur="changeRate()">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-12" id="part2" style="display: none;">
                    <div class="x_title">观察法</div>
                    <div class="form-group" style="display: none;" id="weight2">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                权重
                            </label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="observeRate" readonly
                                       name="observeRate" data-rule-number='true' placeholder="权重">
                            </div>
                        </div>
                    </div>
                    <h4>结构完损部分</h4>
                    <input type="hidden" id="structuralScore">
                    <table id="structural" class="table table-bordered"></table>
                    <h4>装修部分</h4>
                    <input type="hidden" id="decorationScore">
                    <table id="decoration" class="table table-bordered"></table>
                    <h4>设备部分</h4>
                    <input type="hidden" id="equipmentScore">
                    <table id="equipment" class="table table-bordered"></table>
                    <h4>其他</h4>
                    <input type="hidden" id="otherScore">
                    <table id="other" class="table table-bordered"></table>

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
    residueRatio.form=$('#residue_ratio_form');
    residueRatio.init = function (options) {
        var defaults = {
            readonly: false,
            residueRatioId: undefined,//数据id
            usedYear: undefined,//已使用年限
            usableYear: undefined,//可用年限
            houseId: undefined,//完损度关联的房屋id
            success: function (id, resultValue) {


            }
        };
        defaults = $.extend({}, defaults, options);

        var index = layer.open({
            type: 1,
            title: '成新率',
            area: ['920px', '640px'],
            offset: 't',
            btn: ['保存'],
            yes: function (index) {
                //保存对应数据
                //defaults.success(0, "80%");
                saveData();
                layer.close(index);
            },
            content: $("#residueRatioHtml").html(),
            success: function () {
                ratioChange();
                //填充数据
                residueRatio.form.find('[name=usedYear]').val(defaults.usedYear);
                residueRatio.form.find('[name=usableYear]').val(defaults.usableYear);
                residueRatio.form.find('[name=houseId]').val(defaults.houseId);

                loadList($("#houseId").val(), "structural", "structural.part");
                loadList($("#houseId").val(), "decoration", "decoration.part");
                loadList($("#houseId").val(), "equipment", "equipment.part");
                loadList($("#houseId").val(), "other", "other");
            }
        });
    }

    residueRatio.loadList = function (houseId, tableName, type) {
        var cols = [];
        cols.push({field: 'categoryName', title: '类别'});
        cols.push({field: 'standardScore', title: '标准分'});
        cols.push({field: 'entityConditionName', title: '实体状况'});
        cols.push({field: 'entityConditionContent', title: '实体状况内容'});
        cols.push({
            field: 'scores', title: '打分', width: 100,
            formatter: function (value, row, index) {
                return '<div class="x-valid">' +
                    '<input data-rule-number="true" placeholder="分数" class="form-control" required style="width: 100px" name=scores' + row.category + ' id=scores' + row.category + ' onblur="checkNumberData(' + index + ',\'' + tableName + '\')">' +
                    '</div>';
            }
        });
        $("#" + tableName).bootstrapTable('destroy');
        TableInit(tableName, "${pageContext.request.contextPath}/dataBlock/getObserveList", cols, {
            houseId: houseId,
            type: type
        }, {
            showColumns: false,
            showRefresh: false,
            uniqueId: "id",
            search: false
        });
    }

    //加载列表数据
    function loadList(houseId, tableName, type) {

    }

    function saveData() {
        if (!$("#residue_ratio_form").valid()) {
            return false;
        }
        var data = formParams("residue_ratio_form");
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/dataBlock/saveResidueRatio",
            type: "post",
            dataType: "json",
            data: {
                formData: JSON.stringify(data)
            },
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('保存成功');

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

    function ratioChange() {
        $("#residueRatioType0").attr("checked", "checked");//默认第一个选中
        $('input:radio[name="method"]').change(function () {
            //清空成新率
            $("#cxl").text("");
            $("#resultValue").val("")
            if ($("#residueRatioType0").is(":checked")) {
                $("#part1").show();
                $("#part2").hide();
                $("#weight1").hide();
                $("#weight2").hide();
                //成新率
                getAgeLimitCxl();


            }
            if ($("#residueRatioType1").is(":checked")) {
                $("#part1").hide();
                $("#part2").show();
                $("#weight1").hide();
                $("#weight2").hide();

                getObserveCxl();
            }
            if ($("#residueRatioType2").is(":checked")) {
                $("#part1").show();
                $("#part2").show();
                $("#weight1").show();
                $("#weight2").show();

            }
        })
    }

    //权重改变
    function changeRate() {
        var ageRate = Number($("#ageRate").val());
        console.log(ageRate)
        if (ageRate >= 0 && ageRate <= 1) {
            $("#observeRate").val(1 - ageRate);
            if ($("#residue_ratio_form").valid()) {
                compositeCxl();
            }
        } else {
            alert("请正确输入")
            $("#ageRate").val("");
            $("#observeRate").val("");
        }
    }

    //分数填写
    function checkNumberData(index, tableName) {
        var row = $("#" + tableName).bootstrapTable('getData')[index];
        var id = "scores" + row.category;
        var reallyScore = $("#" + id).val();
        if (!row.standardScore) {
            row.standardScore = 0;
        }
        if (0 <= reallyScore && reallyScore <= row.standardScore) {
            getTableScore(row.weight, tableName);
            getObserveCxl();
            if ($("#residueRatioType2").is(":checked")) {
                compositeCxl();
            }
            return true;
        }
        alert("请正确输入分数");
        $("#" + id).val("");
        return false;
    }

    //计算每部分得分
    function getTableScore(weight, tableName) {
        var finished = true;
        var tableScore = 0;
        var arr = $("#" + tableName + " input");
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
    function getAgeLimitCxl() {
        var usedYear = Number($("#usedYear").val());
        var usableYear = Number($("#usableYear").val());
        console.log(usedYear);
        console.log(usableYear);
        if ($("#usedYear").val() && $("#usableYear").val()) {
            if (usedYear > usableYear) {
                $("#usedYear").val("");
                $("#usableYear").val("");
                alert("可用年限不能小于已使用年限");
            } else {
                var ageLimitCxl = 1 - (usedYear / usableYear);
                if ($("#residueRatioType0").is(":checked")) {
                    $("#cxl").text(ageLimitCxl * 100 + "%");
                    $("#resultValue").val(ageLimitCxl * 100 + "%")
                }
                return ageLimitCxl * 100;
            }
        }
        return -1;
    }

    //观察法成新率
    function getObserveCxl() {
        if ($("#residue_ratio_form").valid()) {
            var structuralScore = Number($("#structuralScore").val());
            var decorationScore = Number($("#decorationScore").val());
            var equipmentScore = Number($("#equipmentScore").val());
            var otherScore = Number($("#otherScore").val());
            var observeCxl = structuralScore + decorationScore + equipmentScore + otherScore;
            if ($("#structuralScore").attr("value") >= 0 &&
                $("#decorationScore").attr("value") >= 0 &&
                $("#equipmentScore").attr("value") >= 0 &&
                $("#otherScore").attr("value") >= 0) {
                if (!$("#residueRatioType0").is(":checked")) {
                    observeCxl = getLevel(observeCxl);
                }
                return observeCxl;
            }
        }
        return -1;
    }

    //综合法
    function compositeCxl() {
        if ($("#residue_ratio_form").valid()) {
            var ageLimitCxl = getAgeLimitCxl();
            console.log(ageLimitCxl);
            var observeCxl = getObserveCxl();
            console.log(observeCxl + "========----")
            var ageRate = $("#ageRate").val();
            var observeRate = $("#observeRate").val();
            if (observeCxl >= 0 && ageLimitCxl >= 0 && ageRate >= 0 && observeRate >= 0) {
                $("#cxl").text(ageLimitCxl * ageRate + observeCxl * observeRate + "%");
                $("#resultValue").val(ageLimitCxl * ageRate + observeCxl * observeRate + "%");
            }
        }
    }

    function getLevel(data) {
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
        $("#cxl").text(level + "%");
        $("#resultValue").val(level + "%")
        return level;
    }
</script>
