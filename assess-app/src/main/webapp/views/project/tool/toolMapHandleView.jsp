<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/html2canvas/html2canvas.js"></script>
    <script src="${pageContext.request.contextPath}/views/project/tool/toolMapHandle.js"></script>
</head>

<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="x_panel">
                <div class="x_title">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                        ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form class="form-horizontal">
                        <div class="form-group ">
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <button type="button" data-toggle="modal" href="#modelToolMapHandleView"
                                        class="btn btn-default"
                                        onclick="toolMapHandleFun.loadMap({drawState:'marker',instantaneousLifeData:JSON.stringify([{P:30.589982,Q:104.083992,lng:104.083992,lat:30.589982}])});">
                                                  <i class="fa fa-map-marker"></i>
                                    map 
                                           
                                </button>

                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- end: MAIN CONTAINER -->
</div>

</body>

<%@include file="/views/share/main_footer.jsp" %>


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
                                    <button type="button" class="btn-blue btn"
                                            onclick="toolMapHandleFun.reset()">
                                        <i class="fa fa-share-square"></i>
                                        清除地图所有标记
                                    </button>
                                </div>
                                <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2  pull-left">
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
                            <div class="form-group" id="toolHtml2Img" style="display: none;">
                                <div class="col-xs-6  col-sm-6  col-md-6  col-lg-6">
                                    <span class="label label-primary">截取的图层</span>
                                    <img class="img-responsive img-rounded">
                                </div>
                                <div class="col-xs-6  col-sm-6  col-md-6  col-lg-6">
                                    <input id="toolMapHandleFileId" name="toolMapHandleFileId" placeholder="截取附件(假如截图了则需要手动上传)" class="form-control" type="file">
                                    <div id="_toolMapHandleFileId"></div>
                                    <span class="label label-info">请把浏览器自动下载的图片使用文件框上传(假如浏览器没有下载则把右边显示的图片用鼠标右键下载下来)</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                    <input type="hidden" name="toolMapHandleId">
                                    <textarea placeholder="标注说明" class="form-control" name="remark"></textarea>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="toolMapHandleFun.save();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>


</html>
