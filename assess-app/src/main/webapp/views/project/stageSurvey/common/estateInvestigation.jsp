<%--
楼盘调查信息
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>楼盘调查信息</h4>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="estateInvestigation.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <div class="btn-group">
            <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">导入数据
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" role="menu">
                <li><a class="btn"
                       onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftBasicEstateInvestigation)">下载模板</a>
                </li>
                <li>
                    <a class="btn" onclick="$('#ajaxFileUploadInvestigation').val('').trigger('click')"><span>导入</span></a>
                </li>
            </ul>
        </div>
        <table class="table table-bordered" id="EstateInvestigationList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>


<div id="divBoxEstateInvestigation" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">楼盘调查信息</h3>
            </div>
            <form id="frmEstateInvestigation" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="estateId">
                <div class="modal-body">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                楼栋号
                            </label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <input type="text" placeholder="楼栋号" name="buildingNumber"class="form-control">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                单元号
                            </label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <input type="text" placeholder="单元号" name="unitNumber"class="form-control">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                房号
                            </label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <input type="text" placeholder="房号" name="houseNumber"class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                建筑面积
                            </label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <input type="text" placeholder="建筑面积" name="buildingArea" data-rule-number='true'
                                       class="form-control">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                价格
                            </label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <input type="text" placeholder="价格" name="price" data-rule-number='true'
                                       class="form-control">
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                规划用途
                            </label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <select name="planningUse" class="form-control search-select select2 planningUse">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                结构
                            </label>
                            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                <input type="text" placeholder="结构" name="construction"class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                备注
                            </label>
                            <div class="col-sm-11">
                                    <textarea placeholder="备注" id="remark" name="remark"
                                              class="form-control"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="estateInvestigation.prototype.saveData()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<input type="file" id="ajaxFileUploadInvestigation" name="file" style="display: none;"
       onchange="estateInvestigation.prototype.importData();">
</html>
