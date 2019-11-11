<!-- 成本法 -->
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/math/6.0.2/math.js?v=${assessVersion}"></script>
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

                <div class="x_content" id="costCheckboxTool">
                    <div class="col-sm-12 form-group">
                            <span class="col-sm-1">
                                <label>建筑形态</label>
                            </span>
                        <span class="col-sm-2 col-sm-offset-1 checkbox-inline">
                                <input type="radio" id="building" name="type" value="1">
                                <label for="building">建筑物</label>
                            </span>
                        <span class="col-sm-2  checkbox-inline">
                                <input type="radio" id="construction" name="type" value="2" checked="checked">
                                <label for="construction">在建工程</label>
                            </span>
                    </div>
                </div>


                <%@include file="/views/method/module/costModule/construction.jsp" %>
                <%@include file="/views/method/module/costModule/constructionJs.jsp" %>
                <%@include file="/views/method/module/developmentCommon.jsp" %>
                <%@include file="/views/project/tool/residueRatio.jsp" %>
                <%@include file="/views/method/module/economicIndicators.jsp" %>
                <script src="${pageContext.request.contextPath}/js/method/developmentCommon.js?v=${assessVersion}"></script>
                <script type="text/javascript" src="${pageContext.request.contextPath}/js/declare/declare.common.js?v=${assessVersion}"></script>
            </div>

            <div class="x_panel">
                <div class="x_content">
                    <form class="form-horizontal" id="md_cost_form">
                        <input type="hidden" name="id" value="${mdCostVo.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    单价<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" data-rule-number="true" required
                                           name="price" value="${mdCostVo.price}">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                附件<span class="symbol required"></span>
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
<script src="${pageContext.request.contextPath}/assets/x-editable/js/bootstrap-editable.min.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/assets/x-editable/js/bootstrap-table-editable.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js?v=${assessVersion}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
<script type="text/javascript">

    var cost = {};
    cost.constructionFrm = $("#constructionFrm");

    cost.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };
    cost.one = 1;
    cost.two = 2;

    cost.isNotBlankObject = function (obj) {
        for (var key in obj) {
            return true;
        }
        return false
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
        //从服务端获取计算后的数据
        cost.calculationNumeric(formSerializeArray(cost.constructionFrm),function (data) {
            cost.initForm(data) ;
        });
    };

    cost.checkedFun = function (that, name,flag) {
        var form = $(that).closest("form");
        var target = form.find("[name='"+name+"']:checkbox") ;
        if (flag) {//全选或者全不选
            var number = 1;
            target.each(function (i, n) {
                if ($(this).prop("checked")) {
                    number++;
                }
            });
            if (number == 1) {
                target.prop("checked", true);
            } else {
                target.prop("checked", false);
            }
        } else {
            //首先让选中的失效
            target.each(function (i, n) {
                if ($(this).prop("checked")) {
                    $(this).prop("disabled", true);
                }
            });
            //然后让没有选中的元素设置为选中
            target.each(function (i, n) {
                if (!$(this).prop("checked")) {
                    $(this).prop("checked", true);
                }
            });
            //最后是让失效的元素恢复,并且使其不选中
            target.each(function (i, n) {
                if ($(this).prop("disabled")) {
                    $(this).prop("disabled", false);
                    $(this).prop("checked", false);
                }
            });
        }
    };

    cost.initForm = function (data) {


//        cost.constructionFrm.initForm(data);

        cost.constructionFrm.find("input[name='landGetCostTotal']").val(data.landGetCostTotal);
        cost.constructionFrm.find(".landGetCostTotal").html(data.landGetCostTotal);
        cost.constructionFrm.find("input[name='constructionSubtotal']").val(data.constructionSubtotal);
        cost.constructionFrm.find(".constructionSubtotal").html(data.constructionSubtotal);
        cost.constructionFrm.find("input[name='interestInvestment']").val(data.interestInvestment);
        cost.constructionFrm.find(".interestInvestment").html(data.interestInvestment);
        cost.constructionFrm.find("input[name='investmentProfit']").val(data.investmentProfit);
        cost.constructionFrm.find(".investmentProfit").html(data.investmentProfit);
        cost.constructionFrm.find("input[name='constructionAssessmentValue']").val(data.constructionAssessmentValue);
        cost.constructionFrm.find(".constructionAssessmentValue").html(data.constructionAssessmentValue);
        cost.constructionFrm.find("input[name='constructionAssessmentPriceCorrecting']").val(data.constructionAssessmentPriceCorrecting);
        cost.constructionFrm.find(".constructionAssessmentPriceCorrecting").html(data.constructionAssessmentPriceCorrecting);

        if ($("#md_cost_form").size() != 0){
            $("#md_cost_form").find("input[name='price']").val(data.constructionAssessmentPriceCorrecting) ;
        }

    };

    cost.writeValueEvent = function (value,callback) {
        var target = $("#LAND_ACQUISITION_COST") ;
        cost.constructionFrm.find("input[name='type']").val(value);
        if (value == cost.one){
            target.find("input").each(function () {
                var text = $(this).val() ;
                if (text){
                    var className = $(this).prop("class") ;
                    if (className.indexOf('x-percent') != -1){
                        text = $(this).attr("data-value");
                    }
                    $(this).attr({'data-value':0,'obj-value':text}).val(0);
                }
            }) ;
            target.hide() ;
            var landGetCostTotal = cost.constructionFrm.find("input[name='landGetCostTotal']").val() ;
            cost.constructionFrm.find("input[name='landGetCostTotal']").val(0).attr({'obj-value':landGetCostTotal}) ;
            cost.constructionFrm.find(".landGetCostTotal").parent().parent().hide() ;
            cost.constructionFrm.find(".residueRatio").show();
        }
        if (value == cost.two){
            target.show() ;
            target.find("input").each(function () {
                var text = $(this).attr("obj-value");
                if (text){
                    $(this).attr({'data-value':text,'obj-value':0}).val(text);
                    var className = $(this).prop("class") ;
                    if (className.indexOf('x-percent') != -1){
                        var vv = Number(text)*100;
                        vv = vv.toFixed(2);
                        vv += "%" ;
                        $(this).val(vv);
                    }
                }
            }) ;

            var landGetCostTotal2 = cost.constructionFrm.find("input[name='landGetCostTotal']").attr('obj-value') ;
            cost.constructionFrm.find("input[name='landGetCostTotal']").val(landGetCostTotal2) ;
            cost.constructionFrm.find(".landGetCostTotal").parent().parent().show() ;
            cost.constructionFrm.find(".residueRatio").hide();
        }
        if (callback) {
            callback(value);
        }
    };

    cost.initParcelSettingData = function (data) {
        var industrySupplyInfoContainer = $("#industrySupplyInfoContainer_BBBBB");
        var developmentDegreeContentContainer = $("#developmentDegreeContentContainer_BBBBB");
        industrySupplyInfoContainer.empty();
        developmentDegreeContentContainer.empty();
        //宗地外设定
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.estateLandInfrastructure, '', function (html, resultData) {
            var resultHtml = '<div>';
            var array = [];
            if (data) {
                if (data.parcelSettingOuter) {
                    array = data.parcelSettingOuter.split(',');
                }
            }
            $.each(resultData, function (i, item) {
                resultHtml += '<span class="checkbox-inline"><input type="checkbox" ';
                if ($.inArray(item.id.toString(), array) > -1) {
                    resultHtml += ' checked="checked" ';
                }
                resultHtml += ' id="parcelSettingOuterBBBBB' + item.id + '" name="parcelSettingOuter" value="' + item.id + '">';
                resultHtml += '<label for="parcelSettingOuterBBBBB' + item.id + '">' + item.name + '</label></span>';
            });
            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '全选或全不选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"cost.checkedFun(this,'parcelSettingOuter',true)\">";
            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '反选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"cost.checkedFun(this,'parcelSettingOuter',false)\">";
            resultHtml += "</div>";
            if (industrySupplyInfoContainer.find("div").size() == 0){
                industrySupplyInfoContainer.append(resultHtml);
            }else {
                $.each(resultData, function (i, item) {
                    var ele = "#parcelSettingOuterBBBBB"+item.id ;
                    ele = $(ele) ;
                    if ($.inArray(item.id.toString(), array) > -1) {
                        ele.prop("checked", true);
                    }else {
                        ele.prop("checked", false);
                    }
                });
            }
        }, true);
        //宗地内设定
        AssessCommon.loadDataDicByKey(AssessDicKey.estateDevelopment_degreePrepared_land, '', function (html, resultData) {
            var resultHtml = '<div>';
            var array = [];
            if (data) {
                if (data.parcelSettingInner) {
                    array = data.parcelSettingInner.split(',');
                }
            }
            $.each(resultData, function (i, item) {
                resultHtml += '<span class="checkbox-inline"><input type="checkbox" ';
                if ($.inArray(item.id.toString(), array) > -1) {
                    resultHtml += ' checked="checked" ';
                }
                resultHtml += ' id="parcelSettingInnerBBBBB' + item.id + '" name="parcelSettingInner" value="' + item.id + '">';
                resultHtml += '<label for="parcelSettingInnerBBBBB' + item.id + '">' + item.name + '</label></span>';
            });
            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '全选或全不选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"cost.checkedFun(this,'parcelSettingInner',true)\">";
            resultHtml += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '反选' + "</span>";
            resultHtml += "<input type=\"radio\" name=\"infrastructureSelect\"  onclick=\"cost.checkedFun(this,'parcelSettingInner',false)\">";
            resultHtml += "</div>";
            if (developmentDegreeContentContainer.find("div").size() == 0){
                developmentDegreeContentContainer.append(resultHtml);
            }else {
                $.each(resultData, function (i, item) {
                    var ele = "#parcelSettingInnerBBBBB"+item.id ;
                    ele = $(ele) ;
                    if ($.inArray(item.id.toString(), array) > -1) {
                        ele.prop("checked", true);
                    }else {
                        ele.prop("checked", false);
                    }
                });
            }
        });
    };
    
    cost.calculationNumeric = function (data,callback) {
        $.ajax({
            type: "post",
            url: getContextPath() +"/mdCostConstruction/calculationNumeric",
            data: {fomData:JSON.stringify(data)},
            success: function (result) {
                if (result.ret) {
                    if (callback){
                        callback(result.data) ;
                    }
                } else {
//                    Alert("失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };

    $(document).ready(function () {
        //建筑安装工程费 估价时点完工程度的设置为100%
        var type = '${mdCostVo.type}';
        if (cost.isNotBlank(type)){
            if (type == cost.one) {
                $("#building").attr('checked','true');
                $("#construction").removeAttr("checked");
            }
            if (type == cost.two) {
                $("#construction").attr('checked','true');
                $("#building").removeAttr("checked");
            }
        }else {
            type = $("#costCheckboxTool").find("input[name='type']:checked").val() ;
        }
        cost.writeValueEvent(type,function () {
            construction.loadMdCalculatingMethodEngineeringCostTable() ;
        }) ;
        $("#costCheckboxTool").find("input[name='type']:radio").change(function () {
            var value = $(this).val() ;
            cost.writeValueEvent(value , function () {
                try {
                    construction.loadMdCalculatingMethodEngineeringCostTable() ;
                } catch (e) {
                    console.log(e) ;
                }
            }) ;
        });
        if (!cost.isNotBlank('${mdCostVo.mdCostConstruction.id}')){
            var query = {province:'${schemeAreaGroup.province}',city:'${schemeAreaGroup.city}',district:'${schemeAreaGroup.district}',bisNationalUnity:true} ;
            $.ajax({
                type: "get",
                url: "${pageContext.request.contextPath}/dataTaxRateAllocation/specialTreatment",
                data: query,
                success: function (result) {
                    if (result.ret) {
                        if (result.data){
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

        var data = {
            parcelSettingInner: '${mdCostVo.mdCostConstruction.parcelSettingInner}',
            parcelSettingOuter: '${mdCostVo.mdCostConstruction.parcelSettingOuter}'
        };
        cost.initParcelSettingData(data) ;


    });

</script>
<script type="application/javascript">
    $(function () {
        FileUtils.uploadFiles({
            target: "report_file",
            disabledTarget: "btn_submit",
            formData: {
                tableName: AssessDBKey.MdCost,
                tableId: '${mdCostVo.id}',
                projectId: "${projectInfo.id}"
            },
            editFlag: true,
            deleteFlag: true
        });
        FileUtils.getFileShows({
            target: "report_file",
            formData: {
                tableName: AssessDBKey.MdCost,
                tableId: '${mdCostVo.id}'
            },
            editFlag: true,
            deleteFlag: true
        })
    });
    //提交
    function submit() {
        var data = formSerializeArray(cost.constructionFrm);
        if (!cost.constructionFrm.valid()) {
            return false;
        }
        var item = formSerializeArray($("#md_cost_form")) ;
        if (item){
            if (item.price){
                data.constructionAssessmentPriceCorrecting =  item.price;
            }
        }
        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(data));
        } else {
            submitToServer(JSON.stringify(data));
        }
    }
</script>

</html>

