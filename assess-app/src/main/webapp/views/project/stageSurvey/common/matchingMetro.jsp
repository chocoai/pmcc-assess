<%--
  地铁
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12"  tab-role="method">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    地铁信息
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
                <div class="row form-group form-inline">
                    <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                            data-toggle="modal" onclick="matchingMetro.prototype.showModel()"
                            href="#divBoxMatchingMetro">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                        新增
                    </button>
                    <button type="button" style="margin-left: 5px" class="btn btn-warning btn-sm" onclick="matchingMetro.prototype.clear()">
                        	<span class="btn-label">
												<i class="fa fa-minus"></i>
											</span>
                        删除
                    </button>
                    
                    <div class="col-sm-3">
                        <input type="text" placeholder="距离(单位/m)" class="form-control input-full input-full" name="distance"
                               value="3000">
                    </div>

                    <button type="button" class="btn btn-info btn-sm" onclick="assessMetro.select(this)">
                        <i class="fa fa-search"></i>搜索
                    </button>

                </div>
                <table class="table table-bordered" id="MatchingMetroList">
                    <!-- cerare document add ajax data-->
                </table>
            </form>
        </div>
    </div>
</div>
<div id="divBoxMatchingMetro" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">地铁</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmMatchingMetro" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="type" class="type">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            地铁站名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control input-full" name="name"
                                                   placeholder="名称" required="required">
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            收费标准
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control input-full" name="costStandard"
                                                   placeholder="收费标准">
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            距大门距离<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="distance"
                                                    class="form-control input-full  distance">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>


                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            所在线路<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <button class="btn btn-sm btn-success"
                                                    onclick="matchingMetro.prototype.appendHTML('theLine',this)"><i
                                                    class="fa fa-plus"></i></button>
                                        </div>
                                    </div>
                                    </div>
                                </div>

                                <div style="margin-bottom: 8px;" class="theLine">
                                    <div class="row form-group" style=" margin-top: 8px;">

                                        <div class="col-md-12">
                                            <div class="form-inline form-inline x-valid">
                                                <label class="col-sm-2 control-label">所在线路</label>
                                                <div class="col-sm-10 ">
                                                    <div class="input-group">
                                                        <input class="form-control input-full" name="name" required="required"
                                                               type="text">
                                                        <span class="input-group-btn">
                                                         <input type="button" class="btn btn-warning" value="X"
                                                                onclick="matchingMetro.prototype.cleanHTMLData(this)"></span>
                                                    </div>
                                                </div>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="matchingMetro.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>



