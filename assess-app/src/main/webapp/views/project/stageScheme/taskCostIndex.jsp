<!-- 成本法 -->
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/math/6.0.2/math.js"></script>
    <link href="${pageContext.request.contextPath}/assets/x-editable/css/bootstrap-editable.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <!-- 引入成本法模块 -->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>成本法</h3>
                    <div class="clearfix"></div>
                </div>

                <div class="x_content">
                    <form class="form-horizontal" id="costMethodFrm">
                        <div class="col-sm-12 form-group">
                            <span class="col-sm-1">
                                <label>建筑形态</label>
                            </span>
                            <span class="col-sm-2 col-sm-offset-1 checkbox-inline" style="display: none">
                                <input type="radio" id="building" name="type" value="1">
                                <label for="building">建筑物</label>
                            </span>
                            <span class="col-sm-2  checkbox-inline">
                                <input type="radio" id="construction" name="type" value="2" checked="checked">
                                <label for="construction">在建工程</label>
                            </span>
                        </div>
                    </form>
                </div>

                <div class="x_content">
                    <form class="form-horizontal" id="buildingFrm" style="display: none">
                        <%@include file="/views/method/module/costModule/building.jsp" %>
                    </form>
                </div>

                <%@include file="/views/method/module/developmentCommon.jsp" %>


                <div class="x_content">
                    <form class="form-horizontal" id="constructionFrm">
                        <%@include file="/views/method/module/costModule/constructionJs.jsp" %>
                        <%@include file="/views/method/module/costModule/construction.jsp" %>
                    </form>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_content">
                    <form class="form-horizontal" id="md_cost_form">
                        <input type="hidden" name="id" value="${mdDevelopment.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    单价<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" data-rule-number="true" required
                                           name="price" value="${mdCost.price}">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                报告附件<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-3">
                                <input id="report_file" name="report_file" type="file" multiple="false">
                                <div id="_report_file"></div>
                            </div>
                        </div>
                    </form>
                </div>
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
<script src="${pageContext.request.contextPath}/assets/x-editable/js/bootstrap-editable.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
<script type="text/javascript">

    var cost = {};

    cost.frm = $("#costMethodFrm");
    cost.buildingFrm = $("#buildingFrm");
    cost.constructionFrm = $("#constructionFrm");

    cost.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    cost.isNotBlankObject = function (obj) {
        for (var key in obj) {
            return true;
        }
        return false
    };

    cost.valid = function (callback) {
        var html = "<div class='help-block' for='for'>" + "该字段为必填项(注意哦!)" + "</div>";
        var head = formSerializeArray(cost.frm);
        if (head.type == '1') {
            if (!cost.buildingFrm.valid()) {
                return false;
            }
        }
        if (head.type == '2') {
            var data = formSerializeArray(cost.constructionFrm);
            if (!AssessCommon.isNumber(data.infrastructureCost)){
                cost.constructionFrm.find("select[name='infrastructureCost']").parent().after(html.replace(/for/g, "infrastructureCost_"));
            }
            if (!AssessCommon.isNumber(data.infrastructureMatchingCost)){
                cost.constructionFrm.find("select[name='infrastructureMatchingCost']").parent().after(html.replace(/for/g, "infrastructureMatchingCost_"));
            }
            if (!AssessCommon.isNumber(data.devDuring)){
                cost.constructionFrm.find("select[name='devDuring']").parent().after(html.replace(/for/g, "devDuring_"));
            }
            if (!cost.constructionFrm.valid()) {
                return false;
            }
        }
        if (callback) {
            callback();
        }
    };

    cost.getFomData = function () {
        var head = formSerializeArray(cost.frm);
        if (head.type == '1') {
            var data = formSerializeArray(cost.buildingFrm);
            $.extend(data, head);
            data.planDetailsId = '${projectPlanDetails.id}';
            return data;
        }
        if (head.type == '2') {
            var data = formSerializeArray(cost.constructionFrm);
            $.extend(data, head);
            data.planDetailsId = '${projectPlanDetails.id}';
            return data;
        }
    };

    //参数校验
    cost.checkParams = function (that) {
        if (!cost.isNotBlank(that)) {
            return false;
        }
        var value = $(that).val();
        var i = 0;
        if (!cost.isNotBlank(value)) {
            return false;
        }
        if (AssessCommon.isNumber(value)) {
            i++;
        }
        var reg = new RegExp(/^[0-9]+\.?[0-9]*%$/);
        if (reg.test(value)) {
            i++;
        }
        if (i == 0) {
            alert("不符合，必须是数字!");
            $(that).attr("data-value", '');
            $(that).val('');
            return false;
        }
    };

    $(document).ready(function () {

        var type = '${mdCost.type}';

        cost.frm.find("input[type='radio'][name='type']").change(function () {
            var data = formSerializeArray(cost.frm);
            if (data.type == '1') {
                cost.buildingFrm.show();
                cost.constructionFrm.hide();
            }
            if (data.type == '2') {
                cost.buildingFrm.hide();
                cost.constructionFrm.show();
            }
        });
        if (cost.isNotBlank(type)) {
            if (type == '2') {
                cost.buildingFrm.hide();
                cost.constructionFrm.show();
            }
            if (type == '1') {
                cost.buildingFrm.show();
                cost.constructionFrm.hide();
            }
        }

        if (!cost.isNotBlank('${mdCostConstruction.id}')){
            var query = {province:'${schemeAreaGroup.province}',city:'${schemeAreaGroup.city}',district:'${schemeAreaGroup.district}',bisNationalUnity:true} ;
            $.ajax({
                type: "get",
                url: "${pageContext.request.contextPath}/dataTaxRateAllocation/specialTreatment",
                data: query,
                success: function (result) {
                    if (result.ret) {
                        if (result.data){
                            console.log(result.data) ;
                            $.each(result.data,function (i,item) {
                                var taxRate = item.taxRate ;
                                if (item.taxRate){
                                    taxRate = Number(item.taxRate) * 100 ;
                                    taxRate = taxRate + "%" ;
                                }
                                if (item.typeName == '增值税金及附加'){
                                    if (item.taxRate){
                                        cost.constructionFrm.find("input[name='salesTaxAndAdditional']").val(taxRate) ;
                                        cost.constructionFrm.find("input[name='salesTaxAndAdditional']").attr("data-value",item.taxRate) ;
                                    }
                                }
                                if (item.fieldName == '管理费率'){
                                    if (item.taxRate){
                                        cost.constructionFrm.find("input[name='managementExpense']").val(taxRate) ;
                                        cost.constructionFrm.find("input[name='managementExpense']").attr("data-value",item.taxRate) ;
                                    }
                                }
                            });
                        }
                    } else {
                        Alert("保存失败:" + result.errmsg);
                    }
                },
                error: function (e) {
                    Alert("调用服务端方法失败，失败原因:" + e);
                }
            });
        };

        construction.loadMdDevelopmentInfrastructureChildrenTable() ;
    });

</script>
<script type="application/javascript">
    $(function () {
        FileUtils.uploadFiles({
            target: "report_file",
            disabledTarget: "btn_submit",
            formData: {
                tableName: AssessDBKey.MdCost,
                tableId: '${mdCost.id}',
                projectId: "${projectInfo.id}"
            },
            editFlag: true,
            deleteFlag: true
        });
        FileUtils.getFileShows({
            target: "report_file",
            formData: {
                tableName: AssessDBKey.MdCost,
                tableId: '${mdCost.id}'
            },
            editFlag: true,
            deleteFlag: true
        })
    });
    //提交
    function submit() {
        cost.valid(function () {
            var data = cost.getFomData();
            var item = formSerializeArray($("#md_cost_form")) ;
            if(data.type == '2'){
                if (item){
                    if (item.price){
                        data.constructionAssessmentPriceCorrecting =  item.price;
                    }
                }
            }
            if ("${processInsId}" != "0") {
                submitEditToServer(JSON.stringify(data));
            } else {
                submitToServer(JSON.stringify(data));
            }
        });
    }
</script>

</html>

