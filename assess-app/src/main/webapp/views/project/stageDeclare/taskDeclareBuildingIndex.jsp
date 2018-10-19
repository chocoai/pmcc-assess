<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <div class="x_panel">
                <div class="x_content">
                    <div class="x_title">
                        <h3>申报证书类型
                        </h3>
                        <div class="clearfix"></div>
                    </div>
                    <form class="form-horizontal" id="frmCertificate">
                        <div id="frmCertificateHTML">

                        </div>
                    </form>
                </div>
            </div>

            <!-- 土建 -->
            <div id="viewCivilEngineering" style="display: none;">
                <%@include file="/views/project/stageDeclare/BuildingDeclareModel/viewCivilEngineering.jsp" %>
            </div>

            <!-- 设备安装 -->
            <div id="viewEquipmentInstallation" style="display: none;">
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
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
<script>
    var config = {
        declare: {
            frm: "frmCertificate"
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
    }

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
    }

    $(function () {
        declareFunObj.declare.init();
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

