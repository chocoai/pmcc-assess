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
    <div class="x_title">
        <h2>
            项目信息
            <small>
                <button type="button"
                        onclick="window.open('${pageContext.request.contextPath}/projectInfo/projectInfoDetails?projectId=${projectInfo.id}');return false;"
                        class="btn btn-success btn-xs">查看更多...
                </button>
            </small>
            <small>${projectInfo.projectClassName}/${projectInfo.projectTypeName}/${projectInfo.projectCategoryName}</small>
        </h2>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div class="form-horizontal">
            <input type="hidden" id="projectId" name="id" value="${projectInfo.id}">
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">项目名称</label>
                    <div class="col-sm-11">
                        <label class="form-control">${projectInfo.projectName}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">委托目的</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.entrustPurposeName}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">评估基准日</label>
                    <div class="col-sm-3">
                        <label class="form-control"><fmt:formatDate value='${projectInfo.valuationDate}'
                                                                    pattern='yyyy-MM-dd'/></label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">项目类别</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.projectTypeName}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">委托目的备注</label>
                    <div class="col-sm-11">
                        <label class="form-control">${projectInfo.remarkEntrustPurpose}</label></div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">紧急程度</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.urgencyName}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">价值类型</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.valueTypeName}</label>
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
                    <label class="col-sm-1 control-label">价值类型备注</label>
                    <div class="col-sm-11">
                        <label class="form-control">${projectInfo.remarkValueType}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">省</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.provinceName}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">市</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.cityName}</label>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">县</label>
                    <div class="col-sm-3">
                        <label class="form-control">${projectInfo.districtName}</label>
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
<script>
    $(function () {
        //---------
        FileUtils.getFileShows({
            target: "attachmentProjectInfoId",
            formData: {
                tableName: AssessDBKey.ProjectInfo,
                tableId: '${empty projectInfo.id?0:projectInfo.id}'
            },
            deleteFlag: false
        })
    });
</script>
