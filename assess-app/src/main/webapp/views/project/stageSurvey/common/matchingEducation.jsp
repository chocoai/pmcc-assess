<%--
教育条件
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    教育条件信息
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
                            data-toggle="modal" onclick="matchingEducation.prototype.showModel()"
                            href="#divBoxMatchingEducation">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                        新增
                    </button>
                    <button type="button" style="margin-left: 5px" class="btn btn-warning btn-sm" onclick="matchingEducation.prototype.clear()">
                        	<span class="btn-label">
												<i class="fa fa-minus"></i>
											</span>
                        删除
                    </button>

                    <label class="col-sm-1 control-label">
                        学校级次
                    </label>
                    <div class="col-sm-2">
                        <select required="required" name="schoolGradation"
                                class="form-control input-full  schoolGradation">
                        </select>
                    </div>
                    <div class="col-sm-3">
                        <input type="text" placeholder="距离(单位/m)" class="form-control input-full" name="distance"
                               value="3000">
                    </div>

                    <button type="button" class="btn btn-info btn-sm" onclick="assessMatchingEducation.select(this)">
                        <i class="fa fa-search"></i>搜索
                    </button>

                </div>
                <table class="table table-bordered" id="MatchingEducationList">
                    <!-- cerare document add ajax data-->
                </table>
            </form>
        </div>
    </div>
</div>
<%--
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>教育条件信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <form class="form-horizontal">
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <div type="button" class="btn btn-success"
                     onclick="matchingEducation.prototype.showModel()"
                     data-toggle="modal" href="#divBox"> 新增
                </div>
                <div type="button" class="btn btn-warning" onclick="matchingEducation.prototype.clear()">
                    删除
                </div>
            </div>
            <div class="form-inline x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    学校级次
                </label>
                <div class="col-sm-2">
                    <select required="required" name="schoolGradation"
                            class="form-control input-full  schoolGradation">
                    </select>
                </div>
            </div>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <input type="text" placeholder="距离(单位/m)" class="form-control input-full" name="distance" value="3000">
            </div>
            <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1 ">
                <div type="button" class="btn btn-default docs-tooltip"
                     onclick="assessMatchingEducation.select(this)">
                    <i class="fa fa-search"></i>搜索
                </div>
            </div>
            <table class="table table-bordered" id="MatchingEducationList">
            </table>
        </form>
    </div>
</div>--%>

<div id="divBoxMatchingEducation" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">教育条件</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmMatchingEducation" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="type" class="type">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">

                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            学校名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control input-full" name="schoolName"
                                                   placeholder="学校名称" required="required">
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            学校性质<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="schoolNature"
                                                    class="form-control input-full schoolNature">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            学校级次<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="schoolGradation"
                                                    class="form-control input-full schoolGradation">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            学校等级<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="schoolLevel"
                                                    class="form-control input-full schoolLevel">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            距离<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="distance"
                                                    class="form-control input-full distance">
                                            </select>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="matchingEducation.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


