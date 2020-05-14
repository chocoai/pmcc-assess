<%--
 电力通讯网络
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    电力通讯网络
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body" style="display: none">
            <form class="form-horizontal">
                <p>
                    <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                            data-toggle="modal" onclick="houseIntelligent.prototype.showModel()"
                            href="#divBoxHouseIntelligent">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                        新增
                    </button>
                </p>
                <table class="table table-bordered" id="HouseIntelligentList">
                    <!-- cerare document add ajax data-->
                </table>
            </form>
        </div>
    </div>
</div>

<div id="divBoxHouseIntelligent" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">电力通讯网络</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmHouseIntelligent" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            开关回路<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="switchCircuit"
                                                    class="form-control input-full  switchCircuit">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            铺设方式<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="layingMethod"
                                                    class="form-control input-full  layingMethod">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            灯具
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="lampsLanterns" multiple="multiple"
                                                    class="form-control input-full  lampsLanterns">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            档次
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="grade"
                                                    class="form-control input-full  grade">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            智能系统<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <a href="javascript://;" class="btn btn-sm btn-success"
                                               onclick="houseIntelligent.prototype.appendHTML('',this)"><i
                                                    class="fa fa-plus"></i></a>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div style="margin-bottom: 8px;" class="system">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                智能系统
                                            </label>
                                            <div class="col-sm-2">
                                                <select required="required" name="intelligentSystem1"
                                                        class="form-control input-full  intelligentSystem1">
                                                </select>
                                            </div>
                                            <div class="col-sm-2">
                                                <select required="required" name="layingMethod1"
                                                        class="form-control input-full  layingMethod1">
                                                </select>
                                            </div>
                                            <div class="col-sm-2">
                                                <select required="required" name="intelligenceGrade1"
                                                        class="form-control input-full  intelligenceGrade1">
                                                </select>
                                            </div>
                                            <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                                <input type="button" class="btn btn-warning" value="X"
                                                       onclick="houseIntelligent.prototype.cleanHTMLData(this)">
                                            </div>
                                        </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            描述
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea name="remark" class="form-control input-full"></textarea>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="houseIntelligent.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


