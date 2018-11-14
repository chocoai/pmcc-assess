<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-11-13
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <title>楼盘信息标注</title>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <div class="page-title" style="margin: 0px">
                <div class="title_left">
                    <h2>
                        ${estateName}
                    </h2>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-9">
                        <iframe name="mapFrame" src="${pageContext.request.contextPath}/map/estateTaggingDetail"
                                width="100%"
                                height="100%" frameborder="0"></iframe>
                    </div>
                    <div class="col-sm-3">
                        <table class="table" id="estateTaggingList"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    $(function () {
        var iframe = document.getElementById("mapFrame");
        if (iframe.attachEvent) {
            iframe.attachEvent("onload", function() {
                loadTaggingList();
            });
        } else {
            iframe.onload = function() {
                loadTaggingList();
            };
        }
    })

    //加载数据列表
    function loadTaggingList() {
        var cols = [];
        cols.push({field: 'content', title: '名称'});
        $("#estateTaggingList").bootstrapTable('destroy');
        TableInit("estateTaggingList", "${pageContext.request.contextPath}/basicEstateTagging/getBootstrapTableVo", cols, {
            estateId: '${estateId}'
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            pagination: false,
            onLoadSuccess: function (data) {
                $('.tooltips').tooltip();
                mapFrame.window.loadMarkerList(data.rows);//显示标注信息
            }
        });
    }


</script>
</body>
</html>
