<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div id="treeLandLevelDetailListModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width: 1000px;height:650px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">基准地价详情
                </h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <div class="row">
                            <div class="panel-body panel">

                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <ul id="treeLandLevelDetail" class="ztree"></ul>
                                </div>
                                <div class=" col-xs-9  col-sm-9  col-md-9  col-lg-9 ">
                                    <div class="row">
                                        <form class="form-horizontal">
                                            <div class="form-group" style="margin-bottom: 30px;">
                                                <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11 col-lg-offset-1">
                                                    <a class="btn btn-xs btn-primary" onclick="landLevel.treeExpandAll(true);">
                                                        全部展开
                                                    </a>
                                                    <a class="btn btn-xs btn-primary" onclick="landLevel.treeExpandAll(false);">
                                                        全部收起
                                                    </a>
                                                    <a class="btn btn-xs btn-primary " onclick="landLevel.treeRefresh();">
                                                        刷新
                                                    </a>
                                                    <a class="btn btn-xs btn-primary " onclick="landLevel.showDataAllocationCorrectionCoefficientVolumeRatioDetail();">
                                                        容积率系数
                                                    </a>
                                                    <a class="btn btn-xs btn-primary " onclick="landLevel.showDataLandDetailAchievementDetail();">
                                                        土地因素
                                                    </a>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="x-valid">
                                                    <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">平方价</label>
                                                    <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                                        <label class=" form-control" name="price"></label>
                                                    </div>
                                                </div>
                                                <div class="x-valid">
                                                    <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">每亩单价</label>
                                                    <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                                        <label class=" form-control" name="muPrice"></label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="x-valid">
                                                    <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">标准容积率</label>
                                                    <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                                        <label class=" form-control" name="volumeRate"></label>
                                                    </div>
                                                </div>
                                                <div class="x-valid">
                                                    <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">法定使用年限</label>
                                                    <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                                        <label class=" form-control" name="legalAge"></label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="x-valid">
                                                    <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">征地比例</label>
                                                    <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                                        <label class=" form-control" name="landAcquisitionProportion"></label>
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
    </div>
</div>

<div id="land_level_detail_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">基准地价内容</h3>
            </div>
            <form class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="landLevelId">
                <input type="hidden" name="pid">

                <!-- 用作树 append -->
                <input type="hidden" name="tId">

                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">大类
                                            <span class="symbol required"></span></label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <select name="classify" required="required"
                                                    class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">类型<span
                                                class="symbol required"></span></label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <select name="type" required="required"
                                                    class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <%--<div class="x-valid">--%>
                                    <%--<label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">类别</label>--%>
                                    <%--<div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">--%>
                                    <%--<input type="text" class="form-control" name="category" placeholder="类别">--%>
                                    <%--</div>--%>
                                    <%--</div>--%>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">平方价</label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input type="text" data-rule-number='true' class="form-control" name="price"
                                                   placeholder="平方价">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">每亩单价</label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input type="text" data-rule-number='true' class="form-control"
                                                   name="muPrice"
                                                   placeholder="每亩单价">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">容积率</label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input type="text" data-rule-number='true' class="form-control"
                                                   name="volumeRate"
                                                   placeholder="容积率">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">法定使用年限</label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input type="text" data-rule-number='true' class="form-control"
                                                   name="legalAge"
                                                   placeholder="法定使用年限">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">征地比例</label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input type="text" class="form-control" name="landAcquisitionProportion"
                                                   placeholder="征地比例">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            级别范围
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <textarea class="form-control" name="levelRange"
                                                      placeholder="级别范围"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            主要街道
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <textarea class="form-control" name="mainStreet"
                                                      placeholder="主要街道"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="landLevel.saveLandLevelDetail()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="achievementBoxDetail" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">基准地价因素列表</h3>
                <input type="hidden" name="levelDetailId">
            </div>
            <div class="modal-body">
                <table class="table table-bordered" id="achievementTable">
                    <!-- cerare document add ajax data-->
                </table>
            </div>
        </div>
    </div>
</div>


<div id="dataAllocationCorrectionCoefficientVolumeRatioDetailTableBox" class="modal fade bs-example-modal-lg"
     data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">容积率修正系数配置 详情</h3>
                <input type="hidden" name="levelDetailId">
            </div>
            <div class="modal-body">
                <table class="table table-bordered" id="dataAllocationCorrectionCoefficientVolumeRatioDetailTable">
                </table>
            </div>
        </div>
    </div>
</div>

