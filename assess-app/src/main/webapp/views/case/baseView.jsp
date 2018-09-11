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
                                <div class="col-sm-5">
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
                                <div class="col-sm-5">
                                    <select id="city" name="city" class="form-control search-select select2"
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
                                    <input type="text" class="form-control"
                                           onkeyup="baseFun.caseEstate.searchInput(this)"/>
                                </div>
                            </div>
                            <div class="x-valid">
                                <div class="col-sm-3">
                                    <button class="btn btn-default">查询</button>
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
                                    <button class="btn btn-default">新增</button>
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
            caseEstate: {
                frm: function () {
                    return "frmCaseEstate";
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
            caseUnit:{
                frm:function () {
                    return "frmCaseUnit" ;
                },
                table:function () {
                    return "caseUnitTable";
                }
            },
            caseHouse:{
                frm:function () {
                    return "frmCaseHouse" ;
                },
                table:function () {
                    return "caseHouseTable";
                }
            }
        }
    };
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
        }
    };
    var baseFun = new BaseViewFun();

    baseFun.caseEstate = {
        newWindow: function () {
            var href = "${pageContext.request.contextPath}/caseEstate/view";
            window.location.href = href;
        },
        searchInput: function (target) {
            var availableTags = [
                "ActionScript",
                "AppleScript",
                "Asp",
                "BASIC",
                "C",
                "C++",
                "Clojure",
                "COBOL",
                "ColdFusion",
                "Erlang",
                "Fortran",
                "Groovy",
                "Haskell",
                "Java",
                "JavaScript",
                "Lisp",
                "Perl",
                "PHP",
                "Python",
                "Ruby",
                "Scala",
                "Scheme"
            ];
            $(target).autocomplete({
                source: availableTags
            });
        }
    };


    baseFun.caseBuild = {
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
        baseFun.caseBuild.loadDataList();
        baseFun.caseUnit.loadDataList();
        baseFun.caseHouse.loadDataList();
    });
</script>
