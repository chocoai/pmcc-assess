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

            <!-- 房产证 -->
            <div class="x_panel" id="viewDeclareRealtyHouseCert">
                <%@include file="/views/project/stageDeclare/model/viewDeclareRealtyHouseCert.jsp" %>
            </div>

            <!-- 土地证 -->
            <div id="viewDeclareRealtyLandCert">
                <%@include file="/views/project/stageDeclare/model/viewDeclareRealtyLandCert.jsp" %>
            </div>

            <!-- 不动产证 -->
            <div id="viewDeclareRealtyRealEstateCert">
                <%@include file="/views/project/stageDeclare/model/viewDeclareRealtyRealEstateCert.jsp" %>
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
<script>
    var config = {
        declare: {
            frm: "frmCertificate"
        },
        declareRealtyHouseCert: {
            name: "房产证",
            view:"viewDeclareRealtyHouseCert"
        },
        declareRealtyLandCert: {
            name: "土地证",
            view:"viewDeclareRealtyLandCert"
        },
        declareRealtyRealEstateCert: {
            name: "不动产证",
            view:"viewDeclareRealtyRealEstateCert"
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
            AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareHousePropertyCertificateType, "", function (html, data) {
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
                            resetHtml += "<input type='checkbox' name='other' required='required' value='" + data[j].id + "'" + ">";
                            resetHtml += data[j].name;
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
                    AssessCommon.getDataDicInfo($(n).val(), function (data) {
                        if (declareFunObj.isEmpty(data)) {
                            if (data.name == config.declareRealtyHouseCert.name) {
                                declareFunObj.declareRealtyHouseCert.toggle();
                            }
                            if (data.name == config.declareRealtyLandCert.name) {
                                declareFunObj.declareRealtyLandCert.toggle();
                            }
                            if (data.name == config.declareRealtyRealEstateCert.name) {
                                declareFunObj.declareRealtyRealEstateCert.toggle();
                            }
                        }
                    })
                });
            });
        }
    };

    /**
     * @author:  zch
     * 描述:房产证
     * @date:2018-09-19
     **/
    declareFunObj.declareRealtyHouseCert = {
        toggle: function () {
            $("#" + config.declareRealtyHouseCert.view).toggle();
        }
    }

    /**
     * @author:  zch
     * 描述:土地证
     * @date:2018-09-19
     **/
    declareFunObj.declareRealtyLandCert = {
        toggle: function () {
            $("#" + config.declareRealtyLandCert.view).toggle();
        },
    }

    /**
     * @author:  zch
     * 描述:不动产证
     * @date:2018-09-19
     **/
    declareFunObj.declareRealtyRealEstateCert = {
        toggle: function () {
            $("#" + config.declareRealtyRealEstateCert.view).toggle();
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

