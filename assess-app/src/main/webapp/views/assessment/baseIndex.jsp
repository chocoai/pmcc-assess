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
                <div class="x_panel">
                    <div class="x_title">
                        <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                            ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                        </h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <div class="form-horizontal">
                            <div class="form-group ">
                                <div>
                                    <label class="col-sm-1 control-label">
                                        流程
                                    </label>
                                    <div class="col-sm-2">
                                        <select class="form-control search-select select2" id="boxId" onchange="loadActivity()">
                                            <option value="-1" selected>-全部-</option>
                                            <option value="0">默认考核模板</option>
                                            <c:forEach var="item" items="${boxRe}">
                                                <option value="${item.id}">${item.cnName}(V${item.versionNumber})</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div>
                                    <label class="col-sm-1 control-label">
                                        节点
                                    </label>
                                    <div class="col-sm-2">
                                        <select class="form-control search-select select2" id="activityId">
                                            <option value="-1" selected>-全部-</option>
                                            <option value="0">默认考核模板</option>
                                        </select>
                                    </div>
                                </div>
                                <div>
                                    <label class="col-sm-1 control-label">
                                        考核标准
                                    </label>
                                    <div class="col-sm-2">
                                        <input type="text" id="des" class="form-control">
                                    </div>
                                </div>
                                <div class="col-sm-3">
                                    <button type="button" class="btn btn-primary" onclick="reloadList();">
                                        查询
                                    </button>
                                    <button type="button" class="btn btn-success" onclick="addAssessmentItem()"
                                            data-toggle="modal" href="#divBox"> 标准模板
                                    </button>
                                </div>
                            </div>
                        </div>

                        <table id="tb_boxReList" class="table table-bordered" >

                        </table>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>

<%@include file="/views/share/tools/assessmentItemPopup.jsp" %>
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">


    $(function () {
        var cols = [];
        cols.push({field: 'id', title: 'ID', visible: false});
        cols.push({field: 'boxNameCn', title: '流程'});
        cols.push({field: 'boxReActivitiNameCn', title: '节点'});
        cols.push({field: 'assessmentDes', title: '考核标准', width: '400px'});
        cols.push({field: 'minScore', title: '最低得分'});
        cols.push({field: 'maxScore', title: '最高得分'});
        cols.push({field: 'itemValid', title: '有效', formatter: function (value) {
            return getBoolChs(value);
        }});
        TableInit("tb_boxReList", "${pageContext.request.contextPath}/AssessmentItem/getAssessmentItemList", cols, {
            boxId: $("#boxId").val(),
            activityId: $("#activityId").val(),
            des: $("#des").val()
        }, {
            showColumns: false,
            showRefresh: false,
            uniqueId: "id",
            search: false
        });
        $("#activityId").select2();
        $("#boxId").select2();
    });
    
    function loadActivity() {
        var boxId=parseInt($("#boxId").val());
        var retHtml = '<option value="-1" selected>-全部-</option>';
        retHtml+='<option value="0">默认考核模板</option>';
        if(boxId>0) {
            $.ajax({
                url: getContextPath() + "/AssessmentItem/getBoxReActivityByBoxId",
                type: "get",
                dataType: "json",
                data: {
                    boxId: boxId
                },
                success: function (result) {
                    if (result.ret) {

                        $.each(result.data, function (i, item) {
                            retHtml += ' <option value="' + item.id + '">' + item.cnName + '</option>';
                        });
                    }
                    $("#activityId").html(retHtml);
                    $("#activityId").select2();
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        }
        else {
            $("#activityId").html(retHtml);
            $("#activityId").select2();
        }
    }
    
    //刷新
    function reloadList() {
        var opt = {
            url: "${pageContext.request.contextPath}/AssessmentItem/getAssessmentItemList",
            silent: true,
            query: {
                boxId: $("#boxId").val(),
                activityId: $("#activityId").val(),
                des: $("#des").val()
            }
        };
        $("#tb_boxReList").bootstrapTable('refresh', opt);
    }



</script>


</html>
