<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-4-17
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="en" class="no-js">
<div class="x_panel">
    <div class="x_title">
        <h2> 项目信息</h2>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div class="form-horizontal">
            <input type="hidden" id="projectId" name="id" value="${csrProjectInfo.id}">
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">项目名称</label>
                    <div class="col-sm-11">
                        <label class="form-control">${csrProjectInfo.name}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        委托单位
                    </label>
                    <div class="col-sm-3">
                        <div class="input-group">
                            <input type="text" value="${csrProjectInfo.entrustmentUnitName}"
                                   class="form-control" required="required" readonly="readonly">
                        </div>
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">项目类型</label>
                    <div class="col-sm-3">
                        <input type="text" value="${csrProjectInfo.projectTypeName}"
                               class="form-control" required="required" readonly="readonly">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">客户类型</label>
                    <div class="col-sm-3">
                        <input type="text" value="${csrProjectInfo.customerTypeName}"
                               class="form-control" required="required" readonly="readonly">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">委托目的</label>
                    <div class="col-sm-3">
                        <input type="text" value="${csrProjectInfo.entrustPurposeName}"
                               class="form-control" required="required" readonly="readonly">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">取行序号</label>
                    <div class="col-sm-3">
                        <input type="text" value="${csrProjectInfo.startRowNumber}"
                               class="form-control" required="required" readonly="readonly">
                    </div>
                </div>

                <div class="x-valid">
                    <label class="col-sm-1 control-label">评估基准日</label>
                    <div class="col-sm-3">
                        <input type="text" value="<fmt:formatDate value='${csrProjectInfo.valuationDate}'/>"
                               class="form-control" readonly="readonly">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">项目分配人</label>
                    <div class="col-sm-3">
                        <label class="form-control">${csrProjectInfo.distributionUserName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">项目类别</label>
                    <div class="col-sm-3">
                        <label class="form-control">${csrProjectInfo.projectCategoryName}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">项目说明</label>
                    <div class="col-sm-11">
                        <label class="form-control">${csrProjectInfo.remark}</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">客户附件</label>
                    <div class="col-sm-11">
                        <div id="_upload_file"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        FileUtils.getFileShows({
            target: "upload_file",
            formData: {
                tableName: "tb_csr_project_info",
                tableId: '${csrProjectInfo.id}'
            },
            deleteFlag: false
        });
    });
</script>