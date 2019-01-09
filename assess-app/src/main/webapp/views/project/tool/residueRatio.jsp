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
                                <span class="col-sm-2 radio-inline">50%</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-12">
                    <div class="x_title">年限法</div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                已使用年限
                            </label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="usedYear"
                                       data-rule-number='true' required="required"
                                       placeholder="已使用年限" onblur="">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                可用年限
                            </label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="usableYear"
                                       data-rule-number='true' required="required"
                                       placeholder="可用年限" onblur="">
                            </div>
                        </div>
                    </div>

                    <div class="form-group" style="display: none;">
                        <div class="x-valid">
                            <label class="col-sm-1 control-label">
                                权重
                            </label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" name="weight"
                                       data-rule-number='true' required="required"
                                       placeholder="权重" onblur="build.newRateModel.handle();">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-12">
                    <div class="x_title">观察法</div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-1 control-label">
                                成新率
                            </label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control x-percent" name="newRate"
                                       data-rule-number='true' required="required"
                                       placeholder="成新率" onblur="build.newRateModel.handle();">
                            </div>
                        </div>
                    </div>
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
            residueRatioId: undefined,//数据id
            usedYear: undefined,//已使用年限
            usableYear: undefined,//可用年限
            houseId: undefined,//完损度关联的房屋id
            success: function (id, resultValue) {

            }
        };
        defaults = $.extend({}, defaults, options);

        layer.open({
            type: 1,
            title: '成新率',
            area: ['920px', '640px'],
            offset: 't',
            btn: ['保存'],
            yes: function (index) {
                //保存对应数据
                defaults.success(0, "80%");
                layer.close();
            },
            content: $("#residueRatioHtml").html(),
            success: function () {
                //填充数据
                var form = $("#residue_ratio_form");
                form.find('[name=usedYear]').val(defaults.usedYear);
                form.find('[name=usableYear]').val(defaults.usableYear);
            }
        });
    }
</script>
