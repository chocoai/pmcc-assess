<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/7/16
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<form id="frm_project_info" class="form-horizontal" enctype="multipart/form-data">
    <input type="hidden" id="projectId" name="id" value="${projectInfo.id}">
    <input type="hidden" name="projectClassId" value="${projectInfo.projectClassId}">
    <input type="hidden" name="projectTypeId" value="${projectInfo.projectTypeId}">
    <input type="hidden" name="projectCategoryId" value="${projectInfo.projectCategoryId}">
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">项目名称<span class="symbol required"></span></label>
            <div class="col-sm-11">
                <input required placeholder="项目名称" id="projectName" name="projectName"
                       value="${projectInfo.projectName}" class="form-control">
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">委托目的<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <select id="entrustPurpose" name="entrustPurpose" class="form-control"
                        onchange="ProjectInfoInit.prototype.entrustPurposeChange(this);"
                        required="required">
                    <option value="">请选择</option>
                    <c:forEach items="${list_entrustment_purpose}" var="item">
                        <c:choose>
                            <c:when test="${item.id == projectInfo.entrustPurpose}">
                                <option value="${item.id}" selected="selected">${item.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${item.id}">${item.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">评估基准日<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input required="required" placeholder="评估基准日" id="valuationDate"
                       name="valuationDate" data-date-format="yyyy-mm-dd"
                       class="form-control date-picker dbdate" readonly="readonly"
                       value="<fmt:formatDate value='${projectInfo.valuationDate}' pattern='yyyy-MM-dd'/>">
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">委托目的描述</label>
            <div class="col-sm-11">
                                     <textarea id="remarkEntrustPurpose" name="remarkEntrustPurpose"
                                               class="form-control"
                                               placeholder="委托目的描述">${projectInfo.remarkEntrustPurpose}</textarea>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">价值类型<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <select id="valueType" name="valueType" class="form-control" required="required"
                        onchange="ProjectInfoInit.prototype.valueTypeChange(this);">
                    <option selected="selected" value="">请选择</option>
                    <c:forEach items="${value_type}" var="item">
                        <c:choose>
                            <c:when test="${item.id == projectInfo.valueType}">
                                <option value="${item.id}" selected="selected">${item.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${item.id}">${item.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">紧急程度<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <select id="urgency" name="urgency" class="form-control" required="required">
                    <option value="">请选择</option>
                    <c:forEach items="${project_initiate_urgency}" var="item">
                        <c:choose>
                            <c:when test="${item.id==projectInfo.urgency}">
                                <option value="${item.id}" selected="selected">${item.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${item.id}">${item.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
        </div>


        <div class="x-valid">
            <label class="col-sm-1 control-label">执业部门<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <div class="input-group">
                    <input type="hidden" id="departmentId" name="departmentId"
                           value="${projectInfo.departmentId}">
                    <input id='departmentName' class='form-control' required="required"
                           readonly="readonly" maxlength="200" onclick="ProjectInfoInit.prototype.selectDepartment();"
                           value="${projectInfo.departmentName}">
                    <span class="input-group-btn">
                                                <button type="button" class="btn btn-default docs-tooltip"
                                                        onclick="ProjectInfoInit.prototype.selectDepartment();"
                                                        data-toggle="tooltip"
                                                        data-original-title="选择">
                                                    <i class="fa fa-search"></i>
                                                </button>
                                                 <button type="button" class="btn btn-default docs-tooltip"
                                                         onclick="$(this).closest('.input-group').find('input').val('');"
                                                         data-toggle="tooltip" data-original-title="清除">
                                                    <i class="fa fa-trash-o"></i>
                                                </button>
                                                </span>
                </div>
            </div>
        </div>

    </div>
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">价值类型备注</label>
            <div class="col-sm-11">
                                     <textarea id="remarkValueType" name="remarkValueType"
                                               class="form-control"
                                               placeholder="委托目的描述">${projectInfo.remarkValueType}</textarea>
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">省<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <select id="province" name="province" class="form-control search-select select2"
                        required="required">
                    <option value="" name="province">-请选择-</option>
                    <c:forEach items="${ProvinceList}" var="item">
                        <c:choose>
                            <c:when test="${item.areaId == projectInfo.province}">
                                <option value="${item.areaId}"
                                        selected="selected">${item.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${item.areaId}">${item.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">市<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <select id="city" name="city" class="form-control search-select select2"
                        required="required">

                </select>
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">县</label>
            <div class="col-sm-3">
                <select id="district" name="district" class="form-control search-select select2">

                </select>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">项目经理</label>
            <div class="col-sm-3">
                <div class="input-group">
                    <input type="hidden" id="userAccountManager" name="userAccountManager"
                           value="${projectInfo.projectMemberVo.userAccountManager}">
                    <input type="text" class="form-control" readonly="readonly"
                           value="${projectInfo.projectMemberVo.userAccountManagerName}"
                           onclick="ProjectInfoInit.prototype.selectUserAccountManager();"
                           id="userAccountManagerName" maxlength="200">
                    <span class="input-group-btn">
                                            <button type="button" class="btn btn-default docs-tooltip"
                                                    data-toggle="tooltip"
                                                    data-original-title="选择"
                                                    onclick="ProjectInfoInit.prototype.selectUserAccountManager();">
                                            <i class="fa fa-search"></i>
                                            </button>
                                            <button type="button" class="btn btn-default docs-tooltip"
                                                    onclick="$(this).closest('.input-group').find('input').val('');"
                                                    data-toggle="tooltip" data-original-title="清除">
                                            <i class="fa fa-trash-o"></i>
                                            </button>
                                        </span>
                </div>
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">
                                    <span class="checkbox-inline">
                                        <input type="checkbox" id="userAccountMemberCheckBox">
                                        <label for="userAccountMemberCheckBox">下级分派</label>
                                    </span>
            </label>
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">项目说明</label>
            <div class="col-sm-11">
                                    <textarea id="remarks" name="remarks"
                                              class="form-control" placeholder="项目说明">${projectInfo.remarks}</textarea>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">项目成员</label>
            <div class="col-sm-11">
                <input type="hidden" id="userAccountMember" name="userAccountMember"
                       value="${projectInfo.projectMemberVo.userAccountMember}">
                <input type="text" id="userAccountMemberName" class="form-control"
                       readonly="readonly"
                       onclick="ProjectInfoInit.prototype.selectUserAccountMember();"
                       value="${projectInfo.projectMemberVo.userAccountMemberName}">
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">附件</label>
            <div class="col-sm-3">
                <input id="attachmentProjectInfoId" name="attachmentProjectInfoId"
                       required="required" placeholder="上传附件" class="form-control" type="file">
                <div id="_attachmentProjectInfoId"></div>
            </div>
        </div>
    </div>
</form>
</body>
<script>
    $(document).ready(function () {
        ProjectInfoInit.prototype.init();
        FileUtils.uploadFiles({
            target: "attachmentProjectInfoId",
            disabledTarget: "btn_submit",
            formData: {
                tableName: AssessDBKey.ProjectInfo,
                tableId: ${empty projectInfo?0:projectInfo.id},
                projectId: "${projectPlanDetails.projectId}",
                creater: "${currUserAccount}"
            },
            deleteFlag: true
        });
        FileUtils.getFileShows({
            target: "attachmentProjectInfoId",
            formData: {
                tableName: AssessDBKey.ProjectInfo,
                tableId: ${empty projectInfo?0:projectInfo.id},
                projectId: "${projectPlanDetails.projectId}",
                creater: "${currUserAccount}"
            },
            deleteFlag: true
        })
    });


    function ProjectInfoInit() {

    }

    ProjectInfoInit.prototype.init = function () {
        AssessCommon.initAreaInfo({
            provinceTarget: $("#province"),
            cityTarget: $("#city"),
            districtTarget: $("#district"),
            provinceValue: '${projectInfo.province}',
            cityValue: '${projectInfo.city}',
            districtValue: '${projectInfo.district}'
        })
    };
    //加载价值类型描述
    ProjectInfoInit.prototype.valueTypeChange = function (_this) {
        AssessCommon.getDataDicInfo($(_this).val(), function (data) {
            if (data) {
                $("#remarkValueType").val(data.remark);
            }
        })
    };
    //加载委托目的描述
    ProjectInfoInit.prototype.entrustPurposeChange = function (_this) {
        AssessCommon.getDataDicInfo($(_this).val(), function (data) {
            if (data) {
                $("#remarkEntrustPurpose").val(data.remark);
            }
        })
    };
    //执业部门
    ProjectInfoInit.prototype.selectDepartment = function () {
        erpDepartment.select({
            onSelected: function (nodes) {
                $("#departmentId").val(nodes[0].id);
                $("#departmentName").val(nodes[0].text);
            }
        });
    }

    //项目经理
    ProjectInfoInit.prototype.selectUserAccountManager = function () {
        erpEmployee.select({
            onSelected: function (data) {
                $("#userAccountManagerName").val(data.name);
                $("#userAccountManager").val(data.account);
            }
        });
    }

    //项目成员
    ProjectInfoInit.prototype.selectUserAccountMember = function () {
        var value = formatToUnderline($("#userAccountMemberName").val(), $("#userAccountMember").val());
        erpEmployee.select({
            multi: true,
            value: value,
            onSelected: function (data) {
                $("#userAccountMember").val(data.account);
                $("#userAccountMemberName").val(data.name);
            }
        });
    }
</script>
</html>
