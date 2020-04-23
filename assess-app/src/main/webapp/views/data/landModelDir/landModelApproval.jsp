<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div id="treeLandLevelDetailListModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
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
                            <button  style="margin-left: 5px" type="button" class="btn btn-sm btn-primary " onclick="landLevel.showDataAllocationCorrectionCoefficientVolumeRatioDetail();">
                                容积率系数
                            </button>
                            <button  style="margin-left: 5px" type="button" class="btn btn-sm btn-primary " onclick="landLevel.showDataLandDetailAchievementDetail();">
                                土地因素
                            </button>
                            <ul id="treeLandLevelDetail" class="ztree"></ul>
                        </div>
                        <div class=" col-xs-9  col-sm-9  col-md-9  col-lg-9 ">
                            <div class="card-body">
                                <form class="form-horizontal">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    平方价
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class=" form-control input-full" name="price"></label>
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    每亩单价
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class=" form-control input-full" name="muPrice"></label>
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
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-1 col-form-label">
                                                    法定使用年限
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class=" form-control input-full" name="legalAge"></label>
                                                </div>
                                                <label class="col-sm-1 col-form-label">
                                                    征地比例
                                                </label>
                                                <div class="col-sm-3">
                                                    <label class=" form-control input-full"
                                                           name="landAcquisitionProportion"></label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="achievementBoxDetail" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">基准地价因素列表</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card-body">
                            <table class="table table-bordered" id="achievementTable"></table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">关闭</button>
            </div>
        </div>
    </div>
</div>

<div id="dataAllocationCorrectionCoefficientVolumeRatioDetailTableBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">容积率修正系数</h4>
                <input type="hidden" name="levelDetailId">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card-body">
                            <table class="table table-bordered" id="dataAllocationCorrectionCoefficientVolumeRatioDetailTable">
                            </table>

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

