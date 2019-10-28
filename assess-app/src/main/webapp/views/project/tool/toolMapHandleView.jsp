<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">

<script src="${pageContext.request.contextPath}/assets/html2canvas/html2canvas.js"></script>
<script src="${pageContext.request.contextPath}/js/tool/toolMapHandle.js"></script>

<div id="modelToolMapHandleMarkerView" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title"><i class="fa fa-map"></i> 标注信息</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <form class="form-horizontal">
                            <input type="hidden" name="amap_id" placeholder="高德地图所独有的名称id">
                            <div class="form-group">
                                <div class="x-valid">
                                    <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 control-label">
                                        标注标题<span class="symbol required"></span>
                                    </label>
                                    <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                        <input type="text" class="form-control" name="title"
                                               placeholder="标注标题" required="required">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 control-label">
                                    标注说明<span class="symbol required"></span>
                                </label>
                                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                    <textarea placeholder="标注说明" required="required" class="form-control"
                                              name="remark"></textarea>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
                <button type="button" class="btn btn-primary mapReadonly" onclick="toolMapHandleFun.saveOverlayInfo();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>


<div id="modelToolMapHandleView" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg" style="width:1100px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title"><i class="fa fa-map"></i> 地图</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2  pull-left">
                                    <button type="button" class="btn-blue btn"
                                            onclick="toolMapHandleFun.switch3DMap()">
                                        <i class="fa fa-joomla"></i>
                                        3D切换或者2D
                                    </button>
                                    <span class="label label-warning">当切换3D后 某些线条无法绘制</span>
                                </div>
                                <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2  pull-left">
                                    <button type="button" class="btn-blue btn mapReadonly"
                                            onclick="toolMapHandleFun.reset()">
                                        <i class="fa fa-share-square"></i>
                                        清除地图所有标记
                                    </button>
                                </div>
                                <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2  pull-left mapReadonly">
                                    <button type="button" class="btn-blue btn"
                                            onclick="toolMapHandleFun.html2canvas();">
                                        <i class="fa fa-mouse-pointer" aria-hidden="true"></i>
                                        地图截取
                                    </button>
                                    <span class="label label-warning">请使用360极速浏览器或者谷歌浏览器或者火狐浏览器请不要使用遨游浏览器和IE浏览器</span>
                                </div>
                                <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4 pull-right">
                                    <input type="text"
                                           onkeyup="toolMapHandleFun.autoCompleteSearch(this)" class="form-control"
                                           placeholder="查询...." name="mapSearchName">
                                    <div class="list-group" id="tipInputToolMapHandle">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <form class="form-horizontal">
                            <div class="form-group">
                                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                    <div id="toolMapHandleContainer" style="height:650px;">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group" id="toolHtml2Img">
                                <div class="col-xs-6  col-sm-6  col-md-6  col-lg-6">
                                    <span class="label label-primary">截取的图层</span>
                                    <img class="img-responsive img-rounded">
                                </div>
                                <div class="col-xs-6  col-sm-6  col-md-6  col-lg-6" style="display: none;">
                                    <input id="toolMapHandleFileId" name="toolMapHandleFileId"
                                           placeholder="截取附件(假如截图了则需要手动上传)" class="form-control" type="file">
                                    <div id="_toolMapHandleFileId"></div>
                                    <span class="label label-info">请把浏览器自动下载的图片使用文件框上传(假如浏览器没有下载则把右边显示的图片用鼠标右键下载下来)</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                    <input type="hidden" name="toolMapHandleId">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
                <button type="button" class="btn btn-primary mapReadonly" onclick="toolMapHandleFun.save();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>


</html>
