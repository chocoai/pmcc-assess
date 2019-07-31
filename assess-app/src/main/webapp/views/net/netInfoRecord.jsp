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
                            <div>
                                <label class="col-sm-1 control-label">
                                    标题
                                </label>
                                <div class="col-sm-2">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="标题" id="queryTitle" name="queryTitle"
                                           class="form-control">
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">
                                    网站
                                </label>
                                <div class="col-sm-2">
                                    <select class="form-control" required id="queryWebName">
                                        <option value="">--请选择--</option>
                                        <c:if test="${not empty webTypes}">
                                            <c:forEach items="${webTypes}" var="item">
                                                <option value="${item}">${item}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">省
                                </label>
                                <div class="col-sm-2">
                                    <select id="province" name="province"
                                            class="form-control search-select select2" required="required">
                                    </select>
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">市
                                </label>
                                <div class="col-sm-2">
                                    <select id="city" name="city" class="form-control search-select select2"
                                            required="required">
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group ">
                            <div>
                                <label class="col-sm-1 control-label">
                                    内容
                                </label>
                                <div class="col-sm-2">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="内容" id="queryContent" name="queryContent"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    抓取日期
                                </label>
                                <div class="col-sm-2">
                                    <input placeholder="抓取日期" id="queryEndTime" data-date-format="yyyy-mm-dd"
                                           class="form-control date-picker dbdate roomTime">
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <button type="button" class="btn btn-primary"
                                        onclick="dataBuilder.prototype.loadDataDicList()">
                                    查询
                                </button>
                                <button type="button" class="btn btn-primary"
                                        onclick="dataBuilder.prototype.getOldData()">
                                    获取两年前数据
                                </button>

                            </div>
                        </div>
                    </form>
                    <table class="table table-bordered" id="transaction_List">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    $(function () {
        dataBuilder.prototype.loadDataDicList();
        AssessCommon.initAreaInfo({
            provinceTarget: $("#province"),
            cityTarget: $("#city"),
            districtTarget: $("#district"),
            provinceValue:'',
            cityValue: '',
            districtValue: ''
        })
    });
    var dataBuilder = function () {

    };
    dataBuilder.prototype = {
        config: function () {
            var data = {};
            data.table = "transaction_List";
            return data;
        },
        loadDataDicList: function () {
            var cols = [];
            cols.push({field: 'title',title: '标题', width: '20%'});
            cols.push({field: 'province', title: '省', width: '10%'});
            cols.push({field: 'city', title: '市', width: '10%'});
            cols.push({field: 'sourceSiteName', title: '来源网站', width: '20%'});
            cols.push({
                field: 'gmtCreated', title: '抓取时间',width: '10%', formatter: function (value, row, index) {
                    return formatDate(row.gmtCreated, false);
                }
            });
            cols.push({field: 'content', title: '内容', width: '30%'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="查看详情" onclick="dataBuilder.prototype.openItem('  + index + ')"><i class="fa fa-eye fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + dataBuilder.prototype.config().table).bootstrapTable('destroy');
            TableInit(dataBuilder.prototype.config().table, "${pageContext.request.contextPath}/netInfoRecordController/getInfoRecordList", cols, {
                queryTitle: $("#queryTitle").val(),
                queryWebName: $("#queryWebName").val(),
                province: $("#province").val(),
                city: $("#city").val(),
                queryContent: $("#queryContent").val(),
                queryEndTime: $("#queryEndTime").val()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        openItem:function (index) {
            var row = $("#transaction_List").bootstrapTable('getData')[index];
            console.log(row);
            if(row.sourceSiteUrl) {
                window.open(row.sourceSiteUrl, "");
            }
        },
        getOldData : function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/netInfoRecordController/getOldData",
            type: "post",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    toastr.success('获取数据成功');
                }
                else {
                    Alert("获取数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }
    }



</script>


</html>
