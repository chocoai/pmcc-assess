<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <title>附件上传页面</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>


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
                                    <div class="x_title">
                                        <h3>
                                            房间
                                        </h3>
                                        <div class="clearfix"></div>
                                    </div>
                                    <form class="form-horizontal">
                                        <div id="houseRoomToolDiv">

                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%@include file="/views/project/stageSurvey/common/canvasQRcodePagination.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>


<script type="text/html" id="houseRoomHtmlModel">
    <div class="form-group">

        <div class="col-sm-12 col-md-12">
            <div class="form-inline x-valid">

                <div class="col-xs-12 col-sm-6  text-center" style="margin-top:15px;">
                    {name} {fileViewName}
                </div>

                <div class="col-xs-12 col-sm-6   text-center" style="margin-top:15px;">
                    操作: {html}
                </div>
            </div>
        </div>

    </div>
</script>


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


<input type="file" id="ajaxFileUpload" name="file" style="display: none;">

<script type="text/javascript">

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
                return data;
            },
            inputFile: function (this_) {
                var target = $(this_);
                var fieldName = target.attr("data-fileid");
                var id = target.attr("data-id");
                Loading.progressShow();
                $.ajaxFileUpload({
                    type: "POST",
                    url: getContextPath() + "/public/importAjaxFile",
                    data: {
                        tableName: AssessDBKey.BasicHouseRoom,
                        tableId: id,
                        fieldsName: fieldName
                    },//要传到后台的参数，没有可以不写
                    secureuri: false,//是否启用安全提交，默认为false
                    fileElementId: target.attr("id"),//文件选择框的id属性
                    dataType: 'json',//服务器返回的格式
                    async: false,
                    success: function (result) {
                        Loading.progressHide();
                        if (result.ret) {
                            houseRoom.prototype.loadDataDicList();
                            notifySuccess("成功", "上传附件成功!");
                        } else {
                            if (result.errmsg) {
                                AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                            } else {
                                AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                            }
                        }
                    },
                    error: function (result, status, e) {
                        Loading.progressHide();
                        if (result.errmsg) {
                            AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                        } else {
                            AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                        }
                    }
                });
            },
            importEvent: function (fileId) {
                $("#" + fileId).trigger('click');
            },
            delFileObj: function (tableId) {
                AssessCommon.getSysAttachmentDtoList({
                    tableId: tableId,
                    tableName: AssessDBKey.BasicHouseRoom
                }, function (data) {
                    if (data.length == 0) {
                        notifyWarning("警告", "没有附件!");
                        return false;
                    }
                    var arr = [];
                    $.each(data, function (i, item) {
                        arr.push(item.id);
                    });
                    AlertConfirm("是否确认删除当前附件", "删除相应的数据后将不可恢复", function (flag) {
                        AssessCommon.deleteAttachmentById(arr.join(","), function () {
                            notifySuccess("成功", "附件成功删除!");
                            houseRoom.prototype.loadDataDicList();
                        });
                    });
                });
            },
            getDataList: function (callback) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/basicHouseRoom/basicHouseRoomList",
                    type: "get",
                    dataType: "json",
                    data: {houseId: houseCommon.getHouseId()},
                    success: function (result) {
                        if (result.ret) {
                            if (callback) {
                                callback(result.data);
                            }
                        } else {
                            AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                        }
                    }
                });
            },
            loadDataDicList: function () {
                var target = $("#houseRoomToolDiv");
                target.empty();
                houseRoom.prototype.getDataList(function (result) {
                    AssessCommon.loadAsyncDataDicByKey(AssessDicKey.examineHouseRoomFilePart, '', function (html, resultData) {
                        $.each(result, function (k, row) {
                            var html = $("#houseRoomHtmlModel").html();
                            var str = "";
                            $.each(resultData, function (i, fileData) {
                                str += "<input name='file' onchange='houseRoom.prototype.inputFile(this)' style='display: none' type='file' multiple='multiple' id='" + fileData.fieldName + row.id + "'" + "data-id='" + row.id + "'" + "data-fileId='" + fileData.fieldName + "'" + ">";
                                str += '<button type="button" onclick="houseRoom.prototype.importEvent(' + "'" + fileData.fieldName + row.id + "'" + ')"  style="margin-left: 5px;"  class="btn btn-info btn-xs tooltips"  data-placement="bottom" data-original-title="上传附件">';
                                str += '<i class="fa fa-cloud-upload-alt"></i>';
                                str += '</button>';

                                str += '<button type="button" onclick="houseRoom.prototype.delFileObj(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn   btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除附件">';
                                str += '<i class="fa fa-minus"></i>';
                                str += '</button>';
                            });

                            var s = "";
                            if (row.name) {
                                s += row.name;
                            }
                            if (row.creatorName) {
                                s += "<span style='padding: 5px;' class='label label-info'>" + row.creatorName.split("_")[0] + "</span>"
                            }
                            if (row.fileViewName) {
                                html = html.replace(/{fileViewName}/g, row.fileViewName);
                            } else {
                                html = html.replace(/{fileViewName}/g, "");
                            }
                            html = html.replace(/{name}/g, s);
                            html = html.replace(/{html}/g, str);

                            target.append(html);

                        });
                    }, false);
                });
            }
        };

//        //绑定事件
//        $('#' + houseRoom.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
//            houseRoom.prototype.loadDataDicList();
//        })
    })();
    $(function () {
        houseCommon.initById('${basicHouse.id}', function () {
            houseRoom.prototype.loadDataDicList();
        });


    })


</script>

