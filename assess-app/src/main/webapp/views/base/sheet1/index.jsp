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
            <div class="x_panel">
                <div class="x_title">
                    <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                        ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <span id="toolbarSub">
                       <button type="button" class="btn btn-success" onclick="bulidReport()"> 生成报告</button>
                    </span>
                    <table class="table table-bordered" id="tb_List">
                    </table>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
</html>
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    $(function () {
        loadList();
    })
    //加载代理数据列表
    function loadList() {
        var cols = [];
//        cols.push({
//            field: 'chk', align: 'center', title: '', width: '3%', formatter: function (value, row) {
//                return "<input type='checkbox' class='backTask' id='chk_" + row.sjhth + "'/>";
//            }
//        });
        cols.push({field: 'checkbox', checkbox: true});
        cols.push({field: 'khxm', title: '借款人'});
        cols.push({field: 'sfzhm', title: '身份证'});
        cols.push({field: 'ejfh', title: '分行'});
        cols.push({field: 'attachmentHtml', title: '报告'});
        TableInit("tb_List", "${pageContext.request.contextPath}/sheet1/getSheet1List", cols, {}, {});
    }

    function bulidReport() {


        var result = $("#tb_List").bootstrapTable('getSelections');
        var ids = "";
        if (result.length == 0) {
            Alert("请至少选择一个客户");
            return false;
        }
        else {
            $.each(result, function (i, item) {
                ids += item.sjhth + ",";
            });
        }
        if (ids != "") {
            ids = ids.substring(0, ids.length - 1);
        }
        else {
            Alert("请至少选择一个客户");
            return false;
        }

        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/sheet1/generateTemp",
            data: {
                ids: ids
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("报告生成成功!", 1, null, function () {
                        TableReload("tb_List");
                    });
                }
                else {
                    Alert("生成报告失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });

    }
</script>


