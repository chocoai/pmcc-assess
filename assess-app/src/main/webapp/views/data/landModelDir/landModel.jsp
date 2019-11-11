<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土地级别区域</h3>
            </div>
            <form class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <%@include file="landLevelBase.jsp" %>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="landLevel.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>


<div id="treeLandLevelDetailListModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width: 1000px;height:650px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土地级别详情
                    <button type="button" class="btn btn-success"
                            onclick="landLevel.addLandLevelDetail({pid:0})" data-toggle="modal"> 新增
                    </button>
                </h3>
                <label class="label label-warning">此按钮添加到第一级</label>
            </div>
            <div class="modal-body">
                <div class="row">

                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <div class="row">
                            <div class="panel-body panel">
                                <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                    <ul id="treeLandLevelDetail" class="ztree"></ul>
                                </div>
                                <div class=" col-xs-8  col-sm-8  col-md-8  col-lg-8 ">
                                    <div class="row">
                                        <form>
                                            <div class="form-group">
                                                <div class="x-valid">
                                                    <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">平方价</label>
                                                    <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                                        <label class=" form-control"></label>
                                                    </div>
                                                </div>
                                                <div class="x-valid">
                                                    <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">每亩单价</label>
                                                    <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                                        <label class=" form-control"></label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="x-valid">
                                                    <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">标准容积率</label>
                                                    <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                                        <label class=" form-control"></label>
                                                    </div>
                                                </div>
                                                <div class="x-valid">
                                                    <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">法定使用年限</label>
                                                    <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                                        <label class=" form-control"></label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="x-valid">
                                                    <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">标准容积率</label>
                                                    <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                                        <label class=" form-control"></label>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="col-md-12" id="btnGroup">
                                        <a class="btn btn-xs btn-success" >
                                            新增
                                        </a>
                                        <a class="btn btn-xs btn-primary">
                                            编辑
                                        </a>
                                        <a class="btn btn-xs btn-warning" >
                                            删除
                                        </a>
                                        <a class="btn btn-xs btn-primary">
                                            全部展开
                                        </a>
                                        <a class="btn btn-xs btn-primary">
                                            全部收起
                                        </a>
                                        <a class="btn btn-xs btn-primary ">
                                            土地因素
                                        </a>
                                        <a class="btn btn-xs btn-primary ">
                                            容积率系数
                                        </a>
                                        <a class="btn btn-xs btn-primary ">
                                            导入模板下载
                                        </a>
                                        <a class="btn btn-xs btn-primary ">
                                            导入模板上传
                                        </a>
                                    </div>
                                    <div class="row">
                                        <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6  pull-left">
                                            <button type="button" class="btn btn-default"
                                                    onclick="landLevel.treeExpandAll(true);">展开
                                            </button>
                                            <button type="button" class="btn btn-default"
                                                    onclick="landLevel.treeExpandAll(false);">收缩
                                            </button>
                                            <button type="button" class="btn btn-default"
                                                    onclick="landLevel.treeRefresh();">刷新
                                            </button>
                                        </div>

                                        <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6  pull-right">

                                            <button type="button" class="btn-default btn"
                                                    onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftpLandLevelDetailBaseTemplate);">
                                                土地级别excel模板
                                                <span class="fa-stack fa-lg">
                                                              <i class="fa fa-square-o fa-stack-2x"></i>
                                                              <i class="fa fa fa-cloud-download fa-stack-1x"></i>
                                                                </span>
                                            </button>
                                            <button class="btn-default btn" type="button"
                                                    onclick="landLevel.importLandLevelDetail(true);">
                                                土地级别excel模板
                                                <span class="fa-stack fa-lg">
                                                              <i class="fa fa-square-o fa-stack-2x"></i>
                                                              <i class="fa fa fa fa-cloud-upload fa-stack-1x"></i>
                                                                </span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <label class="label label-warning">请在左边树上点击需要导入的节点然后再按此按钮导入excel数据,注意当不选择的时候导入的数据将是第一层级</label>
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
                <h3 class="modal-title">土地级别内容</h3>
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
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <select name="classify" required="required"
                                                    class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">类型</label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <select name="type" class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">平方价</label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <input type="text" data-rule-number='true' class="form-control" name="price"
                                                   placeholder="平方价">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">每亩单价</label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <input type="text" data-rule-number='true' class="form-control"
                                                   name="muPrice"
                                                   placeholder="每亩单价">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">标准容积率</label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <input type="text" data-rule-number='true' class="form-control"
                                                   name="volumeRate"
                                                   placeholder="标准容积率">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">法定使用年限</label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <input type="text" data-rule-number='true' class="form-control"
                                                   name="legalAge"
                                                   placeholder="法定使用年限">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">征地比例</label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
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
                <h3 class="modal-title">土地级别因素列表</h3>
                <input type="hidden" name="levelDetailId">
            </div>
            <div class="modal-body">
                <div type="button" class="btn btn-success"
                     onclick="landLevel.showDataLandDetailAchievement()"
                     data-toggle="modal" href="#divBox"> 新增
                </div>
                <table class="table table-bordered" id="achievementTable">
                    <!-- cerare document add ajax data-->
                </table>
            </div>
        </div>
    </div>
</div>

<div id="achievementBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土地级别因素</h3>
            </div>
            <form id="achievementFrm" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="levelDetailId">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">类型<span
                                                class="symbol required"></span></label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <select name="type" required
                                                    class="form-control search-select select2 type">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">类别<span
                                                class="symbol required"></span></label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <select name="category" required
                                                    class="form-control search-select select2 category">
                                                <option>请先选择类型</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">等级<span
                                                class="symbol required"></span></label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <select name="grade" required
                                                    class="form-control search-select select2 grade">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">分值<span
                                                class="symbol required"></span></label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input type="text" required="required" class="form-control x-percent"
                                                   name="achievement"
                                                   placeholder="分值">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">说明</label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <textarea class="form-control" name="reamark"
                                                      placeholder="说明"></textarea>
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
                    <button type="button" class="btn btn-primary" onclick="landLevel.saveDataLandDetailAchievement()">
                        保存
                    </button>
                </div>
            </form>
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
                <input type="hidden" name="allocationVolumeRatioId">
            </div>
            <div class="modal-body">
                <div type="button" class="btn btn-success"
                     onclick="landLevel.showDataAllocationCorrectionCoefficientVolumeRatioDetailBox()"
                     data-toggle="modal" href="#dataAllocationCorrectionCoefficientVolumeRatioDetailBox"> 新增
                </div>
                <table class="table table-bordered" id="dataAllocationCorrectionCoefficientVolumeRatioDetailTable">
                </table>
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
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">容积率修正系数配置 详情</h3>
            </div>
            <form id="dataAllocationCorrectionCoefficientVolumeRatioDetailFrm" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="allocationVolumeRatioId">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            容积率<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" required data-rule-number='true'
                                                   class="form-control"
                                                   name="plotRatio" placeholder="容积率">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            修正系数<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" required data-rule-number='true'
                                                   class="form-control"
                                                   name="correctionFactor" placeholder="修正系数">
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
                    <button type="button" class="btn btn-primary"
                            onclick="landLevel.saveDataAllocationCorrectionCoefficientVolumeRatioDetail()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>