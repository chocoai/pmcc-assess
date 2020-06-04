<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <title>信息填写</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <div class="page-inner">
                <div class="row">
                    <!-- 填写表单 start -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        信息填写
                                        <button type="button" class="btn btn-sm btn-primary"
                                                onclick="showHistoryModal();">历史记录
                                        </button>

                                        <button type="button" class="btn btn-sm btn-primary"
                                                onclick="showCaseQuoteModal();">引用案例
                                        </button>

                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form class="form-horizontal" id="frm_estate">
                                    <input type="hidden" name="id" value="${basicEstate.id}">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">省
                                                    <span class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <select name="province"
                                                            class="form-control input-full search-select select2 province">
                                                    </select>
                                                </div>
                                                <label class="col-sm-1 control-label">市<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <select name="city"
                                                            class="form-control input-full search-select select2 city">
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <div class="col-sm-12">
                                                    <table class="table estateVillages">
                                                        <thead>
                                                        <tr>
                                                            <th style="width: 10%">区（县）</th>
                                                            <th style="width: 10%">街道(乡)名称</th>
                                                            <th style="width: 10%">街道(村)名称</th>
                                                            <th style="width: 10%">附号(组名称)</th>
                                                            <th style="width: 7%"></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody></tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">地块名称<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <div class="input-group">
                                                        <input type="text" id="txt_estate_search"
                                                               data-rule-maxlength="100" placeholder="地块名称"
                                                               required="required"
                                                               name="name" class="form-control"
                                                               value="${basicEstate.name}">
                                                        <div class="input-group-prepend">
                                                            <button class="btn btn-info btn-sm "
                                                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                    type="button"
                                                                    onclick="stateCommon.mapMarker();">
                                                                标注
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                                <label class="col-sm-1 control-label">地块方位</label>
                                                <div class="col-sm-3">
                                                    <select name="position"
                                                            class="form-control input-full search-select position select2">
                                                    </select>
                                                </div>
                                                <label class="col-sm-1 control-label">基础版块<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <div class="input-group">
                                                        <input type="hidden" name="blockId"
                                                               value="${basicEstate.blockId}">
                                                        <input type="text"
                                                               onchange="$(this).closest('.input-group').find('[name=blockId]').val('0');"
                                                               placeholder="基础版块" class="form-control" name="blockName"
                                                               value="${basicEstate.blockName}">
                                                        <div class="input-group-prepend">
                                                            <button class="btn btn-warning btn-sm "
                                                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                    type="button"
                                                                    onclick="$(this).closest('.input-group').find('input').val('');">
                                                                清空
                                                            </button>
                                                        </div>
                                                        <div class="input-group-prepend">
                                                            <button class="btn btn-primary btn-sm "
                                                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                    type="button"
                                                                    onclick="estateCommon.blockSelect(this);">选择
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">基础版块描述<span
                                                        class="symbol required"></span></label>
                                                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                        <textarea class="form-control input-full" name="blockDescription" id="blockDescription" required
                                  placeholder="基础版块描述">${basicEstate.blockDescription}</textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1">占地面积</label>
                                                <div class=" col-xs-3  col-sm-3  col-md-3 col-lg-3">
                                                    <div class="input-group">
                                                        <select class="form-control form-control-sm" name="coverAnAreaUnit">
                                                            <option value="">--请选择--</option>
                                                            <option value="平方米">平方米</option>
                                                            <option value="亩">亩</option>
                                                        </select>
                                                        <input type="number" data-rule-maxlength="100"
                                                               data-rule-number='true'
                                                               placeholder="占地面积(请输入数字)" name="coverAnArea"
                                                               class="form-control form-control-sm"
                                                               value="${basicEstate.coverAnArea}">
                                                    </div>
                                                </div>
                                                <label class="col-sm-1">土地权利人</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="土地权利人" name="developerName"
                                                           class="form-control input-full"
                                                           value="${basicEstate.developerName}">
                                                </div>

                                                <label class="col-sm-1 control-label">均价</label>
                                                <div class=" col-xs-3  col-sm-3  col-md-3 col-lg-3">
                                                    <div class="input-group">
                                                        <select class="form-control form-control-sm" name="averagePriceUnit">
                                                            <option value="">--请选择--</option>
                                                            <option value="元/平方米">元/平方米</option>
                                                            <option value="万元/亩">万元/亩</option>
                                                        </select>
                                                        <input type="number" data-rule-maxlength="100" data-rule-number='true'
                                                               placeholder="均价(请输入数字)" name="averagePrice"
                                                               class="form-control-sm form-control"
                                                               value="${basicEstate.averagePrice}">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">价格区间</label>
                                                <div class=" col-xs-3  col-sm-3  col-md-3 col-lg-3">
                                                    <div class="input-group">
                                                        <select class="form-control form-control-sm" name="priceRangeUnit">
                                                            <option value="">--请选择--</option>
                                                            <option value="元/平方米">元/平方米</option>
                                                            <option value="万元/亩">万元/亩</option>
                                                        </select>
                                                        <input type="text" placeholder="价格区间"
                                                               name="priceRange" class="form-control-sm form-control"
                                                               value="${basicEstate.priceRange}">
                                                    </div>
                                                </div>
                                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                                    取得时间
                                                </label>
                                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                                    <input type="text" placeholder="取得时间" data-date-format='yyyy-mm-dd'
                                                           name="openTime" class="form-control input-full dbdate">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">地块区位分析<span
                                                        class="symbol required"></span></label>
                                                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                        <textarea class="form-control input-full" name="locationDescribe" required
                                  placeholder="地块区位分析">${basicEstate.locationDescribe}</textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="landEstateFile"></div>

                                    <div class="card-header">
                                        <div class="card-category">宗地外</div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1">基础设施完备度<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <select class="form-control input-full  "
                                                            name="infrastructureCompleteness"
                                                            required="required">
                                                    </select>
                                                </div>
                                                <label class="col-sm-1">
                                                    宗地外
                                                </label>
                                                <div class="col-sm-7">
                                                    <div id="industrySupplyInfoContainer"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <div class="x_title">
                                    <div class="clearfix"></div>
                                </div>
                                <form class="form-horizontal" id="frm_estateLandState">
                                    <input type="hidden" name="id" value="${basicEstateLandState.id}">
                                    <input type="hidden" name="landFactorTotalScoreResult">
                                    <input type="hidden" name="landLevelContentResult">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1">四至(1)<span
                                                        class="symbol required"></span></label>
                                                <div class=" col-xs-3  col-sm-3  col-md-3 col-lg-3">
                                                    <div class="input-group">
                                                        <select class="form-control form-control-sm" name="eastToName">
                                                            <option value="东至" selected="selected">东至</option>
                                                            <option value="东南">东南</option>
                                                            <option value="东北">东北</option>
                                                        </select>
                                                        <input type="text" class="form-control form-control-sm" required
                                                               name="eastTo"
                                                               value="${basicEstateLandState.eastTo}">
                                                    </div>
                                                </div>
                                                <label class="col-sm-1">四至(2)<span
                                                        class="symbol required"></span></label>
                                                <div class=" col-xs-3  col-sm-3  col-md-3 col-lg-3">
                                                    <div class="input-group">
                                                        <select class="form-control form-control-sm" name="southToName">
                                                            <option value="南至" selected="selected">南至</option>
                                                            <option value="东南">东南</option>
                                                            <option value="西南">西南</option>
                                                        </select>
                                                        <input type="text" class="form-control form-control-sm" required
                                                               name="southTo"
                                                               value="${basicEstateLandState.southTo}">
                                                    </div>
                                                </div>
                                                <label class="col-sm-1">四至(3)<span
                                                        class="symbol required"></span></label>
                                                <div class=" col-xs-3  col-sm-3  col-md-3 col-lg-3">
                                                    <div class="input-group">
                                                        <select class="form-control form-control-sm" name="westToName">
                                                            <option value="西至" selected="selected">西至</option>
                                                            <option value="西南">西南</option>
                                                            <option value="西北"></option>
                                                        </select>
                                                        <input type="text" class="form-control form-control-sm" required
                                                               name="westTo"
                                                               value="${basicEstateLandState.westTo}">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1">四至(4)<span
                                                        class="symbol required"></span></label>
                                                <div class=" col-xs-3  col-sm-3  col-md-3 col-lg-3">
                                                    <div class="input-group">
                                                        <select class="form-control form-control-sm" name="northToName">
                                                            <option value="北至" selected="selected">北至</option>
                                                            <option value="东北">东北</option>
                                                            <option value="西北">西北</option>
                                                        </select>
                                                        <input type="text" class="form-control form-control-sm" required
                                                               name="northTo"
                                                               value="${basicEstateLandState.northTo}">
                                                    </div>
                                                </div>
                                                <label class="col-sm-1 control-label">土地面积</label>
                                                <div class=" col-xs-3  col-sm-3  col-md-3 col-lg-3">
                                                    <div class="input-group">
                                                        <select class="form-control form-control-sm" name="landAreaUnit">
                                                            <option value="平方米" selected="selected">平方米</option>
                                                            <option value="亩">亩</option>
                                                        </select>
                                                        <input type="number" class="form-control form-control-sm"
                                                               data-rule-number='true'
                                                               placeholder="土地面积(请输入数字)" name="landArea"
                                                               value="${basicEstateLandState.landArea}">
                                                    </div>
                                                </div>
                                                <label class="col-sm-1 control-label">地形</label>
                                                <div class="col-sm-3">
                                                    <select class="form-control input-full  planeness"
                                                            name="planeness">
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">地势</label>
                                                <div class="col-sm-3">
                                                    <select class="form-control input-full  topographicTerrain"
                                                            name="topographicTerrain">
                                                    </select>
                                                </div>
                                                <label class="col-sm-1">土地开发程度</label>
                                                <div class="col-sm-3">
                                                    <select class="form-control input-full developmentDegree"
                                                            name="developmentDegree">
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1">土地利用现状</label>
                                                <div class="col-sm-3">
                                                    <input type="text" class="form-control input-full"
                                                           placeholder="未开发，已开发完成投入使用，部分开发"
                                                           name="presentSituationLandUse"
                                                           value="${basicEstateLandState.presentSituationLandUse}">
                                                </div>
                                                <label class="col-sm-1 developmentDegreeContent">土地开发程度备注</label>
                                                <div class="col-sm-3 developmentDegreeContent">
                                                    <input type="text" class="form-control input-full"
                                                           placeholder="土地开发程度备注"
                                                           name="developmentDegreeRemark"
                                                           value="${basicEstateLandState.developmentDegreeRemark}">
                                                </div>
                                                <label class="col-sm-1 parcelSettingInner">
                                                    宗地内
                                                </label>
                                                <div class="col-sm-7 parcelSettingInner">
                                                    <div id="developmentDegreeContentContainer"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">宗地内现状</label>
                                                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                        <textarea class="form-control input-full" name="currentSituation" required
                                  placeholder="宗地内现状">${basicEstateLandState.currentSituation}</textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>

                                <div class="x_content">
                                    <%@include file="/views/project/stageSurvey/common/estateNetwork.jsp" %>
                                    <%@include file="/views/project/stageSurvey/common/estateParking.jsp" %>
                                    <%@include file="/views/project/stageSurvey/common/matchingEnvironment.jsp" %>
                                    <%@include file="/views/project/stageSurvey/common/matchingFinance.jsp" %>
                                    <c:if test="${formType eq 'residence'}">
                                        <%@include file="/views/project/stageSurvey/common/matchingEducation.jsp" %>
                                        <%@include file="/views/project/stageSurvey/common/matchingRecreation.jsp" %>
                                        <%@include file="/views/project/stageSurvey/common/matchingRestaurant.jsp" %>
                                        <%@include file="/views/project/stageSurvey/common/matchingMarket.jsp" %>
                                        <%@include file="/views/project/stageSurvey/common/matchingMedical.jsp" %>
                                    </c:if>
                                    <%@include file="/views/project/stageSurvey/common/matchingTransit.jsp" %>
                                    <%@include file="/views/project/stageSurvey/common/matchingTrafficHub.jsp" %>
                                    <%@include file="/views/project/stageSurvey/common/matchingMetro.jsp" %>
                                    <%@include file="/views/project/stageSurvey/common/matchingMainRoad.jsp" %>
                                    <%@include file="/views/project/stageSurvey/common/matchingMainConversion.jsp" %>
                                    <c:if test="${formType eq 'industry'}">
                                        <%@include file="/views/project/stageSurvey/common/matchingMaterial.jsp" %>
                                    </c:if>
                                </div>
                                <script type="text/javascript"
                                        src="${pageContext.request.contextPath}/js/examine/examine.estate.js?v=${assessVersion}"></script>
                                <script type="text/javascript"
                                        src="${pageContext.request.contextPath}/js/examine/sonEstateView.js?v=${assessVersion}"></script>
                                <script src="${pageContext.request.contextPath}/js/select/land.level.select.js?v=${assessVersion}"></script>
                                <script src="${pageContext.request.contextPath}/js/select/block.select.js?v=${assessVersion}"></script>
                            </div>
                        </div>
                    </div>
                    <%@include file="/views/project/stageSurvey/common/canvasQRcode.jsp" %>
                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">

                        <div class="card-body">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                关闭
                            </button>
                            <button class="btn btn-warning" onclick="saveDataInfo();">
                                保存<i style="margin-left: 10px" class="fa fa-save"></i>
                            </button>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>
<%@include file="/views/project/stageSurvey/common/applyInfoHistory.jsp" %>
<%@include file="/views/project/stageSurvey/common/applyInfoQuote.jsp" %>
<%@include file="/views/project/stageSurvey/common/estateLandCategoryInfo.jsp" %>
<%@include file="/views/project/stageSurvey/common/estateStreetInfo.jsp" %>
<%@include file="/views/project/stageSurvey/common/estateVillage.jsp" %>

<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.min.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<!-- 高德抓取周边数据 -->
<script src="${pageContext.request.contextPath}/js/select/selectMap/transit.checkbox.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/metro.checkbox.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/finance.checkbox.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/education.checkbox.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/recreation.checkbox.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/restaurant.checkbox.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/market.checkbox.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/medical.checkbox.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/trafficHub.checkbox.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/js/select/selectMap/distance.get.fun.js?v=${assessVersion}"></script>
<script src="${pageContext.request.contextPath}/js/map.placeSearch.js?v=${assessVersion}"></script>
</html>
<script type="text/javascript">
    //保存数据信息
    function saveDataInfo() {
        Loading.progressShow();
        var formData =examineCommon.getFormData();

        formData.landUseTypeCategory = [];
        var forms = $("#LandUseTypeContent").find("form");
        //校验
        for (var i = 0; i < forms.size(); i++) {
            if (!$(forms[i]).valid()) {
                return false;
            }
        }

        $.each(forms, function (i, n) {
            var typeCategoryData = formParams($(n).attr("id"));
            typeCategoryData.landId = estateCommon.estateLandStateForm.find('input[name="id"]').val();
            formData.landUseTypeCategory.push(typeCategoryData);

        });
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/saveDraft",
            type: "post",
            dataType: "json",
            async: false,
            data: {
                formData: JSON.stringify(formData),
                formClassify: '${tbType}',
                planDetailsId: '${planDetailsId}'
            },
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "保存数据成功", function () {
                        window.close();
                    });
                } else {
                    AlertError("保存失败,失败原因:" + result.errmsg);
                }
            }
        });
    }

    //打开历史数据modal
    function showHistoryModal() {
        historyInfo.caseEstate.showModel('${tbId}', '${formClassify}', '${tbType}', '${basicApplyBatch.id}');
    };

    function showCaseQuoteModal() {
        caseFun.caseEstate.showModel();
    }
    function showProjectQuoteModal() {
        projectData.prototype.showModel();
    }
</script>
<script type="text/javascript">
    $(function () {
        estateCommon.initById('${basicEstate.id}', '${tbType}');
    })
</script>

