<%--
 装修情况
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    装修情况
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
                            data-toggle="modal" onclick="houseRoomDecorate.prototype.showModel()"
                            href="#divBoxHouseRoomDecorate">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                        新增
                    </button>
                </p>
                <table class="table table-bordered" id="HouseRoomDecorateList">
                    <!-- cerare document add ajax data-->
                </table>
            </form>
        </div>
    </div>
</div>

<div id="divBoxHouseRoomDecorate" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">装修情况</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmHouseRoomDecorate" class="form-horizontal">
                    <input type="hidden" name="id">
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
                                                所在位置<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <div class="input-group">
                                                    <input type="text" placeholder="所在位置" required
                                                           name="location"
                                                           class="form-control">
                                                    <div class="input-group-prepend">
                                                        <button class="btn btn-primary btn-sm "
                                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                type="button"
                                                                onclick="houseRoomDecorate.prototype.openLocationModal(this);">
                                                            编辑
                                                        </button>
                                                    </div>
                                                </div>
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
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                装修档次
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="level" class="form-control input-full level">
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
                <button type="button" class="btn btn-primary btn-sm" onclick="houseRoomDecorate.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<div id="divBoxDecoratePart" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">装修部位</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmDecoratePart" class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <div class="col-sm-12">
                                                <div id="industrySupplyInfoContainer"></div>
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
                <button type="button" class="btn btn-primary btn-sm"
                        onclick="houseRoomDecorate.prototype.saveDecoratePart()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>
<div id="divBoxLocation" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">所在位置</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmLocation" class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <%--住宅 residence--%>

                                <div style="display: none;" class="row form-group residence">
                                    <div class="col-md-12">
                                        <div class="form-check" style="justify-content:left">
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="卧室">
                                                <span class="form-check-sign">卧室</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="客厅">
                                                <span class="form-check-sign">客厅</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="中餐厅">
                                                <span class="form-check-sign">中餐厅</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="西餐厅">
                                                <span class="form-check-sign">西餐厅</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="茶室">
                                                <span class="form-check-sign">茶室</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="影视室">
                                                <span class="form-check-sign">影视室</span>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <%--商铺/商场 store--%>
                                <div style="display: none;" class="row form-group store">
                                    <div class="col-md-12">
                                        <div class="form-check" style="justify-content:left">
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="商间">
                                                <span class="form-check-sign">商间</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="商区">
                                                <span class="form-check-sign">商区</span>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <%--餐饮酒店 hotel--%>
                                <div style="display: none;" class="x_title hotel">住宿</div>
                                <div style="display: none;" class="row form-group hotel">
                                    <div class="col-md-12">
                                        <div class="form-check" style="justify-content:left">
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="普通标间">
                                                <span class="form-check-sign">普通标间</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="商务标间">
                                                <span class="form-check-sign">商务标间</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="高级标间">
                                                <span class="form-check-sign">高级标间</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="普通单间">
                                                <span class="form-check-sign">普通单间</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="商务单间">
                                                <span class="form-check-sign">商务单间</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="高级单间">
                                                <span class="form-check-sign">高级单间</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="普通套房">
                                                <span class="form-check-sign">普通套房</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="商务套房">
                                                <span class="form-check-sign">商务套房</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="高级套房">
                                                <span class="form-check-sign">高级套房</span>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none;" class="x_title hotel">商业</div>
                                <div style="display: none;" class="row form-group hotel">
                                    <div class="col-md-12">
                                        <div class="form-check" style="justify-content:left">
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="会议室">
                                                <span class="form-check-sign">会议室</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="会议厅">
                                                <span class="form-check-sign">会议厅</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="商务厅">
                                                <span class="form-check-sign">商务厅</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="影视厅">
                                                <span class="form-check-sign">影视厅</span>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none;" class="x_title hotel">餐饮</div>
                                <div style="display: none;" class="row form-group hotel">
                                    <div class="col-md-12">
                                        <div class="form-check" style="justify-content:left">
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="普通包间">
                                                <span class="form-check-sign">普通包间</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="标准包间">
                                                <span class="form-check-sign">标准包间</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="豪华包间">
                                                <span class="form-check-sign">豪华包间</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="餐饮大厅">
                                                <span class="form-check-sign">餐饮大厅</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="共用餐区">
                                                <span class="form-check-sign">共用餐区</span>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <%--办公 work--%>
                                <div style="display: none;" class="row form-group work">
                                    <div class="col-md-12">
                                        <div class="form-check" style="justify-content:left">
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="会议室">
                                                <span class="form-check-sign">会议室</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="会客室">
                                                <span class="form-check-sign">会客室</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="休息室">
                                                <span class="form-check-sign">休息室</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="办公室">
                                                <span class="form-check-sign">办公室</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="办公区">
                                                <span class="form-check-sign">办公区</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="档案室">
                                                <span class="form-check-sign">档案室</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="影视室">
                                                <span class="form-check-sign">影视室</span>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <%--生产 production--%>
                                <div style="display: none;" class="row form-group production">
                                    <div class="col-md-12">
                                        <div class="form-check" style="justify-content:left">
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="生产车间">
                                                <span class="form-check-sign">生产车间</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="维修车间">
                                                <span class="form-check-sign">维修车间</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="成品车间">
                                                <span class="form-check-sign">成品车间</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="热力车间">
                                                <span class="form-check-sign">热力车间</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="中转车间">
                                                <span class="form-check-sign">中转车间</span>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <%--仓储 storage--%>
                                <div style="display: none;" class="row form-group storage">
                                    <div class="col-md-12">
                                        <div class="form-check" style="justify-content:left">
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="储库">
                                                <span class="form-check-sign">储库</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="储仓">
                                                <span class="form-check-sign">储仓</span>
                                            </label>
                                        </div>
                                    </div>
                                </div>

                                <div class="x_title common">通用部分</div>
                                <div style="display: none;" class="row form-group common">
                                    <div class="col-md-12">
                                        <div class="form-check" style="justify-content:left">
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="卫生间">
                                                <span class="form-check-sign">卫生间</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="洗浴间">
                                                <span class="form-check-sign">洗浴间</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="厨房">
                                                <span class="form-check-sign">厨房</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="阳台">
                                                <span class="form-check-sign">阳台</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="车库">
                                                <span class="form-check-sign">车库</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="楼梯间">
                                                <span class="form-check-sign">楼梯间</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="电梯间">
                                                <span class="form-check-sign">电梯间</span>
                                            </label>
                                            <label class="form-check-label">
                                                <input class="form-check-input" type="checkbox" name="locationCheckBox"
                                                       value="过道">
                                                <span class="form-check-sign">过道</span>
                                            </label>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="houseRoomDecorate.prototype.spliceLocation()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


