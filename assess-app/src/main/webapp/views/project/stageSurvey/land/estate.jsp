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
                                                        <tbody>

                                                        </tbody>
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
                                                        <span class="input-group-btn">
                        <div onclick="estateCommon.mapMarker();" class="btn btn-info"><i
                                class="fa fa-map-marker"></i> 标注</div>
                        </span>
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
                                                <label class="col-sm-1">占地面积(平方米)</label>
                                                <div class="col-sm-3">
                                                    <input type="text" data-rule-maxlength="100"
                                                           data-rule-number='true'
                                                           placeholder="占地面积(请输入数字)" name="coverAnArea"
                                                           class="form-control input-full"
                                                           value="${basicEstate.coverAnArea}">
                                                </div>
                                                <label class="col-sm-1">土地权利人</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="土地权利人" name="developerName"
                                                           class="form-control input-full"
                                                           value="${basicEstate.developerName}">
                                                </div>

                                                <label class="col-sm-1 control-label">均价</label>
                                                <div class="col-sm-3">
                                                    <input type="text" data-rule-maxlength="100" data-rule-number='true'
                                                           placeholder="均价(请输入数字)" name="averagePrice"
                                                           class="form-control input-full"
                                                           value="${basicEstate.averagePrice}">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">价格区间</label>
                                                <div class="col-sm-3">
                                                    <input type="text" placeholder="价格区间"
                                                           name="priceRange" class="form-control input-full"
                                                           value="${basicEstate.priceRange}">
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
                                        <div class="card-category">基础设施情况</div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1">基础设施完备度<span
                                                        class="symbol required"></span></label>
                                                <div class="col-sm-3">
                                                    <select class="form-control input-full search-select select2 "
                                                            name="infrastructureCompleteness"
                                                            required="required">
                                                    </select>
                                                </div>
                                                <div class="col-sm-8">
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
                                                <label class="col-sm-1 control-label">东至</label>
                                                <div class="col-sm-3">
                                                    <input type="text" class="form-control input-full" placeholder="东至"
                                                           name="eastTo" value="${basicEstateLandState.eastTo}">
                                                </div>
                                                <label class="col-sm-1 control-label">南至</label>
                                                <div class="col-sm-3">
                                                    <input type="text" class="form-control input-full" placeholder="南至"
                                                           name="southTo" value="${basicEstateLandState.southTo}">
                                                </div>
                                                <label class="col-sm-1 control-label">西至</label>
                                                <div class="col-sm-3">
                                                    <input type="text" class="form-control input-full" placeholder="西至"
                                                           name="westTo" value="${basicEstateLandState.westTo}">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 control-label">北至</label>
                                                <div class="col-sm-3">
                                                    <input type="text" class="form-control input-full" placeholder="北至"
                                                           name="northTo" value="${basicEstateLandState.northTo}">
                                                </div>
                                                <label class="col-sm-1 control-label">土地面积</label>
                                                <div class="col-sm-3">
                                                    <input type="text" class="form-control input-full"
                                                           data-rule-number='true'
                                                           placeholder="土地面积(请输入数字)" name="landArea"
                                                           value="${basicEstateLandState.landArea}">
                                                </div>
                                                <label class="col-sm-1 control-label">地形</label>
                                                <div class="col-sm-3">
                                                    <select class="form-control input-full search-select select2 planeness"
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
                                                    <select class="form-control input-full search-select select2 topographicTerrain"
                                                            name="topographicTerrain">
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                                    土地用途类型<span class="symbol required"></span>
                                                </label>
                                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                                    <div class="input-group">
                                                        <input type="text" name="landUseType" class="form-control"
                                                               list="landUseTypeList"
                                                               value="${basicEstateLandState.landUseType}">
                                                        <datalist id="landUseTypeList">

                                                        </datalist>
                                                        <div class="input-group-prepend">
                                                            <button class="btn btn-warning btn-sm "
                                                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                    type="button"
                                                                    onclick="$(this).closest('.input-group').find('input').val('');">
                                                                清空
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    土地用途类别<span class="symbol required"></span>
                                                </label>
                                                <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                                    <div class="input-group">
                                                        <input type="text" name="landUseCategory"
                                                               list="landUseCategoryList"
                                                               value="${basicEstateLandState.landUseCategory}"
                                                               class="form-control">
                                                        <datalist id="landUseCategoryList">

                                                        </datalist>
                                                        <div class="input-group-prepend">
                                                            <button class="btn btn-warning btn-sm "
                                                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                    type="button"
                                                                    onclick="$(this).closest('.input-group').find('input').val('');">
                                                                清空
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                </form>

                                <%@include file="/views/project/stageSurvey/common/houseTradingBodyBox.jsp" %>
                                <%@include file="/views/project/tool/landLevelModalView.jsp" %>

                                <script type="text/javascript"
                                        src="${pageContext.request.contextPath}/js/examine/examine.estate.js?v=${assessVersion}"></script>
                                <script type="text/javascript"
                                        src="${pageContext.request.contextPath}/js/examine/examine.house.js?v=${assessVersion}"></script>
                                <script type="text/javascript"
                                        src="${pageContext.request.contextPath}/js/examine/sonEstateView.js?v=${assessVersion}"></script>
                                <script type="text/javascript"
                                        src="${pageContext.request.contextPath}/js/examine/sonHouseView.js?v=${assessVersion}"></script>
                                <script src="${pageContext.request.contextPath}/js/select/land.level.select.js?v=${assessVersion}"></script>
                                <script src="${pageContext.request.contextPath}/js/select/block.select.js?v=${assessVersion}"></script>
                            </div>
                        </div>
                    </div>

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

</html>
<script type="text/javascript">
    //保存数据信息
    function saveDataInfo() {
        Loading.progressShow();
        var formData = JSON.stringify(examineCommon.getFormData());
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/saveDraft",
            type: "post",
            dataType: "json",
            async: false,
            data: {
                formData: formData,
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
        //打开楼盘modal
        historyInfo.caseEstate.showModel('${tbId}', '${formClassify}', '${tbType}', '${basicApplyBatch.id}');


    };

    function showCaseQuoteModal() {
        //打开楼盘modal
        if ("estate" == "${tbType}") {
            caseFun.caseEstate.showModel();
        }
    }
</script>
<script type="text/javascript">
    $(function () {
        estateCommon.initById('${basicEstate.id}','${tbType}');
    })
</script>

