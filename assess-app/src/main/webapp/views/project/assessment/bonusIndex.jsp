<%--
  Created by IntelliJ IDEA.
  User: huhao
  Date: 2018/01/29
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>外勤考核</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md">
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        外勤考核调整
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frmQuery" class="form-horizontal">
                                    <input type="hidden" name="bonusId" value="${projectAssessmentBonus.id}">
                                    <input type="hidden" name="projectManager" value="${projectManager}">
                                </form>
                                <table class="table table-bordered" id="tbAssessmentBonusList"></table>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">
                        <div class="card-body">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                取消
                            </button>
                            <button id="commit_btn" class="btn btn-success" onclick="masterObj.commit();">
                                提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function () {
        loadAssementBonusItemList();
    })

    //加载数据列表
    function loadAssementBonusItemList() {
        var cols = [];
        cols.push({field: 'projectName', title: '项目名称', width: '10%'});
        cols.push({field: 'projectManager', title: '项目经理', width: '10%'});
        cols.push({field: 'totalScore', title: '总得分', width: '10%'});
        cols.push({field: 'status', title: '状态', width: '10%'});
        cols.push({
            field: 'memberScore', title: '成员得分', width: '14%', formatter: function (value, row, index) {
                return formatDate(row.gmtCreated, true);
            }
        });
        cols.push({
            field: 'id', title: '操作', width: '10%', formatter: function (value, row, index) {
                var str = "";
                if (row.projectStatus) {
                    if (row.projectStatus == '草稿') {
                        str += '<button type="button" onclick="editHref(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="继续填写">';
                        str += '<i class="fa fa-pen"></i>';
                        str += '</button>';
                        str += '<button type="button" onclick="projectClearData (' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-warning btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                        str += '<i class="fa fa-minus"></i>';
                        str += '</button>';
                    } else {
                        str += '<button type="button" onclick="checkDetail(' + row.id + ')" style="margin-left: 5px;" class="btn  btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看详情">';
                        str += '<i class="fa fa-search"></i>';
                        str += '</button>';
                    }
                }
                return str;
            }
        });
        var selectObj = formSerializeArray($("#frmQuery"));
        $("#tbAssessmentBonusList").bootstrapTable('destroy');
        TableInit("tbAssessmentBonusList", "${pageContext.request.contextPath}/projectAssessmentBonus/getAssessmentBonusItems", cols, selectObj, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }
</script>
