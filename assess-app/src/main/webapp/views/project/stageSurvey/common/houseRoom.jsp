<%--
 房间
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12" >
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    房间
                </div>
                <div class="card-tools">
                    <button class="btn btn-icon btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body" style="display: none">
            <form class="form-horizontal">
                <p>
                    <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                            data-toggle="modal" onclick="houseRoom.prototype.showModel()"
                            href="#divBoxHouseRoom">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                        新增
                    </button>
                </p>
                <table class="table table-bordered" id="HouseRoomList">
                    <!-- cerare document add ajax data-->
                </table>
            </form>
        </div>
    </div>
</div>
<div id="divBoxHouseRoom" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">房间</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmHouseRoom" class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                房间类型<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="房间类型" class="form-control input-full"
                                                       name="roomType">
                                                <input type="hidden" placeholder="房间类型" class="form-control input-full"
                                                       name="roomTypeId">
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                面积<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="面积" name="area" data-rule-number='true'
                                                       class="form-control input-full" required="required">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label"
                                                   title="层高通常指下层地板面或楼板面到上层楼板面之间的距离。层高减去楼板的厚度或结构层的高度的差,叫做净高。出于降低成本、节约建材和节约土地等考虑,一般住宅层高都在28米左右">
                                                层高(m)<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" data-rule-number="true" placeholder="层高(m)"
                                                       name="layerHeight" class="form-control input-full" required>
                                            </div>
                                            <label class="col-sm-2 control-label"
                                                   title="层高通常指下层地板面或楼板面到上层楼板面之间的距离。层高减去楼板的厚度或结构层的高度的差,叫做净高。出于降低成本、节约建材和节约土地等考虑,一般住宅层高都在28米左右">
                                                净高(m)<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" data-rule-number="true" placeholder="净高(m)"
                                                       name="clearHeight" class="form-control input-full" required>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                开间/宽(m)<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" data-rule-number="true" placeholder="开间/宽(m)"
                                                       name="opening" class="form-control input-full" required>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                进深/长(m)<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" data-rule-number="true" placeholder="进深/长(m)"
                                                       name="depth" class="form-control input-full" required>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                通风<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="通风" name="aeration"
                                                       class="form-control input-full" required>
                                            </div>

                                            <label class="col-sm-2 control-label">
                                                日照<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="日照" name="sunshine"
                                                       class="form-control input-full" required>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                采光<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="采光" name="lighting"
                                                       class="form-control input-full" required>
                                            </div>

                                            <label class="col-sm-2 control-label">
                                                隔音<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="隔音" name="soundInsulation"
                                                       class="form-control input-full" required>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            附件
                                        </label>
                                        <div class="col-sm-10">
                                            <input id="house_room_file" name="house_room_file"
                                                   placeholder="上传附件" class="form-control input-full"
                                                   type="file">
                                            <div id="_house_room_file"></div>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="houseRoom.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>



<div id="SubclassDivBoxHouseRoom" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">房间装修数据</h4>
                <input type="hidden" name="roomId" class="roomId">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card-body">
                            <p id="toolbarSub">
                    <button type="button" class="btn btn-success btn-sm"
                            onclick="houseRoom.prototype.showModelSubclassSaveView()"
                            data-toggle="modal" href="#divSubDataDicManage">
                        <span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                        新增
                    </button>
                </p>
                            <table class="table table-bordered" id="SubclassHouseRoomList">
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

<div id="boxSubclassSaveViewHouseRoom" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">房间装修</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="SubclassFrmHouseRoom" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="roomId" class="roomId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修部位<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="part"
                                                    class="form-control input-full  part">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            部位描述
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea name="remark" class="form-control input-full"></textarea>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修材料
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="material" class="form-control input-full  material">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            施工工艺
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="constructionTechnology"
                                                    class="form-control input-full constructionTechnology">
                                            </select>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修材料价格区间
                                        </label>
                                        <div class="col-sm-10">
                                            <select name="materialPrice"
                                                    class="form-control input-full materialPrice">
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
                <button type="button" class="btn btn-primary btn-sm" onclick="houseRoom.prototype.subclassSave()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>



