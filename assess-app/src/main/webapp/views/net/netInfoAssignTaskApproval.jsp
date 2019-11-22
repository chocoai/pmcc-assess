<%--
  Created by IntelliJ IDEA.
  User: huhao
  Date: 2018/9/3
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>拍卖信息补全</title>

    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md">


<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0px">
            <!-- 公共模块引用 -->
            <%@include file="/views/share/form_head.jsp" %>
            <!-- 公共模块end -->


            <div class="x_panel">

                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>拍卖信息补全
                    </h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="row">
                        <div class="panel-body">
                            <form id="master_form" class="form-horizontal">
                                <table class="table table-bordered" id="transaction_List">
                                    <!-- cerare document add ajax data-->
                                </table>
                            </form>

                        </div>

                    </div>
                </div>
            </div>
            <!-- 公共尾部模块引用 -->
            <!-- 审批用 -->
            <%@include file="/views/share/form_approval.jsp" %>

            <%@include file="/views/share/form_log.jsp" %>
            <!-- 尾部end -->
        </div>
    </div>
</div>

<%@include file="/views/share/main_footer.jsp" %>
</body>
</html>
<script type="text/javascript">
    function saveform() {
        if (!$("#frm_approval").valid()) {
            return false;
        }
        var data = formApproval.getFormData();
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/netInfoAssignTask/approvalCommit",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }

    $(function(){
        detailInfo.prototype.loadDataDicList();
    })

    var detailInfo = function () {

    };
    detailInfo.prototype = {
        config: function () {
            var data = {};
            data.table = "transaction_List";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            return data;
        },
        loadDataDicList: function () {
            var cols = [];
            cols.push({field: 'provinceName', title: '省', width: '5%'});
            cols.push({field: 'cityName', title: '市', width: '5%'});
            cols.push({field: 'districtName', title: '区', width: '5%'});
            cols.push({field: 'content', title: '内容', width: '70%'});
            cols.push({
                field: 'id', title: '查看网址', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="查看网址" onclick="detailInfo.prototype.openItem(' + index + ')"><i class="fa fa-eye fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + detailInfo.prototype.config().table).bootstrapTable('destroy');
            TableInit(detailInfo.prototype.config().table, "${pageContext.request.contextPath}/netInfoRecordController/getInfoRecordListByIds", cols, {
                ids: '${netInfoAssignTask.netInfoIds}'
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        openItem: function (index) {
            var row = $("#transaction_List").bootstrapTable('getData')[index];
            if (row.sourceSiteUrl) {
                window.open(row.sourceSiteUrl, "");
            }
        }
    }
</script>