
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                        ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frmQuery" class="form-horizontal">
                        <div class="form-group">
                            <div class="col-sm-3">
                                <button type="button" class="btn btn-success"
                                        onclick="landLevel.showModel()"
                                        data-toggle="modal"> 新增
                                </button>
                            </div>

                            <div class="col-sm-3">
                                <button type="button" class="btn btn-success"
                                        onclick="dataImgTwoDimensionalBackgroundFun()"
                                        data-toggle="modal"> 背景图导入
                                </button>
                            </div>

                            <div class="col-sm-3">
                                <button type="button" class="btn btn-success"
                                        onclick="dataImgTwoDimensionalImgFun()"
                                        data-toggle="modal"> 户型图导入
                                </button>
                            </div>
                        </div>

                        <div class="form-group">
                            <input type="file" id="dataImgTwoDimensionalBackground" name="dataImgTwoDimensionalBackground" style="display: none"
                                   onchange="dataImgTwoDimensionalBackgroundChange(this)">

                            <input type="file" id="dataImgTwoDimensionalImg" name="dataImgTwoDimensionalImg" style="display: none"
                                   onchange="dataImgTwoDimensionalImgChange(this)">
                        </div>

                        <div class="form-group">
                            <div class="col-sm-12">
                                <table class="table table-bordered" id="tb_FatherList">
                                    <!-- cerare document add ajax data-->
                                </table>
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="col-sm-3">
                               <label class="control-label">户型图</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <table class="table table-bordered" id="dataImgTwoDimensionalImgTable">
                                    <!-- cerare document add ajax data-->
                                </table>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-3">
                                <label class="control-label">背景图</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <table class="table table-bordered" id="dataImgTwoDimensionalBackgroundTable">
                                    <!-- cerare document add ajax data-->
                                </table>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
<script type="text/javascript">
    $(function () {
        landLevel.loadLandLevelList();
        loadDataList();
    });

    var landLevel = {
        config: function () {
            var data = {};
            data.table = "tb_FatherList";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            return data;
        },
        loadLandLevelList: function () {
            var cols = [];
            cols.push({field: 'deg', title: '旋转角度'});
            cols.push({field: 'name', title: '名称'});
            cols.push({field: 'backgroundUrl', title: '背景url'});
            cols.push({field: 'imgUrl', title: '图片url'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="详情" onclick="landLevel.fineData(' + row.id + ',\'tb_List\')"><i class="fa fa-search"></i></a>';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="landLevel.editData(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="landLevel.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + landLevel.config().table).bootstrapTable('destroy');
            TableInit(landLevel.config().table, "${pageContext.request.contextPath}/dataImgTwoDimensional/getBootstrapTableVo", cols, {
                name: null
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        showModel:function () {
            var itemA = $("#dataImgTwoDimensionalImgTable").bootstrapTable('getSelections');
            var itemB = $("#dataImgTwoDimensionalBackgroundTable").bootstrapTable('getSelections');
            if (itemA.length < 1){
                Alert("请选择户型图");
                return false;
            }
            if (itemB.length < 1){
                Alert("请选背景图");
                return false;
            }
            var href = "${pageContext.request.contextPath}/dataImgTwoDimensional/index";
            href += "?sysAttachmentIdA=" +itemA[0].id+"&sysAttachmentIdB="+itemB[0].id;
            window.open(href, "");
        },
        editData:function (id) {
            var href = "${pageContext.request.contextPath}/dataImgTwoDimensional/index";
            href += "?id=" +id;
            window.open(href, "");
        },
        fineData:function (id) {
            var href = "${pageContext.request.contextPath}/dataImgTwoDimensional/detail";
            href += "?id=" +id;
            window.open(href, "");
        },
        removeData:function (id) {
            Alert('确认要删除么？', 2, null, function () {
                Loading.progressShow();
                $.ajax({
                    url: '${pageContext.request.contextPath}/dataImgTwoDimensional/deleteDataImgTwoDimensionalById',
                    data: {id: id},
                    method:"post",
                    success: function (result) {
                        Loading.progressHide();
                        if (result.ret) {
                            toastr.success('删除成功');
                            landLevel.loadLandLevelList();
                        } else {
                            Alert(result.msg);
                        }
                    }
                })
            })
        }
    };

    function dataImgTwoDimensionalBackgroundChange() {
        $.ajaxFileUpload({
            type: "POST",
            url: "${pageContext.request.contextPath}/public/importAjaxFile",
            data: {
                tableName: AssessDBKey.DataImgTwoDimensional,
                tableId: 0,
                fieldsName: "dataImgTwoDimensionalBackground"
            },//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: "dataImgTwoDimensionalBackground",//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                if (result.ret) {
                    loadDataList();
                    Alert("success");
                }
            },
            error: function (result, status, e) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }

    function dataImgTwoDimensionalBackgroundFun() {
        $("#dataImgTwoDimensionalBackground").attr("data-id", 0);
        $("#dataImgTwoDimensionalBackground").trigger('click');
    }

    function dataImgTwoDimensionalImgFun() {
        $("#dataImgTwoDimensionalImg").attr("data-id", 0);
        $("#dataImgTwoDimensionalImg").trigger('click');
    }

    function dataImgTwoDimensionalImgChange() {
        $.ajaxFileUpload({
            type: "POST",
            url: "${pageContext.request.contextPath}/public/importAjaxFile",
            data: {
                tableName: AssessDBKey.DataImgTwoDimensional,
                tableId: 0,
                fieldsName: "dataImgTwoDimensionalImg"
            },//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: "dataImgTwoDimensionalImg",//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                if (result.ret) {
                    loadDataList();
                    Alert("success");
                }
            },
            error: function (result, status, e) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }

    function loadDataList() {
        (function () {
            var colsA = [];
            colsA.push({
                checkbox: true
            });
            colsA.push({
                field: 'id', title: '名称', formatter: function (value, row, index) {
                    return row.fileName;
                }
            });
            $("#dataImgTwoDimensionalImgTable").bootstrapTable('destroy');
            TableInit('dataImgTwoDimensionalImgTable', "${pageContext.request.contextPath}/dataImgTwoDimensional/dataImgTwoDimensionalImg", colsA, {
                name: null
            }, {
                showColumns: false,
                showRefresh: false,
                singleSelect : true,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        }());
        (function () {
            var colsB = [];
            colsB.push({
                radio: true
            });
            colsB.push({
                field: 'id', title: '名称', formatter: function (value, row, index) {
                    return row.fileName;
                }
            });
            $("#dataImgTwoDimensionalBackgroundTable").bootstrapTable('destroy');
            TableInit('dataImgTwoDimensionalBackgroundTable', "${pageContext.request.contextPath}/dataImgTwoDimensional/dataImgTwoDimensionalBackground", colsB, {
                name: null
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        }());
    }
</script>




</html>
