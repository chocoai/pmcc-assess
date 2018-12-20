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
            <div class="row">
                <div class="col-xs-12">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>
                                <i class="fa ${baseViewDto.currentMenu.icon}"></i>
                                ${baseViewDto.currentMenu.name}
                            </h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <table id="tb_csr_projectList" class="table table-bordered">
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

<script type="application/javascript">
    $(function () {
        loadCsrProjectInfoList("");
    })

    //债权人列表
    function loadCsrProjectInfoList(name) {
        var cols = [];
        cols.push({field: 'name', title: '项目名称'});
        cols.push({field: 'statusName', title: '项目状态'});
        cols.push({field: 'projectCategoryName', title: '项目类别'});
        cols.push({field: 'customerTypeName', title: '客户类型'});
        cols.push({
            field: 'opation', title: '操作', formatter: function (value, row, index) {
                var str = "<a target='_blank' href='${pageContext.request.contextPath}/csrProjectInfo/projectDetails?csrProjectInfoID=" + row.id + "' style='margin-left: 5px;' data-placement='top' data-original-title='查看详情' class='btn btn-xs btn-success tooltips' ><i class='fa fa-search fa-white'></i></a>";
                return str;
            }
        });
        $("#tb_csr_projectList").bootstrapTable('destroy');
        TableInit("tb_csr_projectList", "${pageContext.request.contextPath}/projectCenter/getCsrProjectInfoList", cols, {
            name: name
        }, {
            showColumns: false,
            showRefresh: true,
            search: true,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }
</script>
</body>
</html>
