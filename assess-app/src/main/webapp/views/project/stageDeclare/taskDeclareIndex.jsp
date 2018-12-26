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
            <form class="form-horizontal" id="declareApplyForm">
                <input type="hidden" name="planDetailsId" value="${projectPlanDetails.id}">
                <input type="hidden" name="projectId" value="${projectPlanDetails.projectId}">
            </form>
            <!-- 申报各种类型的html视图 -->
            <%@include file="/views/project/stageDeclare/declareApplyModel.jsp" %>
            <!-- 房产证 -->
            <div id="viewDeclareRealtyHouseCert">
                <%@include file="/views/project/stageDeclare/houseDeclarationModel/viewDeclareRealtyHouseCert.jsp" %>
            </div>

            <!-- 土地证 -->
            <div id="viewDeclareRealtyLandCert">
                <%@include file="/views/project/stageDeclare/houseDeclarationModel/viewDeclareRealtyLandCert.jsp" %>
            </div>

            <!-- 不动产证 -->
            <div id="viewDeclareRealtyRealEstateCert">
                <%@include
                        file="/views/project/stageDeclare/houseDeclarationModel/viewDeclareRealtyRealEstateCert.jsp" %>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/declare/declare.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
<input type="file" id="ajaxFileUpload" name="file" style="display: none;">
<script type="text/javascript">
    var config = {
        declare: {
            frm: "declareApplyForm"
        },
        declareRealtyHouseCert: {
            name: "房产证",
            view: "viewDeclareRealtyHouseCert"
        },
        declareRealtyLandCert: {
            name: "土地证",
            view: "viewDeclareRealtyLandCert"
        },
        declareRealtyRealEstateCert: {
            name: "不动产证",
            view: "viewDeclareRealtyRealEstateCert"
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

    //返回修改时 显示申报证书
    declareFunObj.updateInit = function () {
        AssessCommon.getProjectClassifyListByFieldName(AssessProjectClassifyKey.singleHousePropertyCertificateType, function (html, data) {
            $.each(data, function (i, n) {
                if (config.declareRealtyLandCert.name == n.name) {
                    $.ajax({
                        type: "get",
                        url: "${pageContext.request.contextPath}/declareRealtyLandCert/listDeclareRealtyLandCert",
                        data: {
                            planDetailsId: '${empty projectPlanDetails.id?0:projectPlanDetails.id}',
                            declareType: n.id,
                            enable: "yes"
                        },
                        success: function (result) {
                            if (result.ret) {
                                if (declareFunObj.isEmpty(result.data)) {
                                    if (result.data.length >= 1) {
                                        declareFunObj.declareRealtyLandCert.toggle();//view 显示
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
                if (config.declareRealtyHouseCert.name == n.name) {
                    $.ajax({
                        type: "get",
                        url: "${pageContext.request.contextPath}/declareRealtyHouseCert/listDeclareRealtyHouseCert",
                        data: {
                            planDetailsId: '${empty projectPlanDetails.id?0:projectPlanDetails.id}',
                            declareType: n.id,
                            enable: "yes"
                        },
                        success: function (result) {
                            if (result.ret) {
                                if (declareFunObj.isEmpty(result.data)) {
                                    if (result.data.length >= 1) {
                                        declareFunObj.declareRealtyHouseCert.toggle();//view 显示
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
                if (config.declareRealtyRealEstateCert.name == n.name) {
                    $.ajax({
                        type: "get",
                        url: "${pageContext.request.contextPath}/declareRealtyRealEstateCert/listDeclareRealtyRealEstateCert",
                        data: {
                            planDetailsId: '${empty projectPlanDetails.id?0:projectPlanDetails.id}',
                            declareType: n.id
                        },
                        success: function (result) {
                            if (result.ret) {
                                if (declareFunObj.isEmpty(result.data)) {
                                    if (result.data.length >= 1) {
                                        declareFunObj.declareRealtyRealEstateCert.toggle();//view 显示
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
            AssessCommon.getProjectClassifyListByFieldName(AssessProjectClassifyKey.singleHousePropertyCertificateType, function (html, data) {
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
                            resetHtml += "<input type='checkbox' id='classify" + data[j].id + "' name='other' required='required' value='" + data[j].id + "'" + ">";
                            resetHtml += "<label for='classify" + data[j].id + "'>" + data[j].name + "<label>";
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
    };

    /**
     * @author:  zch
     * 描述:土地证
     * @date:2018-09-19
     **/
    declareFunObj.declareRealtyLandCert = {
        toggle: function () {
            $("#" + config.declareRealtyLandCert.view).toggle();
        },
    };

    /**
     * @author:  zch
     * 描述:不动产证
     * @date:2018-09-19
     **/
    declareFunObj.declareRealtyRealEstateCert = {
        toggle: function () {
            $("#" + config.declareRealtyRealEstateCert.view).toggle();
        }
    };


    $(function () {
        //declareFunObj.declare.init();
    });
</script>
<script type="application/javascript">
    //提交
    function submit() {
        //检查是否填写了申报数据
        var rows = $("#" + declareRealtyHouseCertConfig.table).bootstrapTable('getData');
        if (rows && rows.length > 0) {
            submitForm();
            return false;
        }
        rows = $("#" + declareRealtyLandCertConfig.table).bootstrapTable('getData');
        if (rows && rows.length > 0) {
            submitForm();
            return false;
        }
        rows = $("#" + declareRealtyRealEstateCertConfig.table).bootstrapTable('getData');
        if (rows && rows.length > 0) {
            submitForm();
            return false;
        }


        toastr.info("请添加相关申报信息");
    }

    //提交表单
    function submitForm() {
        if ("${processInsId}" != "0") {
            submitEditToServer("");
        }
        else {
            submitToServer("");
        }
    }

</script>


</html>

