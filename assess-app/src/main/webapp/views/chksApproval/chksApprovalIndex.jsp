<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2018/3/28
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<%--<%@include file="share/main_head.jsp" %>--%>
<!-- start: MAIN CONTAINER -->
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="row">
                <div class="col-md-8">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                                ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                            </h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <table class="table table-bordered" id="tb_approval_business">
                            </table>

                        </div>
                    </div>
                </div>
                <div class="col-md-4" id="div_p">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>节点及人员</h2>
                            <div class="clearfix"></div>
                            <small id="small_title"></small>
                        </div>
                        <div class="x_content">
                            <table class="table table-bordered" id="tb_approval_user">

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
<script type="text/javascript">
    function divSize() {
        $("#div_p").css("top", ($(window).height() - $('#div_p').outerHeight()) / 2 + $(document).scrollTop());
    }
    $(function () {
        loadApprovalBusiness();
        loadApprovalUser();
        divSize();
        $(window).scroll(function () {
            divSize();
        });
    })
    function loadApprovalBusiness() {
        var cols = [];
        cols.push({field: 'processCnName', title: '流程'});
        cols.push({field: 'processTitle', title: '流程描述'});
        cols.push({field: 'businessProcessInsId', title: '流程编号'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var s = "<a href='" + row.businessUrl + "' target='_blank' style='margin-left: 5px;' data-placement='top' data-original-title='查看详情'  class='btn btn-xs btn-info tooltips'    ><i class='fa fa-search fa-white'></i></a>";
                if (row.bisCheck) {
                    s += "<a href='${pageContext.request.contextPath}/ChksAssess/detailsBusiness?processInsId=" + row.businessProcessInsId + "' target='_blank' style='margin-left: 5px;' data-placement='top' data-original-title='查看考核得分'  class='btn btn-xs btn-success tooltips'    ><i class='fa fa-search fa-white'></i></a>";
                }
                else {
                    s += "<a  href='${pageContext.request.contextPath}/ChksAssess/applyByProcessInsId?processInsId=" + row.businessProcessInsId + "' target='_blank' style='margin-left: 5px;' data-placement='top' data-original-title='考核'  class='btn btn-xs btn-warning tooltips'    ><i class='fa fa-gavel fa-white'></i></a>";
                }

                return s;
            }
        });
        TableInit("tb_approval_business", "${pageContext.request.contextPath}/ChksApprovalBusiness/getChksApprovalBusinessList", cols,
            {
                bisCheck: -1
            }, {
                onLoadSuccess: function () {
                    $(".tooltips").tooltip();
                },
                onClickRow: function (row, $element) {
                    $("#small_title").html(row.processTitle);
                    TableReload("tb_approval_user", "${pageContext.request.contextPath}/ChksApprovalBusiness/getChksApprovalInfoList",
                        {
                            processInsId: row.businessProcessInsId
                        });
                }

            });
    }

    function loadApprovalUser() {
        var cols = [];
        cols.push({field: 'activityName', title: '节点名称'});
        cols.push({field: 'userName', title: '操作人员'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var s = "";
                if (row.bisChks) {
                    s += "<a href='${pageContext.request.contextPath}/ChksAssess/editAssessChks?id=" + value + "' target='_blank' style='margin-left: 5px;' data-placement='top' data-original-title='修改'  class='btn btn-xs btn-warning tooltips'    ><i class='fa fa-edit fa-white'></i></a>";
                }
                else {
                    s += "<a href='${pageContext.request.contextPath}/ChksAssess/repairAssessChks?id=" + value + "' target='_blank' style='margin-left: 5px;' data-placement='top' data-original-title='补考'  class='btn btn-xs btn-info tooltips'    ><i class='fa fa-gavel fa-white'></i></a>";
                }
                return s;
            }
        });
        TableInit("tb_approval_user", "${pageContext.request.contextPath}/ChksApprovalBusiness/getChksApprovalInfoList", cols, {processInsId: "0"}, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
                divSize();
            }
        });
    }
</script>

</html>
