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
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <form id="frmQuery" class="form-horizontal">
                                <input type="hidden" name="readOnly" value="${readOnly}">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">省</label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <select name="province"
                                                    class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">市</label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <select name="city" class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">区县</label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <select name="district"
                                                    class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">乡镇街道</label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <input placeholder="乡镇名称" class="form-control" name="townShipName"
                                                   type="text">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">权利类型</label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <select name="landRightType" class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">创建人</label>
                                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                            <input placeholder="创建人" class="form-control" name="creator" type="text">
                                        </div>
                                    </div>
                                    <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                        <button type="button" class="btn btn-primary"
                                                onclick="findQuery();">
                                            查询
                                        </button>
                                        <a class="btn btn-success" onclick="comeInLandLevelIndex();">新增</a>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <table class="table table-bordered" id="tb_FatherList">
                                <!-- cerare document add ajax data-->
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<%@include file="/views/data/landModelDir/landModelApproval.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/views/data/landModelDir/landLevel.js?v=${assessVersion}"></script>
<script type="text/javascript">
    function findQuery() {
        landLevel.loadLandLevelList({status: '', creator: null, processInsId: null, checkbox: true});
    }

    function comeInLandLevelIndex() {
        var result = landLevel.config.table.bootstrapTable('getSelections');
        var data = [];
        if (result.length > 0) {
            $.each(result, function (i, item) {
                data.push(item.id);
            });
        }
        var href = "${pageContext.request.contextPath}/dataLandLevel/comeInLandLevelIndex";
        if (data.length != 0) {
            href += "?id=" + data.join(",");
            Alert(
                "确认将勾选的数据修改",
                2,
                function () {
                    landLevel.config.table.bootstrapTable('uncheckAll');
                }, function () {
                    window.open(href);
                });
        } else {
            window.open(href);
        }
    }

    $(document).ready(function () {
        findQuery();
        (function (frm, data) {
            AssessCommon.initAreaInfo({
                provinceTarget: frm.find("select[name='province']"),
                cityTarget: frm.find("select[name='city']"),
                districtTarget: frm.find("select[name='district']"),
                provinceValue: data.province,
                cityValue: data.city,
                districtValue: data.district
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.projectDeclareLandCertificateType, data.landRightType, function (html, data) {
                frm.find("select[name='landRightType']").empty().html(html).trigger('change');
            });
        }(landLevel.config.frmQuery, {}));
    });

</script>
</html>
