<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <form class="form-horizontal" id="declareApplyForm">
                <input type="hidden" name="planDetailsId" value="${projectPlanDetails.id}">
            </form>

            <!-- 土建 -->
            <div id="viewCivilEngineering">
                <%@include file="/views/project/stageDeclare/BuildingDeclareModel/viewCivilEngineering.jsp" %>
            </div>

            <!-- 设备安装 -->
            <div id="viewEquipmentInstallation">
                <%@include file="/views/project/stageDeclare/BuildingDeclareModel/viewEquipmentInstallation.jsp" %>
            </div>

            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button id="btn_submit" class="btn btn-success" onclick="submit();">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/project/stageDeclare/BuildingDeclareModel/viewEconomicIndicators.jsp" %>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/declare/declare.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js"></script>
<script>
    var config = {
        declare: {
            frm: "declareApplyForm"
        },
        civilEngineering: {
            name: "土建",
            view: "viewCivilEngineering"
        },
        equipmentInstallation: {
            name: "设备安装",
            view: "viewEquipmentInstallation"
        }
    };

    var declareFunObj = new Object();

    declareFunObj.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    /**
     * @author:  zch
     * 描述:获取申报证书类型
     * @date:2018-10-22
     **/
    declareFunObj.getDeclareType = function (name) {
        var declareType = null;
        $("#" + config.declare.frm + " :checkbox").each(function (j, oo) {
            AssessCommon.getProjectClassifyInfoAsync($(oo).val(), function (data) {
                if (declareFunObj.isEmpty(data)) {
                    if (data.name == name) {
                        declareType = data.id;
                    }
                }
            })
        });
        return declareType;
    };

    declareFunObj.updateInit = function () {
        AssessCommon.getProjectClassifyListByFieldName(AssessProjectClassifyKey.singleDeclareBuildingCertificateType, function (html, data) {
            $.each(data, function (i, n) {
                if (config.civilEngineering.name == n.name) {
                    $.ajax({
                        type: "get",
                        url: "${pageContext.request.contextPath}/declareBuildEngineering/listDeclareBuildEngineering",
                        data: {
                            planDetailsId: '${empty projectPlanDetails.id?0:projectPlanDetails.id}',
                            declareType: n.id
                        },
                        success: function (result) {
                            if (result.ret) {
                                if (declareFunObj.isEmpty(result.data)) {
                                    if (result.data.length >= 1) {
                                        declareFunObj.civilEngineering.toggle();//view 显示
                                        $("#" + config.declare.frm + " :checkbox").each(function (j, oo) {
                                            if ($(oo).val() == n.id) {
                                                $(this).prop("checked", true);//单选框 选中
                                            }
                                        });
                                    }
                                }
                            } else {
                                Alert("失败:" + result.errmsg);
                            }
                        },
                        error: function (e) {
                            Alert("调用服务端方法失败，失败原因:" + e);
                        }
                    });
                }
            });
            $.each(data, function (i, n) {
                if (config.equipmentInstallation.name == n.name) {
                    $.ajax({
                        type: "get",
                        url: "${pageContext.request.contextPath}/declareBuildEquipmentInstall/listDeclareBuildEquipmentInstall",
                        data: {
                            planDetailsId: '${empty projectPlanDetails.id?0:projectPlanDetails.id}',
                            declareType: n.id
                        },
                        success: function (result) {
                            if (result.ret) {
                                if (declareFunObj.isEmpty(result.data)) {
                                    if (result.data.length >= 1) {
                                        declareFunObj.equipmentInstallation.toggle();//view 显示
                                        $("#" + config.declare.frm + " :checkbox").each(function (j, oo) {
                                            if ($(oo).val() == n.id) {
                                                $(this).prop("checked", true);//单选框 选中
                                            }
                                        });
                                    }
                                }
                            } else {
                                Alert("失败:" + result.errmsg);
                            }
                        },
                        error: function (e) {
                            Alert("调用服务端方法失败，失败原因:" + e);
                        }
                    });
                }
            });
        });
    };

    declareFunObj.declare = {
        init: function () {
            var num = 6;
            AssessCommon.getProjectClassifyListByFieldName(AssessProjectClassifyKey.singleDeclareBuildingCertificateType, function (html, data) {
                var resetHtml = "";
                var k = 0;
                $.each(data, function (i, n) {
                    if (i % num == 0) {
                        k++;
                    }
                });
                for (var i = 0; i < k; i++) {
                    resetHtml += "<div class='form-group'>";
                    for (var j = i * num; j < i * num + num; j++) {
                        if (j < data.length) {
                            resetHtml += "<div class='col-sm-2'>";
                            resetHtml += "<span class='checkbox-inline'>";
                            resetHtml += "<input type='checkbox' id='other" + data[j].id + "' name='other' required='required' value='" + data[j].id + "'" + ">";
                            resetHtml += "<label for='other" + data[j].id + "'>" + data[j].name + "</label>";
                            resetHtml += "</span>";
                            resetHtml += "</div>";
                        }
                    }
                    resetHtml += "</div>";
                }
                //HTML
                $("#" + config.declare.frm + "HTML").append(resetHtml);
                declareFunObj.updateInit();
                declareFunObj.declare.monitor();
            });
        },
        monitor: function () {
            $.each($("#" + config.declare.frm + " :checkbox"), function (i, n) {
                $(n).click(function () {
                    AssessCommon.getProjectClassifyInfo($(n).val(), function (data) {
                        if (declareFunObj.isEmpty(data)) {
                            if (data.name == config.civilEngineering.name) {
                                declareFunObj.civilEngineering.toggle();
                            }
                            if (data.name == config.equipmentInstallation.name) {
                                declareFunObj.equipmentInstallation.toggle();
                            }
                        }
                    })
                });
            });
        }
    };

    declareFunObj.civilEngineering = {
        toggle: function () {
            $("#" + config.civilEngineering.view).toggle();
        }
    };

    declareFunObj.equipmentInstallation = {
        toggle: function () {
            $("#" + config.equipmentInstallation.view).toggle();
        }
    };

    $(function () {
        //declareFunObj.declare.init();
    });
</script>
<script type="application/javascript">
    //提交
    function submit() {
        if ("${processInsId}" != "0") {
            submitEditToServer("");
        }
        else {
            submitToServer("");
        }
    }

</script>


</html>

