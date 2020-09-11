<%--
 房间
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12" tab-role="base">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    房间
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
                                <div style="display: none" class="row form-group common">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="name"></label>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                房间形状<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="houseShape"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group common">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                使用面积
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="area"></label>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                层高<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="layerHeight"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group common">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                净高
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="clearHeight"></label>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                所在楼层
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="currentFloor"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%--住宅，办公--%>
                                <div style="display: none" class="row form-group residence base">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                通风
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="aeration"></label>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                采光
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="lighting"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group residence base">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                日照
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="sunshine"></label>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                隔音
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="soundInsulation"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group residence rule">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                长度
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="length"></label>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                宽度
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="width"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group residence unruled">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                最长
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="length"></label>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                最宽
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="width"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%-- 餐饮酒店--%>
                                <div style="display: none" class="row form-group hotel base">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                通风
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="aeration"></label>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                采光
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="lighting"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group hotel rule">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                长度
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="length"></label>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                宽度
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="width"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group hotel unruled">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                最长
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="length"></label>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                最宽
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="width"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%-- 商铺、商场--%>
                                <div style="display: none" class="row form-group store base">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                相邻位置
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="adjacentPositionName"></label>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                方位
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="orientationName"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group store rule">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                开间
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="opening"></label>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                进深
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="depth"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group store unruled">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                最大开间
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="opening"></label>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                最小进深
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="depth"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group store base">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                距离
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="distance"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%--生产--%>
                                <div style="display: none" class="row form-group production base">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                跨长
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="spanLength"></label>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                跨数
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="spanNum"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group production base">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                通风
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="aeration"></label>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                采光
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="lighting"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group production unruled">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                最大跨距
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="maxSpan"></label>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                最小跨距
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="minSpan"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group production rule">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                标准跨距
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="standardSpan"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%-- 仓储--%>
                                <div style="display: none" class="row form-group storage base">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                计量标准
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="standardMeasureName"></label>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                仓储要求
                                            </label>
                                            <div class="col-sm-4">
                                                <label class="form-control input-full" name="storageRequestName"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div style="display: none" class="row form-group common">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                附件
                                            </label>
                                            <div class="col-sm-10">
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
            </div>

        </div>
    </div>
</div>

