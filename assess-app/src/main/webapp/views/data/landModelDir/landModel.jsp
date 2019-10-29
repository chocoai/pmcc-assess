
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
                <input type="hidden" id="id" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">省
                                            <span class="symbol required"></span></label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <select name="province"
                                                    class="form-control search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">市<span
                                                class="symbol required"></span></label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <select name="city" class="form-control search-select select2"
                                                    required="required">

                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">县</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <select name="district"
                                                    class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">乡镇名称</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <input placeholder="乡镇名称" class="form-control" name="townShipName"
                                                   type="text">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">权利类型</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <select name="landRightType"
                                                    class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">估价期日</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <input type="text" readonly="readonly"
                                                   class="form-control date-picker dbdate" data-date-format="yyyy-mm-dd"
                                                   name="valuationDate" placeholder="估价期日">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">发布日期</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <input type="text" readonly="readonly"
                                                   class="form-control date-picker dbdate" data-date-format="yyyy-mm-dd"
                                                   name="releaseDate" placeholder="发布日期">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">执行时间</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <input type="text" readonly="readonly"
                                                   class="form-control date-picker dbdate" data-date-format="yyyy-mm-dd"
                                                   name="executionTime" placeholder="执行时间">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">标题</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <input placeholder="标题" class="form-control" name="title" type="text">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">文号</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <input placeholder="文号" class="form-control" name="wordSymbol" type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            基准地价定义
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <div style="width:99%;height:200px;" id="landDefinition"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附件</label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input id="uploadFile" placeholder="上传附件" class="form-control" type="file">
                                            <div id="_uploadFile"></div>
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
                    <button type="button" class="btn btn-primary" onclick="landLevel.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="land_level_detail_list_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土地级别内容</h3>
            </div>
            <div class="modal-body">
                <div type="button" class="btn btn-success"
                     onclick="landLevel.addLandLevelDetail()"
                     data-toggle="modal"> 新增
                </div>
                <table class="table table-bordered" id="land_level_detail_list">
                    <!-- cerare document add ajax data-->
                </table>
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
                                            <input type="text" data-rule-number='true' class="form-control" name="muPrice"
                                                   placeholder="每亩单价">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">容积率</label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input type="text" data-rule-number='true' class="form-control" name="volumeRate"
                                                   placeholder="容积率">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">法定使用年限</label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <input type="text" data-rule-number='true' class="form-control" name="legalAge"
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