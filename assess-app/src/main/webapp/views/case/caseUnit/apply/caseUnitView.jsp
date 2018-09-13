<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/9/11
  Time: 17:18
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
                        单元
                    </h2>
                    <div class="clearfix"></div>
                </div>

                <div class="x_content">
                    <div class="x_title">
                        <h3>单元基本信息 </h3>
                        <div class="clearfix"></div>
                    </div>
                    <form class="form-horizontal" id="frm_Unit">
                        <input type="hidden" name="id" value="${caseUnit.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">单元编号<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="单元编号" required="required"
                                           name="unitNumber" class="form-control" value="${caseUnit.unitNumber}">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">户梯比<span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="户梯比" required="required"
                                           name="elevatorHouseholdRatio" class="form-control" value="${caseUnit.elevatorHouseholdRatio}">
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

                            <button id="commit_btn" class="btn btn-success" onclick="CaseUnitFun.prototype.submit();">
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
    var CaseUnitFun = function () {

    }

    CaseUnitFun.prototype.config = {
        unit: {
            frm: function () {
                return "frm_Unit";//单元基本信息frm
            },
        }

    }

    CaseUnitFun.prototype.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    }

    CaseUnitFun.prototype.writeSelectData = function (frm, data, name) {
        if (CaseUnitFun.prototype.isEmpty(data)) {
            $("#" + frm + " ." + name).val(data).trigger("change");
        } else {
            $("#" + frm + " ." + name).val(null).trigger("change");
        }
    }

    //提交
    CaseUnitFun.prototype.submit = function () {
        if (!$("#" + CaseUnitFun.prototype.config.unit.frm()).valid()) {
            return false;
        }
        var data = formParams(CaseUnitFun.prototype.config.unit.frm());
        var buildingId = "${buildingId}";
        if (CaseUnitFun.prototype.isEmpty(buildingId)){
            data.buildingId = buildingId;
        }
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/caseUnit/saveAndUpdateCaseUnit",
            data: data,
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


</script>
<%@include file="/views/share/main_footer.jsp" %>
</body>
</html>
