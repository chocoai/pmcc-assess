<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.css">
    <script src='${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.js?v=${assessVersion}'></script>
</head>


<body>
<div class="wrapper">
    <%@include file="/views/share/main_navigation.jsp" %>
    <%@include file="/views/share/main_head.jsp" %>
    <div class="main-panel">
        <div class="content">
            <div class="panel-header bg-primary-gradient">
                <div class="page-inner py-5">
                </div>
            </div>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">${baseViewDto.currentMenu.name}</div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frmCaseEstate" class="form-horizontal">
                                    <div class="form-group form-inline">
                                        <label class="col-md-1 col-form-label">省</label>
                                        <div class="col-md-2 p-0">
                                            <select name="province" class="form-control input-full search-select select2">
                                            </select>
                                        </div>
                                        <label class="col-md-1 col-form-label">市</label>
                                        <div class="col-md-2 p-0">
                                            <select name="city" class="form-control input-full search-select select2">
                                            </select>
                                        </div>
                                        <label class="col-md-1 col-form-label">楼盘名称</label>
                                        <div class="col-md-2 p-0">
                                            <input type="text" class="form-control input-full" name="search"/>
                                        </div>
                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="baseFun.caseEstate.find()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                    </div>
                                </form>
                                <table class="table table-bordered" id="caseEstateTable">
                                    <!-- cerare document add ajax data-->
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/map.position.js?v=${assessVersion}"></script>
<script src='${pageContext.request.contextPath}/js/autocomplete/estate.case.js?v=${assessVersion}'></script>
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
                table: function () {
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

    BaseViewFun.prototype.event = {};
    var baseFun = new BaseViewFun();

    baseFun.caseEstate = {
        /**
         * @author:  zch
         * 描述:加载数据列表
         * @date:2018-09-13
         **/
        loadDataList: function (flag) {
            var estate = formParams(baseFun.config.father.caseEstate.frm());
            if (!flag) {
                estate = {search: null, city: null, district: null, province: null};
            }
            var cols = [];
            cols.push({field: 'estateName', title: '名称'});
            cols.push({field: 'provinceName', title: '省'});
            cols.push({field: 'cityName', title: '市'});
            cols.push({
                field: 'id', title: '查询', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" onclick="baseFun.caseEstate.findData(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="详情">';
                    str += '<i class="fa fa-search"></i>';
                    str += '</button>';
                    return str;
                }
            });
            $("#" + baseFun.config.father.caseEstate.table()).bootstrapTable('destroy');
            TableInit(baseFun.config.father.caseEstate.table(), "${pageContext.request.contextPath}/basicApplyBatch/getCaseEstateListByName", cols, {
                name: estate.search,
                city: estate.city,
                province: estate.province
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                },
            });

        },
        find: function () {
            var itemVal = $("#" + baseFun.config.father.caseEstate.frm()).find("[name='search']").val();
            baseFun.caseEstate.loadDataList(true);
        },
        /**
         * @author:  zch
         * 描述:详情数据
         * @date:2018-09-13
         **/
        findData: function (applyBatchId) {
            var href = "${pageContext.request.contextPath}/basic/checkCaseDetail";
            href += "?applyBatchId=" + applyBatchId;
            window.open(href, "");
        },

        findDataMap: function (id) {
            var href = "${pageContext.request.contextPath}/basic/estateCaseMap";
            href += "?estateId=" + id;
            window.open(href, "");
        }
    };


    $(function () {
        AssessCommon.initAreaInfo({
            provinceTarget: $("#" + baseFun.config.father.caseEstate.frm()).find('[name=province]'),
            cityTarget: $("#" + baseFun.config.father.caseEstate.frm()).find('[name=city]')
        });
        baseFun.caseEstate.loadDataList();
        $("#" + baseFun.config.father.caseEstate.frm() + " input[name='search']").apEstate({
            getProvince: function () {
                return $("#" + baseFun.config.father.caseEstate.frm()).find("select[name='province']").val();
            },
            getCity: function () {
                return $("#" + baseFun.config.father.caseEstate.frm()).find("select[name='city']").val();
            },
            onSelect: function (id, name) {
                $("#" + baseFun.config.father.caseEstate.frm() + " input[name='search']").val(name);
            }
        });
    });
</script>
