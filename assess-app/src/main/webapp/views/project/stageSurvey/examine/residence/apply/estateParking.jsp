<%--
  车位信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>车位</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <button type="button" class="btn btn-success" onclick="estateParking.prototype.showModel()"
                data-toggle="modal" href="#divBox"> 新增
        </button>
        <table class="table table-bordered" id="estateParkingList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div id="divBoxEstateParking" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">车位</h3>
            </div>
            <form id="frmEstateParking" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            车位位置<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select class="form-control search-select select2 location"
                                                    name="location" required>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <%--<div class="form-group">--%>
                                    <%--<div class="x-valid">--%>
                                        <%--<label class="col-sm-2 control-label">--%>
                                            <%--车位类型<span class="symbol required"></span>--%>
                                        <%--</label>--%>
                                        <%--<div class="col-sm-10">--%>
                                            <%--<select class="form-control search-select select2 parkingType"--%>
                                                    <%--name="parkingType" required>--%>
                                            <%--</select>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            楼盘车位<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select class="form-control search-select select2 parkingEstate"
                                                    name="parkingEstate" required>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            车位数量
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="number"
                                                   placeholder="车位数量">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            平面图
                                        </label>
                                        <div class="col-sm-10">
                                            <input id="house_estateParking" name="house_estateParking"
                                                   placeholder="上传附件" class="form-control"
                                                   type="file">
                                            <div id="_house_estateParking"></div>
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
                    <button type="button" class="btn btn-primary" onclick="estateParking.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>