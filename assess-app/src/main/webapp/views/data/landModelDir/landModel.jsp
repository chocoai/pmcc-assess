<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">基准地价区域</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <%@include file="landLevelBase.jsp" %>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="landLevel.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>
<div id="treeLandLevelDetailListModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 85%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">基准地价详情</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                    <div class="row">
                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                            <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                    onclick="landLevel.addLandLevelDetail()">
                                新增
                            </button>
                            <button style="margin-left: 5px" class="btn btn-primary btn-sm" type="button"
                                    onclick="zTreeOnEdit()">
                                编辑
                            </button>
                            <button style="margin-left: 5px" class="btn btn-warning btn-sm" type="button"
                                    data-permission="permission"
                                    onclick="zTreeOnRemove()">
                                删除
                            </button>
                            <button type="button" style="margin-left: 5px" class="btn btn-sm btn-primary "
                                    onclick="landLevel.treeRefresh();">
                                刷新
                            </button>
                            <button type="button" style="margin-left: 5px" class="btn btn-sm btn-primary "
                                    onclick="landLevel.treeCheckAllNodes(this);">
                                全(全不)选
                            </button>
                            <button style="margin-left: 5px" class="btn btn-warning btn-sm" type="button"
                                    data-permission="permission"
                                    onclick="landLevel.clearNodeChild(this);">
                                清除子数据
                            </button>
                            <button type="button" class="btn btn-info btn-sm"
                                    style="margin-left: 5px"
                                    onclick="landLevel.importLandLevelDetail(true);">
                                                    <span class="btn-label">
                                                        <i class="fa fa-cloud-upload-alt"></i>
                                                    </span>
                            </button>
                            <button type="button" class="btn btn-success btn-sm"
                                    style="margin-left: 5px"
                                    onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftpLandLevelDetailBaseTemplate);">
                                                    <span class="btn-label">
                                                        <i class="fa fa-cloud-download-alt"></i>
                                                    </span>
                            </button>

                            <ul id="treeLandLevelDetail" class="ztree" style="max-height: 260px;overflow: auto;"></ul>
                        </div>
                        <div class=" col-xs-9  col-sm-9  col-md-9  col-lg-9 ">
                            <form class="form-horizontal">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <div class="col-sm-6">
                                                <button type="button" class="btn btn-primary btn-sm"
                                                        onclick="landLevel.showDataLandDetailAchievementDetail();">
                                                    土地因素
                                                </button>
                                                <button type="button" class="btn btn-info btn-sm"
                                                        style="margin-left: 5px"
                                                        onclick="landLevel.importDataLandDetailAchievement(true);">
                                                    <span class="btn-label">
                                                        <i class="fa fa-cloud-upload-alt"></i>
                                                    </span>
                                                </button>
                                                <button type="button" class="btn btn-success btn-sm"
                                                        style="margin-left: 5px"
                                                        onclick="landLevel.downloadDataLandDetailAchievementFile(this);">
                                                    <span class="btn-label">
                                                        <i class="fa fa-cloud-download-alt"></i>
                                                    </span>
                                                </button>
                                            </div>
                                            <div class="col-sm-6">
                                                <button type="button" class="btn btn-primary btn-sm"
                                                        onclick="landLevel.showDataAllocationCorrectionCoefficientVolumeRatioDetail();">
                                                    容积率系数
                                                </button>
                                                <button type="button" class="btn btn-info btn-sm"
                                                        style="margin-left: 5px"
                                                        onclick="landLevel.importDataAllocationCorrectionCoefficientVolumeRatio(true);">
                                                    <span class="btn-label">
                                                        <i class="fa fa-cloud-upload-alt"></i>
                                                    </span>
                                                </button>
                                                <button type="button" class="btn btn-success btn-sm"
                                                        style="margin-left: 5px"
                                                        onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftpLandLevelDetailBaseCoefficientTemplate);">
                                                    <span class="btn-label">
                                                        <i class="fa fa-cloud-download-alt"></i>
                                                    </span>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 col-form-label">
                                                平方价(元/m²)
                                            </label>
                                            <div class="col-sm-3">
                                                <label class=" form-control input-full" name="price"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                每亩单价(万元/亩)
                                            </label>
                                            <div class="col-sm-3">
                                                <label class=" form-control input-full" name="muPrice"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                楼面地价(元/m²)
                                            </label>
                                            <div class="col-sm-3">
                                                <label class=" form-control input-full" name="floorPrice"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 col-form-label">
                                                征地比例
                                            </label>
                                            <div class="col-sm-3">
                                                <label class=" form-control input-full"
                                                       name="landAcquisitionProportion"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                法定使用年限
                                            </label>
                                            <div class="col-sm-3">
                                                <label class=" form-control input-full" name="legalAge"></label>
                                            </div>
                                            <label class="col-sm-1 col-form-label">
                                                标准容积率
                                            </label>
                                            <div class="col-sm-3">
                                                <label class=" form-control input-full" name="volumeRate"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>


<div id="land_level_detail_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">土地级别</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="landLevelId">
                    <input type="hidden" name="pid">
                    <!-- 用作树 append -->
                    <input type="hidden" name="tId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <div class="input-group">
                                                    <input type="text" name="name" class="form-control form-control-sm"
                                                           list="LandLevelDetailNameList"
                                                           required>
                                                    <datalist id="LandLevelDetailNameList"></datalist>
                                                    <div class="input-group-prepend ">
                                                        <button class="btn btn-warning btn-sm"
                                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                type="button"
                                                                onclick="$(this).closest('.input-group').find('input').val('');">
                                                            清空
                                                            <i class="fa "></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                楼面地价(元/m²)
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" data-rule-number='true'
                                                       class="form-control input-full"
                                                       name="floorPrice"
                                                       placeholder="楼面地价(元/m²)">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                平方价(元/m²)
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" data-rule-number='true'
                                                       class="form-control input-full"
                                                       name="price"
                                                       placeholder="平方价(元/m²)">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                每亩单价(万元/亩)
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" data-rule-number='true'
                                                       class="form-control input-full"
                                                       name="muPrice"
                                                       placeholder="每亩单价(万元/亩)">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                标准容积率
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" data-rule-number='true'
                                                       class="form-control input-full"
                                                       name="volumeRate"
                                                       placeholder="标准容积率">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                法定使用年限
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" data-rule-number='true'
                                                       class="form-control input-full"
                                                       name="legalAge"
                                                       placeholder="法定使用年限">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                征地比例
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full"
                                                       name="landAcquisitionProportion"
                                                       placeholder="征地比例">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                级别范围
                                            </label>
                                            <div class="col-sm-11">
                                                  <textarea class="form-control input-full" name="levelRange"
                                                            placeholder="级别范围"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                主要街道
                                            </label>
                                            <div class="col-sm-11">
                                                  <textarea class="form-control input-full" name="mainStreet"
                                                            placeholder="主要街道"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="landLevel.saveLandLevelDetail()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
<div id="achievementBoxDetail" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">土地级别因素</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <input type="hidden" name="levelDetailId">
                <form class="form-horizontal">
                    <div class="row form-group">
                        <div class="col-md-7">
                            <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button" onclick="landLevel.showDataLandDetailAchievement(this);">
                                <span class="btn-label"><i class="fa fa-plus"></i></span>新增
                            </button>

                            <button style="margin-left: 5px" class="btn btn-warning btn-sm" type="button" onclick="landLevel.deleteDataLandDetailAchievementBatch()">
                                <span class="btn-label"><i class="fa fa-minus"></i></span>删除
                            </button>
                        </div>
                    </div>
                </form>
                <table class="table table-bordered" id="achievementTable"></table>
            </div>
        </div>
    </div>
</div>
<div id="achievementBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">土地级别因素</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form id="achievementFrm" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="levelDetailId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                因素类型<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="type" required
                                                        class="form-control input-full  type">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                因素一级
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text"  class="form-control input-full  "
                                                       name="classification">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                因素二级
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text"  name="category"
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                等级<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="grade" required
                                                        class="form-control input-full search-select select2 grade">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                分值<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" required="required"
                                                       class="form-control input-full x-percent"
                                                       name="achievement"
                                                       placeholder="分值">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                说明
                                            </label>
                                            <div class="col-sm-11">
                                              <textarea class="form-control input-full" name="reamark"
                                                        placeholder="说明"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm"
                        onclick="landLevel.saveDataLandDetailAchievement()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


<div id="dataAllocationCorrectionCoefficientVolumeRatioDetailTableBox" class="modal fade bs-example-modal-lg"
     data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">容积率修正系数</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <input type="hidden" name="levelDetailId">
                        <div class="card-body">
                            <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                    data-toggle="modal"
                                    onclick="landLevel.showDataAllocationCorrectionCoefficientVolumeRatioDetailBox()"
                                    href="#dataAllocationCorrectionCoefficientVolumeRatioDetailBox">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                新增
                            </button>

                            <button style="margin-left: 5px" class="btn btn-warning btn-sm" type="button" onclick="landLevel.deleteCorrectionCoefficientVolumeRatioBatch()">
                                <span class="btn-label"><i class="fa fa-minus"></i></span>删除
                            </button>


                            <table class="table table-bordered"
                                   id="dataAllocationCorrectionCoefficientVolumeRatioDetailTable">
                                <!-- cerare document add ajax data-->
                            </table>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


<div id="dataAllocationCorrectionCoefficientVolumeRatioDetailBox" class="modal fade bs-example-modal-lg"
     data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">容积率修正系数</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="dataAllocationCorrectionCoefficientVolumeRatioDetailFrm" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="levelDetailId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                容积率<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" required data-rule-number='true'
                                                       class="form-control input-full"
                                                       name="plotRatio" placeholder="容积率">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                修正系数<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" required data-rule-number='true'
                                                       class="form-control input-full"
                                                       name="correctionFactor" placeholder="修正系数">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm"
                        onclick="landLevel.saveDataAllocationCorrectionCoefficientVolumeRatioDetail()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


