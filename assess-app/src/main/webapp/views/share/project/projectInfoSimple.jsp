<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-4-17
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en" class="no-js">
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>
            项目信息
            <small>
                <input type="button" id="btnViewProjectInfoMore" class="btn btn-success btn-xs" value="查看更多..."/>
            </small>
            <small>${projectInfo.projectClassName}/${projectInfo.projectTypeName}/${projectInfo.projectCategoryName}</small>
        </h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse" >
        <div class="form-horizontal">
            <input type="hidden" id="projectId" name="id" value="${projectInfo.id}">
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">项目名称</label>
                    <div class="col-sm-7">
                        <label class="form-control">${projectInfo.projectName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">紧急程度</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.urgencyName}</label>
                    </div>
                </div>

            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">评估基准日</label>
                    <div class="col-sm-3">
                        <label class="form-control"><fmt:formatDate value='${projectInfo.valuationDate}'
                                                                    pattern='yyyy-MM-dd'/></label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">委托目的</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.entrustPurposeName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">委托目的描述</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.remarkEntrustPurpose}</label></div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">价值类型</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.valueTypeName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">价值类型描述</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.remarkValueType}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">执业部门</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.departmentName}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">项目经理</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.projectMemberVo.userAccountManagerName}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">项目成员</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.projectMemberVo.userAccountMemberName}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">项目说明</label>
                    <div class="col-sm-11">
                        <label class="form-control">${projectInfo.remarks}</label></div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">附件</label>
                    <div class="col-sm-3">
                        <div id="_attachmentProjectInfoId"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        //显示附件
        FileUtils.getFileShows({
            target: "attachmentProjectInfoId",
            formData: {
                tableName: AssessDBKey.ProjectInfo,
                tableId: '${empty projectInfo.id?0:projectInfo.id}'
            },
            deleteFlag: false
        })
        //查看更多
        $("#btnViewProjectInfoMore").click(function (e) {
            window.open('${pageContext.request.contextPath}/projectInfo/projectInfoDetails?projectId=${projectInfo.id}');
            e.preventDefault();
            e.stopPropagation();
        })
    });
</script>
