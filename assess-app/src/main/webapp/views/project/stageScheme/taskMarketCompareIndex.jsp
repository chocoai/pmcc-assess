<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>
                        房价指数
                    </h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content collapse">
                    <form id="frm_query_house_price_index" class="form-horizontal">
                        <div class="form-group ">
                            <div>
                                <label class="col-sm-1 control-label">
                                    开始时间
                                </label>
                                <div class="col-sm-1">
                                    <input type="text" data-date-format="yyyy-mm-dd"
                                           placeholder="开始日期" name="startTime"
                                           class="form-control date-picker dbdate">
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">
                                    结束时间
                                </label>
                                <div class="col-sm-1">
                                    <input type="text" data-date-format="yyyy-mm-dd"
                                           placeholder="结束日期" name="endTime"
                                           class="form-control date-picker dbdate">
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">
                                    省
                                </label>
                                <div class="col-sm-1">
                                    <select name="province" class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">
                                    市
                                </label>
                                <div class="col-sm-1">
                                    <select name="city" class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">
                                    区县
                                </label>
                                <div class="col-sm-1">
                                    <select name="district" class="form-control search-select select2">
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-2">
                                <button type="button" class="btn btn-primary" onclick="loadHousePriceIndexList();">
                                    查询
                                </button>
                            </div>
                        </div>
                    </form>
                    <table class="table table-bordered" id="tb_house_price_index_list">
                    </table>
                </div>
            </div>
            <jsp:include page="/views/method/module/marketCompareIndex.jsp"></jsp:include>
            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button id="btn_save" class="btn btn-warning" onclick="save();">
                            保存<i style="margin-left: 10px" class="fa fa-save"></i>
                        </button>
                        <button id="btn_submit" class="btn btn-success" onclick="submit();">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<link href="${pageContext.request.contextPath}/assets/x-editable/css/bootstrap-editable.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/assets/x-editable/js/bootstrap-editable.min.js"></script>
<input type="hidden" id="marketCompareJSON" value='${marketCompareJSON}'>
<input type="hidden" id="fieldsJSON" value='${fieldsJSON}'>
<input type="hidden" id="evaluationJSON" value='${evaluationJSON}'>
<input type="hidden" id="casesJSON" value='${casesJSON}'>
<input type="hidden" id="casesAllJSON" value='${casesAllJSON}'>

<script type="text/javascript">
    $(function () {
        //市场比较法信息初始化
        marketCompare.init({
            marketCompare: JSON.parse($("#marketCompareJSON").val()),
            fields: JSON.parse($("#fieldsJSON").val()),
            evaluation: JSON.parse($("#evaluationJSON").val()),
            casesAll: JSON.parse($("#casesAllJSON").val()),
            mcId: '${mcId}',
            cases: JSON.parse($("#casesJSON").val())
        });


        loadHousePriceIndexList();
        AssessCommon.initAreaInfo({
            useDefaultText: false,
            provinceTarget: $("#frm_query_house_price_index").find("[name='province']"),
            cityTarget: $("#frm_query_house_price_index").find("[name='city']"),
            districtTarget: $("#frm_query_house_price_index").find("[name='district']"),
            provinceValue: "",
            cityValue: "",
            districtValue: ""
        });
    })

    //加载房价指数列表
    function loadHousePriceIndexList() {
        var cols = [];
        cols.push({field: 'yearMonthCalendarName', title: '日期'});
        cols.push({field: 'provinceName', title: '省'});
        cols.push({field: 'cityName', title: '市'});
        cols.push({field: 'districtName', title: '县'});
        cols.push({field: 'indexCalendar', title: '指数'});
        $("#tb_house_price_index_list").bootstrapTable('destroy');
        TableInit("tb_house_price_index_list", "${pageContext.request.contextPath}/housePriceIndex/list", cols, {
            startTime: $("#frm_query_house_price_index").find("[name='startTime']").val(),
            endTime: $("#frm_query_house_price_index").find("[name='endTime']").val(),
            province: $("#frm_query_house_price_index").find("[name='province']").val(),
            city: $("#frm_query_house_price_index").find("[name='city']").val(),
            district: $("#frm_query_house_price_index").find("[name='district']").val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }
</script>
<script type="application/javascript">
    //提交
    function submit() {
        if (!marketCompare.valid()) {
            return false;
        }
        var data = {};
        data.marketCompare = marketCompare.getData();

        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(data));
        }
        else {
            submitToServer(JSON.stringify(data));
        }
    }

    //保存
    function save() {
        marketCompare.save(function () {
            Alert('保存成功');
        });
    }

</script>

</html>

