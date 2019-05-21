<%--
 电力通讯网络
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>电力通讯网络</h4>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="houseIntelligent.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="HouseIntelligentList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div id="divBoxHouseIntelligent" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">电力通讯网络</h3>
            </div>
            <form id="frmHouseIntelligent" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            开关回路<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <select required="required" name="switchCircuit"
                                                    class="form-control search-select select2 switchCircuit">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            铺设方式<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <select required="required" name="layingMethod"
                                                    class="form-control search-select select2 layingMethod">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            灯具
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <select name="lampsLanterns" multiple="multiple"
                                                    class="form-control search-select select2 lampsLanterns">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            档次
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <select name="grade"
                                                    class="form-control search-select select2 grade">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            智能系统<span class="symbol required"></span>
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <a href="javascript://;" class="btn btn-xs btn-success"
                                                    onclick="houseIntelligent.prototype.appendHTML('',this)"><i
                                                    class="fa fa-plus"></i></a>
                                        </div>
                                    </div>
                                </div>
                                <div style="margin-bottom: 8px;" class="system">
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                                智能系统
                                            </label>
                                            <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                                <select required="required" name="intelligentSystem1"
                                                        class="form-control search-select select2 intelligentSystem1">
                                                </select>
                                            </div>
                                            <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                                <select required="required" name="layingMethod1"
                                                        class="form-control search-select select2 layingMethod1">
                                                </select>
                                            </div>
                                            <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                                <select required="required" name="intelligenceGrade1"
                                                        class="form-control search-select select2 intelligenceGrade1">
                                                </select>
                                            </div>
                                            <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                                <input type="button" class="btn btn-warning" value="X"
                                                       onclick="houseIntelligent.prototype.cleanHTMLData(this)">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                            描述
                                        </label>
                                        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                            <textarea name="remark" class="form-control"></textarea>
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
                    <button type="button" class="btn btn-primary" onclick="houseIntelligent.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
