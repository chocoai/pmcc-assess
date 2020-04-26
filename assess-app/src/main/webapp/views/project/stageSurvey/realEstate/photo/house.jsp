<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <title>附件上传页面</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>

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
                            <div class="card-body content">
                                <div id="houseRoomFilePart"></div>
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

<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <div class="page-inner">
                <div class="row">
                    <!-- 填写表单 start -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        附件上传页面
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class="x_content">
                                    <div class="x_title">
                                        <h3>
                                            房屋基本信息
                                        </h3>
                                        <div class="clearfix"></div>
                                    </div>
                                    <form class="form-horizontal" id="basicHouseFrm">
                                        <input type="hidden" name="id" value="${basicHouse.id}">
                                        <input type="hidden" name="quoteId" value="${basicHouse.quoteId}">
                                        <input type="hidden" name="unitId" value="${basicHouse.unitId}">
                                        <input type="hidden" name="estateId" value="${basicHouse.estateId}">
                                        <input type="hidden" name="buildingId" value="${basicHouse.buildingId}">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="card-body">


                                                    <div id="houseFilePart"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="x_content">
                                    <div class="x_title">
                                        <h3>
                                            户型
                                        </h3>
                                        <div class="clearfix"></div>
                                    </div>
                                    <form class="form-horizontal" id="basicHouseHuxing">
                                        <input type="hidden" name="id" value="${basicHouseHuxing.id}">
                                        <div id="houseHuxingFilePart"></div>
                                    </form>
                                </div>
                                <div class="x_content">
                                    <div class="x_title">
                                        <h3>
                                            房屋交易信息
                                        </h3>
                                        <div class="clearfix"></div>
                                    </div>
                                    <form class="form-horizontal" id="basicTradingFrm">
                                        <input type="hidden" name="id" value="${basicHouseTrading.id}">



                                        <div class="x_title">
                                            <div class="clearfix"></div>
                                        </div>


                                        <div id="houseTradingFilePart"></div>

                                    </form>
                                </div>
                                <div class="x_content">
                                    <div class="col-md-12">
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
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">
                        <div class="card-body">
                            <button type="button" id="cancel_btn btn-sm" class="btn btn-default"
                                    onclick="window.close()">关闭
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>
</html>



<script src='${pageContext.request.contextPath}/js/common.column.js?v=${assessVersion}'></script>


<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.common.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/examine.house.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/examine/sonHouseView.js?v=${assessVersion}"></script>


<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/select/huxing.select.js?v=${assessVersion}"></script>

<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/autocomplete/roomType.js?v=${assessVersion}"></script>

<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
<script type="text/javascript">

    $(function () {
        houseCommon.initById('${basicHouse.id}');
    })


</script>


<script>

    var houseRoom;
    (function () {
        houseRoom = function () {

        };
        houseRoom.num = 0;
        houseRoom.beCopyObjectId = undefined;
        houseRoom.prototype = {
            isNotBlank: function (item) {
                if (item) {
                    return true;
                }
                return false;
            },
            isEmpty: function (item) {
                if (item) {
                    return true;
                }
                return false;
            },
            config: function () {
                var data = {};
                data.table = "HouseRoomList";
                data.box = "divBoxHouseRoom";
                data.frm = "frmHouseRoom";
                data.tableSubclass = "SubclassHouseRoomList";
                data.boxSubclass = "SubclassDivBoxHouseRoom";
                data.boxSubclassSaveView = "boxSubclassSaveViewHouseRoom";
                data.frmSubclass = "SubclassFrmHouseRoom";
                data.type = "null";//
                return data;
            },
            loadDataDicList: function () {
                var cols = commonColumn.houseRoomColumn();
                cols.push({field: 'fileViewName', title: '附件'});
                cols.push({
                    field: 'id', title: '操作', formatter: function (value, row, index) {
                        var str = '<div class="btn-margin">';
                        str += '<button type="button" style="margin-left: 5px;"  class="btn btn-xs btn-primary tooltips"  data-placement="top" data-original-title="编辑" onclick="houseRoom.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-pen"></i></button>';
                        str += '</div>';
                        return str;
                    }
                });
                $("#" + houseRoom.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseRoom.prototype.config().table, getContextPath() + "/basicHouseRoom/getBootstrapTableVo", cols, {
                    houseId: houseCommon.getHouseId()
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
            },
            getAndInit: function (id) {
                var data = $("#" + houseRoom.prototype.config().table).bootstrapTable('getRowByUniqueId',id);
                var box = $('#' + houseRoom.prototype.config().box) ;
                var frm = box.find("form") ;
                frm.clearAll();
                frm.initForm(frm);
                houseRoom.prototype.getFilePartHtml(AssessDicKey.examineHouseRoomFilePart, data);
                box.modal("show");
            },
            //附件上传
            fileUpload: function (fieldsName, id) {
                FileUtils.uploadFiles({
                    target: fieldsName,
                    disabledTarget: "btn_submit",
                    formData: {
                        fieldsName: fieldsName,
                        tableName: AssessDBKey.BasicHouseRoom,
                        tableId: houseRoom.prototype.isNotBlank(id) ? id : "0"
                    },
                    deleteFlag: true,
                    onUploadComplete: function () {
                        houseRoom.prototype.fileShow(fieldsName, id);
                    }
                });
            },
            fileShow: function (fieldsName, id) {
                FileUtils.getFileShows({
                    target: fieldsName,
                    formData: {
                        fieldsName: fieldsName,
                        tableName: AssessDBKey.BasicHouseRoom,
                        tableId: houseRoom.prototype.isNotBlank(id) ? id : "0"
                    },
                    deleteFlag: true
                })
            },
            getFilePartHtml: function (fieldName, item) {
                var fileDiv = $('#' + fieldName);
                fileDiv.empty();
                var houseFileHtml = '';
                AssessCommon.loadAsyncDataDicByKey(fieldName, '', function (html, resultData) {
                    houseFileHtml += '<div class="row form-group common">';
                    houseFileHtml += '<div class="col-md-12">';
                    houseFileHtml += '<div class="form-inline x-valid">';
                    for (var i = 0; i < resultData.length; i++) {
                        houseFileHtml += '<label class="col-sm-2">' + resultData[i].name + '</label>';
                        houseFileHtml += '<div class="col-sm-10">';
                        houseFileHtml += '<input id="' + resultData[i].fieldName + '" placeholder="上传附件" class="form-control input-full" type="file">';
                        houseFileHtml += '<div id="_' + resultData[i].fieldName + '"></div>';
                        houseFileHtml += '</div>';
                    }
                    houseFileHtml += "</div>";
                    houseFileHtml += "</div>";
                    houseFileHtml += "</div>";

                }, false);
                fileDiv.append(houseFileHtml);
                AssessCommon.loadAsyncDataDicByKey(fieldName, '', function (html, resultData) {
                    $.each(resultData, function (i, fileData) {
                        houseRoom.prototype.fileUpload(fileData.fieldName, item.id);
                        houseRoom.prototype.fileShow(fileData.fieldName, item.id);
                    });
                }, false);
            }
        };

        //绑定事件
        $('#' + houseRoom.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
            houseRoom.prototype.loadDataDicList();
        })
    })();

</script>