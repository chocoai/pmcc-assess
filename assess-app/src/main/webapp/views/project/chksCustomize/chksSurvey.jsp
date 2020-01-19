
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <c:if test="${!empty boxReActivityDtoList}">

        <c:forEach items="${boxReActivityDtoList}" var="itemActivityDto">
            <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                <table class="table" id="chksTableList${itemActivityDto.id}">
                    <thead>
                    <tr>
                        <th width="3%">序号</th>
                        <th width="7%">节点名称</th>
                        <th width="50%">考核标准</th>
                        <th width="10%">打分</th>
                        <th width="10%">说明</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
            <script>
                $(function () {
                    assessmentCommonHandle.loadChksServerNew('${itemActivityDto.id}',$("#chksTableList${itemActivityDto.id}").find("tbody"),'${tbType}') ;
                });
            </script>
            <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6 col-xs-offset-6 col-sm-offset-6 col-md-offset-6 col-lg-offset-6">
                <button class="btn btn-success" onclick="saveAssessmentItem('${itemActivityDto.id}','${itemActivityDto.cnName}','#chksTableList${itemActivityDto.id}','${tbType}');">
                    保存考核记录
                </button>
            </div>
        </c:forEach>
        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
            <table class="table" id="boxReActivityDtoTableView">
            </table>
        </div>
    </c:if>
</div>

<div class="x_content">
    <c:if test="${!empty spotAssessmentProjectPerformanceList}">
        <c:forEach items="${spotAssessmentProjectPerformanceList}" var="entryItem"
                   varStatus="userStatus">

            <div class="row">
                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                    <h2 class="text-center">${userStatus.index+1} : ${entryItem.key.key}
                        <input type="button" class="btn btn-xs btn-primary" value="抽查"
                               onclick="showChkSpotAssessmentParent('${entryItem.key.value}','${entryItem.key.explain}');">
                    </h2>
                </div>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <td width='5%' align='center'>被考核人</td>
                        <td width='5%' align='center'>考核人</td>
                        <td width='60%' align='center'>考核详情</td>
                        <td width='5%' align='center'>得分</td>
                        <td width='5%' align='center'>考核时间</td>
                        <td width='20%' align='center'>综合说明</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${entryItem.value}" var="item">
                        <tr>
                            <td width='5%' align='center'>${item.byExaminePeopleName}</td>
                            <td width='5%' align='center'>${item.examinePeopleName}</td>
                            <td width='60%' align='center'>
                                <table class="table table-striped">
                                    <tbody>
                                    <c:forEach items="${item.detailList}" var="assessmentItem">
                                        <tr>
                                            <td width="50%" align="center">考核标准: ${assessmentItem.content}</td>
                                            <td width="10%" align="center">实际得分: ${assessmentItem.actualScore}</td>
                                            <td width="40%" align="center">考核说明: ${assessmentItem.remark}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </td>
                            <td width='5%' align='center'>${item.examineScore}</td>
                            <td width='5%' align='center'>
                                <fmt:formatDate value="${item.examineDate}"
                                                pattern="yyyy-MM-dd"/>
                            </td>
                            <td width='20%' align='center'>${item.remarks}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:forEach>
    </c:if>
</div>