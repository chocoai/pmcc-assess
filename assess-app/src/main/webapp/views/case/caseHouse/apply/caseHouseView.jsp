<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/9/13
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <script type="text/javascript" src="/pmcc-crm/js/crm-customer-utils.js"></script>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <div class="x_panel">

                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h2>
                        房屋
                    </h2>
                    <div class="clearfix"></div>
                </div>

                <div class="x_content">
                    <div class="x_title">
                        <h3>房屋基本信息 </h3>
                        <div class="clearfix"></div>
                    </div>
                    <form class="form-horizontal" id="frm_house">
                        <input type="hidden" name="id" value="${caseHouse.id}">

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">房号<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="100" placeholder="房号"
                                           value="${caseHouse.houseNumber}" name="houseNumber"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">所在楼层<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="100" data-rule-number='true' placeholder="所在楼层(请输入数字)"
                                           value="${caseHouse.houseNumber}" name="floor"
                                           class="form-control">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">户型选择<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <select class="form-control huxingId" name="huxingId">
                                        </select>
                                        <label class="input-group-addon btn">刷新户型<i
                                                class="fa fa-refresh"></i></label>
                                    </div>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">户型图<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <div class="house_latest_family_plan"></div>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">朝向<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="朝向" readonly="readonly"
                                           value="${caseHouse.orientation}" name="orientation"
                                           class="form-control">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">证载用途<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 certUse" name="certUse" required="required">
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">实际用途<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 practicalUse" name="practicalUse" required="required">
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">权益限制<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" data-rule-maxlength="100" placeholder="权益限制"
                                           value="${caseHouse.rightInterestsRestriction}"
                                           name="rightInterestsRestriction"
                                           class="form-control">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">房屋出租占用情况途描述<span class="symbol required"></span></label>
                                <div class="col-sm-11">
                                    <textarea class="form-control" name="description" required="required">
                                        ${caseHouse.description}
                                    </textarea>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">房屋平面图<span class="symbol required"></span></label>
                                <div class="col-sm-5">
                                    <input id="house_house_plan"
                                           required="required" placeholder="上传附件" class="form-control" type="file">
                                    <div id="_house_house_plan"></div>
                                </div>
                            </div>
                        </div>

                    </form>
                </div>

                <div class="x_content">
                    <div class="x_title">
                        <h3>房屋交易信息 </h3>
                        <div class="clearfix"></div>
                    </div>
                    <form class="form-horizontal" id="frm_trading">
                        <input type="hidden" name="id" value="${caseHouseTrading.id}">

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">财产范围<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="财产范围" required="required"
                                           name="scopeProperty" class="form-control" value="${caseHouseTrading.scopeProperty}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">融资条件<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="融资条件" required="required"
                                           name="financingConditions" class="form-control" value="${caseHouseTrading.financingConditions}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">税费负担<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2 taxBurden" name="taxBurden"
                                            required="required">
                                    </select>
                                </div>
                            </div>
                        </div>

                    </form>
                </div>

            </div>

            <div class="x_panel">
                <div class="x_content">
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-5">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                取消
                            </button>

                            <button id="commit_btn" class="btn btn-success"
                                    onclick="CaseHouseModelFun.prototype.submit();">
                                提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var CaseHouseModelFun = function () {

    }

    CaseHouseModelFun.prototype.config = {
        house: {
            frm: function () {
                return "frm_house";//房屋基本信息frm
            },
            getHouseNewLatestFamilyPlan:function () {
                return "house_new_latest_family_plan";//最新户型图id和字段
            },
            getHouseHousePlan:function () {
                return "house_house_plan" ; //房屋平面图id和字段
            }
        },
        trading: {
            frm: function () {
                return "frm_trading";//房屋交易基本信息frm
            }
        }

    }

    CaseHouseModelFun.prototype.select2 = {
        house:function () {
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseLoadUtility, null, function (html, data) {
                $("#" + CaseHouseModelFun.prototype.config.house.frm() + " .certUse").html(html);
                $("#" + CaseHouseModelFun.prototype.config.house.frm() + " .certUse").select2();//加载样式
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePracticalUse, null, function (html, data) {
                $("#" + CaseHouseModelFun.prototype.config.house.frm() + " .practicalUse").html(html);
                $("#" + CaseHouseModelFun.prototype.config.house.frm() + " .practicalUse").select2();//加载样式
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseNewsHuxing, null, function (html, data) {
                $("#" + CaseHouseModelFun.prototype.config.house.frm() + " .newsHuxing").html(html);
                $("#" + CaseHouseModelFun.prototype.config.house.frm() + " .newsHuxing").select2();//加载样式
            });
        },
        init:function () {
            CaseHouseModelFun.prototype.select2.house();
            CaseHouseModelFun.prototype.select2.trading();
            CaseHouseModelFun.prototype.select2.huXinSelect();
        },
        trading:function () {
            AssessCommon.loadDataDicByKey(AssessDicKey.examineHousetaxBurden,"",function (html,data) {
                $("#" + CaseHouseModelFun.prototype.config.trading.frm() + " .taxBurden").html(html);
                $("#" + CaseHouseModelFun.prototype.config.trading.frm() + " .taxBurden").select2();//加载样式
            });
        },
        huXinSelect:function () {

        },
        rule:function (item) {
            var text = "";
            if (CaseHouseModelFun.prototype.isEmpty(item.house)) {
                text += item.house + "房-";
            }
            if (CaseHouseModelFun.prototype.isEmpty(item.saloon)) {
                text += item.saloon + "客厅-";
            }
            if (CaseHouseModelFun.prototype.isEmpty(item.kitchen)) {
                text += item.kitchen + "厨房-";
            }
            if (CaseHouseModelFun.prototype.isEmpty(item.toilet)) {
                text += item.toilet + "卫生间-";
            }
            if (CaseHouseModelFun.prototype.isEmpty(item.garden)) {
                text += item.garden + "花园-";
            }
            if (CaseHouseModelFun.prototype.isEmpty(item.balcony)) {
                text += item.balcony + "阳台";
            }
            return text;
        }
    }

    CaseHouseModelFun.prototype.fileModel = {
        uploadFileHouse:function (fieldsName, table) {
            FileUtils.uploadFiles({
                target: fieldsName,
                disabledTarget: "btn_submit",
                formData: {
                    fieldsName: fieldsName,
                    tableName: table,
                    tableId: ${empty caseHouse.id?0:caseHouse.id},
                    creater: "${currUserAccount}"
                },
                deleteFlag: true
            });
        },
        showFileHouse:function (fieldsName, table) {
            FileUtils.getFileShows({
                target: fieldsName,
                formData: {
                    fieldsName: fieldsName,
                    tableName: table,
                    tableId: ${empty caseHouse.id?0:caseHouse.id},
                    creater: "${currUserAccount}"
                },
                deleteFlag: true
            })
        }
    }

    CaseHouseModelFun.prototype.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    }

    CaseHouseModelFun.prototype.writeSelectData = function (frm, data, name) {
        if (CaseHouseModelFun.prototype.isEmpty(data)) {
            $("#" + frm + " ." + name).val(data).trigger("change");
        } else {
            $("#" + frm + " ." + name).val(null).trigger("change");
        }
    }

    //提交
    CaseHouseModelFun.prototype.submit = function () {
        if (!$("#" + CaseHouseModelFun.prototype.config.house.frm()).valid()) {
            return false;
        }
        if (!$("#" + CaseHouseModelFun.prototype.config.trading.frm()).valid()) {
            return false;
        }
        var house = formParams(CaseHouseModelFun.prototype.config.house.frm());
        var trading = formParams(CaseHouseModelFun.prototype.config.trading.frm());
        var unitId = "${unitId}";
        if (CaseHouseModelFun.prototype.isEmpty(unitId)) {
            house.unitId = unitId;
        }
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/caseHouse/saveAndUpdateCaseHouse",
            data: {formData: JSON.stringify({house: house, trading: trading})},
            success: function (result) {
                if (result.ret) {
                    //保存完后其他动作
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    }


    $(function () {
        CaseHouseModelFun.prototype.select2.init();
        CaseHouseModelFun.prototype.fileModel.uploadFileHouse(CaseHouseModelFun.prototype.config.house.getHouseHousePlan(),AssessDBKey.CaseHouse);
        CaseHouseModelFun.prototype.fileModel.showFileHouse(CaseHouseModelFun.prototype.config.house.getHouseHousePlan(),AssessDBKey.CaseHouse);
        CaseHouseModelFun.prototype.fileModel.uploadFileHouse(CaseHouseModelFun.prototype.config.house.getHouseNewLatestFamilyPlan(),AssessDBKey.CaseHouse);
        CaseHouseModelFun.prototype.fileModel.showFileHouse(CaseHouseModelFun.prototype.config.house.getHouseNewLatestFamilyPlan(),AssessDBKey.CaseHouse);
    });
</script>
<%@include file="/views/share/main_footer.jsp" %>
</body>
</html>
