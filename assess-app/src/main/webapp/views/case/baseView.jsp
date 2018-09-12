<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/9/11
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.css">
    <script src='${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.js'></script>
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
                    <h2>
                        ${baseViewDto.currentMenu.name}
                    </h2>
                    <div class="clearfix"></div>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i
                                class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h3>基础板块信息
                    </h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form class="form-horizontal" id="frmCaseBlock">
                        <input type="hidden" name="id">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">基础板块
                                    <span class="symbol required"></span></label>
                                <div class="col-sm-5">
                                    <select name="blockId" class="form-control search-select select2 blockId"
                                            required="required">
                                        <option value="" name="blockId">-请选择-</option>
                                        <c:forEach items="${dataBlocks}" var="item">
                                            <option value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i
                                class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h3>楼盘信息
                    </h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form class="form-horizontal" id="frmCaseEstate">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">省
                                    <span class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select name="province" id="province"
                                            class="form-control search-select select2"
                                            required="required">
                                        <option value="" name="province">-请选择-</option>
                                        <c:forEach items="${ProvinceList}" var="item">
                                            <c:choose>
                                                <c:when test="${item.areaId == projectInfo.province}">
                                                    <option value="${item.areaId}"
                                                            selected="selected">${item.name}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${item.areaId}">${item.name}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">市<span
                                        class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select id="city" name="city" class="form-control search-select select2"
                                            required="required">

                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">县<span
                                        class="symbol required"></span></label>
                                <div class="col-sm-3">
                                    <select id="district" name="district" class="form-control search-select select2"
                                            required="required">

                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <div class="col-sm-1">

                                </div>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" name="search"
                                           onkeyup="baseFun.caseEstate.searchInput();"/>
                                </div>
                            </div>
                            <div class="x-valid">
                                <div class="col-sm-3">
                                    <label class="btn btn-default" onclick="baseFun.caseEstate.find()">
                                        查询
                                    </label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <div class="col-sm-3">
                                    <label class="btn btn-success" onclick="baseFun.caseEstate.newWindow(this)">
                                        新增
                                    </label>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="id">
                        <input type="hidden" name="blockId">
                        <div class="form-group">
                            <div class="x-valid">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">楼盘名称</label>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" readonly="readonly" name="name" placeholder="名称">
                                    </div>
                                </div>
                            </div>

                            <div class="x-valid">
                                <div class="col-sm-3">
                                    <label class="btn btn-default" onclick="baseFun.caseEstate.editData()">
                                        编辑
                                    </label>
                                </div>
                            </div>

                            <div class="x-valid">
                                <div class="col-sm-3">
                                    <label class="btn btn-default" onclick="baseFun.caseEstate.removeData()">
                                        删除
                                    </label>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i
                                class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h3>楼栋信息
                    </h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form class="form-horizontal" id="frmCaseBuild">
                        <div class="form-group">
                            <div class="x-valid">
                                <div class="col-sm-3">
                                    <label class="btn btn-default" onclick="baseFun.caseBuild.addData();">
                                        新增
                                    </label>
                                </div>
                                <div class="col-sm-3">
                                    <button class="btn btn-default">地图显示</button>
                                </div>
                                <div class="col-sm-6">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <div class="col-sm-12">
                                    <table class="table table-bordered" id="caseBuildTable">
                                    </table>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i
                                class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h3>单元信息
                    </h3>
                    <div class="clearfix"></div>
                </div>

                <div class="x_content">
                    <form class="form-horizontal" id="frmCaseUnit">
                        <div class="form-group">
                            <div class="x-valid">
                                <div class="col-sm-3">
                                    <button class="btn btn-default">新增</button>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <div class="col-sm-12">
                                    <table class="table table-bordered" id="caseUnitTable">
                                    </table>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i
                                class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h3>房屋信息
                    </h3>
                    <div class="clearfix"></div>
                </div>

                <div class="x_content">
                    <form class="form-horizontal" id="frmCaseHouse">
                        <div class="form-group">
                            <div class="x-valid">
                                <div class="col-sm-3">
                                    <button class="btn btn-default">新增</button>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <div class="col-sm-12">
                                    <table class="table table-bordered" id="caseHouseTable">
                                    </table>
                                </div>
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
</html>
<script type="text/javascript">
    var BaseViewFun = function () {

    };
    BaseViewFun.prototype.config = {
        father: {
            caseBlock: {
                frm: function () {
                    return "frmCaseBlock";
                }
            },
            caseEstate: {
                frm: function () {
                    return "frmCaseEstate";//楼盘 frm
                },
                table:function () {
                    return "caseEstateTable";//楼盘 table
                }
            },
            caseBuild: {
                frm: function () {
                    return "frmCaseBuild";
                },
                table: function () {
                    return "caseBuildTable";
                }
            },
            caseUnit: {
                frm: function () {
                    return "frmCaseUnit";
                },
                table: function () {
                    return "caseUnitTable";
                }
            },
            caseHouse: {
                frm: function () {
                    return "frmCaseHouse";
                },
                table: function () {
                    return "caseHouseTable";
                }
            }
        }
    };

    BaseViewFun.prototype.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    }

    BaseViewFun.prototype.WriteSelectData = function (frm, data, name) {
        if (BaseViewFun.prototype.isEmpty(data)) {
            $("#" + frm + " ." + name).val(data).trigger("change");
        } else {
            $("#" + frm + " ." + name).val(null).trigger("change");
        }
    }

    BaseViewFun.prototype.WriteSelectData2 = function (data, id) {
        if (BaseViewFun.prototype.isEmpty(data)) {
            $("#" + id).val(data).trigger("change");
        } else {
            $("#" + id).val(null).trigger("change");
        }
    }

    BaseViewFun.prototype.event = {
        selectInit: function () {
            $("#province").select2();
            $("#city").select2();
            AssessCommon.initAreaInfo({
                provinceTarget: $("#province"),
                cityTarget: $("#city"),
                districtTarget: $("#district"),
                provinceValue: '',
                cityValue: '',
                districtValue: ''
            });
            $("#" + BaseViewFun.prototype.config.father.caseBlock.frm() + " .blockId").select2();
        },
        monitor: function () {
            $("#" + BaseViewFun.prototype.config.father.caseBlock.frm() + " .blockId").change(function () {
                var item = $("#" + BaseViewFun.prototype.config.father.caseBlock.frm() + " .blockId").eq(1).val();
                $.ajax({
                    url: "${pageContext.request.contextPath}/dataBlock/getDataBlockById",
                    type: "get",
                    dataType: "json",
                    data: {id: item},
                    success: function (result) {
                        if (result.ret) {
                            var data = result.data;
                            if (BaseViewFun.prototype.isEmpty(data)) {
                                $("#" + BaseViewFun.prototype.config.father.caseEstate.frm()).initForm({blockId: data.id});
                                // BaseViewFun.prototype.WriteSelectData2(data.province, "province");
                                // BaseViewFun.prototype.WriteSelectData2(data.city, "city");
                                // BaseViewFun.prototype.WriteSelectData2(data.district, "district");
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            });
        }
    };
    var baseFun = new BaseViewFun();

    baseFun.caseBlock = {
        init: function () {

        }
    }

    baseFun.caseEstate = {
        newWindow: function () {
            var href = "${pageContext.request.contextPath}/caseEstate/appView";
            var estate = formParams(baseFun.config.father.caseEstate.frm());
            if (baseFun.isEmpty(estate)) {
                if (baseFun.isEmpty(estate.blockId)) {
                    href += "?blockId=" + estate.blockId;
                    window.open(href, "");
                } else {
                    toastr.success('请先选择板块');
                }
            }
        },
        find: function () {
            var itemVal = $("#" + baseFun.config.father.caseEstate.frm()).find("[name='search']").val();
            if (!baseFun.isEmpty(itemVal)){
                toastr.success('请在输入框输入数据后 在按此按钮');
                return false;
            }
            $.ajax({
                url: "${pageContext.request.contextPath}/caseEstate/getCaseEstateList",
                type: "get",
                dataType: "json",
                async: true,
                data: {name: itemVal},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (baseFun.isEmpty(data)){
                            $("#" + BaseViewFun.prototype.config.father.caseEstate.frm()).initForm(data[0]);
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        },
        searchInput: function () {
            $("#" + baseFun.config.father.caseEstate.frm() + " input[name='search']").autocomplete(
                {
                    source: function (request, response) {
                        var itemVal = $("#" + baseFun.config.father.caseEstate.frm() + " input[name='search']").val();
                        $.ajax({
                            url: "${pageContext.request.contextPath}/caseEstate/autoCompleteCaseEstate",
                            type: "get",
                            dataType: "json",
                            data: {
                                maxRows: 10,
                                name: itemVal
                            },
                            success: function (result) {
                                response($.each(result.data, function (i, item) {
                                    return {
                                        label: item.value,
                                        value: item.key
                                    }
                                }));
                            }
                        });
                    },
                    minLength: 2,
                    select: function (event, ele) {
                    }
                }
            );
        },
        editData:function () {
            var estate = formParams(baseFun.config.father.caseEstate.frm());
            if (!baseFun.isEmpty(estate)){
                toastr.success("请先选择楼盘");
                return false;
            }
            if (!baseFun.isEmpty(estate.id)){
                toastr.success("请先选择楼盘");
                return false;
            }
            var href = "${pageContext.request.contextPath}/caseEstate/editView";
            href += "?id=" + estate.id;
            window.open(href, "");
        },
        removeData:function () {
            var estate = formParams(baseFun.config.father.caseEstate.frm());
            if (!baseFun.isEmpty(estate)){
                toastr.success("请先选择楼盘");
                return false;
            }
            if (!baseFun.isEmpty(estate.id)){
                toastr.success("请先选择楼盘");
                return false;
            }
            console.log(estate);
            $.ajax({
                url: "${pageContext.request.contextPath}/caseEstate/deleteCaseEstateById",
                type: "post",
                dataType: "json",
                async: false,
                data: {id: estate.id},
                success: function (result) {
                    if (result.ret) {
                        toastr.success(result.data);
                    }
                },
                error: function (result) {
                    console.log(result);
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        },
        init: function () {
        }
    };


    baseFun.caseBuild = {
        addData: function () {
            var estate = formParams(baseFun.config.father.caseEstate.frm());
            if (!baseFun.isEmpty(estate)){
                toastr.success("请先选择楼盘");
                return false;
            }
            if (!baseFun.isEmpty(estate.id)){
                toastr.success("请先选择楼盘");
                return false;
            }
            var href = "${pageContext.request.contextPath}/caseBuilding/appView";
            href += "?estateId=" + estate.id;
            window.open(href, "");
        },
        editData: function () {

        },
        copyData: function () {

        },
        removeData: function () {

        },
        loadDataList: function () {
            var estate = formParams(baseFun.config.father.caseEstate.frm());
            if (!baseFun.isEmpty(estate)){
                estate = {id:null};
            }
            if (!baseFun.isEmpty(estate.id)){
                estate = {id:null};
            }
            var cols = [];
            cols.push({field: 'buildingNumber', title: '楼栋编号'});
            cols.push({field: 'name', title: '楼栋名称'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="baseFun.caseBuild.editData(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="baseFun.caseBuild.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="复制并新增" onclick="baseFun.caseBuild.copyData(' + row.id + ',\'tb_List\')"><i class="fa fa-copy"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + baseFun.config.father.caseBuild.table()).bootstrapTable('destroy');
            TableInit(baseFun.config.father.caseBuild.table(), "${pageContext.request.contextPath}/caseBuilding/getCaseBuildingList", cols, {
                estateId: estate.id
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        }
    };


    baseFun.caseUnit = {
        addData: function () {

        },
        editData: function () {

        },
        copyData: function () {

        },
        removeData: function () {

        },
        loadDataList: function () {
            var cols = [];
            cols.push({field: 'elevatorHouseholdRatio', title: '户梯比'});
            cols.push({field: 'unitNumber', title: '单元编号'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="baseFun.caseUnit.editData(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="baseFun.caseUnit.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="复制并新增" onclick="baseFun.caseUnit.copyData(' + row.id + ',\'tb_List\')"><i class="fa fa-copy"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + baseFun.config.father.caseUnit.table()).bootstrapTable('destroy');
            TableInit(baseFun.config.father.caseUnit.table(), "${pageContext.request.contextPath}/caseUnit/getCaseUnitList", cols, {
                name: $("#queryName").val()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        }
    };

    baseFun.caseHouse = {
        addData: function () {

        },
        editData: function () {

        },
        copyData: function () {

        },
        removeData: function () {

        },
        loadDataList: function () {
            var cols = [];
            cols.push({field: 'floor', title: '所在楼层'});
            cols.push({field: 'houseNumber', title: '房号'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="baseFun.caseHouse.editData(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="baseFun.caseHouse.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="复制并新增" onclick="baseFun.caseHouse.copyData(' + row.id + ',\'tb_List\')"><i class="fa fa-copy"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + baseFun.config.father.caseHouse.table()).bootstrapTable('destroy');
            TableInit(baseFun.config.father.caseHouse.table(), "${pageContext.request.contextPath}/caseHouse/getCaseHouseList", cols, {
                name: $("#queryName").val()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        }
    };


    $(function () {
        baseFun.event.selectInit();
        baseFun.event.monitor();
        baseFun.caseEstate.init();
        baseFun.caseBuild.loadDataList();
        baseFun.caseUnit.loadDataList();
        baseFun.caseHouse.loadDataList();
    });
</script>
